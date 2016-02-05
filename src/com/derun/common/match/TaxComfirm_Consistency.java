package com.derun.common.match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.derun.beans.BaseConfirmReqInfo;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.DateUtil;
import com.derun.common.util.LogUtil;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
import com.derun.model.po.SYJK_CCS_CCSXX;
import com.derun.taxconfirm.dao.impl.TaxConfirmDAO_comm;

/**
 * @author MILI
 * @time 2014-3-26 16:29:48
 * @ 描述：投保确认效验
 * */
public class TaxComfirm_Consistency {
	TaxConfirmDAO_comm thzopDao = new TaxConfirmDAO_comm();
	
	/**
	 * @author MILI
	 * @time 2014-4-14 16:48:39
	 * @描述：投保确认一致性效验
	 * @入参：请求信息对象入参  + SYJK_CCS_CCSXX + 车船税查询出参基本信息   + rkmx
	 * @出参：String returncode
	 * */
	public String Consistency_N_O(BaseConfirmReqInfo baseconfirmReqinfo,SYJK_CCS_CCSXX ccsxx,SYJK_CCS_CCSCXCCJBXX ccscxccjb){
		LogUtil log = new LogUtil("投保确认 一致性效验");
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.startLog();
		}
		String returnCode = ChkUtil.CHK_0000;
		String condType = baseconfirmReqinfo.getTaxInfo().getTaxConditionCode();
		String NewTaxLocationCode = "" ;			// 地区代码
		String NewTaxConditionCode = ""; 			// 入参纳税类型
		String NewTaxStartDate = "";				// 入参税款所属始期
		String NewTaxEndDate = "";					// 入参税款所属止期
		double NewAnnualTaxDue = 0.00;				// 入参本年车船税金额
		double NewSumTaxDefault = 0.00;				// 入参合计欠税金额
		double NewSumOverdue = 0.00;				// 入参合计滞纳金
		double NewSumTax = 0.00;					// 入参合计金额
		String NewTaxTermTypeCode = "";				// 入参税种类型代码
		String NewExceedDate = "";					// 入参逾期时间
		int NewExceedDaysCount = 0;					// 入参逾期天数
		double NewUnitRate = 0.00;					// 入参单位计税金额
		double NewAnnualTaxAmount = 0.00;			// 入参当期年单位税额
		double NewTaxDue = 0.00;					// 当期应纳税额
		String NewTaxUnitTypeCode = "";				// 计税单位代码
		double NewTotalAmount = 0.00;				// 合计金额
		String NewDeductionDueCode = ""; 			// 入参减免税原因代码
		String NewDeductionDueType = ""; 			// 入参减免税方案代码
		double NewDeductionDueProportion = 0.00; 	// 入参减免比例
		double NewDeduction = 0.00; 				// 入参减免金额
		String NewTaxDepartment = ""; 				// 入参开具减免税凭证的税务机关名称
		String TaxDepartmentCode = "" ;				// 入参开具减免税凭证的税务机关代码
		String NewTaxDepartmentCode = "" ;			// 入参开具完税凭证的税务机关代码
		String NewDepartment = ""; 					// 入参开具完税凭证的税务机关名称
		String NewDocumentNumber = ""; 				// 入参完税凭证号码
		double NewOverDue = 0.00;
		double OldOverDue = 0.00;
		NewTaxConditionCode = baseconfirmReqinfo.getTaxInfo().getTaxConditionCode() == null ? ""
				: baseconfirmReqinfo.getTaxInfo().getTaxConditionCode();
		NewSumTax = baseconfirmReqinfo.getTaxInfo().getTaxAmount()
				.getSumTax();
		NewSumTaxDefault = baseconfirmReqinfo.getTaxInfo()
				.getTaxAmount().getSumTaxDefault();
		NewAnnualTaxDue = baseconfirmReqinfo.getTaxInfo()
				.getTaxAmount().getAnnualTaxDue();
		NewSumOverdue = baseconfirmReqinfo.getTaxInfo().getTaxAmount()
				.getSumOverdue();
		NewTaxTermTypeCode = baseconfirmReqinfo.getTaxInfo()
				.getTaxTermTypeCode() == null ? "" : baseconfirmReqinfo
				.getTaxInfo().getTaxTermTypeCode();
		if (baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue() != null) {
			NewTaxLocationCode = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getTaxLocationCode();
			NewUnitRate = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getUnitRate();
			NewAnnualTaxAmount = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getAnnualTaxAmount();
			NewTaxDue = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getTaxDue();
			NewTaxUnitTypeCode = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getTaxUnitTypeCode() == null ? ""
					: baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getTaxUnitTypeCode();
			NewTotalAmount = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getTotalAmount();
			String II = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getExceedDate() == null ? ""
					: baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getExceedDate();
			if ("".equals(II) == false && II.length() >= 10) {
				NewExceedDate = II.substring(0, 10);
			}
			NewExceedDaysCount = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getExceedDaysCount();
			NewOverDue = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getOverDue();
			String SD = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getTaxStartDate() == null ? ""
					: baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getTaxStartDate();
			String ED = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getTaxEndDate() == null ? ""
					: baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getTaxEndDate();
			if ("".equals(SD) == false && SD.length() >= 10) {
				NewTaxStartDate = SD.substring(0, 10);
			}
			if ("".equals(ED) == false && ED.length() >= 10) {
				NewTaxEndDate = ED.substring(0, 10);
			}
			if (baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue() != null
					&& baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getDerate() != null) {
				NewDeductionDueCode = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getDerate()
						.getDeductionDueCode() == null ? "": baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getDerate()
								.getDeductionDueCode();
				NewDeductionDueType = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getDerate()
						.getDeductionDueType() == null ? "": baseconfirmReqinfo.getTaxInfo()
								.getCurrentTaxDue().getDerate().getDeductionDueType();
				NewDeductionDueProportion = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getDerate()
						.getDeductionDueProportion();
				NewDeduction = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getDerate().getDeduction();
				NewTaxDepartment = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getDerate()
						.getTaxDepartment() == null ? "": baseconfirmReqinfo.getTaxInfo()
								.getCurrentTaxDue().getDerate().getTaxDepartment();
				TaxDepartmentCode = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getDerate()
				.getTaxDepartmentCode() == null ? "": baseconfirmReqinfo.getTaxInfo()
						.getCurrentTaxDue().getDerate().getTaxDepartmentCode();
				
			}
			if (baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue() != null && baseconfirmReqinfo.getTaxInfo()
							.getCurrentTaxDue().getPaid() != null) {
				NewDepartment = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getPaid().getTaxDepartment() == null ? ""
						: baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getPaid().getTaxDepartment();
				NewTaxDepartmentCode = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getPaid().getTaxDepartmentCode() == null ? ""
						: baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getPaid()
						.getTaxDepartmentCode();
				NewDocumentNumber = baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getPaid().getTaxDocumentNumber() == null ? ""
						: baseconfirmReqinfo.getTaxInfo().getCurrentTaxDue().getPaid()
								.getTaxDocumentNumber();
			}
		}
		String OldTaxLocationCode = "" ;			// 地区代码
		String OldDeductionDueCode = "";
		String OldTaxStartDate = "";
		String OldTaxEndDate = "";
		String OldTaxTermTypeCode = "";			// 税种类型代码
		String OldExceedDate = "";				// 逾期时间
		int OldExceedDaysCount = 0;				// 逾期天数
		String OldDeductionDueType = "";
		double OldDeductionDueProportion = 0.00;
		double OldDeduction = 0.00;
		String OldTaxDepartment = "";
		String OldDepartmentCode = "" ;
		String OldDepartment = "";
		String OldDocumentNumber = "";
		double OldUnitRate = 0.00;
		double OldAnnualTaxAmount = 0.00;
		double OldTaxDue = 0.00;
		String OldTaxUnitTypeCode = "";
		double OldTotalAmount = 0.00;
		String OldTaxConditionCode = "";
		String OldTaxDepartmentCode = "" ;
		double OldAnnualTaxDue = 0.00;
		double OldSumTaxDefault = 0.00;
		double OldSumOverdue = 0.00;
		double OldSumTax = 0.00;
		OldSumTax = ccscxccjb == null ? 0.00 : ccscxccjb.getSUMTAX();
		OldAnnualTaxDue = ccscxccjb == null ? 0.00: ccscxccjb.getANNUALTAXDUE();
		OldSumOverdue = ccscxccjb == null ? 0.00 : ccscxccjb.getSUMOVERDUE();
		OldSumTaxDefault = ccscxccjb == null ? 0.00: ccscxccjb.getSUMTAXDEFAULT();

