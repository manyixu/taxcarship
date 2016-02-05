package com.derun.common.chk;

import com.derun.beans.ReconciliationReqInfo;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;

/**
 * @author MILI
 * @time 2014-3-7 9:10:44
 * @描述：对账用户名密码和入参有效性验证
 * */
public class ReconciliationChk {

	public String Join_valid(Object o,Object oo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String N_P_checking(Object o) {
		String returncode = ChkUtil.CHK_8008;
		ReconciliationReqInfo rci = (ReconciliationReqInfo) o ;
		String Pwssword_f = rci.getPassword();
		String Pwssword_l = CfgLoader.getConfigValue("SysParam", "Password");
		String Username_f = rci.getUserName();
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
