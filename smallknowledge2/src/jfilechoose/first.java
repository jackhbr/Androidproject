package jfilechoose;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class first {

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
		JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new File(".")); 
        int result = chooser.showSaveDialog(null); 
        chooser.setDialogTitle("0.0"); 
        if(result==JFileChooser.APPROVE_OPTION); 
        System.out.println(chooser.getSelectedFile().getPath()); 

	}

}
