package com.derun.controller.paraCfg.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.controller.paraCfg.dao.ParaCfgDao;
import com.derun.controller.paraCfg.util.ParaCfgUtil;
import com.derun.controller.paraCfg.util.StringUtil;

public class SavaParaCfgServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
		
		 String code = request.getParameter("code");
		 String codealla = request.getParameter("codealla");
		 String codeName = request.getParameter("codeName");
		 String odeType = request.getParameter("odeType");
		 String codeValue = request.getParameter("codeValue");
		 String ishotpara = request.getParameter("ishotpara");
		 String remark = request.getParameter("remark");
		 String validateflag = request.getParameter("validateflag");
		 String id = request.getParameter("id");
		 ParaCfgUtil paraCfgUtil = new ParaCfgUtil(code,odeType,codeName,codealla,validateflag,ishotpara,remark,codeValue);
		 ParaCfgDao paraCfgDao = new ParaCfgDao();
		 JSONObject jsonObject = new JSONObject();
		 int result = 0;
		 try {
			 if(StringUtil.isNotEmpty(id)){
			 result = paraCfgDao.updata(paraCfgUtil,id); 
			 }else{
			result = paraCfgDao.addPara(paraCfgUtil);
			 }
			if(result!=0){
				jsonObject.put("success", "true");
			}else{
				jsonObject.put("success", "true");
				jsonObject.put("errorMsg", "添加失败");
			}
			ResponseUtil.write(response, jsonObject);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

      
	
}
