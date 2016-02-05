package com.derun.taxchangequery.dao.impl;

import java.util.List;

import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.BaseChangeQueryResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.model.po.Car_Id_No;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-3-18 08:08:50
 * @描述：批改查询 封装写库类方法规范
 * */
public interface TaxChangeQueryDao_IF {
	/**
	 * 描述：封装写库对象
	 * 
	 * @入参：SYJK_CCS_CCSXX 、
	 **/
	public List<Object> Packaging(BaseChangeQueryReqInfo bcqri,
			BaseChangeQueryResInfo bcqrs, TaxDealCode_Type ChangeQueryNo,
			SYJK_CCS_RKMX rkmx, Tax_Type taxInfo, String returncode,
			String tbqrm,Car_Id_No cin,String isInsert);

	/**
	 * 描述：写库
	 **/
	public boolean Write_Bank(List<Object> list);

	/**
	 * 描述：封装返回出参
	 * */
	public BaseChangeQueryResInfo CC_Object(
			BaseChangeQueryReqInfo basechangequeryreqinfo,
			TaxDealCode_Type type, String calcTaxFlag, String returnCode,
			Tax_Type taxInfo, Car_Id_No cin);
}
