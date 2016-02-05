package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_DSCCSJMDJXX（地税车船税减免登记信息）VO
 * */
public class SYJK_CCS_DSCCSJMDJXX implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ID = null ; 				// 	内部主键
	private String NSRSBH = null ; 			// 	纳税人识别号
	private String LOGINNAME = null ; 		// 	操作员
	private String REVENUECODE = null ; 	// 	税务机关编码
	private Date SJCJRQ = null ; 			//	系统采集日期
	private String SJCJFS = null ; 			// 	系统采集方式
	private String CLHPHM = null ; 			// 	车辆号牌号码
	private String CLHPZL = null ; 			// 	车辆号牌种类
	private String CLLX = null ; 			// 	车辆类型
	private String CBMC = null ; 			// 	船舶名称
	private String CBLX = null ; 			// 	船舶类型
	private String SYRMC = null ; 			// 	所有人名称
	private String JMSPZH = null ;			// 	减免税凭证号
	private Double JZBL = 0.0 ; 			//	减征比例
	private Date JMSQRQ = null ; 			//	减免税起日期
	private Date JMSZRQ = null ; 			//	减免税止日期
	private Double NJMSE = 0.0 ; 			//	年减免税额
	private String KJSWJGDM = null ; 		// 	开具税务机关代码
	private String ZGSWJGMC = null ; 		// 	主管税务机关名称
	private String CCSBDM = null ; 			// 	车船识别代码
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNSRSBH() {
		return NSRSBH;
	}
	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
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
	public String getCLHPHM() {
		return CLHPHM;
	}
	public void setCLHPHM(String cLHPHM) {
		CLHPHM = cLHPHM;
	}
	public String getCLHPZL() {
		return CLHPZL;
	}
	public void setCLHPZL(String cLHPZL) {
		CLHPZL = cLHPZL;
	}
	public String getCLLX() {
		return CLLX;
	}
	public void setCLLX(String cLLX) {
		CLLX = cLLX;
	}
	public String getCBMC() {
		return CBMC;
	}
	public void setCBMC(String cBMC) {
		CBMC = cBMC;
	}
	public String getCBLX() {
		return CBLX;
	}
	public void setCBLX(String cBLX) {
		CBLX = cBLX;
	}
	public String getSYRMC() {
		return SYRMC;
	}
	public void setSYRMC(String sYRMC) {
		SYRMC = sYRMC;
	}
	public String getJMSPZH() {
		return JMSPZH;
	}
	public void setJMSPZH(String jMSPZH) {
		JMSPZH = jMSPZH;
	}
	public Double getJZBL() {
		return JZBL;
	}
	public void setJZBL(Double jZBL) {
		JZBL = jZBL;
	}
	public Date getJMSQRQ() {
		return JMSQRQ;
	}
	public void setJMSQRQ(Date jMSQRQ) {
		JMSQRQ = jMSQRQ;
	}
	public Date getJMSZRQ() {
		return JMSZRQ;
	}
	public void setJMSZRQ(Date jMSZRQ) {
		JMSZRQ = jMSZRQ;
	}
	public Double getNJMSE() {
		return NJMSE;
	}
	public void setNJMSE(Double nJMSE) {
		NJMSE = nJMSE;
	}
	public String getKJSWJGDM() {
		return KJSWJGDM;
	}
	public void setKJSWJGDM(String kJSWJGDM) {
		KJSWJGDM = kJSWJGDM;
	}
	public String getZGSWJGMC() {
		return ZGSWJGMC;
	}
	public void setZGSWJGMC(String zGSWJGMC) {
		ZGSWJGMC = zGSWJGMC;
	}
	public String getCCSBDM() {
		return CCSBDM;
	}
	public void setCCSBDM(String cCSBDM) {
		CCSBDM = cCSBDM;
	}
}
