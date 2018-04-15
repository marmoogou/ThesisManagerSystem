
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/util.js"></script>
		<script type="text/javascript">
			window.onload=function(){
					document.getElementById("justcheck").onblur=function(){
						
						alert("abc");
						if(this.value==""){
							alert("请输入题目");
							this.focus();//恢复焦点
							return;
						}
						var xhr = getXHR();
						
						xhr.onreadystatechange=function(){
							if(xhr.readyState==4){
								if(xhr.status==200){
									
									document.getElementById("msg").innerHTML=xhr.responseText;
									
									
								}
							}
						}
						/*
						xhr.open("GET","${pageContext.request.contextPath}/servlet/ServletDemo2?username="+this.value+"&time="+new Date().getTime());
						xhr.send(null);
						*/
						
						xhr.open("POST","teacherpage.action?checksame=yes&time="+new Date().getTime());
						//设置请求消息头：告知客户端提交的正文的MIME类型
						xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
						xhr.send("thesischeckintitle="+this.value);
						
						
						
					}
				
				
			}
		
		
		
		</script>
		<script type="text/javascript">
			function b() {
					var issure=<%=request.getAttribute("issuresure")%>;
					alert(issure);
					if(issure){
						
						document.getElementById("sure").removeAttribute("disabled").removeClass("elsecss");
						
					}else{
						
						document.getElementById("sure").attr('disabled','true').addClass("elsecss");
					}
					
					
				
				
			}
		
		
		</script>
		<script type="text/javascript">
			
		
		</script>
		
</head>
<body onload="b()">
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
						<p>开题报告</p>
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
						<form class="formA" action="teacherpage.action?checksame=yes" method="post">

							<input class="text-title" type="text" id="justcheck" name="thesischeckintitle" value=<%=request.getSession().getAttribute("anstitle")==null?"请输入题目:":(String)request.getSession().getAttribute("anstitle") %> />
						<input class="button-title" type="submit" name="" id="" value="查重" />
							<span class="feedback">
							<%=request.getSession().getAttribute("sametitle") == null?"  ":request.getSession().getAttribute("sametitle")  %>
							<%=request.getSession().getAttribute("checkerrorinfo") == null?"  ":request.getSession().getAttribute("checkerrorinfo") %>	
							<%
							request.getSession().setAttribute("sametitle", "  ");
							request.getSession().setAttribute("checkerrorinfo", "  ");
							request.getSession().setAttribute("anstitle", "  ");
							
							
							
							
							%>
							</span>
						</form>
				
					</li>
					<li>
						<p>论文简介</p>
						
					</li>
				</ul>
			</div>
			<!--确定选择按钮开始-->
			<form class="sure" action="teacherpage.action?savethesis=yes" method="post">

				
				<textarea  class="brief" rows="15" cols="105" name="thesisbrief">"请输入论文简介"</textarea>

				<input class="isTrue elsecss " id="sure" type="submit" value="确定选择该论题"  disabled="disabled" />
			</form>
			<!--确定选择按钮结束-->
			<span id="js-span">
				
			</span>
		</div>
<%request.setAttribute("issuresure", false); %>
</body>
</html>