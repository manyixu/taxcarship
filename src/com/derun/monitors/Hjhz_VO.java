package com.derun.monitors;

import java.io.Serializable;

public class Hjhz_VO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int Date_Number = 0 ;			//	本日交易数
	private Double Date_Money = 0.0 ;		//	本日税款数
	private int Month_Number = 0 ;			//	本月交易数
	private Double Month_Money = 0.0 ;		//	本月税款数
	private int Year_Number = 0 ;			//	本年交易数
	private Double Year_Money = 0.0 ;		//	本年税款数
	public int getDate_Number() {
		return Date_Number;
	}
	public void setDate_Number(int dateNumber) {
		Date_Number = dateNumber;
	}
	public Double getDate_Money() {
		return Date_Money;
	}
	public void setDate_Money(Double dateMoney) {
		Date_Money = dateMoney;
	}
	public int getMonth_Number() {
		return Month_Number;
	}
	public void setMonth_Number(int monthNumber) {
		Month_Number = monthNumber;
	}
	public Double getMonth_Money() {
		return Month_Money;
	}
	public void setMonth_Money(Double monthMoney) {
		Month_Money = monthMoney;
	}
	public int getYear_Number() {
		return Year_Number;
	}
	public void setYear_Number(int yearNumber) {
		Year_Number = yearNumber;
	}
	public Double getYear_Money() {
		return Year_Money;
	}
	public void setYear_Money(Double yearMoney) {
		Year_Money = yearMoney;
	}
}
