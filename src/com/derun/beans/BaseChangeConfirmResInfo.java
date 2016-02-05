package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税变更确认服务--请求信息对象出参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class BaseChangeConfirmResInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private TaxDealCode_Type ChangeConfirmNo=null;// 车船税变更确认码 ,Y

	private TaxDealCode_Type TaxPrintNo=null;// 车船税变更打印码
	// subaofeng 2013-7-3 10:42:16 start
	private String carMatchId = null; // 是哪条匹配规则匹配到的。
	// subaofeng 2013-7-3 10:42:26 end
	private String ReturnCode=null;// 返回代码 ,Y
	
	private String carSerialNo = null;//机动车序列号
	
	public String getCarSerialNo() {
		return carSerialNo;
	}	
	

	public String getCarMatchId() {
		return carMatchId;
	}

	public void setCarMatchId(String carMatchId) {
		this.carMatchId = carMatchId;
	}

	public TaxDealCode_Type getChangeConfirmNo() {
		return ChangeConfirmNo;
	}

	public void setChangeConfirmNo(TaxDealCode_Type changeConfirmNo) {
		ChangeConfirmNo = changeConfirmNo;
	}

	

	public TaxDealCode_Type getTaxPrintNo() {
		return TaxPrintNo;
	}

	public void setTaxPrintNo(TaxDealCode_Type taxPrintNo) {
		TaxPrintNo = taxPrintNo;
	}

	public String getReturnCode() {
		return ReturnCode;
	}

	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}

}
