package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_CCSCXCCJBXX（车船税查询出参基本信息表） VO
 * */
public class SYJK_CCS_CCSCXCCJBXX implements Serializable{
	private static final long serialVersionUID = 1L;
	private String TAXQUERYNO = null ;					// 车船税查询码
	private String CALCTAXFLAG = null ;					// 算税标志
	private String TAXAMOUNT_FLAG = null ;				// 合计金额标志码
	private Double ANNUALTAXDUE = 0.0 ;					// 本年车船税金额
	private Double SUMTAXDEFAULT = 0.0 ;				// 合计欠税金额
	private Double SUMOVERDUE = 0.0 ;					// 合计滞纳金
	private Double SUMTAX = 0.0 ;						// 合计金额
	private String RETURNCODE = null ;					// 返回代码
	private String LOGINNAME = null ;					// 操作员
	private String REVENUECODE = null ;					// 税务机关编码
	private String SJCJRQ = null ;						// 系统采集日期
	private String SJCJFS = null ;						// 系统采集方式
	private String VIN = null ;							// 车辆识别代码
	private String HPHM = null ;						// 号牌号码
	private String HPZL = null ;						// 号牌种类
	private String CLLX = null ;						// 交管车辆类型
	private Date FIRSTREGISTERDATE = null ;				// 车辆初始登记日期
	private String SPECIALCARTYPE = null ;				// 特殊车标志
	private String TSBZ = null ;						// 退税标志，默认为
	private String ENGINENO = null ;					// 发动机号
	private String CARMATCHID = null ;					// 车辆匹配ID
	private Date INSURESTARTDATE = null ;				// 投保始期
	private Date INSUREENDDATE = null ;					// 投保止期
	private String CARSERIALNO = null ;					// 机动车序列号
	
	private String MOTORUSAGETYPECODE = null ; 	// 使用性质
	private String MODEL = null ; 				// 车辆型号
	private String VEHICLETYPE = null ; 		// 交管车辆类型
	private double RATEDPASSENGERCAPACITY = 0.0;// 核定载客数
	private double TONNAGE = 0.0;				// 核定载质量
	private double WHOLEWEIGHT = 0.0;			// 整备质量
	private double DISPLACEMENT = 0.0;			// 排量
	private double POWER = 0.0;					// 功率
	private String FUELTYPE = null ; 			// 源种类
	private String ISINSERT = null ;			// 是否为核定数据
	private String MOTORTYPECODE = null ;		// 车辆种类
	private String MADEFACTORY = null ;			// 制造厂名称
	private String NOLICENSEFLAG = null ;		// 未上牌车辆标志
	
	public String getMOTORTYPECODE() {
		return MOTORTYPECODE;
	}
	public void setMOTORTYPECODE(String mOTORTYPECODE) {
		MOTORTYPECODE = mOTORTYPECODE;
	}
	public String getMADEFACTORY() {
		return MADEFACTORY;
	}
	public void setMADEFACTORY(String mADEFACTORY) {
		MADEFACTORY = mADEFACTORY;
	}
	public String getNOLICENSEFLAG() {
		return NOLICENSEFLAG;
	}
	public void setNOLICENSEFLAG(String nOLICENSEFLAG) {
		NOLICENSEFLAG = nOLICENSEFLAG;
	}
	public String getISINSERT() {
		return ISINSERT;
	}
	public void setISINSERT(String iSINSERT) {
		ISINSERT = iSINSERT;
	}
	
