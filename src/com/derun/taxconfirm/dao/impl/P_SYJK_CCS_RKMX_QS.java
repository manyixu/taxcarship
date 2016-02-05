package com.derun.taxconfirm.dao.impl;

import java.util.Date;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseConfirmReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
import com.derun.model.po.SYJK_CCS_RKMX_QS;
/**
 * @author MILI
 * @time 2014-3-19 16:00:09
 * @描述：插入对象 SYJK_CCS_RKMX_QS 封装
 * */
public class P_SYJK_CCS_RKMX_QS {
	/**
	 * @author MILI
	 * @time 2014-3-19 16:00:09
	 * @描述：插入对象 SYJK_CCS_RKMX_QS 封装
	 * */
	public SYJK_CCS_RKMX_QS getrkmx_qs(BaseConfirmReqInfo baseconfirmReqinfo,
			AnnualTax_Type delinquentTaxDue,SYJK_CCS_CCSCXCCJBXX cxccjbxx,TaxDealCode_Type taxcon){
		String taxConfirmNo = taxcon.getTaxDealCode_Type();
		SYJK_CCS_RKMX_QS ccs_rkmx_qs = new SYJK_CCS_RKMX_QS();
		Date taxstart = null , taxend = null ,exceedDate = null ,fisdate = null ;
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
				&& "1".equals(baseconfirmReqinfo.getCalcTaxFlag())
				&& "2".equals(BCFlag)) {
			ccs_rkmx_qs.setTAXQUERYNO(taxConfirmNo);				// 车船税查询码,唯一标示符
			ccs_rkmx_qs.setTAXCONFIRMNO(taxConfirmNo);				// 车船税确认码,唯一标示符
			if ("R".equals(baseconfirmReqinfo.getTaxInfo()
					.getTaxConditionCode())) {
				ccs_rkmx_qs.setTAXPRINTNO(null);					// 车船税查询码
			} else {
				String agoNo = taxConfirmNo.substring(0, 11);
				String endNo = taxConfirmNo
						.substring(12, taxConfirmNo.length());
				String middleNo = agoNo + "G" + endNo;
				ccs_rkmx_qs.setTAXPRINTNO(middleNo);				// 车船税打印码
			}
			ccs_rkmx_qs.setVIN(cxccjbxx.getVIN());					// VIN
			ccs_rkmx_qs.setHPHM(cxccjbxx.getHPHM());				// HPHM
			ccs_rkmx_qs.setHPZL(cxccjbxx.getHPZL());				// HPZL
			ccs_rkmx_qs.setCLLX(cxccjbxx.getCLLX());				// CLLX
		} else {
			ccs_rkmx_qs.setTAXQUERYNO(baseconfirmReqinfo.getTaxConfirmNo()
							.getTaxDealCode_Type());				// 车船税查询码
			ccs_rkmx_qs.setTAXCONFIRMNO(baseconfirmReqinfo.getTaxConfirmNo()
							.getTaxDealCode_Type());				// 车船税确认码
			if (baseconfirmReqinfo.getTaxPrintNo() == null) {
				ccs_rkmx_qs.setTAXPRINTNO(null);
			} else {
				ccs_rkmx_qs.setTAXPRINTNO(baseconfirmReqinfo.getTaxPrintNo()
								.getTaxDealCode_Type());			// 车船税打印码
			}
			if (baseconfirmReqinfo.getVehicleInfo() != null) {
				ccs_rkmx_qs.setVIN( baseconfirmReqinfo.getVehicleInfo().getVIN());		// VIN
				ccs_rkmx_qs.setHPHM( baseconfirmReqinfo.getVehicleInfo()
								.getLicensePlateNo());				// HPHM
				ccs_rkmx_qs.setHPZL(baseconfirmReqinfo.getVehicleInfo()
								.getLicensePlateType());			// HPZL
				ccs_rkmx_qs.setCLLX(baseconfirmReqinfo.getVehicleInfo()
								.getMotorTypeCode());				// CLLX
			} else {
				ccs_rkmx_qs.setVIN(null);					// VIN
				ccs_rkmx_qs.setHPHM(null);					// HPHM
				ccs_rkmx_qs.setHPZL(null);					// HPZL
				ccs_rkmx_qs.setCLLX(null);					// CLLX

			}
		}
		ccs_rkmx_qs.setTAXCONDITIONCODE(baseconfirmReqinfo.getTaxInfo().getTaxConditionCode());		// 纳税类型代码,Y
		ccs_rkmx_qs.setTAXREGISTRYNUMBER(baseconfirmReqinfo.getTaxInfo().getTaxRegistryNumber());	// 税务登记证号
		ccs_rkmx_qs.setTAXPAYERNAME(baseconfirmReqinfo.getTaxInfo().getTaxPayerName());				// 纳税人名称
		ccs_rkmx_qs.setTAXPAYERIDENTIFICATIONCODE(baseconfirmReqinfo.getTaxInfo().getTaxPayerIdentificationCode());		// 纳税人识别号
		if (baseconfirmReqinfo.getTaxInfo() != null
				&& !"".equals(baseconfirmReqinfo.getTaxInfo())
				&& baseconfirmReqinfo.getTaxInfo().getPayCompanyCode() != null
				&& !"".equals(baseconfirmReqinfo.getTaxInfo().getPayCompanyCode())) {
			ccs_rkmx_qs.setPAYCOMPANYCODE(baseconfirmReqinfo.getTaxInfo().getPayCompanyCode());		// 代收公司
		} else if (baseconfirmReqinfo.getCompanyCode() != null
				&& !"".equals(baseconfirmReqinfo)) {
			ccs_rkmx_qs.setPAYCOMPANYCODE(baseconfirmReqinfo.getCompanyCode());						// 代收公司
		} else {
			if (baseconfirmReqinfo.getTaxQueryNo() != null
					&& baseconfirmReqinfo.getTaxQueryNo().getTaxDealCode_Type() != null
					&& !"".equals(baseconfirmReqinfo.getTaxQueryNo().getTaxDealCode_Type())) {
				String taxqueryno = baseconfirmReqinfo.getTaxQueryNo().getTaxDealCode_Type();
				taxqueryno = taxqueryno.substring(1, 5);
				ccs_rkmx_qs.setPAYCOMPANYCODE(taxqueryno);											// 代收公司
			} else {
				ccs_rkmx_qs.setPAYCOMPANYCODE(null);												// 代收公司
			}
		}
		if (delinquentTaxDue != null) {
			ccs_rkmx_qs.setTAXLOCATIONCODE(delinquentTaxDue.getTaxLocationCode());					// 纳税地区代码
			String startDate = delinquentTaxDue.getTaxStartDate();
			String endDate = delinquentTaxDue.getTaxEndDate();
			if (startDate != null && startDate.length() >= 10) {
				taxstart = DateUtil.getStringDate(startDate.substring(0, 10),null);
			} 
			if (endDate != null && endDate.length() >= 10) {
				taxend = DateUtil.getStringDate(endDate.substring(0, 10),null);
			} 
			ccs_rkmx_qs.setTAXSTARTDATE(taxstart);			// 税款所属始期
			ccs_rkmx_qs.setTAXENDDATE(taxend);				// 税款所属止期
			ccs_rkmx_qs.setTAXUNITTYPECODE(delinquentTaxDue.getTaxUnitTypeCode());					// 计税单位代码
			ccs_rkmx_qs.setUNITRATE(delinquentTaxDue.getUnitRate());								// 单位计税金额
			ccs_rkmx_qs.setANNUALTAXAMOUNT(delinquentTaxDue.getAnnualTaxAmount());					// 当期年单位税额
			if (delinquentTaxDue.getPaid() != null) {
				ccs_rkmx_qs.setTAXDEPARTMENTCODE(delinquentTaxDue.getPaid().getTaxDepartmentCode());// 开具减免税(完税)凭证的税务机关代码
				ccs_rkmx_qs.setTAXDEPARTMENT(delinquentTaxDue.getPaid().getTaxDepartment());		// 开具减免税(完税)凭证的税务机关名称
				ccs_rkmx_qs.setTAXDOCUMENTNUMBER(delinquentTaxDue.getPaid().getTaxDocumentNumber());// 完税凭证号码
			} else {
				ccs_rkmx_qs.setTAXDEPARTMENTCODE(null);				// 开具减免税(完税)凭证的税务机关代码
				ccs_rkmx_qs.setTAXDEPARTMENT(null);					// 开具减免税(完税)凭证的税务机关名称
				ccs_rkmx_qs.setTAXDOCUMENTNUMBER(null);				// 完税凭证号码
			}
			if (delinquentTaxDue.getDerate() != null) {
				ccs_rkmx_qs.setDEDUCTIONDEPARTMENTCODE(delinquentTaxDue.getDerate().getTaxDepartmentCode());		// 开具减免税(完税)凭证的税务机关代码
				ccs_rkmx_qs.setDEDUCTIONDEPARTMENT(delinquentTaxDue.getDerate().getTaxDepartment());				// 开具减免税(完税)凭证的税务机关名称
				ccs_rkmx_qs.setDEDUCTIONDUECODE(delinquentTaxDue.getDerate().getDeductionDueCode());					// 减免税原因代码
				ccs_rkmx_qs.setDEDUCTIONDUETYPE(delinquentTaxDue.getDerate().getDeductionDueType());					// 减免税方案代码
				ccs_rkmx_qs.setDEDUCTIONDUEPROPORTION(delinquentTaxDue.getDerate().getDeductionDueProportion());		// 减免比例
				ccs_rkmx_qs.setDEDUCTION(delinquentTaxDue.getDerate().getDeduction());									// 减免金额
				ccs_rkmx_qs.setDEDUCTIONDOCUMENTNUMBER(delinquentTaxDue.getDerate().getDeductionDocumentNumber());		// 减免税凭证号

			} else {
				ccs_rkmx_qs.setDEDUCTIONDEPARTMENTCODE(null);					// 开具减免税(完税)凭证的税务机关代码
				ccs_rkmx_qs.setDEDUCTIONDEPARTMENT(null);						// 开具减免税(完税)凭证的税务机关名称
				ccs_rkmx_qs.setDEDUCTIONDUECODE(null);					// 减免税原因代码
				ccs_rkmx_qs.setDEDUCTIONDUETYPE(null);					// 减免税方案代码
				ccs_rkmx_qs.setDEDUCTIONDUEPROPORTION(0.0);				// 减免比例
				ccs_rkmx_qs.setDEDUCTION(0.0);							// 减免金额
				ccs_rkmx_qs.setDEDUCTIONDOCUMENTNUMBER(null);			// 减免税凭证号
			}
			ccs_rkmx_qs.setTAXDUE(delinquentTaxDue.getTaxDue());		// 当期应纳税额
			String ExceedDate = delinquentTaxDue.getExceedDate();
			if (ExceedDate != null && ExceedDate.length() >= 10) {
				exceedDate = DateUtil.getStringDate(ExceedDate.substring(0, 10),null);
			} 
			ccs_rkmx_qs.setEXCEEDDATE(exceedDate);									// 逾期时间
			ccs_rkmx_qs.setEXCEEDDAYSCOUNT(delinquentTaxDue.getExceedDaysCount());	// 逾期天数
			ccs_rkmx_qs.setOVERDUE(delinquentTaxDue.getOverDue());					// 滞纳金
			ccs_rkmx_qs.setTOTALAMOUNT(delinquentTaxDue.getTotalAmount());			// 合计金额
		} else {
			ccs_rkmx_qs.setTAXLOCATIONCODE(null);					// 纳税地区代码
			ccs_rkmx_qs.setTAXSTARTDATE(null);						// 税款所属始期
			ccs_rkmx_qs.setTAXENDDATE(null);						// 税款所属止期
			ccs_rkmx_qs.setTAXUNITTYPECODE(null);					// 计税单位代码
			ccs_rkmx_qs.setUNITRATE(null);							// 单位计税金额
			ccs_rkmx_qs.setANNUALTAXAMOUNT(null);					// 当期年单位税额
			ccs_rkmx_qs.setTAXDEPARTMENTCODE(null);					// 开具减免税(完税)凭证的税务机关代码
			ccs_rkmx_qs.setTAXDEPARTMENT(null);						// 开具减免税(完税)凭证的税务机关名称
			ccs_rkmx_qs.setTAXDOCUMENTNUMBER(null);					// 完税凭证号码		
			ccs_rkmx_qs.setTAXDEPARTMENTCODE(null);					// 开具减免税(完税)凭证的税务机关代码
			ccs_rkmx_qs.setTAXDEPARTMENT(null);						// 开具减免税(完税)凭证的税务机关名称
			ccs_rkmx_qs.setDEDUCTIONDUECODE(null);					// 减免税原因代码
			ccs_rkmx_qs.setDEDUCTIONDUETYPE(null);					// 减免税方案代码
			ccs_rkmx_qs.setDEDUCTIONDUEPROPORTION(0.0);				// 减免比例
			ccs_rkmx_qs.setDEDUCTION(0.0);							// 减免金额
			ccs_rkmx_qs.setDEDUCTIONDOCUMENTNUMBER(null);			// 减免税凭证号
			ccs_rkmx_qs.setTAXDUE(null);							// 当期应纳税额
			ccs_rkmx_qs.setEXCEEDDATE(null);						// 逾期时间
			ccs_rkmx_qs.setEXCEEDDAYSCOUNT(0);						// 逾期天数
			ccs_rkmx_qs.setOVERDUE(0.0);							// 滞纳金
			ccs_rkmx_qs.setTOTALAMOUNT(0.0);						// 合计金额
		}
		ccs_rkmx_qs.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));				// 操作员
		ccs_rkmx_qs.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
		ccs_rkmx_qs.setSJCJRQ(new Date());
		ccs_rkmx_qs.setPAYDATE(DateUtil.getDateYYYY());				// 所属年度
		ccs_rkmx_qs.setTAXAMOUNT_FLAG(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getTaxAmount_Flag());
		ccs_rkmx_qs.setANNUALTAXDUE(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getAnnualTaxDue());
		ccs_rkmx_qs.setSUMTAXDEFAULT(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getSumTaxDefault());
		ccs_rkmx_qs.setSUMOVERDUE(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getSumOverdue());
