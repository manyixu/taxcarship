package com.derun.taxconfirm.service;

import com.derun.beans.BaseConfirmReqInfo;
import com.derun.beans.BaseConfirmResInfo;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-2-27
 *
 * 投保确认服务接口
 * @version
 */
public interface TaxConfirmService {
	
	public BaseConfirmResInfo taxConfrim(BaseConfirmReqInfo baseconfirmReqinfo);

}
