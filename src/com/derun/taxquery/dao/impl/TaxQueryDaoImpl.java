package com.derun.taxquery.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-5-7 16:51:34
 * @描述：投保查询 	返回出参 辅助类
 */
public class TaxQueryDaoImpl {
	/**
	 * @author MILI
	 * @time 2014-5-7 16:53:20
	 * @描述：投保查询 返回出参  Tax_Type 封装
	 * */
	public Tax_Type getBqri(BaseQueryReqInfo basequeryreqinfo,Tax_Type taxInfo){
		Tax_Type TT = new Tax_Type();
		TT.setPayCompanyCode(basequeryreqinfo.getCompanyCode());		// 代收公司
		String taxInsuSynFlag = CfgLoader.getConfigValue("SysSwitch", "TaxInsuSynFlag");	// 税险同步开关	1=打开;0=关闭
		SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		if(taxInsuSynFlag!=null&&"0".equals(taxInsuSynFlag)&&Integer.parseInt(sdfy.format(basequeryreqinfo.getInsureStartDate())) > Integer.parseInt(sdfy.format(new Date()))){
			//税险同步开关关闭时提前续保返回的税款代收日期不能是提前续保年 wbzhao 20150925
			TT.setPayDate(String.valueOf(Integer.parseInt(sdfy.format(basequeryreqinfo.getInsureStartDate()))-1));
		}else{
			TT.setPayDate(DateUtil.getStringDate(basequeryreqinfo.getInsureStartDate(), "yyyy"));																// 代收日期
		}
		TT.setTaxTermTypeCode(basequeryreqinfo.getTaxInfo().getTaxTermTypeCode());		// 税种类型代码,Y
		TT.setTaxConditionCode(basequeryreqinfo.getTaxInfo().getTaxConditionCode()); 	// 纳税类型代码,Y
		TT.setTaxRegistryNumber(basequeryreqinfo.getTaxInfo().getTaxRegistryNumber());	// 税务登记证号
		TT.setTaxPayerName(basequeryreqinfo.getTaxInfo().getTaxPayerName()); 			// 纳税人名称
		TT.setTaxPayerIdentificationCode(basequeryreqinfo.getTaxInfo().getTaxPayerIdentificationCode());	// 纳税人识别号
		return TT ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-7 16:53:20
	 * @描述：投保查询 返回出参  AnnualTax_Type 封装
	 * */
	public AnnualTax_Type getAT(BaseQueryReqInfo basequeryreqinfo,Tax_Type taxInfo){
		AnnualTax_Type AT = new AnnualTax_Type();
		AT = taxInfo.getCurrentTaxDue();
		if(AT != null){
			if(AT.getDerate() == null){
				AT.setDerate(new Derate_Type());
			}
			if(AT.getPaid() == null){
				AT.setPaid(new Paid_Type());
			}
		}
		AT.setTaxLocationCode(basequeryreqinfo.getAreaCode());		// 纳税地区代码
		// mili 2014-12-24 14:43:50  原来是 固定值 1 根据需求 计税单位返回的是根据什么算税，如果按排量算的就是1吧，如果按整备质量就是2
		AT.setTaxUnitTypeCode(taxInfo.getCurrentTaxDue().getTaxUnitTypeCode());// 计算单位代码    
//		AT.setTaxUnitTypeCode("1");									// 计算单位代码
		return AT ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-7 16:53:20
	 * @描述：投保查询 返回出参  AnnualTax_Type[] 封装
	 * */
	public AnnualTax_Type[] getATS(BaseQueryReqInfo basequeryreqinfo,Tax_Type taxInfo){
		AnnualTax_Type[] AT = null;
		AnnualTax_Type[] anntax = taxInfo.getDelinquentTaxDue();
		if(anntax != null && anntax.length > 0){
			int number = anntax.length ;
			AT = new AnnualTax_Type[number];
			for(int i = 0 ; i < number ; i++){
				AnnualTax_Type ann = taxInfo.getDelinquentTaxDue()[i];
				if(ann != null){
					if(ann.getDerate() == null){
						ann.setDerate(new Derate_Type());
					}
					if(ann.getPaid() == null){
						ann.setPaid(new Paid_Type());
					}
				}
				ann.setTaxLocationCode(basequeryreqinfo.getAreaCode());		// 纳税地区代码
//				ann.setTaxUnitTypeCode("1");								// 计算单位代码
				AT[i] = ann ;
			}
		}
		return AT ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-7 16:53:20
	 * @描述：投保查询 返回出参  TaxAmount_Type 封装
	 * */
	public TaxAmount_Type getTT(TaxDealCode_Type taxQueryNo,Tax_Type taxInfo){
		TaxAmount_Type TT = new TaxAmount_Type();
		TT = taxInfo.getTaxAmount();
		TT.setTaxAmount_Flag("1");
		TaxDealCode_Type taxdelcode = new TaxDealCode_Type();
		taxdelcode.setTaxDealCode_Type(taxQueryNo.getTaxDealCode_Type());
		TT.setTaxDealCode(taxdelcode);
		return TT ;
	}
	
	/**
	 * @author MILI
	 * @time 2014-6-25 9:33:08
	 * @描述：重复投保 要上次的的缴税纪录
	 * */
	public BaseQueryReqInfo FZ_Tax_Type(SYJK_CCS_RKMX rkmx,BaseQueryReqInfo bqr){
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
			
			Vehicle_Type vt = new Vehicle_Type();
			//2015-12-17	start	suobaowen	
			//修改匹配规则时发现第二次投保返回完税时，数据库中本次储存的车辆信息4项是取得之前入库明细表的值
			//匹配4 项用入参值
			vt.setLicensePlateNo(bqr.getVehicleInfo().getLicensePlateNo());		// 号牌号码
			vt.setLicensePlateType(bqr.getVehicleInfo().getLicensePlateType());		// 号牌种类
			vt.setEngineNo(bqr.getVehicleInfo().getEngineNo());			// 发动机号,Y
			vt.setVIN(bqr.getVehicleInfo().getVIN());					// 车架号,Y
			//2015-12-17	end		suobaowen	修改匹配规则时发现第二次投保返回完税时，数据库中本次储存的车辆信息4项是取得之前入库明细表的值

			
			vt.setMotorTypeCode(rkmx.getCLLX());				// 车辆种类,Y
//			vt.setMadeFactory(rkmx.getm);				// 制造厂名称
			vt.setMotorUsageTypeCode(rkmx.getMOTORUSAGETYPECODE());// 使用性质,Y
//			vt.setNoLicenseFlag(rkmx.get);				// 未上牌车辆标志,Y
			vt.setModel(rkmx.getMODEL());				// 车辆型号
			vt.setFirstRegisterDate(rkmx.getFIRSTREGISTERDATE());		// 车辆初始登记日期,Y
			vt.setVehicleType(rkmx.getVEHICLETYPE());					// 交管车辆类型,Y
			vt.setRatedPassengerCapacity((int)rkmx.getRATEDPASSENGERCAPACITY());		// 核定载客数,Y
			vt.setTonnage(rkmx.getTONNAGE());			// 核定载质量,Y
			vt.setWholeWeight(rkmx.getWHOLEWEIGHT());	// 整备质量,Y
			vt.setDisplacement(rkmx.getDISPLACEMENT());	// 排量 ,Y
			vt.setPower(rkmx.getPOWER());				// 功率 ,Y
			vt.setFuelType(rkmx.getFUELTYPE());			// 燃料种类
			vt.setSpecialCarType(rkmx.getSPECIALCARTYPE());				// 特殊车标志
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
			basequeryReqInfo.setVehicleInfo(vt);
		}
		return basequeryReqInfo ;
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
