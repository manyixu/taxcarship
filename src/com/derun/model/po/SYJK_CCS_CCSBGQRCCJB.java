package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：车船税变更确认出参基本信息表VO
 * */
public class SYJK_CCS_CCSBGQRCCJB implements Serializable{
	private static final long serialVersionUID = 1L;
	private String CHANGEQUERYNO = null ; 		//	车船税变更查询码
	private String CHANGECONFIRMNO = null ; 	//	车船税变更确认码
	private String RETURNCODE = null ; 			//	返回代码
	private String LOGINNAME = null ; 			//	操作员
	private String REVENUECODE = null ; 		//	税务机关编码
	private Date SJCJRQ = null ; 				//	系统采集日期
	private String SJCJFS = null ; 				//	系统采集方式
	private String TAXPRINTNO = null ; 			//	车船税打印码
	private String TAXCONFIRMNO = null ; 		//	车船税确认码
	public String getCHANGEQUERYNO() {
		return CHANGEQUERYNO;
	}
	public void setCHANGEQUERYNO(String cHANGEQUERYNO) {
		CHANGEQUERYNO = cHANGEQUERYNO;
	}
	public String getCHANGECONFIRMNO() {
		return CHANGECONFIRMNO;
	}
	public void setCHANGECONFIRMNO(String cHANGECONFIRMNO) {
		CHANGECONFIRMNO = cHANGECONFIRMNO;
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
	public String getTAXPRINTNO() {
		return TAXPRINTNO;
	}
	public void setTAXPRINTNO(String tAXPRINTNO) {
		TAXPRINTNO = tAXPRINTNO;
	}
	public String getTAXCONFIRMNO() {
		return TAXCONFIRMNO;
	}
	public void setTAXCONFIRMNO(String tAXCONFIRMNO) {
		TAXCONFIRMNO = tAXCONFIRMNO;
	}
}
