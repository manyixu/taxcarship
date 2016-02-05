package com.derun.taxchangequery.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.util.DateUtil;
import com.derun.model.po.BaseChangeQueryReqInfoUtilEntity;
import com.derun.model.po.SYJK_CCS_CCSXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_RKMX_QS;
import com.derun.model.po.TaxConfirmno_CHK;
/**
 * @author MILI
 * @time 2014-5-5 17:50:12
 * @描述：批改查询 封装  公共
 * */
public class TaxChangeQuery_Common {
	/**
	 * @author MILI
	 * @time 2014-5-6 16:36:31
	 * @描述：入参没有纳税信息对象的情况下  取入库明细的信息 封装 
	 * */
	public Tax_Type FZ_Tax_Type(SYJK_CCS_RKMX rkmx,String flag,Tax_Type taxtype){
		Tax_Type tax_type = new Tax_Type();
		TaxAmount_Type tat = new TaxAmount_Type();
		if(rkmx != null ){
			tax_type.setPayCompanyCode(rkmx.getPAYCOMPANYCODE());
			tax_type.setPayDate(rkmx.getPAYDATE());
			// 4.普通车拒缴批改问题车正常纳税返回拒缴  2014-11-3   MILI  
			if(taxtype != null){
				tax_type.setTaxConditionCode(taxtype.getTaxConditionCode());
			}else{
				tax_type.setTaxConditionCode(rkmx.getTAXCONDITIONCODE());
			}
			tax_type.setTaxPayerIdentificationCode(rkmx.getTAXPAYERIDENTIFICATIONCODE());
			tax_type.setTaxPayerName(rkmx.getTAXPAYERNAME());
			tax_type.setTaxRegistryNumber(rkmx.getTAXREGISTRYNUMBER());
			tax_type.setTaxTermTypeCode("08");
			AnnualTax_Type acc_ = new AnnualTax_Type();
			if("02".equals(flag)){		// 批改查询不能批改情况时  除税款信息  其他都使用上次次纳税信息   02 = 批改
				acc_.setAnnualTaxAmount(taxtype.getCurrentTaxDue().getAnnualTaxAmount());
				acc_.setExceedDate(taxtype.getCurrentTaxDue().getExceedDate());
				acc_.setExceedDaysCount(taxtype.getCurrentTaxDue().getExceedDaysCount());
				acc_.setOverDue(taxtype.getCurrentTaxDue().getOverDue());
				acc_.setTaxDue(taxtype.getCurrentTaxDue().getTaxDue());
				acc_.setTaxEndDate(Tentiem(taxtype.getCurrentTaxDue().getTaxEndDate()));
				acc_.setTaxLocationCode(taxtype.getCurrentTaxDue().getTaxLocationCode());
				acc_.setTaxStartDate(Tentiem(taxtype.getCurrentTaxDue().getTaxStartDate()));
				acc_.setTaxUnitTypeCode(taxtype.getCurrentTaxDue().getTaxUnitTypeCode());
				acc_.setTotalAmount(taxtype.getCurrentTaxDue().getTotalAmount());
				acc_.setUnitRate(taxtype.getCurrentTaxDue().getUnitRate());
			}else{
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
			}
			//-----------------------------------------	
			Paid_Type mili_paid = new Paid_Type();
			Derate_Type mili_derate = new Derate_Type();
			mili_paid.setTaxDepartment(rkmx.getTAXDEPARTMENT());
			mili_paid.setTaxDepartmentCode(rkmx.getTAXDEPARTMENTCODE());
			mili_paid.setTaxDocumentNumber(rkmx.getTAXDOCUMENTNUMBER());
			//-----------------------------------------	
			mili_derate.setDeduction(rkmx.getDEDUCTION());
			mili_derate.setDeductionDocumentNumber(rkmx.getDEDUCTIONDOCUMENTNUMBER());
			mili_derate.setDeductionDueCode(rkmx.getDEDUCTIONDUECODE());
			mili_derate.setDeductionDueProportion(rkmx.getDEDUCTIONDUEPROPORTION());
			mili_derate.setDeductionDueType(rkmx.getDEDUCTIONDUETYPE());
			mili_derate.setTaxDepartment(rkmx.getDEDUCTIONDEPARTMENT());
			mili_derate.setTaxDepartmentCode(rkmx.getDEDUCTIONDEPARTMENTCODE());
			acc_.setDerate(mili_derate);
			acc_.setPaid(mili_paid);
			
			if(mili_paid.getTaxDocumentNumber() != null && !"".equals(mili_paid.getTaxDocumentNumber())){
				mili_derate.setTaxDepartment(null);
				mili_derate.setTaxDepartmentCode(null);
			}
			//-----------------------------------------	
			// 本年纳税对象
			tax_type.setCurrentTaxDue(acc_);  			
			// 本年欠税对象
			if(taxtype != null && taxtype.getDelinquentTaxDue() != null && taxtype.getDelinquentTaxDue().length > 0){	
				tax_type.setDelinquentTaxDue(taxtype.getDelinquentTaxDue());	
			}else{
				tax_type.setDelinquentTaxDue(new AnnualTax_Type[0]); 
			}
			// 合计金额对象
			// 
			tat.setAnnualTaxDue(rkmx.getANNUALTAXDUE());
			tat.setSumOverdue(rkmx.getSUMOVERDUE());
			tat.setSumTax(rkmx.getSUMTAX());
			tat.setSumTaxDefault(rkmx.getSUMTAXDEFAULT());
			tax_type.setTaxAmount(tat);
		}
		return tax_type ;
	}
	 /**
     * @author MILI
     * @time 2014-5-6 15:37:28
     * @描述：变更查入参 封装 入库明细 参数
     * */
    public BaseChangeQueryReqInfoUtilEntity getBcqriue(SYJK_CCS_RKMX rkmx){
    	BaseChangeQueryReqInfoUtilEntity baseChangeQueryReqInfoUtilEntity = null ;
    	if(rkmx != null){
    		baseChangeQueryReqInfoUtilEntity = new BaseChangeQueryReqInfoUtilEntity();
			baseChangeQueryReqInfoUtilEntity.setPOWER(rkmx.getTAXDUE());							//最后的合计金额
			baseChangeQueryReqInfoUtilEntity.setFIRSTREGISTERDATEL(rkmx.getFIRSTREGISTERDATE());	//初始登记日期
			baseChangeQueryReqInfoUtilEntity.setTAXAMOUNT_FLAG(rkmx.getTAXAMOUNT_FLAG());			//合计金额标志代码
			baseChangeQueryReqInfoUtilEntity.setANNUALTAXDUE(rkmx.getANNUALTAXDUE()) ;				//本年车船税金额
			baseChangeQueryReqInfoUtilEntity.setSUMTAXDEFAULT(rkmx.getSUMTAXDEFAULT()) ;			//合计欠税金额
			baseChangeQueryReqInfoUtilEntity.setSUMOVERDUE(rkmx.getSUMOVERDUE()) ;					//合计滞纳金
			baseChangeQueryReqInfoUtilEntity.setSUMTAX(rkmx.getSUMTAX()) ;							//合计金额
			baseChangeQueryReqInfoUtilEntity.setTAXQUERYNO(rkmx.getTAXQUERYNO());					//变更查询码
			baseChangeQueryReqInfoUtilEntity.setTAXCONFIRMNO(rkmx.getTAXQUERYNO());					//变更确认码
			baseChangeQueryReqInfoUtilEntity.setCHANGETYPE(rkmx.getCHANGETYPE());					//变更类型
			baseChangeQueryReqInfoUtilEntity.setADDRESS(rkmx.getSJCJRQ());							//用车主地址代表系统采集时间
			baseChangeQueryReqInfoUtilEntity.setPHONENO(rkmx.getPLATFORMSTATE());					//用联系号码代表平台状态
			baseChangeQueryReqInfoUtilEntity.setVEHICLEOWNERNAME(rkmx.getCHANGETYPE());				//用车主姓名存储变更类型
			baseChangeQueryReqInfoUtilEntity.setPLATFORMSTATE(rkmx.getPLATFORMSTATE()); 			// 申报状态   
    	}
		return baseChangeQueryReqInfoUtilEntity ;
    }
    /**
     * @author MILI
     * @time 2014-5-6 16:38:33
     * @描述：用于把入库明细表里面的车船税信息封装到车船税信息表里面
     */
    public SYJK_CCS_CCSXX getTaxCcsxx(SYJK_CCS_RKMX rkmx){
    	SYJK_CCS_CCSXX ccsxx = new SYJK_CCS_CCSXX();
    	ccsxx.setTAXQUERYNO(rkmx.getTAXQUERYNO());						// 车船税查询码,唯一标示符
    	ccsxx.setTAXTERMTYPECODE("08");									// 税种类型代码,Y
    	ccsxx.setTAXCONDITIONCODE(rkmx.getTAXCONDITIONCODE());			// 纳税类型代码,Y
    	ccsxx.setTAXREGISTRYNUMBER(rkmx.getTAXREGISTRYNUMBER());		// 税务登记证号
    	ccsxx.setTAXPAYERNAME(rkmx.getTAXPAYERNAME());					// 纳税人名称
    	ccsxx.setTAXPAYERIDENTIFICATIONCODE(rkmx.getTAXPAYERIDENTIFICATIONCODE());		// 纳税人识别号
    	ccsxx.setTAXLOCATIONCODE(rkmx.getTAXLOCATIONCODE());							// 纳税地区代码
    	ccsxx.setTAXSTARTDATE(DateUtil.getStringDate(rkmx.getTAXSTARTDATE(),null));			// 税款所属始期
    	ccsxx.setTAXENDDATE(DateUtil.getStringDate(rkmx.getTAXENDDATE(),null));				// 税款所属止期
    	ccsxx.setPAYDATE(DateUtil.getStringDate(rkmx.getPAYDATE(),"yyyy"));					// 所属年度,Y
    	ccsxx.setTAXUNITTYPECODE(rkmx.getTAXUNITTYPECODE());			// 计税单位代码
    	ccsxx.setUNITRATE(rkmx.getUNITRATE());							// 单位计税金额
    	ccsxx.setANNUALTAXAMOUNT(rkmx.getANNUALTAXAMOUNT());			// 当期年单位税额
			if(rkmx.getTAXCONDITIONCODE().equals("P")){
				ccsxx.setTAXDEPARTMENTCODE(rkmx.getTAXDEPARTMENTCODE());		// 开具减免税(完税)凭证的税务机关代码,Y
				ccsxx.setTAXDEPARTMENT(rkmx.getTAXDEPARTMENT());				// 开具减免税(完税)凭证的税务机关名称,Y
			}else{
				ccsxx.setTAXDEPARTMENTCODE(rkmx.getDEDUCTIONDEPARTMENTCODE());	// 开具减免税(完税)凭证的税务机关代码,Y
				ccsxx.setTAXDEPARTMENT(rkmx.getDEDUCTIONDEPARTMENT());			// 开具减免税(完税)凭证的税务机关名称,Y
			}
		ccsxx.setTAXDOCUMENTNUMBER(rkmx.getTAXDOCUMENTNUMBER());			// 完税凭证号码
		ccsxx.setDEDUCTIONDUECODE(rkmx.getDEDUCTIONDUECODE());				// 减免税原因代码,Y
		ccsxx.setDEDUCTIONDUETYPE(rkmx.getDEDUCTIONDUETYPE());				// 减免税方案代码,Y
		ccsxx.setDEDUCTIONDUEPROPORTION(rkmx.getDEDUCTIONDUEPROPORTION());	// 减免比例
		ccsxx.setDEDUCTION(rkmx.getDEDUCTION());							// 减免金额
		ccsxx.setDEDUCTIONDOCUMENTNUMBER(rkmx.getDEDUCTIONDOCUMENTNUMBER());// 减免税凭证号
		ccsxx.setTAXDUE(rkmx.getTAXDUE());									// 当期应纳税额
		ccsxx.setEXCEEDDATE(DateUtil.getStringDate(rkmx.getEXCEEDDATE(),null));	// 逾期时间
		ccsxx.setEXCEEDDAYSCOUNT(rkmx.getEXCEEDDAYSCOUNT());				// 逾期天数
		ccsxx.setOVERDUE(rkmx.getOVERDUE());								// 滞纳金
		ccsxx.setTOTALAMOUNT(rkmx.getTOTALAMOUNT());						// 合计金额
		ccsxx.setSJCJRQ(DateUtil.getStringDate(rkmx.getSJCJRQ(),null));			// 系统采集日期
		ccsxx.setANNUALTAXAMOUNT(rkmx.getANNUALTAXDUE());
//		ccsxx.setSumTax(rkmx.getSUMTAX());
//		ccsxx.setSumTaxDefault(rkmx.getSUMTAXDEFAULT());
//		ccsxx.setSumOverdue(rkmx.getSUMOVERDUE());
		return ccsxx;
    }
    /**
	 * @author MILI
	 * @time 2014-5-6 10:02:19
	 * @描述：填充车辆信息  入参没有的情况下 取入库明细 的车辆信息
	 * */
	public BaseChangeQueryReqInfo getVT(BaseChangeQueryReqInfo bcqri ,SYJK_CCS_RKMX rkmx){
		Vehicle_Type vt = null;
		vt = bcqri.getVehicleInfo();
		if(vt == null && rkmx != null){
			vt = new Vehicle_Type();
			vt.setDisplacement(rkmx.getDISPLACEMENT());
			vt.setFuelType(rkmx.getFUELTYPE());
//			vt.setMadeFactory(rkmx.get);
			vt.setModel(rkmx.getMODEL());
			vt.setMotorUsageTypeCode(rkmx.getMOTORUSAGETYPECODE());
//			vt.setNoLicenseFlag(rkmx.getn);
			vt.setPower(rkmx.getPOWER());
			vt.setRatedPassengerCapacity((int)rkmx.getRATEDPASSENGERCAPACITY());
			vt.setSpecialCarType(rkmx.getSPECIALCARTYPE());
			vt.setTonnage(rkmx.getTONNAGE());
			vt.setVehicleType(rkmx.getVEHICLETYPE());
			vt.setWholeWeight(rkmx.getWHOLEWEIGHT());
			
			
			vt.setVIN(rkmx.getVIN());						// VIN
			vt.setLicensePlateNo(rkmx.getHPHM());			// 号牌号码
			vt.setLicensePlateType(rkmx.getHPZL());			// 号牌种类
			vt.setEngineNo(rkmx.getEngineNo());				// 发动机号
			vt.setMotorTypeCode(rkmx.getCLLX());			// 车辆类型
			vt.setFirstRegisterDate(rkmx.getFIRSTREGISTERDATE());	// 初登日期
			
			
			vt.setMotorUsageTypeCode(rkmx.getMOTORUSAGETYPECODE());
			vt.setModel(rkmx.getMODEL());
			vt.setVehicleType(rkmx.getVEHICLETYPE());
			vt.setRatedPassengerCapacity((int)rkmx.getRATEDPASSENGERCAPACITY());
			vt.setTonnage(rkmx.getTONNAGE());
			vt.setWholeWeight(rkmx.getWHOLEWEIGHT());
			vt.setDisplacement(rkmx.getDISPLACEMENT());
			vt.setPower(rkmx.getPOWER());
			vt.setFuelType(rkmx.getFUELTYPE());
			
			bcqri.setVehicleInfo(vt);  
		}
		return bcqri ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-14 16:28:31
	 * @描述：批改查询本年合计
	 * */
	public Tax_Type getTaxAmout(List<TaxConfirmno_CHK> tax_chk,Tax_Type tax_type){
		TaxAmount_Type taxamount_type = new TaxAmount_Type();
		if(tax_chk == null){
			taxamount_type.setAnnualTaxDue(-tax_type.getTaxAmount().getAnnualTaxDue());
			taxamount_type.setSumOverdue(-tax_type.getTaxAmount().getSumOverdue());
			taxamount_type.setSumTax(-tax_type.getTaxAmount().getSumTax());
			taxamount_type.setSumTaxDefault(-tax_type.getTaxAmount().getSumTaxDefault());
		}else{
			for(TaxConfirmno_CHK chk : tax_chk){
				taxamount_type.setAnnualTaxDue(chk.getANNUALTAXDUE() + taxamount_type.getAnnualTaxDue());
				taxamount_type.setSumOverdue(chk.getSUMOVERDUE() + taxamount_type.getSumOverdue());
				taxamount_type.setSumTax(chk.getSUMTAX() + taxamount_type.getSumTax());
				taxamount_type.setSumTaxDefault(chk.getSUMTAXDEFAULT() + taxamount_type.getSumTaxDefault());
			}
			tax_type.getTaxAmount().setAnnualTaxDue(tax_type.getTaxAmount().getAnnualTaxDue() - taxamount_type.getAnnualTaxDue());
			tax_type.getTaxAmount().setSumOverdue(tax_type.getTaxAmount().getSumOverdue() - taxamount_type.getSumOverdue());
			tax_type.getTaxAmount().setSumTax(tax_type.getTaxAmount().getSumTax() - taxamount_type.getSumTax());
			tax_type.getTaxAmount().setSumTaxDefault(tax_type.getTaxAmount().getSumTaxDefault() - taxamount_type.getSumTaxDefault());
			tax_type.setTaxAmount(this.goTaxAmount(tax_type.getTaxAmount()));
		}
		return tax_type ;
	}
	/**
	 * @author MILI
	 * @time 2014-6-4 15:27:36
	 * @描述：格式化 double类型 保留2位小数
	 * */
	public TaxAmount_Type goTaxAmount(TaxAmount_Type taxamount){
		TaxAmount_Type taxamount_type = new TaxAmount_Type();
		taxamount_type.setAnnualTaxDue(doubleFormat(taxamount.getAnnualTaxDue(),2));
		taxamount_type.setSumOverdue(doubleFormat(taxamount.getSumOverdue(),2));
		taxamount_type.setSumTax(doubleFormat(taxamount.getSumTax(),2));
		taxamount_type.setSumTaxDefault(doubleFormat(taxamount.getSumTaxDefault(),2));
		return taxamount_type ;
	}
	/**
	 * double类型精度格式化（四舍五入）
	 * @param d 源金额
	 * @param num 保留位数
	 * @return
	 */
	public static double doubleFormat(double d, int num){
		return new BigDecimal(""+d).setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * @author MILI
	 * @time 2014-6-5 10:50:06
	 * @描述：截取时间  年月日 
	 * */
	public String Tentiem(String dates){
		if(dates != null && dates.length() >= 10){
			dates = dates.substring(0, 10);
		}
		return dates ;
	}
	/**
	 * @author MILI
	 * @time 2014-6-23 10:47:59
	 * @描述：合计金额对象赋值
	 * */
	public Tax_Type set_TaxAamount(Tax_Type taxType){
		TaxAmount_Type taxamount = new TaxAmount_Type();
		Tax_Type TT = taxType;
		if(taxType != null && taxType.getTaxAmount() != null){
			taxamount.setAnnualTaxDue(-taxType.getTaxAmount().getAnnualTaxDue());
			taxamount.setSumOverdue(-taxType.getTaxAmount().getSumOverdue());
			taxamount.setSumTax(-taxType.getTaxAmount().getSumTax());
			taxamount.setSumTaxDefault(-taxType.getTaxAmount().getSumTaxDefault());
			TT.setTaxAmount(taxamount);
		}
		return TT ;
	}
//	/**
//	 * @author MILI
//	 * @time 2014-5-6 10:02:19
//	 * @描述：填充车辆信息  入参没有的情况下 取入库明细 的车辆信息
//	 * */
//	public Vehicle_Type getVT(SYJK_CCS_RKMX rkmx){
//		Vehicle_Type VT = null;
//		if(rkmx != null){
//			VT = new Vehicle_Type();
////			vt.setDisplacement(rkmx.);
////			vt.setFuelType(fuelType);
////			vt.setMadeFactory(madeFactory);
////			vt.setModel(model);
////			vt.setMotorUsageTypeCode(motorUsageTypeCode);
////			vt.setNoLicenseFlag(noLicenseFlag);
////			vt.setPower(power);
////			vt.setRatedPassengerCapacity(ratedPassengerCapacity);
////			vt.setSpecialCarType(specialCarType);
////			vt.setTonnage(tonnage);
////			vt.setVehicleType(vehicleType);
////			vt.setWholeWeight();
//			
//			
//			VT.setVIN(rkmx.getVIN());						// VIN
//			VT.setLicensePlateNo(rkmx.getHPHM());			// 号牌号码
//			VT.setLicensePlateType(rkmx.getHPZL());			// 号牌种类
//			VT.setEngineNo(rkmx.getEngineNo());				// 发动机号
//			VT.setMotorTypeCode(rkmx.getCLLX());			// 车辆类型
//			VT.setFirstRegisterDate(rkmx.getFIRSTREGISTERDATE());	// 初登日期
//		}
//		return VT ;
//	}
	/**
	 * @author MILI
	 * @time 2014-6-30 16:39:25
	 * @描述：批改查询时 返回值 封装 Tax_Type
	 * */
	public Tax_Type setTax_Type(Tax_Type tax_type,String CT){
		if("T".equals(CT)){
			tax_type.getCurrentTaxDue().setDerate(new Derate_Type());
			tax_type.getCurrentTaxDue().setPaid(new Paid_Type());
		}else if("C".equals(CT)){
		}else if("P".equals(CT)){
			
		}else if("E".equals(CT)){
		}else if("R".equals(CT)){
		}
		return tax_type ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-15 14:25:46
	 * @描述：欠税表封装  欠税对象
	 * */
	public AnnualTax_Type[] setAnnualTax(List<SYJK_CCS_RKMX_QS> list_Rk_qs){
		List<AnnualTax_Type> list_ann = new ArrayList<AnnualTax_Type>();
		AnnualTax_Type[] annuas = null ;
		AnnualTax_Type annua = null ;
		SYJK_CCS_RKMX_QS rkmx_qs = null ;
		Paid_Type paid = null;
		Derate_Type derate = null ;
		int paydate = 0 ,pay = 0 ;
		if(list_Rk_qs != null){
			int number = list_Rk_qs.size();
			for(int i = 0; i < number; i++){
				annua = new AnnualTax_Type();
				paid = new Paid_Type();
				derate = new Derate_Type() ;
				rkmx_qs = list_Rk_qs.get(i);
				pay = Integer.parseInt(DateUtil.getStringDate(rkmx_qs.getTAXSTARTDATE(), "yyyy"));
				if(paydate != pay){
					annua.setAnnualTaxAmount(rkmx_qs.getANNUALTAXAMOUNT());
					paid.setTaxDepartment(rkmx_qs.getTAXDEPARTMENT());
					paid.setTaxDepartmentCode(rkmx_qs.getTAXDEPARTMENTCODE());
					paid.setTaxDocumentNumber(rkmx_qs.getTAXDOCUMENTNUMBER());
					
					annua.setExceedDate(DateUtil.getStringDate(rkmx_qs.getEXCEEDDATE(),null));
					annua.setExceedDaysCount(rkmx_qs.getEXCEEDDAYSCOUNT());
					annua.setOverDue(rkmx_qs.getOVERDUE());
					
					derate.setDeduction(rkmx_qs.getDEDUCTION());
					derate.setDeductionDocumentNumber(rkmx_qs.getDEDUCTIONDOCUMENTNUMBER());
					derate.setDeductionDueCode(rkmx_qs.getDEDUCTIONDUECODE());
					derate.setDeductionDueProportion(rkmx_qs.getDEDUCTIONDUEPROPORTION());
					derate.setDeductionDueType(rkmx_qs.getDEDUCTIONDUETYPE());
					derate.setTaxDepartment(rkmx_qs.getDEDUCTIONDEPARTMENT());
					derate.setTaxDepartmentCode(rkmx_qs.getDEDUCTIONDEPARTMENTCODE());
					
					
					annua.setTaxDue(rkmx_qs.getTAXDUE());
					annua.setTaxEndDate(DateUtil.getStringDate(rkmx_qs.getTAXENDDATE(),null));
					annua.setTaxLocationCode(rkmx_qs.getTAXLOCATIONCODE());
					annua.setTaxStartDate(DateUtil.getStringDate(rkmx_qs.getTAXSTARTDATE(),null));
					annua.setTaxUnitTypeCode(rkmx_qs.getTAXUNITTYPECODE());
					annua.setTotalAmount(rkmx_qs.getTOTALAMOUNT());
					annua.setUnitRate(rkmx_qs.getUNITRATE());
					
					annua.setPaid(paid);
					annua.setDerate(derate);
					list_ann.add(annua) ;
				}
				paydate = Integer.parseInt(DateUtil.getStringDate(rkmx_qs.getTAXSTARTDATE(), "yyyy"));
			}
		}
		int n = list_ann.size() ;
		annuas = new AnnualTax_Type[n];
		for(int i = 0; i < n ;i++){
			annuas[i] = list_ann.get(i);
		}
		return annuas ;
	}
}
