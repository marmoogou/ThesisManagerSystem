<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="thesisdata.*,teacherdata.*,studentdata.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员界面</title>
		<link rel="stylesheet" type="text/css" href="css/all.css" />
		<link rel="stylesheet" type="text/css" href="css/administrator-css.css" charset="UTF-8"/>
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<style type="text/css">  
		.container{  
			margin-top:280px;
			margin-left:230px;
		   width:450px;  
		   border:1px solid #6C9C2C;  
		   height:25px;  
		 }
		#bar{ 
			margin-top:220px;
			margin-left:230px;
		   background:#95CA0D;  
		   float:left; 
		   height:100%; 
		   width:450px;
		   text-align:center;  
		   line-height:150%; 
		 }
		 #total{
		 	margin-top:200px;
			margin-left:200px;
		 
		 }
</style> 
<script type="text/javascript">  
  function run(){  
        var bar = document.getElementById("bar"); 
        var total = document.getElementById("total"); 
    bar.style.width=parseInt(bar.style.width) + 1 + "%";  
    total.innerHTML = bar.style.width; 
    if(bar.style.width == "99%"){  
      window.clearTimeout(timeout); 
      return; 
    }
    if(bar.style.width < "99%"){
    var timeout=window.setTimeout("run()",200); 
    }
  } 
    
    function showprocess(){
    
    	 document.getElementById('bar').style.display="";
    	 document.getElementById('total').style.display="";
    	
    	 run();
    	
    	
    }
    function reload(){
    	window.setTimeout("window.location.reload()",120000); 
    	
    }
</script> 
</head>
<body>
<%
ArrayList<studentdata> student=(ArrayList<studentdata>)request.getSession().getAttribute("studentinfo");
ArrayList<teacherdata> teacher=(ArrayList<teacherdata>)request.getSession().getAttribute("teacherinfo");
ArrayList<String[]> title=(ArrayList<String[]>)request.getSession().getAttribute("titleinfo");


%>
<div class="top">
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
				<a href="${pageContext.request.contextPath}">注销</a>
			</div>
			<div class="top-name">
			
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
					<li class="first-li">
						<div class="arrow">
							
						</div>
						<form action="uploadstudentinfo.action"  enctype="multipart/form-data" method="post" onsubmit="showprocess()">
							
							<input id="myfile" name="uploadFiless" type="file"   />
							<input class="into" type="button" value="批量导入学生信息" />
							<input class="present" type="submit"  value="批量导入信息" />
							<div class="cover">
								<p>选择文件</p>
							</div>
						</form>
				
 	
   				<div id="bar" style="width:0%;style="display:none"></div>  
  				<span id="total" style="display:none"></span>	
						
					</li>
					<li>
						<div class="arrow">
							
						</div>
						<form action="administratorpage.action?downloadall=yes" method="post">
							
							<input type="submit" value="批量导出成功选题信息"/>
						</form>
					</li>
					<li>
						<div class="arrow">
							
						</div>
						<form action="administratorpage.action?createstudent=yes" method="post">
							
							<input type="submit" value="单独填写学生信息"/>
						</form>
					</li>
						<li>
						<div class="arrow">
							
						</div>
						<form action="administratorpage.action?createteacher=yes" method="post">
							
							<input type="submit" value="单独填写老师信息"/>
						</form>
					</li>
					<li>
						<div class="arrow">
							
						</div>
						<form action="administratorpage.action?downloadallthesis=yes" method="post">
							
							<input type="submit" value="批量导出论文信息"/>
						</form>
					</li>
						<li>
						<div class="arrow">
							
						</div>
						<form action="administratorpage.action?sendtoallteacher=yes" method="post">
							
							<input type="submit" value="向老师群发邮件"/>
						</form>
					</li>
					<li>
						<div class="arrow">
							
						</div>
						<form action="administratorpage.action?sendtoallstudent=yes" method="post">
							
							<input type="submit" value="向学生群发邮件"/>
						</form>
					</li>
					<li class="A">
						<div class="arrow">

						</div>
						<form action="uploadthesisinfo.action" enctype="multipart/form-data" method="post">
							<input type="file" id="myefile" value="" name="uploadFiles" />
							<input class="present" type="submit" name="" id="" value="批量提交文件" />

							<input type="botton" value="批量导入论文题目" />
							<div class="cover">
								<p>选择文件</p>
							</div>
						</form>
					</li>
					<li>
						<div class="arrow">

						</div>
						<form action="administratorpage.action?downthesisplan=yes" method="post">

							<input class="assignment" type="submit" value="下载毕业论文任务书" />
						</form>
					</li>
					<li>
						<div class="arrow">

						</div>
						<form action="administratorpage.action?start=yes" method="post">

							<input class="assignment" type="submit" value="开启学生选题" />
						</form>
					</li>
					<li>
						<div class="arrow">

						</div>
						<form action="administratorpage.action?end=yes" method="post">

							<input class="assignment" type="submit" value="关闭学生选题" />
						</form>
					</li>
					<li>
						<div class="arrow">

						</div>
						<form action="administratorpage.action?downreplan=yes" method="post">

							<input class="assignment" type="submit" value="下载指导记录" />
						</form>
					</li>
					<li class="first-li">
						<div class="arrow">
							
						</div>
						<form action="uploadteacherinfo.action"  enctype="multipart/form-data" method="post" onsubmit="showprocess()">
							
							<input id="myfile" name="uploadFile" type="file"   />
							<input class="into" type="button" value="批量导入老师信息" />
							<input class="present" type="submit"  value="批量导入信息" />
							<div class="cover">
								<p>选择文件</p>
							</div>
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
							for(studentdata data:student){
								out.println("<li>"+"<a href=astudentpersonpage.action?studentid="+data.getStudentid()+">"+data.getStudentname()+"</a>"+"</li>");

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
								for(teacherdata data:teacher){
									out.println("<li>"+"<a href=ateacherpersonpage.action?teacherid="+data.getSalaryid()+">"+data.getTeachername()+"</a>"+"</li>");

								}
							
							
							%>
							</ul>
						</div>
					</li>
					<li>
						<p>论文信息</p>
						<div class="infro">
							<ul>
							<%
								for(String[] a:title){
									out.println("<li>论文题目："+a[0]+"指导老师："+a[1]+"发布时间："+a[2]+ "</li><br/>");
								}
							
							
							%>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		
		</div>
		
		
<!--  <form action="upload?upload=yes" enctype="multipart/form-data" method="post">
<input id="myfile" name="uploadFile" type="file"  /><br/>
<input type="submit" value="提交"/>
</form>-->

</body>
</html>