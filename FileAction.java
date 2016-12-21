package AppYun.commend.BaseAction.FileAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import AppYun.commend.BaseAction.FileAction.FileUtil;
import AppYun.commend.BaseAction.Impl.BaseActionImpl;

public class FileAction extends BaseActionImpl{  
    private File file;  
     private String fileFileName;   
     private String message = "你已成功上传文件";   
     private PrintWriter out;  

     public String getMessage() {  
         return message;  
     }  

     public void setMessage(String message) {  
         this.message = message;  
     }  

     public File getFile() {  
         return file;  
     }  

     public void setFile(File file) {  
         this.file = file;  
     }  

     public String getFileFileName() {  
         return fileFileName;  
     }  

     public void setFileFileName(String fileFileName) {  
         this.fileFileName = fileFileName;  
     }  

     public String getPiceFile() throws Exception {  
         String saveDir = request.getParameter("saveDir");  
         String path = request.getRealPath("/"+saveDir);  
         //如果文件夹不存在则创建      
         FileUtil.createDirectory(path);  
         try {  
             File f = this.getFile();  
             if(this.getFileFileName().endsWith(".exe")){  
                 message="对不起,你上传的文件格式不允许!!!";  
                 return ERROR;  
             }  
             FileInputStream inputStream = new FileInputStream(f);  
             FileOutputStream outputStream = new FileOutputStream(path + "/"+ this.getFileFileName());  
             byte[] buf = new byte[1024];  
             int length = 0;  
             while ((length = inputStream.read(buf)) != -1) {  
                 outputStream.write(buf, 0, length);  
             }  
             inputStream.close();  
             outputStream.flush();
             outputStream.close();
         } catch (Exception e) {  
             e.printStackTrace();  
             message = "对不起,文件上传失败了!!!!";  
         }  
         return SUCCESS;  
     }  
} 
