package com.derun.taxchangeconfirm.service;

import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.BaseChangeConfirmResInfo;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-2-27
 *
 * 说明
 * @version
 */
public interface TaxChangeConfirmService {
	
	public BaseChangeConfirmResInfo taxChangeConfirm(BaseChangeConfirmReqInfo basechangeconfirmreqinfo);

}
