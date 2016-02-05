package com.derun.monitors;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import com.derun.common.util.DateUtil;
/**
 * @author MILI
 * @time 2014-7-24 9:26:00
 * @描述：定时器
 * */
public class Timer_Start{
	static String year = null ;
	/**每天定时执行任务*/
	public void Start_DateRun() {
		year = DateUtil.getStringDate(new Date(),"yyyy");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 1);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				Hjhz_VO hjhz = List_Monitor.getHjhz();
				hjhz.setDate_Money(0.0);
				hjhz.setDate_Number(0);
				String date = DateUtil.getStringDate(new Date(),"dd");
				if("01".equals(date) || "1".equals(date)){		// 每月一号 执行
					hjhz.setMonth_Money(0.0);
					hjhz.setMonth_Number(0);
				}
				String _year = DateUtil.getStringDate(new Date(),"yyyy");
				if(!_year.equals(year)){		// 跨年时执行
					year = _year ;
					hjhz.setYear_Money(0.0);
					hjhz.setYear_Number(0);
				}
				List_Monitor.init_Hjhz(hjhz);
			}
		}, cal.getTime(),  24 * 60 * 60 * 1000);	//  24小时以后执行
	}
	/**每月定时执行任务*/
	public void Start_MonthRun() {
		
	}
	/**每年定时执行任务*/
	public void Start_YYYYRun() {
		
	}
}
