package com.derun.taxquery.service;

import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.BaseQueryResInfo;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-2-27
 *
 * 投保查询服务接口
 * @version
 */
public interface TaxQueryService {
	
	public BaseQueryResInfo tarQuery(BaseQueryReqInfo basequeryreqinfo);

}
