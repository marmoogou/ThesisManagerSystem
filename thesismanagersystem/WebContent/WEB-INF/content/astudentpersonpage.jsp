<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="studentdata.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改学生信息</title>
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="js/studentPersonal-js.js"></script>
		<link rel="stylesheet" type="text/css" href="css/all.css" />
		<link rel="stylesheet" type="text/css" href="css/a-studentPersonal-css.css" />
</head>
<body>
<!--top开始-->
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
			<div class="top-name">
				
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
				<div class="s-content-down-l">
					<ul class="s-content-down-l-ul">
						<li>
							<div class="book">
							
							</div>
						
						</li>
						<li class="special">
							<div class="account">
							
							</div>
							<a href="#">我的账户</a>
							
						</li>
						<li class="message">
							<a href="#">基本信息</a>
							
						</li>
					</ul>
				</div>
				<div class="s-content-down-r">
					<div class="words">
						<p>账户设置</p>
						<span>绑定设置</span>
					</div>
					<ul class="info">
						<%studentdata data=(studentdata)request.getSession().getAttribute("ss"); %>
						<li class="change">
							<p class="change-p5">姓名：</p>
							<p class="change-p6">学号：</p>
							<p class="change-p7">专业：</p>
							<p class="change-p8">班级：</p>
							<p class="change-p9">性别：</p>
							<p class="change-p1">密码：</p>
							<p class="change-p2">QQ：</p>
							<p class="change-p3">邮箱：</p>
							<p class="change-p4">电话：</p>
							<form action="astudentpersonpage.action?update=yes&studentid=<%=data.getStudentid() %>" method="post">
								<input type="text" value=<%=data.getStudentname() %> name="updatesname"/>
								<input type="text" value=<%=data.getStudentsex() %> name="updatessex"/>
								<p class="sId"><%=data.getStudentid() %> </p>
								<input type="text" value=<%=data.getStudentmajor() %> name="updatesmajor"/>
								<input type="text" value=<%=data.getStudentgrade() %> name="updatesgrade"/>
								
								<input type="text" value=<%=data.getStudentpassword() %> name="updatespassword"/>
								<input type="text" value=<%=data.getStudentqq() %> name="updatesqq"/>
								<input type="text" value=<%=data.getStudentemail() %> name="updatesemail"/>
								<input type="text" value=<%=data.getStudenttel() %> name="updatestel"/>
								<input class="submit" type="submit" name="" id="" value="确认修改并保存" />
							</form>
							<form class="delete" action="astudentpersonpage.action?deletestudent=yes&studentid=<%=data.getStudentid() %>" method="post">
								
								<input type="submit" value="删除"/>
							</form>
						</li>
						
					</ul>
				</div>
			</div>
		</div>
		<!--内容结束-->

</body>
</html>