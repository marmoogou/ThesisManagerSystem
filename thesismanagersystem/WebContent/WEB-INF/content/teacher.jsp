<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="thesisdata.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用毕业论文管理系统</title>
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<link rel="stylesheet" type="text/css" href="css/all.css"/>
		<link rel="stylesheet" type="text/css" href="css/teacher.css"/>
		<script type="text/javascript" src="js/teacher-js.js"></script>
		
</head>
<body>
<%
ArrayList<thesisdata> data=new ArrayList<thesisdata>();
if(request.getSession().getAttribute("teacherthesisdata")!=null){
 data=(ArrayList<thesisdata>)request.getSession().getAttribute("teacherthesisdata");
}else{
	 data=null;
}
HashMap<Integer,String> querythesis=new HashMap<Integer,String>();
ArrayList<Integer> querytitle =new ArrayList<Integer>();
ArrayList<thesisdata> chosedtitle =new ArrayList<thesisdata>();
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
					<a href="teacherpersonpage.action"><%=(String)request.getSession().getAttribute("teachername") %></a> 老师!</p>
			</div>
			<div class="top-administor">
				<a href="teacherpage.action?ad=yes">进入管理员模式</a>
			</div>
		</div>
		<!--top开始-->
		<div class="content">
			<div class="content-fun">
				<form class="form1" action="teacherpersonpage.action"  method="post">
					<div class="icon1">

					</div>
					<input  class="input1 alone" type="submit" value="个人中心" />
				</form>

				<form action="teacherpage.action?downloadinfo=1" method="post">
					<div class="icon2">

					</div>
					
					<input class="text1" type="text" name="yearall" id="" value="请输入下载年份" />
					<input class="input2" type="submit" value="下载所有论文信息" />
				</form>
				<form action="teacherpage.action?downloadinfo=2" method="post">
					<div class="icon3">

					</div>
					
					<input class="text2" type="text" name="yearall1" id="" value="请输入下载年份" />
					<input class="input3" type="submit" value="下载所有被选择成功的论文" />
				</form>
				
				<form action="teacherpage.action?downloadinfo=3" method="post">
					<div class="icon4">

					</div>
					<input class="input4" type="submit" value="下载今年所有论文信息" />
				</form>
				<form action="" method="post">
					<div class="icon5">

					</div>
					<input class="input5" type="submit" value="下载今年已被选择的论文" />
				</form>
				<form action="teacherpage.action?checkin=yes" method="post">
					<div class="icon6">

					</div>
					<input class="input6" type="submit" value="录入" />
				</form>
					<div class="search">
					<div class="search-input">
						<form class="form2" action="teacherpage.action?search=yes" method="post">
							
							<input class="text3"  type="text" name="searchyear" value="输入查询年份"/>
							<input class="button1" type="submit" name="" id="" value="搜索" />
						</form>
						<div class="show">
							<%	
								if(request.getSession().getAttribute("titless")!=null){
								ArrayList<String> datass =(ArrayList<String>)request.getSession().getAttribute("titless");
								for(String a: datass){
								out.println("论文题目："+a+"<br/>");
							
							}
								}
							%>
						</div>
					</div>
				</div>
				
			</div>
			<div class="content-the">
				<div class="on">
					<h2>今年未被选择的论文:</h2>
					<ul class="on-ul">
					<!--  	<li>
							
							<form action="" method="post">
								
								<input type="submit" value="修改"/>
							
								
							</form>
							<form action="" method="post">
								
								<input type="submit" value="删除"/>
							
								
							</form>
							
						</li>-->
					<%
						if(data!=null){
						for(thesisdata demo:data){
							if(demo.getIschosed().equals("no")){
								out.println("<li>"+"<a href=thesispage.action?isteacher=yes&thesisid="+demo.getThesisid()+">"+demo.getThesistitle()+"</a>"+"<form action=teacherpage.action?rewrite=yes&thesisid="+demo.getThesisid()+" "+"method="+"post"+" "+">"+"	<input type="+"submit"+" "+"value="+"修改"+" "+"/>"+"</form>"+"<form action=teacherpage.action?delete=yes&thesisid="+demo.getThesisid()+" "+"method="+"post"+">"+"	<input type="+"submit"+" "+"value="+"删除"+" "+"/>"+"</form>"+"</li>");
								
							}else
							if(demo.getIschosed().equals("wtq")){
								querytitle.add(demo.getThesisid());
								querythesis.put(demo.getThesisid(), demo.getThesistitle());
								
							}else{
								chosedtitle.add(demo);
								
							}
							
							
						}
					
					
						}
					
					%>
					</ul>
				</div>
				<hr />
				<div class="waiting">
					<h2>等待您审核的学生选择的论文题目:</h2>
					<ul class="waiting-ul">
						<%
							for(int i:querytitle){
								out.println("<li>"+"<a href=thesispage.action?isteacher=yes&thesisid="+i+">"+querythesis.get(i)+"</a>"+"<form action=teacherpage.action?passid=yes&thesisid="+i+" "+"method="+"post"+">"+"	<input type="+"submit"+" "+"value="+"通过"+" "+"/>"+"</form>"+"<form action=teacherpage.action?passid=no&thesisid="+i+" "+"method="+"post"+" "+">"+"	<input type="+"submit"+" "+"value="+"不通过"+" "+"/>"+"</form>"+"<form action=teacherpage.action?checkstudentinfo=yes&thesisid="+i+" "+"method="+"post"+">"+"	<input class=view type="+"submit"+" "+"value="+"查看选题学生信息"+" "+"width=8px"+"/>"+"</form>"+"</li><br/>");
								
								
							}
						
						
						
						%>
						
					</ul>
				</div>
				<hr />
				<div class="off">
					<h2>历年和已被选中的论文题目:</h2>
					<ul class="off-ul">
						<%
							for(thesisdata title:chosedtitle){
								out.println("<li>"+title.getThesistitle()+"  "+"发布时间:"+title.getPublicdate().toString()+"</li>");
								
							}
						
						
						
						%>
						
					</ul>
				</div>
			</div>
		</div>


</body>
</html>