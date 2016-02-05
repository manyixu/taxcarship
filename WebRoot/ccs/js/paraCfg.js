function initTable() {
	var editRow = undefined;

	$("#tt").datagrid( {
		height : 'auto',
		width : 'auto',
		title : '参数配置',
		collapsible : true,
		singleSelect : true,
		pagination : true,
		idField : 'ID',
		pageSize : 10,// 每页显示的条数
		pageList : [ 5, 10, 15, 20 ],
		loadMsg : '数据加载中...',
		url : '../../paraCfgServlet',
		rownumbers : true,
		columns : [ [
		// { field: 'ID', title: 'ID', align: 'center', width: 60 },
				{
					field : 'CODE',
					title : '系统代码',
					width : 100,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'CODEALIA',
					title : '系统代码别名',
					width : 100,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'CODENAME',
					title : '系统代码名称',
					width : 300,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'ODETYPE',
					title : '系统代码类型',
					width : 100,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'CODEVALUE',
					title : '对应值',
					width : 100,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				},

				{
					field : 'ISHOTPARA',
					title : '是否热配置',
					width : 100,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'REMARK',
					title : '说明',
					width : 100,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'VALIDATEFLAG',
					title : '是否有效标识',
					width : 100,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				} ] ],
		toolbar : [ "-", {

			text : '系统代码:<input type="text" id="code" />'
		// iconCls: 'icon-search'

				}, "-", {
					text : '系统代码类型:<input type="text" id="codeType" />'

				}, "-", {
					text : '是否热配置:<input type="text" id="idhotp" />'
				}, "-", {

					text : '是否有效标识:<input type="text" id="validateflag" />'

				}, "-", {
					id : "query",
					text : '查询',
					handler : function() {
						bindSearchEvent();
					}
				} ]

	});
	$('#tt').datagrid('getPager').pagination( {
		beforePageText : '第',
		afterPageText : '页',
		displayMsg : '当前显示第{from}到{to},共{total}条记录'
	});

}
// 参数配置 密码验证
function preCheck() {
	$.messager.prompt('访问权限口令认证', '请输入访问权限口令：', function(r) {
		if (r == '1234') {
			initTable();
		} else {
			$.messager.alert("系统提示", "密码不正确");
		}
	});
}
// 
function bindSearchEvent() {
//	$("#query").click(function() {
//		var queryData = {
//			code : $("#code").val(),
//			codeType : $("#codeType").val(),
//			idhotp : $("#idhotp").val(),
//			validateflag : $("#validateflag").val()
//		}
	$('#tt').datagrid('reload', {
		code : $("#code").val(),
		codeType : $("#codeType").val(),
		idhotp : $("#idhotp").val(),
		validateflag : $("#validateflag").val()
	});
//	});
}
