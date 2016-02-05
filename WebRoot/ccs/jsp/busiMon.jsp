<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@include file="inc-common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<script src="../js/YeWu/hjhz.js"></script>
	<script src="../js/YeWu/ywjk.js"></script>
	<script src="../js/YeWu/Test.js"></script>
	<link href="../css/main.css" rel="stylesheet" type="text/css"/>
<style  type='text/css'>
	table{
		font-size : 13px;
	}
	td{
		witdh : 78px ;
		height : 23px ;
		align : center ;
	}
</style>
<script type="text/javascript">
function ajaxs(){
	$.ajax({
        url: "test",
        type: "post",
        data:{},
        beforeSend:function(){
        	alert('beforsSend');
          return true;
        },
        success:function(result){
        	alert(result);
        },
        error:function(){
        	 alert('error');
        }
     });
}


	var freshFlag = true ;  // 是否自动刷新
	var time1 = null;		// 
	function shuax(){	
		if(freshFlag){
			document.getElementById('btn').value = "暂停";
			freshFlag = false ;
			time1 = setInterval("initTable();shua()", 3000);
		}else{
			document.getElementById('btn').value = "刷新";
			freshFlag = true ;
			clearInterval(time1);
		}
	}
function init(){
	auto();
}
function auto(){
//	alert(document.getElementById('form1').action);
	document.getElementById('form1').submit();
}
function adc(){
	double0();
//	double1();
	setTimeout("double1()",1000);
//	double2();
	setTimeout("double2()",2000);
//	double3();
	setTimeout("double3()",3000);
//	double4();
	setTimeout("double4()",4000);
//	double5();
	setTimeout("double5()",5000);
//	double6();
	setTimeout("double6()",6000);
//	double7();
	setTimeout("double7()",7000);
	setTimeout("init()",9000);
}
function double0() {
	var speed = 30;
	var MyMarh = setInterval(Marqueeh, speed)
	demo3.innerHTML = demo2.innerHTML ;
	demo1.scrollTop = demo1.scrollHeight ;
	function Marqueeh() {
		demo1.scrollTop--;
	}
}
function double1(){
	var speed = 30;
	var MyMarh = setInterval(Marqueeh, speed)
	demo6.innerHTML = demo5.innerHTML ;
	demo4.scrollTop = demo4.scrollHeight ;
	function Marqueeh() {
		demo4.scrollTop-- ;
	}
}
function double2(){
	var speed = 30;
	var MyMarh = setInterval(Marqueeh, speed)
	demo9.innerHTML = demo8.innerHTML ;
	demo7.scrollTop = demo7.scrollHeight ;
	function Marqueeh() {
		demo7.scrollTop-- ;
	}
}
function double3(){
	var speed = 30;
	var MyMarh = setInterval(Marqueeh, speed)
	demo12.innerHTML = demo11.innerHTML ;
	demo10.scrollTop = demo10.scrollHeight ;
	function Marqueeh() {
		demo10.scrollTop-- ;
	}
}
function double4(){
	var speed = 30;
	var MyMarh = setInterval(Marqueeh, speed)
	demo15.innerHTML = demo14.innerHTML ;
	demo13.scrollTop = demo13.scrollHeight ;
	function Marqueeh() {
		demo13.scrollTop-- ;
	}
}
function double5(){
	var speed = 30;
	var MyMarh = setInterval(Marqueeh, speed)
	demo18.innerHTML = demo17.innerHTML ;
	demo16.scrollTop = demo16.scrollHeight ;
	function Marqueeh() {
		demo16.scrollTop-- ;
	}
}
function double6(){
	var speed = 30;
	var MyMarh = setInterval(Marqueeh, speed)
	demo21.innerHTML = demo20.innerHTML ;
	demo19.scrollTop = demo19.scrollHeight ;
	function Marqueeh() {
		demo19.scrollTop-- ;
	}
}
function double7(){
	var speed = 30;
	var MyMarh = setInterval(Marqueeh, speed)
	demo24.innerHTML = demo23.innerHTML ;
	demo22.scrollTop = demo22.scrollHeight ;
	function Marqueeh() {
		demo22.scrollTop-- ;
	}
}

