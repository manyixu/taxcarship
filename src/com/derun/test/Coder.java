package com.derun.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.derun.common.db.ExeSQL;
import com.derun.common.util.DateUtil;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-4
 *
 * 说明
 * @version
 */
public class Coder {
	
	private static Logger log = Logger.getLogger(Coder.class.getSimpleName());
	
	public static void main(String[] args) throws ParseException{String line = null;
	    try
	    {
	        Process pro = Runtime.getRuntime().exec("ping www.qq.com");
	        BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream(),"GB2312"));
	        while ((line = buf.readLine()) != null)
	            System.out.println(line);
	    }
	    catch (Exception ex)
	    {
	        System.out.println(ex.getMessage());
	    }
    }

}
