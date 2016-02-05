package com.derun.monitors.yewu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.monitors.List_Monitor;
/**
 * @author MILI
 * @time 2014-9-1 14:33:13
 * @描述：ajax 请求
 * */
public class Ajax_Servlet extends HttpServlet{
	static SYJK_CCS_RKMX static_rkmx = new SYJK_CCS_RKMX();
	private YeWu_Assist yewu_assist = new YeWu_Assist();
	private static final long serialVersionUID = 1L;
	private final static int SIZE = 12 ;
	static List<YeWu_VO> list = new ArrayList<YeWu_VO>();
	YeWu_VO yewu_vo = new YeWu_VO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String String_flag = "no" ;
		boolean flag = false ;
		List<SYJK_CCS_RKMX> list_mon = List_Monitor.getList();
		JSONArray jsonarray = new JSONArray();
		list = new ArrayList<YeWu_VO>();
		SYJK_CCS_RKMX rkmx = new SYJK_CCS_RKMX();
		int number = 0 ,listsize = list_mon.size();
		int i = 0 ;
		if(listsize <= SIZE){
			number = listsize ;
		}else{
			number = SIZE ;
		}
		i = listsize - 1 ;
		for(int j = 0 ; j < number ;j++){
			rkmx = new SYJK_CCS_RKMX();
			rkmx = yewu_assist.set_Mon(list_mon.get(i));
			if(j == 0){
				flag = yewu_assist.Is_newYewu(static_rkmx,rkmx);
				static_rkmx = yewu_assist.set_Mon(rkmx); ;
			}
			
			rkmx = yewu_assist.sub_rkmx(rkmx);
			
			jsonarray.add(rkmx);
			i-- ;
		}
		if(flag){
			String_flag = "yes" ;
		}else{
			String_flag = "no";
		}
		jsonarray.add(String_flag);
		ResponseUtil.write(resp, jsonarray);
	}
}
