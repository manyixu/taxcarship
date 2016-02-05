package com.derun.common.mon;

import java.io.Serializable;

/**
 * 磁盘信息
 * @author wbzhao
 *
 */
public class DiskObj implements Serializable {

	String devName;		//磁盘分区名称
	String totalC;		//磁盘分区总容量
	String free;		//磁盘分区可用大小
	String userPercent;	//磁盘分区利用率
	float y;			//饼图的大小（占比）
	
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public String getTotalC() {
		return totalC;
	}
	public void setTotalC(String totalC) {
		this.totalC = totalC;
	}
	public String getFree() {
		return free;
	}
	public void setFree(String free) {
		this.free = free;
	}
	public String getUserPercent() {
		return userPercent;
	}
	public void setUserPercent(String userPercent) {
		this.userPercent = userPercent;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}
