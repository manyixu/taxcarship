package com.derun.taxchangequery.service;

import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.BaseChangeQueryResInfo;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-2-27
 *
 * 变更查询服务接口
 * @version
 */
public interface TaxChangeQueryService {
	
	public BaseChangeQueryResInfo taxChangeQuery(BaseChangeQueryReqInfo basechangequeryreqinfo);

}
