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
		
		
		try { // ʹ��Windows�Ľ�����     ����κ�û��εĽ�������ر����
			UIManager    //���ý�������
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
//		JFileChooser fd = new JFileChooser();  
//		//fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
//		//fd.showOpenDialog(null);  
//		fd.showSaveDialog(null);  //�����ƴ����ʾ�Ǳ��滹�Ǵ�
//		File f = fd.getSelectedFile();  
//    	if(f != null){}  
		
		JFileChooser jf = new JFileChooser();  
		jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);  
		jf.showDialog(null,null);  
		File fi = jf.getSelectedFile();  
		String f = fi.getAbsolutePath()+"\\test.txt";  
		System.out.println("save: "+f);  
		try{  
		    FileWriter out = new FileWriter(f);  //�����ļ��ĺ�׺�������ļ�
		    out.write("successful!!!");  //���ļ�����д������
		    out.close();  
		}  
		catch(Exception e){}  

	}

}
