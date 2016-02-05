package com.derun.beans;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 问题名单数据类型
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class TaxDownload_Type implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private Vehicle_Type VehicleInfo=null;// 车辆信息

	private VehicleOwner_Type VehicleOwnerInfo=null;// 车主信息

	private Tax_Type TaxInfo=null;// 车船税信息

	private String 	reason=null;//问题原因

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public Tax_Type getTaxInfo() {
		return TaxInfo;
	}

	public void setTaxInfo(Tax_Type taxInfo) {
		TaxInfo = taxInfo;
	}

	public Vehicle_Type getVehicleInfo() {
		return VehicleInfo;
	}

	public void setVehicleInfo(Vehicle_Type vehicleInfo) {
		VehicleInfo = vehicleInfo;
	}

	public VehicleOwner_Type getVehicleOwnerInfo() {
		return VehicleOwnerInfo;
	}

	public void setVehicleOwnerInfo(VehicleOwner_Type vehicleOwnerInfo) {
		VehicleOwnerInfo = vehicleOwnerInfo;
	}

}
