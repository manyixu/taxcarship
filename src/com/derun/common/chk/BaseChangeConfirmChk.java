package com.derun.common.chk;

import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;
import com.derun.loggedout.dao.LoggedOutDao;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-3-11 10:14:59
 * @描述：批改确认用户名密码和入参有效性验证
 * */
public class BaseChangeConfirmChk {
	LoggedOutDao logged = new LoggedOutDao();
	public String Join_valid(Object o,Object oo) {
		String returncode = ChkUtil.CHK_0000 ;
		BaseChangeConfirmReqInfo bccci = (BaseChangeConfirmReqInfo) o ;
		Derate_Type daeate_type = bccci.getTaxInfo().getCurrentTaxDue().getDerate(); // 减免对象
		Paid_Type paid_type = bccci.getTaxInfo().getCurrentTaxDue().getPaid();	// 完税对象
		SYJK_CCS_RKMX rkmx = logged.getChangeSYJK_CCS_RKMX(bccci.getChangeQueryNo().getTaxDealCode_Type());
		String PG_Switch_R = CfgLoader.getConfigValue("SysParam", "PG_Switch_R"); // 投保确认时是否允许拒缴 开关 Y 开  N 关
		if(rkmx == null || "".equals(rkmx)){
			rkmx = new SYJK_CCS_RKMX();
		}
		if(bccci.getTaxInfo() != null){
			if(IsNull(ChkUtil.CHK_0000) && ("5".equals(bccci.getChangeType()) || "6".equals(bccci.getChangeType())
					|| "7".equals(bccci.getChangeType()))){
				return ChkUtil.CHK_0000 ; 	// 注销无需校验
			}else if(IsNull(ChkUtil.CHK_8140) && (null == bccci.getCompanyCode() || "".equals(bccci.getCompanyCode()))){
				returncode = ChkUtil.CHK_8140 ;  // 公司代码不能为空 
			}
//			else if(IsNull(ChkUtil.CHK_8139) && (null == bccci.getAreaCode() || "".equals(bccci.getAreaCode()))){
//				returncode = ChkUtil.CHK_8139 ;  // 地区代码(校验纳税地区不一致)
//			}else if(IsNull(ChkUtil.CHK_8473) && !("".equals(bccci.getTaxInfo().getPayDate()) || null == bccci.getTaxInfo().getPayDate())){
//				returncode = ChkUtil.CHK_8473 ;  // 车船税已经申报，不能注销
//			}else if(IsNull(ChkUtil.CHK_8487) && (bccci.getCalcTaxFlag() == null || "".equals(bccci.getCalcTaxFlag()))){
//				returncode = ChkUtil.CHK_8487 ;  // 算税标志不能为空
//			}else if(IsNull(ChkUtil.CHK_8404) && (!"2".equals(bccci.getChangeType()) && !"4".equals(bccci.getChangeType()))){
//				returncode = ChkUtil.CHK_8404 ;  // 车船税变更类型是否有效 2   批改确认      4  退保确认
//			}else if(IsNull(ChkUtil.CHK_8486) && (!"4".equals(bccci.getTaxInfo().getTaxAmount().getTaxAmount_Flag()))){
//				returncode = ChkUtil.CHK_8486 ;  // 确认时合计金额标识代码只能为4
//			}
			else if("T".equals(bccci.getTaxInfo().getTaxConditionCode())){
				if(bccci.getTaxInfo().getCurrentTaxDue() != null){
					if(IsNull(ChkUtil.CHK_8443) && (daeate_type != null	&& (daeate_type.getDeductionDocumentNumber() != null 
							&& !"".equals(daeate_type.getDeductionDocumentNumber())))){
						returncode = ChkUtil.CHK_8443 ;  // 纳税类型为T时，减免税凭证号不能有值
					}else if(IsNull(ChkUtil.CHK_8443) && (daeate_type != null && daeate_type.getDeductionDueCode() != null
							&& !"".equals(daeate_type.getDeductionDueCode()))){
						returncode = ChkUtil.CHK_8443 ;  // 纳税类型为T时，减免税原因代码不能有值
					}else if(IsNull(ChkUtil.CHK_8443) && (daeate_type != null && daeate_type.getDeductionDueType() != null
							&& !"".equals(daeate_type.getDeductionDueType()))){
						returncode = ChkUtil.CHK_8443 ;  // 纳税类型为T时，减免税方案代码不能有值
					}else if(IsNull(ChkUtil.CHK_8443) && (daeate_type != null && daeate_type.getTaxDepartment() != null
							&& !"".equals(daeate_type.getTaxDepartment()))){
						returncode = ChkUtil.CHK_8443 ;  // 纳税类型为T时，开具减免税凭证的税务机关名称不能有值
					}else if(IsNull(ChkUtil.CHK_8443) && (daeate_type != null && daeate_type.getDeductionDueProportion() != 0.0
							&& 0.0 != daeate_type.getDeductionDueProportion())){
						returncode = ChkUtil.CHK_8443 ;  // 纳税类型为T时，开具减免税凭证的减免比例不能有值
					}else if(IsNull(ChkUtil.CHK_8443) && (daeate_type != null && daeate_type.getDeduction() != 0.0
							&& 0.0 != daeate_type.getDeduction())){
						returncode = ChkUtil.CHK_8443 ;  // 纳税类型为T时，开具减免税凭证的减免金额不能有值 
					}else if(IsNull(ChkUtil.CHK_8443) && (daeate_type != null && daeate_type.getTaxDepartmentCode() != null
							&& !"".equals(daeate_type.getTaxDepartmentCode()))){
						returncode = ChkUtil.CHK_8443 ;  // 纳税类型为T时，开具减免凭证的机关名称代码
					}else{
						returncode = ChkUtil.CHK_0000 ;
					}
					if(IsNull(ChkUtil.CHK_8444) && (paid_type != null && paid_type.getTaxDepartment() != null
							&& !"".equals(paid_type.getTaxDepartment()))){
						returncode = ChkUtil.CHK_8444 ;  // 纳税类型为T时，不可以传开具完税凭证的税务机关名称
					}else if(IsNull(ChkUtil.CHK_8444) && (paid_type != null && paid_type.getTaxDocumentNumber() != null
							&& !"".equals(paid_type.getTaxDocumentNumber()))){
						returncode = ChkUtil.CHK_8444 ;  // 纳税类型为T时，不可以传完税凭证号码
					}else if(IsNull(ChkUtil.CHK_8444) && (paid_type != null && paid_type.getTaxDepartmentCode() != null
							&& !"".equals(paid_type.getTaxDepartmentCode()))){
						returncode = ChkUtil.CHK_8444 ;  // 纳税类型为T时，不可以传开具完税凭证的税务机关代码
					}
				}else{
					returncode = ChkUtil.CHK_0000 ;
				}
			}else if("C".equals(bccci.getTaxInfo().getTaxConditionCode())){
				if(bccci.getTaxInfo().getCurrentTaxDue() != null){
					if(IsNull(ChkUtil.CHK_8327) && (daeate_type == null)){
						return ChkUtil.CHK_8327 ; //	纳税信息为减免税时，减税信息对象必填"
					}else if(IsNull(ChkUtil.CHK_8327) && (daeate_type == null)){
						returncode = ChkUtil.CHK_8327 ; // 纳税类型为C时，减免税信息不能为空
					}else if(IsNull(ChkUtil.CHK_8327) && (daeate_type != null
							&& (daeate_type.getDeductionDueCode() == null 
							|| "".equals(daeate_type.getDeductionDueCode())))){
						returncode = ChkUtil.CHK_8327 ; // 纳税类型为C时，减免税原因代码不能为空
					}else if(IsNull(ChkUtil.CHK_8327) && (daeate_type != null
							&& (daeate_type.getDeductionDueType() == null 
							|| "".equals(daeate_type.getDeductionDueType())))){
						returncode = ChkUtil.CHK_8327 ; // 纳税类型为C时，减免税方案代码不能为空
					}else if(IsNull(ChkUtil.CHK_8327) && (daeate_type != null
							&& (daeate_type.getTaxDepartment() == null 
							|| "".equals(daeate_type.getTaxDepartment())))){
						returncode = ChkUtil.CHK_8327 ; // 纳税类型为C时，开具减免税凭证的税务机关名称不能为空
					}
//					else if(IsNull(ChkUtil.CHK_8327) && (daeate_type != null && !("P".equals(daeate_type.getDeductionDueType()) || "A".equals(daeate_type.getDeductionDueType())))){
//						returncode = ChkUtil.CHK_8327 ; // 减免税方案代码校验  不正确
//					}else if(IsNull(ChkUtil.CHK_8364) && (daeate_type.getDeductionDueProportion() != rkmx.getDEDUCTIONDUEPROPORTION())){
//						returncode = ChkUtil.CHK_8364 ; // 纳税类型为C时，批改查询是不允许批改减免比例
//					}
					else if("P".equals(daeate_type.getDeductionDueType())
							&& (daeate_type.getDeductionDueProportion() <= 0
							|| daeate_type.getDeduction() != 0 
							|| daeate_type.getDeductionDueProportion() > 1)){
						returncode = ChkUtil.CHK_8471 ; // 减免方案为比例减免时，减免比例不能为0，且减免金额不能有值
					}else if("A".equals(daeate_type.getDeductionDueType())
							&& (daeate_type.getDeductionDueProportion() > 0 
							|| daeate_type.getDeduction() < 0)){
						returncode = ChkUtil.CHK_8472 ; // 减免方案为金额减免时，减免比例不能大于0，且减免金额不能小于0
					}
					
					// 2014-9-25 20:53:02 mili
					if(IsNull(ChkUtil.CHK_8332) && (paid_type != null && paid_type.getTaxDepartment() != null
							&& !"".equals(paid_type.getTaxDepartment()))){
						returncode = ChkUtil.CHK_8332 ; //	纳税类型为C时，不可以传开具完税凭证的税务机关名称
					}else if(IsNull(ChkUtil.CHK_8332) && (paid_type != null && paid_type.getTaxDocumentNumber() != null
							&& !"".equals(paid_type.getTaxDocumentNumber()))){
						returncode = ChkUtil.CHK_8332 ; //	纳税类型为C时，不可以传完税凭证号码
					}else if(IsNull(ChkUtil.CHK_8332) && (paid_type != null && paid_type.getTaxDepartmentCode() != null
							&& !"".equals(paid_type.getTaxDepartmentCode()))){
						returncode = ChkUtil.CHK_8332 ; //	纳税类型为C时，不可以传完税凭证代码
					}
				}else{
					returncode = ChkUtil.CHK_8419 ;	// 确认时，本年纳税信息不可以为空
				}
			}else if("E".equals(bccci.getTaxInfo().getTaxConditionCode())){
				if(bccci.getTaxInfo().getCurrentTaxDue() != null){
					if(IsNull(ChkUtil.CHK_8337) && (daeate_type == null)){
						returncode = ChkUtil.CHK_8337 ;	// 纳税信息为免税时，本年纳税信息对象必填
					}else if("C".equals(daeate_type.getDeductionDueCode())){ // 根据减免税方案代码来判断是何中减免类型
						if(IsNull(ChkUtil.CHK_8339) && (daeate_type.getDeductionDueCode() == null
								|| "".equals(daeate_type.getDeductionDueCode()))){
							returncode =  ChkUtil.CHK_8339 ;	// 纳税类型为E时，减免税原因代码不能为空
						}else if(IsNull(ChkUtil.CHK_8340) && (daeate_type.getDeductionDueType() == null
								|| "".equals(daeate_type.getDeductionDueType()))){
							returncode =  ChkUtil.CHK_8340 ;	// 纳税类型为E时，减免税方案代码不能为空
						}
//						else if(IsNull(ChkUtil.CHK_8341) && (daeate_type.getTaxDepartment() == null
//								|| "".equals(daeate_type.getTaxDepartment()))){
//							returncode =  ChkUtil.CHK_8341 ;	// 纳税类型为E时，开具减免税凭证的税务机关名称不能为空
//						}else if(IsNull(ChkUtil.CHK_8244) && (paid_type != null
//								&&paid_type.getTaxDepartment() != null		
//								&& !"".equals(paid_type.getTaxDepartment()))){
//							returncode =  ChkUtil.CHK_8244 ;	// 纳税类型为E时，不可以传开具完税凭证的税务机关名称
//						}
						else if(IsNull(ChkUtil.CHK_8342) && (paid_type != null
								&& paid_type.getTaxDocumentNumber() != null
								&& !"".equals(paid_type.getTaxDocumentNumber()))){
							returncode =  ChkUtil.CHK_8342 ;	// 纳税类型为E时，不可以传完税凭证号码
						}
//						else if(IsNull(ChkUtil.CHK_8246) && ("P".equals(daeate_type.getDeductionDueType())
//								&& (daeate_type.getDeduction() != 0
//										|| daeate_type.getDeductionDueProportion() > 1 
//										|| daeate_type.getDeductionDueProportion() < 0))){
//							returncode =  ChkUtil.CHK_8246 ;	// 减免方案为比例减免时，减免比例不能为0，且减免金额不能有值
//						}else if(IsNull(ChkUtil.CHK_8247) && ("A".equals(daeate_type.getDeductionDueType())
//								&& (daeate_type.getDeductionDueProportion() != 0 
//										|| daeate_type.getDeduction() < 0))){
//							returncode =  ChkUtil.CHK_8247 ;	// 减免方案为金额减免时，减免比例不能大于0，且减免金额不能小于0
//						}else if(IsNull(ChkUtil.CHK_8248) && (paid_type != null
//								&& _tax_type.getCurrentTaxDue().getPaid().getTaxDepartmentCode() != null
//								&& !"".equals(paid_type.getTaxDepartmentCode()))){
//							returncode =  ChkUtil.CHK_8248 ;	// 纳税类型为E时，不可以传开具完税凭证的税务机关代码
//						}
					}
//					else if(IsNull(ChkUtil.CHK_8241) && (daeate_type.getDeductionDueCode() == null
//							|| "".equals(daeate_type.getDeductionDueCode()))){
//						returncode =  ChkUtil.CHK_8241 ;	// 纳税信息为免税时，减免税原因不能为空
//					}else if(IsNull(ChkUtil.CHK_8242) && (daeate_type.getDeductionDueType() == null
//							|| "".equals(daeate_type.getDeductionDueType()))){
//						returncode =  ChkUtil.CHK_8242 ;	// 纳税信息为免税时，减免税方案不能为空
//					}
//					else if(IsNull(ChkUtil.CHK_8243) && (daeate_type.getTaxDepartment() == null
//							|| "".equals(daeate_type.getTaxDepartment()))){
//						returncode =  ChkUtil.CHK_8243 ;	// 纳税信息为免税时，减免税方案不能为空
//					}
					
					if(IsNull(ChkUtil.CHK_8342) && (paid_type != null && paid_type.getTaxDepartment() != null
							&& !"".equals(paid_type.getTaxDepartment()))){
						returncode = ChkUtil.CHK_8342 ;	// 纳税类型为E时，不可以传开具完税凭证的税务机关名称
					}else if(IsNull(ChkUtil.CHK_8342) && (paid_type != null && paid_type.getTaxDocumentNumber() != null
							&& !"".equals(paid_type.getTaxDocumentNumber()))){
						returncode = ChkUtil.CHK_8342 ;	// 纳税类型为E时，不可以传开具完税凭证的税务机关号码
					}else if(IsNull(ChkUtil.CHK_8342) && (paid_type != null && paid_type.getTaxDepartmentCode() != null
							&& !"".equals(paid_type.getTaxDepartmentCode()))){
						returncode = ChkUtil.CHK_8342 ;	// 纳税类型为E时，不可以传开具完税凭证的税务机关代码
					}
				}else{
					returncode = ChkUtil.CHK_8424 ;	// 确认时，本年纳税信息不可以为空
				}
			}else if("P".equals(bccci.getTaxInfo().getTaxConditionCode())){
				// 已申报的怎么这么地
				if(bccci.getTaxInfo().getCurrentTaxDue() != null){
					if(IsNull(ChkUtil.CHK_0000) && (!(rkmx.getTAXDEPARTMENT() == null || "".equals(rkmx.getTAXDEPARTMENT())
							|| rkmx.getTAXDEPARTMENTCODE() == null || "".equals(rkmx.getTAXDEPARTMENTCODE())
							|| rkmx.getTAXDOCUMENTNUMBER() == null || "".equals(rkmx.getTAXDOCUMENTNUMBER())))){
						returncode = ChkUtil.CHK_0000 ; // 续保标志true 为续保，false不为续保
					}
//					else if(IsNull(ChkUtil.CHK_8484) && (rkmx != null && !"1".equals(rkmx.getPLATFORMSTATE()))){
//						returncode = ChkUtil.CHK_8484 ; // 完税请将申报状态设为1-申报
//					}
					// 2014-9-25 20:38:52  mili 纳税类型为P时，减免信息不能有值  
					else if(IsNull(ChkUtil.CHK_8131) && (daeate_type != null && (daeate_type.getDeductionDocumentNumber() != null 
							&& !"".equals(daeate_type.getDeductionDocumentNumber())))){
						returncode = ChkUtil.CHK_8131 ;  //	纳税类型为P时，减免税凭证号不能有值
					}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type != null && daeate_type.getDeductionDueCode() != null
							&& !"".equals(daeate_type.getDeductionDueCode()))){
						returncode = ChkUtil.CHK_8131 ;	 // 纳税类型为P时，减免税原因代码不能有值
					}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type != null && daeate_type.getDeductionDueType() != null
							&& !"".equals(daeate_type.getDeductionDueType()))){
						returncode = ChkUtil.CHK_8131 ;	 // 纳税类型为P时，减免税方案代码不能有值
					}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type != null && daeate_type.getTaxDepartment() != null
							&& !"".equals(daeate_type.getTaxDepartment()))){	
						returncode = ChkUtil.CHK_8131 ;	 // 纳税类型为P时，开具减免税凭证的税务机关名称不能有值
					}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type != null && daeate_type.getDeductionDueProportion() != 0.0
							&& 0.0 != daeate_type.getDeductionDueProportion())){
						returncode = ChkUtil.CHK_8131 ;	 // 纳税类型为P时，减免比例不能有值
					}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type != null && daeate_type.getDeduction() != 0.0
							&& 0.0 == daeate_type.getDeduction())){
						returncode = ChkUtil.CHK_8131 ;	 // 纳税类型为P时，减免金额不能有值
					}
					
