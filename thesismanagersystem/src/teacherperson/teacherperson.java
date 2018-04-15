package teacherperson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;

import teacherdata.teacherdata;
import teacherdata.teacherdataDao;

public class teacherperson implements Action, ServletRequestAware, ServletResponseAware {
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
		if(req.getParameter("update")!=null) {
			if(req.getParameter("update").equals("yes")) {
				if(req.getParameter("updatetpassword")!=null&&req.getParameter("updatetqq")!=null&&req.getParameter("updatettel")!=null&&req.getParameter("updatetemail")!=null) {
					tdao.teacherupdate(req.getParameter("updatetpassword"), req.getParameter("updatettel"), req.getParameter("updatetqq"), req.getParameter("updatetemail"),(String)req.getSession().getAttribute("teacherusername"));
					
					return "complete";
				}
			}
			
		}
		
			String salaryid=(String) req.getSession().getAttribute("teacherusername");
			teacherdata data=tdao.queryteacher(salaryid);
			req.getSession().setAttribute("teacherdatas", data);
			
		
		return SUCCESS;
	}

}
