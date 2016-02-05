package com.derun.taxpayquery.vo;

import java.util.Date;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.TaxPayQueryResInfo;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSNSXXCXCC;

/**
 * @author 郑艳英
 * 
 * @date 2014-5-7
 * 
 *       纳税信息查询出参表封装类
 * @version
 */
public class SYJK_CCS_CCSNSXXCXCCVO {

	// 纳税信息查询出参信息封装
	public SYJK_CCS_CCSNSXXCXCC getTaxpayqueryReqVO(
			TaxPayQueryResInfo taxPayQueryResInfo) {
		// 纳税信息查询出参表
		SYJK_CCS_CCSNSXXCXCC ccsnsxxcxcc = new SYJK_CCS_CCSNSXXCXCC();
		// 年度纳税
		AnnualTax_Type annualTaxType = null;
		if (taxPayQueryResInfo.getTaxInfo() == null
				|| taxPayQueryResInfo.getTaxInfo().getCurrentTaxDue() == null) {
			annualTaxType = new AnnualTax_Type();
		} else {
			annualTaxType = taxPayQueryResInfo.getTaxInfo().getCurrentTaxDue();
		}
		// 完税信息对象
		Paid_Type paidType = null;
		if (annualTaxType.getPaid() == null) {
			paidType = new Paid_Type();
		} else {
			paidType = annualTaxType.getPaid();
		}
		// 减免税对象
		Derate_Type derateType = null;
		if (annualTaxType.getDerate() == null) {
			derateType = new Derate_Type();
		} else {
			derateType = annualTaxType.getDerate();
		}
		// 税款所属始期
		Date taxStartDate = null;
		String taxStartDateStr = annualTaxType.getTaxStartDate() == null ? ""
				: annualTaxType.getTaxStartDate();
		if (taxStartDateStr != null && taxStartDateStr.length() >= 10) {
			String startDate = taxStartDateStr.substring(0, 10);
			taxStartDate = DateUtil.getStringDate(startDate, "yyyy-MM-dd");
		}
		// 税款所属止期
		Date taxEndDate = null;
		String taxEndDateStr = annualTaxType.getTaxEndDate() == null ? ""
				: annualTaxType.getTaxEndDate();
		if (taxEndDateStr != null && taxEndDateStr.length() >= 10) {
			String endDate = taxEndDateStr.substring(0, 10);
			taxEndDate = DateUtil.getStringDate(endDate, "yyyy-MM-dd");
		}
		// 逾期时间
		Date exceedDate = null;
		String exceeDateStr = annualTaxType.getExceedDate();
		if (exceeDateStr != null && exceeDateStr.length() >= 10) {
			String excee = exceeDateStr.substring(0, 10);
			exceedDate = DateUtil.getStringDates(excee);
		}
		// 代收日期
		Date payDate = null;
		String payDateStr = taxPayQueryResInfo.getTaxInfo() == null ? null : taxPayQueryResInfo.getTaxInfo().getPayDate();
		if (payDateStr != null && payDateStr.length() >= 10) {
			String payDateSub = payDateStr.substring(0, 10);
			payDate = DateUtil.getStringDates(payDateSub);
		}

		// 返回代码
		ccsnsxxcxcc.setRETURNCODE(taxPayQueryResInfo.getReturnCode());
		// 平台状态
		ccsnsxxcxcc.setDECLAREDSTATUS(taxPayQueryResInfo.getDeclaredStatus());
		// 税种类型
		String TaxTermTypeCode = taxPayQueryResInfo.getTaxInfo() == null ? null :taxPayQueryResInfo.getTaxInfo().getTaxTermTypeCode() ;
		ccsnsxxcxcc.setTAXTERMTYPECODE(TaxTermTypeCode);
		// 纳税类型
		String TaxConditionCode = taxPayQueryResInfo.getTaxInfo() == null ? null :taxPayQueryResInfo.getTaxInfo().getTaxConditionCode() ;
		ccsnsxxcxcc.setTAXCONDITIONCODE(TaxConditionCode);
		// 税务登记证号
		String TaxRegistryNumber = taxPayQueryResInfo.getTaxInfo() == null ? null : taxPayQueryResInfo.getTaxInfo().getTaxRegistryNumber() ;
		ccsnsxxcxcc.setTAXREGISTRYNUMBER(TaxRegistryNumber);
		// 纳税人姓名
		String TaxPayerName = taxPayQueryResInfo.getTaxInfo() == null ? null : taxPayQueryResInfo.getTaxInfo().getTaxPayerName() ;
		ccsnsxxcxcc.setTAXPAYERNAME(TaxPayerName);
		// 纳税人识别号
		String TaxPayerIdentificationCode = taxPayQueryResInfo.getTaxInfo() == null ? null : taxPayQueryResInfo.getTaxInfo().getTaxPayerIdentificationCode() ;
		ccsnsxxcxcc.setTAXPAYERIDENTIFICATIONCODE(TaxPayerIdentificationCode);
		// 代收公司
		String PayCompanyCode = taxPayQueryResInfo.getTaxInfo() == null ? null : taxPayQueryResInfo.getTaxInfo().getPayCompanyCode() ;
		ccsnsxxcxcc.setPAYCOMPANYCODE(PayCompanyCode);
		// 纳税地区代码
		ccsnsxxcxcc.setTAXLOCATIONCODE(annualTaxType.getTaxLocationCode());
		// 税款所属始期
		ccsnsxxcxcc.setTAXSTARTDATE(taxStartDate);
		// 税款所属止期
		ccsnsxxcxcc.setTAXENDDATE(taxEndDate);
		// 计税单位代码
		ccsnsxxcxcc.setTAXUNITTYPECODE(annualTaxType.getTaxUnitTypeCode());
		// 单位计税金额
		ccsnsxxcxcc.setUNITRATE(annualTaxType.getUnitRate());
		// 当期年单位税额
		ccsnsxxcxcc.setANNUALTAXAMOUNT(annualTaxType.getAnnualTaxAmount());
		// 开具完税凭证的税务机关代码
		ccsnsxxcxcc.setTAXDEPARTMENTCODE(paidType.getTaxDepartmentCode());
		// 开具完税凭证的税务机关名称
		ccsnsxxcxcc.setTAXDEPARTMENT(paidType.getTaxDepartment());
		// 完税凭证号码
		ccsnsxxcxcc.setTAXDOCUMENTNUMBER(paidType.getTaxDocumentNumber());
		// 减免税原因代码
		ccsnsxxcxcc.setDEDUCTIONDUECODE(derateType.getDeductionDueCode());
		// 减免税方案代码
		ccsnsxxcxcc.setDEDUCTIONDUETYPE(derateType.getDeductionDueType());
		// 减免比例
		ccsnsxxcxcc.setDEDUCTIONDUEPROPORTION(derateType
				.getDeductionDueProportion());
		// 减免金额
		ccsnsxxcxcc.setDEDUCTION(derateType.getDeduction());
		// 减免税凭证号
		ccsnsxxcxcc.setDEDUCTIONDOCUMENTNUMBER(derateType
				.getDeductionDocumentNumber());
		// 税务机关编码
		ccsnsxxcxcc.setREVENUECODE(derateType.getTaxDepartment());
		// 当期应纳税额
		ccsnsxxcxcc.setTAXDUE(annualTaxType.getTaxDue());
		// 逾期时间
		ccsnsxxcxcc.setEXCEEDDATE(exceedDate);
		// 逾期天数
		ccsnsxxcxcc.setEXCEEDDAYSCOUNT((double) annualTaxType
				.getExceedDaysCount());
		// 滞纳金
		ccsnsxxcxcc.setOVERDUE(annualTaxType.getOverDue());
		// 合计金额
		ccsnsxxcxcc.setTOTALAMOUNT(annualTaxType.getTotalAmount());
		// 操作员
		ccsnsxxcxcc.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER",
				"username"));
		// 代收日期
		ccsnsxxcxcc.setPAYDATE(payDate);
		// 系统采集日期
		ccsnsxxcxcc.setSJCJRQ(new Date());
		// 系统采集方式
		ccsnsxxcxcc.setSJCJFS("0");
		return ccsnsxxcxcc;
	}
}
