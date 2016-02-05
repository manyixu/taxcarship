package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_RKMX_QS(车船税入库明细欠税信息表)VO
 * */
public class SYJK_CCS_RKMX_QS implements Serializable{
	private static final long serialVersionUID = 1L;
	private String TAXQUERYNO = null ;					//	车船税交易码（确认码，批改确认码）
	private String TAXCONFIRMNO = null ;				//	确认码
	private String TAXPRINTNO = null ;					//	打印码
	private String VIN = null ;							//	车辆识别代码
	private String HPHM = null ;						//	号牌号码
	private String HPZL = null ;						//	号牌种类
	private String CLLX = null ;						//	车辆类型
	private String TAXCONDITIONCODE = null ;			//	纳税类型
	private String TAXREGISTRYNUMBER = null ;			//	税务登记证号
	private String TAXPAYERNAME = null ;				//	纳税人名称
	private String TAXPAYERIDENTIFICATIONCODE = null ;	//	纳税人识别号
	private String TAXLOCATIONCODE = null ;				//	纳税地区代码
	private Date TAXSTARTDATE = null ;					//	税款所属始期
	private Date TAXENDDATE = null ;					//	税款所属止期
	private String TAXUNITTYPECODE = null ;				//	计税单位代码
	private Double UNITRATE = 0.0 ;						//	单位计税金额
	private Double ANNUALTAXAMOUNT = 0.0 ;				//	当期年单位税额
	private String TAXDEPARTMENTCODE = null ;			//	开具完税凭证的税务机关代码
	private String TAXDEPARTMENT = null ;				//	开具完税凭证的税务机关名称
	private String TAXDOCUMENTNUMBER = null ;			//	完税凭证号码
	
	private String DEDUCTIONDEPARTMENTCODE = null ;		//	开具减免税凭证的税务机关代码
	private String DEDUCTIONDEPARTMENT = null ;			//	开具减免税凭证的税务机关名称
	private String DEDUCTIONDUECODE = null ;			//	减免税原因代码
	private String DEDUCTIONDUETYPE = null ;			//	减免税方案代码
	private Double DEDUCTIONDUEPROPORTION = 0.0 ;		//	减免比例
	private Double DEDUCTION = 0.0 ;					//	减免金额
	private String DEDUCTIONDOCUMENTNUMBER = null ;		//	减免税凭证号
	
	private Double TAXDUE = 0.0 ;						//	当期应纳税额
	private Date EXCEEDDATE = null ;					//	逾期时间
	private int EXCEEDDAYSCOUNT = 0 ;					//	逾期天数
	private Double OVERDUE = 0.0 ;						//	滞纳金
	private Double TOTALAMOUNT = 0.0 ;					//	合计金额
	private String LOGINNAME = null ;					//	操作员
	private String REVENUECODE = null ;					//	税务机关编码
	private Date SJCJRQ = null ;						//	系统采集日期
	private String PAYCOMPANYCODE = null ;				//	代收公司
	private String PAYDATE = null ;						//	所属年度
	private String TAXAMOUNT_FLAG = null ;				//	合计金额标志码
	private Double ANNUALTAXDUE = 0.0 ;					//	本年车船税金额
	private Double SUMTAXDEFAULT = 0.0 ;				//	合计欠税金额
	private Double SUMOVERDUE = 0.0 ;					//	合计滞纳金
	private Double SUMTAX = 0.0 ;						//	合计金额
	private String LOGGEDOUT = null ;					//	注销状态   0= 未注销  1=注销
	private String PLATFORMSTATE = null ;				//	平台状态  0=代收 1=申报（完税）4=拒缴
	private String CHANGETYPE = null ;					//	变更类型 0=确认 4=退保，2=批改 
	private String COUNTTAXTYPE = null ;				//	算税标志  1=税源  2，3=平台（补传）
	private String REFUSETYPE = null ;					//	拒缴标志    0=拒缴  1=不拒缴
	private Date STATUSDATE = null ;					//	申报日期
	private Date FIRSTREGISTERDATE = null ;				//	车辆登记日期
	private String SPECIALCARTYPE = null ;				//	特殊车标志 
	private String TSBZ = null ;						//	退税标志 默认=0 ， 1=长期， 2=短期 
	private String ENGINENO = null ;					//	发动机号
	private Date INSURESTARTDATE = null ;				//	投保始期
	private Date INSUREENDDATE = null ;					//	投保止期
	private String CARSERIALNO = null ;					//	车辆匹配ID
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
	public String getTAXPRINTNO() {
		return TAXPRINTNO;
	}
	public void setTAXPRINTNO(String tAXPRINTNO) {
		TAXPRINTNO = tAXPRINTNO;
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
	
	public String getTAXDOCUMENTNUMBER() {
		return TAXDOCUMENTNUMBER;
	}
	public void setTAXDOCUMENTNUMBER(String tAXDOCUMENTNUMBER) {
		TAXDOCUMENTNUMBER = tAXDOCUMENTNUMBER;
	}
	public String getDEDUCTIONDEPARTMENTCODE() {
		return DEDUCTIONDEPARTMENTCODE;
	}
	public void setDEDUCTIONDEPARTMENTCODE(String dEDUCTIONDEPARTMENTCODE) {
		DEDUCTIONDEPARTMENTCODE = dEDUCTIONDEPARTMENTCODE;
	}
	public String getDEDUCTIONDEPARTMENT() {
		return DEDUCTIONDEPARTMENT;
	}
	public void setDEDUCTIONDEPARTMENT(String dEDUCTIONDEPARTMENT) {
		DEDUCTIONDEPARTMENT = dEDUCTIONDEPARTMENT;
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
	
	public int getEXCEEDDAYSCOUNT() {
		return EXCEEDDAYSCOUNT;
	}
	public void setEXCEEDDAYSCOUNT(int eXCEEDDAYSCOUNT) {
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
	public String getPAYCOMPANYCODE() {
		return PAYCOMPANYCODE;
	}
	public void setPAYCOMPANYCODE(String pAYCOMPANYCODE) {
		PAYCOMPANYCODE = pAYCOMPANYCODE;
	}
	public String getPAYDATE() {
		return PAYDATE;
	}
	public void setPAYDATE(String pAYDATE) {
		PAYDATE = pAYDATE;
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
	public String getLOGGEDOUT() {
		return LOGGEDOUT;
	}
	public void setLOGGEDOUT(String lOGGEDOUT) {
		LOGGEDOUT = lOGGEDOUT;
	}
	public String getPLATFORMSTATE() {
		return PLATFORMSTATE;
	}
	public void setPLATFORMSTATE(String pLATFORMSTATE) {
		PLATFORMSTATE = pLATFORMSTATE;
	}
	public String getCHANGETYPE() {
		return CHANGETYPE;
	}
	public void setCHANGETYPE(String cHANGETYPE) {
		CHANGETYPE = cHANGETYPE;
	}
	public String getCOUNTTAXTYPE() {
		return COUNTTAXTYPE;
	}
	public void setCOUNTTAXTYPE(String cOUNTTAXTYPE) {
		COUNTTAXTYPE = cOUNTTAXTYPE;
	}
	public void setREFUSETYPE(String rEFUSETYPE) {
		REFUSETYPE = rEFUSETYPE;
	}
	public String getREFUSETYPE() {
		return REFUSETYPE;
	}
	public Date getSTATUSDATE() {
		return STATUSDATE;
	}
	public void setSTATUSDATE(Date sTATUSDATE) {
		STATUSDATE = sTATUSDATE;
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
