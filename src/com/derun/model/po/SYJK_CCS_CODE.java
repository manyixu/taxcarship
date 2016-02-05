package com.derun.model.po;

/**
 * @author 赵文斌
 * @Email wbzhao7@gmail.com
 * @date 2014-3-4
 * 
 *       说明
 * @version
 */
public class SYJK_CCS_CODE {

	private String CODE; // 系统代码
	private String CODETYPE; // 系统代码类型
	private String CODENAME; // 系统代码名称
	private String CODEALIA; // 系统代码别名
	private String VALIDATEFLAG; // 是否有效标识
	private String ISHOTPARA; // 是否热配置
	private String REMARK ;  // 说明
	private String CODEVALUE ; // 对应值
	public String getCODEVALUE() {
		return CODEVALUE;
	}

	public void setCODEVALUE(String cODEVALUE) {
		CODEVALUE = cODEVALUE;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
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

}
