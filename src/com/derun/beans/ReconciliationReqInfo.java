package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税对账服务--请求信息对象入参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class ReconciliationReqInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private String CheckingType=null;// 车船税对账类型 ,Y
	private	TaxDealCode_Type[] TaxConfirmNo;//	确认码数组 
	private String UserName=null	;//用户名	String（6）	Y
	private String 	Password=null;//密码		String（6）	Y
	private String AreaCode=null;//国标区域代码
	private String CompanyCode=null;//公司代码

	
	public String getAreaCode() {
		return AreaCode;
	}

	public void setAreaCode(String areaCode) {
		AreaCode = areaCode;
	}

	public String getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	

	public String getCheckingType() {
		return CheckingType;
	}

	public void setCheckingType(String checkingType) {
		CheckingType = checkingType;
	}

	public TaxDealCode_Type[] getTaxConfirmNo() {
		return TaxConfirmNo;
	}

	public void setTaxConfirmNo(TaxDealCode_Type[] taxConfirmNo) {
		TaxConfirmNo = taxConfirmNo;
	}

}
