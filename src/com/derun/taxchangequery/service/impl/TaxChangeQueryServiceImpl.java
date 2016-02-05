package com.derun.taxchangequery.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.derun.all.common.Double_Decimal;
import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.BaseChangeQueryResInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxCarCount;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.car.TaxCarType;
import com.derun.common.chk.BaseChangeQueryChk;
import com.derun.common.init.CfgLoader;
import com.derun.common.match.Matching_Rule;
import com.derun.common.tax.TaxBase;
import com.derun.common.tax.TaxCounter;
import com.derun.common.tax.TaxCounterImpl;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.DateUtil;
import com.derun.common.util.FDMianShui;
import com.derun.common.util.LogUtil;
import com.derun.hdk.HDK_DAO;
import com.derun.model.po.Car_Id_No;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_RKMX_QS;
import com.derun.model.po.TaxConfirmno_CHK;
import com.derun.taxchangequery.check.TaxChangeQueryCommon;
import com.derun.taxchangequery.dao.impl.TaxChangeQueryDAO_Common;
import com.derun.taxchangequery.dao.impl.TaxChangeQueryDao;
import com.derun.taxchangequery.dao.impl.TaxChangeQueryImpl;
import com.derun.taxchangequery.dao.impl.TaxChangeQuery_Common;
import com.derun.taxchangequery.service.TaxChangeQueryService;

/**
 * @author MILI
 * @Email
 * @date 2014-4-23
 *       变更查询服务
 * @version
 */
