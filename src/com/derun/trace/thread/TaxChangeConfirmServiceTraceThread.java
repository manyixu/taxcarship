package com.derun.trace.thread;

import java.util.HashMap;

import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.BaseChangeConfirmResInfo;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.trace.dao.TraceDao;

/**
 * 变更确认 轨迹线程
 * @author 郑艳英
 *
 */
public class TaxChangeConfirmServiceTraceThread implements Runnable{

	 HashMap hm;
		
		public TaxChangeConfirmServiceTraceThread(HashMap mmap){
			
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
		 BaseChangeConfirmReqInfo basechangeconfirmreqinfo = (BaseChangeConfirmReqInfo) hm.get("Req");
		 BaseChangeConfirmResInfo basechangeconfirmresinfo = (BaseChangeConfirmResInfo) hm.get("Res");
		 //写轨迹表
		 String vin = null;
		 String engineNo = null;
		 String licensePlateNo = null;
		 String licensePlateType = null;
		 if(StringUtil.isEmpty(carId)){
			 if(basechangeconfirmreqinfo.getVehicleInfo()==null){
				 SYJK_CCS_CCSBGCXCCJB bgcxcc =  traceDao.getBGVehicleType(basechangeconfirmreqinfo.getChangeQueryNo().getTaxDealCode_Type());
				  if(bgcxcc!=null){
					 vin = bgcxcc.getVIN();
					 engineNo = bgcxcc.getENGINENO();
					 licensePlateNo = bgcxcc.getHPHM();
					 licensePlateType = bgcxcc.getHPZL();
				  }
			 }
			 vin = basechangeconfirmreqinfo.getVehicleInfo().getVIN();
			 engineNo = basechangeconfirmreqinfo.getVehicleInfo().getEngineNo();
			 licensePlateNo = basechangeconfirmreqinfo.getVehicleInfo().getLicensePlateNo();
			 licensePlateType = basechangeconfirmreqinfo.getVehicleInfo().getLicensePlateType();
			 //发动机号+vin + 号牌号码+号牌种类
			 carId = engineNo+ "," + vin+ "," + licensePlateNo + "," + licensePlateType;
		 }
		 String returnCode = basechangeconfirmresinfo.getReturnCode();
		 String taxChangeConfirmNo = basechangeconfirmresinfo.getChangeConfirmNo().getTaxDealCode_Type();
		 traceDao.saveTrace(returnCode, taxChangeConfirmNo, carId, "30");
	}

}
