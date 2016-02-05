package com.derun.monitors;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derun.all.common.Double_Decimal;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @author MILI
 * @time 2014年7月24日9:13:53
 * @描述：合计汇总
 * */
public class Hjhz_Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Hjhz_VO Hjhz_vo = List_Monitor.getHjhz();
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Date_Money", Double_Decimal.Partition(Hjhz_vo.getDate_Money()));
		jsonobj.put("Date_Number",Hjhz_vo.getDate_Number());
		jsonobj.put("Month_Money",  Double_Decimal.Partition(Hjhz_vo.getMonth_Money()));
		jsonobj.put("Month_Number",Hjhz_vo.getMonth_Number());
		jsonobj.put("Year_Money",  Double_Decimal.Partition(Hjhz_vo.getYear_Money()));
		jsonobj.put("Year_Number",Hjhz_vo.getYear_Number());
		jsonarray.add(jsonobj);
		PrintWriter	out = resp.getWriter();
		out.print(jsonarray);
	}
}
