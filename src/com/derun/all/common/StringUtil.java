package com.derun.all.common;

public class StringUtil {
	/**
	 * @author MILI
	 * @time 2014-6-25 11:02:16
	 * @描述：是不是空
	 * */
	public static String Isnotnull(String strNull){
		String str = "" ;
		if(null == strNull){
			return str ;
		}else{
			return strNull ;
		}
	}
}
