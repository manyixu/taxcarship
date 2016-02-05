package com.derun.common.tax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.util.DateUtil;
import com.derun.common.util.Tax_Type_Code;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.taxpayquery.dao.impl.TaxPayQueryServiceDao;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-24
 *
 * 说明:问题车类型税款计算
 * @version
 */
public class TC_QNameList extends TaxBase implements TaxCounter {
	
	private Tax_Type tax = null;						//最终纳税信息对象
	TaxBase tb = new TaxBase();							//算税基类
	TaxCounter tc = null;								//算税工具类


	@SuppressWarnings("unchecked")
	@Override
	public Tax_Type getTax(HashMap hm) {
		/**
		 * 问题名单车算税
		 * 1、取得问题名单车欠税历年信息
		 * 2、根据车辆信息计算本年应缴
		 * 3、计算税款合计信息对象
		 */
		tax = new Tax_Type();
		
		String carType = (String)hm.get("CT");
		
		String ServiceType = (String)hm.get("ServiceType");
		SYJK_CCS_RKMX rkmx = (SYJK_CCS_RKMX)hm.get("rkmx");
		
		
		Vehicle_Type carInfo = (Vehicle_Type)hm.get("VT");	//车辆信息
		Tax_Type taxInfo = (Tax_Type)hm.get("TT");	//车辆纳税信息
		AnnualTax_Type[] delinquentTaxDue = (AnnualTax_Type[])hm.get("AT");	//问题车历年欠税信息
		List<SYJK_CCS_QGCLWTMDXX> wtmdList = (List<SYJK_CCS_QGCLWTMDXX>)hm.get("WTMD");
		String TSCLBZ = (String)hm.get("TSCLBZ");//退税处理标识
		
		Date insureStartDate = (Date)hm.get("insureStartDate");	//投保起期
		Date insureEndDate = (Date)hm.get("insureEndDate");	//投保止期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		Date firstRegisterDate = null;
		Date taxStartDate = null;
		try {
			firstRegisterDate = sdf.parse(carInfo.getFirstRegisterDate());
			taxStartDate = sdf.parse(sdfy.format(insureStartDate)+"-01-01");//在问题名单中存在，说明不是新车从投保起期年1月1日算
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//计算本年度纳税信息对象
		AnnualTax_Type currentTax = tb.getCurrentTax(carInfo, taxInfo, taxStartDate, insureEndDate, Tax_Type_Code.BLACK_LIST);
		//欠税中的本年应缴从库中去，滞纳金、逾期时间、逾期天数重新计算。
		//AnnualTax_Type[] delinquentTaxDueNew = tb.getDelinquentTaxDue(carInfo, taxInfo, firstRegisterDate, insureEndDate);
		if(delinquentTaxDue!=null && wtmdList!=null && delinquentTaxDue.length>0 && wtmdList.size()>0){
			for(int i=0; i<delinquentTaxDue.length; i++){
				for(int j=0; j<wtmdList.size(); j++){
					if(delinquentTaxDue[i].getTaxStartDate().substring(0, 4).equals(sdfy.format(wtmdList.get(j).getSKSSSQ()))){//对应年份的问题名单数据
						if("1".equals(wtmdList.get(j).getOVERDUEPAYMENT())){//问题名单滞纳金开关打开(1=收 0=不收)
							try {
								// 滞纳金计算不准确   mili  2016-1-8 16:31:27  start
								if("02".equals(ServiceType)){   //  批改时
									if(rkmx == null || (rkmx.getSUMTAXDEFAULT() <= 0.0 && "0".equals(rkmx.getCHANGETYPE()))){
										delinquentTaxDue[i].setOverDue(tb.getOverDue(wtmdList.get(j).getDQNDWSE(), sdf.parse(String.valueOf(Integer.parseInt(sdfy.format(sdfy.parse(delinquentTaxDue[i].getTaxStartDate())))+1)+"-01-01"),null));
										delinquentTaxDue[i].setExceedDate(sdf.format(new Date()));
										delinquentTaxDue[i].setTotalAmount(doubleFormat(delinquentTaxDue[i].getTotalAmount() + delinquentTaxDue[i].getOverDue(), 2));//合计金额
										delinquentTaxDue[i].setExceedDaysCount(DateUtil.getBetweenDays(sdf.parse(String.valueOf(Integer.parseInt(sdfy.format(sdfy.parse(delinquentTaxDue[i].getTaxStartDate())))+1)+"-01-01"), new Date()));
									}else{
										TaxPayQueryServiceDao TaxPayQueryServiceDao = new TaxPayQueryServiceDao();
										List<AnnualTax_Type> listAT = TaxPayQueryServiceDao.getQS(rkmx.getTAXCONFIRMNO());
										delinquentTaxDue[i].setOverDue(tb.getOverDue(wtmdList.get(j).getDQNDWSE(), sdf.parse(String.valueOf(Integer.parseInt(sdfy.format(sdfy.parse(delinquentTaxDue[i].getTaxStartDate())))+1)+"-01-01"),DateUtil.getStringDates(listAT.get(0).getExceedDate())));
										delinquentTaxDue[i].setExceedDate(listAT.get(0).getExceedDate());
										delinquentTaxDue[i].setTotalAmount(doubleFormat(delinquentTaxDue[i].getTotalAmount() + delinquentTaxDue[i].getOverDue(), 2));//合计金额
										delinquentTaxDue[i].setExceedDaysCount(DateUtil.getBetweenDays(sdf.parse(String.valueOf(Integer.parseInt(sdfy.format(sdfy.parse(delinquentTaxDue[i].getTaxStartDate())))+1)+"-01-01"), DateUtil.getStringDates(listAT.get(0).getExceedDate())));
									}
								}else{
//									delinquentTaxDue[i].setOverDue(tb.getOverDue(wtmdList.get(j).getDQNDWSE(), wtmdList.get(j).getSKSSSQ(),null));
									delinquentTaxDue[i].setOverDue(tb.getOverDue(wtmdList.get(j).getDQNDWSE(), sdf.parse(String.valueOf(Integer.parseInt(sdfy.format(sdfy.parse(delinquentTaxDue[i].getTaxStartDate())))+1)+"-01-01"),null));
									delinquentTaxDue[i].setExceedDate(sdf.format(new Date()));
									delinquentTaxDue[i].setTotalAmount(doubleFormat(delinquentTaxDue[i].getTotalAmount() + delinquentTaxDue[i].getOverDue(), 2));//合计金额
									delinquentTaxDue[i].setExceedDaysCount(DateUtil.getBetweenDays(sdf.parse(String.valueOf(Integer.parseInt(sdfy.format(sdfy.parse(delinquentTaxDue[i].getTaxStartDate())))+1)+"-01-01"), new Date()));
								}
								// 滞纳金计算不准确   mili  2016-1-8 16:31:27  end
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							delinquentTaxDue[i].setOverDue(0.00);
							delinquentTaxDue[i].setExceedDate(null);
							delinquentTaxDue[i].setExceedDaysCount(0);
							delinquentTaxDue[i].setTotalAmount(0.0);//合计金额
						}
					}
				}
			}
		}
		//提前续保往年应正常计算
		if(delinquentTaxDue!=null && delinquentTaxDue.length > 0){
			try{
				for(int i=0; i<delinquentTaxDue.length; i++){
					if(delinquentTaxDue[i].getTaxStartDate().substring(0,4).equals(sdfy.format(new Date()))){
						Date startDate = null;
						double thisYearTaxDue = 0.00;
						if(TSCLBZ!=null && "TS".equals(TSCLBZ)){//退短期
							startDate = new Date();
							//往年应退金额 = 往年已缴金额 * 应退月（=退保查询月到年底）
							thisYearTaxDue = tb.getTaxDue(delinquentTaxDue[i].getTaxDue(), 0, 0, startDate, sdf.parse(delinquentTaxDue[i].getTaxEndDate()));//应退金额
						}else{
							startDate = sdf.parse(delinquentTaxDue[i].getTaxStartDate());
						}
						delinquentTaxDue[i] = tb.getCurrentTax(carInfo, taxInfo, startDate, sdf.parse(delinquentTaxDue[i].getTaxEndDate()), carType);
						if(thisYearTaxDue > 0.00){
							delinquentTaxDue[i].setTaxDue(thisYearTaxDue);
							delinquentTaxDue[i].setTotalAmount(thisYearTaxDue);
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//计算合计金额对象
		TaxAmount_Type taxAmount = null;
		if(taxInfo.getTaxConditionCode()!=null && "E".equals(taxInfo.getTaxConditionCode())){
			taxAmount = tb.getTaxAmount(null, delinquentTaxDue);
		}else{
			taxAmount = tb.getTaxAmount(currentTax, delinquentTaxDue);
		}
		//20140630 问题名单中的税款不能退钱，20140707合计滞纳金、合计欠税不做处理
		if(taxAmount.getAnnualTaxDue()<0){
			taxAmount.setAnnualTaxDue(0.00);
		}
		if(taxAmount.getSumTax()<0){
			taxAmount.setSumTax(0.00);
		}
		
		tax.setCurrentTaxDue(currentTax);
		tax.setDelinquentTaxDue(delinquentTaxDue);
		tax.setTaxAmount(taxAmount);
		
		return tax;
	}
	
}
