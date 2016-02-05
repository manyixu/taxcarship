package com.derun.model.po;

import java.util.Date;
public class SYJK_CCS_RKMX{

	private String TAXQUERYNO = "";// 车船税交易码 VARCHAR2(50)
	private String TAXPRINTNO = "";// 打印码
	private String TAXCONFIRMNO = "";// 确认码 VARCHAR2(50)
	private String VIN = "";// Vin VARCHAR2(50)
	private String HPHM = "";// 号牌号码 VARCHAR2(50)
	private String HPZL = "";// 号牌种类 VARCHAR2(50)
	private String CLLX = "";// 车辆类型 VARCHAR2(50)
	private String TAXCONDITIONCODE = "";// 纳税类型 VARCHAR2(1)
	private String TAXREGISTRYNUMBER = "";// 税务登记证号 VARCHAR2(30)
	private String TAXPAYERNAME = "";// 纳税人名称 VARCHAR2(100)
	private String TAXPAYERIDENTIFICATIONCODE = "";// 纳税人识别号 VARCHAR2(30)
	private String TAXLOCATIONCODE = "";// 纳税地区代码 VARCHAR2(6)
	private String TAXSTARTDATE = "";// 税款所属始期 DATE
	private String TAXENDDATE = "";// 税款所属止期 DATE
	private String TAXUNITTYPECODE = "";// 计税单位代码 VARCHAR2(11)
	private double UNITRATE = 0.0;// 单位计税金额 NUMBER(18,2)
	private double ANNUALTAXAMOUNT = 0.0;// 当期年单位税额 NUMBER(18,2)
	
	private String TAXDEPARTMENTCODE = "";// 开具完税凭证的税务机关代码 VARCHAR2(11)
	private String TAXDEPARTMENT = "";// 开具完税凭证的税务机关名称 VARCHAR2(100)
	private String TAXDOCUMENTNUMBER = "";// 完税凭证号码 VARCHAR2(30)
	
	private String DEDUCTIONDEPARTMENTCODE = "";// 开具减免税凭证的税务机关代码 VARCHAR2(100)
	private String DEDUCTIONDEPARTMENT = "";// 开具减免税凭证的税务机关名称 VARCHAR2(100)
	private String DEDUCTIONDUECODE = "";// 减免税原因代码 VARCHAR2(30)
	private String DEDUCTIONDUETYPE = "";// 减免税方案代码 VARCHAR2(1)
	private double DEDUCTIONDUEPROPORTION = 0.0;// 减免比例 NUMBER(18,2)
	private double DEDUCTION = 0.0;// 减免金额 NUMBER(18,2)
	private String DEDUCTIONDOCUMENTNUMBER = "";// 减免税凭证号 VARCHAR2(30)
	
	private double TAXDUE = 0.0;// 当期应纳税额 NUMBER(18,2)
	private String EXCEEDDATE = "";// 逾期时间 DATE
	private int EXCEEDDAYSCOUNT = 0;// 逾期天数 NUMBER(18,2)
	private double OVERDUE = 0.0;// 滞纳金 NUMBER(18,2)
	private double TOTALAMOUNT = 0.0;// 合计金额 NUMBER(18,2)
	private String LOGINNAME = "";// 操作员 VARCHAR2(200)
	private String REVENUECODE = "";// 9000 税务机关编码 VARCHAR2(50)
	private String SJCJRQ = "";// 系统采集日期 DATE
	private String PAYCOMPANYCODE = "";// 代收公司 VARCHAR2(30)
	private String PAYDATE = "";// 所属年度 VARCHAR2(30)
	private String TAXAMOUNT_FLAG = "";// 合计金额标志码 VARCHAR2(30)
	private double ANNUALTAXDUE = 0.0;// 本年车船税金额 NUMBER(18,2)
	private double SUMTAXDEFAULT = 0.0;// 合计欠税金额 NUMBER(18,2)
	private double SUMOVERDUE = 0.0;// 合计滞纳金 NUMBER(18,2)
	private double SUMTAX = 0.0;// 合计金额 NUMBER(18,2)
	private String LOGGEDOUT = "";// 注销状态 0= 未注销 1=注销 VARCHAR2(10)
	private String PLATFORMSTATE = "";// 平台 0=代收 1=申报（完税）4=拒缴 VARCHAR2(10)
	private String CHANGETYPE = "";// 变更类型 0=确认 4=退保，2=批改 VARCHAR2(10)
	private String COUNTTAXTYPE = "";// 算税标志 1=税源 2,3平台
	private String Refusetype = ""; // 拒缴标志 0=拒缴 1=不拒缴
	private String STATUSDATE = ""; // 申报日期
	private String FIRSTREGISTERDATE = "";// DATE, --- 车辆初始登记日期
	private String SPECIALCARTYPE = "";// varchar2(10), --- 特殊车标志
	private String TSBZ = ""; // varchar2(10)=""; ---退税标志 默认=0 ， 1=长期， 2=短期
	private String engineNo = "";// 发动机号
	private Date insureStartDate = null;// 投保开始日期
	private Date insureEndDate = null;// 投保结束日期
	private String CARSERIALNO = null ;	// 问题序列号
	private String CARMATCHID = null ;	// 车辆匹配ID
	
