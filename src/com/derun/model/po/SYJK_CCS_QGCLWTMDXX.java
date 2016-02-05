package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_QGCLWTMDXX （全国车辆问题名单信息表）VO
 * */
public class SYJK_CCS_QGCLWTMDXX implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ID = null ;				//  内部主键
	private String CZMC = null ;			// 	车主名称
	private String CZZJHM = null ;			// 	车主证件号码
	private String CZZJLX = null ;			// 	车主证件类型
	private String CZDZ = null ;			// 	车主地址
	private String CZLXDH = null ;			// 	车主联系电话
	private String YCYYDM = null ;			// 	异常原因代码
	private String CLSBDM = null ;			// 	车辆识别代码
	private String HPHM = null ;			//	号牌号码
	private String HPZL = null ;			//	号牌种类
	private String CLZL = null ;			//	车辆种类
	private String FDJH = null ;			//	发动机号
	private String ZCCMC = null ;			//	制造厂名称
	private String SYXZ = null ;			//	使用性质
	private String WSPCLBZ = null ;			//	未上牌车辆标志
	private String CLXH = null ;			//	车辆型号
	private Date CLCSDJRQ = null ;			//	车辆初始登记日期
	private String JGCLLX = null ;			//	交管车辆类型
	private Double HDZKS = 0.0 ;			//	核定载客数
	private Double HDZZL = 0.0 ;			//	核定载质量
	private Double ZBZL = 0.0 ;				//	整备质量
	private Double PL = 0.0 ;				//	排量
	private Double GL = 0.0 ;				//	功率
	private String RLZL = null ;			//	燃料种类
	private Date SKSSSQ = null ;			//	税款所属始期
	private Date SKSSZQ = null ;			//	税款所属止期
	private String KJWSPZSWJGDM = null ;	//	开具完税凭证税务机关代码
	private String KJWSPZSWJGMC = null ;	//	开具完税凭证税务机关名称
	private String WSPZHM = null ;			//	完税凭证号码
	private String SWJGJTGJFL = null ;		//	税务机关交通工具分类
	private String JSDW = null ;			//	计税单位
	private Double DWJSJE = 0.0 ;			//	单位计税金额
	private Double DQNDWSE = 0.0 ;			//	当期年单位税额
	private String JMSYY = null ;			//	减免税原因
	private String JMSFA = null ;			//	减免税方案
	private Double JMBL = null ;			//	减免比例
	private Double JMJE = null ;			//	减免金额
	private String JMSPZH = null ;			//	减免税凭证号
	private String SWJGDM = null ;			//	税务机关代码
	private String SWJGMC = null ;			//	税务机关名称
	private Double DQYNSE = 0.0 ;			//	当期应纳税额
	private Date YQSJ = null ;				//	逾期时间
	private Double YQTS = 0.0 ;				//	逾期天数
	private Double ZNJ = 0.0 ;				//	滞纳金
	private Double HEJE = 0.0 ;				//	合计金额
	private String LOGINNAME = null ;		//	操作员
	private String REVENUECODE = null ;		//	税务机关编码
	private Date SJCJRQ = null ;			//	系统采集日期
	private String SJCJFS = null ;			//	系统采集方式
	private String ND = null ;				//	年度
	private String OVERDUEPAYMENT = null ;	
//	private String HANDLEFLAG		
	private String VALIDATEFLAG	= null ;	// 是否有效
//	private String SENDFLG		
	private String CARSERIALNO = null ;		//	问题序列号
