function initTable() {
	var number = 150;
	$("#tt").datagrid( {
		height : 340,
		width : 1100,
		title : '问题名单查询',
		collapsible : true,
		singleSelect : true,
		loadMsg : false,
		pagination : true,
		url : '../../whiteQueryServlet',
		idField : 'ID',
		columns : [ [
		// {
				// field : 'R',
				// title : '〇',
				// checkbox:true
				// },
				{
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
					field : 'LOGINNAME',
					title : '申请人',
					width : number,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'SJCJRQ',
					title : '申请日期',
					width : number,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'CHECKMAN',
					title : '审核人',
					width : number,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'CHECKDAT',
					title : '审核日期',
					width : number,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'CHECKREMARK',
					title : '审核意见',
					width : number,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				} ] ],
		// 双击事件监听
		onDblClickRow : function() {
			detail();
		}
	});

	// 分页查询
	$('#tt').datagrid('getPager').pagination( {
		pageSize : 10, // 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ], // 可以设置每页记录条数的列表
		beforePageText : '第', // 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});

}

function query() {
	// alert($('#status').combobox('getValue'))
	$('#tt').datagrid('reload', {
		status : $('#status').combobox('getValue'),
		sqStartdate : $("#sqStartDate").datebox('getValue'),
		sqEnddate : $("#sqEndDate").datebox('getValue'),
		shStartdate : $("#shStartDate").datebox('getValue'),
		shEnddate : $("#shEndDate").datebox('getValue')
	});

}
// 详细信息
function detail() {
	var row = $('#tt').datagrid('getSelected');
	var id = row.ID;
	// //document.location = "../../whiteQueryDetailServlet?clsbdm="+clsbdm;
	// window.open("../../whiteQueryDetailServlet?clsbdm="+clsbdm,"newwindow","height=100,width=400");
	// //$('#dlg').dialog('open');
	// var row = $('#tt').datagrid('getSelected');
	if (row) {
		$('#fm').form('clear');
		$('#dlg').dialog('open').dialog('setTitle', '详细信息');
		$('#fm').form('load', "../../whiteQueryDetailServlet?id=" + id);
		// url = 'update_user.php?id='+row.id;
		setTimeout("IsNoPhoto();", 300); // 延时加载
	}

	function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		

}

function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}


// 显示照片
function photo() {
	var url = $('#WS').val();
	// alert(url); // 中文名 暂时不支持
	// 读取绝对路径 图片
	// $('#img_address').attr("src","Photo_open.jsp?address=" + url);
	// 显示相对路径图片
	$('#img_address').attr("src", "../../" + url);
	$('#photo_open').dialog('open').dialog('setTitle', '图片预览');
}

// 是否上传图片
function IsNoPhoto() {
	var url = $('#WS').val();
	if ("" == url || null == url) {
		$('#span').show();
		$('#photo').hide();
	} else {
		$('#span').hide();
		$('#photo').show();
	}
}