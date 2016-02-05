package com.derun.controller.whiteList;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.derun.common.util.DateUtil;
import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.controller.whiteList.common.WhiteList_Tool;
import com.derun.controller.whiteList.dao.WhiteListCheckServletDao;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX_TEMP;
/**
 * @author MILI
 * @time 2014-11-26 08:49:18
 * @描述：查询待审批数据
 * */
public class WhiteListCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WhiteListCheckServletDao wlcs = new WhiteListCheckServletDao();
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
		String begindate = req.getParameter("tartdate") ;
		String enddate = req.getParameter("enddate") ;
		int count = wlcs.getWTMD_Count(begindate,enddate);
		JSONObject jsonObj = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    JSONObject json_Obj = null;
		List<SYJK_CCS_QGCLWTMDXX_TEMP> list_w = wlcs.getWTMD_W(page,rows,begindate,enddate);
		for(int i = 0 ; i < list_w.size() ; i++){
			json_Obj = new JSONObject();
			SYJK_CCS_QGCLWTMDXX_TEMP wtmd = list_w.get(i);
			wtmd = WhiteList_Tool.Hanization(wtmd);
			json_Obj.put("R", i + 1);
			json_Obj.put("ID", wtmd.getID());
			json_Obj.put("CLSBDM", wtmd.getCLSBDM());
			json_Obj.put("WSPZHM", wtmd.getWSPZHM());
			json_Obj.put("HEJE", wtmd.getHEJE());
			json_Obj.put("LOGINNAME", wtmd.getLOGINNAME());
			json_Obj.put("SJCJRQ", DateUtil.getStringDate(wtmd.getSJCJRQ(),null));
			json_Obj.put("APPLYSTATUS", wtmd.getAPPLYSTATUS());
			json_Obj.put("YCYYDM", wtmd.getYCYYDM());
			jsonArray.add(json_Obj);
		}
		jsonObj.put("total",count);
		jsonObj.put("rows", jsonArray);
		ResponseUtil.write(resp, jsonObj);
	}
}
