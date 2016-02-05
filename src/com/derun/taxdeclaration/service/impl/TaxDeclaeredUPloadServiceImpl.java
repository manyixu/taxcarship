package com.derun.taxdeclaration.service.impl;

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.DeclareDateUploadResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.SBDZUtil;
import com.derun.taxdeclaration.dao.DeclareDateUploadDAO;
import com.derun.taxdeclaration.service.TaxDeclaeredUPloadService;
import com.derun.taxdeclaration.vo.DateUpLoadThread;
import com.derun.taxdeclaration.vo.InsertDateUpLoadReqThread;
import com.derun.taxdeclaration.vo.InsertDateUpLoadResThread;

/**
 * @author 郑艳英
 * 
 * @date 2014-5-13
 * 
 *       申报日期上传服务
 * @version
 */
public class TaxDeclaeredUPloadServiceImpl implements TaxDeclaeredUPloadService {
	@Override
	public DeclareDateUploadResInfo[] taxDeclaeredUPload(
			DeclareDateUploadReqInfo declaredateuploadreq) {
		String username=null;
		String password=null;
		//读数据库配置，获取用户名密码
		String localusr =  CfgLoader.getConfigValue("SysParam", "Username");
		String localpwd =  CfgLoader.getConfigValue("SysParam", "Password");
		username = declaredateuploadreq.getUserName();
		password = declaredateuploadreq.getPassword();
		DeclareDateUploadResInfo[] decDURespArray = new DeclareDateUploadResInfo[1];
		DeclareDateUploadResInfo declaredateUploadRes = new DeclareDateUploadResInfo();
		DeclareDateUploadDAO uploadDao = new DeclareDateUploadDAO();
		if((username==null||password==null)||(username.equals(localusr)==false)||localpwd.equals(localpwd)==false){
			DeclareDateUploadResInfo dduResInfo = new DeclareDateUploadResInfo();
			dduResInfo.setReturnCode("8008");
			decDURespArray = new DeclareDateUploadResInfo[1];//
			decDURespArray[0] = dduResInfo;
			return decDURespArray;
		}
		else 
		{   
			if(declaredateuploadreq.getTaxConfirmNo()!=null){
			TaxDealCode_Type[] confirmNo = new TaxDealCode_Type[declaredateuploadreq.getTaxConfirmNo().length];
			confirmNo = declaredateuploadreq.getTaxConfirmNo();
			int count  = SBDZUtil.getSbCount();
			boolean flag = uploadDao.insertConfrim(declaredateuploadreq,count);
			if(flag==false){
				System.out.println("--------确认码插入到库里面有错误----");
				declaredateUploadRes.setTaxConfirmNo(new TaxDealCode_Type[0]);
				declaredateUploadRes.setReturnCode("1");
				decDURespArray[0] = declaredateUploadRes;
			}else{
			String [] NoExit = uploadDao.getDateUploadNotIn(declaredateuploadreq,count);
			uploadDao.deleteConfirm(count);
			if(NoExit.length!=0){
			TaxDealCode_Type[]  tType = new TaxDealCode_Type[NoExit.length];
			TaxDealCode_Type taxType = null;
			for(int k = 0;k<NoExit.length;k++ ){
				taxType = new TaxDealCode_Type();
				taxType.setTaxDealCode_Type(NoExit[k]);
				tType[k] = taxType;
			}
			declaredateUploadRes.setTaxConfirmNo(tType);
			declaredateUploadRes.setReturnCode("9");
			decDURespArray[0] = declaredateUploadRes;
			}else{
				declaredateUploadRes.setTaxConfirmNo(new TaxDealCode_Type[0]);
				declaredateUploadRes.setReturnCode("1");
				decDURespArray[0] = declaredateUploadRes;
			}
			}
			
			//启动更新数据库线程
			DateUpLoadThread upLoadThread= new DateUpLoadThread(declaredateuploadreq);
			Thread thread = new Thread(upLoadThread);
			thread.start();
			//启动入参入库线程
			InsertDateUpLoadReqThread insertDateUpLoadReqThread = new InsertDateUpLoadReqThread(declaredateuploadreq);
			Thread th1 = new Thread(insertDateUpLoadReqThread);
            th1.start();		
            //启动出入入库线程
			InsertDateUpLoadResThread insertDateUpLoadResThread = new InsertDateUpLoadResThread(declaredateUploadRes);
			Thread th2 = new Thread(insertDateUpLoadResThread);
            th2.start();	
			}
		}
		return decDURespArray;
	}

}
