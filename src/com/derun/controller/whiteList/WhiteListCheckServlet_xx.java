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
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX_TEMP;
/**
 * @author MILI
 * @time 2014-11-25 16:54:34
 * @描述：白名单 详细信息
 * */
public class WhiteListCheckServlet_xx extends HttpServlet{
	private static final long serialVersionUID = 1L;
	WhiteListCheckServletDao wlcsd = new WhiteListCheckServletDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // 设置编码
		resp.setContentType("text/html;charset=utf-8");	// 设置编码
		JSONObject json_Obj = new JSONObject();
		SYJK_CCS_QGCLWTMDXX_TEMP wtmd = new SYJK_CCS_QGCLWTMDXX_TEMP();
//		String clsbdms = req.getParameter("clsbdm");
		String clsbdms = new String(req.getParameter("clsbdm").getBytes("ISO-8859-1"),"utf-8");
		String[] clsbdm = {clsbdms};
		List<SYJK_CCS_QGCLWTMDXX_TEMP> List_wtmd = wlcsd.getDetail_xx(clsbdm);
		if(List_wtmd != null){
			if(List_wtmd.size() > 0){
				wtmd = List_wtmd.get(0);
			}
			json_Obj.put("CLSBDM", empty_null(wtmd.getCLSBDM()));
			json_Obj.put("HPHM", empty_null(wtmd.getHPHM()));
			json_Obj.put("HPZL", empty_null(wtmd.getHPZL()));
			json_Obj.put("CLZL", empty_null(wtmd.getCLZL()));
			json_Obj.put("FDJH", empty_null(wtmd.getFDJH()));
			json_Obj.put("ZCCMC", empty_null(wtmd.getZCCMC()));
			json_Obj.put("SYXZ", empty_null(wtmd.getSYXZ()));
			json_Obj.put("CLXH", empty_null(wtmd.getCLXH()));
			json_Obj.put("CLCSDJRQ", empty_null(DateUtil.getStringDate(wtmd.getCLCSDJRQ(),null)));
			json_Obj.put("JGCLLX", empty_null(wtmd.getJGCLLX()));
			json_Obj.put("HDZKS", empty_null(wtmd.getHDZKS()));
			json_Obj.put("HDZZL", empty_null(wtmd.getHDZZL()));
			json_Obj.put("ZBZL", empty_null(wtmd.getZBZL()));
			json_Obj.put("PL", empty_null(wtmd.getPL()));
			json_Obj.put("GL", empty_null(wtmd.getGL()));
			json_Obj.put("SKSSSQ",  empty_null(DateUtil.getStringDate(wtmd.getSKSSSQ(),null)));
			json_Obj.put("SKSSZQ",  empty_null(DateUtil.getStringDate(wtmd.getSKSSZQ(),null)));
			json_Obj.put("DQYNSE", empty_null(wtmd.getDQYNSE()));
			json_Obj.put("WSPZ", empty_null(wtmd.getWSPZ()));
			json_Obj.put("REVENUECODE", empty_null(wtmd.getREVENUECODE()));
		}
		ResponseUtil.write(resp, json_Obj); 
	}
	// null 换  ""
	public static String empty_null(Object str){
		if(str == null){
			str = "" ;
		}
		return str.toString() ;
	} 
}
