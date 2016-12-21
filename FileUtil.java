package AppYun.commend.BaseAction.FileAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/** 
 * 有关文件操作的工具类 
 *  
 * @author K3vinsG
 *  
 */  
public class FileUtil {
	 /** 
     * 判断文件夹是否存在，不存在创建文件夹 
     *  
     * @param path 
     *            文件夹路径 
     */  
	public static void createDirectory(String path) {  
        File file = new File(path);  
        // 如果文件夹不存在则创建  
        if (!file.exists() && !file.isDirectory()) {  
            System.out.println("//不存在");  
            file.mkdir();  
        } else {  
            System.out.println("//目录存在");  
        }  
    
    }  
	 /** 
     * 判断文件是否存在，不存在创建文件 
     *  
     * @param path 
     *            文件路径 
     */ 
	 public static void createFile(String path) {  
	        File file = new File(path);  
	        if (!file.exists()) {  
	            try {  
	                file.createNewFile();  
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	 /** 
	     * 判断两个文件的内容是否相同，文件名要用绝对路径 
	     *  
	     * @param fileName1 
	     *            文件1的绝对路径 
	     * @param fileName2 
	     *            文件2的绝对路径 
	     * @return 相同返回true，不相同返回false 
	     */ 
	 public boolean isSameFile(String fileName1, String fileName2) {  
	        FileInputStream fis1 = null;  
	        FileInputStream fis2 = null;  
	        try {  
	            fis1 = new FileInputStream(fileName1);  
	            fis2 = new FileInputStream(fileName2);  
	  
	            int len1 = fis1.available();// 返回总的字节数  
	            int len2 = fis2.available();  
	  
	            if (len1 == len2) {// 长度相同，则比较具体内容  
	                // 建立两个字节缓冲区  
	                byte[] data1 = new byte[len1];  
	                byte[] data2 = new byte[len2];  
	  
	                // 分别将两个文件的内容读入缓冲区  
	                fis1.read(data1);  
	                fis2.read(data2);  
	  
	                // 依次比较文件中的每一个字节  
	                for (int i = 0; i < len1; i++) {  
	                    // 只要有一个字节不同，两个文件就不一样  
	                    if (data1[i] != data2[i]) {  
	                        System.out.println("文件内容不一样");  
	                        return false;  
	                    }  
	                }  
	                System.out.println("两个文件完全相同");  
	                return true;  
	            } else {  
	                // 长度不一样，文件肯定不同  
	                return false;  
	            }  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {// 关闭文件流，防止内存泄漏  
	            if (fis1 != null) {  
	                try {  
	                    fis1.close();  
	                } catch (IOException e) {  
	                    // 忽略  
	                    e.printStackTrace();  
	                }  
	            }  
	            if (fis2 != null) {  
	                try {  
	                    fis2.close();  
	                } catch (IOException e) {  
	                    // 忽略  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        return false;  
	    }  
	 /** 
	     * 以字符串形式返回文件内容 
	     *  
	     * @param filePath 
	     *            文件的绝对路径 
	     * @return 返回字符串形式的文件内容 
	     */  
	 public static String readFileByChars(String filePath) {  
	        Reader reader = null;  
	        StringBuffer sb = new StringBuffer();  
	        try {  
	             System.out.println("以字符为单位读取文件内容，一次读多个字节：");  
	            // 一次读多个字符  
	            char[] tempchars = new char[1];  
	            int charread = 0;  
	            // 该文件存在  
	            reader = new InputStreamReader(new FileInputStream(filePath));  
	            // 读入多个字符到字符数组中，charread为一次读取字符数  
	            while ((charread = reader.read(tempchars)) != -1) {  
	                // 同样屏蔽掉\r不显示  
	                if ((charread == tempchars.length)  
	                        && (tempchars[tempchars.length - 1] != '\r')) {  
	                    System.out.print(tempchars);  
	                } else {  
	                    for (int i = 0; i < charread; i++) {  
	                        if (tempchars[i] == '\r') {  
	                            continue;  
	                        } else {  
	                            // System.out.print(tempchars[i]);  
	                        }  
	                    }  
	                }  
	                sb.append(tempchars);  
	            }  
	  
	        } catch (Exception e1) {  
	            e1.printStackTrace();  
	        } finally {  
	            if (reader != null) {  
	                try {  
	                    reader.close();  
	                } catch (IOException e1) {  
	                }  
	            }  
	        }  
	        return sb.toString();  
	    }  

}
