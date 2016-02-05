package com.derun.common.match;

import java.util.ArrayList;
import java.util.List;
import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSBGXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.taxchangeconfirm.dao.impl.TaxChangeConfirmDAO_Common;

/**
 * @author MILI
 * @time 2014-3-25 16:17:19
 * @描述：批改确认一致性效验
 * */
public class TaxChangComfirm_Consistency {
	TaxChangeConfirmDAO_Common thzopDao = new TaxChangeConfirmDAO_Common();
	/**
	 * @author MILI
	 * @time 2014-3-25 16:17:19
	 * @描述：批改一致性比对效验
	 * */
	public String Consistency_N_O(TaxComfirm_Consistency_VO consNew,TaxComfirm_Consistency_VO consOld,SYJK_CCS_RKMX rkmx,BaseChangeConfirmReqInfo bccri){
		String returncode = ChkUtil.CHK_0000 ;
		String TaxConditionCode = bccri.getTaxInfo().getTaxConditionCode();
		if("T".equals(TaxConditionCode)){
			if("T".equals(consOld.getTaxConditionCode())){
				if("T".equals(consNew.getTaxConditionCode()) || "C".equals(consNew.getTaxConditionCode()) || "P".equals(consNew.getTaxConditionCode()) || "E".equals(consNew.getTaxConditionCode())){
					if(!consNew.getTaxConditionCode().equals(consOld.getTaxConditionCode())){
						returncode = ChkUtil.CHK_8414 ;			// 纳税类型不一致
					}else if(!consNew.getTaxStartDate().equals(consOld.getTaxStartDate())){
						returncode = ChkUtil.CHK_8439 ;			// 税款所属止期不一致
					}else if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8440 ;			// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8463 ;			// 税种类型代码不一致
					}else if(consNew.getUnitRate() != (consOld.getUnitRate())){
						returncode = ChkUtil.CHK_8217 ;			// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != (consOld.getAnnualTaxAmount())){
						returncode = ChkUtil.CHK_8218 ;			// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ;			// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ;			// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ;			// 合计金额不一致
					}
//					else if(consNew.getExceedDate() != consOld.getExceedDate()){
//						returncode = ChkUtil.CHK_8247 ;			// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ;			// 逾期天数不一致
//					}
					else if(consNew.getSumTaxDefault() != consOld.getSumTaxDefault()){
						returncode = ChkUtil.CHK_8437 ;			// 合计欠税金额不一致
					}else if(consNew.getSumOverdue() != consOld.getSumOverdue()){
						returncode = ChkUtil.CHK_8235 ;			// 合计滞纳金不一致
					}else if(consNew.getSumTax() != consOld.getSumTax()){
						returncode = ChkUtil.CHK_8462 ;			// 合计金额不一致
					}else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ;			// 本年滞纳金不一致
					}
//					else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
//						returncode = ChkUtil.CHK_8615 ;			// 地区代码不一致 
//					}else if(!consOld.getPayDate().equals(consNew.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ;			// 申报日期与查询不一致
//					}
					else{
						returncode = ChkUtil.CHK_0000 ;
					}
				}else{
					// System.out.println("已交过税，不能确认为拒缴");
					returncode = ChkUtil.CHK_8708 ;
				}
			}else if("E".equals(consOld.getTaxConditionCode())){
				if("E".equals(consNew.getTaxConditionCode()) || "C".equals(consNew.getTaxConditionCode()) || "P".equals(consNew.getTaxConditionCode()) || "T"	.equals(consNew.getTaxConditionCode())){
					if(!consOld.getTaxConditionCode().equals(consNew.getTaxConditionCode())){
						returncode = ChkUtil.CHK_8414 ;		// 纳税类型不一致
					}else if(!consNew.getTaxStartDate().equals(consOld.getTaxStartDate())){
						returncode = ChkUtil.CHK_8439 ;		// 税款所属始期不一致
					}else if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8440 ;		// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8463 ;		// 税种类型代码不一致
					}else if(consNew.getUnitRate() != consOld.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ;		// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != consOld.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ;		// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ;		// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consNew.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ;		// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213;		// 合计金额不一致
					}
//					else if(!consNew.getExceedDate().equals(consNew.getExceedDate())){
//						returncode = ChkUtil.CHK_8247 ;		// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ;		// 逾期天数不一致
//					}
					// 2014-9-25 20:53:02 mili
					else if(consNew.getAnnualTaxDue() != consOld.getAnnualTaxDue()){
						returncode = ChkUtil.CHK_8436 ;		// 本年车船税金额不一致
					}
					else if(consNew.getSumTaxDefault() != consOld.getSumTaxDefault()){
						returncode = ChkUtil.CHK_8437 ;		// 合计欠税金额不一致
					}else if(consNew.getSumOverdue() != consOld.getSumOverdue()){
						returncode = ChkUtil.CHK_8235 ;		// 合计滞纳金不一致
					}else if(consNew.getSumTax() != consOld.getSumTax()){
						returncode = ChkUtil.CHK_8462 ;		// 合计金额不一致
					}else if(!consNew.getDeductionDueCode().equals(consOld.getDeductionDueCode())){
						returncode = ChkUtil.CHK_8428 ;		// 减免税原因代码不一致
					}else if(!consNew.getDeductionDueType().equals(consOld.getDeductionDueType())){
						returncode = ChkUtil.CHK_8429 ;		// 减免税方案代码不一致
					}else if(consNew.getDeductionDueProportion() != consOld.getDeductionDueProportion()){
						returncode = ChkUtil.CHK_8430 ;		// 减免比例不一致
					}else if(consNew.getDeduction() != consOld.getDeduction()){
						returncode = ChkUtil.CHK_8431 ;		// 减免金额不一致
					}else if(!consNew.getDepartment().equals(consOld.getDepartment())){
						returncode = ChkUtil.CHK_8425 ;		// 开具减免税凭证的税务机关名称不一致
					}else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ;		// 本年滞纳金不一致
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ;		// 地区代码不一致 
					}
//					else if(!consNew.getPayDate().equals(consOld.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ;		// 申报日期与查询不一致
//					}
					else {
						returncode = ChkUtil.CHK_0000 ;
					}
				}else{
//					System.out.println("已交过税，不能确认为拒缴");
					returncode = ChkUtil.CHK_8708 ;
				}
			}
			else{
				// 纳税类型不一致
				returncode = ChkUtil.CHK_8414 ;
			}
		}else if("C".equals(TaxConditionCode)){
			if("C".equals(consOld.getTaxConditionCode()) || "E".equals(consOld.getTaxConditionCode())){
				if("C".equals(consNew.getTaxConditionCode()) || "E".equals(consNew.getTaxConditionCode())){
					if(!consNew.getTaxConditionCode().equals(consOld.getTaxConditionCode())){
						returncode = ChkUtil.CHK_8414 ; 	// 纳税类型不一致
					}else if(!consNew.getTaxStartDate().equals(consOld.getTaxStartDate())){
						returncode = ChkUtil.CHK_8439 ; 	// 税款所属始期不一致
					}else if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8440 ; 	// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8463 ; 	// 税种类型代码不一致
					}else if(consOld.getUnitRate() != consNew.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ; 	// 单位计税金额不一致
					}else if(consOld.getAnnualTaxAmount() != consNew.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ; 	// 当期年单位税额不一致
					}else if(consOld.getTaxDue() != consNew.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ; 	// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ; 	// 计税单位代码不一致
					}else if(consOld.getTotalAmount() != consNew.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ; 	// 合计金额不一致
					}
//					else if(consNew.getExceedDate() != consOld.getExceedDate()){
//						returncode = ChkUtil.CHK_8247 ; 	// 逾期日期不一致
//					}
//					else if(consOld.getExceedDaysCount() != consNew.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ; 	// 逾期天数不一致
//					}
					// 2014-9-25 20:53:02 mili
					else if(consOld.getAnnualTaxDue() != consNew.getAnnualTaxDue()){
						returncode = ChkUtil.CHK_8436 ; 	// 本年车船税金额不一致
					}
					else if(consOld.getSumTaxDefault() != consNew.getSumTaxDefault()){
						returncode = ChkUtil.CHK_8437 ; 	// 合计欠税金额不一致
					}else if(consOld.getSumOverdue() != consNew.getSumOverdue()){
						returncode = ChkUtil.CHK_8235 ; 	// 合计滞纳金不一致
					}else if(consOld.getSumTax() != consNew.getSumTax()){
						returncode = ChkUtil.CHK_8462 ; 	// 合计金额不一致
					}else if(!consOld.getDeductionDueCode().equals(consNew.getDeductionDueCode())){
						returncode = ChkUtil.CHK_8428 ; 	// 减免税原因代码不一致
					}else if(!consOld.getDeductionDueType().equals(consNew.getDeductionDueType())){
						returncode = ChkUtil.CHK_8429 ; 	// 减免税方案代码不一致
					}else if(consOld.getDeductionDueProportion() != consNew.getDeductionDueProportion()){
						returncode = ChkUtil.CHK_8430 ; 	// 减免比例不一致
					}else if(consOld.getDeduction() != consNew.getDeduction()){
						returncode = ChkUtil.CHK_8431 ; 	// 减免金额不一致
					}else if(!consOld.getDepartment().equals(consNew.getDepartment())){
						returncode = ChkUtil.CHK_8425 ; 	// 开具减免税凭证的税务机关名称不一致
					}else if(consOld.getOverDue() != consNew.getOverDue()){
						returncode = ChkUtil.CHK_8703 ; 	// 本年滞纳金不一致
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ; 	// 地区代码不一致
					}
//					else if(!consNew.getPayDate().equals(consOld.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ; 	// 申报日期与查询不一致
//					}
					else {
						returncode = ChkUtil.CHK_0000 ;
					}
				}else if("T".equals(consNew.getTaxConditionCode()) || "P".equals(consNew.getTaxConditionCode())){
//					System.out.println("纳税类型不一致");
					returncode = ChkUtil.CHK_8414 ;
				}else{
//					System.out.println("已交过税，不能确认为拒缴");
					returncode = ChkUtil.CHK_8708 ;
				}
			}else {
//				System.out.println("纳税类型不一致");
				returncode = ChkUtil.CHK_8414 ;
			}
		}else if("E".equals(TaxConditionCode)){
			if("T".equals(consOld.getTaxConditionCode())){
				if("T".equals(consNew.getTaxConditionCode())){
					if(!consNew.getTaxConditionCode().equals(consOld.getTaxConditionCode())){
						returncode = ChkUtil.CHK_8414 ; 	// 纳税类型不一致
					}else if(!consNew.getTaxStartDate().equals(consOld.getTaxStartDate())){
						returncode = ChkUtil.CHK_8439 ; 	// 税款所属始期不一致
					}else if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8440 ; 	// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8463 ; 	// 税种类型代码不一致
					}else if(consNew.getUnitRate() != consOld.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ; 	// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != consOld.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ; 	// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ; 	// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230  ; 	// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ; 	// 合计金额不一致
					}
//					else if(!consNew.getExceedDate().equals(consOld.getExceedDate())){
//						returncode = ChkUtil.CHK_8247 ; 	// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ; 	// 逾期天数不一致
//					}
					// 2014-9-25 20:53:02 mili
					else if(consNew.getAnnualTaxDue() != consOld.getAnnualTaxDue()){
						returncode = ChkUtil.CHK_8436 ; 	// 本年车船税金额不一致
					}
					else if(consNew.getSumTaxDefault() != consOld.getSumTaxDefault()){
						returncode = ChkUtil.CHK_8437 ; 	// 合计欠税金额不一致
					}else if(consNew.getSumOverdue() != consOld.getSumOverdue()){
						returncode = ChkUtil.CHK_8235 ; 	// 合计滞纳金不一致
					}else if(consNew.getSumTax() != consOld.getSumTax()){
						returncode = ChkUtil.CHK_8462 ; 	// 合计金额不一致
					}else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ; 	// 本年滞纳金不一致
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ; 	// 地区代码不一致
					}
//					else if(!consNew.getPayDate().equals(consOld.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ; 	// 申报日期与查询不一致
//					}
					else{
						returncode = ChkUtil.CHK_0000 ;
					}
				}else if("R".equals(consNew.getTaxConditionCode())){
					
				}else if("C".equals(consNew.getTaxConditionCode()) || "E".equals(consNew.getTaxConditionCode()) || "P".equals(consNew.getTaxConditionCode())){
//					System.out.println("纳税类型不一致");
					returncode = ChkUtil.CHK_8414 ;
				}else{
					System.out.println("已交过税，不能确认为拒缴");
					returncode = ChkUtil.CHK_8708 ;
				}
			}else if("E".equals(consOld.getTaxConditionCode())){
				if("E".equals(consNew.getTaxConditionCode())){
					if(!consNew.getTaxConditionCode().equals(consOld.getTaxConditionCode())){
						returncode = ChkUtil.CHK_8414 ; 	// 纳税类型不一致
					}else if(!consNew.getTaxStartDate().equals(consOld.getTaxStartDate())){
						returncode = ChkUtil.CHK_8439 ; 	// 税款所属始期不一致
					}else if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8440 ; 	// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8463 ; 	// 税种类型代码不一致
					}else if(consNew.getUnitRate() != consOld.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ; 	// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != consOld.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ; 	// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ; 	// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ; 	// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ; 	// 合计金额不一致
					}
//					else if(consNew.getExceedDate() != consOld.getExceedDate()){
//						returncode = ChkUtil.CHK_8247 ; 	// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ; 	// 逾期天数不一致
//					}
					// 2014-9-25 20:53:02 mili
					else if(consNew.getAnnualTaxDue() != consOld.getAnnualTaxDue()){
						returncode = ChkUtil.CHK_8436 ; 	// 本年车船税金额不一致
					}
					else if(consNew.getSumTaxDefault() != consOld.getSumTaxDefault()){
						returncode = ChkUtil.CHK_8437 ; 	// 合计欠税金额不一致
					}else if(consNew.getSumTax() != consOld.getSumTax()){
						returncode = ChkUtil.CHK_8235 ; 	// 合计金额不一致
					}else if(!consOld.getDeductionDueCode().equals(consNew.getDeductionDueCode())){
						returncode = ChkUtil.CHK_8428 ; 	// 减免税原因代码不一致
					}else if(!consOld.getDeductionDueType().equals(consNew.getDeductionDueType())){
						returncode = ChkUtil.CHK_8429; 	// 减免税方案代码不一致
					}else if(consNew.getDeductionDueProportion() != consOld.getDeductionDueProportion()){
						returncode = ChkUtil.CHK_8430 ; 	// 减免比例不一致
					}else if(consNew.getDeduction() != consOld.getDeduction()){
						returncode = ChkUtil.CHK_8431 ; 	// 减免金额不一致
					}else if(!consNew.getDepartment().equals(consOld.getDepartment())){
						returncode = ChkUtil.CHK_8425 ; 	// 开具减免税凭证的税务机关名称不一致
					}else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ; 	// 本年滞纳金不一致 
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ; 	// 地区代码不一致
					}
