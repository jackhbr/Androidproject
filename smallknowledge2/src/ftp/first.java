package ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class first {

	public static void main(String[] args) {
	first sFirst=new first();
	sFirst.testUpLoadFromDisk();

	}
	
	public void testUpLoadFromDisk(){  
	    try {  
	        FileInputStream in=new FileInputStream(new File("D://3.mp4"));  
	        boolean flag = uploadFile("120.78.55.19", 21, "jackhe", "QQqq2986388386", "c:/ftp", "3.mp4", in);  
	        System.out.println(flag);  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	}  
	
	/** 
	 * Description: ��FTP�������ϴ��ļ� 
	 * @Version1.0 Jul 27, 2008 4:31:09 PM by �޺챣��cuihongbao@d-heaven.com������ 
	 * @param url FTP������hostname ip
	 * @param port FTP�������˿� 
	 * @param username FTP��¼�˺� 
	 * @param password FTP��¼���� 
	 * @param path FTP����������Ŀ¼ 
	 * @param filename �ϴ���FTP�������ϵ��ļ��� 
	 * @param input ������ 
	 * @return �ɹ�����true�����򷵻�false 
	 */  
	public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input) {  
	    boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);//����FTP������  
	        //�������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������  
	        ftp.login(username, password);//��¼  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return success;  
	        }  
	        ftp.changeWorkingDirectory(path);  
	        ftp.setFileType(FTP.BINARY_FILE_TYPE);//���������ı䣬��Ϊ������
	        //ftpĬ�ϴ����ı������Դ���ͼƬ����ʧ�棬��Ϊ�����ƺ�Ϳ�����������ͼƬ��
	        //����ȥ������ô�Ż�������ļ���ȥ��һ��ʱ��
	        
	        ftp.storeFile(filename, input);           
	          
	        input.close();  
	        ftp.logout();  
	        success = true;  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return success;  
	}  

}