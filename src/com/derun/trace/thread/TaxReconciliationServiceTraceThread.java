package com.derun.trace.thread;

import java.util.HashMap;

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.DeclareDateUploadResInfo;
import com.derun.beans.ReconciliationReqInfo;
import com.derun.beans.ReconciliationResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.taxdeclaration.dao.impl.TaxDeclaeredUPloadServiceDao;
import com.derun.trace.dao.TraceDao;

/**
 *  对账轨迹线程
 * @author 郑艳英
 *
 */
public class TaxReconciliationServiceTraceThread implements Runnable{

	  HashMap hm;
		
		public TaxReconciliationServiceTraceThread(HashMap mmap){
			
			this.hm = mmap;

		}
	
	
	@Override
	public void run() {
		//调用旧版本投保查询服务，比较新处理结果
		//ServiceInvoker si = new TaxQueryInvoker();
		//si.invokeService(hm);
		
		//数据冲正
		
		//数据同步（写库、将监控数据放内存）
		 String carId = null;	//同步车辆信息表
		 //写轨迹表
		 TraceDao traceDao = new TraceDao();
		 String tmp = null;
		 ReconciliationReqInfo reconciliationReqInfo = (ReconciliationReqInfo) hm.get("Req");
		 ReconciliationResInfo reconciliationResInfo = (ReconciliationResInfo) hm.get("Res");
		 TaxDeclaeredUPloadServiceDao taxDeclaeredUPloadServiceDao = new TaxDeclaeredUPloadServiceDao();
		 String returnCode = null;
		 String taxchangeQueryNo =null;
		 TaxDealCode_Type[] taxDealCode_Type = reconciliationReqInfo.getTaxConfirmNo();
		 for(int h = 0;h<taxDealCode_Type.length;h++){
			 taxchangeQueryNo = taxDealCode_Type[h].getTaxDealCode_Type();
			 if(StringUtil.isEmpty(carId)){
				 SYJK_CCS_RKMX rkmx = taxDeclaeredUPloadServiceDao.getVehicleType(taxchangeQueryNo);
				 if(rkmx!=null){
				 String vin = rkmx.getVIN();
				 String engineNo = rkmx.getEngineNo();
				 String licensePlateNo = rkmx.getHPHM();
				 String licensePlateType = rkmx.getHPZL();
				 
				 //发动机号+vin + 号牌号码+号牌种类
				 carId = engineNo+ "," + vin+ ","
				 + licensePlateNo + "," + licensePlateType;
				 }
				 }
				
			    returnCode = reconciliationResInfo.getReturnCode();
				
				 traceDao.saveTrace(returnCode, taxchangeQueryNo, carId, "40");
				 carId = null;
		 }
		 
		 }
	

}
