package studentfunction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import studentdata.studentdataDao;

/**
 * Servlet implementation class studentupload
 */
@WebServlet("/studentupload")
public class studentupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	studentdataDao sdao=new studentdataDao();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentupload() {
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
		
			if(request.getParameter("studentupload")!=null) {
				if(request.getParameter("studentupload").equals("yes")) {
			String major=sdao.majorofstudent((String) request.getSession().getAttribute("studentusername"));
			String pp=request.getServletContext().getRealPath("/WEB-INF/thesisplan/");
			  File files=new File(pp);
			 if(!files.exists() && !files.isDirectory())   
		       {  
		          
		           files.mkdir();  
		           //System.out.println(savepath);
		       }      
		   String savepath=request.getServletContext().getRealPath("/WEB-INF/thesisplan/"+major+"/");
		 
		   File file=new File(savepath);
	      
	       if(!file.exists() && !file.isDirectory())   
	       {  
	          
	           file.mkdir();  
	           System.out.println(savepath);
	       }      
	       
	        String message="";   
	        
	        try{
	        	System.out.println("i am here");
	            DiskFileItemFactory fctory=new DiskFileItemFactory();
	            ServletFileUpload fileuplaod=new ServletFileUpload(fctory);
	            fileuplaod.setHeaderEncoding("UTF-8");
	            if(!ServletFileUpload.isMultipartContent(request)){
	             
	                return;
	            }
	            System.out.println("i am here1");
	            List<FileItem> list=fileuplaod.parseRequest(request);
	            for(FileItem item : list){
	                if(item.isFormField()){
	                 
	                    String name=item.getFieldName();
	                    String value=item.getString("UTF-8");
	                    System.out.println("i am here2");
	                    
	                }else{
	                    
	                    String name=item.getName();
	                    System.out.println("i am here3");
	                    if(name==null||name.trim().equals("")){
	                        continue;
	                    }
	                    name=(String) request.getSession().getAttribute("studentusername")+(String)request.getSession().getAttribute("studentname")+".doc";
	                    System.out.println(name);
	                    InputStream in=item.getInputStream();
	                    FileOutputStream out =new FileOutputStream(savepath+"\\"+name);
	                    System.out.println(savepath+"\\"+name);
	                   // document=savepath+"\\"+name;
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
				}
			}
			request.getRequestDispatcher("studentpage.action?abc").forward(request, response);
			doGet(request, response);
	}

}
