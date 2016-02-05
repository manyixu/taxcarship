package com.derun.taxquery.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.derun.all.common.Double_Decimal;
import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.BaseQueryResInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxCarCount;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.car.TaxCarType;
import com.derun.common.chk.TaxQueryBusinessChk;
import com.derun.common.init.CfgLoader;
import com.derun.common.tax.TaxCounter;
import com.derun.common.tax.TaxCounterImpl;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.DateUtil;
import com.derun.common.util.LogUtil;
import com.derun.common.util.Tax_Type_Code;
import com.derun.hdk.HDK_DAO;
import com.derun.model.po.Car_Id_No;
import com.derun.model.po.SYJK_CCS_CODE;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.taxquery.dao.TaxQueryDao;
import com.derun.taxquery.dao.impl.TaxQueryDaoImpl;
import com.derun.taxquery.service.TaxQueryService;

/**
 * 投保查询服务实现
 * 
 * @author ZHENG
 * 
 */
public class TaxQueryServiceImpl implements TaxQueryService {
	private final static String BDQUEYY = "YES" ;					// 是否需要查询数据库标志
	private HDK_DAO hdk_dao = new HDK_DAO();
	TaxQueryDaoImpl taxqueryImpl = new TaxQueryDaoImpl();	// 业务查询类
	TaxQueryBusinessChk taxQueryBusinessChk = new TaxQueryBusinessChk();// 投保查询校验类
	TaxCarType taxCarType = new TaxCarType();// 车辆信息、车船税信息匹配
	TaxCounter taxConter = new TaxCounterImpl();// 算税接口
	TaxQueryDao taxqueryDao = new TaxQueryDao();// 投保查询 封装写库
	LogUtil logUtil = new LogUtil("投保查询服务");
	
