package com.derun.taxchangequery.dao.impl;

import java.util.Date;

import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSBGCXRCJB;

/**
 * @author MILI
 * @time 2014-3-20 17:05:36
 * @描述：插入对象 SYJK_CCS_CCSBGCXRCJB 封装
 * */
public class P_SYJK_CCS_CCSBGCXRCJB {
	/**
	 * @author MILI
	 * @time 2014-3-20 17:05:36
	 * @描述：插入对象 SYJK_CCS_CCSBGCXRCJB 封装
	 * @入参：交易码数据类型 、请求信息对象入参
	 * @出参：SYJK_CCS_CCSBGCXRCJB 车船税变更查询入参
	 * */
	public SYJK_CCS_CCSBGCXRCJB getCcsbgcxrc(TaxDealCode_Type ChangeQueryNo,
			BaseChangeQueryReqInfo basechangequeryreqinfo){
		SYJK_CCS_CCSBGCXRCJB ccsbgcxrc = new SYJK_CCS_CCSBGCXRCJB();
		ccsbgcxrc.setTAXQUERYNO(ChangeQueryNo.getTaxDealCode_Type());	// 重新生成的变更查询码
		ccsbgcxrc.setTAXCONFIRMNO(basechangequeryreqinfo
				.getTaxConfirmNo().getTaxDealCode_Type());				// 车船税确认码（入参）
		ccsbgcxrc.setCHANGETYPE(basechangequeryreqinfo
				.getChangeType());							// 变更类型
		ccsbgcxrc.setLICENSEPLATENO(basechangequeryreqinfo
				.getVehicleInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleInfo().getLicensePlateNo());		// 号牌号码
		ccsbgcxrc.setLICENSEPLATETYPE(basechangequeryreqinfo
				.getVehicleInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleInfo().getLicensePlateType());	// 号牌种类
		ccsbgcxrc.setMOTORTYPECODE(basechangequeryreqinfo
				.getVehicleInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleInfo().getMotorTypeCode());		// 车辆种类
		ccsbgcxrc.setENGINENO(basechangequeryreqinfo
				.getVehicleInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleInfo().getEngineNo());			// 发动机号
		ccsbgcxrc
				.setVIN(basechangequeryreqinfo.getVehicleInfo() == null ? ""
						: basechangequeryreqinfo.getVehicleInfo().getVIN());	// 车架号
		ccsbgcxrc.setMADEFACTORY(basechangequeryreqinfo
				.getVehicleInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleInfo().getMadeFactory());		// 制造厂名称
		ccsbgcxrc.setMOTORUSAGETYPECODE(basechangequeryreqinfo
				.getVehicleInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleInfo().getMotorUsageTypeCode());	// 使用性质
		ccsbgcxrc.setNOLICENSEFLAG(basechangequeryreqinfo
				.getVehicleInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleInfo().getNoLicenseFlag());		// 未上牌车辆标志
		ccsbgcxrc
				.setMODEL(basechangequeryreqinfo.getVehicleInfo() == null ? ""
						: basechangequeryreqinfo.getVehicleInfo().getModel());	// 车辆型号
		ccsbgcxrc.setFIRSTREGISTERDATE(DateUtil.getStringDate(basechangequeryreqinfo
				.getVehicleInfo().getFirstRegisterDate(),null));	// 车辆初始登记日期,Y
		ccsbgcxrc.setVEHICLETYPE(basechangequeryreqinfo
				.getVehicleInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleInfo().getVehicleType());		// 交管车辆类型,Y
		ccsbgcxrc.setRATEDPASSENGERCAPACITY(basechangequeryreqinfo
				.getVehicleInfo() == null ? 0.0 : basechangequeryreqinfo
				.getVehicleInfo().getRatedPassengerCapacity());	// 核定载客数,Y
		ccsbgcxrc.setTONNAGE(basechangequeryreqinfo
				.getVehicleInfo() == null ? 0 : basechangequeryreqinfo
				.getVehicleInfo().getTonnage());				// 核定载质量,Y
		ccsbgcxrc.setWHOLEWEIGHT(basechangequeryreqinfo
				.getVehicleInfo() == null ? 0 : basechangequeryreqinfo
				.getVehicleInfo().getWholeWeight());			// 整备质量,Y
		ccsbgcxrc.setDISPLACEMENT(basechangequeryreqinfo
				.getVehicleInfo() == null ? 0 : basechangequeryreqinfo
				.getVehicleInfo().getDisplacement());			// 排量 ,Y
		ccsbgcxrc.setPOWER(basechangequeryreqinfo.getVehicleInfo() == null ? 0
						: basechangequeryreqinfo.getVehicleInfo().getPower());	// 功率
		ccsbgcxrc.setFUELTYPE(basechangequeryreqinfo
				.getVehicleInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleInfo().getFuelType());				// 燃料种类
		ccsbgcxrc.setVEHICLEOWNERNAME(basechangequeryreqinfo
				.getVehicleOwnerInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleOwnerInfo().getVehicleOwnerName());	// 车主名称
		ccsbgcxrc.setCREDENTIALCODE(basechangequeryreqinfo
				.getVehicleOwnerInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleOwnerInfo().getCredentialNo());		// 证件号码
		ccsbgcxrc.setCREDENTIALCODE(basechangequeryreqinfo
				.getVehicleOwnerInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleOwnerInfo().getCredentialCode());	// 自然人证件类型
		ccsbgcxrc.setADDRESS(basechangequeryreqinfo
				.getVehicleOwnerInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleOwnerInfo().getAddress());			// 车主地址
		ccsbgcxrc.setPHONENO(basechangequeryreqinfo
				.getVehicleOwnerInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleOwnerInfo().getPhoneNo());			// 联系电话
		if (basechangequeryreqinfo.getTaxInfo() != null) {// 判断车船税信息是否为空
			// 判断车船税信息中,车船税合计金额数据类型是否为空
			if (basechangequeryreqinfo.getTaxInfo().getTaxAmount() != null) {
				ccsbgcxrc.setTAXAMOUNT_FLAG("3");	// 合计金额标志码,Y
				ccsbgcxrc.setANNUALTAXDUE(basechangequeryreqinfo
						.getTaxInfo().getTaxAmount() == null ? 0.00
						: basechangequeryreqinfo.getTaxInfo().getTaxAmount()
								.getAnnualTaxDue());			// 本年车船税金额
				ccsbgcxrc.setSUMTAXDEFAULT(basechangequeryreqinfo
						.getTaxInfo().getTaxAmount() == null ? 0.00
						: basechangequeryreqinfo.getTaxInfo().getTaxAmount()
								.getSumTaxDefault());			// 合计欠税金额
				ccsbgcxrc.setSUMOVERDUE(basechangequeryreqinfo
						.getTaxInfo().getTaxAmount() == null ? 0.00
						: basechangequeryreqinfo.getTaxInfo().getTaxAmount()
								.getSumOverdue());				// 合计滞纳金
				ccsbgcxrc.setSUMTAX(basechangequeryreqinfo
						.getTaxInfo().getTaxAmount() == null ? 0.00
						: basechangequeryreqinfo.getTaxInfo().getTaxAmount()
								.getSumTax());					// 合计金额,Y
			} else {	// 判断车船税信息中,车船税合计金额数据类型为空,设置默认值
				ccsbgcxrc.setTAXAMOUNT_FLAG("3");	// 合计金额标志码,Y
				ccsbgcxrc.setANNUALTAXDUE(0.00);	// 本年车船税金额
				ccsbgcxrc.setSUMTAXDEFAULT(0.00);	// 合计欠税金额
				ccsbgcxrc.setSUMOVERDUE(0.00);		// 合计滞纳金
				ccsbgcxrc.setSUMTAX(0.00);			// 合计金额,Y
			}
		} else {	// 判断车船税信息为空,,设置默认值
			ccsbgcxrc.setTAXAMOUNT_FLAG("3");		// 合计金额标志码,Y
			ccsbgcxrc.setANNUALTAXDUE(0.0);		// 本年车船税金额
			ccsbgcxrc.setSUMTAXDEFAULT(0.0);		// 合计欠税金额
			ccsbgcxrc.setSUMOVERDUE(0.0);			// 合计滞纳金
			ccsbgcxrc.setSUMTAX(0.0);				// 合计金额,Y
		}
		ccsbgcxrc.setSPECIALCARTYPE(basechangequeryreqinfo
				.getVehicleInfo() == null ? "" : basechangequeryreqinfo
				.getVehicleInfo().getSpecialCarType());			// 特殊车标志
		return ccsbgcxrc ;
	}
	/**
	 * @author MILI
	 * @time 2014-3-20 17:05:36
	 * @描述：插入数据库  SYJK_CCS_CCSBGCXRCJB 封装
	 * @入参：BaseChangeQueryReqInfo、TaxDealCode_Type
	 * @出参：SYJK_CCS_CCSBGCXRCJB 车船税变更查询入参
	 * */
	public SYJK_CCS_CCSBGCXRCJB getCcsbgcxrc(BaseChangeQueryReqInfo basechangequeryreqinfo,TaxDealCode_Type ChangeQueryNo){
		SYJK_CCS_CCSBGCXRCJB baseChangeReq = this.getCcsbgcxrc(ChangeQueryNo,basechangequeryreqinfo);
		String FirstRegisterDate = DateUtil.getStringDate(baseChangeReq.getFIRSTREGISTERDATE(),null);
		if (baseChangeReq.getFIRSTREGISTERDATE() != null
				&& FirstRegisterDate.length() >= 10) {
			String fe = FirstRegisterDate.substring(0, 10);
			FirstRegisterDate = fe;
		} else {
			FirstRegisterDate = "";
		}
		SYJK_CCS_CCSBGCXRCJB _gbrc = new SYJK_CCS_CCSBGCXRCJB();
		_gbrc.setTAXQUERYNO(baseChangeReq.getTAXQUERYNO());
		_gbrc.setTAXCONFIRMNO(baseChangeReq.getTAXCONFIRMNO());
		_gbrc.setCHANGETYPE(baseChangeReq.getCHANGETYPE());
		_gbrc.setLICENSEPLATENO(baseChangeReq.getLICENSEPLATENO());
		_gbrc.setLICENSEPLATETYPE(baseChangeReq.getLICENSEPLATETYPE());
		_gbrc.setMOTORTYPECODE(baseChangeReq.getMOTORTYPECODE());
		_gbrc.setENGINENO(baseChangeReq.getENGINENO());
		_gbrc.setVIN(baseChangeReq.getVIN());
		_gbrc.setMADEFACTORY(baseChangeReq.getMADEFACTORY());
		_gbrc.setMOTORUSAGETYPECODE(baseChangeReq.getMOTORUSAGETYPECODE());
		_gbrc.setNOLICENSEFLAG(baseChangeReq.getNOLICENSEFLAG());
		_gbrc.setMODEL(baseChangeReq.getMODEL());
		_gbrc.setFIRSTREGISTERDATE(DateUtil.getStringDate(FirstRegisterDate,null));
		_gbrc.setVEHICLETYPE(baseChangeReq.getVEHICLETYPE());
		_gbrc.setRATEDPASSENGERCAPACITY(baseChangeReq.getRATEDPASSENGERCAPACITY());
		_gbrc.setTONNAGE(baseChangeReq.getTONNAGE());
		_gbrc.setWHOLEWEIGHT(baseChangeReq.getWHOLEWEIGHT());
		_gbrc.setDISPLACEMENT(baseChangeReq.getDISPLACEMENT());
		_gbrc.setPOWER(baseChangeReq.getPOWER());
		_gbrc.setFUELTYPE(baseChangeReq.getFUELTYPE());
		_gbrc.setVEHICLEOWNERNAME(baseChangeReq.getVEHICLEOWNERNAME());
		_gbrc.setCERTICODE(baseChangeReq.getCERTICODE());
		_gbrc.setCREDENTIALCODE(baseChangeReq.getCREDENTIALCODE());
		_gbrc.setADDRESS(baseChangeReq.getADDRESS());
		_gbrc.setPHONENO(baseChangeReq.getPHONENO());
		_gbrc.setTAXAMOUNT_FLAG(baseChangeReq.getTAXAMOUNT_FLAG());
		_gbrc.setANNUALTAXDUE(baseChangeReq.getANNUALTAXDUE());
		_gbrc.setSUMTAXDEFAULT(baseChangeReq.getSUMTAXDEFAULT());
		_gbrc.setSUMOVERDUE(baseChangeReq.getSUMOVERDUE());
		_gbrc.setSUMTAX(baseChangeReq.getSUMTAX());
		_gbrc.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username")); 
		_gbrc.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
		_gbrc.setSJCJRQ(new Date());
		_gbrc.setSPECIALCARTYPE(baseChangeReq.getSPECIALCARTYPE());
		if (basechangequeryreqinfo.getInsureStartDate() != null) {
			_gbrc.setINSURESTARTDATE(basechangequeryreqinfo.getInsureStartDate());
		} else {
			_gbrc.setINSURESTARTDATE(null);
		}
		if (basechangequeryreqinfo.getInsureEndDate() != null) {
			_gbrc.setINSUREENDDATE(basechangequeryreqinfo.getInsureEndDate());
		} else {
			_gbrc.setINSUREENDDATE(null);
		}
		if (basechangequeryreqinfo.getCarMatchId() != null && !"".equals(basechangequeryreqinfo.getCarMatchId())) {
			_gbrc.setCARMATCHID(basechangequeryreqinfo.getCarMatchId());
		} else {
			_gbrc.setCARMATCHID(null);
		}
		return _gbrc ;
	}
}
