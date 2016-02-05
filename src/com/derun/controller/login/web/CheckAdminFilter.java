package com.derun.controller.login.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.derun.controller.login.util.User;

/**
 * 
 * @author 郑艳英
 * 
 * 
 */

public class CheckAdminFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");// 此session是你在登录是放入的用户实体
//			System.out.println(req.getRequestURI());
//			System.out.println(req.getContextPath());

			if (user == null) {

				res.sendRedirect(req.getContextPath() + "/index.jsp ");
			} else {
				filterChain.doFilter(request, response);
			}

		} catch (ServletException sx) {
			filterConfig.getServletContext().log(sx.getMessage());
		} catch (IOException iox) {
			filterConfig.getServletContext().log(iox.getMessage());
		}

	}

}