	private String MOTORUSAGETYPECODE = null ; 	// 使用性质
	private String MODEL = null ; 				// 车辆型号
	private String VEHICLETYPE = null ; 		// 交管车辆类型
	private double RATEDPASSENGERCAPACITY = 0.0;// 核定载客数
	private double TONNAGE = 0.0;				// 核定载质量
	private double WHOLEWEIGHT = 0.0;			// 整备质量
	private double DISPLACEMENT = 0.0;			// 排量
	private double POWER = 0.0;					// 功率
	private String FUELTYPE = null ; 			// 能源种类
	
	private String CityCode = null;//税款归属（地市）
	private String CountryCode = null;//税款归属（县区）
	
	public String getCityCode() {
		return CityCode;
	}

	public void setCityCode(String cityCode) {
		CityCode = cityCode;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
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

	public String getCARMATCHID() {
		return CARMATCHID;
	}

	public void setCARMATCHID(String cARMATCHID) {
		CARMATCHID = cARMATCHID;
	}

	public String getCARSERIALNO() {
		return CARSERIALNO;
	}

	public void setCARSERIALNO(String cARSERIALNO) {
		CARSERIALNO = cARSERIALNO;
	}

	public String getRefusetype() {
		return Refusetype;
	}

	public void setRefusetype(String refusetype) {
		Refusetype = refusetype;
	}

	public String getSTATUSDATE() {
		return STATUSDATE;
	}

	public void setSTATUSDATE(String statusdate) {
		STATUSDATE = statusdate;
	}

	public String getTAXPRINTNO() {
		return TAXPRINTNO;
	}

	public void setTAXPRINTNO(String taxprintno) {
		TAXPRINTNO = taxprintno;
	}

	public double getANNUALTAXAMOUNT() {
		return ANNUALTAXAMOUNT;
	}

	public void setANNUALTAXAMOUNT(double annualtaxamount) {
		ANNUALTAXAMOUNT = annualtaxamount;
	}

	public double getANNUALTAXDUE() {
		return ANNUALTAXDUE;
	}

	public void setANNUALTAXDUE(double annualtaxdue) {
		ANNUALTAXDUE = annualtaxdue;
	}

	public String getCHANGETYPE() {
		return CHANGETYPE;
	}

	public void setCHANGETYPE(String changetype) {
		CHANGETYPE = changetype;
	}

	public String getCLLX() {
		return CLLX;
	}

	public void setCLLX(String cllx) {
		CLLX = cllx;
	}

	public String getCOUNTTAXTYPE() {
		return COUNTTAXTYPE;
	}

	public void setCOUNTTAXTYPE(String counttaxtype) {
		COUNTTAXTYPE = counttaxtype;
	}

	public double getDEDUCTION() {
		return DEDUCTION;
	}

	public void setDEDUCTION(double deduction) {
		DEDUCTION = deduction;
	}

	public String getDEDUCTIONDEPARTMENT() {
		return DEDUCTIONDEPARTMENT;
	}

	public void setDEDUCTIONDEPARTMENT(String deductiondepartment) {
		DEDUCTIONDEPARTMENT = deductiondepartment;
	}

	public String getDEDUCTIONDEPARTMENTCODE() {
		return DEDUCTIONDEPARTMENTCODE;
	}

	public void setDEDUCTIONDEPARTMENTCODE(String deductiondepartmentcode) {
		DEDUCTIONDEPARTMENTCODE = deductiondepartmentcode;
	}

	public String getDEDUCTIONDOCUMENTNUMBER() {
		return DEDUCTIONDOCUMENTNUMBER;
	}

	public void setDEDUCTIONDOCUMENTNUMBER(String deductiondocumentnumber) {
		DEDUCTIONDOCUMENTNUMBER = deductiondocumentnumber;
	}

	public String getDEDUCTIONDUECODE() {
		return DEDUCTIONDUECODE;
	}

	public void setDEDUCTIONDUECODE(String deductionduecode) {
		DEDUCTIONDUECODE = deductionduecode;
	}

	public double getDEDUCTIONDUEPROPORTION() {
		return DEDUCTIONDUEPROPORTION;
	}

	public void setDEDUCTIONDUEPROPORTION(double deductiondueproportion) {
		DEDUCTIONDUEPROPORTION = deductiondueproportion;
	}

	public String getDEDUCTIONDUETYPE() {
		return DEDUCTIONDUETYPE;
	}

	public void setDEDUCTIONDUETYPE(String deductionduetype) {
		DEDUCTIONDUETYPE = deductionduetype;
	}

	public String getEXCEEDDATE() {
		return EXCEEDDATE;
	}

	public void setEXCEEDDATE(String exceeddate) {
		EXCEEDDATE = exceeddate;
	}

	public int getEXCEEDDAYSCOUNT() {
		return EXCEEDDAYSCOUNT;
	}

	public void setEXCEEDDAYSCOUNT(int exceeddayscount) {
		EXCEEDDAYSCOUNT = exceeddayscount;
	}

	public String getHPHM() {
		return HPHM;
	}

	public void setHPHM(String hphm) {
		HPHM = hphm;
	}

	public String getHPZL() {
		return HPZL;
	}

	public void setHPZL(String hpzl) {
		HPZL = hpzl;
	}

	public String getLOGGEDOUT() {
		return LOGGEDOUT;
	}

	public void setLOGGEDOUT(String loggedout) {
		LOGGEDOUT = loggedout;
	}

	public String getLOGINNAME() {
		return LOGINNAME;
	}

	public void setLOGINNAME(String loginname) {
		LOGINNAME = loginname;
	}

	public double getOVERDUE() {
		return OVERDUE;
	}

	public void setOVERDUE(double overdue) {
		OVERDUE = overdue;
	}

	public String getPAYCOMPANYCODE() {
		return PAYCOMPANYCODE;
	}

	public void setPAYCOMPANYCODE(String paycompanycode) {
		PAYCOMPANYCODE = paycompanycode;
	}

	public String getPAYDATE() {
		return PAYDATE;
	}

	public void setPAYDATE(String paydate) {
		PAYDATE = paydate;
	}

	public String getPLATFORMSTATE() {
		return PLATFORMSTATE;
	}

	public void setPLATFORMSTATE(String platformstate) {
		PLATFORMSTATE = platformstate;
	}

	public String getREVENUECODE() {
		return REVENUECODE;
	}

	public void setREVENUECODE(String revenuecode) {
		REVENUECODE = revenuecode;
	}

	public String getSJCJRQ() {
		return SJCJRQ;
	}

	public void setSJCJRQ(String sjcjrq) {
		SJCJRQ = sjcjrq;
	}

	public double getSUMOVERDUE() {
		return SUMOVERDUE;
	}

	public void setSUMOVERDUE(double sumoverdue) {
		SUMOVERDUE = sumoverdue;
	}

	public double getSUMTAX() {
		return SUMTAX;
	}

	public void setSUMTAX(double sumtax) {
		SUMTAX = sumtax;
	}

	public double getSUMTAXDEFAULT() {
		return SUMTAXDEFAULT;
	}

	public void setSUMTAXDEFAULT(double sumtaxdefault) {
		SUMTAXDEFAULT = sumtaxdefault;
	}

	public String getTAXAMOUNT_FLAG() {
		return TAXAMOUNT_FLAG;
	}

	public void setTAXAMOUNT_FLAG(String taxamount_flag) {
		TAXAMOUNT_FLAG = taxamount_flag;
	}

	public String getTAXCONDITIONCODE() {
		return TAXCONDITIONCODE;
	}

	public void setTAXCONDITIONCODE(String taxconditioncode) {
		TAXCONDITIONCODE = taxconditioncode;
	}

	public String getTAXCONFIRMNO() {
		return TAXCONFIRMNO;
	}

	public void setTAXCONFIRMNO(String taxconfirmno) {
		TAXCONFIRMNO = taxconfirmno;
	}

	public String getTAXDEPARTMENT() {
		return TAXDEPARTMENT;
	}

	public void setTAXDEPARTMENT(String taxdepartment) {
		TAXDEPARTMENT = taxdepartment;
	}

	public String getTAXDEPARTMENTCODE() {
		return TAXDEPARTMENTCODE;
	}

	public void setTAXDEPARTMENTCODE(String taxdepartmentcode) {
		TAXDEPARTMENTCODE = taxdepartmentcode;
	}

	public String getTAXDOCUMENTNUMBER() {
		return TAXDOCUMENTNUMBER;
	}

	public void setTAXDOCUMENTNUMBER(String taxdocumentnumber) {
		TAXDOCUMENTNUMBER = taxdocumentnumber;
	}

	public double getTAXDUE() {
		return TAXDUE;
	}

	public void setTAXDUE(double taxdue) {
		TAXDUE = taxdue;
	}

	public String getTAXENDDATE() {
		return TAXENDDATE;
	}

	public void setTAXENDDATE(String taxenddate) {
		TAXENDDATE = taxenddate;
	}

	public String getTAXLOCATIONCODE() {
		return TAXLOCATIONCODE;
	}

	public void setTAXLOCATIONCODE(String taxlocationcode) {
		TAXLOCATIONCODE = taxlocationcode;
	}

	public String getTAXPAYERIDENTIFICATIONCODE() {
		return TAXPAYERIDENTIFICATIONCODE;
	}

	public void setTAXPAYERIDENTIFICATIONCODE(String taxpayeridentificationcode) {
		TAXPAYERIDENTIFICATIONCODE = taxpayeridentificationcode;
	}

	public String getTAXPAYERNAME() {
		return TAXPAYERNAME;
	}

	public void setTAXPAYERNAME(String taxpayername) {
		TAXPAYERNAME = taxpayername;
	}

	public String getTAXQUERYNO() {
		return TAXQUERYNO;
	}

	public void setTAXQUERYNO(String taxqueryno) {
		TAXQUERYNO = taxqueryno;
	}

	public String getTAXREGISTRYNUMBER() {
		return TAXREGISTRYNUMBER;
	}

	public void setTAXREGISTRYNUMBER(String taxregistrynumber) {
		TAXREGISTRYNUMBER = taxregistrynumber;
	}

	public String getTAXSTARTDATE() {
		return TAXSTARTDATE;
	}

	public void setTAXSTARTDATE(String taxstartdate) {
		TAXSTARTDATE = taxstartdate;
	}

	public String getTAXUNITTYPECODE() {
		return TAXUNITTYPECODE;
	}

	public void setTAXUNITTYPECODE(String taxunittypecode) {
		TAXUNITTYPECODE = taxunittypecode;
	}

	public double getTOTALAMOUNT() {
		return TOTALAMOUNT;
	}

	public void setTOTALAMOUNT(double totalamount) {
		TOTALAMOUNT = totalamount;
	}

	public double getUNITRATE() {
		return UNITRATE;
	}

	public void setUNITRATE(double unitrate) {
		UNITRATE = unitrate;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vin) {
		VIN = vin;
	}

	public String getFIRSTREGISTERDATE() {
		return FIRSTREGISTERDATE;
	}

	public void setFIRSTREGISTERDATE(String firstregisterdate) {
		FIRSTREGISTERDATE = firstregisterdate;
	}

	public String getSPECIALCARTYPE() {
		return SPECIALCARTYPE;
	}

	public void setSPECIALCARTYPE(String specialcartype) {
		SPECIALCARTYPE = specialcartype;
	}

	public String getTSBZ() {
		return TSBZ;
	}

	public void setTSBZ(String tsbz) {
		TSBZ = tsbz;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public Date getInsureStartDate() {
		return insureStartDate;
	}

	public void setInsureStartDate(Date insureStartDate) {
		this.insureStartDate = insureStartDate;
	}

	public Date getInsureEndDate() {
		return insureEndDate;
	}

	public void setInsureEndDate(Date insureEndDate) {
		this.insureEndDate = insureEndDate;
	}
}
