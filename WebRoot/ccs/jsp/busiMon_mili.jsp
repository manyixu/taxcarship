<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="inc-common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<script src="../js/YeWu/hjhz.js"></script>
	<script src="../js/YeWu/ywjk.js"></script>
	<link href="../css/main.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	var freshFlag = true ;  // 是否自动刷新
	var time1 = null;		// 
	function shuax(){	
		if(freshFlag){
			document.getElementById('btn').value = "暂停";
			freshFlag = false ;
			time1 = setInterval("initTable();shua()", 3000);
		}else{
			document.getElementById('btn').value = "刷新";
			freshFlag = true ;
			clearInterval(time1);
		}
	}
//	$(document).ready(function(){
//		setInterval("initTable();initTables()", freshseconds * 1000);
//	});
</script>
</head>  
<!--    <body onload="initCheck()" >-->
    <body onload="initTable();initTables([])" >	
	<form action=""> 
		<table id="ywjk"></table><br/>
		<table id="hjhz"></table>
		<br/>
		<!-- <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">业务监控</a> -->
	<input type = "button" id = "btn" value = "刷新" onclick = "shuax()"/>
	</form>
</body>
</html>