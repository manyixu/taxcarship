package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 年度纳税数据类型
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class AnnualTax_Type implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private String TaxLocationCode = null;// 纳税地区代码

	private String TaxStartDate = null;// 税款所属始期

	private String TaxEndDate = null;// 税款所属止期

	private String TaxUnitTypeCode = null;// 计税单位代码

	private double UnitRate = 0;// 单位计税金额

	private double AnnualTaxAmount = 0;// 当期年单位税额

	private Paid_Type Paid = null;// 适用完税对象

	private Derate_Type Derate = null;// 适用减免税对象

	private double TaxDue = 0;// 当期应纳税额

	private String ExceedDate = null;// 逾期时间

	private int ExceedDaysCount = 0;// 逾期天数

	private double OverDue = 0;// 滞纳金

	private double TotalAmount = 0;// 合计金额

	public double getAnnualTaxAmount() {
		return AnnualTaxAmount;
	}

	public void setAnnualTaxAmount(double annualTaxAmount) {
		AnnualTaxAmount = annualTaxAmount;
	}

	public Derate_Type getDerate() {
		return Derate;
	}

	public void setDerate(Derate_Type derate) {
		Derate = derate;
	}

	public String getExceedDate() {
		return ExceedDate;
	}

	public void setExceedDate(String exceedDate) {
		ExceedDate = exceedDate;
	}

	public int getExceedDaysCount() {
		return ExceedDaysCount;
	}

	public void setExceedDaysCount(int exceedDaysCount) {
		ExceedDaysCount = exceedDaysCount;
	}

	public double getOverDue() {
		return OverDue;
	}

	public void setOverDue(double overDue) {
		OverDue = overDue;
	}

	public Paid_Type getPaid() {
		return Paid;
	}

	public void setPaid(Paid_Type paid) {
		Paid = paid;
	}

	public double getTaxDue() {
		return TaxDue;
	}

	public void setTaxDue(double taxDue) {
		TaxDue = taxDue;
	}

	public String getTaxEndDate() {
		return TaxEndDate;
	}

	public void setTaxEndDate(String taxEndDate) {
		TaxEndDate = taxEndDate;
	}

	public String getTaxLocationCode() {
		return TaxLocationCode;
	}

	public void setTaxLocationCode(String taxLocationCode) {
		TaxLocationCode = taxLocationCode;
	}

	public String getTaxStartDate() {
		return TaxStartDate;
	}

	public void setTaxStartDate(String taxStartDate) {
		TaxStartDate = taxStartDate;
	}

	public String getTaxUnitTypeCode() {
		return TaxUnitTypeCode;
	}

	public void setTaxUnitTypeCode(String taxUnitTypeCode) {
		TaxUnitTypeCode = taxUnitTypeCode;
	}

	public double getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}

	public double getUnitRate() {
		return UnitRate;
	}

	public void setUnitRate(double unitRate) {
		UnitRate = unitRate;
	}

}
