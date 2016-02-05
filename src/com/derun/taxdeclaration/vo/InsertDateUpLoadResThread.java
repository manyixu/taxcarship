package com.derun.taxdeclaration.vo;

import com.derun.beans.DeclareDateUploadResInfo;
import com.derun.taxdeclaration.dao.DeclareDateUploadDAO;

public class InsertDateUpLoadResThread implements Runnable{
    
	private DeclareDateUploadResInfo res;
	
	public InsertDateUpLoadResThread(DeclareDateUploadResInfo res){
		this.res = res;
	}
	
	public void run() {
		DeclareDateUploadDAO uploadDao = new DeclareDateUploadDAO();	
		uploadDao.insertDateUpLoadRes(res);
//		System.out.println("申报日期出参入库线程结束------------------");
	}

}
