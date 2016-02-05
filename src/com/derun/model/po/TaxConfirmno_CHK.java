package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;
/**
 * @author MILI
 * @time 2014-5-14 15:56:14
 * @描述：批改查询时  确认码有效性校验 封装类
 * */
public class TaxConfirmno_CHK implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date SJCJRQ = null ;				// 系统采集日期
	private String TAXQUERYNO = null ;			// 交易码
	private Double ANNUALTAXDUE = 0.0 ;			// 本年车船税金额
	private Double SUMTAXDEFAULT = 0.0 ;		// 合计欠税金额
	private Double SUMTAX = 0.0 ;				// 合计金额
	private Double SUMOVERDUE = 0.0 ;			// 合计滞纳金
	private String  SPECIALCARTYPE = null;		// 特殊车标志
	private Date FIRSTREGISTERDATE = null ;		// 车辆初始登记日期
	private String TAXCONDITIONCODE = null ;	// 用于存储纳税类型
	private String TAXCONFIRMNO = null ;
	
	public String getTAXCONFIRMNO() {
		return TAXCONFIRMNO;
	}
	public void setTAXCONFIRMNO(String tAXCONFIRMNO) {
		TAXCONFIRMNO = tAXCONFIRMNO;
	}
	public Date getSJCJRQ() {
		return SJCJRQ;
	}
	public void setSJCJRQ(Date sJCJRQ) {
		SJCJRQ = sJCJRQ;
	}
	public String getTAXQUERYNO() {
		return TAXQUERYNO;
	}
	public void setTAXQUERYNO(String tAXQUERYNO) {
		TAXQUERYNO = tAXQUERYNO;
	}
	public Double getANNUALTAXDUE() {
		return ANNUALTAXDUE;
	}
	public void setANNUALTAXDUE(Double aNNUALTAXDUE) {
		ANNUALTAXDUE = aNNUALTAXDUE;
	}
	public Double getSUMTAXDEFAULT() {
		return SUMTAXDEFAULT;
	}
	public void setSUMTAXDEFAULT(Double sUMTAXDEFAULT) {
		SUMTAXDEFAULT = sUMTAXDEFAULT;
	}
	public Double getSUMTAX() {
		return SUMTAX;
	}
	public void setSUMTAX(Double sUMTAX) {
		SUMTAX = sUMTAX;
	}
	public Double getSUMOVERDUE() {
		return SUMOVERDUE;
	}
	public void setSUMOVERDUE(Double sUMOVERDUE) {
		SUMOVERDUE = sUMOVERDUE;
	}
	public String getSPECIALCARTYPE() {
		return SPECIALCARTYPE;
	}
	public void setSPECIALCARTYPE(String sPECIALCARTYPE) {
		SPECIALCARTYPE = sPECIALCARTYPE;
	}
	public Date getFIRSTREGISTERDATE() {
		return FIRSTREGISTERDATE;
	}
	public void setFIRSTREGISTERDATE(Date fIRSTREGISTERDATE) {
		FIRSTREGISTERDATE = fIRSTREGISTERDATE;
	}
	public String getTAXCONDITIONCODE() {
		return TAXCONDITIONCODE;
	}
	public void setTAXCONDITIONCODE(String tAXCONDITIONCODE) {
		TAXCONDITIONCODE = tAXCONDITIONCODE;
	}
}
