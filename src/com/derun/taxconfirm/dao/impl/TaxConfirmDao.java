package com.derun.taxconfirm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseConfirmReqInfo;
import com.derun.beans.BaseConfirmResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.db.ExeSQL;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.LogUtil;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
import com.derun.model.po.SYJK_CCS_CCSQRRCJBXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_RKMX_QS;

/**
 * @author MILI
 * @time 2014-3-18 11:21:59
 * @描述：投保确认 封装写库
 * */
public class TaxConfirmDao {
	TaxComfirmDao_SQL comfirmsql = new TaxComfirmDao_SQL();
	/**
	 * @author MILI
	 * @time 2014-5-16 11:11:55
	 * @描述：封装返回出参
	 * @入参：返回码、确认码、打印码
	 * @出参：确认出参
	 * */
	public BaseConfirmResInfo CC_Object(String Returncode,TaxDealCode_Type taxConfirmNo,TaxDealCode_Type TaxPrintNo,BaseConfirmReqInfo bcri) {
		BaseConfirmResInfo baseConfrimResInfo = new BaseConfirmResInfo();	
		if(ChkUtil.CHK_0000.equals(Returncode)){
			baseConfrimResInfo.setTaxConfirmNo(taxConfirmNo);	// 确认码
			if("R".equals(bcri.getTaxInfo().getTaxConditionCode())){
				baseConfrimResInfo.setTaxPrintNo(null);		// 打印码
			}else{
				baseConfrimResInfo.setTaxPrintNo(TaxPrintNo);		// 打印码
			}
		}else{
			baseConfrimResInfo.setTaxConfirmNo(null);	// 确认码
			baseConfrimResInfo.setTaxPrintNo(null);		// 打印码
		}
		if(bcri != null){
//			baseConfrimResInfo.setCarSerialNo(bcri.getCarSerialNo());	// 问题名单序列号
			baseConfrimResInfo.setCarMatchId(bcri.getCarMatchId());
		}else{
//			baseConfrimResInfo.setCarSerialNo(null);		// 问题名单序列号
			baseConfrimResInfo.setCarMatchId(null);
		}
		baseConfrimResInfo.setReturnCode(Returncode);		// 返回码
		return baseConfrimResInfo;
	}
	/**
	 * @author MILI
	 * @time 2014-5-16 11:10:15
	 * @描述：封装写库对象  
	 * @入参：投保确认入参、确认码、查询出参对象、打印码
	 * @出参：List<Object>
	 **/
	public List<Object> Packaging(BaseConfirmReqInfo baseconfirmReqinfo,TaxDealCode_Type taxcon,
			SYJK_CCS_CCSCXCCJBXX cxccjbxx, String printN) {
		List<Object> listPO = new ArrayList<Object>();
		// 实例
		P_SYJK_CCS_CCSQRRCJBXX p_ccsqrrc = new P_SYJK_CCS_CCSQRRCJBXX();
		P_SYJK_CCS_RKMX_QS p_rkmx_qs = new P_SYJK_CCS_RKMX_QS();
		P_SYJK_CCS_RKMX p_rkmx = new P_SYJK_CCS_RKMX();
		// 实例
		SYJK_CCS_CCSQRRCJBXX ccsqrrc = new SYJK_CCS_CCSQRRCJBXX();
		SYJK_CCS_RKMX_QS rkmx_qs = new SYJK_CCS_RKMX_QS();
		SYJK_CCS_RKMX rkmx = new SYJK_CCS_RKMX();
		// 封装
		ccsqrrc = p_ccsqrrc.getCcsqrrcjbxx(baseconfirmReqinfo,taxcon,printN);
		
		int leng = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue() == null ? 0
				: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue().length;
		List<SYJK_CCS_RKMX_QS> list_qs = new ArrayList<SYJK_CCS_RKMX_QS>();
		if (leng > 0) {
			AnnualTax_Type[] DelinquentTaxDue = baseconfirmReqinfo
					.getTaxInfo().getDelinquentTaxDue(); 			// 欠税信息对象数组
			AnnualTax_Type delinquentTaxDue = new AnnualTax_Type();
			for (int i = 0; i < leng; i++) {
				delinquentTaxDue = DelinquentTaxDue[i];
				rkmx_qs = p_rkmx_qs.getrkmx_qs(baseconfirmReqinfo,delinquentTaxDue, cxccjbxx,taxcon);
				list_qs.add(rkmx_qs);
			}
		}
		rkmx = p_rkmx.getrkmx(baseconfirmReqinfo,cxccjbxx,taxcon);
		// add
		listPO.add(ccsqrrc);
		listPO.add(rkmx);
		listPO.add(list_qs);
		return listPO;
	}
	/**
	 * @author MILI
	 * @time 2014-5-16 11:13:02
	 * @描述：写库
	 * @入参：Packaging 方法返回出参
	 * @出参：boolean 
	 **/
	@SuppressWarnings("unchecked")
	public boolean Write_Bank(List<Object> list) {
		LogUtil log = new LogUtil("投保确认 写库");
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.startLog();
		}
		boolean flag = false;
		ExeSQL exesql = new ExeSQL();
		String sqlArr[] = null ;		// 所有要执行的sql
		List<SYJK_CCS_RKMX_QS> list_qs = (List<SYJK_CCS_RKMX_QS>) list.get(2);
		if(list_qs != null && list_qs.size() > 0){
			int number = list_qs.size();
			sqlArr = new String[number + 2];
			int i = 0;
			for(; i < number ; i++){
				sqlArr[i] = comfirmsql.getRKMX_QS_sql(list_qs.get(i));
			}
			sqlArr[i++] = comfirmsql.getCCSQRRCJBXX_sql((SYJK_CCS_CCSQRRCJBXX)list.get(0));
			sqlArr[i++] = comfirmsql.getRKMX_sql((SYJK_CCS_RKMX)list.get(1));
		}else{
			sqlArr = new String[2];
			sqlArr[0] = comfirmsql.getCCSQRRCJBXX_sql((SYJK_CCS_CCSQRRCJBXX)list.get(0));
			sqlArr[1] = comfirmsql.getRKMX_sql((SYJK_CCS_RKMX)list.get(1));
		}
		// 一起提交事务
		flag = exesql.exeSqlBatch(sqlArr);
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.endLog();
		}
		return flag ;
	}
}