public class TaxChangeQueryServiceImpl implements TaxChangeQueryService {
	TaxChangeQueryImpl taxchangeQueryImpl = new TaxChangeQueryImpl();
	TaxChangeQueryCommon taxchangecommon = new TaxChangeQueryCommon();
	TaxCounter taxConter = new TaxCounterImpl();		// 算税接口
	TaxChangeQueryDAO_Common taxchangeDao = new TaxChangeQueryDAO_Common();		// 本地数据库交互类 公共
	TaxChangeQuery_Common taxchange_com = new TaxChangeQuery_Common();			// 公共封装类
	BaseChangeQueryChk baseChangQueryChk = new BaseChangeQueryChk();	// 批改查询用户名密码和入参有效性验证
	TaxChangeQueryDao taxChangeQueryDao = new TaxChangeQueryDao();		// 变更查询 封装写库
	TaxCounter taxCounter = new TaxCounterImpl();						// 税款计算
	TaxCarType taxCarType = new TaxCarType();			// 车辆信息、车船税信息匹配
	TaxBase taxBase = new TaxBase();					//税款基类
	Matching_Rule matching_Rule = new Matching_Rule();	//车辆匹配规则
	LogUtil log = new LogUtil("变更查询服务");
	private HDK_DAO hdk_dao = new HDK_DAO();
//	private CarType_DAO cattype_dao = new CarType_DAO();
	@Override
	public BaseChangeQueryResInfo taxChangeQuery(BaseChangeQueryReqInfo basechangequeryreqinfo) {
		Tax_Type taxType = null ; Tax_Type taxType_h = null , taxType_wtmd = null;
		String changeQueryConfirmNO = null ;	//	入参的确认码
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.startLog();
		}
		String isInsert = null ;
		boolean same_car = true ;   // 批改前后是不是同一辆车
		BaseChangeQueryResInfo basechangequeryresinfo = new BaseChangeQueryResInfo();   // 出参对象
		Matching_Rule MR = new Matching_Rule();		// 车辆匹配规则
		SYJK_CCS_RKMX rkmx = null ;					// 入库明细 
		Map<String, Object> taxCarMap = null;		// 纳税类型、纳税对象信息
		String acompany = basechangequeryreqinfo.getCompanyCode();		// 公司代码（4位）
		String aacre = basechangequeryreqinfo.getAreaCode();			// 国标区域代码（6位）
		TaxDealCode_Type dealcode = TaxCarCount.getTaxqueryNo("1", acompany,aacre, "C");	// 车船税变更查询码(TaxDealCode_Type类型的)
		boolean secondInsure = true ;	// 续保标志  secondInsure	 true 为续保，false不为续保
		String linshiCar = null ,_linshiCar = null ;			// 临时上路车标志
//		String mytime = DateUtil.getDateYYYY();		// 系统当前年String
//		int xtrq = Integer.parseInt(mytime) ;		// 系统当前年Integer
		int fd = 4;						// 是否是 法定免税车
//		boolean Local_tax = false ;		// 是否是本地免税车
		int rkmx_fd = 4 ;				// 上次是否是 法定免税车
		String nslx_bg = "";			// 要批改的纳税类型
		String nslx_zq = "";			// 批改之前的纳税类型 
		String VIN = "";				// VIN
		String HPHM = "";				// 号牌号码
//		String HPZL = "";				// 号牌种类
		String ENGINENO = "";			// 发动机号
		String CARSERIALNO = "";		// 车辆序列号
		Car_Id_No cin = null ;
		String declarState = "" ;		// 平台状态
		// 用户有效性验证
		List<SYJK_CCS_QGCLWTMDXX> listWt = new ArrayList<SYJK_CCS_QGCLWTMDXX>();
		String userChk = baseChangQueryChk.N_P_checking(basechangequeryreqinfo);
		if (ChkUtil.CHK_0000.equals(userChk)) {		// 用户验证通过
			String changeType = basechangequeryreqinfo.getChangeType() == null ? "15" : basechangequeryreqinfo.getChangeType();// 获取变更类型
			changeQueryConfirmNO = basechangequeryreqinfo.getTaxConfirmNo().getTaxDealCode_Type();	// 获取变更查询入参的确认码
			List<TaxConfirmno_CHK> tax_chk = taxchangeDao.checkConfirm(changeQueryConfirmNO);		// 根据入参确认码 封装确认入参信息
			// 判断确认码是否有效
			if(tax_chk != null && tax_chk.size() > 0){
				// 根据确认码 查询入库明细
				rkmx = taxchangeDao.getRKMX(changeQueryConfirmNO);
				rkmx = taxchangeQueryImpl.getCommon_rkmx(rkmx);
				// 保单不允许批改  直接取 投保查询时的保单日期
				basechangequeryreqinfo.setInsureStartDate(rkmx.getInsureStartDate());
				basechangequeryreqinfo.setInsureEndDate(rkmx.getInsureEndDate());
				
				List<SYJK_CCS_RKMX> listRk = taxchangeDao.getRKMXList(changeQueryConfirmNO);
				// 是否做过退保 	条件：车主名称 是 4
				if(rkmx != null && "4".equals(rkmx.getCHANGETYPE())){
					// 做过退保的  不能批改 直接返回
					return taxChangeQueryDao.CC_Object(basechangequeryreqinfo,dealcode,"1",ChkUtil.CHK_8353,null,null);
				}
				// 如果车辆信息入参不传的情况下  去入库明细查找填充
				basechangequeryreqinfo = taxchange_com.getVT(basechangequeryreqinfo,rkmx);
				// 是否是续保? 条件：开具完税凭证的税务机关名称 是 空 或者 开具完税凭证的税务机关代码 是 空 或者 完税凭证号码 是空
				if (rkmx.getTAXDEPARTMENT() == null || "".equals(rkmx.getTAXDEPARTMENT())
						|| rkmx.getTAXDEPARTMENTCODE() == null || "".equals(rkmx.getTAXDEPARTMENTCODE())
						|| rkmx.getTAXDOCUMENTNUMBER() == null || "".equals(rkmx.getTAXDOCUMENTNUMBER())) {
					secondInsure = false;
				}
				// 封装  车船税变更查询服务入参  
//				BaseChangeQueryReqInfoUtilEntity bcqriue = taxchange_com.getBcqriue(rkmx);  
				// 入参 车船税信息不为空的情况下
				if(basechangequeryreqinfo.getTaxInfo() != null && !"".equals(basechangequeryreqinfo.getTaxInfo())){
					// 入参的纳税类型代码 不是空的情况下
					if(basechangequeryreqinfo.getTaxInfo().getTaxConditionCode() != null && !"".equals(basechangequeryreqinfo.getTaxInfo().getTaxConditionCode())){
						nslx_zq = rkmx.getTAXCONDITIONCODE();
						nslx_bg = basechangequeryreqinfo.getTaxInfo().getTaxConditionCode();
						
					}else{
						// 入参 车船税信息 为空的情况下  查询入库明细 填充
						basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,null,null));
						nslx_zq = rkmx.getTAXCONDITIONCODE();
						nslx_bg = rkmx.getTAXCONDITIONCODE();
					}
				}else{
					// 入参 车船税信息 为空的情况下  查询入库明细 填充
					basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,null,null));
					nslx_zq = rkmx.getTAXCONDITIONCODE();
					nslx_bg = rkmx.getTAXCONDITIONCODE();
				}
				if("1".equals(basechangequeryreqinfo.getChangeType())){
					// 入参有效性校验
					userChk = baseChangQueryChk.Join_valid(basechangequeryreqinfo,rkmx);				// xiugai
					// 新加 一个核定库总开关  mili 2015年8月26日15:57:57 
					if("Y".equals(CfgLoader.getConfigValue("SysSwitch", "HDK_Flag").trim())){
						//mili 核定库的车辆匹配  2015-5-21 15:05:38  start
						Map<String,Object> map = hdk_dao.getVehicle_Type(basechangequeryreqinfo.getVehicleInfo());
						isInsert = (String) map.get("SF");
						basechangequeryreqinfo.setVehicleInfo((Vehicle_Type)map.get("VT"));
						//mili 核定库的车辆匹配  2015-5-21 15:05:38  end
					}
					if(!ChkUtil.CHK_0000.equals(userChk)){		//  入参有效性效验 失败
						return taxChangeQueryDao.CC_Object(basechangequeryreqinfo,dealcode,"1",userChk,null,null);
					}
				}
				VIN = basechangequeryreqinfo.getVehicleInfo().getVIN() == null ? "" : basechangequeryreqinfo.getVehicleInfo().getVIN();
				HPHM = basechangequeryreqinfo.getVehicleInfo().getLicensePlateNo();
				linshiCar = rkmx.getSPECIALCARTYPE() == null ? "" : rkmx.getSPECIALCARTYPE() ;
				_linshiCar = basechangequeryreqinfo.getVehicleInfo().getSpecialCarType() == null ? "" : basechangequeryreqinfo.getVehicleInfo().getSpecialCarType();
