package teacherfunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

import mailfunction.failedpass;
import mailfunction.mailinfo;
import mailfunction.teachertostudentmail;
import optthesisdata.optthesisdataDao;
import simhash.simhash;
import studentdata.studentdata;
import studentdata.studentdataDao;
import teacherdata.teacherdata;
import teacherdata.teacherdataDao;
import thesisdata.*
;public class teacherpagefunction implements Action, ServletRequestAware, ServletResponseAware {
	private HttpServletResponse res;
	private HttpServletRequest req;
	thesisdata teacherthesisdata=new thesisdata();
	thesisdataDao  teacherdemo=new thesisdataDao();
	optthesisdataDao demo=new optthesisdataDao();
	studentdataDao demodao=new studentdataDao();
	teacherdataDao ddemo=new teacherdataDao();
	ArrayList<thesisdata> data1=new ArrayList<thesisdata>();
	public static String thesistitle;
	public boolean check=true;
	public static boolean check1=false;
	public static boolean check2=false;
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
		if(req.getSession().getAttribute("teacherusername")==null) {
			
			return "fof";
		}
		req.setAttribute("issuresure", false);
		String salaryid=(String) req.getSession().getAttribute("teacherusername");
		if(req.getParameter("delete")!=null&&req.getParameter("thesisid")!=null) {
			int thesisid=Integer.valueOf(req.getParameter("thesisid"));
			if(req.getParameter("delete").equals("yes")){
				teacherdemo.deletethesis(thesisid);
				
				
			}
			
			
		}
		if(req.getParameter("passid")!=null&&req.getParameter("thesisid")!=null) {
			int thesisid=Integer.valueOf(req.getParameter("thesisid"));
			if(req.getParameter("passid").equals("yes")){
				teacherdemo.setchosed(thesisid, "yes");
				demo.setchecked(thesisid, "yes");
				String studentid =demo.querystudentid(thesisid);
				studentdata data=demodao.querystudent(studentid);
				thesisdata data1=teacherdemo.queryinside(thesisid);
				teacherdata data2=ddemo.queryteacher(salaryid);
				mailinfo info=new mailinfo();
				info.setteacherinfo(data, data1, data2);
				teachertostudentmail sendmail=new teachertostudentmail(info);
				sendmail.start();
				
			}else {
				
				teacherdemo.setchosed(thesisid, "no");
				demo.setchecked(thesisid, "no");
				String studentid =demo.querystudentid(thesisid);
				studentdata data=demodao.querystudent(studentid);
				thesisdata data1=teacherdemo.queryinside(thesisid);
				teacherdata data2=ddemo.queryteacher(salaryid);
				mailinfo info=new mailinfo();
				info.setteacherinfo(data, data1, data2);
				failedpass sendmail=new failedpass(info);
				sendmail.start();
			}
			
			
		}
		if(req.getParameter("checkstudentinfo")!=null&&req.getParameter("thesisid")!=null) {
			int thesisid=Integer.valueOf(req.getParameter("thesisid"));
			if(req.getParameter("checkstudentinfo").equals("yes")) {
				String studentid =demo.querystudentid(thesisid);
				studentdata data=demodao.querystudent(studentid);
				req.getSession().setAttribute("studentinfo", data);
				return "showinfo";
				
			}
			
			
		}
		
		if(req.getParameter("rewrite")!=null&&req.getParameter("thesisid")!=null) {
			int thesisid=Integer.valueOf(req.getParameter("thesisid"));
			if(req.getParameter("rewrite").equals("yes")) {
				req.getSession().setAttribute("rewriteid", thesisid);
				thesisdata data1=teacherdemo.queryinside(thesisid);
				req.getSession().setAttribute("thesisreinfo", data1.getThesistitle());
				req.getSession().setAttribute("thesisrebri", data1.getThesisbrief());
				return "rewrite";
				
			}
			
		
		}
	
			
				
			
				
			if(req.getParameter("checksame1")!=null) {
					if(req.getParameter("checksame1").equals("yes")&&req.getParameter("thesischeckintitle")!=null) {
						simhash sim=new simhash();
						ArrayList<thesisdata> data=teacherdemo.teacherthesisdataquery(salaryid);
						if(!(data.isEmpty())) {
							thesistitle=req.getParameter("thesischeckintitle");
							
						for(thesisdata checksame:data) {
							System.out.println(checksame.getThesisid());
							if(checksame.getThesisid()==(int)req.getSession().getAttribute("rewriteid")) {
								continue;
							}
							if(!sim.compute(req.getParameter("thesischeckintitle"),checksame.getThesistitle())) {
								req.getSession().setAttribute("sametitle", checksame.getThesistitle());
								req.getSession().setAttribute("checkerrorinfo", "有重合超过80%不能录入");
								check=false;
								
								return "rewrite";
								
							}
						}
						req.getSession().setAttribute("anstitle", req.getParameter("thesischeckintitle"));
						check2=true;
						check=true;
						thesistitle=req.getParameter("thesischeckintitle");
						req.getSession().setAttribute("thesisreinfo", thesistitle);
						return "rewrite";
						}else {
							
							req.getSession().setAttribute("anstitle", req.getParameter("thesischeckintitle"));
							check2=true;
							check=true;
							thesistitle=req.getParameter("thesischeckintitle");
							//System.out.println("i am here4");
							
							return "rewrite";
							
						}
						
					}
				}
					
				if(check2&&check) {
					if(req.getParameter("rewritein")!=null) {
					if(req.getParameter("rewritein").equals("yes")) {
						System.out.println(thesistitle);
						String teachername=ddemo.teachername(salaryid);
						teacherdemo.updatethesis(thesistitle, req.getParameter("thesisbrief"),(int)req.getSession().getAttribute("rewriteid") );
						
						check=true;
						check2=false;
						return "checkok";
						
					}
				
				
			}
					}
			
				
		
		// TODO Auto-generated method stub
		
		ArrayList<thesisdata> data=teacherdemo.teacherthesisdataquery(salaryid);
		req.getSession().setAttribute("teacherthesisdata", data);
		if(req.getParameter("downloadinfo")!=null) {
			
			if(req.getParameter("downloadinfo").equals("1")&&!(req.getParameter("yearall").equals("请输入下载年份"))) {
				
				
				
				HSSFWorkbook workbook = new HSSFWorkbook();
			      // 创建工作表
			      HSSFSheet sheet = workbook.createSheet("sheet1");
			      System.out.println("i am here");
			     data1=teacherdemo.querybysalaryidanddate(salaryid,req.getParameter("yearall"));
			     
			     int row=1;
			      HSSFRow rowss = sheet.createRow(0);
			      for(int i=0;i<6;i++) {
			    	  switch(i) {
			    	  case 0: rowss.createCell(i).setCellValue("论文id"); break;
			    	  case 1: rowss.createCell(i).setCellValue("论文标题"); break;
			    	  case 2: rowss.createCell(i).setCellValue("论文简介"); break;
			    	  case 3: rowss.createCell(i).setCellValue("论文指导老师"); break;	
			    	  case 4: rowss.createCell(i).setCellValue("论文发布时间"); break;
			    	  case 5: rowss.createCell(i).setCellValue("是否被选择"); break;
			    	  }
			    	  
			      }
			      for (thesisdata data2:data1)
			      {
			    	
			         HSSFRow rows = sheet.createRow(row);
			      
			         
			         
			         for (int col = 0; col < 6; col++)
			         {	//System.out.println("i am here 1");
			            // 向工作表中添加数据
			        	  switch(col) {
				    	  case 0: rows.createCell(col).setCellValue(data2.getThesisid()); break;
				    	  case 1: rows.createCell(col).setCellValue(data2.getThesistitle()); break;
				    	  case 2: rows.createCell(col).setCellValue(data2.getThesisbrief()); break;
				    	  case 3: rows.createCell(col).setCellValue(data2.getThesisteacher()); break;	
				    	  case 4: rows.createCell(col).setCellValue(data2.getPublicdate().toString()); break;
				    	  case 5: rows.createCell(col).setCellValue(data2.getIschosed()); break;
				    	  }
			            //rows.createCell(col).setCellValue("data" + row + col);
			         }
			         
			         row++;
			      }
			      String savepath=req.getServletContext().getRealPath("/WEB-INF/documents");
			      File xlsFile = new File(savepath+"//"+(String)req.getSession().getAttribute("teachername")+"allinfo.xls");
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
			
if(req.getParameter("downloadinfo").equals("2")&&!(req.getParameter("yearall1").equals("请输入下载年份"))) {
				
				
				
				HSSFWorkbook workbook = new HSSFWorkbook();
			      // 创建工作表
			      HSSFSheet sheet = workbook.createSheet("sheet1");
			     
			     data1=teacherdemo.querybysalaryidanddateandpassed(salaryid,req.getParameter("yearall1"));
			     
			      int row=1;
			      HSSFRow rowss = sheet.createRow(0);
			      for(int i=0;i<6;i++) {
			    	  switch(i) {
			    	  case 0: rowss.createCell(i).setCellValue("论文id"); break;
			    	  case 1: rowss.createCell(i).setCellValue("论文标题"); break;
			    	  case 2: rowss.createCell(i).setCellValue("论文简介"); break;
			    	  case 3: rowss.createCell(i).setCellValue("论文指导老师"); break;	
			    	  case 4: rowss.createCell(i).setCellValue("论文发布时间"); break;
			    	  case 5: rowss.createCell(i).setCellValue("是否被选择"); break;
			    	  }
			    	  
			      }
			      for (thesisdata data2:data1)
			      {
			    	System.out.println(data2.getThesisid());
			         HSSFRow rows = sheet.createRow(row);
			       
			         for (int col = 0; col < 6; col++)
			         {	//System.out.println("i am here 1");
			            // 向工作表中添加数据
			        	  switch(col) {
				    	  case 0: rows.createCell(col).setCellValue(data2.getThesisid()); break;
				    	  case 1: rows.createCell(col).setCellValue(data2.getThesistitle()); break;
				    	  case 2: rows.createCell(col).setCellValue(data2.getThesisbrief()); break;
				    	  case 3: rows.createCell(col).setCellValue(data2.getThesisteacher()); break;	
				    	  case 4: rows.createCell(col).setCellValue(data2.getPublicdate().toString()); break;
				    	  case 5: rows.createCell(col).setCellValue(data2.getIschosed()); break;
				    	  }
			            //rows.createCell(col).setCellValue("data" + row + col);
			         }
			         
			         row++;
			      }
			      String savepath=req.getServletContext().getRealPath("/WEB-INF/documents");
			      File xlsFile = new File(savepath+"//"+(String)req.getSession().getAttribute("teachername")+"allchosedinfo.xls");
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
if(req.getParameter("downloadinfo").equals("3")) {
	
	
	
	HSSFWorkbook workbook = new HSSFWorkbook();
      // 创建工作表
      HSSFSheet sheet = workbook.createSheet("sheet1");
     java.util.Date date=new java.util.Date();
     long times = date.getTime();//时间戳
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
     String dateString = formatter.format(date);
      
     data1=teacherdemo.querybysalaryidanddate(salaryid,dateString);
     
     int row=1;
      HSSFRow rowss = sheet.createRow(0);
      for(int i=0;i<6;i++) {
    	  switch(i) {
    	  case 0: rowss.createCell(i).setCellValue("论文id"); break;
    	  case 1: rowss.createCell(i).setCellValue("论文标题"); break;
    	  case 2: rowss.createCell(i).setCellValue("论文简介"); break;
    	  case 3: rowss.createCell(i).setCellValue("论文指导老师"); break;	
    	  case 4: rowss.createCell(i).setCellValue("论文发布时间"); break;
    	  case 5: rowss.createCell(i).setCellValue("是否被选择"); break;
    	  }
    	  
      }
      for (thesisdata data2:data1)
      {
    	
         HSSFRow rows = sheet.createRow(row);
      
         
         
         for (int col = 0; col < 6; col++)
         {	//System.out.println("i am here 1");
            // 向工作表中添加数据
        	  switch(col) {
	    	  case 0: rows.createCell(col).setCellValue(data2.getThesisid()); break;
	    	  case 1: rows.createCell(col).setCellValue(data2.getThesistitle()); break;
	    	  case 2: rows.createCell(col).setCellValue(data2.getThesisbrief()); break;
	    	  case 3: rows.createCell(col).setCellValue(data2.getThesisteacher()); break;	
	    	  case 4: rows.createCell(col).setCellValue(data2.getPublicdate().toString()); break;
	    	  case 5: rows.createCell(col).setCellValue(data2.getIschosed()); break;
	    	  }
            //rows.createCell(col).setCellValue("data" + row + col);
         }
         
         row++;
      }
      String savepath=req.getServletContext().getRealPath("/WEB-INF/documents");
      File xlsFile = new File(savepath+"//"+(String)req.getSession().getAttribute("teachername")+"allinfo.xls");
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
	
if(req.getParameter("downloadinfo").equals("4")) {
	
	
	
	HSSFWorkbook workbook = new HSSFWorkbook();
      // 创建工作表
      HSSFSheet sheet = workbook.createSheet("sheet1");
      java.util.Date date=new java.util.Date();
      long times = date.getTime();//时间戳
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
      String dateString = formatter.format(date);
     data1=teacherdemo.querybysalaryidanddateandpassed(salaryid,dateString);
     if(data!=null) {
     int row=1;
      HSSFRow rowss = sheet.createRow(0);
      for(int i=0;i<6;i++) {
    	  switch(i) {
    	  case 0: rowss.createCell(i).setCellValue("论文id"); break;
    	  case 1: rowss.createCell(i).setCellValue("论文标题"); break;
    	  case 2: rowss.createCell(i).setCellValue("论文简介"); break;
    	  case 3: rowss.createCell(i).setCellValue("论文指导老师"); break;	
    	  case 4: rowss.createCell(i).setCellValue("论文发布时间"); break;
    	  case 5: rowss.createCell(i).setCellValue("是否被选择"); break;
    	  }
    	  
      }
      for (thesisdata data2:data1)
      {
    	
         HSSFRow rows = sheet.createRow(row);
      
         
         
         for (int col = 0; col < 6; col++)
         {	//System.out.println("i am here 1");
            // 向工作表中添加数据
        	  switch(col) {
	    	  case 0: rows.createCell(col).setCellValue(data2.getThesisid()); break;
	    	  case 1: rows.createCell(col).setCellValue(data2.getThesistitle()); break;
	    	  case 2: rows.createCell(col).setCellValue(data2.getThesisbrief()); break;
	    	  case 3: rows.createCell(col).setCellValue(data2.getThesisteacher()); break;	
	    	  case 4: rows.createCell(col).setCellValue(data2.getPublicdate().toString()); break;
	    	  case 5: rows.createCell(col).setCellValue(data2.getIschosed()); break;
	    	  }
            //rows.createCell(col).setCellValue("data" + row + col);
         }
         
         row++;
      }
      String savepath=req.getServletContext().getRealPath("/WEB-INF/documents");
      File xlsFile = new File(savepath+"//"+(String)req.getSession().getAttribute("teachername")+"allinfo.xls");
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
		}
		if(req.getParameter("checkin")!=null) {
			if(req.getParameter("checkin").equals("yes")) {
				return "checkin";
			}
		}
		
		if(req.getParameter("search")!=null&&req.getParameter("searchyear")!=null) {
			if(req.getParameter("search").equals("yes")&&!(req.getParameter("searchyear").equals("输入查询年份"))) {
				ArrayList<String> datas=teacherdemo.querytitles(salaryid, req.getParameter("searchyear"));
				if(datas!=null) {
					
				
				req.getSession().setAttribute("titless", datas);}
				
			}
		}
		if(req.getParameter("checksame")!=null) {
			if(req.getParameter("checksame").equals("yes")&&req.getParameter("thesischeckintitle")!=null) {
				simhash sim=new simhash();
				//res.setContentType("text/html;charset=UTF-8");
				if(!(data.isEmpty())) {
					thesistitle=req.getParameter("thesischeckintitle");
					System.out.println("i am here3");
				for(thesisdata checksame:data) {
					if(!sim.compute(req.getParameter("thesischeckintitle"),checksame.getThesistitle())) {
						req.getSession().setAttribute("sametitle", checksame.getThesistitle());
						req.getSession().setAttribute("checkerrorinfo", "有重合超过80%不能录入");
						//res.getWriter().println("与"+checksame.getThesistitle()+"有重合超过80%不能录入");
						check=false;
						if(check1&&check) {
							req.setAttribute("issuresure", true);
						}else {
							req.setAttribute("issuresure", false);
						}
						
						return "checkin";
						
					}
					req.getSession().setAttribute("anstitle", req.getParameter("thesischeckintitle"));
					check1=true;
					check=true;
					thesistitle=req.getParameter("thesischeckintitle");
					if(check1&&check) {
						req.setAttribute("issuresure", true);
					}else {
						req.setAttribute("issuresure", false);
					}
					return "checkin";
				}
				}else {
					
					req.getSession().setAttribute("anstitle", req.getParameter("thesischeckintitle"));
					check1=true;
					check=true;
					thesistitle=req.getParameter("thesischeckintitle");
					System.out.println("i am here4");
					if(check1&&check) {
						req.setAttribute("issuresure", true);
					}else {
						req.setAttribute("issuresure", false);
					}
					return "checkin";
					
				}
				
			}
			
			
			}
		
		if(check1&&check&&req.getParameter("savethesis")!=null&&req.getParameter("thesisbrief")!=null&&!(req.getParameter("thesisbrief").equals("请输入论文简介"))) {
			System.out.println("i am here2");
			if(req.getParameter("savethesis").equals("yes")) {
				System.out.println(thesistitle);
				String teachername=ddemo.teachername(salaryid);
				String major=ddemo.teachermajor(salaryid);
				teacherdemo.savethesis(thesistitle, req.getParameter("thesisbrief"), salaryid, teachername,major);
				
				check=true;
				check1=false;
				return "checkok";
				
			}
			
					
		}
		if(req.getParameter("ad")!=null) {
			if(req.getParameter("ad").equals("yes")) {
				String isad=ddemo.isad(salaryid);
				if(isad.equals("yes")) {
					return "isad";
				}else {
					return SUCCESS;
				}
				
			}
		}
		return SUCCESS;
	}

}
