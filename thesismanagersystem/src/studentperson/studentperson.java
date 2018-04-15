package studentperson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;

import studentdata.studentdata;
import studentdata.studentdataDao;
import teacherdata.teacherdata;

public class studentperson implements Action, ServletRequestAware, ServletResponseAware {
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
		if(req.getParameter("update")!=null) {
			if(req.getParameter("update").equals("yes")) {
				if(req.getParameter("updatespassword")!=null&&req.getParameter("updatesqq")!=null&&req.getParameter("updatestel")!=null&&req.getParameter("updatesemail")!=null) {
					sdao.update(req.getParameter("updatespassword"), req.getParameter("updatesqq"), req.getParameter("updatestel"), req.getParameter("updatesemail"),(String)req.getSession().getAttribute("studentusername"));
					return "complete";
					
				}
			}
			
		}
		
			String studentid=(String) req.getSession().getAttribute("studentusername");
			studentdata data=sdao.querystudent(studentid);
			req.getSession().setAttribute("studentdatas", data);
			
		
		return SUCCESS;
	}

}
