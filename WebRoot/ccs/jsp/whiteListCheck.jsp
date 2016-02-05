<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="inc-common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<link href="../css/main.css" rel="stylesheet" type="text/css"/>
	<script src="../js/whiteListCheck.js"></script>
	<script src="../js/date.js"></script>
	<script type="text/javascript">
		function init_mili(){
			
		}
	</script>
</head>
<body onload = "initTable();">
	<div id="data-head" align="left">
		<h1>问题名单审核</h1>
	</div>
	<br/> <!-- editable="false" -->
	<div id="data-content">
		申请日期从<input id = "dd" class="easyui-datebox" data-options="formatter:myformatter">&nbsp;
		到&nbsp;<input id = "dt" class="easyui-datebox" data-options="formatter:myformatter">
	</div>
	<div style = "padding-left: 1000px">
		<input type = "button" value = "查询" onclick = "query();">
	</div>
	<br/>
	<form action = "white_examine"> 
		<table id="examine"></table>
	</form>
	<div style = "padding-left: 1000px">
		<input type = "button" value = "直接提交" onclick = "piliang();">
	</div>
	<div closed = "true" id="win" class="easyui-window" title="Login" style="width:700px;height:450px;">  
        <form id = "xx" style="padding:10px 20px 10px 40px;" > 
        	<table border="0">
        		<tr><th><label style = "color:red;">车辆信息</label></th></tr>
        		<tr>
        			<td><label>车辆识别代码</label></td>
        			<td><input type = "text" readonly="readonly" name = "CLSBDM" id = "clsbdm"></td>
        			<td width = "100px"></td>
        			<td><label>号牌号码</label></td>
        			<td><input type = "text" readonly="readonly" name = "HPHM"></td>
        		</tr>
        		<tr>
        			<td><label>号牌种类</label></td>
        			<td><input type = "text" readonly="readonly" name = "HPZL"></td>
        			<td></td>
        			<td><label>车辆种类</label></td>
        			<td><input type = "text" readonly="readonly" name = "CLZL"></td>
        		</tr>
        		<tr>
        			<td><label>发动机号</label></td>
	           		<td><input type = "text" readonly="readonly" name = "FDJH"></td>
	           		<td></td>
        			<td> <label>使用性质</label></td>
	           		<td><input type = "text" readonly="readonly" name = "SYXZ"></td>
        		</tr>
        		<tr>
        			<td><label>整备质量</label></td>
        			<td><input type = "text" readonly="readonly" name = "ZBZL"></td>
        			<td></td>
        			<td><label>初等日期</label></td>
        			<td><input type = "text" readonly="readonly" name = "CLCSDJRQ"></td>
        		</tr>
        		<tr>
        			<td><label>排量</label></td>
        			<td><input type = "text" readonly="readonly" name = "PL"></td>
        			<td></td>
        			<td><label>交管车辆类型</label></td>
        			<td><input type = "text" readonly="readonly" name = "JGCLLX"></td>
        		</tr>
        		<tr><th><label style = "color: red">缴税信息</label></th></tr>
        		<tr>
        			<td><label>税款所属始期</label></td>
        			<td><input type = "text" readonly="readonly" name = "SKSSSQ"></td>
        			<td></td>
        			<td><label>税款所属止期</label></td>
        			<td><input type = "text" readonly="readonly" name = "SKSSZQ"></td>
        		</tr>
        		<tr>
        			<td><label>已缴税额</label></td>
        			<td><input type = "text" readonly="readonly" name = "DQYNSE"></td>
        			<td><input type = "text" readonly="readonly" id = "WSPZ" name = "WSPZ" hidden = "hidden"></td>
        			<td><label>税务机关编码</label></td>
        			<td><input type = "text" readonly="readonly" name = "REVENUECODE"></td>
        		</tr>
        		<tr><th><label style = "color: red">完税凭证</label></th></tr>
        		<tr>
        			<td colspan="5"><a id = "photo" href = "#" onclick = "photo();">点击打开图片</a><span id = "span">未上传图片</span></td>
        			<td></td>
        			<td></td>
        			<td></td>
        			<td><br></td>
        		</tr>
        		<tr></tr>
        		<tr><th><label style = "color: red">审核意见</label></th></tr>
        	</table> 
        	<div>
            	<select id = "pass" name = "pass" >   
					<option value = "go" selected = "selected">通过</option>   
					<option value = "nogo">不通过</option> 
				</select><br/>
				<textarea id = "textstring" name = "textstring" style = "width: 400px;height: 40px;"></textarea>
            </div>
            <div style="padding:5px;text-align:right;">  
                <a href="#" class="easyui-linkbutton" icon="icon-ok" onclick = "submit_ok();">提交</a>  
                <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick = "javascript:$('#win').dialog('close')">取消</a>  
            </div> 
            <!-- 照片展示 -->
            <div closed = "true" id="photo_open" class="easyui-window" style="width:700px;height:450px;" > 
           		<img id = "img_address"/>
            </div>
        </form>  
    </div>  
</body>
</html>