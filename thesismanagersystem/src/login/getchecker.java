package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;

import cn.dsna.util.images.ValidateCode;

public class getchecker implements Action, ServletRequestAware, ServletResponseAware {
	private HttpServletResponse res;
	private HttpServletRequest req;
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
		ValidateCode code=new ValidateCode(120,30,4,100);
		req.getSession().setAttribute("code", code.getCode());
		res.setContentType("image/jpeg");
		code.write(res.getOutputStream());
		req.getParameter("abc");
		
		//System.out.println(code.getCode());
		return null;
	}

}