	/**
	 * 投保查询主线
	 */
	@Override
	public BaseQueryResInfo tarQuery(BaseQueryReqInfo basequeryreqinfo) {
		BaseQueryResInfo _BaseQueryResInfo = null;// 投保查询返回对象
		String isInsert = null ;
		logUtil.startLog();
		boolean same_car = true ;   // 批改前后是不是同一辆车
		// 纳税类型、纳税对象信息
		Map<String, Object> taxCarMap = null;
		Tax_Type newTax = null ;
		// 登录用户校验
		String loginFlag = taxQueryBusinessChk.N_P_checking(basequeryreqinfo);
		if (ChkUtil.CHK_0000.equals(loginFlag)) { // 用户登录校验成功
			// 车船税标签不是空的情况下
			if(basequeryreqinfo.getTaxInfo() != null || !"".equals(basequeryreqinfo.getTaxInfo())){
				// 入参有效性验证
				String reqReturnFlag = "" ;
				try {
					reqReturnFlag = taxQueryBusinessChk.Join_valid(basequeryreqinfo,null);
				} catch (Exception e) {
					System.out.println("入参有效性验证 出错。。。。。。。");
					reqReturnFlag = "" ;
				}
				// 入参有效性验证
				if (ChkUtil.CHK_0000.equals(reqReturnFlag)) {
					// 新加 一个核定库总开关  mili 2015年8月26日15:57:57 
					if("Y".equals(CfgLoader.getConfigValue("SysSwitch", "HDK_Flag").trim())){
						//mili 核定库的车辆匹配  2015-5-21 15:05:38  start
						Map<String,Object> map = hdk_dao.getVehicle_Type(basequeryreqinfo.getVehicleInfo());
						isInsert = (String) map.get("SF");
						basequeryreqinfo.setVehicleInfo((Vehicle_Type)map.get("VT"));
						//mili 核定库的车辆匹配  2015-5-21 15:05:38  end
					}
					// 进行信息匹配（车辆信息、纳税信息）
					try {
						taxCarMap = taxCarType.getCarType(basequeryreqinfo,null,BDQUEYY,null,"YES",same_car);
						String CF = (String) taxCarMap.get("DuplicateInsure");
						SYJK_CCS_RKMX rkmx = (SYJK_CCS_RKMX) taxCarMap.get("rkmx");
						if("true".equals(CF)){	//  如果是重复投保的情况  纳税类型为P
							String taxTerm = basequeryreqinfo.getTaxInfo().getTaxTermTypeCode();
	
							//2015-12-17	start	suobaowen	
							//修改匹配规则时发现第二次投保返回完税时，数据库中本次储存的车辆信息4项是取得之前入库明细表的值
							//FZ_Tax_Type方法增加了一个入参参数
							basequeryreqinfo = taxqueryImpl.FZ_Tax_Type(rkmx,basequeryreqinfo);
							//2015-12-17	end		suobaowen	修改匹配规则时发现第二次投保返回完税时，数据库中本次储存的车辆信息4项是取得之前入库明细表的值

							basequeryreqinfo.getTaxInfo().getCurrentTaxDue().setDerate(new Derate_Type());
//							basequeryreqinfo.getTaxInfo().getCurrentTaxDue().setPaid(new Paid_Type());
							basequeryreqinfo.getTaxInfo().setTaxConditionCode("P");
							// mili 3.1.2	新增投保查询是否返回完税凭证信息开关  2014-12-11 15:58:19 start
							String Paid_flag = CfgLoader.getConfigValue("SysParam", "Paid_flag") ;	// 是否返回完税信息
							if("P".equals(basequeryreqinfo.getTaxInfo().getTaxConditionCode()) && "false".equals(Paid_flag)){
								basequeryreqinfo.getTaxInfo().getCurrentTaxDue().getPaid().setTaxDepartmentCode("");//wbzhao add 20151023
								basequeryreqinfo.getTaxInfo().getCurrentTaxDue().getPaid().setTaxDepartment("");
								basequeryreqinfo.getTaxInfo().getCurrentTaxDue().getPaid().setTaxDocumentNumber("");
							}else if("P".equals(basequeryreqinfo.getTaxInfo().getTaxConditionCode()) && "true".equals(Paid_flag)){
								basequeryreqinfo.getTaxInfo().getCurrentTaxDue().getPaid().setTaxDepartmentCode("");//wbzhao add 20151023
								basequeryreqinfo.getTaxInfo().getCurrentTaxDue().getPaid().setTaxDepartment(CfgLoader.getConfigValue("SysParam", "TaxDepartment")) ;	
								basequeryreqinfo.getTaxInfo().getCurrentTaxDue().getPaid().setTaxDocumentNumber(CfgLoader.getConfigValue("SysParam", "TaxDocumentNumber"));
							}
							// mili 3.1.2	新增投保查询是否返回完税凭证信息开关  2014-12-11 15:58:19 end
							newTax = basequeryreqinfo.getTaxInfo();
							newTax.setTaxTermTypeCode(taxTerm);
							String carType = (String)taxCarMap.get("CT");
							if(Tax_Type_Code.VOUCHER_F.equals(carType)){//法定免税重复投保不走算税方法，totalAmout返回0.00（不正确），特殊处理 bug241
								newTax.getCurrentTaxDue().setTotalAmount(newTax.getCurrentTaxDue().getTaxDue());
							}
						}else{
							LogUtil lutc = new LogUtil("算税模块");
							lutc.startLog();
							// 算税,调用算税方法，得到车船税信息  重复投保不用再次计算税款
							newTax = taxConter.getTax((HashMap<String, Object>) taxCarMap);
							// mili 2015-5-5 15:13:36  问题名单白名单 记录为 系统当前年  不应该收税 start
							List<SYJK_CCS_QGCLWTMDXX> wtmdList = (List<SYJK_CCS_QGCLWTMDXX>)taxCarMap.get("WTMD");
							if(wtmdList != null && !"".equals(wtmdList) && wtmdList.size() > 0){
								SYJK_CCS_QGCLWTMDXX wtmd = wtmdList.get(0);
								if(wtmd != null && !"".equals(wtmd)){
									if(DateUtil.getDIY("yyyy").equals(wtmd.getND()) && "W".equals(wtmd.getYCYYDM())){
										newTax.setTaxAmount(new TaxAmount_Type());
									}
								}
							}
							// mili 2015-5-5 15:13:36  问题名单白名单 记录为 系统当前年  不应该收税 end
							lutc.endLog();
						}
					} catch (Exception e) {
						newTax = null ;
						e.printStackTrace();
					}
					
					// 车船税 数据类型 为空
					if(newTax == null){
						return taxqueryDao.CC_Object(basequeryreqinfo,new Tax_Type(),new TaxDealCode_Type(),ChkUtil.CHK_8000, new Car_Id_No());
					}
					// 生成车船税交易码 
					TaxDealCode_Type dealCode = TaxCarCount.getTaxqueryNo("1", basequeryreqinfo.getCompanyCode(),basequeryreqinfo.getAreaCode(), "A");
					// 车辆标识信息（机动车序列号、车辆匹配规则ID）
					Car_Id_No cin= (Car_Id_No) taxCarMap.get("CIN");
					_BaseQueryResInfo = taxqueryDao.CC_Object(basequeryreqinfo,newTax,dealCode, ChkUtil.CHK_0000, cin);
					// 封装写库对象和返回对象（查询请求入参,车船税信息,车船税查询码,匹配规则,算税标志）
					
					basequeryreqinfo.getVehicleInfo().setFirstRegisterDate((String)taxCarMap.get("bxcdri"));   // mili 2016年1月22日16:42:00
					
					List<Object> list_object = taxqueryDao.Packaging(basequeryreqinfo, _BaseQueryResInfo.getTaxInfo(), dealCode.getTaxDealCode_Type(), cin.getCarSerialNo(),cin.getCarMatchId(),isInsert);
					// 写库
					boolean flage = taxqueryDao.Write_Bank(list_object);  
					if (flage) {
						// 车船税合计金额  保留2为小数
						_BaseQueryResInfo.getTaxInfo().setTaxAmount(Double_Decimal.double_Amout(_BaseQueryResInfo.getTaxInfo().getTaxAmount()));
						// 写库成功返回(车船税信息,返回码,车船税查询码)
//					_BaseQueryResInfo = taxqueryDao.CC_Object(basequeryreqinfo,newTax,dealCode, ChkUtil.CHK_0000, cin);
					} else {
						// 写库失败返回(车船税信息,返回码,车船税查询码)
						_BaseQueryResInfo = taxqueryDao.CC_Object(basequeryreqinfo,newTax,new TaxDealCode_Type(),ChkUtil.CHK_8000, cin);
					}
				} else {
					// 入参验证失败，直接返回(车船税信息,返回码,车船税查询码)
					_BaseQueryResInfo = taxqueryDao.CC_Object(basequeryreqinfo,new Tax_Type(),new TaxDealCode_Type(),reqReturnFlag, new Car_Id_No());
				}
			}else{
				// 车船税标签是空的情况下 返回空
				_BaseQueryResInfo = new BaseQueryResInfo();
			}
		} else {
			// 用户登录失败,直接返回,(车船税信息,返回码,车船税查询码)
          _BaseQueryResInfo = taxqueryDao.CC_Object(basequeryreqinfo,new Tax_Type(), new TaxDealCode_Type(),loginFlag, new Car_Id_No());

		}
		
		// 事后处理（冲正、）
//		HashMap mmap = new HashMap();
//		mmap.put("Req", basequeryreqinfo);
//		mmap.put("Res", _BaseQueryResInfo);
//		mmap.put("ServiceURI", "http://192.168.1.51:8888/taxcarship/services/TaxQueryService");
//		mmap.put("ServiceURI", "http://localhost:7080/taxcarship/services/TaxQueryService");
//      AfterServiceDealer asd = new AfterServiceDealer(mmap);
		logUtil.endLog();
		return _BaseQueryResInfo;
	}

}

