package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_CCSSBRQSCRC（申报日期上传入参表）VO
 * */
public class SYJK_CCS_CCSSBRQSCRC implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date DECLAREDATE = null ;				//	申报时间
	private String LISTRESPECTIVEANNUAL = null ;	//	车船税确认码
	private String LOGINNAME = null ;				//	操作员
	private String REVENUECODE = null ;				//	税务机关编码
	private Date SJCJRQ = null ;					//	系统采集日期
	private String SJCJFS = null ;					//	系统采集方式
	private String TAXCHANGEQUERYNO = null ;		//	车船税变更确认码
	private String AREACODE = null ;				//	国标区域代码
	private String COMPANYCODE = null ;				//	公司代码
	public Date getDECLAREDATE() {
		return DECLAREDATE;
	}
	public void setDECLAREDATE(Date dECLAREDATE) {
		DECLAREDATE = dECLAREDATE;
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
	public String getTAXCHANGEQUERYNO() {
		return TAXCHANGEQUERYNO;
	}
	public void setTAXCHANGEQUERYNO(String tAXCHANGEQUERYNO) {
		TAXCHANGEQUERYNO = tAXCHANGEQUERYNO;
	}
	public String getAREACODE() {
		return AREACODE;
	}
	public void setAREACODE(String aREACODE) {
		AREACODE = aREACODE;
	}
	public String getCOMPANYCODE() {
		return COMPANYCODE;
	}
	public void setCOMPANYCODE(String cOMPANYCODE) {
		COMPANYCODE = cOMPANYCODE;
	}
	
}
