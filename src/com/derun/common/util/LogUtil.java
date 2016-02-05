package com.derun.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class LogUtil {

	private static Logger log = Logger.getLogger(LogUtil.class);
	private long startTime ;
	private String taskName ;
	
	public LogUtil(){
		startTime = 0L;
	}
	
	public LogUtil(String taskName){
		startTime = 0L;
		this.taskName = taskName;
		this.startLog();
	}

	public void startLog(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd : HH:mm:ss:SSS");
		Date now = new Date();
		startTime = System.currentTimeMillis();
		log.debug("【"+taskName+"】开始计时："+sdf.format(now));
	}
	
	public void endLog(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd : HH:mm:ss:SSS");
		Date now = new Date();
		long endTime = System.currentTimeMillis();		
		long costTime = endTime - startTime;
		log.debug("【"+taskName+"】计时结束："+sdf.format(now));
		log.debug("【"+taskName+"】共计耗时：【"+ costTime +"】 毫秒");
	}

}
