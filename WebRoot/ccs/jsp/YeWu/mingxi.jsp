<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page import = "com.derun.model.po.SYJK_CCS_RKMX" %>
<%@ page import = "com.derun.monitors.yewu.Ajax_Servlet" %>
<%@ page import = "com.derun.monitors.yewu.YeWu_Assist" %>
<%@ page import = "com.derun.common.util.DateUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";	
%>
<%
	String trString = new String(request.getParameter("taxconfig").getBytes("ISO-8859-1"), "UTF-8");
	Ajax_Servlet ajax = new Ajax_Servlet();
	YeWu_Assist yewu = new YeWu_Assist();
	SYJK_CCS_RKMX rkmx = yewu.getRKMX(trString);
	DateUtil dateutil = new DateUtil();
	String InsureStartDate = dateutil.getStringDate(rkmx.getInsureStartDate(),null);
	String InsureEndDate = dateutil.getStringDate(rkmx.getInsureEndDate(),null);
//	out.print(rkmx.getHPHM());
//	request.setAttribute("trColums",rkmx);
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据明细</title>
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
			var td_color = document.getElementById('table_xx');
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
  
  <body onload = "row_color();" bgcolor="">
  	<br/><center><font color = "black" face = "隶书" size = "5">车辆纳税信息明细</font></center><br/>
  	<div style = "border:0px solid #000 ;width: auto;height: auto;">
  		<table id = "table_xx" cellpadding = "5px" align = "center" style="font-size:13px;">
	  		<tr align="center" bgcolor = "E0ECFF">		
	  			<td>序号</td><td></td><td>代收公司</td><td><%=rkmx.getPAYCOMPANYCODE()%></td><td>车架号</td>
	  			<td><%=rkmx.getVIN()%></td><td>号牌号码</td><td><%=rkmx.getHPHM()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #EBEBEB -->
	  			<td>号牌种类</td><td><%=rkmx.getHPZL()%></td><td>车辆类型</td><td><%=rkmx.getCLLX()%></td><td>纳税类型</td>
	  			<td><%=rkmx.getTAXCONDITIONCODE()%></td><td>纳税地区代码</td><td><%=rkmx.getTAXLOCATIONCODE()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #E0ECFF -->
	  			<td>税款所属始期</td><td><%=rkmx.getTAXSTARTDATE()%></td><td>税款所属止期</td><td><%=rkmx.getTAXENDDATE()%></td><td>计税单位代码</td>
	  			<td><%=rkmx.getTAXUNITTYPECODE()%></td><td>单位计税金额</td><td><%=rkmx.getUNITRATE()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #EBEBEB -->
	  			<td>年单位税额</td><td><%=rkmx.getANNUALTAXAMOUNT()%></td><td>完税机关代码</td><td><%=rkmx.getTAXDEPARTMENTCODE() == null ? "" : rkmx.getTAXDEPARTMENTCODE()%></td><td>完税机关名称</td>
	  			<td><%=rkmx.getTAXDEPARTMENT() == null ? "" : rkmx.getTAXDEPARTMENT()%></td><td>完税凭证号码</td><td><%=rkmx.getTAXDOCUMENTNUMBER()==null ? "" :rkmx.getTAXDOCUMENTNUMBER()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #E0ECFF -->
	  			<td>减免税机关代码</td><td><%=rkmx.getDEDUCTIONDEPARTMENTCODE()==null?"":rkmx.getDEDUCTIONDEPARTMENTCODE()%></td><td>减税税机关名称</td><td><%=rkmx.getDEDUCTIONDEPARTMENT()==null?"":rkmx.getDEDUCTIONDEPARTMENT()%></td><td>减免税原因</td>
	  			<td><%=rkmx.getDEDUCTIONDUECODE()==null?"":rkmx.getDEDUCTIONDUECODE()%></td><td>减免方案</td><td><%=rkmx.getDEDUCTIONDUETYPE()==null?"":rkmx.getDEDUCTIONDUETYPE()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #EBEBEB -->
	  			<td>减免比例</td><td><%=rkmx.getDEDUCTIONDUEPROPORTION()%></td><td>减免金额</td><td><%=rkmx.getDEDUCTION()%></td><td>减免税凭证号</td>
	  			<td><%=rkmx.getDEDUCTIONDOCUMENTNUMBER()==null?"":rkmx.getDEDUCTIONDOCUMENTNUMBER()%></td><td>当期应纳税额</td><td><%=rkmx.getTAXDUE()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #E0ECFF -->
	  			<td>逾期时间</td><td><%=rkmx.getEXCEEDDATE()==null?"":rkmx.getEXCEEDDATE()%></td><td>逾期天数</td><td><%=rkmx.getEXCEEDDAYSCOUNT()%></td><td>滞纳金</td>
	  			<td><%=rkmx.getOVERDUE()%></td><td>合计金额</td><td><%=rkmx.getTOTALAMOUNT()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #EBEBEB -->
	  			<td>操作员</td><td><%=rkmx.getLOGINNAME()%></td><td>税务机关编码</td><td><%=rkmx.getREVENUECODE()%></td><td>系统采集日期</td>
	  			<td><%=rkmx.getSJCJRQ()%></td><td>所属年度</td><td><%=rkmx.getPAYDATE()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #E0ECFF -->
	  			<td>功率</td><td><%=rkmx.getPOWER()%></td><td>合计金额标志码</td><td><%=rkmx.getTAXAMOUNT_FLAG()%></td><td>本年车船金额</td>
	  			<td><%=rkmx.getANNUALTAXDUE()%></td><td>合计欠税金额</td><td><%=rkmx.getSUMTAXDEFAULT()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #EBEBEB -->
	  			<td>合计滞纳金</td><td><%=rkmx.getSUMOVERDUE()%></td><td>合计金额</td><td><%=rkmx.getSUMTAX()%></td><td>注销状态</td>
	  			<td><%=rkmx.getLOGGEDOUT()%></td><td>平台状态</td><td><%=rkmx.getPLATFORMSTATE()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #E0ECFF -->
	  			<td>变更类型</td><td><%=rkmx.getCHANGETYPE()%></td><td>算税标志</td><td><%=rkmx.getCOUNTTAXTYPE()%></td><td>拒缴标志</td>
	  			<td><%=rkmx.getRefusetype()%></td><td>申报日期</td><td><%=rkmx.getSTATUSDATE()==null?"":rkmx.getSTATUSDATE()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #EBEBEB -->
	  			<td>车辆初等日期</td><td><%=rkmx.getFIRSTREGISTERDATE()%></td><td>特殊车标志</td><td><%=rkmx.getSPECIALCARTYPE()==null?"":rkmx.getSPECIALCARTYPE()%></td><td>退税标志</td>
	  			<td><%=rkmx.getTSBZ()%></td><td>发动机号</td><td><%=rkmx.getEngineNo()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #E0ECFF -->
	  			<td>投保始期</td><td><%=InsureStartDate%></td><td>投保止期</td><td><%=InsureEndDate%></td><td>问题序列号</td>
	  			<td><%=rkmx.getCARSERIALNO()==null?"":rkmx.getCARSERIALNO()%></td><td>使用性质</td><td><%=rkmx.getMOTORUSAGETYPECODE()==null?"":rkmx.getMOTORUSAGETYPECODE()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #EBEBEB -->
	  			<td>车辆型号</td><td><%=rkmx.getMODEL()==null?"":rkmx.getMODEL()%></td><td>交管车辆类型</td><td><%=rkmx.getVEHICLETYPE()==null?"":rkmx.getVEHICLETYPE()%></td><td>核定载客人数</td>
	  			<td><%=rkmx.getRATEDPASSENGERCAPACITY()%></td><td>确认码</td><td><%=rkmx.getTAXCONFIRMNO()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #E0ECFF -->
	  			<td>整备质量</td><td><%=rkmx.getWHOLEWEIGHT()%></td><td>排量</td><td><%=rkmx.getDISPLACEMENT()%></td><td>能源种类</td>
	  			<td><%=rkmx.getFUELTYPE()==null?"":rkmx.getFUELTYPE()%></td><td>查询码</td><td><%=rkmx.getTAXQUERYNO()%></td>
	  		</tr>
	  		<tr align="center" bgcolor = "">	<!-- #EBEBEB -->
	  			<td>核定载客质量</td><td><%=rkmx.getTONNAGE()%></td><td>备注</td><td colspan = "5"></td>
	  		</tr>
  		</table>
  	</div>
  </body>
</html>