//					else if(!consOld.getPayDate().equals(consNew.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ; 	// 申报日期与查询不一致 
//					}
					else{
//						INSURE_CONFORM_FLAG = "M";
						returncode = ChkUtil.CHK_0000 ;
					}
				}else if("R".equals(consNew.getTaxConditionCode())){
//					System.out.println("纳税类型仅允许由投保询价时的正常缴税、减税变更为拒缴");
					returncode = ChkUtil.CHK_8480 ;
				}else{
					System.out.println("纳税类型不一致");
					returncode = ChkUtil.CHK_8414  ;
				}
			}else{
				System.out.println("纳税类型不一致");
				returncode = ChkUtil.CHK_8414 ;
			}
		}else if("R".equals(TaxConditionCode)){
			// R 拒缴 批改时是 E 免税 确认可以是 R 拒缴    其他情况要有纳税类型 一致性校验
//			if(!"E".equals(consOld.getTaxConditionCode()) && !"R".equals(consOld.getTaxConditionCode())){
//				return returncode = ChkUtil.CHK_8414 ; 	// 纳税类型不一致
//			}
			
			if ("T".equals(consOld.getTaxConditionCode())) {
				if("T".equals(consNew.getTaxConditionCode())){
					if(!consNew.getTaxConditionCode().equals(consOld.getTaxConditionCode())){
						returncode = ChkUtil.CHK_8414 ; 	// 纳税类型不一致
					}else if(!consNew.getTaxStartDate().equals(consOld.getTaxStartDate())){
						returncode = ChkUtil.CHK_8439 ; 	// 税款所属始期不一致
					}else if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8440 ; 	// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8463 ; 	// 税种类型代码不一致
					}else if(consNew.getUnitRate() != consOld.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ; 	// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != consOld.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ; 	// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ; 	// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ; 	// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ; 	// 合计金额不一致
					}
//					else if(!consNew.getExceedDate().equals(consOld.getExceedDate())){
//						returncode = ChkUtil.CHK_8247 ; 	// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ; 	// 逾期天数不一致
//					}
					// 2014-9-25 20:53:02 mili
					else if(consNew.getAnnualTaxDue() != consOld.getAnnualTaxDue()){
						returncode = ChkUtil.CHK_8436 ; 	// 本年车船税金额不一致
					}
					else if(consNew.getSumTaxDefault() != consOld.getSumTaxDefault()){
						returncode = ChkUtil.CHK_8437 ; 	// 合计欠税金额不一致
					}else if(consNew.getSumTax() != consOld.getSumTax()){
						returncode = ChkUtil.CHK_8462 ; 	// 合计金额不一致
					}else if(!consNew.getDeductionDueCode().equals(consOld.getDeductionDueCode())){
						returncode = ChkUtil.CHK_8428 ; 	// 减免税原因代码不一致
					}else if(!consNew.getDeductionDueType().equals(consOld.getDeductionDueType())){
						returncode = ChkUtil.CHK_8429 ; 	// 减免税方案代码不一致
					}else if(consNew.getDeductionDueProportion() != consOld.getDeductionDueProportion()){
						returncode = ChkUtil.CHK_8430 ; 	// 减免比例不一致
					}else if(consNew.getDeduction() != consOld.getDeduction()){
						returncode = ChkUtil.CHK_8431 ; 	// 减免金额不一致
					}else if(!consNew.getDepartment().equals(consOld.getDepartment())){
						returncode = ChkUtil.CHK_8425 ; 	// 开具减免税凭证的税务机关名称不一致
					}else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ; 	// 本年滞纳金不一致 
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ; 	// 地区代码不一致
					}
//					else if(!consNew.getPayDate().equals(consOld.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ; 	// 申报日期与查询不一致 
//					}
					else{
//						INSURE_CONFORM_FLAG = "M";
//						DY_falg = "NN";
						returncode = ChkUtil.CHK_0000 ;
					}
				}else if("R".equals(consNew.getTaxConditionCode())){
					if(!consNew.getTaxStartDate().equals(consOld.getTaxStartDate())){
						returncode = ChkUtil.CHK_8439 ;		// 税款所属始期不一致
					}else if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8440 ;		// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8463 ;		// 税种类型代码不一致
					}else if(consNew.getUnitRate() != consOld.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ;		// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != consOld.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ;		// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ;		// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ;		// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ;		// 合计金额不一致
					}
