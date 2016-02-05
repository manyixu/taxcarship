package com.derun.taxconfirm.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.derun.beans.BaseConfirmReqInfo;
import com.derun.beans.BaseConfirmResInfo;
import com.derun.beans.TaxCarCount;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.car.TaxCarType;
import com.derun.common.chk.BaseConfirmChk;
import com.derun.common.init.CfgLoader;
import com.derun.common.match.TaxComfirm_Consistency;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.LogUtil;
import com.derun.hdk.HDK_DAO;
import com.derun.hdk.Hdk_ThreadInsert;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
import com.derun.model.po.SYJK_CCS_CCSXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.monitors.List_Monitor;
import com.derun.taxconfirm.dao.impl.C_SYJK_CCS_CCSCXCCJBXX;
import com.derun.taxconfirm.dao.impl.C_SYJK_CCS_CCSXX;
import com.derun.taxconfirm.dao.impl.TaxConfirmDao;
import com.derun.taxconfirm.service.TaxConfirmService;

/**
 * 投保确认服务
 * 
 * @author
 */
public class TaxConfirmServiceImpl implements TaxConfirmService {
	List_Monitor list_mon = new List_Monitor();
	// 投保确认用户名密码和入参有效性验证
	BaseConfirmChk baseConfirmChk = new BaseConfirmChk();
	// 投保确认 封装写库
	TaxConfirmDao taxConfirmDao = new TaxConfirmDao();
	// 车辆信息、车船税信息匹配
	TaxCarType taxCarType = new TaxCarType();
	HDK_DAO hdk_dao = new HDK_DAO();
	// 投保确认效验
	TaxComfirm_Consistency taxComfirm_Consistency = new TaxComfirm_Consistency();
	LogUtil logUtil = new LogUtil("投保确认服务");

