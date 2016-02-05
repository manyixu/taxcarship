package com.derun.taxchangeconfirm.dao.impl;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSBGXX;
/**
 * @author MILI
 * @time 2014-5-9 15:49:48
 * @描述：批改确认 封装  公共
 * */
public class TaxChangeConfirm_Common {
	/**
	 * @author MILI
	 * @time 2014-5-9 15:51:08
	 * @描述：批改确认 变更 还是 补传
	 * */
	public String getDecondInsure(BaseChangeConfirmReqInfo bccr){
		String BCFlag = "2";
		if (bccr.getChangeConfirmNo() != null) {
			if (bccr.getChangeConfirmNo().getTaxDealCode_Type() != null
					&& !"".equals(bccr.getChangeConfirmNo().getTaxDealCode_Type().trim())) {
				BCFlag = "1";
			}
		}
		return BCFlag ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-9 17:04:30
	 * @描述：封装 Tax_Type 
	 * */
	public Tax_Type getTaxType(SYJK_CCS_CCSBGXX bgxx,SYJK_CCS_CCSBGCXCCJB bgcxcc) {
		Tax_Type taxInfo = new Tax_Type();
		
		taxInfo.setTaxTermTypeCode(bgxx.getTAXTERMTYPECODE());				// 税种类型代码,Y
		taxInfo.setTaxConditionCode(bgxx.getTAXCONDITIONCODE());			// 纳税类型代码,Y
		taxInfo.setTaxRegistryNumber(bgxx.getTAXREGISTRYNUMBER());			// 税务登记证号
		taxInfo.setTaxPayerName(bgxx.getTAXPAYERNAME());					// 纳税人名称
		taxInfo.setTaxPayerIdentificationCode(bgxx.getTAXPAYERIDENTIFICATIONCODE());		// 纳税人识别号
		taxInfo.setPayDate(DateUtil.getStringDate(bgxx.getPAYDATE(),"yyyy") );				// 代收日期
		taxInfo.setPayCompanyCode(bgxx.getPAYCOMPANYCODE());				// 代收公司
		TaxAmount_Type TaxAmount = new TaxAmount_Type();					// 车船税合计金额
		TaxDealCode_Type taxdealcodetype = new TaxDealCode_Type();			// 车船税交易码,用于对账服务
		taxdealcodetype.setTaxDealCode_Type(bgcxcc.getTAXQUERYNO());
		TaxAmount.setTaxDealCode(taxdealcodetype);
		TaxAmount.setTaxAmount_Flag(bgcxcc.getTAXAMOUNT_FL());				// 合计金额标志码,Y
		TaxAmount.setAnnualTaxDue(bgcxcc.getANNUALTAXDUE());				// 本年车船税金额
		TaxAmount.setSumTaxDefault(bgcxcc.getSUMTAXDEFAULT());				// 合计欠税金额
		TaxAmount.setSumOverdue(bgcxcc.getSUMOVERDUE());					// 合计滞纳金
		TaxAmount.setSumTax(bgcxcc.getSUMTAX());							// 合计金额,Y
		AnnualTax_Type CurrentTaxDue = new AnnualTax_Type();				// 本年纳税信息对象
		CurrentTaxDue.setTaxLocationCode(bgxx.getTAXLOCATIONCODE());
		CurrentTaxDue.setTaxStartDate(DateUtil.getStringDate(bgxx.getTAXSTARTDATE(),"yyyy-MM-dd"));
		CurrentTaxDue.setTaxEndDate(DateUtil.getStringDate(bgxx.getTAXENDDATE(),"yyyy-MM-dd"));
		CurrentTaxDue.setTaxUnitTypeCode(bgxx.getTAXUNITTYPECODE());
		CurrentTaxDue.setUnitRate(bgxx.getUNITRATE());
		CurrentTaxDue.setAnnualTaxAmount(bgxx.getANNUALTAXAMOUNT());
		Paid_Type Paid = new Paid_Type();// 适用完税对象
		Paid.setTaxDepartmentCode(bgxx.getTAXDEPARTMENTCODE());				// 开具完税凭证的税务机关代码
		Paid.setTaxDepartment(bgxx.getTAXDEPARTMENT());						// 开具完税凭证的税务机关名称
		Paid.setTaxDocumentNumber(bgxx.getTAXDOCUMENTNUMBER());				// 完税凭证号码	
		Derate_Type Derate = new Derate_Type();								// 适用减免税对象
		Derate.setDeductionDueCode(bgxx.getDEDUCTIONDUECODE());				// 减免税原因代码,Y
		Derate.setDeductionDueType(bgxx.getDEDUCTIONDUETYPE());				// 减免税方案代码,Y
		Derate.setDeductionDueProportion(bgxx.getDEDUCTIONDUEPROPORTION());	// 减免比例
		Derate.setDeduction(bgxx.getDEDUCTION());							// 减免金额
		Derate.setDeductionDocumentNumber(bgxx.getDEDUCTIONDOCUMENTNUMBER());	// 减免税凭证号	
		Derate.setTaxDepartmentCode(bgxx.getDEPARTMENTCODE());			// 税务机关代码,Y
		Derate.setTaxDepartment(bgxx.getDEPARTMENT());					// 开具减免税凭证的税务机关名称,Y
		CurrentTaxDue.setPaid(Paid);
		CurrentTaxDue.setDerate(Derate);
		CurrentTaxDue.setTaxDue(bgxx.getTAXDUE());
		CurrentTaxDue.setExceedDate(DateUtil.getStringDate(bgxx.getEXCEEDDATE(),"yyyy-MM-dd"));
		CurrentTaxDue.setExceedDaysCount(bgxx.getEXCEEDDAYSCOUNT());
		CurrentTaxDue.setOverDue(bgxx.getOVERDUE());
		CurrentTaxDue.setTotalAmount(bgxx.getTOTALAMOUNT());
		AnnualTax_Type[] DelinquentTaxDue = new AnnualTax_Type[] {}; 		// 欠税信息对象
		taxInfo.setTaxAmount(TaxAmount);									// 车船税合计金额
		taxInfo.setCurrentTaxDue(CurrentTaxDue);							// 本年纳税信息对象
		taxInfo.setDelinquentTaxDue(DelinquentTaxDue);						// 欠税信息对象

		return taxInfo;
	}
}
