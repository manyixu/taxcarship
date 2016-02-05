package com.derun.taxpayquery.vo;

import com.derun.beans.TaxPayQueryReqInfo;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSNSXXCXRC;

/**
 * @author 郑艳英
 * 
 * @date 2014-5-7
 * 
 *      纳税信息查询入参表封装类
 * @version
 */
public class SYJK_CCS_CCSNSXXCXRCV0 {
   
	//纳税信息查询入参信息封装
	public SYJK_CCS_CCSNSXXCXRC getTaxpayqueryReqVO(TaxPayQueryReqInfo taxpayqueryReq){
		//纳税信息查询入参表
		SYJK_CCS_CCSNSXXCXRC  ccsnsxxcxrc = new SYJK_CCS_CCSNSXXCXRC();
		// 车辆种类
		ccsnsxxcxrc.setMOTORTYPECODE(taxpayqueryReq.getVehicleInfo().getMotorTypeCode());
		// 发动机号
		ccsnsxxcxrc.setENGINENO(taxpayqueryReq.getVehicleInfo().getEngineNo());
		//车架号
		ccsnsxxcxrc.setVIN(taxpayqueryReq.getVehicleInfo().getVIN());
		//使用性质
		ccsnsxxcxrc.setMOTORUSAGETYPECODE(taxpayqueryReq.getVehicleInfo().getMotorUsageTypeCode());
		//未上牌车辆标志
		ccsnsxxcxrc.setNOLICENSEFLAG(taxpayqueryReq.getVehicleInfo().getNoLicenseFlag());
		//车辆初登日期
		ccsnsxxcxrc.setFIRSTREGISTERDATE(DateUtil.getStringDate(taxpayqueryReq.getVehicleInfo().getFirstRegisterDate(),null));
		//交管车辆类型
		ccsnsxxcxrc.setVEHICLETYPE(taxpayqueryReq.getVehicleInfo().getVehicleType());
		//核定载客数
		ccsnsxxcxrc.setRATEDPASSENGERCAPACITY((double)taxpayqueryReq.getVehicleInfo().getRatedPassengerCapacity());
		//核定载质量
		ccsnsxxcxrc.setTONNAGE(taxpayqueryReq.getVehicleInfo().getTonnage());
		//整备质量
		ccsnsxxcxrc.setWHOLEWEIGHT(taxpayqueryReq.getVehicleInfo().getWholeWeight());
		//排量
		ccsnsxxcxrc.setDISPLACEMENT(taxpayqueryReq.getVehicleInfo().getDisplacement());
		//功率
		ccsnsxxcxrc.setPOWER(taxpayqueryReq.getVehicleInfo().getPower());
		// 号牌号码
		String licensePlateNo = taxpayqueryReq.getVehicleInfo().getLicensePlateNo() == null ? "" : taxpayqueryReq.getVehicleInfo().getLicensePlateNo();
		// 号牌种类
		String licensePlateType = taxpayqueryReq.getVehicleInfo().getLicensePlateType() == null ? "" : taxpayqueryReq.getVehicleInfo().getLicensePlateType();
		// 制造厂名称
		String madeFactory = taxpayqueryReq.getVehicleInfo().getMadeFactory() == null ? "" : taxpayqueryReq.getVehicleInfo().getMadeFactory();
		// 车辆型号
		String model = taxpayqueryReq.getVehicleInfo().getModel() == null ? "" : taxpayqueryReq.getVehicleInfo().getModel();
		//燃料种类
		String fuelType = taxpayqueryReq.getVehicleInfo().getFuelType() == null ? "" : taxpayqueryReq.getVehicleInfo().getFuelType();
		// 特殊车标志
		String specialCarType = taxpayqueryReq.getVehicleInfo().getSpecialCarType() == null ? "" : taxpayqueryReq.getVehicleInfo().getSpecialCarType();
		ccsnsxxcxrc.setLICENSEPLATENO(licensePlateNo);
		ccsnsxxcxrc.setLICENSEPLATETYPE(licensePlateType);
		ccsnsxxcxrc.setMADEFACTORY(madeFactory);
		ccsnsxxcxrc.setMODEL(model);
		ccsnsxxcxrc.setFUELTYPE(fuelType);
		ccsnsxxcxrc.setSPECIALCARTYPE(specialCarType);
		return ccsnsxxcxrc;
	}
}
