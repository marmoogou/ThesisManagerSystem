<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="studentdata.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生个人中心</title>
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="js/studentPersonal-js.js"></script>
		<link rel="stylesheet" type="text/css" href="css/all.css" />
		<link rel="stylesheet" type="text/css" href="css/studentPersonal-css.css" />
</head>
<body>
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
				<p>欢迎
					<a href="#"><%=request.getSession().getAttribute("studentname") %></a> 同学!</p>
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
								<li>
									<a href="#">账户设置</a>
								</li>
								<li>
									<a href="#">我的选题</a>
								</li>
								<li>
									<a href="#">退出</a>
								</li>
							</ul>
						</div>
						<p class="triangle"></p>
						<a class="right" href="studentpage.action?abc">首页</a>

						
					</div>
				</div>
			</div>
			<div class="s-content-down">
				<div class="s-content-down-l">
					<ul class="s-content-down-l-ul">
						<li>
							<div class="book">
							
							</div>
						<a href="thesispage.action?query=111">我的选题</a>
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
				<%studentdata data=(studentdata)request.getSession().getAttribute("studentdatas"); %>
				<div class="s-content-down-r">
					<div class="words">
						<p>账户设置</p>
						<span>绑定设置</span>
					</div>
					<ul class="info">
						<li>
							<p>姓名：</p>
							<span><%=data.getStudentname() %></span>
						</li>
						<li>
							<p>性别：</p>
							<span><%=data.getStudentsex() %></span>
						</li>
						<li>
							<p>学号：</p>
							<span><%=data.getStudentid() %></span>
							
						</li>
						<li>
							<p>专业：</p>
							<span><%=data.getStudentmajor() %></span>
							
						</li>
						<li>
							<p>班级：</p>
							<span><%=data.getStudentgrade() %></span>
							
						</li>
						<li class="change">
							<p class="change-p1">密码：</p>
							<p class="change-p2">QQ：</p>
							<p class="change-p3">邮箱：</p>
							<p class="change-p4">电话：</p>
							<form action="studentpersonpage.action?update=yes" method="post">
								
								<input type="text" value=<%=data.getStudentpassword() %> name="updatespassword"/>
								<input type="text" value=<%=data.getStudentqq() %> name="updatesqq"/>
								<input type="text" value=<%=data.getStudentemail() %> name="updatesemail"/>
								<input type="text" value=<%=data.getStudenttel() %> name="updatestel"/>
								<input class="submit" type="submit" name="" id="" value="确认修改并保存" />
							</form>
							
						</li>
						
					</ul>
				</div>
			</div>
		</div>
		<!--内容结束-->
</body>
</html>