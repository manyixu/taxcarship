package com.derun.common.tax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.common.util.Tax_Type_Code;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-24
 *
 * 说明:普通类型有欠税（新车欠税）税款计算
 * 本年收一整年，还要补缴欠税
 * @version
 */
public class TC_NormalD extends TaxBase implements TaxCounter {
	
	private Tax_Type tax = null;						//最终纳税信息对象
	TaxBase tb = new TaxBase();							//算税基类
	TaxCounter tc = null;								//算税工具类


	@Override
	public Tax_Type getTax(HashMap hm) {
		// TODO Auto-generated method stub
		tax = new Tax_Type();
		
		String carType = (String)hm.get("CT");
		String taxPaidFlag = (String)hm.get("TaxPaidFlag");//批改时传投保时是否已缴税标志
		Vehicle_Type carInfo = (Vehicle_Type)hm.get("VT");	//车辆信息
		Tax_Type taxInfo = (Tax_Type)hm.get("TT");	//车辆纳税信息
		AnnualTax_Type[] inDelinquent = (AnnualTax_Type[])hm.get("AT");	//历年欠税信息
		Date insureStartDate = (Date)hm.get("insureStartDate");	//投保起期
		Date insureEndDate = (Date)hm.get("insureEndDate");	//投保止期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date firstRegisterDate = null;
		Date taxStartDate = null;
		try {
			firstRegisterDate = sdf.parse(carInfo.getFirstRegisterDate());
			if(null!=hm.get("taxstartdate")){
				taxStartDate = (Date)hm.get("taxstartdate");
			}else{
				taxStartDate = tb.getTaxStartDate(carInfo, insureStartDate, firstRegisterDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//计算本年度纳税信息对象
		AnnualTax_Type currentTax = tb.getCurrentTax(carInfo, taxInfo, taxStartDate, insureEndDate, Tax_Type_Code.NEW_OWING);
		//计算欠税信息对象
//		AnnualTax_Type[] calDelinquent = tb.getDelinquentTaxDue(carInfo, taxInfo, firstRegisterDate, insureEndDate);
//		AnnualTax_Type[] delinquent = tb.filterDelinquent(inDelinquent, calDelinquent);
		AnnualTax_Type[] delinquent = tb.fillDelinquent(inDelinquent, carInfo, taxInfo, carType, taxPaidFlag, hm);
		//计算合计金额对象
		TaxAmount_Type taxAmount = tb.getTaxAmount(currentTax, delinquent);
		
		tax.setCurrentTaxDue(currentTax);
		
		tax.setDelinquentTaxDue(delinquent);
		tax.setTaxAmount(taxAmount);
		
		return tax;
	}
	
	
}
