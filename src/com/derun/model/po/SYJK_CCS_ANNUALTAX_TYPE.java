package com.derun.model.po;

import java.io.Serializable;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_ANNUALTAX_TYPE (车船税纳税信息表) VO
 * */
public class SYJK_CCS_ANNUALTAX_TYPE implements Serializable{
	private static final long serialVersionUID = 1L;
	private String TAXLOCATIONCODE = null ; 		//	纳税地区代码
	private String TAXSTARTDATE = null ; 			//	税款所属始期
	private String TAXENDDATE = null ; 				//	税款所属止期
	private String TAXUNITTYPECODE = null ; 		//	计税单位代码
	private String UNITRATE = null ; 				//	单位计税金额
	private String ANNUALTAXAMOUNT = null ; 		//	当期年单位税额
	private String TAXDUE = null ; 					//	当期应纳税额
	private String EXCEEDDATE = null ; 				//	逾期时间
	private Double EXCEEDDAYSCOUNT = 0.0 ; 			//	逾期天数
	private String OVERDUE = null ; 				//	滞纳金
	private String TOTALAMOUNT = null ; 			//	合计金额
	private String TAXTERMTYPECODE = null ; 		//	税种类型代码
	private String TAXCONDITIONCODE = null ; 		//	纳税类型代码
	private String TAXREGISTRYNUMBER = null ; 		//	税务登记证号
	private String TAXPAYERNAME = null ; 			//	纳税人名称
	private String TAXPAYERIDENTIFICATIONCODE = null ; //	纳税人识别号
	private String PAYDATE = null ; 				//	代收日期
	private String PAYCOMPANYCODE = null ; 			//	代收公司
	public String getTAXLOCATIONCODE() {
		return TAXLOCATIONCODE;
	}
	public void setTAXLOCATIONCODE(String tAXLOCATIONCODE) {
		TAXLOCATIONCODE = tAXLOCATIONCODE;
	}
	public String getTAXSTARTDATE() {
		return TAXSTARTDATE;
	}
	public void setTAXSTARTDATE(String tAXSTARTDATE) {
		TAXSTARTDATE = tAXSTARTDATE;
	}
	public String getTAXENDDATE() {
		return TAXENDDATE;
	}
	public void setTAXENDDATE(String tAXENDDATE) {
		TAXENDDATE = tAXENDDATE;
	}
	public String getTAXUNITTYPECODE() {
		return TAXUNITTYPECODE;
	}
	public void setTAXUNITTYPECODE(String tAXUNITTYPECODE) {
		TAXUNITTYPECODE = tAXUNITTYPECODE;
	}
	public String getUNITRATE() {
		return UNITRATE;
	}
	public void setUNITRATE(String uNITRATE) {
		UNITRATE = uNITRATE;
	}
	public String getANNUALTAXAMOUNT() {
		return ANNUALTAXAMOUNT;
	}
	public void setANNUALTAXAMOUNT(String aNNUALTAXAMOUNT) {
		ANNUALTAXAMOUNT = aNNUALTAXAMOUNT;
	}
	public String getTAXDUE() {
		return TAXDUE;
	}
	public void setTAXDUE(String tAXDUE) {
		TAXDUE = tAXDUE;
	}
	public String getEXCEEDDATE() {
		return EXCEEDDATE;
	}
	public void setEXCEEDDATE(String eXCEEDDATE) {
		EXCEEDDATE = eXCEEDDATE;
	}
	public Double getEXCEEDDAYSCOUNT() {
		return EXCEEDDAYSCOUNT;
	}
	public void setEXCEEDDAYSCOUNT(Double eXCEEDDAYSCOUNT) {
		EXCEEDDAYSCOUNT = eXCEEDDAYSCOUNT;
	}
	public String getOVERDUE() {
		return OVERDUE;
	}
	public void setOVERDUE(String oVERDUE) {
		OVERDUE = oVERDUE;
	}
	public String getTOTALAMOUNT() {
		return TOTALAMOUNT;
	}
	public void setTOTALAMOUNT(String tOTALAMOUNT) {
		TOTALAMOUNT = tOTALAMOUNT;
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
	public String getPAYDATE() {
		return PAYDATE;
	}
	public void setPAYDATE(String pAYDATE) {
		PAYDATE = pAYDATE;
	}
	public String getPAYCOMPANYCODE() {
		return PAYCOMPANYCODE;
	}
	public void setPAYCOMPANYCODE(String pAYCOMPANYCODE) {
		PAYCOMPANYCODE = pAYCOMPANYCODE;
	}
}
