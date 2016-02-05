package com.derun.taxdeclaration.vo;

import com.derun.beans.DeclareDateUploadResInfo;
import com.derun.model.po.SYJK_CCS_CCSSBRQSCCC;
import com.derun.taxdeclaration.dao.impl.TaxDeclaeredUPloadServiceDao;

/**
 * 保存申报日期上传服务出参信息线程
 * @author 郑艳英
 *
 */
public class InsertResThread implements Runnable{
	
	private DeclareDateUploadResInfo declareDateUploadResInfo;
	public InsertResThread(DeclareDateUploadResInfo declareDateUploadResInfo){
		this.declareDateUploadResInfo=declareDateUploadResInfo;
	}

	@Override
	public void run() {
		TaxDeclaeredUPloadServiceDao taxDeclaeredUPloadServiceDao = new TaxDeclaeredUPloadServiceDao();
		boolean flg = taxDeclaeredUPloadServiceDao.saveTaxDeclaeredUPloadRes(declareDateUploadResInfo);
		if(flg){
			System.out.println("保存出参信息成功");
		}else{
		  System.out.println("保存出参信息失败");
		}
	}

}
