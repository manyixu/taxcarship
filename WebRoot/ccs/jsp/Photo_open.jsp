<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'a.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	<% 
		String file = request.getParameter("address");
		FileInputStream in = new FileInputStream(new File(file)); 
		OutputStream o = response.getOutputStream(); 
		int l = 0;
		byte[] buffer = new byte[4096]; 
		while((l = in.read(buffer)) != -1){
			o.write(buffer,0,l); 
		} 
		o.flush(); 
		in.close(); 
		o.close();
		out.clear();  
     	out = pageContext.pushBody(); 
	%>
	</head>
	<body>
	</body>
</html>
