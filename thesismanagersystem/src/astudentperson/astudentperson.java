package astudentperson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;

import studentdata.studentdata;
import studentdata.studentdataDao;
import teacherdata.teacherdata;
import teacherdata.teacherdataDao;

public class astudentperson implements Action, ServletRequestAware, ServletResponseAware {

	private HttpServletResponse res;
	private HttpServletRequest req;
	studentdataDao sdao=new studentdataDao();
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
		if(req.getParameter("studentid")!=null) {
		if(req.getParameter("update")!=null) {
			if(req.getParameter("update").equals("yes")) {
				if(req.getParameter("updatespassword")!=null&&req.getParameter("updatesqq")!=null&&req.getParameter("updatestel")!=null&&req.getParameter("updatesemail")!=null&&req.getParameter("studentid")!=null&&req.getParameter("updatesname")!=null&&req.getParameter("updatesmajor")!=null&&req.getParameter("updatesgrade")!=null&&req.getParameter("updatessex")!=null) {
					sdao.update(req.getParameter("updatespassword"), req.getParameter("updatestel"), req.getParameter("updatesqq"), req.getParameter("updatesemail"),req.getParameter("studentid"),req.getParameter("updatesname"),req.getParameter("updatesmajor"),req.getParameter("updatesgrade"),req.getParameter("updatessex"));
					return "complete";
				}
			}
			
		}
		}
		if(req.getParameter("studentid")!=null) {
			if(req.getParameter("deletestudent")!=null) {
				if(req.getParameter("deletestudent").equals("yes")) {
					
					sdao.deletestudent(req.getParameter("studentid"));
						
						return "complete";
					
				}
				
			}
			}
		if(req.getParameter("studentid")!=null) {
			studentdata data=sdao.querystudent(req.getParameter("studentid"));
			req.getSession().setAttribute("ss", data);
			
		}
		return SUCCESS;
	}


}
