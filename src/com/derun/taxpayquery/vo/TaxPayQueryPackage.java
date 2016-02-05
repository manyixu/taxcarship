package com.derun.taxpayquery.vo;

import java.util.ArrayList;
import java.util.List;

import com.derun.beans.TaxPayQueryReqInfo;
import com.derun.beans.TaxPayQueryResInfo;
import com.derun.beans.Tax_Type;
import com.derun.model.po.SYJK_CCS_CCSNSXXCXCC;
import com.derun.model.po.SYJK_CCS_CCSNSXXCXRC;
import com.derun.taxpayquery.dao.impl.TaxPayQueryServiceDao;

/**
 * @author 郑艳英
 * 
 * @date 2014-5-7
 * 
 *       车船税纳税信息查询封装类
 * @version
 */
public class TaxPayQueryPackage {
	// 封装返回出参(车船税信息,纳税状态,返回代码,算税标志)
	public TaxPayQueryResInfo resParameter(Tax_Type taxInfo,String declaredStatus, String returnCode, String taxFlag) {
		TaxPayQueryResInfo taxPayQueryResInfo = new TaxPayQueryResInfo();
		taxPayQueryResInfo.setTaxInfo(taxInfo);
		taxPayQueryResInfo.setDeclaredStatus(declaredStatus);
		taxPayQueryResInfo.setReturnCode(returnCode);
		taxPayQueryResInfo.setTaxFlag(taxFlag);
		return taxPayQueryResInfo;
	}

	// 封装返回出参(车船税信息,纳税状态,返回代码)
	public TaxPayQueryResInfo resParameter(Tax_Type taxInfo,String declaredStatus, String returnCode) {
		TaxPayQueryResInfo taxPayQueryResInfo = new TaxPayQueryResInfo();
		taxPayQueryResInfo.setTaxInfo(taxInfo);
		taxPayQueryResInfo.setDeclaredStatus(declaredStatus);
		taxPayQueryResInfo.setReturnCode(returnCode);
		return taxPayQueryResInfo;
	}
	//封装请求入参和请求出参
	public List<Object> FZ(TaxPayQueryResInfo taxPayQueryResInfo,TaxPayQueryReqInfo taxpayqueryReq){
		List<Object> listObject = new ArrayList<Object>();
		//纳税信息查询入参表封装类
		SYJK_CCS_CCSNSXXCXRCV0  ccsnsxxcxrcVO = new SYJK_CCS_CCSNSXXCXRCV0();
		//得到封装的纳税信息查询入参信息
		SYJK_CCS_CCSNSXXCXRC ccsnsxxcxrc = ccsnsxxcxrcVO.getTaxpayqueryReqVO(taxpayqueryReq);
		//纳税信息查询出参表封装类
		SYJK_CCS_CCSNSXXCXCCVO ccsnsxxcxccVO = new SYJK_CCS_CCSNSXXCXCCVO();
		//得到封装的纳税信息查询出参信息
		SYJK_CCS_CCSNSXXCXCC ccsnsxxcxcc = ccsnsxxcxccVO.getTaxpayqueryReqVO(taxPayQueryResInfo);
		listObject.add(ccsnsxxcxrc);
		listObject.add(ccsnsxxcxcc);
		return listObject;
	}
	
	//保存请求入参和请求出参
	public boolean saveReqRes(List<Object> list){
		boolean saveReq = false;
		boolean saveRes = false;
		boolean falg = false;
		TaxPayQueryServiceDao taxPayQueryServiceDao = new TaxPayQueryServiceDao();
		SYJK_CCS_CCSNSXXCXRC ccsnsxxcxrc = (SYJK_CCS_CCSNSXXCXRC) list.get(0);
		SYJK_CCS_CCSNSXXCXCC ccsnsxxcxcc = (SYJK_CCS_CCSNSXXCXCC) list.get(1);
		saveReq = taxPayQueryServiceDao.savePayReq(ccsnsxxcxrc);
		saveRes = taxPayQueryServiceDao.savePayRes(ccsnsxxcxcc);
		if(saveReq == true && saveRes == true){
			falg = true;
		}
		return falg;
	}
	
	
}
