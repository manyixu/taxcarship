package com.derun.taxquery.dao;
import java.util.ArrayList;
import java.util.List;
import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.BaseQueryResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.db.ExeSQL;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.LogUtil;
import com.derun.model.po.Car_Id_No;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
import com.derun.model.po.SYJK_CCS_CCSCXRCJBXX;
import com.derun.model.po.SYJK_CCS_CCSXX;
import com.derun.taxquery.dao.impl.TaxQueryDaoImpl;
/**
 * @author MILI
 * @time 2014-3-18 11:21:59
 * @描述：投保查询  封装写库
 * */
public class TaxQueryDao{
	TaxQueryDao_SQL querysql = new TaxQueryDao_SQL();
	TaxQueryDaoImpl tqdl = new TaxQueryDaoImpl();
	LogUtil log = new LogUtil("投保查询 写库");
	// 封装返回出参
	public BaseQueryResInfo CC_Object(BaseQueryReqInfo basequeryreqinfo,Tax_Type taxInfo,TaxDealCode_Type taxQueryNo,String returnCode,Car_Id_No Carin) {
		BaseQueryResInfo basequeryresinfo = new BaseQueryResInfo();
		if(ChkUtil.CHK_0000.equals(returnCode)){
			basequeryresinfo.setCarMatchId(Carin.getCarMatchId());
			basequeryresinfo.setCarSerialNo(Carin.getCarSerialNo());   // 问题名单序列号
			basequeryresinfo.setTaxInfo(tqdl.getBqri(basequeryreqinfo,taxInfo));
			basequeryresinfo.getTaxInfo().setCurrentTaxDue(tqdl.getAT(basequeryreqinfo,taxInfo));
			basequeryresinfo.getTaxInfo().setDelinquentTaxDue(tqdl.getATS(basequeryreqinfo,taxInfo));
			basequeryresinfo.getTaxInfo().setTaxAmount(tqdl.getTT(taxQueryNo,taxInfo));
		}else{
			basequeryresinfo.setTaxInfo(new Tax_Type());	// 车辆纳税信息
		}
		basequeryresinfo.setCalcTaxFlag("1");				// 算税标志
		basequeryresinfo.setTaxQueryNo(taxQueryNo);			// 车船税查询码
		basequeryresinfo.setReturnCode(returnCode);			// 返回错误代码
		return basequeryresinfo ;
	}
	// 写库
	@SuppressWarnings("unchecked")
	public boolean Write_Bank(List<Object> list) {
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.startLog();
		}
		boolean flag = false;
		ExeSQL exesql = new ExeSQL();
		String sqlArry[] = null ;	// 所有要执行的sql
		List<SYJK_CCS_CCSXX> ccsxx_list = (List<SYJK_CCS_CCSXX>) list.get(3);
		if(ccsxx_list != null && ccsxx_list.size() > 0){
			int number = ccsxx_list.size();
			sqlArry = new String[number + 3];
			int i = 0 ;
			for( ; i < number ;i++){
				sqlArry[i] = querysql.getCCSXX_sql(ccsxx_list.get(i));
			}
			sqlArry[i++] = querysql.getCCSCXCCJBXX_sql((SYJK_CCS_CCSCXCCJBXX)list.get(2));
			sqlArry[i++] = querysql.getCCSCXRCJBXX_sql((SYJK_CCS_CCSCXRCJBXX)list.get(1));
			sqlArry[i++] = querysql.getCCSXX_sql((SYJK_CCS_CCSXX)list.get(0));
		}else{
			sqlArry = new String[3];
			sqlArry[0] = querysql.getCCSCXCCJBXX_sql((SYJK_CCS_CCSCXCCJBXX)list.get(2));
			sqlArry[1] = querysql.getCCSCXRCJBXX_sql((SYJK_CCS_CCSCXRCJBXX)list.get(1));
			sqlArry[2] = querysql.getCCSXX_sql((SYJK_CCS_CCSXX)list.get(0));
		}
		// 一起提交事务
		flag = exesql.exeSqlBatch(sqlArry);
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.endLog();
		}
		return flag ;
	}
	// 封装写库对象
	public List<Object> Packaging(BaseQueryReqInfo bqri, Tax_Type taxInfo,
			String confirmN, String carSerialNo, String carMatchId,String isInsert) {
		List<Object> listVO = new ArrayList<Object>();
		// 实例
		P_SYJK_CCS_CCSXX p_ccsxx = new P_SYJK_CCS_CCSXX();
		P_SYJK_CCS_CCSCXRCJBXX p_ccscxcjbxx = new P_SYJK_CCS_CCSCXRCJBXX();
		P_SYJK_CCS_CCSCXCCJBXX p_ccscxccjbxx = new P_SYJK_CCS_CCSCXCCJBXX();
		// 实例
		SYJK_CCS_CCSXX ccsxx = new SYJK_CCS_CCSXX();						// 车船税信息表
		SYJK_CCS_CCSCXRCJBXX ccscxcjbxx = new SYJK_CCS_CCSCXRCJBXX();		// 投保查询入参
		SYJK_CCS_CCSCXCCJBXX ccscxccjbxx = new SYJK_CCS_CCSCXCCJBXX();		// 投保查询出参
		// 封装
		ccsxx = p_ccsxx.getCCSXX(taxInfo, confirmN, bqri, carMatchId);
		ccscxcjbxx = p_ccscxcjbxx.getCcscxrcjbxx(bqri, confirmN);
		ccscxccjbxx = p_ccscxccjbxx.getCcscxccjbxx(taxInfo, carSerialNo, confirmN, bqri, carMatchId,isInsert);
		List<SYJK_CCS_CCSXX> ccsxx_list = p_ccsxx.getCCSXX_list(taxInfo, confirmN, bqri, carMatchId);
		// add
		listVO.add(ccsxx);				// 0	
		listVO.add(ccscxcjbxx);			// 1
		listVO.add(ccscxccjbxx);		// 2
		listVO.add(ccsxx_list);			// 3
		return listVO ;
	}
}
