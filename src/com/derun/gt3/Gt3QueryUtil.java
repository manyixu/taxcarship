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
		SYJK_CCS_WSDJXX wsdj = null;
		Gt3DBUtil gt3DBUtil = Gt3DBUtil.getInstance();
		String conntype = Gt3DBUtil.proMap.get("conntype");//金三系统数据库连接方式 (1=直连, 2=DBLink)
		String wsSql01="";
		String wsSql02="";
		String wsSql03="";
		String wsSql04="";
		if(conntype!=null && "1".equals(conntype)){
			Gt3SQLNoDblink gt3sql = new Gt3SQLNoDblink(wsIn);
			wsSql01 = gt3sql.sb1.toString();
			wsSql02 = gt3sql.sb2.toString();
			wsSql03 = gt3sql.sb3.toString();
			wsSql04 = gt3sql.sb4.toString();
		}else{
			Gt3SQL gt3sql = new Gt3SQL(wsIn);	
			wsSql01 = gt3sql.sb1.toString();
			wsSql02 = gt3sql.sb2.toString();
			wsSql03 = gt3sql.sb3.toString();
			wsSql04 = gt3sql.sb4.toString();
		}
		SSRS ssrs = gt3DBUtil.Query(wsSql01);	
		boolean ssrsFull = false;
		if(ssrs!=null && ssrs.MaxRow>0){
			ssrsFull = true;
		}else{
			ssrs = gt3DBUtil.Query(wsSql02);	
			if(ssrs!=null && ssrs.MaxRow>0){
				ssrsFull = true;
			}else{
				ssrs = gt3DBUtil.Query(wsSql03);	
				if(ssrs!=null && ssrs.MaxRow>0){
					ssrsFull = true;
				}else{
					ssrs = gt3DBUtil.Query(wsSql04);	
					if(ssrs!=null && ssrs.MaxRow>0){
						ssrsFull = true;
					}
				}
			}
		}
		if(ssrsFull){
			wsdj = new SYJK_CCS_WSDJXX();
			try {
				wsdj.setID(ssrs.GetText(1, 1));
				wsdj.setNSRSBH(ssrs.GetText(1, 2));//c.nsrsbh
//			wsdj.setLOGINNAME("");
//			wsdj.setREVENUECODE("");
//			wsdj.setSJCJFS("");
				wsdj.setCCSBDM(ssrs.GetText(1, 3));//VIN车架号
				wsdj.setCLHPHM(ssrs.GetText(1, 4));//hphm号牌号码
				wsdj.setCLHPZL(ssrs.GetText(1, 5));
				wsdj.setCLLX(ssrs.GetText(1, 6));
				wsdj.setSYRMC(ssrs.GetText(1, 7));//mc所有人名称
				wsdj.setSKSSKSRQ(sdfs.parse(ssrs.GetText(1, 8)));//skssqq税款所属起期
				wsdj.setSKSSJSRQ(sdfs.parse(ssrs.GetText(1, 9)));//sksszq税款所属止期
				wsdj.setJNJE(Double.parseDouble(ssrs.GetText(1, 10)));//je缴纳金额
				wsdj.setWSRQ(sdfs.parse(ssrs.GetText(1, 11)));//wsrq完税日期
				wsdj.setDZDWDM(ssrs.GetText(1, 12));//代征单位代码
				wsdj.setWSPZH(ssrs.GetText(1, 13));//wspzh完税凭证号***
				wsdj.setSJCJRQ(new Date());//sjcjrq数据采集日期
				wsdj.setKJSWJGDM(ssrs.GetText(1, 12));//swjgdm开具凭证税务机关代码*** （认为与完税机关代码相同）
				wsdj.setZGSWJGMC("GT3ZGSWJGMC");//主管税务机关名称***（需要关联字典表）
//			wsdj.setDZDWMC("");
//			wsdj.setSKLX("");
//			wsdj.setZSPM("");
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
			
		}
		
		log.debug("gt3 wsxx query endtime: "+sdfp.format(new Date()));
		long end = Long.parseLong(sdf.format(new Date()));
		log.debug("gt3 wsxx query whole time cost: "+String.valueOf(end-start)+" ms. result:matched?" + String.valueOf(ssrs!=null&&ssrs.MaxRow>0));
		return wsdj;
	}
	
	private void getJmxx() {
		// TODO Auto-generated method stub

	}
	
	private void getTsxx() {
		// TODO Auto-generated method stub

	}
	
}