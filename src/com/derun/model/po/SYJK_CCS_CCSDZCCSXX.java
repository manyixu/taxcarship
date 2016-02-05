package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_CCSDZCCSXX(对账出参基本信息) VO
 * */
public class SYJK_CCS_CCSDZCCSXX implements Serializable{
	private static final long serialVersionUID = 1L;
	private String TAXRECONCILIATIONNO = null ; 		//	车船税对账码
	private String CHECKINGTYPE	 = null ; 				//	车船税对账类型
	private String TAXDEALCODE = null ; 				//	车船税交易码
	private String TAXAMOUNT_FLAG = null ; 				//	合计金额标志码
	private Double ANNUALTAXDUE = 0.0 ; 				//	本年车船税金额
	private Double SUMTAXDEFAULT = 0.0 ; 				//	合计欠税金额
	private Double SUMOVERDUE = 0.0 ; 					//	合计滞纳金
	private Double SUMTAX = 0.0 ; 						//	合计金额
	private String RETURNCODE = null ;	 				//	返回代码
	private String LOGINNAME = null ; 					//	操作员
	private String REVENUECODE = null ; 				//	税务机关编码
	private Date SJCJRQ = null ; 						//	系统采集日期
	private String SJCJFS = null ; 						//	系统采集方式
	public String getTAXRECONCILIATIONNO() {
		return TAXRECONCILIATIONNO;
	}
	public void setTAXRECONCILIATIONNO(String tAXRECONCILIATIONNO) {
		TAXRECONCILIATIONNO = tAXRECONCILIATIONNO;
	}
	public String getCHECKINGTYPE() {
		return CHECKINGTYPE;
	}
	public void setCHECKINGTYPE(String cHECKINGTYPE) {
		CHECKINGTYPE = cHECKINGTYPE;
	}
	public String getTAXDEALCODE() {
		return TAXDEALCODE;
	}
	public void setTAXDEALCODE(String tAXDEALCODE) {
		TAXDEALCODE = tAXDEALCODE;
	}
	public String getTAXAMOUNT_FLAG() {
		return TAXAMOUNT_FLAG;
	}
	public void setTAXAMOUNT_FLAG(String tAXAMOUNTFLAG) {
		TAXAMOUNT_FLAG = tAXAMOUNTFLAG;
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
	public Double getSUMOVERDUE() {
		return SUMOVERDUE;
	}
	public void setSUMOVERDUE(Double sUMOVERDUE) {
		SUMOVERDUE = sUMOVERDUE;
	}
	public Double getSUMTAX() {
		return SUMTAX;
	}
	public void setSUMTAX(Double sUMTAX) {
		SUMTAX = sUMTAX;
	}
	public String getRETURNCODE() {
		return RETURNCODE;
	}
	public void setRETURNCODE(String rETURNCODE) {
		RETURNCODE = rETURNCODE;
	}
	public String getLOGINNAME() {
		return LOGINNAME;
	}
	public void setLOGINNAME(String lOGINNAME) {
		LOGINNAME = lOGINNAME;
	}
	public String getREVENUECODE() {
		return REVENUECODE;
	}
	public void setREVENUECODE(String rEVENUECODE) {
		REVENUECODE = rEVENUECODE;
	}
	public Date getSJCJRQ() {
		return SJCJRQ;
	}
	public void setSJCJRQ(Date sJCJRQ) {
		SJCJRQ = sJCJRQ;
	}
	public String getSJCJFS() {
		return SJCJFS;
	}
	public void setSJCJFS(String sJCJFS) {
		SJCJFS = sJCJFS;
	}
}