		if (ccsxx != null && !"".equals(ccsxx)) {
//			 System.out.println("已查询出车船税表中数据!");
			OldTaxTermTypeCode = ccsxx.getTAXTERMTYPECODE() == null ? "": ccsxx.getTAXTERMTYPECODE();
			String XX = String.valueOf(ccsxx.getEXCEEDDATE());
			if ("".equals(XX) == false && XX.length() >= 10) {
				OldExceedDate = XX.substring(0, 10);
			} else {
				OldExceedDate = "";
			}
			OldExceedDaysCount = ccsxx.getEXCEEDDAYSCOUNT();
			OldOverDue = ccsxx.getOVERDUE();
			OldTaxConditionCode = ccsxx.getTAXCONDITIONCODE() == null ? "": ccsxx.getTAXCONDITIONCODE();
			String GG = String.valueOf(ccsxx.getTAXSTARTDATE());
			String FF = String.valueOf(ccsxx.getTAXENDDATE());
			if ("".equals(GG) == false && GG.length() >= 10) {
				OldTaxStartDate = GG.substring(0, 10);
			} else {
				OldTaxStartDate = "";
			}
			if ("".equals(FF) == false && FF.length() >= 10) {
				OldTaxEndDate = FF.substring(0, 10);
			} else {
				OldTaxEndDate = "";
			}
			OldTaxLocationCode = ccsxx.getTAXLOCATIONCODE() == null ? "": ccsxx.getTAXLOCATIONCODE();
			OldDeductionDueCode = ccsxx.getDEDUCTIONDUECODE() == null ? "": ccsxx.getDEDUCTIONDUECODE();
			OldDeductionDueType = ccsxx.getDEDUCTIONDUETYPE() == null ? "": ccsxx.getDEDUCTIONDUETYPE();
			OldDeductionDueProportion = ccsxx.getDEDUCTIONDUEPROPORTION();
			OldDeduction = ccsxx.getDEDUCTION();
			OldTaxDepartment = ccsxx.getDEPARTMENT() == null ? "" : ccsxx.getDEPARTMENT();
			OldDepartmentCode = ccsxx.getDEPARTMENTCODE() == null ? "" : ccsxx.getDEPARTMENTCODE() ;
			OldDepartment = ccsxx.getTAXDEPARTMENT() == null ? "" : ccsxx.getTAXDEPARTMENT();
			OldTaxDepartmentCode = ccsxx.getTAXDEPARTMENTCODE() == null ? "" : ccsxx.getTAXDEPARTMENTCODE();
			OldDocumentNumber = ccsxx.getTAXDOCUMENTNUMBER() == null ? "": ccsxx.getTAXDOCUMENTNUMBER();
			OldUnitRate = ccsxx.getUNITRATE();
			OldAnnualTaxAmount = ccsxx.getANNUALTAXAMOUNT();
			OldTaxDue = ccsxx.getTAXDUE();
			OldTaxUnitTypeCode = ccsxx.getTAXUNITTYPECODE() == null ? "": ccsxx.getTAXUNITTYPECODE();
			OldTotalAmount = ccsxx.getTOTALAMOUNT();
		}else{
			return returnCode = ChkUtil.CHK_8211;  // 无效查询码
		}
		//针对跨年确认信息处理
		Date date2 = new Date();
		SimpleDateFormat simfor = new SimpleDateFormat("yyyy-MM-dd");
		String confirmDate = simfor.format(date2).substring(0, 4);//获取系统日期
		String sjcj = "";
		if (ccsxx == null) {
			sjcj = confirmDate;//数据采集日期
		} else {
			sjcj = DateUtil.getStringDate(ccsxx.getSJCJRQ(),null).substring(0, 4);
		}
		if (confirmDate.equals(sjcj)) {
			if (!"R".equals(NewTaxConditionCode) && !NewTaxConditionCode.equals(OldTaxConditionCode)) {
				returnCode = ChkUtil.CHK_8214;			// T 纳税类型不一致
			}else if (IsNull(ChkUtil.CHK_8217) && (OldUnitRate != NewUnitRate)) {
				returnCode = ChkUtil.CHK_8217 ;			// 单位计税金额不一致
			} else if (IsNull(ChkUtil.CHK_8218) && (OldAnnualTaxAmount != NewAnnualTaxAmount)) {	
				returnCode = ChkUtil.CHK_8218;				// 当期年单位税额不一致
			} else if (IsNull(ChkUtil.CHK_8233) && (OldTotalAmount != NewTotalAmount)) {
				returnCode = ChkUtil.CHK_8233;				// 合计金额不一致
			} else if (IsNull(ChkUtil.CHK_8228) && (NewTaxStartDate.equals(OldTaxStartDate) == false)) {
				returnCode = ChkUtil.CHK_8228;				// 税款所属始期不一致
			} else if (IsNull(ChkUtil.CHK_8229) && (NewTaxEndDate.equals(OldTaxEndDate) == false)) {
				returnCode = ChkUtil.CHK_8229;				// 税款所属止期不一致
			} else if (IsNull(ChkUtil.CHK_8230) && (NewTaxUnitTypeCode.equals(OldTaxUnitTypeCode) == false)) {
				returnCode = ChkUtil.CHK_8230;				// 计税单位代码不一致
			} else if (IsNull(ChkUtil.CHK_8232) && (NewTaxDue != OldTaxDue)) {
				returnCode = ChkUtil.CHK_8232;				// 当期应纳税额不一致
			} else if (IsNull(ChkUtil.CHK_8239) && (NewTaxTermTypeCode.equals(OldTaxTermTypeCode) == false)) {
				returnCode = ChkUtil.CHK_8239;				// 税种类型代码不一致
			}
//			else if (IsNull(ChkUtil.CHK_8247) && (NewExceedDate.equals(OldExceedDate) == false)) {
//				returnCode = ChkUtil.CHK_8247;				// 逾期时间不一致
//			}
//			else if (IsNull(ChkUtil.CHK_8248) && (NewExceedDaysCount != OldExceedDaysCount)) {
//				returnCode = ChkUtil.CHK_8248;				// 逾期天数不一致
//			}
//			else if (IsNull(ChkUtil.CHK_8249) && (OldOverDue != NewOverDue)) {
//				returnCode = ChkUtil.CHK_8249;				// 滞纳金不一致
//			}else if (IsNull(ChkUtil.CHK_8249) && (!OldTaxLocationCode.equals(NewTaxLocationCode))) {
//				returnCode = ChkUtil.CHK_8249;				// 滞纳金不一致
//			}
			if ("R".equals(condType)) {
				if (IsNull(ChkUtil.CHK_8214) && ("P".equals(OldTaxConditionCode)
						|| "E".equals(OldTaxConditionCode))) {
					returnCode = ChkUtil.CHK_8214;			// 拒缴 纳税类型不正确
				} else {
//					if (IsNull(ChkUtil.CHK_8478) && (NewSumTaxDefault != 0)) {
//						returnCode = ChkUtil.CHK_8478;		// 拒缴 合计欠税金额不为0
//					} else {
						if (IsNull(ChkUtil.CHK_8244) && (NewSumOverdue != 0)) {
							returnCode = ChkUtil.CHK_8244;	// 拒缴 合计滞纳金不为0
						} else {
							if (IsNull(ChkUtil.CHK_8245) && (NewSumTax != 0)) {
								returnCode = ChkUtil.CHK_8245; 	// 拒缴 合计金额不为0
							} else {
								if (IsNull(ChkUtil.CHK_8243) && (NewAnnualTaxDue != 0)) {
									returnCode = ChkUtil.CHK_8243;	// 拒缴 本年车船税金额不为0
								} 
//								else {
//									returnCode = ChkUtil.CHK_0000;
//								}
							}
						}
//					}
				}
			} else {
				if (IsNull(ChkUtil.CHK_8234) && (NewSumTaxDefault != OldSumTaxDefault)) {
					returnCode = ChkUtil.CHK_8234;			// 合计欠税金额不一致
				}
				if (IsNull(ChkUtil.CHK_8235) && (NewSumOverdue != OldSumOverdue)) {
					returnCode = ChkUtil.CHK_8235;			// 合计滞纳金不一致
				}
				if (IsNull(ChkUtil.CHK_8233) && (NewSumTax != OldSumTax)) {
					returnCode = ChkUtil.CHK_8233;			// 合计金额不一致
				}
				// 2014-9-25 20:50:57  mili
				if (IsNull(ChkUtil.CHK_8233) && (NewAnnualTaxDue != OldAnnualTaxDue)) {
					returnCode = ChkUtil.CHK_8233;			// 本年车船税金额不一致
				}
//				else {
//					returnCode = ChkUtil.CHK_0000;
//				}
			}

			if ("T".equals(condType)) {
				if (IsNull(ChkUtil.CHK_8214) && (!NewTaxConditionCode.equals(OldTaxConditionCode))) {
					returnCode = ChkUtil.CHK_8214;			// T 纳税类型不一致
				} 
//				else {
//					returnCode = ChkUtil.CHK_0000;
//				}
			} else if ("P".equals(condType)) {
				if (IsNull(ChkUtil.CHK_8214) && (!NewTaxConditionCode.equals(OldTaxConditionCode))) {
					returnCode = ChkUtil.CHK_8214;			// P 纳税类型不一致
				} else if (IsNull(ChkUtil.CHK_8237) && (!NewDepartment.equals(OldDepartment))) {
					returnCode = ChkUtil.CHK_8237;			// 开具完税凭证的税务机关名称不一致
				}else if (IsNull(ChkUtil.CHK_8236) && (!NewTaxDepartmentCode.equals(OldTaxDepartmentCode))) {
					returnCode = ChkUtil.CHK_8236;			// 开具完税凭证的税务机关代码不一致
				} else if (IsNull(ChkUtil.CHK_8238) && (!NewDocumentNumber.equals(OldDocumentNumber))) {
					returnCode = ChkUtil.CHK_8238;			// 开具完税凭证号码不一致
				}
//				else {
//					returnCode = ChkUtil.CHK_0000;
//				}
			} else if ("E".equals(condType) || "C".equals(condType)) {
				if (IsNull(ChkUtil.CHK_8214) && (NewTaxConditionCode.equals(OldTaxConditionCode) == false)) {
					returnCode = ChkUtil.CHK_8214;				// C || E 纳税类型不一致
				} else {
					if (IsNull(ChkUtil.CHK_8224) && (NewDeductionDueCode.equals(OldDeductionDueCode) == false)) {
						returnCode = ChkUtil.CHK_8224;			// 减免税原因代码不一致
					} else {
//						if (IsNull(ChkUtil.CHK_8225) && (NewDeductionDueType.equals(OldDeductionDueType) == false)) {
//							returnCode = ChkUtil.CHK_8225;		// 减免税方案代码不一致
//						} else {
							if (IsNull(ChkUtil.CHK_8226) && (NewDeductionDueProportion != OldDeductionDueProportion)) {
								returnCode = ChkUtil.CHK_8226;	// 减免比例不一致
							} else {
								if (IsNull(ChkUtil.CHK_8227) && (NewDeduction != OldDeduction)) {
									returnCode = ChkUtil.CHK_8227;	// 减免金额不一致
								} else {
									if (IsNull(ChkUtil.CHK_8219) && (NewTaxDepartment
											.equals(OldTaxDepartment) == false)) {
										returnCode = ChkUtil.CHK_8219;	// 开具减免税凭证的税务机关名称不一致
									}
									else {
										if(NewDepartment.equals(OldDepartment) == false){
											returnCode = ChkUtil.CHK_8237 ; // 开具减免税凭证的税务机关名称不一致
										}else {
											if(NewDocumentNumber.equals(OldDocumentNumber) == false){
												returnCode = ChkUtil.CHK_8238 ; // 入参完税凭证号码
											}else {
												if(NewTaxDepartmentCode.equals(OldTaxDepartmentCode) == false){
													returnCode = ChkUtil.CHK_8236 ; // 入参开具完税凭证的税务机关代码
												}
											}
										}
									}
								}
							}
//						}
					}
				}
			}
		}
		
		
		//  mili 2015-5-7 17:25:36   因欠税信息没有一致性校验  导致 退保长期出错  
		List<SYJK_CCS_CCSXX> Listtaxutil = thzopDao.getTaxConditionCode(baseconfirmReqinfo.getTaxQueryNo().getTaxDealCode_Type()); 
		if(Listtaxutil != null ){
			returnCode = this.QSwingtaxes(baseconfirmReqinfo,Listtaxutil,returnCode);
			if(!returnCode.equals("1")){
				return returnCode;
			}
		}
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.endLog();
		}
		return returnCode ;
	}
	/**
	 * @author MILI
	 * @time 2014-3-26 16:29:48
	 * @描述：投保确认欠税一致性效验
	 * @入参：请求信息对象入参  SYJK_CCS_CCSXX 返回码
	 * @出参：String returncode
	 * */
	public String QSwingtaxes(BaseConfirmReqInfo baseconfirmReqinfo,List<SYJK_CCS_CCSXX> ListTaxUtilEntity_,String returnCode_){
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
			List<SYJK_CCS_CCSXX> ListTaxUtilEntity = this.MIli_Paix(ListTaxUtilEntity_,baseconfirmReqinfo);
			int nuber = ListTaxUtilEntity.size();
			for(int i = 0 ; i < nuber ; i++){
				SYJK_CCS_CCSXX ccsxx = ListTaxUtilEntity.get(i);
				if(baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue() != null && baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue().length > 0 && baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i] != null){
					NewUnitRate = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getUnitRate();// 入参单位计税金额
					OldUnitRate = ccsxx.getUNITRATE();
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
					OldTaxDepartment = ccsxx.getDEPARTMENT() == null ? "": ccsxx.getDEPARTMENT();
					OldDeductionDueCode = ccsxx.getDEDUCTIONDUECODE() == null ? "": ccsxx.getDEDUCTIONDUECODE();
					OldDeductionDueType = ccsxx.getDEDUCTIONDUETYPE() == null ? "": ccsxx.getDEDUCTIONDUETYPE();
					OldDeductionDueProportion = ccsxx.getDEDUCTIONDUEPROPORTION();
					OldDeduction = ccsxx.getDEDUCTION();
					String GG = ccsxx.getTAXSTARTDATE() == null ? "" : DateUtil.getStringDate(ccsxx.getTAXSTARTDATE(),null);
					String SD = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxStartDate() == null ? ""
							: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxStartDate();
					
					if (!"".equals(GG) && GG.length() >= 10) {
						OldTaxStartDate = GG.substring(0, 10);
					}
					if ("".equals(SD) == false && SD.length() >= 10) {
						NewTaxStartDate = SD.substring(0, 10);
					}
					//----------------------------------------------------------------------------------------------
					
					
					String FF = ccsxx.getTAXENDDATE() == null ? "": DateUtil.getStringDate(ccsxx.getTAXENDDATE(),null);
					String ED = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxEndDate() == null ? ""
							: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxEndDate();
					if ("".equals(FF) == false && FF.length() >= 10) {
						OldTaxEndDate = FF.substring(0, 10);
					}
					if ("".equals(ED) == false && ED.length() >= 10) {
						NewTaxEndDate = ED.substring(0, 10);
					}
					//----------------------------------------------------------------------------------------------
					OldTaxUnitTypeCode = ccsxx.getTAXUNITTYPECODE() == null ? "": ccsxx.getTAXUNITTYPECODE();
					NewTaxUnitTypeCode = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxUnitTypeCode() == null ? ""
							: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxUnitTypeCode();
					//----------------------------------------------------------------------------------------------
					NewAnnualTaxAmount = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getAnnualTaxAmount();// 入参当期年单位税额
					OldAnnualTaxAmount = ccsxx.getANNUALTAXAMOUNT();// 入参当期年单位税额
					//----------------------------------------------------------------------------------------------
					OldTaxDue = ccsxx.getTAXDUE();
					NewTaxDue = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTaxDue();
					//----------------------------------------------------------------------------------------------
					OldDepartment = ccsxx.getTAXDEPARTMENT() == null ? "": ccsxx.getTAXDEPARTMENT();
					if (baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i] != null && baseconfirmReqinfo.getTaxInfo()
							.getDelinquentTaxDue()[i].getPaid() != null) {
						NewDepartment = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDepartment() == null ? ""
								: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDepartment();
						NewDocumentNumber = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDocumentNumber() == null ? ""
								: baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDocumentNumber();
						NewWtaxDepartmentCode = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDepartmentCode() == null ? "" : baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getPaid().getTaxDepartmentCode();
					}
					
					
					String AG = ccsxx.getEXCEEDDATE() == null ? ""	: DateUtil.getStringDate(ccsxx.getEXCEEDDATE(),null);
					if(!"".equals(AG) && AG.length() >= 10){
						OldEXCEEDDATE = AG.substring(0,10);
					}else{
						OldEXCEEDDATE = "";
					}
					OldEXCEEDDAYSCOUNT = ccsxx.getEXCEEDDAYSCOUNT();
					OldOVERDUE = ccsxx.getOVERDUE();
					OldTaxDepartmentCode = ccsxx.getDEPARTMENTCODE() == null ? "" : ccsxx.getDEPARTMENTCODE();
					OldDocumentNumber = ccsxx.getDEDUCTIONDOCUMENTNUMBER() == null ? "": ccsxx.getDEDUCTIONDOCUMENTNUMBER();
					//----------------------------------------------------------------------------------------------
					NewTotalAmount = baseconfirmReqinfo.getTaxInfo().getDelinquentTaxDue()[i].getTotalAmount();// 合计金额
					OldTotalAmount = ccsxx.getTOTALAMOUNT();// 合计金额
					OldTaxLocationCode = ccsxx.getTAXLOCATIONCODE() == null ? "" : ccsxx.getTAXLOCATIONCODE();
					OldDeductionDocumentNumber = ccsxx.getDEDUCTIONDOCUMENTNUMBER() == null ? "" : ccsxx.getDEDUCTIONDOCUMENTNUMBER();
					OldWtaxDepartmentCode = ccsxx.getTAXDEPARTMENTCODE() == null ? "" : ccsxx.getTAXDEPARTMENTCODE();
				}else{
					return returnCode ;    // 欠税对象不能为空
				}
