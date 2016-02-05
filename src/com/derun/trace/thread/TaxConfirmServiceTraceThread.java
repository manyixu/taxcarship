package com.derun.trace.thread;

import java.util.HashMap;
import java.util.List;

import com.derun.beans.BaseConfirmReqInfo;
import com.derun.beans.BaseConfirmResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.trace.dao.TraceDao;


/**
 * 投保确认轨迹线程
 * @author 郑艳英
 *
 */
public class TaxConfirmServiceTraceThread implements Runnable{
 
      HashMap hm;
	
	public TaxConfirmServiceTraceThread(HashMap mmap){
		
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
		 BaseConfirmReqInfo baseconfirmReqinfo = (BaseConfirmReqInfo) hm.get("Req");
		 BaseConfirmResInfo baseConfirmResInfo = (BaseConfirmResInfo) hm.get("Res");
		 List<Object> rkmxlist = (List<Object>) hm.get("rkmx");
		 SYJK_CCS_RKMX rkmx = null;
		 if(rkmxlist != null && rkmxlist.size() > 0){
			 rkmx = (SYJK_CCS_RKMX) rkmxlist.get(1);
		 }
		 //写轨迹表
		 String vin =null;
		 String engineNo = null;
		 String licensePlateNo = null;
		 String licensePlateType = null;
		 String taxConfrimNo = baseconfirmReqinfo.getTaxQueryNo()== null ? null :baseconfirmReqinfo.getTaxQueryNo().getTaxDealCode_Type();
		//carId为空的时候
		 if(StringUtil.isEmpty(carId)){
		 //入参中没有要从入库明细中取
		 if(baseconfirmReqinfo.getVehicleInfo()== null && rkmx!= null){
			 
			 vin= rkmx.getVIN();
			 engineNo = rkmx.getEngineNo();
			 licensePlateNo = rkmx.getHPHM();
			 licensePlateType=rkmx.getHPZL();
		 }else if(baseconfirmReqinfo.getVehicleInfo()== null && rkmx == null){
			 //入参和入库明细中都没有从查询出参中查
			 if(StringUtil.isNotEmpty(taxConfrimNo)){
				 SYJK_CCS_CCSCXCCJBXX cxcc = traceDao.getVehicleType(taxConfrimNo);
				 vin= cxcc.getVIN();
				 engineNo = cxcc.getENGINENO();
				 licensePlateNo = cxcc.getHPHM();
				 licensePlateType=cxcc.getHPZL();  
			 }
		
		 }else{
			 vin= baseconfirmReqinfo.getVehicleInfo().getVIN();
			 engineNo = baseconfirmReqinfo.getVehicleInfo().getEngineNo();
			 licensePlateNo = baseconfirmReqinfo.getVehicleInfo().getLicensePlateNo();
			 licensePlateType=baseconfirmReqinfo.getVehicleInfo().getLicensePlateType(); 
		 }
		 
		
			 //发动机号+vin + 号牌号码+号牌种类
			 carId = engineNo+ "," + vin+ ","
			 + licensePlateNo + "," + licensePlateType;
		 }
		 String returnCode = baseConfirmResInfo.getReturnCode();
		
		 traceDao.saveTrace(returnCode, taxConfrimNo, carId, "10");
	}
	
}
