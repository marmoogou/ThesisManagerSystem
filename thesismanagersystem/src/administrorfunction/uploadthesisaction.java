package administrorfunction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import teacherdata.teacherdataDao;
import thesisdata.thesisdataDao;

public class uploadthesisaction extends ActionSupport {
	private File[] uploadFiles;
	private String[] uploadFilesFileName;
	private String[] uploadFilesContentType;
	thesisdataDao ttdao=new thesisdataDao();
	teacherdataDao tdao=new teacherdataDao();
	ArrayList<String> thesislist=new ArrayList<String>();
	public File[] getUploadFiles() {
		return uploadFiles;
	}
	public void setUploadFiles(File[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}
	public String[] getUploadFilesFileName() {
		return uploadFilesFileName;
	}
	public void setUploadFilesFileName(String[] uploadFilesFileName) {
		this.uploadFilesFileName = uploadFilesFileName;
	}
	public String[] getUploadFilesContentType() {
		return uploadFilesContentType;
	}
	public void setUploadFilesContentType(String[] uploadFilesContentType) {
		this.uploadFilesContentType = uploadFilesContentType;
	}
	public String upload() {
		
		String sc=ServletActionContext.getRequest().getRealPath("/WEB-INF/documents");
		File savefile=new File(sc);
		if(!savefile.exists()||!savefile.isDirectory()) {
			savefile.mkdirs();
			
		}
		 if(uploadFiles!=null&&uploadFiles.length>0) {
			 for(int j=0;j<uploadFiles.length;j++) {
				 File target=new File(sc,uploadFilesFileName[j]);
				 try {
					FileUtils.copyFile(uploadFiles[j], target);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
				   File xlsFile = new File(sc+"\\"+uploadFilesFileName[j]);
			        // 获得工作簿
			        Workbook workbook = null;
					try {
						try {
							workbook = WorkbookFactory.create(xlsFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
			         //System.out.println(rows);
			           // 获得列数，先获得一行，在得到改行列数
			           Row tmp = sheet.getRow(0);
			           if (tmp == null)
			           {
			              continue;
			           }
			         
			           int cols = tmp.getPhysicalNumberOfCells();
			           //System.out.println(cols);
			           // 读取数据
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
			 
			 }
	
}
		 return SUCCESS;
}
}