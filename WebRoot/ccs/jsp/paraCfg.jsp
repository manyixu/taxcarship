<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="inc-common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<script src="../js/paraCfg.js"></script>
	<link href="../css/main.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">

	   function newPara(){
			$("#dlg").dialog('open').dialog('setTitle','添加参数配置');
			$('#fm').form('clear');
			url='../../savaParaCfgServlet';
		}
		
	  function savePara(){
	    $('#fm').form('submit',{
	       url :url,
	       onSubmit:function(){
	         return $(this).form('validate');
	       },
	       success:function(result){
	          var result=eval('('+result+')');
	          if(result.errorMsg){
	            $.messager.alert("系统提示",result.errorMsg);
	             $('#dlg').dialog('close');
	            return ;
	           }else{
	              $.messager.alert("系统提示","保存成功");
	              $('#dlg').dialog('close');
				  $("#tt").datagrid("reload");
	           }
	         
	       }
	       
	    
	    });
	  }
	  
	  function editPara(){
	     var row=$('#tt').datagrid('getSelected');
			if(row){
		       $("#dlg").dialog('open').dialog('setTitle','修改参数配置');
				$('#fm').form('load',row);
				url='../../savaParaCfgServlet?id='+row.CODE;
	     }
	  }
	  
	  function deletePara(){
	       var row=$('#tt').datagrid('getSelected');
			if(row){
				$.messager.confirm("系统提示","您确定要删除这条记录吗?",function(r){
					if(r){  
						$.post('deleteParaCfgServlet',{code:row.CODE},function(result){
							if(result.success){
								$.messager.alert("系统提示","已成功删除这条记录!");
								$("#tt").datagrid("reload");
							}else{
								$.messager.alert("系统提示",result.errorMsg);
							}
						},'json');
					}
				});
			}
	  }
	</script>
	
</head>


	 <body onload="preCheck()">	

	    <div id="toolbar" style="display: none">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newPara()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPara()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deletePara()">删除</a>
        
    </div>
		<table id="tt"></table>
		<table id="di"></table>
		<!--  <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">业务查询</a>-->
    
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:250px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post">
        	<table cellspacing="10px;">
        		<tr>
        			<td>系统代码：</td>
        			<td><input name="code" class="easyui-validatebox" required="true" style="width: 200px;"></td>
        		</tr>
        		<tr>
        			<td>系统代码别名：</td>
        			<td><input name="codealla" class="easyui-validatebox"  style="width: 200px;"></td>
        		</tr>
        		<tr>
        			<td>系统代码名称：</td>
        			<td><input name="codeName" class="easyui-validatebox"  required="true" style="width: 200px;"></td>
        		</tr>
        		<tr>
        			<td>系统代码类型：</td> 
        			<td><input name="odeType" class="easyui-validatebox" required="true" style="width: 200px;"></td>
        		</tr>
        		<tr>
        			<td>对应值：</td> 
        			<td><input name="codeValue" class="easyui-validatebox" required="true" style="width: 200px;"></td>
        		</tr>
        		<tr>
        			<td>是否热配置：</td> 
        			<td><input name="ishotpara" class="easyui-validatebox"  style="width: 200px;"></td>
        		</tr>
        		<tr>
        			<td>说明：</td> 
        			<td><input name="remark" class="easyui-validatebox"  style="width: 200px;"></td>
        		</tr>
        		<tr>
        			<td>是否有效标识：</td> 
        			<td><input name="validateflag" class="easyui-validatebox" required="true" style="width: 200px;"></td>
        		</tr>
        	</table>
        </form>
	</div>
    
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePara()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
	</div>
	
</body>
	

</html>