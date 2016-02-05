package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 船税确认服务--请求信息对象出参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class BaseConfirmResInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private TaxDealCode_Type TaxConfirmNo=null;// 车船税确认码,Y

	private TaxDealCode_Type TaxPrintNo=null;// 车船税打印码

	private String ReturnCode=null;// 返回代码 ,Y
	private String carMatchId = null;//用于记录这辆车是用哪个匹配规则匹配到的
	
	private String cityCode = null;//税款归属（地市）
	
	private String countryCode = null; //税款归属（县区）
	
	private String carSerialNo = null;//机动车序列号
	
	public String getCarSerialNo() {
		return carSerialNo;
	}
	
	public void setCarSerialNo(String carSerialNo) {
		this.carSerialNo = carSerialNo;
	}

	public String getReturnCode() {
		return ReturnCode;
	}

	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}

	public TaxDealCode_Type getTaxConfirmNo() {
		return TaxConfirmNo;
	}

	public void setTaxConfirmNo(TaxDealCode_Type taxConfirmNo) {
		TaxConfirmNo = taxConfirmNo;
	}

	public TaxDealCode_Type getTaxPrintNo() {
		return TaxPrintNo;
	}

	public void setTaxPrintNo(TaxDealCode_Type taxPrintNo) {
		TaxPrintNo = taxPrintNo;
	}

	public String getCarMatchId() {
		return carMatchId;
	}

	public void setCarMatchId(String carMatchId) {
		this.carMatchId = carMatchId;
	}


	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
}
