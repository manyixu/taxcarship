<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String trContent = new String(request.getParameter("trContent").getBytes("ISO-8859-1"), "UTF-8");
%>
<!DOCTYPE html>
<html>	

<head>	
	<title>车辆纳税信息明细</title>
</head>
   <body bgcolor = "">	
   			<br/><center><font color = "black" face = "隶书" size = "5">车辆纳税信息明细</font></center><br/>
		<form>	
			<table border = "0" cellpadding = "5px" align = "center" style = "font-size : 13px">
				<tr align="center" bgcolor = "E0ECFF">
					<td>代收公司</td><td>车辆识别码</td><td>号牌号码</td><td>车辆类型</td><td>税款始起</td><td>税款止起</td>
				</tr>
				<tr align="center" bgcolor = "EBEBEB">
					<td><% if("undefined".equals(trContent.split("@")[0])){
						out.print("");
					}else{out.print(trContent.split("@")[0]);}%></td><td><% if("undefined".equals(trContent.split("@")[1])){
						out.print("");
					}else{out.print(trContent.split("@")[1]);}%></td><td><% if("undefined".equals(trContent.split("@")[2])){
						out.print("");
					}else{out.print(trContent.split("@")[2]);}%></td><td><% if("undefined".equals(trContent.split("@")[3])){
						out.print("");
					}else{out.print(trContent.split("@")[3]);}%></td><td><% if("undefined".equals(trContent.split("@")[4])){
						out.print("");
					}else{out.print(trContent.split("@")[4]);}%></td><td><% if("undefined".equals(trContent.split("@")[5])){
						out.print("");
					}else{out.print(trContent.split("@")[5]);}%></td>
				</tr>
				<tr align="center" bgcolor = "E0ECFF">
					<td>纳税类型</td><td>单位税额</td><td>年度税额</td><td>所属年度</td><td>实缴税款</td><td>合计欠税</td>
				</tr>
				<tr align="center" bgcolor = "EBEBEB">
					<td><% if("undefined".equals(trContent.split("@")[6])){
						out.print("");
					}else{out.print(trContent.split("@")[6]);}%></td><td><% if("undefined".equals(trContent.split("@")[7])){
						out.print("");
					}else{out.print(trContent.split("@")[7]);}%></td><td><% if("undefined".equals(trContent.split("@")[8])){
						out.print("");
					}else{out.print(trContent.split("@")[8]);}%></td><td><% if("undefined".equals(trContent.split("@")[9])){
						out.print("");
					}else{out.print(trContent.split("@")[9]);}%></td><td><% if("undefined".equals(trContent.split("@")[10])){
						out.print("");
					}else{out.print(trContent.split("@")[10]);}%></td><td><% if("undefined".equals(trContent.split("@")[11])){
						out.print("");
					}else{out.print(trContent.split("@")[11]);}%></td>
				</tr>
				<tr align="center" bgcolor = "E0ECFF">
					<td>滞纳金</td><td>合计金额</td><td>完税机关代码</td><td>完税机关名称</td><td>完税凭证号码</td><td>减免税机关代码</td>
				</tr>
				<tr align="center" bgcolor = "EBEBEB">
					<td><% if("undefined".equals(trContent.split("@")[12])){
						out.print("");
					}else{out.print(trContent.split("@")[12]);}%></td><td><% if("undefined".equals(trContent.split("@")[13])){
						out.print("");
					}else{out.print(trContent.split("@")[13]);}%></td><td><% if("undefined".equals(trContent.split("@")[14])){
						out.print("");
					}else{out.print(trContent.split("@")[14]);}%></td><td><% if("undefined".equals(trContent.split("@")[15])){
						out.print("");
					}else{out.print(trContent.split("@")[15]);}%></td><td><% if("undefined".equals(trContent.split("@")[16])){
						out.print("");
					}else{out.print(trContent.split("@")[16]);}%></td><td><% if("undefined".equals(trContent.split("@")[17])){
						out.print("");
					}else{out.print(trContent.split("@")[17]);}%></td>
				</tr>
				<tr align="center" bgcolor = "E0ECFF" style = "">
					<td>减免税机关名称</td><td>减免税原因代码</td><td>减免税方案代码</td><td>减免比例</td><td>减免金额</td><td>减免税凭证号</td>
				</tr>
				<tr align="center" bgcolor = "EBEBEB">
					<td><% if("undefined".equals(trContent.split("@")[18])){
						out.print("");
					}else{out.print(trContent.split("@")[18]);}%></td><td><% if("undefined".equals(trContent.split("@")[19])){
						out.print("");
					}else{out.print(trContent.split("@")[19]);}%></td><td><% if("undefined".equals(trContent.split("@")[20])){
						out.print("");
					}else{out.print(trContent.split("@")[20]);}%></td><td><% if("undefined".equals(trContent.split("@")[21])){
						out.print("");
					}else{out.print(trContent.split("@")[21]);}%></td><td><% if("undefined".equals(trContent.split("@")[22])){
						out.print("");
					}else{out.print(trContent.split("@")[22]);}%></td><td><% if("undefined".equals(trContent.split("@")[23])){
						out.print("");
					}else{out.print(trContent.split("@")[23]);}%></td>
				</tr>
			</table>
		</form>	
</body>
</html>