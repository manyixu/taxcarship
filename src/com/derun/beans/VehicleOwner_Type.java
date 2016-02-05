package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车主信息数据类型
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class VehicleOwner_Type implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private String VehicleOwnerName = null;// 车主名称

	private String CredentialNo = null;// 证件号码

	private String CredentialCode = null;// 自然人证件类型

	private String Address = null;// 车主地址

	private String PhoneNo = null;// 联系电话

	
	


	
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCredentialCode() {
		return CredentialCode;
	}

	public void setCredentialCode(String credentialCode) {
		CredentialCode = credentialCode;
	}

	public String getCredentialNo() {
		return CredentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		CredentialNo = credentialNo;
	}

	public String getPhoneNo() {
		return PhoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}

	public String getVehicleOwnerName() {
		return VehicleOwnerName;
	}

	public void setVehicleOwnerName(String vehicleOwnerName) {
		VehicleOwnerName = vehicleOwnerName;
	}

}
