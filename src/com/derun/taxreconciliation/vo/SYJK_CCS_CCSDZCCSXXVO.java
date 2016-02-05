package com.derun.taxreconciliation.vo;

import java.util.Date;

import com.derun.beans.ReconciliationResInfo;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.init.CfgLoader;
import com.derun.model.po.SYJK_CCS_CCSDZCCSXX;

/**
 * 对账出参信息封装类
 * @author 郑艳英
 * 2014.5.15
 */
public class SYJK_CCS_CCSDZCCSXXVO {
	/**
	 * 封闭对账出参信息
	 * @param reconciliationResInfo
	 * @param taxDealCode
	 * @return SYJK_CCS_CCSDZCCSXX[]
	 */
	public SYJK_CCS_CCSDZCCSXX[] getReconciliationResInfo(ReconciliationResInfo reconciliationResInfo,TaxDealCode_Type taxDealCode){
		
		TaxAmount_Type[] taxAmount_Type = reconciliationResInfo.getCheckingArray();
		SYJK_CCS_CCSDZCCSXX[] cssdzccsxxs = new SYJK_CCS_CCSDZCCSXX[taxAmount_Type.length];
		for(int i = 0;i<taxAmount_Type.length;i++){
		SYJK_CCS_CCSDZCCSXX cssdzccsxx = new SYJK_CCS_CCSDZCCSXX();
		cssdzccsxx.setTAXRECONCILIATIONNO(reconciliationResInfo.getTaxReconciliationNo().getTaxDealCode_Type());
		cssdzccsxx.setCHECKINGTYPE(reconciliationResInfo.getCheckingType());
		cssdzccsxx.setTAXDEALCODE(taxDealCode.getTaxDealCode_Type());
		cssdzccsxx.setTAXAMOUNT_FLAG(taxAmount_Type[i].getTaxAmount_Flag());
		cssdzccsxx.setANNUALTAXDUE(taxAmount_Type[i].getAnnualTaxDue());
		cssdzccsxx.setSUMTAXDEFAULT(taxAmount_Type[i].getSumTaxDefault());
		cssdzccsxx.setSUMOVERDUE(taxAmount_Type[i].getSumOverdue());
		cssdzccsxx.setSUMTAX(taxAmount_Type[i].getSumTax());
		cssdzccsxx.setRETURNCODE(reconciliationResInfo.getReturnCode());
		cssdzccsxx.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));
		cssdzccsxx.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
		cssdzccsxx.setSJCJRQ(new Date());
		cssdzccsxx.setSJCJFS("0");
		cssdzccsxxs[i] = cssdzccsxx;
		}
		return cssdzccsxxs;
	}
}
