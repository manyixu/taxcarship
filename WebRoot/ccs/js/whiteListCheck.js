/**
 * @author MILI
 * @time 2014-12-3 08:47:55
 * @描述：待审批数据操作JS
 * */ 
function initTable() {
	var number = 150;
	$("#examine").datagrid( {
		height : 340,
		width : 1100,
		title : '问题名单待审核任务池',
		collapsible : true,
		singleSelect : false,   // 选择框是否能复选  false 能  true 否
		loadMsg : false,
		pagination : true,
		url : '../../white_examine',
		idField : 'ID',
		columns : [ [ {
			field : 'R',
			title : '〇',
			checkbox:true
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
			field : 'HEJE',
			title : '已缴税额',
			width : number,
			align : 'center',
			editor : {
				type : 'combotree',
				options : {
					url : '../data/course.json',
					required : true
				}
			}
		}, {
			field : 'WSPZHM',
			title : '完税凭证',
			width : number,
			align : 'center',
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
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
		} , {
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
		}]],
		// 双击事件监听
		onClickRow : function(rowIndex) {
			// 清空所有选项
		 	$("#examine").datagrid('unselectAll');
		 	// 默认选择当前行
			$("#examine").datagrid('selectRow',rowIndex);
			var row = $("#examine").datagrid('getSelected');
			if(row != null){
				$('#win').form('clear');   // 清空上次所有记录
				$('#win').dialog('open').dialog('setTitle','审核界面');
				$('#xx').form('load',"../../white_xx?clsbdm="+row.CLSBDM);
				$("#examine").datagrid('unselectAll');
  				setTimeout("IsNoPhoto();", 300);  // 延时加载 
			}
		}
	});
	// 分页查询
	$('#examine').datagrid('getPager').pagination({ 
        pageSize: 10,			//每页显示的记录条数，默认为10 
        pageList: [5,10,15],	//可以设置每页记录条数的列表 
        beforePageText: '第',	//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
}
//批量通过
function piliang(){
	var i = 0 ;
	var strings = '' ;	//	
	var all = $('#examine').datagrid('getChecked');
	if(all != null && all.length > 0){
		for(; i < all.length ;){
			strings += all[i].CLSBDM + ",";
			i += 1 ;
		}
		// ajax 请求
		$.ajax({
			url: "../../white_pl",
			type: "post",
			data:{columns:strings},
			beforeSend:function(){
				return true;
			},
			success:function(result){
				if(result == "null"){
					$.messager.alert('批量审批','成功','info');
				}else{
					$.messager.alert('批量审批',result,'info');
				}
				$("#examine").datagrid('unselectAll');
				$("#examine").datagrid('reload'); // 重新加载
			},
			error:function(){
				$.messager.alert('单条审批',"错错错 是我的错。。。",'info');
			}
		});
	}else{
		$.messager.alert('批量通过','能不能先选择 在点击 (很生气的说:)','info');
	}
}
// 单条提交
function submit_ok(){
	var clsbdm = $('#clsbdm').val();
	var textString = $('#textstring').val();
	var pass_no = $('#pass').val();
	if(pass_no == "nogo" && "" == textString.trim()){
		$.messager.alert('单条审批','审批失败原因不能为空','info');
	}else{
		// ajax 请求
		$.ajax({
			url: "../../white_dt",
			type: "post",
			data:{clsbdm:clsbdm,account:textString,pass_no:pass_no},
			beforeSend:function(){
				return true;
			},
			success:function(result){
				if(result == "null"){
					$.messager.alert('单条审批','提交成功','info',function(){
						$('#win').dialog('close');	// 关闭
					});
				}else{
					$.messager.alert('单条审批',result,'info');
				}
				$("#examine").datagrid('unselectAll');	// 清空所有选项
				$("#examine").datagrid('reload');  // 重新加载
			},
			error:function(){
				$.messager.alert('单条审批',"错错错 是我的错。。。",'info');
			}
		});
	}
}
// 条件查询
function query(){
	 $('#examine').datagrid('reload', {
		tartdate : $("#dd").datebox('getValue'),
		enddate : $("#dt").datebox('getValue')
	 });
}
// 时间控件
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
// 显示照片
function photo(){
	var url = $('#WSPZ').val();
//	alert(url);   // 中文名 暂时不支持
	// 读取绝对路径 图片
//	$('#img_address').attr("src","Photo_open.jsp?address=" + url);
	// 显示相对路径图片
	$('#img_address').attr("src","../../" + url);
	$('#photo_open').dialog('open').dialog('setTitle','图片预览');
}
// 是否上传图片
function IsNoPhoto(){
	var url = $('#WSPZ').val();
	// 显示 隐藏 图片超链接
	if("" == url || null == url){
		$('#span').show();  
		$('#photo').hide();
	}else{
		$('#span').hide();
		$('#photo').show();
	}
}