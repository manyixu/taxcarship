package com.derun.taxchangequery.check;

import java.util.List;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-6-26 9:32:11
 * @描述：批改查询 辅助
 * */
public class TaxChangeQueryCommon {
	/**
	 * @author MILI
	 * @time 2014-6-26 9:35:11
	 * @描述： 退保
	 * */
	public Tax_Type getTax_Type(List<SYJK_CCS_RKMX> listRk,Tax_Type taxType,String flag){
		double a = 0 , b = 0 , c = 0 , d = 0 ;
		if(listRk != null && listRk.size() > 0){		// 入库明细List
			for(int i = 0 ; i < listRk.size() ; i++){
				SYJK_CCS_RKMX rkmxs = listRk.get(i);
				a += rkmxs.getANNUALTAXDUE() ;
				b += rkmxs.getSUMOVERDUE();
				c += rkmxs.getSUMTAXDEFAULT();
			}
			d = a + b + c ;
		}
		if("DQ".equals(flag)){
			taxType.getTaxAmount().setAnnualTaxDue(-taxType.getTaxAmount().getAnnualTaxDue());
			taxType.getTaxAmount().setSumOverdue(-taxType.getTaxAmount().getSumOverdue());
			taxType.getTaxAmount().setSumTax(-taxType.getTaxAmount().getSumTax());
			taxType.getTaxAmount().setSumTaxDefault(-taxType.getTaxAmount().getSumTaxDefault());
		}else if("CQ".equals(flag)){
			taxType.getTaxAmount().setAnnualTaxDue(-a);
			taxType.getTaxAmount().setSumOverdue(-b);
			taxType.getTaxAmount().setSumTax(-d);
			taxType.getTaxAmount().setSumTaxDefault(-c);
		}
		return taxType ;
	} 
	/**
	 * @author MILI
	 * @time 2014-6-26 9:56:07
	 * @描述：去掉负号
	 * */
	public TaxAmount_Type setTaxamount(TaxAmount_Type Amount){
		if(Math.abs(Amount.getAnnualTaxDue()) == 0.0){
			Amount.setAnnualTaxDue(Math.abs(Amount.getAnnualTaxDue()));
		}
		if(Math.abs(Amount.getSumOverdue()) == 0.0){
			Amount.setSumOverdue(Math.abs(Amount.getSumOverdue()));
		}
		if(Math.abs(Amount.getSumTax()) == 0.0){
			Amount.setSumTax(Math.abs(Amount.getSumTax()));
		}
		if(Math.abs(Amount.getSumTaxDefault()) == 0.0){
			Amount.setSumTaxDefault(Math.abs(Amount.getSumTaxDefault()));
		}
		return Amount ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-13 17:14:46
	 * @描述：封装 Tax_Type 
	 * */
	public Tax_Type setTax_Type(SYJK_CCS_RKMX rkmx){
		Tax_Type tax_type = new Tax_Type();
		BaseQueryReqInfo basequeryReqInfo = new BaseQueryReqInfo();
		if(rkmx != null ){
			tax_type.setPayCompanyCode(rkmx.getPAYCOMPANYCODE());
			tax_type.setPayDate(rkmx.getPAYDATE());
			tax_type.setTaxConditionCode(rkmx.getTAXCONDITIONCODE());
			tax_type.setTaxPayerIdentificationCode(rkmx.getTAXPAYERIDENTIFICATIONCODE());
			tax_type.setTaxPayerName(rkmx.getTAXPAYERNAME());
			tax_type.setTaxRegistryNumber(rkmx.getTAXREGISTRYNUMBER());
			//-----------------------------------------	------------------------------------------
			AnnualTax_Type acc_ = new AnnualTax_Type();
			acc_.setAnnualTaxAmount(rkmx.getANNUALTAXAMOUNT());
			acc_.setExceedDate(rkmx.getEXCEEDDATE());
			acc_.setExceedDaysCount(rkmx.getEXCEEDDAYSCOUNT());
			acc_.setOverDue(rkmx.getOVERDUE());
			acc_.setTaxDue(rkmx.getTAXDUE());
			acc_.setTaxEndDate(Tentiem(rkmx.getTAXENDDATE()));
			acc_.setTaxLocationCode(rkmx.getTAXLOCATIONCODE());
			acc_.setTaxStartDate(Tentiem(rkmx.getTAXSTARTDATE()));
			acc_.setTaxUnitTypeCode(rkmx.getTAXUNITTYPECODE());
			acc_.setTotalAmount(rkmx.getTOTALAMOUNT());
			acc_.setUnitRate(rkmx.getUNITRATE());
//-----------------------------------------	
			Paid_Type mili_paid = new Paid_Type();
			Derate_Type mili_derate = new Derate_Type();
			mili_paid.setTaxDepartment(rkmx.getTAXDEPARTMENT());
			mili_paid.setTaxDepartmentCode(rkmx.getTAXDEPARTMENTCODE());
			mili_paid.setTaxDocumentNumber(rkmx.getTAXDOCUMENTNUMBER());
//			-----------------------------------------	
			mili_derate.setDeduction(rkmx.getDEDUCTION());
			mili_derate.setDeductionDocumentNumber(rkmx.getDEDUCTIONDOCUMENTNUMBER());
			mili_derate.setDeductionDueCode(rkmx.getDEDUCTIONDUECODE());
			mili_derate.setDeductionDueProportion(rkmx.getDEDUCTIONDUEPROPORTION());
			mili_derate.setDeductionDueType(rkmx.getDEDUCTIONDUETYPE());
			mili_derate.setTaxDepartment(rkmx.getDEDUCTIONDEPARTMENT());
			mili_derate.setTaxDepartmentCode(rkmx.getDEDUCTIONDEPARTMENTCODE());
			
			basequeryReqInfo.setUserName(rkmx.getLOGINNAME());			// 用户名 String(6) 
//			basequeryReqInfo.setPassword(rkmx.get);			// 密码 String(6) 
//			basequeryReqInfo.setQuerySequencetTime(rkmx.getq);	// 投保查询日期
			basequeryReqInfo.setAreaCode(rkmx.getTAXLOCATIONCODE());			// 国标区域代码
			basequeryReqInfo.setCompanyCode(rkmx.getPAYCOMPANYCODE());		// 公司代码
			basequeryReqInfo.setInsureStartDate(rkmx.getInsureStartDate());	// 投保开始日期
			basequeryReqInfo.setInsureEndDate(rkmx.getInsureEndDate());		// 投保结束日期
			basequeryReqInfo.setCarMatchId(rkmx.getCARMATCHID());			// 用于记录这辆车是用哪个匹配规则匹配到的
			
//			Vehicle_Type vt = new Vehicle_Type();
//			vt.setLicensePlateNo(rkmx.getHPHM());		// 号牌号码
//			vt.setLicensePlateType(rkmx.getHPZL());		// 号牌种类
//			vt.setMotorTypeCode(rkmx.getCLLX());		// 车辆种类,Y
//			vt.setEngineNo(rkmx.getEngineNo());			// 发动机号,Y
//			vt.setVIN(rkmx.getVIN());					// 车架号,Y
////			vt.setMadeFactory(rkmx.getm);				// 制造厂名称
//			vt.setMotorUsageTypeCode(rkmx.getMOTORUSAGETYPECODE());// 使用性质,Y
////			vt.setNoLicenseFlag(rkmx.get);				// 未上牌车辆标志,Y
//			vt.setModel(rkmx.getMODEL());				// 车辆型号
//			vt.setFirstRegisterDate(rkmx.getFIRSTREGISTERDATE());		// 车辆初始登记日期,Y
//			vt.setVehicleType(rkmx.getVEHICLETYPE());					// 交管车辆类型,Y
//			vt.setRatedPassengerCapacity((int)rkmx.getRATEDPASSENGERCAPACITY());		// 核定载客数,Y
//			vt.setTonnage(rkmx.getTONNAGE());			// 核定载质量,Y
//			vt.setWholeWeight(rkmx.getWHOLEWEIGHT());	// 整备质量,Y
//			vt.setDisplacement(rkmx.getDISPLACEMENT());	// 排量 ,Y
//			vt.setPower(rkmx.getPOWER());				// 功率 ,Y
//			vt.setFuelType(rkmx.getFUELTYPE());			// 燃料种类
//			vt.setSpecialCarType(rkmx.getSPECIALCARTYPE());				// 特殊车标志
			//-----------------------------------------	
			acc_.setDerate(mili_derate);
			acc_.setPaid(mili_paid);
			// 本年纳税对象
			tax_type.setCurrentTaxDue(acc_);  			
			// 本年欠税对象
			tax_type.setDelinquentTaxDue(new AnnualTax_Type[0]); 
			// 合计金额对象
			tax_type.setTaxAmount(new TaxAmount_Type());
			basequeryReqInfo.setTaxInfo(tax_type);
		}
		return tax_type ;
	}
	/**
	 * @author MILI
	 * @time 2014-6-25 9:33:59
	 * @描述：截取时间  年月日 
	 * */
	public String Tentiem(String dates){
		if(dates != null && dates.length() >= 10){
			dates = dates.substring(0, 10);
		}
		return dates ;
	}
}