	/**
	 * 投保确认主线
	 */
	@Override
	public BaseConfirmResInfo taxConfrim(BaseConfirmReqInfo baseconfirmReqinfo) {
		// 船税确认服务--请求信息对象出参
		BaseConfirmResInfo baseConfirmResInfo = null;
		logUtil.startLog();
		// TODO Auto-generated method stub
		// 用户合法性校验
		String reqReturnFlage = ChkUtil.CHK_0000;
		List<Object> list = new ArrayList<Object>();
		reqReturnFlage = baseConfirmChk.N_P_checking(baseconfirmReqinfo);
		if (ChkUtil.CHK_0000.equals(reqReturnFlage)) {
			// 用户校验合法,进行入参有效性验证
			reqReturnFlage = baseConfirmChk
					.Join_valid(baseconfirmReqinfo, null);
			if (ChkUtil.CHK_0000.equals(reqReturnFlage)) {
				// 入参有效性验证成功,判断是否补传
				// 公司代码（4位）
				String acompany = baseconfirmReqinfo.getCompanyCode();
				// 国标区域代码（6位）
				String aacre = baseconfirmReqinfo.getAreaCode();
				// 车船税确认码
				TaxDealCode_Type taxConfirmNo = TaxCarCount.getTaxqueryNo("1",
						acompany, aacre, "B");
				TaxDealCode_Type taxConfirmNoprin = new TaxDealCode_Type();
				// 补传标志( 1为补传，2为确认)
				String BCFlag = "2";
				if (baseconfirmReqinfo.getTaxConfirmNo() != null) {
					if (baseconfirmReqinfo.getTaxConfirmNo()
							.getTaxDealCode_Type() != null
							&& !"".trim().equals(
									baseconfirmReqinfo.getTaxConfirmNo()
											.getTaxDealCode_Type().trim())) {
						BCFlag = "1";
					}
				}

				if ("1".equals(BCFlag)) {
					String printNo = taxConfirmNo.getTaxDealCode_Type()
							.substring(0, 11)
							+ "G"
							+ taxConfirmNo.getTaxDealCode_Type()
									.substring(
											12,
											taxConfirmNo.getTaxDealCode_Type()
													.length());// 打印码 ;
					taxConfirmNoprin.setTaxDealCode_Type(printNo);
					taxConfirmNo = baseconfirmReqinfo.getTaxConfirmNo();
					// 为补传服务
					// 封装（投保确认入参、确认码、查询入参对象、打印码）
					// String printNo =
					// taxConfirmNo.getTaxDealCode_Type().substring(0,
					// 11)+"G"+taxConfirmNo.getTaxDealCode_Type().substring(12,
					// taxConfirmNo.getTaxDealCode_Type().length());//打印码
					list = taxConfirmDao.Packaging(baseconfirmReqinfo,
							taxConfirmNo, null, printNo);
					// 写库
					boolean flage = taxConfirmDao.Write_Bank(list);
					if (flage) {

						// 返回（返回码,车船税确认码,车船税打印码）
						baseConfirmResInfo = taxConfirmDao.CC_Object(
								ChkUtil.CHK_0000, taxConfirmNo, taxConfirmNo,
								baseconfirmReqinfo);
						// baseConfirmResInfo.setTaxConfirmNo(null); //
						// 2014-10-11 16:40:30 MILI 为什么要清空?
						// baseConfirmResInfo.setTaxPrintNo(null);
					} else {
						// 返回（返回码,车船税确认码,车船税打印码）
						baseConfirmResInfo = taxConfirmDao.CC_Object(
								ChkUtil.CHK_8000, null, null, null);
					}

				} else {
					C_SYJK_CCS_CCSXX c_ccsxx = new C_SYJK_CCS_CCSXX();
					SYJK_CCS_CCSXX ccsxx = c_ccsxx.getCcsxx(baseconfirmReqinfo
							.getTaxQueryNo().getTaxDealCode_Type());
					C_SYJK_CCS_CCSCXCCJBXX c_ccscxccjb = new C_SYJK_CCS_CCSCXCCJBXX();
					SYJK_CCS_CCSCXCCJBXX ccscxccjb = c_ccscxccjb
							.getQrccjbxx(baseconfirmReqinfo.getTaxQueryNo()
									.getTaxDealCode_Type()); // 查询出参
					String printNo = taxConfirmNo.getTaxDealCode_Type()
							.substring(0, 11)
							+ "G"
							+ taxConfirmNo.getTaxDealCode_Type()
									.substring(
											12,
											taxConfirmNo.getTaxDealCode_Type()
													.length());// 打印码
					taxConfirmNoprin.setTaxDealCode_Type(printNo);
					baseconfirmReqinfo.setCarMatchId(ccscxccjb.getCARMATCHID()); // 车辆匹配ID
					// 一致性校验（入参中车船信息与车船税信息表中的数据是否一致）
					reqReturnFlage = taxComfirm_Consistency.Consistency_N_O(
							baseconfirmReqinfo, ccsxx, ccscxccjb);
					if (ChkUtil.CHK_0000.equals(reqReturnFlage)) {// 一致性效验通过
						// 封装（投保确认入参、确认码、查询出参对象、打印码）
						list = taxConfirmDao.Packaging(baseconfirmReqinfo,
								taxConfirmNo, ccscxccjb, printNo);
						// 是否要插入数据库
						if ("1".equals(baseconfirmReqinfo
								.getINSURE_CONFORM_FLAG())) {
							// 写库
							boolean flage = taxConfirmDao.Write_Bank(list);
							if (flage) {
								// 新加 一个核定库总开关  mili 2015年8月26日15:57:57
								if("Y".equals(CfgLoader.getConfigValue("SysSwitch", "HDK_Flag").trim())){
									if ("Y".equals(CfgLoader.getConfigValue("SysSwitch", "InsertFlag").trim())) {
										if ("Y".equals(ccscxccjb.getISINSERT())) {
											// mili 2015-4-1 09:09:55 车辆信息插入核定库
											// start
											hdk_dao.Insert_carinfo(ccscxccjb,printNo,"CX"); // 同步插入
											// new Hdk_ThreadInsert(ccscxccjb,printNo).start(); // 异步插入
											// mili 2015-4-1 09:09:55 车辆信息插入核定库 end
										}
									}
								}
								// 数据监控
								list_mon.addMonitor((SYJK_CCS_RKMX) list.get(1));
								// 入库成功返回（返回码,车船税确认码,车船税打印码）
								baseConfirmResInfo = taxConfirmDao.CC_Object(reqReturnFlage, taxConfirmNo,taxConfirmNoprin, baseconfirmReqinfo);
							} else {
								// 入库失败返回（返回码,车船税确认码,车船税打印码）
								baseConfirmResInfo = taxConfirmDao.CC_Object(ChkUtil.CHK_8000, null, null, null);
							}
						} else {
							baseConfirmResInfo = taxConfirmDao.CC_Object(reqReturnFlage, taxConfirmNo,taxConfirmNoprin, baseconfirmReqinfo);
						}

					} else {
						// 一致性效验不过,返回（返回码,车船税确认码,车船税打印码）
						baseConfirmResInfo = taxConfirmDao.CC_Object(
								reqReturnFlage, null, null, null);
					}
				}

			} else {
				// 入参有效性判断失败,直接返回(返回码,车船税确认码,车船税打印码)
				baseConfirmResInfo = taxConfirmDao.CC_Object(reqReturnFlage,
						null, null, null);
			}
		} else {
			// 用户校验不合法,直接返回（返回码,车船税确认码,车船税打印码）
			baseConfirmResInfo = taxConfirmDao.CC_Object(reqReturnFlage, null,
					null, null);
		}
		// 事后处理（冲正、）
		// HashMap mmap = new HashMap();
		// mmap.put("Req", baseconfirmReqinfo);
		// mmap.put("Res", baseConfirmResInfo);
		// mmap.put("rkmx", list.get(1));
		// TaxConfirmServiceTraceThread asd = new
		// TaxConfirmServiceTraceThread(mmap);
		// Thread tt = new Thread(asd);
		// tt.start();
		// List<Object> list1 = new ArrayList<Object>();
		logUtil.endLog();
		return baseConfirmResInfo;
	}

}
