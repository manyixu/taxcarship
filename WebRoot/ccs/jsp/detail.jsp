<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="inc-common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	
	<link href="../css/main.css" rel="stylesheet" type="text/css"/>
	<script src="../js/whiteListQuery.js"></script>
	<script type="text/javascript">
	
	
	</script>
	
</head>
<body onload="initTable()">
<div id="data-head">
		问题名单查询
	</div>
	<br/>
	<div id="data-content1">
		申请状态  &nbsp;&nbsp;&nbsp;<input type = "text" id="status">
	</div>
	<br/>
	<div id="data-content2">
		申请日期从<input type = "text" id="sqStartDate">
		申请日期到<input type = "text" id="sqEndDate">
	</div>
	<br/>
	<div id="data-content3">
		审核日期从<input type = "text" id="shStartDate">
		审核日期到<input type = "text" id="shEndDate">
	</div>
	<div style = "padding-left: 1000px">
		<input type = "button" value = "查询" onclick="query()">
	</div>
	<br/>
	<form action=""> 
		<table id="tt"></table>
	</form>

<div id="dlg" class="easyui-dialog" style="width:700px;height:400px;padding:10px 20px"
	            closed="true" modal="true" buttons="#dlg-buttons">
	        <div class="ftitle">User Information</div>
	        <form id="fm" method="post" novalidate>
	            <div class="fitem">
	                <label>vin:</label>
	                <input name="firstname" class="easyui-validatebox" required="true">
	            </div>
	            <div class="fitem">
	                <label>hphm:</label>
	                <input name="lastname" class="easyui-validatebox" required="true">
	            </div>
	            <div class="fitem">
	                <label>hpzl:</label>
	                <input name="phone" class="easyui-validatebox">
	            </div>
	            <div class="fitem">
	                <label>Email:</label>
	                <input name="email" class="easyui-validatebox" validType="email">
	            </div>
	        </form>
	    </div>

</body>
</html>