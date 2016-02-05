package com.derun.taxconfirm.dao.impl;

import java.util.Date;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseConfirmReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
import com.derun.model.po.SYJK_CCS_RKMX;
/**
 * @author MILI
 * @time 2014-3-20 15:04:40
 * @描述：插入对象 SYJK_CCS_RKMX 封装
 * */
public class P_SYJK_CCS_RKMX {
	/**
	 * @author MILI
	 * @time 2014-3-20 15:04:47
	 * @描述：插入对象 SYJK_CCS_RKMX 封装
	 * */
	public SYJK_CCS_RKMX getrkmx(BaseConfirmReqInfo baseconfirmReqinfo,
			SYJK_CCS_CCSCXCCJBXX cxccjbxx,TaxDealCode_Type codetype){
		
		AnnualTax_Type delinquentTaxDue = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue();
		String taxConfirmNo = codetype.getTaxDealCode_Type();
//		String carSerialNo = baseconfirmReqinfo.getCarSerialNo() == null ? "" : baseconfirmReqinfo.getCarSerialNo();	// 问题名单序列号
		String carSerialNo = "" ;
		if(cxccjbxx != null){
			carSerialNo = cxccjbxx.getCARSERIALNO() ;
		}
		SYJK_CCS_RKMX rkmx = new SYJK_CCS_RKMX();
		String Ptzt = "";
		String BCFlag = "2";
		if (baseconfirmReqinfo.getTaxConfirmNo() != null) {
			if (baseconfirmReqinfo.getTaxConfirmNo().getTaxDealCode_Type() != null
					&& !"".trim().equals(
							baseconfirmReqinfo.getTaxConfirmNo()
									.getTaxDealCode_Type().trim())) {
				BCFlag = "1";
			}
		}
		if (baseconfirmReqinfo.getCalcTaxFlag() != null
				&& baseconfirmReqinfo.getCalcTaxFlag().equals("1")
				&& "2".equals(BCFlag)) {
			rkmx.setTAXQUERYNO(taxConfirmNo);					// 车船税查询码
			rkmx.setTAXCONFIRMNO(taxConfirmNo);					// 车船税确认码
			if ("R".equals(baseconfirmReqinfo.getTaxInfo()
					.getTaxConditionCode())) {
				rkmx.setTAXPRINTNO(null);						// 车船税打印码
			} else {
				String agoNo = taxConfirmNo.substring(0, 11);
				String endNo = taxConfirmNo
						.substring(12, taxConfirmNo.length());
				String middleNo = agoNo + "G" + endNo;
				rkmx.setTAXPRINTNO(middleNo);					// 车船税打印码
			}
			rkmx.setVIN(cxccjbxx.getVIN());						// VIN
			rkmx.setHPHM(cxccjbxx.getHPHM());					// HPHM
			rkmx.setHPZL(cxccjbxx.getHPZL());					// HPZL
			rkmx.setCLLX(cxccjbxx.getCLLX());					// CLLX

			rkmx.setMOTORUSAGETYPECODE(cxccjbxx.getMOTORUSAGETYPECODE()); 	// 使用性质
			rkmx.setMODEL(cxccjbxx.getMODEL()); 					// 车辆型号
			rkmx.setVEHICLETYPE(cxccjbxx.getVEHICLETYPE()); 		// 交管车辆类型
			rkmx.setRATEDPASSENGERCAPACITY(cxccjbxx.getRATEDPASSENGERCAPACITY());// 核定载客数
			rkmx.setTONNAGE(cxccjbxx.getTONNAGE());				// 核定载质量
			rkmx.setWHOLEWEIGHT(cxccjbxx.getWHOLEWEIGHT());		// 整备质量
			rkmx.setDISPLACEMENT(cxccjbxx.getDISPLACEMENT());	// 排量
			rkmx.setPOWER(cxccjbxx.getPOWER());					// 功率
			rkmx.setFUELTYPE(cxccjbxx.getFUELTYPE()); 			// 源种类
			// mili 2015-5-19 15:20:09
//			rkmx.setSJCJRQ(DateUtil.getStringDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
		} else {	//补传
			rkmx.setTAXQUERYNO( taxConfirmNo);	// 车船税查询码
			rkmx.setTAXCONFIRMNO(taxConfirmNo);	// 车船税确认码
			if (baseconfirmReqinfo.getTaxPrintNo() == null) {
				rkmx.setTAXPRINTNO(null);
			} else {
				rkmx.setTAXPRINTNO(baseconfirmReqinfo.getTaxPrintNo().getTaxDealCode_Type());	// 车船税打印码
			}
			if (baseconfirmReqinfo.getVehicleInfo() != null) {
				rkmx.setVIN(baseconfirmReqinfo.getVehicleInfo().getVIN());						// VIN
				rkmx.setHPHM(baseconfirmReqinfo.getVehicleInfo().getLicensePlateNo());			// HPHM
				rkmx.setHPZL(baseconfirmReqinfo.getVehicleInfo().getLicensePlateType());		// HPZL
				rkmx.setCLLX(baseconfirmReqinfo.getVehicleInfo().getMotorTypeCode());			// CLLX
				
				rkmx.setMOTORUSAGETYPECODE(baseconfirmReqinfo.getVehicleInfo().getMotorUsageTypeCode()); 	// 使用性质
				rkmx.setMODEL(baseconfirmReqinfo.getVehicleInfo().getModel()); 					// 车辆型号
				rkmx.setVEHICLETYPE(baseconfirmReqinfo.getVehicleInfo().getVehicleType()); 		// 交管车辆类型
				rkmx.setRATEDPASSENGERCAPACITY(baseconfirmReqinfo.getVehicleInfo().getRatedPassengerCapacity());// 核定载客数
				rkmx.setTONNAGE(baseconfirmReqinfo.getVehicleInfo().getTonnage());				// 核定载质量
				rkmx.setWHOLEWEIGHT(baseconfirmReqinfo.getVehicleInfo().getWholeWeight());		// 整备质量
				rkmx.setDISPLACEMENT(baseconfirmReqinfo.getVehicleInfo().getDisplacement());	// 排量
				rkmx.setPOWER(baseconfirmReqinfo.getVehicleInfo().getPower());					// 功率
				rkmx.setFUELTYPE(baseconfirmReqinfo.getVehicleInfo().getFuelType()); 			// 源种类
				
			} else {
				rkmx.setVIN(null);			// VIN
				rkmx.setHPHM(null);			// HPHM
				rkmx.setHPZL(null);			// HPZL
				rkmx.setCLLX(null);			// CLLX
				
				rkmx.setMOTORUSAGETYPECODE(null); 		// 使用性质
				rkmx.setMODEL(null); 					// 车辆型号
				rkmx.setVEHICLETYPE(null); 				// 交管车辆类型
				rkmx.setRATEDPASSENGERCAPACITY(0.0);	// 核定载客数
				rkmx.setTONNAGE(0.0);					// 核定载质量
				rkmx.setWHOLEWEIGHT(0.0);				// 整备质量
				rkmx.setDISPLACEMENT(0.0);				// 排量
				rkmx.setPOWER(0.0);						// 功率
				rkmx.setFUELTYPE(null); 				// 源种类

			}
//			// 确认补传时 截取确认码的时间 作为系统采集日期 mili 2015-5-19 15:20:09
//			String sjcjrq = taxConfirmNo.substring(12, 26);
//			rkmx.setSJCJRQ(DateUtil.getStringDate(DateUtil.change_Date(sjcjrq),"yyyy-MM-dd HH:mm:ss"));
		}
		rkmx.setTAXCONDITIONCODE(baseconfirmReqinfo.getTaxInfo().getTaxConditionCode());						// 纳税类型代码
		rkmx.setTAXREGISTRYNUMBER(baseconfirmReqinfo.getTaxInfo().getTaxRegistryNumber());						// 税务登记证号
		rkmx.setTAXPAYERNAME(baseconfirmReqinfo.getTaxInfo().getTaxPayerName());								// 纳税人名称
		rkmx.setTAXPAYERIDENTIFICATIONCODE(baseconfirmReqinfo.getTaxInfo().getTaxPayerIdentificationCode());	// 纳税人识别号
		if (baseconfirmReqinfo.getTaxInfo() != null
				&& !"".equals(baseconfirmReqinfo.getTaxInfo())
				&& baseconfirmReqinfo.getTaxInfo().getPayCompanyCode() != null
				&& !"".equals(baseconfirmReqinfo.getTaxInfo()
						.getPayCompanyCode())) {
			rkmx.setPAYCOMPANYCODE(baseconfirmReqinfo.getTaxInfo().getPayCompanyCode());		// 代收公司
		} else if (baseconfirmReqinfo.getCompanyCode() != null
				&& !"".equals(baseconfirmReqinfo)) {
			rkmx.setPAYCOMPANYCODE(baseconfirmReqinfo.getCompanyCode());						// 代收公司
		} else {
			if (baseconfirmReqinfo.getTaxQueryNo() != null
					&& baseconfirmReqinfo.getTaxQueryNo().getTaxDealCode_Type() != null
					&& !"".equals(baseconfirmReqinfo.getTaxQueryNo()
							.getTaxDealCode_Type())) {
				String taxqueryno = baseconfirmReqinfo.getTaxQueryNo()
						.getTaxDealCode_Type();
				taxqueryno = taxqueryno.substring(1, 5);

				rkmx.setPAYCOMPANYCODE(taxqueryno);		// 代收公司
			} else {
				rkmx.setPAYCOMPANYCODE(null);			// 代收公司
			}
		}
		if (delinquentTaxDue != null) {
			rkmx.setTAXLOCATIONCODE(delinquentTaxDue.getTaxLocationCode());		// 纳税地区代码
			String startDate = delinquentTaxDue.getTaxStartDate();
			String endDate = delinquentTaxDue.getTaxEndDate();
			if (startDate != null && startDate.length() >= 10) {
				startDate = startDate.substring(0, 10);
			} 
			if (endDate != null && endDate.length() >= 10) {
				endDate = endDate.substring(0, 10);
			} 
			rkmx.setTAXSTARTDATE(startDate);
			rkmx.setTAXENDDATE(endDate);
			rkmx.setTAXUNITTYPECODE(delinquentTaxDue.getTaxUnitTypeCode());		// 计税单位代码
			rkmx.setUNITRATE(delinquentTaxDue.getUnitRate());					// 单位计税金额
			rkmx.setANNUALTAXAMOUNT(delinquentTaxDue.getAnnualTaxAmount());		// 当期年单位税额
			if (delinquentTaxDue.getPaid() != null) {
				rkmx.setTAXDEPARTMENTCODE(delinquentTaxDue.getPaid().getTaxDepartmentCode());	// 开具减免税(完税)凭证的税务机关代码
				rkmx.setTAXDEPARTMENT(delinquentTaxDue.getPaid().getTaxDepartment());			// 开具减免税(完税)凭证的税务机关名称
				rkmx.setTAXDOCUMENTNUMBER(delinquentTaxDue.getPaid().getTaxDocumentNumber());	// 完税凭证号码
			} else {
				rkmx.setTAXDEPARTMENTCODE(null);	// 开具减免税(完税)凭证的税务机关代码
				rkmx.setTAXDEPARTMENT(null);		// 开具减免税(完税)凭证的税务机关名称
				rkmx.setTAXDOCUMENTNUMBER(null);	// 完税凭证号码
			}
			if (delinquentTaxDue.getDerate() != null) {
				rkmx.setDEDUCTIONDEPARTMENTCODE(delinquentTaxDue.getDerate().getTaxDepartmentCode());	// 开具减免税(完税)凭证的税务机关代码
				rkmx.setDEDUCTIONDEPARTMENT(delinquentTaxDue.getDerate().getTaxDepartment());			// 开具减免税(完税)凭证的税务机关名称
				rkmx.setDEDUCTIONDUECODE(delinquentTaxDue.getDerate().getDeductionDueCode());	// 减免税原因代码
				rkmx.setDEDUCTIONDUETYPE(delinquentTaxDue.getDerate().getDeductionDueType());	// 减免税方案代码
				rkmx.setDEDUCTIONDUEPROPORTION(delinquentTaxDue.getDerate().getDeductionDueProportion());	// 减免比例
				rkmx.setDEDUCTION(delinquentTaxDue.getDerate().getDeduction());								// 减免金额
				rkmx.setDEDUCTIONDOCUMENTNUMBER(delinquentTaxDue.getDerate().getDeductionDocumentNumber());	// 减免税凭证号
			} else {
				rkmx.setDEDUCTIONDEPARTMENTCODE(null);			// 开具减免税(完税)凭证的税务机关代码
				rkmx.setDEDUCTIONDEPARTMENT(null);				// 开具减免税(完税)凭证的税务机关名称
				rkmx.setDEDUCTIONDUECODE(null);				// 减免税原因代码
				rkmx.setDEDUCTIONDUETYPE(null);				// 减免税方案代码
				rkmx.setDEDUCTIONDUEPROPORTION(0.0);		// 减免比例
				rkmx.setDEDUCTION(0.0);						// 减免金额
				rkmx.setDEDUCTIONDOCUMENTNUMBER(null);		// 减免税凭证号
			}

			rkmx.setTAXDUE(delinquentTaxDue.getTaxDue());	// 当期应纳税额
			String ExceedDate = delinquentTaxDue.getExceedDate();
			if (ExceedDate != null && ExceedDate.length() >= 10) {
				ExceedDate = ExceedDate.substring(0, 10);
			} 
			rkmx.setEXCEEDDATE(ExceedDate);
			rkmx.setEXCEEDDAYSCOUNT(delinquentTaxDue.getExceedDaysCount());		// 逾期天数
			rkmx.setOVERDUE(delinquentTaxDue.getOverDue());						// 滞纳金
			rkmx.setTOTALAMOUNT(delinquentTaxDue.getTotalAmount());				// 合计金额
		} else {
			rkmx.setTAXLOCATIONCODE(null);				// 纳税地区代码
			rkmx.setTAXSTARTDATE(null);
			rkmx.setTAXENDDATE(null);
			rkmx.setTAXUNITTYPECODE(null);				// 计税单位代码
			rkmx.setUNITRATE(0.0);						// 单位计税金额
			rkmx.setANNUALTAXAMOUNT(0.0);				// 当期年单位税额
			rkmx.setTAXDEPARTMENTCODE(null);			// 开具减免税(完税)凭证的税务机关代码
			rkmx.setTAXDEPARTMENT(null);				// 开具减免税(完税)凭证的税务机关名称
			rkmx.setTAXDOCUMENTNUMBER(null);			// 完税凭证号码
			rkmx.setTAXDEPARTMENTCODE(null);			// 开具减免税(完税)凭证的税务机关代码
			rkmx.setTAXDEPARTMENT(null);				// 开具减免税(完税)凭证的税务机关名称
			rkmx.setDEDUCTIONDUECODE(null);				// 减免税原因代码
			rkmx.setDEDUCTIONDUETYPE(null);				// 减免税方案代码
			rkmx.setDEDUCTIONDUEPROPORTION(0.0);		// 减免比例
			rkmx.setDEDUCTION(0.0);						// 减免金额
			rkmx.setDEDUCTIONDOCUMENTNUMBER(null);		// 减免税凭证号
			rkmx.setTAXDUE(0.0);						// 当期应纳税额
			rkmx.setEXCEEDDATE(null);
			rkmx.setEXCEEDDAYSCOUNT(0);					// 逾期天数
			rkmx.setOVERDUE(0.0);						// 滞纳金
			rkmx.setTOTALAMOUNT(0.0);					// 合计金额
		}
		rkmx.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));			// 操作员
		rkmx.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
		rkmx.setSJCJRQ(DateUtil.getStringDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
		rkmx.setPAYDATE(baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getTaxStartDate().substring(0, 4));		// 所属年度
		rkmx.setTAXAMOUNT_FLAG(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getTaxAmount_Flag());
		rkmx.setANNUALTAXDUE(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getAnnualTaxDue());
		rkmx.setSUMTAXDEFAULT(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getSumTaxDefault());
		rkmx.setSUMOVERDUE(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getSumOverdue());
		rkmx.setSUMTAX(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getSumTax());
		rkmx.setLOGGEDOUT("0");							// 注销状态
		String Refusetype = "1";
		if ("R".equals(baseconfirmReqinfo.getTaxInfo().getTaxConditionCode())) {
			Ptzt = "4";
			Refusetype = "0";
		} else if ("P".equals(baseconfirmReqinfo.getTaxInfo()
				.getTaxConditionCode())) {
			Ptzt = "1";
		} else {
			Ptzt = "0";
		}
		rkmx.setPLATFORMSTATE(Ptzt);			// 平台 0=代收 1=申报（完税）
		rkmx.setCHANGETYPE("0");				// 变更类型 0=确认 4=退保，2=批改
		rkmx.setCOUNTTAXTYPE(baseconfirmReqinfo.getCalcTaxFlag());	// 算税标志
		rkmx.setRefusetype(Refusetype);
		if ("1".equals(baseconfirmReqinfo.getCalcTaxFlag())
				&& "2".equals(BCFlag)) {
			String fisdate = "";
			if (cxccjbxx != null && cxccjbxx.getFIRSTREGISTERDATE() != null) {
				fisdate = DateUtil.getStringDate(cxccjbxx.getFIRSTREGISTERDATE(),null);
			}
			if ("P".equals(baseconfirmReqinfo.getTaxInfo().getTaxConditionCode())) {
				rkmx.setSTATUSDATE(DateUtil.getStringDate(new Date(),null));
			} else {
				rkmx.setSTATUSDATE(null);
			}
			rkmx.setFIRSTREGISTERDATE(fisdate);
			rkmx.setSPECIALCARTYPE(cxccjbxx.getSPECIALCARTYPE());
			rkmx.setTSBZ(cxccjbxx.getTSBZ());	
			rkmx.setCARSERIALNO(carSerialNo);
			rkmx.setEngineNo(cxccjbxx.getENGINENO());
		} else {
			String fisdate = "";
			if (baseconfirmReqinfo.getVehicleInfo() != null
					&& baseconfirmReqinfo.getVehicleInfo()
							.getFirstRegisterDate() != null) {
				if (baseconfirmReqinfo.getVehicleInfo().getFirstRegisterDate()
						.length() >= 10) {
					fisdate = baseconfirmReqinfo.getVehicleInfo()
							.getFirstRegisterDate().substring(0, 10);
				}
			}
			if ("P".equals(baseconfirmReqinfo.getTaxInfo()
					.getTaxConditionCode())){
				rkmx.setSTATUSDATE(DateUtil.getStringDate(new Date(),null));
			}else{
				rkmx.setSTATUSDATE(null);
			}
			if (baseconfirmReqinfo.getVehicleInfo() == null) {
				rkmx.setFIRSTREGISTERDATE(fisdate);
				rkmx.setSPECIALCARTYPE(null);
				rkmx.setTSBZ("0");
				rkmx.setEngineNo(null);
			} else {
				rkmx.setFIRSTREGISTERDATE(fisdate);
				rkmx.setSPECIALCARTYPE(baseconfirmReqinfo.getVehicleInfo().getSpecialCarType());
//				rkmx.setSPECIALCARTYPE("0");
				rkmx.setTSBZ("0");   // 没有保存 退税标志 mili 2014-12-17 17:21:57 
				rkmx.setEngineNo(baseconfirmReqinfo.getVehicleInfo().getEngineNo());
			}
			rkmx.setCARSERIALNO(carSerialNo);				// 问题序列号
		}
		rkmx.setInsureStartDate(baseconfirmReqinfo.getInsureStartDate());	// 投保开始日期
		rkmx.setInsureEndDate(baseconfirmReqinfo.getInsureEndDate());		// 投保结束日期
		rkmx.setCARMATCHID(baseconfirmReqinfo.getCarMatchId());				// 车辆匹配ID
		rkmx.setCityCode(baseconfirmReqinfo.getCityCode());	//税款归属（地市）wbzhao 20150126
		rkmx.setCountryCode(baseconfirmReqinfo.getCountryCode());//税款归属（县区）wbzhao 20150126
		return rkmx ;
	}
}
