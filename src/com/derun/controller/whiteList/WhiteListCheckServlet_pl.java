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
 * @time 2014-11-24 15:34:29
 * @描述：批量通过 Servlet
 * */
public class WhiteListCheckServlet_pl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	WhiteListCheckServletDao wlcs = new WhiteListCheckServletDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int n = 0 ;
		String strs = req.getParameter("columns");
		String[] pl_str = strs.split(",") ;
		WhiteListCheckServletDao.setList_abnormal(new ArrayList<String>());
		List<String> list_abnormal = null ;
		if(pl_str != null && pl_str.length > 0){
			n = wlcs.set_pl(pl_str);
			if(n > 0){
				
			}else{
				list_abnormal = WhiteListCheckServletDao.getList_abnormal() ;
			}
		}else{
			
		}
		ResponseUtil.write(resp, list_abnormal);
	}
}
