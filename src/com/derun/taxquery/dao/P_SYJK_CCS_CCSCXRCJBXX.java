package com.derun.taxquery.dao;

import java.util.Date;

import com.derun.beans.BaseQueryReqInfo;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSCXRCJBXX;
/**
 * @author MILI
 * @time 2014-3-18 15:46:18
 * @描述：插入对象 CCSCXRCJBXX 封装
 * */
public class P_SYJK_CCS_CCSCXRCJBXX {
	/**
	 * @author MILI
	 * @time 2014-3-18 15:46:18
	 * @描述：插入对象 CCSCXRCJBXX 封装   投保查询入参
	 * */
	public SYJK_CCS_CCSCXRCJBXX getCcscxrcjbxx(BaseQueryReqInfo basequeryreqinfo, String confirmN){
		SYJK_CCS_CCSCXRCJBXX ccscxrcjbxx  = new SYJK_CCS_CCSCXRCJBXX();
		Date firsttime = null , querytime = null ;
		// 车辆初登日期
		String datetime = basequeryreqinfo.getVehicleInfo() == null ? "" : basequeryreqinfo.getVehicleInfo().getFirstRegisterDate();
		if (datetime != null && datetime.length() >= 10) {
			firsttime = DateUtil.getStringDate(datetime.substring(0, 10),null);
		}
		// 投保查询日期
		String quereyTime = basequeryreqinfo.getQuerySequencetTime();   
		if (quereyTime != null && quereyTime.length() >= 10) {
			querytime = DateUtil.getStringDate(quereyTime.substring(0, 10),null);
		} else {
			querytime = new Date();
		}
		
		ccscxrcjbxx.setTAXQUERYNO(confirmN);														// 车船税查询码,唯一标示符
		ccscxrcjbxx.setLICENSEPLATENO(basequeryreqinfo.getVehicleInfo().getLicensePlateNo());		// 号牌号码
		ccscxrcjbxx.setLICENSEPLATETYPE(basequeryreqinfo.getVehicleInfo().getLicensePlateType());	// 号牌种类
		ccscxrcjbxx.setMOTORTYPECODE(basequeryreqinfo.getVehicleInfo().getMotorTypeCode());			// 车辆种类,Y
		ccscxrcjbxx.setENGINENO(basequeryreqinfo.getVehicleInfo().getEngineNo());					// 发动机号,Y
		ccscxrcjbxx.setVIN(basequeryreqinfo.getVehicleInfo().getVIN());								// 车架号,Y
		ccscxrcjbxx.setMADEFACTORY(basequeryreqinfo.getVehicleInfo().getMadeFactory());				// 制造厂名称
		ccscxrcjbxx.setMOTORUSAGETYPECODE(basequeryreqinfo.getVehicleInfo().getMotorUsageTypeCode());	// 使用性质,Y
		ccscxrcjbxx.setNOLICENSEFLAG(basequeryreqinfo.getVehicleInfo().getNoLicenseFlag());			// 未上牌车辆标志,Y
		ccscxrcjbxx.setMODEL(basequeryreqinfo.getVehicleInfo().getModel());							// 车辆型号
		ccscxrcjbxx.setFIRSTREGISTERDATE(firsttime);												// 车辆初始登记日期,Y
		ccscxrcjbxx.setVEHICLETYPE(basequeryreqinfo.getVehicleInfo().getVehicleType());				// 交管车辆类型,Y
		ccscxrcjbxx.setRATEDPASSENGERCAPACITY(basequeryreqinfo.getVehicleInfo().getRatedPassengerCapacity());		// 核定载客数,
		ccscxrcjbxx.setTONNAGE(basequeryreqinfo.getVehicleInfo().getTonnage());						// 核定载质量,Y
		ccscxrcjbxx.setWHOLEWEIGHT(basequeryreqinfo.getVehicleInfo().getWholeWeight());				// 整备质量,Y
		ccscxrcjbxx.setDISPLACEMENT(basequeryreqinfo.getVehicleInfo().getDisplacement());			// 排量 ,Y
		ccscxrcjbxx.setPOWER(basequeryreqinfo.getVehicleInfo().getPower());							// 功率//
		ccscxrcjbxx.setFUELTYPE(basequeryreqinfo.getVehicleInfo().getFuelType());					// 燃料种类
		ccscxrcjbxx.setTAXAMOUNT_FLAG(basequeryreqinfo.getTaxInfo().getTaxAmount().getTaxAmount_Flag());			// 合计金额标志码,Y
		ccscxrcjbxx.setANNUALTAXDUE(basequeryreqinfo.getTaxInfo().getTaxAmount().getAnnualTaxDue());// 本年车船税金额
		ccscxrcjbxx.setSUMTAXDEFAULT(basequeryreqinfo.getTaxInfo().getTaxAmount().getSumTaxDefault());	// 合计欠税金额
		ccscxrcjbxx.setSUMOVERDUE(basequeryreqinfo.getTaxInfo().getTaxAmount().getSumOverdue());	// 合计滞纳金
		ccscxrcjbxx.setSUMTAX(basequeryreqinfo.getTaxInfo().getTaxAmount().getSumTax());			// 合计金额,Y
		ccscxrcjbxx.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));				// 操作员
		ccscxrcjbxx.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
		ccscxrcjbxx.setSJCJRQ(DateUtil.getDateDate());												// 数据采集日期
		ccscxrcjbxx.setSJCJFS("0");																	// 数据采集方式
		ccscxrcjbxx.setSPECIALCARTYPE(basequeryreqinfo.getVehicleInfo().getSpecialCarType());		// 特殊车标志
		ccscxrcjbxx.setQUERYSEQUENCETTIME(querytime);												// 投保查询日期
		ccscxrcjbxx.setINSURESTARTDATE(basequeryreqinfo.getInsureStartDate());						// 投保开始日期
		ccscxrcjbxx.setINSUREENDDATE(basequeryreqinfo.getInsureEndDate());							// 投保结束日期
		ccscxrcjbxx.setCARMATCHID(basequeryreqinfo.getCarMatchId());
		return ccscxrcjbxx;
	}
}
