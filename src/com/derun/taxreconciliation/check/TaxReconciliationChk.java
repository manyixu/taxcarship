package com.derun.taxreconciliation.check;

import java.util.List;

import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.util.LogUtil;
import com.derun.common.util.SBDZUtil;
import com.derun.taxreconciliation.dao.impl.TaxReconciliationServiceDao;
import com.derun.taxreconciliation.service.Thread.TaxReconciliationService_delete2;
import com.derun.taxreconciliation.service.Thread.TaxReconciliationService_detele4;
import com.derun.taxreconciliation.vo.TaxReconciliationBeans;

/**
 * 
 * @author 郑艳英 对帐类型校验
 */
public class TaxReconciliationChk {

	TaxReconciliationServiceDao taxReconciliationServiceDao = new TaxReconciliationServiceDao();

	// 根据不同的对帐类型进行对帐(对帐类型,交易码数组)
	public TaxReconciliationBeans taxReconciliation(String checkType,
			TaxDealCode_Type[] taxDealCodeType) {
		List<TaxAmount_Type> list = null;
		TaxReconciliationBeans taxReconciliationBeans = new TaxReconciliationBeans();

		// 对帐类型为1(车船税查询对帐)
		if ("1".equals(checkType)) {
			list = taxReconciliationServiceDao
					.taxQueryReconciliation(taxDealCodeType);

			// 对帐类型为2(车船税确认对帐)
		} else if ("2".equals(checkType)) {
			int count = SBDZUtil.getCount();
			LogUtil logUtil = new LogUtil("保存对账服务临时表");
			logUtil.startLog();
			taxReconciliationServiceDao.saveConfirmNo(taxDealCodeType, count);
			logUtil.endLog();
			LogUtil logUtil1 = new LogUtil("查询入库明细和临时表");
			logUtil1.startLog();
			list = taxReconciliationServiceDao
					.taxConfirmNoReconciliation(count);
			logUtil1.endLog();
			LogUtil logUtil2 = new LogUtil("删除对账服务临时表");
			logUtil2.startLog();
//			taxReconciliationServiceDao.deleteConfirmNo(count);
			//线程删除数据 MILI 2014-11-27 11:27:29
			new TaxReconciliationService_delete2(count).start();
			logUtil2.endLog();
			// 对帐类型为3(车船税变更查询对帐)
		} else if ("3".equals(checkType)) {
			list = taxReconciliationServiceDao
					.taxChangeQqueryReconciliation(taxDealCodeType);
			// 对帐类型为4(车船税变更确认对帐)
		} else if ("4".equals(checkType)) {
			int count = SBDZUtil.getCount();
			taxReconciliationServiceDao.saveConfirmNo(taxDealCodeType, count);
			list = taxReconciliationServiceDao
					.taxChangeConfirmReconciliation(count);
//			taxReconciliationServiceDao.deleteConfirmNo(count);
			//线程删除数据 MILI 2014-11-27 11:27:29
			new TaxReconciliationService_detele4(count).start();
		} else {
			// 对帐类型不对
			taxReconciliationBeans.setReturnCode("0");
			return taxReconciliationBeans;
		}
		if (list.size() > 0) {
			taxReconciliationBeans.setReturnCode("1");
		} else {
			taxReconciliationBeans.setReturnCode("6");
		}
		taxReconciliationBeans.setTaxAmoutType(list);

		return taxReconciliationBeans;
	}
}
