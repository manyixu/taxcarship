package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税数据类型
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class Tax_Type implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private String TaxTermTypeCode = null;// 税种类型代码,Y

	private String TaxConditionCode = null;// 纳税类型代码,Y

	private String TaxRegistryNumber = null;// 税务登记证号

	private String TaxPayerName = null;// 纳税人名称

	private String TaxPayerIdentificationCode = null;// 纳税人识别号
	/************* New *******************/
	private String PayDate = null;// 代收日期

	private String PayCompanyCode = null;// 代收公司
	/********************************/
	private AnnualTax_Type CurrentTaxDue = null;// 本年纳税信息对象

	private AnnualTax_Type[] DelinquentTaxDue = null; // 欠税信息对象

	private TaxAmount_Type TaxAmount = null;// 车船税合计金额

	public AnnualTax_Type getCurrentTaxDue() {
		return CurrentTaxDue;
	}

	public void setCurrentTaxDue(AnnualTax_Type currentTaxDue) {
		CurrentTaxDue = currentTaxDue;
	}

	public AnnualTax_Type[] getDelinquentTaxDue() {
		return DelinquentTaxDue;
	}

	public void setDelinquentTaxDue(AnnualTax_Type[] delinquentTaxDue) {
		DelinquentTaxDue = delinquentTaxDue;
	}

	public TaxAmount_Type getTaxAmount() {
		return TaxAmount;
	}

	public void setTaxAmount(TaxAmount_Type taxAmount) {
		TaxAmount = taxAmount;
	}

	public String getTaxConditionCode() {
		return TaxConditionCode;
	}

	public void setTaxConditionCode(String taxConditionCode) {
		TaxConditionCode = taxConditionCode;
	}

	public String getTaxPayerIdentificationCode() {
		return TaxPayerIdentificationCode;
	}

	public void setTaxPayerIdentificationCode(String taxPayerIdentificationCode) {
		TaxPayerIdentificationCode = taxPayerIdentificationCode;
	}

	public String getTaxPayerName() {
		return TaxPayerName;
	}

	public void setTaxPayerName(String taxPayerName) {
		TaxPayerName = taxPayerName;
	}

	public String getTaxRegistryNumber() {
		return TaxRegistryNumber;
	}

	public void setTaxRegistryNumber(String taxRegistryNumber) {
		TaxRegistryNumber = taxRegistryNumber;
	}

	public String getTaxTermTypeCode() {
		return TaxTermTypeCode;
	}

	public void setTaxTermTypeCode(String taxTermTypeCode) {
		TaxTermTypeCode = taxTermTypeCode;
	}

	public String getPayCompanyCode() {
		return PayCompanyCode;
	}

	public void setPayCompanyCode(String payCompanyCode) {
		PayCompanyCode = payCompanyCode;
	}

	public String getPayDate() {
		return PayDate;
	}

	public void setPayDate(String payDate) {
		PayDate = payDate;
	}

}
