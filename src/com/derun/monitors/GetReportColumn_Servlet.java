package com.derun.monitors;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derun.controller.busiQuery.util.ResponseUtil;
/**
 * @author MILI
 * @time 2014-9-3 14:43:47
 * @描述：密码验证
 * */
public class GetReportColumn_Servlet extends HttpServlet{
	static String column = "" ; 
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
//		String nj[] = req.getParameterValues("checkbox");
//		System.out.println(req.getContextPath());
		String field = "{ field: '" ;
		String title = "',title: '" ;
		String width = "',width:";
		String align = ",align: 'center'" ;
		String editor = ", editor: { type: 'text', options: { required: true } } }";
		String fields[] = {"PAYCOMPANYCODE","VIN","HPHM","CLLX","TAXCONDITIONCODE","TAXSTARTDATE","TAXENDDATE","ANNUALTAXAMOUNT","UNITRATE","PAYDATE","ANNUALTAXDUE","SUMTAXDEFAULT","SUMOVERDUE","SUMTAX"};
		String titles[] = {"代收公司","车辆识别码","号牌号码","车辆类型","纳税类型","税款始起","税款止起","单位税额","年度税额","所属年度","实缴税款","合计欠税","滞纳金","合计金额"};
		int width_number = 1270 / fields.length - 1 ;
		for(int i = 0 ;i < fields.length ;i++){
			column += field + fields[i] + title + titles[i] + width + width_number + align + editor + ","; 
		}
		column += "{ field:'REFRESH',hidden:true,title:'标记',width:" + width_number + ",align:'center', editor:{ type:'text',options:{ required: true} } }" ;

//	    { field: 'DEDUCTIONDOCUMENTNUMBER',  hidden:falg,title:'减免税凭证号',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
//	    { field: 'REFRESH', hidden:true,title:'标记',width:number,align:'center', editor:{ type:'text', options:{ required: true} } }
//		req.setAttribute("column", column);
//		req.getRequestDispatcher("../YeWu/busiMon.jsp").forward(req, resp);
//		System.out.println(column);
		ResponseUtil.write(resp, column);
	}
	
}
