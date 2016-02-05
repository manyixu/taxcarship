package com.derun.taxdeclaration.vo;

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.taxdeclaration.dao.DeclareDateUploadDAO;

public class DateUpLoadThread implements Runnable{
	private DeclareDateUploadReqInfo decDUreq;
    

	public DateUpLoadThread(DeclareDateUploadReqInfo decDUreq){
		this.decDUreq = decDUreq;
	}
	public void run() {
		long startTime = System.currentTimeMillis();
		DeclareDateUploadDAO uploadDao = new DeclareDateUploadDAO();
		TaxDealCode_Type[] confirmNo = new TaxDealCode_Type[decDUreq.getTaxConfirmNo().length];
		confirmNo = decDUreq.getTaxConfirmNo();
//		boolean flag = uploadDao.updateDeclaredStatusChange(confirmNo);
		boolean flag1= uploadDao.updateDeclaredStatus(confirmNo);
		if(flag1==true){
			long endTime = System.currentTimeMillis();
			long newTime = endTime - startTime;
			System.out.println("----打印出程序更新时间 ： " + newTime);
//			System.out.println("申报状态更新成功！！！！！！！！");
			}else{
//			System.out.println("申报状态更新失败！！！！！！！！！");
			}
//		    System.out.println("更新状态线程结束————————————————————————————————————————————");
		
		}
	    
}		
		
		
//		String[] sqlt = sql.split("--");
//		for(int i=0;i<sqlt.length;i++){
//		boolean flag = uploadDao.updateDeclaredStatus(sqlt[i]);
//		if(flag==true){
//			System.out.println("申报状态更新成功！！！！！！！！");
//		}else{
//			System.out.println("申报状态更新失败！！！！！！！！！");
//		}
//		}
		
//	}


