package com.derun.taxdeclaration.vo;

import java.util.ArrayList;
import java.util.List;

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.DeclareDateUploadResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.model.po.SYJK_CCS_CCSSBRQSCCC;
import com.derun.model.po.SYJK_CCS_CCSSBRQSCRC;
import com.derun.taxdeclaration.dao.impl.TaxDeclaeredUPloadServiceDao;

/**
 * 
 * @author 郑艳英
 * 申报日期封装
 */
public class TaxDeclareDataPackage {
	TaxDeclaeredUPloadServiceDao taxDeclaeredUPloadServiceDao = new TaxDeclaeredUPloadServiceDao();
	//封装返回出参
	public DeclareDateUploadResInfo[] resParameter(String returnCode,TaxDealCode_Type[] taxConfirmNo){
		DeclareDateUploadResInfo[] declaredateUploadResInfos = new DeclareDateUploadResInfo[1];
		DeclareDateUploadResInfo declaredateUploadResInfo = new DeclareDateUploadResInfo();
//		if(taxConfirmNo != null && taxConfirmNo.length>0){
//			declaredateUploadResInfos = new DeclareDateUploadResInfo[taxConfirmNo.length];
//			for(int i = 0 ; i < taxConfirmNo.length ;i++){
//				declaredateUploadResInfo.setTaxConfirmNo(new TaxDealCode_Type[0]);
//				declaredateUploadResInfo.setReturnCode(returnCode);
//				declaredateUploadResInfos[i] = declaredateUploadResInfo;
//			}
//		}else{
//			declaredateUploadResInfo.setTaxConfirmNo(new TaxDealCode_Type[0]);
//			declaredateUploadResInfo.setReturnCode(returnCode);
//			declaredateUploadResInfos[0] = declaredateUploadResInfo;
//		}
		if(("9").equals(returnCode)){
			declaredateUploadResInfo.setTaxConfirmNo(taxConfirmNo);
		}else{
			declaredateUploadResInfo.setTaxConfirmNo(new TaxDealCode_Type[0]);
		}
		
		declaredateUploadResInfo.setReturnCode(returnCode);
		declaredateUploadResInfos[0] = declaredateUploadResInfo;
		return declaredateUploadResInfos;
	}

	//封装入参出参信息
	public List<Object> FZ(DeclareDateUploadReqInfo declaredateuploadreq,DeclareDateUploadResInfo[] declareDateUploadResInfos,TaxDealCode_Type[] taxConfirmNo) {
		List<Object> list = new ArrayList<Object>();
		SYJK_CCS_CCSSBRQSCRCVO ccssbrqscrcVO = new SYJK_CCS_CCSSBRQSCRCVO();
		SYJK_CCS_CCSSBRQSCCCVO ccssbrqscccVO = new SYJK_CCS_CCSSBRQSCCCVO();
		SYJK_CCS_CCSSBRQSCRC[] ccssbrqscrcs = ccssbrqscrcVO.getDeclareDateUploadReq(declaredateuploadreq);
		SYJK_CCS_CCSSBRQSCCC[] ccssbrqscccs = ccssbrqscccVO.getDeclareDateUploadRes(declareDateUploadResInfos,taxConfirmNo);
		list.add(ccssbrqscrcs);
		list.add(ccssbrqscccs);
		return list;
	}
	//保存申报日期上传的入参信息和出参信息,更新入库明细
  public boolean saveReqRes( List<Object> list,DeclareDateUploadReqInfo declaredateuploadreq){
		boolean flag = false;
		SYJK_CCS_CCSSBRQSCRC[] ccssbrqscrcs = (SYJK_CCS_CCSSBRQSCRC[]) list.get(0);
		SYJK_CCS_CCSSBRQSCCC[] ccssbrqscccs = (SYJK_CCS_CCSSBRQSCCC[]) list.get(1);
		//更新入库明细细线程
		UpdateRKMXThread updateRKMXThread = new UpdateRKMXThread(declaredateuploadreq);
		Thread rkmxThread = new Thread(updateRKMXThread);
		rkmxThread.start();
		//2014-11-18修改申报日期上传超时
//		//保存入参线程    
//		InsertReqThread insertReqThread = new InsertReqThread(ccssbrqscrcs);
//		Thread reqThread = new Thread(insertReqThread);
//		reqThread.start();
//		//保存出参线程
//		InsertResThread InsertResThread = new InsertResThread(ccssbrqscccs);
//		Thread resThread = new Thread(InsertResThread);
//		resThread.start();
	  return flag;
  }
  
  
  //保存入参信息,更新入库明细
  public boolean saveReq(List<Object> list,DeclareDateUploadReqInfo declaredateuploadreq) {
		boolean saveReq = false;
		boolean updateRKMX = false;
		boolean flag = false;
		TaxDealCode_Type[] confirmNo = new TaxDealCode_Type[declaredateuploadreq.getTaxConfirmNo().length];
		confirmNo = declaredateuploadreq.getTaxConfirmNo();
		SYJK_CCS_CCSSBRQSCRC[] ccssbrqscrcs = (SYJK_CCS_CCSSBRQSCRC[]) list.get(0);
		updateRKMX = taxDeclaeredUPloadServiceDao.updateRkMX(confirmNo);
		saveReq = taxDeclaeredUPloadServiceDao.saveTaxDeclaeredUPloadReq(ccssbrqscrcs);
		
		if(updateRKMX && saveReq){
			flag = true;
		}
		
	    return flag;
	} 

}
