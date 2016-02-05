package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_CCSDZRCJBXX(对账入参基本信息)VO
 * */
public class SYJK_CCS_CCSDZRCJBXX implements Serializable{
	private static final long serialVersionUID = 1L;
	private String TAXRECONCILIATIONNO = null ; 	//	车船税对账码
	private String CHECKINGTYPE = null ; 			//	车船税对账类型
	private String TAXDEALCODE = null ; 			//	车船税交易码
	private String LOGINNAME = null ; 				//	操作员
	private String REVENUECODE = null ; 			//	税务机关编码
	private Date SJCJRQ = null ; 					//	系统采集日期
	private String SJCJFS = null ; 					//	系统采集方式
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
