package com.derun.taxreconciliation.service.impl;

import java.util.List;
import com.derun.beans.ReconciliationReqInfo;
import com.derun.beans.ReconciliationResInfo;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxCarCount;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.chk.ReconciliationChk;
import com.derun.common.util.ChkUtil;
import com.derun.common.util.LogUtil;
import com.derun.taxreconciliation.check.TaxReconciliationChk;
import com.derun.taxreconciliation.service.TaxReconciliationService;
import com.derun.taxreconciliation.vo.TaxReconciliationBeans;
import com.derun.taxreconciliation.vo.TaxReconciliationPackage;

/**
 * @author 郑艳英
 * 
 * @date 2014-5-14
 * 
 *       对帐服务
 * @version
 */
public class TaxReconciliationServiceImpl implements TaxReconciliationService {

	@Override
	public ReconciliationResInfo taxReconciliation(
			ReconciliationReqInfo reconciliationReqInfo) {
		LogUtil logUtil = new LogUtil("对账服务");
		logUtil.startLog();
		ReconciliationResInfo reconciliationResInfo = null;
		// 对账用户名密码和入参有效性验证
		ReconciliationChk reconciliationChk = new ReconciliationChk();
		// 封装返回类
		TaxReconciliationPackage taxReconciliationPackage = new TaxReconciliationPackage();
		TaxReconciliationChk taxReconciliationChk = new TaxReconciliationChk();
		// 用户有效性校验
		String loginChk = reconciliationChk.N_P_checking(reconciliationReqInfo);
		if (ChkUtil.CHK_0000.equals(loginChk)) {
			// 用户名有效,生成交易码
			TaxDealCode_Type taxDealCodeType = TaxCarCount.getTaxqueryNo("1",
					reconciliationReqInfo.getCompanyCode(),
					reconciliationReqInfo.getAreaCode(), "E");
			// 确认码数组
			int taxConfirmNoLength = reconciliationReqInfo.getTaxConfirmNo() == null ? 0
					: reconciliationReqInfo.getTaxConfirmNo().length;
			if (taxConfirmNoLength != 0) {
				// 对帐
				TaxReconciliationBeans taxReconciliationBeans = taxReconciliationChk
						.taxReconciliation(reconciliationReqInfo
								.getCheckingType(), reconciliationReqInfo
								.getTaxConfirmNo());
				String returnCode = taxReconciliationBeans.getReturnCode();
				if (!"6".equals(returnCode) && !"0".equals(returnCode)) {
					List<TaxAmount_Type> listType = taxReconciliationBeans
							.getTaxAmoutType();
					reconciliationResInfo = taxReconciliationPackage
							.resParameter(taxDealCodeType,
									reconciliationReqInfo.getCheckingType(),
									listType, returnCode);
					// 2014-11-18修改对账超时
					// 封装
					// List<Object> list =
					// taxReconciliationPackage.FZ(reconciliationReqInfo,
					// reconciliationResInfo,taxDealCodeType);
					// // 写库
					// boolean flag = taxReconciliationPackage.saveReqRes(list);
					// if (flag == false) {
					// reconciliationResInfo =
					// taxReconciliationPackage.resParameter(null, null,
					// null,ChkUtil.CHK_8000);
					// }
				} else {
					// 对帐类型无效,返回0,6数据库没有查到
					String _returnCode = "0";
					if ("6".equals(returnCode)) {
						_returnCode = "6";
					}
					reconciliationResInfo = taxReconciliationPackage
							.resParameter(null, null, null, _returnCode);
				}
			}
		} else {
			// 用户名无效,返回
			reconciliationResInfo = taxReconciliationPackage.resParameter(null,
					null, null, loginChk);
		}

		// 事后处理（冲正、）
		// HashMap mmap = new HashMap();
		// mmap.put("Req", reconciliationReqInfo);
		// mmap.put("Res", reconciliationResInfo);
		// TaxReconciliationServiceTraceThread asd = new
		// TaxReconciliationServiceTraceThread(mmap);
		// Thread tt = new Thread(asd);
		// tt.start();
		logUtil.endLog();
		return reconciliationResInfo;
	}

}
