<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";	
%>
<%
	String trString = new String(request.getParameter("strings").getBytes("ISO-8859-1"), "UTF-8");
	String colums = new String(request.getParameter("colums").getBytes("ISO-8859-1"), "UTF-8");
	
	String trColums[] = colums.split("@");
	String columns[] = trString.split("@");
	
	request.setAttribute("cols",columns);
	request.setAttribute("trColums",trColums);
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据明细test</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function row_color(){
			var td_color = document.getElementById('table_row');
			var rows = td_color.rows ;
			for(var i = 0 ; i < rows.length ; i++ ){
				var uu = rows[i].cells.length ;
				for(var j = 0 ;j < uu ; j++){
					if(0 == j % 2){
						rows[i].cells[j].style.backgroundColor = '#EBEBEB';
					}else{
						rows[i].cells[j].style.backgroundColor = '#E0ECFF';
					}
				}
			}
		}
	</script>
  </head>
  
  <body onload = "row_color();">
  	<br/><center><font color = "black" face = "隶书" size = "5">车辆纳税信息明细</font></center><br/>
  	<div style = "border:0px solid #000 ;width: auto;height: auto;">
  		<table id = "table_row" cellpadding = "5px" align = "center" style="font-size:13px;">
	  		<tr align="center" bgcolor = "">		
	  			<td>${trColums[0]}</td><td>${cols[0]}</td><td>${trColums[1]}</td><td>${cols[1]}</td><td>${trColums[2]}</td>
	  			<td>${cols[2]}</td><td>${trColums[3]}</td><td>${cols[3]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #EBEBEB -->
	  			<td>${trColums[4]}</td><td>${cols[4]}</td><td>${trColums[5]}</td><td>${cols[5]}</td><td>${trColums[6]}</td>
	  			<td>${cols[6]}</td><td>${trColums[7]}</td><td>${cols[7]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #E0ECFF -->
	  			<td>${trColums[8]}</td><td>${cols[8]}</td><td>${trColums[9]}</td><td>${cols[9]}</td><td>${trColums[10]}</td>
	  			<td>${cols[10]}</td><td>${trColums[11]}</td><td>${cols[11]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "">
	  			<td>${trColums[12]}</td><td>${cols[12]}</td><td>${trColums[13]}</td><td>${cols[13]}</td><td>${trColums[14]}</td>
	  			<td>${cols[14]}</td><td>${trColums[15]}</td><td>${cols[15]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "">
	  			<td>${trColums[16]}</td><td>${cols[16]}</td><td>${trColums[17]}</td><td>${cols[17]}</td><td>${trColums[18]}</td>
	  			<td>${cols[18]}</td><td>${trColums[19]}</td><td>${cols[19]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "">
	  			<td>${trColums[20]}</td><td>${cols[20]}</td><td>${trColums[21]}</td><td>${cols[21]}</td><td>${trColums[22]}</td>
	  			<td>${cols[22]}</td><td>${trColums[23]}</td><td>${cols[23]}</td>
	  		</tr>
	  		
	  		<tr align="center" bgcolor = "">
	  			<td>${trColums[24]}</td><td>${cols[24]}</td><td>${trColums[25]}</td><td>${cols[25]}</td><td>${trColums[26]}</td>
	  			<td>${cols[26]}</td><td>${trColums[27]}</td><td>${cols[27]}</td>
	  		</tr>	
			<tr align="center" bgcolor = "">
	  			<td>${trColums[28]}</td><td>${cols[28]}</td><td>${trColums[29]}</td><td>${cols[29]}</td><td>${trColums[30]}</td>
	  			<td>${cols[30]}</td><td>${trColums[31]}</td><td>${cols[31]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "">
	  			<td>${trColums[32]}</td><td>${cols[32]}</td><td>${trColums[33]}</td><td>${cols[33]}</td><td>${trColums[34]}</td>
	  			<td>${cols[34]}</td><td>${trColums[35]}</td><td>${cols[35]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "">
	  			<td>${trColums[36]}</td><td>${cols[36]}</td><td>${trColums[37]}</td><td>${cols[37]}</td><td>${trColums[38]}</td>
	  			<td>${cols[38]}</td><td>${trColums[39]}</td><td>${cols[39]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "">
	  			<td>${trColums[40]}</td><td>${cols[40]}</td><td>${trColums[41]}</td><td>${cols[41]}</td><td>${trColums[42]}</td>
	  			<td>${cols[42]}</td><td>${trColums[43]}</td><td>${cols[43]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "">
	  			<td>${trColums[44]}</td><td>${cols[44]}</td><td>${trColums[45]}</td><td>${cols[45]}</td><td>${trColums[46]}</td>
	  			<td>${cols[46]}</td><td>${trColums[47]}</td><td>${cols[47]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "">
	  			<td>${trColums[48]}</td><td>${cols[48]}</td><td>${trColums[49]}</td><td>${cols[49]}</td><td>${trColums[50]}</td>
	  			<td>${cols[50]}</td><td>${trColums[51]}</td><td>${cols[51]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "" >
	  			<td>${trColums[52]}</td><td>${cols[52]}</td><td>${trColums[53]}</td><td>${cols[53]}</td><td>${trColums[54]}</td>
	  			<td>${cols[54]}</td><td>${trColums[60]}</td><td>${cols[60]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "" >
	  			<td>${trColums[56]}</td><td>${cols[56]}</td><td>${trColums[57]}</td><td>${cols[57]}</td><td>${trColums[58]}</td>
	  			<td>${cols[58]}</td><td>${trColums[59]}</td><td>${cols[59]}</td>
	  		</tr>
	  		<tr align="center" bgcolor = "" >
	  			<td>${trColums[55]}</td><td>${cols[55]}</td><td>${trColums[61]}</td><td colspan = "5">${cols[61]}</td>
	  		</tr>
	  		<!-- 
	  		-->
  		</table>
  	</div>
  </body>
</html>
