package ateacherperson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;

import teacherdata.teacherdata;
import teacherdata.teacherdataDao;

public class ateacherperson implements Action, ServletRequestAware, ServletResponseAware {
	private HttpServletResponse res;
	private HttpServletRequest req;
	teacherdataDao tdao=new teacherdataDao();
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
		if(req.getParameter("teacherid")!=null) {
		if(req.getParameter("update")!=null) {
			if(req.getParameter("update").equals("yes")) {
				if(req.getParameter("updatetpassword")!=null&&req.getParameter("updatetqq")!=null&&req.getParameter("updatettel")!=null&&req.getParameter("updatetemail")!=null&&req.getParameter("teacherid")!=null&&req.getParameter("updatetname")!=null&&req.getParameter("updatettitle")!=null&&req.getParameter("updatetmajor")!=null) {
					tdao.teacherupdate(req.getParameter("updatetpassword"), req.getParameter("updatettel"), req.getParameter("updatetqq"), req.getParameter("updatetemail"),req.getParameter("teacherid"),req.getParameter("updatetname"),req.getParameter("updatettitle"),req.getParameter("updatetmajor"));
					return "complete";
				}
			}
			
		}
		}
		if(req.getParameter("teacherid")!=null) {
			if(req.getParameter("deleteteacher")!=null) {
				if(req.getParameter("deleteteacher").equals("yes")) {
					
					tdao.deleteteacher(req.getParameter("teacherid"));
						
						return "complete";
					
				}
				
			}
			}
		if(req.getParameter("teacherid")!=null) {
			teacherdata data=tdao.queryteacher(req.getParameter("teacherid"));
			req.getSession().setAttribute("tt", data);
			
		}
		if(req.getParameter("upteacher")!=null) {
			if(req.getParameter("upteacher").equals("yes")) {
			tdao.upteacher(req.getParameter("teacherid"));
			}
			return "complete";
			
		}
		return SUCCESS;
	}

}
