package com.derun.beans;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税变更确认服务
 *            --请求信息对象入参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class BaseChangeConfirmReqInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private TaxDealCode_Type ChangeQueryNo = null;// 车船税变更查询码 ,Y

	private TaxDealCode_Type TaxPrintNo = null;// 车船税变更打印码

	private TaxDealCode_Type ChangeConfirmNo = null;// 车船税变更确认码

	private String CalcTaxFlag = null;// 算税标志 ,Y

	private Tax_Type taxInfo = null;// 车船税信息

	public void setCarSerialNo(String carSerialNo) {
		this.carSerialNo = carSerialNo;
	}

	private Vehicle_Type vehicleInfo;// 车辆信息
	private VehicleOwner_Type VehicleOwnerInfo;// 13 车主信息

	private String UserName = null;// 用户名 String（6） Y
	private String Password = null;// 密码 String（6） Y

	private String AreaCode = null;// 国标区域代码
	private String CompanyCode = null;// 公司代码

	private String ChangeType = null;

	private String INSURE_CONFORM_FLAG = null;
	// subaofeng 2013-7-3 10:42:16 start
	private String carMatchId = null;	// 是哪条匹配规则匹配到的。
	// subaofeng 2013-7-3 10:42:26 end
	// START 2012-11-22 BY YANLONG
	private Date insureStartDate = null;// 投保开始日期
	private Date insureEndDate = null;// 投保结束日期
	
	private String carSerialNo = null;//机动车序列号
	
	public String getCarSerialNo() {
		return carSerialNo;
	}
	
	public String getCarMatchId() {
		return carMatchId;
	}

	public void setCarMatchId(String carMatchId) {
		this.carMatchId = carMatchId;
	}

	public Date getInsureStartDate() {
		return insureStartDate;
	}

	public void setInsureStartDate(Date insureStartDate) {
		this.insureStartDate = insureStartDate;
	}

	public Date getInsureEndDate() {
		return insureEndDate;
	}

	public void setInsureEndDate(Date insureEndDate) {
		this.insureEndDate = insureEndDate;
	}

	// END 2012-11-22 BY YANLONG

	public String getINSURE_CONFORM_FLAG() {
		return INSURE_CONFORM_FLAG;
	}

	public void setINSURE_CONFORM_FLAG(String insure_conform_flag) {
		INSURE_CONFORM_FLAG = insure_conform_flag;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getCalcTaxFlag() {
		return CalcTaxFlag;
	}

	public void setCalcTaxFlag(String calcTaxFlag) {
		CalcTaxFlag = calcTaxFlag;
	}

	public TaxDealCode_Type getChangeQueryNo() {
		return ChangeQueryNo;
	}

	public void setChangeQueryNo(TaxDealCode_Type changeQueryNo) {
		ChangeQueryNo = changeQueryNo;
	}

	public Tax_Type getTaxInfo() {
		return taxInfo;
	}

	public void setTaxInfo(Tax_Type taxInfo) {
		this.taxInfo = taxInfo;
	}

	public TaxDealCode_Type getChangeConfirmNo() {
		return ChangeConfirmNo;
	}

	public void setChangeConfirmNo(TaxDealCode_Type changeConfirmNo) {
		ChangeConfirmNo = changeConfirmNo;
	}

	public String getAreaCode() {
		return AreaCode;
	}

	public void setAreaCode(String areaCode) {
		AreaCode = areaCode;
	}

	public String getChangeType() {
		return ChangeType;
	}

	public void setChangeType(String changeType) {
		ChangeType = changeType;
	}

	public String getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	public TaxDealCode_Type getTaxPrintNo() {
		return TaxPrintNo;
	}

	public void setTaxPrintNo(TaxDealCode_Type taxPrintNo) {
		TaxPrintNo = taxPrintNo;
	}

	public Vehicle_Type getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(Vehicle_Type vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public VehicleOwner_Type getVehicleOwnerInfo() {
		return VehicleOwnerInfo;
	}

	public void setVehicleOwnerInfo(VehicleOwner_Type vehicleOwnerInfo) {
		VehicleOwnerInfo = vehicleOwnerInfo;
	}

}
