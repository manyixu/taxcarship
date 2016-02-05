package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;
/**
 * @author MILI
 * @描述：核定库表PO
 * @Date : 2015-3-31 15:08:23
 * */
public class SYJK_CCS_CARINFO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String CARSERIALNO = null ;						// 机动车序列号	Y
	private String VIN = null ;								// 车辆车架号	Y
	private String ENGINENO = null ;						// 车辆发动机号	Y
	private String LICENSEPLATENO = null ;					// 车辆号牌号码	N
	private String LICENSEPLATETYPE = null ;				// 车辆号牌种类	N
	private String MOTORTYPECODE = null ;					// 车辆种类代码	Y
	private String MADEFACTORY = null ;						// 制造厂名称	N
//	private String MOTORUSAGETYPECODE = null ;				// 车辆使用性质代码
//	private String NOLICENSEFLAG = null ;					// 未上牌车辆标志代码
	private String MODEL = null ;							// 车辆型号	Y
	private Date FIRSTREGISTERDATE = null ;					// 车辆初登日期	Y
	private String VEHICLETYPE = null ;						// 车辆类型代码	N
	private int RATEDPASSENGERCAPACITY = 0 ;				// 核定载客数	N
	private Double TONNAGE = null ;							// 核定载质量	N
	private Double WHOLEWEIGHT = null ;						// 整备质量	N
	private Double DISPLACEMENT = null ;					// 车辆排量	N
//	private Double POWER = null ;							// 车辆功率	
//	private String FUELTYPE = null ;						// 能源种类
//	private String SPECIALCARTYPE = null ;					// 特殊车标志
	private String VEHICLEOWNERNAME = null ;				// 车主姓名	N
	private String CREDENTIALNO = null ;					// 证件类型代码	N
	private String VALIDATWFLAG = null ;					// 有效状态	Y
	private String APPROVEDFLAG = null ;					// 核定状态	Y
	private Date SJCJRQ = null ;							// 系统采集日期	Y
	public String getCARSERIALNO() {
		return CARSERIALNO;
	}
	public void setCARSERIALNO(String cARSERIALNO) {
		CARSERIALNO = cARSERIALNO;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getENGINENO() {
		return ENGINENO;
	}
	public void setENGINENO(String eNGINENO) {
		ENGINENO = eNGINENO;
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
	public String getMADEFACTORY() {
		return MADEFACTORY;
	}
	public void setMADEFACTORY(String mADEFACTORY) {
		MADEFACTORY = mADEFACTORY;
	}
	public String getMODEL() {
		return MODEL;
	}
	public void setMODEL(String mODEL) {
		MODEL = mODEL;
	}
	public Date getFIRSTREGISTERDATE() {
		return FIRSTREGISTERDATE;
	}
	public void setFIRSTREGISTERDATE(Date fIRSTREGISTERDATE) {
		FIRSTREGISTERDATE = fIRSTREGISTERDATE;
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
	public Double getTONNAGE() {
		return TONNAGE;
	}
	public void setTONNAGE(Double tONNAGE) {
		TONNAGE = tONNAGE;
	}
	public Double getWHOLEWEIGHT() {
		return WHOLEWEIGHT;
	}
	public void setWHOLEWEIGHT(Double wHOLEWEIGHT) {
		WHOLEWEIGHT = wHOLEWEIGHT;
	}
	public Double getDISPLACEMENT() {
		return DISPLACEMENT;
	}
	public void setDISPLACEMENT(Double dISPLACEMENT) {
		DISPLACEMENT = dISPLACEMENT;
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
	public String getVALIDATWFLAG() {
		return VALIDATWFLAG;
	}
	public void setVALIDATWFLAG(String vALIDATWFLAG) {
		VALIDATWFLAG = vALIDATWFLAG;
	}
	public String getAPPROVEDFLAG() {
		return APPROVEDFLAG;
	}
	public void setAPPROVEDFLAG(String aPPROVEDFLAG) {
		APPROVEDFLAG = aPPROVEDFLAG;
	}
	public Date getSJCJRQ() {
		return SJCJRQ;
	}
	public void setSJCJRQ(Date sJCJRQ) {
		SJCJRQ = sJCJRQ;
	}
}
