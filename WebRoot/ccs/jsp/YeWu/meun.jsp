<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="javax.activation.URLDataSource"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ include file = "../inc-common.jsp" %>
<%@ page import = "com.derun.monitors.Meun_VO" %>

<% 
	Meun_VO meun_vo = new Meun_VO();
	List<Meun_VO> list = new ArrayList<Meun_VO>();
	String trContent = new String(request.getParameter("Columns").getBytes("ISO-8859-1"), "UTF-8");
	String title = new String(request.getParameter("Title").getBytes("ISO-8859-1"), "UTF-8");
	String columns[] = trContent.split(",");
	String titles[] = title.split(",");
	for(int i = 0 ;i < columns.length ;){
		meun_vo.setHidden1(columns[i]);
		meun_vo.setTitle1(titles[i++]);
		if(i >= columns.length){
			break;
		}
		meun_vo.setHidden2(columns[i]);
		meun_vo.setTitle2(titles[i++]);
		if(i >= columns.length){
			break;
		}
		meun_vo.setHidden3(columns[i]);
		meun_vo.setTitle3(titles[i++]);
		if(i >= columns.length){
			break;
		}
		meun_vo.setHidden4(columns[i]);
		meun_vo.setTitle4(titles[i++]);
		list.add(meun_vo);
		meun_vo = new Meun_VO();
	}
	request.setAttribute("cols",list);
%>
<html>
<head>
	<title>选择界面</title>
	<script type="text/javascript">
		function checkeds(){
			var boxs = document.getElementsByName('box') ;
			var str = [] ;
			for(var i = 0 ; i < boxs.length ; i++){
				if(boxs[i].checked){
					str[i] = false ;
				}else{
					str[i] = true ;
				}
			}
//			window.opener.initTables(str);
			window.opener.retuen_add(str);
			window.close();
			
		}
	</script>
</head>
   <body onload = "">
   
   <br/>
   <center><font color = "blue" face = "隶书" size = "5">选择列</font></center><br/>
		<form action = "" method = "post">
			<table border = "0" align = "center" style = "font-size : 13px">
				<c:forEach items = "${cols}" var = "col" varStatus = "status">
					<c:if test = "${status.index % 2 != 1}">
		 				<tr align = "center" bgcolor = "E0ECFF" height = "30px">
		 				<td width = "180px" ><input type = "Checkbox" name = "box" <c:if test = "${col.hidden1 eq false }">checked</c:if>/>${col.title1}</td>
		 				<td width = "180px" ><input type = "Checkbox" name = "box" <c:if test = "${col.hidden2 eq false }">checked</c:if>/>${col.title2}</td>
		 				<td width = "180px" ><input type = "Checkbox" name = "box" <c:if test = "${col.hidden3 eq false }">checked</c:if>/>${col.title3}</td>
		 				<td width = "180px" ><input type = "Checkbox" name = "box" <c:if test = "${col.hidden4 eq false }">checked</c:if>/>${col.title4}</td>
		 				</tr>
	 				</c:if>
	 				<c:if test = "${status.index % 2 == 1}">
		 				<tr align = "center" bgcolor = "F4F4F4" height = "30px">
		 				<td width = "180px" ><input type = "Checkbox" name = "box" <c:if test = "${col.hidden1 eq false}">checked</c:if>/>${col.title1}</td>
		 				<td width = "180px" ><input type = "Checkbox" name = "box" <c:if test = "${col.hidden2 eq false}">checked</c:if>/>${col.title2}</td>
		 				<td width = "180px" ><input type = "Checkbox" name = "box" <c:if test = "${col.hidden3 eq false}">checked</c:if>/>${col.title3}</td>
		 				<td width = "180px" ><input type = "Checkbox" name = "box" <c:if test = "${col.hidden4 eq false}">checked</c:if>/>${col.title4}</td>
		 				</tr>
	 				</c:if>
				</c:forEach>
			</table>
			<br/><br/>
			<input type = "button" onclick = "checkeds();"  value = "确定"/>
		</form>
</body>
</html>