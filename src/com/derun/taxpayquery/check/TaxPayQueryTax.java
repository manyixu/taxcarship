package com.derun.taxpayquery.check;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.TaxPayQueryReqInfo;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.db.SqlText;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.DateUtil;
import com.derun.common.util.ShuiKuanType;
import com.derun.taxpayquery.dao.impl.TaxPayQueryServiceDao;

/**
 * 
 * @author 郑艳英 车船税纳税信息查询服务----车辆信息
 */
public class TaxPayQueryTax {

	TaxPayQueryServiceDao taxPayQueryServiceDao = new TaxPayQueryServiceDao();

	public Map<Object, Object> getRKMXList(TaxPayQueryReqInfo taxPayQueryReqInfo) {
		// 返回map
		Map<Object, Object> RKMXMap = new HashMap<Object, Object>();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Double> sumTax = new HashMap<String, Double>();
		TaxAmount_Type taxAmountType = new TaxAmount_Type();// 车船税合计金额
		TaxDealCode_Type taxDealCode = new TaxDealCode_Type();// 车船税交易码数据类型
		
		List<Map<Object, Object>> list_map = new ArrayList<Map<Object, Object>>() ;
//		Map<Object, Object> Map_RKMX = new HashMap<Object, Object>();
		String sql = "";
		if (taxPayQueryReqInfo.getVehicleInfo() != null
				|| "".equals(taxPayQueryReqInfo.getVehicleInfo())) {
			// 如果车架号和发动机号，号牌号码不为空的情况VIN+HPHM+engineno
			if (taxPayQueryReqInfo.getVehicleInfo().getVIN() != null
					&& taxPayQueryReqInfo.getVehicleInfo().getEngineNo() != null
					&& taxPayQueryReqInfo.getVehicleInfo()
							.getLicensePlateType() != null) {
				sql = SqlText.C_08_RKMX_001;
				System.out.println(sql);
				list_map = taxPayQueryServiceDao.getRKMX(taxPayQueryReqInfo,
						sql, "1");
			} else if (taxPayQueryReqInfo.getVehicleInfo().getLicensePlateNo() != null
					|| "".equals(taxPayQueryReqInfo.getVehicleInfo()
							.getLicensePlateNo())
					|| taxPayQueryReqInfo.getVehicleInfo()
							.getLicensePlateType() != null
					|| "".equals(taxPayQueryReqInfo.getVehicleInfo()
							.getLicensePlateType())) {
				// 如果号牌号码和号牌种类不为空的话，VIN+HPHM+HPZL
				sql = SqlText.C_08_RKMX_002;
				System.out.println(sql);
				list_map = taxPayQueryServiceDao.getRKMX(taxPayQueryReqInfo,
						sql, "2");
			} else if (taxPayQueryReqInfo.getVehicleInfo().getEngineNo() != null) {
				// 如果号牌号码和号牌种类为空的话，VIN+engineno
				sql = SqlText.C_08_RKMX_003;
				System.out.println(sql);
				list_map = taxPayQueryServiceDao.getRKMX(taxPayQueryReqInfo,
						sql, "3");
			} else {
				sql = SqlText.C_08_RKMX_004;
				System.out.println(sql);
				list_map = taxPayQueryServiceDao.getRKMX(taxPayQueryReqInfo,
						sql, "4");
			}

		}

		// 如果发动机号，号牌号码和号牌种类为空的话VIN

		
		
		// taxPayQueryServiceDao.getRKMX(taxPayQueryReqInfo, sql);
		if (list_map == null || list_map.size() == 0) {
			return RKMXMap;
		}
		Tax_Type taxType = new Tax_Type();
		// 税种类型代码
		String TaxTermTypeCode = (String) list_map.get(0).get("TaxTermTypeCode");
		taxType.setTaxTermTypeCode(TaxTermTypeCode);
		// 纳税类型代码,Y
		String taxConditionCode = (String) list_map.get(0).get("TAXCONDITIONCODE");
		taxType.setTaxConditionCode(taxConditionCode);
		// 税务登记证号
		String taxRegistryNumber = (String) list_map.get(0).get("TAXREGISTRYNUMBER");
		taxType.setTaxRegistryNumber(taxRegistryNumber);
		// 纳税人名称
		String taxPayerName = (String) list_map.get(0).get("TAXPAYERNAME");
		taxType.setTaxPayerName(taxPayerName);
		// 纳税人识别号
		String taxPayerIdentificationCode = (String) list_map.get(0)
				.get("TAXPAYERIDENTIFICATIONCODE");
		taxType.setTaxPayerIdentificationCode(taxPayerIdentificationCode);
		// 代收公司
		String payCompanyCode = (String) list_map.get(0).get("PAYCOMPANYCODE");
		taxType.setPayCompanyCode(payCompanyCode);
		
		// 本年纳税信息对象
		AnnualTax_Type currentTaxDue = new AnnualTax_Type();
		// 纳税地区代码
		String taxLocationCode = (String) list_map.get(0).get("TAXLOCATIONCODE");
		currentTaxDue.setTaxLocationCode(taxLocationCode);
		// 税款所属始期
		String taxStartDate = (String) list_map.get(0).get("TAXSTARTDATE");
		currentTaxDue.setTaxStartDate(taxStartDate);
		// 税款所属止期
		String taxEndDate = (String) list_map.get(0).get("TAXENDDATE");
		currentTaxDue.setTaxEndDate(taxEndDate);
		// 计税单位代码
		String taxUnitTypeCode = (String) list_map.get(0).get("TAXUNITTYPECODE");
		currentTaxDue.setTaxUnitTypeCode(taxUnitTypeCode);
		// 单位计税金额
		double unitRate = (Double) list_map.get(0).get("UNITRATE");
		currentTaxDue.setUnitRate(unitRate);
		// 当期年单位税额
		double annualTaxAmount = (Double) list_map.get(0).get("ANNUALTAXAMOUNT");
		currentTaxDue.setAnnualTaxAmount(annualTaxAmount);
		// 当期应纳税额
		double taxDue = (Double) list_map.get(0).get("TAXDUE");
		currentTaxDue.setTaxDue(taxDue);
		// 逾期时间
		String exceedDate = (String) list_map.get(0).get("EXCEEDDATE");
		currentTaxDue.setExceedDate(exceedDate);
		// 逾期天数
		int exceedDaysCount = (Integer) list_map.get(0).get("EXCEEDDAYSCOUNT");
		currentTaxDue.setExceedDaysCount(exceedDaysCount);
		// 滞纳金
		double overDue = (Double) list_map.get(0).get("OVERDUE");
		currentTaxDue.setOverDue(overDue);
		// 合计金额
		double totalAmount = (Double) list_map.get(0).get("TOTALAMOUNT");
		currentTaxDue.setTotalAmount(totalAmount);
		
		// 适用完税对象 Paid
		Paid_Type paid = new Paid_Type();
		// 开具完税凭证的税务机关代码
		String taxDepartmentCode = (String) list_map.get(0).get("TAXDEPARTMENTCODE");
		paid.setTaxDepartmentCode(taxDepartmentCode);
		// 开具完税凭证的税务机关名称
		String taxDepartment = (String) list_map.get(0).get("TAXDEPARTMENT");
		paid.setTaxDepartment(taxDepartment);
		// 完税凭证号码
		String taxDocumentNumber = (String) list_map.get(0).get("TAXDOCUMENTNUMBER");
		paid.setTaxDocumentNumber(taxDocumentNumber);
		// 适用减免税对象 Derate
		Derate_Type derate = new Derate_Type();
		// 减免税原因代码,Y
		String deductionDueCode = (String) list_map.get(0).get("DEDUCTIONDUECODE");
		derate.setDeductionDueCode(deductionDueCode);
		// 减免税方案代码,Y
		String deductionDueType = (String) list_map.get(0).get("DEDUCTIONDUETYPE");
		derate.setDeductionDueType(deductionDueType);
		// 减免比例
		double deductionDueProportion = (Double) list_map.get(0)
				.get("DEDUCTIONDUEPROPORTION");
		derate.setDeductionDueProportion(deductionDueProportion);
		// 减免金额
		double deduction = (Double) list_map.get(0).get("DEDUCTIONDUEPROPORTION");
		derate.setDeduction(deduction);
		// 减免税凭证号
		String deductionDocumentNumber = (String) list_map.get(0)
				.get("DEDUCTIONDOCUMENTNUMBER");
		derate.setDeductionDocumentNumber(deductionDocumentNumber);
		// 税务机关代码,Y
		String taxdeductiondepartmentCode = (String) list_map.get(0)
				.get("DEDUCTIONDEPARTMENTCODE");
		derate.setTaxDepartmentCode(taxdeductiondepartmentCode);
		// 开具减免税凭证的税务机关名称,Y
		String taxdeductiondepartment = (String) list_map.get(0)
				.get("DEDUCTIONDEPARTMENT");
		derate.setTaxDepartment(taxdeductiondepartment);

		// 车船税合计金额 TaxAmount
		TaxAmount_Type taxAmount = new TaxAmount_Type();
		// 车船税交易码,用于对账服务
		TaxDealCode_Type TaxDealCode = new TaxDealCode_Type();
		String taxQueryNo = (String) list_map.get(0).get("TAXDEALCODE");
		TaxDealCode.setTaxDealCode_Type(taxQueryNo);
		taxAmount.setTaxDealCode(TaxDealCode);
		// 合计金额标志码,Y
		String taxAmount_Flag = (String) list_map.get(0).get("TAXAMOUNT_FLAG");
		taxAmount.setTaxAmount_Flag(taxAmount_Flag);
		
		double annualTaxDue = 0.0 ;
		double SumTaxDefault = 0.0;
		double SumOverdue = 0.0 ;
		double SumTax = 0.0 ;
		
		for(int i = 0 ; i < list_map.size() ; i++){
			// 本年车船税金额
			annualTaxDue += (Double) list_map.get(i).get("ANNUALTAXDUE");
			// 合计欠税金额
			SumTaxDefault += (Double) list_map.get(i).get("SUMTAXDEFAULT");
			// 合计滞纳金
			SumOverdue += (Double) list_map.get(i).get("SUMOVERDUE");
			// 合计金额,Y
			SumTax += (Double) list_map.get(i).get("SumTax");
		}
		taxAmount.setAnnualTaxDue(annualTaxDue);
		taxAmount.setSumTaxDefault(SumTaxDefault);
		taxAmount.setSumOverdue(SumOverdue);
		
		
		// 特殊车标志
		String specialcarType = (String) list_map.get(0).get("SPECIALCARTYPE");
		// 所属年度
		// paydate 代收日期 给 数据采集日期 中科软 截取时不正确  mili 2014-12-23 16:17:15 start
		String payDate = DateUtil.getStringDate((Date)list_map.get(0).get("SJCJRQ"),null);
//		String payDate = (String) list_map.get(0).get("PAYDATE");
		// paydate 代收日期 给 数据采集日期 中科软 截取时不正确  mili 2014-12-23 16:17:15 end
		
		// 确认码
		String taxConfirmNo = (String) list_map.get(0).get("TAXCONFIRMNO");
		// 根据确认码去欠税表中查询有没有欠税

		if ("P".equals(taxConditionCode)
				|| ("T".equals(taxConditionCode) && "3".equals(specialcarType))) {
			taxAmount.setAnnualTaxDue(0.0);
			SumTax = 0.0;
		}
		if ("E".equals(taxConditionCode)) {
			currentTaxDue.setTotalAmount(0.0);
		}
		// List<AnnualTax_Type> list =
		// taxPayQueryServiceDao.getQS(taxConfirmNo);
		// AnnualTax_Type[] delinquentTaxDue = new AnnualTax_Type[list.size()];
		// if(list !=null && list.size()>0){
		// for(int i = 0;i<list.size();i++){
		// delinquentTaxDue[i]=list.get(i);
		//				
		// }
		// taxType.setDelinquentTaxDue(delinquentTaxDue);
		// }

		currentTaxDue.setPaid(paid);
		currentTaxDue.setDerate(derate);
		taxType.setCurrentTaxDue(currentTaxDue);
		taxAmount.setSumTax(SumTax);
		taxType.setTaxAmount(taxAmount);
		taxType.setPayDate(payDate);
		// taxType.setDelinquentTaxDue(delinquentTaxDue);

		RKMXMap.put("DECLAREDSTATUS", list_map.get(0).get("DECLAREDSTATUS"));
		RKMXMap.put("CALCTAXFLAG", list_map.get(0).get("CALCTAXFLAG"));
		RKMXMap.put("Tax_Type", taxType);
		RKMXMap.put("RETURNCODE", "1");
		RKMXMap.put("TAXCONFIRMNO", list_map.get(0).get("TAXDEALCODE")); // 用于写轨迹表
		return RKMXMap;
	}

	public static void main(String[] args) {

		Date dateFrom = new Date("2015/05/01");
		// Date dateFrom = new Date();
		System.out.println(dateFrom);
		Date dateTo = new Date("2016/06/01");
		// Date dateTo = new Date();
		System.out.println(dateTo);
		int i = DateUtil.getMonthCount(dateFrom, dateTo);
		System.out.println(i);
	}

}