//				HPZL = basechangequeryreqinfo.getVehicleInfo().getLicensePlateType();
				ENGINENO = basechangequeryreqinfo.getVehicleInfo().getEngineNo();
				
				if (!"1".equals(rkmx.getPLATFORMSTATE()) && !"E".equals(rkmx.getTAXCONDITIONCODE())		// 
						&& ((!VIN.equals(rkmx.getVIN()) && VIN.trim().length() >= 17) || (VIN.trim().length() < 17 
						&& (!VIN.equals(rkmx.getVIN()) || !HPHM.equals(rkmx.getHPHM()) || !ENGINENO.equals(rkmx.getEngineNo()))))
						|| "R".equals(rkmx.getTAXCONDITIONCODE())) {
					// 未申报且认为批改前后不是同一车辆时查问题名单，批改前后不是同一车辆依据:
					// 1 车架号大于等于17位且批改前后车架号不一样
					// 2 车架号小于17位，批改前后其车架号或车牌或改动机号不一样
					// 批改拒缴时查问题名单
					// 退保情况下  不用查询 问题名单
//					if(!("8".equals(changeType) || "10".equals(changeType)
//							|| "9".equals(changeType) || "11".equals(changeType)
//							|| "12".equals(changeType) || "13".equals(changeType) 
//							|| "14".equals(changeType))){
						listWt = MR.getMatching_wtmd(basechangequeryreqinfo.getVehicleInfo()); 		// 问题名单车辆匹配
						same_car = false ;			// 批改前后是不是同一辆车   fales  不是同一辆车
//					}
					
				}
