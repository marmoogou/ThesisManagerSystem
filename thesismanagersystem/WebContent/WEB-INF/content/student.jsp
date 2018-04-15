<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>毕业论文系统</title>
		<link rel="stylesheet" type="text/css" href="css/student-css.css"/>
		<link rel="stylesheet" type="text/css" href="css/all.css"/>
		<script type="text/javascript" src="js/student-js.js"></script>
		
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
</head>
<body>
<%
List<Integer> title=(List<Integer>)request.getSession().getAttribute("thesisid");
//HashMap<Integer,String> briefmap=(HashMap<Integer,String>)request.getSession().getAttribute("briefmap");
HashMap<Integer,String> chosedmap=(HashMap<Integer,String>)request.getSession().getAttribute("chosedmap");
//HashMap<Integer,Date> timemap=(HashMap<Integer,Date>)request.getSession().getAttribute("timemap");
HashMap<Integer,String> teachermap=(HashMap<Integer,String>)request.getSession().getAttribute("teachermap");
HashMap<Integer,String> titlemap=(HashMap<Integer,String>)request.getSession().getAttribute("titlemap");
HashMap<String,String> chosedthesis=new HashMap<String,String>();
ArrayList<String> chosedtitle =new ArrayList<String>();
HashMap<String,String> querythesis=new HashMap<String,String>();
ArrayList<String> querytitle =new ArrayList<String>();
	

%>
<div class="top ">
			<div class="top-school pic">
				<img src="img/school.png" width="300px"/>
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
				<p>欢迎 <a href="studentpersonpage.action"><%=request.getSession().getAttribute("studentname") %></a>同学!</p>
			</div>
		</div>
		<!--<div class="header">
			<div class="header-cont">
				<img src="img/library.jpg" width="998px" height="114px"/>
			</div>
		</div>-->
		<div class="content">
			<div class="content-fun">
				<form action="studentpersonpage.action" method="post">
					
					<input class="input1 alone" type="submit" value="个人中心"/>
					<div class="icon1">
						
					</div>
	
				</form>
				
				<form action="studentpage.action?download=1" method="post">
					<div class="icon2">
						
					</div>
					 <input class="input2" type="submit" value="毕业论文任务书"/>
					
				</form>
				<form action="studentpage.action?download=2"  method="post">
					<div class="icon3">
						
					</div>
					<input class="input3" type="submit" value="毕业实习鉴定表"/>
				</form>
				<form action="studentpage.action?download=3"  method="post">
					<div class="icon5">
						
					</div>
					<input class="input5" type="submit" value="论文格式模板"/>
				</form>
				<form action="studentpage.action?download=4"  method="post">
					<div class="icon6">
						
					</div>
					<input class="input6" type="submit" value="毕业实习报告与毕业实习评价表模板"/>
				</form>
				<form action="studentupload?studentupload=yes" enctype="multipart/form-data"  method="post">
					<div class="icon9">

					</div>
					
					
					<input type="file"  id="stu-myfile" value="" name="uploadFile"/>
					<input class="input9" type="submit" value="上传毕业论文任务书" />
					<div class="cover">
						
						<p>选择文件</p>
					
				
					</div>
				</form>
				<form action="studentuploaddirectplan?studentuploadre=yes" enctype="multipart/form-data" method="post">
					<div class="icon8">

					</div>
				
					<input type="file"  id="stu-myfile2" value="" name="uploadFile" />
					<input class="input8" type="submit" value="上传指导记录" />
					<div class="cover2">
						<p>选择文件</p>
					</div>
				</form>
				<form action="studentpage.action?download=5" method="post">
					<div class="icon7">

					</div>
					<input class="input7" type="submit" value="下载指导记录" />
				</form>
				<form action="thesispage.action?query=111" method="post">
					<div class="icon4">
						
					</div>
					<input class="input4" type="submit" value="查询选题结果"/>
				</form>
			
				
		
				<div class="result">
					<font color="red"><%=(request.getSession().getAttribute("errorinfo")==null)?" ": request.getSession().getAttribute("errorinfo")%></font>
				</div>	
				</div>
			<%request.getSession().setAttribute("errorinf", " "); %>
			<div class="content-the">
				<div class="on-ul">
				<h2>可选择的论文题目：</h2>
					<ul>
					<%
					for(int a:title){
						//String brief=briefmap.get(a);
						String chosed=chosedmap.get(a);
						//Date publictime=timemap.get(a);
						//String time=publictime.toString();
						String teachername=teachermap.get(a);
						String thesistitle=titlemap.get(a);
						//out.println(a+" "+thesistitle+"  "+brief+" "+" "+chosed+" "+time+"  "+teachername+"  "+"<br/>");
						//String teachername=teachermap.get(a);
						//out.println(teachername);
						if(chosed.equals("no")){
							out.println("<li>"+"<a href=thesispage.action?thesisid="+a+">"+thesistitle+"  "+"指导老师："+teachername+"</a>"+"<br/>");
						}else if(chosed.equals("wtq")){
							querytitle.add(thesistitle);
							querythesis.put(thesistitle, teachername);
							
						}else{
							chosedtitle.add(thesistitle);
							chosedthesis.put(thesistitle, teachername);
						}
							
						
					}
					
					
					%>
					
					
					</ul>
				</div>
				<hr />
				<div class="waiting">
				<h2>等待老师通过的论文题目：</h2>
				<ul class="waiting-ul">
				<%
				for(String c:querytitle){
					out.println("<li>"+c+"  "+"指导老师："+querythesis.get(c));}
				%>
				
				
				</ul>
				
				
				</div>
				<hr/>
				<div class="off">
				<h2>已被选择的论文题目：</h2>
					<ul class="off-ul">
						<%
						for(String b:chosedtitle){
							out.println("<li>"+b+"  "+"指导老师："+chosedthesis.get(b));
							
							
							}
						%>
					
					
					</ul>
				</div>
			</div>
		</div>


</body>
</html>