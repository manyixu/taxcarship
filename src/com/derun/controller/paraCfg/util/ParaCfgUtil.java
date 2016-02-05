package com.derun.controller.paraCfg.util;

import javax.xml.crypto.Data;

public class ParaCfgUtil {
	private String CODE; // 系统代码
	private String CODETYPE; // 系统代码类型
	private String CODENAME; // 系统代码名称
	private String CODEALIA; // 系统代码别名
	private String VALIDATEFLAG; // 是否有效标识
	private String ISHOTPARA; // 是否热配置
	private String REMARK ;  // 说明
	private String CODEVALUE ; // 对应值
	private Data MAKDATE; 
	
	
	public Data getMAKDATE() {
		return MAKDATE;
	}
	public void setMAKDATE(Data mAKDATE) {
		MAKDATE = mAKDATE;
	}

	private int total;

	 public ParaCfgUtil(){}
	 public ParaCfgUtil(String code,String codeType,String codeName ,String codealia,String validateflag ,String ishotpara,String remark,String codevalue){
		 this.CODE= code;
		 this.CODETYPE = codeType;
		 this.CODENAME = codeName;
		 this.CODEALIA= codealia;
		 this.VALIDATEFLAG=validateflag;
		 this.ISHOTPARA = ishotpara;
		 this.REMARK = remark;
		 this.CODEVALUE = codevalue;
	 }
	
	public String getCODE() {
		return CODE;
	}

	public void setCODE(String cODE) {
		CODE = cODE;
	}

	public String getCODETYPE() {
		return CODETYPE;
	}

	public void setCODETYPE(String cODETYPE) {
		CODETYPE = cODETYPE;
	}

	public String getCODENAME() {
		return CODENAME;
	}

	public void setCODENAME(String cODENAME) {
		CODENAME = cODENAME;
	}

	public String getCODEALIA() {
		return CODEALIA;
	}

	public void setCODEALIA(String cODEALIA) {
		CODEALIA = cODEALIA;
	}

	public String getVALIDATEFLAG() {
		return VALIDATEFLAG;
	}

	public void setVALIDATEFLAG(String vALIDATEFLAG) {
		VALIDATEFLAG = vALIDATEFLAG;
	}

	public String getISHOTPARA() {
		return ISHOTPARA;
	}

	public void setISHOTPARA(String iSHOTPARA) {
		ISHOTPARA = iSHOTPARA;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getCODEVALUE() {
		return CODEVALUE;
	}

	public void setCODEVALUE(String cODEVALUE) {
		CODEVALUE = cODEVALUE;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