//				if(listWt.isEmpty()	&& (rkmx.getCARSERIALNO() != null || !"".equals(rkmx.getCARSERIALNO()))
//						&& !"E".equals(nslx_bg)	&& ((VIN.equals(rkmx.getVIN()) && VIN.trim().length() >= 17) 
//						|| (VIN.trim().length() < 17 && (VIN.equals(rkmx.getVIN()) && (HPHM.equals(rkmx.getHPHM()) 
//						|| ENGINENO.equals(rkmx.getEngineNo()))))) || "R".equals(rkmx.getTAXCONDITIONCODE())){
//					// 批改前后为同一车辆且投保拒缴为问题车时，按投保时查出的机动车序列号查出问题信息
//					if("R".equals(nslx_bg) && "T".equals(nslx_zq)){
//						listWt = MR.getMatching_wtmd(basechangequeryreqinfo.getVehicleInfo());
//					}else{
////						if(!("8".equals(changeType) || "10".equals(changeType)
////								|| "9".equals(changeType) || "11".equals(changeType)
////								|| "12".equals(changeType) || "13".equals(changeType) 
////								|| "14".equals(changeType))){
//							listWt = MR.getWTMDList(SqlText.R_03_QGCLWTMDXX_001 ,rkmx.getCARSERIALNO());
////					}
//					}
//				}
				// 返回出参 增加问题序列号
				if(listWt != null && listWt.size() > 0 ){
					SYJK_CCS_QGCLWTMDXX lastYear = (SYJK_CCS_QGCLWTMDXX) listWt.get(0);
					if (null != lastYear) {
						CARSERIALNO = lastYear.getCARSERIALNO();	// 问题数据 序列号
						basechangequeryresinfo.setCarSerialNo(CARSERIALNO);		// 问题名单序列号
						// 退保长期
						if("9".equals(changeType) || "11".equals(changeType) || "12".equals(changeType) 
								|| "13".equals(changeType) || "14".equals(changeType)){
							taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
							taxType_wtmd = taxConter.getTax((HashMap<String, Object>) taxCarMap);
							taxType = taxchangecommon.getTax_Type(listRk, taxType_wtmd,"CQ");
							taxType_h = taxType;
						}else if("8".equals(changeType) || "10".equals(changeType)){		// 退保短期
							taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,null,"YES",null,"YES",same_car);
							taxType_wtmd = taxConter.getTax((HashMap<String, Object>) taxCarMap);
							taxType = taxchangecommon.getTax_Type(listRk, taxType_wtmd,"DQ");
							taxType_h = taxType ;
						}else{	// 其他类型的车批改为问题车  2014-11-4 MILI
//							basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",basechangequeryreqinfo.getTaxInfo()));
							taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
							taxType_wtmd = taxConter.getTax((HashMap<String, Object>) taxCarMap);
							taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType_wtmd);
						}
					}
				}else{
					declarState = rkmx.getPLATFORMSTATE();		// 获取平台状态
					// 跨年批改
					if((Integer.parseInt(DateUtil.getDateYYYY()) > Integer
							.parseInt(rkmx.getPAYDATE().substring(0, 4))
							&& "1".equals(changeType) && !"R".equals(nslx_zq))){
						// 跨年批改 不涉及任何税款信息 直接返回
						basechangequeryreqinfo.getTaxInfo().setTaxConditionCode(rkmx.getTAXCONDITIONCODE());
						taxType_h = taxchange_com.FZ_Tax_Type(rkmx,null,null);
						taxType_h.setTaxAmount(new TaxAmount_Type());
//						return basechangequeryresinfo = taxChangeQueryDao.CC_Object(basechangequeryreqinfo,dealcode,"1",userChk,null,null);			
					}else if(Integer.parseInt(DateUtil.getDateYYYY()) > Integer.parseInt(rkmx.getPAYDATE().substring(0, 4))
							&& "1".equals(changeType) && "R".equals(nslx_zq)){		// 批改拒缴重新按新车算税
						taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
						taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
						taxType_h = taxType ;
//						basechangequeryresinfo = taxChangeQueryDao.CC_Object(basechangequeryreqinfo,dealcode,"1",userChk,null,null);
					}else if(Integer.parseInt(DateUtil.getDateYYYY()) > Integer.parseInt(rkmx.getPAYDATE().substring(0, 4))
							&& ("8".equals(changeType) || "10".equals(changeType)
									|| "9".equals(changeType) || "11".equals(changeType)
									|| "12".equals(changeType) || "13".equals(changeType) 
									|| "14".equals(changeType))){	
						// 跨年退保 不涉及任何税款信息 直接返回
						basechangequeryreqinfo.getTaxInfo().setTaxConditionCode(rkmx.getTAXCONDITIONCODE());
						taxType_h = taxchange_com.FZ_Tax_Type(rkmx,null,null);
						taxType_h.setTaxAmount(new TaxAmount_Type());
//						basechangequeryresinfo = taxChangeQueryDao.CC_Object(basechangequeryreqinfo,dealcode,"1",userChk,null,null);
					}
					else if("1".equals(declarState) || "2".equals(declarState)){		// 已申报 1   未申报 0  
						if("1".equals(declarState) && ("8".equals(declarState) || "10".equals(changeType))){	// 针对于变更类型为8 ,10（注销登记和丢失,只退短期税额的情况）
							// mili 2014-12-25 17:44:59  start   已申报退短期  车船税对象应该是 取 上次的 记录
							taxType_h = taxchange_com.FZ_Tax_Type(rkmx,null,null);
							taxType_h.setTaxAmount(new TaxAmount_Type());
//							basechangequeryresinfo = taxChangeQueryDao.CC_Object(basechangequeryreqinfo,dealcode,"1",userChk,null,null);	
							// mili 2014-12-25 17:44:59  end
						}else if(declarState.equals("1") && (changeType.equals("9")	|| changeType.equals("11")
								|| changeType.equals("12") || changeType.equals("13") || changeType.equals("14"))){	// 针对于变更类型为8 ,10（注销登记和丢失,只退长期税额的情况）
							
							basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,null,null));
							taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
							taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
//							List<SYJK_CCS_RKMX> listRk = taxchangeDao.getRKMXList(changeQueryConfirmNO);
							taxType = taxchangecommon.getTax_Type(listRk, taxType,"CQ");
							taxType.setTaxAmount(new TaxAmount_Type());
							taxType_h = taxType ;
						}else{	
							// 不能批改或退保
							if(changeType.equals("8") || changeType.equals("10")){		// 申报以后 变更类型是8,10  退短期
								basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,null,null));
								taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
								taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
								taxType.setTaxAmount(new TaxAmount_Type());
								taxType_h = taxType ;
								
							}
	//						else if(changeType.equals("9")	|| changeType.equals("11") || changeType.equals("12")
