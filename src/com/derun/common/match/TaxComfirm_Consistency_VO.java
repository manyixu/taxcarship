package com.derun.common.match;

import java.io.Serializable;
/**
 * @author MILI
 * @time 2014-3-25 14:54:32
 * @描述：一致性效验VO
 * */
public class TaxComfirm_Consistency_VO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String TaxTermTypeCode = "";			// 入参税种类型代码
	private String ExceedDate = "";					// 入参逾期时间
	private int ExceedDaysCount = 0;				// 入参逾期天数
	private String PayDate = "";					// 入参申报日期
	private String TaxLocationCode = "";			// 入参纳税地区代码
	private String TaxConditionCode = ""; 			// 入参纳税类型
	private String TaxStartDate = "";				// 入参税款所属始期
	private String TaxEndDate = "";					// 入参税款所属止期
	private double AnnualTaxDue = 0.00;				// 入参本年车船税金额
	private double SumTaxDefault = 0.00;			// 入参合计欠税金额
	private double SumOverdue = 0.00	;			// 入参合计滞纳金
	private double SumTax = 0.00;					// 入参合计金额
	private double UnitRate = 0.00;					// 入参单位计税金额
	private double AnnualTaxAmount = 0.00;			// 入参当期年单位税额
	private double TaxDue = 0.00;					// 当期应纳税额
	private String TaxUnitTypeCode = "";			// 计税单位代码
	private double TotalAmount = 0.00;				// 合计金额
	private String TSBZ = "";						// 退税标志
	private String DEDUCTIONDEPARTMENTCODE = "" ;	// 入参开具减免税凭证的税务机关代码 
	private String Department = ""; 				// 入参开减免税凭证的税务机关名称
	private String DocumentNumber = ""; 			// 入参减免凭证号码
	private String DeductionDueCode = ""; 			// 入参减免税原因代码
	private String DeductionDueType = ""; 			// 入参减免税方案代码
	private double DeductionDueProportion = 0.0; 	// 入参减免比例
	private double Deduction = 0.00; 				// 入参减免金额
	
	private String TAXDEPARTMENT = ""; 				// 入参开具减免税凭证的税务机关名称
	private String TAXDEPARTMENTCODE = "";			// 开具完税凭证的税务机关代码 VARCHAR2(11)
	private String TAXDOCUMENTNUMBER = "";			// 完税凭证号码 VARCHAR2(30)
	
	public String getTSBZ() {
		return TSBZ;
	}
	public void setTSBZ(String tSBZ) {
		TSBZ = tSBZ;
	}
	public String getDEDUCTIONDEPARTMENTCODE() {
		return DEDUCTIONDEPARTMENTCODE;
	}
	public void setDEDUCTIONDEPARTMENTCODE(String dEDUCTIONDEPARTMENTCODE) {
		DEDUCTIONDEPARTMENTCODE = dEDUCTIONDEPARTMENTCODE;
	}
	public String getTAXDEPARTMENT() {
		return TAXDEPARTMENT;
	}
	public void setTAXDEPARTMENT(String tAXDEPARTMENT) {
		TAXDEPARTMENT = tAXDEPARTMENT;
	}
	public String getTAXDEPARTMENTCODE() {
		return TAXDEPARTMENTCODE;
	}
	public void setTAXDEPARTMENTCODE(String tAXDEPARTMENTCODE) {
		TAXDEPARTMENTCODE = tAXDEPARTMENTCODE;
	}
	public String getTAXDOCUMENTNUMBER() {
		return TAXDOCUMENTNUMBER;
	}
	public void setTAXDOCUMENTNUMBER(String tAXDOCUMENTNUMBER) {
		TAXDOCUMENTNUMBER = tAXDOCUMENTNUMBER;
	}
	private double OverDue = 0.00;					// 欠税
	public String getTaxTermTypeCode() {
		return TaxTermTypeCode;
	}
	public void setTaxTermTypeCode(String taxTermTypeCode) {
		TaxTermTypeCode = taxTermTypeCode;
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
	public String getPayDate() {
		return PayDate;
	}
	public void setPayDate(String payDate) {
		PayDate = payDate;
	}
	public String getTaxLocationCode() {
		return TaxLocationCode;
	}
	public void setTaxLocationCode(String taxLocationCode) {
		TaxLocationCode = taxLocationCode;
	}
	public String getTaxConditionCode() {
		return TaxConditionCode;
	}
	public void setTaxConditionCode(String taxConditionCode) {
		TaxConditionCode = taxConditionCode;
	}
	public String getTaxStartDate() {
		return TaxStartDate;
	}
	public void setTaxStartDate(String taxStartDate) {
		TaxStartDate = taxStartDate;
	}
	public String getTaxEndDate() {
		return TaxEndDate;
	}
	public void setTaxEndDate(String taxEndDate) {
		TaxEndDate = taxEndDate;
	}
	public double getAnnualTaxDue() {
		return AnnualTaxDue;
	}
	public void setAnnualTaxDue(double annualTaxDue) {
		AnnualTaxDue = annualTaxDue;
	}
	public double getSumTaxDefault() {
		return SumTaxDefault;
	}
	public void setSumTaxDefault(double sumTaxDefault) {
		SumTaxDefault = sumTaxDefault;
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
	public double getUnitRate() {
		return UnitRate;
	}
	public void setUnitRate(double unitRate) {
		UnitRate = unitRate;
	}
	public double getAnnualTaxAmount() {
		return AnnualTaxAmount;
	}
	public void setAnnualTaxAmount(double annualTaxAmount) {
		AnnualTaxAmount = annualTaxAmount;
	}
	public double getTaxDue() {
		return TaxDue;
	}
	public void setTaxDue(double taxDue) {
		TaxDue = taxDue;
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
	public String getDeductionDueCode() {
		return DeductionDueCode;
	}
	public void setDeductionDueCode(String deductionDueCode) {
		DeductionDueCode = deductionDueCode;
	}
	public String getDeductionDueType() {
		return DeductionDueType;
	}
	public void setDeductionDueType(String deductionDueType) {
		DeductionDueType = deductionDueType;
	}
	public double getDeductionDueProportion() {
		return DeductionDueProportion;
	}
	public void setDeductionDueProportion(double deductionDueProportion) {
		DeductionDueProportion = deductionDueProportion;
	}
	public double getDeduction() {
		return Deduction;
	}
	public void setDeduction(double deduction) {
		Deduction = deduction;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getDocumentNumber() {
		return DocumentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		DocumentNumber = documentNumber;
	}
	public double getOverDue() {
		return OverDue;
	}
	public void setOverDue(double overDue) {
		OverDue = overDue;
	}
}
