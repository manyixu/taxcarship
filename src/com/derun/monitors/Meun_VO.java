package com.derun.monitors;

import java.io.Serializable;

/**
 * @author MILI
 * @time 2014-8-19 16:51:25
 * @描述：选择菜单封装类
 * */
public class Meun_VO implements Serializable{
	private static final long serialVersionUID = 1L;
	String title1 = "" ;
	String hidden1 = "" ;
	String title2 = "" ;
	String hidden2 = "" ;
	String title3 = "" ;
	String hidden3 = "" ;
	String title4 = "" ;
	String hidden4 = "" ;
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getHidden1() {
		return hidden1;
	}
	public void setHidden1(String hidden1) {
		this.hidden1 = hidden1;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getHidden2() {
		return hidden2;
	}
	public void setHidden2(String hidden2) {
		this.hidden2 = hidden2;
	}
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public String getHidden3() {
		return hidden3;
	}
	public void setHidden3(String hidden3) {
		this.hidden3 = hidden3;
	}
	public String getTitle4() {
		return title4;
	}
	public void setTitle4(String title4) {
		this.title4 = title4;
	}
	public String getHidden4() {
		return hidden4;
	}
	public void setHidden4(String hidden4) {
		this.hidden4 = hidden4;
	}
}