//					else if(!consNew.getExceedDate().equals(consOld.getExceedDate())){
//						returncode = ChkUtil.CHK_8247 ;		// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ;		// 逾期天数不一致
//					}
					else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ;		// 本年滞纳金不一致
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ;		// 地区代码不一致
					}
//					else if(!consNew.getPayDate().equals(consOld.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ;		// 申报日期与查询不一致
//					}
					else {
//						INSURE_CONFORM_FLAG = "M";
						returncode = ChkUtil.CHK_0000 ;
					}
				}else{
//					System.out.println("纳税类型不一致");
					returncode = ChkUtil.CHK_8414 ;
				}
			}else if("P".equals(consOld.getTaxConditionCode())){
				if("P".equals(consNew.getTaxConditionCode())){
					if(!consNew.getTaxConditionCode().equals(consOld.getTaxConditionCode())){
						returncode = ChkUtil.CHK_8414 ; 	// 纳税类型不一致
					}else if(!consNew.getTaxStartDate().equals(consOld.getTaxStartDate())){
						returncode = ChkUtil.CHK_8439 ; 	// 税款所属始期不一致
					}else if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8440 ; 	// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8463 ; 	// 税种类型代码不一致
					}else if(consNew.getUnitRate() != consOld.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ; 	// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != consOld.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ; 	// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ; 	// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ; 	// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ; 	// 合计金额不一致
					}
//					else if(!consNew.getExceedDate().equals(consOld.getExceedDate())){
//						returncode = ChkUtil.CHK_8247 ; 	// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ; 	// 逾期天数不一致
//					}
					// 2014-9-25 20:53:02 mili
					else if(consNew.getAnnualTaxDue() != consOld.getAnnualTaxDue()){
						returncode = ChkUtil.CHK_8436 ; 	// 本年车船税金额不一致
					}
					else if(consNew.getSumTaxDefault() != consOld.getSumTaxDefault()){
						returncode = ChkUtil.CHK_8437 ; 	// 合计欠税金额不一致
					}else if(consNew.getSumTax() != consOld.getSumTax()){
						returncode = ChkUtil.CHK_8462 ; 	// 合计金额不一致
					}else if(!consNew.getDeductionDueCode().equals(consOld.getDeductionDueCode())){
						returncode = ChkUtil.CHK_8428  ; 	// 减免税原因代码不一致
					}else if(!consNew.getDeductionDueType().equals(consOld.getDeductionDueType())){
						returncode = ChkUtil.CHK_8429 ; 	// 减免税方案代码不一致
					}else if(consNew.getDeductionDueProportion() != consOld.getDeductionDueProportion()){
						returncode = ChkUtil.CHK_8430 ; 	// 减免比例不一致
					}else if(consNew.getDeduction() != consOld.getDeduction()){
						returncode = ChkUtil.CHK_8431 ; 	// 减免金额不一致
					}else if(!consNew.getDepartment().equals(consOld.getDepartment())){
						returncode = ChkUtil.CHK_8425 ; 	// 开具减免税凭证的税务机关名称不一致
					}else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ; 	// 本年滞纳金不一致 
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ; 	// 地区代码不一致
					}
