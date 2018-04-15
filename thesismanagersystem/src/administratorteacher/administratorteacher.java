package administratorteacher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;

import administrorfunction.TestCreateZip;
import mailfunction.sendmailtoall;
import optthesisdata.optthesisdataDao;
import studentdata.studentdata;
import studentdata.studentdataDao;
import teacherdata.teacherdataDao;
import thesisdata.thesisdata;
import thesisdata.thesisdataDao;

public class administratorteacher implements Action, ServletRequestAware, ServletResponseAware {
	
	private HttpServletResponse res;
	private HttpServletRequest req;
	teacherdataDao tdao=new teacherdataDao();
	studentdataDao sdao=new studentdataDao();
	thesisdataDao ttdao=new thesisdataDao();
	optthesisdataDao optdao=new optthesisdataDao();
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
		if(req.getSession().getAttribute("teacherusername")==null) {
			
			return "fof";
		}
		String teachermajor=(String)req.getSession().getAttribute("teachermajor");
		
		
		if(req.getParameter("sendtoallteacher")!=null) {
			if(req.getParameter("sendtoallteacher").equals("yes")) {
				return "sendtoallteacher";
				
			}
		}
		if(req.getParameter("sendtt")!=null&&req.getParameter("sendinside")!=null) {
			if(req.getParameter("sendtt").equals("yes")) {
				ArrayList<String> data=tdao.teacheremailformajor(teachermajor);
				sendmailtoall toall=new sendmailtoall(data,req.getParameter("sendinside"));
				toall.start();
				return "complete";
				
			}
		}
		if(req.getParameter("sendtoallstudent")!=null) {
			if(req.getParameter("sendtoallstudent").equals("yes")) {
				return "sendtoallstudent";
				
			}
		}
		if(req.getParameter("sendss")!=null&&req.getParameter("sendinside")!=null) {
			if(req.getParameter("sendss").equals("yes")) {
				ArrayList<String> data=sdao.mailofstudentbymajor(teachermajor);
				sendmailtoall toall=new sendmailtoall(data,req.getParameter("sendinside"));
				toall.start();
				return "complete";
			}
		}
		if(req.getParameter("downthesis")!=null) {
			if(req.getParameter("downthesis").equals("yes")) {
				downloadthesis();
				return "complete";
				
			}
		}
		if(req.getParameter("downloaddirectplan")!=null) {
			if(req.getParameter("downloaddirectplan").equals("yes")) {
				downloaddirectplan();
				return "complete";
				
			}
		}
		if(req.getParameter("downthesisplan")!=null) {
			if(req.getParameter("downthesisplan").equals("yes")) {
				downloadthesisplan();
				return "complete";
				
			}
		}
		ArrayList<Object[]> data= tdao.teacheridandname(teachermajor);
		ArrayList<Object[]> data1=sdao.studentidandname(teachermajor);
		ArrayList<Object[]>data2=ttdao.querytitlesandnameanddatebymajor(teachermajor);
		ArrayList<String[]> datas=new ArrayList<String[]>();
		ArrayList<String[]> data1s=new ArrayList<String[]>();
		ArrayList<String[]> data2s=new ArrayList<String[]>();
		for(Object[] a:data) {
			String a1[]=new String[2];
			 a1[0]=(String)a[0];
			 a1[1]=(String)a[1];
			 datas.add(a1);
			
		}
		for(Object[] a:data1) {
			String a1[]=new String[2];
			 a1[0]=(String)a[0];
			 a1[1]=(String)a[1];
			 data1s.add(a1);
			
		}
		for(Object[] a:data2) {
			String a1[]=new String[3];
			 a1[0]=(String)a[0];
			 a1[1]=(String)a[1];
			 a1[2]=a[2].toString();
			 data2s.add(a1);
			
		}
		req.getSession().setAttribute("ateacherinfo", datas);
		req.getSession().setAttribute("astudentinfo", data1s);
		req.getSession().setAttribute("athesistitleinfo", data2s);
		return SUCCESS;
	}
	public void downloadthesis() throws IOException {
		//ArrayList<thesisdata> dataopt=thesisdao.thesisdataquery();
		ArrayList<studentdata> studata=sdao.queryalldata();
		
		
		HSSFWorkbook workbook = new HSSFWorkbook();
	      // 创建工作表
	      HSSFSheet sheet = workbook.createSheet("sheet1");
	      java.util.Date date=new java.util.Date();
	      long times = date.getTime();//时间戳
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	      String dateString = formatter.format(date);
	    // data1=teacherdemo.querybysalaryidanddateandpassed(salaryid,dateString);
	     if(studata!=null&&studata.size()>0) {
	     int row=1;
	      HSSFRow rowss = sheet.createRow(0);
	      for(int i=0;i<9;i++) {
	    	  switch(i) {
	    	  case 0: rowss.createCell(i).setCellValue("学号"); break;
	    	  case 1: rowss.createCell(i).setCellValue("姓名"); break;
	    	  case 2: rowss.createCell(i).setCellValue("性别"); break;
	    	  case 3: rowss.createCell(i).setCellValue("联系电话"); break;	
	    	  case 4: rowss.createCell(i).setCellValue("联系qq"); break;
	    	  case 5: rowss.createCell(i).setCellValue("联系电子邮箱"); break;
	    	  case 6: rowss.createCell(i).setCellValue("题目"); break;
	    	  case 7: rowss.createCell(i).setCellValue("指导教师"); break;
	    	  case 8: rowss.createCell(i).setCellValue("职称"); break;
	    	  }
	    	  
	      }
	      for (studentdata data2:studata)
	      {
	    	
	         HSSFRow rows = sheet.createRow(row);
	      
	         
	        // studentdata studata=studentdao.querystudent(data2.getStudentid());
	         for (int col = 0; col < 9; col++)
	         {	//System.out.println("i am here 1");
	            // 向工作表中添加数据
	        	  switch(col) {
	        	  case 0: rows.createCell(col).setCellValue(data2.getStudentid()); break;
		    	  case 1: rows.createCell(col).setCellValue(data2.getStudentname()); break;
		    	  case 2: rows.createCell(col).setCellValue(data2.getStudentsex()); break;
		    	  case 3: rows.createCell(col).setCellValue(data2.getStudenttel()); break;	
		    	  case 4: rows.createCell(col).setCellValue(data2.getStudentqq()); break;
		    	  case 5: rows.createCell(col).setCellValue(data2.getStudentemail()); break;
		    	  case 6: String titlett=optdao.titleforstuid(data2.getStudentid());if(titlett!=null) { rows.createCell(col).setCellValue(titlett);} break;
		    	  case 7: String namett=optdao.teacherforstuid(data2.getStudentid());if(namett!=null) { rows.createCell(col).setCellValue(namett);} break;
		    	  case 8: String ttt=optdao.teacherforstuid(data2.getStudentid());if(ttt!=null)rows.createCell(col).setCellValue(tdao.namefortitle(ttt)); break;
		    	
		    	  }
	            //rows.createCell(col).setCellValue("data" + row + col);
	         }
	         
	         row++;
	      }
	      String savepath=req.getServletContext().getRealPath("/WEB-INF/documents");
	      File xlsFile = new File(savepath+"//"+"allthesisinfoofteachers.xls");
	      FileOutputStream xlsStream = new FileOutputStream(xlsFile);
	      workbook.write(xlsStream);
	      if(xlsFile.exists()) {
				
				FileInputStream fis=new FileInputStream(xlsFile);
				String filename=URLEncoder.encode(xlsFile.getName(), "UTF-8");
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
	public void downloadthesisplan() throws IOException {
		String teachermajor=(String)req.getSession().getAttribute("teachermajor");
		String pp=req.getServletContext().getRealPath("/WEB-INF/thesisplan/");
		String zip=req.getServletContext().getRealPath("/WEB-INF/zips/");
		String pp1=req.getServletContext().getRealPath("/WEB-INF/thesisplan/"+teachermajor+"/");
		  File files=new File(pp);
		  File file=new File(zip);
		  File filess=new File(pp1);
		 if(!files.exists() && !files.isDirectory())   
	       {  
	          
	           files.mkdir();  
	           //System.out.println(savepath);
	       } 
		 if(!file.exists() && !file.isDirectory())   
	       {  
	          
	           file.mkdir();  
	           //System.out.println(savepath);
	       } 
		 if(!filess.exists() && !filess.isDirectory())   
	       {  
	          
	           filess.mkdir();  
	           //System.out.println(savepath);
	       } 
		 //fileToZip(pp,zip,"allthesisplan");
		 TestCreateZip zips=new TestCreateZip();
		 zips.createZip(pp1,zip+teachermajor+".zip");
		 File f=new File(zip+teachermajor+".zip");
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
	public void downloaddirectplan() throws IOException {
		String teachermajor=(String)req.getSession().getAttribute("teachermajor");
		String pp=req.getServletContext().getRealPath("/WEB-INF/directplan/");
		String zip=req.getServletContext().getRealPath("/WEB-INF/zips/");
		String pp1=req.getServletContext().getRealPath("/WEB-INF/directplan/"+teachermajor+"/");
		  File files=new File(pp);
		  File file=new File(zip);
		  File filess=new File(pp1);
		 if(!files.exists() && !files.isDirectory())   
	       {  
	          
	           files.mkdir();  
	           //System.out.println(savepath);
	       } 
		 if(!file.exists() && !file.isDirectory())   
	       {  
	          
	           file.mkdir();  
	           //System.out.println(savepath);
	       } 
		 if(!filess.exists() && !filess.isDirectory())   
	       {  
	          
	           filess.mkdir();  
	           //System.out.println(savepath);
	       } 
		 //fileToZip(pp,zip,"allthesisplan");
		 TestCreateZip zips=new TestCreateZip();
		 zips.createZip(pp1,zip+teachermajor+"指导记录.zip");
		 File f=new File(zip+teachermajor+"指导记录.zip");
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

}
