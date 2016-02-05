package com.derun.controller.whiteList;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import com.derun.common.util.DateUtil;
import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.controller.whiteList.dao.WhiteListCheckServletDao;
import com.derun.controller.whiteList.dao.WhiteQueryServletDao;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX_TEMP;
/**
 * 
 * */
public class WhiteQueryDetailServlet extends HttpServlet {

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
		String id = req.getParameter("id");
		//JSONObject jsonObj = new JSONObject();
	    //JSONArray jsonArray = new JSONArray();
	    JSONObject json_Obj = new JSONObject();
		List<SYJK_CCS_QGCLWTMDXX_TEMP> list_w = whiteQueryServletDao.getDetail(id);
		SYJK_CCS_QGCLWTMDXX_TEMP wtmd = null;
		for(int i = 0 ; i < list_w.size() ; i++){
			 json_Obj = new JSONObject();
			 wtmd = list_w.get(i);
			 String hphm = wtmd.getHPHM();
			 String jc1="";
			 String jc2="";
			 String cp="";
			 if(!"".equals(hphm)&&hphm != null){
				  jc1 =hphm.substring(0,1);
				  jc2=hphm.substring(1,2);
				  cp = hphm.substring(2); 
			 }
			json_Obj.put("CLSBDM", wtmd.getCLSBDM());
			json_Obj.put("HPHM", wtmd.getHPHM());
			json_Obj.put("jc1", jc1);
			json_Obj.put("jc2", jc2);
			json_Obj.put("cphm", cp);
			json_Obj.put("HPZL", wtmd.getHPZL());
			json_Obj.put("CLZL", wtmd.getCLZL());
			json_Obj.put("FDJH", wtmd.getFDJH());
			json_Obj.put("ZCCMC", wtmd.getZCCMC());
			json_Obj.put("SYXZ", wtmd.getSYXZ());
			json_Obj.put("CLXH", wtmd.getCLXH());
			json_Obj.put("CLCSDJRQ", DateUtil.getStringDate(wtmd.getCLCSDJRQ(),null));
		    json_Obj.put("JGCLLX", wtmd.getJGCLLX());
		    json_Obj.put("HDZKS", wtmd.getHDZKS());
			json_Obj.put("HDZZL", wtmd.getHDZZL());
			json_Obj.put("ZBZL", wtmd.getZBZL());
			json_Obj.put("PL", wtmd.getPL());
			json_Obj.put("GL", wtmd.getGL());
		    json_Obj.put("SKSSSQ",  DateUtil.getStringDate(wtmd.getSKSSSQ(),null));
		    json_Obj.put("SKSSZQ",  DateUtil.getStringDate(wtmd.getSKSSZQ(),null));
		    json_Obj.put("DQYNSE", wtmd.getDQYNSE());
			json_Obj.put("REVENUECODE", wtmd.getREVENUECODE());
			json_Obj.put("WS", wtmd.getWSPZ());
			json_Obj.put("WSPZNO", wtmd.getWSPZNO());
			json_Obj.put("ycyy", wtmd.getYCYYDM());
			
			json_Obj.put("DQYNSE_W", wtmd.getDQYNSE());
			json_Obj.put("SKSSSQ_W", DateUtil.getStringDate(wtmd.getSKSSSQ(),null));
			//jsonArray.add(json_Obj);
		}
		
		//jsonObj.put("rows", jsonArray);
		
		System.out.println(json_Obj.toString());
		ResponseUtil.write(resp, json_Obj.toString()); 
		//req.setAttribute("wtmd", wtmd);
		//req.getRequestDispatcher("/ccs/jsp/detail.jsp").forward(req, resp);

	}
}
