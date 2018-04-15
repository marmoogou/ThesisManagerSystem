package thesisfunction;

import java.text.SimpleDateFormat;
import studentdata.*;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import teacherdata.*;
import javax.servlet.http.HttpServletResponse;
import mailfunction.*;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;
import thesisdata.*;
import optthesisdata.*;
public class thesispagefunction implements Action, ServletRequestAware, ServletResponseAware {
	private HttpServletResponse res;
	private HttpServletRequest req;
	thesisdata thesisdatademo=new thesisdata();
	thesisdataDao thesisdataDaodemo=new thesisdataDao();
	optthesisdata optthesisdatademo=new optthesisdata();
	optthesisdataDao optthesisdataDaodemo=new optthesisdataDao();
	teacherdata datademo=new teacherdata();
	teacherdataDao Daodemo=new teacherdataDao();
	thesisdata thesisdatademo1=new thesisdata();
	thesisdataDao thesisdataDaodemo1=new thesisdataDao();
	studentdata student=new studentdata();
	studentdataDao studentdao=new studentdataDao();
	public int thesisid;
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
		// TODO Auto-generated method stub
		if(req.getSession().getAttribute("teacherusername")==null&&req.getSession().getAttribute("studentusername")==null) {
			return "fof";
		}
		
		String the=req.getParameter("thesisid");
		if(the!=null) {
		thesisid=Integer.valueOf(the);
		thesisdatademo=thesisdataDaodemo.queryinside(thesisid);
		datademo=Daodemo.queryteacher(thesisdatademo.getSalaryid());
		}
		
		if(req.getParameter("isteacher")!=null) {
			if(!(req.getParameter("isteacher").equals("yes")))
			student=studentdao.querystudent((String)req.getSession().getAttribute("studentusername"));
		}else {
		student=studentdao.querystudent((String)req.getSession().getAttribute("studentusername"));}
		String studentid=(String) req.getSession().getAttribute("studentusername");
		if(req.getParameter("issure")!=null&&thesisdatademo!=null&&datademo!=null) {
		if(req.getParameter("issure").equals("yes")&&(optthesisdataDaodemo.ishasstu(thesisid))&&optthesisdataDaodemo.inopt(studentid)) {
			Date d=new Date();
			java.sql.Date chosedtime=new java.sql.Date(d.getTime());
			String teacher =thesisdatademo.getThesisteacher();
			String topic=thesisdatademo.getThesistitle();
			//int thesisidforresult=optthesisdataDaodemo.querythesisid(studentid);
			thesisdataDaodemo.setchosed(thesisid, "wtq");
			optthesisdataDaodemo.savethesisdata(studentid, teacher, thesisid, topic, chosedtime);
			req.getSession().setAttribute("errorinfo", "选题成功,等待老师通过");
			//req.getRequestDispatcher("/studentpage").forward(req, res);
			mailinfo info=new mailinfo();
			info.setstudentinfo(student, thesisdatademo, datademo);
			mailfunction sendmail=new mailfunction(info);
			sendmail.start();
			
			return ERROR;
		}else {
			req.getSession().setAttribute("errorinfo", "选题失败,请换其他题目");
			//req.getRequestDispatcher("studentpage").forward(req, res);
			return ERROR;
		}
		}
		if(req.getParameter("query")!=null&&req.getParameter("query").equals("111")) {
				
					int thesisidforresult=optthesisdataDaodemo.querythesisid(studentid);
					if(thesisidforresult!=0) {
					thesisdata dataforresult=thesisdataDaodemo.queryinside(thesisidforresult);
					teacherdata datafor=Daodemo.queryteacher(dataforresult.getSalaryid());
					req.getSession().setAttribute("thesisdataforresult", dataforresult);
					req.getSession().setAttribute("teacherdataforresult", datafor);
					req.getRequestDispatcher("/WEB-INF/content/queryresult.jsp").forward(req, res);
					}else if(optthesisdataDaodemo.idforchecked(studentid)){
						req.getSession().setAttribute("errorinfo", "选题失败,请换其他题目");
						return ERROR;
					}else {
						req.getSession().setAttribute("errorinfo", "等待老师确认中");
						return ERROR;
					}
					
				
		}
		//System.out.println("i am here");
		
		if(!(optthesisdataDaodemo.ishasstu(thesisid))) {
			req.getSession().setAttribute("errorinfo", "你已经选过了，不能再选啦");
			
			return ERROR;
		}
		if(!(optthesisdataDaodemo.inopt(studentid))) {
			req.getSession().setAttribute("errorinfo", "你已经选过了，不能再选啦");
			
			return ERROR;
		}
		if(!(optthesisdataDaodemo.tthesisid(studentid))) {
			req.getSession().setAttribute("errorinfo", "来晚一步已经被选了，不能再选");
			return ERROR;
		}
		if(thesisdatademo!=null&&datademo!=null) {
		if(!(thesisdatademo.getIschosed().equals("no"))){
			req.getSession().setAttribute("errorinfo", "来晚一步已经被选了，不能再选");
			
			return ERROR;
		}
		}
		
		
		req.getSession().setAttribute("teacherinfo", datademo);
		req.getSession().setAttribute("thesisinfo", thesisdatademo);
		if(req.getParameter("isteacher")!=null) {
			if(req.getParameter("isteacher").equals("yes"))
			return "teacherpage";
		}
		return SUCCESS;
		
		
		
		
	}

}