function table_(){
	var td = document.getElementById('table1');
	var rows = td.rows ;
	for(var i = 0 i < rows.length ;i++){
		var cells = rows[i].cells ;
		alert(cells[i].value);
	} 
	
}
//	$(document).ready(function(){
//		setInterval("initTable();initTables()", freshseconds * 1000);
//	});
</script>
</head>  
<!--    <body onload="initCheck()" >-->
    <body onload="adc()">	
	<form id = "form1" action = "test" method = "post"> 
		<div id = "div_mili" style = "height: 300px;width: 1300px;border : 1px solid #f00">
		<table cellpadding="0" cellspacing="0" class = "table" bgcolor = "F4F4F4">
			<tr><td width = "78px" align = "center">代收公司</td><td width = "78px" align = "center">VIN</td><td width = "78px" align = "center">号牌号码</td>
			<td width = "78px" align = "center">车辆类型</td><td width = "78px" align = "center">纳税类型</td><td width = "78px" align = "center">税款始起</td>
			<td width = "78px" align = "center">税款止起</td><td width = "78px" align = "center">单位税额</td>
		</table>
		<c:forEach items = "${number}" var = "num" varStatus = "status">
			<div id="${num.num0}" style="OVERFLOW: hidden; HEIGHT:23px;">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td id="${num.num1}" style = "display:none" >
							<table id = "table1"  height="20" border="0" cellpadding="0px" cellspacing="0" class = "table" style="table-layout:fixed" width="624px">
								<tr> <!-- 历史数据  --> 
									<td style = "overflow: hidden;" align = "center" bgcolor = "E0ECFF">${num.oldrkmx.PAYCOMPANYCODE}</td>
									<td style = "overflow: hidden;" align = "center" bgcolor = "E0ECFF">${num.oldrkmx.VIN}</td>
									<td style = "overflow: hidden;" align = "center" bgcolor = "E0ECFF">${num.oldrkmx.HPHM}</td>
									<td style = "overflow: hidden;" align = "center" bgcolor = "E0ECFF">${num.oldrkmx.CLLX}</td>
									<td style = "overflow: hidden;" align = "center" bgcolor = "E0ECFF">${num.oldrkmx.TAXCONDITIONCODE}</td>
									<td style = "overflow: hidden;" align = "center" bgcolor = "E0ECFF">${num.oldrkmx.TAXSTARTDATE}</td>
									<td style = "overflow: hidden;" align = "center" bgcolor = "E0ECFF">${num.oldrkmx.TAXENDDATE}</td>
									<td style = "overflow: hidden;" align = "center" bgcolor = "E0ECFF">${num.oldrkmx.UNITRATE}</td>
								</tr>
							</table>
						</td>
						<td>
							<table id = "table2" border = "0px" cellpadding="0" cellspacing="0" class = "table" style="table-layout:fixed" width="624px">
								<tr> <!-- 刷新数据  --> 	
									<td style = "overflow: hidden;" align = "center"  >${num.newrkmx.PAYCOMPANYCODE}</td>
									<td style = "overflow: hidden;" align = "center"  >${num.newrkmx.VIN}</td>
									<td style = "overflow: hidden;" align = "center"  >${num.newrkmx.HPHM}</td>
									<td style = "overflow: hidden;" align = "center"  >${num.newrkmx.CLLX}</td>
									<td style = "overflow: hidden;" align = "center"  >${num.newrkmx.TAXCONDITIONCODE}</td>
									<td style = "overflow: hidden;" align = "center"  >${num.newrkmx.TAXSTARTDATE}</td>
									<td style = "overflow: hidden;" align = "center"  >${num.newrkmx.TAXENDDATE}</td>
									<td style = "overflow: hidden;" align = "center"  >${num.newrkmx.UNITRATE}</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr><td id="${num.num2}"></td></tr>
				</table>
			</div>
		</c:forEach><br/><br/>
		<a href = "../../test?a=1">超超超</a><br/>
		<a href = "test">超超超1</a><br/>
		<input type = "button" value = "刷新" onclick = "init()"/>
		<input type = "button" value = "刷新table" onclick = "table_()"/>
		</div>
	</form>
		<br/>
		<div style = "margin 500px 0 0 0 ; border : 1px solid #000 ; heigth:200px">
		<table id="hjhz"></table>
		</div>
		<div style = "border : 1px solid #F00">
			<table >
				<tr align = "center" bgcolor = "F4F4F4">
				<td width = "200px">本日交易数</td>
				<td width = "200px">本日税款</td>
				<td width = "200px">本月交易数</td>
				<td width = "200px">本月税款</td>
				<td width = "200px">本年交易数</td>
				<td width = "200px">本年税款</td>
				</tr>
				<tr align = "center">
				<td>1990</td><td>34566.0</td><td>23456</td><td>567568899.0</td><td>2345654</td><td>56347568899.0</td>
				</tr>
			</table>
		</div>
		<br/>
		<!-- <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">业务监控</a> -->
	<input type = "button" id = "btn" value = "刷新" onclick = "shuax()"/>
	<input type = "button" value = "刷新ajax" onclick = "ajaxs()"/>
	
</body>
</html>