package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_CCSSBRQSCCC(申报日期上传出参表) VO
 * */
public class SYJK_CCS_CCSSBRQSCCC implements Serializable{
	private static final long serialVersionUID = 1L;
	private String RETURNCODE = null ;				//	返回代码
	private String LISTRESPECTIVEANNUAL = null ;	//	车船税确认码
	private String LOGINNAME = null ;				//	操作员
	private String REVENUECODE = null ;				//	税务机关编码
	private Date SJCJRQ = null ;					//	系统采集日期
	private String SJCJFS = null ;					//	系统采集方式
	public String getRETURNCODE() {
		return RETURNCODE;
	}
	public void setRETURNCODE(String rETURNCODE) {
		RETURNCODE = rETURNCODE;
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
