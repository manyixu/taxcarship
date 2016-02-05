package com.derun.taxchangequery.dao.impl;

import java.util.ArrayList;
import java.util.List;
import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.BaseChangeQueryResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.db.ExeSQL;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.LogUtil;
import com.derun.model.po.Car_Id_No;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSBGCXRCJB;
import com.derun.model.po.SYJK_CCS_CCSBGXX;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-3-18 11:21:59
 * @描述：变更查询 封装写库
 * @入参：车船税变更查询码，算税标志，返回代码，车船税信息
 * @出参：变更请求信息对象出参 
 * */
public class TaxChangeQueryDao implements TaxChangeQueryDao_IF {
	TaxChangQueryDao_SQL changquery = new TaxChangQueryDao_SQL();
	TaxChangeQueryImpl tcqi = new TaxChangeQueryImpl();
	LogUtil log = new LogUtil("批改查询  写库");
	@Override
	public BaseChangeQueryResInfo CC_Object(BaseChangeQueryReqInfo basechangequeryreqinfo,TaxDealCode_Type type ,String calcTaxFlag ,String returnCode,Tax_Type taxInfo,Car_Id_No cin) {
		BaseChangeQueryResInfo bcri = new BaseChangeQueryResInfo();
		// 增加 1Y 1N 条件  mili 2014-12-25 18:27:34
		if(ChkUtil.CHK_0000.equals(returnCode) || "1Y".equals(returnCode) || "1N".equals(returnCode)){
			if(taxInfo != null){
				bcri.setTaxInfo(tcqi.getTaxType(basechangequeryreqinfo,taxInfo,type));
			}else{
				bcri.setTaxInfo(new Tax_Type());
			}
		}else{
			bcri.setTaxInfo(new Tax_Type());
		}
		if(cin != null){
			bcri.setCarMatchId(cin.getCarMatchId());				// 匹配规则ID
			bcri.setCarSerialNo(cin.getCarSerialNo());				// 机动车序列号			// 问题名单序列号
		}else{
			bcri.setCarMatchId("");				// 匹配规则ID
//			bcri.setCarSerialNo("");			// 机动车序列号		// 问题名单序列号
		}
		bcri.setChangeQueryNo(type);		// 车船税变更查询码
		bcri.setCalcTaxFlag(calcTaxFlag);	// 算税标志
		bcri.setReturnCode(returnCode);		// 返回代码
		return bcri;
	}

	@Override
	public List<Object> Packaging(BaseChangeQueryReqInfo bcqri,BaseChangeQueryResInfo bcqrs,TaxDealCode_Type ChangeQueryNo
			,SYJK_CCS_RKMX rkmx,Tax_Type taxInfo,String returncode,String tbqrm,Car_Id_No cin,String isInsert) {
		List<Object> listPO = new ArrayList<Object>();
		List<SYJK_CCS_CCSBGXX> list_bgxx = new ArrayList<SYJK_CCS_CCSBGXX>(); 
		// 实例
		P_SYJK_CCS_CCSBGCXCCJB p_ccsbgcxcc = new P_SYJK_CCS_CCSBGCXCCJB();
		P_SYJK_CCS_CCSBGCXRCJB p_ccsbgcxrc = new P_SYJK_CCS_CCSBGCXRCJB();
		P_SYJK_CCS_CCSBGXX p_ccsbgxx = new P_SYJK_CCS_CCSBGXX();
		// 实例
		SYJK_CCS_CCSBGCXCCJB ccsbgcxcc = new SYJK_CCS_CCSBGCXCCJB();
		SYJK_CCS_CCSBGCXRCJB ccsbgcxrc = new SYJK_CCS_CCSBGCXRCJB();
		SYJK_CCS_CCSBGXX ccsbgxx = new SYJK_CCS_CCSBGXX();
		// 封装
		ccsbgcxcc = p_ccsbgcxcc.getCcsbgcxccjb(bcqri,bcqrs,tbqrm,rkmx,taxInfo,ChangeQueryNo,returncode,cin,isInsert);
		ccsbgcxrc = p_ccsbgcxrc.getCcsbgcxrc(bcqri, ChangeQueryNo);
		ccsbgxx = p_ccsbgxx.getCcsbgxx(bcqri, taxInfo,ChangeQueryNo);
		list_bgxx = p_ccsbgxx.getCcsbgxx_qs(bcqri, taxInfo,ChangeQueryNo);
		// add
		listPO.add(ccsbgcxcc);
		listPO.add(ccsbgcxrc);
		listPO.add(ccsbgxx);
		listPO.add(list_bgxx);
		return listPO ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean Write_Bank(List<Object> list) {
		
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.startLog();
		}
		boolean flag = false;
		ExeSQL exesql = new ExeSQL();
		String sqlArr[] = null ;
		List<String> list_sql = changquery.getCCSBGXX_sql_qs((List<SYJK_CCS_CCSBGXX>) list.get(3));
		if(list_sql != null && list_sql.size() > 0){
			int number = list_sql.size();
			sqlArr = new String[number + 3] ;
			int i = 0 ;
			for(;i < number ; i++){
				sqlArr[i] = list_sql.get(i) ;
			}
			sqlArr[i++] = changquery.getCCSBGCXCCJB_sql((SYJK_CCS_CCSBGCXCCJB)list.get(0));
			sqlArr[i++] = changquery.getCCSBGCXRCJB_sql((SYJK_CCS_CCSBGCXRCJB)list.get(1));
			sqlArr[i++] = changquery.getCCSBGXX_sql((SYJK_CCS_CCSBGXX)list.get(2));
		}else{
			sqlArr = new String[3] ;
			sqlArr[0] = changquery.getCCSBGCXCCJB_sql((SYJK_CCS_CCSBGCXCCJB)list.get(0));
			sqlArr[1] = changquery.getCCSBGCXRCJB_sql((SYJK_CCS_CCSBGCXRCJB)list.get(1));
			sqlArr[2] = changquery.getCCSBGXX_sql((SYJK_CCS_CCSBGXX)list.get(2));
		}
		flag = exesql.exeSqlBatch(sqlArr);
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.endLog();
		}
		return flag ;
	}
}
