package com.derun.taxreconciliation.vo;

import java.util.Date;

import com.derun.beans.ReconciliationReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.init.CfgLoader;
import com.derun.model.po.SYJK_CCS_CCSDZRCJBXX;

/**
 * 对帐入参信息封装类
 * @author 郑艳英
 *  2014.5.15
 */
public class SYJK_CCS_CCSDZRCJBXXVO {
	/**
	 * 封装对帐入参信息
	 * @param reconciliationReqInfo
	 * @param taxDealCode
	 * @return SYJK_CCS_CCSDZRCJBXX[]
	 */
	public SYJK_CCS_CCSDZRCJBXX[] getReconciliationReqInfo(ReconciliationReqInfo reconciliationReqInfo,TaxDealCode_Type taxDealCode ){
		TaxDealCode_Type[] taxDealCode_Type = reconciliationReqInfo.getTaxConfirmNo();
		SYJK_CCS_CCSDZRCJBXX[] ccsdzrcjbxxs = new SYJK_CCS_CCSDZRCJBXX[taxDealCode_Type.length];
		String taxConfirmNo = "";
		
		for(int i = 0;i<taxDealCode_Type.length;i++){
			taxConfirmNo = taxDealCode_Type[i].getTaxDealCode_Type();
			SYJK_CCS_CCSDZRCJBXX ccsdzrcjbxx = new SYJK_CCS_CCSDZRCJBXX();
			ccsdzrcjbxx.setTAXRECONCILIATIONNO(taxConfirmNo);
			ccsdzrcjbxx.setCHECKINGTYPE(reconciliationReqInfo.getCheckingType());
			ccsdzrcjbxx.setTAXDEALCODE(taxDealCode.getTaxDealCode_Type());
			ccsdzrcjbxx.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));
			ccsdzrcjbxx.setSJCJRQ(new Date());
			ccsdzrcjbxx.setSJCJFS("0");
			ccsdzrcjbxxs[i] = ccsdzrcjbxx;
		}
		
		return ccsdzrcjbxxs;
	}
	
}
