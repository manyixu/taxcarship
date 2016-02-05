package com.derun.common.match;

import com.derun.all.common.StringUtil;
import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSBGXX;
/**
 * @author MILI
 * @time 2014-3-25 14:58:44
 * @描述：一致性效验对象封装
 * */
public class O_N_Encapsulation {
	StringUtil isnotnll = new StringUtil();
	/**
	 * @author MILI
	 * @time 2014-3-25 14:59:38
	 * @描述：批改确认一致性对象封装
	 * */
	public TaxComfirm_Consistency_VO setNEW(BaseChangeConfirmReqInfo basechangeconfirmreqinfo){
		TaxComfirm_Consistency_VO consNew = new TaxComfirm_Consistency_VO();
		Tax_Type taxinfo = new Tax_Type();					// 车船税数据类型
		TaxAmount_Type taxamout = new TaxAmount_Type();		// 车船税合计金额	
		AnnualTax_Type annualtax = new AnnualTax_Type();	// 年度纳税数据类型
		
		taxinfo = basechangeconfirmreqinfo.getTaxInfo() ;	 
		taxamout = taxinfo.getTaxAmount();
		annualtax = taxinfo.getCurrentTaxDue();
		consNew.setTaxConditionCode(StringUtil.Isnotnull(taxinfo.getTaxConditionCode()));
		consNew.setAnnualTaxAmount(annualtax.getAnnualTaxAmount());
		consNew.setAnnualTaxDue(taxamout.getAnnualTaxDue());
		if(annualtax.getDerate() != null){
			consNew.setDEDUCTIONDEPARTMENTCODE(StringUtil.Isnotnull(annualtax.getDerate().getTaxDepartmentCode()));		// 入参开具减免税凭证的税务机关代码 
			consNew.setDepartment(StringUtil.Isnotnull(annualtax.getDerate().getTaxDepartment())); 						// 入参开减免税凭证的税务机关名称
			consNew.setDocumentNumber(StringUtil.Isnotnull(annualtax.getDerate().getDeductionDocumentNumber())); 			// 入参减免凭证号码
			consNew.setDeductionDueCode(StringUtil.Isnotnull(annualtax.getDerate().getDeductionDueCode())); 				// 入参减免税原因代码
			consNew.setDeductionDueType(StringUtil.Isnotnull(annualtax.getDerate().getDeductionDueType())); 				// 入参减免税方案代码
			consNew.setDeductionDueProportion(annualtax.getDerate().getDeductionDueProportion()); 	// 入参减免比例
			consNew.setDeduction(annualtax.getDerate().getDeduction()); 							// 入参减免金额
		}else{
			consNew.setDEDUCTIONDEPARTMENTCODE(StringUtil.Isnotnull(null));	// 入参开具减免税凭证的税务机关代码 
			consNew.setDepartment(StringUtil.Isnotnull(null)); 				// 入参开减免税凭证的税务机关名称
			consNew.setDocumentNumber(StringUtil.Isnotnull(null)); 			// 入参减免凭证号码
			consNew.setDeductionDueCode(StringUtil.Isnotnull(null)); 			// 入参减免税原因代码
			consNew.setDeductionDueType(StringUtil.Isnotnull(null)); 			// 入参减免税方案代码
			consNew.setDeductionDueProportion(0.0); 	// 入参减免比例
			consNew.setDeduction(0.0); 					// 入参减免金额
		}
		if(annualtax.getPaid() != null){
			consNew.setTAXDEPARTMENT(StringUtil.Isnotnull(annualtax.getPaid().getTaxDepartment()));
			consNew.setTAXDEPARTMENTCODE(StringUtil.Isnotnull(annualtax.getPaid().getTaxDepartmentCode()));
			consNew.setTAXDOCUMENTNUMBER(StringUtil.Isnotnull(annualtax.getPaid().getTaxDocumentNumber()));
		}else{
			consNew.setTAXDEPARTMENT(StringUtil.Isnotnull(null));
			consNew.setTAXDEPARTMENTCODE(StringUtil.Isnotnull(null));
			consNew.setTAXDOCUMENTNUMBER(StringUtil.Isnotnull(null));
		}
		if(annualtax.getExceedDate() != null && annualtax.getExceedDate().length() >= 10){
			consNew.setExceedDate(StringUtil.Isnotnull(annualtax.getExceedDate().substring(0, 10)));
		}else{
			consNew.setExceedDate(StringUtil.Isnotnull(null));
		}
		
		consNew.setExceedDaysCount(annualtax.getExceedDaysCount());
		consNew.setOverDue(annualtax.getOverDue());
		consNew.setPayDate(StringUtil.Isnotnull(basechangeconfirmreqinfo.getTaxInfo().getPayDate()));
		consNew.setSumOverdue(taxamout.getSumOverdue());
		consNew.setSumTax(taxamout.getSumTax());
		consNew.setSumTaxDefault(taxamout.getSumTaxDefault());
		consNew.setTaxDue(annualtax.getTaxDue());
		if(annualtax.getTaxEndDate() != null && annualtax.getTaxEndDate().length() >= 10){
			consNew.setTaxEndDate(StringUtil.Isnotnull(annualtax.getTaxEndDate().substring(0, 10)));
		}else{
			consNew.setTaxEndDate(StringUtil.Isnotnull(null));
		}
		consNew.setTaxLocationCode(StringUtil.Isnotnull(annualtax.getTaxLocationCode()));
		if(annualtax.getTaxStartDate() != null && annualtax.getTaxStartDate().length() >= 10){
			consNew.setTaxStartDate(StringUtil.Isnotnull(annualtax.getTaxStartDate().substring(0, 10)));
		}else{
			consNew.setTaxStartDate(StringUtil.Isnotnull(null));
		}
		consNew.setTaxTermTypeCode(StringUtil.Isnotnull(taxinfo.getTaxTermTypeCode()));
		consNew.setTaxUnitTypeCode(StringUtil.Isnotnull(annualtax.getTaxUnitTypeCode()));
		consNew.setTotalAmount(annualtax.getTotalAmount());
		consNew.setUnitRate(annualtax.getUnitRate());
		return consNew;
	}
	/**
	 * @author MILI
	 * @time 2014-3-25 14:59:38
	 * @描述：批改确认一致性对象old封装
	 * */
	public TaxComfirm_Consistency_VO setOLD(SYJK_CCS_CCSBGXX ccsbgxx,SYJK_CCS_CCSBGCXCCJB bgcxcc){
		TaxComfirm_Consistency_VO consOld = new TaxComfirm_Consistency_VO();
		
		consOld.setTaxConditionCode(StringUtil.Isnotnull(ccsbgxx.getTAXCONDITIONCODE()));
		consOld.setAnnualTaxAmount(ccsbgxx.getANNUALTAXAMOUNT());
		consOld.setAnnualTaxDue(bgcxcc.getANNUALTAXDUE());
		
		consOld.setDEDUCTIONDEPARTMENTCODE(StringUtil.Isnotnull(ccsbgxx.getDEPARTMENTCODE()));		// 入参开具减免税凭证的税务机关代码 
		consOld.setDepartment(StringUtil.Isnotnull(ccsbgxx.getDEPARTMENT())); 						// 入参开减免税凭证的税务机关名称
		consOld.setDocumentNumber(StringUtil.Isnotnull(ccsbgxx.getDEDUCTIONDOCUMENTNUMBER())); 		// 入参减免凭证号码
		consOld.setDeductionDueCode(StringUtil.Isnotnull(ccsbgxx.getDEDUCTIONDUECODE())); 			// 入参减免税原因代码
		consOld.setDeductionDueType(StringUtil.Isnotnull(ccsbgxx.getDEDUCTIONDUETYPE())); 			// 入参减免税方案代码
		consOld.setDeductionDueProportion(ccsbgxx.getDEDUCTIONDUEPROPORTION()); // 入参减免比例
		consOld.setDeduction(ccsbgxx.getDEDUCTION()); 							// 入参减免金额
		
		
		consOld.setTAXDEPARTMENT(StringUtil.Isnotnull(ccsbgxx.getTAXDEPARTMENT()));
		consOld.setTAXDEPARTMENTCODE(StringUtil.Isnotnull(ccsbgxx.getTAXDEPARTMENTCODE()));
		consOld.setTAXDOCUMENTNUMBER(StringUtil.Isnotnull(ccsbgxx.getTAXDOCUMENTNUMBER()));
		
		consOld.setExceedDate(StringUtil.Isnotnull(DateUtil.getStringDate(ccsbgxx.getEXCEEDDATE(),null)));
		consOld.setExceedDaysCount(ccsbgxx.getEXCEEDDAYSCOUNT());
		consOld.setOverDue(ccsbgxx.getOVERDUE());
		consOld.setPayDate(StringUtil.Isnotnull(DateUtil.getStringDate(ccsbgxx.getPAYDATE(),"yyyy")));
		consOld.setSumOverdue(bgcxcc.getSUMOVERDUE());
		consOld.setSumTax(bgcxcc.getSUMTAX());
		consOld.setSumTaxDefault(bgcxcc.getSUMTAXDEFAULT());
		consOld.setTSBZ(bgcxcc.getTSBZ());
		consOld.setTaxDue(ccsbgxx.getTAXDUE());
		consOld.setTaxEndDate(StringUtil.Isnotnull(DateUtil.getStringDate(ccsbgxx.getTAXENDDATE(),null).substring(0, 10)));
		consOld.setTaxLocationCode(StringUtil.Isnotnull(ccsbgxx.getTAXLOCATIONCODE()));
		consOld.setTaxStartDate(StringUtil.Isnotnull(DateUtil.getStringDate(ccsbgxx.getTAXSTARTDATE(),null).substring(0, 10)));
		consOld.setTaxTermTypeCode(StringUtil.Isnotnull(ccsbgxx.getTAXTERMTYPECODE()));
		consOld.setTaxUnitTypeCode(StringUtil.Isnotnull(ccsbgxx.getTAXUNITTYPECODE()));
		consOld.setTotalAmount(ccsbgxx.getTOTALAMOUNT());
		consOld.setUnitRate(ccsbgxx.getUNITRATE());
		
		return consOld;
	}
}
