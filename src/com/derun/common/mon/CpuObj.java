package com.derun.common.mon;

import java.io.Serializable;

/**
 * CPU信息
 * @author wbzhao
 *
 */
public class CpuObj implements Serializable {
	
	String model;			//CPU类别
	String clockSpeed;		//CPU主频（HZ）
	String totalPercent;	//CPU总使用率（包括用户使用率和系统使用率）
	float y;				//饼图的大小（占比）
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getClockSpeed() {
		return clockSpeed;
	}
	public void setClockSpeed(String clockSpeed) {
		this.clockSpeed = clockSpeed;
	}
	public String getTotalPercent() {
		return totalPercent;
	}
	public void setTotalPercent(String totalPercent) {
		this.totalPercent = totalPercent;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
}
