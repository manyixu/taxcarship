package com.derun.common.car;

import com.derun.beans.Vehicle_Type;
import com.derun.common.util.FDMianShui;
import com.derun.model.po.Query_ChangeQurey;
import com.derun.model.po.SYJK_CCS_DSCCSJMDJXX;
import com.derun.model.po.SYJK_CCS_WSDJXX;

/**
 * @author MILI
 * @Date 2015-4-28
 * @描述：车辆种类判断
 * */
public class Car_Type {
	/**
	 * @author MILI
	 * @Date 2015-4-28
	 * @描述：车辆种类分类
	 * */
	public static String CAR_SPECIES(Query_ChangeQurey bqri,SYJK_CCS_WSDJXX wsdj,SYJK_CCS_DSCCSJMDJXX jmdj,int jmszrq ,int skssjsrq,
			int xtrq,int djrq,boolean isCarNo,String anyCode){
		String car_type = "" ;
		Vehicle_Type VT = bqri.getVehicleInfo();
//		String carNo = VT.getLicensePlateNo() ;		// 车牌
		String carType = VT.getMotorTypeCode();		// 车辆种类
//		boolean wai_flah = CarType_Assist.Car_Tpye(carNo);			
		String taxConditionCode = bqri.getTaxInfo().getTaxConditionCode() ;
		// 完税车
		if("P".equals(taxConditionCode)){
			car_type = "PAID" ;
		}else if("E".equals(taxConditionCode)){ // 免税车
			car_type = "EXEMPTION" ;
		}else if(isCarNo || FDMianShui.getcarType(carType)){	// 法定免税车
			car_type = "FD_EXEMPTION" ;
		}else if(jmdj != null && jmszrq >= xtrq){	// 本地库免税车
			double JMBL = jmdj.getJZBL();
			if (JMBL == 1) {
				car_type = "BD_EXEMPTION" ;
			}
		}else if(wsdj != null && skssjsrq >= xtrq ){	// 本地库完税车
			car_type = "BD_PAID" ;
		}
//		else if(!wai_flah){	// 外地车
//			car_type = "OTHER" ;
//		}
		else if("2".equals(VT.getSpecialCarType())){	// 临时入境车
			car_type = "TEMPORARY_R" ;
		}else if(anyCode != null && anyCode.equals("3") && !"P".equals(taxConditionCode)){	// 临时上路车
			car_type = "TEMPORARY_S" ;
		}
		return car_type ;
	}
	/**
	 * @author MILI
	 * @Date 2015-4-28 15:10:01
	 * @描述：免税车 法定免税车  完税车 临时入境车 临时上路车 
	 * */
	public static boolean NO_WTMD(Query_ChangeQurey bqri,SYJK_CCS_WSDJXX wsdj,SYJK_CCS_DSCCSJMDJXX jmdj,int jmszrq ,int skssjsrq,
			int xtrq,int djrq,boolean isCarNo,String anyCode){
		boolean flag = false ;
		String car_type = CAR_SPECIES(bqri,wsdj,jmdj,jmszrq ,skssjsrq,xtrq,djrq,isCarNo,anyCode);
		if("PAID".equals(car_type) || "EXEMPTION".equals(car_type) || "FD_EXEMPTION".equals(car_type) || "BD_EXEMPTION".equals(car_type) 
				|| "BD_PAID".equals(car_type) || "TEMPORARY_R".equals(car_type) || "TEMPORARY_S".equals(car_type)){
			flag = true ;
		}
		return flag ;
	}
}
