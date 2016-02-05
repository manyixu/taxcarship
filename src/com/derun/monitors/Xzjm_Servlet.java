package com.derun.monitors;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.derun.common.init.CfgLoader;
import com.derun.controller.busiQuery.util.ResponseUtil;
import net.sf.json.JSONArray;

public class Xzjm_Servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String password = req.getParameter("password");
		JSONArray json = new JSONArray();
		String DBpassword = CfgLoader.getConfigValue("SysCode_Password", "YZ_Password");
		if(DBpassword.equals(password)){
			json.add("success");
		}else{
			json.add("errorMsg");
		}
		ResponseUtil.write(resp, json);
		
//		String CheckBoxs[] = req.getParameterValues("Columns");
//		System.out.println(CheckBoxs);
//		resp.sendRedirect("../../test.jsp");
//		req.getRequestDispatcher("../YeWu/meun.jsp").forward(req, resp);
	}
	
}
