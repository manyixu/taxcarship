package com.derun.taxchangeconfirm.dao.impl;

import java.util.Date;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseConfirmReqInfo;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
import com.derun.model.po.SYJK_CCS_CODE;
import com.derun.model.po.SYJK_CCS_RKMX_QS;
/**
 * @author MILI
 * @time 2014-3-21 9:50:35
 * @描述：插入对象 SYJK_CCS_RKMX_QS 封装
 * */
public class P_SYJK_CCS_RKMX_QS {
	/**
	 * @author MILI
	 * @time 2014-3-21 9:50:35
	 * @描述：插入对象 SYJK_CCS_RKMX_QS 封装
	 * */
	public SYJK_CCS_RKMX_QS getrkmx_qs(BaseConfirmReqInfo baseconfirmReqinfo,
			AnnualTax_Type delinquentTaxDue,
			SYJK_CCS_CCSCXCCJBXX cxccjbxx, String taxConfirmNo,SYJK_CCS_CODE code){
		SYJK_CCS_RKMX_QS rkmx_qs = new SYJK_CCS_RKMX_QS();
		Date fisdate = null ;
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
			rkmx_qs.setTAXQUERYNO(taxConfirmNo);			// 车船税查询码
			rkmx_qs.setTAXCONFIRMNO(taxConfirmNo);			// 车船税确认码
			if ("R".equals(baseconfirmReqinfo.getTaxInfo()
					.getTaxConditionCode())) {
				rkmx_qs.setTAXPRINTNO(null);				// 车船税打印码
			} else {
				String agoNo = taxConfirmNo.substring(0, 11);
				String endNo = taxConfirmNo
						.substring(12, taxConfirmNo.length());
				String middleNo = agoNo + "G" + endNo;		// 
				rkmx_qs.setTAXPRINTNO(middleNo);			// 车船税打印码
			}
			rkmx_qs.setVIN(cxccjbxx.getVIN());			// VIN
			rkmx_qs.setHPHM(cxccjbxx.getHPHM());		// HPHM
			rkmx_qs.setHPZL(cxccjbxx.getHPZL());		// HPZL
			rkmx_qs.setCLLX(cxccjbxx.getCLLX());		// CLLX
		} else {
			rkmx_qs.setTAXQUERYNO(baseconfirmReqinfo.getTaxConfirmNo().getTaxDealCode_Type());		// 车船税查询码
			rkmx_qs.setTAXCONFIRMNO(baseconfirmReqinfo.getTaxConfirmNo().getTaxDealCode_Type());	// 车船税确认码
			if (baseconfirmReqinfo.getTaxPrintNo() == null) {
				rkmx_qs.setTAXPRINTNO(null);
			} else {
				rkmx_qs.setTAXPRINTNO(baseconfirmReqinfo.getTaxPrintNo().getTaxDealCode_Type());	// 车船税打印码
			}

			if (baseconfirmReqinfo.getVehicleInfo() != null) {
				rkmx_qs.setVIN(baseconfirmReqinfo.getVehicleInfo().getVIN());				// VIN
				rkmx_qs.setHPHM(baseconfirmReqinfo.getVehicleInfo().getLicensePlateNo());	// HPHM
				rkmx_qs.setHPZL(baseconfirmReqinfo.getVehicleInfo().getLicensePlateType());	// HPZL
				rkmx_qs.setCLLX(baseconfirmReqinfo.getVehicleInfo().getMotorTypeCode());	// CLLX
			} else {
				rkmx_qs.setVIN(null);	// VIN
				rkmx_qs.setHPHM(null);	// HPHM
				rkmx_qs.setHPZL(null);	// HPZL
				rkmx_qs.setCLLX(null);	// CLLX
			}
		}
		rkmx_qs.setTAXCONDITIONCODE(baseconfirmReqinfo.getTaxInfo().getTaxConditionCode());		// 纳税类型代码
		rkmx_qs.setTAXREGISTRYNUMBER(baseconfirmReqinfo.getTaxInfo().getTaxRegistryNumber());	// 税务登记证号
		rkmx_qs.setTAXPAYERNAME(baseconfirmReqinfo.getTaxInfo().getTaxPayerName());				// 纳税人名称
		rkmx_qs.setTAXPAYERIDENTIFICATIONCODE(baseconfirmReqinfo.getTaxInfo().getTaxPayerIdentificationCode());	// 纳税人识别号
		if (baseconfirmReqinfo.getTaxInfo() != null
				&& !"".equals(baseconfirmReqinfo.getTaxInfo())
				&& baseconfirmReqinfo.getTaxInfo().getPayCompanyCode() != null
				&& !"".equals(baseconfirmReqinfo.getTaxInfo()
						.getPayCompanyCode())) {
			rkmx_qs.setPAYCOMPANYCODE(baseconfirmReqinfo.getTaxInfo().getPayCompanyCode());		// 代收公司
		} else if (baseconfirmReqinfo.getCompanyCode() != null
				&& !"".equals(baseconfirmReqinfo)) {
			rkmx_qs.setPAYCOMPANYCODE(baseconfirmReqinfo.getCompanyCode());			// 代收公司
		} else {
			if (baseconfirmReqinfo.getTaxQueryNo() != null
					&& baseconfirmReqinfo.getTaxQueryNo().getTaxDealCode_Type() != null
					&& !"".equals(baseconfirmReqinfo.getTaxQueryNo()
							.getTaxDealCode_Type())) {
				String taxqueryno = baseconfirmReqinfo.getTaxQueryNo()
						.getTaxDealCode_Type();
				taxqueryno = taxqueryno.substring(1, 5);

				rkmx_qs.setPAYCOMPANYCODE(taxqueryno);				// 代收公司
			} else {
				rkmx_qs.setPAYCOMPANYCODE(null);			// 代收公司
			}
		}
		if (delinquentTaxDue != null) {
			rkmx_qs.setTAXLOCATIONCODE(delinquentTaxDue.getTaxLocationCode());	// 纳税地区代码
			String startDate = delinquentTaxDue.getTaxStartDate();
			String endDate = delinquentTaxDue.getTaxEndDate();
			if (startDate != null && startDate.length() >= 10) {
				startDate = startDate.substring(0, 10);
			} 
			if (endDate != null && endDate.length() >= 10) {
				endDate = endDate.substring(0, 10);
			} 
			rkmx_qs.setTAXSTARTDATE(DateUtil.getStringDate(startDate,null));
			rkmx_qs.setTAXENDDATE(DateUtil.getStringDate(endDate,null));
			rkmx_qs.setTAXUNITTYPECODE(delinquentTaxDue.getTaxUnitTypeCode());	// 计税单位代码
			rkmx_qs.setUNITRATE(delinquentTaxDue.getUnitRate());				// 单位计税金额
			rkmx_qs.setANNUALTAXAMOUNT(delinquentTaxDue.getAnnualTaxAmount());	// 当期年单位税额
			if (delinquentTaxDue.getPaid() != null) {
				rkmx_qs.setTAXDEPARTMENTCODE(delinquentTaxDue.getPaid().getTaxDepartmentCode());	// 开具减免税(完税)凭证的税务机关代码
				rkmx_qs.setTAXDEPARTMENT(delinquentTaxDue.getPaid().getTaxDepartment());			// 开具减免税(完税)凭证的税务机关名称
				rkmx_qs.setTAXDOCUMENTNUMBER(delinquentTaxDue.getPaid().getTaxDocumentNumber());	// 完税凭证号码
			} else {
				rkmx_qs.setTAXDEPARTMENTCODE(null);	// 开具减免税(完税)凭证的税务机关代码
				rkmx_qs.setTAXDEPARTMENT(null);		// 开具减免税(完税)凭证的税务机关名称
				rkmx_qs.setTAXDOCUMENTNUMBER(null);	// 完税凭证号码
			}
			if (delinquentTaxDue.getDerate() != null) {
				rkmx_qs.setTAXDEPARTMENTCODE(delinquentTaxDue.getDerate().getTaxDepartmentCode());	// 开具减免税(完税)凭证的税务机关代码
				rkmx_qs.setTAXDEPARTMENT(delinquentTaxDue.getDerate().getTaxDepartment());			// 开具减免税(完税)凭证的税务机关名称
				rkmx_qs.setDEDUCTIONDUECODE(delinquentTaxDue.getDerate().getDeductionDueCode());	// 减免税原因代码
				rkmx_qs.setDEDUCTIONDUETYPE(delinquentTaxDue.getDerate().getDeductionDueType());	// 减免税方案代码
				rkmx_qs.setDEDUCTIONDUEPROPORTION(delinquentTaxDue.getDerate().getDeductionDueProportion());	// 减免比例
				rkmx_qs.setDEDUCTION(delinquentTaxDue.getDerate().getDeduction());					// 减免金额
				rkmx_qs.setDEDUCTIONDOCUMENTNUMBER(delinquentTaxDue.getDerate().getDeductionDocumentNumber());	// 减免税凭证号
			} else {
				rkmx_qs.setTAXDEPARTMENTCODE(null);			// 开具减免税(完税)凭证的税务机关代码
				rkmx_qs.setTAXDEPARTMENT(null);				// 开具减免税(完税)凭证的税务机关名称
				rkmx_qs.setDEDUCTIONDUECODE(null);			// 减免税原因代码
				rkmx_qs.setDEDUCTIONDUETYPE(null);			// 减免税方案代码
				rkmx_qs.setDEDUCTIONDUEPROPORTION(0.0);		// 减免比例
				rkmx_qs.setDEDUCTION(0.0);					// 减免金额
				rkmx_qs.setDEDUCTIONDOCUMENTNUMBER(null);	// 减免税凭证号
			}
			rkmx_qs.setTAXDUE(delinquentTaxDue.getTaxDue());// 当期应纳税额
			String ExceedDate = delinquentTaxDue.getExceedDate();
			if (ExceedDate != null && ExceedDate.length() >= 10) {
				ExceedDate = ExceedDate.substring(0, 10);
			} 
			rkmx_qs.setEXCEEDDATE(DateUtil.getStringDate(ExceedDate,null));				// 逾期时间
			rkmx_qs.setEXCEEDDAYSCOUNT(delinquentTaxDue.getExceedDaysCount());		// 逾期天数
			rkmx_qs.setOVERDUE(delinquentTaxDue.getOverDue());						// 滞纳金
			rkmx_qs.setTOTALAMOUNT(delinquentTaxDue.getTotalAmount());				// 合计金额
		} else {
			rkmx_qs.setTAXLOCATIONCODE(null);			// 纳税地区代码
			rkmx_qs.setTAXSTARTDATE(null);
			rkmx_qs.setTAXENDDATE(null);
			rkmx_qs.setTAXUNITTYPECODE(null);			// 计税单位代码
			rkmx_qs.setUNITRATE(null);					// 单位计税金额
			rkmx_qs.setANNUALTAXAMOUNT(null);			// 当期年单位税额
			rkmx_qs.setTAXDEPARTMENTCODE(null);			// 开具减免税(完税)凭证的税务机关代码
			rkmx_qs.setTAXDEPARTMENT(null);				// 开具减免税(完税)凭证的税务机关名称
			rkmx_qs.setTAXDOCUMENTNUMBER(null);			// 完税凭证号码
			rkmx_qs.setTAXDEPARTMENTCODE(null);			// 开具减免税(完税)凭证的税务机关代码
			rkmx_qs.setTAXDEPARTMENT(null);				// 开具减免税(完税)凭证的税务机关名称
			rkmx_qs.setDEDUCTIONDUECODE(null);			// 减免税原因代码
			rkmx_qs.setDEDUCTIONDUETYPE(null);			// 减免税方案代码
			rkmx_qs.setDEDUCTIONDUEPROPORTION(0.0);		// 减免比例
			rkmx_qs.setDEDUCTION(0.0);					// 减免金额
			rkmx_qs.setDEDUCTIONDOCUMENTNUMBER(null);	// 减免税凭证号
			rkmx_qs.setTAXDUE(null);					// 当期应纳税额
			rkmx_qs.setEXCEEDDATE(null);				// 逾期时间
			rkmx_qs.setEXCEEDDAYSCOUNT(0);				// 逾期天数
			rkmx_qs.setOVERDUE(null);					// 滞纳金
			rkmx_qs.setTOTALAMOUNT(null);				// 合计金额
		}
		rkmx_qs.setLOGINNAME(code.getCODENAME());				// 操作员
		rkmx_qs.setREVENUECODE(code.getCODENAME());
		rkmx_qs.setSJCJRQ(new Date());
		rkmx_qs.setPAYDATE(DateUtil.getDateYYYY());
		rkmx_qs.setTAXAMOUNT_FLAG(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getTaxAmount_Flag());
		rkmx_qs.setANNUALTAXDUE(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getAnnualTaxDue());
		rkmx_qs.setSUMTAXDEFAULT(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getSumTaxDefault());
		rkmx_qs.setSUMOVERDUE(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getSumOverdue());
		rkmx_qs.setSUMTAX(baseconfirmReqinfo.getTaxInfo().getTaxAmount().getSumTax());
		rkmx_qs.setLOGGEDOUT("0");
		
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
		rkmx_qs.setPLATFORMSTATE(Ptzt);									// 平台 0=代收 1=申报（完税）
		rkmx_qs.setCHANGETYPE("0");										// 变更类型 0=确认 4=退保，2=批改
		rkmx_qs.setCOUNTTAXTYPE(baseconfirmReqinfo.getCalcTaxFlag());	// 算税标志
		rkmx_qs.setREFUSETYPE(Refusetype);
		if ("1".equals(baseconfirmReqinfo.getCalcTaxFlag())
				&& "2".equals(BCFlag)) {
			if (cxccjbxx != null) {
				if (cxccjbxx.getFIRSTREGISTERDATE() != null
						&& "".equals(cxccjbxx.getFIRSTREGISTERDATE())) {
					fisdate = cxccjbxx.getFIRSTREGISTERDATE();
				}
			}
			if ("P".equals(baseconfirmReqinfo.getTaxInfo()
					.getTaxConditionCode()))
				rkmx_qs.setPAYDATE(DateUtil.getDateYYYY());
			else
				rkmx_qs.setPAYDATE(null);
			rkmx_qs.setFIRSTREGISTERDATE(fisdate);
			rkmx_qs.setSPECIALCARTYPE(cxccjbxx.getSPECIALCARTYPE());
			rkmx_qs.setTSBZ(cxccjbxx.getTSBZ());
			rkmx_qs.setCARSERIALNO(cxccjbxx.getCARSERIALNO());
			if (cxccjbxx.getENGINENO() == null
					|| "".equals(cxccjbxx.getENGINENO())) {
				rkmx_qs.setENGINENO(cxccjbxx.getENGINENO());
			} else {
				rkmx_qs.setENGINENO(null);
			}
		} else {
			if (baseconfirmReqinfo.getVehicleInfo() != null
					&& baseconfirmReqinfo.getVehicleInfo()
							.getFirstRegisterDate() != null) {
				if (baseconfirmReqinfo.getVehicleInfo().getFirstRegisterDate()
						.length() >= 10) {
					fisdate = DateUtil.getStringDate(baseconfirmReqinfo.getVehicleInfo().getFirstRegisterDate(),null);
				}
			}
			if ("P".equals(baseconfirmReqinfo.getTaxInfo()
					.getTaxConditionCode())){
				rkmx_qs.setPAYDATE(DateUtil.getDateYYYY());
			}else{
				rkmx_qs.setENGINENO(null);
			}
			rkmx_qs.setFIRSTREGISTERDATE(fisdate);
			rkmx_qs.setSPECIALCARTYPE(baseconfirmReqinfo.getVehicleInfo().getSpecialCarType());		 
			rkmx_qs.setTSBZ("0");
			if (cxccjbxx != null || "".equals(cxccjbxx)) {
				rkmx_qs.setCARSERIALNO(cxccjbxx.getCARSERIALNO());
			} else {
//				rkmx_qs.setCARSERIALNO(baseconfirmReqinfo.getCarSerialNo());  	// 问题名单序列号 
				rkmx_qs.setCARSERIALNO(""); 
			}
			if (baseconfirmReqinfo.getVehicleInfo().getEngineNo() == null
					|| "".equals(baseconfirmReqinfo.getVehicleInfo()
							.getEngineNo())) {
				rkmx_qs.setENGINENO(null);
			} else {
				rkmx_qs.setENGINENO(baseconfirmReqinfo.getVehicleInfo().getEngineNo());
			}
		}
		rkmx_qs.setINSURESTARTDATE(baseconfirmReqinfo.getInsureStartDate());
		rkmx_qs.setINSUREENDDATE(baseconfirmReqinfo.getInsureEndDate());
		return rkmx_qs ;
	}
}
