package com.derun.controller.login.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





import com.derun.common.db.SSRS;
import com.derun.controller.busiQuery.util.ResponseUtil;
import com.derun.controller.login.dao.LoginDao;
import com.derun.controller.login.util.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author 郑艳英
 * 用户登录
 *
 */
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	 String userName = java.net.URLDecoder.decode(request.getParameter("userName"),"UTF-8");
	 String passWord = request.getParameter("passWord");
	 JSONObject json = new JSONObject();
	 LoginDao loginDao = new LoginDao();
	 User user  = loginDao.ChkUser(userName, passWord);
	 if(user != null){
		 HttpSession session = request.getSession();
		 session.removeAttribute("user");
		 session.removeAttribute("menujson");
		 session.setAttribute("user", user);
		 session.setAttribute("menujson", user.getMenujson());
		 json.put("success", "true");
	 }else{
		 json.put("error", "用户名或密码不对");
	 }
	 ResponseUtil.write(response, json);
	}

	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user){
		
		boolean addFlag = false;
		LoginDao loginDao = new LoginDao();
		addFlag = loginDao.saveUser(user);
		return addFlag;
	}
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public boolean destroyUser(String userid){
		
		boolean delFlag = false;
		LoginDao loginDao = new LoginDao();
		delFlag = loginDao.destroyUser(userid);
		return delFlag;
	}
	  
	/**
	 * 查询用户列表
	 * @return
	 */
	public String getUserList(HttpServletRequest request, HttpServletResponse response){
		LoginDao loginDao = new LoginDao();
		List<User> userList = loginDao.getUserList();
		JSONObject json = new JSONObject();
		JSONArray ja = JSONArray.fromObject(userList);
		json.put("total", userList.size());
		json.put("rows", ja);
		return json.toString();
	}
	
	/**
	 * 验证用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean isExist(String username){
		
		LoginDao loginDao = new LoginDao();
		return loginDao.isExist(username);
	}
}
