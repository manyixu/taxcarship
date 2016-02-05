function initTables(str) { 
	var number = 78 ;
    var top_width = window.innerWidth;
	var left_width = window.innerHeight;
    $("#ywjk").datagrid({
        height: 300,
        width: 1300,
        title: '业务明细',
        collapsible: true,
        singleSelect: true,
        rownumbers:true ,
        loadMsg: '' ,
        url: '../../ywjk',
        idField: 'ID',
        columns: [[
         { field: 'PAYCOMPANYCODE',hidden:str[0],title: '代收公司', width: number , align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'VIN', hidden:str[1],title:'车辆识别码',width:number, align: 'center', editor: { type: 'text', options: { required: true } } },
		 { field: 'HPHM',hidden:str[2], title:'号牌号码',width:number, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'CLLX',hidden:str[3], title: '车辆类型', width: number, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'TAXCONDITIONCODE',  hidden:str[4],title: '纳税类型', width: number, align: 'center', editor: { type: 'numberbox', options: { required: true } } },
         { field: 'TAXSTARTDATE',  hidden:str[5],title: '税款始起', width: number, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'TAXENDDATE', hidden:str[6], title: '税款止起', width: number, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'ANNUALTAXAMOUNT',  hidden:str[7],title:'单位税额',width:number,align:'center', editor:{ type:'text', options:{ required: true} }},
         { field: 'UNITRATE',  hidden:str[8],title: '年度税额', width: number, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'PAYDATE',  hidden:str[9],title:'所属年度',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'ANNUALTAXDUE',  hidden:str[10],title:'实缴税款',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'SUMTAXDEFAULT', hidden:str[11], title:'合计欠税',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'SUMOVERDUE',  hidden:str[12],title:'滞纳金',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'SUMTAX',  hidden:str[13],title:'合计金额',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'TAXDEPARTMENTCODE',  hidden:str[14],title:'完税机关代码',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'TAXDEPARTMENT',  hidden:str[15],title:'完税机关名称',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'TAXDOCUMENTNUMBER',  hidden:str[16],title:'完税凭证号码',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'DEDUCTIONDEPARTMENTCODE',  hidden:str[17],title:'减免税机关代码',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'DEDUCTIONDEPARTMENT',  hidden:str[18],title:'减免税机关名称',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'DEDUCTIONDUECODE',  hidden:str[19],title:'减免税原因代码',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'DEDUCTIONDUETYPE',  hidden:str[20],title:'减免税方案代码',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'DEDUCTIONDUEPROPORTION',  hidden:str[21],title:'减免比例',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'DEDUCTION',  hidden:str[22],title:'减免金额',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'DEDUCTIONDOCUMENTNUMBER',  hidden:str[23],title:'减免税凭证号',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         { field: 'REFRESH', hidden:true,title:'标记',width:number,align:'center', editor:{ type:'text', options:{ required: true} } },
         ]],
        toolbar:[{
        	text : '选择显示列',iconCls:'icon-add',handler: function () {
        		var all_field = $('#ywjk').datagrid('getColumnFields',false) ;   	// 返回所有列信息
        		var strings = '' ;	//	hidden 属性
        		var titles = '' ;	//  title 属性
        		var i = 0 ;
        		for(; i < all_field.length ;){
        			var date = $('#ywjk').datagrid('getColumnOption',all_field[i]) ; 		// 返回特定的列属性
        			strings += date.hidden + "," ;
        			titles += date.title + "," ;
        			i += 1 ;
        		}
        		strings = strings.substring(0, strings.length - 1);
        		titles = titles.substring(0, titles.length - 1);
        		meunwin(strings,titles);
            }
        }],
        rowStyler:function(index,row){
    		// 反色条 
//    		if(row.REFRESH == 'N'){
//    			if(index == 0){
//    				return 'background-color:#FF0000';
//    			}else{
//    				return 'background-color:#FFFFFF';
//    			}
//    		}else{
//    			if(index == 1){
//    				return 'background-color:#FF0000';
//    			}else{
//    				return 'background-color:#FFFFFF';
//    			}
//    		}
		},
		
//		rowStyler:function(index,row){
//    		return 'background-color:#FFFFFF';
//		},
        onClickRow:function(row){
			// 单击事件
    	},
       
        onDblClickRow:function(){
    		// 双击 明细窗口
    		openwin();	
		}
    });
//    fetchData(str);
}
// 明细
function openwin(){
	var row = $("#ywjk").datagrid('getSelected');
	var long_tr = row.PAYCOMPANYCODE + '@' + row.VIN + '@' + row.HPHM + '@' + row.CLLX + '@' + row.TAXSTARTDATE + '@' + row.TAXENDDATE + '@' + row.TAXCONDITIONCODE
	+ '@' + row.ANNUALTAXAMOUNT + '@' + row.UNITRATE + '@' + row.PAYDATE + '@' + row.ANNUALTAXDUE + '@' + row.SUMTAXDEFAULT + '@' + row.SUMOVERDUE 
	+ '@' + row.SUMTAX + '@' + row.TAXDEPARTMENTCODE + '@' + row.TAXDEPARTMENT + '@' + row.TAXDOCUMENTNUMBER + '@' + row.DEDUCTIONDEPARTMENTCODE + '@' + row.DEDUCTIONDEPARTMENT + '@' + row.DEDUCTIONDUECODE
	+ '@' + row.DEDUCTIONDUETYPE + '@' + row.DEDUCTIONDUEPROPORTION + '@' + row.DEDUCTION + '@' + row.DEDUCTIONDOCUMENTNUMBER;
	var shuxing = ',toolbar = no,menuber = no,scrollbars = no,Resizable = no,location = no,status = no,titebar = yes' ;
	var wid_width = 800 ;		// 宽
	var wid_height = 400 ;		// 高
	var top_width = (window.innerWidth - wid_width) / 2;
	var left_width = (window.innerHeight - wid_height) / 2;
	var url = '../jsp/YeWu/yewu.jsp?trContent=' + long_tr;			// 新的jsp明细页面
	var parameters = 'width = '+ wid_width +',height = '+ wid_height +',top = '+ top_width +',left = '+ left_width + shuxing;
	window.open(url,'业务明细',parameters);
}

//菜单
function meunwin(strings,titles){
//	$.ajax({
//        url: "../../column",
//        type: "post",
//        data:{columns:strings},
//        beforeSend:function(){
//          return true;
//        },
//        success:function(result){
//        	var shuxing = ',toolbar = no,menuber = no,scrollbars = no,Resizable = no,location = no,status = no,titebar = yes' ;
//        	var wid_width = 800 ;		// 宽
//        	var wid_height = 400 ;		// 高
//        	var top_width = (window.innerWidth - wid_width) / 2;
//        	var left_width = (window.innerHeight - wid_height) / 2;
//        	var url = '../jsp/YeWu/meun.jsp?columns=' + result;			// 新的jsp选择列页面
//        	var parameters = 'width = '+ wid_width +',height = '+ wid_height +',top = '+ top_width +',left = '+ left_width + shuxing;
//        	var str = window.open(url,'选择页',parameters);
//        },
//        error:function(){
//        }
//     });
	
//	var row = $("#ywjk").datagrid('getSelected');
//	var long_tr = row.VIN + '@' + row.AnnualTaxAmount ;
	var shuxing = ',toolbar = no,menuber = no,scrollbars = no,Resizable = no,location = no,status = no,titebar = yes' ;
	var wid_width = 800 ;		// 宽
	var wid_height = 400 ;		// 高
	var top_width = (window.innerWidth - wid_width) / 2;
	var left_width = (window.innerHeight - wid_height) / 2;
	var url = '../jsp/YeWu/meun.jsp?Columns=' + strings + '&Title=' + titles;			// 新的jsp选择列页面
	var parameters = 'width = '+ wid_width +',height = '+ wid_height +',top = '+ top_width +',left = '+ left_width + shuxing;
	window.open(url,'选择页',parameters);
}
/**刷新时*/
function shua(){
	var all_field = $('#ywjk').datagrid('getColumnFields',false) ;   	// 返回所有列信息
	var i = 0 ;
	var str = [] ;
	for(; i < all_field.length ;){
		var date = $('#ywjk').datagrid('getColumnOption',all_field[i]) ; 		// 返回特定的列属性
		str[i] = date.hidden
		i += 1 ;
	}
	initTables(str);
}

/**初始化*/
function init(){
	$.ajax({
		url : '../../getColumn',
		type : 'post' ,
		data : {},
		beforeSend:function(){
           return true;
        },
		success:function(result){
        	if(result != null){
        		initTables(result);
        	}else{
        		$.messager.alert('系统提示','加载列信息出错');
        	}
        },
        error:function(){
        }
	})
}
/**加载列信息*/
function fetchData(str) {  
    var s = "";  
    s = "[[";  
//    s = s + "{ field: 'PAYCOMPANYCODE',title: '代收公司', width: 80 , align: 'center', editor: { type: 'text', options: { required: true } } },";  
    s = s + str ;
    s = s + "]]";  
    options = {};  
    options.columns = eval(s);  
    $('#ywjk').datagrid(options);  
    $('#ywjk').datagrid('reload');     
}
/**权限过滤*/
function initCheck(){
	$.messager.prompt('访问权限口令认证','请输入访问权限口令：', function(r){
		/**  post 请求 */
//		$.post('../../preCheck_password',{password:r} , function(result){
//			if(result.success){
//				initTable();
////				$.messager.alert("系统提示","success");
//			}else{
//				$.messager.alert("系统提示",result.errorMsg,'',function(){
//					preCheck();
//				});
//			}
//		},'json');
		
		/**  ajax 请求 */
		$.ajax({
			url : '../../preCheck_password',
			dataType : 'json' ,
			type : 'post' ,
			data : {'password':r},
			beforeSend:function(){
	           return true;
	        },
			success:function(result){
//	        	var obj = eval("(" + result + ")");
//	        	alert(obj.success);
	        	$.each(result,function(index,value){
	        		if(value == 'true'){
	        			initTables([]);
	        			initTable();
	        		}else{
		        		$.messager.alert("系统提示",value,'',function(){
		        			initCheck();
		        		});
	        		}
	        	});
	        },
	        error:function(){
	        }
		});
    });
}