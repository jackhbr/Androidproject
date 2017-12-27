package fileio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDemo {

	public static void main(String[] args) {
		String newName = "‪D:\\java\\adt-bundle-windows-x86_64-20130917\\adt-bundle-windows-x86_64-20130917\\Androidproject\\xchengge\\src\\fileio\\file.java";
		File newfile = new File(newName);
		try {
			FileDemo.fileOutput(newfile);
			FileDemo.fileInput(newfile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取文件
	 * @param file
	 */
	public static void fileInput(File file)throws IOException{
		
		//打开文件输入流
		FileInputStream fis = new FileInputStream(file);
		DataInputStream dis = new DataInputStream(fis);
		
		StringBuffer str = new StringBuffer();
		
		int length = dis.readInt();
		for(int i=0;i<length;i++){
			char c = dis.readChar();
			str.append(c);
		}
		String name = new String(str);
		int score = dis.readInt();
		System.out.println("name:"+name+"   分数："+score);
		fis.close();
		
		
	}
	public static void fileOutput(File file)throws IOException{
		String name="宋泽";  int score=10002;
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(fos);
		
		int length = name.length();
		dos.writeInt(length);
		for(int i=0;i<length;i++){
			dos.writeChar(name.charAt(i));
		}
		dos.writeInt(score);
		fos.flush();   //强制写入
		fos.close();
		
	}
}
