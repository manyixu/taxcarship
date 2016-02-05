package com.derun.common.tax;

import java.util.HashMap;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.util.Tax_Type_Code;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-4-9
 *
 * 说明：算税接口代理实现类，所有税款计算方法通过此类转发
 * @version
 */
public class TaxCounterImpl implements TaxCounter {
	
	private Tax_Type tax = null;						//最终纳税信息对象
	TaxBase tb = new TaxBase();							//算税基类
	TaxCounter tc = null;								//算税工具类
	
	@SuppressWarnings("unchecked")
	@Override
	public Tax_Type getTax(HashMap hm){
		
		String carType = (String)hm.get("CT");	//车辆分类（决定使用哪种类型算税方法）
		Tax_Type lastTax = (Tax_Type)hm.get("TT");	//上次缴税信息
		String serviceType = (String)hm.get("ServiceType");	//服务类型 03=批改查询
		Tax_Type thisTax = null;	//本次算税结果
		
		//选择具体算税方法
		if(Tax_Type_Code.BLACK_LIST.equals(carType)){//问题名单车
			
			tc = new TC_QNameList();
			thisTax = tc.getTax(hm);
			
		}else if(Tax_Type_Code.NO_VOUCHER.equals(carType)){//不算税车
			
			tc = new TC_NoTax();
			thisTax = tc.getTax(hm);
			
		}else if(Tax_Type_Code.VOUCHER_M.equals(carType) || Tax_Type_Code.VOUCHER_F.equals(carType)){//免税车
			
			tc = new TC_Free();
			thisTax = tc.getTax(hm);
			
		}else if(Tax_Type_Code.DQ_VOUCHER.equals(carType)){//短期车
			
			tc = new TC_ShortTerm();
			thisTax = tc.getTax(hm);
			
		}else if(Tax_Type_Code.NEW_CARS.equals(carType)){//新车
			
			tc = new TC_Normal();
			thisTax = tc.getTax(hm);
			
		}else if(Tax_Type_Code.NEW_OWING.equals(carType)){//欠税新车
			
			tc = new TC_NormalD();
			thisTax = tc.getTax(hm);
			
		}else if(Tax_Type_Code.J_VOUCHER.equals(carType)){//减税车
			
			tc = new TC_Reduce();
			thisTax = tc.getTax(hm);
			
		}else if(Tax_Type_Code.T_VOUCHER.equals(carType)){//退税车
			
			tc = new TC_Refund();
			thisTax = tc.getTax(hm);
			
		}
		
		if(lastTax.getTaxConditionCode()!=null && "R".equals(lastTax.getTaxConditionCode())){//拒缴合计金额都返回0.00	 From SBW：2014-7-18 09:33:08
			thisTax.getTaxAmount().setAnnualTaxDue(0.00);
			thisTax.getTaxAmount().setSumOverdue(0.00);
			thisTax.getTaxAmount().setSumTax(0.00);
			thisTax.getTaxAmount().setSumTaxDefault(0.00);
		}
		
		if(null!=serviceType&&"03".equals(serviceType)){
			//变更查询算税需要合并本次和上次纳税信息对象
			tax = tb.getMergeTax(lastTax, thisTax);
		}else{
			tax = thisTax;
		}

		return tax;
	}

	public AnnualTax_Type[] getAnnualTaxDues() {
		// TODO Auto-generated method stub
		return tax.getDelinquentTaxDue();
	}

	public AnnualTax_Type getCurrentTaxDue(double perTax) {
		// TODO Auto-generated method stub
		return tax.getCurrentTaxDue();
	}

	public double getSumOverDue(double money, int days) {
		// TODO Auto-generated method stub
		double sumOverDue = 0.00;
		if(null!=tax.getDelinquentTaxDue()&&tax.getDelinquentTaxDue().length>0){
			
			for(int i=0;i<tax.getDelinquentTaxDue().length;i++){
				sumOverDue += tax.getDelinquentTaxDue()[i].getOverDue();
			}
		}
		
		return sumOverDue;
	}

	public TaxAmount_Type getTaxAmount() {
		// TODO Auto-generated method stub
		return tax.getTaxAmount();
	}

}
