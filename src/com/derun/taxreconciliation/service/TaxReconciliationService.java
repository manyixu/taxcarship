package com.derun.taxreconciliation.service;

import com.derun.beans.ReconciliationReqInfo;
import com.derun.beans.ReconciliationResInfo;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-2-27
 *
 * 对账服务接口
 * @version
 */
public interface TaxReconciliationService {

	public ReconciliationResInfo taxReconciliation(ReconciliationReqInfo reconciliationreqinfo);

}
