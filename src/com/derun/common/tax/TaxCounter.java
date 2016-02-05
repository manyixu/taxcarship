package com.derun.common.tax;

import java.util.HashMap;

import com.derun.beans.Tax_Type;


/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-18
 *
 * 说明
 * @version
 */
public interface TaxCounter {
	
	
	/**
	 * 取得最终算税信息
	 * @param HashMap
	 * @param CT = String 车辆分类（决定使用哪种类型算税方法）
	 * @param VT = Vehicle_Type 车辆信息
	 * @param TT = Tax_Type  上次缴税信息
	 * @param AT = AnnualTax_Type[] 历年欠税信息
	 * @return
	 */
	public Tax_Type getTax(HashMap hm) ;
	
//	/**
//	 * 计算本年应纳税款信息 
//	 * @param perTax	单位税额
//	 */
//	public AnnualTax_Type getCurrentTaxDue(double perTax);
//	
//	
//	/**
//	 * 计算历年欠税信息
//	 * @return
//	 */
//	public AnnualTax_Type[] getAnnualTaxDues();
//
//	/**
//	 * 计算合计滞纳金（每年欠税信息中的滞纳金加起来）
//	 * @param money
//	 * @param days
//	 * @return
//	 */
//	public double getSumOverDue(double money, int days);
//	
//	
//	/**
//	 * 计算合计金额（本年应缴+合计欠税+合计滞纳金）
//	 * @return
//	 */
//	public TaxAmount_Type getTaxAmount();

}
