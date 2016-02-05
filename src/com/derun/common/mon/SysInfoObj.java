package com.derun.common.mon;

import java.io.Serializable;
import java.util.List;

/**
 * 系统信息实体
 * @author wbzhao
 *
 */
public class SysInfoObj implements Serializable{
	
	String computerName;	//计算机名
	String ip;				//ip地址
	
	String javaVersion;		//java运行环境版本
	String jvmTotalMem;		//JVM总内存
	String jvmFreeMem;		//JVM当前分配总内存
	String jvmMaxMem;		//JVM最大可分配内存
	
	String totalMem;		//系统总内存
	String freeMem;			//系统可用内存
	
	double cpuPercent;		//当前cpu占用率
	
	List<CpuObj> cpuObjList;	//CPU信息列表
	List<DiskObj> diskObjList;	//磁盘信息列表
	
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getJavaVersion() {
		return javaVersion;
	}
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}
	public String getJvmTotalMem() {
		return jvmTotalMem;
	}
	public void setJvmTotalMem(String jvmTotalMem) {
		this.jvmTotalMem = jvmTotalMem;
	}
	public String getJvmFreeMem() {
		return jvmFreeMem;
	}
	public void setJvmFreeMem(String jvmFreeMem) {
		this.jvmFreeMem = jvmFreeMem;
	}
	public String getJvmMaxMem() {
		return jvmMaxMem;
	}
	public void setJvmMaxMem(String jvmMaxMem) {
		this.jvmMaxMem = jvmMaxMem;
	}
	public String getTotalMem() {
		return totalMem;
	}
	public void setTotalMem(String totalMem) {
		this.totalMem = totalMem;
	}
	public String getFreeMem() {
		return freeMem;
	}
	public void setFreeMem(String freeMem) {
		this.freeMem = freeMem;
	}
	public double getCpuPercent() {
		return cpuPercent;
	}
	public void setCpuPercent(double cpuPercent) {
		this.cpuPercent = cpuPercent;
	}
	public List<CpuObj> getCpuObjList() {
		return cpuObjList;
	}
	public void setCpuObjList(List<CpuObj> cpuObjList) {
		this.cpuObjList = cpuObjList;
	}
	public List<DiskObj> getDiskObjList() {
		return diskObjList;
	}
	public void setDiskObjList(List<DiskObj> diskObjList) {
		this.diskObjList = diskObjList;
	}
	
	

}
