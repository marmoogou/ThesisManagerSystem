package administrorfunction;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;

import administratordata.administratordataDao;
import mailfunction.sendmailtoall;
import optthesisdata.optthesisdata;
import optthesisdata.optthesisdataDao;
import startdata.startdataDao;
import studentdata.studentdata;
import studentdata.studentdataDao;
import teacherdata.teacherdata;
import teacherdata.teacherdataDao;
import thesisdata.thesisdata;
import thesisdata.thesisdataDao;

public class administrorfunction implements ServletRequestAware, Action, ServletResponseAware {
	private HttpServletResponse res;
	private HttpServletRequest req;
	ArrayList<studentdata> studentlist =new ArrayList<studentdata>();
	ArrayList<teacherdata> teacherlist =new ArrayList<teacherdata>();
	studentdataDao studentdao=new studentdataDao();
	teacherdataDao teacherdao=new teacherdataDao();
	optthesisdataDao optdao=new optthesisdataDao();
	thesisdataDao thesisdao=new thesisdataDao();
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

	  public void fileToZip(String sourceFilePath,String zipFilePath,String fileName){  
	        boolean flag = false;  
	        //String realpath= new File(sourceFilePath).getPath();
	        File sourceFile = new File(sourceFilePath);  
	        FileInputStream fis = null;  
	        BufferedInputStream bis = null;  
	        FileOutputStream fos = null;  
	        ZipOutputStream zos = null;  
	          
	        if(sourceFile.exists() == false){  
	            //System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");  
	        }else{  
	            try {  
	                File zipFile = new File(zipFilePath + "/" + fileName +".zip");  
	                if(zipFile.exists()){  
	                   // System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");  
	                }else{  
	                    File[] sourceFiles = sourceFile.listFiles();  
	                    if(null == sourceFiles || sourceFiles.length<1){  
	                       // System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");  
	                    }else{  
	                        fos = new FileOutputStream(zipFile);  
	                        zos = new ZipOutputStream(new BufferedOutputStream(fos));  
	                        byte[] bufs = new byte[1024*10];  
	                        for(int i=0;i<sourceFiles.length;i++){  
	                            //创建ZIP实体，并添加进压缩包  
	                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());  
	                            zos.putNextEntry(zipEntry);  
	                            //读取待压缩的文件并写进压缩包里  
	                            fis = new FileInputStream(sourceFiles[i]);  
	                            bis = new BufferedInputStream(fis, 1024*10);  
	                            int read = 0;  
	                            while((read=bis.read(bufs, 0, 1024*10)) != -1){  
	                                zos.write(bufs,0,read);  
	                            }  
	                        }  
	                        flag = true;  
	                    }  
	                }  
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            } finally{  
	                //关闭流  
	                try {  
	                    if(null != bis) bis.close();  
	                    if(null != zos) zos.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                    throw new RuntimeException(e);  
	                }  
	            }  
	        }  
	         
	    }  
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(req.getSession().getAttribute("administratorusername")==null) {
			return "fof";
		}
		
		
		if(req.getParameter("downloadallthesis")!=null) {
			if(req.getParameter("downloadallthesis").equals("yes")) {

				
				ArrayList<thesisdata> dataopt=thesisdao.thesisdataquery();
				
				
				HSSFWorkbook workbook = new HSSFWorkbook();
			      // 创建工作表
			      HSSFSheet sheet = workbook.createSheet("sheet1");
			      java.util.Date date=new java.util.Date();
			      long times = date.getTime();//时间戳
			      SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			      String dateString = formatter.format(date);
			    // data1=teacherdemo.querybysalaryidanddateandpassed(salaryid,dateString);
			     if(dataopt!=null) {
			     int row=1;
			      HSSFRow rowss = sheet.createRow(0);
			      for(int i=0;i<6;i++) {
			    	  switch(i) {
			    	  case 0: rowss.createCell(i).setCellValue("论文id"); break;
			    	  case 1: rowss.createCell(i).setCellValue("论文标题"); break;
			    	  case 2: rowss.createCell(i).setCellValue("论文简介"); break;
			    	  case 3: rowss.createCell(i).setCellValue("论文指导老师"); break;	
			    	  case 4: rowss.createCell(i).setCellValue("发布时间"); break;
			    	  case 5: rowss.createCell(i).setCellValue("选择情况"); break;
			    	
			    	
			    	  }
			    	  
			      }
			      for (thesisdata data2:dataopt)
			      {
			    	
			         HSSFRow rows = sheet.createRow(row);
			      
			         
			        // studentdata studata=studentdao.querystudent(data2.getStudentid());
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
			         
			         ++row;
			      }
			      String savepath=req.getServletContext().getRealPath("/WEB-INF/documents");
			      File xlsFile = new File(savepath+"//"+"allthesisinfo.xls");
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
		if(req.getParameter("downloadall")!=null) {
			if(req.getParameter("downloadall").equals("yes")) {

				
				ArrayList<optthesisdata> dataopt=optdao.optthesisdataquerya();
				
				
				HSSFWorkbook workbook = new HSSFWorkbook();
			      // 创建工作表
			      HSSFSheet sheet = workbook.createSheet("sheet1");
			      java.util.Date date=new java.util.Date();
			      long times = date.getTime();//时间戳
			      SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			      String dateString = formatter.format(date);
			    // data1=teacherdemo.querybysalaryidanddateandpassed(salaryid,dateString);
			     if(dataopt!=null) {
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
			      for (optthesisdata data2:dataopt)
			      {
			    	
			         HSSFRow rows = sheet.createRow(row);
			      
			         
			         studentdata studata=studentdao.querystudent(data2.getStudentid());
			         teacherdataDao teadao=new teacherdataDao();
			         for (int col = 0; col < 9; col++)
			         {	//System.out.println("i am here 1");
			            // 向工作表中添加数据
			        	  switch(col) {
			        	  case 0: rows.createCell(col).setCellValue(data2.getStudentid()); break;
				    	  case 1: rows.createCell(col).setCellValue(studata.getStudentname()); break;
				    	  case 2: rows.createCell(col).setCellValue(studata.getStudentsex()); break;
				    	  case 3: rows.createCell(col).setCellValue(studata.getStudenttel()); break;
				    	  case 4: rows.createCell(col).setCellValue(studata.getStudentqq()); break;
				    	  case 5: rows.createCell(col).setCellValue(studata.getStudentemail()); break;
				    	  case 6: rows.createCell(col).setCellValue(data2.getThesistopic()); break;
				    	  case 7: rows.createCell(col).setCellValue(data2.getThesisteacher()); break;
				    	  case 8: rows.createCell(col).setCellValue(teadao.namefortitle(data2.getThesisteacher())); break;



				    	  }
			            //rows.createCell(col).setCellValue("data" + row + col);
			         }
			         
			         row++;
			      }
			      String savepath=req.getServletContext().getRealPath("/WEB-INF/documents");
			      File xlsFile = new File(savepath+"//"+"allselectinfo.xls");
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
		if(req.getParameter("sendtoallteacher")!=null) {
			if(req.getParameter("sendtoallteacher").equals("yes")) {
				return "sendtoallteacher";
				
			}
		}
		if(req.getParameter("sendtt")!=null&&req.getParameter("sendinside")!=null) {
			if(req.getParameter("sendtt").equals("yes")) {
				ArrayList<String> data=teacherdao.queryteachermail();
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
				ArrayList<String> data=studentdao.mailofstudent();
				sendmailtoall toall=new sendmailtoall(data,req.getParameter("sendinside"));
				toall.start();
				return "complete";
				
			}
		}
	
		if(req.getParameter("createstudent")!=null) {
			if(req.getParameter("createstudent").equals("yes")) {
				return "createstudent";
				
			}
		}
		if(req.getParameter("updatess")!=null) {
			if(req.getParameter("updatess").equals("yes")) {
				if(req.getParameter("updatespassword")!=null&&req.getParameter("updatesqq")!=null&&req.getParameter("updatestel")!=null&&req.getParameter("updatesemail")!=null&&req.getParameter("updatesid")!=null&&req.getParameter("updatesname")!=null&&req.getParameter("updatesmajor")!=null&&req.getParameter("updatesgrade")!=null) {
					studentdao.savedata(req.getParameter("updatesid"), req.getParameter("updatesname"), req.getParameter("updatespassword"), req.getParameter("updatesqq"), req.getParameter("updatesemail"), req.getParameter("updatestel"), req.getParameter("updatesgrade"), req.getParameter("updatesmajor"),req.getParameter("updatessex"));
					
					return "complete";
				}
				
			}
		}
		if(req.getParameter("createteacher")!=null) {
			if(req.getParameter("createteacher").equals("yes")) {
				return "createteacher";
				
			}
		}
		if(req.getParameter("updatett")!=null) {
			if(req.getParameter("updatett").equals("yes")) {
				if(req.getParameter("updatetpassword")!=null&&req.getParameter("updatetqq")!=null&&req.getParameter("updatettel")!=null&&req.getParameter("updatetemail")!=null&&req.getParameter("updatetsalaryid")!=null&&req.getParameter("updatetname")!=null&&req.getParameter("updatettitle")!=null) {
					teacherdao.savedata(req.getParameter("updatetsalaryid"), req.getParameter("updatetname"), req.getParameter("updatetpassword"), req.getParameter("updatetqq"), req.getParameter("updatettel"), req.getParameter("updatetemail"), req.getParameter("updatettitle"),req.getParameter("majorin"));
					return "complete";
				}
				
			}
		}
		if(req.getParameter("downthesisplan")!=null) {
			if(req.getParameter("downthesisplan").equals("yes")) {
				String pp=req.getServletContext().getRealPath("/WEB-INF/thesisplan/");
				String zip=req.getServletContext().getRealPath("/WEB-INF/zips/");
				  File files=new File(pp);
				  File file=new File(zip);
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
				 //fileToZip(pp,zip,"allthesisplan");
				 TestCreateZip zips=new TestCreateZip();
				 zips.createZip(pp,zip+"论文任务书.zip");
				 File f=new File(zip+"论文任务书.zip");
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
		if(req.getParameter("downreplan")!=null) {
			if(req.getParameter("downreplan").equals("yes")) {
				String pp=req.getServletContext().getRealPath("/WEB-INF/directplan/");
				String zip=req.getServletContext().getRealPath("/WEB-INF/zips/");
				  File files=new File(pp);
				  File file=new File(zip);
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
				 //fileToZip(pp,zip,"allthesisplan");
				 TestCreateZip zips=new TestCreateZip();
				 zips.createZip(pp,zip+"指导记录.zip");
				 File f=new File(zip+"指导记录.zip");
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
		if(req.getParameter("start")!=null) {
			if(req.getParameter("start").equals("yes")) {
				//addao.updatestartyes((String) req.getSession().getAttribute("administratorusername"));
				ssdao.updatestartyes(1);
				return "complete";
			}
		}
		if(req.getParameter("end")!=null) {
			if(req.getParameter("end").equals("yes")) {
				//addao.updatestartno((String) req.getSession().getAttribute("administratorusername"));
				ssdao.updatestartno(1);
				return "complete";
			}
		}
		ArrayList<teacherdata> datademo=teacherdao.queryalldata();
		ArrayList<studentdata> data=studentdao.queryalldata();
		ArrayList<Object[]> titlelist=thesisdao.querytitlesandnameanddate();
		ArrayList<String[]> datalist=new ArrayList<String[]>();
		
		for(Object[] a:titlelist) {
			String aa[]=new String[3];
			aa[0]=(String) a[0];
			aa[1]=(String) a[1];
			aa[2]=a[2].toString();
			datalist.add(aa);
		}
		req.getSession().setAttribute("teacherinfo",datademo);
		req.getSession().setAttribute("studentinfo", data);
		req.getSession().setAttribute("titleinfo", datalist);
			 return SUCCESS;
		 }
	
	}


