package com.derun.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	// private final String pattern = "yyyy-MM-dd";
	// private final String pattern1 = "yyyyMMdd";
	// private SimpleDateFormat df;
	// private SimpleDateFormat df1;
	// // @Constructor
	// public DateUtil()
	// {
	// df = new SimpleDateFormat(pattern);
	// df1 = new SimpleDateFormat(pattern1);
	// }

	/**
	 * 由给定日期字符串获取格式日期(年/月/日)
	 * 
	 * @param strYear
	 *            String 年
	 * @param strMonth
	 *            String 月
	 * @param strDay
	 *            String 日
	 * @return String 格式日期：年/月/日
	 */
	public static String getDate(String strYear, String strMonth, String strDay) {
		String strReturn = "";
		int intYear = 0;
		int intMonth = 0;
		int intDay = 0;
		if ((strYear != null) && (strMonth != null) && (strDay != null)
				&& (strYear.trim().length() > 0)
				&& (strMonth.trim().length() > 0)
				&& (strDay.trim().length() > 0)) {
			try {
				intYear = new Integer(strYear).intValue();
				intMonth = new Integer(strMonth).intValue();
				intDay = new Integer(strDay).intValue();
			} catch (Exception exception) {
				return strReturn;
			}

			if ((intYear <= 0) || (intMonth <= 0) || (intMonth > 12)
					|| (intDay <= 0) || (intDay > 31)) {
				strReturn = "";
			} else {
				strReturn = "" + intYear;

				if (intMonth < 10) {
					strReturn += StrTool.DATEDELIMITER + "0" + intMonth;
				} else {
					strReturn += StrTool.DATEDELIMITER + intMonth;
				}

				if (intDay < 10) {
					strReturn += StrTool.DATEDELIMITER + "0" + intDay;
				} else {
					strReturn += StrTool.DATEDELIMITER + intDay;
				}
			}
		}
		return strReturn;
	}

	/**
	 * 获得系统日期(年/月/日)
	 * 
	 * @return String 格式日期：年/月/日
	 */
	public static String getDate() {
		String strReturn = "";
		int intYear = Calendar.getInstance().get(Calendar.YEAR);
		int intMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int intDate = Calendar.getInstance().get(Calendar.DATE);
		strReturn = "" + intYear;

		if (intMonth < 10) {
			strReturn += StrTool.DATEDELIMITER + "0" + intMonth;
		} else {
			strReturn += StrTool.DATEDELIMITER + intMonth;
		}

		if (intDate < 10) {
			strReturn += StrTool.DATEDELIMITER + "0" + intDate;
		} else {
			strReturn += StrTool.DATEDELIMITER + intDate;
		}
		return strReturn;
	}

	/**
	 * 由给定日期字符串获取格式日期(年/月)
	 * 
	 * @param strYear
	 *            String 年
	 * @param strMonth
	 *            String 月
	 * @return String 格式日期：年/月
	 */
	public static String getDate(String strYear, String strMonth) {
		String strReturn = "";
		int intYear = 0;
		int intMonth = 0;
		if ((strYear != null) && (strMonth != null)
				&& (strYear.trim().length() > 0)
				&& (strMonth.trim().length() > 0)) {
			intYear = new Integer(strYear).intValue();
			intMonth = new Integer(strMonth).intValue();
			if ((intYear <= 0) || (intMonth <= 0) || (intMonth > 12)) {
				strReturn = "";
			} else {
				strReturn = "" + intYear + StrTool.DATEDELIMITER + intMonth;
			}
		}
		return strReturn;
	}

	/**
	 * 获取日期值中的年份
	 * 
	 * @param strDate
	 *            String 日期（年/月/日）
	 * @return String 年
	 */
	public static String getYear(String strDate) {
		int intPosition = 0;
		String strReturn = "";
		int intYear = 0;

		if ((strDate != null) && (strDate.trim().length() > 0)) {
			intPosition = StrTool.getPos(strDate, StrTool.DATEDELIMITER, 1);
			if (intPosition > 0) {
				strReturn = strDate.substring(0, intPosition);
				intYear = new Integer(strReturn).intValue();
				if ((intYear <= 0)) {
					strReturn = "";
				} else {
					strReturn = "" + intYear;
				}

				if ((intYear < 10) && (!strReturn.equals(""))) {
					strReturn = "0" + strReturn;
				}
			}
		}
		return strReturn;
	}

	/**
	 * 获取系统日期中的年份
	 * 
	 * @return String 年
	 */
	public static String getYear() {
		String strReturn = "";
		int intYear = Calendar.getInstance().get(Calendar.YEAR);
		strReturn = "" + intYear;
		return strReturn;
	}

	/**
	 * 获取日期值中的月份
	 * 
	 * @param strDate
	 *            String 日期
	 * @return String 月
	 */
	public static String getMonth(String strDate) {
		int intPosition1 = 0, intPosition2 = 0;
		String strReturn = "";
		int intMonth = 0;
		if ((strDate != null) && (strDate.trim().length() > 0)) {
			intPosition1 = StrTool.getPos(strDate, StrTool.DATEDELIMITER, 1);
			intPosition2 = StrTool.getPos(strDate, StrTool.DATEDELIMITER, 2);
			if ((intPosition1 > 0) && intPosition2 > intPosition1) {

				strReturn = strDate.substring(intPosition1 + 1, intPosition2);

				intMonth = new Integer(strReturn).intValue();
				if ((intMonth <= 0) || (intMonth > 12)) {
					strReturn = "";
				} else {
					strReturn = "" + intMonth;
				}

				if ((intMonth < 10) && (!strReturn.equals(""))) {
					strReturn = "0" + strReturn;
				}
			}
		}
		return strReturn;
	}

	/**
	 * 获取系统日期中的月份
	 * 
	 * @return String 月
	 */
	public static String getMonth() {
		String strReturn = "";
		int intMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (intMonth < 10) {
			strReturn = "0" + intMonth;
		} else {
			strReturn = "" + intMonth;
		}
		return strReturn;
	}

	/**
	 * 获取给定日期值中的天
	 * 
	 * @param strDate
	 *            String 日期
	 * @return String 天
	 */
	public static String getDay(String strDate) {
		int intPosition = 0;
		String strReturn = "";
		int intDay = 0;
		if ((strDate != null) && (strDate.trim().length() > 0)) {
			intPosition = StrTool.getPos(strDate, StrTool.DATEDELIMITER, 2);
			if (intPosition > 0) {

				strReturn = strDate.substring(intPosition + 1);

				intDay = new Integer(strReturn).intValue();

				if ((intDay <= 0) || (intDay > 31)) {
					strReturn = "";
				} else {
					strReturn = "" + intDay;
				}

				if ((intDay < 10) && (!strReturn.equals(""))) {
					strReturn = "0" + strReturn;
				}
			}
		}
		return strReturn;
	}

	/**
	 * 获取系统日期值中的天
	 * 
	 * @return String 天
	 */
	public static String getDay() {
		String strReturn = "";
		int intDate = Calendar.getInstance().get(Calendar.DATE);
		if (intDate < 10) {
			strReturn = "0" + intDate;
		} else {
			strReturn = "" + intDate;
		}

		return strReturn;
	}

	/**
	 * 获取系统时间值
	 * 
	 * @return String 小时: 分钟：秒
	 */
	public static String getTime() {
		return DateUtil.getHour() + ":" + DateUtil.getMinute() + ":"
				+ DateUtil.getSecond();
	}

	/**
	 * 获取系统时间值中的小时
	 * 
	 * @return String 小时
	 */
	public static String getHour() {
		String strReturn = "";
		int intHour = Calendar.getInstance().get(Calendar.HOUR)
				+ (Calendar.HOUR_OF_DAY + 1)
				* Calendar.getInstance().get(Calendar.AM_PM);
		if (intHour < 10) {
			strReturn = "0" + intHour;
		} else {
			strReturn = "" + intHour;
		}

		return strReturn;
	}

	/**
	 * 获取系统时间值中的分钟
	 * 
	 * @return String 分钟
	 */
	public static String getMinute() {
		String strReturn = "";
		int intMinute = Calendar.getInstance().get(Calendar.MINUTE);
		if (intMinute < 10) {
			strReturn = "0" + intMinute;
		} else {
			strReturn = "" + intMinute;
		}

		return strReturn;
	}

	/**
	 * 获取系统时间值中秒数
	 * 
	 * @return String 秒数
	 */
	public static String getSecond() {
		String strReturn = "";
		int intSecond = Calendar.getInstance().get(Calendar.SECOND);
		if (intSecond < 10) {
			strReturn = "0" + intSecond;
		} else {
			strReturn = "" + intSecond;
		}

		return strReturn;
	}

	/**
	 * 获取以"-"分隔日期值中的年份
	 * 
	 * @param strDate
	 *            String 日期
	 * @return String 年
	 */
	public static String getVisaYear(String strDate) {
		int intPosition = 0;
		String strReturn = "";
		int intYear = 0;

		if ((strDate != null) && (strDate.trim().length() > 0)) {
			intPosition = StrTool.getPos(strDate, StrTool.VISADATEDELIMITER, 1);
			if (intPosition > 0) {
				strReturn = strDate.substring(0, intPosition);
				intYear = new Integer(strReturn).intValue();
				if ((intYear <= 0)) {
					strReturn = "";
				} else {
					strReturn = "" + intYear;
				}

				if ((intYear < 10) && (!strReturn.equals(""))) {
					strReturn = "0" + strReturn;
				}
			}
		}
		return strReturn;
	}

	/**
	 * 获取以"-"分隔日期值中的月份
	 * 
	 * @param strDate
	 *            String 日期
	 * @return String 月
	 */
	public static String getVisaMonth(String strDate) {
		int intPosition1 = 0, intPosition2 = 0;
		String strReturn = "";
		int intMonth = 0;
		if ((strDate != null) && (strDate.trim().length() > 0)) {
			intPosition1 = StrTool
					.getPos(strDate, StrTool.VISADATEDELIMITER, 1);
			intPosition2 = StrTool
					.getPos(strDate, StrTool.VISADATEDELIMITER, 2);
			if ((intPosition1 > 0) && intPosition2 > intPosition1) {

				strReturn = strDate.substring(intPosition1 + 1, intPosition2);

				intMonth = new Integer(strReturn).intValue();
				if ((intMonth <= 0) || (intMonth > 12)) {
					strReturn = "";
				} else {
					strReturn = "" + intMonth;
				}

				if ((intMonth < 10) && (!strReturn.equals(""))) {
					strReturn = "0" + strReturn;
				}
			}
		}
		return strReturn;
	}

	/**
	 * 获取以"-"分隔日期值中的天
	 * 
	 * @param strDate
	 *            String 日期
	 * @return String 天
	 */
	public static String getVisaDay(String strDate) {
		int intPosition = 0;
		String strReturn = "";
		int intDay = 0;
		if ((strDate != null) && (strDate.trim().length() > 0)) {
			intPosition = StrTool.getPos(strDate, StrTool.VISADATEDELIMITER, 2);
			if (intPosition > 0) {

				strReturn = strDate.substring(intPosition + 1);

				intDay = new Integer(strReturn).intValue();

				if ((intDay <= 0) || (intDay > 31)) {
					strReturn = "";
				} else {
					strReturn = "" + intDay;
				}

				if ((intDay < 10) && (!strReturn.equals(""))) {
					strReturn = "0" + strReturn;
				}
			}
		}
		return strReturn;
	}

	/**
	 * 获取以"-"分隔日期值的中文表示
	 * 
	 * @param strDate
	 *            String 日期
	 * @return String YYYY年MM月DD日
	 */
	public static String getChnDate(String strDate) {
		String strReturn = getVisaYear(strDate) + "年" + getVisaMonth(strDate)
				+ "月" + getVisaDay(strDate) + "日";
		return strReturn;
	}

	/**
	 * 输入日期类型变量，返回日期字符串
	 * <p>
	 * <b>Example: </b>
	 * <p>
	 * <p>
	 * getString("Tue Oct 08 00:00:00 CST 2002") returns "2002-10-8"
	 * <p>
	 * 
	 * @param mDate
	 *            日期类型变量
	 * @return 日期字符串
	 */
	public static String getString(Date mDate, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String tString = null;
		if (mDate != null) {
			tString = sdf.format(mDate);
		}
		return tString;
	}

	/**
	 * @author MILI
	 * @time 2014年3月7日11:32:45
	 * @描述：输入String类型变量 返回 Date类型时间
	 * @param String 
	 * @return Date 年-月-日
	 * */
	public static Date getStringDate(String dateString,String yms) {
		Date date = null ;
		SimpleDateFormat sdf = null ;
		if(dateString != null && !"".equals(dateString)){
			if(yms != null && !"".equals(yms)){
				sdf = new SimpleDateFormat(yms);
			}else{
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			}
			try {
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("时间格式转换错误 DateUtil -> getStringDate()" + e);
			}
		}else{
			return date ;
		}
		return date ;
	}
	/**
	 * @author MILI
	 * @time 2014年3月7日11:32:45
	 * @描述：得到系统当前时间 
	 * @param  
	 * @return Date 
	 * */
	public static Date getDateDate() {
		Date date = new Date();
		if(date == null){
			date = new Date();
		}
		return date ;
	}
	/**
	 * @author MILI
	 * @time 2014年3月7日11:32:45
	 * @描述：验证日期格式是否正确 
	 * @param  String
	 * @return boolean 
	 * */
	public static boolean checkDate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = df.parse(date);
		} catch (Exception e) {
			return false;
		}
		String s1 = df.format(d);
		return date.equals(s1);
	}
	/**
	 * @author MILI
	 * @time 2014-3-17 15:37:04
	 * @描述：得到系统当前时间年份  
	 * @param  String
	 * @return Date 
	 * */
	public static String getDateYYYY(){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(date) ;
	}
	/**
	 * @author MILI
	 * @time 2014-3-17 15:37:04
	 * @描述：date 类型的时间格式转换为 String 类型的  默认转换为 YYYY-MM-DD 格式
	 * date 为null 的情况下  默认 是系统当前时间
	 * @param  String
	 * @return Date,String 
	 * */
	public static String getStringDate(Date date,String ymds){
		SimpleDateFormat df = null ;
		if(date == null){
			return null ;
//			date = new Date();
		}
		if(ymds != null && !"".equals(ymds)){
			df = new SimpleDateFormat(ymds);
		}else{
			df = new SimpleDateFormat("yyyy-MM-dd");
		}
		return df.format(date);
	}
	/**
	 * @author MILI
	 * @time 2014-3-18 9:21:58
	 * @描述：输入String类型变量 返回 Date类型时间
	 * @param String 
	 * @return Date 年-月-日
	 * */
	public static Date getStringDates(String dateString) {
		Date date = new Date();
		SimpleDateFormat sdf = null ;
		if(dateString != null && !"".equals(dateString)){
			int number = dateString.length();
			if(number >= 19){
				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			}else if(number >= 10){
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			}else if(number >= 4){
				sdf = new SimpleDateFormat("yyyy");
			}else{
				sdf = new SimpleDateFormat();
			}
			try {
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("时间格式转换错误 DateUtil -> getStringDates()" + e);
			}
		}else{
			return date ;
		}
		return date ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-15 10:34:54
	 * @描述：给出2个日期之间的天数
	 * @入参：Date 类型的时间 
	 * @出参：int
	 * */
	public static int getTwoDates(Date date1 ,Date date2){
		int dates = 0 ;
		if(date1 != null && date2 != null){
			long to = date1.getTime();
		    long from = date2.getTime();
		    dates = (int) ((to - from) / (1000 * 60 * 60 * 24)) ;
		}
		return dates ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-21 11:22:32
	 * @描述：自定义时间格式类型
	 * @入参：String 时间格式类型
	 * @出参：String
	 * */
	public static String getDIY(String diy){
		String dates = null ;
		if(diy != null && !"".equals(diy)){
			Date date = new Date();
			SimpleDateFormat sbf = new SimpleDateFormat(diy);
			dates = sbf.format(date);
		}else{
			
		}
		return dates ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-23 11:15:10
	 * @描述：自定义时间格式类型 默认是 YYYY-MM-DD
	 * @入参：String 时间格式类型  
	 * @出参：SimpleDateFormat
	 * */
	public static SimpleDateFormat getSim(String sim){
		SimpleDateFormat sdf = null;
		if(sim != null && !"".equals(sim)){
			sdf = new SimpleDateFormat(sim);
		}else{
			sdf = new SimpleDateFormat("yyyy-MM-DD");
		}
		return sdf ;
	}
	
	/**
	 * 取得两个日期横跨的月份（包含起期和止期所在月）
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	public static int getMonthCount(Date dateFrom , Date dateTo){
		if(dateFrom.after(dateTo)){
			Date t = dateFrom;
			dateFrom = dateTo;
			dateTo = t;
		}
		Calendar dateFromCalendar = Calendar.getInstance();
		Calendar dateToCalendar = Calendar.getInstance();
		dateFromCalendar.setTime(dateFrom);
		dateToCalendar.setTime(dateTo);
		Calendar temp = Calendar.getInstance();
		temp.setTime(dateTo);
		int year = dateToCalendar.get(Calendar.YEAR) - dateFromCalendar.get(Calendar.YEAR);
		int month = dateToCalendar.get(Calendar.MONTH) - dateFromCalendar.get(Calendar.MONTH);
		if((dateFromCalendar.get(Calendar.DATE)==1 && temp.get(Calendar.DATE)==1)){
			return year * 12 + month + 2;
		}else if((dateFromCalendar.get(Calendar.DATE)!=1 && temp.get(Calendar.DATE)==1)){
			return  year * 12 + month + 1;
		}else if(dateFromCalendar.get(Calendar.DATE)==1 && temp.get(Calendar.DATE)!=1){
			return year * 12 + month + 1;
		}else{
			return (year * 12 + month ) == 0 ? 1 : (year * 12 + month + 1);
		}
	}
	
	/**
	 * 
	 * @param dateTime
	 *            :计算某个时间到当前时间的天数
	 * @return int
	 */
	public static int getBetweenDays(Date startDate, Date endDate ) {
		long dayscount = 0;
		try {
			if(startDate.after(endDate)){
				dayscount = 1;
			}else{
				long milliseconds = endDate.getTime() - startDate.getTime();
				dayscount = milliseconds / (long) (1000 * 60 * 60 * 24) + 1;// 获取差值，并计算天数
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return (int) dayscount - 1;
		return (int) dayscount ;  // 滞纳天数  截止日期应该到 投保查询日当天（包含投保查询日） mili 2015-5-5 15:19:25
	}
	/**
	 * @author MILI
	 * @time 2015年9月1日17:11:46
	 * @描述：获取上一年或者下一年
	 * */
	public static Date getYear(Date date, int day) {
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		gc.setTime(date);
		gc.add(1, day);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return getStringDates(sf.format(gc.getTime()));
	}
	
	/**
     * 取得两日期月份间隔
     * @param date1 <String>
     * @param date2 <String>
     * @return int
     * @throws ParseException
     */
    public static int getMonths(Date date1, Date date2)
            throws ParseException {

        int result = 0;

        Date dateFrom;
        Date dateTo;
        if(date1.after(date2)){
        	dateFrom = date2;
        	dateTo = date1;
        }else{
        	dateFrom = date1;
        	dateTo = date2;
        }
        
        Calendar cFrom = Calendar.getInstance();
        Calendar cTo = Calendar.getInstance();

        cFrom.setTime(dateFrom);
        cTo.setTime(dateTo);
        
        int yearC = cTo.get(Calendar.YEAR) - cFrom.get(Calendar.YEAR);
        int monthC = cTo.get(Calendar.MONTH) - cFrom.get(Calendar.MONTH);

        return yearC * 12 + monthC + 1;

    }
    /**
     * @author MILI
     * @Date 2015-4-22 11:16:06
     * @描述：获取时间的下一个月
     * */
    public static String get_nextMonth(String date){
    	String next_Month = "" ;
    	try {
			SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sbf.parse(date));
			cal.add(Calendar.MONTH, +1);
			next_Month = sbf.format(cal.getTime()) + "-01";
		} catch (ParseException e) {
			System.out.println("获取时间的下一个月  错误");
			e.printStackTrace();
		}
    	return next_Month ;
    }
    /**
     * @author MILI
     * @描述：截取字符串转换为时间格式
     * @Date: 2015-5-19 15:13:39 
     * */
    public static Date change_Date(String str){
    	Date date = null ;
    	SimpleDateFormat sbf = new SimpleDateFormat("yyyyMMddHHmmss");
    	try {
			date = sbf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return date ;
    }
//    public static void main(String args[]){
//    	Date d1 = getStringDates("2013-12-12");
//    	Date d2 = getStringDates("2014-01-12");
//    	int n = getMonthCount(d1,d2);
//    	System.out.println(n);
//    }
}
