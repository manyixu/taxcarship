package com.derun.common.car;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.nopaytax.Nopaytax;
import com.derun.common.util.DateUtil;
import com.derun.model.po.Query_ChangeQurey;
import com.derun.model.po.SYJK_CCS_CODE;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_RKMX_QS;
import com.derun.model.po.SYJK_CCS_WSDJXX;
import com.derun.taxchangequery.dao.impl.TaxChangeQueryDAO_Common;
import com.derun.taxchangequery.dao.impl.TaxChangeQuery_Common;
/**
 *@author MILI
 *@time 2014-5-4 
 *@描述：TaxCarType 封装类 
 * */
public class CarType_Assist {
	Nopaytax nopaytax = new Nopaytax();			// 欠税信息
	TaxChangeQueryDAO_Common taxchangeDao = new TaxChangeQueryDAO_Common();		// 本地数据库交互类 公共
	TaxChangeQuery_Common taxchange_com = new TaxChangeQuery_Common();			// 公共封装类
	/**
	 *@author MILI
	 *@time 2014-4-11 17:57:53
	 *@描述：封装 Vehicle_Type 
	 * */
	public Vehicle_Type getVT(Query_ChangeQurey bqri){
		Vehicle_Type VT = new Vehicle_Type();
		if(bqri != null){
			VT = bqri.getVehicleInfo();
		}
		return VT ;
	}
	/**
	 *@author MILI
	 *@time 2014-4-11 17:57:53
	 *@描述：封装 Tax_Type 
	 * */
	public Tax_Type getTT(Query_ChangeQurey bqri){
		Tax_Type TT = new Tax_Type();
		if(bqri != null){
			if(bqri.getTaxInfo() != null){
				TT = bqri.getTaxInfo();
			}
		}
		return TT ;
	}
	/**
	 *@author MILI
	 *@time 2014-4-11 17:57:53
	 *@描述：封装 AnnualTax_Type[] 欠税信息
	 * */
	public AnnualTax_Type[] getAT(Query_ChangeQurey bqri,SYJK_CCS_RKMX rkmx,String ServiceType,boolean flag,SYJK_CCS_WSDJXX wsdj){
		SYJK_CCS_RKMX_QS[] rkmx_qs = null ;			// 欠税对象
		AnnualTax_Type[] AT = null ;				// 年度纳税数据类型
		int number = 0 ;
//		if(!listWt.isEmpty() && listWt.size() > 0){
//			AT = this.getA_AT(listWt);
//		}else{
			rkmx_qs = nopaytax.getNoPayTax1(bqri,rkmx,ServiceType,wsdj);	// 欠税信息
			if(rkmx_qs != null && rkmx_qs.length > 0){
				number = rkmx_qs.length ; 
			}
			AT = new AnnualTax_Type[number];
			for(int i = 0 ; i < number ; i++){
				AnnualTax_Type ann = new AnnualTax_Type();
				ann.setTaxStartDate(DateUtil.getStringDate(rkmx_qs[i].getTAXSTARTDATE(),null));	//	逾期始期
				ann.setTaxEndDate(DateUtil.getStringDate(rkmx_qs[i].getTAXENDDATE(),null));		//	逾期止期
//				ann.setExceedDate(DateUtil.getStringDate(rkmx_qs[i].getEXCEEDDATE(),null));		//	逾期时间
//				ann.setExceedDaysCount(rkmx_qs[i].getEXCEEDDAYSCOUNT());						//	逾期天数
				if(flag){	// 如果是节能车的情况下  以往年的缴税也按节能车计算
					ann.setDerate(bqri.getTaxInfo().getCurrentTaxDue().getDerate());
					ann.setPaid(bqri.getTaxInfo().getCurrentTaxDue().getPaid());
				}
				AT[i] = ann ;
			}
//		}
		return AT ;
	}
//	/**
//	 * @author MILI
//	 * @time 2014-5-27 15:19:06
//	 * @描述：问题名单 封装 欠税信息
//	 * */
//	public AnnualTax_Type[] getA_AT(List<SYJK_CCS_QGCLWTMDXX> listWt){
//		AnnualTax_Type[] AT = new AnnualTax_Type[listWt.size()] ;	// 年度纳税数据类型
//		for(int i = 0 ; i < listWt.size() ; i++){
//			AnnualTax_Type at = new AnnualTax_Type();
//			SYJK_CCS_QGCLWTMDXX wtmd = listWt.get(i);
//			at.setAnnualTaxAmount(wtmd.getDQNDWSE());			// 当期年单位税额
////			at.setDerate(wtmd.get);								// 适用减免税对象
//			at.setExceedDate(DateUtil.getStringDate(wtmd.getYQSJ(),null) );	// 逾期时间
//			at.setExceedDaysCount(wtmd.getYQTS().intValue());				// 逾期天数
//			at.setOverDue(wtmd.getZNJ());		// 滞纳金
////			at.setPaid(wtmd.get);				// 适用完税对象
//			at.setTaxDue(wtmd.getDQYNSE());		// 当期应纳税额
//			at.setTaxEndDate(DateUtil.getStringDate(wtmd.getSKSSZQ(), "yyyy-MM-dd"));	// 税款所属止期
////			at.setTaxLocationCode(wtmd.get);		// 纳税地区代码
//			at.setTaxStartDate(DateUtil.getStringDate(wtmd.getSKSSSQ(), "yyyy-MM-dd"));	// 税款所属始期
//			at.setTaxUnitTypeCode(wtmd.getJSDW());	// 计税单位代码
//			at.setTotalAmount(wtmd.getHEJE());		// 合计金额
//			at.setUnitRate(wtmd.getDWJSJE());		// 单位计税金额
//			AT[i] = at ;
//		}
//		return AT;
//	}
	/**
	 * @author MILI
	 * @time 2014-4-15 17:24:11
	 * @描述：不同类型的 对应封装
	 * */
	public Query_ChangeQurey getObject(Object obj){
		Query_ChangeQurey qc = new Query_ChangeQurey();
		boolean flag = false ;
		//	投保查询查询服务--请求信息对象入参
		try{
			BaseQueryReqInfo bqrib = (BaseQueryReqInfo) obj;
			qc.setAreaCode(bqrib.getAreaCode());
			qc.setCarMatchId(bqrib.getCarMatchId());
			qc.setCompanyCode(bqrib.getCompanyCode());
			qc.setInsureEndDate(bqrib.getInsureEndDate());
			qc.setInsureStartDate(bqrib.getInsureStartDate());
			qc.setPassword(bqrib.getPassword());
			qc.setQuerySequencetTime(bqrib.getQuerySequencetTime());
			qc.setTaxInfo(bqrib.getTaxInfo());
			qc.setUserName(bqrib.getUserName());
			
			bqrib.getVehicleInfo().setEngineNo(bqrib.getVehicleInfo().getEngineNo() == null ? null :bqrib.getVehicleInfo().getEngineNo().toUpperCase());
			bqrib.getVehicleInfo().setLicensePlateNo(bqrib.getVehicleInfo().getLicensePlateNo() == null ? null :bqrib.getVehicleInfo().getLicensePlateNo().toUpperCase());
			bqrib.getVehicleInfo().setVIN(bqrib.getVehicleInfo().getVIN() == null ? null :bqrib.getVehicleInfo().getVIN().toUpperCase());
			
			qc.setVehicleInfo(bqrib.getVehicleInfo());
			qc.setVehicleOwnerInfo(null);
			qc.setChangeType(null);
			qc.setTaxConfirmNo(null);
		}catch(Exception e){
			flag = true ;
		}
		if(flag){
			//	变更查询服务--请求信息对象入参
			try{
				BaseChangeQueryReqInfo bqcri = (BaseChangeQueryReqInfo) obj;
				qc.setAreaCode(bqcri.getAreaCode());
				qc.setCarMatchId(bqcri.getCarMatchId());
				qc.setCompanyCode(bqcri.getCompanyCode());
				qc.setInsureEndDate(bqcri.getInsureEndDate());
				qc.setInsureStartDate(bqcri.getInsureStartDate());
				qc.setPassword(bqcri.getPassword());
				qc.setTaxInfo(bqcri.getTaxInfo());
				qc.setUserName(bqcri.getUserName());
				
				bqcri.getVehicleInfo().setEngineNo(bqcri.getVehicleInfo().getEngineNo() == null ? null :bqcri.getVehicleInfo().getEngineNo().toUpperCase());
				bqcri.getVehicleInfo().setLicensePlateNo(bqcri.getVehicleInfo().getLicensePlateNo() == null ? null :bqcri.getVehicleInfo().getLicensePlateNo().toUpperCase());
				bqcri.getVehicleInfo().setVIN(bqcri.getVehicleInfo().getVIN() == null ? null :bqcri.getVehicleInfo().getVIN().toUpperCase());
				
				
				qc.setVehicleInfo(bqcri.getVehicleInfo());
				qc.setVehicleOwnerInfo(bqcri.getVehicleOwnerInfo());
				qc.setChangeType(bqcri.getChangeType());
				qc.setTaxConfirmNo(bqcri.getTaxConfirmNo());
				qc.setQuerySequencetTime(null);
			}catch(Exception e){}
		}
		return qc ;
	}
	/**
	 * @author MILI
	 * @param carNo 车牌
	 * @return boolean
	 * @time 2014-6-20 11:06:15
	 * @描述： 本地车 外地车 判断  true 本地车    false 外地车（空号牌和法定免税车都按本地车处理 ?）
	 */
	public static boolean Car_Tpye(String carNo){
		boolean flag = true ;
		if(carNo != null && !"".equals(carNo)){
			String bd_car = CfgLoader.getConfigValue("SysParam", "LocalCarNo");	 // 本地车标志
			flag = carNo.startsWith(bd_car);				// 本地车 or 外地车  
			String flag_Local = CfgLoader.getConfigValue("SysParam", "LocalCarNo_X");
			String Loacl[] = flag_Local.split("/");
			if(flag && "0".equals(Loacl[0])){		// 是本地车 并且 开关是 0 打开的状态 在判断 MILI 2014-12-12 09:23:13 
				String s[] = Loacl[1].split(",");
				for(int i = 0 ; i < s.length ; i++){
					if(s[i].equals(carNo.substring(1,2))){
						flag = false ;
						break ;
					}
				}
			}
		}else{
			// 1.无车牌投保，按外地车算，只收本年车船税。  mili 2015-1-30 10:39:06 start   ZYY
			flag = false ;
			// 1.无车牌投保，按外地车算，只收本年车船税。  mili 2015-1-30 10:39:06 end		ZYY
		}
		return flag ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-1 10:05:27
	 * @描述： 去除之前有缴税的年份
	 * */
	public AnnualTax_Type[] getAT(AnnualTax_Type[] annual,List<SYJK_CCS_RKMX> listRk){
		AnnualTax_Type[] annualtax = annual ;
		int rkmxtime = 0 ;
		int listtime = 0 ;
		List<AnnualTax_Type> list = new ArrayList<AnnualTax_Type>();
		if(listRk != null && listRk.size() > 0){
			SYJK_CCS_RKMX rkmx = listRk.get(0);
			listtime = Integer.parseInt(rkmx.getPAYDATE().substring(0, 4));
			for(AnnualTax_Type ann : annual){
				rkmxtime = Integer.parseInt(ann.getTaxStartDate().substring(0, 4));
				if(rkmxtime > listtime){
					list.add(ann);
				}
			}
			int number_a = list.size() ;
			if(number_a > 0){
				annualtax = new AnnualTax_Type[number_a];
			}else{
				annualtax = new AnnualTax_Type[0]; 
			}
			for(int i = 0 ;i < number_a ; i++){
				annualtax[i] = list.get(i);
			}
			// 最后一年缴税 按 1 月 1日 开始
			int number_b = annualtax.length ;
			if(number_b >= 1){
				annualtax[number_b - 1].setTaxStartDate(annualtax[number_b - 1].getTaxStartDate().substring(0, 4)+ "-01-01");
			}
		}else{
			annualtax = annual ;
		}
		// 针对提前续保  如果
		if(listtime > rkmxtime){
			annualtax = new AnnualTax_Type[0]; 
		}
		return annualtax ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-4 12:30:49
	 * @描述:节约能源车判断
	 * */
	public boolean Is_listcode_jy(List<SYJK_CCS_CODE> list_code,String model){
		boolean flag_jy = false ;
		int listsize = list_code.size();
		for(int i = 0;i < listsize;i++){
			SYJK_CCS_CODE code = list_code.get(i);
			if(model != null){
//				if(code.getCODEVALUE().equals(model)){
				if(model.contains(code.getCODEVALUE())){  // 填写的值  只要包含有配置信息 算匹配成功	
					flag_jy = true ;
					break ;
				}
			}
		}
		return flag_jy ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-4 12:30:49
	 * @描述:新能源车判断 
	 * */
	public boolean Is_listcode_xn(List<SYJK_CCS_CODE> list_code,String model){
		boolean flag_jy = false ;
		int listsize = list_code.size();
		for(int i = 0;i < listsize;i++){
			SYJK_CCS_CODE code = list_code.get(i);
			if(model != null){
//				if(code.getCODEVALUE().equals(model)){
				if(model.contains(code.getCODEVALUE())){  // 填写的值  只要包含有配置信息 算匹配成功		
					flag_jy = true ;
					break ;
				}
			}
				
		}
		return flag_jy ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-15 10:00:55
	 * @描述：提前续保 is ?
	 * */
	public boolean Xubao(String paydate){
		boolean flag = false ;
		int year_rkmx = Integer.parseInt(paydate);
		int year_sys = Integer.parseInt(DateUtil.getDateYYYY());
		if(year_sys + 1 == year_rkmx){
			flag = true ;
		}
		return flag ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-18 15:57:18
	 * @描述：追加欠税对象
	 * */
	public AnnualTax_Type[] setAT(AnnualTax_Type[] ATS ,AnnualTax_Type AT,Date T_START ,Date T_END,String firstTime,SYJK_CCS_WSDJXX wsdj){
		AnnualTax_Type[] Annua = null ;
		boolean flag = true ;
		String skssjsrq = "" ;
		if(wsdj != null){
			skssjsrq = DateUtil.getStringDate(wsdj.getSKSSJSRQ(),null).substring(0, 4);
		}
		if(AT == null){
			AT = new AnnualTax_Type();
		}
		if(ATS != null && ATS.length > 0){	// 欠税对象有值的情况下
			for(int j = 0 ; j < ATS.length ;j++){
				// 是否有 系统当前年的缴税纪录
				if(DateUtil.getDateYYYY().equals(ATS[j].getTaxStartDate().substring(0, 4))){
					flag = false ;
					break;
				}
			}
			if(flag){
				Annua = new AnnualTax_Type[ATS.length + 1] ;
			}else{
				Annua = new AnnualTax_Type[ATS.length] ;
			}
			
//			Annua = new AnnualTax_Type[ATS.length + 1] ;
			int i = 0 ;
			for(; i < ATS.length ; i++){
				Annua[i] = ATS[i];
			}
			
			if(flag){
				AT.setTaxStartDate(DateUtil.getDateYYYY() + "-01-01");
				AT.setTaxEndDate(DateUtil.getDateYYYY() + "-12-31");
				Annua[i] = AT ;
			}
		}
		else if(DateUtil.getDateYYYY().equals(skssjsrq)){
			Annua = new AnnualTax_Type[0] ;
		}
		else{						// 欠税对象无值的情况下
			Annua = new AnnualTax_Type[1] ;
			if(firstTime != null && !"".equals(firstTime)){
				if(firstTime.substring(0, 4).equals(DateUtil.getDateYYYY())){
					AT.setTaxStartDate(firstTime);
				}else{
					AT.setTaxStartDate(DateUtil.getDateYYYY() + "-01-01");
				}
			}else{
				AT.setTaxStartDate(DateUtil.getDateYYYY() + "-01-01");
			}
			AT.setTaxEndDate(DateUtil.getDateYYYY() + "-12-31");
			Annua[0] = AT ;
		}
		return Annua ;
	}
	/**
	 * @author MILI
	 * @time 2014-11-18 15:15:57
	 * @描述：提前续保 退短期后 再次投保 欠税对象不在计算 系统年以前的税款
	 * */
	public AnnualTax_Type[] get_AnnuaTax(AnnualTax_Type[] AnnualTax,String T_dq,SYJK_CCS_RKMX rkmx){
		AnnualTax_Type[] Annua = new AnnualTax_Type[1] ;
		if(AnnualTax != null && AnnualTax.length > 0){
			String date_yyyy = DateUtil.getDateYYYY();
			for(int i = 0 ; i < AnnualTax.length ; i++){
				if(date_yyyy.equals(AnnualTax[i].getTaxStartDate().substring(0, 4))){
					if("10".equals(T_dq)){
						AnnualTax[i].setTaxStartDate(DateUtil.getDIY("yyyy-MM-dd"));
					}else{
						AnnualTax[i].setTaxStartDate(rkmx.getSJCJRQ().substring(0, 10));
					}
					Annua[0] = AnnualTax[i] ;
					break ;
				}
			}
		}else{
			Annua = AnnualTax ;
		}
		return Annua ;
	} 
	/**
	 *@author MILI
	 *@time 2014-12-15 08:55:38
	 *@描述：封装入库明细 
	 * */
	public SYJK_CCS_RKMX setRKMX(SYJK_CCS_QGCLWTMDXX questionlist){
		SYJK_CCS_RKMX rkmx = new SYJK_CCS_RKMX();
//		rkmx.setTSBZ("");
//		rkmx.setANNUALTAXDUE(questionlist);				// 本年车船税金额
//		rkmx.setPLATFORMSTATE(questionlist);			// 平台 0=代收
//		rkmx.setRefusetype(questionlist);			 		// 拒缴标志
//		rkmx.setLOGGEDOUT(questionlist);					// 注销状态 0= 未注销 1=注销
//		rkmx.setPAYCOMPANYCODE(questionlist); 		// 代收公司
//		rkmx.setSTATUSDATE(questionlist); 				// 申报日期
//		rkmx.setCHANGETYPE(questionlist);					// 变更类型
//		rkmx.setCOUNTTAXTYPE(questionlist); 			// 算税标志
//		rkmx.setSPECIALCARTYPE(rs.getString("SPECIALCARTYPE"));
//		rkmx.setInsureStartDate(rs.getDate("INSURESTARTDATE"));
//		rkmx.setInsureEndDate(rs.getDate("INSUREENDDATE"));
//		rkmx.setTAXPRINTNO(questionlist);					// 打印码
//		rkmx.setTAXQUERYNO(questionlist);					// 车船税交易码
//		rkmx.setTAXREGISTRYNUMBER(questionlist);	// 税务登记证号
//		rkmx.setSUMTAXDEFAULT(questionlist);			// 合计欠税金额
//		rkmx.setTAXAMOUNT_FLAG(""); 	
//		rkmx.setTAXCONDITIONCODE(questionlist.getKJWSPZSWJGDM());		// 开具减免税凭证的税务机关代码
//		rkmx.setTAXCONFIRMNO(questionlist);				// 确认码
//		rkmx.setTAXLOCATIONCODE(questionlist); 		// 纳税地区代码
//		rkmx.setTAXPAYERIDENTIFICATIONCODE(questionlist);				// 纳税人识别号
		
		rkmx.setANNUALTAXAMOUNT(questionlist.getDQNDWSE());				// 当期年单位税额
		rkmx.setCLLX(questionlist.getCLZL()); 							// 车辆类型
		rkmx.setDEDUCTION(questionlist.getJMJE()); 						// 减免金额
		rkmx.setDEDUCTIONDEPARTMENT(questionlist.getSWJGMC()); 			// 开具减免税凭证的税务机关名称
		rkmx.setDEDUCTIONDEPARTMENTCODE(questionlist.getSWJGDM());		// 开具减免税凭证的税务机关代码
		rkmx.setDEDUCTIONDOCUMENTNUMBER(questionlist.getJMSPZH());		// 减免税凭证号
		rkmx.setDEDUCTIONDUECODE(questionlist.getJMSYY()); 				// 减免税原因代码
		rkmx.setDEDUCTIONDUEPROPORTION(questionlist.getJMBL());			// 减免比例
		rkmx.setDEDUCTIONDUETYPE(questionlist.getJMSFA());				// 减免税方案代码
		rkmx.setEXCEEDDATE(DateUtil.getStringDate(questionlist.getYQSJ(),null));					// 逾期时间
		rkmx.setEXCEEDDAYSCOUNT(questionlist.getYQTS().intValue()); 	// 逾期天数
		rkmx.setHPHM(questionlist.getHPHM()); 							// 号牌号码
		rkmx.setHPZL(questionlist.getHPZL()); 							// 号牌种类
		rkmx.setLOGINNAME(questionlist.getLOGINNAME());					// 操作员
		rkmx.setOVERDUE(questionlist.getZNJ()); 						// 滞纳金
		rkmx.setPAYDATE(questionlist.getND());							// 所属年度
		rkmx.setREVENUECODE(questionlist.getREVENUECODE());				// 9000 税务机关编码
		rkmx.setSJCJRQ(DateUtil.getStringDate(questionlist.getSJCJRQ(),null)); 						// 系统采集日期
		rkmx.setSUMOVERDUE(questionlist.getZNJ()); 						// 合计滞纳金
		rkmx.setSUMTAX(questionlist.getHEJE()); 						// 合计金额
		rkmx.setTAXDEPARTMENT(questionlist.getKJWSPZSWJGMC());			// 开具完税凭证的税务机关名称
		rkmx.setTAXDEPARTMENTCODE(questionlist.getKJWSPZSWJGDM());		// 开具完税凭证的税务机关代码
		rkmx.setTAXDOCUMENTNUMBER(questionlist.getWSPZHM());			// 完税凭证号码
		rkmx.setTAXDUE(questionlist.getDQYNSE());						// 当期应纳税额
		rkmx.setTAXENDDATE(DateUtil.getStringDate(questionlist.getYQSJ(),null));					// 逾期时间
		rkmx.setTAXPAYERNAME(questionlist.getCZMC());					// 纳税人名称
		rkmx.setTAXSTARTDATE(DateUtil.getStringDate(questionlist.getSKSSSQ(),null));				// 税款所属始期
		rkmx.setTAXENDDATE(DateUtil.getStringDate(questionlist.getSKSSZQ(),null));					// 税款所属止期
		rkmx.setTAXUNITTYPECODE(questionlist.getJSDW());				// 计税单位代码
		rkmx.setTOTALAMOUNT(questionlist.getHEJE());					// 合计金额
		rkmx.setUNITRATE(questionlist.getDWJSJE()); 					// 单位计税金额
		rkmx.setVIN(questionlist.getCLSBDM());							// Vin a
		rkmx.setFIRSTREGISTERDATE(DateUtil.getStringDate(questionlist.getCLCSDJRQ(),null));
		rkmx.setCARSERIALNO(questionlist.getYCYYDM());
		rkmx.setEngineNo(questionlist.getFDJH());						//发动机号
		rkmx.setMOTORUSAGETYPECODE(questionlist.getSYXZ()); 			// 使用性质
		rkmx.setMODEL(questionlist.getCLXH()); 							// 车辆型号
		rkmx.setVEHICLETYPE(questionlist.getJGCLLX()) ; 				// 交管车辆类型
		rkmx.setRATEDPASSENGERCAPACITY(questionlist.getHDZKS());		// 核定载客数
		rkmx.setTONNAGE(questionlist.getHDZZL());						// 核定载质量
		rkmx.setWHOLEWEIGHT(questionlist.getZBZL());					// 整备质量
		rkmx.setDISPLACEMENT(questionlist.getPL());						// 排量
		rkmx.setPOWER(questionlist.getGL());							// 功率
		rkmx.setFUELTYPE(questionlist.getRLZL()); 						// 源种类
		
		return rkmx ;
	}
}
