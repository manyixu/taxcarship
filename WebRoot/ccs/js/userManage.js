       
       function addUser(){
    	   if(checkUser()){
    		   var user = {userName:$("#userName").val(),passWord:$("#passWord").val(),menujson:$("#menujson").val()};
    		   LoginServlet.addUser(user,addUserCallBack);//AAA.bbb([参数],callBack);//回调函数callBack()
    	   }
       }
       function addUserCallBack(data){
    	   if(data==false){//用户创建成功
    		   $.messager.alert('提示信息','保存失败','info');
    	   }else{
    		   $.messager.alert('提示信息','保存成功','info');
    		   $('#dlg').dialog('close');
    		   $('#dg').datagrid('reload');
    		   initUserList();
    	   }
       }
       
       function postToCheck(){
    	   alert('提交申请成功');
       }
       
       function checkUser(){
    	   //判断是否为空
    	   if($("#userName").val().replace(/(^\s*)|(\s*$)/g,'')==null || $("#userName").val().replace(/(^\s*)|(\s*$)/g,'')==''){	//相当于trim
    		  $.messager.alert('提示信息','请输入用户名','info');
    		  return false;
    	   }else if($("#passWord").val().replace(/(^\s*)|(\s*$)/g,'')==null || $("#passWord").val().replace(/(^\s*)|(\s*$)/g,'')==''){	//相当于trim
     		  $.messager.alert('提示信息','请输入用密码','info');
     		 return false;
     	   }else if($("#menujson").val().replace(/(^\s*)|(\s*$)/g,'')==null || $("#menujson").val().replace(/(^\s*)|(\s*$)/g,'')==''){	//相当于trim
     		  $.messager.alert('提示信息','请配置用户菜单权限','info');
     		  return false;
     	   }else if(ifUserExist($("#userName").val())){
	   		  $.messager.alert('提示信息','用户名已存在，请重新输入','info');
			  return false;
     	   }
    	   return true;
       }
       
    /**
     * 用户名输入框失去焦点触发
     */
    function ifUserExist(username){
    	var dlgTitle = $('#dlg').panel('options').title;
    	if(dlgTitle=='用户信息录入'){//新增用户
    		LoginServlet.isExist(username,callbackfun);
    		function callbackfun(data){
    			//新增用户才做校验
    			//判断用户是否存在：输入用户名，焦点离开后触发，存在则提示已存在，清空用户名，并且用户名输入框重新获得焦点
    			if(data){
    				alert('存在');
    			}
    			return data;
    		}
    	}
    }
       
       
       
       
       