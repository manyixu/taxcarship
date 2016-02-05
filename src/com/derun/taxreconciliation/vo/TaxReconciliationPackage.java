package com.derun.taxreconciliation.vo;

import java.util.ArrayList;
import java.util.List;

import com.derun.beans.ReconciliationReqInfo;
import com.derun.beans.ReconciliationResInfo;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxDealCode_Type;
import com.derun.model.po.SYJK_CCS_CCSDZCCSXX;
import com.derun.model.po.SYJK_CCS_CCSDZRCJBXX;
import com.derun.taxreconciliation.dao.impl.TaxReconciliationServiceDao;

/**
 * 
 * @author 郑艳英
 * 对帐服务封装类
 */
public class TaxReconciliationPackage {
	
	TaxReconciliationServiceDao taxReconciliationServiceDao = new TaxReconciliationServiceDao();
	/**
	 * 封装返回对象
	 * @param taxReconciliationNo
	 * @param checkingType
	 * @param listType
	 * @param returnCode
	 * @return  ReconciliationResInfo
	 */
	public ReconciliationResInfo resParameter(TaxDealCode_Type taxReconciliationNo,String checkingType,List<TaxAmount_Type> listType,String returnCode){
		ReconciliationResInfo reconciliationResInfo = new ReconciliationResInfo();
		reconciliationResInfo.setTaxReconciliationNo(taxReconciliationNo);
		reconciliationResInfo.setCheckingType(checkingType);
		 TaxAmount_Type[] taxAmount_Type = null;
		if(listType != null ){
			taxAmount_Type= new TaxAmount_Type[listType.size()];
         for(int i=0;i<listType.size();i++)
         {
        	 taxAmount_Type [i]=listType.get(i); 
         }
		}
		reconciliationResInfo.setCheckingArray(taxAmount_Type);
		reconciliationResInfo.setReturnCode(returnCode);
		return reconciliationResInfo;
	}
    
	/**
	 * 封装入参出参信息
	 * @param reconciliationReqInfo
	 * @param reconciliationResInfo
	 * @param taxDealCode
	 * @return List<Object> 
	 */
	public List<Object> FZ(ReconciliationReqInfo reconciliationReqInfo,ReconciliationResInfo reconciliationResInfo,
			TaxDealCode_Type taxDealCode) {
		List<Object> list = new ArrayList<Object>();
		SYJK_CCS_CCSDZCCSXXVO cssdzccsxxvo = new SYJK_CCS_CCSDZCCSXXVO();
		SYJK_CCS_CCSDZRCJBXXVO ccsdzrcjbxxvo = new SYJK_CCS_CCSDZRCJBXXVO();
		SYJK_CCS_CCSDZRCJBXX[] ccsdzrcjbxx = ccsdzrcjbxxvo.getReconciliationReqInfo(reconciliationReqInfo, taxDealCode);
		SYJK_CCS_CCSDZCCSXX[] cssdzccsxx = cssdzccsxxvo.getReconciliationResInfo(reconciliationResInfo, taxDealCode);
		list.add(ccsdzrcjbxx);
		list.add(cssdzccsxx);
		return list;
	}
   
	/**
	 * 保存入参出参信息
	 * @param list
	 * @return boolean
	 */
	public boolean saveReqRes(List<Object> list) {
	  boolean flag = false;
	  boolean saveReq = false;
	  boolean saveRes = false;
	  SYJK_CCS_CCSDZRCJBXX[] ccsdzrcjbxx = (SYJK_CCS_CCSDZRCJBXX[]) list.get(0);
	  SYJK_CCS_CCSDZCCSXX[] cssdzccsxx = (SYJK_CCS_CCSDZCCSXX[]) list.get(1);
	  saveReq = taxReconciliationServiceDao.saveReconciliationReqInfo(ccsdzrcjbxx);
	  saveRes = taxReconciliationServiceDao.saveReconciliationResInfo(cssdzccsxx);  
     if(saveReq && saveRes){
    	 flag = true;
     }
		return flag;
	}
	
}
