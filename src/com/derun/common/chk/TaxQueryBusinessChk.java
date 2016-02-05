package com.derun.common.chk;
import java.util.List;
import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.DateUtil;
import com.derun.common.util.ShuiKuanType;
import com.derun.model.po.SYJK_CCS_CODE;
/**
 * @author MILI
 * @time 2014-3-7 9:10:44
 * @描述：投保查询用户名密码和入参有效性验证
 * */
public class TaxQueryBusinessChk {
	/**
	 * 入参有效性验证
	 * */
	public String Join_valid(Object o,Object oo) {
		String returncode = ChkUtil.CHK_0000 ;
		BaseQueryReqInfo bqci = (BaseQueryReqInfo) o ;
		
		Derate_Type daeate_type = null ;
		Paid_Type paid_type = null ;
		if(bqci.getTaxInfo().getCurrentTaxDue() != null && !"".equals(bqci.getTaxInfo().getCurrentTaxDue())){
			daeate_type = bqci.getTaxInfo().getCurrentTaxDue().getDerate() ; 	// 减免对象
			paid_type = bqci.getTaxInfo().getCurrentTaxDue().getPaid();			// 完税对象
		}
		Vehicle_Type vehicle = bqci.getVehicleInfo() ;					// 车辆信息数据类型 
		double WholeWeight = vehicle.getWholeWeight();
		int W_Number = Integer.parseInt(CfgLoader.getConfigValue("SysParam", "Kerb_mass")) ;	// 系统配置整备质量
//		String firstTime = vehicle.getFirstRegisterDate();				// 车辆初始登记日期
//		if (IsNull(ChkUtil.CHK_8321) && (firstTime == null || "".equals(firstTime) || firstTime.length() < 10)){
//			return ChkUtil.CHK_8321 ;				// 初登日期格式不正确
//		}
//		String mytime = DateUtil.getDateYYYY();		// 系统当前年Date
//		int xtrq = Integer.parseInt(mytime) ;		// 系统当前年Intger
//		int djrq = Integer.parseInt(firstTime.substring(0 ,4));		// 车辆初始登记日期  年
		List<SYJK_CCS_CODE> ccs_code = CfgLoader.getConfig("SysCode_SM"); // 税目表
		// 匹配交强险 车辆类型
		String jqtype = ShuiKuanType.JiaoQiangType(bqci.getVehicleInfo().getMotorTypeCode());
		// 税目匹配
		boolean flag = ShuiKuanType.ShuiMu_type(ccs_code,jqtype);
		if(!flag){
			returncode = ChkUtil.CHK_8111 ;		// 不存在对应税目
		}else if(IsNull(ChkUtil.CHK_8114) && (ShuiKuanType.MotorD_Matching(bqci.getVehicleInfo().getMotorTypeCode()) && bqci.getVehicleInfo().getWholeWeight() == 0.0)){
			returncode = ChkUtil.CHK_8114 ;		// 按吨位算税的车辆，整备质量必须大于0
		}else if(IsNull(ChkUtil.CHK_8115) && (!ShuiKuanType.MotorT_Matching(bqci.getVehicleInfo().getSpecialCarType()))){
			returncode = ChkUtil.CHK_8115 ;		// 特殊车标志只能是1、2、3、4、5或空
		}else if(IsNull(ChkUtil.CHK_8140) && (null == bqci.getCompanyCode() || "".equals(bqci.getCompanyCode()))){
 			returncode = ChkUtil.CHK_8140 ; // 验证公司代码是否为空
		}else if(IsNull(ChkUtil.CHK_8479) && (bqci.getTaxInfo().getTaxConditionCode() == null || "".equals(bqci.getTaxInfo().getTaxConditionCode()) || !ShuiKuanType.getTaxConditionCode(bqci.getTaxInfo().getTaxConditionCode()))){
			returncode = ChkUtil.CHK_8479 ; // 纳税类型为空或填写不正确
//		}else if(IsNull(ChkUtil.CHK_8113) && (("11".equals(vehicle.getMotorTypeCode()) || "12".equals(vehicle.getMotorTypeCode())) && vehicle.getDisplacement() == 0 && !"E".equals(bqci.getTaxInfo().getTaxConditionCode()))){
		}else if(IsNull(ChkUtil.CHK_8113) && (ShuiKuanType.MotorP_Matching(vehicle.getMotorTypeCode()) && vehicle.getDisplacement() == 0 && !"E".equals(bqci.getTaxInfo().getTaxConditionCode()))){
			returncode = ChkUtil.CHK_8113 ; // 按排量计算的车辆，排量值必须大于0，否则返回校验
		}else if(bqci.getVehicleInfo().getVIN() == null || "".equals(bqci.getVehicleInfo().getVIN())){
			returncode = ChkUtil.CHK_8112 ; // 车架号不可为空
		}
		// 整备质量 效验 mili 2014-12-11 15:17:20 start
		else if(IsNull(ChkUtil.CHK_8150) && ((int)WholeWeight) > W_Number){
			returncode = ChkUtil.CHK_8150 ; // 整备质量超出规定范围
		}
		// mili 2014-12-11 15:17:03 end
//		else if(IsNull(ChkUtil.CHK_8322) && (vehicle.getTonnage() == 0.0 || "".equals(vehicle.getTonnage()))){
//			returncode = ChkUtil.CHK_8322 ; // 核定载质量字段代码不能为空
//		}
//		else if(IsNull(ChkUtil.CHK_8322) && (!("11".equals(vehicle.getMotorTypeCode()) || "12".equals(vehicle.getMotorTypeCode())) && (vehicle.getWholeWeight() == 0.0 || "".equals(vehicle.getWholeWeight())))){
//			returncode = ChkUtil.CHK_8322 ; // 整备质量字段不能为空
//		}
//		else if(IsNull(ChkUtil.CHK_8101) && (jqtype == null || "".equals(jqtype))){
//			returncode = ChkUtil.CHK_8101 ;		// 交强险车辆类型为空
//		}else if(IsNull(ChkUtil.CHK_8112) && (daeate_type != null && ShuiKuanType.getSysCode_DueCode(daeate_type.getDeductionDueCode()))){
//			returncode = ChkUtil.CHK_8112 ; // 减免税原因代码校验   不正确
//		}else if(IsNull(ChkUtil.CHK_8113) && (daeate_type != null && ShuiKuanType.getSysCode_DueType(daeate_type.getDeductionDueType()))){
//			returncode = ChkUtil.CHK_8113 ; // 减免税方案代码校验  不正确
//		}else if(IsNull(ChkUtil.CHK_8323) && (vehicle.getEngineNo() == null || "".equals(vehicle.getEngineNo()))){
//			returncode = ChkUtil.CHK_8323 ; // 发动机号不能为空
//		}else if(bqci.getVehicleInfo().getMotorUsageTypeCode() == null || "".equals(bqci.getVehicleInfo().getMotorUsageTypeCode())){
//			returncode = ChkUtil.CHK_8106 ; // 车辆使用性质不能为空
//		}else if(!ShuiKuanType.getMotorType(bqci.getVehicleInfo().getMotorUsageTypeCode())){
//			returncode = ChkUtil.CHK_8107 ; // 车辆使用性质代码不能超出文档中“车辆使用性质代码”之外的数据，否则提示车辆使用性质的值无效
//		}else if(IsNull(ChkUtil.CHK_8179) && (!ShuiKuanType.getVehicleType(bqci.getVehicleInfo().getVehicleType()))){
//			returncode = ChkUtil.CHK_8179 ; // 交管车辆类型字段代码可以为空，同时不能超出文档中“交管车辆类型”之外的数据
//		}else if(IsNull(ChkUtil.CHK_8110) && (djrq == xtrq && ( vehicle.getVIN() == null || "".equals(vehicle.getVIN()) || vehicle.getVIN().length() != 17))){
//			returncode = ChkUtil.CHK_8110 ; // 新车VIN码字段必须为17位，否则返回新车必须传送17位VIN码的错误信息
//		}else if(IsNull(ChkUtil.CHK_8111) && (djrq != xtrq && vehicle.getVIN().length() > 31)){
//			returncode = ChkUtil.CHK_8111 ; // 旧车VIN字段不固定位数，但不能超过31位
//		}else if(IsNull(ChkUtil.CHK_8153) && (vehicle.getPower() == 0.0 || "".equals(vehicle.getPower()))){
//			returncode = ChkUtil.CHK_8153 ; // 车辆功率代码不能为空
//		}else if(IsNull(ChkUtil.CHK_8154) && (vehicle.getRatedPassengerCapacity() == 0.0 || "".equals(vehicle.getRatedPassengerCapacity()))){
//			returncode = ChkUtil.CHK_8154 ; // 核定载客人数不能为空
//		}else if(IsNull(ChkUtil.CHK_8155) && (vehicle.getRatedPassengerCapacity() < 0 || vehicle.getRatedPassengerCapacity() > 999)){
//			returncode = ChkUtil.CHK_8155 ; // 核定载客人数字段代码且必须在0到999之间，否则返回值不在有效范围内
//		}else if(IsNull(ChkUtil.CHK_8888) && RegEx.int_type(String.valueOf(vehicle.getTonnage()))){
//			returncode = ChkUtil.CHK_8888 ;	// 验证数字类型
//		}else if(IsNull(ChkUtil.CHK_8158) && (vehicle.getNoLicenseFlag() == null || "".equals(vehicle.getNoLicenseFlag())
//				|| !ShuiKuanType.getNoLicense(bqci.getVehicleInfo().getNoLicenseFlag()))){
//			returncode = ChkUtil.CHK_8158 ; // 未上牌车辆代码字段为空或为文档中“未上牌车辆代码”之外的数据，返回错误提示信息
//		}else if(IsNull(ChkUtil.CHK_8159) && ("1".equals(vehicle.getNoLicenseFlag()) && ((vehicle.getLicensePlateNo() != null 
//				|| "".equals(vehicle.getLicensePlateNo())) || (vehicle.getLicensePlateType() != null 
//				|| "".equals(vehicle.getLicensePlateType()))))){
//			returncode = ChkUtil.CHK_8159 ; // 未上牌车辆代码字段为“1”时，传送车牌种类和车牌号码
//		}else if(IsNull(ChkUtil.CHK_8160) && ((vehicle.getLicensePlateNo() == null || "".equals(vehicle.getLicensePlateNo()))
//				&& "0".equals(vehicle.getNoLicenseFlag()))){
//			returncode = ChkUtil.CHK_8160 ; // 车牌号码为空时，未上牌车辆为0时
//		}else if(IsNull(ChkUtil.CHK_8161) && ((vehicle.getLicensePlateType() == null || "".equals(vehicle.getLicensePlateType()))
//				&& "0".equals(vehicle.getNoLicenseFlag()))){
//			returncode = ChkUtil.CHK_8161 ; // 车牌种类为空时，未上牌车辆为0时
//		}else if(IsNull(ChkUtil.CHK_8123) && (bqci.getVehicleInfo().getEngineNo() == null || "".equals(bqci.getVehicleInfo().getEngineNo()))){
//			returncode = ChkUtil.CHK_8123 ; // 发动机号不可为空 
//		}else if(IsNull(ChkUtil.CHK_8124) && (bqci.getVehicleInfo().getVIN() == null || "".equals(bqci.getVehicleInfo().getVIN()))){
//			returncode = ChkUtil.CHK_8124 ; // 车架号不可为空 
//		}else if(IsNull(ChkUtil.CHK_8128) && (bqci.getVehicleInfo().getFirstRegisterDate() == null || "".equals(bqci.getVehicleInfo().getFirstRegisterDate()) || !DateUtil.checkDate(bqci.getVehicleInfo().getFirstRegisterDate()))){
//			returncode = ChkUtil.CHK_8128;	 //初登记日期格式不正确	
//		}
//		else if(IsNull(ChkUtil.CHK_8355) && (null == bqci.getTaxInfo().getCurrentTaxDue() || (null == bqci.getTaxInfo().getCurrentTaxDue().getTaxLocationCode() || "".equals(bqci.getTaxInfo().getCurrentTaxDue().getTaxLocationCode())))){
//			returncode = ChkUtil.CHK_8355 ; // 验证地区代码否为空
//		}
//		else if(IsNull(ChkUtil.CHK_8139) && (null == bqci.getAreaCode() || "".equals(bqci.getAreaCode()))){
//			returncode = ChkUtil.CHK_8139 ;	// 验证国标地区代码是否为空
//		}
		// mili 2015-1-29 15:36:42  start 投保查询初登日期大于当前日期应返回相应校验 	ZLL
		else if(IsNull(ChkUtil.CHK_8143) && (DateUtil.getDateDate().before(DateUtil.getStringDate(bqci.getVehicleInfo().getFirstRegisterDate(),null)))){
			returncode = ChkUtil.CHK_8143 ;	// 初登日期验证(不能晚于当前日期)
		}
		// mili 2015-1-29 15:36:42  end 投保查询初登日期大于当前日期应返回相应校验		ZLL
//		else if(IsNull(ChkUtil.CHK_8144) && (!"08".equals(bqci.getTaxInfo().getTaxTermTypeCode()))){
//			returncode = ChkUtil.CHK_8144 ; // 税种类型代码(必须为”08”) 
//		}
		else if(IsNull(ChkUtil.CHK_8349) && (!("".equals(bqci.getTaxInfo().getPayDate()) || null == bqci.getTaxInfo().getPayDate()))){
			returncode = ChkUtil.CHK_8349; // 申报日期(投保查询时不能填写申报日期字段) 
		}else if(IsNull(ChkUtil.CHK_8111) && (bqci.getVehicleInfo().getMotorTypeCode() == null || "".equals(bqci.getVehicleInfo().getMotorTypeCode()))){
			returncode = ChkUtil.CHK_8111 ; // 交强险车辆类型为空
		}
//		else if(IsNull(ChkUtil.CHK_8355) && (null != bqci.getAreaCode() && bqci.getAreaCode().length() != 6)){
//			returncode = ChkUtil.CHK_8355 ;	// 验证国标地区代码不合法
//		}
		else if(IsNull(ChkUtil.CHK_8140) && (null != bqci.getCompanyCode() && bqci.getCompanyCode().length() != 4)){
			returncode = ChkUtil.CHK_8140 ;	// 验证公司代码不合法
		}else if("T".equals(bqci.getTaxInfo().getTaxConditionCode())){	//	 纳税类型是 T 的业务效验
			if(daeate_type != null){
				if(IsNull(ChkUtil.CHK_8118) && (daeate_type.getDeductionDocumentNumber() != null
						&& !"".equals(daeate_type.getDeductionDocumentNumber()))){
					returncode = ChkUtil.CHK_8118;	 //	纳税类型为T时，不可以减免税凭证号
				}else if(IsNull(ChkUtil.CHK_8118) && (daeate_type.getDeductionDueCode() != null
						&& !"".equals(daeate_type.getDeductionDueCode()))){
					returncode = ChkUtil.CHK_8118;	 //	纳税类型为T时，不可以传减免税原因代码
				}else if(IsNull(ChkUtil.CHK_8118) && (daeate_type.getDeductionDueType() != null
						&& !"".equals(daeate_type.getDeductionDueType()))){
					returncode = ChkUtil.CHK_8118;	 //	纳税类型为T时，不可以传减免税方案代码
				}else if(IsNull(ChkUtil.CHK_8118) && (daeate_type.getTaxDepartment() != null
						&& !"".equals(daeate_type.getTaxDepartment()))){
					returncode = ChkUtil.CHK_8118;	 //	纳税类型为T时，不可以传开具减免税凭证的税务机关名称
				}else if(IsNull(ChkUtil.CHK_8118) && (daeate_type.getDeduction() != 0.0
						&& !"".equals(daeate_type.getDeduction()))){
					returncode = ChkUtil.CHK_8118;	 //	纳税类型为T时，不可以传减免金额
				}else if(IsNull(ChkUtil.CHK_8118) && (daeate_type.getDeductionDueProportion() != 0.0 
						&& !"".equals(daeate_type.getDeductionDueProportion()))){
					returncode = ChkUtil.CHK_8118;	 //	纳税类型为T时，不可以传减免比例
				}else if(IsNull(ChkUtil.CHK_8118) && (daeate_type.getTaxDepartmentCode() != null
						&& !"".equals(daeate_type.getTaxDepartmentCode()))){
					returncode = ChkUtil.CHK_8118;	 //	纳税类型为T时，不可以税务机关代码
				}
			}
			if(paid_type != null){
				if(IsNull(ChkUtil.CHK_8119) && (paid_type.getTaxDepartment() != null
						&& !"".equals(paid_type.getTaxDepartment()))){
					returncode = ChkUtil.CHK_8119;	 //	纳税类型为T时，不可以传开具完税凭证的税务机关名称
				}else if(IsNull(ChkUtil.CHK_8119) && (paid_type.getTaxDocumentNumber() != null
						&& !"".equals(paid_type.getTaxDocumentNumber()))){
					returncode = ChkUtil.CHK_8119;	 //	纳税类型为T时，不可以传完税凭证号码
				}else if(IsNull(ChkUtil.CHK_8119) && (paid_type.getTaxDepartmentCode() != null
						&& !"".equals(paid_type.getTaxDepartmentCode()))){
					returncode = ChkUtil.CHK_8119;	 //	纳税类型为T时，不可以传开具完税凭证的税务机关代码
				}
			}
		}else if("C".equals(bqci.getTaxInfo().getTaxConditionCode())){  //	 纳税类型是 C 的业务效验
			if(IsNull(ChkUtil.CHK_8120) && (daeate_type == null)){
				returncode = ChkUtil.CHK_8120 ; // 纳税类型为C时，减免税信息不能为空
			}
			// mili start 2014-12-26 新增减免税凭证号是否必传的开关  Y 不做效验  N 做效验
			else if("N".equals(CfgLoader.getConfigValue("SysParam", "JM_Switch")) && (daeate_type.getDeductionDocumentNumber() == null || "".equals(daeate_type.getDeductionDocumentNumber()))){
				returncode = ChkUtil.CHK_8121 ; // 纳税类型为C时，减免税凭证号不能为空
			}
			// mili end 2014-12-26 新增减免税凭证号是否必传的开关  Y 不做效验  N 做效验
//			else if("C".equals(daeate_type.getDeductionDueCode()) && daeate_type.getDeductionDocumentNumber() == null || "".equals(daeate_type.getDeductionDocumentNumber())){
//				returncode = ChkUtil.CHK_8121 ; // 纳税类型为C时，减免税凭证号不能为空
//			}
			else if(IsNull(ChkUtil.CHK_8122) && (daeate_type.getDeductionDueCode() == null
					|| "".equals(daeate_type.getDeductionDueCode()))){
				returncode = ChkUtil.CHK_8122 ; // 纳税类型为C时，减免税原因代码不能为空
			}else if(IsNull(ChkUtil.CHK_8123) && (daeate_type.getDeductionDueType() == null
					|| "".equals(daeate_type.getDeductionDueType()))){
				returncode = ChkUtil.CHK_8123 ; // 纳税类型为C时，减免税方案代码不能为空
			}
//			else if(IsNull(ChkUtil.CHK_8113) && (daeate_type != null && !("P".equals(daeate_type.getDeductionDueType()) || "A".equals(daeate_type.getDeductionDueType())))){
//				returncode = ChkUtil.CHK_8113 ; // 减免税方案代码校验  不正确
//			}
			else if(IsNull(ChkUtil.CHK_8124) && (daeate_type.getTaxDepartment() == null
					|| "".equals(daeate_type.getTaxDepartment()))){
				returncode = ChkUtil.CHK_8124 ; // 纳税类型为C时，开具减免税凭证的税务机关名称不能为空
			}
//			else if(daeate_type.getTaxDepartmentCode() == null
//					|| "".equals(daeate_type.getTaxDepartmentCode())){
//				returncode = ChkUtil.CHK_8125 ; // 纳税类型为C时，开具减免税凭证的税务机关代码不能为空
//			}
//			else if(IsNull(ChkUtil.CHK_8135) && ( daeate_type.getDeductionDueType() != null && "E".equals(daeate_type.getDeductionDueType()))){
//				returncode = ChkUtil.CHK_8135; 	// 纳税类型为C时，减免税方案代码不符合减免规定
//			}
//			else if(IsNull(ChkUtil.CHK_8126) && (paid_type != null
//					&& paid_type.getTaxDepartment() != null
//					&& !"".equals(paid_type.getTaxDepartment()))){
//				returncode = ChkUtil.CHK_8126 ; // 纳税类型为C时，不可以传开具完税凭证的税务机关名称
//			}
			else if(IsNull(ChkUtil.CHK_8126) && (paid_type != null
					&& paid_type.getTaxDocumentNumber() != null
					&& !"".equals(paid_type.getTaxDocumentNumber()))){
				returncode = ChkUtil.CHK_8126 ; // 纳税类型为C时，不可以传完税凭证号码
			}else if(IsNull(ChkUtil.CHK_8126) && (paid_type != null
					&& paid_type.getTaxDepartmentCode() != null
					&& !"".equals(paid_type.getTaxDepartmentCode()))){
				returncode = ChkUtil.CHK_8126 ; // 纳税类型为C时，不可以传开具完税凭证的税务机关代码
			}else if((paid_type != null
					&& paid_type.getTaxDepartment() != null
					&& !"".equals(paid_type.getTaxDepartment()))){
				returncode = ChkUtil.CHK_8126 ; // 纳税类型为C时，不可以传开具完税凭证的税务机关代码
			}
			
			//根据整合说明书 增加  减免税业务规则完善  mili 2014-12-25 14:48:43 start
			else if("P".equals(daeate_type.getDeductionDueType())){	// 减免方案为比例减免时，减免比例不能为0并且在[0,1)之间，且减免金额不能有值
				if(IsNull(ChkUtil.CHK_8471) && (daeate_type.getDeductionDueProportion() <= 0.0
						|| daeate_type.getDeductionDueProportion() > 1)){	
					returncode = ChkUtil.CHK_8471 ; 	// 减免比例不能为0并且在[0,1)之间  	
				}
				if(IsNull(ChkUtil.CHK_8138) && (daeate_type.getDeduction() != 0.0 
						&& !"".equals(daeate_type.getDeduction()))){			
					returncode = ChkUtil.CHK_8138 ;		// 减免金额不能有值
				}
			}else if("A".equals(daeate_type.getDeductionDueType())){	// 金额减免
				if(IsNull(ChkUtil.CHK_8472) && (daeate_type.getDeductionDueProportion() != 0 
						|| daeate_type.getDeduction() <= 0)){
					returncode = ChkUtil.CHK_8472 ; // 	减免方案为金额减免时，减免比例不能大于0，且减免金额不能为0
				}
			}
			//根据整合说明书 增加  减免税业务规则完善  mili 2014-12-25 14:48:43 end
		}else if("P".equals(bqci.getTaxInfo().getTaxConditionCode())){	//	 纳税类型是 P 的业务效验
			if(paid_type == null){
				returncode = ChkUtil.CHK_8127;	// 纳税类型为P时，完税信息不能为空
			}else if(IsNull(ChkUtil.CHK_8128) && (paid_type.getTaxDepartment() == null
					|| "".equals(paid_type.getTaxDepartment()))){
				returncode = ChkUtil.CHK_8128;	// 纳税类型为P时，开具完税凭证的税务机关名称不能为空
			}else if(IsNull(ChkUtil.CHK_8130) && (paid_type.getTaxDocumentNumber() == null
					|| "".equals(paid_type.getTaxDocumentNumber()))){
				returncode = ChkUtil.CHK_8130;	// 纳税类型为P时，完税凭证号码不能为空
			}else if(daeate_type != null){
				if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getDeductionDocumentNumber() != null
						&& !"".equals(daeate_type.getDeductionDocumentNumber()))){
					returncode = ChkUtil.CHK_8131;	// 纳税类型为P时，减免税凭证号不能有值
				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getDeductionDueCode() != null
						&& !"".equals(daeate_type.getDeductionDueCode()))){
					returncode = ChkUtil.CHK_8131;	// 纳税类型为P时，减免税原因代码不能有值
				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getDeductionDueType() != null
						&& !"".equals(daeate_type.getDeductionDueType()))){
					returncode = ChkUtil.CHK_8131;	// 纳税类型为P时，减免税方案代码不能有值
				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getTaxDepartment() != null
						&& !"".equals(daeate_type.getTaxDepartment()))){
					returncode = ChkUtil.CHK_8131;	// 纳税类型为P时，开具减免税凭证的税务机关名称不能有值
				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getDeduction() != 0)){
					returncode = ChkUtil.CHK_8131;	// 纳税类型为P时，减免金额不能有值
				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getDeductionDueProportion() != 0)){
					returncode = ChkUtil.CHK_8131;	// 纳税类型为P时，减免比例不能有值
				}else if(IsNull(ChkUtil.CHK_8131) && (daeate_type.getTaxDepartmentCode() != null
						&& !"".equals(daeate_type.getTaxDepartmentCode()))){
					returncode = ChkUtil.CHK_8131;	// 纳税类型为P时，税务机关代码不能有值
				}
			}
		}else if("E".equals(bqci.getTaxInfo().getTaxConditionCode())){
			if(bqci.getTaxInfo().getCurrentTaxDue() != null){
				if(IsNull(ChkUtil.CHK_8132) && (daeate_type == null)){
					returncode = ChkUtil.CHK_8132;	// 纳税类型为E时，减免税信息对象不能为空
				}else if(daeate_type != null
						&& "C".equals(daeate_type.getDeductionDueCode())){
					if(IsNull(ChkUtil.CHK_8134) && (daeate_type.getDeductionDueCode() == null
							|| "".equals(daeate_type.getDeductionDueCode()))){
						returncode = ChkUtil.CHK_8134;	// 纳税类型为E时，减免税原因代码不能为空
					}else if(IsNull(ChkUtil.CHK_8135) && (daeate_type.getDeductionDueType() == null
							|| "".equals(daeate_type.getDeductionDueType()))){
						returncode = ChkUtil.CHK_8135;	// 纳税类型为E时，减免税方案代码不能为空
					}else if(IsNull(ChkUtil.CHK_8136) && (daeate_type.getTaxDepartment() == null
							|| "".equals(daeate_type.getTaxDepartment()))){
						returncode = ChkUtil.CHK_8136;	// 纳税类型为E时，开具减免税凭证的税务机关名称不能为空
					}
//					else if(IsNull(ChkUtil.CHK_8148) && (daeate_type.getDeductionDueProportion() != 0
//							|| daeate_type.getDeduction() != 0)){
//						returncode = ChkUtil.CHK_8148;	// 纳税信息为E时，减税比例和减税金额不能都大于零
//					}
					else if(IsNull(ChkUtil.CHK_8141) && (!"E".equals(daeate_type.getDeductionDueType()))){
						returncode = ChkUtil.CHK_8141;	// 纳税类型为E时,减免方案必须是免税
					}
				}else if(IsNull(ChkUtil.CHK_8134) && (daeate_type.getDeductionDueCode() == null
						|| "".equals(daeate_type.getDeductionDueCode()))){
					returncode = ChkUtil.CHK_8134;	// 纳税类型为E时，减免税原因代码不能为空
				}else if(IsNull(ChkUtil.CHK_8135) && (daeate_type.getDeductionDueType() == null
						|| "".equals(daeate_type.getDeductionDueType()))){
					returncode = ChkUtil.CHK_8135;	// 纳税类型为E时，减免税方案代码不能为空
				}
//				else if(IsNull(ChkUtil.CHK_8148) && (daeate_type.getDeductionDueProportion() != 0
//						|| daeate_type.getDeduction() != 0)){
//					returncode = ChkUtil.CHK_8148;	// 纳税信息为免税时，减税比例和减税金额不能都大于零
//				}
				else if(IsNull(ChkUtil.CHK_8136) && (daeate_type.getTaxDepartment() == null 
						|| "".equals(daeate_type.getTaxDepartment()))){
					returncode = ChkUtil.CHK_8136;	// 纳税类型为E时,开机减免机关税务名称代码不能为空
				}else if(IsNull(ChkUtil.CHK_8141) && !("E".equals(daeate_type.getDeductionDueType()))){
					returncode = ChkUtil.CHK_8141 ; // 减免税方案代码校验  不正确
				}
				
				// mili start 2014-12-26 新增减免税凭证号是否必传的开关 Y 不做效验  N 做效验
				else if("N".equals(CfgLoader.getConfigValue("SysParam", "JM_Switch")) && (daeate_type.getDeductionDocumentNumber() == null || "".equals(daeate_type.getDeductionDocumentNumber()))){
					returncode = ChkUtil.CHK_8133 ; // 纳税类型为E时，减免税凭证号不能为空
				}
				// mili end 2014-12-26 新增减免税凭证号是否必传的开关  Y 不做效验 N 做效验
			}
			if(IsNull(ChkUtil.CHK_8138) && (paid_type != null
					&& paid_type.getTaxDepartment() != null
					&& !"".equals(paid_type.getTaxDepartment()))){
				returncode = ChkUtil.CHK_8138;	//	纳税类型为E时，开具完税凭证的税务机关名称不能有值
			}else if(IsNull(ChkUtil.CHK_8138) && (paid_type != null
					&& paid_type.getTaxDocumentNumber() != null
					&& !"".equals(paid_type.getTaxDocumentNumber()))){
				returncode = ChkUtil.CHK_8138;	//	纳税类型为E时，完税凭证号码不能有值
			}else if(IsNull(ChkUtil.CHK_8138) && (paid_type != null
					&& paid_type.getTaxDepartmentCode() != null
					&& !"".equals(paid_type.getTaxDepartmentCode()))){
				returncode = ChkUtil.CHK_8138;	//	纳税类型为E时，开具完税凭证的税务机关代码不能有值
			}
		}else if(IsNull(ChkUtil.CHK_8113) && (ShuiKuanType.MotorP_Matching(bqci.getVehicleInfo().getMotorTypeCode()) && bqci.getVehicleInfo().getDisplacement() <= 0.00)){
			returncode = ChkUtil.CHK_8113 ;		// 按排量计算的车辆，排量值必须大于0   这个判断条件必须在 E 下面  因为 纳税类型是E 的情况下排量是不做效验的
		}else if(IsNull(ChkUtil.CHK_8142) && ("R".equals(bqci.getTaxInfo().getTaxConditionCode()))){
			returncode = ChkUtil.CHK_8142;	// 投保查询纳税类型不能为拒缴
		}else{
			returncode = ChkUtil.CHK_0000 ;
		}
		return returncode ;
	}
	/**
	 * 用户名密码验证
	 * */
	public String N_P_checking(Object o) {
		String returncode = ChkUtil.CHK_8008;
		BaseQueryReqInfo bqci = (BaseQueryReqInfo) o ;
		String Pwssword_f = bqci.getPassword();
		String Pwssword_l = CfgLoader.getConfigValue("SysParam", "Password");
		String Username_f = bqci.getUserName();
		String Username_l = CfgLoader.getConfigValue("SysParam", "Username");
		// 用户名密码 是  null 默认为验证失败
		if(IsNull(ChkUtil.CHK_8008) && (Username_f == null || Pwssword_f == null || "".equals(Username_f) || "".equals(Pwssword_f))){
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
		if(CHK_CODE == null || "".equals(CHK_CODE.trim())){
			flag = false ;
		}
		return flag ;
	}
}
