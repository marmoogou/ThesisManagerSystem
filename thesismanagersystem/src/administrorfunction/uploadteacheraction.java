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

public class uploadteacheraction extends ActionSupport {
	private File[] uploadFile;
	private String[] uploadFileFileName;
	private String[] uploadFileContentType;
	teacherdataDao teacherdatadao=new teacherdataDao();
	public File[] getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File[] uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String[] getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String[] uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	public String[] getUploadFileContentType() {
		return uploadFileContentType;
	}
	public void setUploadFileContentType(String[] uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	public String upload() {
			String sc=ServletActionContext.getRequest().getRealPath("/WEB-INF/documents");
			File savefile=new File(sc);
			if(!savefile.exists()||!savefile.isDirectory()) {
				savefile.mkdirs();
				
			}
			 if(uploadFile!=null&&uploadFile.length>0) {
				 for(int j=0;j<uploadFile.length;j++) {
					 File target=new File(sc,uploadFileFileName[j]);
					 try {
						FileUtils.copyFile(uploadFile[j], target);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					   File xlsFile = new File(sc+"\\"+uploadFileFileName[j]);
					   ArrayList<String> teacherlist=new  ArrayList<String>();
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
						} catch (IOException e) {
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
				                	 teacherlist.add(r.getCell(col).getStringCellValue());
				                	 }
				                	
				                 }
				                
				              }
				              if(teacherlist!=null&&teacherlist.size()>0) {
					        	   //studentdatadao.savedata(studentlist.get(0), studentlist.get(1), studentlist.get(2), studentlist.get(3), studentlist.get(4), studentlist.get(5), studentlist.get(6), studentlist.get(7),studentlist.get(8));
				            	  	teacherdatadao.savedata(teacherlist.get(0), teacherlist.get(1), teacherlist.get(2), teacherlist.get(3), teacherlist.get(4), teacherlist.get(5), teacherlist.get(6), teacherlist.get(7));
					        	   //System.out.println("i am here");
				            	  teacherlist.clear();
					           }
				            
				              //System.out.println(studentlist);
				        
				           }
				          
				        } 
					 
					 
				 }
		
				 
				 
		
	
		
		
		
	}
				return SUCCESS;
			 
	}
}


