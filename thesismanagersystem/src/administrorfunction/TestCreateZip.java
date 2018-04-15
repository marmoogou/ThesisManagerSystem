package administrorfunction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.util.Date;  
import org.apache.tools.zip.ZipEntry;  
import org.apache.tools.zip.ZipOutputStream;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory; 
public class TestCreateZip {
	  //private static final Logger log = LoggerFactory.getLogger(TestCreateZip.class);  
    public TestCreateZip(){};  
   /** 
     * ����ZIP�ļ� 
     * @param sourcePath �ļ����ļ���·�� 
     * @param zipPath ���ɵ�zip�ļ�����·���������ļ����� 
     */  
    public static void createZip(String sourcePath, String zipPath) {  
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  
        try {  
            fos = new FileOutputStream(zipPath);  
            zos = new ZipOutputStream(fos);  
            zos.setEncoding("gbk");//�˴��޸��ֽ��뷽ʽ��  
            //createXmlFile(sourcePath,"293.xml");  
            writeZip(new File(sourcePath), "", zos);  
        } catch (FileNotFoundException e) {  
           // log.error("����ZIP�ļ�ʧ��",e);  
        } finally {  
            try {  
                if (zos != null) {  
                    zos.close();  
                }  
            } catch (IOException e) {  
               // log.error("����ZIP�ļ�ʧ��",e);  
            }  
  
        }  
    }  
  
    private static void writeZip(File file, String parentPath, ZipOutputStream zos) {  
        if(file.exists()){  
            if(file.isDirectory()){//�����ļ���  
                parentPath+=file.getName()+File.separator;  
                File [] files=file.listFiles();  
                if(files.length != 0)  
                {  
                    for(File f:files){  
                        writeZip(f, parentPath, zos);  
                    }  
                }  
                else  
                {       //��Ŀ¼�򴴽���ǰĿ¼  
                        try {  
                            zos.putNextEntry(new ZipEntry(parentPath));  
                        } catch (IOException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        }  
                }  
            }else{  
                FileInputStream fis=null;  
                try {  
                    fis=new FileInputStream(file);  
                    ZipEntry ze = new ZipEntry(parentPath +  file.getName());  
                    zos.putNextEntry(ze);  
                    byte [] content=new byte[1024];  
                    int len;  
                    while((len=fis.read(content))!=-1){  
                        zos.write(content,0,len);  
                        zos.flush();  
                    }  
  
                } catch (FileNotFoundException e) {  
                   // log.error("����ZIP�ļ�ʧ��",e);  
                } catch (IOException e) {  
                   // log.error("����ZIP�ļ�ʧ��",e);  
                }finally{  
                    try {  
                        if(fis!=null){  
                            fis.close();  
                        }  
                    }catch(IOException e){  
                        //log.error("����ZIP�ļ�ʧ��",e);  
                    }  
                }  
            }  
        }  
    }   
}