//					else if(!consNew.getPayDate().equals(consOld.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ; 	// 申报日期与查询不一致 
//					}
					else{
//						INSURE_CONFORM_FLAG = "M";
						returncode = ChkUtil.CHK_0000 ;
					}
				}else if("R".equals(consNew.getTaxConditionCode())){
//					System.out.println("纳税类型仅允许由投保询价时的正常缴税、减税变更为拒缴");
					returncode = ChkUtil.CHK_8480 ;
				}else{
//					System.out.println("纳税类型不一致");
					returncode = ChkUtil.CHK_8414 ;
				}
			}else if("E".equals(consOld.getTaxConditionCode()) || "C".equals(consOld.getTaxConditionCode())){
				if("E".equals(consNew.getTaxConditionCode()) || "C".equals(consNew.getTaxConditionCode())){
					if(!consNew.getTaxConditionCode().equals(consOld.getTaxConditionCode())){
						returncode = ChkUtil.CHK_8414 ; 	// 纳税类型不一致
					}else if(!consNew.getTaxStartDate().equals(consOld.getTaxStartDate())){
						returncode = ChkUtil.CHK_8439 ; 	// 税款所属始期不一致
					}else if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8440 ; 	// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8463 ; 	// 税种类型代码不一致
					}else if(consNew.getUnitRate() != consOld.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ; 	// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != consOld.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ; 	// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ; 	// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ; 	// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ; 	// 合计金额不一致
					}
//					else if(!consNew.getExceedDate().equals(consOld.getExceedDate())){
//						returncode = ChkUtil.CHK_8247 ; 	// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ; 	// 逾期天数不一致
//					}
					// 2014-9-25 20:53:02 mili
					else if(consNew.getAnnualTaxDue() != consOld.getAnnualTaxDue()){
						returncode = ChkUtil.CHK_8436 ; 	// 本年车船税金额不一致
					}
					else if(consNew.getSumTaxDefault() != consOld.getSumTaxDefault()){
						returncode = ChkUtil.CHK_8437 ; 	// 合计欠税金额不一致
					}else if(consNew.getSumTax() != consOld.getSumTax()){
						returncode = ChkUtil.CHK_8462  ; 	// 合计金额不一致
					}else if(!consNew.getDeductionDueCode().equals(consOld.getDeductionDueCode())){
						returncode = ChkUtil.CHK_8428 ; 	// 减免税原因代码不一致
					}else if(!consNew.getDeductionDueType().equals(consOld.getDeductionDueType())){
						returncode = ChkUtil.CHK_8429 ; 	// 减免税方案代码不一致
					}else if(consNew.getDeductionDueProportion() != consOld.getDeductionDueProportion()){
						returncode = ChkUtil.CHK_8430 ; 	// 减免比例不一致
					}else if(consNew.getDeduction() != consOld.getDeduction()){
						returncode = ChkUtil.CHK_8431 ; 	// 减免金额不一致
					}else if(!consNew.getDepartment().equals(consOld.getDepartment())){
						returncode = ChkUtil.CHK_8425 ; 	// 开具减免税凭证的税务机关名称不一致
					}else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ; 	// 本年滞纳金不一致 
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ; 	// 地区代码不一致
					}
