<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
	<head>
	<title>业务监控</title>
	<script src="ccs/js/jquery-1.6.4.min.js"></script>
	<script src = "ccs/jsp/YeWu/js/animate.js"></script>
	<link href="ccs/jsp/YeWu/css/animate.css" rel="stylesheet" type="text/css"/>
	</head>
	<body onload = "init_tb();anim_hjhz();">
	<!-- 业务明细 -->
	<div id = "maxdiv" style = "border:1px solid #95B8E7;overflow: scroll;display:" >
		<div id = "" style = "background:#E0ECFF ; height: 23px;font-size:13px;width:6295px">
			<div style = "padding-left: 5px;padding-top: 3px ; font-size:13px;font-weight:bold;cursor:pointer;" onclick = "display_menu();" >业务明细</div>
		</div>
		<div id = "menu_id" style = "background:#F4F4F4 ; height: 30px;font-size:13px;border-top: 1px solid #95B8E7;width:6295px;display:;">
			<div style = "background:;padding-left: 3px;padding-top: 8px; font-size:13px;width:60px;float: left">刷新状态      
				<!--  
				| 动画效果  <select id = "select_d" onchange = "optiond()">
				<option value = "1">同时</option>
				<option value = "2">渐变</option>
				<option value = "3">顺序</option></select> -->
			</div>
			<div id = "shu_id" style = "background:;margin-left:0px;float: left;width:55px;margin-top: 8px;" onmouseover = "div_style(this.id,'add');" onmouseout = "div_style(this.id,'del');">
				<a id = "sx" onclick = "auto()">手动刷新</a> 
			</div>
			<div style = "background:;margin-left: 5px;width:160px;margin-top: 6px;;float: left">
				| 刷新时间 
				 <select id = "select_o" onchange = "option()">
					<option value = "3">3</option>
					<option value = "5" selected>5</option>
					<option value = "10">8</option>
					<option value = "15">15</option>
				</select> 秒	 |
			</div>
			<div id = "add_id" style = "margin-left: 0px;width:66px;margin-top: 8px;float: left" onmouseover = "div_style(this.id,'add');" onmouseout = "div_style(this.id,'del');">
				<a onclick = "add_delete_l()">增加删除列</a> 
			</div>
			<div style = "margin-left: 10px;width:20px;margin-top: 8px;float: left">
				|
			</div>	
			<div id = "add_music" style = "margin-left: 0px;width:66px;margin-top: 8px;float: left;" onmouseover = "div_style(this.id,'add');" onmouseout = "div_style(this.id,'del');">
				<a id = "music" onclick = "add_music(this.id)">声音提示开</a> 
			</div>
		</div>
		<div id = "div_mili" style = "padding-left: 0px;border-top: 1px solid #DDDDDD;width:6295px"> <!-- 上横线样式 -->
			<table id = "ttable" cellpadding = "0px" cellspacing = "1px" style="table-layout:fixed;overflow: hidden;width:6295px"  border = "0px">
				<tr bgcolor = "#F4F4F4" style = "font-size:13px;height: 23px">
					<c:forEach items = "${list_td}" var = "td" varStatus = "status">	
						<c:if test = "${status.index == 0}" >
							<td id = "${status.index}" style = "overflow: hidden;hidden;border-right: 0px dashed #DDDDDD;display:table-cell"  width = "31px" align = "center">${td}</td>
						</c:if>
						<c:if test = "${status.index == 30}" >
							<td id = "${status.index}" style = "overflow: hidden;hidden;border-right: 0px dashed #DDDDDD;display:table-cell"  width = "200px" align = "center">${td}</td>
						</c:if>
						<c:if test = "${status.index != 0 && status.index != 30}" >
							<td id = "${status.index}" style = "overflow: hidden;hidden;border-right: 0px dashed #DDDDDD;display:table-cell"  width = "100px" align = "center">${td}</td>
						</c:if>
					</c:forEach>
				</tr>
			</table>
		<c:forEach items = "${number}" var = "num" varStatus = "status">
			<div id="${num.num0}" style="OVERFLOW: hidden; HEIGHT:23px;width:6295px">
				<table border="0px" cellpadding="3" cellspacing="0">
					<tr bgcolor = "#F4F4F4" onmouseover = "table_style(this,'add');" onmouseout = "table_style(this,'del');"> <!-- #F4F4F4 -->
						<td id="${num.num1}" style = "display:none;">
							<table id = "${num.table1}" border = "0px" cellpadding="0" cellspacing="0" style="table-layout:fixed" width="6295px">
								<tr style = "font-size:13px"> <!-- 历史数据  --> 
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "28px">${status.index + 1}&nbsp;</td>		<!-- 1 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>			<!-- 10 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>			<!-- 20 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>			<!-- 30 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "200px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>			<!-- 40 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>			<!-- 50 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>			<!-- 60 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;" align = "center"  width = "100px">&nbsp;</td>
								</tr>
							</table>
						</td>
						<td>
							<table id = "${num.table2}" border = "0px" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="6295px" ondblclick = "onjx('${num.table2}')">
								<tr style = "font-size:13px"> <!-- 刷新数据  --> 
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "28px">${status.index + 1}&nbsp;</td>					<!-- 1 -->
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.PAYCOMPANYCODE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.VIN}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.HPHM}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.HPZL}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.CLLX}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXCONDITIONCODE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXLOCATIONCODE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXSTARTDATE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXENDDATE}&nbsp;</td>			<!-- 10 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXUNITTYPECODE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.UNITRATE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.ANNUALTAXAMOUNT}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXDEPARTMENTCODE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXDEPARTMENT}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXDOCUMENTNUMBER}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.DEDUCTIONDEPARTMENTCODE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.DEDUCTIONDEPARTMENT}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.DEDUCTIONDUECODE}&nbsp;</td>	<!-- 20 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.DEDUCTIONDUETYPE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.DEDUCTIONDUEPROPORTION}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.DEDUCTION}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.DEDUCTIONDOCUMENTNUMBER}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXDUE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.EXCEEDDATE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.EXCEEDDAYSCOUNT}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.OVERDUE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TOTALAMOUNT}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.LOGINNAME}&nbsp;</td>			<!-- 30 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.REVENUECODE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "200px" >${num.newrkmx.SJCJRQ}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.PAYDATE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.POWER}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXAMOUNT_FLAG}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.ANNUALTAXDUE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.SUMTAXDEFAULT}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.SUMOVERDUE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.SUMTAX}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.LOGGEDOUT}&nbsp;</td>			<!-- 40 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.PLATFORMSTATE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.CHANGETYPE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.COUNTTAXTYPE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.refusetype}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.STATUSDATE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.FIRSTREGISTERDATE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.SPECIALCARTYPE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TSBZ}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.engineNo}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.FIRSTREGISTERDATE}&nbsp;</td><!-- insureStartDate  --> <!-- 50 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.FIRSTREGISTERDATE}&nbsp;</td><!-- insureEndDate  -->
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.CARSERIALNO}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.MOTORUSAGETYPECODE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.MODEL}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.VEHICLETYPE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.RATEDPASSENGERCAPACITY}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.CARMATCHID}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TONNAGE}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.WHOLEWEIGHT}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXREGISTRYNUMBER}&nbsp;</td>	<!-- 60 -->	
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXQUERYNO}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >${num.newrkmx.TAXCONFIRMNO}&nbsp;</td>
									<td style = "overflow: hidden;border-right: 1px dashed #DDDDDD;display:" align = "center"  width = "100px" >&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr bgcolor = "#E0ECFF"><td id="${num.num2}"></td></tr> <!-- #E0ECFF -->
				</table>
			</div>
		</c:forEach><br/><br/>
		</div>
	</div>
		<br/><br/>
	<!-- 合计汇总 -->
	<div style = "border:1px solid #95B8E7;overflow: scroll;display:">
		<div style = "background:#E0ECFF ; height: 23px;font-size:13px;width:1330px">
			<div style = "padding-left: 5px;padding-top: 3px ;font-weight:bold;width:1330px">合计汇总</div>
		</div>
		<div id = "" style = "padding-left: 0px; border-top:1px solid #95B8E7;width:1330px" >
			<table id = "heji" border = "0px" cellpadding = "0px" cellspacing="1px" style = "width:1330px">
				<tr align = "center" bgcolor = "F4F4F4" style = "font-size:13px;height: 23px">
					<td width = "200px">本日交易数</td>
					<td width = "200px">本日税款</td>
					<td width = "200px">本月交易数</td>
					<td width = "200px">本月税款</td>
					<td width = "200px">本年交易数</td>
					<td width = "200px">本年税款</td>
				</tr>
			</table>
			<div id = "hjhz0" style="OVERFLOW: hidden; HEIGHT:28px;width:1330px">		
				<table border="0px" cellpadding="1px" cellspacing="1px">	
					<tr bgcolor = "#F4F4F4"> 			 
						<td style = "display:none">	
						 	<table id = "hjhz_t0" border = "0px" cellpadding="0" cellspacing="1px" style="table-layout:fixed" width="1330px">
								<tr align = "center" style = "font-size:13px;height: 23px">
									<td width = "77px"></td>
									<td width = "77px"></td>
									<td width = "77px"></td>
									<td width = "77px"></td>
									<td width = "77px"></td>
									<td width = "77px"></td>
								</tr>
							</table>
						</td>
						<td id = "hjhz1">	
						 	<table id = "hjhz_t1" border = "0px" cellpadding="0" cellspacing="0" style="table-layout:fixed" width="1330px">
								<tr align = "center" style = "font-size:13px;height: 23px;">
									<td style = "text-align: center" width = "77px">${hjhz.date_Number}</td>
									<td width = "77px">${hjhz.date_Money}</td>
									<td width = "77px">${hjhz.month_Number}</td>
									<td width = "77px">${hjhz.month_Money}</td>
									<td width = "77px">${hjhz.year_Number}</td>
									<td width = "77px">${hjhz.year_Money}</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr bgcolor = "#E0ECFF"><td id = "hjhz2"></td></tr>
				</table>
			</div>
		</div>
	</div>
	<!-- 声音提示 -->
	<div style = "border:0px solid red;width:0px;height:0px;display:none">
	 	<embed id = "embed" src = "" hidden = "true" autostart = "true" loop = "1" type="application/http-index-format">  
	</div>
	</body>
</html>