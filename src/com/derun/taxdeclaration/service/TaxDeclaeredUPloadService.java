package com.derun.taxdeclaration.service;

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.DeclareDateUploadResInfo;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-2-27
 *
 * 申报日期上传服务
 * @version
 */
public interface TaxDeclaeredUPloadService {
	
	public DeclareDateUploadResInfo[] taxDeclaeredUPload(DeclareDateUploadReqInfo declaredateuploadreq);

}
