package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税问题车辆名单下载服务--请求信息对象出参
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class QueryDownloadResInfo implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private TaxDealCode_Type TaxDownloadNo=null;// 车船税问题名单下载码 ,Y

	private TaxDownload_Type[]	TaxDownloadArray=null;//问题名单对象数组

	private String ReturnCode=null;// 返回代码 ,Y
	
	

	public TaxDownload_Type[] getTaxDownloadArray() {
		return TaxDownloadArray;
	}

	public void setTaxDownloadArray(TaxDownload_Type[] taxDownloadArray) {
		TaxDownloadArray = taxDownloadArray;
	}

	public String getReturnCode() {
		return ReturnCode;
	}

	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}

	public TaxDealCode_Type getTaxDownloadNo() {
		return TaxDownloadNo;
	}

	public void setTaxDownloadNo(TaxDealCode_Type taxDownloadNo) {
		TaxDownloadNo = taxDownloadNo;
	}

	

}
