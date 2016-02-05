package com.derun.taxquery.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.Tax_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSXX;
/**
 * @author MILI
 * @time 2014-3-18 15:46:18
 * @描述：插入对象 SYJK_CCS_CCSXX 封装  车船税信息表
 * */
public class P_SYJK_CCS_CCSXX {
	/**
	 * @author MILI
	 * @time 2014-3-18 15:46:18
	 * @描述：插入对象 SYJK_CCS_CCSXX 封装
	 * @param taxInfo
	 * @param confirmN
	 * @param basequeryreqinfo
	 * @param carMatchId
	 * @return
	 */
	public SYJK_CCS_CCSXX getCCSXX(Tax_Type taxInfo,String confirmN,BaseQueryReqInfo basequeryreqinfo,String carMatchId){
		SYJK_CCS_CCSXX ccsxx = new SYJK_CCS_CCSXX();
		Date starttime = null , endtime = null ,paydate = null , exceeddate = null ;
		String startDate = taxInfo.getCurrentTaxDue() == null ? "" : taxInfo
				.getCurrentTaxDue().getTaxStartDate();
		String endDate = taxInfo.getCurrentTaxDue() == null ? "" : taxInfo
				.getCurrentTaxDue().getTaxEndDate();
		String ExceedDate = taxInfo.getCurrentTaxDue() == null ? ""
				: taxInfo.getCurrentTaxDue().getExceedDate();
		if (ExceedDate != null && ExceedDate.length() >= 10) {
			exceeddate = DateUtil.getStringDate(ExceedDate.substring(0, 10),null);
		}
		if (startDate != null && startDate.length() >= 10) {
			starttime = DateUtil.getStringDate(startDate.substring(0, 10),null);
		}
		if (endDate != null && endDate.length() >= 10) {
			endtime = DateUtil.getStringDate(endDate.substring(0, 10),null);

		}
		String patdat = taxInfo.getPayDate() == null ? "" : taxInfo.getPayDate();
		if (patdat != null && patdat.length() >= 10) {
			paydate = DateUtil.getStringDate(patdat.substring(0, 10),null);
		} 
		ccsxx.setTAXQUERYNO(confirmN);								// 车船税查询码,唯一标示符
		ccsxx.setTAXTERMTYPECODE(basequeryreqinfo.getTaxInfo().getTaxTermTypeCode());		// 税种类型代码,Y
		ccsxx.setTAXCONDITIONCODE(basequeryreqinfo.getTaxInfo().getTaxConditionCode());		// 纳税类型代码,Y
		ccsxx.setTAXREGISTRYNUMBER(basequeryreqinfo.getTaxInfo().getTaxRegistryNumber());	// 税务登记证号
		ccsxx.setTAXPAYERNAME(basequeryreqinfo.getTaxInfo().getTaxPayerName());				// 纳税人名称
		ccsxx.setTAXPAYERIDENTIFICATIONCODE(basequeryreqinfo.getTaxInfo().getTaxPayerIdentificationCode());		// 纳税人识别号
		ccsxx.setTAXLOCATIONCODE(taxInfo.getCurrentTaxDue().getTaxLocationCode());	// 纳税地区代码
		ccsxx.setTAXSTARTDATE(starttime);							// 税款所属始期
		ccsxx.setTAXENDDATE(endtime);								// 税款所属止期
		if (taxInfo.getCurrentTaxDue() != null) {
			ccsxx.setTAXUNITTYPECODE(taxInfo.getCurrentTaxDue().getTaxUnitTypeCode());	// 计税单位代码
			ccsxx.setUNITRATE(taxInfo.getCurrentTaxDue().getUnitRate());				// 单位计税金额
			ccsxx.setANNUALTAXAMOUNT(taxInfo.getCurrentTaxDue().getAnnualTaxAmount());	// 当期年单位税额
		} else {
			ccsxx.setTAXUNITTYPECODE("");							// 计税单位代码
			ccsxx.setUNITRATE(0.0);									// 单位计税金额
			ccsxx.setANNUALTAXAMOUNT(0.0);							// 当期年单位税额
		}
		ccsxx.setPAYDATE(paydate);									// 代收日期,Y
		ccsxx.setPAYCOMPANYCODE(basequeryreqinfo.getTaxInfo().getPayCompanyCode());		// 代收公司

		// 完税减免税
		if (taxInfo.getCurrentTaxDue() != null) {
			if(taxInfo.getCurrentTaxDue().getDerate() != null){
				ccsxx.setDEDUCTIONDUECODE(taxInfo.getCurrentTaxDue().getDerate().getDeductionDueCode());					// 减免税原因代码
				ccsxx.setDEDUCTIONDUETYPE(taxInfo.getCurrentTaxDue().getDerate().getDeductionDueType());					// 减免税方案代码
				ccsxx.setDEDUCTIONDUEPROPORTION(taxInfo.getCurrentTaxDue().getDerate().getDeductionDueProportion());		// 减免比例
				ccsxx.setDEDUCTION(taxInfo.getCurrentTaxDue().getDerate().getDeduction());									// 减免金额
				ccsxx.setDEDUCTIONDOCUMENTNUMBER(taxInfo.getCurrentTaxDue().getDerate().getDeductionDocumentNumber());		// 减免税凭证号
				ccsxx.setDEPARTMENT(taxInfo.getCurrentTaxDue().getDerate().getTaxDepartment());								// 开具减免税凭证的税务机关名称
				ccsxx.setDEPARTMENTCODE(taxInfo.getCurrentTaxDue().getDerate().getTaxDepartmentCode());						// 开具减免税凭证的税务机关代码
			}else{
				ccsxx.setDEDUCTIONDUECODE(null);					// 减免税原因代码
				ccsxx.setDEDUCTIONDUETYPE(null);					// 减免税方案代码
				ccsxx.setDEDUCTIONDUEPROPORTION(null);				// 减免比例
				ccsxx.setDEDUCTION(null);							// 减免金额
				ccsxx.setDEDUCTIONDOCUMENTNUMBER(null);				// 减免税凭证号
				ccsxx.setDEPARTMENT(null);							// 开具减免税凭证的税务机关名称
				ccsxx.setDEPARTMENTCODE(null);						// 开具减免税凭证的税务机关代码
			}
			if(taxInfo.getCurrentTaxDue().getPaid() != null){
				ccsxx.setTAXDEPARTMENT(taxInfo.getCurrentTaxDue().getPaid().getTaxDepartment());			// 开具完税凭证的税务机关名称
				ccsxx.setTAXDEPARTMENTCODE(taxInfo.getCurrentTaxDue().getPaid().getTaxDepartmentCode());	// 开具完税凭证的税务机关代码
				ccsxx.setTAXDOCUMENTNUMBER(taxInfo.getCurrentTaxDue().getPaid().getTaxDocumentNumber());	// 完税凭证号码
			}else{
				ccsxx.setTAXDEPARTMENT(null);		// 开具完税凭证的税务机关名称
				ccsxx.setTAXDEPARTMENTCODE(null);	// 开具完税凭证的税务机关代码
				ccsxx.setTAXDOCUMENTNUMBER(null);	// 完税凭证号码
			}
		} else {
			ccsxx.setDEPARTMENT(null);							// 开具减免税凭证的税务机关名称
			ccsxx.setDEPARTMENTCODE(null);						// 开具减免税凭证的税务机关代码
			ccsxx.setDEDUCTIONDUECODE(null);					// 减免税原因代码
			ccsxx.setDEDUCTIONDUETYPE(null);					// 减免税方案代码
			ccsxx.setDEDUCTIONDUEPROPORTION(null);				// 减免比例
			ccsxx.setDEDUCTION(null);							// 减免金额
			ccsxx.setDEDUCTIONDOCUMENTNUMBER(null);				// 减免税凭证号
			ccsxx.setTAXDEPARTMENTCODE(null);					// 开具完税凭证的税务机关代码
			ccsxx.setTAXDEPARTMENT(null);						// 开具完税凭证的税务机关名称,Y
			ccsxx.setTAXDOCUMENTNUMBER(null);					// 完税凭证号码
		}
		ccsxx.setTAXDUE(taxInfo.getCurrentTaxDue().getTaxDue());						// 当期应纳税额
		ccsxx.setEXCEEDDAYSCOUNT(taxInfo.getCurrentTaxDue().getExceedDaysCount());		// 逾期天数
		ccsxx.setOVERDUE(taxInfo.getCurrentTaxDue().getOverDue());						// 滞纳金
		ccsxx.setTOTALAMOUNT(taxInfo.getCurrentTaxDue().getTotalAmount());				// 合计金额
		ccsxx.setEXCEEDDATE(exceeddate);												// 逾期时间
		ccsxx.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));		// 操作员
		ccsxx.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
		ccsxx.setSJCJRQ(DateUtil.getDateDate());										// 数据采集日期
		ccsxx.setSJCJFS("0");															// 数据采集方式
		ccsxx.setPARATYPE("1");															// 参数类型0入参1出参
		ccsxx.setINSURESTARTDATE(basequeryreqinfo.getInsureStartDate());
		ccsxx.setINSUREENDDATE(basequeryreqinfo.getInsureEndDate());
		ccsxx.setCARMATCHID(carMatchId);
		ccsxx.setTAXESFLAG(null);
		return ccsxx ;
	}
	/**
	 * @author MILI
	 * @time 2014-6-4 10:16:22
	 * @描述：欠税信息封装
	 * */
	public List<SYJK_CCS_CCSXX> getCCSXX_list(Tax_Type taxInfo,String confirmN,BaseQueryReqInfo basequeryreqinfo,String carMatchId){
		Tax_Type _taxInfo = new Tax_Type();
 		List<SYJK_CCS_CCSXX> list_ccsxx = new  ArrayList<SYJK_CCS_CCSXX>() ;
		SYJK_CCS_CCSXX ccsxx = new SYJK_CCS_CCSXX();
		int number = taxInfo.getDelinquentTaxDue() == null ? 0 : taxInfo.getDelinquentTaxDue().length;
		for(int i = 0 ; i < number ; i++){
			_taxInfo.setCurrentTaxDue(taxInfo.getDelinquentTaxDue()[i]);
			ccsxx = this.getCCSXX(_taxInfo,confirmN,basequeryreqinfo,carMatchId);
			ccsxx.setTAXESFLAG("1");
			list_ccsxx.add(ccsxx);
		}
		return list_ccsxx ;
	}
}
