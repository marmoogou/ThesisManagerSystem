<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>群发邮件</title>
		<link rel="stylesheet" type="text/css" href="css/email-css.css"/>
		<link rel="stylesheet" type="text/css" href="css/all.css" />
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
				<p>群发邮件</p>
			</div>
		</div>
		<!--top结束-->

		<!--内容开始-->
		<!--确定选择按钮开始-->
		<div class="choose">
			<form class="sure" action="teacheradpage.action?sendtt=yes" method="post">

				<!--<input class="brief" type="text" name="" id="" value="请输入论文简介" />-->
				<textarea class="brief" name="sendinside" rows="" cols=""></textarea>

				<input class="isTrue" type="submit" value="确定发送" />
			</form>
		</div>
		<!--确定选择按钮结束-->
		<!--内容结束-->

</body>
</html>