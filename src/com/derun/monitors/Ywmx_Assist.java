package com.derun.monitors;

import java.util.List;

import com.derun.common.init.CfgLoader;
import com.derun.model.po.SYJK_CCS_CODE;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-8-6 18:09:52
 * @描述：业务明细 辅助类
 * */
public class Ywmx_Assist {
	/**过滤  纳税类型*/
	public static SYJK_CCS_RKMX setRkmx_ns(SYJK_CCS_RKMX rkmx){
		if("T".equals(rkmx.getTAXCONDITIONCODE())){
			rkmx.setTAXCONDITIONCODE("正常纳税");
		}else if("E".equals(rkmx.getTAXCONDITIONCODE())){
			rkmx.setTAXCONDITIONCODE("免税车辆");
		}else if("P".equals(rkmx.getTAXCONDITIONCODE())){
			rkmx.setTAXCONDITIONCODE("完税车辆");
		}else if("R".equals(rkmx.getTAXCONDITIONCODE())){
			rkmx.setTAXCONDITIONCODE("拒缴车辆");
		}else if("C".equals(rkmx.getTAXCONDITIONCODE())){
			rkmx.setTAXCONDITIONCODE("减税车辆");
		}
		String vin = rkmx.getVIN() , _vin = "" ;;
		String xin = "" ;
		if(vin != null && !"".equals(vin)){
			int vin_length = vin.length() / 2 ;
			if(vin_length != 0){
				_vin = vin.substring(0, vin_length);
				for(int i = 0 ;i < vin.length() - 1 - vin_length; i++){
					xin += "*";
				}
				_vin += xin ;
			}else{
				vin = "*" ;
			}
		}
//		rkmx.setVIN(_vin);
		return rkmx ;
	}
	/**
	 * @author MILI
	 * @time 2014-8-6 18:12:26
	 * @描述：过滤  保险公司
	 * */
	public static SYJK_CCS_RKMX setRkmx_bx(SYJK_CCS_RKMX rkmx){
		
		List<SYJK_CCS_CODE> listCode = CfgLoader.getConfig("SysCode_Insurance");
		for(SYJK_CCS_CODE code : listCode){
			if(code.getCODE().equals(rkmx.getPAYCOMPANYCODE())){
				rkmx.setPAYCOMPANYCODE(code.getCODEVALUE());
				break;
			}
		}
		return rkmx ;
	}
	/**
	 * @author MILI
	 * @time 2014-8-6 18:12:26
	 * @描述：过滤  车辆种类
	 * */
	public static SYJK_CCS_RKMX set_HPZL(SYJK_CCS_RKMX rkmx){
		if("01".equals(rkmx.getHPZL())){
			rkmx.setHPZL("大型汽车号牌");
		}else if("02".equals(rkmx.getHPZL())){
			rkmx.setHPZL("小型汽车号牌");
		}else if("03".equals(rkmx.getHPZL())){
			rkmx.setHPZL("使馆汽车号牌");
		}else if("04".equals(rkmx.getHPZL())){
			rkmx.setHPZL("领馆汽车号牌");
		}else if("05".equals(rkmx.getHPZL())){
			rkmx.setHPZL("境外汽车号牌");
		}else if("06".equals(rkmx.getHPZL())){
			rkmx.setHPZL("外籍汽车号牌");
		}else if("07".equals(rkmx.getHPZL())){
			rkmx.setHPZL("两、三轮摩托车号牌");
		}else if("08".equals(rkmx.getHPZL())){
			rkmx.setHPZL("轻便摩托车号牌");
		}else if("09".equals(rkmx.getHPZL())){
			rkmx.setHPZL("使馆摩托车号牌");
		}else if("10".equals(rkmx.getHPZL())){
			rkmx.setHPZL("领馆摩托车号牌");
		}else if("11".equals(rkmx.getHPZL())){
			rkmx.setHPZL("境外摩托车号牌");
		}else if("12".equals(rkmx.getHPZL())){
			rkmx.setHPZL("外籍摩托车号牌");
		}else if("13".equals(rkmx.getHPZL())){
			rkmx.setHPZL("农用运输车号牌");
		}else if("14".equals(rkmx.getHPZL())){
			rkmx.setHPZL("拖拉机号牌");
		}else if("15".equals(rkmx.getHPZL())){
			rkmx.setHPZL("挂车号牌");
		}else if("16".equals(rkmx.getHPZL())){
			rkmx.setHPZL("教练车号牌");
		}else if("17".equals(rkmx.getHPZL())){
			rkmx.setHPZL("教练摩托车号牌");
		}else if("18".equals(rkmx.getHPZL())){
			rkmx.setHPZL("实验汽车号牌");
		}else if("19".equals(rkmx.getHPZL())){
			rkmx.setHPZL("实验摩托车号牌");
		}else if("20".equals(rkmx.getHPZL())){
			rkmx.setHPZL("临时入境汽车号牌");
		}else if("21".equals(rkmx.getHPZL())){
			rkmx.setHPZL("临时入境摩托车号牌");
		}else if("22".equals(rkmx.getHPZL())){
			rkmx.setHPZL("临时行驶车号牌");
		}else if("23".equals(rkmx.getHPZL())){
			rkmx.setHPZL("公安汽车号牌");
		}else if("24".equals(rkmx.getHPZL())){
			rkmx.setHPZL("公安摩托车号牌");
		}else if("99".equals(rkmx.getHPZL())){
			rkmx.setHPZL("其它");
		}
		return rkmx ;
	}
	/**
	 * @author MILI
	 * @time 2014-8-6 18:12:26
	 * @描述：过滤  计算单位代码
	 * */
	public static SYJK_CCS_RKMX set_JS(SYJK_CCS_RKMX rkmx){
		if("1".equals(rkmx.getTAXUNITTYPECODE())){
			rkmx.setTAXUNITTYPECODE("辆");
		}else if("2".equals(rkmx.getTAXUNITTYPECODE())){
			rkmx.setTAXUNITTYPECODE("吨");
		}else{
			return rkmx ;
		}
		return rkmx ;
	}
	/**
	 * @author MILI
	 * @time 2014-8-6 18:12:26
	 * @描述：过滤  算税标志
	 * */
	public static SYJK_CCS_RKMX set_SS(SYJK_CCS_RKMX rkmx){
		if("0".equals(rkmx.getCHANGETYPE())){
			rkmx.setCOUNTTAXTYPE("税源算税");
		}else if("1".equals(rkmx.getCHANGETYPE())){
			rkmx.setCOUNTTAXTYPE("车险算税");
		}else if("2".equals(rkmx.getCHANGETYPE())){
			rkmx.setCOUNTTAXTYPE("保险算税");
		}else{
			return rkmx ;
		}
		return rkmx ;
	}
	/**
	 * @author MILI
	 * @time 2014-8-6 18:12:26
	 * @描述：过滤  拒缴标志
	 * */
	public static SYJK_CCS_RKMX set_JJ(SYJK_CCS_RKMX rkmx){
		if("0".equals(rkmx.getRefusetype())){
			rkmx.setRefusetype("拒缴");
		}else if("1".equals(rkmx.getRefusetype())){
			rkmx.setRefusetype("非拒缴");
		}else{
			return rkmx ;
		}
		return rkmx ;
	}
	/**
	 * @author MILI
	 * @time 2014-8-6 18:12:26
	 * @描述：过滤  减免原因
	 * */
	public static SYJK_CCS_RKMX set_JMYY(SYJK_CCS_RKMX rkmx){
		if("C".equals(rkmx.getDEDUCTIONDUECODE())){
			rkmx.setDEDUCTIONDUECODE("具备减免税");
		}else if("F".equals(rkmx.getDEDUCTIONDUECODE())){
			rkmx.setDEDUCTIONDUECODE("拖拉机");
		}else if("A".equals(rkmx.getDEDUCTIONDUECODE())){
			rkmx.setDEDUCTIONDUECODE("军队、武警");
		}else if("P".equals(rkmx.getDEDUCTIONDUECODE())){
			rkmx.setDEDUCTIONDUECODE("警车");
		}else if("D".equals(rkmx.getDEDUCTIONDUECODE())){
			rkmx.setDEDUCTIONDUECODE("国际组织");
		}else if("E".equals(rkmx.getDEDUCTIONDUECODE())){
			rkmx.setDEDUCTIONDUECODE("能源减免");
		}else if("O".equals(rkmx.getDEDUCTIONDUECODE())){
			rkmx.setDEDUCTIONDUECODE("其他");
		}else{
			return rkmx ;
		}
		return rkmx ;
	}
	/**
	 * @author MILI
	 * @time 2014-8-6 18:12:26
	 * @描述：过滤  减免方案
	 * */
	public static SYJK_CCS_RKMX set_JMFA(SYJK_CCS_RKMX rkmx){
		if("E".equals(rkmx.getDEDUCTIONDUETYPE())){
			rkmx.setDEDUCTIONDUETYPE("免税");
		}else if("P".equals(rkmx.getDEDUCTIONDUETYPE())){
			rkmx.setDEDUCTIONDUETYPE("比例减免");
		}else if("A".equals(rkmx.getDEDUCTIONDUETYPE())){
			rkmx.setDEDUCTIONDUETYPE("金额减免");
		}else{
			return rkmx ;
		}
		return rkmx ;
	}
	/**
	 * @author MILI
	 * @time 2014-8-6 18:12:26
	 * @描述：过滤
	 * */
	public static SYJK_CCS_RKMX Filter(SYJK_CCS_RKMX rkmx){
		rkmx = Ywmx_Assist.setRkmx_bx(rkmx);	// 汉化 保险公司名称
		rkmx = Ywmx_Assist.setRkmx_ns(rkmx);	// 汉化 纳税类型名称
		rkmx = Ywmx_Assist.set_HPZL(rkmx);		// 汉化 号牌种类
		rkmx = Ywmx_Assist.set_JS(rkmx);		// 汉化 计算单位代码
		rkmx = Ywmx_Assist.set_SS(rkmx);		// 汉化 算税代码
		rkmx = Ywmx_Assist.set_JJ(rkmx);		// 汉化 拒缴标志
		rkmx = Ywmx_Assist.set_JMYY(rkmx);
		rkmx = Ywmx_Assist.set_JMFA(rkmx);
		return rkmx ;
	}
}
