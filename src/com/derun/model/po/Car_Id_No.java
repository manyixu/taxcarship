package com.derun.model.po;

import java.io.Serializable;
/**
 * @author MILI
 * @time 2014-4-21 16:35:57
 * @描述：插入数据库的时候 需要用到的参数
 * */
public class Car_Id_No implements Serializable{
	private static final long serialVersionUID = 1L;
	private String carMatchId = null ;   	// 车辆匹配ID
	private String carSerialNo = null ;		// 机动车序列号
	public String getCarMatchId() {
		return carMatchId;
	}
	public void setCarMatchId(String carMatchId) {
		this.carMatchId = carMatchId;
	}
	public String getCarSerialNo() {
		return carSerialNo;
	}
	public void setCarSerialNo(String carSerialNo) {
		this.carSerialNo = carSerialNo;
	}
}
