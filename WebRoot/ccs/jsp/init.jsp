<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="inc-common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'init.jsp' starting page</title>
    <script src="ccs/js/jquery-1.6.4.min.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function post_t(){
	var pass = document.getElementById('pass').value;
	$.ajax({
		url : 'preCheck_password',
		type : 'post' ,
		data : {'password':pass},
		beforeSend:function(){
           return true;
        },
		success:function(result){
			var obj = eval("(" + result + ")");
        	if(obj == 'success'){
        		document.getElementById('form_init').submit();
        	}else{
        		alert('密码错误');
        		window.location.reload();
        	}
        },
        error:function(){
        }
	});
}
</script>
  </head>
  <body onload = "">  
	<div style = "padding-left:400px;padding-top:130px;border:0px solid #F00">
		<div style = "border:0px solid #000;width: 200px;height: 100px">
			请输入访问权限口令:
	  		<form id = "form_init" action = "initser" method = "post" >
	  			<input type = "password" name = "" value = "" id = "pass" maxlength = "10"/>
	  			<input type = "button" onclick = "post_t();" value = "确定"/>
	  		</form>
		</div>
  	</div>
  </body>
</html>
