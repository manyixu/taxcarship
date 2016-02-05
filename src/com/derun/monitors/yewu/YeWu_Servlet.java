package com.derun.monitors.yewu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.monitors.Hjhz_VO;
import com.derun.monitors.List_Monitor;
/**
 * @author MILI
 * @time 2014-9-1 9:28:45
 * @描述：业务明细servlet 
 * */
public class YeWu_Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final static int SIZE = 12 ;
	private YeWu_Assist yewu_assist = new YeWu_Assist();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<YeWu_VO> list = new ArrayList<YeWu_VO>();
		YeWu_VO mili = new YeWu_VO();
		List<SYJK_CCS_RKMX> list_mon = List_Monitor.getList();
		Hjhz_VO hjhz_vo = List_Monitor.getHjhz();
		HjHz_VO hjhz = new HjHz_VO();
		int number = 0 ,listsize = list_mon.size();
		if(listsize <= SIZE){
			number = listsize ;
		}else{
			number = SIZE ;
		}
		for(int i = 0 ,j = 1 , k = 0; i < number ;i++){
			mili = new YeWu_VO();
			mili.setNum0("demo" + j);
			mili.setNum1("demo" + (++j));
			mili.setNum2("demo" + (++j));
			mili.setTable1("table" + (k++)) ;
			mili.setTable2("table" + (k++)) ;
			j++ ;
//			mili.setNewrkmx(yewu_assist.sub_rkmx(list_mon.get(number - i - 1)));
			
			SYJK_CCS_RKMX rkmx = yewu_assist.set_Mon(list_mon.get(number - i - 1));
			rkmx = yewu_assist.sub_rkmx(rkmx);
			mili.setNewrkmx(rkmx);
			list.add(mili);
		}
		
		hjhz = yewu_assist.set_HjHz(hjhz_vo);
		req.setAttribute("number", list);	// 业务明细
		req.setAttribute("list_td", yewu_assist.set_Tabletd());	// 业务明细
		req.setAttribute("hjhz",hjhz);	// 合计汇总
		req.getRequestDispatcher("/ccs/jsp/YeWu/animate.jsp").forward(req, resp);
	}
}
