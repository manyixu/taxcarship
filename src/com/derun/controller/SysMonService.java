package com.derun.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.hyperic.sigar.SigarException;

import com.derun.common.mon.SysInfoUtil;

/**
 * 业务监听控制服务层
 * @author wbzhao
 *
 */
public class SysMonService {
	
	public static String sysInfoJson = null;
	SimpleDateFormat sdf = new SimpleDateFormat("HH24:mm:ss:SSS");
	
	public SysMonService(int seconds){
		Timer timer = new Timer();
	 	timer.schedule(new MyTask(), 0, seconds*1000);//1秒后执行，每隔seconds秒执行一次
	 	//System.out.println(SysMonService.sysInfoJson);
	}
	
	
	
	/**
	 * 定时刷新服务器信息
	 * @author wbzhao
	 */
	class MyTask extends TimerTask {
		@Override
		public void run() {

			sysInfoJson = SysInfoUtil.getSysInfoJson();//sdf.format(new Date())
			
			
		}
		
	}
	
}
