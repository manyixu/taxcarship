package com.derun.controller.paraCfg.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.controller.paraCfg.dao.ParaCfgDao;
import com.derun.controller.paraCfg.util.ParaCfgUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author
 * 
 * */
public class ParaCfgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		String code = request.getParameter("code");
		String codeType = request.getParameter("codeType");
		String idhotp = request.getParameter("idhotp");
		String validateflag = request.getParameter("validateflag");
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject result = new JSONObject();
		ParaCfgDao paraCfgDao = new ParaCfgDao();
		try {
			int total = paraCfgDao.getCount();
			List<ParaCfgUtil> list = paraCfgDao.query(page, rows, code,
					codeType, idhotp, validateflag);
			for (int i = 0; i < list.size(); i++) {
				ParaCfgUtil paraCfgUtil = list.get(i);
				result.put("CODE", paraCfgUtil.getCODE());
				result.put("CODEALIA", paraCfgUtil.getCODEALIA());
				result.put("CODENAME", paraCfgUtil.getCODENAME());
				result.put("ODETYPE", paraCfgUtil.getCODETYPE());
				result.put("CODEVALUE", paraCfgUtil.getCODEVALUE());
				result.put("ISHOTPARA", paraCfgUtil.getISHOTPARA());
				result.put("REMARK", paraCfgUtil.getREMARK());
				result.put("VALIDATEFLAG", paraCfgUtil.getVALIDATEFLAG());
				array.add(result);
			}
			json.put("total", total);
			json.put("rows", array);
			ResponseUtil.write(response, json);
//			System.out.println(json.toString());
//			System.out.println(array.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
