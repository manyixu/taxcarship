package com.client;

import java.util.HashMap;

import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.BaseQueryResInfo;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.trace.dao.TraceDao;



/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-5-5
 *
 * 说明
 * @version
 */
public class AfterServiceDealer implements Runnable {
	
	HashMap hm;
	
	public AfterServiceDealer(HashMap mmap){
		
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
		 BaseQueryReqInfo basequeryreqinfo =  (BaseQueryReqInfo) hm.get("Req");
		 BaseQueryResInfo baseQueryResInfo = (BaseQueryResInfo) hm.get("Res");
		 if(StringUtil.isEmpty(carId)){
			 //发动机号+vin + 号牌号码+号牌种类
			 carId = basequeryreqinfo.getVehicleInfo().getEngineNo()+"," + basequeryreqinfo.getVehicleInfo().getVIN()+","
			 + basequeryreqinfo.getVehicleInfo().getLicensePlateNo()+ "," + basequeryreqinfo.getVehicleInfo().getLicensePlateType();
		 }
		 String returnCode = baseQueryResInfo.getReturnCode();
		 String taxQueryNo = baseQueryResInfo.getTaxQueryNo() == null ? "" :baseQueryResInfo.getTaxQueryNo().getTaxDealCode_Type();
		 traceDao.saveTrace(returnCode,taxQueryNo,carId,"00");
		  
	}

}