//					else if(!consNew.getPayDate().equals(consOld.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ; 	// 申报日期与查询不一致 
//					}
					else{
//						INSURE_CONFORM_FLAG = "M";
//						DY_falg = "NN";
						returncode = ChkUtil.CHK_0000 ;
					}
				}else if("R".equals(consNew.getTaxConditionCode()) && "C".equals(consOld.getTaxConditionCode())){
					if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8439 ; 	// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8440 ; 	// 税种类型代码不一致
					}else if(consNew.getUnitRate() != consOld.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ; 	// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != consOld.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ; 	// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ; 	// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ; 	// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ; 	// 合计金额不一致
					}
//					else if(!consNew.getExceedDate().equals(consOld.getExceedDate())){
//						returncode = ChkUtil.CHK_8247 ; 	// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ; 	// 逾期天数不一致
//					}
					else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ; 	// 本年滞纳金不一致 
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ; 	// 地区代码不一致
					}
//					else if(!consNew.getPayDate().equals(consOld.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ; 	// 申报日期与查询不一致 
//					}
					else{
//						INSURE_CONFORM_FLAG = "M";
						returncode = ChkUtil.CHK_0000 ;
					}
				}else if("R".equals(consNew.getTaxConditionCode()) && "E".equals(consOld.getTaxConditionCode())){
					System.out.println("纳税类型仅允许由投保询价时的正常缴税、减税变更为拒缴");
					returncode = ChkUtil.CHK_8480 ;
				}else{
//					System.out.println("纳税类型不一致");
					returncode = ChkUtil.CHK_8414 ;
				}
			}else if("R".equals(consOld.getTaxConditionCode())){
				if("R".equals(consNew.getTaxConditionCode())){
					if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8439 ; 	// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8440 ; 	// 税种类型代码不一致
					}else if(consNew.getUnitRate() != consOld.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ; 	// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != consOld.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ; 	// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ; 	// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ; 	// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ; 	// 合计金额不一致
					}
//					else if(consNew.getExceedDate() != consOld.getExceedDate()){
//						returncode = ChkUtil.CHK_8247 ; 	// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ; 	// 逾期天数不一致
//					}
					else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ; 	// 本年滞纳金不一致 
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ; 	// 地区代码不一致
					}
