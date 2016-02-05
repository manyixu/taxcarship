package com.derun.monitors.yewu;

import java.io.Serializable;
import java.util.List;

import com.derun.model.po.SYJK_CCS_RKMX;
/**
 * @author MILI
 * @time 2014-9-1 11:06:18
 * @描述：业务展示封装类
 * */
public class YeWu_VO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String num0 = "" ;		// div id
	private String num1 = "" ;		// tr1  id
	private String num2 = "" ;		// tr2  id
	private String table1 = "" ;	// table1 id
	private String table2 = "" ;	// table2 id
	
	private List<String> Newlist = null ;
	private List<String> Oldlist = null ;
	private SYJK_CCS_RKMX newrkmx = null ; 
	private SYJK_CCS_RKMX oldrkmx = null ;
	
	
	public String getTable1() {
		return table1;
	}
	public void setTable1(String table1) {
		this.table1 = table1;
	}
	public String getTable2() {
		return table2;
	}
	public void setTable2(String table2) {
		this.table2 = table2;
	}
	public SYJK_CCS_RKMX getNewrkmx() {
		return newrkmx;
	}
	public void setNewrkmx(SYJK_CCS_RKMX newrkmx) {
		this.newrkmx = newrkmx;
	}
	public SYJK_CCS_RKMX getOldrkmx() {
		return oldrkmx;
	}
	public void setOldrkmx(SYJK_CCS_RKMX oldrkmx) {
		this.oldrkmx = oldrkmx;
	}
	public List<String> getNewlist() {
		return Newlist;
	}
	public void setNewlist(List<String> newlist) {
		Newlist = newlist;
	}
	public List<String> getOldlist() {
		return Oldlist;
	}
	public void setOldlist(List<String> oldlist) {
		Oldlist = oldlist;
	}
	public String getNum0() {
		return num0;
	}
	public void setNum0(String num0) {
		this.num0 = num0;
	}
	public String getNum1() {
		return num1;
	}
	public void setNum1(String num1) {
		this.num1 = num1;
	}
	public String getNum2() {
		return num2;
	}
	public void setNum2(String num2) {
		this.num2 = num2;
	}
}
