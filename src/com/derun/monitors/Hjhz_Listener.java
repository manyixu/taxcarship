package com.derun.monitors;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.derun.model.po.SYJK_CCS_RKMX;
/**
 * @author MILI
 * @time 2014-7-24 9:19:14
 * @描述：合计汇总 初始化
 * */
public class Hjhz_Listener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		List<SYJK_CCS_RKMX> list_rkmx = Ywjk_Sql.getYwjkSql();
		for(SYJK_CCS_RKMX rkmx : list_rkmx){
			rkmx = Ywmx_Assist.setRkmx_bx(rkmx);	// 汉化 保险公司名称
//			rkmx = Ywmx_Assist.setRkmx_ns(rkmx);	// 汉化 纳税类型名称
//			rkmx = Ywmx_Assist.set_HPZL(rkmx);		// 汉化 号牌种类
//			rkmx = Ywmx_Assist.set_JS(rkmx);		// 汉化 计算单位代码
			rkmx = Ywmx_Assist.Filter(rkmx);		
			rkmx.setTAXSTARTDATE(rkmx.getTAXSTARTDATE().substring(0, 10));
			rkmx.setTAXENDDATE(rkmx.getTAXENDDATE().substring(0, 10));
		}
		List_Monitor.init_Ywjk(list_rkmx);
		List_Monitor.init_Hjhz(Hjhz_Sql.getHjhzSql());  	// 初始化合计汇总
		new Timer_Start().Start_DateRun();	// 启动定时器
	}
}
