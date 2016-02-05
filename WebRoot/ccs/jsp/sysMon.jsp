<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="inc-common.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- <!DOCTYPE html> -->
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<base href="<%=basePath%>">
 	<script src="ccs/js/highcharts/highcharts.js"></script>
 	<script src="ccs/js/highcharts/highcharts-more.js"></script>
 	<script src="ccs/js/highcharts/exporting.js"></script>
 	<script src="ccs/js/highcharts/highcharts-3d.js"></script>
 	
    <script type='text/javascript' src='dwr/engine.js'></script>  
	<script type='text/javascript' src='dwr/util.js'></script>   
	<script type='text/javascript' src='dwr/interface/SysInfoUtil.js'></script> 
    
	<script src="ccs/js/sysMon.js"></script>
	<link href="ccs/css/main.css" rel="stylesheet" type="text/css"/>
	
	<script type="text/javascript">
	$(function () {
		loadCpuData();
		loadDiskData();
		loadMemData();
		loadJvmMemData();

		loadDbCpuData();
		loadDbDiskData();
		loadDbMemData();
		loadDbTsData();//表空间使用率
	});

	function openDetWin(winId){
		if(winId=='app_disk_det'){
			diskMonDet();
		}
		$('#'+winId).window('open');
	}
	function viewData(){
		SysInfoUtil.testDwr();
	}
	</script>
</head>
<body class="easyui-layout"> 
	<div data-options="region:'east',split:true" style="width:160px; vertical-align: middle">
	<table>
	<tr valign="middle">
	<td>处理器监控</td>
	<td><a onclick="openDetWin('app_cpu_det')"> <img src="ccs/image/cpu.png" style="margin-bottom:-3px" border="0"> </a>
	</td>
	</tr>
	<tr valign="middle">
	<td>磁盘监控</td>
	<td><a onclick="openDetWin('app_disk_det')"><img src="ccs/image/harddisk.png" border="0"> </a>
	</td>
	</tr>
	<tr valign="middle">
	<td>内存监控</td>
	<td><a onclick="openDetWin('app_mem_det')"><img src="ccs/image/memory.png" border="0"> </a>
	</td>
	</tr>
	<tr valign="middle">
	<td>网络监控</td>
	<td><a onclick="openDetWin('app_net_det')"><img src="ccs/image/network.png" border="0"> </a>
	</td>
	</tr>
	<tr valign="middle">
	<td>虚拟机监控</td>
	<td><a onclick="openDetWin('app_jvm_det')"><img src="ccs/image/java.png" border="0"> </a>
	</td>
	</tr>
	<tr valign="middle">
	<td>数据库监控</td>
	<td><a onclick="openDetWin('app_db_det')"><img src="ccs/image/database.png" border="0"> </a>
	</td>
	</tr>
	</table>
	</div>
	<!-- 监控内容显示区 -->
	<div data-options="region:'center',split:true" iconCls="icon-bench" style="width: 800px">
		<div id="cont_cpu" style="min-width: 200px; max-width: 200px; height: 200px;  float: left "></div>
		<div id="cont_disk" style="min-width: 200px; max-width: 200px; height: 200px; float: left "></div>
		<div id="cont_mem" style="min-width: 200px; max-width: 200px; height: 200px;  float: left "></div>
		<div id="cont_jvmmem" style="min-width: 200px; max-width: 200px; height: 200px;  float: left "></div>
		<!-- 
	<input type="button" value="查询数据" onclick="javascript:viewData()">
		<div id="space1" style="width: 40px; float: left; height: 200px;"> </div>
		<div id="u_head" style="min-width: 60px; max-width: 60px; height: 200px; margin: 0 auto;  float: left; font-family: serif;font-size: x-large; text-align: center;"><br>应<br>用<br>服<br>务<br>器</div>
		<div id="d_head" style="min-width: 60px; max-width: 60px; height: 200px; margin: 0 auto;  float: left; margin-top: 50px; font-family: serif;font-size: x-large; text-align: center;">数<br>据<br>库<br>服<br>务<br>器</div>
		<div id="space1" style="width: 40px; float: left; height: 200px; margin-top: 50px;"> </div>
		 -->
		<div id="cont_tablespace_db" style="min-width: 200px; max-width: 200px; height: 200px;  float: left; margin-top: 50px "></div>
		<!-- 
		<div id="cont_cpu_db" style="min-width: 200px; max-width: 200px; height: 200px; float: left; margin-top: 50px "></div>
		<div id="cont_disk_db" style="min-width: 200px; max-width: 200px; height: 200px; float: left; margin-top: 50px "></div>
		<div id="cont_mem_db" style="min-width: 200px; max-width: 200px; height: 200px;  float: left; margin-top: 50px "></div>
		 -->
		
		<!-- 详细信息区 -->
		<div id="mon_detail">
				<div id="app_cpu_det" class="easyui-window" title="处理器信息"
					style="width: 600px; height: 400px"
					data-options="iconCls:'icon-save',modal:true, closed:true">
					 
				</div>
				<div id="app_disk_det" class="easyui-window" title="磁盘信息"
					style="width: 600px; height: 400px"
					data-options="iconCls:'icon-save',modal:true, closed:true">
					<div id="cont_disk_det" style="height: 270px">
					</div>
				</div>
				<div id="app_mem_det" class="easyui-window" title="系统内存信息"
					style="width: 600px; height: 400px"
					data-options="iconCls:'icon-save',modal:true, closed:true">
					Window Content
				</div>
				<div id="app_net_det" class="easyui-window" title="网络信息"
					style="width: 600px; height: 400px"
					data-options="iconCls:'icon-save',modal:true, closed:true">
					Window Content
				</div>
				<div id="app_jvm_det" class="easyui-window" title="JVM信息"
					style="width: 600px; height: 400px"
					data-options="iconCls:'icon-save',modal:true, closed:true">
					Window Content
				</div>
				<div id="app_db_det" class="easyui-window" title="数据库信息"
					style="width: 600px; height: 400px"
					data-options="iconCls:'icon-save',modal:true, closed:true">
					Window Content
				</div>
		</div>
	</div>
	
	
</body>
</html>