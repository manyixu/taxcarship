package com.derun.taxdeclaration.vo;

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.taxdeclaration.dao.DeclareDateUploadDAO;

public class InsertDateUpLoadReqThread implements Runnable{

	private DeclareDateUploadReqInfo req;
	
	public InsertDateUpLoadReqThread(DeclareDateUploadReqInfo req){
		this.req = req;
	}
	public void run() {
		DeclareDateUploadDAO uploadDao = new DeclareDateUploadDAO();
		uploadDao.insertDateUpLoadReq(req);
//		System.out.println("申报日期上传入参线程结束");
	}

}
