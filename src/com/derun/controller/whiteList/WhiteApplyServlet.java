package com.derun.controller.whiteList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.derun.common.util.DateUtil;
import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.controller.login.util.User;
import com.derun.controller.whiteList.common.WhiteList_Tool;
import com.derun.controller.whiteList.dao.WhiteApplyServletDao;

import com.derun.model.po.SYJK_CCS_QGCLWTMDXX_TEMP;

public class WhiteApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WhiteApplyServletDao wlcs = new WhiteApplyServletDao();
	WhiteList_Tool white_tool = new WhiteList_Tool();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String ulr = req.getParameter("aUrl");
		req.setCharacterEncoding("utf-8"); // 设置编码
		resp.setContentType("text/html;charset=utf-8");
		// 获得session
		HttpSession session = req.getSession();
		// 获得session中保留的信息
		User user = (User) session.getAttribute("user");
		String name = user.getUserName();
		System.out.println(ulr);
		if ("query".equals(ulr)) {
			query(req, resp, name);
		} else if ("save".equals(ulr)) {
			Map<String, String> map = white_tool.setPhoto(req, resp, "jpg");
			save(req, resp, map, name);
		} else if ("delete".equals(ulr)) {
			delete(req, resp);
		} else if ("updata".equals(ulr)) {
			updata(req, resp, name); // 提交申请
			// } else if("edit".equals(ulr)){
			// edit(req,resp);
		} else if ("fileSave".equals(ulr)) {
			// 上传xls文件 并且写库
			Map<String, String> map = white_tool.setPhoto(req, resp, "xls");
			this.Insert_xls(req, resp, map);
		} else {
			Map<String, String> map = white_tool.setPhoto(req, resp, "jpg");
			editSave(req, resp, map); // 修改保存
		}
	}

	// 查询

	public void query(HttpServletRequest req, HttpServletResponse resp,
			String name) throws ServletException, IOException {
		int page = Integer.parseInt(req.getParameter("page"));
		int rows = Integer.parseInt(req.getParameter("rows"));
		int count = wlcs.getWTMD_Count(name);
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject json_Obj = null;
		List<SYJK_CCS_QGCLWTMDXX_TEMP> list_w = wlcs
				.getWTMD_W(page, rows, name);
		for (int i = 0; i < list_w.size(); i++) {
			json_Obj = new JSONObject();
			SYJK_CCS_QGCLWTMDXX_TEMP wtmd = list_w.get(i);
			wtmd = WhiteList_Tool.Hanization(wtmd);
			json_Obj.put("R", i + 1);
			json_Obj.put("ID", wtmd.getID());
			json_Obj.put("CLSBDM", wtmd.getCLSBDM());
			json_Obj.put("SKSSSQ", DateUtil.getStringDate(wtmd.getSKSSSQ(),
					null));
			json_Obj.put("SKSSZQ", DateUtil.getStringDate(wtmd.getSKSSZQ(),
					null));
			json_Obj.put("DQYNSE", wtmd.getDQYNSE());
			json_Obj.put("REVENUECODE", wtmd.getREVENUECODE());
			json_Obj.put("WS", wtmd.getWSPZ());
			json_Obj.put("YCYYDM", wtmd.getYCYYDM());
			jsonArray.add(json_Obj);
		}
		jsonObj.put("total", count);
		jsonObj.put("rows", jsonArray);
		ResponseUtil.write(resp, jsonObj);
		System.out.println(jsonArray);
	}

	// 保存
	public void save(HttpServletRequest req, HttpServletResponse resp,
			Map<String, String> map, String name) throws ServletException,
			IOException {
		SYJK_CCS_QGCLWTMDXX_TEMP wtmd = getWDMI(map);
		boolean falg = wlcs.saveWtmd(wtmd, name);

		req.getParameter("imgage");

		JSONArray jsonArray = new JSONArray();
		JSONObject json_Obj = new JSONObject();
		if (falg) {
			json_Obj.put("success", "success");
		} else {
			json_Obj.put("error", "error");
		}
		jsonArray.add(json_Obj);
		ResponseUtil.write(resp, json_Obj);
		System.out.println(json_Obj);
		System.out.println(jsonArray);

	}

	// 删除
	public void delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String[] ids = id.split(",");
		boolean falg = wlcs.deleteWtmd(ids);
		JSONObject json_Obj = new JSONObject();
		if (falg) {
			json_Obj.put("success", "success");
		} else {
			json_Obj.put("error", "error");
		}

		ResponseUtil.write(resp, json_Obj);
		System.out.println(json_Obj);

	}

	// 提交
	public void updata(HttpServletRequest req, HttpServletResponse resp,
			String name) throws ServletException, IOException {

//		System.out.println(name);
		String id = req.getParameter("id");
		String[] ids = id.split(",");
		boolean falg = wlcs.updataWtmd(ids, name);
		JSONObject json_Obj = new JSONObject();
		if (falg) {
			json_Obj.put("success", "success");
		} else {
			json_Obj.put("error", "error");
		}

		ResponseUtil.write(resp, json_Obj);
		System.out.println(json_Obj);

	}

	// //修改查询
	// public void edit(HttpServletRequest req, HttpServletResponse resp)throws
	// ServletException, IOException{
	// String id = req.getParameter("id");
	// boolean falg = wlcs.queryWtmd(id);
	// System.out.println("edit1");
	// }
	// 修改保存
	public void editSave(HttpServletRequest req, HttpServletResponse resp,
			Map<String, String> map) throws ServletException, IOException {
		String id = req.getParameter("id");

		SYJK_CCS_QGCLWTMDXX_TEMP wtmd = getWDMI(map);
		boolean falg = wlcs.saveEdit(wtmd, id);
		JSONArray jsonArray = new JSONArray();
		JSONObject json_Obj = new JSONObject();
		if (falg) {
			json_Obj.put("success", "success");
		} else {
			json_Obj.put("error", "error");
		}
		jsonArray.add(json_Obj);
		ResponseUtil.write(resp, json_Obj);
		System.out.println(json_Obj);
		System.out.println(jsonArray);
	}
	/**
	 * 封装问题名单临时表数据
	 * */
	public SYJK_CCS_QGCLWTMDXX_TEMP getWDMI(Map<String, String> map) {
		SYJK_CCS_QGCLWTMDXX_TEMP wtmd = new SYJK_CCS_QGCLWTMDXX_TEMP();

		// String name = req.getParameter("img");
		// System.out.println(name);
		wtmd.setCLSBDM(map.get("CLSBDM"));
		String ycyy = map.get("ycyy") == null ? "W" : map.get("ycyy");
		String jc1 = map.get("jc1") == null ? "" : map.get("jc1");
		String jc2 = map.get("jc2") == null ? "" : map.get("jc2");
		String cphm = map.get("cphm") == null ? "" : map.get("cphm");
		String hphm = "";
		if (jc1 != null && !"".equals(jc1) && jc2 != null && !"".equals(jc2)
				&& cphm != null && !"".equals(cphm)) {
			hphm = jc1 + jc2 + cphm;
		}
		wtmd.setYCYYDM(ycyy);
		wtmd.setHPHM(hphm);
		wtmd.setHPZL(map.get("HPZL").trim());
		wtmd.setCLZL(map.get("CLZL").trim());
		wtmd.setFDJH(map.get("FDJH").trim());
		wtmd.setZCCMC(map.get("ZCCMC").trim());
		String syxz = map.get("SYXZ") == null ? "" : map.get("SYXZ");
		wtmd.setSYXZ(syxz);
		wtmd.setCLXH(map.get("CLXH").trim());

		// String CLCSDJRQ = req.getParameter("CLCSDJRQ").trim();
		Date CLCSDJRQ = null;
		if (map.get("CLCSDJRQ") != null && !"".equals(map.get("CLCSDJRQ"))) {
			CLCSDJRQ = DateUtil.getStringDate(WhiteList_Tool.Date_format(map
					.get("CLCSDJRQ")), null);
		}
		wtmd.setCLCSDJRQ(CLCSDJRQ);
		wtmd.setJGCLLX(map.get("JGCLLX").trim());
		String HDZKS = map.get("HDZKS").trim();
		if ("".equals(HDZKS)) {
			wtmd.setHDZKS(null);
		} else {
			wtmd.setHDZKS(Double.valueOf(HDZKS));
		}

		String HDZZL = map.get("HDZZL").trim();
		if ("".equals(HDZZL)) {
			wtmd.setHDZZL(null);
		} else {
			wtmd.setHDZZL(Double.valueOf(HDZZL));
		}
		String ZBZL = map.get("ZBZL").trim();
		if ("".equals(ZBZL)) {
			wtmd.setZBZL(null);
		} else {
			wtmd.setZBZL(Double.valueOf(ZBZL));
		}
		String PL = map.get("PL").trim();
		if ("".equals(PL)) {
			wtmd.setPL(null);
		} else {
			wtmd.setPL(Double.valueOf(PL));
		}
		String GL = map.get("GL").trim();
		if ("".equals(GL)) {
			wtmd.setGL(null);
		} else {
			wtmd.setGL(Double.valueOf(GL));
		}

		Date SKSSSQ = null;
		Date SKSSZQ = null;
		String DQYNSE = null;
		if (!ycyy.equals("W")) {	// 非白名单的处理
			SKSSSQ = DateUtil.getStringDate(WhiteList_Tool.Date_format(map
					.get("SKSSSQ_W")), null);
			SKSSZQ = DateUtil.getStringDate(WhiteList_Tool.Date_format(
					map.get("SKSSSQ_W")).substring(0, 4)
					+ "-12-31", null);

			DQYNSE = map.get("DQYNSE_W");
			if (DQYNSE != null && !"".equals(DQYNSE)) {
				wtmd.setDQYNSE(Double.valueOf(DQYNSE));
				wtmd.setDWJSJE(Double.valueOf(DQYNSE));
				wtmd.setDQNDWSE(Double.valueOf(DQYNSE));
				wtmd.setHEJE(Double.valueOf(DQYNSE));
			} else {
				wtmd.setDQYNSE(0.0);
			}
		} else {		// 白名单的处理
			if (map.get("SKSSSQ") != null && !"".equals((map.get("SKSSSQ")))) {
				SKSSSQ = DateUtil.getStringDate(WhiteList_Tool.Date_format(map
						.get("SKSSSQ")), null);
			}

			if (map.get("SKSSZQ") != null && !"".equals((map.get("SKSSZQ")))) {
				SKSSZQ = DateUtil.getStringDate(WhiteList_Tool.Date_format(map
						.get("SKSSZQ")), null);
			}
			DQYNSE = map.get("DQYNSE");
			if (DQYNSE != null && !"".equals(DQYNSE)) {
				wtmd.setDQYNSE(Double.valueOf(DQYNSE));
			} else {
				wtmd.setDQYNSE(0.0);
			}
		}
		wtmd.setSKSSSQ(SKSSSQ);
		wtmd.setSKSSZQ(SKSSZQ);

		wtmd.setREVENUECODE(map.get("REVENUECODE"));
		wtmd.setWSPZ(map.get("WSPZ"));
		String WSPZNO = map.get("WSPZNO") == null ? "" : map.get("WSPZNO");
		wtmd.setWSPZNO(WSPZNO);
		return wtmd;
	}

	/**
	 * @author MILI
	 * @throws IOException
	 * @time 2014-12-4 09:00:37
	 * @描述：xls写入数据库
	 * */
	public void Insert_xls(HttpServletRequest request,
			HttpServletResponse resp, Map<String, String> map)
			throws IOException {
		String file_date = map.get("File_path");
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "upload/bmd/" + file_date + "/";
		path += map.get("NAME");
		// 保存到本地
		List<String[]> list = white_tool.analyze_xls(path);
		List<String> list_error = null;
		List<Map<String, String>> list_map = new ArrayList<Map<String, String>>();
		for (int i = 2; i < list.size(); i++) {
			Map<String, String> map_value = new HashMap<String, String>();
			for (int j = 0; j < list.get(i).length; j++) {
				// if("APPLYSTATUS".equals(list.get(0)[j])){
				// map_value.put(list.get(0)[j], "0");
				// }else{
				map_value.put(list.get(0)[j], list.get(i)[j].trim());
				// }
			}
			list_map.add(map_value);
		}
		boolean flag = false;
		for (int i = 0; i < list_map.size(); i++) {
			Map<String, String> map_clsbdm = list_map.get(i);
			flag = wlcs.saveWtmd(this.getWDMI(map_clsbdm), map_clsbdm
					.get("LOGINNAME"));
			if (!flag) { // 有更新失败记录提示
				list_error = new ArrayList<String>();
				list_error.add(map_clsbdm.get("CLSBDM") + "文件上传出错");
			}
		}
		ResponseUtil.write(resp, list_error);
	}
}
