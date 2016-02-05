<%@ page language="java" import="java.util.*,javax.servlet.http.*,com.derun.controller.login.util.*" pageEncoding="UTF-8"%>

<%@include file="inc-common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<link id="skinStyle" rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
	<script src="../js/userManage.js"></script>
	<script src='<%=request.getContextPath()%>/dwr/interface/LoginServlet.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/util.js'></script>
	
	<link href="../css/main.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
	$(function(){
		initUserList();
	});
	</script>
	
</head>
<body onload="">	

	<div id="data-content" style="margin-top: 10px;">
	    <table id="dgtt">
	    </table>
	    <div id="toolbar">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建用户</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改用户</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除用户</a>
	    </div>
	    
	    <div id="dlg" class="easyui-dialog" style="width:700px;height:400px;padding:10px 20px"
	            closed="true" modal="true" buttons="#dlg-buttons">
	        <div class="ftitle">用户信息</div>
	        <form id="fm" method="post" novalidate>
	        <div style="float: left;">
	            <div class="fitem">
	                <label>用户名:</label>
	                <input id="userName" name="userName" class="easyui-textbox" onchange="ifUserExist(this.value)" >
	            </div>
	            <div class="fitem">
	                <label>密码:</label>
	                <input id="passWord" name="passWord" type="password" class="easyui-textbox" >
	            </div>
	            <div class="fitem" >
	                <label style="vertical-align: top;">菜单权限:</label>
	                <textarea id="menujson" name="menujson"  class="easyui-textbox" style="width: 160px; height: 180px" ></textarea>
	            </div>
	        </div>
	        <div>
	        	<table>
	        		<tr><td width="20px"></td> <td>
	        		<div style="padding: 0px">
	        		<label id='treelabel' style="vertical-align: top;"></label>
					<ul id="edittree"></ul>
					</div>
	        		</td> </tr>
	        	</table>
	        </div>
	        <div id="mm" class="easyui-menu" style="width:120px;">
				<div onclick="append()" data-options="iconCls:'icon-add'">新增</div>
				<div onclick="remove()" data-options="iconCls:'icon-remove'">删除</div>
				<div class="menu-sep"></div>
				<div onclick="expand()">展开</div>
				<div onclick="collapse()">收起</div>
			</div>
	        </form>
	    </div>
	    <div id="dlg-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="addUser()" style="width:90px">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	    </div>
	</div>

    <script type="text/javascript">
        var url;
        function initUserList(){
	        
	        $("#dgtt").datagrid({
	             height: 'auto',
	             width: '800',
	             title: '车船税系统用户列表',
	             collapsible: true,
	             singleSelect: true,
	             pagination:true,
	             pageSize:5,//每页显示的条数
	             pageList:[5,10,15,20],
	             loadMsg:'数据加载中...',
	             //url: LoginServlet.getUserList(),
	             rownumbers:true,
	             columns: [[
	              { field: 'id', title: 'ID', width: 100, align: 'center',sortable:true },
	              { field: 'userName', title: '用户名', width: 250, align: 'center',sortable:true },
	               { field: 'menujson', title: '权限菜单', width: 450, align: 'center'}
	             ]],
	             toolbar: "#toolbar",
	             sortName:'id',
	             sortOrder:'desc',
	             onDblClickRow:function(){
	            	 editUser();  		
	             }
	            	 
	         });
	         $('#dgtt').datagrid('getPager').pagination({
	              beforePageText:'第',
	              afterPageText:'页',
	              displayMsg:'当前显示第{from}到{to},共{total}条记录'
	         });
	       	LoginServlet.getUserList(initCallBack);
        }
        function initCallBack(data){
      		var obj = JSON.parse(data);
      		if(obj.rows!=null && obj.rows.length>0){
	      		for(var i=0; i<obj.rows.length; i++){
	      			if(JSON.stringify(obj.rows[i].menujson)[0]!='\"'){
		      			obj.rows[i].menujson = JSON.stringify(obj.rows[i].menujson);
	      			}
	      		}
      		}
        	$('#dgtt').datagrid('loadData',obj);
        }
        
        function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','用户信息录入');
            $('#fm').form('clear');
            initTree();
            //url = 'save_user.php';
        }
        function editUser(){
            var row = $('#dgtt').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','用户信息修改');
                $('#fm').form('load',row);
                initTree();
                //url = 'update_user.php?id='+row.id;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dgtt').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyUser(){
            var row = $('#dgtt').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','是否确认删除此用户?',function(r){
                    if (r){
                    	LoginServlet.destroyUser(row.id,delUserCallBack);
                    }
                });
            }
        }
        function delUserCallBack(result){
        	if (result == true){
                $('#dgtt').datagrid('reload');    // reload the user data
                initUserList();
            } else {
                $.messager.show({    // show error message
                    title: 'Error',
                    msg: result.errorMsg
                });
            }
        }
        
        function initTree(){
        	var lableTxt = "【<font color='red'>"+$("#userName").val()+"</font>】的权限菜单:";
        	document.getElementById('treelabel').innerHTML = lableTxt;
        	var menuStr0 = "[{\"id\" : 0,\"iconCls\":\"icon-bench\",\"text\" : \"菜单管理 - "+$("#userName").val()+"\"}]";
        	$("#edittree").tree( {
    			data:JSON.parse(menuStr0),
    			animate:true,
    			lines:true,
    			dnd:true,
    			onClick: function(node){
    				$(this).tree('beginEdit',node.target);
    			},
    			onContextMenu: function(e,node){
    				e.preventDefault();
    				$(this).tree('select',node.target);
    				$('#mm').menu('show',{
    					left: e.pageX,
    					top: e.pageY
    				});
    			},
    			onAfterEdit: function(node){
    				applyTreeData();
    			},
    			onDrop: function(target){
    				applyTreeData();
    			}
    		});
        	
        	var menuStr = "[{\"id\" : 0,\"iconCls\":\"icon-bench\",\"text\" : \"菜单管理 - "+$("#userName").val()+"\",\"children\" :"+$("#menujson").val()+"}]";
        	$("#edittree").tree( {
    			data:JSON.parse(menuStr),
    			animate:true,
    			lines:true,
    			dnd:true,
    			onClick: function(node){
    				$(this).tree('beginEdit',node.target);
    			},
    			onContextMenu: function(e,node){
    				e.preventDefault();
    				$(this).tree('select',node.target);
    				$('#mm').menu('show',{
    					left: e.pageX,
    					top: e.pageY
    				});
    			},
    			onAfterEdit: function(node){
    				applyTreeData();
    			},
    			onDrop: function(target){
    				applyTreeData();
    			}
    		});
        }
        
        function append(){
			var t = $('#edittree');
			var node = t.tree('getSelected');
			t.tree('append', {
				parent: (node?node.target:null),
				data: [{
					text: '未命名新菜单'
				}]
			});
			applyTreeData();
		}
		function remove(){
			var thisnode = $('#edittree').tree('getSelected');
			$('#edittree').tree('remove', thisnode.target);
			applyTreeData();
		}
		function collapse(){
			var node = $('#edittree').tree('getSelected');
			$('#edittree').tree('collapse',node.target);
		}
		function expand(){
			var node = $('#edittree').tree('getSelected');
			$('#edittree').tree('expand',node.target);
		}
		function applyTreeData(){
			//图形化修改菜单对应textarea生效
			//$('#edittree').tree('getData',$('#edittree').tree('getRoot'))
			//获取当前用户菜单jsondata
			var rootNode = $('#edittree').tree('find',0);
			var tree = $('#edittree').tree('getData',rootNode.target);//树数据
			var treeData = "[";
			if(tree.children!=null){	//直接使用tree数据有冗余内容，重新组织（支持三级，应该用递归的）
				for(var i=0;i<tree.children.length;i++){
					if(i!=0){
						treeData +=",";
					}
					treeData += "{\"id\":\""+tree.children[i].id+"\",\"text\":\""+tree.children[i].text+"\"";
					if(tree.children[i].children!=null){
						treeData +=",\"children\":[ ";
						for(var j=0;j<tree.children[i].children.length;j++){
							if(j!=0){
								treeData +=",";
							}
							treeData += "{\"id\":\""+tree.children[i].children[j].id+"\",\"text\":\""+tree.children[i].children[j].text+"\"";
							
							if(tree.children[i].children[j].children!=null){
								treeData +=",\"children\":[ ";
								for(var k=0;k<tree.children[i].children[j].children.length;k++){
									if(k!=0){
										treeData +=",";
									}
									treeData += "{\"id\":\""+tree.children[i].children[j].children[k].id+"\",\"text\":\""+tree.children[i].children[j].children[k].text+"\"";
									treeData += "}";
								}
								treeData += "]";
							}
							
							treeData += "}";
						}
						treeData += "]";
					}
					treeData += "}";
				}
			}
			treeData += "]";
			
			var newtree = JSON.parse(treeData);
			var treejson = JSON.stringify(newtree);
			$("#menujson").val(treejson);
			/*
			*/
			//
			
		}

    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
	
</body>
</html>