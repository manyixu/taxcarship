package com.derun.controller.whiteList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.controller.whiteList.dao.WhiteListCheckServletDao;
/**
 * @author MILI
 * @time 2014-11-26 09:29:46
 * @描述：白名单 单条通过
 * */
public class WhiteListCheckServlet_dt extends HttpServlet{
	private static final long serialVersionUID = 1L;
	WhiteListCheckServletDao wlcsd = new WhiteListCheckServletDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // 设置编码
		String clsbdm = req.getParameter("clsbdm");
		String account = req.getParameter("account");
		String pass_no = req.getParameter("pass_no");
		WhiteListCheckServletDao.setList_abnormal(new ArrayList<String>());
		int n = wlcsd.single_e(account,pass_no,clsbdm);
		List<String> list_abnormal = null ;
		if(n > 0){
			
		}else{
			list_abnormal = WhiteListCheckServletDao.getList_abnormal() ;
		}
		ResponseUtil.write(resp, list_abnormal);
	}
}
