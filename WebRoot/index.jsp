<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>车船税代收代缴联网征收系统</title>
		<meta name="generator" content="MShtml 8.00.6001.18783" />
		<meta name="copyright" content="Copyright 2014 - L-Sky.Cn" />
		<meta name="Author" content="车船税代收代缴联网征收系统 - L-Sky.Cn" />
		<meta name="keywords" content="车船税代收代缴联网征收系统" />
		<meta name="description" content="车船税代收代缴联网征收系统登陆页面" />
		<script src="ccs/js/jquery-1.6.4.min.js"></script>
		<link rel="stylesheet" type="text/css" href="ccs/css/style.css" />
		<style type="text/css">
.download {
	margin: 20px 33px 10px; *
	margin-bottom: 30px;
	padding: 5px;
	border-radius: 3px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	background: #e6e6e6;
	border: 1px dashed #df0031;
	font-size: 14px;
	font-family: Comic Sans MS;
	font-weight: bolder;
	color: #555
}

.download a {
	padding-left: 5px;
	font-size: 14px;
	font-weight: normal;
	color: #555;
	text-decoration: none;
	letter-spacing: 1px
}

.download a:hover {
	text-decoration: underline;
	color: #36F
}

.download span {
	float: right
}
</style>
		<script type="text/javascript">
		
		function loginUrl(){
			if(window.top!=window.self){
				top.location='/taxcarship/index.jsp';
			}
		}
	// 判断是什么浏览器
	function getOs(){   
	    var OsObject = "";   
	    if(navigator.userAgent.indexOf("MSIE")>0){   
	         return "MSIE";   
	    }   
	    if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){   
	         return "Firefox";   
	    }   
	    if(isSafari=navigator.userAgent.indexOf("Safari")>0) {   
	         return "Safari";   
	    }    
	    if(isCamino=navigator.userAgent.indexOf("Camino")>0){   
	         return "Camino";   
	    }   
	    if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){   
	         return "Gecko";   
	    }   
	} 
	// 回车登录
	function KeyDown(){
		//获取当前浏览器;  
      	var browser = getOs(); 
      	//火狐浏览器; 
      	if(browser == "Firefox"){  
          	$("html").die().live("keydown",function(event){       
				if(event.keyCode == 13){      
                	onlogin();
                }       
          	});     
      	}  
      	//如果是IE获取其他浏览器，则调用此种方式;  
      	if(browser == "" || browser == "MSIE"){  
          	if(event.keyCode == 13){  
               onlogin();
            }  
      	}  
	}
		
	function onlogin(){
	        var name = $("#name").val();
	        var pword = $("#password").val();
	        $("#name").focus(function(){
	          $("#umsg").text("");
	        });
	       $("#password").focus(function(){
	          $("#umsg").text("");
	       });
	       if(name==""){
	         $("#umsg").text("用户名不能为空");
	       }
	        else if(pword==""){
	         $("#umsg").text("密码不能为空");
	       }else{
	    	 var url = encodeURI(encodeURI("loginServlet?userName="+name+"&passWord="+pword));
	         $.ajax({
	        	url:url,//"loginServlet",
	            type:"post",
	            //data:{"userName":name,"passWord":pword},
	            beforeSend:function(){
	              $("#umsg").text("正在登录系统,请稍后...");
	              return true;
	            },
	            success:function(result){
	            var json = eval("("+result+")");
	            
	            var key=[];
	             for(var p in json){
	               if(json.hasOwnProperty(p)){
	                  key.push(p);
	               }
	             }
	              if(key=="success"){
	                window.location="ccs/jsp/main.jsp";
	              }else{
	                $("#umsg").text("用户名或密码不对"); 
	              }
	            }
	         
	         });
	       
	       }
	      }
      
	
	</script>
	</head>

	<body  onload="loginUrl()">
		<div class="main">
			<div class="header hide">
				大屏管理系统 Beta 1.0
			</div>
			<div class="content">
				<div class="title hide">
					管理登录
				</div>

				<fieldset>
					<div class="checkbox">
						<label for="remember">
							<span id="umsg" style="color: red"></span>
						</label>
					</div>
					<div class="input">
						<input class="input_all name" name="name" id="name" type="text"
							maxLength="24" value="" onkeydown="KeyDown()" />
					</div>
					<div class="input">
						<input class="input_all password" name="password" id="password"
							type="password" value="" maxLength="24" onkeydown="KeyDown()" />
					</div>

					<div class="enter">
						<input class="button hide" type="submit" value="登录"
							onclick="onlogin()"/>
					</div>
				</fieldset>

			</div>
		</div>

		<!--[if IE 6]>
<script type="text/javascript" src="js/placeholder.js"></script>
<script type="text/javascript" src="js/belatedpng.js" ></script>
<script type="text/javascript">
	DD_belatedPNG.fix("*");
</script>
<![endif]-->
	</body>
</html>
