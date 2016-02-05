package com.derun.taxchangequery.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSBGXX;

/**
 * @author MILI
 * @time 2014-3-20 17:05:36
 * @描述：插入对象 SYJK_CCS_CCSBGXX 封装
 * */
public class P_SYJK_CCS_CCSBGXX {
	/**
	 * @author MILI
	 * @time 2014-3-20 17:05:36
	 * @描述：插入对象 SYJK_CCS_CCSBGXX 封装
	 * */
	public SYJK_CCS_CCSBGXX getCcsbgxx(BaseChangeQueryReqInfo basechangequeryreqinfo,Tax_Type taxInfo,TaxDealCode_Type ChangeQueryNo){
		SYJK_CCS_CCSBGXX ccsbgxx = new SYJK_CCS_CCSBGXX();
		Date startdate = null , enddate = null , exceedDate = null ;
		ccsbgxx.setTAXQUERYNO(ChangeQueryNo.getTaxDealCode_Type());
		ccsbgxx.setTAXTERMTYPECODE("08");
		ccsbgxx.setTAXCONDITIONCODE(taxInfo.getTaxConditionCode());
		ccsbgxx.setTAXREGISTRYNUMBER(taxInfo.getTaxRegistryNumber());
		ccsbgxx.setTAXPAYERNAME(taxInfo.getTaxPayerName());
		ccsbgxx.setTAXPAYERIDENTIFICATIONCODE(taxInfo.getTaxPayerIdentificationCode());
		ccsbgxx.setTAXLOCATIONCODE(taxInfo.getCurrentTaxDue().getTaxLocationCode());
		if (taxInfo.getCurrentTaxDue().getTaxStartDate() != null) {
			startdate = DateUtil.getStringDate(taxInfo.getCurrentTaxDue().getTaxStartDate(), "yyyy-MM-dd");
		} 
		ccsbgxx.setTAXSTARTDATE(startdate);
		if (taxInfo.getCurrentTaxDue().getTaxEndDate() != null) {
			enddate = DateUtil.getStringDate(taxInfo.getCurrentTaxDue().getTaxEndDate(), "yyyy-MM-dd");
		} 
		ccsbgxx.setTAXENDDATE(enddate);
		
		ccsbgxx.setTAXUNITTYPECODE(taxInfo.getCurrentTaxDue().getTaxUnitTypeCode());
		ccsbgxx.setUNITRATE(taxInfo.getCurrentTaxDue().getUnitRate());
		ccsbgxx.setANNUALTAXAMOUNT(taxInfo.getCurrentTaxDue().getAnnualTaxAmount());
		if(taxInfo.getCurrentTaxDue() != null && taxInfo.getCurrentTaxDue().getDerate() != null){
			ccsbgxx.setDEPARTMENTCODE(taxInfo.getCurrentTaxDue().getDerate().getTaxDepartmentCode());
			ccsbgxx.setDEPARTMENT(taxInfo.getCurrentTaxDue().getDerate().getTaxDepartment());
			ccsbgxx.setDEDUCTIONDUECODE(taxInfo.getCurrentTaxDue().getDerate().getDeductionDueCode());
			ccsbgxx.setDEDUCTIONDUETYPE(taxInfo.getCurrentTaxDue().getDerate().getDeductionDueType());
			ccsbgxx.setDEDUCTIONDUEPROPORTION(taxInfo.getCurrentTaxDue().getDerate().getDeductionDueProportion());
			ccsbgxx.setDEDUCTION(taxInfo.getCurrentTaxDue().getDerate().getDeduction());
			ccsbgxx.setDEDUCTIONDOCUMENTNUMBER(taxInfo.getCurrentTaxDue().getDerate().getDeductionDocumentNumber());
		}else{
			ccsbgxx.setDEPARTMENTCODE(null);
			ccsbgxx.setDEPARTMENT(null);
			ccsbgxx.setDEDUCTIONDUECODE(null);
			ccsbgxx.setDEDUCTIONDUETYPE(null);
			ccsbgxx.setDEDUCTIONDUEPROPORTION(null);
			ccsbgxx.setDEDUCTION(null);
			ccsbgxx.setDEDUCTIONDOCUMENTNUMBER(null);
		}
		if(taxInfo.getCurrentTaxDue() != null && taxInfo.getCurrentTaxDue().getPaid() != null){
			ccsbgxx.setTAXDOCUMENTNUMBER(taxInfo.getCurrentTaxDue().getPaid().getTaxDocumentNumber());
			ccsbgxx.setTAXDEPARTMENTCODE(taxInfo.getCurrentTaxDue().getPaid().getTaxDepartmentCode());
			ccsbgxx.setTAXDEPARTMENT(taxInfo.getCurrentTaxDue().getPaid().getTaxDepartment());
		}else{
			ccsbgxx.setTAXDOCUMENTNUMBER(null);
			ccsbgxx.setTAXDEPARTMENTCODE(null);
			ccsbgxx.setTAXDEPARTMENT(null);
		}
		ccsbgxx.setTAXDUE(taxInfo.getCurrentTaxDue().getTaxDue());
		if (taxInfo.getCurrentTaxDue().getExceedDate() != null) {
			exceedDate = DateUtil.getStringDate(taxInfo.getCurrentTaxDue().getExceedDate(), "yyyy-MM-dd");
		} 
		ccsbgxx.setEXCEEDDATE(exceedDate);
		ccsbgxx.setEXCEEDDAYSCOUNT(taxInfo.getCurrentTaxDue().getExceedDaysCount());
		ccsbgxx.setOVERDUE(taxInfo.getCurrentTaxDue().getOverDue());
		ccsbgxx.setTOTALAMOUNT(taxInfo.getCurrentTaxDue().getTotalAmount());
		ccsbgxx.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));			// 操作员
		ccsbgxx.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
		ccsbgxx.setPAYCOMPANYCODE(taxInfo.getPayCompanyCode());
		ccsbgxx.setPAYDATE(DateUtil.getStringDate(taxInfo.getPayDate(), "yyyy"));
		ccsbgxx.setPARATYPE("1");
		ccsbgxx.setCARSERIALNO(null);
		ccsbgxx.setINSURESTARTDATE(basechangequeryreqinfo.getInsureStartDate());
		ccsbgxx.setINSUREENDDATE(basechangequeryreqinfo.getInsureEndDate());
		return ccsbgxx ;
	}
	/**
	 * @author MILI
	 * @time 2014-3-20 17:05:36
	 * @描述：插入对象 SYJK_CCS_CCSBGXX 封装 欠税
	 * */
	public List<SYJK_CCS_CCSBGXX> getCcsbgxx_qs(BaseChangeQueryReqInfo basechangequeryreqinfo,Tax_Type taxInfo,TaxDealCode_Type ChangeQueryNo){
		List<SYJK_CCS_CCSBGXX> list_ccsbgxx = new ArrayList<SYJK_CCS_CCSBGXX>() ;
		SYJK_CCS_CCSBGXX ccsbgxx = null ;
		AnnualTax_Type annualtax = null ;
		int nuber = taxInfo.getDelinquentTaxDue() == null ? 0 : taxInfo.getDelinquentTaxDue().length;
		for(int i = 0 ; i < nuber ; i++){
			annualtax = taxInfo.getDelinquentTaxDue()[i];
			ccsbgxx = new SYJK_CCS_CCSBGXX();
			ccsbgxx.setTAXQUERYNO(ChangeQueryNo.getTaxDealCode_Type());
			ccsbgxx.setUNITRATE(annualtax.getUnitRate());
			if(annualtax.getDerate() != null){
				ccsbgxx.setDEPARTMENT(annualtax.getDerate().getTaxDepartment());
				ccsbgxx.setDEPARTMENTCODE(annualtax.getDerate().getTaxDepartmentCode());
				ccsbgxx.setDEDUCTIONDOCUMENTNUMBER(annualtax.getDerate().getDeductionDocumentNumber());
				ccsbgxx.setDEDUCTIONDUECODE(annualtax.getDerate().getDeductionDueCode());
				ccsbgxx.setDEDUCTION(annualtax.getDerate().getDeduction());
				ccsbgxx.setDEDUCTIONDUETYPE(annualtax.getDerate().getDeductionDueType());
				ccsbgxx.setDEDUCTIONDUEPROPORTION(annualtax.getDerate().getDeductionDueProportion());
			}else{
				ccsbgxx.setDEPARTMENT(null);
				ccsbgxx.setDEPARTMENTCODE(null);
				ccsbgxx.setDEDUCTIONDOCUMENTNUMBER(null);
				ccsbgxx.setDEDUCTIONDUECODE(null);
				ccsbgxx.setDEDUCTION(null);
				ccsbgxx.setDEDUCTIONDUETYPE(null);
				ccsbgxx.setDEDUCTIONDUEPROPORTION(null);
			}
			String ExceedDate = annualtax.getExceedDate();
			if (ExceedDate != null && ExceedDate.length() >= 10) {
				String exceD = annualtax.getExceedDate().substring(0, 10);
				ExceedDate = exceD;
			} else {
				ExceedDate = "";
			}
			ccsbgxx.setEXCEEDDATE(DateUtil.getStringDate(ExceedDate,null));
			ccsbgxx.setEXCEEDDAYSCOUNT(annualtax.getExceedDaysCount());
			ccsbgxx.setOVERDUE(annualtax.getOverDue());
			ExceedDate = annualtax.getTaxStartDate();
			if (ExceedDate != null && ExceedDate.length() >= 10) {
				String exceD = annualtax.getTaxStartDate().substring(0, 10);
				ExceedDate = exceD;
			} else {
				ExceedDate = "";
			}
			ccsbgxx.setTAXSTARTDATE(DateUtil.getStringDate(ExceedDate,null));
			ExceedDate = annualtax.getTaxEndDate();
			if (ExceedDate != null && ExceedDate.length() >= 10) {
				String exceD = annualtax.getTaxEndDate().substring(0, 10);
				ExceedDate = exceD;
			} else {
				ExceedDate = "";
			}
			ccsbgxx.setTAXENDDATE(DateUtil.getStringDate(ExceedDate,null));
			ccsbgxx.setTAXUNITTYPECODE(annualtax.getTaxUnitTypeCode());
			ccsbgxx.setANNUALTAXAMOUNT(annualtax.getAnnualTaxAmount());
			ccsbgxx.setTAXDUE(annualtax.getTaxDue());
			if(annualtax.getPaid() != null){
				ccsbgxx.setTAXDEPARTMENT(annualtax.getPaid().getTaxDepartment());
				ccsbgxx.setTAXDOCUMENTNUMBER(annualtax.getPaid().getTaxDocumentNumber());
				ccsbgxx.setTAXDEPARTMENTCODE(annualtax.getPaid().getTaxDepartmentCode());
			}else{
				ccsbgxx.setTAXDEPARTMENT(null);
				ccsbgxx.setTAXDOCUMENTNUMBER(null);
				ccsbgxx.setTAXDEPARTMENTCODE(null);
			}
			if(basechangequeryreqinfo.getTaxInfo().getCurrentTaxDue() != null){
				ccsbgxx.setTAXLOCATIONCODE(basechangequeryreqinfo.getTaxInfo().getCurrentTaxDue().getTaxLocationCode());
			}else{
				ccsbgxx.setTAXLOCATIONCODE("");
			}
			ccsbgxx.setPARATYPE("1");
			ccsbgxx.setTAXESFLAG("1");
			ccsbgxx.setTOTALAMOUNT(annualtax.getTotalAmount());
			ccsbgxx.setTAXLOCATIONCODE(annualtax.getTaxLocationCode());
			list_ccsbgxx.add(ccsbgxx);
		}
		return list_ccsbgxx ;
	}
}