//		ccs_rkmx_qs.setTAXDUE(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getSumTax());
		ccs_rkmx_qs.setLOGGEDOUT("0");								// 注销状态
		if ("R".equals(baseconfirmReqinfo.getTaxInfo().getTaxConditionCode())) {
			Ptzt = "4";
		} else if ("P".equals(baseconfirmReqinfo.getTaxInfo()
				.getTaxConditionCode())) {
			Ptzt = "1";
		} else {
			Ptzt = "0";
		}
		ccs_rkmx_qs.setPLATFORMSTATE(Ptzt);										// 平台 0=代收 1=申报（完税）
		ccs_rkmx_qs.setCHANGETYPE("0");											// 变更类型 0=确认 4=退保，2=批改
		ccs_rkmx_qs.setCOUNTTAXTYPE(baseconfirmReqinfo.getCalcTaxFlag());		// 算税标志
		ccs_rkmx_qs.setREFUSETYPE(Ptzt);										// 拒缴标志    0=拒缴  1=不拒缴
		if ("1".equals(baseconfirmReqinfo.getCalcTaxFlag())
				&& "2".equals(BCFlag)) {
			if (cxccjbxx != null) {
				if (cxccjbxx.getFIRSTREGISTERDATE() != null
						&& !"".equals(cxccjbxx.getFIRSTREGISTERDATE())) {
					fisdate = cxccjbxx.getFIRSTREGISTERDATE();
				}
			}
			if ("P".equals(baseconfirmReqinfo.getTaxInfo().getTaxConditionCode())){
				ccs_rkmx_qs.setSTATUSDATE(new Date());
			}else{
				ccs_rkmx_qs.setSTATUSDATE(null);
			}
			ccs_rkmx_qs.setFIRSTREGISTERDATE(fisdate);		
			ccs_rkmx_qs.setSPECIALCARTYPE(cxccjbxx.getSPECIALCARTYPE()); 
			ccs_rkmx_qs.setTSBZ(cxccjbxx.getTSBZ());
//			ccs_rkmx_qs.setCARSERIALNO(cxccjbxx.getCarSerialNo());
			if (cxccjbxx.getENGINENO() != null && !"".equals(cxccjbxx.getENGINENO())) {
				ccs_rkmx_qs.setENGINENO(cxccjbxx.getENGINENO());
			} else {
				ccs_rkmx_qs.setENGINENO(null);
			}
		} else {
			if (baseconfirmReqinfo.getVehicleInfo() != null
					&& baseconfirmReqinfo.getVehicleInfo()
							.getFirstRegisterDate() != null) {
				if (baseconfirmReqinfo.getVehicleInfo().getFirstRegisterDate()
						.length() >= 10) {
					fisdate = DateUtil.getStringDate(baseconfirmReqinfo.getVehicleInfo().getFirstRegisterDate().substring(0, 10),null);
				}
			}
			if ("P".equals(baseconfirmReqinfo.getTaxInfo().getTaxConditionCode())){
				ccs_rkmx_qs.setSTATUSDATE(new Date());
			}else{
				ccs_rkmx_qs.setSTATUSDATE(null);
			}
			ccs_rkmx_qs.setFIRSTREGISTERDATE(fisdate);
			if(baseconfirmReqinfo.getVehicleInfo() != null){	//  ZXL BUG 107  131 2014-10-11 15:50:23  MILI
				ccs_rkmx_qs.setSPECIALCARTYPE(baseconfirmReqinfo.getVehicleInfo().getSpecialCarType()); 
			}else{
				ccs_rkmx_qs.setSPECIALCARTYPE("");	
			}
			ccs_rkmx_qs.setTSBZ("0");
//			if (queryRes != null || "".equals(queryRes)) {
//				ccs_rkmx_qs.setCARSERIALNO(queryRes.getCarSerialNo());
//			} else {
//				ccs_rkmx_qs.setCARSERIALNO(baseconfirmReqinfo.getCarSerialNo());
//			}
			if(baseconfirmReqinfo.getVehicleInfo() != null ){	//  ZXL BUG 107  131 2014-10-11 15:50:23  MILI
				if (baseconfirmReqinfo.getVehicleInfo().getEngineNo() == null
						|| "".equals(baseconfirmReqinfo.getVehicleInfo().getEngineNo())) {
					ccs_rkmx_qs.setENGINENO(null);
				} else {
					ccs_rkmx_qs.setENGINENO(baseconfirmReqinfo.getVehicleInfo().getEngineNo());
				}
			}else{
				ccs_rkmx_qs.setENGINENO(null);
			}
		}
		if (baseconfirmReqinfo.getInsureStartDate() != null) {
			ccs_rkmx_qs.setINSURESTARTDATE(baseconfirmReqinfo.getInsureStartDate());
		} else {
			ccs_rkmx_qs.setINSURESTARTDATE(null);
		}
		if (baseconfirmReqinfo.getInsureEndDate() != null) {
			ccs_rkmx_qs.setINSUREENDDATE(baseconfirmReqinfo.getInsureEndDate());
		} else {
			ccs_rkmx_qs.setINSUREENDDATE(null);
		}
		return ccs_rkmx_qs ;
	}
}
