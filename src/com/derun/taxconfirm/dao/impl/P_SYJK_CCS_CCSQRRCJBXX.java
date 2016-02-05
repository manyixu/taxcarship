package com.derun.taxconfirm.dao.impl;

import java.util.Date;

import com.derun.beans.BaseConfirmReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSQRRCJBXX;

/**
 * @author MILI
 * @time 2014-3-19 16:00:09
 * @描述：插入对象 SYJK_CCS_CCSQRRCJBXX 封装
 * */
public class P_SYJK_CCS_CCSQRRCJBXX{
	/**
	 * @author MILI
	 * @time 2014-3-19 16:00:09
	 * @描述：插入对象 SYJK_CCS_CCSQRRCJBXX 封装
	 * */
	public SYJK_CCS_CCSQRRCJBXX getCcsqrrcjbxx(BaseConfirmReqInfo baseconfirmReqinfo, TaxDealCode_Type codetype, String printNo){
		String ptzt = "";		// 退税标志
		SYJK_CCS_CCSQRRCJBXX ccsqrrcjbxx = new SYJK_CCS_CCSQRRCJBXX();
		Date firsttime = null ;
		ccsqrrcjbxx.setTAXQUERYNO(baseconfirmReqinfo.getTaxQueryNo().getTaxDealCode_Type()); // 车船税查询码
		
		String BCFlag = "2";
		if (baseconfirmReqinfo.getTaxConfirmNo() != null) {
			if (baseconfirmReqinfo.getTaxConfirmNo().getTaxDealCode_Type() != null
					&& !"".trim().equals(
							baseconfirmReqinfo.getTaxConfirmNo()
									.getTaxDealCode_Type().trim())) {
				BCFlag = "1";
			}
		}
		if ("1".equals(baseconfirmReqinfo.getCalcTaxFlag())
				&& "2".equals(BCFlag)) {
			if ("R".equals(baseconfirmReqinfo.getTaxInfo()
					.getTaxConditionCode())) {
				ccsqrrcjbxx.setTAXPRINTNO(null);	// 车船税打印码
			} else {
				ccsqrrcjbxx.setTAXPRINTNO(printNo);	// 车船税打印码
			}
		} else {
			String DYM = codetype == null ? "" : codetype.getTaxDealCode_Type();
			ccsqrrcjbxx.setTAXPRINTNO(DYM);			// 车船税打印码
		}
		if ("1".equals(baseconfirmReqinfo.getCalcTaxFlag())
				&& "2".equals(BCFlag)) {
			ccsqrrcjbxx.setTAXCONFIRMNO(codetype.getTaxDealCode_Type());		// 车船税确认码
		} else {
			ccsqrrcjbxx.setTAXCONFIRMNO(baseconfirmReqinfo.getTaxConfirmNo().getTaxDealCode_Type());	// 车船税确认码
		}

		if ("R".equals(baseconfirmReqinfo.getTaxInfo()
				.getTaxConditionCode())) {
			ptzt = "4";
		} else if ("P".equals(baseconfirmReqinfo.getTaxInfo()
				.getTaxConditionCode())) {
			ptzt = "1";
		} else {
			ptzt = "0";
		}
		
		ccsqrrcjbxx.setCALCTAXFLAG(baseconfirmReqinfo.getCalcTaxFlag());		// 算税标志
		if (baseconfirmReqinfo.getVehicleInfo() != null) {
			ccsqrrcjbxx.setLICENSEPLATENO(baseconfirmReqinfo.getVehicleInfo().getLicensePlateNo());		// 号牌号码
			ccsqrrcjbxx.setLICENSEPLATETYPE(baseconfirmReqinfo.getVehicleInfo().getLicensePlateType());	// 号牌种类
			ccsqrrcjbxx.setMOTORTYPECODE(baseconfirmReqinfo.getVehicleInfo().getMotorTypeCode());		// 车辆种类
			ccsqrrcjbxx.setENGINENO(baseconfirmReqinfo.getVehicleInfo().getEngineNo());					// 发动机号
			if (baseconfirmReqinfo.getVehicleInfo().getVIN() != null
					&& baseconfirmReqinfo.getVehicleInfo().getVIN().startsWith("'")) {
				ccsqrrcjbxx.setVIN(baseconfirmReqinfo.getVehicleInfo().getVIN());						// 车架号
			} else {
				ccsqrrcjbxx.setVIN(baseconfirmReqinfo.getVehicleInfo().getVIN());						// 车架号
			}
			ccsqrrcjbxx.setMADEFACTORY(baseconfirmReqinfo.getVehicleInfo().getMadeFactory());			// 制造厂名称
			ccsqrrcjbxx.setMOTORUSAGETYPECODE(baseconfirmReqinfo.getVehicleInfo().getMotorUsageTypeCode());	// 使用性质
			ccsqrrcjbxx.setNOLICENSEFLAG(baseconfirmReqinfo.getVehicleInfo().getNoLicenseFlag());		// 未上牌车辆标志
			ccsqrrcjbxx.setMODEL(baseconfirmReqinfo.getVehicleInfo().getModel());						// 车辆型号
			
			String firstDate = baseconfirmReqinfo.getVehicleInfo().getFirstRegisterDate();
			if (firstDate != null && firstDate.length() >= 10) {
				firsttime = DateUtil.getStringDate(firstDate.substring(0, 10),null);
			} 
			ccsqrrcjbxx.setFIRSTREGISTERDATE(firsttime);
			ccsqrrcjbxx.setVEHICLETYPE(baseconfirmReqinfo.getVehicleInfo().getVehicleType());			// 交管车辆类型
			ccsqrrcjbxx.setRATEDPASSENGERCAPACITY(baseconfirmReqinfo.getVehicleInfo().getRatedPassengerCapacity());	// 核定载客数
			ccsqrrcjbxx.setTONNAGE(baseconfirmReqinfo.getVehicleInfo().getTonnage());					// 核定载质量
			ccsqrrcjbxx.setWHOLEWEIGHT(baseconfirmReqinfo.getVehicleInfo().getWholeWeight());			// 整备质量
			ccsqrrcjbxx.setDISPLACEMENT(baseconfirmReqinfo.getVehicleInfo().getDisplacement());			// 排量
			ccsqrrcjbxx.setPOWER(baseconfirmReqinfo.getVehicleInfo().getPower());						// 功率
			ccsqrrcjbxx.setFUELTYPE(baseconfirmReqinfo.getVehicleInfo().getFuelType());					// 燃料种类
		} else {
			ccsqrrcjbxx.setLICENSEPLATENO(null);			// 号牌号码
			ccsqrrcjbxx.setLICENSEPLATETYPE(null);			// 号牌种类
			ccsqrrcjbxx.setMOTORTYPECODE(null);				// 车辆种类
			ccsqrrcjbxx.setENGINENO(null);					// 发动机号
			ccsqrrcjbxx.setVIN(null);						// 车架号
			ccsqrrcjbxx.setMADEFACTORY(null);				// 制造厂名称
			ccsqrrcjbxx.setMOTORUSAGETYPECODE(null);		// 使用性质
			ccsqrrcjbxx.setNOLICENSEFLAG(null);				// 未上牌车辆标志
			ccsqrrcjbxx.setMODEL(null);						// 车辆型号
			ccsqrrcjbxx.setFIRSTREGISTERDATE(null);
			ccsqrrcjbxx.setVEHICLETYPE(null);				// 交管车辆类型
			ccsqrrcjbxx.setRATEDPASSENGERCAPACITY(0);		// 核定载客数
			ccsqrrcjbxx.setTONNAGE(0.0);					// 核定载质量
			ccsqrrcjbxx.setWHOLEWEIGHT(0.0);				// 整备质量
			ccsqrrcjbxx.setDISPLACEMENT(0.0);				// 排量
			ccsqrrcjbxx.setPOWER(0.0);						// 功率
			ccsqrrcjbxx.setFUELTYPE(null);					// 燃料种类
		}
		if (baseconfirmReqinfo.getVehicleOwnerInfo() != null) {
			ccsqrrcjbxx.setVEHICLEOWNERNAME(baseconfirmReqinfo.getVehicleOwnerInfo().getVehicleOwnerName());	// 车主名称
			ccsqrrcjbxx.setCREDENTIALNO(baseconfirmReqinfo.getVehicleOwnerInfo().getCredentialNo());			// 证件号码
			ccsqrrcjbxx.setCREDENTIALCODE(baseconfirmReqinfo.getVehicleOwnerInfo().getCredentialCode());		// 自然人证件类型
			ccsqrrcjbxx.setADDRESS(baseconfirmReqinfo.getVehicleOwnerInfo().getAddress());						// 车主地址
			ccsqrrcjbxx.setPHONENO(baseconfirmReqinfo.getVehicleOwnerInfo().getPhoneNo());						// 联系电话
		} else {
			ccsqrrcjbxx.setVEHICLEOWNERNAME(null);				// 车主名称
			ccsqrrcjbxx.setCREDENTIALNO(null);					// 证件号码
			ccsqrrcjbxx.setCREDENTIALCODE(null);				// 自然人证件类型
			ccsqrrcjbxx.setADDRESS(null);						// 车主地址
			ccsqrrcjbxx.setPHONENO(null);						// 联系电话
		}

		if (baseconfirmReqinfo.getTaxInfo() != null) {
			if (baseconfirmReqinfo.getTaxInfo().getTaxAmount() != null) {
				ccsqrrcjbxx.setTAXAMOUNT_FLAG(baseconfirmReqinfo.getTaxInfo().getTaxAmount()
								.getTaxAmount_Flag());			// 合计金额标志码
				ccsqrrcjbxx.setANNUALTAXDUE(baseconfirmReqinfo.getTaxInfo().getTaxAmount()
								.getAnnualTaxDue());			// 本年车船税金额
			} else {
				ccsqrrcjbxx.setTAXAMOUNT_FLAG(null);			// 合计金额标志码
				ccsqrrcjbxx.setANNUALTAXDUE(0.0);				// 本年车船税金额
			}
			if (baseconfirmReqinfo.getTaxInfo().getTaxConditionCode() == null
					|| "R".equals(baseconfirmReqinfo.getTaxInfo()
							.getTaxConditionCode())) {

				ccsqrrcjbxx.setSUMTAXDEFAULT(0.0);				// 合计欠税金额
				ccsqrrcjbxx.setSUMOVERDUE(0.0);					// 合计滞纳金
				ccsqrrcjbxx.setSUMTAX(0.0);						// 合计金额

			} else {
				ccsqrrcjbxx.setSUMTAXDEFAULT(baseconfirmReqinfo.getTaxInfo().getTaxAmount()
								.getSumTaxDefault());			// 合计欠税金额
				ccsqrrcjbxx.setSUMOVERDUE(baseconfirmReqinfo.getTaxInfo().getTaxAmount()
								.getSumOverdue());				// 合计滞纳金
				ccsqrrcjbxx.setSUMTAX(baseconfirmReqinfo.getTaxInfo().getTaxAmount()
								.getSumTax());					// 合计金额
			}
		} else {
			ccsqrrcjbxx.setTAXAMOUNT_FLAG(null);				// 合计金额标志码
			ccsqrrcjbxx.setANNUALTAXDUE(0.0);					// 本年车船税金额
			ccsqrrcjbxx.setSUMTAXDEFAULT(0.0);					// 合计欠税金额
			ccsqrrcjbxx.setSUMOVERDUE(0.0);						// 合计滞纳金
			ccsqrrcjbxx.setSUMTAX(0.0);							// 合计金额
		}
		ccsqrrcjbxx.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));			// 操作员
		ccsqrrcjbxx.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
		ccsqrrcjbxx.setSJCJRQ(new Date());
		ccsqrrcjbxx.setSJCJFS("0");
		if (baseconfirmReqinfo.getVehicleInfo() == null){
			ccsqrrcjbxx.setSPECIALCARTYPE(null);				// 特殊车标志
		}else{
			ccsqrrcjbxx.setSPECIALCARTYPE(baseconfirmReqinfo.getVehicleInfo().getSpecialCarType());		// 特殊车标志
		}
		ccsqrrcjbxx.setDECLAREDSTATUS(ptzt);
		ccsqrrcjbxx.setSTATUSDATE(new Date());
		ccsqrrcjbxx.setINSURESTARTDATE(baseconfirmReqinfo.getInsureStartDate());
		ccsqrrcjbxx.setINSUREENDDATE(baseconfirmReqinfo.getInsureEndDate());
		ccsqrrcjbxx.setCARMATCHID(baseconfirmReqinfo.getCarMatchId());
		
		return ccsqrrcjbxx;
	}
}
