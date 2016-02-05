
var dept = [
		    {Dept_ID:'CW',name:'财务部'},
		    {Dept_ID:'XZ',name:'行政部'},
		    {Dept_ID:'RL',name:'人力资源部'},
		    {Dept_ID:'XS',name:'销售管理部'},
		    {Dept_ID:'YM',name:'域名业务部'},
		    {Dept_ID:'ZX',name:'在线运营部'},
		    {Dept_ID:'SC',name:'市场部'},
		    {Dept_ID:'KF',name:'客户服务部'},
		    {Dept_ID:'Y_FW',name:'云计算服务管理部'},
		    {Dept_ID:'Y_CP',name:'云计算产品研发部'},
		    {Dept_ID:'Y_IDC',name:'云计算IDC运营部'},
		    {Dept_ID:'FGS',name:'分公司'}
		];

var month = [
		    {Month_ID:'1',nname:'一月'},
		    {Month_ID:'2',nname:'二月'},
		    {Month_ID:'3',nname:'三月'},
		    {Month_ID:'4',nname:'四月'},
		    {Month_ID:'5',nname:'五月'},
		    {Month_ID:'6',nname:'六月'},
		    {Month_ID:'7',nname:'七月'},
		    {Month_ID:'8',nname:'八月'},
		    {Month_ID:'9',nname:'九月'},
		    {Month_ID:'10',nname:'十月'},
		    {Month_ID:'11',nname:'十一月'},
		    {Month_ID:'12',nname:'十二月'}
		];

var state = [
 		    {Month_ID1:'01',nname1:'待审批'},
 		    {Month_ID1:'02',nname1:'退回'},
 		    {Month_ID1:'03',nname1:'审批通过'},
 		    {Month_ID1:'04',nname1:'正在执行'},
 		    {Month_ID1:'05',nname1:'执行完毕'}
 		];
