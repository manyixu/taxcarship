package com.derun.common.chk;

import com.derun.beans.BaseConfirmReqInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;

/**
 * @author MILI
 * @time 2014-3-11 10:14:59
 * @描述：投保确认用户名密码和入参有效性验证
 * */
public class BaseConfirmChk {
	public String Join_valid(Object o,Object oo) {
		String returncode = ChkUtil.CHK_0000 ;
		BaseConfirmReqInfo bcci = (BaseConfirmReqInfo)o ;
		Tax_Type _tax_type = bcci.getTaxInfo();	 //	车船税数据类型 
		Derate_Type daeate_type = _tax_type.getCurrentTaxDue().getDerate(); // 减免对象
		Paid_Type paid_type = _tax_type.getCurrentTaxDue().getPaid();	// 完税对象
		String TB_Switch_R = CfgLoader.getConfigValue("SysParam", "TB_Switch_R"); // 投保确认时是否允许拒缴
		if(IsNull(ChkUtil.CHK_8140) && (null == bcci.getCompanyCode() || "".equals(bcci.getCompanyCode()) || bcci.getCompanyCode().length() != 4)){
 			returncode = ChkUtil.CHK_8140 ; // 验证公司代码是否为空
		}
//		else if(IsNull(ChkUtil.CHK_8139) && (null == _tax_type.getCurrentTaxDue().getTaxLocationCode() || "".equals(_tax_type.getCurrentTaxDue().getTaxLocationCode()))){
//			returncode =  ChkUtil.CHK_8139 ; // 验证地区代码否为空
//		}else if(IsNull(ChkUtil.CHK_8139) && (null == bcci.getAreaCode() || "".equals(bcci.getAreaCode()))){
//			returncode =  ChkUtil.CHK_8139 ;	//验证国标区域代码是否为空
//		}
//		else if(IsNull(ChkUtil.CHK_8269) && (!"2".equals(_tax_type.getTaxAmount().getTaxAmount_Flag()))){
//			returncode =  ChkUtil.CHK_8269 ;	// 车船税确认时，车船税合计金额标志应为  ：2--确认实收
//		}
//		else if("T".equals(_tax_type.getTaxConditionCode())){
//			if(daeate_type != null){
//				if(IsNull(ChkUtil.CHK_8325) && (daeate_type.getDeductionDocumentNumber() != null
//						&& !"".equals(daeate_type.getDeductionDocumentNumber()))){
//					returncode =  ChkUtil.CHK_8325 ;	// 纳税类型为T时，减免税凭证号不能有值
//				}else if(IsNull(ChkUtil.CHK_8325) && (daeate_type.getDeductionDueCode() != null
//						&& !"".equals(daeate_type.getDeductionDueCode()))){
//					returncode =  ChkUtil.CHK_8325 ;	// 纳税类型为T时，减免税原因代码不能有值
//				}else if(IsNull(ChkUtil.CHK_8325) && (daeate_type.getDeductionDueType() != null
//						&& !"".equals(daeate_type.getDeductionDueType()))){
//					returncode =  ChkUtil.CHK_8325 ;	// 纳税类型为T时，减免税方案代码不能有值
//				}else if(IsNull(ChkUtil.CHK_8325) && (daeate_type.getTaxDepartment() != null
//						&& !"".equals(daeate_type.getTaxDepartment()))){
//					returncode =  ChkUtil.CHK_8325;	// 纳税类型为T时，开具减免税凭证的税务机关名称不能有值
//				}else if(IsNull(ChkUtil.CHK_8325) && (daeate_type.getDeduction() != 0)){
//					returncode =  ChkUtil.CHK_8325 ;	// 纳税类型为T时，减免金额不能有值
//				}else if(IsNull(ChkUtil.CHK_8325) && (daeate_type.getDeductionDueProportion() != 0)){
//					returncode =  ChkUtil.CHK_8325 ;	// 纳税类型为T时，减免比例不能有值
//				}else if(IsNull(ChkUtil.CHK_8325) && (daeate_type.getTaxDepartmentCode() != null
//						&& !"".equals(daeate_type.getTaxDepartmentCode()))){
//					returncode =  ChkUtil.CHK_8325 ;	// 纳税类型为T时，税务机关代码不能有值
//				}
//			}
//			if(paid_type != null){ 
//				if(IsNull(ChkUtil.CHK_8326) && (paid_type.getTaxDepartment() != null
//						&& !"".equals(paid_type.getTaxDepartment()))){
//					returncode =  ChkUtil.CHK_8326 ;	// 纳税类型为T时，不可以传开具完税凭证的税务机关名称
//				}else if(IsNull(ChkUtil.CHK_8326) && (paid_type.getTaxDocumentNumber() != null
//						&& !"".equals(paid_type.getTaxDocumentNumber()))){
//					returncode =  ChkUtil.CHK_8326 ;	// 纳税类型为T时，不可以传完税凭证号码
//				}else if(IsNull(ChkUtil.CHK_8326) && (paid_type.getTaxDepartmentCode() != null
//						&& !"".equals(paid_type.getTaxDepartmentCode()))){
//					returncode =  ChkUtil.CHK_8326 ;	// 纳税类型为T时，不可以传开具完税凭证的税务机关代码
//				}
//			}
//		}
//		else if("C".equals(_tax_type.getTaxConditionCode())){
//			if(_tax_type.getCurrentTaxDue() != null){
//				if(IsNull(ChkUtil.CHK_8327) && (daeate_type == null)){
//					returncode =  ChkUtil.CHK_8327 ;	// 纳税类型为C时，减免税信息不能为空
//				}else if(IsNull(ChkUtil.CHK_8122) && (daeate_type.getDeductionDueCode() == null
//						|| "".equals(daeate_type.getDeductionDueCode()))){
//					returncode =  ChkUtil.CHK_8122 ;	// 纳税类型为C时，减免税原因代码不能为空
//				}else if(IsNull(ChkUtil.CHK_8123) && (daeate_type.getDeductionDueType() == null
//						|| "".equals(daeate_type.getDeductionDueType()))){
//					returncode =  ChkUtil.CHK_8123 ;	// 纳税类型为C时，减免税方案代码不能为空
//				}else if(IsNull(ChkUtil.CHK_8124) && (daeate_type.getTaxDepartment() == null
//						|| "".equals(daeate_type.getTaxDepartment()))){
//					returncode =  ChkUtil.CHK_8124 ;	// 纳税类型为C时，开具减免税凭证的税务机关名称不能为空
//				}else if(IsNull(ChkUtil.CHK_8471) && ("P".equals(daeate_type.getDeductionDueType()))){	// 减免方案为比例减免时，减免比例不能为0并且在[0,1)之间，且减免金额不能有值
//					if(daeate_type.getDeductionDueProportion() <= 0.0
//							|| daeate_type.getDeductionDueProportion() > 1){	
//						returncode = ChkUtil.CHK_8471 ; 	// 减免比例不能为0并且在[0,1)之间  	
//					}
////					if(IsNull(ChkUtil.CHK_8138) && (daeate_type.getDeduction() != 0.0 
////							&& !"".equals(daeate_type.getDeduction()))){			
////						returncode = ChkUtil.CHK_8138 ;		// 减免金额不能有值
////					}
//				}
////				else if("P".equals(daeate_type.getDeductionDueType())
////						&& (daeate_type.getDeduction() > 0 
////								|| (daeate_type.getDeductionDueProportion() > 1 
////								|| daeate_type.getDeductionDueProportion() < 0))){
////					returncode =  ChkUtil.CHK_8218 ;	// 减免方案为比例减免时，减免比例不能为0，且减免金额不能有值
////				}
//				else if(IsNull(ChkUtil.CHK_8472) && ("A".equals(daeate_type.getDeductionDueType())
//						&& (daeate_type.getDeductionDueProportion() > 0 
//							|| _tax_type.getCurrentTaxDue()	.getDerate().getDeduction() < 0))){
//					returncode =  ChkUtil.CHK_8472 ;	// 减免方案为金额减免时，减免比例不能大于0，且减免金额不能为0
//				}
//			}
//			if((paid_type != null && paid_type.getTaxDepartment() != null
//					&& !"".equals(paid_type.getTaxDepartment()))){
//				returncode =  ChkUtil.CHK_8332 ;	// 纳税类型为C时，不可以传开具完税凭证的税务机关名称
//			}else if((paid_type != null	&& paid_type.getTaxDocumentNumber() != null 
//					&& !"".equals(paid_type.getTaxDocumentNumber()))){
//				returncode =  ChkUtil.CHK_8332 ;	// 纳税类型为C时，不可以传完税凭证号码
//			}else if((paid_type != null	
//					&& paid_type.getTaxDepartmentCode() != null
//					&& !"".equals(paid_type.getTaxDepartmentCode()))){
//				returncode =  ChkUtil.CHK_8332 ;	// 纳税类型为C时，不可以传开具完税凭证的税务机关代码
//			}
//		}
//		else if("P".equals(_tax_type.getTaxConditionCode())){
//			if(daeate_type != null){
//				if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getDeductionDocumentNumber() != null
//						&& !"".equals(daeate_type.getDeductionDocumentNumber()))){
//					returncode =  ChkUtil.CHK_8131 ;	// 纳税类型为P时，减免税凭证号不能有值
//				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getDeductionDueCode() != null
//						&& !"".equals(daeate_type.getDeductionDueCode()))){
//					returncode =  ChkUtil.CHK_8131 ;	// 纳税类型为P时，减免税原因代码不能有值
//				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getDeductionDueType() != null
//						&& !"".equals(daeate_type.getDeductionDueType()))){
//					returncode =  ChkUtil.CHK_8131 ;	// 纳税类型为P时，减免税方案代码不能有值
//				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getTaxDepartment() != null
//						&& "".equals(daeate_type.getTaxDepartment()))){
//					returncode =  ChkUtil.CHK_8131 ;	// 纳税类型为P时，开具减免税凭证的税务机关名称不能有值
//				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getDeduction() != 0)){
//					returncode =  ChkUtil.CHK_8131 ;	// 纳税类型为P时，减免金额不能有值
//				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getDeductionDueProportion() != 0)){
//					returncode =  ChkUtil.CHK_8131 ;	// 纳税类型为P时， 减免比例不能有值
//				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getTaxDepartmentCode() != null
//						&& !"".equals(daeate_type.getTaxDepartmentCode()))){
//					returncode =  ChkUtil.CHK_8131 ;	// 纳税类型为P时，税务机关代码不能有值
//				}
//			}
//		}else if("E".equals(bcci.getTaxInfo().getTaxConditionCode())){
//			if(_tax_type.getCurrentTaxDue() != null){
//				if(IsNull(ChkUtil.CHK_8337) && (daeate_type == null)){
//					returncode = ChkUtil.CHK_8337 ;	// 纳税类型为E时，本年纳税信息对象不能为空
//				}else if("C".equals(daeate_type.getDeductionDueCode())){ // 根据减免税方案代码来判断是何中减免类型
//					if(IsNull(ChkUtil.CHK_8339) && (daeate_type.getDeductionDueCode() == null
//							|| "".equals(daeate_type.getDeductionDueCode()))){
//						returncode =  ChkUtil.CHK_8339 ;	// 纳税类型为E时，减免税原因代码不能为空
//					}else if(IsNull(ChkUtil.CHK_8340) && (daeate_type.getDeductionDueType() == null
//							|| "".equals(daeate_type.getDeductionDueType()))){
//						returncode =  ChkUtil.CHK_8340 ;	// 纳税类型为E时，减免税方案代码不能为空
//					}else if(IsNull(ChkUtil.CHK_8341) && (daeate_type.getTaxDepartment() == null
//							|| "".equals(daeate_type.getTaxDepartment()))){
//						returncode =  ChkUtil.CHK_8341 ;	// 纳税类型为E时，开具减免税凭证的税务机关名称不能为空
//					}else if(IsNull(ChkUtil.CHK_8342) && (paid_type != null
//							&&paid_type.getTaxDepartment() != null		
//							&& !"".equals(paid_type.getTaxDepartment()))){
//						returncode =  ChkUtil.CHK_8342 ;	// 纳税类型为E时，不可以传开具完税凭证的税务机关名称
//					}else if(IsNull(ChkUtil.CHK_8342) && (paid_type != null
//							&& paid_type.getTaxDocumentNumber() != null
//							&& !"".equals(paid_type.getTaxDocumentNumber()))){
//						returncode =  ChkUtil.CHK_8342 ;	// 纳税类型为E时，不可以传完税凭证号码
//					}
////					else if(IsNull(ChkUtil.CHK_8246) && ("P".equals(daeate_type.getDeductionDueType())
////							&& (daeate_type.getDeduction() != 0
////									|| daeate_type.getDeductionDueProportion() > 1 
////									|| daeate_type.getDeductionDueProportion() < 0))){
////						returncode =  ChkUtil.CHK_8246 ;	// 减免方案为比例减免时，减免比例不能为0，且减免金额不能有值
////					}else if(IsNull(ChkUtil.CHK_8247) && ("A".equals(daeate_type.getDeductionDueType())
////							&& (daeate_type.getDeductionDueProportion() != 0 
////									|| daeate_type.getDeduction() < 0))){
////						returncode =  ChkUtil.CHK_8247 ;	// 减免方案为金额减免时，减免比例不能大于0，且减免金额不能小于0
////					}else if(IsNull(ChkUtil.CHK_8248) && (paid_type != null
////							&& _tax_type.getCurrentTaxDue()	.getPaid().getTaxDepartmentCode() != null
////							&& !"".equals(paid_type.getTaxDepartmentCode()))){
////						returncode =  ChkUtil.CHK_8248 ;	// 纳税类型为E时，不可以传开具完税凭证的税务机关代码
////					}
//				}else if(IsNull(ChkUtil.CHK_8339) && (daeate_type.getDeductionDueCode() == null
//						|| "".equals(daeate_type.getDeductionDueCode()))){
//					returncode =  ChkUtil.CHK_8339 ;	// 纳税信息为减免税时，减免税原因不能为空
//				}else if(IsNull(ChkUtil.CHK_8340) && (daeate_type.getDeductionDueType() == null
//						|| "".equals(daeate_type.getDeductionDueType()))){
//					returncode =  ChkUtil.CHK_8340 ;	// 纳税信息为免税时，减免税方案不能为空
//				}
//				if(IsNull(ChkUtil.CHK_8342) && (paid_type != null
//						&& paid_type.getTaxDepartment() != null
//						&& !"".equals(paid_type.getTaxDepartment()))){
//					returncode =  ChkUtil.CHK_8342 ;	// 纳税类型为E时，不可以传开具完税凭证的税务机关名称
//				}else if(IsNull(ChkUtil.CHK_8342) && (paid_type != null
//						&& paid_type.getTaxDocumentNumber() != null
//						&& !"".equals(paid_type.getTaxDocumentNumber()))){
//					returncode =  ChkUtil.CHK_8342 ;	// 纳税类型为E时，不可以传完税凭证号码
//				}else if(IsNull(ChkUtil.CHK_8342) && (paid_type != null
//						&& paid_type.getTaxDepartmentCode() != null
//						&& !"".equals(paid_type.getTaxDepartmentCode()))){
//					returncode =  ChkUtil.CHK_8342 ;	// 纳税类型为E时，不可以传开具完税凭证的税务机关代码
//				}
//			}
//		}else 
		if("R".equals(bcci.getTaxInfo().getTaxConditionCode())){
			
			if(bcci.getTaxInfo().getTaxAmount().getAnnualTaxDue() > 0){
				returncode =  ChkUtil.CHK_8243 ;	//	当确认拒缴时，本年车船税金额不能大于0
			}else if(bcci.getTaxInfo().getTaxAmount().getSumOverdue() > 0){
				returncode =  ChkUtil.CHK_8244 ;	//	当确认拒缴时，合计滞纳金不能大于0
			}else if(bcci.getTaxInfo().getTaxAmount().getSumTax() > 0){
				returncode =  ChkUtil.CHK_8245 ;	//	当确认拒缴时，合计金额不能大于0
			}
			// mili 2014-12-24 16:43:55 新增投保确认是否允许拒缴的开关 start
			else if("N".equals(TB_Switch_R)){
				returncode =  ChkUtil.CHK_8246 ;	// 投保确认时不允许拒缴
			}
			// mili 2014-12-24 16:43:55 新增投保确认是否允许拒缴的开关 end
			
//			if(_tax_type.getCurrentTaxDue() != null){
//				if(IsNull(ChkUtil.CHK_8357) && (daeate_type != null
//						&& (daeate_type.getDeductionDocumentNumber() != null 
//						&& !"".equals(daeate_type.getDeductionDocumentNumber())))){
//					returncode =  ChkUtil.CHK_8357 ;	// 纳税类型为R时，减免税凭证号不能有值
//				}else if(IsNull(ChkUtil.CHK_8358) && (daeate_type != null
//						&& daeate_type.getDeductionDueCode() != null
//						&& !"".equals(daeate_type.getDeductionDueCode()))){
//					returncode =  ChkUtil.CHK_8358 ;	// 纳税类型为R时，减免税原因代码不能有值
//				}else if(IsNull(ChkUtil.CHK_8359) && (daeate_type != null
//						&& daeate_type.getDeductionDueType() != null
//						&& !"".equals(daeate_type.getDeductionDueType()))){
//					returncode =  ChkUtil.CHK_8359 ;	// 纳税类型为R时，减免税方案代码不能有值
//				}else if(IsNull(ChkUtil.CHK_8360) && (daeate_type != null
//						&& daeate_type.getTaxDepartment() != null
//						&& !"".equals(daeate_type.getTaxDepartment()))){
//					returncode =  ChkUtil.CHK_8360 ;	// 纳税类型为R时，开具减免税凭证的税务机关名称不能有值
//				}else if(IsNull(ChkUtil.CHK_8363) && (paid_type != null
//						&& paid_type.getTaxDepartment() != null
//						&& !"".equals(paid_type.getTaxDepartment()))){
//					returncode =  ChkUtil.CHK_8363 ;	// 纳税类型为R时，不可以传开具完税凭证的税务机关名称
//				}else if(IsNull(ChkUtil.CHK_8362) && (paid_type != null
//						&& paid_type.getTaxDocumentNumber() != null
//						&& !"".equals(paid_type.getTaxDocumentNumber()))){
//					returncode =  ChkUtil.CHK_8362 ;	// 纳税类型为R时，不可以传完税凭证号码
//				}else if(IsNull(ChkUtil.CHK_8364) && (paid_type != null
//						&& paid_type.getTaxDepartmentCode() != null
//						&& !"".equals(paid_type.getTaxDepartmentCode()))){
//					returncode =  ChkUtil.CHK_8364 ;	// 纳税类型为R时，不可以传开具完税凭证的税务机关代码
//				}else if(IsNull(ChkUtil.CHK_8358) && (daeate_type != null
//						&& (daeate_type.getDeduction() > 0))){
//					returncode =  ChkUtil.CHK_8358 ;	// 纳税类型为R时，减免金额不能有值
//				}else if(IsNull(ChkUtil.CHK_8358) && (daeate_type != null
//						&& (daeate_type.getDeductionDueProportion() > 0))){
//					returncode =  ChkUtil.CHK_8358 ;	// 纳税类型为R时，减免比例不能有值
//				}
////				else if(IsNull(ChkUtil.CHK_8263) && (!("".equals(_tax_type.getPayDate()) || null == _tax_type.getPayDate()))){
////					returncode =  ChkUtil.CHK_8263 ; // 确认时选择拒缴不能填写申报状态字段
////				}
//			}
		}
//		else{
//			returncode =  ChkUtil.CHK_8414 ; // 确认纳税类型不正确
//		}
		
// 		if("P".equals(basequeryreqinfo.getTaxInfo().getTaxConditionCode())){
// 			if(!"1".equals(basequeryreqinfo.getTaxInfo().getPayDate())){
// 				returncode = "CHK_8264" ; // 确认纳税类型为完税时，申报状态字段必须送“1”
// 			}
// 		}
// 		if(!("R".equals(basequeryreqinfo.getTaxInfo().getTaxConditionCode()) || "P".equals(basequeryreqinfo.getTaxInfo().getTaxConditionCode()))){
// 			if(!"0".equals(basequeryreqinfo.getTaxInfo().getPayDate())){
// 				returncode = "CHK_8265" ; // 确认纳税类型不是拒缴和完税时，申报状态字段必须送“0”
// 			}
// 		}
		return returncode;
	}

	/**
	 * 用户名密码验证
	 * */
	public String N_P_checking(Object o) {
		String returncode = ChkUtil.CHK_8008;
		BaseConfirmReqInfo bqci = (BaseConfirmReqInfo) o ;
		String Pwssword_f = bqci.getPassword();
		String Pwssword_l = CfgLoader.getConfigValue("SysParam", "Password");
		String Username_f = bqci.getUserName();
		String Username_l = CfgLoader.getConfigValue("SysParam", "Username");
		// 用户名密码 是  null 默认为验证失败
		if(Username_f == null || Pwssword_f == null || "".equals(Username_f) || "".equals(Pwssword_f)){
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
