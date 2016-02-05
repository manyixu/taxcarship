package com.derun.all.common;

import com.derun.beans.AnnualTax_Type;

/**
 * @author MILI
 * @time 2014-8-8 9:26:18
 * @描述：排序类
 * */
public class Sort_All {
	/**
	 * @author MILI
	 * @time 2014-8-8 9:27:40
	 * @描述：欠税信息排序 由小到大
	 * */
	public AnnualTax_Type[] Sort_annualTax(AnnualTax_Type[] annualtax){
		AnnualTax_Type _annualtax[] = null ;
		if(annualtax != null && annualtax.length > 1){
			for(int i = 0 ; i < annualtax.length ; i++){
				for(int j = 0 ; j < annualtax.length - i ; j++){
					if(Integer.parseInt(annualtax[j].getTaxStartDate().substring(0, 4)) > Integer.parseInt(annualtax[j + 1].getTaxStartDate().substring(0, 4))){
						
					}
				}
			}
		}else{
			_annualtax = annualtax ;
		}
		
		return _annualtax ;
	}
}
