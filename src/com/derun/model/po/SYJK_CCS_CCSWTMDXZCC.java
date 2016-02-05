package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_CCSWTMDXZCC (车船税问题名单下载出参基本信息表) VO
 * */
public class SYJK_CCS_CCSWTMDXZCC implements Serializable{
	private static final long serialVersionUID = 1L;
	private String LISTDOWNLOADNO = null ; 				//	问题名单下载码
	private String LICENSEPLATENO = null ; 				//	号牌号码
	private String LICENSEPLATETYPE = null ;	 		//	号牌种类
	private String MOTORTYPECODE = null ; 				//	车辆种类
	private String ENGINENO = null ; 					//	发动机号
	private String VIN = null ; 						//	车辆识别代码
	private String MADEFACTORY = null ; 				//	制造厂名称
	private String MOTORUSAGETYPECODE = null ; 			//	使用性质
	private String NOLICENSEFLAG = null ; 				//	未上牌车辆标志
	private String MODEL = null ; 						//	车辆型号
	private Date FIRSTREGISTERDATE = null ; 			//	车辆初始登记日期
	private String VEHICLETYPE = null ; 				//	交管车辆类型
	private Double RATEDPASSENGERCAPACITY = 0.0 ; 		//	核定载客数
	private Double TONNAGE = 0.0 ; 						//	核定载质量
	private Double WHOLEWEIGHT = 0.0 ; 					//	整备质量
	private Double DISPLACEMENT = 0.0 ; 				//	排量
	private Double POWER = 0.0 ; 						//	功率
	private String FUELTYPE = null ; 					//	能源种类
	private String VEHICLEOWNERNAME = null ; 			//	车主名称
	private String CREDENTIALNO = null ; 				//	证件号码
	private String CREDENTIALCODE = null ; 				//	自然人证件类型
	private String ADDRESS = null ; 					//	车主地址
	private String PHONENO = null ; 					//	联系电话
	private String TAXTERMTYPECODE = null ; 			//	税种类型代码
	private String TAXCONDITIONCODE = null ; 			//	纳税类型代码
	private String TAXREGISTRYNUMBER = null ; 			//	税务登记证号
	private String TAXPAYERNAME = null ; 				//	纳税人名称
	private String TAXPAYERIDENTIFICATIONCODE = null ; 	//	纳税人识别号
	private String TAXLOCATIONCODE = null ; 			//	纳税地区代码
	private Date TAXSTARTDATE = null ; 					//	税款所属始期
	private Date TAXENDDATE = null ; 					//	税款所属止期
	private String TAXUNITTYPECODE = null ; 			//	计税单位代码
	private Double UNITRATE = 0.0 ; 					//	单位计税金额
	private Double ANNUALTAXAMOUNT = 0.0 ; 				//	当期年单位税额
	private String TAXDOCUMENTNUMBER = null ; 			//	完税凭证号码
	private String DEDUCTIONDUECODE = null ; 			//	减免税原因代码
	private String DEDUCTIONDUETYPE = null ; 			//	减免税方案代码
	private Double DEDUCTIONDUEPROPORTION = 0.0 ; 		//	减免比例
	private Double DEDUCTION = 0.0 ; 					//	减免金额
	private String DEDUCTIONDOCUMENTNUMBER = null ; 	//	减免税凭证号
	private String TAXDEPARTMENTCODE = null ; 			//	开具减免税凭证的税务机关代码
	private String TAXDEPARTMENT = null ; 				//	开具减免税凭证的税务机关名称
	private Double TAXDUE = 0.0 ; 						//	当期应纳税额
	private Date EXCEEDDATE = null ; 					//	逾期时间
	private Double EXCEEDDAYSCOUNT = 0.0 ; 				//	逾期天数
	private Double OVERDUE = 0.0 ; 						//	滞纳金
	private Double TOTALAMOUNT = 0.0 ; 					//	合计金额
	private String RETURNCODE = null ; 					//	返回代码
	private String LOGINNAME = null ; 					//	操作员
	private String REVENUECODE = null ; 				//	税务机关编码
	private Date SJCJRQ = null ; 						//	系统采集日期
	private String SJCJFS = null ; 						//	系统采集方式
	private String SPECIALCARTYPE = null ; 				//	特殊车标志
	private String PAYCOMPANYCODE = null ; 				//	代收公司
	private String REASON = null ; 						//	问题原因
	private Date PAYDATE = null ; 						//	代收日期
	public String getLISTDOWNLOADNO() {
		return LISTDOWNLOADNO;
	}
	public void setLISTDOWNLOADNO(String lISTDOWNLOADNO) {
		LISTDOWNLOADNO = lISTDOWNLOADNO;
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
	public Double getRATEDPASSENGERCAPACITY() {
		return RATEDPASSENGERCAPACITY;
	}
	public void setRATEDPASSENGERCAPACITY(Double rATEDPASSENGERCAPACITY) {
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
	public Double getPOWER() {
		return POWER;
	}
	public void setPOWER(Double pOWER) {
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
	public String getTAXTERMTYPECODE() {
		return TAXTERMTYPECODE;
	}
	public void setTAXTERMTYPECODE(String tAXTERMTYPECODE) {
		TAXTERMTYPECODE = tAXTERMTYPECODE;
	}
	public String getTAXCONDITIONCODE() {
		return TAXCONDITIONCODE;
	}
	public void setTAXCONDITIONCODE(String tAXCONDITIONCODE) {
		TAXCONDITIONCODE = tAXCONDITIONCODE;
	}
	public String getTAXREGISTRYNUMBER() {
		return TAXREGISTRYNUMBER;
	}
	public void setTAXREGISTRYNUMBER(String tAXREGISTRYNUMBER) {
		TAXREGISTRYNUMBER = tAXREGISTRYNUMBER;
	}
	public String getTAXPAYERNAME() {
		return TAXPAYERNAME;
	}
	public void setTAXPAYERNAME(String tAXPAYERNAME) {
		TAXPAYERNAME = tAXPAYERNAME;
	}
	public String getTAXPAYERIDENTIFICATIONCODE() {
		return TAXPAYERIDENTIFICATIONCODE;
	}
	public void setTAXPAYERIDENTIFICATIONCODE(String tAXPAYERIDENTIFICATIONCODE) {
		TAXPAYERIDENTIFICATIONCODE = tAXPAYERIDENTIFICATIONCODE;
	}
	public String getTAXLOCATIONCODE() {
		return TAXLOCATIONCODE;
	}
	public void setTAXLOCATIONCODE(String tAXLOCATIONCODE) {
		TAXLOCATIONCODE = tAXLOCATIONCODE;
	}
	public Date getTAXSTARTDATE() {
		return TAXSTARTDATE;
	}
	public void setTAXSTARTDATE(Date tAXSTARTDATE) {
		TAXSTARTDATE = tAXSTARTDATE;
	}
	public Date getTAXENDDATE() {
		return TAXENDDATE;
	}
	public void setTAXENDDATE(Date tAXENDDATE) {
		TAXENDDATE = tAXENDDATE;
	}
	public String getTAXUNITTYPECODE() {
		return TAXUNITTYPECODE;
	}
	public void setTAXUNITTYPECODE(String tAXUNITTYPECODE) {
		TAXUNITTYPECODE = tAXUNITTYPECODE;
	}
	public Double getUNITRATE() {
		return UNITRATE;
	}
	public void setUNITRATE(Double uNITRATE) {
		UNITRATE = uNITRATE;
	}
	public Double getANNUALTAXAMOUNT() {
		return ANNUALTAXAMOUNT;
	}
	public void setANNUALTAXAMOUNT(Double aNNUALTAXAMOUNT) {
		ANNUALTAXAMOUNT = aNNUALTAXAMOUNT;
	}
	public String getTAXDOCUMENTNUMBER() {
		return TAXDOCUMENTNUMBER;
	}
	public void setTAXDOCUMENTNUMBER(String tAXDOCUMENTNUMBER) {
		TAXDOCUMENTNUMBER = tAXDOCUMENTNUMBER;
	}
	public String getDEDUCTIONDUECODE() {
		return DEDUCTIONDUECODE;
	}
	public void setDEDUCTIONDUECODE(String dEDUCTIONDUECODE) {
		DEDUCTIONDUECODE = dEDUCTIONDUECODE;
	}
	public String getDEDUCTIONDUETYPE() {
		return DEDUCTIONDUETYPE;
	}
	public void setDEDUCTIONDUETYPE(String dEDUCTIONDUETYPE) {
		DEDUCTIONDUETYPE = dEDUCTIONDUETYPE;
	}
	public Double getDEDUCTIONDUEPROPORTION() {
		return DEDUCTIONDUEPROPORTION;
	}
	public void setDEDUCTIONDUEPROPORTION(Double dEDUCTIONDUEPROPORTION) {
		DEDUCTIONDUEPROPORTION = dEDUCTIONDUEPROPORTION;
	}
	public Double getDEDUCTION() {
		return DEDUCTION;
	}
	public void setDEDUCTION(Double dEDUCTION) {
		DEDUCTION = dEDUCTION;
	}
	public String getDEDUCTIONDOCUMENTNUMBER() {
		return DEDUCTIONDOCUMENTNUMBER;
	}
	public void setDEDUCTIONDOCUMENTNUMBER(String dEDUCTIONDOCUMENTNUMBER) {
		DEDUCTIONDOCUMENTNUMBER = dEDUCTIONDOCUMENTNUMBER;
	}
	public String getTAXDEPARTMENTCODE() {
		return TAXDEPARTMENTCODE;
	}
	public void setTAXDEPARTMENTCODE(String tAXDEPARTMENTCODE) {
		TAXDEPARTMENTCODE = tAXDEPARTMENTCODE;
	}
	public String getTAXDEPARTMENT() {
		return TAXDEPARTMENT;
	}
	public void setTAXDEPARTMENT(String tAXDEPARTMENT) {
		TAXDEPARTMENT = tAXDEPARTMENT;
	}
	public Double getTAXDUE() {
		return TAXDUE;
	}
	public void setTAXDUE(Double tAXDUE) {
		TAXDUE = tAXDUE;
	}
	public Date getEXCEEDDATE() {
		return EXCEEDDATE;
	}
	public void setEXCEEDDATE(Date eXCEEDDATE) {
		EXCEEDDATE = eXCEEDDATE;
	}
	public Double getEXCEEDDAYSCOUNT() {
		return EXCEEDDAYSCOUNT;
	}
	public void setEXCEEDDAYSCOUNT(Double eXCEEDDAYSCOUNT) {
		EXCEEDDAYSCOUNT = eXCEEDDAYSCOUNT;
	}
	public Double getOVERDUE() {
		return OVERDUE;
	}
	public void setOVERDUE(Double oVERDUE) {
		OVERDUE = oVERDUE;
	}
	public Double getTOTALAMOUNT() {
		return TOTALAMOUNT;
	}
	public void setTOTALAMOUNT(Double tOTALAMOUNT) {
		TOTALAMOUNT = tOTALAMOUNT;
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
	public String getSPECIALCARTYPE() {
		return SPECIALCARTYPE;
	}
	public void setSPECIALCARTYPE(String sPECIALCARTYPE) {
		SPECIALCARTYPE = sPECIALCARTYPE;
	}
	public String getPAYCOMPANYCODE() {
		return PAYCOMPANYCODE;
	}
	public void setPAYCOMPANYCODE(String pAYCOMPANYCODE) {
		PAYCOMPANYCODE = pAYCOMPANYCODE;
	}
	public String getREASON() {
		return REASON;
	}
	public void setREASON(String rEASON) {
		REASON = rEASON;
	}
	public Date getPAYDATE() {
		return PAYDATE;
	}
	public void setPAYDATE(Date pAYDATE) {
		PAYDATE = pAYDATE;
	}
}