//							|| changeType.equals("13") || changeType.equals("14")){		// // 申报以后  变更类型是9,11 退长期
	//							
	//							basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,null,null));
	//							taxCarMap = taxCarType.getCarType(basechangequeryreqinfo);
	//							taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
	//							List<SYJK_CCS_RKMX> listRk = taxchangeDao.getRKMXList(changeQueryConfirmNO);
	//							taxType = taxchangecommon.getTax_Type(listRk, taxType,"CQ");
	//							taxType.setTaxAmount(new TaxAmount_Type());
	//							taxType_h = taxType ;
	//						}
							else{	// 申报后做批改
								
								basechangequeryreqinfo.getTaxInfo().setTaxConditionCode(rkmx.getTAXCONDITIONCODE());
								taxType_h = taxchange_com.FZ_Tax_Type(rkmx,null,null);
								taxType_h.setTaxAmount(new TaxAmount_Type());
							}
						}
					}else {	// 可以退保和批改的情况
//						// 判断是否是本地免税车
//						SYJK_CCS_DSCCSJMDJXX jmdj = cattype_dao.getSYJK_CCS_DSCCSJMDJXX(null,rkmx);
//						if(jmdj != null){
//							int jmszrq = Integer.parseInt((String) DateUtil.getStringDate(jmdj.getJMSZRQ(),null).substring(0, 4));
//							if(jmszrq >= xtrq){
//								Local_tax = true ;
//							}
//						}
						// 是否是法定免税车
						fd = FDMianShui.getFDCL(basechangequeryreqinfo.getVehicleInfo().getLicensePlateNo());
						if(rkmx != null){
							rkmx_fd = FDMianShui.getFDCL(rkmx.getHPHM());
						}
						if("1".equals(changeType)){		// 变更类型是1:批改
							// 特殊车处理 
							if(linshiCar != null && !"".equals(linshiCar) && ("2".equals(linshiCar) || "3".equals(linshiCar))){
								if("2".equals(linshiCar)){  // 临时入境车 批改为 法定免税车
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									basechangequeryreqinfo.getVehicleInfo().setLicensePlateNo(rkmx.getHPHM());
									basechangequeryreqinfo.getVehicleInfo().setSpecialCarType(linshiCar);
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType.getCurrentTaxDue().setTaxStartDate(rkmx.getTAXSTARTDATE());
									taxType.getCurrentTaxDue().setTaxEndDate(rkmx.getTAXENDDATE());
									taxType_h = taxType ; 
								}else if("3".equals(linshiCar)){ 
									// 贵州测试 临时上路车 R 拒缴 批改为 C 减税车没有批改成功 mili 2014-12-17 11:01:49
									if("R".equals(rkmx.getTAXCONDITIONCODE())){
										
									}else{
										basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									}
									// 临时上路车 批改为法定免税车  不能批改 ZYY 2014-11-19 09:28:33 mili 
									basechangequeryreqinfo.getVehicleInfo().setSpecialCarType(linshiCar);
									basechangequeryreqinfo.getVehicleInfo().setLicensePlateNo(rkmx.getHPHM());
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									if(fd != 4){
										// 临时上路车批改为法定免税车 税款始期 应该是批改查询日   MILI 2014-11-4 
										taxType_h.getCurrentTaxDue().setTaxStartDate(DateUtil.getDIY("YYYY-MM-dd"));
									}else{
										
									}
								}else{
									
								}
							}else if(_linshiCar != null && !"".equals(_linshiCar)){		//	批改为 特殊车   2 临时入境  3 临时上到
								//wbzhao20151106 投保拒缴再批改还返回拒缴，入参有纳税类型用入参的
								Tax_Type tt = basechangequeryreqinfo.getTaxInfo()==null?null:basechangequeryreqinfo.getTaxInfo();
								//wbzhao20151106 end
								if(basechangequeryreqinfo.getTaxInfo()!=null&&rkmx!=null&&rkmx.getTAXCONDITIONCODE()!=null&&"R".equals(rkmx.getTAXCONDITIONCODE())){
									//wbzhao20151112投保拒缴，入参有车船税对象，不用rkmx填充入参
								}else{
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",tt));    // ZXL BUG 123   2014-10-11 10:27:06   MILI
								}
								basechangequeryreqinfo.getVehicleInfo().setSpecialCarType(linshiCar);
								taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
								taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
								taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
							}
							else if("T".equals(nslx_zq)){	// 投保时 纳税类型是 T
								if(fd != 4){	// 投保时 纳税类型是 T 变更后纳税类型是 E法定免税
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									// 普通车批改为法定免税车 税款始期 应该是和投保保持一致   MILI 2014-11-10  
									taxType_h.getCurrentTaxDue().setTaxStartDate(rkmx.getTAXSTARTDATE());
								}else if("T".equals(nslx_bg)){		// 投保时 纳税类型是 T 变更后纳税类型是  T
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"NO",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
								}else if("C".equals(nslx_bg)){		// 投保时 纳税类型是 T 变更后纳税类型是 C
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);//wbzhao 20150121 from sbf T批改C不改任何信息返回税款
									taxType_h = taxType ; 
								}else if("E".equals(nslx_bg)){		// 投保时 纳税类型是 T 变更后纳税类型是 E
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									taxType_h = taxType ; 
								}else if("P".equals(nslx_bg)){		// 投保时 纳税类型是 T 变更后纳税类型是 P
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									taxType_h = taxType ; 
								}else if("R".equals(nslx_bg)){		// 投保时 纳税类型是 T 变更后纳税类型是 R
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									taxType_h = taxType ; 
								}else{
									System.out.println("未知错误   纳税类型：" + nslx_bg);
								}
							}else if("C".equals(nslx_zq)){	// 投保时 纳税类型是 C
								if(fd != 4){	// 投保时 纳税类型是 C 变更后纳税类型是 E法定免税
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									// 普通车批改为法定免税车 税款始期 应该是和投保保持一致   MILI 2014-11-10  
									taxType_h.getCurrentTaxDue().setTaxStartDate(rkmx.getTAXSTARTDATE());
								}else if("T".equals(nslx_bg)){	// 投保时 纳税类型是 C 变更后纳税类型是 T
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									taxType_h = taxType ; 
								}else if("C".equals(nslx_bg)){	// 投保时 纳税类型是 C 变更后纳税类型是 C
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"NO",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
								}else if("E".equals(nslx_bg)){	// 投保时 纳税类型是 C 变更后纳税类型是 E
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
								}else if("P".equals(nslx_bg)){	// 投保时 纳税类型是 C 变更后纳税类型是 P
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									taxType_h = taxType ; 
								}else if("R".equals(nslx_bg)){	// 投保时 纳税类型是 C 变更后纳税类型是 R
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									taxType_h = taxType ; 
								}else{
									System.out.println("未知错误   纳税类型：" + nslx_bg);
								}
							}else if("E".equals(nslx_zq)){	// 投保时 纳税类型是 E
								if(rkmx_fd != 4){	// 上次是法定免税 
									if("0".equals(CfgLoader.getConfigValue("SysParam", "VOUCHER_F").trim())){ // 法免车可以随意批改 0 可以 mili 2014-12-19 12:42:09
										if(fd != 4){	// 投保时 纳税类型是 E 变更后纳税类型是 E法定免税
											taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"NO",same_car);
											taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
											taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
											// 普通车批改为法定免税车 税款始期 应该是和投保保持一致   MILI 2014-11-10  
											taxType_h.getCurrentTaxDue().setTaxStartDate(rkmx.getTAXSTARTDATE());
										}else if("T".equals(nslx_bg)){	// 投保时 纳税类型是 E 变更后纳税类型是 T
											// 投保法定免税车 批改改为非法定免税车时 税款起期应该是 批改查询日 mili 2014-12-19 16:15:14  ZLL
											String firstdate = basechangequeryreqinfo.getVehicleInfo().getFirstRegisterDate();
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(DateUtil.getDIY("yyyy-MM-dd"));
											taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"NO",same_car);
											taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
											taxType_h = taxType ; 
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(firstdate);
										}else if("C".equals(nslx_bg)){	// 投保时 纳税类型是 E 变更后纳税类型是 C
											// 投保法定免税车 批改改为非法定免税车时 税款起期应该是 批改查询日 mili 2014-12-19 16:15:14  ZLL
											String firstdate = basechangequeryreqinfo.getVehicleInfo().getFirstRegisterDate();
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(DateUtil.getDIY("yyyy-MM-dd"));
											taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"NO",same_car);
											taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
											taxType_h = taxType ; 
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(firstdate);
										}else if("E".equals(nslx_bg)){	// 投保时 纳税类型是 E 变更后纳税类型是 E
											// 投保法定免税车 批改改为非法定免税车时 税款起期应该是 批改查询日 mili 2014-12-19 16:15:14  ZLL
											String firstdate = basechangequeryreqinfo.getVehicleInfo().getFirstRegisterDate();
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(DateUtil.getDIY("yyyy-MM-dd"));
											taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"NO",same_car);
											taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
											taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(firstdate);
										}else if("P".equals(nslx_bg)){	// 投保时 纳税类型是 E 变更后纳税类型是 P
											// 投保法定免税车 批改改为非法定免税车时 税款起期应该是 批改查询日 mili 2014-12-19 16:15:14  ZLL
											String firstdate = basechangequeryreqinfo.getVehicleInfo().getFirstRegisterDate();
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(DateUtil.getDIY("yyyy-MM-dd"));
											taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"NO",same_car);
											taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
											taxType_h = taxType ; 
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(firstdate);
										}else if("R".equals(nslx_bg)){	// 投保时 纳税类型是 E 变更后纳税类型是 R
											// 投保法定免税车 批改改为非法定免税车时 税款起期应该是 批改查询日 mili 2014-12-19 16:15:14  ZLL
											String firstdate = basechangequeryreqinfo.getVehicleInfo().getFirstRegisterDate();
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(DateUtil.getDIY("yyyy-MM-dd"));
											taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"NO",same_car);
											taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
											taxType_h = taxType ; 
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(firstdate);
										}else{
											System.out.println("未知错误   纳税类型：" + nslx_bg);
										}
									}else{	// 法免车开关没有打开的情况下  只能批改为法免和 正常纳税车  mili 2014-12-19 12:42:09
										if(fd != 4){	// 投保时 纳税类型是 E 变更后纳税类型是 E法定免税
											taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"NO",same_car);
											taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
											taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
											// 普通车批改为法定免税车 税款始期 应该是和投保保持一致   MILI 2014-11-10  
											taxType_h.getCurrentTaxDue().setTaxStartDate(rkmx.getTAXSTARTDATE());
										}else{
											if("C".equals(nslx_bg) || "E".equals(nslx_bg) || "P".equals(nslx_bg)){
												basechangequeryreqinfo.getTaxInfo().getCurrentTaxDue().setDerate(null);
												basechangequeryreqinfo.getTaxInfo().getCurrentTaxDue().setPaid(null);
												basechangequeryreqinfo.getTaxInfo().setTaxConditionCode("T");
											}
											// 投保法定免税车 批改改为非法定免税车时 税款起期应该是 批改查询日 mili 2014-12-19 16:15:14  ZLL
											String firstdate = basechangequeryreqinfo.getVehicleInfo().getFirstRegisterDate();
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(DateUtil.getDIY("yyyy-MM-dd"));
											taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"NO",same_car);
											taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
											taxType_h = taxType ; 
											basechangequeryreqinfo.getVehicleInfo().setFirstRegisterDate(firstdate);
										}
									}
								}else if("2".equals(rkmx.getSPECIALCARTYPE())){	// 上次是 临时入境车辆
									
									basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									
								}else{		// 上次是普通免税
									if(fd != 4){	// 投保时 纳税类型是 E 变更后纳税类型是 E法定免税
										taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
										taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
										taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
										// 普通车批改为法定免税车 税款始期 应该是和投保保持一致   MILI 2014-11-10  
										taxType_h.getCurrentTaxDue().setTaxStartDate(rkmx.getTAXSTARTDATE());
									}else if("T".equals(nslx_bg)){	// 投保时 纳税类型是 E 变更后纳税类型是 T
										basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
										taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
										taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
										taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									}else if("C".equals(nslx_bg)){	// 投保时 纳税类型是 E 变更后纳税类型是 C
										basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
										taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
										taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
										taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									}else if("E".equals(nslx_bg)){	// 投保时 纳税类型是 E 变更后纳税类型是 E
										basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
										taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"NO",same_car);
										taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
										taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									}else if("P".equals(nslx_bg)){	// 投保时 纳税类型是 E 变更后纳税类型是 P
										basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
										taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
										taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
										taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									}else if("R".equals(nslx_bg)){	// 投保时 纳税类型是 E 变更后纳税类型是 R
										basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,"",null));
										taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
										taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
										taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
									}else{
										System.out.println("未知错误   纳税类型：" + nslx_bg);
									}
								}
							}else if("R".equals(nslx_zq)){	// 投保时 纳税类型是 R
								if(fd != 4){	// 投保时 纳税类型是 R 变更后纳税类型是 E法定免税
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
								}else if("T".equals(nslx_bg)){	// 投保时 纳税类型是 R 变更后纳税类型是 T
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
								}else if("C".equals(nslx_bg)){	// 投保时 纳税类型是 R 变更后纳税类型是 C
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
								}else if("E".equals(nslx_bg)){	// 投保时 纳税类型是 R 变更后纳税类型是 E
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
								}else if("P".equals(nslx_bg)){	// 投保时 纳税类型是 R 变更后纳税类型是 P
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
								}else if("R".equals(nslx_bg)){	// 投保时 纳税类型是 R 变更后纳税类型是 R
									taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",null,"YES",same_car);
									taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
									taxType_h = taxchange_com.getTaxAmout(tax_chk,taxType);
								}else{
									System.out.println("未知错误   纳税类型：" + nslx_bg);
								}
							}else{
								System.out.println("未知错误   纳税类型：" + nslx_zq);
							}
						// 跨年退保长期 短期
						}else if(Integer.parseInt(DateUtil.getDateYYYY()) > Integer.parseInt(rkmx.getPAYDATE().substring(0, 4))){
							taxType_h = taxchangecommon.setTax_Type(rkmx);
							List<SYJK_CCS_RKMX_QS> list_Rk_qs = taxchangeDao.getRKMX_QS(changeQueryConfirmNO);
							taxType_h.setDelinquentTaxDue(taxchange_com.setAnnualTax(list_Rk_qs));
							taxType_h = taxchangecommon.getTax_Type(listRk, taxType_h,"CQ");
							taxType_h.setTaxAmount(new TaxAmount_Type());
						}else if(changeType.equals("8") || changeType.equals("10")){		// 变更类型是8,10  退短期
							basechangequeryreqinfo.setTaxInfo(taxchange_com.FZ_Tax_Type(rkmx,null,null));
							taxCarMap = taxCarType.getCarType(basechangequeryreqinfo,listWt,"YES",rkmx,"NO",same_car);
							
							double tmp_ann = 0.0 ;
							for(SYJK_CCS_RKMX rk : listRk){
								tmp_ann += rk.getANNUALTAXDUE();
							}
							Tax_Type taxType_tmp = (Tax_Type) taxCarMap.get("TT");
							taxCarMap.remove("TT");
							taxType_tmp.getTaxAmount().setAnnualTaxDue(tmp_ann);
							taxCarMap.put("TT", taxType_tmp);
							
							
							taxType = taxConter.getTax((HashMap<String, Object>) taxCarMap);
							taxType = taxchangecommon.getTax_Type(listRk, taxType,"DQ");
							taxType.setTaxAmount(taxchangecommon.setTaxamount(taxType.getTaxAmount()));
							taxType_h = taxType ;
						}else if(changeType.equals("9")	|| changeType.equals("11") || changeType.equals("12")
								|| changeType.equals("13") || changeType.equals("14") ){		// 变更类型是9,11  退长期
							taxType_h = taxchangecommon.setTax_Type(rkmx);
							List<SYJK_CCS_RKMX_QS> list_Rk_qs = taxchangeDao.getRKMX_QS(changeQueryConfirmNO);
							taxType_h.setDelinquentTaxDue(taxchange_com.setAnnualTax(list_Rk_qs));
							taxType_h = taxchangecommon.getTax_Type(listRk, taxType_h,"CQ");
						}else{	// 
							return taxChangeQueryDao.CC_Object(basechangequeryreqinfo,dealcode,"1",ChkUtil.CHK_8315,null,null);
						}
					}
				}
			}else{	// 确认码  效验没有通过
				return taxChangeQueryDao.CC_Object(basechangequeryreqinfo,dealcode,"1",ChkUtil.CHK_8311,null,null);
			}
		}else{	// 用户名校验没有通过
			return taxChangeQueryDao.CC_Object(basechangequeryreqinfo,dealcode,"1",ChkUtil.CHK_8008,null,null);
		}
		
		// 车辆标识信息（机动车序列号、车辆匹配规则ID）
		if(taxCarMap != null){
			cin = (Car_Id_No) taxCarMap.get("CIN");
		}
		// 合计金额 去除负号 - 
		taxType_h.setTaxAmount(taxchangecommon.setTaxamount(taxType_h.getTaxAmount()));
		
		// mili 申报后做批改 应该是 1Y start
		if("1".equals(declarState) || "P".equals(basechangequeryreqinfo.getTaxInfo().getTaxConditionCode())){
			userChk = "1Y" ;
		}else if("4".equals(declarState) && (null==basechangequeryreqinfo.getTaxInfo()) || (null!=basechangequeryreqinfo.getTaxInfo() && "R".equals(basechangequeryreqinfo.getTaxInfo().getTaxConditionCode()))){
			//wbzhao add 20150421 纳税类型是拒缴
			//确认时是拒缴，批改的时候没有传送车船税标签，那么取投保确认的纳税类型，返回1N
			userChk = "1N" ;
		}
		// mili 申报后做批改 应该是 1Y end
		basechangequeryresinfo = taxChangeQueryDao.CC_Object(basechangequeryreqinfo,dealcode,"1",userChk,taxType_h,cin);
		List<Object> list_obj = taxChangeQueryDao.Packaging(basechangequeryreqinfo,basechangequeryresinfo,dealcode,
				rkmx,basechangequeryresinfo.getTaxInfo(),userChk,changeQueryConfirmNO,cin,isInsert);		// 封装返回出参
		boolean flag = taxChangeQueryDao.Write_Bank(list_obj);		// 插入数据库
		if(flag){	// 写库成功
			// 车船税合计金额  保留2为小数
			basechangequeryresinfo.getTaxInfo().setTaxAmount(Double_Decimal.double_Amout(basechangequeryresinfo.getTaxInfo().getTaxAmount()));
			// 纳税类型是 P 完税的情况下  减免信息置空
			if("P".equals(basechangequeryresinfo.getTaxInfo().getTaxConditionCode())){
				basechangequeryresinfo.getTaxInfo().getCurrentTaxDue().setDerate(new Derate_Type());
			}
//			return basechangequeryresinfo ;
		}else{		// 写库失败
			basechangequeryresinfo = taxChangeQueryDao.CC_Object(null,dealcode,"1",ChkUtil.CHK_8000,null,null);
		}
		
		// 事后处理（冲正、）
// 		HashMap mmap = new HashMap();
// 		mmap.put("Req", basechangequeryreqinfo);
// 		mmap.put("Res", basechangequeryresinfo);
// 		TaxChangeQueryServiceTraceThread asd = new TaxChangeQueryServiceTraceThread(mmap);
// 	    Thread tt = new Thread(asd);
//      tt.start();
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.endLog();
		}
		return basechangequeryresinfo ;
	}
}
