package com.derun.taxchangequery.dao.impl;

import java.util.Date;

import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.BaseChangeQueryResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.Car_Id_No;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-3-20 17:05:36
 * @描述：插入对象 SYJK_CCS_CCSBGCXCCJB 封装
 * */
public class P_SYJK_CCS_CCSBGCXCCJB {
	/**
	 * @author MILI
	 * @time 2014-3-20 17:05:36
	 * @描述：插入对象 SYJK_CCS_CCSBGCXCCJB 封装
	 * @入参：问题序列号、入库明细、车船税数据类型、入参、交易码数据类型、返回码
	 * @出参：SYJK_CCS_CCSBGCXCCJB 
	 * */
	public SYJK_CCS_CCSBGCXCCJB getCcsbgcxccjb_(String bcqrs,SYJK_CCS_RKMX rkmx,Tax_Type taxInfo,
			BaseChangeQueryReqInfo basechangequeryreqinfo,TaxDealCode_Type dealcode,String returnCode){
		SYJK_CCS_CCSBGCXCCJB ccsbgcxcc = new SYJK_CCS_CCSBGCXCCJB();
		ccsbgcxcc.setCARSERIALNO(bcqrs);					// 车辆序列号
		
		ccsbgcxcc.setTAXQUERYNO(dealcode.getTaxDealCode_Type());
		ccsbgcxcc.setCALCTAXFLAG("1");
		ccsbgcxcc.setRETURNCODE(returnCode);
		if ("1".equals(basechangequeryreqinfo.getChangeType())) {
			ccsbgcxcc.setVIN(basechangequeryreqinfo
					.getVehicleInfo().getVIN());
			ccsbgcxcc.setHPHM(basechangequeryreqinfo
					.getVehicleInfo().getLicensePlateNo());
			ccsbgcxcc.setENGINENO(basechangequeryreqinfo.getVehicleInfo().getEngineNo());			// 发动机号
			if (basechangequeryreqinfo.getVehicleInfo()
					.getLicensePlateType() != null
					&& !"".equals(basechangequeryreqinfo
							.getVehicleInfo().getLicensePlateType())) {
				ccsbgcxcc.setHPZL(basechangequeryreqinfo
						.getVehicleInfo().getLicensePlateType());
			} else {
				ccsbgcxcc.setHPZL(rkmx.getHPZL());
			}
			ccsbgcxcc.setCLLX(basechangequeryreqinfo
					.getVehicleInfo().getMotorTypeCode());
			
			ccsbgcxcc.setMOTORUSAGETYPECODE(basechangequeryreqinfo
					.getVehicleInfo().getMotorUsageTypeCode()); 			// 使用性质
			ccsbgcxcc.setMODEL(basechangequeryreqinfo
					.getVehicleInfo().getModel()); 							// 车辆型号
			ccsbgcxcc.setVEHICLETYPE(basechangequeryreqinfo
					.getVehicleInfo().getVehicleType()); 					// 交管车辆类型
			ccsbgcxcc.setRATEDPASSENGERCAPACITY(basechangequeryreqinfo
					.getVehicleInfo().getRatedPassengerCapacity());			// 核定载客数
			ccsbgcxcc.setTONNAGE(basechangequeryreqinfo
					.getVehicleInfo().getTonnage());						// 核定载质量
			ccsbgcxcc.setWHOLEWEIGHT(basechangequeryreqinfo
					.getVehicleInfo().getWholeWeight());					// 整备质量
			ccsbgcxcc.setDISPLACEMENT(basechangequeryreqinfo
					.getVehicleInfo().getDisplacement());					// 排量
			ccsbgcxcc.setPOWER(basechangequeryreqinfo
					.getVehicleInfo().getPower());							// 功率
			ccsbgcxcc.setFUELTYPE(basechangequeryreqinfo
					.getVehicleInfo().getFuelType()); 						// 源种类
			
			ccsbgcxcc.setMOTORTYPECODE(basechangequeryreqinfo
					.getVehicleInfo().getMotorTypeCode()) ;
			ccsbgcxcc.setMADEFACTORY(basechangequeryreqinfo
					.getVehicleInfo().getMadeFactory());
			ccsbgcxcc.setNOLICENSEFLAG(basechangequeryreqinfo
					.getVehicleInfo().getNoLicenseFlag());
			
			ccsbgcxcc
					.setFIRSTREGISTERDATE(DateUtil.getStringDate(basechangequeryreqinfo
							.getVehicleInfo().getFirstRegisterDate(),null));
			ccsbgcxcc
					.setSPECIALCARTYPE(basechangequeryreqinfo
							.getVehicleInfo().getSpecialCarType());
		} else {
			ccsbgcxcc.setVIN(rkmx.getVIN());
			ccsbgcxcc.setHPHM(rkmx.getHPHM());
			ccsbgcxcc.setHPZL(rkmx.getHPZL());
			ccsbgcxcc.setCLLX(rkmx.getCLLX());
			ccsbgcxcc.setENGINENO(rkmx.getEngineNo());
			ccsbgcxcc.setMOTORUSAGETYPECODE(rkmx.getMOTORUSAGETYPECODE()); 	// 使用性质
			ccsbgcxcc.setMODEL(rkmx.getMODEL()); 							// 车辆型号
			ccsbgcxcc.setVEHICLETYPE(rkmx.getVEHICLETYPE()); 				// 交管车辆类型
			ccsbgcxcc.setRATEDPASSENGERCAPACITY(rkmx.getRATEDPASSENGERCAPACITY());// 核定载客数
			ccsbgcxcc.setTONNAGE(rkmx.getTONNAGE());						// 核定载质量
			ccsbgcxcc.setWHOLEWEIGHT(rkmx.getWHOLEWEIGHT());				// 整备质量
			ccsbgcxcc.setDISPLACEMENT(rkmx.getDISPLACEMENT());				// 排量
			ccsbgcxcc.setPOWER(rkmx.getPOWER());							// 功率
			ccsbgcxcc.setFUELTYPE(rkmx.getFUELTYPE()); 						// 源种类
			
			ccsbgcxcc.setFIRSTREGISTERDATE(DateUtil.getStringDate(rkmx.getFIRSTREGISTERDATE(),null));
			ccsbgcxcc.setSPECIALCARTYPE("");
		}
		if (basechangequeryreqinfo.getChangeType().equals("8")
				|| basechangequeryreqinfo.getChangeType().equals("10")) {
			ccsbgcxcc.setTSBZ("2");
		} else if (basechangequeryreqinfo.getChangeType().equals("9")
				|| basechangequeryreqinfo.getChangeType().equals("11")
				|| basechangequeryreqinfo.getChangeType().equals("12")
				|| basechangequeryreqinfo.getChangeType().equals("13")
				|| basechangequeryreqinfo.getChangeType().equals("14")) {
			ccsbgcxcc.setTSBZ("1");
		} else {
			ccsbgcxcc.setTSBZ("0");
		}
		ccsbgcxcc.setTAXAMOUNT_FL("3");
		if(taxInfo != null && taxInfo.getTaxAmount() != null){
			ccsbgcxcc.setSUMTAXDEFAULT(taxInfo.getTaxAmount()
					.getSumTaxDefault());
			ccsbgcxcc.setSUMOVERDUE(taxInfo.getTaxAmount()
					.getSumOverdue());
			ccsbgcxcc.setSUMTAX(taxInfo.getTaxAmount()
					.getSumTax());
			ccsbgcxcc.setANNUALTAXDUE(taxInfo.getTaxAmount()
					.getAnnualTaxDue());
		}else{
			ccsbgcxcc.setSUMTAXDEFAULT(0.0);
			ccsbgcxcc.setSUMOVERDUE(0.0);
			ccsbgcxcc.setSUMTAX(0.0);
			ccsbgcxcc.setANNUALTAXDUE(0.0);
		}
		
		return ccsbgcxcc ;
	}
	/**
	 * @author MILI
	 * @time 2014-3-20 17:05:36
	 * @描述：入库对象 SYJK_CCS_CCSBGCXCCJB 封装
	 * @入参：.....
	 * @出参：SYJK_CCS_CCSBGCXCCJB 
	 * */
	public SYJK_CCS_CCSBGCXCCJB getCcsbgcxccjb(BaseChangeQueryReqInfo bcqri,BaseChangeQueryResInfo bcqrs,String tbqrm
			,SYJK_CCS_RKMX rkmx,Tax_Type taxInfo,TaxDealCode_Type dealcode,String returnCode,Car_Id_No cin,String isInsert){
//		String carSerialNo = bcqrs.getCarSerialNo();		// 问题名单序列号
		String carSerialNo = "" ;
		if(cin != null){
			carSerialNo = cin.getCarSerialNo();
		}
		double heji = 0.0 ;
		if(taxInfo != null && taxInfo.getTaxAmount() != null){
			heji = rkmx.getTAXDUE() + taxInfo.getTaxAmount().getSumTax();
		}
		SYJK_CCS_CCSBGCXCCJB ccsbgcxccjb = this.getCcsbgcxccjb_(carSerialNo, rkmx, taxInfo, bcqri, dealcode, returnCode);
		SYJK_CCS_CCSBGCXCCJB _ccsbgcxcc = new SYJK_CCS_CCSBGCXCCJB();
		_ccsbgcxcc.setTAXQUERYNO(ccsbgcxccjb.getTAXQUERYNO());
		_ccsbgcxcc.setCALCTAXFLAG(ccsbgcxccjb.getCALCTAXFLAG());
		_ccsbgcxcc.setTAXAMOUNT_FL(ccsbgcxccjb.getTAXAMOUNT_FL());
		_ccsbgcxcc.setANNUALTAXDUE(ccsbgcxccjb.getANNUALTAXDUE());
		_ccsbgcxcc.setSUMTAXDEFAULT(ccsbgcxccjb.getSUMTAXDEFAULT());
		_ccsbgcxcc.setSUMOVERDUE(ccsbgcxccjb.getSUMOVERDUE());
		_ccsbgcxcc.setSUMTAX(ccsbgcxccjb.getSUMTAX());
		_ccsbgcxcc.setRETURNCODE(ccsbgcxccjb.getRETURNCODE());
		_ccsbgcxcc.setTAXCONFIRMNO(tbqrm);
		_ccsbgcxcc.setCHANGESUMTAX(heji);
		_ccsbgcxcc.setLOGINNAME(CfgLoader.getConfigValue("SysCode_USER", "username"));
		_ccsbgcxcc.setREVENUECODE(CfgLoader.getConfigValue("SysCode_USER", "usercode"));
		_ccsbgcxcc.setSJCJRQ(new Date());
		_ccsbgcxcc.setSJCJFS("0");
		_ccsbgcxcc.setVIN(ccsbgcxccjb.getVIN()); 
		_ccsbgcxcc.setHPHM(ccsbgcxccjb.getHPHM());
		_ccsbgcxcc.setHPZL(ccsbgcxccjb.getHPZL());
		_ccsbgcxcc.setCLLX(ccsbgcxccjb.getCLLX());
		
		_ccsbgcxcc.setMOTORUSAGETYPECODE(ccsbgcxccjb.getMOTORUSAGETYPECODE()); 	// 使用性质
		_ccsbgcxcc.setMODEL(ccsbgcxccjb.getMODEL()); 							// 车辆型号
		_ccsbgcxcc.setVEHICLETYPE(ccsbgcxccjb.getVEHICLETYPE()); 				// 交管车辆类型
		_ccsbgcxcc.setRATEDPASSENGERCAPACITY(ccsbgcxccjb.getRATEDPASSENGERCAPACITY());// 核定载客数
		_ccsbgcxcc.setTONNAGE(ccsbgcxccjb.getTONNAGE());						// 核定载质量
		_ccsbgcxcc.setWHOLEWEIGHT(ccsbgcxccjb.getWHOLEWEIGHT());				// 整备质量
		_ccsbgcxcc.setDISPLACEMENT(ccsbgcxccjb.getDISPLACEMENT());				// 排量
		_ccsbgcxcc.setPOWER(ccsbgcxccjb.getPOWER());							// 功率
		_ccsbgcxcc.setFUELTYPE(ccsbgcxccjb.getFUELTYPE()); 						// 源种类
		
		_ccsbgcxcc.setMOTORTYPECODE(ccsbgcxccjb.getMOTORTYPECODE()) ;
		_ccsbgcxcc.setMADEFACTORY(ccsbgcxccjb.getMADEFACTORY());
		_ccsbgcxcc.setNOLICENSEFLAG(ccsbgcxccjb.getNOLICENSEFLAG());
		_ccsbgcxcc.setISINSERT(isInsert);
		
		_ccsbgcxcc.setFIRSTREGISTERDATE(ccsbgcxccjb.getFIRSTREGISTERDATE());
		_ccsbgcxcc.setSPECIALCARTYPE(ccsbgcxccjb.getSPECIALCARTYPE());
		_ccsbgcxcc.setTSBZ(ccsbgcxccjb.getTSBZ());
		_ccsbgcxcc.setCARSERIALNO(ccsbgcxccjb.getCARSERIALNO());	// 车辆序列号
		_ccsbgcxcc.setENGINENO(ccsbgcxccjb.getENGINENO());			// 车辆发动机号
		// 投保开始日期存在
		if (bcqri.getInsureStartDate() != null) {
			_ccsbgcxcc.setINSURESTARTDATE(bcqri.getInsureStartDate());
		} else {
			_ccsbgcxcc.setINSURESTARTDATE(null);
		}
		if (bcqri.getInsureEndDate() != null) {
			_ccsbgcxcc.setINSUREENDDATE(bcqri.getInsureEndDate());
		} else {
			_ccsbgcxcc.setINSUREENDDATE(null);
		}
		_ccsbgcxcc.setCARMATCHID(bcqrs.getCarMatchId());
		return _ccsbgcxcc ;
	}
	
}
