package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税申报日期上传服务定义服务 --请求信息对象入参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class DeclareDateUploadReqInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private String 	DeclareDate=null;//申报时间 yyyyddmm
	private TaxDealCode_Type[]	TaxConfirmNo=null;//车船税确认码
//	private TaxDealCode_Type[]  TaxChangeQueryNo = null;//车船税变更确认码
	private String UserName=null	;//用户名	String（6）	Y
	private String 	Password=null;//密码		String（6）	Y
	private String areaCode = null;//国标区域代码
	private String companyCode = null;//公司代码
	
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
	public String getDeclareDate() {
		return DeclareDate;
	}
	public void setDeclareDate(String declareDate) {
		DeclareDate = declareDate;
	}
	public TaxDealCode_Type[] getTaxConfirmNo() {
		return TaxConfirmNo;
	}
	public void setTaxConfirmNo(TaxDealCode_Type[] taxConfirmNo) {
		TaxConfirmNo = taxConfirmNo;
	}
//	public TaxDealCode_Type[] getTaxChangeQueryNo()
//	{
//		return TaxChangeQueryNo;
//	}
//	public void setTaxChangeQueryNo(TaxDealCode_Type[] taxChangeQueryNo)
//	{
//		TaxChangeQueryNo = taxChangeQueryNo;
//	}
	public String getAreaCode()
	{
		return areaCode;
	}
	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}
	public String getCompanyCode()
	{
		return companyCode;
	}
	public void setCompanyCode(String companyCode)
	{
		this.companyCode = companyCode;
	}
}
