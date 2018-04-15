<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小管理员页面</title>
		<link rel="stylesheet" type="text/css" href="css/all.css"/>
		<link rel="stylesheet" type="text/css" href="css/administrator-css.css"/>
		
</head>
<body>
<!--top开始-->
<%
	ArrayList<String[]> studentdata=(ArrayList<String[]>)request.getSession().getAttribute("astudentinfo");
	ArrayList<String[]> teacherdata=(ArrayList<String[]>)request.getSession().getAttribute("ateacherinfo");
	ArrayList<String[]> thesistitledata=(ArrayList<String[]>)request.getSession().getAttribute("athesistitleinfo");


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
				
				<a href="teacherpage.action?abc">进入教师模式</a>
			
			</div>
		</div>
		<!--top结束-->
		<!--内容开始-->
		<div class="content">
			
			<div class="content-index">
				<div class="content-index-title">
					<p>管理员操作界面</p>
				</div>
				<div class="color"></div>
				
				<ul class="index">
					
					
					<li>
						<div class="arrow">
							
						</div>
						<form action="teacheradpage.action?downthesis=yes" method="post">
							
							<input type="submit" value="批量导出论文信息"/>
						</form>
					</li>
						<li>
						<div class="arrow">
							
						</div>
						<form action="teacheradpage.action?sendtoallteacher=yes" method="post">
							
							<input type="submit" value="向老师群发邮件"/>
						</form>
					</li>
					<li>
						<div class="arrow">
							
						</div>
						<form action="teacheradpage.action?sendtoallstudent=yes" method="post">
							
							<input type="submit" value="向学生群发邮件"/>
						</form>
					</li>
					
					<li>
						<div class="arrow">
							
						</div>
						<form action="teacheradpage.action?downthesisplan=yes" method="post">
							
							<input class="assignment" type="submit" value="下载毕业论文任务书"/>
						</form>
					</li>
					<li>
						<div class="arrow">

						</div>
						<form action="teacheradpage.action?downloaddirectplan=yes" method="post">

							<input class="assignment" type="submit" value="下载指导记录" />
						</form>
					</li>
				</ul>
			</div>
			<div class="content-detail">
				<div class="content-detail-pic">
					<img src="img/library.jpg" width="760px"/>
				</div>
				<ul class="detail">
					<li>
						<p>学生信息</p>
						<div class="infro">
							<ul>
								<%
							for(String[] data:studentdata){
								out.println("<li>"+"<a href=astudentpersonpage.action?studentid="+data[1]+">"+data[0]+"</a>"+"</li>");

							}
							
							
							%>
								
							</ul>
						</div>
					</li>
					<li>
						<p>老师信息</p>
						<div class="infro">
							<ul>
							<%
								for(String[] data:teacherdata){
								out.println("<li>"+"<a href=ateacherpersonpage.action?teacherid="+data[1]+">"+data[0]+"</a>"+"</li>");

							}	%>
							</ul>
						</div>
					</li>
					<li>
						<p>论文题目</p>
						<div class="infro">
						<ul>
						<%
								for(String[] a:thesistitledata){
									out.println("<li> 论文题目："+a[0]+"指导老师："+a[1]+"发布时间："+a[2]+"</li><br/>");
								}
						%>
						</div>
					</li>
				</ul>
			</div>
		
		</div>
		
		
		<!--内容结束-->
</body>
</html>