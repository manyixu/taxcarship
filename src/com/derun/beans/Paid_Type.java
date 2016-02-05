package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 完税数据类型
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class Paid_Type implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private String TaxDepartmentCode=null;// 开具完税凭证的税务机关代码

	private String TaxDepartment=null;// 开具完税凭证的税务机关名称

	private String TaxDocumentNumber=null;// 完税凭证号码

	public String getTaxDepartment() {
		return TaxDepartment;
	}

	public void setTaxDepartment(String taxDepartment) {
		TaxDepartment = taxDepartment;
	}

	public String getTaxDepartmentCode() {
		return TaxDepartmentCode;
	}

	public void setTaxDepartmentCode(String taxDepartmentCode) {
		TaxDepartmentCode = taxDepartmentCode;
	}

	public String getTaxDocumentNumber() {
		return TaxDocumentNumber;
	}

	public void setTaxDocumentNumber(String taxDocumentNumber) {
		TaxDocumentNumber = taxDocumentNumber;
	}

}
