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

public class DeleteParaCfgServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest requset, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(requset, response);
	}

	@Override
	protected void doPost(HttpServletRequest requset, HttpServletResponse response)
			throws ServletException, IOException {
		String code = requset.getParameter("code");
		 ParaCfgDao paraCfgDao = new ParaCfgDao();
		 JSONObject jsonObject = new JSONObject();
		 int i = 0;
		 try {
			i = paraCfgDao.delete(code);
			if(i!=1){
				jsonObject.put("success", "true");
			}else{
				jsonObject.put("success", "true");
				jsonObject.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(response, jsonObject);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	
	  
	
	
}
