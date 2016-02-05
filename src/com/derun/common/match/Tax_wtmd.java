package com.derun.common.match;

import java.util.ArrayList;
import java.util.List;

import com.derun.beans.AnnualTax_Type;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX;
/**
 * @author MILI
 * @time 2014-6-27 10:13:13
 * @描述：问题名单
 * */
public class Tax_wtmd {
	/**
	 * @author MILI
	 * @time 2014-6-27 10:13:13
	 * @描述：问题名单欠税封装
	 * */
	public AnnualTax_Type[] setAnnal(List<SYJK_CCS_QGCLWTMDXX> listWt){
		List<SYJK_CCS_QGCLWTMDXX> list = new ArrayList<SYJK_CCS_QGCLWTMDXX>();
		for(SYJK_CCS_QGCLWTMDXX wtmd : listWt){
			if("W".equals(wtmd.getYCYYDM().toUpperCase())){
				continue ;
			}else{
				list.add(wtmd);
 			}
		}
		if(list != null && list.size() > 0){
			int i = 0 ;
			AnnualTax_Type[] AT = new AnnualTax_Type[list.size()] ;
			for(SYJK_CCS_QGCLWTMDXX wtmd : list){
				AT[i++] = this.setMTMD(wtmd);
			}
			return AT ;
		}else{
			return new AnnualTax_Type[0] ;
		}
	}
	/**
	 * @author MILI
	 * @time 2014-6-27 10:13:13
	 * @描述：问题名单欠税封装
	 * */
	public AnnualTax_Type setMTMD(SYJK_CCS_QGCLWTMDXX wtmd){
		AnnualTax_Type AT = new AnnualTax_Type();
		AT.setAnnualTaxAmount(wtmd.getDQNDWSE());	// 当期年单位税额
		AT.setDerate(null);							// 适用减免税对象
		AT.setExceedDate(DateUtil.getStringDate(wtmd.getYQSJ(),"yyyy-MM-dd"));		// 逾期时间
		AT.setExceedDaysCount(wtmd.getYQTS().intValue());							// 逾期天数
		AT.setOverDue(wtmd.getZNJ());				// 滞纳金
		AT.setPaid(null);							// 适用完税对象
		AT.setTaxDue(wtmd.getDQYNSE());				// 当期应纳税额
		AT.setTaxEndDate(DateUtil.getStringDate(wtmd.getSKSSZQ(),"yyyy-MM-dd"));	// 税款所属止期
		AT.setTaxLocationCode(null);				// 纳税地区代码
		AT.setTaxStartDate(DateUtil.getStringDate(wtmd.getSKSSSQ(),"yyyy-MM-dd"));	// 税款所属始期
		AT.setTaxUnitTypeCode(wtmd.getJSDW());		// 计税单位代码
		AT.setTotalAmount(wtmd.getHEJE());			// 合计金额
		AT.setUnitRate(wtmd.getDWJSJE());			// 单位计税金额
		return AT ;
	}
}
