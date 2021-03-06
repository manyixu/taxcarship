package com.derun.common.chk;

import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;
/**
 * @author MILI
 * @time 2014-4-29 
 * @描述：注销服务用户名密码验证
 * */
public class LoggedOutChk {

	public String Join_valid(Object o,Object oo) {
		return null;
	}
	public String N_P_checking(Object o) {
		String returncode = ChkUtil.CHK_8008;
		BaseChangeConfirmReqInfo bqci = (BaseChangeConfirmReqInfo) o ;
		String Pwssword_f = bqci.getPassword();
		String Pwssword_l = CfgLoader.getConfigValue("SysParam", "Password");
		String Username_f = bqci.getUserName();
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

}
