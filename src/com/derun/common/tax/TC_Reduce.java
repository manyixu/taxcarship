package com.derun.common.tax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

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
 * 说明:减税类型税款计算
 * @version
 */
public class TC_Reduce extends TaxBase implements TaxCounter {
	
	private static Logger log = Logger.getLogger(TC_Reduce.class.getSimpleName());
	private Tax_Type tax = null;						//最终纳税信息对象
	TaxBase tb = new TaxBase();							//算税基类
	TaxCounter tc = null;								//算税工具类


	@Override
	public Tax_Type getTax(HashMap hm) {
		// TODO Auto-generated method stub
		tax = new Tax_Type();
		try {
			String carType = (String)hm.get("CT");
			String taxPaidFlag = (String)hm.get("TaxPaidFlag");//批改时传投保时是否已缴税标志
			Vehicle_Type carInfo = (Vehicle_Type)hm.get("VT");	//车辆信息
			Tax_Type taxInfo = (Tax_Type)hm.get("TT");	//车辆纳税信息
			AnnualTax_Type[] inDelinquent = (AnnualTax_Type[])hm.get("AT");	//历年欠税信息
			Date insureStartDate = (Date)hm.get("insureStartDate");//投保起期
			Date insureEndDate = (Date)hm.get("insureEndDate");	//投保止期
			String TSCLBZ = (String)hm.get("TSCLBZ");//退税处理标识
			Date firstRegisterDate = null;
			Date taxStartDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			firstRegisterDate = sdf.parse(carInfo.getFirstRegisterDate());
			if(null!=hm.get("taxstartdate")){
				taxStartDate = (Date)hm.get("taxstartdate");
			}else{
				taxStartDate = tb.getTaxStartDate(carInfo, insureStartDate, firstRegisterDate);
			}
			//历年欠税信息
//			AnnualTax_Type[] calDelinquent = tb.getDelinquentTaxDue(carInfo, taxInfo, firstRegisterDate, insureEndDate);
//			AnnualTax_Type[] delinquent = tb.filterDelinquent(inDelinquent, calDelinquent);
			AnnualTax_Type[] delinquent = tb.fillDelinquent(inDelinquent, carInfo, taxInfo, carType, taxPaidFlag, hm);
			
			//计算本年度纳税信息对象
			AnnualTax_Type currentTax = tb.getCurrentTax(carInfo, taxInfo, taxStartDate, insureEndDate, Tax_Type_Code.J_VOUCHER);
			//计算合计金额对象
			TaxAmount_Type taxAmount = tb.getTaxAmount(currentTax, delinquent);
			
//			if(TSCLBZ!=null && "T_C_S".equals(TSCLBZ)){//bug92 减税（金额、比例）退短期
//				
//				//按比例减免和按金额减免时：taxdue和totalamount是投保实际缴税金额
//				currentTax.setTaxDue(taxInfo.getCurrentTaxDue().getTaxDue());
//				currentTax.setTotalAmount(taxInfo.getCurrentTaxDue().getTotalAmount());
//				
//				if(currentTax.getDerate().getDeduction()>0){
//					//实际退税金额=平均每月的缴税金额X应退月-减免金额/实际缴税月X应退月
//					double deduction = currentTax.getDerate().getDeduction();
//					int realPayMonth = DateUtil.getMonths(sdf.parse(taxInfo.getCurrentTaxDue().getTaxStartDate()), sdf.parse(taxInfo.getCurrentTaxDue().getTaxStartDate().substring(0,4)+"-12-31"));//实际缴税月
//					int oughtRefundMonth = DateUtil.getMonths(new Date(), sdf.parse(sdf.format(new Date()).substring(0,4)+"-12-31"));//应退月 = 退保查询月到年底
//					double realRefund = currentTax.getUnitRate()/12*oughtRefundMonth - deduction/realPayMonth*oughtRefundMonth;
//					//按金额减免，最后的合计是实际的退税金额
//					taxAmount.setAnnualTaxDue(realRefund);
//					taxAmount.setSumTax(realRefund);
//				}
//				
//			}
			tax.setCurrentTaxDue(currentTax);
			tax.setDelinquentTaxDue(delinquent);
			tax.setTaxAmount(taxAmount);
			tax.setTaxConditionCode("C");
		} catch (ParseException e) {
			log.debug("减税方法计算异常："+e.getLocalizedMessage());
		}
		
		
		return tax;
	}
	

}
