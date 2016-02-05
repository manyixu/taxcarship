package com.derun.model.po;

import java.io.Serializable;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_VEHICLE_TYPE (车船税车辆信息表)VO 
 * */
public class SYJK_CCS_VEHICLE_TYPE implements Serializable{
	private static final long serialVersionUID = 1L;
	private String LICENSEPLATENO = null ; 			//	号牌号码
	private String LICENSEPLATETYPE = null ; 		//	号牌种类
	private String MOTORTYPECODE = null ; 			//	车辆种类
	private String ENGINENO = null ; 				//	发动机号
	private String VIN = null ;	 					//	车架号
	private String MADEFACTORY = null ; 			//	制造厂名称
	private String MOTORUSAGETYPECODE = null ; 		//	使用性质
	private String NOLICENSEFLAG = null ; 			//	未上牌车辆标志
	private String MODEL = null ; 					//	车辆型号
	private String FIRSTREGISTERDATE = null ; 		//	车辆初始登记日期
	private String VEHICLETYPE = null ; 			//	交管车辆类型
	private Double RATEDPASSENGERCAPACITY = 0.0 ; 	//	核定载客数
	private String TONNAGE = null ; 				//	核定载质量
	private String WHOLEWEIGHT = null ; 			//	整备质量
	private String DISPLACEMENT = null ; 			//	排量
	private String POWER = null ; 					//	功率
	private String FUELTYPE = null ; 				//	燃料种类
	private String SPECIALCARTYPE = null ; 			//	特殊车标志
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
	public String getFIRSTREGISTERDATE() {
		return FIRSTREGISTERDATE;
	}
	public void setFIRSTREGISTERDATE(String fIRSTREGISTERDATE) {
		FIRSTREGISTERDATE = fIRSTREGISTERDATE;
	}
	public String getVEHICLETYPE() {
		return VEHICLETYPE;
	}
	public void setVEHICLETYPE(String vEHICLETYPE) {
		VEHICLETYPE = vEHICLETYPE;
	}
	public Double getRATEDPASSENGERCAPACITY() {
		return RATEDPASSENGERCAPACITY;
	}
	public void setRATEDPASSENGERCAPACITY(Double rATEDPASSENGERCAPACITY) {
		RATEDPASSENGERCAPACITY = rATEDPASSENGERCAPACITY;
	}
	public String getTONNAGE() {
		return TONNAGE;
	}
	public void setTONNAGE(String tONNAGE) {
		TONNAGE = tONNAGE;
	}
	public String getWHOLEWEIGHT() {
		return WHOLEWEIGHT;
	}
	public void setWHOLEWEIGHT(String wHOLEWEIGHT) {
		WHOLEWEIGHT = wHOLEWEIGHT;
	}
	public String getDISPLACEMENT() {
		return DISPLACEMENT;
	}
	public void setDISPLACEMENT(String dISPLACEMENT) {
		DISPLACEMENT = dISPLACEMENT;
	}
	public String getPOWER() {
		return POWER;
	}
	public void setPOWER(String pOWER) {
		POWER = pOWER;
	}
	public String getFUELTYPE() {
		return FUELTYPE;
	}
	public void setFUELTYPE(String fUELTYPE) {
		FUELTYPE = fUELTYPE;
	}
	public String getSPECIALCARTYPE() {
		return SPECIALCARTYPE;
	}
	public void setSPECIALCARTYPE(String sPECIALCARTYPE) {
		SPECIALCARTYPE = sPECIALCARTYPE;
	}
}
