package com.derun.trace.thread;

import java.util.HashMap;

import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.BaseChangeQueryResInfo;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.trace.dao.TraceDao;

/**
 *  变更查询轨迹线程
 * @author 郑艳英
 *
 */
public class TaxChangeQueryServiceTraceThread implements Runnable{

	
	  HashMap hm;
		
		public TaxChangeQueryServiceTraceThread(HashMap mmap){
			
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
		 BaseChangeQueryReqInfo basechangequeryreqinfo = (BaseChangeQueryReqInfo) hm.get("Req");
		 BaseChangeQueryResInfo basechangequeryresinfo = (BaseChangeQueryResInfo) hm.get("Res");
		 //写轨迹表
		 String vin = null;
		 String engineNo = null;
		 String licensePlateNo = null;
		 String licensePlateType = null;
		 if(StringUtil.isEmpty(carId)){
			 vin = basechangequeryreqinfo.getVehicleInfo().getVIN();
			 engineNo = basechangequeryreqinfo.getVehicleInfo().getEngineNo();
			 licensePlateNo = basechangequeryreqinfo.getVehicleInfo().getLicensePlateNo();
			 licensePlateType = basechangequeryreqinfo.getVehicleInfo().getLicensePlateType();
			 //发动机号+vin + 号牌号码+号牌种类
			 carId = engineNo+ "," + vin+ "," + licensePlateNo + "," + licensePlateType;
		 }
		 String returnCode = basechangequeryresinfo.getReturnCode();
		 String taxchangeQueryNo = basechangequeryresinfo.getChangeQueryNo().getTaxDealCode_Type();
		 traceDao.saveTrace(returnCode, taxchangeQueryNo, carId, "20");
	}
 
}
