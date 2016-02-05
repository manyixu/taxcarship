function formInit() {
	$('#fileType').hide(); //文件导入方式默认隐藏
	$('#handType').hide();
	var number = 150;
	$("#tt").datagrid( {
		height : 340,
		width : 1100,
		title : '问题名单申请',
		collapsible : true,
		singleSelect : false,
		loadMsg : false,
		pagination : true,
		url : '../../whiteApplyServlet',
		idField : 'ID',
		columns : [ [ {
			field : 'R',
			title : '〇',
			checkbox : true
		}, {
			field : 'ID',
			title : 'ID',
			width : 70,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'CLSBDM',
			title : '车辆识别代码',
			width : number,
			align : 'center',
			editor : {
				type : 'numberbox',
				options : {
					precision : 2,
					required : true
				}
			}
		}, {
			field : 'SKSSSQ',
			title : '税款所属始期',
			width : number,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'SKSSZQ',
			title : '税款所属止期',
			width : number,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'DQYNSE',
			title : '当前应纳税额(元)',
			width : number,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'YCYYDM',
			title : '异常原因',
			width : number,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'REVENUECODE',
			title : '税务机关编码',
			width : number,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'WS',
			title : '完税凭证',
			width : number,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		} ] ],
		toolbar : [ {
			text : '新建申请',
			iconCls : 'icon-add',
			handler : function() {
				newUser();
			}
		}, '-', {
			text : '修改申请',
			iconCls : 'icon-edit',
			handler : function() {
				editUser();
			}
		}, '-', {
			text : '删除申请',
			iconCls : 'icon-remove',
			handler : function() {
				destroyUser();
			}
		}, '-', {
			text : '提交审核',
			iconCls : 'icon-up',
			handler : function() {
				Check();
			}

		} ],
		queryParams : {
			'aUrl' : 'query'

		},
		// 双击事件监听
		onDblClickRow : function(rowIndex) {
			$("#tt").datagrid('unselectAll');
			// 默认选择当前行
		$("#tt").datagrid('selectRow', rowIndex);
		var row = $('#tt').datagrid('getSelected');
		editUser(row);
	}
	});

	// 分页查询
	$('#tt').datagrid('getPager').pagination( {
		pageSize : 10, //每页显示的记录条数，默认为10 
		pageList : [ 5, 10, 15 ], //可以设置每页记录条数的列表 
		beforePageText : '第', //页数文本框前显示的汉字 
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
//新建申请
function newUser() {
	$("#save").show();
	$("#edit").hide();
	$('#WS').hide();
	$('#photo').hide();
	//	var numb = $("#ycyy option:selected").val();
	$('#dlg').dialog('open').dialog('setTitle', '问题名单数据录入');
	$('#fm').form('clear');
	$("#ycyy option[value='W']").attr("selected", true);
	$("#CLZL option[value='11']").attr("selected", true);
}
//修改申请
function editUser(row) {
	if (row) {

	} else {
		var row = $('#tt').datagrid('getSelected');
	}
	$("#save").hide();
	$("#edit").show();
	var id = row.ID;
	$('#fm').form('clear');
	$('#dlg').dialog('open').dialog('setTitle', '修改信息');
	$('#fm').form('load',
			"../../whiteQueryDetailServlet?aUrl=editSave&id=" + id);
	setTimeout("IsNoPhoto(" + id + ");", 300);
	$("#tt").datagrid('unselectAll');
	setTimeout("ycyy_flag();", 300);
	//document.getElementById("edit").onclick=saveEdit(id);
}
//保存申请
function saveUser() {
	var form = $('#fm');
	//            $('#fm').form('submit',{
	//                url : '../../whiteApplyServlet',
	////                onSubmit: function(){
	////                    return $(this).form('validate');
	////                },
	//                       type:'post', 
	//                        data:{'aUrl':'save','f':form.serialize()}, 
	//                        dataType:"json", 
	//                success: function(result){
	//                    var result = eval('('+result+')');
	//                    if (result.errorMsg){
	//                        $.messager.show({
	//                            title: 'Error',
	//                            msg: result.errorMsg
	//                        });
	//                    } else {
	//                        $('#dlg').dialog('close');        // close the dialog
	//                        $('#tt').datagrid('reload');    // reload the user data
	//                    }
	//                }
	//            });
	//        	
	//        	$.ajax({
	//        		
	//        		      url:'../../whiteApplyServlet', 
	//                        type:'post', 
	//                        data:{'aUrl':'save',},
	//                        dataType:"json", 
	//                        success:function(data){ 
	//                            $("#dialog").dialog("close"); 
	//                            alert("成功啦"); 
	//                            }
	//        	});

	$('#fm').form('submit', {
		type : 'post',
		url : '../../whiteApplyServlet?aUrl=save',
		//data:{'aUrl':'save'},
		onSubmit : function() {
			//			return $(this).form('validate');
		return validate();
	},
	dataType : "json",
	success : function(data) {
		var result = eval('(' + data + ')');
		if (result.error) {
			$.messager.show( {
				title : 'Error',
				msg : result.errorMsg
			});
		} else {
			//				$.messager.loadMsg();
		$.messager.alert('新建申请', '申请成功', 'info');
		$('#dlg').dialog('close'); // close the dialog
		$('#tt').datagrid('reload'); // reload the user data
	}
}
	});
}
//删除申请
function destroyUser() {
	//	var row = $('#tt').datagrid('getSelected');
	//	if (row) {
	var i = 0;
	var strings = ''; //	
	var all = $('#tt').datagrid('getChecked');
	if (all != null && all.length > 0) {
		for (; i < all.length;) {
			strings += all[i].ID + ",";
			i += 1;
		}
		$.messager.confirm('Confirm', '您确定要删除这条信息吗?', function(r) {
			if (r) {
				$.post('../../whiteApplyServlet?aUrl=delete', {
					id : strings
				}, function(result) {
					//var result = eval('('+data+')');
						if (result.success) {
							$.messager.alert('删除申请', '删除成功', 'info');
							$('#tt').datagrid('reload'); // reload the user data
					} else {
						$.messager.show( { // show error message
									title : 'Error',
									msg : result.errorMsg
								});
					}
				}, 'json');
			}
		});
	}
	$("#tt").datagrid('unselectAll');
}

//提交审核
function postToCheck() {
	alert("sss");
	var row = $('#tt').datagrid('getSelected');
	alert(row);
	alert(row.ID);
	//            if (row){
	//                $.messager.confirm('Confirm','您确定要提交审核这条信息吗?',function(r){
	//                    if (r){
	//                        $.post('../../whiteApplyServlet?aUrl=updata',{id:row.ID},function(result){
	//                            if (result.success){
	//                                $('#tt').datagrid('reload');    // reload the user data
	//                            } else {
	//                                $.messager.show({    // show error message
	//                                    title: 'Error',
	//                                    msg: result.errorMsg
	//                                });
	//                            }
	//                        },'json');
	//                    }
	//                });
	//            }
}
//提交审核
function Check() {
	var row = $('#tt').datagrid('getSelected');
	var all = $('#tt').datagrid('getChecked');
	var i = 0;
	var strings = '';
	if (all != null && all.length > 0) {
		for (; i < all.length;) {
			strings += all[i].ID + ",";
			i += 1;
		}
		//	if (all) {
		$.messager.confirm('Confirm', '您确定要提交审核这条信息吗?', function(r) {
			if (r) {
				$.post('../../whiteApplyServlet?aUrl=updata', {
					id : strings
				}, function(result) {
					if (result.success) {
						$.messager.alert('提交申请', '提交申请成功', 'info');
						$('#tt').datagrid('reload'); // reload the user data
					} else {
						$.messager.show( { // show error message
									title : 'Error',
									msg : result.errorMsg
								});
					}
				}, 'json');
			}
		});
	}
	$("#tt").datagrid('unselectAll');
}

//
function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}

//保存修改
function saveEdit() {
	var id = $("#hid").val();
	//	  alert(id);
	$('#fm').form('submit', {
		type : 'post',
		url : '../../whiteApplyServlet?aUrl=editSave&id=' + id,
		//data:{'aUrl':'save'},
		onSubmit : function() {
//			return $(this).form('validate');
		return true;
	},
	dataType : "json",
	success : function(data) {
		var result = eval('(' + data + ')');
		if (result.error) {
			$.messager.show( {
				title : 'Error',
				msg : result.errorMsg
			});
		} else {
			$.messager.alert('修改申请', '修改申请成功', 'info');
			$('#dlg').dialog('close'); 		// close the dialog
			$('#tt').datagrid('reload'); 	// reload the user data
	}
	}
	});
}
/**
 * @author MILI
 * @time 2014-12-3 17:03:29
 * @描述:文件上传
 * */
function file_upload() {
	var file_name = $('#fileToUpload').val();
	if (file_name == null || "" == file_name) {
		$.messager.alert('文件上传', '请选择上传文件', 'info');
	} else {
		$("<div class=\"datagrid-mask\"></div>").css( {
			display : "block",
			width : "100%",
			height : $(window).height()
		}).appendTo("body");
		$("<div class=\"datagrid-mask-msg\"></div>").html("请稍后...").appendTo(
				"body").css( {
			display : "block",
			left : 500,
			top : 180
		});
		//		$.messager.progress({
		//			title:'load....',	// 头信息
		//			text:'load....',	// 进度条显示
		//			interval:200,		// 属性时间
		//		});	// 显示进度条
		// submit 提交表单
		$('#file_up').form('submit', {
			type : 'post',
			url : '../../whiteApplyServlet?aUrl=fileSave',
			onSubmit : function() {
				return true;
			},
			success : function(result) {
				if (result == null) {
					$.messager.alert('文件上传', result, 'info');
				} else {
					$.messager.alert('文件上传', "文件导入成功", 'info');
				}
				$("body").find("div.datagrid-mask-msg").remove(); // 关闭	datagrid-mask-msg
			$("body").find("div.datagrid-mask").remove(); // 关闭	datagrid-mask
			//				$.messager.progress('close');		// 隐藏进度条
			$('#file_up').form('clear'); // 清空上次内容
			$('#tt').datagrid('reload'); // 重新加载
		}
		});
	}
}

function IsNoPhoto(id) {
	$("#hid").val(id);
	var url = $('#WS').val();
	if ("" == url || null == url) {
		$('#WSPZ').show();
		$('#photo').hide();
	} else {
		$('#photo').show();
		$('#WSPZ').show();
	}
}
function changeName() {
	var filename = $('#WSPZ').val();
	$('#WS').val(filename);
}

// 显示照片
function photo() {
	var url = $('#WS').val();
	// alert(url); // 中文名 暂时不支持
	// 读取绝对路径 图片
	// $('#img_address').attr("src","Photo_open.jsp?address=" + url);
	// 显示相对路径图片
	$('#img_address').attr("src", "../../" + url);
	var ul = $('#img_address').val();
	$('#photo_open').dialog('open').dialog('setTitle', '图片预览');
}

//图片预览

function preview() {
	var url = document.getElementById("fm").files.item(0).getAsDataURL();
	document.getElementById("view").innerHTML = "图片预览<img src='" + url
			+ "'style='border:6px double #ccc';padding:5px;>";
}
// 
function ycyy_flag() {
	var ycyy = $('#ycyy').val();
	if (ycyy == 'W') {
		$("#yc").css('display', '');
		$("#xs").css('display', 'none');
	} else {
		$("#yc").css('display', 'none');
		$("#xs").css('display', '');
	}
}
// 页面信息验证
function validate() {
	var flag_code = true ;
	var ycyy = $("#ycyy").val();		// 异常原因
	var CLSBDM = $("#CLSBDM").val();	// 车架号
	var FDJH = $("#FDJH").val();		// 发动机号
	var CLCSDJRQ = $("#CLCSDJRQ").datebox('getValue');	// 初等日期
	var DQYNSE = $("#DQYNSE").numberbox('getValue');		// 已交税额
	var SKSSSQ = $("#SKSSSQ").datebox('getValue');		// 税款起期
	var REVENUECODE = $("#REVENUECODE").val();		// 税务机关代码
//	var WSPZNO = $("#WSPZNO").numberbox('getValue');				// 完税凭证号
	var WSPZNO = $("#WSPZNO").val();				// 完税凭证号
	var CLZL = $("#CLZL").val();					// 车辆种类
	
	var DQYNSE_W = $("#DQYNSE_W").numberbox('getValue');			// 白名单-已交税额
	var SKSSSQ_W = $("#SKSSSQ_W").datebox('getValue');	// 白名单-税款起期
	
	var re = new RegExp("^[0-9A-Z]*$");
	if("W" == ycyy){
		if(hdk_val(CLSBDM) || hdk_val(FDJH) || hdk_val(CLCSDJRQ)  || hdk_val(DQYNSE) || hdk_val(SKSSSQ) || hdk_val(REVENUECODE) || hdk_val(WSPZNO)){
			$.messager.alert('提示信息', "请填写必填信息", 'info');
			return false ;
		}
	}else{
		if(hdk_val(CLSBDM) || hdk_val(FDJH) || hdk_val(CLCSDJRQ)  || hdk_val(DQYNSE_W) || hdk_val(SKSSSQ_W)){
			$.messager.alert('提示信息', "请填写非空项信息", 'info');
			return false ;
		}
	}
	
	var flag_vin = re.test(CLSBDM);
	var flag_eng = re.test(FDJH);
	if(!flag_vin){
		$.messager.alert('提示信息', "车辆识别码必须是英文大写字母或数字", 'info');
		return false ;
	}else if(!flag_eng){
		$.messager.alert('提示信息', "发动机号必须是英文大写字母或数字", 'info');
		return false ;
	}
	flag_code = clzl_va(CLZL);
	return flag_code;
}
// 车辆种类
function clzl_va(clzl){
	var flag = true ;
	var PL = $("#PL").numberbox('getValue');		// 排量
	var ZBZL = $("#ZBZL").numberbox('getValue');	// 整备质量
	// 排量计算
	if('11' == clzl || '12' == clzl){
		if(PL == "" || null == PL){
			$.messager.alert('提示信息', "按排量计算的车辆，排量不能为空", 'info');
			flag = false ;
		}
	}
	// 每辆计算
	if('15' == clzl || '14' == clzl || '15' == clzl || '71' == clzl || '72' == clzl || '73' == clzl){
		
	}
	// 按整备质量计算
	if('21' == clzl || '22' == clzl || '23' == clzl || '24' == clzl || '93' == clzl || '94'== clzl 
			|| '25' == clzl || '26' == clzl || '27' == clzl || '28' == clzl || '30' == clzl || '31' == clzl || '40' == clzl || '41' == clzl
			|| '50' == clzl || '51' == clzl || '60' == clzl || 'BA' == clzl || 'BB' == clzl || 'BC' == clzl || 'BD' == clzl || 'CG' == clzl 
			|| 'BE' == clzl || 'BF' == clzl || 'BG' == clzl || 'CA' == clzl || 'CB' == clzl || 'CD' == clzl || 'CE' == clzl || 'CF' == clzl ){
		if(ZBZL == "" || null == ZBZL){
			$.messager.alert('提示信息', "按整备质量计算的车辆，整备质量不能为空", 'info');
			flag = false ;
		}
	}
	return flag ;
}
// 校验
function hdk_val(str){
	if(str == null || "" == str){
		return true ;
	}else{
		return false ;
	}
}
