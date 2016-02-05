package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税问题车辆名单下载服务--请求信息对象入参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class QueryDownloadReqInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private String QueryDownloadAnnual=null;// 问题名单所属年度 Y
	private String UserName=null	;//用户名	String（6）	Y
	private String 	Password=null;//密码		String（6）	Y
	

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

	public String getQueryDownloadAnnual() {
		return QueryDownloadAnnual;
	}

	public void setQueryDownloadAnnual(String queryDownloadAnnual) {
		QueryDownloadAnnual = queryDownloadAnnual;
	}

	
}
