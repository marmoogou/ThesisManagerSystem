<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<head>
		<meta charset="utf-8" />
		<title>毕业论文管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/index-css.css"/>
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="js/index-js.js"></script>
		<link rel="stylesheet" type="text/css" href="css/all.css"/>
		<script  type="text/javascript">
		function myReload(){
			//1  获得img对象
			var img = document.getElementById("checker");
			//2 改变img对象 src属性
				img.src = "getchecker.action?abc="+new Date();
		
		}
	
		</script>
	
	</head>
	<body>
		<div class="all">
			<div class="all-pic">
				<img class="all-pic-img1" src="img/school-hui.jpg" width="47px" height="44px"/>
				
				<img src="img/login_logo.jpg"/>
			</div>
			<p class="all-title title">
				毕业论文管理系统
			</p>
			<div class="bluearrows">
				<img src="img/hat.png" width="400px"/>
			</div>
			<div class="safe">
				<p>毕业论文</p>
				</div>
				<div class="safe-e">
				<p>Graduation Thesis</p>	
				
				</div>
				
			
			<div class="all-help help">
				<a href="#">帮助中心</a>
			</div>
			<div class="all-onload">
				<p class="all-words">毕业论文登录系统</p>
				<font class="break" color="red" ><%=request.getSession().getAttribute("suggest")==null?"  ":request.getSession().getAttribute("suggest")%></font>
				<%request.getSession().setAttribute("suggest", " "); %>
				<form action="login.action" method="post">
					<input class="all-words-1" type="text" value="用户名" name="username"/> 
					<input id="pwd" class="all-words-2" type="password" value="密码" name="password"/>
					<input id="showPwd" class="all-words-2" type="text" value="密码" tabindex="2" /> 
					<input class="all-words-3" type="text" value="请输入验证码" name="checker"/>
					<div class="yzm-pic" >
					<img  src="getchecker.action" id="checker"  onclick="myReload();">
						
					</div>
					<div class="exchange">
						<a href="#top"  onclick="myReload()" >看不清楚，换一张</a>
					</div>
					<input class="all-words-4" type="submit" name="" id="" value="登录" />
						
				</form>
					<script type="text/javascript">
					var showPwd = $("#showPwd"), pwd = $("#pwd");  
					showPwd.focus(function(){  
					   pwd.show().focus();  
					   showPwd.hide();  
					});  
					  
					pwd.blur(function(){  
					   if(pwd.val()=="") {  
					       showPwd.show();  
					       pwd.hide();  
					    }  
					}); 
					
					
					</script>
					
			</div>
		</div>
	</body>

</html>