package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税查询服务--请求信息对象出参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class BaseQueryResInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private TaxDealCode_Type TaxQueryNo=null;// 车船税查询码,Y

	private String CalcTaxFlag=null;// 算税标志,Y

	private Tax_Type taxInfo=null;// 车船税信息

	private String ReturnCode=null;// 返回代码,Y
//	private String UserName=null	;//用户名	String（6）	Y
//	private String 	Password=null;//密码		String（6）	Y
	private String carMatchId = null;//用于记录这辆车是用哪个匹配规则匹配到的
	
	private String carSerialNo = null;//机动车序列号
	
	public String getCarSerialNo() {
		return carSerialNo;
	}

	public void setCarSerialNo(String carSerialNo) {
		this.carSerialNo = carSerialNo;
	}

	public String getCalcTaxFlag() {
		return CalcTaxFlag;
	}

	public void setCalcTaxFlag(String calcTaxFlag) {
		CalcTaxFlag = calcTaxFlag;
	}

	public String getReturnCode() {
		return ReturnCode;
	}

	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}

	public Tax_Type getTaxInfo() {
		return taxInfo;
	}

	public void setTaxInfo(Tax_Type taxInfo) {
		this.taxInfo = taxInfo;
	}

	public TaxDealCode_Type getTaxQueryNo() {
		return TaxQueryNo;
	}

	public void setTaxQueryNo(TaxDealCode_Type taxQueryNo) {
		TaxQueryNo = taxQueryNo;
	}

	public String getCarMatchId() {
		return carMatchId;
	}

	public void setCarMatchId(String carMatchId) {
		this.carMatchId = carMatchId;
	}
	
}
