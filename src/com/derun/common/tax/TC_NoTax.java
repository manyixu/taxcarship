package com.derun.common.tax;

import java.util.Date;
import java.util.HashMap;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-24
 *
 * 说明:不用算税类型税款计算（目前只有新能源车型无法计算）
 * @version
 */
public class TC_NoTax extends TaxBase implements TaxCounter {
	
	private Tax_Type tax = null;						//最终纳税信息对象
	TaxBase tb = new TaxBase();							//算税基类
	TaxCounter tc = null;								//算税工具类


	@Override
	public Tax_Type getTax(HashMap hm) {
		// TODO Auto-generated method stub
		AnnualTax_Type ann = new AnnualTax_Type();
		ann.setDerate(new Derate_Type());
		ann.setPaid(new Paid_Type());
		tax = new Tax_Type();
		tax.setTaxConditionCode("E");//新能源免税
		tax.setTaxAmount(new TaxAmount_Type());
		tax.setCurrentTaxDue(ann);
		return tax;
	}
	
	
}
