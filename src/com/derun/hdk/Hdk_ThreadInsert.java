package com.derun.hdk;

import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;

/**
 * @author MILI
 * @描述：线程异步插入数据库carinfo
 * @Date:2015-4-2 16:02:03
 * */
public class Hdk_ThreadInsert extends Thread{
	public HDK_DAO hdk_dao = new HDK_DAO();
	public String acerCode = null ;
	public String flag = null ;
	public SYJK_CCS_CCSCXCCJBXX ccscxccjb = new SYJK_CCS_CCSCXCCJBXX();
	public Hdk_ThreadInsert(SYJK_CCS_CCSCXCCJBXX ccscxccjb,String acerCode,String flag){
		this.ccscxccjb = ccscxccjb ;
		this.acerCode = acerCode ;
		this.flag = flag ;
	}
	@Override
	public void run() {
		hdk_dao.Insert_carinfo(ccscxccjb,acerCode,flag);
	}
}
