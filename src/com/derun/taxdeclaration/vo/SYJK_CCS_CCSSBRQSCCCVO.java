package com.derun.taxdeclaration.vo;

import java.util.Date;

import com.derun.beans.DeclareDateUploadResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.init.CfgLoader;
import com.derun.model.po.SYJK_CCS_CCSSBRQSCCC;

/**
 * @author 郑艳英
 * 
 * @date 2014-5-13
 * 
 *       申报日期上传出参表封装类
 * @version
 */
public class SYJK_CCS_CCSSBRQSCCCVO {

	//封装申报日期出参信息
	public SYJK_CCS_CCSSBRQSCCC[] getDeclareDateUploadRes(DeclareDateUploadResInfo[] declareDateUploadResInfos,TaxDealCode_Type[] taxConfirmNos){
		SYJK_CCS_CCSSBRQSCCC[] ccssbrqscccs = new SYJK_CCS_CCSSBRQSCCC[taxConfirmNos.length];
		DeclareDateUploadResInfo declareDateUploadResInfo = declareDateUploadResInfos[0];
		if(taxConfirmNos!=null){
		String taxConfirmNo = "";
		for(int j = 0;j<taxConfirmNos.length;j++){
			SYJK_CCS_CCSSBRQSCCC  ccssbrqsccc = new SYJK_CCS_CCSSBRQSCCC();
			taxConfirmNo=taxConfirmNos[j].getTaxDealCode_Type();
			ccssbrqsccc.setRETURNCODE(declareDateUploadResInfo.getReturnCode());
			ccssbrqsccc.setLISTRESPECTIVEANNUAL(taxConfirmNo);
			ccssbrqsccc.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));
			ccssbrqsccc.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
			ccssbrqsccc.setSJCJRQ(new Date());
			ccssbrqsccc.setSJCJFS("0");
			ccssbrqscccs[j] = ccssbrqsccc;

		
	}
	}
	
	
	return ccssbrqscccs;
	}
}
