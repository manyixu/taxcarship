package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税合计金额数据类型
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class TaxAmount_Type implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private TaxDealCode_Type TaxDealCode=null;// 车船税交易码,用于对账服务

	private String TaxAmount_Flag=null;// 合计金额标志码,Y

	private double AnnualTaxDue=0.00;// 本年车船税金额

	private double SumTaxDefault=0.00;// 合计欠税金额

	private double SumOverdue=0.00;// 合计滞纳金

	private double SumTax=0.00;// 合计金额,Y


	
	public double getAnnualTaxDue() {
		return AnnualTaxDue;
	}

	public void setAnnualTaxDue(double annualTaxDue) {
		AnnualTaxDue = annualTaxDue;
	}

	public double getSumOverdue() {
		return SumOverdue;
	}

	public void setSumOverdue(double sumOverdue) {
		SumOverdue = sumOverdue;
	}

	public double getSumTax() {
		return SumTax;
	}

	public void setSumTax(double sumTax) {
		SumTax = sumTax;
	}

	public double getSumTaxDefault() {
		return SumTaxDefault;
	}

	public void setSumTaxDefault(double sumTaxDefault) {
		SumTaxDefault = sumTaxDefault;
	}

	public String getTaxAmount_Flag() {
		return TaxAmount_Flag;
	}

	public void setTaxAmount_Flag(String taxAmount_Flag) {
		TaxAmount_Flag = taxAmount_Flag;
	}

	public TaxDealCode_Type getTaxDealCode() {
		return TaxDealCode;
	}

	public void setTaxDealCode(TaxDealCode_Type taxDealCode) {
		TaxDealCode = taxDealCode;
	}

}
