package com.derun.all.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.derun.beans.TaxAmount_Type;

/**
 * @author MILI
 * @time 2014-8-11 17:22:33
 * @描述：Double 类型 
 * */
public class Double_Decimal {
	/**
	 * @author MILI
	 * @time 2014-8-11 17:23:19
	 * @描述：保留2位小数
	 * */
	public static Double set_Double(Double _double){
		Double double_ = 0.0 ;
		double_ = new BigDecimal("" + _double).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		return double_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-8-11 17:28:01
	 * @描述：车船税合计金额  保留2为小数
	 * */
	public static TaxAmount_Type double_Amout(TaxAmount_Type _amount){
		TaxAmount_Type tax_amount = _amount;
		if(tax_amount != null){
			tax_amount.setAnnualTaxDue(set_Double(tax_amount.getAnnualTaxDue()));
			tax_amount.setSumOverdue(set_Double(tax_amount.getSumOverdue()));
			tax_amount.setSumTax(set_Double(tax_amount.getSumTax()));
			tax_amount.setSumTaxDefault(set_Double(tax_amount.getSumTaxDefault()));
		}
		return tax_amount ;
	}
	/**
	 * @author MILI
	 * @time 2014-8-22 17:22:45
	 * @描述: 3位一组
	 * */
	public static String setThree(Double dou){
		DecimalFormat df = new DecimalFormat("#,###.##");
		return df.format(dou);
	}
	/**
	 * @author MILI
	 * @time 2014-8-22 17:22:45
	 * @描述: 1位一组
	 * */
	public static String set_ZERO(int dou){
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(dou);
	}
	/**
	 * @author MILI
	 * @time 2014-8-22 18:46:41
	 * @描述：double 类型处理
	 * */
	public static String Partition(Double dou){
		return Double_Decimal.setThree(Double_Decimal.set_Double(dou)); 
	}
}
