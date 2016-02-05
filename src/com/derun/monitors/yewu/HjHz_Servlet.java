package com.derun.monitors.yewu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.monitors.Hjhz_VO;
import com.derun.monitors.List_Monitor;

public class HjHz_Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	YeWu_Assist yewu = new YeWu_Assist();
	static int static_HjHz = 0;
	String flag = "yes" ;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Hjhz_VO hjhz_vo = List_Monitor.getHjhz();
		JSONArray jsonarray = new JSONArray();
		HjHz_VO hjhz = new HjHz_VO();
		if(static_HjHz != hjhz_vo.getDate_Number()){
			flag = "yes" ;
		}else{
			flag = "no" ;
		}
		static_HjHz = hjhz_vo.getDate_Number();
		hjhz = yewu.set_HjHz(hjhz_vo);
		jsonarray.add(hjhz);
		jsonarray.add(flag);
		ResponseUtil.write(resp, jsonarray);
	}
	
}
