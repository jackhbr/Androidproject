package jfilechoose;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class sec {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		try { // 使用Windows的界面风格     有这段和没这段的界面差别就特别大了
			UIManager    //设置界面的外观
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
//		JFileChooser fd = new JFileChooser();  
//		//fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
//		//fd.showOpenDialog(null);  
//		fd.showSaveDialog(null);  //里面的拼命表示是保存还是打开
//		File f = fd.getSelectedFile();  
//    	if(f != null){}  
		
		JFileChooser jf = new JFileChooser();  
		jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);  
		jf.showDialog(null,null);  
		File fi = jf.getSelectedFile();  
		String f = fi.getAbsolutePath()+"\\test.txt";  
		System.out.println("save: "+f);  
		try{  
		    FileWriter out = new FileWriter(f);  //根据文件的后缀名生成文件
		    out.write("successful!!!");  //往文件里面写入内容
		    out.close();  
		}  
		catch(Exception e){}  

	}

}
