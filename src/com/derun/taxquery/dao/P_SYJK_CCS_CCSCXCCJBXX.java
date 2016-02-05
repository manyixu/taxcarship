package com.derun.taxquery.dao;

import java.util.Date;

import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.Tax_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
/**
 * @author MILI
 * @time 2014-3-18 15:46:18
 * @描述：插入对象 SYJK_CCS_CCSCXCCJBXX 封装
 * */
public class P_SYJK_CCS_CCSCXCCJBXX {
	static String YMD = "yyyy-MM-dd hh:mm:ss" ;
	/**
	 * @author MILI
	 * @time 2014-3-18 15:46:18
	 * @描述：插入对象 SYJK_CCS_CCSCXCCJBXX 封装     投保查询出参
	 * */
	public SYJK_CCS_CCSCXCCJBXX getCcscxccjbxx(Tax_Type taxInfo,String carSerialNo, String confirmN,BaseQueryReqInfo basequeryreqinfo,String carMatchId,String isInsert){
		SYJK_CCS_CCSCXCCJBXX ccscxccjbxx  = new SYJK_CCS_CCSCXCCJBXX();
		Date first = null ;
		String FIRSTREGISTERDATE = basequeryreqinfo.getVehicleInfo().getFirstRegisterDate();
		if (FIRSTREGISTERDATE != null && FIRSTREGISTERDATE.length() >= 10) {
			first = DateUtil.getStringDate(FIRSTREGISTERDATE.substring(0, 10),null);
		}
		ccscxccjbxx.setTAXQUERYNO(confirmN);		
		ccscxccjbxx.setCALCTAXFLAG("1");
		ccscxccjbxx.setTAXAMOUNT_FLAG("1");
		if (taxInfo.getTaxAmount() != null) {
			ccscxccjbxx.setANNUALTAXDUE(taxInfo.getTaxAmount().getAnnualTaxDue());
			ccscxccjbxx.setSUMTAXDEFAULT(taxInfo.getTaxAmount().getSumTaxDefault());
			ccscxccjbxx.setSUMOVERDUE(taxInfo.getTaxAmount().getSumOverdue());
			ccscxccjbxx.setSUMTAX(taxInfo.getTaxAmount().getSumTax());
		} else {
			ccscxccjbxx.setANNUALTAXDUE(0.0);
			ccscxccjbxx.setSUMTAXDEFAULT(0.0);
			ccscxccjbxx.setSUMOVERDUE(0.0);
			ccscxccjbxx.setSUMTAX(0.0);
		}
		ccscxccjbxx.setRETURNCODE("1");
		ccscxccjbxx.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));				// 操作员
		ccscxccjbxx.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
		ccscxccjbxx.setSJCJRQ(DateUtil.getStringDate(new Date(),YMD));
		ccscxccjbxx.setSJCJFS("0");
		ccscxccjbxx.setVIN(basequeryreqinfo.getVehicleInfo().getVIN());
		ccscxccjbxx.setHPHM(basequeryreqinfo.getVehicleInfo().getLicensePlateNo());
		ccscxccjbxx.setHPZL(basequeryreqinfo.getVehicleInfo().getLicensePlateType());
		ccscxccjbxx.setCLLX(basequeryreqinfo.getVehicleInfo().getMotorTypeCode());
		
		ccscxccjbxx.setMOTORUSAGETYPECODE(basequeryreqinfo.getVehicleInfo().getMotorUsageTypeCode()); 	// 使用性质
		ccscxccjbxx.setMODEL(basequeryreqinfo.getVehicleInfo().getModel()); 					// 车辆型号
		ccscxccjbxx.setVEHICLETYPE(basequeryreqinfo.getVehicleInfo().getVehicleType()); 		// 交管车辆类型
		ccscxccjbxx.setRATEDPASSENGERCAPACITY(basequeryreqinfo.getVehicleInfo().getRatedPassengerCapacity());// 核定载客数
		ccscxccjbxx.setTONNAGE(basequeryreqinfo.getVehicleInfo().getTonnage());				// 核定载质量
		ccscxccjbxx.setWHOLEWEIGHT(basequeryreqinfo.getVehicleInfo().getWholeWeight());		// 整备质量
		ccscxccjbxx.setDISPLACEMENT(basequeryreqinfo.getVehicleInfo().getDisplacement());	// 排量
		ccscxccjbxx.setPOWER(basequeryreqinfo.getVehicleInfo().getPower());					// 功率
		ccscxccjbxx.setFUELTYPE(basequeryreqinfo.getVehicleInfo().getFuelType()); 			// 源种类
		ccscxccjbxx.setMOTORTYPECODE(basequeryreqinfo.getVehicleInfo().getMotorTypeCode());		// 车辆种类
		ccscxccjbxx.setMADEFACTORY(basequeryreqinfo.getVehicleInfo().getMadeFactory());			// 制造厂名称
		ccscxccjbxx.setNOLICENSEFLAG(basequeryreqinfo.getVehicleInfo().getNoLicenseFlag());		// 未上牌车辆标志
		
		
		ccscxccjbxx.setFIRSTREGISTERDATE(first);
		ccscxccjbxx.setSPECIALCARTYPE(basequeryreqinfo.getVehicleInfo().getSpecialCarType());
		ccscxccjbxx.setTSBZ("0");
		ccscxccjbxx.setCARSERIALNO(carSerialNo);
		ccscxccjbxx.setENGINENO(basequeryreqinfo.getVehicleInfo().getEngineNo());
		ccscxccjbxx.setCARMATCHID(carMatchId);
		ccscxccjbxx.setINSURESTARTDATE(basequeryreqinfo.getInsureStartDate());
		ccscxccjbxx.setINSUREENDDATE(basequeryreqinfo.getInsureEndDate());
		ccscxccjbxx.setISINSERT(isInsert);
		return ccscxccjbxx ;
	}
}
