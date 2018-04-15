<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,teacherdata.*,thesisdata.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>论文详情页</title>
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="js/detailPage-js.js"></script>
		<link rel="stylesheet" type="text/css" href="css/all.css" />
		<link rel="stylesheet" type="text/css" href="css/detailPage-css.css" />
</head>
<body>
<%
teacherdata datademo=(teacherdata)request.getSession().getAttribute("teacherinfo");
thesisdata data=(thesisdata)request.getSession().getAttribute("thesisinfo");
String thesisid=String.valueOf(data.getThesisid());

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
				<p>欢迎
					<a href="#"><%=request.getSession().getAttribute("teachername") %></a> 老师!</p>
			</div>
		</div>
		<!--top结束-->
		<!--内容开始-->
		<div class="content">
			
			<div class="content-index">
				<div class="content-index-title">
					<p>论文详情</p>
				</div>
				<div class="color"></div>
				
				<ul class="index">
					<li>
						<div class="arrow">
							
						</div>
						<a href="#">指导老师</a>
					</li>
					<li>
						<div class="arrow">
							
						</div>
						<a href="#">论文题目</a>
					</li>
					<li>
						<div class="arrow">
							
						</div>
						<a href="#">论文简介</a>
					</li>
				</ul>
			</div>
			<div class="content-detail">
				<div class="content-detail-pic">
					<img src="img/library.jpg" width="760px"/>
				</div>
				<ul class="detail">
					<li>
						<p>指导老师</p>
						<span id="teacher">
							<ul>
								<li>姓名：<%=data.getThesisteacher() %></li>
								<li>职称：<%=datademo.getTeachertitle() %></li>
								<li>电话：<%=datademo.getTeachertel() %></li>
								<li>QQ：<%=datademo.getTeacherqq() %></li>
								<li>邮箱：<%=datademo.getTeacheremail() %></li>
								
							</ul>
						</span>
					</li>
					<li>
						<p>论文题目</p>
						<span id="topic"><%=data.getThesistitle() %></span>
					</li>
					<li>
						<p>论文简介</p>
						<span id="thesis">
							<%=data.getThesisbrief() %>
							
							
						</span>
					</li>
				</ul>
			</div>
		<!--确定选择按钮开始-->
		<!--确定选择按钮结束-->
		</div>
		
		
		<!--内容结束-->
	</body>
</body>
</html>