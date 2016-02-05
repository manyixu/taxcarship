package com.derun.common.tax;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.car.CarType_Assist;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.common.util.Tax_Type_Code;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-20
 *
 * 说明：税款基类
 * @version
 */
public class TaxBase {
	
	Logger log = Logger.getLogger(com.derun.common.tax.TaxBase.class.getName());
	
	/**
	 * 根据车辆信息获取税目税额 
	 * @param carType 新车船税法中交强险车辆类型（配置表SysCode_SM）
	 * @param peoNum 载客人数,如果没有就传-1
	 * @param pl 排量,如果没有就传-1
	 * @param dw 整备质量,如果没有就传-1
	 */
	private double getUnitTax(int carType, int peoNum, double pl, double dw) {
		double rez = 0;
		if (carType == 11 || carType == 12) {
			//乘用车按排量计算税额
			rez = getCycjs(pl / 1000);
		} else if (carType == 13 || carType == 14 || carType == 15
				|| carType == 71 || carType == 72 || carType == 73) {
			//客车、摩托车按辆计算税额（固定不变）
			rez = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_"+carType+"M"));
		} else if (carType == 25 || carType == 26 || carType == 27
				|| carType == 28) {
			//挂车按整备质量每吨等于货车50%计算税额
			double dou = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_"+carType+"M"));
			rez = dou * dw;
		} else if (carType == 30 || carType == 50 || carType == 60) {
			rez = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_"+carType+"M")) * dw;
		} else if (carType == 21 || carType == 22 || carType == 23
				|| carType == 24 || carType == 93 || carType == 94
				|| carType == 30 || carType == 31 || carType == 40
				|| carType == 50 || carType == 51 || carType == 41
				|| carType == 60) {
			rez = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_"+carType+"M")) * dw;
		}
		return doubleFormat(rez, 2);
	}
	
	/**
	 * 取得交强险车辆类型代码
	 * @param jgcllx
	 * @return
	 */
	public int getMotorTypeCodeValue(String motorTypeCode){
		
		int motorTypeCodeValue ;
		if (motorTypeCode.equals("9A") || motorTypeCode.equals("9B")
				|| motorTypeCode.equals("AA") || motorTypeCode.equals("AC")
				|| motorTypeCode.equals("AD") || motorTypeCode.equals("AB")
				|| motorTypeCode.equals("AE") || motorTypeCode.equals("BA")
				|| motorTypeCode.equals("BB") || motorTypeCode.equals("BC")
				|| motorTypeCode.equals("BD") || motorTypeCode.equals("BE")
				|| motorTypeCode.equals("BF") || motorTypeCode.equals("BG")
				|| motorTypeCode.equals("CA") || motorTypeCode.equals("CB")
				|| motorTypeCode.equals("CC") || motorTypeCode.equals("CD")
				|| motorTypeCode.equals("CE") || motorTypeCode.equals("CF")
				|| motorTypeCode.equals("CG")) {
			motorTypeCodeValue = 94;
		} else{
			motorTypeCodeValue = Integer.parseInt(motorTypeCode);
		}		
		return motorTypeCodeValue;
	}
	
	/**
	 * 根据排量取得税目税额
	 * @param pl
	 * @return
	 */
	private double getCycjs(double pl){
		double rez = 0;
		try {
			if(pl <= 1){
				rez = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_11M1"));	//11m-0.0-1.0
			}else if(1 < pl && pl <= 1.6){
				rez = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_11M2"));	//11m-1.0-1.6
			}else if(1.6 < pl && pl <= 2){
				rez = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_11M3"));	//11m-1.6-2.0
			}else if(2 < pl && pl <= 2.5){
				rez = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_11M4"));	//11m-2.0-2.5
			}else if(2.5 < pl && pl <= 3){
				rez = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_11M5"));	//11m-2.5-3.0
			}else if(3 < pl && pl <= 4){
				rez = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_11M6"));	//11m-3.0-4.0
			}else if(pl > 4){
				rez = Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_11M7"));	//11m-4.0-0.0
			}
		} catch (Exception e) {
			rez = 0;
		}
		return rez;
	}

