package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_WSDJXX(完税登记信息表)VO
 * */
public class SYJK_CCS_WSDJXX implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ID = null ; 				// 	内部主键
	private String CCSBDM = null ; 			// 	车船识别代码
	private String ZGSWJGMC = null ; 		// 	主管税务机关名称
	private String NSRSBH = null ; 			// 	纳税人识别号
	private String CLHPHM = null ; 			// 	车辆号牌号码
	private String CLHPZL = null ; 			// 	车辆号牌种类
	private String CLLX = null ; 			// 	车辆类型
	private String SYRMC = null ; 			// 	所有人名称
	private String KJSWJGDM = null ; 		// 	开具税务机关代码
	private String LOGINNAME = null ; 		// 	操作员
	private String REVENUECODE = null ; 	// 	税务机关编码
	private Date SJCJRQ = null ; 			// 	系统采集日期
	private String SJCJFS = null ; 			// 	系统采集方式
	private String DZDWMC = null ; 			// 	代征单位名称
	private String DZDWDM = null ; 			// 	代征单位代码
	private String WSPZH = null ; 			// 	完税凭证号
	private Date SKSSKSRQ = null ; 			// 	税款所属起始日期
	private Date SKSSJSRQ = null ; 			// 	税款所属结束日期
	private Double JNJE = 0.0 ; 			// 	缴纳金额
	private Date WSRQ = null ; 				// 	完税日期
	private String SKLX = null ; 			// 	税款类型
	private String ZSPM = null ; 			// 	征收品目
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCCSBDM() {
		return CCSBDM;
	}
	public void setCCSBDM(String cCSBDM) {
		CCSBDM = cCSBDM;
	}
	public String getZGSWJGMC() {
		return ZGSWJGMC;
	}
	public void setZGSWJGMC(String zGSWJGMC) {
		ZGSWJGMC = zGSWJGMC;
	}
	public String getNSRSBH() {
		return NSRSBH;
	}
	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
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
	public String getSYRMC() {
		return SYRMC;
	}
	public void setSYRMC(String sYRMC) {
		SYRMC = sYRMC;
	}
	public String getKJSWJGDM() {
		return KJSWJGDM;
	}
	public void setKJSWJGDM(String kJSWJGDM) {
		KJSWJGDM = kJSWJGDM;
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
	public String getDZDWMC() {
		return DZDWMC;
	}
	public void setDZDWMC(String dZDWMC) {
		DZDWMC = dZDWMC;
	}
	public String getDZDWDM() {
		return DZDWDM;
	}
	public void setDZDWDM(String dZDWDM) {
		DZDWDM = dZDWDM;
	}
	public String getWSPZH() {
		return WSPZH;
	}
	public void setWSPZH(String wSPZH) {
		WSPZH = wSPZH;
	}
	public Date getSKSSKSRQ() {
		return SKSSKSRQ;
	}
	public void setSKSSKSRQ(Date sKSSKSRQ) {
		SKSSKSRQ = sKSSKSRQ;
	}
	public Date getSKSSJSRQ() {
		return SKSSJSRQ;
	}
	public void setSKSSJSRQ(Date sKSSJSRQ) {
		SKSSJSRQ = sKSSJSRQ;
	}
	public Double getJNJE() {
		return JNJE;
	}
	public void setJNJE(Double jNJE) {
		JNJE = jNJE;
	}
	public Date getWSRQ() {
		return WSRQ;
	}
	public void setWSRQ(Date wSRQ) {
		WSRQ = wSRQ;
	}
	public String getSKLX() {
		return SKLX;
	}
	public void setSKLX(String sKLX) {
		SKLX = sKLX;
	}
	public String getZSPM() {
		return ZSPM;
	}
	public void setZSPM(String zSPM) {
		ZSPM = zSPM;
	}
}
