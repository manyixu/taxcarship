<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String strings = new String(request.getParameter("strings").getBytes("ISO-8859-1"), "UTF-8");
	String colums = new String(request.getParameter("colums").getBytes("ISO-8859-1"), "UTF-8");
	String cols[] = strings.split("@");
//	System.out.println(cols.length);
	String trColums[] = colums.split("@") ;
	request.setAttribute("cols",cols);
	request.setAttribute("trColums",trColums);
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add_delete.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function checkeds(){
		var boxs = document.getElementsByName('box') ;
		var str = [] ;
		for(var i = 0 ; i < boxs.length ; i++){
			if(boxs[i].checked){
				str[i] = 'table-cell' ;
			}else{
				str[i] = 'none' ;
			}
		}
		window.opener.retuen_add(str);
		window.close();
	}
</script>
  </head>
  
  <body>
  	<div>
  		
   <br/>
   <center><font color = "blue" face = "隶书" size = "5">选择列</font></center><br/>
		<form action = "" method = "post">
			<table cellpadding = "5px" border = "0" align = "center" style = "font-size : 13px">
				<tr align="center" bgcolor = "E0ECFF">		
	  				<td><input type = "checkbox" name = "box" <c:if test = "${cols[0] eq 'table-cell'}">checked</c:if>/>${trColums[0]}</td>
	  				<td><input type = "checkbox" name = "box" <c:if test = "${cols[1] eq 'table-cell'}">checked</c:if>/>${trColums[1]}</td>
	  				<td><input type = "checkbox" name = "box" <c:if test = "${cols[2] eq 'table-cell'}">checked</c:if>/>${trColums[2]}</td>
	  				<td><input type = "checkbox" name = "box" <c:if test = "${cols[3] eq 'table-cell'}">checked</c:if>/>${trColums[3]}</td>
	  				<td><input type = "checkbox" name = "box" <c:if test = "${cols[4] eq 'table-cell'}">checked</c:if>/>${trColums[4]}</td>
	  				<td><input type = "checkbox" name = "box" <c:if test = "${cols[5] eq 'table-cell'}">checked</c:if>/>${trColums[5]}</td>
	  				<td><input type = "checkbox" name = "box" <c:if test = "${cols[6] eq 'table-cell'}">checked</c:if>/>${trColums[6]}</td>
		  		</tr>
		  		<tr align="center" bgcolor = "F4F4F4">
	  				<td><input type = "checkbox" name = "box" <c:if test = "${cols[7] eq 'table-cell'}">checked</c:if>/>${trColums[7]}</td>
	  				<td><input type = "checkbox" name = "box" <c:if test = "${cols[8] eq 'table-cell'}">checked</c:if>/>${trColums[8]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[9] eq 'table-cell'}">checked</c:if>/>${trColums[9]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[10] eq 'table-cell'}">checked</c:if>/>${trColums[10]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[11] eq 'table-cell'}">checked</c:if>/>${trColums[11]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[12] eq 'table-cell'}">checked</c:if>/>${trColums[12]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[13] eq 'table-cell'}">checked</c:if>/>${trColums[13]}</td>
		  		</tr>
		  		<tr align="center" bgcolor = "E0ECFF">
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[14] eq 'table-cell'}">checked</c:if>/>${trColums[14]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[15] eq 'table-cell'}">checked</c:if>/>${trColums[15]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[16] eq 'table-cell'}">checked</c:if>/>${trColums[16]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[17] eq 'table-cell'}">checked</c:if>/>${trColums[17]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[18] eq 'table-cell'}">checked</c:if>/>${trColums[18]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[19] eq 'table-cell'}">checked</c:if>/>${trColums[19]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[20] eq 'table-cell'}">checked</c:if>/>${trColums[20]}</td>
		  		</tr>
		  		<tr align="center" bgcolor = "F4F4F4">
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[21] eq 'table-cell'}">checked</c:if>/>${trColums[21]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[22] eq 'table-cell'}">checked</c:if>/>${trColums[22]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[23] eq 'table-cell'}">checked</c:if>/>${trColums[23]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[24] eq 'table-cell'}">checked</c:if>/>${trColums[24]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[25] eq 'table-cell'}">checked</c:if>/>${trColums[25]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[26] eq 'table-cell'}">checked</c:if>/>${trColums[26]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[27] eq 'table-cell'}">checked</c:if>/>${trColums[27]}</td>
		  		</tr>	
		  		<tr align="center" bgcolor = "E0ECFF">
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[28] eq 'table-cell'}">checked</c:if>/>${trColums[28]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[29] eq 'table-cell'}">checked</c:if>/>${trColums[29]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[30] eq 'table-cell'}">checked</c:if>/>${trColums[30]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[31] eq 'table-cell'}">checked</c:if>/>${trColums[31]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[32] eq 'table-cell'}">checked</c:if>/>${trColums[32]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[33] eq 'table-cell'}">checked</c:if>/>${trColums[33]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[34] eq 'table-cell'}">checked</c:if>/>${trColums[34]}</td>
		  		</tr>
		  		<tr align="center" bgcolor = "F4F4F4">
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[35] eq 'table-cell'}">checked</c:if>/>${trColums[35]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[36] eq 'table-cell'}">checked</c:if>/>${trColums[36]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[37] eq 'table-cell'}">checked</c:if>/>${trColums[37]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[38] eq 'table-cell'}">checked</c:if>/>${trColums[38]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[39] eq 'table-cell'}">checked</c:if>/>${trColums[39]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[40] eq 'table-cell'}">checked</c:if>/>${trColums[40]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[41] eq 'table-cell'}">checked</c:if>/>${trColums[41]}</td>
		  		</tr>
		  		<tr align="center" bgcolor = "E0ECFF">
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[42] eq 'table-cell'}">checked</c:if>/>${trColums[42]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[43] eq 'table-cell'}">checked</c:if>/>${trColums[43]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[44] eq 'table-cell'}">checked</c:if>/>${trColums[44]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[45] eq 'table-cell'}">checked</c:if>/>${trColums[45]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[46] eq 'table-cell'}">checked</c:if>/>${trColums[46]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[47] eq 'table-cell'}">checked</c:if>/>${trColums[47]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[48] eq 'table-cell'}">checked</c:if>/>${trColums[48]}</td>
		  		</tr>
		  		<tr align="center" bgcolor = "F4F4F4">
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[49] eq 'table-cell'}">checked</c:if>/>${trColums[49]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[50] eq 'table-cell'}">checked</c:if>/>${trColums[50]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[51] eq 'table-cell'}">checked</c:if>/>${trColums[51]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[52] eq 'table-cell'}">checked</c:if>/>${trColums[52]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[53] eq 'table-cell'}">checked</c:if>/>${trColums[53]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[54] eq 'table-cell'}">checked</c:if>/>${trColums[54]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[55] eq 'table-cell'}">checked</c:if>/>${trColums[55]}</td>
		  		</tr>
		  		<tr align="center" bgcolor = "E0ECFF">
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[56] eq 'table-cell'}">checked</c:if>/>${trColums[56]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[57] eq 'table-cell'}">checked</c:if>/>${trColums[57]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[58] eq 'table-cell'}">checked</c:if>/>${trColums[58]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[59] eq 'table-cell'}">checked</c:if>/>${trColums[59]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[60] eq 'table-cell'}">checked</c:if>/>${trColums[60]}</td>
		  			<td><input type = "checkbox" name = "box" <c:if test = "${cols[61] eq 'table-cell'}">checked</c:if>/>${trColums[61]}</td>
		  			<td>@author MILI</td>
		  		</tr>
			</table>
			<br/><br/><br/>
			<div style = "border: 0px solid #F00">
				<div style = "padding-left: 900px">
					<input type = "button" onclick = "checkeds();"  value = "确定"/> | 
					<input type = "button" onclick = "window.close()"  value = "返回"/>
				</div>
			</div>
		</form>
  	</div>
  </body>
</html>
