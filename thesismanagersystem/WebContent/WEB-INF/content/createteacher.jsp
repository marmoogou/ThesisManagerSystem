<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="teacherdata.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师录入页面</title>
		<link rel="stylesheet" type="text/css" href="css/all.css"/>
		<link rel="stylesheet" type="text/css" href="css/teacherLoggin-css.css"/>
<body>
<%
teacherdata data=new teacherdata();
if(request.getSession().getAttribute("tt")!=null){
	data=(teacherdata)request.getSession().getAttribute("tt");
}
%>
<div class="top ">
			<div class="top-school pic">
				<img src="img/school.png" width="300px" />
				<!--<img src="img/login_logo.jpg"/>-->
			</div>
			<p class="top-title title">
				毕业论文管理系统
			</p>
			<div class="top-help">
				<a href="#">帮助中心</a>
			</div>
			<div class="top-exit">
				<a href="#">返回上一级</a>
			</div>
			
		</div>
		<!--top结束-->
		<!--内容开始-->
		<div class="s-content">
			<div class="s-content-hea">
				<div class="s-content-hea-1">
					<img src="img/personal.png" />
					<span>个人中心</span>

				</div>
				<div class="s-content-hea-2">
					<div class="a1">
						<div class="user">
							<p class="user-p">用户名</p>
							<ul class="user-ul">
								<div class="s-arrow">

								</div>
							
							</ul>
						</div>
						<p class="triangle"></p>
						<a class="right" href="#">首页</a>

						
					</div>
				</div>
			</div>
			<div class="s-content-down">
				<div class="s-content-down-r">
					<div class="words">
						<p>账户设置</p>
						<span>绑定设置</span>
					</div>
					
					<ul class="info">
						
						
						<li class="change">
							<p class="change-p1">密码：</p>
							<p class="change-p2">QQ：</p>
							<p class="change-p3">邮箱：</p>
							<p class="change-p4">电话：</p>
							<p class="change-p5">工资号：</p>
							<p class="change-p6">专业：</p>
							<p class="change-p7">姓名：</p>
							<p class="change-p8">职称：</p>
							
							<form action="administratorpage.action?updatett=yes" method="post">
								<input type="text"  name="updatetsalaryid"/>
								<input type="text"  name="majorin"/>
								<input type="text" name="updatetname"/>
								<input type="text" name="updatettitle"/>
								<input type="text"  name="updatetpassword"/>
								<input type="text"  name="updatetqq"/>
								<input type="text"  name="updatetemail"/>
								<input type="text"   name="updatettel"/>
								<input class="submit" type="submit"  value="确认修改并保存" />
							</form>
							
						</li>
						
					</ul>
				</div>
			</div>
		</div>
</body>
</html>