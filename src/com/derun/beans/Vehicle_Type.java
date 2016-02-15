package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车辆信息数据类型
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class Vehicle_Type implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private String LicensePlateNo = null;	// 号牌号码
	private String LicensePlateType = null;	// 号牌种类
	private String MotorTypeCode = null;	// 车辆种类,Y
	private String EngineNo = null;			// 发动机号,Y
	private String VIN = null;				// 车架号,Y
	private String MadeFactory = null;		// 制造厂名称
	private String MotorUsageTypeCode = null;// 使用性质,Y
	private String NoLicenseFlag = null;	// 未上牌车辆标志,Y
	private String Model = null;			// 车辆型号
	private String FirstRegisterDate = null;// 车辆初始登记日期,Y
	private String VehicleType = null;		// 交管车辆类型,Y
	private int RatedPassengerCapacity = 0;	// 核定载客数,Y
	private double Tonnage = 0;				// 核定载质量,Y
	private double WholeWeight = 0;			// 整备质量,Y
	private double Displacement = 0;		// 排量 ,Y
	private double Power = 0;				// 功率 ,Y
	private String FuelType = null;			// 燃料种类
	private String SpecialCarType = null;	// 特殊车标志
	private String NoticeType = null;	// 公告型号（新能源）

	public double getDisplacement() {
		return Displacement;
	}

	public void setDisplacement(double displacement) {
		Displacement = displacement;
	}

	public String getEngineNo() {
		return EngineNo;
	}

	public void setEngineNo(String engineNo) {
		EngineNo = engineNo;
	}

	public String getFuelType() {
		return FuelType;
	}

	public void setFuelType(String fuelType) {
		FuelType = fuelType;
	}

	public String getLicensePlateNo() {
		return LicensePlateNo;
	}

	public void setLicensePlateNo(String licensePlateNo) {
		LicensePlateNo = licensePlateNo;
	}

	public String getLicensePlateType() {
		return LicensePlateType;
	}

	public void setLicensePlateType(String licensePlateType) {
		LicensePlateType = licensePlateType;
	}

	public String getMadeFactory() {
		return MadeFactory;
	}

	public void setMadeFactory(String madeFactory) {
		MadeFactory = madeFactory;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public String getMotorTypeCode() {
		return MotorTypeCode;
	}

	public void setMotorTypeCode(String motorTypeCode) {
		MotorTypeCode = motorTypeCode;
	}

	public String getMotorUsageTypeCode() {
		return MotorUsageTypeCode;
	}

	public void setMotorUsageTypeCode(String motorUsageTypeCode) {
		MotorUsageTypeCode = motorUsageTypeCode;
	}

	public String getNoLicenseFlag() {
		return NoLicenseFlag;
	}

	public void setNoLicenseFlag(String noLicenseFlag) {
		NoLicenseFlag = noLicenseFlag;
	}

	public double getPower() {
		return Power;
	}

	public void setPower(double power) {
		Power = power;
	}

	public int getRatedPassengerCapacity() {
		return RatedPassengerCapacity;
	}

	public void setRatedPassengerCapacity(int ratedPassengerCapacity) {
		RatedPassengerCapacity = ratedPassengerCapacity;
	}

	public String getSpecialCarType() {
		return SpecialCarType;
	}

	public void setSpecialCarType(String specialCarType) {
		SpecialCarType = specialCarType;
	}

	public double getTonnage() {
		return Tonnage;
	}

	public void setTonnage(double tonnage) {
		Tonnage = tonnage;
	}

	public String getVehicleType() {
		return VehicleType;
	}

	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vin) {
		VIN = vin;
	}

	public double getWholeWeight() {
		return WholeWeight;
	}

	public void setWholeWeight(double wholeWeight) {
		WholeWeight = wholeWeight;
	}

	public String getFirstRegisterDate() {
		return FirstRegisterDate;
	}

	public void setFirstRegisterDate(String firstRegisterDate) {
		FirstRegisterDate = firstRegisterDate;
	}

	public String getNoticeType() {
		return NoticeType;
	}

	public void setNoticeType(String noticeType) {
		NoticeType = noticeType;
	}

}
