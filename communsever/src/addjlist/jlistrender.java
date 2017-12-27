package addjlist;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

public class jlistrender implements ListCellRenderer<String> {

	@Override  //注意看这个函数的说明！！！value是model里的获取数组队列里的对象
	public Component getListCellRendererComponent(JList<? extends String> list,
			String value, int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		
		JPanel jPanel=new JPanel();
		
      //  JTextField jt=new JTextField();  //这个是有边框的
            JTextArea jt  =new JTextArea();    //文本域就是单独的文字，没有边框
       
        
		if(value !=null)
		{
			//ImageIcon jImage=new ImageIcon("d:\\"+value+".jpg");  
			ImageIcon jImage=new ImageIcon("d:\\jc.jpg"); 
			jImage.setImage(jImage.getImage().getScaledInstance(60, 60, 0));  //设置图片的大小
			jt.setText(value);
			 JLabel jLabel=new JLabel(jImage);
			 
			 jPanel.add(jLabel);//只能add组件
			jPanel.add(jt);
			
			//jPanel.add(comp)
		}
	       if   (isSelected)   {   
              jPanel. setBackground(list.getSelectionBackground());   
              jPanel.  setForeground(list.getSelectionForeground());   
       }   
       else   {   
               //设置选取与取消选取的前景与背景颜色.   
    	   jPanel.  setBackground(list.getBackground());   
    	   jPanel.  setForeground(list.getForeground());   
       }   
       
		
		
		return jPanel;
	}

}