//  			mili 2015-5-7 17:25:36   因欠税信息没有一致性校验  导致 退保长期出错
				if(IsNull(ChkUtil.CHK_8250) && (NewUnitRate != OldUnitRate)){
					returnCode = ChkUtil.CHK_8250;    // 欠税单位计税金额不一致 
				}else if(IsNull(ChkUtil.CHK_8251) && (!NewTaxDepartment.equals(OldTaxDepartment))){
					returnCode = ChkUtil.CHK_8251;	// 欠税开具减免税务机关名称不一致
				}else if(IsNull(ChkUtil.CHK_8252) && (!OldDeductionDueCode.equals(NewDeductionDueCode))){
					returnCode = ChkUtil.CHK_8252;	// 欠税减免税原因代码不一致 
				}else if(IsNull(ChkUtil.CHK_8253) && (!OldDeductionDueType.equals(NewDeductionDueType))){
					returnCode = ChkUtil.CHK_8253; 	// 欠税减免税方案代码不一致
				}else if(IsNull(ChkUtil.CHK_8254) && (OldDeductionDueProportion != NewDeductionDueProportion)){
					returnCode = ChkUtil.CHK_8254;	// 欠税减免比例不一致 
				}else if(IsNull(ChkUtil.CHK_8255) && (OldDeduction != NewDeduction)){
					returnCode = ChkUtil.CHK_8255;	// 欠税减免金额不一致
				}else if(IsNull(ChkUtil.CHK_8256) && (!OldTaxStartDate.equals(NewTaxStartDate))){
					returnCode = ChkUtil.CHK_8256;	// 欠税税款所属始期不一致
				}else if(IsNull(ChkUtil.CHK_8257) && (!OldTaxEndDate.equals(NewTaxEndDate))){
					returnCode = ChkUtil.CHK_8257;	// 欠税税款所属止期不一致
				}else if(IsNull(ChkUtil.CHK_8258) && (!NewTaxUnitTypeCode.equals(OldTaxUnitTypeCode))){
					returnCode = ChkUtil.CHK_8258;	// 欠税计税单位代码不一致 
				}else if(IsNull(ChkUtil.CHK_8259) && (NewAnnualTaxAmount != OldAnnualTaxAmount)){
					returnCode = ChkUtil.CHK_8259;	// 欠税当期年单位税额与查询信息不一致
				}else if(IsNull(ChkUtil.CHK_8260) && (NewTaxDue != OldTaxDue)){
					returnCode = ChkUtil.CHK_8260;	// 欠税当期应纳税额与查询信息不一致 
				}else if(IsNull(ChkUtil.CHK_8261) && (!NewDepartment.equals(OldDepartment))){
					returnCode = ChkUtil.CHK_8261;	// 欠税 开具完税凭证的税务机关名称不一致 
				}else if(IsNull(ChkUtil.CHK_8262) && (!NewDocumentNumber.equals(OldDocumentNumber))){
					returnCode = ChkUtil.CHK_8262;	// 欠税完税凭证号码不一致 
				}else if(IsNull(ChkUtil.CHK_8263) && (NewTotalAmount != OldTotalAmount)){
					returnCode = ChkUtil.CHK_8263;	// 欠税合计金额不一致
				}
				else if(IsNull(ChkUtil.CHK_8247) && (!OldEXCEEDDATE.equals(NewEXCEEDDATE))){
					returnCode = ChkUtil.CHK_8247;	// 欠税逾期时间不一致
				}
				else if(IsNull(ChkUtil.CHK_8248) && (OldEXCEEDDAYSCOUNT != NewEXCEEDDAYSCOUNT)){
					returnCode = ChkUtil.CHK_8248;	// 欠税逾期天数不一致
				}
				else if(IsNull(ChkUtil.CHK_8249) && (OldOVERDUE != NewOVERDUE)){
					returnCode = ChkUtil.CHK_8249;	// 欠税滞纳金不一致
				}else if(IsNull(ChkUtil.CHK_8265) && (!OldTaxLocationCode.equals(NewTaxLocationCode))){
					returnCode = ChkUtil.CHK_8265; // 欠税纳税地区代码不一致
				}else if(IsNull(ChkUtil.CHK_8266) && (!OldTaxDepartmentCode.equals(NewTaxDepartmentCode))){
					returnCode = ChkUtil.CHK_8266; // 欠税开具减免税凭证的税务机关代码不一致
				}else if(IsNull(ChkUtil.CHK_8428) && (!OldDeductionDocumentNumber.equals(NewDeductionDocumentNumber))){
					returnCode = ChkUtil.CHK_8428; // 欠税开具减免税原因代码不一致
				}else if(IsNull(ChkUtil.CHK_8268) && (!OldWtaxDepartmentCode.equals(NewWtaxDepartmentCode))){
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
	public List<SYJK_CCS_CCSXX> MIli_Paix(List<SYJK_CCS_CCSXX> mili_list,BaseConfirmReqInfo mili_){
		List<SYJK_CCS_CCSXX> mili_l = mili_list ;
		boolean mili_f = this.MIli_PX(mili_); // 应该是 11 12  还是  12 11
		if(mili_f){  //  12 11   排序
			if(mili_list != null && mili_list.size() > 1){
				String mili_a = DateUtil.getStringDate(mili_l.get(0).getTAXSTARTDATE(),null).substring(0, 4);
				String mili_b = DateUtil.getStringDate(mili_l.get(1).getTAXSTARTDATE(),null).substring(0, 4);
				if(Integer.parseInt(mili_a) < Integer.parseInt(mili_b)){ // 如果 第一条数据的税款所属年小于 第二条的 
					mili_l = new ArrayList<SYJK_CCS_CCSXX>() ;
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
					mili_l = new ArrayList<SYJK_CCS_CCSXX>() ;
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
	public boolean MIli_PX(BaseConfirmReqInfo mili_){
		boolean mili_f = false ;
		if(mili_.getTaxInfo() != null && mili_.getTaxInfo().getDelinquentTaxDue() != null && mili_.getTaxInfo().getDelinquentTaxDue().length > 1){
			if(mili_.getTaxInfo().getDelinquentTaxDue()[0].getTaxStartDate() != null && mili_.getTaxInfo().getDelinquentTaxDue()[0].getTaxStartDate().length() >= 4){
				String mili_a = mili_.getTaxInfo().getDelinquentTaxDue()[0].getTaxStartDate().substring(0, 4);
				String mili_b = mili_.getTaxInfo().getDelinquentTaxDue()[1].getTaxStartDate().substring(0, 4);
				if(Integer.parseInt(mili_a) > Integer.parseInt(mili_b)){  // 第一条数据 的税款年度 大于 第二条的情况  也就是  12 11 
					mili_f = true ;
				}
			}
		}
		return mili_f;
	}
	/**
	 * @author MILI
	 * @time 2014-6-12 11:17:24
	 * @描述：是否需要校验
	 * */
	public static boolean IsNull(String CHK_CODE){
		boolean flag = true ;
		if(CHK_CODE == null || "".equals(CHK_CODE)){
			flag = false ;
		}
		return flag ;
	}
}
