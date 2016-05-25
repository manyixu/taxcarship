package com.derun.gt3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.derun.common.db.SSRS;
import com.derun.model.po.SYJK_CCS_WSDJXX;

public class Gt3QueryUtil {
	
	private static Logger log = Logger.getLogger(Gt3QueryUtil.class);
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	static SimpleDateFormat sdfp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	static SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		byte[] testMaxMem = new byte[1000000000];
//		log.info(testMaxMem.length);
	}
	
	/**
	 * 查询完税信息
	 * @param wsIn
	 * @return
	 * @throws ParseException 
	 */
	public static SYJK_CCS_WSDJXX getWsxx(Gt3InPara wsIn) {
		long start = Long.parseLong(sdf.format(new Date()));
		log.debug("gt3 wsxx query starttime: "+sdfp.format(new Date()));
		SYJK_CCS_WSDJXX wsdj = new SYJK_CCS_WSDJXX();
		
		Gt3DBUtil gt3DBUtil = Gt3DBUtil.getInstance();
		
		String sql = "select SWSX_DM from DJ_ZRR where 1=1 and b.clsbdh = '"+wsIn.getVin()+"'";
		SSRS ssrs = gt3DBUtil.Query(sql);	
		
		try {
			wsdj.setID("");
			wsdj.setNSRSBH(ssrs.GetText(1, 1));//c.nsrsbh
			wsdj.setLOGINNAME("");
			wsdj.setREVENUECODE("");
			wsdj.setSJCJRQ(sdfd.parse(ssrs.GetText(1, 2)));//sjcjrq数据采集日期
			wsdj.setSJCJFS("");
			wsdj.setCLHPHM(ssrs.GetText(1, 3));//hphm号牌号码
			wsdj.setCLHPZL("");
			wsdj.setCLLX("");
			wsdj.setSYRMC(ssrs.GetText(1, 4));//mc所有人名称
			wsdj.setKJSWJGDM(ssrs.GetText(1, 5));//swjgdm开具凭证税务机关代码
			wsdj.setZGSWJGMC("");
			wsdj.setCCSBDM(ssrs.GetText(1, 6));//VIN车架号
			wsdj.setDZDWMC("");
			wsdj.setDZDWDM(ssrs.GetText(1, 7));//代征单位代码
			wsdj.setWSPZH(ssrs.GetText(1, 8));//wspzh完税凭证号
			wsdj.setSKSSKSRQ(sdfs.parse(ssrs.GetText(1, 9)));//skssqq税款所属起期
			wsdj.setSKSSJSRQ(sdfs.parse(ssrs.GetText(1, 10)));//sksszq税款所属止期
			wsdj.setJNJE(Double.parseDouble(ssrs.GetText(1, 11)));//je缴纳金额
			wsdj.setWSRQ(sdfs.parse(ssrs.GetText(1, 12)));//wsrq完税日期
			wsdj.setSKLX("");
			wsdj.setZSPM("");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			log.debug("gt3 wsxx all matched rows count: "+ssrs.MaxRow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("gt3 wsxx query endtime: "+sdfp.format(new Date()));
		long end = Long.parseLong(sdf.format(new Date()));
		log.debug("gt3 wsxx query whole time cost: "+String.valueOf(end-start)+" ms");
		return wsdj;
	}
	
	private void getJmxx() {
		// TODO Auto-generated method stub

	}
	
	private void getTsxx() {
		// TODO Auto-generated method stub

	}

}