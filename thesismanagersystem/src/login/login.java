package login;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import teacherdata.teacherdata;
import teacherdata.teacherdataDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;

import administratordata.administratordata;
import administratordata.administratordataDao;
import cn.dsna.util.images.ValidateCode;
import startdata.startdataDao;
import studentdata.studentdata;
import studentdata.studentdataDao;
import thesisdata.thesisdata;
import thesisdata.thesisdataDao;
public class login implements Action, ServletRequestAware, ServletResponseAware {
	private String username;
	private String password;
	private String code;
	private HttpServletRequest req;
	private HttpServletResponse res;
	public String a;
	public String b;
	public boolean flag=false;
	public boolean flag1=false;
	public boolean flag2=false;
	public boolean flag3=false;
	public boolean flag4=false;
	public boolean flag5=false;
	public boolean flag6=false;
	studentdataDao querydemo=new studentdataDao();
	studentdata datademo=new studentdata();
	teacherdata tdatademo=new teacherdata();
	teacherdataDao tquerydemo=new teacherdataDao();
	administratordataDao administratordatadao=new administratordataDao();
	startdataDao ssdao=new startdataDao();
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.res=arg0;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.req=arg0;
	}

	@Override
	public String execute() throws Exception {
	
		String aa=req.getParameter("username");
		datademo=querydemo.querystudent(aa);
		tdatademo=tquerydemo.queryteacher(aa);
		administratordata administrator=administratordatadao.querypassword(aa);
		String start=ssdao.querystart(1);
		//System.out.println((String)req.getSession().getAttribute("username"));
		//System.out.println(datademo.getStudentname());
		//System.out.println(datademo.getStudentpassword());
		//code.write(new FileOutputStream("G:/a.jpg"));
		a=" ";
		b=" ";
		//System.out.println(req.getParameter("checker"));
		// TODO Auto-generated method stub
		setUsername(aa);
		setPassword(req.getParameter("password"));
		setCode(req.getParameter("checker"));
		//System.out.println(getCode());
		//System.out.println((String)req.getSession().getAttribute("code"));
		//System.out.println(administrator.getAdministratorid());
		if(datademo==null) {
			req.getSession().setAttribute("suggest", "没有该用户存在");
			flag2=true;
			
			
		}
		if(tdatademo==null) {
			req.getSession().setAttribute("suggest", "没有该用户存在");
			flag3=true;
			
			
		}
		if(administrator==null) {
			req.getSession().setAttribute("suggest", "没有该用户存在");
			flag5=true;
			
			
		}
		
		if(flag3&&flag2&&flag5) {
			return ERROR;
			
		}
		if(!flag2) {
		if(getUsername().equals(datademo.getStudentid())&&getPassword().equals(datademo.getStudentpassword())){
			
			flag=true;
			
		}else {
			
			 a="用户名或密码错误";
			
		}}
		
		if(!flag5) {
			if(getUsername().equals(administrator.getAdministratorid())&&getPassword().equals(administrator.getAdministratorpassword())){
				
				
				flag6=true;
				
			}else {
				
				 a="用户名或密码错误";
				
			}
			
			
		}
		if(!flag3) {
			if(getUsername().equals(tdatademo.getSalaryid())&&getPassword().equals(tdatademo.getTeacherpassword())){
				
				flag4=true;
				
			}else {
				
				 a="用户名或密码错误";
				
			}
			
			
		}
		
		
		
		if(getCode().equals((String)req.getSession().getAttribute("code"))||getCode().toUpperCase().equals(req.getSession().getAttribute("code"))) {
			
			flag1=true;
		}else {
			
			 b="验证码错误";
		}
		
		if(start.equals("yes")) {
		if(flag&&flag1) {
			
			req.getSession().setAttribute("studentusername", getUsername());
			req.getSession().setAttribute("studentname", datademo.getStudentname());
			//System.out.println(list);
			return "student";
			
		}
		}else {
			req.getSession().setAttribute("suggest", "未到选题时间");
			
		}
		if(flag4&&flag1) {
			req.getSession().setAttribute("teacherusername", getUsername());
			req.getSession().setAttribute("teachername", tdatademo.getTeachername());
			req.getSession().setAttribute("teachermajor", tdatademo.getMajor());
			return "teacher";
			
		}
		
		if(flag6&&flag1) {
			req.getSession().setAttribute("administratorusername", getUsername());
			
			return "administrator";
			
		}
		req.getSession().setAttribute("suggest", a+b);
		return ERROR;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
