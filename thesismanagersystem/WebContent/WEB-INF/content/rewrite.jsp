<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="thesisdata.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>录入信息页面</title>
		<script type="text/javascript" src="js/jquery-1.4.2.js">
		</script>
		<script type="text/javascript" src="js/logging-js.js">
		</script>
		<link rel="stylesheet" type="text/css" href="css/all.css" />
		<link rel="stylesheet" type="text/css" href="css/logging.css" />
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
					<a href="#"><%=request.getSession().getAttribute("teachername") %></a>老师!</p>
			</div>
		</div>
		<!--top结束-->
		<!--内容开始-->
		<div class="content">
<%
	String title=(String)request.getSession().getAttribute("thesisreinfo");
	String brief=(String)request.getSession().getAttribute("thesisrebri");
%>
			<div class="content-index">
				<div class="content-index-title">
					<p>论文录入</p>
				</div>
				<div class="color"></div>

				<ul class="index">

					<li>
						<div class="arrow">

						</div>
						<p>论文题目</p>
					</li>
					<li>
						<div class="arrow">

						</div>
						<p>论文简介</p>
					</li>
				</ul>
			</div>
			<div class="content-detail">
				<div class="content-detail-pic">
					<img src="img/library.jpg" width="760px" />
				</div>
				<ul class="detail">

					<li>
						<p>论文题目</p>
						<form class="formA" action="teacherpage.action?checksame1=yes" method="post">

							<input class="text-title" type="text" name="thesischeckintitle" value=<%=title %> />
							<input class="button-title" type="submit" name="" id="" value="查重" />
							<span class="feedback">
							<%=request.getSession().getAttribute("sametitle") == null?"  ":request.getSession().getAttribute("sametitle")  %>
							<%=request.getSession().getAttribute("checkerrorinfo") %>	
							
						
							</span>
						</form>
				
					</li>
					<li>
						<p>论文简介</p>
						
					</li>
				</ul>
			</div>
			<!--确定选择按钮开始-->
			<form class="sure" action="teacherpage.action?rewritein=yes" method="post">

				
				<textarea  class="brief" rows="15" cols="105" name="thesisbrief"><%=brief %></textarea>

				<input class="isTrue" type="submit" value="确定选择该论题" />
			</form>
			<!--确定选择按钮结束-->
		</div>

</body>
</html>