package com.derun.taxchangequery.dao.impl;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-5-14 11:20:07
 * @描述：变更查询 	返回出参 辅助类
 */
public class TaxChangeQueryImpl {
	/**
	 * @author MILI
	 * @time 2014-5-14 14:37:37
	 * @描述：批改查询 返回出参  Tax_Type 封装
	 * */
	public Tax_Type getTaxType(BaseChangeQueryReqInfo basechangequeryreqinfo,Tax_Type taxInfo,TaxDealCode_Type type){
		Tax_Type tax_type = taxInfo ;
		
		tax_type.setPayCompanyCode(basechangequeryreqinfo.getCompanyCode());
		
		if(taxInfo!=null && taxInfo.getPayDate()!=null && taxInfo.getPayDate().length()>3){
			tax_type.setPayDate(taxInfo.getPayDate().substring(0, 4));
		}else{
			tax_type.setPayDate(DateUtil.getStringDate(basechangequeryreqinfo.getInsureStartDate(), "yyyy"));
		}
//		tax_type.setTaxConditionCode(taxInfo.getTaxConditionCode());
		tax_type.setTaxConditionCode(basechangequeryreqinfo.getTaxInfo().getTaxConditionCode());
		tax_type.setTaxPayerIdentificationCode(basechangequeryreqinfo.getTaxInfo().getTaxPayerIdentificationCode());
		tax_type.setTaxPayerName(basechangequeryreqinfo.getTaxInfo().getTaxPayerName());
		tax_type.setTaxRegistryNumber(basechangequeryreqinfo.getTaxInfo().getTaxRegistryNumber());
		tax_type.setTaxTermTypeCode(basechangequeryreqinfo.getTaxInfo().getTaxTermTypeCode());
		
		tax_type.setCurrentTaxDue(this.getAT(basechangequeryreqinfo,taxInfo));
		tax_type.setDelinquentTaxDue(this.getATS(basechangequeryreqinfo,taxInfo));
		tax_type.setTaxAmount(this.getTT(type,taxInfo));
		return tax_type ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-14 14:45:29
	 * @描述：批改查询 返回出参  AnnualTax_Type 封装
	 * */
	public AnnualTax_Type getAT(BaseChangeQueryReqInfo basechangequeryreqinfo,Tax_Type taxInfo){
		AnnualTax_Type AT = new AnnualTax_Type();
		AT = taxInfo.getCurrentTaxDue();
		if(AT != null){
			if(AT.getDerate() == null){
				AT.setDerate(new Derate_Type());
			}
			if(AT.getPaid() == null){
				AT.setPaid(new Paid_Type());
			}
		}
		AT.setTaxLocationCode(basechangequeryreqinfo.getAreaCode());	// 纳税地区代码
//		AT.setTaxUnitTypeCode("1");										// 计算单位代码
		return AT ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-14 14:45:47
	 * @描述：批改查询 返回出参  AnnualTax_Type[] 封装
	 * */
	public AnnualTax_Type[] getATS(BaseChangeQueryReqInfo basechangequeryreqinfo,Tax_Type taxInfo){
		AnnualTax_Type[] AT = null ;
		AnnualTax_Type[] anntax = taxInfo.getDelinquentTaxDue();
		if(anntax != null && anntax.length > 0){
			int number = anntax.length ;
			AT = new AnnualTax_Type[number];
			for(int i = 0 ; i < number ; i++){
				Paid_Type PT = anntax[i].getPaid();
				Derate_Type DT = anntax[i].getDerate();
				AT[i] = anntax[i];
				AT[i].setTaxLocationCode(basechangequeryreqinfo.getAreaCode());		// 纳税地区代码
//				AT[i].setTaxUnitTypeCode("1");									// 计算单位代码
				if(PT != null){
//					Paid_Type paid = anntax[i].getPaid();
//					PT.setTaxDepartment(paid.getTaxDepartment());				// 开具完税凭证的税务机关名称
//					PT.setTaxDepartmentCode(paid.getTaxDepartmentCode());		// 开具完税凭证的税务机关代码
//					PT.setTaxDocumentNumber(paid.getTaxDocumentNumber());		// 完税凭证号码
					AT[i].setPaid(PT);
				}else{
					AT[i].setPaid(new Paid_Type());
				}
				if(DT != null){
//					Derate_Type derate = basechangequeryreqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate();
//					DT.setDeduction(derate.getDeduction());
//					DT.setDeductionDocumentNumber(derate.getDeductionDocumentNumber());
//					DT.setDeductionDueCode(derate.getDeductionDueCode());
//					DT.setDeductionDueProportion(derate.getDeductionDueProportion());
//					DT.setDeductionDueType(derate.getDeductionDueType());
//					DT.setTaxDepartment(derate.getTaxDepartment());
//					DT.setTaxDepartmentCode(derate.getDeductionDueCode());
					AT[i].setDerate(DT);
				}else{
					AT[i].setDerate(new Derate_Type());
				}
			}
		}
		return AT ;
	}/**
	 * @author MILI
	 * @time 2014-5-14 14:47:20
	 * @描述：批改查询 返回出参  TaxAmount_Type 封装
	 * */
	public TaxAmount_Type getTT(TaxDealCode_Type taxQueryNo,Tax_Type taxInfo){
		TaxAmount_Type TT = new TaxAmount_Type();
		TT = taxInfo.getTaxAmount();
		if(TT != null){
			TT.setTaxAmount_Flag("3");
			TaxDealCode_Type taxdelcode = new TaxDealCode_Type();
			taxdelcode.setTaxDealCode_Type(taxQueryNo.getTaxDealCode_Type());
			TT.setTaxDealCode(taxdelcode);
		}
		return TT ;
	}
	/**
	 * @author MILI
	 * @time 2014年11月10日16:30:00
	 * @描述：
	 * */
	public SYJK_CCS_RKMX getCommon_rkmx(SYJK_CCS_RKMX rkmx){
		if(rkmx != null){
			String taxStartDate = rkmx.getTAXSTARTDATE();
			String taxEndDate = rkmx.getTAXENDDATE();
			String firstdate = rkmx.getFIRSTREGISTERDATE();
			if(taxStartDate != null && !"".equals(taxStartDate)){
				if(taxStartDate.length() >= 10){
					taxStartDate = taxStartDate.substring(0, 10);
					rkmx.setTAXSTARTDATE(taxStartDate);
				}
			}
			if(taxEndDate != null && !"".equals(taxEndDate)){
				if(taxEndDate.length() >= 10){
					taxEndDate = taxEndDate.substring(0, 10);
					rkmx.setTAXENDDATE(taxEndDate);
				}
			}
			if(firstdate != null && !"".equals(firstdate)){
				if(firstdate.length() >= 10){
					firstdate = firstdate.substring(0, 10);
					rkmx.setFIRSTREGISTERDATE(firstdate);
				}
			}
		}
		return rkmx ;
	}
}
