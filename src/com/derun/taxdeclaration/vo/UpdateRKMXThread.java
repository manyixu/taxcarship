package com.derun.taxdeclaration.vo;

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.taxdeclaration.dao.impl.TaxDeclaeredUPloadServiceDao;

/**
 * 修改入库明细状态线程
 * @author 郑艳英
 *
 */
public class UpdateRKMXThread implements Runnable{
	
	private DeclareDateUploadReqInfo declaredateuploadreq;
	public UpdateRKMXThread(DeclareDateUploadReqInfo declaredateuploadreq){
		this.declaredateuploadreq = declaredateuploadreq;
		
	}

	@Override
	public void run() {
		TaxDeclaeredUPloadServiceDao taxDeclaeredUPloadServiceDao = new TaxDeclaeredUPloadServiceDao();
		TaxDealCode_Type[] confirmNo = new TaxDealCode_Type[declaredateuploadreq.getTaxConfirmNo().length];
		confirmNo = declaredateuploadreq.getTaxConfirmNo();
		boolean updateRkmx = taxDeclaeredUPloadServiceDao.updateRkMX(confirmNo);
		if(updateRkmx){
			System.out.println("修改入库明细状态成功");
		}else{
			System.out.println("修改入库明细状态失败");
		}
	}

}
