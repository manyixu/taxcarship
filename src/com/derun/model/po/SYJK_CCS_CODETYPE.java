package com.derun.model.po;

import java.util.Date;

/**
 * @author 赵文斌
 * @Email wbzhao7@gmail.com
 * @date 2014-3-4
 * 
 *       说明
 * @version
 */
public class SYJK_CCS_CODETYPE {

	private String CODETYPE; // 系统代码类型
	private String CODETYPENAME; // 系统代码类型名称
	private String REMARK; // 代码类型说明
	private Date MAKEDATE; // 配置日期

	public String getCODETYPE() {
		return CODETYPE;
	}

	public void setCODETYPE(String cODETYPE) {
		CODETYPE = cODETYPE;
	}

	public String getCODETYPENAME() {
		return CODETYPENAME;
	}

	public void setCODETYPENAME(String cODETYPENAME) {
		CODETYPENAME = cODETYPENAME;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public Date getMAKEDATE() {
		return MAKEDATE;
	}

	public void setMAKEDATE(Date mAKEDATE) {
		MAKEDATE = mAKEDATE;
	}

}
