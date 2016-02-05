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
import com.derun.common.util.Tax_Type_Code;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-24
 *
 * 说明:免税类型税款计算（法定免税要计算应纳税款）
 * @version
 */
public class TC_Free extends TaxBase implements TaxCounter {
	
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
		//投保起期、止期
		Date insureStartDate = (Date)hm.get("insureStartDate");
		Date insureEndDate = (Date)hm.get("insureEndDate");	//投保止期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		
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
		AnnualTax_Type currentTax = tb.getCurrentTax(carInfo, taxInfo, taxStartDate, insureEndDate, carType);
		AnnualTax_Type[] delinquent = null;//最终欠税信息
		
		if(carType.equals(Tax_Type_Code.VOUCHER_F) || "P".equals(taxInfo.getTaxConditionCode()) || isCarOnSale(taxInfo)){
			//法定免税、完税车、商品车不算欠税
			delinquent = null;	//法定免税车只计算本年的，历年欠税信息对象返回NULL
		}else{
			delinquent = tb.fillDelinquent(inDelinquent, carInfo, taxInfo, carType, taxPaidFlag, hm);
		}
		
		AnnualTax_Type[] delinquentTemp = null;
		if(delinquent!=null && delinquent.length>0){
			delinquentTemp = new AnnualTax_Type[delinquent.length];
			int index = 0;
			//免税情况下：若提前续保，“返回金额<taxDue、totalAmount返回税款>”的“往年”也不应算入合计
			for(int i=0;i<delinquent.length;i++){//欠税对象去掉（往年）
				if(delinquent[i].getTaxStartDate() != null && !delinquent[i].getTaxStartDate().substring(0, 4).equals(sdfy.format(new Date()))){
					delinquentTemp[index] = delinquent[i];
					index++;
				}
			}
		}else{
			delinquentTemp = delinquent;
		}
		//计算合计金额对象
		TaxAmount_Type taxAmount = tb.getTaxAmount(new AnnualTax_Type() , delinquentTemp);//合计中本年税款为0.00
		taxAmount.setAnnualTaxDue(0.00);//合计当期年纳税额
		if(carType.equals(Tax_Type_Code.VOUCHER_F)){//法定免税只计算当年的税款
			taxAmount.setSumOverdue(0.00);//合计滞纳金
			taxAmount.setSumTaxDefault(0.00);//合计欠税金额
			taxAmount.setSumTax(0.00);//合计金额
		}
		
		tax.setCurrentTaxDue(currentTax);
		tax.setDelinquentTaxDue(delinquent);
		tax.setTaxAmount(taxAmount);
		tax.setTaxConditionCode("E");
		
		return tax;
	}
	
}
