package com.derun.model.po;

import java.io.Serializable;
import java.util.Date;

import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.VehicleOwner_Type;
import com.derun.beans.Vehicle_Type;
/**
 * @author MILI
 * @time 2014-4-15 17:09:04
 * @描述：投保查询 批改查询 公共部分
 * */
public class Query_ChangeQurey implements Serializable{
	private static final long serialVersionUID = 1L;
	private Vehicle_Type vehicleInfo = null;		// 车辆信息
	private Tax_Type taxInfo = null;				// 车船税信息
	private String UserName = null;					// 用户名 String（6） Y
	private String Password = null;					// 密码 String（6） Y
	private String QuerySequencetTime = null;		// 投保查询日期
	private String AreaCode = null;					// 国标区域代码
	private String CompanyCode = null;				// 公司代码
	private Date insureStartDate = null;			// 投保开始日期
	private Date insureEndDate = null;				// 投保结束日期
	private String carMatchId = null;				// 用于记录这辆车是用哪个匹配规则匹配到的
	private TaxDealCode_Type TaxConfirmNo = null;	// 车船税确认码 ,Y
	private String ChangeType = null;				// 变更类型 ,Y
	private VehicleOwner_Type VehicleOwnerInfo = null;	// 车主信息
	public Vehicle_Type getVehicleInfo() {
		return vehicleInfo;
	}
	public void setVehicleInfo(Vehicle_Type vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}
	public Tax_Type getTaxInfo() {
		return taxInfo;
	}
	public void setTaxInfo(Tax_Type taxInfo) {
		this.taxInfo = taxInfo;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getQuerySequencetTime() {
		return QuerySequencetTime;
	}
	public void setQuerySequencetTime(String querySequencetTime) {
		QuerySequencetTime = querySequencetTime;
	}
	public String getAreaCode() {
		return AreaCode;
	}
	public void setAreaCode(String areaCode) {
		AreaCode = areaCode;
	}
	public String getCompanyCode() {
		return CompanyCode;
	}
	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
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
	public String getCarMatchId() {
		return carMatchId;
	}
	public void setCarMatchId(String carMatchId) {
		this.carMatchId = carMatchId;
	}
	public TaxDealCode_Type getTaxConfirmNo() {
		return TaxConfirmNo;
	}
	public void setTaxConfirmNo(TaxDealCode_Type taxConfirmNo) {
		TaxConfirmNo = taxConfirmNo;
	}
	public String getChangeType() {
		return ChangeType;
	}
	public void setChangeType(String changeType) {
		ChangeType = changeType;
	}
	public VehicleOwner_Type getVehicleOwnerInfo() {
		return VehicleOwnerInfo;
	}
	public void setVehicleOwnerInfo(VehicleOwner_Type vehicleOwnerInfo) {
		VehicleOwnerInfo = vehicleOwnerInfo;
	}
}
