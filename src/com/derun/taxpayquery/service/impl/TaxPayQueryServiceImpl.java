package com.derun.taxpayquery.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.derun.beans.TaxPayQueryReqInfo;
import com.derun.beans.TaxPayQueryResInfo;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.chk.TaxPayQueryChk;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.LogUtil;
import com.derun.taxpayquery.check.TaxPayQueryTax;
import com.derun.taxpayquery.service.TaxPayQueryService;
import com.derun.taxpayquery.vo.TaxPayQueryPackage;
import com.derun.trace.thread.TaxConfirmServiceTraceThread;
import com.derun.trace.thread.TaxPayQueryServiceTraceThread;

/**
 * @author	郑艳英
 *
 * @date	2014-5-5
 *
 * 车船税纳税信息查询服务
 * @version
 */
public class TaxPayQueryServiceImpl implements TaxPayQueryService {
	LogUtil logUtil = new LogUtil("纳税信息查询服务");
	@Override
 	public TaxPayQueryResInfo taxPayQuery(TaxPayQueryReqInfo taxpayqueryReq) {
		logUtil.startLog();
		// 车船税纳税信息查询服务上传服务--请求信息对象出参
		TaxPayQueryResInfo taxPayQueryResInfo = null;
		//纳税信息查询用户名密码和入参有效性验证
		TaxPayQueryChk taxPayQueryChk = new TaxPayQueryChk();
		TaxPayQueryTax taxPayQueryTax = new TaxPayQueryTax();
		//车船税纳税信息查询封装
		TaxPayQueryPackage taxPayQueryPackage = new TaxPayQueryPackage();
		//登录用户校验
		String loginChk = taxPayQueryChk.N_P_checking(taxpayqueryReq);
		String taxConfirmNo = null;
		if(ChkUtil.CHK_0000.equals(loginChk)){
			//登录用户有效,进行入参有效性校验
			if(taxpayqueryReq.getVehicleInfo()!=null){
//			String RCChek = taxPayQueryChk.Join_valid(taxpayqueryReq,null );
//			if(!ChkUtil.CHK_0000.equals(RCChek)){
				//传入的入参信息不对,直接返回
//				taxPayQueryResInfo = taxPayQueryPackage.resParameter(null, null, RCChek);
//			}else{
				//入参信息有效,在入库明细中查询纳税信息
				Map<Object, Object> rkmxMap = taxPayQueryTax.getRKMXList(taxpayqueryReq);
				if(rkmxMap != null && rkmxMap.size() > 0){
					taxConfirmNo = (String) rkmxMap.get("TAXCONFIRMNO");
					Tax_Type taxType = (Tax_Type) rkmxMap.get("Tax_Type");
				    String 	returnCode = (String) rkmxMap.get("RETURNCODE");
				    String 	calcateFlag = (String) rkmxMap.get("CALCTAXFLAG");
					String declaredStatus = (String) rkmxMap.get("DECLAREDSTATUS");
					taxPayQueryResInfo = taxPayQueryPackage.resParameter(taxType, declaredStatus, returnCode,calcateFlag);
					//封装
				    List<Object> list = taxPayQueryPackage.FZ(taxPayQueryResInfo, taxpayqueryReq);
					//写库
				   boolean flag = taxPayQueryPackage.saveReqRes(list);
				   if(flag == false){
					   taxPayQueryResInfo = taxPayQueryPackage.resParameter(null, null, ChkUtil.CHK_8000);
				   }
				}else{
					//没有信息,直接返回
					taxPayQueryResInfo = taxPayQueryPackage.resParameter(null, null, ChkUtil.CHK_8702);
				}
			//}
			}else{
				//车辆信息为空直接返回8702
				taxPayQueryResInfo = taxPayQueryPackage.resParameter(null, null, ChkUtil.CHK_8702);
			}
		}else{
			//用户校验失败,直接返回
			taxPayQueryResInfo = taxPayQueryPackage.resParameter(null, null, loginChk);
		}
		
		// 事后处理（冲正、）
// 		HashMap mmap = new HashMap();
// 		mmap.put("Req", taxpayqueryReq);
// 		mmap.put("Res", taxPayQueryResInfo);
// 		mmap.put("taxConfirmNo", taxConfirmNo);
// 		TaxPayQueryServiceTraceThread asd = new TaxPayQueryServiceTraceThread(mmap);
// 	    Thread tt = new Thread(asd);
// 	    tt.start();
		logUtil.endLog();
		return taxPayQueryResInfo;
	}
	
	
	
	
	public static void test(){
		TaxPayQueryReqInfo taxpayqueryReq = new TaxPayQueryReqInfo();
		taxpayqueryReq.setUserName("admin123");
		taxpayqueryReq.setPassword("123456");
		Vehicle_Type vehicleInfo = new Vehicle_Type();
		vehicleInfo.setVIN("1abIBMC123s367101");
		vehicleInfo.setLicensePlateNo("津YK5CD");
		vehicleInfo.setLicensePlateType("01");
		vehicleInfo.setEngineNo("E24FCB00014");
		taxpayqueryReq.setVehicleInfo(vehicleInfo);
		TaxPayQueryServiceImpl t = new TaxPayQueryServiceImpl();
		TaxPayQueryResInfo tpqResp = t.taxPayQuery(taxpayqueryReq);
		Tax_Type taxInfo = tpqResp.getTaxInfo();
		String status = tpqResp.getDeclaredStatus();
		String rtncode = tpqResp.getReturnCode();
		System.out.println("taxInfo == "+taxInfo);
		System.out.println("status == "+status);
		System.out.println("rtncode == "+rtncode);
	}
	public static void main(String[] args)
	{
		test();
  }
}
