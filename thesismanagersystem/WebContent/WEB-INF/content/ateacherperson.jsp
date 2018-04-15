<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="teacherdata.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改老师信息</title>
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="js/teacherPersonal-js.js"></script>
		<link rel="stylesheet" type="text/css" href="css/all.css"/>
		<link rel="stylesheet" type="text/css" href="css/a-teacherPersonal-css.css"/>
</head>
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
								<li>
									<a href="#">账户设置</a>
								</li>
								<li>
									<a href="#">我的课题</a>
								</li>
								<li>
									<a href="#">退出</a>
								</li>
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
							
							
						</li>
						<li class="message">
							
							
						</li>
					</ul>
				</div>
				<div class="s-content-down-r">
					<div class="words">
						<p>账户设置</p>
						<span>绑定设置</span>
					</div>
					<ul class="info">
					
						
						<li class="change">
							
							<p class="change-p8">专业：</p>
							<p class="change-p5">姓名：</p>
							<p class="change-p6">职称：</p>
							
							<p class="change-p1">密码：</p>
							<p class="change-p2">QQ：</p>
							<p class="change-p3">邮箱：</p>
							<p class="change-p4">电话：</p>
							<form action="ateacherpersonpage.action?update=yes&teacherid=<%=data.getSalaryid()%>" method="post">
								<input type="text" value=<%=data.getMajor() %> name="updatetmajor"/>
								<input type="text" value=<%=data.getTeachername() %> name="updatetname"/>
								<input type="text" value=<%=data.getTeachertitle() %> name="updatettitle"/>
								<input type="text" value=<%=data.getTeacherpassword() %> name="updatetpassword"/>
								<input type="text" value=<%=data.getTeacherqq() %> name="updatetqq"/>
								<input type="text" value=<%=data.getTeacheremail() %> name="updatetemail"/>
								<input type="text" value=<%=data.getTeachertel() %> name="updatettel"/>
								<input class="submit" type="submit" name="" id="" value="确认修改并保存" />
							</form>
								<form class="delete" action="ateacherpersonpage.action?deleteteacher=yes&teacherid=<%=data.getSalaryid() %>" method="post">
								
								<input type="submit" value="删除"/>
							</form>
							<form class="promote" action="ateacherpersonpage.action?upteacher=yes&teacherid=<%=data.getSalaryid() %>" method="post">
								
								<input type="submit" value="提升权限"/>
							</form>
						</li>
						
					</ul>
				</div>
			</div>
		</div>
</body>
</html>