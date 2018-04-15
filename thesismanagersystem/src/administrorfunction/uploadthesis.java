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

import optthesisdata.optthesisdataDao;
import studentdata.studentdataDao;
import teacherdata.teacherdataDao;
import thesisdata.thesisdataDao;

/**
 * Servlet implementation class uploadthesis
 */
@WebServlet("/uploadthesis")
public class uploadthesis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public String document;
	ArrayList<String> thesislist=new ArrayList<String>();
	/*ArrayList<String> teacherlist=new ArrayList<String>();
	teacherdataDao teacherdatadao=new teacherdataDao();
	studentdataDao studentdatadao=new studentdataDao();*/
	thesisdataDao ttdao=new thesisdataDao();
	teacherdataDao tdao=new teacherdataDao();
	optthesisdataDao optdao=new optthesisdataDao();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadthesis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//studentlist.clear();
       // teacherlist.clear();
		thesislist.clear();
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
			                    message="�ļ��ϴ��ɹ�";
			                            
			                }
			                
			            }
			            
			        }catch(Exception e){
			            message="�ļ��ϴ�ʧ�ܣ�";
			            e.printStackTrace();
			            
			        }
			    
			        //request.getSession().setAttribute("document", savepath+"\\" + item.get);
			      
			        File xlsFile = new File(document);
			        // ��ù�����
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
			        // ��ù��������
			        int sheetCount = workbook.getNumberOfSheets();
			        //System.out.println(sheetCount);
			        // ����������
			        for (int i = 0; i < sheetCount; i++)
			        {
			           Sheet sheet = workbook.getSheetAt(i);
			           // �������
			         
			           int rows = sheet.getLastRowNum() + 1;
			         //System.out.println(rows);
			           // ����������Ȼ��һ�У��ڵõ���������
			           Row tmp = sheet.getRow(0);
			           if (tmp == null)
			           {
			              continue;
			           }
			         
			           int cols = tmp.getPhysicalNumberOfCells();
			           //System.out.println(cols);
			           // ��ȡ����
			           for (int row = 1; row < rows; row++)
			           {	
			              Row r = sheet.getRow(row);
			              for (int col = 1; col < cols; col++)
			              {	  
			                
			                	if(r.getCell(col)!=null) {
			                	thesislist.add(r.getCell(col).getStringCellValue());
			                	 }
			                	
			                 
			               
			              }
			              if(thesislist!=null&&thesislist.size()>0) {
				        	   //studentdatadao.savedata(studentlist.get(0), studentlist.get(1), studentlist.get(2), studentlist.get(3), studentlist.get(4), studentlist.get(5), studentlist.get(6), studentlist.get(7));
				        	   //System.out.println("i am here");
			            	  ArrayList<Object[]>data=tdao.nameforidandmajor(thesislist.get(0));
			            	  String salaryid = " ";
			            	  String major=" ";
			            	  for(Object[] a:data) {
			            		  salaryid=(String) a[0];
			            		  major=(String) a[1];
			            		  
			            	  }
			            	  ttdao.savethesisupload(thesislist.get(2), thesislist.get(3), salaryid, thesislist.get(0), major);
			            	 
				        	   thesislist.clear();
				           }
			        
			             
			              //System.out.println(studentlist);
			              //System.out.println(teacherlist);
			           }
			          
			        } 
			        request.getRequestDispatcher("administratorpage.action?abc").forward(request, response);
			        doGet(request, response);
	}
		}
	}

}
