package com.derun.common.util;

import java.util.List;

import com.derun.common.init.CfgLoader;
import com.derun.model.po.SYJK_CCS_CODE;

/**
 * @author MILI
 * @time : 2014-3-10 8:57:48
 * @描述：涉税税款信息匹配
 * */
public class ShuiKuanType {
	/**
	 * 描述： 匹配交强险车辆类型
	 * */
	public static String JiaoQiangType(String MotorTypeCode) {
		String _MotorTypeCode = MotorTypeCode;
		// 如果参数是 null 给 100
		_MotorTypeCode = _MotorTypeCode == null ? "100" : _MotorTypeCode;
		// 有一项匹配 交强险类型属于 94
		if ("9A".equals(_MotorTypeCode) || "9B".equals(_MotorTypeCode)
				|| "AA".equals(_MotorTypeCode) || "AC".equals(_MotorTypeCode)
				|| "AD".equals(_MotorTypeCode) || "AB".equals(_MotorTypeCode)
				|| "AE".equals(_MotorTypeCode) || "BA".equals(_MotorTypeCode)
				|| "BB".equals(_MotorTypeCode) || "BC".equals(_MotorTypeCode)
				|| "BD".equals(_MotorTypeCode) || "BE".equals(_MotorTypeCode)
				|| "BF".equals(_MotorTypeCode) || "BG".equals(_MotorTypeCode)
				|| "CA".equals(_MotorTypeCode) || "CB".equals(_MotorTypeCode)
				|| "CC".equals(_MotorTypeCode) || "CD".equals(_MotorTypeCode)
				|| "CE".equals(_MotorTypeCode) || "CF".equals(_MotorTypeCode)
				|| "CG".equals(_MotorTypeCode)) {
			_MotorTypeCode = "94";
		}
		return _MotorTypeCode;
	}

