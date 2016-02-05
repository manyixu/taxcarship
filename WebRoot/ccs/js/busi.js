function initTable() {
	var editRow = undefined;

	$("#tt").datagrid( {
		height : 'auto',
		width : 'auto',
		title : '业务查询',
		collapsible : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,//每页显示的条数
		pageList : [ 5, 10, 15, 20 ],
		loadMsg : '数据加载中...',
		url : '../../busiQueryServlets',
		rownumbers : true,
		//queryParams: queryData,　
		idField : 'ID',
		columns : [ [
		//{ field: 'id', title: 'ID', align: 'center', width: 80 },
				{
					field : 'TAXQUERYNO',
					title : '车船税交易码',
					width : '250',
					align : 'center',
					halign : 'center'
				}, {
					field : 'VIN',
					title : 'vin码',
					width : '180',
					align : 'center'
				}, {
					field : 'TAXPAYERNAME',
					title : '纳税人姓名',
					width : '80',
					align : 'center'
				}, {
					field : 'TAXLOCATIONCODE',
					title : '纳税地区代码',
					width : '100',
					align : 'center'
				}, {
					field : 'EngineNo',
					title : '发动机号',
					width : '100',
					align : 'center'
				}, {
					field : 'TAXCONDITIONCODE',
					title : '纳税类型',
					width : '80',
					align : 'center'
				},
		// { field: 'TOTALAMOUNT', title: '合计金额', width: '100', align: 'center' },
		//{ field: 'SJCJRQ', title: '系统采集日期', width: '180', align: 'center' }

		// { field: 'Remark', title: '备注', width: 180, align: 'center', editor: { type: 'text', options: { required: true } } }
		] ],
		toolbar : [ "-", {

			text : 'vin码:<input type="text" id="vin" />'
		//  iconCls: 'icon-search'

				}, "-", {
					text : '交易码:<input type="text" id="taxqueryno" />'

				}, "-", {
					text : '车牌号:<input type="text" id="cph" />'
				}, "-", {

					text : '交易码类型:<input type="text" id="jym" />'

				}, "-", {
					id : "query",
					text : '查询',
					handler : function() {
						bindSearchEvent();
					}
				} ],
			onDblClickRow : function() {

			detail();
		}
	});

	$('#tt').datagrid('getPager').pagination( {
		beforePageText : '第',
		afterPageText : '页',
		displayMsg : '当前显示第{from}到{to},共{total}条记录'

	});

}

function bindSearchEvent() {
	$("#query").click(function() {
		var queryData = {
			vin : $("#vin").val(),
			taxqueryno : $("#taxqueryno").val(),
			cph : $("#cph").val(),
			jym : $("#jym").val()
		}
		$('#tt').datagrid('reload', {
			vin : $("#vin").val(),
			taxqueryno : $("#taxqueryno").val(),
			cph : $("#cph").val(),
			jym : $("#jym").val()
		});
	});
}

function detail() {
	var row = $("#tt").datagrid('getSelected');
	var vin = row.VIN;
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	var path = curPath.substring(0, curPath.indexOf(pathName));
	var url = path + projectName + "/busiPageQueryServlet?vin=" + vin;
	//    	     var  url = '../jsp/busiMon.jsp'
	// var row = $("#tt").datagrid('getSelected');
	// var vin = row.VIN;
	//detail(url,row);
	//$.post(url,{vin:row.VIN},function(result){
	//	var str="";
	//  if(result){

	// }
	// }
	// )

	$("#di").datagrid( {
		height : 'auto',
		width : 'auto',
		title : '详细信息',
		collapsible : true,
		singleSelect : true,
		//pagination:true,
		//pageSize:10,//每页显示的条数
		//pageList:[5,10,15,20],
		loadMsg : '数据加载中...',
		url : url,
		// rownumbers:true,
		// queryParams: queryData,　
		idField : 'ID',
		columns : [ [
		//{ field: 'id', title: 'ID', align: 'center', width: 80 },
				{
					field : 'TAXQUERYNO',
					title : '车船税交易码',
					width : '250',
					align : 'center',
					halign : 'center'
				}, {
					field : 'VIN',
					title : 'vin码',
					width : '180',
					align : 'center'
				}, {
					field : 'TAXPAYERNAME',
					title : '纳税人姓名',
					width : '80',
					align : 'center'
				}, {
					field : 'TAXLOCATIONCODE',
					title : '纳税地区代码',
					width : '100',
					align : 'center'
				}, {
					field : 'EngineNo',
					title : '发动机号',
					width : '100',
					align : 'center'
				}, {
					field : 'TAXCONDITIONCODE',
					title : '纳税类型',
					width : '80',
					align : 'center'
				}, {
					field : 'SUMTAX',
					title : '合计金额',
					width : '100',
					align : 'center'
				}, {
					field : 'SJCJRQ',
					title : '系统采集日期',
					width : '180',
					align : 'center'
				},

				{
					field : 'CLLX',
					title : '备注',
					width : 180,
					align : 'center',
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				} ] ]

	});

}