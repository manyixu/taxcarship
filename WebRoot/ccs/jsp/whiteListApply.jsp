<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="inc-common.jsp" %>
<!DOCTYPE html>
<html>
<head>

	<link href="../css/main.css" rel="stylesheet" type="text/css"/>
	<script src="../js/whiteListApply.js"></script>
	<script type="text/javascript">

	function selectType(){	//选择输入类型
		if($('#inputtype').val()=='mannual'){
			$('#handType').hide();
			$('#fileType').hide();
		}else{
			$('#handType').hide();
			$('#fileType').show();
		}
	}
	</script>
	
</head>
<body onload="formInit()">
	<div id="head">
		<h1>问题名单申请</h1>
	</div>
	<div id="data-head" >
			请选择录入方式：
			<select id="inputtype" name="inputtype" onchange="selectType()" >   
				<option value="mannual" selected="selected">手工录入</option>   
				<option value="file">文件导入</option> 
			</select>
		<form action = "" enctype="multipart/form-data" id = "file_up" method="post"> 
			<div id="handType" style="">
				<a id="btn" href="#" style="align:right;" onclick="hand()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">手工录入</a>
			</div>
			
			<div id="fileType" style="">
				选择数据文件：&nbsp;&nbsp;&nbsp;
				<input type="file" size="30" name="fileToUpload" id="fileToUpload" multiple="multiple" onchange="" />&nbsp;&nbsp;&nbsp;
				<a id="btn" href="#" style="align:right;" onclick="file_upload();" class="easyui-linkbutton" data-options="iconCls:'icon-add'" type="submit">导入数据</a>
			</div>
		</form>
	</div>
	    <form action=""> 
		<table id="tt"></table>
	    </form>
	    <div id="dlg" class="easyui-dialog" style="width:700px;height:400px;padding:10px 20px;"
	            closed="true" modal="true" buttons="#dlg-buttons">
	        <form id="fm" method="post" novalidate enctype="multipart/form-data" name="form1">
	           <input type="hidden" name="aUrl" id="hid"></input>
	           <table border="0px">
	           <tr><th>异常原因</th></tr>
	           <tr><td>异常原因类型:</td><td> 
	           <select name="ycyy" id="ycyy" onchange="ycyy_flag()">
	           		 <option value="P" selected="selected">算欠税和滞纳金</option>
	           		 <option value="S">只算欠税</option>
	           		 <option value="M">负值（多缴税款）</option>
	           		 <option value="W">白名单</option>
	           </select></td></tr>
	           <tr><th>车辆信息</th></tr>
	    		<tr>
	    			<td width="100px">车辆识别代码:</td>	<!-- onBlur = "validate()" -->
	    			<td width="200px"><input class="easyui-validatebox" type="text" name="CLSBDM" id = "CLSBDM" data-options="required:true" validType="length[0,17]" missingMessage="不能为空"></input></td>
	    			<td>号牌号码:</td>
	    			<td>
					  <select name="jc1" id="jc1">
					  <option selected="selected" value="粤">粤</option>
					  <option value="浙">浙</option>
					  <option value="京">京</option>
					  <option value="沪">沪</option>
					  <option value="川">川</option>

					  <option value="津">津</option>
					  <option value="渝">渝</option>
					  <option value="鄂">鄂</option>
					  <option value="赣">赣</option>
					  <option value="冀">冀</option>
					  <option value="蒙">蒙</option>

					  <option value="鲁">鲁</option>
					  <option value="苏">苏</option>
					  <option value="辽">辽</option>
					  <option value="吉">吉</option>
					  <option value="皖">皖</option>
					  <option value="湘">湘</option>

					  <option value="黑">黑</option>
					  <option value="琼">琼</option>
					  <option value="贵">贵</option>
					  <option value="桂">桂</option>
					  <option value="云">云</option>
					  <option value="藏">藏</option>

					  <option value="陕">陕</option>
					  <option value="甘">甘</option>
					  <option value="宁">宁</option>
					  <option value="青">青</option>
					  <option value="豫">豫</option>
					  <option value="闽">闽</option>

					  <option value="新">新</option>
					  <option value="晋">晋</option>

					</select>
					<select name="jc2" id="jc2">
					  <option selected="selected" value="A">A</option>
					  <option value="B">B</option>
					  <option value="C">C</option>

					  <option value="D">D</option>
					  <option value="E">E</option>
					  <option value="F">F</option>
					  <option value="G">G</option>
					  <option value="H">H</option>
					  <option value="I">I</option>

					  <option value="J">J</option>
					  <option value="K">K</option>
					  <option value="L">L</option>
					  <option value="M">M</option>
					  <option value="N">N</option>
					  <option value="O">O</option>

					  <option value="P">P</option>
					  <option value="Q">Q</option>
					  <option value="R">R</option>
					  <option value="S">S</option>
					  <option value="T">T</option>
					  <option value="U">U</option>

					  <option value="V">V</option>
					  <option value="W">W</option>
					  <option value="X">X</option>
					  <option value="Y">Y</option>
					  <option value="Z">Z</option>
					</select>
                      <input name="cphm" type="text" id="cphm" size="10" style="width:70px" />
		             </td>
	    		</tr>
	    		<tr>
	    			<td>号牌种类:</td>
	    			<td>
	    			<input class="easyui-numberbox" type="text" name="HPZL" data-options="required:false" validType="length[0,2]" missingMessage="只能为数字"></input>
					</td>
	    			<td>车辆种类:</td>
	    			<td>
	    		<!-- 	<input class="easyui-validatebox" type="text" name="CLZL" data-options="required:true" validType="length[0,2]" missingMessage="不能为空"></input>  -->
	    			<select name="CLZL" id="CLZL">
					  <option selected="selected" value="11">11</option>
					  <option value="12">12</option>
					  <option value="13">13</option>
					  <option value="14">14</option>
					  <option value="15">15</option>
					  <option value="21">21</option>
					  <option value="22">22</option>
					  <option value="23">23</option>
					  <option value="24">24</option>
					  <option value="25">25</option>
					  <option value="26">26</option>
					  <option value="27">27</option>
					  <option value="28">28</option>
					  <option value="30">30</option>
					  <option value="31">31</option>
					  <option value="40">40</option>
					  <option value="41">41</option>
					  <option value="50">50</option>
					  <option value="51">51</option>
					  <option value="60">60</option>
					  <option value="71">71</option>
					  <option value="72">72</option>
					  <option value="73">73</option>
					  <option value="93">93</option>
					  
					  <option value="BA">BA</option>
					  <option value="BB">BB</option>
					  <option value="BC">BC</option>
					  <option value="BD">BD</option>
					  <option value="BE">BE</option>
					  <option value="BF">BF</option>
					  <option value="BG">BG</option>
					  
					  <option value="BA">CA</option>
					  <option value="BB">CB</option>
					  <option value="BC">CC</option>
					  <option value="BD">CD</option>
					  <option value="BE">CE</option>
					  <option value="BF">CF</option>
					  <option value="BG">CG</option>
					</select>
					</td>
	    		</tr>
	    		<tr>
	    			<td>发动机号:</td>
	    			<td><input class="easyui-validatebox" type="text" id="FDJH" name="FDJH" data-options="required:true" missingMessage="不能为空"></input></td>
	    			<td>制造厂名称:</td>
	    			<td><input class="easyui-validatebox" type="text" name="ZCCMC" data-options="required:false" missingMessage="不能为空"></input></td>
	    		</tr>
	    		<tr>
	    			<td>使用性质:</td>
	    			<td>
	    			  <select name="SYXZ" id="SYXZ" data-options="required:true" validType="length[0,3]" missingMessage="不能为空">
					  <option selected="selected" value="A">非营运</option>
					  <option value="B">公路客运</option>
					  <option value="C">公交客运</option>

					  <option value="D">出租客运</option>
					  <option value="E">旅游客运</option>
					  <option value="F">货运</option>
					  <option value="G">租赁</option>
					  <option value="H">警用</option>
					  <option value="I">消防</option>

					  <option value="J">救护</option>
					  <option value="K">工程抢险车</option>
					  <option value="L">营转非</option>
					  <option value="M">出租转非</option>
					  <option value="Z">其它</option>
					</select>
	    			
	    			</td>
	    			<td>车辆型号:</td>
	    			<td><input class="easyui-validatebox" type="text" name="CLXH" missingMessage="不能为空"></input></td>
	    		</tr>
	    			<tr>
	    			<td>初登日期:</td>
	    			<td><input class="easyui-datebox" type="text" id="CLCSDJRQ" name="CLCSDJRQ" editable="false" data-options="formatter:myformatter" required="true" missingMessage="不能为空"></input></td>
	    			<td>交管车辆类型:</td>
	    			<td><input class="easyui-validatebox" type="text" name="JGCLLX" missingMessage="不能为空"></input></td>
	    		</tr>
	    			<tr>
	    			<td>核定载客数:</td>
	    			<td><input class="easyui-numberbox" type="text" name="HDZKS" data-options="required:false" valueField="number"></input></td>
	    			<td>核定载客质量:</td>
	    			<td><input class="easyui-numberbox" type="text" name="HDZZL" data-options="required:false" valueField="number" precision="2"></input></td>
	    		</tr>
	    			<tr>
	    			<td>整备质量:</td>
	    			<td><input class="easyui-numberbox" type="text" id="ZBZL" name="ZBZL" data-options="required:false" valueField="number" precision="2"></input></td>
	    			<td>排量:</td>
	    			<td><input class="easyui-numberbox" type="text" id="PL" name="PL" data-options="required:false" valueField="number" precision="2"></input></td>
	    		</tr>
	    			<tr>
	    			<td>功率:</td>
	    			<td><input class="easyui-numberbox" type="text" name="GL" data-options="required:false" valueField="number" precision="2"></input></td>
	    		</tr>
	    	</table>
	    	
	    	
	    	<table id="xs" style = "display:none">
	    		<tr id="yc1"><th>缴税信息</th></tr>
	    		<tr id="yc3">
	    			<td width="100px">已缴税额:</td>
	    			<td width="200px"><input class="easyui-numberbox" type="text" id="DQYNSE_W" name="DQYNSE_W" data-options="required:true"  precision="2" valueField="number" missingMessage="不能为空"></input></td>
	    			<td>税款所属始期:</td>
	    			<td><input class="easyui-datebox" type="text" id="SKSSSQ_W" name="SKSSSQ_W" data-options="required:true" missingMessage="不能为空"></input></td>
	    		</tr>
	    	</table>
	    	
	    	<table id="yc">
	    		<tr id="yc1"><th>缴税信息</th></tr>
	    		<tr id="yc2">
	    			<td>税款所属始期:</td>
	    			<td><input class="easyui-datebox"  data-options="formatter:myformatter" required="true" name="SKSSSQ" id="SKSSSQ" editable="false" missingMessage="不能为空"></input></td>
	    			<td>税款所属止期:</td>
	    			<td><input class="easyui-datebox" data-options="formatter:myformatter" name="SKSSZQ" editable="false" missingMessage="不能为空"></input></td>
	    		</tr>
	    		<tr id="yc3">
	    			<td>已缴税额:</td>
	    			<td><input class="easyui-numberbox" type="text" id="DQYNSE" name="DQYNSE" precision="2" required="true" valueField="number" missingMessage="不能为空"></input></td>
	    			<td>税务机关代码:</td>
	    			<td><input class="easyui-validatebox" type="text" id="REVENUECODE" name="REVENUECODE" required="true" missingMessage="不能为空"></input></td>
	    		</tr>
	    		<tr id="yc4"><th>完税凭证</th></tr>
	    		<tr id="yc5">
	    			<td>上传完税凭证图片:</td>
	    			<!-- <td><input type="file" data-options="required:true" id ="img" name="imgage"></input></td> -->
	    			<td><input type="file" data-options="required:true" name="WSPZ" id="WSPZ" ></input>
	    			     <input type = "text" readonly="readonly" id = "WS" name = "WS" hidden = "hidden"></input>
	    			</td>
	    			<td>完税凭证号:</td>
	    			<td><input class="easyui-numberbox" type="text" id="WSPZNO" name="WSPZNO" required="true" missingMessage="只能为数字"></input></td>
	    		</tr>
	    		<tr>
	    		<td><a id = "photo" href = "#" onclick = "photo();" >完税凭证图片链接地址</a></td>
                    <td><span id="view"></span><br></td>
                </tr>
	    	</table>
	    	<div id="dlg-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton c6" id="save" iconCls="icon-ok" onclick="saveUser()" type="submit">提交</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton c6" id="edit" iconCls="icon-ok" onclick="saveEdit()" type="submit" >保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	        </div>
	        </form>
	    
	   
	    <!-- 
	    <a id="btn" href="#" style="align:right;" onclick="postToCheck()" class="easyui-linkbutton" data-options="iconCls:''">提交审核</a>
	     -->
	</div>
	 <!-- 照片展示 -->
            <div closed = "true" id="photo_open" class="easyui-window" style="width:700px;height:450px;" > 
           		<img id = "img_address"/>
            </div>
</body>
</html>