package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税纳税信息查询服务上传服务--请求信息对象入参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class TaxPayQueryReqInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private Vehicle_Type	vehicleInfo=null	;//车辆信息	Y
	private String  UserName=null;//用户名
	private String Password ;//密码
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

	public Vehicle_Type getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(Vehicle_Type vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}
	
}
