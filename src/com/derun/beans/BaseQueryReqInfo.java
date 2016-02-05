package com.derun.beans;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税查询服务--请求信息对象入参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class BaseQueryReqInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private Vehicle_Type vehicleInfo=null;// 车辆信息

	private Tax_Type taxInfo=null;// 车船税信息
	
	
	private String UserName=null	;//用户名	String（6）	Y
	private String 	Password=null;//密码		String（6）	Y
	/**********************/
	private String QuerySequencetTime=null;//投保查询日期
	private String AreaCode=null;//国标区域代码
	private String  CompanyCode=null;//公司代码
//	START 2012-11-22 BY YANLONG
	private Date insureStartDate = null;//投保开始日期
	private Date insureEndDate = null;//投保结束日期
	private String carMatchId = null;//用于记录这辆车是用哪个匹配规则匹配到的
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
//	END   2012-11-22 BY YANLONG		

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

	public String getQuerySequencetTime() {
		return QuerySequencetTime;
	}

	public void setQuerySequencetTime(String querySequencetTime) {
		QuerySequencetTime = querySequencetTime;
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

	public Tax_Type getTaxInfo() {
		return taxInfo;
	}

	public void setTaxInfo(Tax_Type taxInfo) {
		this.taxInfo = taxInfo;
	}

	public Vehicle_Type getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(Vehicle_Type vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public String getCarMatchId() {
		return carMatchId;
	}

	public void setCarMatchId(String carMatchId) {
		this.carMatchId = carMatchId;
	}

}
