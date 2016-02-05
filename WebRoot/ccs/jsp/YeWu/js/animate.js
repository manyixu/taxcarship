var speed = 50;		// 动作时间
var freshFlag = false ;		// 是否自动刷新
var MyMar ;	// 刷新对象
var freshMy = 5000 ;	// 默认刷新时间
var animate_id = 1 ;
var musicFlag = true ;		// 是否有声音提示
var menuFlag = true ;		// 隐藏显示 菜单栏
// 业务详细明细
function onj(number){
	var Strings = "" ;
	var colums = "" ;
	var td = document.getElementById(number);
	var rows = td.rows ;
	for(var i = 0 ; i < rows.length ;i++){
		var uu = rows[i].cells.length ;
		for(var j = 0 ; j < uu ; j++){
			var inner = rows[i].cells[j].innerHTML ;
			Strings += inner.replace("&nbsp;", "") + "@" ;
		}
	}
	// 取得列信息
	var td = document.getElementById('ttable');
	var rows = td.rows ;
	for(var i = 0 ; i < rows.length ;i++){
		var uu = rows[i].cells.length ;
		for(var j = 0 ; j < uu ; j++){
			var inner = rows[i].cells[j].innerHTML ;
			colums += inner + "@" ;
		}
	}
	// 设置窗口信息
	var shuxing = ',toolbar = no,menuber = no,scrollbars = no,Resizable = no,location = no,status = no,titebar = yes' ;
	var wid_width = 1100 ;		// 宽
	var wid_height = 600 ;		// 高
	var top_width = (window.innerWidth - wid_width) / 2;
	var left_width = (window.innerHeight - wid_height) / 2;
	var url = 'ccs/jsp/YeWu/mingxi_row.jsp?strings=' + Strings +'&colums=' + colums;			// 新的jsp明细页面
	var parameters = 'width = '+ wid_width +',height = '+ wid_height +',top = '+ top_width +',left = '+ left_width + shuxing;
	window.open(url,'业务明细',parameters);
}
// 手自一体 刷新
function auto(){
	if(freshFlag){
		document.getElementById('sx').innerHTML = '手动刷新' ;
		clearInterval(MyMar);
		freshFlag = false ;
	}else{
		freshFlag = true ;
		document.getElementById('sx').innerHTML = '自动刷新' ;
		MyMar = setInterval("yewu();hjhz();",freshMy);
	}
}
// 刷新时间选择
function option(){
	var id = document.getElementById('select_o').value ;
	if(id == 3){
		freshMy = 3000 ;
	}else if(id == 5){
		freshMy = 5000 ;
	}else if(id == 8){
		freshMy = 8000 ;
	}
	if(freshFlag){
		clearInterval(MyMar);
		MyMar = setInterval("yewu();hjhz();",freshMy);
	}
}
// 刷新动画 同时
function init_tb(){
	music_M();
	double0();
	double1();
	double2();
	double3();
	double4();
	double5();
	double6();
	double7();
	double8();
	double9();
	double10();
	double11();
	yewu_b();
}
// 刷新动画 渐变
function init_jb(){
	double0();
	setTimeout("double1()", 500);
	setTimeout("double2()", 1000);
	setTimeout("double3()", 1500);
	setTimeout("double4()", 2000);
	setTimeout("double5()", 2500);
	setTimeout("double6()", 3000);
	setTimeout("double7()", 3500);
	setTimeout("double8()", 4000);
	setTimeout("double9()", 4500);
	setTimeout("double10()", 5000);
	setTimeout("double11()", 5500);
	yewu_b();
}
// 刷新动画 循序
function init_sx(){
	double0();
	setTimeout("double1()", 1000);
	setTimeout("double2()", 2000);
	setTimeout("double3()", 3000);
	setTimeout("double4()", 4000);
	setTimeout("double5()", 5000);
	setTimeout("double6()", 6000);
	setTimeout("double7()", 7000);
	setTimeout("double8()", 8000);
	setTimeout("double9()", 9000);
	setTimeout("double10()", 10000);
	setTimeout("double11()", 11000);
	yewu_b();
}
function double0() {
//	var speed = 100;
	var MyMarh = setInterval(Marqueeh, speed);
	demo3.innerHTML = demo2.innerHTML ;
	demo1.scrollTop = demo1.scrollHeight ;
	function Marqueeh() {
		demo1.scrollTop--;
		if(demo1.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double1(){
//	var speed = 100;
	var MyMarh = setInterval(Marqueeh, speed);
	demo6.innerHTML = demo5.innerHTML ;
	demo4.scrollTop = demo4.scrollHeight ;
	function Marqueeh() {
		demo4.scrollTop-- ;
		if(demo4.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double2(){
//	var speed = 100;
	var MyMarh = setInterval(Marqueeh, speed);
	demo9.innerHTML = demo8.innerHTML ;
	demo7.scrollTop = demo7.scrollHeight ;
	function Marqueeh() {
		demo7.scrollTop-- ;
		if(demo7.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double3(){
///	var speed = 100;
	var MyMarh = setInterval(Marqueeh, speed);
	demo12.innerHTML = demo11.innerHTML ;
	demo10.scrollTop = demo10.scrollHeight ;
	function Marqueeh() {
		demo10.scrollTop-- ;
		if(demo10.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double4(){
///	var speed = 100;
	var MyMarh = setInterval(Marqueeh, speed);
	demo15.innerHTML = demo14.innerHTML ;
	demo13.scrollTop = demo13.scrollHeight ;
	function Marqueeh() {
		demo13.scrollTop-- ;
		if(demo13.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double5(){
//	var speed = 100;
	var MyMarh = setInterval(Marqueeh, speed);
	demo18.innerHTML = demo17.innerHTML ;
	demo16.scrollTop = demo16.scrollHeight ;
	function Marqueeh() {
		demo16.scrollTop-- ;
		if(demo16.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double6(){
//	var speed = 50;
	var MyMarh = setInterval(Marqueeh, speed);
	demo21.innerHTML = demo20.innerHTML ;
	demo19.scrollTop = demo19.scrollHeight ;
	function Marqueeh() {
		demo19.scrollTop-- ;
		if(demo19.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double7(){
//	var speed = 50;
	var MyMarh = setInterval(Marqueeh, speed);
	demo24.innerHTML = demo23.innerHTML ;
	demo22.scrollTop = demo22.scrollHeight ;
	function Marqueeh() {
		demo22.scrollTop-- ;
		if(demo22.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double8(){
//	var speed = 50;
	var MyMarh = setInterval(Marqueeh, speed);
	demo27.innerHTML = demo26.innerHTML ;
	demo25.scrollTop = demo25.scrollHeight ;
	function Marqueeh() {
		demo25.scrollTop-- ;
		if(demo25.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double9(){
//	var speed = 50;
	var MyMarh = setInterval(Marqueeh, speed);
	demo30.innerHTML = demo29.innerHTML ;
	demo28.scrollTop = demo28.scrollHeight ;
	function Marqueeh() {
		demo28.scrollTop-- ;
		if(demo28.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double10(){
//	var speed = 50;
	var MyMarh = setInterval(Marqueeh, speed);
	demo33.innerHTML = demo32.innerHTML ;
	demo31.scrollTop = demo31.scrollHeight ;
	function Marqueeh() {
		demo31.scrollTop-- ;
		if(demo31.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double11(){
//	var speed = 50;
	var MyMarh = setInterval(Marqueeh, speed);
	demo36.innerHTML = demo35.innerHTML ;
	demo34.scrollTop = demo34.scrollHeight ;
	function Marqueeh() {
		demo34.scrollTop-- ;
		if(demo34.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double12(){
//	var speed = 50;
	var MyMarh = setInterval(Marqueeh, speed);
	demo39.innerHTML = demo38.innerHTML ;
	demo37.scrollTop = demo37.scrollHeight ;
	function Marqueeh() {
		demo37.scrollTop-- ;
		if(demo37.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double13(){
//	var speed = 50;
	var MyMarh = setInterval(Marqueeh, speed);
	demo42.innerHTML = demo41.innerHTML ;
	demo40.scrollTop = demo40.scrollHeight ;
	function Marqueeh() {
		demo40.scrollTop-- ;
		if(demo40.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
function double14(){
//	var speed = 50;
	var MyMarh = setInterval(Marqueeh, speed);
	demo45.innerHTML = demo44.innerHTML ;
	demo43.scrollTop = demo43.scrollHeight ;
	function Marqueeh() {
		demo43.scrollTop-- ;
		if(demo43.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
/**合计汇总*/
function anim_hjhz() {
//	var speed = 100;
	var MyMarh = setInterval(Marqueeh, speed);
	hjhz2.innerHTML = hjhz1.innerHTML ;
	hjhz0.scrollTop = hjhz0.scrollHeight ;
	function Marqueeh() {
		hjhz0.scrollTop--;
		if(hjhz0.scrollTop == 0){
			clearInterval(MyMarh);
		}
	}
}
// 业务明细
function yewu_b(){
	var n = 1;
	var m = 0;
	for(var k = 0 ; k < 12 ; k++){
		var td = document.getElementById('table' + n);	// 显示
		var _td = document.getElementById('table' + m);	// 隐藏
		var _rows = _td.rows ;
		for(var i = 0 ; i < _rows.length ;i++){
			_td.rows.item(i).cells[0].innerHTML = td.rows.item(i).cells[0].innerHTML ;
			_td.rows.item(i).cells[1].innerHTML = td.rows.item(i).cells[1].innerHTML ;
			_td.rows.item(i).cells[2].innerHTML = td.rows.item(i).cells[2].innerHTML ;
			_td.rows.item(i).cells[3].innerHTML = td.rows.item(i).cells[3].innerHTML ;
			_td.rows.item(i).cells[4].innerHTML = td.rows.item(i).cells[4].innerHTML ;
			_td.rows.item(i).cells[5].innerHTML = td.rows.item(i).cells[5].innerHTML ;
			_td.rows.item(i).cells[6].innerHTML = td.rows.item(i).cells[6].innerHTML ;
			_td.rows.item(i).cells[7].innerHTML = td.rows.item(i).cells[7].innerHTML ;
			_td.rows.item(i).cells[8].innerHTML = td.rows.item(i).cells[8].innerHTML ;
			_td.rows.item(i).cells[9].innerHTML = td.rows.item(i).cells[9].innerHTML ;
			_td.rows.item(i).cells[10].innerHTML = td.rows.item(i).cells[10].innerHTML ;
			_td.rows.item(i).cells[11].innerHTML = td.rows.item(i).cells[11].innerHTML ;
			_td.rows.item(i).cells[12].innerHTML = td.rows.item(i).cells[12].innerHTML ;
			_td.rows.item(i).cells[13].innerHTML = td.rows.item(i).cells[13].innerHTML ;
			_td.rows.item(i).cells[14].innerHTML = td.rows.item(i).cells[14].innerHTML ;
			_td.rows.item(i).cells[15].innerHTML = td.rows.item(i).cells[15].innerHTML ;
			_td.rows.item(i).cells[16].innerHTML = td.rows.item(i).cells[16].innerHTML ;
			_td.rows.item(i).cells[17].innerHTML = td.rows.item(i).cells[17].innerHTML ;
			_td.rows.item(i).cells[18].innerHTML = td.rows.item(i).cells[18].innerHTML ;
			_td.rows.item(i).cells[19].innerHTML = td.rows.item(i).cells[18].innerHTML ;
			_td.rows.item(i).cells[20].innerHTML = td.rows.item(i).cells[20].innerHTML ;
			_td.rows.item(i).cells[21].innerHTML = td.rows.item(i).cells[21].innerHTML ;
			_td.rows.item(i).cells[22].innerHTML = td.rows.item(i).cells[22].innerHTML ;
			_td.rows.item(i).cells[23].innerHTML = td.rows.item(i).cells[23].innerHTML ;
			_td.rows.item(i).cells[24].innerHTML = td.rows.item(i).cells[24].innerHTML ;
			_td.rows.item(i).cells[25].innerHTML = td.rows.item(i).cells[25].innerHTML ;
			_td.rows.item(i).cells[26].innerHTML = td.rows.item(i).cells[26].innerHTML ;
			_td.rows.item(i).cells[27].innerHTML = td.rows.item(i).cells[27].innerHTML ;
			_td.rows.item(i).cells[28].innerHTML = td.rows.item(i).cells[28].innerHTML ;
			_td.rows.item(i).cells[29].innerHTML = td.rows.item(i).cells[29].innerHTML ;
			_td.rows.item(i).cells[30].innerHTML = td.rows.item(i).cells[30].innerHTML ;
			_td.rows.item(i).cells[31].innerHTML = td.rows.item(i).cells[31].innerHTML ;
			_td.rows.item(i).cells[32].innerHTML = td.rows.item(i).cells[32].innerHTML ;
			_td.rows.item(i).cells[33].innerHTML = td.rows.item(i).cells[33].innerHTML ;
			_td.rows.item(i).cells[34].innerHTML = td.rows.item(i).cells[34].innerHTML ;
			_td.rows.item(i).cells[35].innerHTML = td.rows.item(i).cells[35].innerHTML ;
			_td.rows.item(i).cells[36].innerHTML = td.rows.item(i).cells[36].innerHTML ;
			_td.rows.item(i).cells[37].innerHTML = td.rows.item(i).cells[37].innerHTML ;
			_td.rows.item(i).cells[38].innerHTML = td.rows.item(i).cells[38].innerHTML ;
			_td.rows.item(i).cells[39].innerHTML = td.rows.item(i).cells[39].innerHTML ;
			_td.rows.item(i).cells[40].innerHTML = td.rows.item(i).cells[40].innerHTML ;
			_td.rows.item(i).cells[41].innerHTML = td.rows.item(i).cells[41].innerHTML ;
			_td.rows.item(i).cells[42].innerHTML = td.rows.item(i).cells[42].innerHTML ;
			_td.rows.item(i).cells[43].innerHTML = td.rows.item(i).cells[43].innerHTML ;
			_td.rows.item(i).cells[44].innerHTML = td.rows.item(i).cells[44].innerHTML ;
			_td.rows.item(i).cells[45].innerHTML = td.rows.item(i).cells[45].innerHTML ;
			_td.rows.item(i).cells[46].innerHTML = td.rows.item(i).cells[46].innerHTML ;
			_td.rows.item(i).cells[47].innerHTML = td.rows.item(i).cells[47].innerHTML ;
			_td.rows.item(i).cells[48].innerHTML = td.rows.item(i).cells[48].innerHTML ;
			_td.rows.item(i).cells[49].innerHTML = td.rows.item(i).cells[49].innerHTML ;
			_td.rows.item(i).cells[50].innerHTML = td.rows.item(i).cells[50].innerHTML ;
			_td.rows.item(i).cells[51].innerHTML = td.rows.item(i).cells[51].innerHTML ;
			_td.rows.item(i).cells[52].innerHTML = td.rows.item(i).cells[52].innerHTML ;
			_td.rows.item(i).cells[53].innerHTML = td.rows.item(i).cells[53].innerHTML ;
			_td.rows.item(i).cells[54].innerHTML = td.rows.item(i).cells[54].innerHTML ;
			_td.rows.item(i).cells[55].innerHTML = td.rows.item(i).cells[55].innerHTML ;
			_td.rows.item(i).cells[56].innerHTML = td.rows.item(i).cells[56].innerHTML ;
			_td.rows.item(i).cells[57].innerHTML = td.rows.item(i).cells[57].innerHTML ;
			_td.rows.item(i).cells[58].innerHTML = td.rows.item(i).cells[58].innerHTML ;
			_td.rows.item(i).cells[59].innerHTML = td.rows.item(i).cells[59].innerHTML ;
			_td.rows.item(i).cells[60].innerHTML = td.rows.item(i).cells[60].innerHTML ;
			_td.rows.item(i).cells[61].innerHTML = td.rows.item(i).cells[61].innerHTML ;
		}
		n += 2 ; m += 2 ;
	}
}

/*业务明细*/
function yewu(){
	var fresh = false ;
	$.ajax({
		url:'ajax',
		type:'post' ,
		beforeSend:function(){
           return true;
        },
		success:function(result){
			var json = eval("("+result+")");
			var key = [];
			var n = 1;
			for(var p in json){
				key.push(json[p]);
				if(json[p] == 'yes'){
					fresh = true ;
				}
			}
			if(fresh){
				music_M(); //声音提示
				for(var k = 0 ; k < 12 ; k++){
					var td = document.getElementById('table' + n);
					var rows = td.rows ;
					for(var i = 0 ; i < rows.length ;i++){
						td.rows.item(i).cells[0].innerHTML = k + 1 ;
						td.rows.item(i).cells[1].innerHTML = key[k].PAYCOMPANYCODE;
						td.rows.item(i).cells[2].innerHTML = key[k].VIN;
						td.rows.item(i).cells[3].innerHTML = key[k].HPHM;
						td.rows.item(i).cells[4].innerHTML = key[k].HPZL;
						td.rows.item(i).cells[5].innerHTML = key[k].CLLX;
						td.rows.item(i).cells[6].innerHTML = key[k].TAXCONDITIONCODE;
						td.rows.item(i).cells[7].innerHTML = key[k].TAXLOCATIONCODE;
						td.rows.item(i).cells[8].innerHTML = key[k].TAXSTARTDATE;
						td.rows.item(i).cells[9].innerHTML = key[k].TAXENDDATE;
						td.rows.item(i).cells[10].innerHTML = key[k].TAXUNITTYPECODE;
						td.rows.item(i).cells[11].innerHTML = key[k].UNITRATE;
						td.rows.item(i).cells[12].innerHTML = key[k].ANNUALTAXAMOUNT;
						td.rows.item(i).cells[13].innerHTML = key[k].TAXDEPARTMENTCODE;
						td.rows.item(i).cells[14].innerHTML = key[k].TAXDEPARTMENT;
						td.rows.item(i).cells[15].innerHTML = key[k].TAXDOCUMENTNUMBER;
						td.rows.item(i).cells[16].innerHTML = key[k].DEDUCTIONDEPARTMENTCODE;
						td.rows.item(i).cells[17].innerHTML = key[k].DEDUCTIONDEPARTMENT;
						td.rows.item(i).cells[18].innerHTML = key[k].DEDUCTIONDUECODE;
						td.rows.item(i).cells[19].innerHTML = key[k].DEDUCTIONDUETYPE;
						td.rows.item(i).cells[20].innerHTML = key[k].DEDUCTIONDUEPROPORTION;
						td.rows.item(i).cells[21].innerHTML = key[k].DEDUCTION;
						td.rows.item(i).cells[22].innerHTML = key[k].DEDUCTIONDOCUMENTNUMBER;
						td.rows.item(i).cells[23].innerHTML = key[k].TAXDUE;
						td.rows.item(i).cells[24].innerHTML = key[k].EXCEEDDATE;
						td.rows.item(i).cells[25].innerHTML = key[k].EXCEEDDAYSCOUNT;
						td.rows.item(i).cells[26].innerHTML = key[k].OVERDUE;
						td.rows.item(i).cells[27].innerHTML = key[k].TOTALAMOUNT;
						td.rows.item(i).cells[28].innerHTML = key[k].LOGINNAME;
						td.rows.item(i).cells[29].innerHTML = key[k].REVENUECODE;
						td.rows.item(i).cells[30].innerHTML = key[k].SJCJRQ;
						td.rows.item(i).cells[31].innerHTML = key[k].PAYDATE;
						td.rows.item(i).cells[32].innerHTML = key[k].POWER;
						td.rows.item(i).cells[33].innerHTML = key[k].TAXAMOUNT_FLAG;
						td.rows.item(i).cells[34].innerHTML = key[k].ANNUALTAXDUE;
						td.rows.item(i).cells[35].innerHTML = key[k].SUMTAXDEFAULT;
						td.rows.item(i).cells[36].innerHTML = key[k].SUMOVERDUE;
						td.rows.item(i).cells[37].innerHTML = key[k].SUMTAX;
						td.rows.item(i).cells[38].innerHTML = key[k].LOGGEDOUT;
						td.rows.item(i).cells[39].innerHTML = key[k].PLATFORMSTATE;
						td.rows.item(i).cells[40].innerHTML = key[k].CHANGETYPE;
						td.rows.item(i).cells[41].innerHTML = key[k].COUNTTAXTYPE;
						td.rows.item(i).cells[42].innerHTML = key[k].refusetype;
						td.rows.item(i).cells[43].innerHTML = key[k].STATUSDATE;
						td.rows.item(i).cells[44].innerHTML = key[k].FIRSTREGISTERDATE;
						td.rows.item(i).cells[45].innerHTML = key[k].SPECIALCARTYPE;
						td.rows.item(i).cells[46].innerHTML = key[k].TSBZ;
						td.rows.item(i).cells[47].innerHTML = key[k].engineNo;
						td.rows.item(i).cells[48].innerHTML = key[k].FIRSTREGISTERDATE;
						td.rows.item(i).cells[49].innerHTML = key[k].FIRSTREGISTERDATE;
						td.rows.item(i).cells[50].innerHTML = key[k].CARSERIALNO;
						td.rows.item(i).cells[51].innerHTML = key[k].MOTORUSAGETYPECODE;
						td.rows.item(i).cells[52].innerHTML = key[k].MODEL;
						td.rows.item(i).cells[53].innerHTML = key[k].VEHICLETYPE;
						td.rows.item(i).cells[54].innerHTML = key[k].RATEDPASSENGERCAPACITY;
						td.rows.item(i).cells[55].innerHTML = key[k].CARMATCHID;
						td.rows.item(i).cells[56].innerHTML = key[k].TONNAGE;
						td.rows.item(i).cells[57].innerHTML = key[k].WHOLEWEIGHT;
						td.rows.item(i).cells[58].innerHTML = key[k].TAXREGISTRYNUMBER;
						td.rows.item(i).cells[59].innerHTML = key[k].TAXQUERYNO;
						td.rows.item(i).cells[60].innerHTML = key[k].TAXCONFIRMNO;
					}
					n += 2 ;
				}
				if(animate_id == 1){
					init_tb();	// 动画刷新 同步
				}else if(animate_id == 2){
					init_jb();	// 动画刷新 渐变
				}else if(animate_id == 3){
					init_sx();	// 动画刷新 顺序
				}
			}
        },
        error:function(){
        }
	});
}
// 动画选择
function optiond(){
	var id = document.getElementById('select_d').value ;
	if(id == 1){
		init_tb();	// 动画刷新 同步
		animate_id = 1 ;
	}else if(id == 2){
		init_jb();	// 动画刷新 渐变
		animate_id = 2 ;
	}else if(id == 3){
		init_sx();	// 动画刷新 顺序
		animate_id = 3 ;
	}
}
// 增加删除列
function add_delete_l(){
	var strings = '' ;
	var colums = '' ;
	var td = document.getElementById('ttable');
	var rows = td.rows ;
	for(var i = 0 ; i < rows[0].cells.length ; i++){
		strings += rows[0].cells[i].style.display + '@';
		colums += rows[0].cells[i].innerHTML + '@';
		
	}
	var shuxing = ',toolbar = no,menuber = no,scrollbars = no,Resizable = no,location = no,status = no,titebar = yes' ;
	var wid_width = 1100 ;		// 宽
	var wid_height = 500 ;		// 高
	var top_width = (window.innerWidth - wid_width) / 2;
	var left_width = (window.innerHeight - wid_height) / 2;
	var url = 'ccs/jsp/YeWu/add_delete.jsp?strings=' + strings + '&colums=' + colums;			// 新的jsp页面
	var parameters = 'width = '+ wid_width +',height = '+ wid_height +',top = '+ top_width +',left = '+ left_width + shuxing;
	window.open(url,'业务明细',parameters);
	
}
// 添加删除列
function retuen_add(strs){
	var td = document.getElementById('ttable');
	var rows = td.rows ;
	var _strs = [];
	var lengths = 0 ;
	// 表头的删减
	for(var i = 0 ; i < rows[0].cells.length ; i++){
		_strs[i] = rows[0].cells[i].style.display ;
		rows[0].cells[i].style.display = strs[i];
	}
	// 表内容删减
	for(var i = 1,k = 0; i < 24 ;i += 2,k += 2){
		var tables = document.getElementById('table' + i);
		var table_rows = tables.rows ;
		for(var j = 0 ; j < table_rows[0].cells.length ; j++){
			table_rows[0].cells[j].style.display = strs[j] ;
		}
		var tablek = document.getElementById('table' + k);
		var table_k = tablek.rows ;
		for(var j = 0 ; j < table_k[0].cells.length ; j++){
			table_k[0].cells[j].style.display = strs[j] ;
		}
	}
//	for(var i = 0 ; i < strs.length ; i++){
//		if(i == 0){
//			if(_strs[i] == 'table-cell' && strs[i] == 'none'){
//				lengths += -31;
//			}else if(strs[i] == 'table-cell' && _strs[i] == 'none'){
//				lengths += 31;
//			}
//		}else if(i == 30){
//			if(_strs[i] == 'table-cell' && strs[i] == 'none'){
//				lengths += -200;
//			}else if(strs[i] == 'table-cell' && _strs[i] == 'none'){
//				lengths += 200;
//			}
//		}else{
//			if(_strs[i] == 'table-cell' && strs[i] == 'none'){
//				lengths += -100;
//			}else if(strs[i] == 'table-cell' && _strs[i] == 'none'){
//				lengths += 100;
//			}
//		}
//	}
	// 样式调整
//	for(var i = 1 ; i < 35 ;i += 3){
//		var _div = document.getElementById('demo' + i);
//		var w = parseInt(_div.style.width)
//		_div.style.width = w + lengths;
//		td.style.width = parseInt(td.style.width) + lengths;
//	}
}
// 合计汇总
function hjhz(){
	var fresh = false ;
	$.ajax({
		url:'ajax_hjhz',
		type:'post' ,
		beforeSend:function(){
           return true;
        },
		success:function(result){
			var json = eval("("+result+")");
			var key = [];
			for(var p in json){
				key.push(json[p]);
				if(json[p] == 'yes'){
					fresh = true ;
				}
			}
			var td = document.getElementById('hjhz_t1');
			td.rows.item(0).cells[0].innerHTML = key[0].date_Number ;
			td.rows.item(0).cells[1].innerHTML = key[0].date_Money ;
			td.rows.item(0).cells[2].innerHTML = key[0].month_Number ;
			td.rows.item(0).cells[3].innerHTML = key[0].month_Money ;
			td.rows.item(0).cells[4].innerHTML = key[0].year_Number ;
			td.rows.item(0).cells[5].innerHTML = key[0].year_Money ;
			if(fresh){
				anim_hjhz();
				hjhz_b();
			}
       	},
        error:function(){
        }
	});
}
// 合计汇总 翻滚
function hjhz_b(){
	var td = document.getElementById('hjhz_t1');  	// 显示的
	var _td = document.getElementById('hjhz_t0');	// 隐藏的
	_td.rows.item(0).cells[0].innerHTML = td.rows.item(0).cells[0].innerHTML ;
	_td.rows.item(0).cells[1].innerHTML = td.rows.item(0).cells[1].innerHTML ;
	_td.rows.item(0).cells[2].innerHTML = td.rows.item(0).cells[2].innerHTML ;
	_td.rows.item(0).cells[3].innerHTML = td.rows.item(0).cells[3].innerHTML ;
	_td.rows.item(0).cells[4].innerHTML = td.rows.item(0).cells[4].innerHTML ;
	_td.rows.item(0).cells[5].innerHTML = td.rows.item(0).cells[5].innerHTML ;
}
// 单选点击样式
function div_style(div_id,str){
	var add_id = document.getElementById(div_id);
	if(str == 'add'){
		add_id.style.backgroundColor = '#E0ECFF';
		add_id.style.border = '1px solid #DDDDDD' ;
		add_id.style.cursor = 'pointer' ;
//		add_id.style.width = parseInt(add_id.style.width) - 1 ;
	}else if(str == 'del'){
		add_id.style.backgroundColor = '#F4F4F4';
		add_id.style.border = '0px solid #DDDDDD' ;
		add_id.style.cursor = 'auto' ;
//		add_id.style.width = parseInt(add_id.style.width) + 1 ;
	}
}
// table tr 样式
function table_style(style_id,str){
	if(str == 'add'){
		style_id.style.backgroundColor = '#E0ECFF';
		style_id.style.cursor = 'pointer';
	}else if(str == 'del'){
		style_id.style.backgroundColor = '#F4F4F4';
	}
}
// 提示声 开关
function add_music(music_id){
	if(musicFlag){
		document.getElementById(music_id).innerHTML = '声音提示关' ;
		musicFlag = false ;
		music_M();
	}else if(!musicFlag){
		document.getElementById(music_id).innerHTML = '声音提示开' ;
		musicFlag = true ;
	}else{
	}
}
// 声音提示声
function music_M(){
	if(musicFlag){
		var emd = document.getElementById("embed");
		emd.src = 'ccs/jsp/YeWu/music/Windows.wav';
	}else{
		
	}
}
// 显示 隐藏 菜单栏
function display_menu(){
	var menu = document.getElementById("menu_id");
	if(menuFlag){
		menu.style.display = 'none' ;
		menuFlag = false ;
	}else{
		menu.style.display = '' ;
		menuFlag = true ;
	}
}
// 页面详细信息
function onjx(table){
	var td = document.getElementById(table);
	var rows = td.rows ;
	var taxconfig = rows[0].cells[60].innerHTML.replace("&nbsp;", "") ;
//	alert(taxconfig);
	var shuxing = ',toolbar = no,menuber = no,scrollbars = no,Resizable = no,location = no,status = no,titebar = yes' ;
	var wid_width = 1100 ;		// 宽
	var wid_height = 600 ;		// 高
	var top_width = (window.innerWidth - wid_width) / 2;
	var left_width = (window.innerHeight - wid_height) / 2;
	var url = 'ccs/jsp/YeWu/mingxi.jsp?taxconfig=' + taxconfig ;			// 新的jsp明细页面
	var parameters = 'width = '+ wid_width +',height = '+ wid_height +',top = '+ top_width +',left = '+ left_width + shuxing;
	window.open(url,'业务明细',parameters);
}