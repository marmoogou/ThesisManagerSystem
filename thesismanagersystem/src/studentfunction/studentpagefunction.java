package studentfunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;

import studentdata.studentdataDao;
import thesisdata.thesisdata;
import thesisdata.thesisdataDao;

public class studentpagefunction implements Action, ServletRequestAware, ServletResponseAware {
	private HttpServletRequest req;
	private HttpServletResponse res;
	thesisdata thesisdatademo=new thesisdata();
	thesisdataDao thesisdataquerydemo=new thesisdataDao();
	
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
		if(req.getSession().getAttribute("studentusername")==null) {
			return "fof";
		}
		String downloads=null;
		downloads=req.getParameter("download");
		
		if(downloads!=null) {
			
		if(downloads.equals("1")) {
			downloadthesisplan();
			//return SUCCESS;
		}
		if(downloads.equals("2")) {
			downloadexplan();
		}
		if(downloads.equals("3")) {
			downloadthesisdemo();
		}
		if(downloads.equals("4")) {
			downloadggsreporter();
		}
		if(downloads.equals("5")) {
			downloaddirectplan();
		}
		}

		ArrayList<thesisdata> anslist=thesisdataquerydemo.thesisdataquery();
		if(anslist==null) {
			return ERROR;
		}
		ArrayList<Integer> idlist=new ArrayList<Integer>();
		
		HashMap<Integer,String> briefmap=new HashMap<Integer,String>();
		HashMap<Integer,String> chosedmap=new HashMap<Integer,String>();
		HashMap<Integer,Date> timemap=new HashMap<Integer,Date>();
		HashMap<Integer,String> teachermap=new HashMap<Integer,String>();
		HashMap<Integer,String> titlemap=new HashMap<Integer,String>();
		for(thesisdata data:anslist) {
			int thesisid=data.getThesisid();
			idlist.add(thesisid);
			briefmap.put(thesisid, data.getThesisbrief());
			chosedmap.put(thesisid, data.getIschosed());
			timemap.put(thesisid, data.getPublicdate());
			teachermap.put(thesisid, data.getThesisteacher());
			titlemap.put(thesisid, data.getThesistitle());
			
		}
		req.getSession().setAttribute("thesisid", idlist);
		req.getSession().setAttribute("briefmap", briefmap);
		req.getSession().setAttribute("chosedmap",chosedmap);
		req.getSession().setAttribute("timemap",timemap);
		req.getSession().setAttribute("teachermap",teachermap);
		req.getSession().setAttribute("titlemap",titlemap);
		//Cookie c=new Cookie("briefmap",briefmap);
		
		return SUCCESS;
	}
	public void downloadthesisplan() throws IOException{
		File f=new File(req.getRealPath("/毕业设计任务书模板.doc"));
		//System.out.println(req.getRealPath("/毕业设计任务书模板.doc"));
		if(f.exists()) {
			
			FileInputStream fis=new FileInputStream(f);
			String filename=URLEncoder.encode(f.getName(), "UTF-8");
			byte[] b=new byte[fis.available()];
			fis.read(b);
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Content-Disposition","attachment; filename="+filename+"");
			ServletOutputStream out=res.getOutputStream();
			out.write(b);
			out.flush();
			out.close();
			
			
		}
		
	}
	public void downloadexplan() throws IOException{
		File f=new File(req.getRealPath("/毕业实习鉴定表格式样本.doc"));
		if(f.exists()) {
			
			FileInputStream fis=new FileInputStream(f);
			String filename=URLEncoder.encode(f.getName(), "UTF-8");
			byte[] b=new byte[fis.available()];
			fis.read(b);
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Content-Disposition","attachment; filename="+filename+"");
			ServletOutputStream out=res.getOutputStream();
			out.write(b);
			out.flush();
			out.close();
			
			
		}
		
	}
	public void downloadthesisdemo() throws IOException{
		File f=new File(req.getRealPath("/毕业论文模板.doc"));
		if(f.exists()) {
			
			FileInputStream fis=new FileInputStream(f);
			String filename=URLEncoder.encode(f.getName(), "UTF-8");
			byte[] b=new byte[fis.available()];
			fis.read(b);
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Content-Disposition","attachment; filename="+filename+"");
			ServletOutputStream out=res.getOutputStream();
			out.write(b);
			out.flush();
			out.close();
			
			
		}
		
	}
	public void downloadggsreporter() throws IOException{
		File f=new File(req.getRealPath("/毕业实习报告与毕业实习评价表（模板）.doc"));
		if(f.exists()) {
			
			FileInputStream fis=new FileInputStream(f);
			String filename=URLEncoder.encode(f.getName(), "UTF-8");
			byte[] b=new byte[fis.available()];
			fis.read(b);
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Content-Disposition","attachment; filename="+filename+"");
			ServletOutputStream out=res.getOutputStream();
			out.write(b);
			out.flush();
			out.close();
			
			
		}
		
	}
	public void downloaddirectplan() throws IOException{
		File f=new File(req.getRealPath("/指导记录.doc"));
		if(f.exists()) {
			
			FileInputStream fis=new FileInputStream(f);
			String filename=URLEncoder.encode(f.getName(), "UTF-8");
			byte[] b=new byte[fis.available()];
			fis.read(b);
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Content-Disposition","attachment; filename="+filename+"");
			ServletOutputStream out=res.getOutputStream();
			out.write(b);
			out.flush();
			out.close();
			
			
		}
		
	}



}