//					else if(!consOld.getPayDate().equals(consNew.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ; 	// 申报日期与查询不一致 
//					}
					else{
//						INSURE_CONFORM_FLAG = "M";
						returncode = ChkUtil.CHK_0000 ;
					}
				}else{
//					System.out.println("纳税类型不在有效范围内");
					returncode = ChkUtil.CHK_8479 ;
				}
			}else{
//				System.out.println("纳税类型不在有效范围内");
				returncode = ChkUtil.CHK_8479 ;
			}
		}else if("P".equals(TaxConditionCode)){
			if("P".equals(consOld.getTaxConditionCode())){
				if("P".equals(consNew.getTaxConditionCode())){
					if(!consNew.getTaxConditionCode().equals(consOld.getTaxConditionCode())){
						returncode = ChkUtil.CHK_8414 ; 	// 纳税类型不一致
					}else if(!consNew.getTaxStartDate().equals(consOld.getTaxStartDate())){
						returncode = ChkUtil.CHK_8439 ; 	// 税款所属始期不一致
					}else if(!consNew.getTaxEndDate().equals(consOld.getTaxEndDate())){
						returncode = ChkUtil.CHK_8440 ; 	// 税款所属止期不一致
					}else if(!consNew.getTaxTermTypeCode().equals(consOld.getTaxTermTypeCode())){
						returncode = ChkUtil.CHK_8463 ; 	// 税种类型代码不一致
					}else if(consNew.getUnitRate() != consOld.getUnitRate()){
						returncode = ChkUtil.CHK_8217 ; 	// 单位计税金额不一致
					}else if(consNew.getAnnualTaxAmount() != consOld.getAnnualTaxAmount()){
						returncode = ChkUtil.CHK_8218 ; 	// 当期年单位税额不一致
					}else if(consNew.getTaxDue() != consOld.getTaxDue()){
						returncode = ChkUtil.CHK_8232 ; 	// 当期应纳税额不一致
					}else if(!consNew.getTaxUnitTypeCode().equals(consOld.getTaxUnitTypeCode())){
						returncode = ChkUtil.CHK_8230 ; 	// 计税单位代码不一致
					}else if(consNew.getTotalAmount() != consOld.getTotalAmount()){
						returncode = ChkUtil.CHK_8213 ; 	// 合计金额不一致
					}
//					else if(!consNew.getExceedDate().equals(consOld.getExceedDate())){
//						returncode = ChkUtil.CHK_8247 ; 	// 逾期日期不一致
//					}
//					else if(consNew.getExceedDaysCount() != consOld.getExceedDaysCount()){
//						returncode = ChkUtil.CHK_8248 ; 	// 逾期天数不一致
//					}
					// 2014-9-25 20:53:02 mili
					else if(consNew.getAnnualTaxDue() != consOld.getAnnualTaxDue()){
						returncode = ChkUtil.CHK_8436 ; 	// 本年车船税金额不一致
					}
					else if(consNew.getSumTaxDefault() != consOld.getSumTaxDefault()){
						returncode = ChkUtil.CHK_8437 ; 	// 合计欠税金额不一致
					}else if(consNew.getSumOverdue() != consOld.getSumOverdue()){
						returncode = ChkUtil.CHK_8235 ; 	// 合计滞纳金不一致
					}else if(consNew.getSumTax() != consOld.getSumTax()){
						returncode = ChkUtil.CHK_8462 ; 	// 合计金额不一致
					}else if(!consNew.getTAXDEPARTMENT().equals(consOld.getTAXDEPARTMENT())){
						returncode = ChkUtil.CHK_8236 ; 	// 开具完税凭证的税务机关名称不一致
					}else if(!consNew.getTAXDOCUMENTNUMBER().equals(consOld.getTAXDOCUMENTNUMBER())){
						returncode = ChkUtil.CHK_8238 ; 	// 完税凭证号码不一致
					}else if(consNew.getOverDue() != consOld.getOverDue()){
						returncode = ChkUtil.CHK_8703 ; 	// 本年滞纳金不一致 
					}else if(!consNew.getTaxLocationCode().equals(consOld.getTaxLocationCode())){
						returncode = ChkUtil.CHK_8470 ; 	// 地区代码不一致
					}
//					else if(!consNew.getPayDate().equals(consOld.getPayDate())){
//						returncode = ChkUtil.CHK_8616 ; 	// 申报日期与查询不一致 
//					}
					else{
//						INSURE_CONFORM_FLAG = "M";
//						DY_falg = "NN";
						returncode = ChkUtil.CHK_0000 ;
					}
				}else if("T".equals(consNew.getTaxConditionCode()) || "C".equals(consNew.getTaxConditionCode()) || "E".equals(consNew.getTaxConditionCode())){
//					System.out.println("纳税类型不一致");
					returncode = ChkUtil.CHK_8414 ;
				}else{
//					System.out.println("纳税类型仅允许由投保询价时的正常缴税、减税变更为拒缴");
					returncode = ChkUtil.CHK_8480 ;
				}
			}else{
				System.out.println("纳税类型不一致");
				returncode = ChkUtil.CHK_8414 ;
			}
		}else{
			if(TaxConditionCode == null){
				return ChkUtil.CHK_8479 ;
			}
		}
		if("1".equals(returncode)){
			if(!"2".equals(consOld.getTSBZ())){  // 退短期不做 欠税一致性校验
				List<SYJK_CCS_CCSBGXX> Listtaxutil = thzopDao.getTaxConditionCodeMili(bccri.getChangeQueryNo().getTaxDealCode_Type()); // 欠税记录
				if (rkmx != null && !"1".equals(rkmx.getPLATFORMSTATE())) {
					if (Listtaxutil != null) {
						returncode = this.QSwingtaxes(bccri, Listtaxutil,returncode); 	// 一致性效验
						if (!returncode.equals("1") ) {
							return returncode;
						}
					} else {
						int NewTiaoShu_ = 0, OldTiaoShu_ = 0;
						if (bccri.getTaxInfo().getDelinquentTaxDue() != null) {
							NewTiaoShu_ = bccri.getTaxInfo().getDelinquentTaxDue().length;
						}
//						if (NewTiaoShu_ != OldTiaoShu_) { 		// 只效验欠税条数
//							returncode = ChkUtil.CHK_8264;
//						}
					}
				}
			}else{
				
			}
		}else{
			return returncode ;
		}
		return returncode ;
	}
	/**
	 * @author MILI
	 * @time 2014-3-26 16:29:48
	 * @ 描述：批改确认欠税一致性效验
	 * */
	public String QSwingtaxes(BaseChangeConfirmReqInfo baseconfirmReqinfo,List<SYJK_CCS_CCSBGXX> ListTaxUtilEntity_,String returnCode_){
		String returnCode = returnCode_;
		double NewUnitRate = 0.0;
		double OldUnitRate = 0.0;
		String NewTaxDepartment = "";
		String NewDeductionDueCode = "";
		String NewDeductionDueType = "";
		String NewDepartment = "";
		String NewDocumentNumber = "";
		String NewTaxEndDate = "";
		String NewTaxStartDate = "";
		double NewTotalAmount = 0.0;
		double NewDeductionDueProportion = 0.0;
		double NewDeduction = 0.0;
		String NewTaxUnitTypeCode = "";
		double NewAnnualTaxAmount = 0.0;
		double NewTaxDue = 0.0;
		String NewEXCEEDDATE = "";
		int NewEXCEEDDAYSCOUNT = 0;
		double NewOVERDUE = 0.0;
		int NewTiaoShu = 0;
		String NewTaxLocationCode = "";
		String NewTaxDepartmentCode = "";
		String NewDeductionDocumentNumber = "";
		String NewWtaxDepartmentCode = "" ;
		// -------------------------------------------------------------------
		
		String OldTaxStartDate = "";
		double OldTotalAmount = 0.0;
		String OldTaxDepartment = "";
		String OldDeductionDueCode = "";
		String OldDeductionDueType = "";
		double OldDeductionDueProportion = 0.0 ;
		double OldDeduction = 0.0;
		String OldTaxEndDate = "";
		String OldTaxUnitTypeCode = "";
		double OldAnnualTaxAmount = 0.0;
		double OldTaxDue = 0.0;
		String OldDepartment = "";
		String OldDocumentNumber = "";
		String OldEXCEEDDATE = "";
		int OldEXCEEDDAYSCOUNT = 0;
		double OldOVERDUE = 0.0;
		int OldTiaoShu = 0;
		String OldTaxLocationCode = "";
		String OldTaxDepartmentCode = "";
		String OldDeductionDocumentNumber = "";
		String OldWtaxDepartmentCode = "" ;
		if(baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue() != null){
			NewTiaoShu = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue().length;
		}
		if(ListTaxUtilEntity_ != null){
			OldTiaoShu = ListTaxUtilEntity_.size();
		}
		if(NewTiaoShu == OldTiaoShu){
			//  根据入参的 欠税 年度  决定 查出来的 欠税排序
			List<SYJK_CCS_CCSBGXX> ListTaxUtilEntity = this.MIli_Paix(ListTaxUtilEntity_,baseconfirmReqinfo);
			int nuber = ListTaxUtilEntity.size();
			for(int i = 0 ; i < nuber ; i++){
				SYJK_CCS_CCSBGXX taxbiduientity = ListTaxUtilEntity.get(i);
				if(baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue() != null && baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue().length > 0 && baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i] != null){
					NewUnitRate = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getUnitRate();// 入参单位计税金额
					OldUnitRate = taxbiduientity.getUNITRATE();
					if (baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i] != null){
						if(baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate() != null){
							NewTaxDepartment = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getTaxDepartment() == null ? "": baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getTaxDepartment(); // 入参开具减免税凭证的税务机关名称
							NewDeductionDueCode = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getDeductionDueCode() == null ? "": baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getDeductionDueCode();
							NewDeductionDueType = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getDeductionDueType() == null ? "": baseconfirmReqinfo.getTaxInfo()
								.getDelinquentTaxDue()[i].getDerate().getDeductionDueType();
							NewDeductionDueProportion = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getDeductionDueProportion();
							NewDeduction = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getDeduction();
							NewTaxDepartmentCode = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getTaxDepartmentCode() == null ? "" : baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getTaxDepartmentCode();
							NewDeductionDocumentNumber = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getDeductionDocumentNumber() == null ? "" :baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getDerate().getDeductionDocumentNumber();
						}
						String AC = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getExceedDate() == null ? ""
								: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getExceedDate();
						if(!"".equals(AC) && AC.length() >= 10){
							NewEXCEEDDATE = AC.substring(0,10);
						}else{
							NewEXCEEDDATE = "";
						}
						NewEXCEEDDAYSCOUNT = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getExceedDaysCount();
						NewOVERDUE = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getOverDue();
						NewTaxLocationCode = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxLocationCode();
					
					}
					OldTaxDepartment = taxbiduientity.getDEPARTMENT() == null ? "": taxbiduientity.getDEPARTMENT();
					OldDeductionDueCode = taxbiduientity.getDEDUCTIONDUECODE() == null ? "": taxbiduientity.getDEDUCTIONDUECODE();
					OldDeductionDueType = taxbiduientity.getDEDUCTIONDUETYPE() == null ? "": taxbiduientity.getDEDUCTIONDUETYPE();
					OldDeductionDueProportion = taxbiduientity.getDEDUCTIONDUEPROPORTION();
					OldDeduction = taxbiduientity.getDEDUCTION();
					String GG = taxbiduientity.getTAXSTARTDATE() == null ? "" : DateUtil.getStringDate(taxbiduientity.getTAXSTARTDATE(),null);
					String SD = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxStartDate() == null ? ""
							: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxStartDate();
					
					if (!"".equals(GG) && GG.length() >= 10) {
						OldTaxStartDate = GG.substring(0, 10);
					}
					if ("".equals(SD) == false && SD.length() >= 10) {
						NewTaxStartDate = SD.substring(0, 10);
					}
					//----------------------------------------------------------------------------------------------
					
					
					String FF = taxbiduientity.getTAXENDDATE() == null ? "": DateUtil.getStringDate(taxbiduientity.getTAXENDDATE(),null);
					String ED = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxEndDate() == null ? ""
							: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxEndDate();
					if ("".equals(FF) == false && FF.length() >= 10) {
						OldTaxEndDate = FF.substring(0, 10);
					}
					if ("".equals(ED) == false && ED.length() >= 10) {
						NewTaxEndDate = ED.substring(0, 10);
					}
					//----------------------------------------------------------------------------------------------
					OldTaxUnitTypeCode = taxbiduientity.getTAXUNITTYPECODE() == null ? "": taxbiduientity.getTAXUNITTYPECODE();
					NewTaxUnitTypeCode = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxUnitTypeCode() == null ? ""
							: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxUnitTypeCode();
					//----------------------------------------------------------------------------------------------
					NewAnnualTaxAmount = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getAnnualTaxAmount();// 入参当期年单位税额
					OldAnnualTaxAmount = taxbiduientity.getANNUALTAXAMOUNT();// 入参当期年单位税额
					//----------------------------------------------------------------------------------------------
					OldTaxDue = taxbiduientity.getTAXDUE();
					NewTaxDue = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxDue();
					//----------------------------------------------------------------------------------------------
					OldDepartment = taxbiduientity.getTAXDEPARTMENT() == null ? "": taxbiduientity.getTAXDEPARTMENT();
					if (baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i] != null && baseconfirmReqinfo.getTaxInfo()
							.getDelinquentTaxDue()[i].getPaid() != null) {
						NewDepartment = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDepartment() == null ? ""
								: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDepartment();
						NewDocumentNumber = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDocumentNumber() == null ? ""
								: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDocumentNumber();
						NewWtaxDepartmentCode = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDepartmentCode() == null ? "" : baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDepartmentCode();
					}
					
					
					String AG = taxbiduientity.getEXCEEDDATE() == null ? ""	: DateUtil.getStringDate(taxbiduientity.getEXCEEDDATE(),null);
					if(!"".equals(AG) && AG.length() >= 10){
						OldEXCEEDDATE = AG.substring(0,10);
					}else{
						OldEXCEEDDATE = "";
					}
					OldEXCEEDDAYSCOUNT = taxbiduientity.getEXCEEDDAYSCOUNT();
					OldOVERDUE = taxbiduientity.getOVERDUE();
					OldTaxDepartmentCode = taxbiduientity.getDEPARTMENTCODE() == null ? "" : taxbiduientity.getDEPARTMENTCODE();
					OldDocumentNumber = taxbiduientity.getDEDUCTIONDOCUMENTNUMBER() == null ? "": taxbiduientity.getDEDUCTIONDOCUMENTNUMBER();
					//----------------------------------------------------------------------------------------------
					NewTotalAmount = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTotalAmount();// 合计金额
					OldTotalAmount = taxbiduientity.getTOTALAMOUNT();// 合计金额
					OldTaxLocationCode = taxbiduientity.getTAXLOCATIONCODE() == null ? "" : taxbiduientity.getTAXLOCATIONCODE();
					OldDeductionDocumentNumber = taxbiduientity.getDEDUCTIONDOCUMENTNUMBER() == null ? "" : taxbiduientity.getDEDUCTIONDOCUMENTNUMBER();
					OldWtaxDepartmentCode = taxbiduientity.getTAXDEPARTMENTCODE() == null ? "" : taxbiduientity.getTAXDEPARTMENTCODE();
				}else{
					return returnCode = ChkUtil.CHK_0000 ;    // 欠税对象不能为空
				}