//	private String SYSTEMDATE	
	
	public String getID() {
		return ID;
	}
	public String getVALIDATEFLAG() {
		return VALIDATEFLAG;
	}
	public void setVALIDATEFLAG(String vALIDATEFLAG) {
		VALIDATEFLAG = vALIDATEFLAG;
	}
	public String getOVERDUEPAYMENT() {
		return OVERDUEPAYMENT;
	}
	public void setOVERDUEPAYMENT(String oVERDUEPAYMENT) {
		OVERDUEPAYMENT = oVERDUEPAYMENT;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCZMC() {
		return CZMC;
	}
	public void setCZMC(String cZMC) {
		CZMC = cZMC;
	}
	public String getCZZJHM() {
		return CZZJHM;
	}
	public void setCZZJHM(String cZZJHM) {
		CZZJHM = cZZJHM;
	}
	public String getCZZJLX() {
		return CZZJLX;
	}
	public void setCZZJLX(String cZZJLX) {
		CZZJLX = cZZJLX;
	}
	public String getCZDZ() {
		return CZDZ;
	}
	public void setCZDZ(String cZDZ) {
		CZDZ = cZDZ;
	}
	public String getCZLXDH() {
		return CZLXDH;
	}
	public void setCZLXDH(String cZLXDH) {
		CZLXDH = cZLXDH;
	}
	public String getYCYYDM() {
		return YCYYDM;
	}
	public void setYCYYDM(String yCYYDM) {
		YCYYDM = yCYYDM;
	}
	public String getCLSBDM() {
		return CLSBDM;
	}
	public void setCLSBDM(String cLSBDM) {
		CLSBDM = cLSBDM;
	}
	public String getHPHM() {
		return HPHM;
	}
	public void setHPHM(String hPHM) {
		HPHM = hPHM;
	}
	public String getHPZL() {
		return HPZL;
	}
	public void setHPZL(String hPZL) {
		HPZL = hPZL;
	}
	public String getCLZL() {
		return CLZL;
	}
	public void setCLZL(String cLZL) {
		CLZL = cLZL;
	}
	public String getFDJH() {
		return FDJH;
	}
	public void setFDJH(String fDJH) {
		FDJH = fDJH;
	}
	public String getZCCMC() {
		return ZCCMC;
	}
	public void setZCCMC(String zCCMC) {
		ZCCMC = zCCMC;
	}
	public String getSYXZ() {
		return SYXZ;
	}
	public void setSYXZ(String sYXZ) {
		SYXZ = sYXZ;
	}
	public String getWSPCLBZ() {
		return WSPCLBZ;
	}
	public void setWSPCLBZ(String wSPCLBZ) {
		WSPCLBZ = wSPCLBZ;
	}
	public String getCLXH() {
		return CLXH;
	}
	public void setCLXH(String cLXH) {
		CLXH = cLXH;
	}
	public Date getCLCSDJRQ() {
		return CLCSDJRQ;
	}
	public void setCLCSDJRQ(Date cLCSDJRQ) {
		CLCSDJRQ = cLCSDJRQ;
	}
	public String getJGCLLX() {
		return JGCLLX;
	}
	public void setJGCLLX(String jGCLLX) {
		JGCLLX = jGCLLX;
	}
	public Double getHDZKS() {
		return HDZKS;
	}
	public void setHDZKS(Double hDZKS) {
		HDZKS = hDZKS;
	}
	public Double getHDZZL() {
		return HDZZL;
	}
	public void setHDZZL(Double hDZZL) {
		HDZZL = hDZZL;
	}
	public Double getZBZL() {
		return ZBZL;
	}
	public void setZBZL(Double zBZL) {
		ZBZL = zBZL;
	}
	public Double getPL() {
		return PL;
	}
	public void setPL(Double pL) {
		PL = pL;
	}
	public Double getGL() {
		return GL;
	}
	public void setGL(Double gL) {
		GL = gL;
	}
	public String getRLZL() {
		return RLZL;
	}
	public void setRLZL(String rLZL) {
		RLZL = rLZL;
	}
	public Date getSKSSSQ() {
		return SKSSSQ;
	}
	public void setSKSSSQ(Date sKSSSQ) {
		SKSSSQ = sKSSSQ;
	}
	public Date getSKSSZQ() {
		return SKSSZQ;
	}
	public void setSKSSZQ(Date sKSSZQ) {
		SKSSZQ = sKSSZQ;
	}
	public String getKJWSPZSWJGDM() {
		return KJWSPZSWJGDM;
	}
	public void setKJWSPZSWJGDM(String kJWSPZSWJGDM) {
		KJWSPZSWJGDM = kJWSPZSWJGDM;
	}
	public String getKJWSPZSWJGMC() {
		return KJWSPZSWJGMC;
	}
	public void setKJWSPZSWJGMC(String kJWSPZSWJGMC) {
		KJWSPZSWJGMC = kJWSPZSWJGMC;
	}
	public String getWSPZHM() {
		return WSPZHM;
	}
	public void setWSPZHM(String wSPZHM) {
		WSPZHM = wSPZHM;
	}
	public String getSWJGJTGJFL() {
		return SWJGJTGJFL;
	}
	public void setSWJGJTGJFL(String sWJGJTGJFL) {
		SWJGJTGJFL = sWJGJTGJFL;
	}
	public String getJSDW() {
		return JSDW;
	}
	public void setJSDW(String jSDW) {
		JSDW = jSDW;
	}
	public Double getDWJSJE() {
		return DWJSJE;
	}
	public void setDWJSJE(Double dWJSJE) {
		DWJSJE = dWJSJE;
	}
	public Double getDQNDWSE() {
		return DQNDWSE;
	}
	public void setDQNDWSE(Double dQNDWSE) {
		DQNDWSE = dQNDWSE;
	}
	public String getJMSYY() {
		return JMSYY;
	}
	public void setJMSYY(String jMSYY) {
		JMSYY = jMSYY;
	}
	public String getJMSFA() {
		return JMSFA;
	}
	public void setJMSFA(String jMSFA) {
		JMSFA = jMSFA;
	}
	public Double getJMBL() {
		return JMBL;
	}
	public void setJMBL(Double jMBL) {
		JMBL = jMBL;
	}
	public Double getJMJE() {
		return JMJE;
	}
	public void setJMJE(Double jMJE) {
		JMJE = jMJE;
	}
	public String getJMSPZH() {
		return JMSPZH;
	}
	public void setJMSPZH(String jMSPZH) {
		JMSPZH = jMSPZH;
	}
	public String getSWJGDM() {
		return SWJGDM;
	}
	public void setSWJGDM(String sWJGDM) {
		SWJGDM = sWJGDM;
	}
	public String getSWJGMC() {
		return SWJGMC;
	}
	public void setSWJGMC(String sWJGMC) {
		SWJGMC = sWJGMC;
	}
	public Double getDQYNSE() {
		return DQYNSE;
	}
	public void setDQYNSE(Double dQYNSE) {
		DQYNSE = dQYNSE;
	}
	public Date getYQSJ() {
		return YQSJ;
	}
	public void setYQSJ(Date yQSJ) {
		YQSJ = yQSJ;
	}
	public Double getYQTS() {
		return YQTS;
	}
	public void setYQTS(Double yQTS) {
		YQTS = yQTS;
	}
	public Double getZNJ() {
		return ZNJ;
	}
	public void setZNJ(Double zNJ) {
		ZNJ = zNJ;
	}
	public Double getHEJE() {
		return HEJE;
	}
	public void setHEJE(Double hEJE) {
		HEJE = hEJE;
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
	public String getND() {
		return ND;
	}
	public void setND(String nD) {
		ND = nD;
	}
	public String getCARSERIALNO() {
		return CARSERIALNO;
	}
	public void setCARSERIALNO(String cARSERIALNO) {
		CARSERIALNO = cARSERIALNO;
	}
}
