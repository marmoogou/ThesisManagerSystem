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

import studentdata.studentdataDao;

public class uploadstudent extends ActionSupport {
	private File[] uploadFiless;
	private String[] uploadFilessFileName;
	private String[] uploadFilessContentType;
	studentdataDao studentdatadao=new studentdataDao();
	ArrayList<String> studentlist=new ArrayList<String>();
	
	public File[] getUploadFiless() {
		return uploadFiless;
	}

	public void setUploadFiless(File[] uploadFiless) {
		this.uploadFiless = uploadFiless;
	}

	public String[] getUploadFilessFileName() {
		return uploadFilessFileName;
	}

	public void setUploadFilessFileName(String[] uploadFilessFileName) {
		this.uploadFilessFileName = uploadFilessFileName;
	}

	public String[] getUploadFilessContentType() {
		return uploadFilessContentType;
	}

	public void setUploadFilessContentType(String[] uploadFilessContentType) {
		this.uploadFilessContentType = uploadFilessContentType;
	}

	public String upload() {
		String sc=ServletActionContext.getRequest().getRealPath("/WEB-INF/documents");
		File savefile=new File(sc);
		if(!savefile.exists()||!savefile.isDirectory()) {
			savefile.mkdirs();
			
		}
		if(uploadFiless!=null&&uploadFiless.length>0) {
			for(int j=0;j<uploadFiless.length;j++) {
				File target=new File(sc,uploadFilessFileName[j]);
				try {
					FileUtils.copyFile(uploadFiless[j], target);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  File xlsFile = new File(sc+"\\"+uploadFilessFileName[j]);
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
				
			}
			
		}
		
		return SUCCESS;
	}
}
