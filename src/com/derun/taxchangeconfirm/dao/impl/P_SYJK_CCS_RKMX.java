package com.derun.taxchangeconfirm.dao.impl;

import java.util.Date;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CODE;
import com.derun.model.po.SYJK_CCS_RKMX;
/**
 * @author MILI
 * @time 2014-3-21 9:50:35
 * @描述：插入对象 SYJK_CCS_RKMX 封装
 * */
public class P_SYJK_CCS_RKMX {
	/**
	 * @author MILI
	 * @time 2014-3-21 9:50:35
	 * @描述：插入对象 SYJK_CCS_RKMX 封装
	 * */
	public SYJK_CCS_RKMX getRkmx(BaseChangeConfirmReqInfo BCCRI,
			TaxDealCode_Type taxQueryNo, AnnualTax_Type delinquentTaxDue,
			String taxConfirmNo, SYJK_CCS_CCSBGCXCCJB bgcxccjb,
			String platformstate, String changeRes,SYJK_CCS_CODE code,String getTime){
		SYJK_CCS_RKMX rkmx = new SYJK_CCS_RKMX();
		Date fisdate = null ;
		String Ptzt = "";
		String middleNo = "";
		String BCFlag = "2";
		if (BCCRI.getChangeConfirmNo() != null) {
			if (BCCRI.getChangeConfirmNo().getTaxDealCode_Type() != null
					&& !"".trim().equals(
							BCCRI.getChangeConfirmNo().getTaxDealCode_Type()
									.trim())) {
				BCFlag = "1";
			}
		}
		if (BCCRI.getCalcTaxFlag() != null
				&& BCCRI.getCalcTaxFlag().equals("1") && "2".equals(BCFlag)) {
			String agoNo = taxConfirmNo.substring(0, 11);
			String endNo = taxConfirmNo.substring(12, taxConfirmNo.length());
			middleNo = agoNo + "G" + endNo;
			rkmx.setTAXQUERYNO(taxQueryNo.getTaxDealCode_Type());		// 车船税查询码
			rkmx.setTAXCONFIRMNO(taxConfirmNo);							// 车船税确认码
			if ("R".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				rkmx.setTAXPRINTNO(null);
			} else {
				rkmx.setTAXPRINTNO(middleNo);							// 车船税打印码
			}
			rkmx.setVIN(bgcxccjb.getVIN());		// VIN
			rkmx.setHPHM(bgcxccjb.getHPHM());	// HPHM
			rkmx.setHPZL(bgcxccjb.getHPZL());	// HPZL
			rkmx.setCLLX(bgcxccjb.getCLLX());	// CLLX
			
			rkmx.setMOTORUSAGETYPECODE(bgcxccjb.getMOTORUSAGETYPECODE()); 	// 使用性质
			rkmx.setMODEL(bgcxccjb.getMODEL()); 					// 车辆型号
			rkmx.setVEHICLETYPE(bgcxccjb.getVEHICLETYPE()); 		// 交管车辆类型
			rkmx.setRATEDPASSENGERCAPACITY(bgcxccjb.getRATEDPASSENGERCAPACITY());// 核定载客数
			rkmx.setTONNAGE(bgcxccjb.getTONNAGE());				// 核定载质量
			rkmx.setWHOLEWEIGHT(bgcxccjb.getWHOLEWEIGHT());		// 整备质量
			rkmx.setDISPLACEMENT(bgcxccjb.getDISPLACEMENT());	// 排量
			rkmx.setPOWER(bgcxccjb.getPOWER());					// 功率
			rkmx.setFUELTYPE(bgcxccjb.getFUELTYPE()); 			// 源种类
		} else {
			rkmx.setTAXQUERYNO(BCCRI.getChangeConfirmNo().getTaxDealCode_Type());
			rkmx.setTAXCONFIRMNO(BCCRI.getTaxInfo().getTaxAmount().getTaxDealCode().getTaxDealCode_Type());
			if (BCCRI.getTaxPrintNo() == null) {
				rkmx.setTAXPRINTNO(null);
			} else {
				rkmx.setTAXPRINTNO(BCCRI.getTaxPrintNo().getTaxDealCode_Type());		// 车船税打印码
			}
			if (BCCRI.getVehicleInfo() != null) {
				rkmx.setVIN(BCCRI.getVehicleInfo().getVIN());				// VIN
				rkmx.setHPHM(BCCRI.getVehicleInfo().getLicensePlateNo());	// HPHM
				rkmx.setHPZL(BCCRI.getVehicleInfo().getLicensePlateType());	// HPZL
				rkmx.setCLLX(BCCRI.getVehicleInfo().getMotorTypeCode());	// CLLX
				
				rkmx.setMOTORUSAGETYPECODE(BCCRI.getVehicleInfo().getMotorUsageTypeCode()); 	// 使用性质
				rkmx.setMODEL(BCCRI.getVehicleInfo().getModel()); 					// 车辆型号
				rkmx.setVEHICLETYPE(BCCRI.getVehicleInfo().getVehicleType()); 		// 交管车辆类型
				rkmx.setRATEDPASSENGERCAPACITY(BCCRI.getVehicleInfo().getRatedPassengerCapacity());// 核定载客数
				rkmx.setTONNAGE(BCCRI.getVehicleInfo().getTonnage());				// 核定载质量
				rkmx.setWHOLEWEIGHT(BCCRI.getVehicleInfo().getWholeWeight());		// 整备质量
				rkmx.setDISPLACEMENT(BCCRI.getVehicleInfo().getDisplacement());		// 排量
				rkmx.setPOWER(BCCRI.getVehicleInfo().getPower());					// 功率
				rkmx.setFUELTYPE(BCCRI.getVehicleInfo().getFuelType()); 			// 源种类
			} else {
				rkmx.setVIN(null);	// VIN
				rkmx.setHPHM(null);	// HPHM
				rkmx.setHPZL(null);	// HPZL
				rkmx.setCLLX(null);	// CLLX
				
				rkmx.setMOTORUSAGETYPECODE(null); 	// 使用性质
				rkmx.setMODEL(null); 				// 车辆型号
				rkmx.setVEHICLETYPE(null); 			// 交管车辆类型
				rkmx.setRATEDPASSENGERCAPACITY(0.0);// 核定载客数
				rkmx.setTONNAGE(0.0);				// 核定载质量
				rkmx.setWHOLEWEIGHT(0.0);			// 整备质量
				rkmx.setDISPLACEMENT(0.0);			// 排量
				rkmx.setPOWER(0.0);					// 功率
				rkmx.setFUELTYPE(null); 			// 源种类
			}
		}	
		rkmx.setTAXCONDITIONCODE(BCCRI.getTaxInfo().getTaxConditionCode());			// 纳税类型代码,Y
		rkmx.setTAXREGISTRYNUMBER(BCCRI.getTaxInfo().getTaxRegistryNumber());		// 税务登记证号
		rkmx.setTAXPAYERNAME(BCCRI.getTaxInfo().getTaxPayerName());					// 纳税人名称
		rkmx.setTAXPAYERIDENTIFICATIONCODE(BCCRI.getTaxInfo().getTaxPayerIdentificationCode());		// 纳税人识别号
		if (BCCRI.getTaxInfo().getPayCompanyCode() != null
				&& !"".equals(BCCRI.getTaxInfo().getPayCompanyCode())) {
			rkmx.setPAYCOMPANYCODE(BCCRI.getTaxInfo().getPayCompanyCode());			// 代收公司
		} else if (BCCRI.getCompanyCode() != null
				&& !"".equals(BCCRI.getCompanyCode())) {
			rkmx.setPAYCOMPANYCODE(BCCRI.getCompanyCode());			// 代收公司
		} else {
			rkmx.setPAYCOMPANYCODE(BCCRI.getChangeQueryNo().getTaxDealCode_Type().substring(1, 5));// 代收公司
		}
		if (delinquentTaxDue != null) {
			rkmx.setTAXLOCATIONCODE(delinquentTaxDue.getTaxLocationCode());	// 纳税地区代码
			String startDate = delinquentTaxDue.getTaxStartDate();
			String endDate = delinquentTaxDue.getTaxEndDate();
			if (startDate != null && startDate.length() >= 10) {
				startDate = startDate.substring(0, 10);
			} 
			if (endDate != null && endDate.length() >= 10) {
				endDate = endDate.substring(0, 10);
			} 
			rkmx.setTAXSTARTDATE(startDate);		// 税款所属始期
			rkmx.setTAXENDDATE(endDate);			// 税款所属止期
			rkmx.setTAXUNITTYPECODE(delinquentTaxDue.getTaxUnitTypeCode());		// 计税单位代码
			rkmx.setUNITRATE(delinquentTaxDue.getUnitRate());					// 单位计税金额
			rkmx.setANNUALTAXAMOUNT(delinquentTaxDue.getAnnualTaxAmount());		// 当期年单位税额
			if (delinquentTaxDue.getPaid() != null) {
				rkmx.setTAXDEPARTMENTCODE(delinquentTaxDue.getPaid().getTaxDepartmentCode());		// 开具减免税(完税)凭证的税务机关代码
				rkmx.setTAXDEPARTMENT(delinquentTaxDue.getPaid().getTaxDepartment());				// 开具减免税(完税)凭证的税务机关名称
				rkmx.setTAXDOCUMENTNUMBER(delinquentTaxDue.getPaid().getTaxDocumentNumber());		// 完税凭证号码
			} else {
				rkmx.setTAXDEPARTMENTCODE(null);		// 开具减免税(完税)凭证的税务机关代码
				rkmx.setTAXDEPARTMENT(null);			// 开具减免税(完税)凭证的税务机关名称
				rkmx.setTAXDOCUMENTNUMBER(null);		// 完税凭证号码
			}
			if (delinquentTaxDue.getDerate() != null) {
				rkmx.setTAXDEPARTMENTCODE(delinquentTaxDue.getDerate().getTaxDepartmentCode());		// 开具减免税(完税)凭证的税务机关代码
				rkmx.setTAXDEPARTMENT(delinquentTaxDue.getDerate().getTaxDepartment());				// 开具减免税(完税)凭证的税务机关名称
				rkmx.setDEDUCTIONDUECODE(delinquentTaxDue.getDerate().getDeductionDueCode());		// 减免税原因代码
				rkmx.setDEDUCTIONDUETYPE(delinquentTaxDue.getDerate().getDeductionDueType());		// 减免税方案代码
				rkmx.setDEDUCTIONDUEPROPORTION(delinquentTaxDue.getDerate().getDeductionDueProportion());		// 减免比例
				rkmx.setDEDUCTION(delinquentTaxDue.getDerate().getDeduction());						// 减免金额
				rkmx.setDEDUCTIONDOCUMENTNUMBER(delinquentTaxDue.getDerate().getDeductionDocumentNumber());		// 减免税凭证号
			} else {
				rkmx.setTAXDEPARTMENTCODE(null);			// 开具减免税(完税)凭证的税务机关代码
				rkmx.setTAXDEPARTMENT(null);				// 开具减免税(完税)凭证的税务机关名称
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
			rkmx.setEXCEEDDAYSCOUNT(delinquentTaxDue.getExceedDaysCount());
			rkmx.setOVERDUE(delinquentTaxDue.getOverDue());			// 滞纳金
			rkmx.setTOTALAMOUNT(delinquentTaxDue.getTotalAmount());	// 合计金额
		} else {
			rkmx.setTAXLOCATIONCODE(null);	// 纳税地区代码
			rkmx.setTAXSTARTDATE(null);		// 税款所属始期
			rkmx.setTAXENDDATE(null);			// 税款所属止期
			rkmx.setTAXUNITTYPECODE(null);		// 计税单位代码
			rkmx.setUNITRATE(0.0);					// 单位计税金额
			rkmx.setANNUALTAXAMOUNT(0.0);		// 当期年单位税额
			
			rkmx.setTAXDEPARTMENTCODE(null);		// 开具减免税(完税)凭证的税务机关代码
			rkmx.setTAXDEPARTMENT(null);			// 开具减免税(完税)凭证的税务机关名称
			rkmx.setTAXDOCUMENTNUMBER(null);		// 完税凭证号码
			
			rkmx.setTAXDEPARTMENTCODE(null);			// 开具减免税(完税)凭证的税务机关代码
			rkmx.setTAXDEPARTMENT(null);				// 开具减免税(完税)凭证的税务机关名称
			rkmx.setDEDUCTIONDUECODE(null);				// 减免税原因代码
			rkmx.setDEDUCTIONDUETYPE(null);				// 减免税方案代码
			rkmx.setDEDUCTIONDUEPROPORTION(0.0);		// 减免比例
			rkmx.setDEDUCTION(0.0);						// 减免金额
			rkmx.setDEDUCTIONDOCUMENTNUMBER(null);		// 减免税凭证号
			
			rkmx.setTAXDUE(0.0);						// 当期应纳税额
			rkmx.setEXCEEDDATE(null);
			rkmx.setEXCEEDDAYSCOUNT(0);
			rkmx.setOVERDUE(0.0);						// 滞纳金
			rkmx.setTOTALAMOUNT(0.0);					// 合计金额
		}
		
		
		rkmx.setLOGINNAME(code.getCODENAME());			// 操作员
		rkmx.setREVENUECODE(code.getCODENAME());
		rkmx.setSJCJRQ(DateUtil.getStringDate(new Date(),"yyyy-MM-dd hh:mm:ss"));
		if (BCCRI.getTaxInfo() != null
				&& BCCRI.getTaxInfo().getPayDate() != null
				&& !"".equals(BCCRI.getTaxInfo().getPayDate())) {
			rkmx.setPAYDATE(BCCRI.getTaxInfo().getPayDate());	// 所属年度
		} else {
			rkmx.setPAYDATE(delinquentTaxDue.getTaxStartDate().substring(0, 4));	// 所属年度
		}
		rkmx.setTAXAMOUNT_FLAG(BCCRI.getTaxInfo().getTaxAmount().getTaxAmount_Flag());
		rkmx.setANNUALTAXDUE(BCCRI.getTaxInfo().getTaxAmount().getAnnualTaxDue());
		rkmx.setSUMTAXDEFAULT(BCCRI.getTaxInfo().getTaxAmount().getSumTaxDefault());
		rkmx.setSUMOVERDUE(BCCRI.getTaxInfo().getTaxAmount().getSumOverdue());
		rkmx.setSUMTAX(BCCRI.getTaxInfo().getTaxAmount().getSumTax());
		rkmx.setLOGGEDOUT("0");// 注销状态
		String Refusetype = "1";
		if ("R".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
			Ptzt = "4";
			Refusetype = "0";
		} else if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode())
				|| "9".equals(BCCRI.getCalcTaxFlag())) {
			Ptzt = "1";
		} else {
			if (platformstate.equals("1")) {
				Ptzt = "1";
			} else {
				Ptzt = "0";
			}
		}
		rkmx.setPLATFORMSTATE(Ptzt);	// 平台 0=代收 1=申报（完税）
		String ct = "";
		if ("1".equals(BCCRI.getChangeType())) {
			ct = "4";
		} else {
			ct = BCCRI.getChangeType();
		}
		rkmx.setCHANGETYPE(ct);			// 变更类型 0=确认 4=退保，2=批改
		rkmx.setCOUNTTAXTYPE(BCCRI.getCalcTaxFlag());	// 算税标志 1=税源 2，3=平台（补传）
		rkmx.setRefusetype(Refusetype);
		if ("1".equals(BCCRI.getCalcTaxFlag()) && "2".equals(BCFlag)) {
			if (bgcxccjb != null && bgcxccjb.getFIRSTREGISTERDATE() != null) {
				fisdate = bgcxccjb.getFIRSTREGISTERDATE() ;
			}
			if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode())){
				rkmx.setSTATUSDATE(DateUtil.getStringDate(new Date(),null));
			}else{
				rkmx.setSTATUSDATE(null);
			}
			rkmx.setFIRSTREGISTERDATE(DateUtil.getStringDate(fisdate,null));
			rkmx.setSPECIALCARTYPE(bgcxccjb.getSPECIALCARTYPE());
			rkmx.setTSBZ(bgcxccjb.getTSBZ());
		} else {
			if (BCCRI.getVehicleInfo() != null
					&& BCCRI.getVehicleInfo().getFirstRegisterDate() != null) {
				fisdate = DateUtil.getStringDate(BCCRI.getVehicleInfo().getFirstRegisterDate(),null);
			}
			// 如果算税标志9（申报复用字段）
			if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				rkmx.setSTATUSDATE(DateUtil.getStringDate(new Date(),null));
			} else if ("9".equals(BCCRI.getCalcTaxFlag())) {
				rkmx.setSTATUSDATE(getTime);
			} else {
				rkmx.setSTATUSDATE(null);
			}
			String tsbz = "";
			if ("2".equals(BCCRI.getChangeType())) {
				tsbz = "0";
			} else if ("1".equals(BCCRI.getChangeType())) {
				tsbz = "1";
			} else {
				tsbz = "2";
			}
			rkmx.setFIRSTREGISTERDATE(DateUtil.getStringDate(fisdate,null));
			if(BCCRI.getVehicleInfo() != null){
				rkmx.setSPECIALCARTYPE(BCCRI.getVehicleInfo().getSpecialCarType());
			}else{
				rkmx.setSPECIALCARTYPE(null);
			}
			rkmx.setTSBZ(tsbz);
		}
		if(bgcxccjb != null || "".equals(bgcxccjb)){
			rkmx.setCARSERIALNO(bgcxccjb.getCARSERIALNO());
		}else{
//			rkmx.setCARSERIALNO(BCCRI.getCarSerialNo());		// 问题名单序列号 
			rkmx.setCARSERIALNO("");
		}
		if (changeRes.split(",")[0] != null
				&& !"".equals(changeRes.split(",")[0])) {
			if (!"".equals(changeRes)) {
				rkmx.setEngineNo(changeRes.split(",")[0]);
			} else {
				rkmx.setEngineNo(null);
			}
		} else {
			rkmx.setEngineNo(BCCRI.getVehicleInfo().getEngineNo());
		}
		if (BCCRI.getInsureStartDate() != null) {
			rkmx.setInsureStartDate(BCCRI.getInsureStartDate());
		} else {
			if (!"".equals(changeRes)) {
				rkmx.setInsureStartDate(DateUtil.getStringDate(changeRes.split(",")[1],null));
			} else {
				rkmx.setInsureStartDate(null);
			}
		}
		if (BCCRI.getInsureEndDate() != null) {
			rkmx.setInsureEndDate(BCCRI.getInsureEndDate());
		} else {
			if (!"".equals(changeRes)) {
				rkmx.setInsureEndDate(DateUtil.getStringDate(changeRes.split(",")[2],null));
			} else {
				rkmx.setInsureEndDate(null);
			}
		}
		return rkmx ;
	}
}
