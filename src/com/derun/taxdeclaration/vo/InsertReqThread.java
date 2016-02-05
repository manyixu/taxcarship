package com.derun.taxdeclaration.vo;


import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.model.po.SYJK_CCS_CCSSBRQSCRC;
import com.derun.taxdeclaration.dao.impl.TaxDeclaeredUPloadServiceDao;
/**
 * 保存申报日期上传服务入参信息线程
 * @author 郑艳英
 *
 */
public class InsertReqThread implements Runnable {
	private DeclareDateUploadReqInfo declaredateuploadreq;
	public InsertReqThread(DeclareDateUploadReqInfo declaredateuploadreq){
		this.declaredateuploadreq = declaredateuploadreq;
	}
	@Override
	public void run() {
		TaxDeclaeredUPloadServiceDao taxDeclaeredUPloadServiceDao = new TaxDeclaeredUPloadServiceDao();
		boolean flg = taxDeclaeredUPloadServiceDao.saveTaxDeclaeredUPloadReq(declaredateuploadreq);
		if(flg){
			System.out.println("保存申报日期上传服务入参信息成功");
		}else{
			System.out.println("保存申报日期上传服务入参信息失败");
		}
	}

}
