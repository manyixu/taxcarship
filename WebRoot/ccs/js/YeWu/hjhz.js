
 function initTable() {
	var number = 214 ;
    $("#hjhz").datagrid({
        height: 100,
        width: 1300,
        title: '合计汇总',
        collapsible: true,
        singleSelect: true,
        loadMsg : false ,
        url: '../../hjhz',
        idField: 'ID',
        columns: [[
         { field: 'Date_Number', title: '本日交易数', width: number,align: 'center' ,editor: { type: 'text', options: { required: true } } },
         { field: 'Date_Money', title: '本日税款数', width: number, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'Month_Number', title: '本月交易数', width: number, align: 'center', editor: { type: 'numberbox', options: { precision:2, required: true } } },
         { field: 'Month_Money', title: '本月税款数', width: number, align: 'center', editor: { type: 'combotree', options: { url:'../data/course.json', required: true } } },
         { field: 'Year_Number', title: '本年交易数', width: number, align: 'center', editor: { type: 'text', options: { required: true } } },
         { field: 'Year_Money', title: '本年税款数', width: number, align: 'center', editor: { type: 'text', options: { required: true } } }
        ]]
//       onDblClickRow:function(){
//        	alert('sdf');
//    	}
    });
    
}