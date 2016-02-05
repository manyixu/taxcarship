package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 * @param 车船税纳税信息查询服务上传服务--请求信息对象出参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class TaxPayQueryResInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private Tax_Type taxInfo=null;	;//车船税信息,Y
	private String 	DeclaredStatus=null	;//纳税状态,Y
	private String 	ReturnCode=null;//返回代码,Y (1,10)
	private String taxFlag;//算税标志
	


	public String getTaxFlag() {
		return taxFlag;
	}
	public void setTaxFlag(String taxFlag) {
		this.taxFlag = taxFlag;
	}
	public String getDeclaredStatus() {
		return DeclaredStatus;
	}
	public void setDeclaredStatus(String declaredStatus) {
		DeclaredStatus = declaredStatus;
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

}
