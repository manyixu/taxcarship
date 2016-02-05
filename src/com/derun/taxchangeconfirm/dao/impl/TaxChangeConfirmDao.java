package com.derun.taxchangeconfirm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.BaseChangeConfirmResInfo;
import com.derun.beans.BaseConfirmReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.db.ExeSQL;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.LogUtil;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSBGQRRCJB;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
import com.derun.model.po.SYJK_CCS_CODE;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_RKMX_QS;
/**
 * @author MILI
 * @time 2014-3-21 9:44:03
 * @描述：批改确认 封装写库类方法规范
 * */
public class TaxChangeConfirmDao{
	TaxChangeConfirmDao_SQL changConfirm = new TaxChangeConfirmDao_SQL();
	public BaseChangeConfirmResInfo CC_Object(TaxDealCode_Type taxconfirmon,String returncode,TaxDealCode_Type printNo,BaseChangeConfirmReqInfo bccr,SYJK_CCS_CCSBGCXCCJB bgcxcc,SYJK_CCS_RKMX rkmx) {
		BaseChangeConfirmResInfo bcri = new BaseChangeConfirmResInfo();
		if(ChkUtil.CHK_0000.equals(returncode)){
			bcri.setChangeConfirmNo(taxconfirmon);	// 确认码
			if(bccr != null){
				if("R".equals(bccr.getTaxInfo().getTaxConditionCode()) && !"R".equals(rkmx.getTAXCONDITIONCODE())){
					bcri.setTaxPrintNo(printNo);			// 打印码
				}else if("R".equals(rkmx.getTAXCONDITIONCODE()) && !"R".equals(bccr.getTaxInfo().getTaxConditionCode())){
					bcri.setTaxPrintNo(printNo);		// 打印码
				}else{
					bcri.setTaxPrintNo(null);
				}
			}else{
				bcri.setTaxPrintNo(null);		// 打印码
			}
		}else{
			bcri.setChangeConfirmNo(null);	// 确认码
			bcri.setTaxPrintNo(null);		// 打印码
		}
		if(bgcxcc != null){
			bcri.setCarMatchId(bgcxcc.getCARMATCHID());
		}else{
			bcri.setCarMatchId(null);
		}
		bcri.setReturnCode(returncode);		// 返回码
		return bcri;
	}

	public List<Object> Packaging(BaseChangeConfirmReqInfo BCCRI,
			TaxDealCode_Type taxQueryNo, String taxConfirmNo,
			double changeSumTax,SYJK_CCS_CODE code,BaseConfirmReqInfo baseconfirmReqinfo,
			AnnualTax_Type delinquentTaxDue,
			SYJK_CCS_CCSCXCCJBXX cxccjbxx,SYJK_CCS_CCSBGCXCCJB bgcxccjb,
			String platformstate, String changeRes,String getTime,String flag) {
		
		List<Object> listPO = new ArrayList<Object>();
		// 实例
		P_SYJK_CCS_CCSBGQRRCJB p_bgqrrc = new P_SYJK_CCS_CCSBGQRRCJB();
		P_SYJK_CCS_QGCLWTMDXX p_qgclwtmd = new P_SYJK_CCS_QGCLWTMDXX();
		P_SYJK_CCS_RKMX_QS p_rkmx_qs = new P_SYJK_CCS_RKMX_QS();
		P_SYJK_CCS_RKMX p_rkmx = new P_SYJK_CCS_RKMX();
		// 实例
		SYJK_CCS_CCSBGQRRCJB bgqrrc = new SYJK_CCS_CCSBGQRRCJB();
		SYJK_CCS_QGCLWTMDXX qgclwtmd = new SYJK_CCS_QGCLWTMDXX();
		SYJK_CCS_RKMX_QS rkmx_qs = new SYJK_CCS_RKMX_QS();
		SYJK_CCS_RKMX rkmx = new SYJK_CCS_RKMX();
		
		if("BC".equals(flag)){ 	// 补传
			changConfirm.getRKMX_BC_sql(BCCRI);
		}else{	// 确认
			bgqrrc = p_bgqrrc.getCcsbgrrcjbb(BCCRI, taxQueryNo, taxConfirmNo, changeSumTax, code);
			qgclwtmd = p_qgclwtmd.getQgclwtmdxx();
			rkmx_qs = p_rkmx_qs.getrkmx_qs(baseconfirmReqinfo, delinquentTaxDue, cxccjbxx, taxConfirmNo, code);
			rkmx = p_rkmx.getRkmx(BCCRI, taxQueryNo, delinquentTaxDue, taxConfirmNo, bgcxccjb, platformstate, changeRes, code, getTime);
		}
		// add
		listPO.add(bgqrrc);
		listPO.add(qgclwtmd);
		listPO.add(rkmx_qs);
		listPO.add(rkmx);
		return listPO ;
	}
	public boolean Write_Bank(BaseChangeConfirmReqInfo BCCRI,TaxDealCode_Type querycode,SYJK_CCS_RKMX rkmx,SYJK_CCS_CCSBGCXCCJB queryRes,
			TaxDealCode_Type taxQueryNo,String taxConfirmNo,String platformstate,String changeRes,double changeSumTax,String flag) {
		LogUtil log = new LogUtil("批改确认  写库");
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.startLog();
		}
		
		boolean flags = false;
		ExeSQL exesql = new ExeSQL();
		if("BC".equals(flag)){
//			flags = exesql.execUpdateSQL(changConfirm.getRKMX_BC_sql(BCCRI));
			flags = changConfirm.getRkmx(BCCRI, rkmx, queryRes, taxQueryNo, taxConfirmNo, platformstate, changeRes, changeSumTax);
		}else if("QR".equals(flag)){
			flags = changConfirm.getRkmx(BCCRI, rkmx, queryRes, taxQueryNo, taxConfirmNo, platformstate, changeRes, changeSumTax);
		}else{
			flags = false ;
		}
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.endLog();
		}
		return flags ;
	}
}
