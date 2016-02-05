<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="inc-draw2d.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>	
	<script type="text/javascript">
		window.setTimeout(function(){
			parent.$.messager.show({
				title:"消息提示",
				msg:'<a href="#" onclick="top.showAbout()">           关于</a>',
				timeout:30000
			})
		},3000);
	</script>
</body>
</html>