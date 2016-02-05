package com.derun.trace.thread;

import java.util.HashMap;

import com.derun.beans.TaxPayQueryReqInfo;
import com.derun.beans.TaxPayQueryResInfo;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.trace.dao.TraceDao;

/**
 * 纳税信息查询轨迹线程
 * @author 郑艳英
 *
 */
public class TaxPayQueryServiceTraceThread implements Runnable{
	   HashMap hm;
		
		public TaxPayQueryServiceTraceThread(HashMap mmap){
			
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
		 TraceDao traceDao = new TraceDao();
		 TaxPayQueryReqInfo taxpayqueryReq = (TaxPayQueryReqInfo) hm.get("Req");
		 TaxPayQueryResInfo taxPayQueryResInfo = (TaxPayQueryResInfo) hm.get("Res");
		 String taxConfirmNo = (String) hm.get("taxConfirmNo");
		 //写轨迹表
		 if(StringUtil.isEmpty(carId)){
			 //发动机号+vin + 号牌号码+号牌种类
			 carId = taxpayqueryReq.getVehicleInfo().getEngineNo()+ "," + taxpayqueryReq.getVehicleInfo().getVIN()+ ","
			 + taxpayqueryReq.getVehicleInfo().getLicensePlateNo() + "," + taxpayqueryReq.getVehicleInfo().getLicensePlateType();
		 }
		 String returnCode = taxPayQueryResInfo.getReturnCode();
		 
		 traceDao.saveTrace(returnCode, taxConfirmNo, carId, "50");
		
	}

}
