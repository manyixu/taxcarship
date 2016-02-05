package com.derun.common.chk;


import com.derun.beans.TaxPayQueryReqInfo;
import com.derun.beans.Vehicle_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;

/**
 * @author MILI
 * @time 2014-3-7 9:10:44
 * @描述：纳税信息查询用户名密码和入参有效性验证
 * */
public class TaxPayQueryChk {
	public String Join_valid(Object o,Object oo) {
		TaxPayQueryReqInfo taxpayqueryReq = (TaxPayQueryReqInfo) o ;
			String returnCode = ChkUtil.CHK_0000 ;
			Vehicle_Type vehicle = taxpayqueryReq.getVehicleInfo() ;	
//			if(IsNull(ChkUtil.CHK_8123) && (vehicle.getEngineNo() == null || "".equals(vehicle.getEngineNo()))){
//				returnCode = ChkUtil.CHK_8123 ; // 发动机号不能为空
//			}else if(IsNull(ChkUtil.CHK_8124) && (vehicle.getVIN() == null || "".equals(vehicle.getVIN()))){
//				returnCode = ChkUtil.CHK_8124 ; // 车架号不可为空
//			}else 
			if(IsNull(ChkUtil.CHK_8320) && (vehicle.getLicensePlateNo() == null || "".equals(vehicle.getLicensePlateNo()))){
				returnCode = ChkUtil.CHK_8320 ; // 车辆信息不能为空
			}
//			else if(IsNull(ChkUtil.CHK_8158) && (vehicle.getNoLicenseFlag() == null || "".equals(vehicle.getNoLicenseFlag())
//					|| !ShuiKuanType.getNoLicense(vehicle.getNoLicenseFlag()))){
//				returnCode = ChkUtil.CHK_8158 ; // 未上牌车辆代码字段为空或为文档中“未上牌车辆代码”之外的数据，返回错误提示信息
//			}else if(IsNull(ChkUtil.CHK_8159) && ("1".equals(vehicle.getNoLicenseFlag()) && ((vehicle.getLicensePlateNo() != null 
//					|| "".equals(vehicle.getLicensePlateNo())) || (vehicle.getLicensePlateType() != null 
//					|| "".equals(vehicle.getLicensePlateType()))))){
//				returnCode = ChkUtil.CHK_8159 ; // 未上牌车辆代码字段为“1”时，传送车牌种类和车牌号码
//			
//			}
			return returnCode;
		}
		

	public String N_P_checking(Object o) {
		String returncode = ChkUtil.CHK_8008;
		TaxPayQueryReqInfo ddci = (TaxPayQueryReqInfo) o ;
		String Pwssword_f = ddci.getPassword();
		String Pwssword_l = CfgLoader.getConfigValue("SysParam", "Password");
		String Username_f = ddci.getUserName();
		String Username_l = CfgLoader.getConfigValue("SysParam", "Username");
		// 用户名密码 是  null 默认为验证失败
		if(Username_f == null || Pwssword_f == null || "".equals(Username_f) || "".equals(Pwssword_f)){
			returncode = ChkUtil.CHK_8008 ;
		}else if(Username_f.equals(Username_l) && Pwssword_f.equals(Pwssword_l)){
			returncode = ChkUtil.CHK_0000 ;
		}else{
			returncode = ChkUtil.CHK_8008 ;
		}
		return returncode ;
	}
	
	public static boolean IsNull(String CHK_CODE){
		boolean flag = true ;
		if(CHK_CODE == null || "".equals(CHK_CODE.trim())){
			flag = false ;
		}
		return flag ;
	}
}
