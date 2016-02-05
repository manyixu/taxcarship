package com.derun.taxreconciliation.vo;

import java.util.ArrayList;
import java.util.List;

import com.derun.beans.TaxAmount_Type;

/**
 * 
 * @author 郑艳英
 * 对帐beans
 */
public class TaxReconciliationBeans {
	 private String returnCode;
     private List<TaxAmount_Type> taxAmoutType = new ArrayList<TaxAmount_Type>(); //车船税合计金额数据类型
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public List<TaxAmount_Type> getTaxAmoutType() {
		return taxAmoutType;
	}
	public void setTaxAmoutType(List<TaxAmount_Type> taxAmoutType) {
		this.taxAmoutType = taxAmoutType;
	}

}
