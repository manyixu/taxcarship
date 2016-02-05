package com.derun.controller.busiQuery.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.derun.controller.busiQuery.dao.BusiQueryDao;
import com.derun.controller.busiQuery.util.BusiQueryUtil;
import com.derun.controller.busiQuery.util.JsonUtil;
import com.derun.controller.busiQuery.util.ResponseUtil;

public class BusiPageQueryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 // int page = Integer.parseInt(request.getParameter("page"));
	     // int rows = Integer.parseInt(request.getParameter("rows"));
	      String vin = request.getParameter("vin");
	      
		try {
			 BusiQueryDao busiQueryDao = new BusiQueryDao();
			JSONObject result=new JSONObject();
			JSONArray array = new JSONArray();
			List<BusiQueryUtil>  list = busiQueryDao.getBusiQuery(vin);
			for(int i=0;i<list.size();i++){
				 result = new JSONObject();
		    	  BusiQueryUtil busiQueryUtil = list.get(i);
		    	  result.put("TAXQUERYNO", busiQueryUtil.getTAXQUERYNO());
		    	  result.put("VIN", busiQueryUtil.getVIN());
		    	  result.put("TAXPAYERNAME", busiQueryUtil.getTAXPAYERNAME());
		    	  result.put("TAXLOCATIONCODE", busiQueryUtil.getTAXLOCATIONCODE());
		    	  result.put("EngineNo", busiQueryUtil.getEngineNo());
		    	  result.put("TAXCONDITIONCODE", busiQueryUtil.getTAXCONDITIONCODE());
		    	  result.put("HPHM", busiQueryUtil.getHPHM());
		    	  result.put("SUMTAX", busiQueryUtil.getSUMTAX());
		    	  result.put("SJCJRQ", busiQueryUtil.getSJCJRQ());
		    	  result.put("HPZL", busiQueryUtil.getHPZL());
		    	  result.put("CLLX", "");
		    	  array.add(result);
			}
			
			result.put("rows", array);
			//result.put("total", total);
			System.out.println(array);
			ResponseUtil.write(response, array);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
}