//  			mili 2015-5-7 17:25:36   因欠税信息没有一致性校验  导致 退保长期出错----------------------------------------------------------------------------------------------
				if(NewUnitRate != OldUnitRate){
					returnCode = ChkUtil.CHK_8250;  // 欠税单位计税金额不一致 
				}else if(!NewTaxDepartment.equals(OldTaxDepartment)){
					returnCode = ChkUtil.CHK_8251;	// 欠税开具减免税务机关名称不一致
				}else if(!OldDeductionDueCode.equals(NewDeductionDueCode)){
					returnCode = ChkUtil.CHK_8252;	// 欠税减免税原因代码不一致 
				}else if(!OldDeductionDueType.equals(NewDeductionDueType)){
					returnCode = ChkUtil.CHK_8253; 	// 欠税减免税方案代码不一致
				}else if(OldDeductionDueProportion != NewDeductionDueProportion){
					returnCode = ChkUtil.CHK_8254;	// 欠税减免比例不一致 
				}else if(OldDeduction != NewDeduction){
					returnCode = ChkUtil.CHK_8255;	// 欠税减免金额不一致
				}else if(!OldTaxStartDate.equals(NewTaxStartDate)){
					returnCode = ChkUtil.CHK_8256;	// 欠税税款所属始期不一致
				}else if(!OldTaxEndDate.equals(NewTaxEndDate)){
					returnCode = ChkUtil.CHK_8257;	// 欠税税款所属止期不一致
				}else if(!NewTaxUnitTypeCode.equals(OldTaxUnitTypeCode)){
					returnCode = ChkUtil.CHK_8258;	// 欠税计税单位代码不一致 
				}else if(NewAnnualTaxAmount != OldAnnualTaxAmount){
					returnCode = ChkUtil.CHK_8259;	// 欠税当期年单位税额与查询信息不一致
				}else if(NewTaxDue != OldTaxDue){
					returnCode = ChkUtil.CHK_8260;	// 欠税当期应纳税额与查询信息不一致 
				}else if(!NewDepartment.equals(OldDepartment)){
					returnCode = ChkUtil.CHK_8261;	// 欠税 开具完税凭证的税务机关名称不一致 
				}else if(!NewDocumentNumber.equals(OldDocumentNumber)){
					returnCode = ChkUtil.CHK_8262;	// 欠税完税凭证号码不一致 
				}else if(NewTotalAmount != OldTotalAmount){
					returnCode = ChkUtil.CHK_8263;	// 欠税合计金额不一致
				}
				else if(!OldEXCEEDDATE.equals(NewEXCEEDDATE)){
					returnCode = ChkUtil.CHK_8247;	// 逾期时间
				}
				else if(OldEXCEEDDAYSCOUNT != NewEXCEEDDAYSCOUNT){
					returnCode = ChkUtil.CHK_8248;	// 逾期天数
				}
				else if(OldOVERDUE != NewOVERDUE){
					returnCode = ChkUtil.CHK_8481;	// 滞纳金
				}else if(!OldTaxLocationCode.equals(NewTaxLocationCode)){
					returnCode = ChkUtil.CHK_8265; // 欠税纳税地区代码不一致
				}else if(!OldTaxDepartmentCode.equals(NewTaxDepartmentCode)){
					returnCode = ChkUtil.CHK_8266; // 欠税开具减免税凭证的税务机关代码不一致
				}else if(!OldDeductionDocumentNumber.equals(NewDeductionDocumentNumber)){
					returnCode = ChkUtil.CHK_8267; // 欠税开具减免税原因代码不一致
				}else if(!OldWtaxDepartmentCode.equals(NewWtaxDepartmentCode)){
					returnCode = ChkUtil.CHK_8268;  // 欠税开具税凭证的税务机关代码不一致
				}
			}
		}else{
			returnCode = ChkUtil.CHK_8264; // 欠税条数不符
		}
		return returnCode;
	}
	/**
	 * mili 
	 * 描述：根据入参的 欠税 年度  决定 查出来的 欠税排序
	 * 2013-12-9 12:48:05 排序
	 * */
	public List<SYJK_CCS_CCSBGXX> MIli_Paix(List<SYJK_CCS_CCSBGXX> mili_list,BaseChangeConfirmReqInfo mili_){
		List<SYJK_CCS_CCSBGXX> mili_l = mili_list ;
		boolean mili_f = MIli_PX(mili_); // 应该是 11 12  还是  12 11
		if(mili_f){  //  12 11   排序
			if(mili_list != null && mili_list.size() > 1){
				String mili_a = DateUtil.getStringDate(mili_l.get(0).getTAXSTARTDATE(),null).substring(0, 4);
				String mili_b = DateUtil.getStringDate(mili_l.get(1).getTAXSTARTDATE(),null).substring(0, 4);
				if(Integer.parseInt(mili_a) < Integer.parseInt(mili_b)){ // 如果 第一条数据的税款所属年小于 第二条的 
					mili_l = new ArrayList<SYJK_CCS_CCSBGXX>() ;
					for(int i = mili_list.size() - 1 ; i >= 0 ; i--){ //  循环最后一条放到第一条  
						mili_l.add(mili_list.get(i));
					}
				}
			}
		}else{  //  11  12 排序
			if(mili_list != null && mili_list.size() > 1){
				String mili_a = DateUtil.getStringDate(mili_l.get(0).getTAXSTARTDATE(),null).substring(0, 4);
				String mili_b = DateUtil.getStringDate(mili_l.get(1).getTAXSTARTDATE(),null).substring(0, 4);
				if(Integer.parseInt(mili_a) > Integer.parseInt(mili_b)){ // 如果 第一条数据的税款所属年大于 第二条的
					mili_l = new ArrayList<SYJK_CCS_CCSBGXX>() ;
					for(int i = mili_list.size() - 1 ; i >= 0 ; i--){
						mili_l.add(mili_list.get(i));
					}
				}
			}
		}
		return mili_l ;
	}
	/**
	 * @author MILI
	 * @time 2014-3-26 17:43:02
	 * @描述：正序 和 倒序的 判断
	 * */
	public boolean MIli_PX(BaseChangeConfirmReqInfo mili_){
		boolean mili_f = false ;
		if(mili_.getTaxInfo() != null && mili_.getTaxInfo().getDelinquentTaxDue() != null && mili_.getTaxInfo().getDelinquentTaxDue().length > 1){
			String mili_a = mili_.getTaxInfo().getDelinquentTaxDue()[0].getTaxStartDate().substring(0, 4);
			String mili_b = mili_.getTaxInfo().getDelinquentTaxDue()[1].getTaxStartDate().substring(0, 4);
			if(Integer.parseInt(mili_a) > Integer.parseInt(mili_b)){  // 第一条数据 的税款年度 大于 第二条的情况  也就是  12 11 
				mili_f = true ;
			}
		}
		return mili_f;
	}

}
