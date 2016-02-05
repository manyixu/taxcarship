package com.derun.common.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.derun.common.db.ExeSQL;
import com.derun.common.init.CfgLoader;
import com.derun.model.po.SYJK_CCS_CODE;
/**
 * @author MILI
 * @time 2014-4-16 16:59:27
 * @描述：法定免税车判断
 * */
public class FDMianShui {
	static ExeSQL exesql = new ExeSQL();
	/**
	 * @author MILI
	 * @time 2014-4-16 14:47:53
	 * @描述：1 = 领 使馆车辆  2 = 军区武警车辆  3 = 警车  4 = 不是法免车辆
	 * @描述：判断是否是法定免税车
	 * */
	public static int getFDCL(String carNo){
		int number  = 4 ;
		// 得到数据库配置法定免税车信息
		List<SYJK_CCS_CODE> listcode = CfgLoader.getConfig("SysCode_FD");
		number = getFd(carNo,listcode);
		return number ;
	}
	/**
	 * @描述：1 = 领 使馆车辆  2 = 军区武警车辆  3 = 警车  4 = 不是法免车辆
	 * */
	public static int getFd(String car,List<SYJK_CCS_CODE> listcode) {
		String carNo = null;
		int fd = 4;
		carNo = car == null ? "" : car;
		String regEx = "[\\u4e00-\\u9fa5]";
//		String reg = "[A-Z]" ;
		Pattern p = Pattern.compile(regEx);
		Matcher m = null;
		if (carNo != null && !"".equals(carNo)
				&& carNo.trim().length() > 1) {
			String NewStr = carNo.replace(" ", "");
			m = p.matcher(NewStr.substring(1, 2));
		}
		if ((m != null && m.find()) || car == null || "".equals(car)) {
			fd = 4;
		} else {
			String NewStr = carNo.replace(" ", "");
			for(int i = 0 ; i < listcode.size() ; i++){
				SYJK_CCS_CODE code = listcode.get(i) ;
				if("start".equals(code.getREMARK())){
					if(NewStr.startsWith(code.getCODEVALUE())){
						fd = Integer.parseInt(code.getCODEALIA());
						break ;
					}
				}else if("end".equals(code.getREMARK())){
					if(NewStr.endsWith(code.getCODEVALUE())){
						fd = Integer.parseInt(code.getCODEALIA());
						break ;
					}
				}else{
					fd = 4 ;
				}
			}
		}
		return fd ;
	}
	
	public static boolean getcarType(String carType) {
		if (carType != null) {
			if (carType.equals("81")) {
				return true;
			}
			if (carType.equals("82")) {
				return true;
			}
			if (carType.equals("AA")) {
				return true;
			}
			if (carType.equals("AB")) {
				return true;
			}
			if (carType.equals("AC")) {
				return true;
			}
			if (carType.equals("AD")) {
				return true;
			}
			if (carType.equals("AE")) {
				return true;
			}
			if (carType.equals("9A")) {
				return true;
			}
			if (carType.equals("9B")) {
				return true;
			}
			if (carType.equals("91")) {
				return true;
			}
			if (carType.equals("92")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @author MILI
	 * @time 2014-4-16 15:34:54
	 * @描述：随机自测  main
	 * */
	public static void main(String[] args){
		getFDCL("");
	}
}
