package com.derun.model.po;

import java.io.Serializable;

/**
 * @author MILI
 * @time 2014-5-6 15:30:26
 * @描述： 车船税变更查询服务--请求信息对象入参,本地数据库交互实体    待删除。。。。。。。。
 */
public class BaseChangeQueryReqInfoUtilEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String TAXQUERYNO = null;					// 生成的查询码,本地数据库唯一识别表示
	private String TAXCONFIRMNO = null;					// 车船税确认码
	private String CHANGETYPE = null;					// 变更类型（保单或车辆信息变更，保单退保）
	private String LICENSEPLATENO = null;				// 号牌号码
	private String LICENSEPLATETYPE = null;				// 号牌种类
	private String MOTORTYPECODE = null;				// 车辆种类,Y
	private String ENGINENO = null;						// 发动机号,Y
	private String VIN = null;							// 车架号,Y
	private String MADEFACTORY = null;					// 制造厂名称
	private String MOTORUSAGETYPECODE = null;			// 使用性质,Y
	private String NOLICENSEFLAG = null;				// 未上牌车辆标志,Y
	private String MODEL = null;						// 车辆型号
	private String FIRSTREGISTERDATEL = null;			// 车辆初始登记日期,Y
	private String VEHICLETYPE = null;					// 交管车辆类型,Y
	private int RATEDPASSENGERCAPACITY = 0;				// 核定载客数,Y
	private double TONNAGE = 0.0;						// 核定载质量,Y
	private double WHOLEWEIGHT = 0.0;					// 整备质量,Y
	private double DISPLACEMENT = 0.0;					// 排量 ,Y
	private double POWER = 0.0;							// 功率 ,Y
	private String FUELTYPE = null;						// 燃料种类
	private String VEHICLEOWNERNAME = null;				// 车主名称
	private String CREDENTIALNO = null;					// 证件号码
	private String CREDENTIALCODE = null;				// 自然人证件类型
	private String ADDRESS = null;						// 车主地址
	private String PHONENO = null;						// 联系电话
	private String TAXAMOUNT_FLAG = null;				// 合计金额标志码,Y
	private double ANNUALTAXDUE = 0.0;					// 本年车船税金额
	private double SUMTAXDEFAULT = 0.0;					// 合计欠税金额
	private double SUMOVERDUE = 0.0;					// 合计滞纳金
	private double SUMTAX = 0.0;						// 合计金额,Y
	private String SPECIALCARTYPE	;					// 特殊车标志代码
	private String PLATFORMSTATE = null ;  				// 申报状态
	public String getTAXQUERYNO() {
		return TAXQUERYNO;
	}
	public void setTAXQUERYNO(String tAXQUERYNO) {
		TAXQUERYNO = tAXQUERYNO;
	}
	public String getTAXCONFIRMNO() {
		return TAXCONFIRMNO;
	}
	public void setTAXCONFIRMNO(String tAXCONFIRMNO) {
		TAXCONFIRMNO = tAXCONFIRMNO;
	}
	public String getCHANGETYPE() {
		return CHANGETYPE;
	}
	public void setCHANGETYPE(String cHANGETYPE) {
		CHANGETYPE = cHANGETYPE;
	}
	public String getLICENSEPLATENO() {
		return LICENSEPLATENO;
	}
	public void setLICENSEPLATENO(String lICENSEPLATENO) {
		LICENSEPLATENO = lICENSEPLATENO;
	}
	public String getLICENSEPLATETYPE() {
		return LICENSEPLATETYPE;
	}
	public void setLICENSEPLATETYPE(String lICENSEPLATETYPE) {
		LICENSEPLATETYPE = lICENSEPLATETYPE;
	}
	public String getMOTORTYPECODE() {
		return MOTORTYPECODE;
	}
	public void setMOTORTYPECODE(String mOTORTYPECODE) {
		MOTORTYPECODE = mOTORTYPECODE;
	}
	public String getENGINENO() {
		return ENGINENO;
	}
	public void setENGINENO(String eNGINENO) {
		ENGINENO = eNGINENO;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getMADEFACTORY() {
		return MADEFACTORY;
	}
	public void setMADEFACTORY(String mADEFACTORY) {
		MADEFACTORY = mADEFACTORY;
	}
	public String getMOTORUSAGETYPECODE() {
		return MOTORUSAGETYPECODE;
	}
	public void setMOTORUSAGETYPECODE(String mOTORUSAGETYPECODE) {
		MOTORUSAGETYPECODE = mOTORUSAGETYPECODE;
	}
	public String getNOLICENSEFLAG() {
		return NOLICENSEFLAG;
	}
	public void setNOLICENSEFLAG(String nOLICENSEFLAG) {
		NOLICENSEFLAG = nOLICENSEFLAG;
	}
	public String getMODEL() {
		return MODEL;
	}
	public void setMODEL(String mODEL) {
		MODEL = mODEL;
	}
	public String getFIRSTREGISTERDATEL() {
		return FIRSTREGISTERDATEL;
	}
	public void setFIRSTREGISTERDATEL(String fIRSTREGISTERDATEL) {
		FIRSTREGISTERDATEL = fIRSTREGISTERDATEL;
	}
	public String getVEHICLETYPE() {
		return VEHICLETYPE;
	}
	public void setVEHICLETYPE(String vEHICLETYPE) {
		VEHICLETYPE = vEHICLETYPE;
	}
	public int getRATEDPASSENGERCAPACITY() {
		return RATEDPASSENGERCAPACITY;
	}
	public void setRATEDPASSENGERCAPACITY(int rATEDPASSENGERCAPACITY) {
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
	public String getVEHICLEOWNERNAME() {
		return VEHICLEOWNERNAME;
	}
	public void setVEHICLEOWNERNAME(String vEHICLEOWNERNAME) {
		VEHICLEOWNERNAME = vEHICLEOWNERNAME;
	}
	public String getCREDENTIALNO() {
		return CREDENTIALNO;
	}
	public void setCREDENTIALNO(String cREDENTIALNO) {
		CREDENTIALNO = cREDENTIALNO;
	}
	public String getCREDENTIALCODE() {
		return CREDENTIALCODE;
	}
	public void setCREDENTIALCODE(String cREDENTIALCODE) {
		CREDENTIALCODE = cREDENTIALCODE;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getPHONENO() {
		return PHONENO;
	}
	public void setPHONENO(String pHONENO) {
		PHONENO = pHONENO;
	}
	public String getTAXAMOUNT_FLAG() {
		return TAXAMOUNT_FLAG;
	}
	public void setTAXAMOUNT_FLAG(String tAXAMOUNTFLAG) {
		TAXAMOUNT_FLAG = tAXAMOUNTFLAG;
	}
	public double getANNUALTAXDUE() {
		return ANNUALTAXDUE;
	}
	public void setANNUALTAXDUE(double aNNUALTAXDUE) {
		ANNUALTAXDUE = aNNUALTAXDUE;
	}
	public double getSUMTAXDEFAULT() {
		return SUMTAXDEFAULT;
	}
	public void setSUMTAXDEFAULT(double sUMTAXDEFAULT) {
		SUMTAXDEFAULT = sUMTAXDEFAULT;
	}
	public double getSUMOVERDUE() {
		return SUMOVERDUE;
	}
	public void setSUMOVERDUE(double sUMOVERDUE) {
		SUMOVERDUE = sUMOVERDUE;
	}
	public double getSUMTAX() {
		return SUMTAX;
	}
	public void setSUMTAX(double sUMTAX) {
		SUMTAX = sUMTAX;
	}
	public String getSPECIALCARTYPE() {
		return SPECIALCARTYPE;
	}
	public void setSPECIALCARTYPE(String sPECIALCARTYPE) {
		SPECIALCARTYPE = sPECIALCARTYPE;
	}
	public String getPLATFORMSTATE() {
		return PLATFORMSTATE;
	}
	public void setPLATFORMSTATE(String pLATFORMSTATE) {
		PLATFORMSTATE = pLATFORMSTATE;
	}
}