	public String getMOTORUSAGETYPECODE() {
		return MOTORUSAGETYPECODE;
	}
	public void setMOTORUSAGETYPECODE(String mOTORUSAGETYPECODE) {
		MOTORUSAGETYPECODE = mOTORUSAGETYPECODE;
	}
	public String getMODEL() {
		return MODEL;
	}
	public void setMODEL(String mODEL) {
		MODEL = mODEL;
	}
	public String getVEHICLETYPE() {
		return VEHICLETYPE;
	}
	public void setVEHICLETYPE(String vEHICLETYPE) {
		VEHICLETYPE = vEHICLETYPE;
	}
	public double getRATEDPASSENGERCAPACITY() {
		return RATEDPASSENGERCAPACITY;
	}
	public void setRATEDPASSENGERCAPACITY(double rATEDPASSENGERCAPACITY) {
		RATEDPASSENGERCAPACITY = rATEDPASSENGERCAPACITY;
	}
	public double getTONNAGE() {
		return TONNAGE;
	}
	public void setTONNAGE(double tONNAGE) {
		TONNAGE = tONNAGE;
	}
	public double getWHOLEWEIGHT() {
		return WHOLEWEIGHT;
	}
	public void setWHOLEWEIGHT(double wHOLEWEIGHT) {
		WHOLEWEIGHT = wHOLEWEIGHT;
	}
	public double getDISPLACEMENT() {
		return DISPLACEMENT;
	}
	public void setDISPLACEMENT(double dISPLACEMENT) {
		DISPLACEMENT = dISPLACEMENT;
	}
	public double getPOWER() {
		return POWER;
	}
	public void setPOWER(double pOWER) {
		POWER = pOWER;
	}
	public String getFUELTYPE() {
		return FUELTYPE;
	}
	public void setFUELTYPE(String fUELTYPE) {
		FUELTYPE = fUELTYPE;
	}
	public String getTAXQUERYNO() {
		return TAXQUERYNO;
	}
	public void setTAXQUERYNO(String tAXQUERYNO) {
		TAXQUERYNO = tAXQUERYNO;
	}
	public String getCALCTAXFLAG() {
		return CALCTAXFLAG;
	}
	public void setCALCTAXFLAG(String cALCTAXFLAG) {
		CALCTAXFLAG = cALCTAXFLAG;
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
	public String getSJCJRQ() {
		return SJCJRQ;
	}
	public void setSJCJRQ(String sJCJRQ) {
		SJCJRQ = sJCJRQ;
	}
	public String getSJCJFS() {
		return SJCJFS;
	}
	public void setSJCJFS(String sJCJFS) {
		SJCJFS = sJCJFS;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
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
	public String getCLLX() {
		return CLLX;
	}
	public void setCLLX(String cLLX) {
		CLLX = cLLX;
	}
	public Date getFIRSTREGISTERDATE() {
		return FIRSTREGISTERDATE;
	}
	public void setFIRSTREGISTERDATE(Date fIRSTREGISTERDATE) {
		FIRSTREGISTERDATE = fIRSTREGISTERDATE;
	}
	public String getSPECIALCARTYPE() {
		return SPECIALCARTYPE;
	}
	public void setSPECIALCARTYPE(String sPECIALCARTYPE) {
		SPECIALCARTYPE = sPECIALCARTYPE;
	}
	public String getTSBZ() {
		return TSBZ;
	}
	public void setTSBZ(String tSBZ) {
		TSBZ = tSBZ;
	}
	public String getENGINENO() {
		return ENGINENO;
	}
	public void setENGINENO(String eNGINENO) {
		ENGINENO = eNGINENO;
	}
	public String getCARMATCHID() {
		return CARMATCHID;
	}
	public void setCARMATCHID(String cARMATCHID) {
		CARMATCHID = cARMATCHID;
	}
	public Date getINSURESTARTDATE() {
		return INSURESTARTDATE;
	}
	public void setINSURESTARTDATE(Date iNSURESTARTDATE) {
		INSURESTARTDATE = iNSURESTARTDATE;
	}
	public Date getINSUREENDDATE() {
		return INSUREENDDATE;
	}
	public void setINSUREENDDATE(Date iNSUREENDDATE) {
		INSUREENDDATE = iNSUREENDDATE;
	}
	public String getCARSERIALNO() {
		return CARSERIALNO;
	}
	public void setCARSERIALNO(String cARSERIALNO) {
		CARSERIALNO = cARSERIALNO;
	}
}
