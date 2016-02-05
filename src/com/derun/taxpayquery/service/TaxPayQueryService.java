package com.derun.taxpayquery.service;

import com.derun.beans.TaxPayQueryReqInfo;
import com.derun.beans.TaxPayQueryResInfo;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-2-27
 *
 * 纳税信息查询服务
 * @version
 */
public interface TaxPayQueryService {
	
	public TaxPayQueryResInfo taxPayQuery(TaxPayQueryReqInfo taxpayqueryReq);

}
