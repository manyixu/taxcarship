package com.derun.taxchangeconfirm.service.impl;

import java.util.List;

import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.BaseChangeConfirmResInfo;
import com.derun.beans.TaxCarCount;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.chk.BaseChangeConfirmChk;
import com.derun.common.init.CfgLoader;
import com.derun.common.match.O_N_Encapsulation;
import com.derun.common.match.TaxChangComfirm_Consistency;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.DateUtil;
import com.derun.common.util.LogUtil;
import com.derun.hdk.HDK_DAO;
import com.derun.loggedout.dao.LoggedOutDao;
import com.derun.loggedoutbusiness.LoggedOutBusiness;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSBGXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.monitors.List_Monitor;
import com.derun.taxchangeconfirm.dao.impl.TaxChangeConfirmDAO_Common;
import com.derun.taxchangeconfirm.dao.impl.TaxChangeConfirmDao;
import com.derun.taxchangeconfirm.dao.impl.TaxChangeConfirm_Common;
import com.derun.taxchangeconfirm.service.TaxChangeConfirmService;

/**
 *@author MILI
 *@time 2014-5-9 15:18:25
 *@描述：批改确认 服务
 */
public class TaxChangeConfirmServiceImpl implements TaxChangeConfirmService {
	List_Monitor list_mon = new List_Monitor();
	BaseChangeConfirmChk bccChk = new BaseChangeConfirmChk();	// 用户名密码 和 入参有效性验证
	TaxChangeConfirmDao taxCC = new TaxChangeConfirmDao();		// 批改确认 封装写库类方法
	TaxChangeConfirm_Common taxcc_com = new TaxChangeConfirm_Common();		// 批改确认 封装 公共
	TaxChangeConfirmDAO_Common taxccdao_com = new TaxChangeConfirmDAO_Common();
	TaxChangComfirm_Consistency taxchang_cons = new TaxChangComfirm_Consistency();
	O_N_Encapsulation o_n_emcap = new O_N_Encapsulation();
	LoggedOutDao logged = new LoggedOutDao();
	HDK_DAO hdk_dao = new HDK_DAO();
	LogUtil log = new LogUtil("批改确认服务开始");
	@Override
	public BaseChangeConfirmResInfo taxChangeConfirm(BaseChangeConfirmReqInfo basechangeconfirmreqinfo) {
		List<SYJK_CCS_RKMX> list_rkmx = null , listrkmx_q = null ;
		boolean secondInsure = true ;	// 续保标志  secondInsure	 true 为续保，false不为续保
		SYJK_CCS_RKMX rkmx = null ;
		SYJK_CCS_CCSBGXX bgxx = null ;
		Tax_Type taxInfo = null ;
		String returncode = "" ;  // 返回码
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.startLog();
		}
		String changeType = basechangeconfirmreqinfo.getChangeType() == null ? "15" : basechangeconfirmreqinfo.getChangeType();// 获取变更类型
		BaseChangeConfirmResInfo basechangeconfirmresinfo = new BaseChangeConfirmResInfo(); 
		// 车船税注销服务 start
		if(("5".equals(basechangeconfirmreqinfo.getChangeType()) || "6".equals(basechangeconfirmreqinfo.getChangeType())
				|| "7".equals(basechangeconfirmreqinfo.getChangeType())) && (!"2".equals(basechangeconfirmreqinfo.getCalcTaxFlag())
				|| !"3".equals(basechangeconfirmreqinfo.getCalcTaxFlag()))){
			LoggedOutBusiness loggedoutbusiness = new LoggedOutBusiness();
			return loggedoutbusiness.loggedOutBusiness(basechangeconfirmreqinfo);
		}
		// 车船税注销服务 end
		returncode = bccChk.N_P_checking(basechangeconfirmreqinfo);
		if(ChkUtil.CHK_0000.equals(returncode)){	// 用户名密码 效验正确
			String BCFlag = taxcc_com.getDecondInsure(basechangeconfirmreqinfo);	// 批改确认 变更 还是 补传
			if("1".equals(BCFlag)){	// 批改补传 
				returncode = bccChk.Join_valid(basechangeconfirmreqinfo,null);	// 批改确认入参有效性校验
				
				if(ChkUtil.CHK_0000.equals(returncode)){	// 效验通过 
					if(basechangeconfirmreqinfo.getTaxInfo().getTaxAmount().getTaxDealCode() == null){
						return  taxCC.CC_Object(null, ChkUtil.CHK_8423, null,null,null,null) ; 
					}
					boolean flag = taxCC.Write_Bank(basechangeconfirmreqinfo,null, null, null, null, "", "", "", 0, "BC");
					if(flag){		// 插库成功
						basechangeconfirmresinfo = taxCC.CC_Object(null, returncode, null,null,null,null);
					}else{			// 插库失败
						return taxCC.CC_Object(null, returncode, null,null,null,null) ;
					}
				}else{		// 效验未通过
					return taxCC.CC_Object(null, returncode, null,null,null,null) ;
				}
			}else{	// 批改确认
				String acompany = basechangeconfirmreqinfo.getCompanyCode();	// 4位公司代码
				String aacre = basechangeconfirmreqinfo.getAreaCode();			// 6位国际区域代码
				TaxDealCode_Type TaxDealCode = TaxCarCount.getTaxqueryNo("1", acompany, aacre, "D");
				
				String taxChangeQuryNo = basechangeconfirmreqinfo.getChangeQueryNo() == null ? "" : basechangeconfirmreqinfo.getChangeQueryNo().getTaxDealCode_Type();
				SYJK_CCS_CCSBGCXCCJB bgcxcc = taxccdao_com.getCCSBGCXCCJB(taxChangeQuryNo) ;		// 车船税变更查询出参信息
				if(bgcxcc == null){			// 无效变更查询码
					return taxCC.CC_Object(null, ChkUtil.CHK_8424, null,null,null,null) ;
				}
				list_rkmx = logged.getSYJK_CCS_RKMX(bgcxcc.getTAXCONFIRMNO());
				bgxx = taxccdao_com.getTaxConditionCode(taxChangeQuryNo);
				
				String taxConfirmNo = bgcxcc.getTAXCONFIRMNO();	// 获取变更查询码对应的确认码
				double changeSumTax = bgcxcc.getCHANGESUMTAX();	// 获取变更后实际所缴纳的总金额
				
				
				int number = list_rkmx.size();
				taxInfo = basechangeconfirmreqinfo.getTaxInfo();
				if(list_rkmx != null && number > 0){
					rkmx = list_rkmx.get(number - 1) ;		// 取得最后一条记录
//					// 确认时 未申报的必传车船税标签
//					if(rkmx != null && !"1".equals(rkmx.getPLATFORMSTATE()) && taxInfo == null){
//						return taxCC.CC_Object(null, ChkUtil.CHK_9999, null,null,null,null) ;
//					}
				}
				String platformstate = "" ;  // 申报状态   mili 2014-12-22 16:31:19
				if("1".equals(rkmx.getPLATFORMSTATE()) || "2".equals(rkmx.getPLATFORMSTATE())){
					platformstate = "1" ;  	// 投保申报 批改没有申报 mili 2014-12-22 16:31:19
					if(taxInfo == null){	// 已申报taxInfo可以为空 ，补添taxInfo信息
						basechangeconfirmreqinfo.setTaxInfo(taxcc_com.getTaxType(bgxx,bgcxcc));
					}
				}
				String TAXCONDITIONCODE = bgxx.getTAXCONDITIONCODE();
				if(null != rkmx.getTAXCONDITIONCODE() && rkmx.getTAXCONDITIONCODE().equals("R")
						&& null != TAXCONDITIONCODE && TAXCONDITIONCODE.equals("P")){
					if(taxInfo == null){	// 已申报taxInfo可以为空 ，补添taxInfo信息
						basechangeconfirmreqinfo.setTaxInfo(taxcc_com.getTaxType(bgxx,bgcxcc));
					}
				}
				if("P".equals(basechangeconfirmreqinfo.getTaxInfo().getTaxConditionCode())){
					if (rkmx.getTAXDEPARTMENT() == null || "".equals(rkmx.getTAXDEPARTMENT())
							|| rkmx.getTAXDEPARTMENTCODE() == null || "".equals(rkmx.getTAXDEPARTMENTCODE())
							|| rkmx.getTAXDOCUMENTNUMBER() == null || "".equals(rkmx.getTAXDOCUMENTNUMBER())) {
						secondInsure = false;
					}
				}
				if(basechangeconfirmreqinfo.getChangeConfirmNo() != null
						&& basechangeconfirmreqinfo.getChangeConfirmNo().getTaxDealCode_Type() != null){
					String changeConfirmNo = basechangeconfirmreqinfo.getChangeConfirmNo().getTaxDealCode_Type();
					if (changeConfirmNo.startsWith("3")	&& changeConfirmNo.substring(5, 11).equals("140000")) {
						returncode = "1";
					} else {
						returncode = bccChk.Join_valid(basechangeconfirmreqinfo,null);	// 批改确认入参有效性校验
					}
				}else{
					if("".equals(returncode)){
						returncode = "1" ;
					}else{
						returncode = bccChk.Join_valid(basechangeconfirmreqinfo,null);	// 批改确认入参有效性校验
					}
				}
				if(!"1".equals(returncode)){    // 基本信息效验未通过
					return taxCC.CC_Object(null, returncode, null,null,null,null) ;
				}else{
					String BGsjcjrq = DateUtil.getStringDate(bgcxcc.getSJCJRQ(),"yyyy-MM-dd HH:mm:ss").substring(0, 19);
					if(rkmx != null && rkmx.getSTATUSDATE() != null 
							&& "1".equals(rkmx.getRefusetype())
							&& rkmx.getSTATUSDATE().compareTo(BGsjcjrq) > 0){
						// 对应的投保确认已申报，批改时未申报，批改确认前申报，此时需重新询价
						basechangeconfirmresinfo = taxCC.CC_Object(null, ChkUtil.CHK_8420, null,null,null,null);
					}
//					else if(rkmx != null && rkmx.getSJCJRQ() != null  
//							&& "1".equals(rkmx.getRefusetype())
//							&& rkmx.getSJCJRQ().compareTo(BGsjcjrq) > 0){
//						// 对应的投保确认已申报，批改时未申报，批改确认前申报，此时需重新询价
//						returncode = ChkUtil.CHK_8416 ;
//						basechangeconfirmresinfo = taxCC.CC_Object(null, returncode, null);
//					}
					else{
						// 一致性效验
						returncode = taxchang_cons.Consistency_N_O(o_n_emcap.setNEW(basechangeconfirmreqinfo),o_n_emcap.setOLD(bgxx,bgcxcc),rkmx,basechangeconfirmreqinfo);
						if("1".equals(returncode)){
							String middleNo = "" ;	// 
							String ChangeConfirmNo = TaxDealCode.getTaxDealCode_Type();
							String agoNo = ChangeConfirmNo.substring(0, 11);
							String endNo = ChangeConfirmNo.substring(12,ChangeConfirmNo.length());
							middleNo = agoNo + "G" + endNo ; 
							TaxDealCode_Type TaxDealCodeP = new TaxDealCode_Type();
							TaxDealCodeP.setTaxDealCode_Type(middleNo);
							
							// 是否要插入数据库													// 新增加 退保确认时不管预确认问题 只有批改确认时 有预确认的判断  MILI 2014-11-3
							if("1".equals(basechangeconfirmreqinfo.getINSURE_CONFORM_FLAG()) || "4".equals(changeType)){
								// 投保申报 批改没有申报 mili 2014-12-22 16:31:19
								boolean flag = taxCC.Write_Bank(basechangeconfirmreqinfo,TaxDealCode, rkmx, bgcxcc, TaxDealCode, taxConfirmNo,platformstate,"1", changeSumTax,"QR");
								if(flag){
									// 数据监控 	mili 2014-6-26 17:45:29
									list_mon.addMonitor(new SYJK_CCS_RKMX()); 
									basechangeconfirmresinfo = taxCC.CC_Object(TaxDealCode, returncode, TaxDealCodeP,basechangeconfirmreqinfo,bgcxcc,rkmx) ;
									// 新加 一个核定库总开关  mili 2015年8月26日15:57:57
									if("Y".equals(CfgLoader.getConfigValue("SysSwitch", "HDK_Flag").trim())){
										if ("Y".equals(CfgLoader.getConfigValue("SysSwitch", "InsertFlag").trim())) {
	//										if ("Y".equals(bgcxcc.getISINSERT())) {
												// mili 2015-4-1 09:09:55 车辆信息插入核定库 start
												hdk_dao.Insert_carinfo(bgcxcc,middleNo,"BG"); // 同步插入
												// new Hdk_ThreadInsert(ccscxccjb,middleNo,"BG").start(); // 异步插入
												// mili 2015-4-1 09:09:55 车辆信息插入核定库 end
	//										}
										}
									}
								}else{
									return taxCC.CC_Object(null, returncode, null,null,null,null) ;
								}
							}else{
								list_mon.addMonitor(new SYJK_CCS_RKMX()); 
								basechangeconfirmresinfo = taxCC.CC_Object(TaxDealCode, returncode, TaxDealCode,basechangeconfirmreqinfo,bgcxcc,rkmx) ;
							}
							// 插库
						}else{
							return taxCC.CC_Object(null, returncode, null,null,null,null) ;
						}
					}
				}
			}
		}else{	// 用户名密码 效验失败
			return taxCC.CC_Object(null, returncode, null,null,null,null) ;
		}
		
		
		// 事后处理（冲正、）
// 		HashMap mmap = new HashMap();
// 		mmap.put("Req", basechangeconfirmreqinfo);
// 		mmap.put("Res", basechangeconfirmresinfo);
// 		TaxChangeConfirmServiceTraceThread asd = new TaxChangeConfirmServiceTraceThread(mmap);
// 	    Thread tt = new Thread(asd);
// 	    tt.start();
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.endLog();
		}
		return basechangeconfirmresinfo;
	}

}
