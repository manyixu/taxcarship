package com.derun.taxchangequery.dao.impl;

import com.derun.beans.Tax_Type;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSXX;
/**
 * @author MILI
 * @time 2014-4-23 9:52:27
 * @描述：封装  SYJK_CCS_CCSXX 车船税信息表
 * */
public class F_SYJK_CCS_CCSXX {
	/**
	 * @author MILI
	 * @time 2014-4-23 9:52:27
	 * @描述：封装  SYJK_CCS_CCSXX 车船税信息表
	 * @入参：Tax_Type 、问题序列号、查询码、代收公司
	 * @出参：SYJK_CCS_CCSXX 
	 * */
	public SYJK_CCS_CCSXX getCCSXX(Tax_Type taxInfo,String carSerialNo,String taxQueryNo,String paycompanycode){
		SYJK_CCS_CCSXX ccsxx = new SYJK_CCS_CCSXX();
		
		ccsxx.setCARSERIALNO(carSerialNo);		//
		ccsxx.setTAXQUERYNO(taxQueryNo);		// 车船税变更查询码,唯一标示符
		ccsxx.setTAXTERMTYPECODE(taxInfo.getTaxTermTypeCode());		// 税种类型代码,Y
		ccsxx.setTAXCONDITIONCODE(taxInfo.getTaxConditionCode());	// 纳税类型代码,Y
		ccsxx.setTAXREGISTRYNUMBER(taxInfo.getTaxRegistryNumber());	// 税务登记证号
		ccsxx.setTAXPAYERNAME(taxInfo.getTaxPayerName());// 纳税人名称
		ccsxx.setTAXPAYERIDENTIFICATIONCODE(taxInfo.getTaxPayerIdentificationCode());	// 纳税人识别号
		ccsxx.setTAXLOCATIONCODE(taxInfo.getCurrentTaxDue().getTaxLocationCode());		// 纳税地区代码
		ccsxx.setTAXSTARTDATE(DateUtil.getStringDate(taxInfo.getCurrentTaxDue().getTaxStartDate(),null));	// 税款所属始期
		ccsxx.setTAXENDDATE(DateUtil.getStringDate(taxInfo.getCurrentTaxDue().getTaxEndDate(),null));		// 税款所属止期
		ccsxx.setPAYDATE(DateUtil.getStringDate(taxInfo.getPayDate(),null));								// 代收日期
		if (taxInfo.getPayCompanyCode() != null	&& !"".equals(taxInfo.getPayCompanyCode())) {
			ccsxx.setPAYCOMPANYCODE(taxInfo.getPayCompanyCode());		// 代收公司
		} else {
			if (!"".equals(paycompanycode) && paycompanycode != null) {
				ccsxx.setPAYCOMPANYCODE(paycompanycode);
			} else {
				ccsxx.setPAYCOMPANYCODE(taxQueryNo.substring(1, 5));
			}
		}
		ccsxx.setTAXUNITTYPECODE(taxInfo.getCurrentTaxDue() == null ? ""
						: taxInfo.getCurrentTaxDue().getTaxUnitTypeCode());		// 计税单位代码
		ccsxx.setUNITRATE(taxInfo.getCurrentTaxDue() == null ? 0
				: taxInfo.getCurrentTaxDue().getUnitRate());					// 单位计税金额
		ccsxx.setANNUALTAXAMOUNT(taxInfo.getCurrentTaxDue() == null ? 0
						: taxInfo.getCurrentTaxDue().getAnnualTaxAmount());		// 当期年单位税额
		ccsxx.setTAXDUE(taxInfo.getCurrentTaxDue() == null ? 0
				: taxInfo.getCurrentTaxDue().getTaxDue());						// 当期应纳税额
		ccsxx.setEXCEEDDATE(DateUtil.getStringDate(taxInfo.getCurrentTaxDue().getExceedDate(),null));// 逾期时间
		ccsxx.setEXCEEDDAYSCOUNT(taxInfo.getCurrentTaxDue() == null ? 0
						: taxInfo.getCurrentTaxDue().getExceedDaysCount());		// 逾期天数
		ccsxx.setOVERDUE(taxInfo.getCurrentTaxDue() == null ? 0
				: taxInfo.getCurrentTaxDue().getOverDue());						// 滞纳金
		ccsxx.setTOTALAMOUNT(taxInfo.getCurrentTaxDue().getTotalAmount());	// 合计金额
		// 完税减免税
		if (taxInfo.getCurrentTaxDue() != null) {
			ccsxx.setDEDUCTIONDUECODE(taxInfo.getCurrentTaxDue()
					.getDerate() == null ? "" : taxInfo.getCurrentTaxDue().getDerate()
					.getDeductionDueCode());// 减免税原因代码,Y
			ccsxx.setDEDUCTIONDUETYPE(taxInfo.getCurrentTaxDue()
					.getDerate() == null ? "" : taxInfo.getCurrentTaxDue().getDerate()
					.getDeductionDueType());// 减免税方案代码,Y
			ccsxx.setDEDUCTIONDUEPROPORTION(taxInfo
					.getCurrentTaxDue().getDerate() == null ? 0
					: taxInfo.getCurrentTaxDue().getDerate().getDeductionDueProportion());	// 减免比例
			ccsxx.setDEDUCTION(taxInfo.getCurrentTaxDue()
					.getDerate() == null ? 0 : taxInfo.getCurrentTaxDue().getDerate().getDeduction());// 减免金额
			ccsxx.setDEDUCTIONDOCUMENTNUMBER(taxInfo
					.getCurrentTaxDue().getDerate() == null ? ""
					: taxInfo.getCurrentTaxDue().getDerate().getDeductionDocumentNumber());	// 减免税凭证号
			String jianmianCodeX = taxInfo.getCurrentTaxDue()
					.getDerate() == null ? "" : taxInfo.getCurrentTaxDue().getDerate().getTaxDepartment();	// 减免税凭证号
			String wanshuiCodeX = taxInfo.getCurrentTaxDue()
					.getPaid() == null ? "" : taxInfo.getCurrentTaxDue().getPaid().getTaxDepartment();		// 完税凭证号码
			if (jianmianCodeX != null && "".equals(jianmianCodeX) == false) {
				ccsxx.setTAXDEPARTMENTCODE(taxInfo.getCurrentTaxDue().getDerate() == null ? ""
								: taxInfo.getCurrentTaxDue().getDerate().getTaxDepartmentCode());			// 开具减免税(完税)凭证的税务机关代码,Y
				ccsxx.setTAXDEPARTMENT(taxInfo.getCurrentTaxDue()
						.getDerate() == null ? "" : taxInfo.getCurrentTaxDue().getDerate().getTaxDepartment());	// 开具减免税(完税)凭证的税务机关名称,Y
			} else if (wanshuiCodeX != null	&& "".equals(wanshuiCodeX) == false) {
				ccsxx.setTAXDEPARTMENTCODE(taxInfo
						.getCurrentTaxDue().getPaid() == null ? ""
						: taxInfo.getCurrentTaxDue().getPaid().getTaxDepartmentCode());		// 开具减免税(完税)凭证的税务机关代码,Y
				ccsxx.setTAXDEPARTMENT(taxInfo.getCurrentTaxDue()
						.getPaid() == null ? "" : taxInfo.getCurrentTaxDue().getPaid().getTaxDepartment());		// 开具减免税(完税)凭证的税务机关名称,Y
			} else {
				ccsxx.setTAXDEPARTMENTCODE(null);// 开具减免税(完税)凭证的税务机关代码,Y
				ccsxx.setTAXDEPARTMENT(null);// 开具减免税(完税)凭证的税务机关名称,Y
			}
			ccsxx.setTAXDOCUMENTNUMBER(taxInfo.getCurrentTaxDue()
					.getPaid() == null ? "" : taxInfo.getCurrentTaxDue().getPaid().getTaxDocumentNumber());		// 完税凭证号码
		} else {
			ccsxx.setDEDUCTIONDUECODE("");		// 减免税原因代码,Y
			ccsxx.setDEDUCTIONDUETYPE("");		// 减免税方案代码,Y
			ccsxx.setDEDUCTIONDUEPROPORTION(0.0);	// 减免比例
			ccsxx.setDEDUCTION(0.0);				// 减免金额
			ccsxx.setDEDUCTIONDOCUMENTNUMBER("");	// 减免税凭证号
			ccsxx.setTAXDEPARTMENTCODE("");		// 开具减免税(完税)凭证的税务机关代码,Y
			ccsxx.setTAXDEPARTMENT("");			// 开具减免税(完税)凭证的税务机关名称,Y
			ccsxx.setTAXDOCUMENTNUMBER("");		// 完税凭证号码
		}
		ccsxx.setPARATYPE("1");// 参数类型 0-入参，1-出参
		return ccsxx ;
	}
}
