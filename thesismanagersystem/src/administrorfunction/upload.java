package administrorfunction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import studentdata.studentdataDao;
import teacherdata.teacherdataDao;

/**
 * 
 * Servlet implementation class upload
 */

@WebServlet("/upload")
public class upload extends HttpServlet {
	public String document;
	ArrayList<String> studentlist=new ArrayList<String>();
	ArrayList<String> teacherlist=new ArrayList<String>();
	teacherdataDao teacherdatadao=new teacherdataDao();
	studentdataDao studentdatadao=new studentdataDao();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		studentlist.clear();
        teacherlist.clear();
		if(request.getParameter("upload")!=null) {
			
			if(request.getParameter("upload").equals("yes")) {
				
				   String savepath=this.getServletContext().getRealPath("/WEB-INF/documents");
				   File file=new File(savepath);
			      
			       if(!file.exists() && !file.isDirectory())   
			       {  
			          
			           file.mkdir();  
			       }      
			       
			        String message="";   
			        
			        try{
			            
			            DiskFileItemFactory fctory=new DiskFileItemFactory();
			            ServletFileUpload fileuplaod=new ServletFileUpload(fctory);
			            fileuplaod.setHeaderEncoding("UTF-8");
			            if(!ServletFileUpload.isMultipartContent(request)){
			             
			                return;
			            }
			           
			            List<FileItem> list=fileuplaod.parseRequest(request);
			            for(FileItem item : list){
			                if(item.isFormField()){
			                 
			                    String name=item.getFieldName();
			                    String value=item.getString("UTF-8");
			                    
			                    
			                }else{
			                    
			                    String name=item.getName();
			                   
			                    if(name==null||name.trim().equals("")){
			                        continue;
			                    }
			                    name=name.substring(name.lastIndexOf("\\")+1);
			                    InputStream in=item.getInputStream();
			                    FileOutputStream out =new FileOutputStream(savepath+"\\"+name);
			                    document=savepath+"\\"+name;
			                    byte buffer[]=new byte[1024];
			                    int len=0;
			                    while((len=in.read(buffer))>0){
			                        out.write(buffer, 0, len);;
			                    }
			                    
			                    in.close();
			                    out.close();
			                    item.delete();
			                    message="文件上传成功";
			                            
			                }
			                
			            }
			            
			        }catch(Exception e){
			            message="文件上传失败！";
			            e.printStackTrace();
			            
			        }
			    
			        //request.getSession().setAttribute("document", savepath+"\\" + item.get);
			      
			        File xlsFile = new File(document);
			        // 获得工作簿
			        Workbook workbook = null;
					try {
						workbook = WorkbookFactory.create(xlsFile);
					} catch (EncryptedDocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        // 获得工作表个数
			        int sheetCount = workbook.getNumberOfSheets();
			        //System.out.println(sheetCount);
			        // 遍历工作表
			        for (int i = 0; i < sheetCount; i++)
			        {
			           Sheet sheet = workbook.getSheetAt(i);
			           // 获得行数
			         
			           int rows = sheet.getLastRowNum() + 1;
			         System.out.println(rows);
			           // 获得列数，先获得一行，在得到改行列数
			           Row tmp = sheet.getRow(0);
			           if (tmp == null)
			           {
			              continue;
			           }
			         
			           int cols = tmp.getPhysicalNumberOfCells();
			           System.out.println(cols);
			           // 读取数据
			           for (int row = 1; row < rows; row++)
			           {	
			              Row r = sheet.getRow(row);
			              for (int col = 0; col < cols; col++)
			              {	  
			                 if(i==0) {
			                	 if(r.getCell(col)!=null) {
			                	 studentlist.add(r.getCell(col).getStringCellValue());
			                	 }
			                	
			                 }
			                
			              }
			              if(studentlist!=null&&studentlist.size()>0) {
				        	   studentdatadao.savedata(studentlist.get(0), studentlist.get(1), studentlist.get(2), studentlist.get(3), studentlist.get(4), studentlist.get(5), studentlist.get(6), studentlist.get(7),studentlist.get(8));
				        	   //System.out.println("i am here");
				        	   studentlist.clear();
				           }
			            
			              //System.out.println(studentlist);
			        
			           }
			          
			        } 
			        //response.setHeader("refresh", "3;url=http://localhost:8080/thesismanagersystem/administratorpage.action");
			        request.getRequestDispatcher("administratorpage.action?abc").forward(request, response);
			       // request.getRequestDispatcher("/error.html").forward(request, response);
			        //response.sendRedirect("http://localhost:8080/thesismanagersystem/administratorpage.action");
			        System.out.println("fucks");
			        doGet(request, response);
	}
		}
	}
		



}
