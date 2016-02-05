package com.derun.beans;

import com.derun.common.util.DateUtil;
/**
 * @author MILI
 * @time 2014-4-21 9:33:37
 * @描述：车船税公共类
 * */
public class TaxCarCount {
	// 流水号
	private static int a = 0 ;
	private static int b = 0 ;
	/**
	 * @author MILI
	 * @time 2014-4-21 9:41:19
	 * @描述：生成各类型码
	 * @入参：算税标志码、公司代码（4位）+国标区域代码（6位）、"A"
	 * @出参：TaxDealCode_Type 各种代码封装类
	 * */
	public static synchronized TaxDealCode_Type getTaxqueryNo(String acode,String acompany,String aacre,String bcode){
		// 生成规则为： 所属标识代码（1位）+公司代码（4位）+国标区域代码（6位）+服务编码（1位）+日期（8位）+时间（9位）+流水号（3位）
		TaxDealCode_Type code_type = new TaxDealCode_Type();
		StringBuffer sbf = new StringBuffer();
		if(a == 1000)
			a = 0 ;
		String cc = String.format("%03d", a++);	// 生成3位流水号
		sbf.append(acode);			// 所属标识代码（1位）
		sbf.append(acompany);		// 公司代码（4位）
		sbf.append(aacre);			// 国标区域代码（6位）
		sbf.append(bcode);			// 服务编码（1位）
		sbf.append(DateUtil.getDIY("yyyyMMddHHmmssSSS")); // 日期（8位）时间（9位）
		sbf.append(cc);				// 流水号（3位）
		code_type.setTaxDealCode_Type(sbf.toString());
		return code_type ;
	}
	/**
	 * @author MILI
	 * @描述：核定库 机动车序列号生成
	 * @date 2015-4-1 10:27:00
	 * @规则：有效状态+更新状态+车辆类型代码+车辆使用性质代码+车辆种类代码+保险公司代码+省份代码+年月日（yyyyMMdd）+时分秒毫秒（HHmmssSSS）+4位随机数
	 * */
	public static synchronized String getHDK_CODE(String ValidateFlag,String update,String MOTORUSAGETYPECODE,String MOTORTYPECODE,String acompany){
		StringBuffer sbf = new StringBuffer();
		if(b == 1000){
			b = 0 ;
		}
		String cc = String.format("%04d", b++);	// 生成3位流水号
		sbf.append(ValidateFlag);	//	有效状态
		sbf.append(update);			//	更新状态
//		sbf.append(VEHICLETYPE);	//	车辆类型代码
		sbf.append(MOTORUSAGETYPECODE);		//	车辆使用性质代码
		sbf.append(MOTORTYPECODE);	//	车辆种类代码
		sbf.append(acompany);		//	保险公司代码
//		sbf.append(sf);				//	省份代码
		sbf.append(DateUtil.getDIY("yyyyMMddHHmmssSSS"));	//	年月日（yyyyMMdd）时分秒毫秒（HHmmssSSS）
		sbf.append(cc);				//	4位随机数
		return sbf.toString() ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-21 11:30:14
	 * @描述：自用自测
	 * */
	public static void main(String[] args){
		TaxDealCode_Type code_type = new TaxDealCode_Type();
		for(int i = 0 ;i < 13 ;i++){
			code_type = getTaxqueryNo("1","PICC","420000","A");
			System.out.println(code_type.getTaxDealCode_Type());
		}
		// 2PICC420000B20111014212106387033
	}
}
