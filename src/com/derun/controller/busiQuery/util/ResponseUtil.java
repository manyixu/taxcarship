package com.derun.controller.busiQuery.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

	public static void write(HttpServletResponse response,Object o) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//response.sendRedirect("../jsp/busiMon.jsp");
		out.print(o);
		out.flush();
		out.close();
	}
	
	
}
