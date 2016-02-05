package com.derun.loggedoutbusiness;

import java.util.List;

import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.BaseChangeConfirmResInfo;
import com.derun.beans.TaxCarCount;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.chk.LoggedOutChk;
import com.derun.common.util.ChkUtil;
import com.derun.loggedout.dao.LoggedOutDao;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-8-22 9:05:19
 * @描述：注销业务类
 * */
public class LoggedOutBusiness {
	LoggedOutChk loggedoutchk = new LoggedOutChk();
	LoggedOutDao loggedoutdao = new LoggedOutDao();
	/**
	 * @author MILI
	 * @time 2014-8-22 9:07:08
	 * @描述：注销业务
	 * */
	public BaseChangeConfirmResInfo loggedOutBusiness(BaseChangeConfirmReqInfo basechangeconfirmreqinfo) {
		BaseChangeConfirmResInfo basechangeconfirmresinfo = new BaseChangeConfirmResInfo();
		String returnCode = loggedoutchk.N_P_checking(basechangeconfirmreqinfo);
		String ChangeConfirmNo = "" ;
		String confirmNo = basechangeconfirmreqinfo.getChangeConfirmNo().getTaxDealCode_Type() ;
		TaxDealCode_Type TaxDealCode = TaxCarCount.getTaxqueryNo("1", basechangeconfirmreqinfo.getCompanyCode(),basechangeconfirmreqinfo.getAreaCode(), "D");
		if (!ChkUtil.CHK_0000.equals(returnCode)) {			//	用户名 密码 效验 失败
			basechangeconfirmresinfo.setChangeConfirmNo(new TaxDealCode_Type());
			basechangeconfirmresinfo.setReturnCode(returnCode);
			basechangeconfirmresinfo.setTaxPrintNo(new TaxDealCode_Type());
			return basechangeconfirmresinfo;
		} else {	//	用户名 密码 效验 成功  进入到 注销服务
			if("6".equals(basechangeconfirmreqinfo.getChangeType())){		// 进入投保注销
//				if("B".equals(confirmNo.substring(11, 12))){
				// 投保确认码
				List<SYJK_CCS_RKMX> list = loggedoutdao.getSYJK_CCS_RKMX(confirmNo);
				if(list != null && list.size() > 0){
					SYJK_CCS_RKMX rkmx = list.get(0);
					if("1".equals(rkmx.getPLATFORMSTATE())){	//  已申报的不能注销
						returnCode = ChkUtil.CHK_8473 ;
						basechangeconfirmresinfo.setChangeConfirmNo(new TaxDealCode_Type());
						basechangeconfirmresinfo.setReturnCode(returnCode);
						basechangeconfirmresinfo.setTaxPrintNo(new TaxDealCode_Type());
						return basechangeconfirmresinfo;
					}
					if("P".equals(rkmx.getTAXCONDITIONCODE())){	// 	已完税的不能注销
						returnCode = ChkUtil.CHK_8474 ;
						basechangeconfirmresinfo.setChangeConfirmNo(new TaxDealCode_Type());
						basechangeconfirmresinfo.setReturnCode(returnCode);
						basechangeconfirmresinfo.setTaxPrintNo(new TaxDealCode_Type());
						return basechangeconfirmresinfo;
					}
					boolean bool = loggedoutdao.updateSurrenderDeclaredstatus(confirmNo,"0");
					if(bool){	//  注销成功
						returnCode = ChkUtil.CHK_0000 ;
						ChangeConfirmNo = TaxDealCode.getTaxDealCode_Type();
					}else{	//	不符合注销,即注销失败或者是重复注销！
						returnCode = ChkUtil.CHK_8418 ;
					}
				}else{	// 不符合注销,即注销失败或者是重复注销！
					returnCode = ChkUtil.CHK_8418 ;
				}
			}else if("5".equals(basechangeconfirmreqinfo.getChangeType())){		//  进入退保注销
				if("D".equals(confirmNo.substring(11, 12))){
					SYJK_CCS_RKMX rkmx = loggedoutdao.getChangeSYJK_CCS_RKMX(confirmNo);
					if(rkmx != null){
						boolean bool = loggedoutdao.updateSurrenderDeclaredstatus(confirmNo,"1");
						if(bool){	// 注销成功
							returnCode =  ChkUtil.CHK_0000 ;
							ChangeConfirmNo = TaxDealCode.getTaxDealCode_Type();
						}else{		// 不符合注销,即注销失败或者是重复注销！
							returnCode = ChkUtil.CHK_8418 ;
						}
					}else{
						returnCode = ChkUtil.CHK_8418 ;
					}
				}else{
					returnCode = ChkUtil.CHK_8418 ;
				}
			}else if("7".equals(basechangeconfirmreqinfo.getChangeType())){		// 	进入批改注销
				SYJK_CCS_RKMX rkmx = loggedoutdao.getChangeSYJK_CCS_RKMX(confirmNo);
				boolean bool = false ;
				if("D".equals(confirmNo.substring(11, 12))){
					//先判断是否有过申报
					if(rkmx != null && ("1".equals(rkmx.getPLATFORMSTATE()) || "2".equals(rkmx.getPLATFORMSTATE()))
							&& "0".equals(rkmx.getLOGGEDOUT())){		// 	已申报过的进行批改注销
						String QRSTATUSDATE = "1111-01-01 00:00:00" ;
						String QRSJCJRQ = "1111-01-01 00:00:00" ;
						if(rkmx != null){
							QRSTATUSDATE = rkmx.getSTATUSDATE().substring(0,19);
							QRSJCJRQ = rkmx.getSJCJRQ().substring(0,19);
						}
						if(QRSTATUSDATE.compareTo(QRSJCJRQ) > 0){	// 投保查询投保确认---批改查询批改确认------申报--------此时是不可以注销的
							returnCode = ChkUtil.CHK_8473 ;
						}else{
							//	----投保查询投保确认----申报-----批改查询批改确认-------此时是可以注销的。
							bool = loggedoutdao.updateSurrenderDeclaredstatus(confirmNo,"1");
							if(bool){
								returnCode = ChkUtil.CHK_0000 ;
								ChangeConfirmNo = TaxDealCode.getTaxDealCode_Type();
							}else{	// 	不符合注销,即注销失败或者是重复注销！
								returnCode = ChkUtil.CHK_8418 ;
							}
						}
					}else{
						// 未申报过的做批改注销
						bool = loggedoutdao.updateSurrenderDeclaredstatus(confirmNo,"1");
						if(bool){	//	注销成功
							returnCode = ChkUtil.CHK_0000 ;
							ChangeConfirmNo = TaxDealCode.getTaxDealCode_Type();
						}else{		//	不符合注销,即注销失败或者是重复注销！
							returnCode = ChkUtil.CHK_8418 ;
						}
					}	
				}else{
					returnCode = ChkUtil.CHK_8418;
				}
			}
			basechangeconfirmresinfo.setReturnCode(returnCode);
			TaxDealCode_Type ConfirmNo = new TaxDealCode_Type();
			ConfirmNo.setTaxDealCode_Type(ChangeConfirmNo);
			basechangeconfirmresinfo.setChangeConfirmNo(ConfirmNo);
			basechangeconfirmresinfo.setTaxPrintNo(new TaxDealCode_Type());
			return basechangeconfirmresinfo;
		}	
	}
}
