package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税申报日期上传服务定义服务 --请求信息对象出参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class DeclareDateUploadResInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")

	private String ReturnCode=null;//返回代码
	private TaxDealCode_Type[] TaxConfirmNo = null;//车船税确认码


	public String getReturnCode() {
		return ReturnCode;
	}
	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}
	public TaxDealCode_Type[] getTaxConfirmNo()
	{
		return TaxConfirmNo;
	}
	public void setTaxConfirmNo(TaxDealCode_Type[] taxConfirmNo)
	{
		TaxConfirmNo = taxConfirmNo;
	}
	
	
	
}
