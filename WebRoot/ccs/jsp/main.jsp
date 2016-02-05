<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page language="java" import="java.util.*,javax.servlet.http.*,com.derun.controller.login.util.*" pageEncoding="UTF-8"%>
<%@include file="inc-common.jsp" %>
<% 
if(session.getAttribute("user")==null)
 response.getWriter().write("<script>top.location='index.jsp'</script>"); 
%>
 
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<title>车船税系统管理平台</title>
	<!--[if IE]>
    <script src="../js/html5.js"></script>
	<![endif]--> 
	<script src="../js/main.js"></script>
	<link href="../css/main.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
	</script>
	
</head>
<body class="easyui-layout" onload="init()">

<form name="fm">

	<!-- --><div data-options="region:'north',border:false,split:false" style="height:60px; padding-bottom:5px; overflow: hidden;">
	 <div style="float: left;"><img src="../image/logo.png"></div> 
	<div style="float: right; padding-top:25px; padding-left: 0px ">
			<a href="javascript:void(0)" id="sb1" class="easyui-splitbutton" menu="#edit-menu" iconCls="icon-lht">换肤</a>
			<a href="javascript:void(0)" id="mb3" class="easyui-menubutton" menu="#mm3" iconCls="icon-help">帮助</a>
	</div>
		<div id="edit-menu" style="width:150px;">
		<div iconCls="icon-create" onclick="changeStyle('default')">天空之蓝</div>
		<div class="menu-sep"></div>
		<div iconCls="icon-save" onclick="changeStyle('bootstrap')">清新淡雅</div>
		<div class="menu-sep"></div>
		<div iconCls="icon-load" onclick="changeStyle('black')">黑色幽默</div>
		<div class="menu-sep"></div>
		<div iconCls="icon-del" onclick="changeStyle('metro')">微软美俏</div>
		</div>
		<div id="mm3" style="width:150px;">
			<div onclick="showHelp()">帮助</div>
			<div class="menu-sep"></div>
			<div onclick="showAbout()">关于</div>
			<div class="menu-sep"></div>
			<div onclick="goBack()">返回主页</div>
		</div>
	</div>
	
	
	<div data-options="region:'west',split:true,title:'车船税'" iconCls="icon-resource" style="width:200px;">
		<div class="easyui-accordion" data-options="fit:true, border:false">
			<div title="功能列表" style="padding: 0px">
				<ul id="tree"></ul>
			</div>
		</div>
	</div>
	
	<div data-options="region:'center',split:true" iconCls="icon-bench" >
		<!-- 主工作区用tab来组织 -->
    	<div id="mainTabs" class="easyui-tabs" border="false" fit="true" >
		    <div title="首页" style="overflow:hidden;" >
		    
		    	<!-- tabindex是项目相关的,所以用iframe来引用 -->
		    	<!-- 
				<iframe id="tabindex" src="../jsp/tabindex.jsp" style="width:100%;height:100%;border:0;"></iframe>
		    	 -->
		    </div>
		</div>
	</div>
	
	<div data-options="region:'south',border:false,split:true" style="height:50px; padding:10px; overflow: hidden;">
	<center>
		<p>Copyright © 2006 北京德润兴业科技有限公司 All Rights Reserved. 备案序号:京ICP备07003902号</p>
	</center>
	</div>
	<input type="hidden" id="menujson" value=<%=String.valueOf(session.getAttribute("menujson")) %>>
</form>	
	
</body>
</html>
<script>
	/**
	 * 初始化工作区
	 */
	function init(){
		var menujson = <%=session.getAttribute("menujson")%>;
		$("#tree").tree( {
			//url : "../data/menu.json",
			data:menujson,
			onClick : doMenuClick
		});
	
		
		$('#workbench-tab').tabs({
			fit:true
		});
		
		initStyle();
	}
</script>