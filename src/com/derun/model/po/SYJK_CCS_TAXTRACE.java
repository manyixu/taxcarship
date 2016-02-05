package com.derun.model.po;

import java.io.Serializable;

import oracle.sql.DATE;

/**
 * @author MILI
 * @time 2014-3-5 9:16:19
 * @描述：SYJK_CCS_TAXTRACE(记录车辆纳税交易轨迹信息)VO
 * */
public class SYJK_CCS_TAXTRACE implements Serializable{
	private static final long serialVersionUID = 1L;
	private String CarSerialNo = null ; 	//	机动车序列号
	private String TaxDealType = null ; 	//	交易类型
	private String TaxDealCode = null ; 	//	交易码
	private String ReturnCode = null ; 		//	返回代码
	private DATE MakeTime = null ; 		//	轨迹记录时间
	public  String getCarSerialNo() {
		return CarSerialNo;
	}
	public void setCarSerialNo(String carSerialNo) {
		CarSerialNo = carSerialNo;
	}
	public String getTaxDealType() {
		return TaxDealType;
	}
	public void setTaxDealType(String taxDealType) {
		TaxDealType = taxDealType;
	}
	public String getTaxDealCode() {
		return TaxDealCode;
	}
	public void setTaxDealCode(String taxDealCode) {
		TaxDealCode = taxDealCode;
	}
	public String getReturnCode() {
		return ReturnCode;
	}
	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}
	public DATE getMakeTime() {
		return MakeTime;
	}
	public void setMakeTime(DATE makeTime) {
		MakeTime = makeTime;
	}
}
