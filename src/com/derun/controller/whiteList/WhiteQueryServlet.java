package com.derun.controller.whiteList;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.derun.common.util.DateUtil;
import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.controller.login.util.User;
import com.derun.controller.whiteList.common.WhiteList_Tool;
import com.derun.controller.whiteList.dao.WhiteListCheckServletDao;
import com.derun.controller.whiteList.dao.WhiteQueryServletDao;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX_TEMP;

public class WhiteQueryServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	WhiteListCheckServletDao wlcs = new WhiteListCheckServletDao();
	WhiteQueryServletDao whiteQueryServletDao = new WhiteQueryServletDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int page = Integer.parseInt(req.getParameter("page"));
		int rows = Integer.parseInt(req.getParameter("rows"));
		String status = req.getParameter("status");
		String sqStartdate = req.getParameter("sqStartdate");
		String sqEnddate = req.getParameter("sqEnddate");
		String shStartdate = req.getParameter("shStartdate");
		String shEnddate = req.getParameter("shEnddate");
		//获得session
	    HttpSession session = req.getSession();
	    //获得session中保留的信息
	     User user = (User) session.getAttribute("user");
	      String name =  user.getUserName();  
		

		int count = whiteQueryServletDao.getWTMD_Count(status,sqStartdate,sqEnddate,shStartdate,shEnddate,name);

	//	int count = wlcs.getWTMD_Count(null,null);

		JSONObject jsonObj = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    JSONObject json_Obj = null;
		List<SYJK_CCS_QGCLWTMDXX_TEMP> list_w = whiteQueryServletDao.getWTMD_W(page,rows,status,sqStartdate,sqEnddate,shStartdate,shEnddate,name);
		for(int i = 0 ; i < list_w.size() ; i++){
			json_Obj = new JSONObject();
			SYJK_CCS_QGCLWTMDXX_TEMP wtmd = list_w.get(i);
			wtmd = WhiteList_Tool.Hanization(wtmd);
			//json_Obj.put("R", i + 1);
			json_Obj.put("ID", wtmd.getID());
			json_Obj.put("CLSBDM", wtmd.getCLSBDM());
			json_Obj.put("LOGINNAME", wtmd.getLOGINNAME());
			json_Obj.put("SJCJRQ", DateUtil.getStringDate(wtmd.getSJCJRQ(),null));
			json_Obj.put("APPLYSTATUS", wtmd.getAPPLYSTATUS());
			json_Obj.put("CHECKMAN", wtmd.getCHECKMAN());
			json_Obj.put("CHECKDAT", DateUtil.getStringDate(wtmd.getCHECKDATE(),null));
			json_Obj.put("CHECKREMARK", wtmd.getCHECKREMARK());
			json_Obj.put("YCYYDM", wtmd.getYCYYDM());
			jsonArray.add(json_Obj);
		}
		
		System.out.println(jsonArray);
		jsonObj.put("total",count);
		jsonObj.put("rows", jsonArray);
		ResponseUtil.write(resp, jsonObj);
		System.out.println(jsonObj);
	}
}
