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
		<h1>问题名单查询</h1>
	</div>
	<br/>
	<div id="data-content1">
		申请状态  &nbsp;&nbsp;&nbsp;&nbsp;
		<select name="language" id="status">
		<option value="" selected="selected">--请选择--</option>
		<option value="0">未提交</option>
		<option value="1">待审核</option>
		<option value="2">审核通过</option>
		<option value="3">审核未通过</option>
		</select>
	</div>
	<br/>
	<div id="data-content2">
		申请日期从&nbsp;&nbsp;<input class="easyui-datebox" data-options="formatter:myformatter" editable="false" id="sqStartDate"></input>&nbsp;&nbsp;&nbsp;&nbsp;
		申请日期到&nbsp;&nbsp;<input class="easyui-datebox" data-options="formatter:myformatter" editable="false" id="sqEndDate"></input>
	</div>
	<br/>
	<div id="data-content3">
		审核日期从&nbsp;&nbsp;<input class="easyui-datebox" data-options="formatter:myformatter" editable="false" id="shStartDate"></input>&nbsp;&nbsp;&nbsp;&nbsp;
		审核日期到&nbsp;&nbsp;<input class="easyui-datebox" data-options="formatter:myformatter" editable="false" id="shEndDate"></input>
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
	        <div class="ftitle">用户信息</div>
	        <form id="fm" method="post" novalidate>
	           <table>
	            <tr><th>车辆信息</th></tr>
	    		<tr>
	    			<td>车辆识别代码:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="CLSBDM" data-options="required:false"></input></td>
	    			<td>号牌号码:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="HPHM" data-options="required:false"></input></td>
	    		</tr>
	    		<tr>
	    			<td>号牌种类:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="HPZL" data-options="required:false"></input></td>
	    			<td>车辆种类:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="CLZL" data-options="required:false"></input></td>
	    		</tr>
	    		<tr>
	    			<td>发动机号:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="FDJH" data-options="required:false"></input></td>
	    			<td>制造厂名称:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="ZCCMC" data-options="required:false"></input></td>
	    		</tr>
	    		<tr>
	    			<td>使用性质:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="SYXZ" data-options="required:false"></input></td>
	    			<td>车辆型号:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="CLXH" data-options="required:false"></input></td>
	    		</tr>
	    			<tr>
	    			<td>初登日期:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="CLCSDJRQ" data-options="required:false"></input></td>
	    			<td>交管车辆类型:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="JGCLLX" data-options="required:false"></input></td>
	    		</tr>
	    			<tr>
	    			<td>核定载客数:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="HDZKS" data-options="required:false"></input></td>
	    			<td>核定载客质量:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="HDZZL" data-options="required:false"></input></td>
	    		</tr>
	    			<tr>
	    			<td>整备质量:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="ZBZL" data-options="required:false"></input></td>
	    			<td>排量:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="PL" data-options="required:false"></input></td>
	    		</tr>
	    			<tr>
	    			<td>功率:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="GL" data-options="required:false"></input></td>
	    		</tr>
	    		  <tr><th>缴税信息</th></tr>
	    		<tr>
	    			<td>税款所属始期:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="SKSSSQ" data-options="required:false"></input></td>
	    			<td>税款所属止期:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="SKSSZQ" data-options="required:false"></input></td>
	    		</tr>
	    		<tr>
	    			<td>已缴税额:</td>
	    			<td><input class="easyui-validatebox" type="text" readonly name="DQYNSE" data-options="required:false"></input></td>
	    			<td>税务机关代码:</td>
	    			<td><input class="easyui-validatebox" type="text"  readonly name="REVENUECODE" data-options="required:false"></input></td>
	    		</tr>
	    		<tr><th>完税凭证</th></tr>
	    		<tr>
	    		   <td><input type = "text" readonly="readonly" id = "WS" name = "WS" hidden = "hidden"></td>
	    			<td><a id = "photo" href = "#" onclick = "photo();">完税凭证图片链接地址</a><span id = "span">未上传图片</span></td>
	    			<td>完税凭证号:</td>
	    			<td><input class="easyui-numberbox" readonly type="text" name="WSPZNO" ></input></td>
	    		</tr>
	    	</table>
	        </form>
	    </div>
	     <div id="dlg-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	    </div>
	     <!-- 照片展示 -->
            <div closed = "true" id="photo_open" class="easyui-window" style="width:700px;height:450px;" > 
           		<img id = "img_address"/>
            </div>
</body>
</html>