package com.derun.common.chk;

import java.util.Date;
import java.util.List;

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.DateUtil;
import com.derun.model.po.TaxConfirmno_CHK;
import com.derun.taxchangequery.dao.impl.TaxChangeQueryDAO_Common;

/**
 * @author MILI
 * @time 2014-3-7 9:10:44
 * @描述：申报日期上传用户名密码和入参有效性验证
 * */
public class DeclareDateChk {
	TaxChangeQueryDAO_Common taxchangeDao = new TaxChangeQueryDAO_Common();	
	public String Join_valid(Object o,Object oo) {
		String returnCode = ChkUtil.CHK_0000;
		DeclareDateUploadReqInfo declareDateUploadReqInfo = (DeclareDateUploadReqInfo)o;
		TaxDealCode_Type[] taxConfirmNo = declareDateUploadReqInfo.getTaxConfirmNo();
		if(null == declareDateUploadReqInfo.getAreaCode() || declareDateUploadReqInfo.getAreaCode().length() != 6){
			return returnCode = ChkUtil.CHK_8347; //国标地区代码不合法
		}else if(null == declareDateUploadReqInfo.getCompanyCode() || declareDateUploadReqInfo.getCompanyCode().length() != 4){
			return returnCode = ChkUtil.CHK_8348; //公司代码不合法

		}else if(null == declareDateUploadReqInfo.getDeclareDate() || !declareDateUploadReqInfo.getDeclareDate().equals(DateUtil.getStringDate(new Date(), "yyyy-MM-dd"))){
			return returnCode = ChkUtil.CHK_8469; //申报日期不一致
		}else{
			for(int i = 0 ;i<taxConfirmNo.length;i++){
				if(taxConfirmNo[i]==null){
					return returnCode = ChkUtil.CHK_8311; //无效的确认码  
				}
				if(taxConfirmNo[i].getTaxDealCode_Type().equals("")){
					return returnCode = ChkUtil.CHK_8311; //无效的确认码
				}	
				List<TaxConfirmno_CHK> tax_chk = taxchangeDao.checkConfirm(taxConfirmNo[i].getTaxDealCode_Type());
				if(tax_chk.isEmpty()){
					return returnCode = ChkUtil.CHK_8311; //无效的确认码
				}
				for(TaxConfirmno_CHK taxChk : tax_chk ){
					if("R".equals(taxChk.getTAXCONDITIONCODE())){
						return returnCode = ChkUtil.CHK_8311; //无效的确认码
					}
				}
			}
			
			
		}
		return returnCode;
	}

	public String N_P_checking(Object o) {
		String returncode = ChkUtil.CHK_8008;
		DeclareDateUploadReqInfo ddci = (DeclareDateUploadReqInfo) o ;
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

}
