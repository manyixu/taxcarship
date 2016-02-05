package com.derun.common.util;

import java.io.Serializable;
/**
 * @author MILI
 * @time 2014-3-14 16:13:00
 * @描述：纳税类型匹配
 * */
public class Tax_Type_Code implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String BLACK_LIST = "问题名单车辆";		// 1
	public static final String NO_VOUCHER = "不算税(新能源)" ;	// 2
	public static final String VOUCHER_M = "免税车" ;			// 3
	public static final String DQ_VOUCHER = "短期车" ;			// 4
	public static final String NEW_CARS = "新车";				// 5
	public static final String NEW_OWING = "新车欠税" ;			// 6
	public static final String J_VOUCHER = "减税车";				// 7
	public static final String T_VOUCHER = "退税车" ;			// 8
	public static final String VOUCHER_F = "法定免税" ;			// 9
	
	/**
	 * @author MILI
	 * @time 2014-3-18 9:52:33
	 * @描述：toString 
	 * */
	public String toString(String names){
		return names;
	}
//	public static void main(String[] args){
//		Tax_type_Code c = new Tax_type_Code();
//		System.out.println(c.toString(BLACK_LIST) + "------");
//	}
}
