package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 减免税数据类型
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class Derate_Type implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private String DeductionDueCode = null;// 减免税原因代码,Y

	private String DeductionDueType = null;// 减免税方案代码,Y

	private double DeductionDueProportion = 0;// 减免比例

	private double Deduction = 0;// 减免金额

	private String DeductionDocumentNumber = null;// 减免税凭证号

	private String TaxDepartmentCode = null;// 税务机关代码,Y

	private String TaxDepartment = null;// 开具减免税凭证的税务机关名称,Y
	
	


	public double getDeduction() {
		return Deduction;
	}

	public void setDeduction(double deduction) {
		Deduction = deduction;
	}

	public String getDeductionDocumentNumber() {
		return DeductionDocumentNumber;
	}

	public void setDeductionDocumentNumber(String deductionDocumentNumber) {
		DeductionDocumentNumber = deductionDocumentNumber;
	}

	public String getDeductionDueCode() {
		return DeductionDueCode;
	}

	public void setDeductionDueCode(String deductionDueCode) {
		DeductionDueCode = deductionDueCode;
	}

	public double getDeductionDueProportion() {
		return DeductionDueProportion;
	}

	public void setDeductionDueProportion(double deductionDueProportion) {
		DeductionDueProportion = deductionDueProportion;
	}

	public String getDeductionDueType() {
		return DeductionDueType;
	}

	public void setDeductionDueType(String deductionDueType) {
		DeductionDueType = deductionDueType;
	}

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


}
