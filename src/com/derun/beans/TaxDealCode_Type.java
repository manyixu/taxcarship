package com.derun.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @param 车船税交易码数据类型
 * @author THZOP
 * 
 */
@SuppressWarnings("serial")
@XmlType(namespace = "http://derun.com")
public class TaxDealCode_Type implements Serializable {
	@XmlElement(namespace = "http://derun.com")
	private String TaxDealCode_Type=null;// 车船税交易码
									// 生成规则为:1位所属标志代码+11位税务机关编码+8位日期+8位时间+1位服务标志代码+4位流水号；

	public String getTaxDealCode_Type() {
		return TaxDealCode_Type;
	}

	public void setTaxDealCode_Type(String taxDealCode_Type) {
		TaxDealCode_Type = taxDealCode_Type;
	}

	

}
