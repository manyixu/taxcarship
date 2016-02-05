package com.derun.controller.busiQuery.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derun.controller.busiQuery.dao.BusiQueryDao;
import com.derun.controller.busiQuery.util.BusiQueryUtil;
import com.derun.controller.busiQuery.util.JsonUtil;
import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.controller.paraCfg.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BusiQueryServlet extends HttpServlet {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	     int page = Integer.parseInt(request.getParameter("page"));
	      int rows = Integer.parseInt(request.getParameter("rows"));
	      String vin = request.getParameter("vin");
	      String taxqueryno = request.getParameter("taxqueryno");
	      String cph = request.getParameter("cph");
	      String jym = request.getParameter("jym");
	      
	      JSONObject json = new JSONObject();
	      JSONArray array = new JSONArray();
	      JSONObject result = null;
	      BusiQueryDao busiQueryDao = new BusiQueryDao();
	      ArrayList<BusiQueryUtil> list;
	      try{
	    	  list = (ArrayList<BusiQueryUtil>) busiQueryDao.getCondition(page, rows,vin,taxqueryno,cph,jym);
	    	  int total = busiQueryDao.busiCount();
	    	  for(int i=0;i<list.size();i++){
		    	  result = new JSONObject();
		    	  BusiQueryUtil busiQueryUtil = list.get(i);
		    	  result.put("TAXQUERYNO", busiQueryUtil.getTAXQUERYNO());
		    	  result.put("VIN", busiQueryUtil.getVIN());
		    	  result.put("TAXPAYERNAME", busiQueryUtil.getTAXPAYERNAME());
		    	  result.put("TAXLOCATIONCODE", busiQueryUtil.getTAXLOCATIONCODE());
		    	  result.put("EngineNo", busiQueryUtil.getEngineNo());
		    	  result.put("TAXCONDITIONCODE", busiQueryUtil.getTAXCONDITIONCODE());
		    	  array.add(result);
	    	  }
    	    json.put("total",total );
		    json.put("rows", array);
		    ResponseUtil.write(response, json);
		    System.out.println(json.toString());
    	    System.out.println(array.toString());
	      }catch(Exception e){
	    	 e.printStackTrace(); 
	    	 
	    	
	      }
		
	      /* try {
			 BusiQueryDao busiQueryDao = new BusiQueryDao();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.getJsonArray(busiQueryDao.getBusiQuery(page ,rows));
			int total=busiQueryDao.busiCount();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
	}
	
	
  
}
