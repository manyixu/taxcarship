package com.derun.common.tax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.util.DateUtil;
import com.derun.common.util.Tax_Type_Code;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-24
 *
 * 说明:退税类型税款计算
 * @version
 */
public class TC_Refund extends TaxBase implements TaxCounter {
	
	private Tax_Type tax = null;						//最终纳税信息对象
	TaxBase tb = new TaxBase();							//算税基类
	TaxCounter tc = null;								//算税工具类


	@Override
	public Tax_Type getTax(HashMap hm) {
		// TODO Auto-generated method stub
		AnnualTax_Type currentTaxDue = new AnnualTax_Type();
		String carType = (String)hm.get("CT");
		String taxPaidFlag = (String)hm.get("TaxPaidFlag");//批改时传投保时是否已缴税标志
		Vehicle_Type carInfo = (Vehicle_Type)hm.get("VT");	//车辆信息
		Tax_Type taxInfo = (Tax_Type)hm.get("TT");	//车辆纳税信息
		Date insureStartDate = (Date)hm.get("insureStartDate");//投保起期
		Date insureEndDate = (Date)hm.get("insureEndDate");//投保止期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		String TSCLBZ = (String)hm.get("TSCLBZ");//退税处理标识
		Date firstRegisterDate = null;
		Date taxStartDate = null;
		try {
			firstRegisterDate = sdf.parse(carInfo.getFirstRegisterDate());
//			if(null!=hm.get("taxstartdate")){
//				taxStartDate = (Date)hm.get("taxstartdate");
//			}else{
//			}
			taxStartDate = tb.getTaxStartDate(carInfo, insureStartDate, firstRegisterDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		tax = new Tax_Type();
		
		//计算本年度纳税信息对象
		AnnualTax_Type currentTax = tb.getCurrentTax(carInfo, taxInfo, taxStartDate, insureEndDate, Tax_Type_Code.T_VOUCHER);
		AnnualTax_Type[] inDelinquent = (AnnualTax_Type[])hm.get("AT");	//历年欠税信息
//		AnnualTax_Type[] calDelinquent = tb.getDelinquentTaxDue(carInfo, taxInfo, firstRegisterDate, insureEndDate);
//		AnnualTax_Type[] delinquent = tb.filterDelinquent(inDelinquent, calDelinquent);
		AnnualTax_Type[] delinquent = tb.fillDelinquent(inDelinquent, carInfo, taxInfo, carType, taxPaidFlag, hm);
		//计算合计金额对象
		TaxAmount_Type taxAmount = tb.getTaxAmount(currentTax, delinquent);
		try{
			if(TSCLBZ!=null && "TS".equals(TSCLBZ)){//退税车辆标识=退短期
				
				if(sdfy.format(new Date()).equals(currentTax.getTaxStartDate().substring(0,4))){
					//taxDue和totalAmount显示投保时的金额
					currentTax.setTaxDue(taxInfo.getCurrentTaxDue().getTaxDue());
					currentTax.setTotalAmount(taxInfo.getCurrentTaxDue().getTotalAmount());
					
					//应退金额 = 投保时月缴金额x应退月
					int realPayMonth = DateUtil.getMonths(sdf.parse(taxInfo.getCurrentTaxDue().getTaxStartDate()), sdf.parse(taxInfo.getCurrentTaxDue().getTaxStartDate().substring(0,4)+"-12-31"));//实际缴税月
					int oughtRefundMonth = DateUtil.getMonths(new Date(), sdf.parse(sdf.format(new Date()).substring(0,4)+"-12-31"));//应退月 = 退保查询月到年底
					double realRefund = tb.doubleFormat(taxInfo.getTaxAmount().getAnnualTaxDue()/realPayMonth*oughtRefundMonth, 2);
					//最后合计应返回当前年退全年的税款，往年退从投保查询年到年底的税款 Bug208
					taxAmount.setAnnualTaxDue(realRefund);
					taxAmount.setSumTax(realRefund);
				}else{//提前续保
//					if(delinquent!=null && delinquent.length>0){
//						for(int o=0; o<delinquent.length; o++){
//							if(delinquent[o].getTaxStartDate()!=null && sdfy.format(new Date()).equals(delinquent[o].getTaxStartDate().substring(0,4))){
//							}
//						}
//					}
				}
			}
		}catch (Exception e) {
			log.debug("退税方法计算异常："+e.getLocalizedMessage());
		}
//		if(TSCLBZ!=null && "T_E_S".equals(TSCLBZ)){//免税退短期
//			currentTax.setTaxDue(0.00);
//			currentTax.setTotalAmount(0.00);
//			taxAmount.setAnnualTaxDue(0.00);
//			taxAmount.setSumOverdue(0.00);
//			taxAmount.setSumTax(0.00);
//			taxAmount.setSumTaxDefault(0.00);
//		}
		
		tax.setCurrentTaxDue(currentTax);
		tax.setDelinquentTaxDue(delinquent);
		tax.setTaxAmount(taxAmount);
		
		return tax;
	}
	
}
