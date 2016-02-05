package com.derun.common.nopaytax;

import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.model.po.Query_ChangeQurey;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_RKMX_QS;
import com.derun.model.po.SYJK_CCS_WSDJXX;
/**
 * @author MILI
 * @time 2014-4-14 14:40:30
 * @描述：查找有关于欠税信息
 * */
public class Nopaytax {
	/**
	 * @author MILI
	 * @time 2014-4-14 
	 * @描述：查询在开始算欠税年以后的欠税记录
	 * @入参：请求信息对象入参  BaseQueryReqInfo   +  开始算欠税的年份  Newcaryear
	 * @出参：SYJK_CCS_RKMX_QS[]
	 * */
	public SYJK_CCS_RKMX_QS[] getNoPayTax1(Query_ChangeQurey basequeryreqinfo,SYJK_CCS_RKMX rkmx,String ServiceType,SYJK_CCS_WSDJXX wsdj) {
		int Newcaryear = 2000 ;
		if(wsdj != null ){
			Newcaryear = Integer.parseInt((String) DateUtil.getStringDate(wsdj.getSKSSJSRQ(),null).substring(0, 4)) + 1 ;
		} else if("02".equals(ServiceType)){
			Newcaryear = Integer.parseInt(CfgLoader.getConfigValue("SysCode_NEW", "newcaryear"));    // 需得到数据库配置的值
		} else if(rkmx != null){
			Newcaryear = Integer.parseInt(rkmx.getPAYDATE());
		} else {
			Newcaryear = Integer.parseInt(CfgLoader.getConfigValue("SysCode_NEW", "newcaryear"));    // 需得到数据库配置的值
		}
		// 车辆初登日期
		int djrq = Integer.parseInt(basequeryreqinfo.getVehicleInfo().getFirstRegisterDate().substring(0, 4));
		String firstDate = null ;		// 车辆初登日期     YYYY
		String firstMon = null ;		// 车辆初登日期 	YYYY-MM-DD
		String currDate = null ;		// 投保查询年份	YYYY
		if (djrq >= Newcaryear) {
			firstDate = basequeryreqinfo.getVehicleInfo().getFirstRegisterDate().substring(0, 4);
			firstMon = basequeryreqinfo.getVehicleInfo().getFirstRegisterDate().substring(0, 10);
		} else {
			firstDate = Newcaryear + "";
			firstMon = Newcaryear + "-01-01";
		}
		// 系统当前时间  YYYY-MM-DD
//		String chucanstart = DateUtil.getStringDate(new Date(),null);
		String chucanstart = DateUtil.getStringDate(basequeryreqinfo.getInsureStartDate(),null);
		currDate = chucanstart.substring(0, 4);		// 投保查询年份
		int tb = Integer.parseInt(currDate);		// 系统当前日期转化成整形
		int fst = Integer.parseInt(firstDate);		// 初始登记年份转化成整形
		// 计算欠税总年度
		int tatilYear = tb - fst ;	
		if(tatilYear < 0){
			tatilYear = 0 ;
		}
		SYJK_CCS_RKMX_QS[] listTime = new SYJK_CCS_RKMX_QS[tatilYear];
		SYJK_CCS_RKMX_QS rkmx_qs = null ;
		// 车辆初登日期 和 系统日期不是同一日期
		if (tatilYear > 0){	// 判断欠税总年度是否大于零
			for(int i = 0 ; i < tatilYear ; i++) {
				rkmx_qs = new SYJK_CCS_RKMX_QS();
				if (i == 0){					// 第一年度欠税
					rkmx_qs.setTAXSTARTDATE(DateUtil.getStringDate(firstMon,null));					//	逾期始期
					rkmx_qs.setTAXENDDATE(DateUtil.getStringDate(firstDate + "-12-31",null));		//	逾期止期
//					rkmx_qs.setEXCEEDDATE(DateUtil.getStringDate(firstDate + "","yyyy"));			//	逾期时间
//					rkmx_qs.setEXCEEDDAYSCOUNT(DateUtil.getTwoDates(rkmx_qs.getTAXENDDATE(), rkmx_qs.getTAXSTARTDATE())) ;	//	逾期天数
				} else if (i == tatilYear - 1){	// 最后一年度欠税
					rkmx_qs.setTAXSTARTDATE(DateUtil.getStringDate(tb - 1 + "-01-01",null));		//	逾期始期
					rkmx_qs.setTAXENDDATE(DateUtil.getStringDate(tb - 1 + "-12-31",null));			//	逾期止期
//					rkmx_qs.setEXCEEDDATE(DateUtil.getStringDate(tb - 1 + "","yyyy"));				//	逾期时间
//					rkmx_qs.setEXCEEDDAYSCOUNT(DateUtil.getTwoDates(rkmx_qs.getTAXENDDATE(), rkmx_qs.getTAXSTARTDATE())) ;	//	逾期天数
				} else {		
					int  firs = 0 ;			// 中间年度欠税
					if(firstMon != null && firstMon.length() > 4 ){
						firs = Integer.valueOf(firstMon.substring(0, 4));
					}
					rkmx_qs.setTAXSTARTDATE(DateUtil.getStringDate(firs + i + "-01-01",null));	//	逾期始期
					rkmx_qs.setTAXENDDATE(DateUtil.getStringDate(firs + i + "-12-31",null));	//	逾期止期
//					rkmx_qs.setEXCEEDDATE(DateUtil.getStringDate(firstMon + i + "","yyyy"));			//	逾期时间
//					rkmx_qs.setEXCEEDDAYSCOUNT(DateUtil.getTwoDates(rkmx_qs.getTAXENDDATE(), rkmx_qs.getTAXSTARTDATE())) ;	//	逾期天数
				}
				listTime[i] = rkmx_qs ;
			}
		}
		return listTime;
	}
}
