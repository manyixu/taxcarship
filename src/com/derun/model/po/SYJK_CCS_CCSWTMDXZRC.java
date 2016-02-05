package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述 ： SYJK_CCS_CCSWTMDXZRC(车船税问题名单下载入参基本信息表)VO
 * */
public class SYJK_CCS_CCSWTMDXZRC implements Serializable{
	private static final long serialVersionUID = 1L;
	private String LISTDOWNLOADNO = null ; 			//	问题名单下载码
	private String LISTRESPECTIVEANNUAL = null ; 	//	问题名单所属年度
	private String LOGINNAME = null ; 				//	操作员
	private String REVENUECODE = null ; 			//	税务机关编码
	private Date SJCJRQ = null ; 					//	系统采集日期
	private String SJCJFS = null ; 					//	系统采集方式
	public String getLISTDOWNLOADNO() {
		return LISTDOWNLOADNO;
	}
	public void setLISTDOWNLOADNO(String lISTDOWNLOADNO) {
		LISTDOWNLOADNO = lISTDOWNLOADNO;
	}
	public String getLISTRESPECTIVEANNUAL() {
		return LISTRESPECTIVEANNUAL;
	}
	public void setLISTRESPECTIVEANNUAL(String lISTRESPECTIVEANNUAL) {
		LISTRESPECTIVEANNUAL = lISTRESPECTIVEANNUAL;
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
