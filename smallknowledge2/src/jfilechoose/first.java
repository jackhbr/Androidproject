package jfilechoose;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class first {

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
		JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new File(".")); 
        int result = chooser.showSaveDialog(null); 
        chooser.setDialogTitle("0.0"); 
        if(result==JFileChooser.APPROVE_OPTION); 
        System.out.println(chooser.getSelectedFile().getPath()); 

	}

}