function initTables() {
    var editRow = undefined;
    
    $("#tests").datagrid({
        height: 400,
        width: 1100,
        title: '业务明细',
        collapsible: true,
        singleSelect: true,
        url: '../js/Home/StuList.json',
        idField: 'ID',
        columns: [[
         { field: 'ID', 	 title: 'ID', width: 60 , align: 'center', editor: { type: 'text', options: { required: true } }  },
         { field: 'Dept',	 title:'分支机构',width:120, align: 'center', editor: { type: 'text', options: { required: true } } },
		 { field: 'Month',	 title:'所属月份',width:100, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'Applyer', title: '申请人姓名', width: 100, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'Amount',  title: '费用金额', width: 100, align: 'center', editor: { type: 'numberbox', options: { required: true } } },
         { field: 'Course',  title: '费用项目', width: 130, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'Reason',  title: '事由', width: 100, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'Status',  title:'预算状态',width:100,align:'center', editor:{ type:'text', options:{ required: true} }},
         { field: 'Remark',  title: '备注', width: 180, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'status',  title:'Status',width:50,align:'center', editor:{ type:'text', options:{ required: true} } }
        ]]
    });
}


 function initTable() {
    var editRow = undefined;
 
    $("#tt").datagrid({
        height: 400,
        width: 1100,
        title: '明细录入',
        
        collapsible: true,
        singleSelect: true,
        url: './js/Home/StuList.json',
        idField: 'ID',
        columns: [[
         { field: 'ID', title: 'ID', align: 'center', width: 60  },
         {field:'Dept',title:'分支机构',width:120,
				formatter:function(value){
					for(var i=0; i<dept.length; i++){
						if (dept[i].Dept_ID == value) return dept[i].name;
					}
					return value;
				},
				editor:{
					type:'combobox',
					options:{
						valueField:'Dept_ID',
						textField:'name',
						data:dept,
						required:true
					}
				}
			},
			{field:'Month',title:'所属月份',width:100,
				formatter:function(value){
					for(var i=0; i<month.length; i++){
						if (month[i].Month_ID == value) return month[i].nname;
					}
					return value;
				},
				editor:{
					type:'combobox',
					options:{
						valueField:'Month_ID',
						textField:'nname',
						data:month,
						required:true
					}
				}
			},
         { field: 'Applyer', title: '申请人姓名', width: 100, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'Amount', title: '费用金额', width: 100, align: 'center', editor: { type: 'numberbox', options: { precision:2, required: true } } },
         { field: 'Course', title: '费用项目', width: 130, align: 'center', editor: { type: 'combotree', options: { url:'../data/course.json', required: true } } },
         { field: 'Reason', title: '事由', width: 100, align: 'center', editor: { type: 'text', options: { required: true } } },
         //{ field: 'Status', title: '预算状态', width: 100, align: 'center', editor: { type: 'combobox', options: { valueField:'id',textField:'text', url:'../data/status.json', required: true } } },
         /**/
         {field:'Status',title:'预算状态',width:100,
				formatter:function(value){
					for(var i=0; i<state.length; i++){
						if (state[i].Month_ID1 == value) return state[i].nname;
					}
					return value;
				},
				editor:{
					type:'combobox',
					options:{
						valueField:'Month_ID1',
						textField:'nname1',
						data:state,
						required:true
					}
				}
			},
          
         { field: 'Remark', title: '备注', width: 180, align: 'center', editor: { type: 'text', options: { required: true } } },
         {field:'status',title:'Status',width:50,align:'center', editor:{ type:'checkbox', options:{ on: 'P', off: '' } } }
        ]],
        toolbar: [{
            text: '添加', iconCls: 'icon-add', handler: function () {
                if (editRow != undefined) {
                    $("#tt").datagrid('endEdit', editRow);
                }
                if (editRow == undefined) {
                    $("#tt").datagrid('insertRow', {
                        index: 0,
                        row: {}
                    });
 
                    $("#tt").datagrid('beginEdit', 0);
                    editRow = 0;
                }
            }
        }, '-', {
            text: '保存', iconCls: 'icon-save', handler: function () {
                $("#tt").datagrid('endEdit', editRow);
                //如果调用acceptChanges(),使用getChanges()则获取不到编辑和新增的数据。
                //使用JSON序列化datarow对象，发送到后台。
                var rows = $("#tt").datagrid('getChanges');
                var rowstr = JSON.stringify(rows);
                $.post('/Home/Create', rowstr, function (data) {
                     
                });
            }
        }, '-', {
            text: '撤销', iconCls: 'icon-redo', handler: function () {
                editRow = undefined;
                $("#tt").datagrid('rejectChanges');
                $("#tt").datagrid('unselectAll');
            }
        }, '-', {
            text: '删除', iconCls: 'icon-del', handler: function () {
                var row = $("#tt").datagrid('getSelections');
                 
            }
        }, '-', {
            text: '修改', iconCls: 'icon-edit', handler: function () {
                var row = $("#tt").datagrid('getSelected');
                if (row !=null) {
                    if (editRow != undefined) {
                        $("#tt").datagrid('endEdit', editRow);
                    }
 
                    if (editRow == undefined) {
                        var index = $("#tt").datagrid('getRowIndex', row);
                        $("#tt").datagrid('beginEdit', index);
                        editRow = index;
                        $("#tt").datagrid('unselectAll');
                    }
                } else {
                     
                }
            }
        }, '-', {
            text: '上移', iconCls: 'icon-up', handler: function () {
                MoveUp();
            }
        }, '-', {
            text: '下移', iconCls: 'icon-down', handler: function () {
                MoveDown();
            }
        }],
        onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },
        onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#tt").datagrid('endEdit', editRow);
            }
 
            if (editRow == undefined) {
                $("#tt").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },
        onClickRow:function(rowIndex,rowData){
            if (editRow != undefined) {
                $("#tt").datagrid('endEdit', editRow);
 
            }
        }
    });
}

//上移
function MoveUp() {
    var row = $("#tt").datagrid('getSelected');
    var index = $("#tt").datagrid('getRowIndex', row);
    mysort(index, 'up', 'tt');
     
}
//下移
function MoveDown() {
    var row = $("#tt").datagrid('getSelected');
    var index = $("#tt").datagrid('getRowIndex', row);
    mysort(index, 'down', 'tt');
     
}

function mysort(index, type, gridname) {
    if ("up" == type) {
        if (index != 0) {
            var toup = $('#' + gridname).datagrid('getData').rows[index];
            var todown = $('#' + gridname).datagrid('getData').rows[index - 1];
            $('#' + gridname).datagrid('getData').rows[index] = todown;
            $('#' + gridname).datagrid('getData').rows[index - 1] = toup;
            $('#' + gridname).datagrid('refreshRow', index);
            $('#' + gridname).datagrid('refreshRow', index - 1);
            $('#' + gridname).datagrid('selectRow', index - 1);
        }
    } else if ("down" == type) {
        var rows = $('#' + gridname).datagrid('getRows').length;
        if (index != rows - 1) {
            var todown = $('#' + gridname).datagrid('getData').rows[index];
            var toup = $('#' + gridname).datagrid('getData').rows[index + 1];
            $('#' + gridname).datagrid('getData').rows[index + 1] = todown;
            $('#' + gridname).datagrid('getData').rows[index] = toup;
            $('#' + gridname).datagrid('refreshRow', index);
            $('#' + gridname).datagrid('refreshRow', index + 1);
            $('#' + gridname).datagrid('selectRow', index + 1);
        }
    }
}
