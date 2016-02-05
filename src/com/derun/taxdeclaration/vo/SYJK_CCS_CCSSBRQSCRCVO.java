package com.derun.taxdeclaration.vo;

import java.util.Date;

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSSBRQSCRC;

/**
 * @author 郑艳英
 * 
 * @date 2014-5-13
 * 
 *       申报日期上传入参表封装类
 * @version
 */
public class SYJK_CCS_CCSSBRQSCRCVO {
   
	//封装申报日期入参信息
	public SYJK_CCS_CCSSBRQSCRC[] getDeclareDateUploadReq(DeclareDateUploadReqInfo declareDateUploadReqInfo){
		TaxDealCode_Type[] taxConfirmNos = declareDateUploadReqInfo.getTaxConfirmNo();
		SYJK_CCS_CCSSBRQSCRC[] ccssbrqscrcs = new SYJK_CCS_CCSSBRQSCRC[taxConfirmNos.length];
		for(int i = 0;i<taxConfirmNos.length;i++){
			SYJK_CCS_CCSSBRQSCRC ccssbrqscrc = new SYJK_CCS_CCSSBRQSCRC();
				 //申报时间
			Date DECLAREDATE = DateUtil.getStringDate(declareDateUploadReqInfo.getDeclareDate(),null) ;
			ccssbrqscrc.setDECLAREDATE(DECLAREDATE);
	         //操作员
			ccssbrqscrc.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));
			//税务机关编码
			ccssbrqscrc.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
			//系统采集日期
			ccssbrqscrc.setSJCJRQ(new Date());
			//系统采集方式
			ccssbrqscrc.setSJCJFS("0");
			//车船税变更确认码
			ccssbrqscrc.setTAXCHANGEQUERYNO(taxConfirmNos[i].getTaxDealCode_Type());
			//国标区域代码
			ccssbrqscrc.setAREACODE(declareDateUploadReqInfo.getAreaCode());
			//公司代码
			ccssbrqscrc.setCOMPANYCODE(declareDateUploadReqInfo.getCompanyCode());
			//车船税确认码
			ccssbrqscrc.setLISTRESPECTIVEANNUAL(taxConfirmNos[i].getTaxDealCode_Type());
			ccssbrqscrcs[i] = ccssbrqscrc;
			}
		return ccssbrqscrcs;
	}
}