	/**
	 * @author MILI
	 * @time 2014-3-10 9:45:47 
	 * 描述：是否是按排量计算的车辆
	 * */
	public static boolean MotorP_Matching(String MotorTypeCode) {
		boolean flag = false;
		if ("11".equals(MotorTypeCode) || "12".equals(MotorTypeCode)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @author MILI
	 * @time 2014-3-10 9:45:47 
	 * @描述：是否是按吨位计算的车辆
	 * */
	public static boolean MotorD_Matching(String MotorTypeCode){
		boolean flag = false ;
		if("21".equals(MotorTypeCode)
				|| "22".equals(MotorTypeCode)
				|| "23".equals(MotorTypeCode)
				|| "24".equals(MotorTypeCode)
				|| "25".equals(MotorTypeCode)
				|| "26".equals(MotorTypeCode)
				|| "27".equals(MotorTypeCode)
				|| "28".equals(MotorTypeCode)
				|| "30".equals(MotorTypeCode)
				|| "31".equals(MotorTypeCode)
				|| "9A".equals(MotorTypeCode)
				|| "9B".equals(MotorTypeCode)
				|| "AA".equals(MotorTypeCode)
				|| "AC".equals(MotorTypeCode)
				|| "AD".equals(MotorTypeCode)
				|| "AB".equals(MotorTypeCode)
				|| "AE".equals(MotorTypeCode)
				|| "BA".equals(MotorTypeCode)
				|| "BB".equals(MotorTypeCode)
				|| "BC".equals(MotorTypeCode)
				|| "BD".equals(MotorTypeCode)
				|| "BE".equals(MotorTypeCode)
				|| "BF".equals(MotorTypeCode)
				|| "BG".equals(MotorTypeCode)
				|| "CA".equals(MotorTypeCode)
				|| "CB".equals(MotorTypeCode)
				|| "CC".equals(MotorTypeCode)
				|| "CD".equals(MotorTypeCode)
				|| "CE".equals(MotorTypeCode)
				|| "CF".equals(MotorTypeCode)
				|| "CG".equals(MotorTypeCode)
				|| "41".equals(MotorTypeCode)
				|| "40".equals(MotorTypeCode)
				|| "93".equals(MotorTypeCode)
				|| "94".equals(MotorTypeCode)
				|| "50".equals(MotorTypeCode)
				|| "51".equals(MotorTypeCode)
				|| "60".equals(MotorTypeCode)){
			flag = true ;
		}
		return flag ;
	}
	/**
	 * @author MILI
	 * @time 2014-3-10 9:45:47 
	 * 描述：特殊车标志 必须是 1、2、3、4、5 或者 ""
	 * */
	public static boolean MotorT_Matching(String tec) {
		boolean flag = false;
		if ("".equals(tec) || "1".equals(tec) || "2".equals(tec)
				|| "3".equals(tec) || "4".equals(tec) || "5".equals(tec) || tec == null) {
			flag = true ;
		}
		return flag;
	}
	/**
	 * @author MILI
	 * @time 2014-3-12 10:49:02 
	 * 描述：变更类型
	 * */
	public static boolean BG_type(String _type){
		boolean flag = true ;
		if(_type != null && !"".equals(_type)){
			if ("1".equals(_type) || "8".equals(_type)
					|| "10".equals(_type) || "9".equals(_type)
					|| "11".equals(_type) || "12".equals(_type)
					|| "13".equals(_type) || "14".equals(_type)) {
				flag = false ;
			}
		}
		return flag ;
	}
	/**
	 * @author MILI
	 * @time 2014-3-13 16:44:01
	 * @描述：税目匹配
	 * */
	public static boolean ShuiMu_type(List<SYJK_CCS_CODE> ccs_code,String jqtype){
		boolean flag = false ;
		if(ccs_code != null && ccs_code.size() > 0){
			int number = ccs_code.size();
			String _ccs_code = null ;
			for(int i = 0 ; i < number ; i++){
				_ccs_code = ccs_code.get(i).getCODEALIA();
				if(_ccs_code.contains(jqtype + "m")){
					flag = true ;
					break ;
				}
			}
		}
		return flag ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-8 11:15:43
	 * @描述：车辆使用性质代码  匹配
	 * */
	public static boolean getMotorType(String motor){
		boolean flag = false ;
		if(motor == null || "".equals(motor)){
			flag = false ;
		}else{
			motor = motor.toUpperCase();  // 转换为大写
			List<SYJK_CCS_CODE> motor_code = CfgLoader.getConfig("SysCode_Motor"); // 车辆使用性质代码
			for(SYJK_CCS_CODE code_value : motor_code){
				if(code_value.getCODEVALUE().equals(motor)){
					flag = true ;
					break ;
				}
			}
		}
		return flag ;
	} 
	/**
	 * @author MILI
	 * @time 2014-5-8 11:15:43
	 * @描述：未上牌车辆代码  匹配
	 * */
	public static boolean getNoLicense(String nolicense){
		boolean flag = false ;
		List<SYJK_CCS_CODE> motor_code = CfgLoader.getConfig("SysCode_NoLicense"); // 未上牌车辆代码
		for(SYJK_CCS_CODE code_value : motor_code){
			if(code_value.getCODEVALUE().equals(nolicense)){
				flag = true ;
				break ;
			}
		}
		return flag ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-20 9:44:41
	 * @描述：纳税类型是否 符合要求
	 * */
	public static boolean getTaxConditionCode(String TaxConditionCode){
		boolean flag = false ;
		if("T".equals(TaxConditionCode)){
			flag = true ;
		}else if("P".equals(TaxConditionCode)){
			flag = true ;
		}else if("R".equals(TaxConditionCode)){
			flag = true ;
		}else if("E".equals(TaxConditionCode)){
			flag = true ;
		}else if("C".equals(TaxConditionCode)){
			flag = true ;
		}else{
			flag = false ;
		}
		return flag ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-30 15:46:03
	 * @描述：交通管理部门车辆类型代码验证
	 * */
	public static boolean getVehicleType(String VehicleType){
		boolean flag = false ;
		if(VehicleType == null || "".equals(VehicleType)){
			flag = true ;
		}else{
			List<SYJK_CCS_CODE> TypeCode = CfgLoader.getConfig("SysCode_MotorUsage");
			for(SYJK_CCS_CODE code_value : TypeCode){
				if(code_value.getCODEVALUE().equals(VehicleType)){
					flag = true ;
					break ;
				}
			}
		}
		return flag ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-30 15:46:03
	 * @描述：减免税原因代码校验
	 * */
	public static boolean getSysCode_DueCode(String DueCode){
		boolean flag = true ;
		if(DueCode == null || "".equals(DueCode)){
			flag = false ;
		}else{
			List<SYJK_CCS_CODE> TypeCode = CfgLoader.getConfig("SysCode_DueCode");
			for(SYJK_CCS_CODE code_value : TypeCode){
				if(code_value.getCODEVALUE().equals(DueCode)){
					flag = false ;
					break ;
				}
			}
		}
		return flag ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-30 15:46:03
	 * @描述：减免税方案代码
	 * */
	public static boolean getSysCode_DueType(String DueType){
		boolean flag = true ;
		if(DueType == null || "".equals(DueType)){
			flag = false ;
		}else{
			List<SYJK_CCS_CODE> TypeCode = CfgLoader.getConfig("SysCode_DueType");
			for(SYJK_CCS_CODE code_value : TypeCode){
				if(code_value.getCODEVALUE().equals(DueType)){
					flag = false ;
					break ;
				}
			}
		}
		return flag ;
	}
}
