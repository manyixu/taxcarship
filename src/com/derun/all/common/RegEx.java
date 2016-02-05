package com.derun.all.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MILI
 * @time 2014-7-15 17:05:47
 * @描述：正则表达式
 * */
public class RegEx {
	/**验证必须为数字（整数或小数）*/
	public static boolean int_type(String str){
		boolean flag = false ;
		String pattern = "[0-9]+(.[0-9]+)?";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		flag = m.matches();
//		System.out.println(m.matches());
		return flag ;
	}
	
	
	
	public static void main(String args[]){
		
		new RegEx().int_type("35.9");
	}
}