	/**
	 *  计算年单位税额
	 *  计算年应纳税额
	 * @param vt 车辆信息
	 * @return
	 */
	public double getUnitTax(Vehicle_Type vt) {
		double rezTax = 0;
		int carType ;
		int peoNum = -1;
		double pl = -1;	
		double dw = -1;	
		//交强险车辆类型
		
		//新车船税法中交强险车辆类型
		String motorTypeCode = vt.getMotorTypeCode() == null ? "100": vt.getMotorTypeCode();
		carType = getMotorTypeCodeValue(motorTypeCode);
		
		if(null != vt){
			peoNum = vt.getRatedPassengerCapacity(); //载客人数,如果没有就传-1
		}
		if(null != vt){
			pl = vt.getDisplacement(); //排量,如果没有就传-1
		}
		if(null != vt){
			dw = doubleFormat(vt.getWholeWeight(), 2)/1000;
		}
		rezTax = getUnitTax(carType, peoNum, pl, dw);
		//四舍五入保留两位小数
		return doubleFormat(rezTax,2);
	}
	
	/**
	 * 当期应纳税额计算通用方法
	 * @param unitTax 年单位税额
	 * @param deduction 减税金额
	 * @param deductionDueProportion 减税比例（不按比例减税传0）
	 * @param insureStartDate 投保起期
	 * @param insureEndDate 投保止期
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public double getTaxDue(double unitTax , double deduction , double deductionDueProportion , Date insureStartDate , Date insureEndDate){
		
		Double taxDue = 0.00;
		//计算两个日期中间的月份 mon/12（这里以后要加分段算税开关）
		int monthCount = 12;
		if(null != insureStartDate && null != insureEndDate){
			monthCount = DateUtil.getMonthCount(insureStartDate, insureEndDate);
		}
		if(monthCount<13){
			taxDue = unitTax * monthCount/12;
		}else{
			taxDue = unitTax;
		}
		//对于新车，先算当期再减免和先减免再算当期在“按金额减免”的情况下税款会不同，旧版程序是先算新车，这样算下来税款比较少。
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		
		if(Integer.parseInt(sdf.format(insureStartDate)) + 1 > Integer.parseInt(sdf.format(new Date()))){
			//1、减税只对当前年有效
			//2、提前续保本年和往年同减同免
			//3、节能、新能源本年、往年、前年同减同免
			
			if(deduction>0){//按金额减免
				taxDue = taxDue - deduction;
			}
			if(deductionDueProportion>0){//按比例减免
				taxDue = taxDue * (1-deductionDueProportion);
			}
		}
		if(taxDue < 0.00){
			taxDue = 0.00; //当减免的金额大于年单位税额的时候结果不能返回负数
		}
		return doubleFormat(taxDue,2);
		
	}
	
	
	/**
	 * 计算本年度纳税信息对象
	 * @param carInfo 车辆信息
	 * @param taxInfo 车辆纳税信息
	 * @param insureStartDate 投保起期
	 * @param insureEndDate 投保止期
	 * @return
	 * @throws ParseException 
	 */
	public AnnualTax_Type getCurrentTax(Vehicle_Type carInfo , Tax_Type taxInfo , Date insureStartDate , Date insureEndDate , String carType) {
		
		AnnualTax_Type currentTaxDue = new AnnualTax_Type();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		double deduction = 0.00;//减税金额
		double deductionDueProportion = 0.00;//减税比例
		
		if(null!=taxInfo.getCurrentTaxDue() && null!=taxInfo.getCurrentTaxDue().getDerate()){
			//当期应纳税额（只有判断是减税车型、问题名单车型才减税）
			currentTaxDue.setDerate(taxInfo.getCurrentTaxDue().getDerate());
//			if(Tax_Type_Code.J_VOUCHER.equals(carType) || Tax_Type_Code.BLACK_LIST.equals(carType) || Tax_Type_Code.DQ_VOUCHER.equals(carType)){//减税车
//			}
			deduction = taxInfo.getCurrentTaxDue().getDerate().getDeduction();
			deductionDueProportion = taxInfo.getCurrentTaxDue().getDerate().getDeductionDueProportion();
		}
		if(null!=taxInfo.getCurrentTaxDue() && null!=taxInfo.getCurrentTaxDue().getPaid()){
			currentTaxDue.setPaid(taxInfo.getCurrentTaxDue().getPaid());
		}
		
		Date currentEndDate = null;
		try {
			currentEndDate = sdf.parse(sdf.format(insureStartDate).substring(0,4)+"-12-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//年单位税额
		double unitTax = this.getUnitTax(carInfo);
		double taxDue = this.getTaxDue(unitTax, deduction, deductionDueProportion, insureStartDate, currentEndDate);
		currentTaxDue.setTaxDue(taxDue);
		
		String Mtc = carInfo.getMotorTypeCode() == null ? "100" : carInfo.getMotorTypeCode();
		if (Mtc.equals("11") || Mtc.equals("12") || Mtc.equals("13")
				|| Mtc.equals("14") || Mtc.equals("15") || Mtc.equals("71")
				|| Mtc.equals("72") || Mtc.equals("73") || Mtc.equals("0")) {
			currentTaxDue.setTaxUnitTypeCode("1");
		} else {
			currentTaxDue.setTaxUnitTypeCode("2");// 计税单位代码
		}
		//currentTaxDue.setUnitRate(unitTax);//单位计税金额	
		
		//modified by wbzhao 20150121 
		// 单位计税金额（UNITRATE）应该取税目税额（每辆每年多少钱/每吨每年多少钱）
//		currentTaxDue.setUnitRate(doubleFormat(Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_"+carInfo.getMotorTypeCode()+"M")),2));
		currentTaxDue.setUnitRate(doubleFormat(Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", this.getUnitRate_code(carInfo))),2));
		//wbzhao end
		
		currentTaxDue.setAnnualTaxAmount(unitTax);//当期年单位税额
		currentTaxDue.setExceedDate("");//逾期时间
		currentTaxDue.setExceedDaysCount(0);//逾期天数
		currentTaxDue.setOverDue(0.00);//滞纳金
		currentTaxDue.setTotalAmount(taxDue);//合计金额
		
		currentTaxDue.setTaxStartDate(sdf.format(insureStartDate));//税款所属起期
		currentTaxDue.setTaxEndDate(sdf.format(insureStartDate).substring(0,4)+"-12-31");//税款所属止期
		
		//返回本年税款处理.
		freeTaxDeal(currentTaxDue, taxInfo, carInfo, carType);
		
		return currentTaxDue;
	}	
	
	/**
	 * 补充欠税对象（计算税款、滞纳金等信息。问题名单单独补充，不走此方法）
	 * @param inDelinquent	欠税对象
	 * @param carInfo	车辆信息
	 * @param taxInfo	纳税信息
	 * @param carType	车辆分类
	 * @param taxPaidFlag	是否已缴税标识
	 * @return
	 */
	public AnnualTax_Type[] fillDelinquent(AnnualTax_Type[] inDelinquent, Vehicle_Type carInfo, Tax_Type taxInfo, String carType, String taxPaidFlag, HashMap hm){
		
		try{
			
			SYJK_CCS_RKMX rkmx = (SYJK_CCS_RKMX)hm.get("rkmx");
			String taxQueryDate = "";
			if(rkmx!=null && rkmx.getSJCJRQ()!=null){
				taxQueryDate = rkmx.getSJCJRQ();//投保查询日
			}
			
			if(inDelinquent!=null){
				String overDueFlag = CfgLoader.getConfigValue("SysSwitch", "NewCarOverduePaymentFlag");//新车欠税滞纳金开关
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
				AnnualTax_Type annualTax = new AnnualTax_Type();
				for(int i=0;i<inDelinquent.length;i++){
					
					annualTax = this.getCurrentTax(carInfo, taxInfo, sdf.parse(inDelinquent[i].getTaxStartDate()), null, Tax_Type_Code.NEW_OWING);
					int overDueStartYear = Integer.parseInt(sdfy.format(sdfy.parse(inDelinquent[i].getTaxStartDate()))) + 1;
					
					if(null==overDueFlag ||"1".equals(overDueFlag) ){//滞纳金开关（0=不收;1=收）
						//当投保已交税时批改时的滞纳时间应返回投保查询日，如果投保拒缴时批改时的滞纳时间应返回批改查询日（包括所有情况都是这样返回）bug199
						if(taxPaidFlag!=null && "Y".equals(taxPaidFlag) && taxQueryDate.length()>9){
							//投保时已交税
							annualTax.setExceedDate(taxQueryDate.substring(0,10));//逾期时间 = 投保查询日
						}else{
							annualTax.setExceedDate(sdf.format(new Date()));//逾期时间 = 批改查询日
						}
						annualTax.setExceedDaysCount(DateUtil.getBetweenDays(sdf.parse(String.valueOf(overDueStartYear)+"-01-01"), sdf.parse(annualTax.getExceedDate())));//逾期天数
						// mili  2015年5月13日17:26:13   滞纳金计算不准确 start
						annualTax.setOverDue(doubleFormat(this.getOverDue(annualTax.getTaxDue(), sdf.parse(String.valueOf(overDueStartYear)+"-01-01"),sdf.parse(annualTax.getExceedDate())),2));//滞纳金
						// mili  2015年5月13日17:26:13   滞纳金计算不准确 end
					}else{
						annualTax.setExceedDate(null);//逾期时间
						annualTax.setExceedDaysCount(0);//逾期天数
						annualTax.setOverDue(0.00);//滞纳金
					}
					if(!(annualTax.getTaxStartDate()!=null && annualTax.getTaxStartDate().length()>4 && annualTax.getTaxStartDate().substring(0,4).equals(sdfy.format(new Date())))){
						annualTax.setDerate(null);//往年(非提前续保)欠税不返回减税对象
					}else{
						annualTax.setExceedDate(null);//提前续保往年不返回逾期时间（2014年8月5日16:03:03）
					}
					annualTax.setUnitRate(annualTax.getUnitRate());//单位计税金额
					annualTax = freeTaxDeal(annualTax, taxInfo, carInfo, carType); //本年税款返回处理
					annualTax.setTotalAmount(doubleFormat(annualTax.getTotalAmount() + annualTax.getOverDue(), 2));//合计金额
					inDelinquent[i] = annualTax;
				}
			}
			
		}catch (Exception e) {
			log.debug("完善欠税对象出错");
			e.printStackTrace();
		}
		
		return inDelinquent;
	}
	
	/**
	 * 计算合计金额对象
	 * @param currentTaxDue
	 * @param delinquentTaxDue
	 * @return
	 */
	public TaxAmount_Type getTaxAmount(AnnualTax_Type currentTaxDue , AnnualTax_Type[] delinquentTaxDue){
		
		TaxAmount_Type taxAmount = new TaxAmount_Type();
		double annualTaxDue=0.00;// 本年车船税金额
		double sumTaxDefault=0.00;// 合计欠税金额
		double sumOverDue=0.00;// 合计滞纳金
		double sumTax=0.00;// 合计金额,Y	
		if(null!=currentTaxDue){
			annualTaxDue = currentTaxDue.getTaxDue();
		}
		if(null != delinquentTaxDue && delinquentTaxDue.length > 0){
			for(AnnualTax_Type annOverDue : delinquentTaxDue){
				if(null!=annOverDue){
					sumTaxDefault += annOverDue.getTaxDue();//欠税合计	? TotalAmount() ? AnnualTaxAmount()
					sumOverDue += annOverDue.getOverDue();//合计滞纳金
				}
			}
		}
		sumTax = annualTaxDue + sumTaxDefault + sumOverDue;//合计金额
		
		taxAmount.setAnnualTaxDue(doubleFormat(annualTaxDue, 2));
		taxAmount.setSumTaxDefault(doubleFormat(sumTaxDefault, 2));
		taxAmount.setSumOverdue(doubleFormat(sumOverDue, 2));
		taxAmount.setSumTax(doubleFormat(sumTax, 2));
		
		return taxAmount;
	}
	
	/**
	 * 计算滞纳金
	 * @param taxDue 当期应纳税额|年单位税额（与原系统一致）
	 * @param startDate	滞纳金开始计算日
	 * @param endDate 
	 * @return
	 */
	public double getOverDue(double taxDue, Date startDate,Date endDate){
		

		double overDue = 0.00;
		int exceedDaysCount = 0 ;
		if(endDate != null){
			exceedDaysCount = DateUtil.getBetweenDays(startDate,endDate);
		}else{
			exceedDaysCount = DateUtil.getBetweenDays(startDate,new Date());
		}
		
		overDue = taxDue * exceedDaysCount * 0.0005;
		
		overDue = doubleFormat(overDue, 2);
		
		return overDue;
	}
	
	/**
	 * 计算税款始期
	 * @param carInfo
	 * @param insureStartDate
	 * @param firstRegisterDate
	 * @return
	 * @throws ParseException
	 */
	public Date getTaxStartDate(Vehicle_Type carInfo, Date insureStartDate, Date firstRegisterDate) throws ParseException{
		
		Date taxStartDate = null;
		SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		String taxInsuSynFlag = CfgLoader.getConfigValue("SysSwitch", "TaxInsuSynFlag");	// 税险同步开关	1=打开;0=关闭
		boolean isLocalCar = CarType_Assist.Car_Tpye(carInfo.getLicensePlateNo());
		
		if(isLocalCar){
			if ((carInfo.getSpecialCarType()!=null && "2".equals(carInfo.getSpecialCarType())) || !sdfy.format(new Date()).equals(sdfy.format(firstRegisterDate))) {
				//特殊车标志:2临时入境 或者初登日期不是系统当前年，应缴为整年
				taxStartDate = sdfy.parse(sdfy.format(insureStartDate)+"-01-01");
			}else if("3".equals(carInfo.getSpecialCarType())){//3临时上路 税款起期==投保查询日
				taxStartDate = new Date();
			}else if("1".endsWith(taxInsuSynFlag) && Integer.parseInt(sdfy.format(insureStartDate)) > Integer.parseInt(sdfy.format(new Date()))){//提前续保
				taxStartDate = sdfy.parse(sdfy.format(insureStartDate)+"-01-01");//insureStartDate; 2014-09-23	提前续保无欠税
			}else{//完税车返回初登日期 from sbw 2014年6月12日10:36:22
				taxStartDate = firstRegisterDate;
			}
		}else{
			//外地车初登日期为当前年就按初等日期算，不是的话按投保起期算
			if("1".endsWith(taxInsuSynFlag) && Integer.parseInt(sdfy.format(insureStartDate)) > Integer.parseInt(sdfy.format(new Date()))){//提前续保
				taxStartDate =  sdfy.parse(sdfy.format(insureStartDate)+"-01-01");	//insureStartDate;	提前续保无欠税
			}else if(sdfy.format(new Date()).equals(sdfy.format(firstRegisterDate))){
				taxStartDate = firstRegisterDate;
			}else{
				taxStartDate = sdfy.parse(sdfy.format(insureStartDate)+"-01-01");
			}
		}
		return taxStartDate;
	}
	
	/**
	 * 返回本年税款处理
	 * @param annualTax
	 * @param taxInfo
	 * @param carInfo
	 * @param carType
	 * @return
	 */
	private AnnualTax_Type freeTaxDeal(AnnualTax_Type annualTax, Tax_Type taxInfo, Vehicle_Type carInfo, String carType){
		//“UnitRate”、“AnnualTaxAmount”、“TaxDue”三个字段返回全年税款（No.1）
		//法定免税taxdue和totalamount返回0.0，最后合计都返回0.0。（No.2）
		//法定免税情况下，UnitRate ,AnnualTaxAmount返回全面税款，TaxDue 返回实际应缴 ,TotalAmount返回0.0（No.3）
		//免税情况下，UnitRate ,AnnualTaxAmount返回全面税款，TaxDue ,TotalAmount 返回0.0
		//完税情况下，UnitRate ,AnnualTaxAmount返回全面税款，TaxDue ,TotalAmount 返回实际应缴
		//临时入境车，UnitRate ,AnnualTaxAmount返回全面税款，TaxDue ,TotalAmount 返回实际应缴
		SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		if(carType.equals(Tax_Type_Code.VOUCHER_F) || "P".equals(taxInfo.getTaxConditionCode()) || isCarOnSale(taxInfo)){//法定免税、完税车、商品车不算欠税
			if(carType.equals(Tax_Type_Code.VOUCHER_F)){//法定免税
				annualTax.setTotalAmount(0.00);
			}
		}else{
			if (!"P".equals(taxInfo.getTaxConditionCode())
					&& !"2".equals(carInfo.getSpecialCarType())
					&& "E".equals(taxInfo.getTaxConditionCode())
					&& Integer.parseInt(sdfy.format(new Date())) <= Integer
							.parseInt(annualTax.getTaxStartDate().substring(0,
									4))){//免税情况下(免税只对本年和往年有效,前年该收则收)
				annualTax.setTaxDue(0.00);
				annualTax.setTotalAmount(0.00);
			}
		}
		return annualTax;
	}
	
	/**
	 * 判断是否商品车
	 * @param taxInfo
	 * @return
	 */
	public boolean isCarOnSale(Tax_Type taxInfo){
		boolean isCarOnSale = false;	//是否商品车
		if(null != taxInfo.getCurrentTaxDue()){
			if(null != taxInfo.getCurrentTaxDue().getDerate()){
				String onSaleCode = CfgLoader.getConfigValue("SysCode_Reduce", "DeductionDocumentNumber");
				if(null != onSaleCode){
					if(onSaleCode.equals(taxInfo.getCurrentTaxDue().getDerate().getDeductionDocumentNumber())){
						isCarOnSale = true;
					}
				}
			}
		}
		return isCarOnSale;
	}
	
	/**
	 * 合并算税方法
	 * @param oldTax 变更前税款信息
	 * @param newTax 变更后税款信息
	 * @return
	 */
	public Tax_Type getMergeTax(Tax_Type lastTax,Tax_Type thisTax){
		
		return new Tax_Type();
	}
	
	/**
	 * double类型精度格式化（四舍五入）
	 * @param d 源金额
	 * @param num 保留位数
	 * @return
	 */
	public double doubleFormat(double d, int num){
		return new BigDecimal(""+d).setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * @author MILI
	 * @描述：因（单位计税金额不准确）
	 * @date:2015年4月1日17:38:10
	 * */
	public String getUnitRate_code(Vehicle_Type carInfo){
		String str = "" ;
		if("11".equals(carInfo.getMotorTypeCode()) || "12".equals(carInfo.getMotorTypeCode())){
			double pl = carInfo.getDisplacement() / 1000;
			if(0.0 >= pl){
				str = "S_" + carInfo.getMotorTypeCode() + "M";
			}else if(0.0 < pl && pl <= 1.0){
				str = "S_" + carInfo.getMotorTypeCode() + "M1";
			}else if(1.0 < pl && pl <= 1.6){
				str = "S_" + carInfo.getMotorTypeCode() + "M2";
			}else if(1.6 < pl && pl <= 2.0){
				str = "S_" + carInfo.getMotorTypeCode() + "M3";
			}else if(2.0 < pl && pl <= 2.5){
				str = "S_" + carInfo.getMotorTypeCode() + "M4";
			}else if(2.5 < pl && pl <= 3.0){
				str = "S_" + carInfo.getMotorTypeCode() + "M5";
			}else if(3.0 < pl && pl <= 4.0){
				str = "S_" + carInfo.getMotorTypeCode() + "M6";
			}else if(4.0 < pl){
				str = "S_" + carInfo.getMotorTypeCode() + "M7";
			}
		}else{
			str = "S_" + carInfo.getMotorTypeCode() + "M" ;
		}
		return str ;
	}
}