//					if(IsNull(ChkUtil.CHK_8333) && (paid_type == null)){
//						returncode = ChkUtil.CHK_8333 ;	// 纳税类型为P时，本年纳税信息对象不能为空
//					}else if(IsNull(ChkUtil.CHK_8334) && (paid_type != null
//							&& (paid_type.getTaxDepartment() == null 
//							|| "".equals(paid_type.getTaxDepartment())))){
//						returncode = ChkUtil.CHK_8334 ;	// 纳税类型为P时，开具完税凭证的税务机关名称不能为空
//					}else if(IsNull(ChkUtil.CHK_8335) && (paid_type != null
//							&& (paid_type.getTaxDocumentNumber() == null)
//							|| "".equals(paid_type.getTaxDocumentNumber()))){
//						returncode = ChkUtil.CHK_8335;	// 纳税类型为P时，完税凭证号码不能为空
//					}
					
				}else{
					returncode = ChkUtil.CHK_8452 ;	// 确认时，本年纳税信息不可以为空
				}
			}
			else if("R".equals(bccci.getTaxInfo().getTaxConditionCode())){
				// mili 2014-12-25 14:30:01  新增批改确认是否允许拒缴的开关 start
				if("N".equals(PG_Switch_R)){
					returncode =  ChkUtil.CHK_8490 ;	// 批改确认时不允许拒缴
				}
				// mili 2014-12-25 14:30:01  新增批改确认是否允许拒缴的开关 end

//				if(IsNull(ChkUtil.CHK_8483) && (!(rkmx.getPLATFORMSTATE() == null || "".equals(rkmx.getPLATFORMSTATE())))){
//					returncode = ChkUtil.CHK_8483 ; // 拒缴时申报状态必须为空 
//				}else if(IsNull(ChkUtil.CHK_8357) && (daeate_type != null && (daeate_type.getDeductionDocumentNumber() != null 
//						&& !"".equals(daeate_type.getDeductionDocumentNumber())))){
//					returncode = ChkUtil.CHK_8357 ;  // 纳税类型为R时，减免税凭证号不能有值
//				}else if(IsNull(ChkUtil.CHK_8358) && (daeate_type != null && daeate_type.getDeductionDueCode() != null
//						&& !"".equals(daeate_type.getDeductionDueCode()))){
//					returncode = ChkUtil.CHK_8358 ;  // 纳税类型为R时， 减免税原因代码不能有值
//				}else if(IsNull(ChkUtil.CHK_8359) && (daeate_type != null && daeate_type.getDeductionDueType() != null
//						&& "".equals(daeate_type.getDeductionDueType()))){
//					returncode = ChkUtil.CHK_8359 ;  // 纳税类型为R时，减免税方案代码不能有值
//				}else if(IsNull(ChkUtil.CHK_8360) && (daeate_type != null && daeate_type.getTaxDepartment() != null
//						&& !"".equals(daeate_type.getTaxDepartment()))){
//					returncode = ChkUtil.CHK_8360 ;  // 纳税类型为R时，开具减免税凭证的税务机关名称不能有值
//				}else if(IsNull(ChkUtil.CHK_8361) && (daeate_type != null && daeate_type.getDeductionDueProportion() != 0.0
//						&& 0.0 != daeate_type.getDeductionDueProportion())){
//					returncode = ChkUtil.CHK_8361 ;  // 纳税类型为R时，开具减免税凭证的减免比例不能有值
//				}else if(IsNull(ChkUtil.CHK_8439) && (daeate_type != null && daeate_type.getDeduction() != 0.0
//						&& 0.0 != daeate_type.getDeduction())){
//					returncode = ChkUtil.CHK_8439 ;  // 纳税类型为R时，开具减免税凭证的减免金额不能有值
//				}else{
//					returncode = ChkUtil.CHK_0000 ;
//				}
				
//				if(IsNull(ChkUtil.CHK_8363) && (paid_type != null && paid_type.getTaxDepartment() != null
//						&& !"".equals(paid_type.getTaxDepartment()))){
//					returncode = ChkUtil.CHK_8363 ;  // 纳税类型为R时，不可以传开具完税凭证的税务机关名称
//				}else if(IsNull(ChkUtil.CHK_8362) && (paid_type != null && paid_type.getTaxDocumentNumber() != null
//						&& !"".equals(paid_type.getTaxDocumentNumber()))){
//					returncode = ChkUtil.CHK_8362 ;  // 纳税类型为R时，不可以传完税凭证号码
//				}else if(IsNull(ChkUtil.CHK_8364) && (paid_type != null && paid_type.getTaxDepartmentCode() != null
//						&& !"".equals(paid_type.getTaxDepartmentCode()))){
//					returncode = ChkUtil.CHK_8364 ;  // 纳税类型为R时，不可以传开具完税凭证的税务机关代码
//				}
			}
//			else{
//				returncode = ChkUtil.CHK_8443 ; // 无法识别的纳税类型
//			}
		}else {
			returncode = ChkUtil.CHK_0000 ;
		}
		return returncode;
	}
	/**
	 * 用户名密码验证
	 * */
	public String N_P_checking(Object o) {
		String returncode = ChkUtil.CHK_8008;
		BaseChangeConfirmReqInfo bqci = (BaseChangeConfirmReqInfo) o ;
		String Pwssword_f = bqci.getPassword();
		String Pwssword_l = CfgLoader.getConfigValue("SysParam", "Password");
		String Username_f = bqci.getUserName();
		String Username_l = CfgLoader.getConfigValue("SysParam", "Username");
		// 用户名密码 是  null 默认为验证失败
		if(Username_f == null || Pwssword_f == null){
			returncode = ChkUtil.CHK_8008 ;
		}else if(Username_f.equals(Username_l) && Pwssword_f.equals(Pwssword_l)){
			returncode = ChkUtil.CHK_0000 ;
		}else{
			returncode = ChkUtil.CHK_8008 ;
		}
		return returncode ;
	}
	/**
	 * @author MILI
	 * @time 2014-6-10 17:17:19
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
