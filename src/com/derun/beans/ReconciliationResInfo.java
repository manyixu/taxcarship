package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税对账服务--请求信息对象出参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class ReconciliationResInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private TaxDealCode_Type TaxReconciliationNo=null;// 车船税对账码 ,Y

	private String CheckingType=null;// 车船税对账类型 ,Y

	private TaxAmount_Type[] CheckingArray=null;// 对账对象数组
	
	private String	ReturnCode	=null;//返回代码
	
	

	public String getReturnCode() {
		return ReturnCode;
	}

	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}

	public TaxAmount_Type[] getCheckingArray() {
		return CheckingArray;
	}

	public void setCheckingArray(TaxAmount_Type[] checkingArray) {
		CheckingArray = checkingArray;
	}

	public String getCheckingType() {
		return CheckingType;
	}

	public void setCheckingType(String checkingType) {
		CheckingType = checkingType;
	}

	public TaxDealCode_Type getTaxReconciliationNo() {
		return TaxReconciliationNo;
	}

	public void setTaxReconciliationNo(TaxDealCode_Type taxReconciliationNo) {
		TaxReconciliationNo = taxReconciliationNo;
	}
}
