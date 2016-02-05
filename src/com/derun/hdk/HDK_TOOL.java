package com.derun.hdk;

import com.derun.beans.Vehicle_Type;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;

/**
 * @author MILI
 * @描述：核定库工具类
 * @date:2015-4-1 11:08:30
 * */
public class HDK_TOOL {
	/**
	 * @author MLLI
	 * @描述：封装车辆信息  投保查询出参
	 * */
	public Vehicle_Type setCXCC(SYJK_CCS_CCSCXCCJBXX ccscxccjb){
		Vehicle_Type vv = new Vehicle_Type();
		vv.setDisplacement(ccscxccjb.getDISPLACEMENT());
		vv.setEngineNo(ccscxccjb.getENGINENO());
		vv.setFuelType(ccscxccjb.getFUELTYPE());
		vv.setLicensePlateNo(ccscxccjb.getHPHM());
		vv.setLicensePlateType(ccscxccjb.getHPZL());
		vv.setModel(ccscxccjb.getMODEL());
		vv.setMotorUsageTypeCode(ccscxccjb.getMOTORUSAGETYPECODE());
		vv.setMadeFactory(ccscxccjb.getMADEFACTORY());
		vv.setMotorTypeCode(ccscxccjb.getMOTORTYPECODE());
		vv.setNoLicenseFlag(ccscxccjb.getNOLICENSEFLAG());
		vv.setPower(ccscxccjb.getPOWER());
		vv.setFirstRegisterDate(DateUtil.getStringDate(ccscxccjb.getFIRSTREGISTERDATE(),null));
		vv.setRatedPassengerCapacity((int)ccscxccjb.getRATEDPASSENGERCAPACITY());
		vv.setSpecialCarType(ccscxccjb.getSPECIALCARTYPE());
		vv.setTonnage(ccscxccjb.getTONNAGE());
		vv.setVehicleType(ccscxccjb.getVEHICLETYPE());
		vv.setVIN(ccscxccjb.getVIN());
		vv.setWholeWeight(ccscxccjb.getWHOLEWEIGHT());
		return vv ;
	}
	/**
	 * @author MLLI
	 * @描述：封装车辆信息 变更查询出参
	 * */
	public Vehicle_Type setBGCXCC(SYJK_CCS_CCSBGCXCCJB ccsbgcxccjb){
		Vehicle_Type vv = new Vehicle_Type();
		vv.setDisplacement(ccsbgcxccjb.getDISPLACEMENT());
		vv.setEngineNo(ccsbgcxccjb.getENGINENO());
		vv.setFuelType(ccsbgcxccjb.getFUELTYPE());
		vv.setLicensePlateNo(ccsbgcxccjb.getHPHM());
		vv.setLicensePlateType(ccsbgcxccjb.getHPZL());
		vv.setModel(ccsbgcxccjb.getMODEL());
		vv.setMotorUsageTypeCode(ccsbgcxccjb.getMOTORUSAGETYPECODE());
		vv.setMadeFactory(ccsbgcxccjb.getMADEFACTORY());
		vv.setMotorTypeCode(ccsbgcxccjb.getMOTORTYPECODE());
		vv.setNoLicenseFlag(ccsbgcxccjb.getNOLICENSEFLAG());
		vv.setPower(ccsbgcxccjb.getPOWER());
		vv.setFirstRegisterDate(DateUtil.getStringDate(ccsbgcxccjb.getFIRSTREGISTERDATE(),null));
		vv.setRatedPassengerCapacity((int)ccsbgcxccjb.getRATEDPASSENGERCAPACITY());
		vv.setSpecialCarType(ccsbgcxccjb.getSPECIALCARTYPE());
		vv.setTonnage(ccsbgcxccjb.getTONNAGE());
		vv.setVehicleType(ccsbgcxccjb.getVEHICLETYPE());
		vv.setVIN(ccsbgcxccjb.getVIN());
		vv.setWholeWeight(ccsbgcxccjb.getWHOLEWEIGHT());
		return vv ;
	}
}
