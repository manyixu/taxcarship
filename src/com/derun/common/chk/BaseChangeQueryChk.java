package com.derun.common.chk;

import java.util.List;
import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.DateUtil;
import com.derun.common.util.FDMianShui;
import com.derun.common.util.ShuiKuanType;
import com.derun.model.po.SYJK_CCS_CCSCXRCJBXX;
import com.derun.model.po.SYJK_CCS_CODE;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.taxchangequery.dao.impl.C_SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-3-11 10:14:59
 * @描述：批改查询用户名密码和入参有效性验证
 * */
public class BaseChangeQueryChk {
	public String Join_valid(Object o,Object oo) {
		String returncode = ChkUtil.CHK_0000 ; 
		SYJK_CCS_RKMX rkmx = (SYJK_CCS_RKMX)oo;
		BaseChangeQueryReqInfo bcqci = (BaseChangeQueryReqInfo) o ;
		Vehicle_Type vehicle = bcqci.getVehicleInfo();
		Derate_Type daeate_type = new Derate_Type();
		Paid_Type paid_type = new Paid_Type();
		if(bcqci.getTaxInfo().getCurrentTaxDue() != null){
			daeate_type = bcqci.getTaxInfo().getCurrentTaxDue().getDerate(); // 减免对象
		}
		if(bcqci.getTaxInfo().getCurrentTaxDue() != null){
			paid_type = bcqci.getTaxInfo().getCurrentTaxDue().getPaid();	// 完税对象
		}
		if(rkmx == null){
			rkmx = new SYJK_CCS_RKMX();
		}
		List<SYJK_CCS_CODE> ccs_code = CfgLoader.getConfig("SysCode_SM"); 		// 税目表
		C_SYJK_CCS_RKMX c_rkmx = new C_SYJK_CCS_RKMX();
		SYJK_CCS_CCSCXRCJBXX ccscxrc = c_rkmx.getCcscxrc(bcqci.getTaxConfirmNo().getTaxDealCode_Type());
		
		if(vehicle == null){
			return ChkUtil.CHK_8320 ;	// 车辆信息不能为空
		}
		double WholeWeight = vehicle.getWholeWeight();
		int W_Number = Integer.parseInt(CfgLoader.getConfigValue("SysParam", "Kerb_mass")) ;	// 系统配置整备质量
		// 匹配交强险 车辆类型
		String jqtype = ShuiKuanType.JiaoQiangType(vehicle.getMotorTypeCode());
		// 税目匹配
		boolean flag = ShuiKuanType.ShuiMu_type(ccs_code,jqtype);
		if(IsNull(ChkUtil.CHK_8311) && (ccscxrc == null)){
			returncode = ChkUtil.CHK_8311 ;		// 无效确认码
		}
//		else if(IsNull(ChkUtil.CHK_8117) && (!flag)){
//			returncode = ChkUtil.CHK_8117 ;		// 无法找到对应税目
//		}
		else if(IsNull(ChkUtil.CHK_8324) && (jqtype == null || "".equals(jqtype))){
			returncode = ChkUtil.CHK_8324 ;		// 交强险车辆类型为空
		}else if(IsNull(ChkUtil.CHK_8348) && (null == bcqci.getCompanyCode() || "".equals(bcqci.getCompanyCode()))){
			returncode = ChkUtil.CHK_8348 ; // 公司代码不能为空
		}
		// 整备质量 效验 mili 2014-12-11 15:17:20 start
		else if(IsNull(ChkUtil.CHK_8150) && ((int)WholeWeight) > W_Number){
			returncode = ChkUtil.CHK_8150 ; // 整备质量超出规定范围
		}
		// mili 2014-12-11 15:17:03 end
		// mili 2015-5-13 15:21:41 strat 只有投保确认时拒缴的情况下  批改查询才可以是拒缴 R 其他情况不允许批改拒缴
		else if(IsNull(ChkUtil.CHK_8315) && !"R".equals(rkmx.getTAXCONDITIONCODE()) && "R".equals(bcqci.getTaxInfo().getTaxConditionCode())){
			return ChkUtil.CHK_8315 ;
		}
		// mili 2015-5-13 15:21:41 end
//		else if(IsNull(ChkUtil.CHK_8347) && (null == bcqci.getTaxInfo().getCurrentTaxDue().getTaxLocationCode() || "".equals(bcqci.getTaxInfo().getCurrentTaxDue().getTaxLocationCode()))){
//			returncode =  ChkUtil.CHK_8347 ; // 验证地区代码否为空
//		}
//		else if(IsNull(ChkUtil.CHK_8139) && (null == bcqci.getAreaCode() || "".equals(bcqci.getAreaCode()))){
//			returncode = ChkUtil.CHK_8139 ;// 地区代码不能为空
//		}
		else if(IsNull(ChkUtil.CHK_8321) && (vehicle.getFirstRegisterDate() == null || "".equals(vehicle.getFirstRegisterDate()) || vehicle.getFirstRegisterDate().length() < 10 || !DateUtil.checkDate(vehicle.getFirstRegisterDate()))){
			return ChkUtil.CHK_8321 ;	   // 初登日期格式不正确
		}
		else if(IsNull(ChkUtil.CHK_8354) && (DateUtil.getDateDate().before(DateUtil.getStringDate(vehicle.getFirstRegisterDate(),null)))){
			returncode = ChkUtil.CHK_8354 ; // 初登日期不能大于当前日期
		}
//		else if(IsNull(ChkUtil.CHK_8479) && ("".equals(bcqci.getTaxInfo().getTaxConditionCode()) || null == bcqci.getTaxInfo().getTaxConditionCode() || !ShuiKuanType.getTaxConditionCode(bcqci.getTaxInfo().getTaxConditionCode()))){
//			returncode = ChkUtil.CHK_8479 ; // 纳税类型为空，或填写不正确时，返回校验
//		}
//		else if(IsNull(ChkUtil.CHK_8308) && ("".equals(bcqci.getTaxInfo().getTaxTermTypeCode()) || null == bcqci.getTaxInfo().getTaxTermTypeCode() || !"08".equals(bcqci.getTaxInfo().getTaxTermTypeCode()))){
//			returncode = ChkUtil.CHK_8308 ; // 批改查询时，税种类型代码为空，或填写不正确时，返回错误信息
//		}else if(IsNull(ChkUtil.CHK_8310) && ((vehicle.getLicensePlateNo() == null || "".equals(vehicle.getLicensePlateNo()))
//				&& "0".equals(vehicle.getNoLicenseFlag()))){
//			returncode = ChkUtil.CHK_8310 ; // 车牌号码为空时，未上牌车辆为0时
//		}else if(IsNull(ChkUtil.CHK_8311) && ((vehicle.getLicensePlateType() == null || "".equals(vehicle.getLicensePlateType()))
//				&& "0".equals(vehicle.getNoLicenseFlag()))){
//			returncode = ChkUtil.CHK_8311 ; // 车牌种类为空时，未上牌车辆为0时
//		}
		else if(IsNull(ChkUtil.CHK_8323) && (ShuiKuanType.MotorP_Matching(vehicle.getMotorTypeCode()) && vehicle.getDisplacement() == 0 && !"E".equals(bcqci.getTaxInfo().getTaxConditionCode()))){
			returncode = ChkUtil.CHK_8323 ; // 按排量计算的车辆，排量值必须大于0，否则返回校验
		}
//		else if(IsNull(ChkUtil.CHK_8312) && (daeate_type != null && ShuiKuanType.getSysCode_DueCode(daeate_type.getDeductionDueCode()))){
//			returncode = ChkUtil.CHK_8112 ; // 减免税原因代码校验   不正确
//		}else if(IsNull(ChkUtil.CHK_8313) && (daeate_type != null && ShuiKuanType.getSysCode_DueType(daeate_type.getDeductionDueType()))){
//			returncode = ChkUtil.CHK_8113 ; // 减免税方案代码校验  不正确
//		}else if(IsNull(ChkUtil.CHK_8323) && (vehicle.getEngineNo() == null || "".equals(vehicle.getEngineNo()))){
//			returncode = ChkUtil.CHK_8323 ; // 发动机号不能为空
//		}else if(bcqci.getVehicleInfo().getMotorUsageTypeCode() == null || "".equals(bcqci.getVehicleInfo().getMotorUsageTypeCode())){
//			returncode = ChkUtil.CHK_8312 ; // 车辆使用性质不能为空
//		}else if(ShuiKuanType.getMotorType(bcqci.getVehicleInfo().getMotorUsageTypeCode())){
//			returncode = ChkUtil.CHK_8313 ; // 车辆使用性质代码不能超出文档中“车辆使用性质代码”之外的数据，否则提示车辆使用性质的值无效
//		}else if(IsNull(ChkUtil.CHK_8314) && (vehicle.getNoLicenseFlag() == null || "".equals(vehicle.getNoLicenseFlag())
//				|| !ShuiKuanType.getNoLicense(vehicle.getNoLicenseFlag()))){
//			returncode = ChkUtil.CHK_8314 ; // 未上牌车辆代码字段为空或为文档中“未上牌车辆代码”之外的数据，返回错误提示信息
//		}else if(IsNull(ChkUtil.CHK_8315) && ("1".equals(vehicle.getNoLicenseFlag()) && ((vehicle.getLicensePlateNo() != null 
//				|| "".equals(vehicle.getLicensePlateNo()) || vehicle.getLicensePlateType() != null 
//				|| "".equals(vehicle.getLicensePlateType()))))){
//			returncode = ChkUtil.CHK_8315 ; // 未上牌车辆代码字段为“1”时，传送车牌种类和车牌号码
//		}else if(IsNull(ChkUtil.CHK_8316) && (0.0 == vehicle.getPower() || "".equals(vehicle.getPower()))){
//			returncode = ChkUtil.CHK_8316 ; // 批改查询时，功率字段代码不能为空，否则返回相应的错误信息
//		}else if(IsNull(ChkUtil.CHK_8320) && (vehicle.getTonnage() == 0.0 || "".equals(vehicle.getTonnage()))){
//			returncode = ChkUtil.CHK_8320 ; // 核定载质量字段代码不能为空
//		}else if(IsNull(ChkUtil.CHK_8317) && (vehicle.getRatedPassengerCapacity() == 0.0 || "".equals(vehicle.getRatedPassengerCapacity()))){
//			returncode = ChkUtil.CHK_8317 ; // 核定载客人数不能为空
//		}else if(IsNull(ChkUtil.CHK_8318) && (vehicle.getRatedPassengerCapacity() < 0 || vehicle.getRatedPassengerCapacity() > 999)){
//			returncode = ChkUtil.CHK_8318 ; // 核定载客人数字段代码且必须在0到999之间，否则返回值不在有效范围内
//		}
		else if(IsNull(ChkUtil.CHK_8346) && (!ShuiKuanType.MotorT_Matching(vehicle.getSpecialCarType()))){
			returncode = ChkUtil.CHK_8346 ;		// 特殊车标志只能是1、2、3、4、5或空
		}
//		else if(IsNull(ChkUtil.CHK_8321) && (ShuiKuanType.getMotorType(vehicle.getMotorUsageTypeCode()))){
//			returncode = ChkUtil.CHK_8321 ; // 车辆使用性质代码不能超出文档中“车辆使用性质代码”之外的数据，否则提示车辆使用性质的值无效
//		}
//		else if(IsNull(ChkUtil.CHK_8114) && (!("11".equals(vehicle.getMotorTypeCode()) || "12".equals(vehicle.getMotorTypeCode())) && (vehicle.getWholeWeight() == 0.0 || "".equals(vehicle.getWholeWeight())))){		
//			returncode = ChkUtil.CHK_8114 ; // 整备质量字段不能为空
//		}
//		else if(IsNull(ChkUtil.CHK_8323) && (ShuiKuanType.BG_type(bcqci.getChangeType()))){
//			returncode = ChkUtil.CHK_8323;	// 变更类型不对
//		}
		else if(IsNull(ChkUtil.CHK_8321) && (vehicle.getFirstRegisterDate() == null)){
			returncode = ChkUtil.CHK_8321 ;	// 初始登记日期不能为空
		}
//		else if(IsNull(ChkUtil.CHK_8326) && (vehicle.getVIN() == null || "".equals(vehicle.getVIN()))){
//			returncode = ChkUtil.CHK_8326 ;	// 车架号不能为空
//		}
		else if(IsNull(ChkUtil.CHK_8327) && (ShuiKuanType.MotorD_Matching(vehicle.getMotorTypeCode()) && vehicle.getWholeWeight() == 0.0)){
			returncode = ChkUtil.CHK_8327 ;	// 按整备质量计算的车辆，整备质量值必须大于0
		}
//		else if(IsNull(ChkUtil.CHK_8328) && (!ShuiKuanType.MotorT_Matching(vehicle.getSpecialCarType()))){
//			returncode = ChkUtil.CHK_8328 ;	// 特殊车标志只能是1、2、3、4、5或空
//		}else if(IsNull(ChkUtil.CHK_8329) && (null != bcqci.getAreaCode() && bcqci.getAreaCode().length() != 6)){
//			returncode = ChkUtil.CHK_8329 ;	// 国际区域代码不合法
//		}else if(IsNull(ChkUtil.CHK_8330) && (null != bcqci.getCompanyCode() && bcqci.getCompanyCode().length() != 4)){
//			returncode = ChkUtil.CHK_8330 ;	// 代收公司代码不合法
//		}else if(IsNull(ChkUtil.CHK_9999) && (null == bcqci.getTaxInfo())){
//			System.out.println("车船税对象为空喽。。。。。。。 BaseChangeQueryChk --> Join_valid()" );
//			returncode = ChkUtil.CHK_9999 ;
//		}
		else if("T".equals(bcqci.getTaxInfo().getTaxConditionCode())){
			if(IsNull(ChkUtil.CHK_0000) && (bcqci.getTaxInfo().getCurrentTaxDue() == null)){
				returncode = ChkUtil.CHK_0000 ;
			}else if(IsNull(ChkUtil.CHK_8325) && (daeate_type != null && (daeate_type.getDeduction() != 0
					|| (daeate_type.getDeductionDocumentNumber() != null && !"".equals(daeate_type.getDeductionDocumentNumber()))
					|| (daeate_type	.getTaxDepartment() != null && !"".equals(daeate_type.getTaxDepartment()))
					|| (daeate_type.getDeductionDueCode() != null && !"".equals(daeate_type.getDeductionDueCode()))	
					|| daeate_type.getDeductionDueProportion() != 0 || (daeate_type.getDeductionDueType() != null 
					&& !"".equals(daeate_type.getDeductionDueType()))))){
				returncode = ChkUtil.CHK_8325 ;	// 纳税类型为T时，不可以传减免税信息
			}else if(IsNull(ChkUtil.CHK_8325) && (daeate_type != null && (daeate_type.getTaxDepartmentCode() != null
					&& !"".equals(daeate_type.getTaxDepartmentCode())))){
				returncode = ChkUtil.CHK_8325;	 //	纳税类型为T时，不可以税务机关代码
			}else if(IsNull(ChkUtil.CHK_8326) && (paid_type != null && ((paid_type.getTaxDepartment() != null && !"".equals(paid_type.getTaxDepartment())) 
					|| (paid_type.getTaxDocumentNumber() != null && !"".equals(paid_type.getTaxDocumentNumber()))))){
				returncode = ChkUtil.CHK_8326 ;	// 纳税类型为T时，不可以传完税信息
			}
		}else if("C".equals(bcqci.getTaxInfo().getTaxConditionCode())){
//			if(IsNull(ChkUtil.CHK_9999) && (bcqci.getTaxInfo() == null	|| bcqci.getTaxInfo().getCurrentTaxDue() == null)){
//				System.out.println("车船税对象为空喽。。。。。。。 BaseChangeQueryChk --> Join_valid()" );
//				returncode = ChkUtil.CHK_9999 ;
//			}else 
			if(IsNull(ChkUtil.CHK_8327) && (daeate_type == null)){
				returncode = ChkUtil.CHK_8327 ; // 纳税类型为C时，减免税信息不能为空
			}else if(IsNull(ChkUtil.CHK_8329) && (daeate_type != null
					&& (daeate_type.getDeductionDueCode() == null 
					|| "".equals(daeate_type.getDeductionDueCode())))){
				returncode = ChkUtil.CHK_8329 ; // 纳税类型为C时，减免税原因代码不能为空
			}else if(IsNull(ChkUtil.CHK_8330) && (daeate_type != null
					&& (daeate_type.getDeductionDueType() == null 
					|| "".equals(daeate_type.getDeductionDueType())))){
				returncode = ChkUtil.CHK_8330 ; // 纳税类型为C时，减免税方案代码不能为空
			}else if(IsNull(ChkUtil.CHK_8331) && (daeate_type != null
					&& (daeate_type.getTaxDepartment() == null 
					|| "".equals(daeate_type.getTaxDepartment())))){
				returncode = ChkUtil.CHK_8331 ; // 纳税类型为C时，开具减免税凭证的税务机关名称不能为空
			}
			
			// mili start 2014-12-26 新增减免税凭证号是否必传的开关  Y 不做效验  N 做效验
			else if("N".equals(CfgLoader.getConfigValue("SysParam", "JM_Switch")) && (daeate_type.getDeductionDocumentNumber() == null || "".equals(daeate_type.getDeductionDocumentNumber()))){
				returncode = ChkUtil.CHK_8328 ; // 纳税类型为C时，减免税凭证号不能为空
			}
			// mili end 2014-12-26 新增减免税凭证号是否必传的开关  Y 不做效验  N 做效验
			
//			else if(IsNull(ChkUtil.CHK_8113) && (daeate_type != null && !("P".equals(daeate_type.getDeductionDueType()) || "A".equals(daeate_type.getDeductionDueType())))){
//				returncode = ChkUtil.CHK_8113 ; // 减免税方案代码校验  不正确
//			}
			//根据整合说明书 增加  减免税业务规则完善  mili 2014-12-25 14:48:43 start
			else if(daeate_type != null
					&& daeate_type.getDeductionDueProportion() > 1){
				returncode = ChkUtil.CHK_8339 ; // 纳税类型为C时，减免比例不能大于1
			}
//			else if(daeate_type != null
//					&& daeate_type.getDeductionDueProportion() > 1){
//				returncode = ChkUtil.CHK_8339 ; // 纳税类型为C时，减免比例不能大于1
//			}else if(IsNull(ChkUtil.CHK_8364) && (daeate_type.getDeductionDueProportion() != rkmx.getDEDUCTIONDUEPROPORTION() && !"R".equals(rkmx.getTAXCONDITIONCODE()))){
//				returncode = ChkUtil.CHK_8364 ; // 纳税类型为C时，批改查询是不允许批改减免比例
//			}
//			else if(IsNull(ChkUtil.CHK_8471) && ("P".equals(daeate_type.getDeductionDueType())
//					&& (daeate_type.getDeductionDueProportion() <= 0
//					|| daeate_type.getDeduction() != 0 
//					|| daeate_type.getDeductionDueProportion() > 1))){
//				returncode = ChkUtil.CHK_8471 ; // 减免方案为比例减免时，减免比例不能为0，且减免金额不能有值
//			}else if(IsNull(ChkUtil.CHK_8472) && ("A".equals(daeate_type.getDeductionDueType())
//					&& (daeate_type.getDeductionDueProportion() > 0 
//					|| daeate_type.getDeduction() < 0))){
//				returncode = ChkUtil.CHK_8472 ; // 减免方案为金额减免时，减免比例不能大于0，且减免金额不能小于0
//			}
			else{
				returncode = ChkUtil.CHK_0000 ;
			}
			// 2014-9-25 20:53:02 mili
			if(IsNull(ChkUtil.CHK_8332) && (paid_type != null && ((paid_type.getTaxDepartment() != null
					&& !"".equals(paid_type.getTaxDepartment())) || (paid_type.getTaxDocumentNumber() != null 
					&& !"".equals(paid_type.getTaxDocumentNumber()))))) {
				returncode = ChkUtil.CHK_8332 ;	// System.out.println("纳税类型为C时，完税信息对象不能有值");
			}
		}else if("P".equals(bcqci.getTaxInfo().getTaxConditionCode())){
//			if(IsNull(ChkUtil.CHK_0000) && (null == bcqci.getTaxInfo() || null == bcqci.getTaxInfo().getCurrentTaxDue())){
//				returncode = ChkUtil.CHK_0000 ;
//			}else 
				
			if(IsNull(ChkUtil.CHK_8333) && (paid_type == null)){
				returncode = ChkUtil.CHK_8333 ;	// 纳税类型为P时，本年纳税信息对象不能为空
			}else if(IsNull(ChkUtil.CHK_8334) && (paid_type != null
					&& (paid_type.getTaxDepartment() == null 
					|| "".equals(paid_type.getTaxDepartment())))){
				returncode = ChkUtil.CHK_8334 ;	// 纳税类型为P时，开具完税凭证的税务机关名称不能为空
			}else if(IsNull(ChkUtil.CHK_8335) && (paid_type != null
					&& (paid_type.getTaxDocumentNumber() == null)
					|| "".equals(paid_type.getTaxDocumentNumber()))){
				returncode = ChkUtil.CHK_8335;	// 纳税类型为P时，完税凭证号码不能为空
			}
//			else{
//				returncode = ChkUtil.CHK_0000 ;
//			}
			// 2014-9-25 20:39:56  mili 纳税类型为P时，减免信息对象不能有值
			if(IsNull(ChkUtil.CHK_8336) && (daeate_type != null && (daeate_type.getDeduction() != 0 || (daeate_type.getDeductionDocumentNumber() != null 
					&& !"".equals(daeate_type.getDeductionDocumentNumber())) || (daeate_type.getTaxDepartment() != null 
					&& !"".equals(daeate_type.getTaxDepartment())) || (daeate_type.getDeductionDueCode() != null 
					&& !"".equals(daeate_type.getDeductionDueCode())) || daeate_type.getDeductionDueProportion() != 0
					|| (daeate_type.getDeductionDueType() != null && !"".equals(daeate_type.getDeductionDueType()))))) {
				returncode = ChkUtil.CHK_8336 ; //  纳税类型为P时，减免税信息对象不能有值
			}
			else if(IsNull(ChkUtil.CHK_8131) && (daeate_type != null && (daeate_type.getTaxDepartmentCode() != null
					&& !"".equals(daeate_type.getTaxDepartmentCode())))){
				returncode = ChkUtil.CHK_8131;	// 纳税类型为P时，税务机关代码不能有值
			}
			else{
				returncode = ChkUtil.CHK_0000 ;
			}
		}else if("E".equals(bcqci.getTaxInfo().getTaxConditionCode())){
			// 增加 是否是 法定免税    
			int fd = FDMianShui.getFDCL(vehicle.getLicensePlateNo());
			if(bcqci.getTaxInfo() != null && bcqci.getTaxInfo().getCurrentTaxDue() != null){
				if(IsNull(ChkUtil.CHK_8337) && (daeate_type == null)){
					returncode = ChkUtil.CHK_8337 ; //  纳税类型为E时，本年纳税信息对象不能为空
				}else if(IsNull(ChkUtil.CHK_8141) && (!"E".equals(daeate_type.getDeductionDueType()))){
					returncode = ChkUtil.CHK_8141 ; //  纳税类型为E时,减免方案必须是免税
				}else if(fd != 4 && fd != 0 && daeate_type != null && daeate_type.getDeductionDueProportion() != 0){
					returncode = ChkUtil.CHK_8343 ; //  纳税类型为E并且是法定免税时，减免比例不能有值
				}else if(fd != 4 && fd != 0 && daeate_type != null && daeate_type.getDeductionDueProportion() != 0){
					returncode = ChkUtil.CHK_8344 ; //  纳税类型为E并且是法定免税时，减免金额不能有值
				}else if(IsNull(ChkUtil.CHK_8339) && (daeate_type != null && (daeate_type.getDeductionDueCode() == null
						|| "".equals(daeate_type.getDeductionDueCode())))){
					returncode = ChkUtil.CHK_8339 ; // 纳税类型为E时，减免税原因代码不能为空
				}else if(IsNull(ChkUtil.CHK_8340) && (daeate_type != null && (daeate_type.getDeductionDueType() == null 
						|| "".equals(daeate_type.getDeductionDueType())))){
					returncode = ChkUtil.CHK_8340 ; // 纳税类型为E时，减免税方案代码不能为空
				}else if(IsNull(ChkUtil.CHK_8341) && (daeate_type != null && (daeate_type.getTaxDepartment() == null 
						|| "".equals(daeate_type.getTaxDepartment())))){
					returncode = ChkUtil.CHK_8341 ; // 纳税类型为E时，开具减免税凭证的税务机关名称不能为空
				}else if(IsNull(ChkUtil.CHK_8141) && ("P".equals(daeate_type.getDeductionDueType()) || "C".equals(daeate_type.getDeductionDueType()))){
					returncode = ChkUtil.CHK_8141 ; // 纳税类型为E时,减免方案必须是免税
				}
				
				// mili start 2014-12-26 新增减免税凭证号是否必传的开关  Y 不做效验  N 做效验
				else if("N".equals(CfgLoader.getConfigValue("SysParam", "JM_Switch")) && (daeate_type.getDeductionDocumentNumber() == null || "".equals(daeate_type.getDeductionDocumentNumber()))){
					returncode = ChkUtil.CHK_8338 ; // 纳税类型为E时，减免税凭证号不能为空
				}
				// mili end 2014-12-26 新增减免税凭证号是否必传的开关  Y 不做效验  N 做效验
				
//				else if(IsNull(ChkUtil.CHK_8352) && (daeate_type.getDeduction() != 0 || daeate_type.getDeductionDueProportion() != 0)){
//					returncode = ChkUtil.CHK_8352 ; // 纳税信息为E时，减税比例和减税金额不能都大于零
//				}
//				else if("P".equals(daeate_type.getDeductionDueType())){	// 减免方案为比例减免时，减免比例不能为0并且在[0,1)之间，且减免金额不能有值
//					if(IsNull(ChkUtil.CHK_8137) && (daeate_type.getDeductionDueProportion() <= 0.0
//							|| daeate_type.getDeductionDueProportion() > 1)){	
//						returncode = ChkUtil.CHK_8137 ; 	// 减免比例不能为0并且在[0,1)之间  	
//					}
//					if(IsNull(ChkUtil.CHK_8138) && (daeate_type.getDeduction() != 0.0 
//							&& !"".equals(daeate_type.getDeduction()))){			
//						returncode = ChkUtil.CHK_8138 ;		// 减免金额不能有值
//					}
//				}else if("A".equals(daeate_type.getDeductionDueType())){	// 金额减免
//					if(IsNull(ChkUtil.CHK_8139) && (daeate_type.getDeductionDueProportion() != 0 
//							|| daeate_type.getDeduction() <= 0)){
//						returncode = ChkUtil.CHK_8139 ; // 	减免方案为金额减免时，减免比例不能大于0，且减免金额不能为0
//					}
//				}else{
//					returncode = ChkUtil.CHK_0000 ;
//				}
				if (null !=  bcqci.getTaxInfo() && null !=  bcqci.getTaxInfo().getCurrentTaxDue()) {
					if (IsNull(ChkUtil.CHK_8342) && (paid_type != null && (( paid_type.getTaxDepartment() != null && !"".equals( paid_type.getTaxDepartment())) 
						|| ( paid_type.getTaxDocumentNumber() != null && !"".equals( paid_type.getTaxDocumentNumber()))))) {
						returncode = ChkUtil.CHK_8342;	// 纳税类型为E时，完税信息对象不能有值
					}
				}
			}else{
				returncode = ChkUtil.CHK_0000 ;
			}
		}
//		else if("R".equals(bcqci.getTaxInfo().getTaxConditionCode())){ // 纳税类型是R的验证
//			if(daeate_type != null){
//				if(IsNull(ChkUtil.CHK_8356) && (0 != daeate_type.getDeductionDueProportion() || 0 != daeate_type.getDeduction())){
//					returncode = ChkUtil.CHK_8356 ; // 纳税类型为拒缴时不能填写减免比例和减免金额
//				}else if(IsNull(ChkUtil.CHK_8357) && (!("".equals(daeate_type.getDeductionDocumentNumber()) || null == daeate_type.getDeductionDocumentNumber()))){
//					returncode = ChkUtil.CHK_8357 ; // 纳税类型为拒缴时不能输入减免税凭证号
//				}else if(IsNull(ChkUtil.CHK_8358) && (!("".equals(daeate_type.getDeductionDueCode()) || null == daeate_type.getDeductionDueCode()))){
//					returncode = ChkUtil.CHK_8358 ; // 纳税类型为拒缴时不能输入减免税原因代码
//				}else if(IsNull(ChkUtil.CHK_8359) && (!("".equals(daeate_type.getDeductionDueType()) || null == daeate_type.getDeductionDueType()))){
//					returncode = ChkUtil.CHK_8359 ; // 纳税类型为拒缴时不能输入减免税方案代码
//				}else if(IsNull(ChkUtil.CHK_8360) && (!("".equals(daeate_type.getTaxDepartment()) || null == daeate_type.getTaxDepartment()))){
//					returncode = ChkUtil.CHK_8360 ; // 纳税类型为拒缴时不能输入开具减免税凭证的税务机关名称
//				}else if(IsNull(ChkUtil.CHK_8361) && (!("".equals(daeate_type.getTaxDepartmentCode()) || null == daeate_type.getTaxDepartmentCode()))){
//					returncode = ChkUtil.CHK_8361 ; // 纳税类型为拒缴时不能输入开具减免税凭证的税务机关代码
//				}
//			}
//			if(paid_type != null){
//				if(IsNull(ChkUtil.CHK_8362) && (!(paid_type.getTaxDocumentNumber() == null || "".equals(paid_type.getTaxDocumentNumber())))){
//					returncode = ChkUtil.CHK_8362 ; // 纳税类型为拒缴时不能输入完税凭证号码
//				}else if(IsNull(ChkUtil.CHK_8363) && (!(paid_type.getTaxDepartment() == null || "".equals(paid_type.getTaxDepartment())))){
//					returncode = ChkUtil.CHK_8363 ; // 纳税类型为拒缴时不能输入开具完税凭证的税务机关名称
//				}else if(IsNull(ChkUtil.CHK_8364) && (!(paid_type.getTaxDepartmentCode() == null || "".equals(paid_type.getTaxDepartmentCode())))){
//					returncode = ChkUtil.CHK_8364 ; // 纳税类型为拒缴时不能输入开具完税凭证的税务机关代码
//				}
//			}
//		}
		else if(IsNull(ChkUtil.CHK_8323) && (ShuiKuanType.MotorP_Matching(vehicle.getMotorTypeCode()) && vehicle.getDisplacement() == 0.00)){
			returncode = ChkUtil.CHK_8323 ;		// 按排量计算的车辆，排量值必须大于0   这个判断条件必须在 E 下面  因为 纳税类型是E 的情况下排量是不做效验的
		}
		return returncode ;
	}
	/**
	 * 用户名密码验证
	 * */
	public String N_P_checking(Object o) {
		String returncode = ChkUtil.CHK_0000;
		BaseChangeQueryReqInfo bqci = (BaseChangeQueryReqInfo) o ;
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
