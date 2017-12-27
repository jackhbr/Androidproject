package client3;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

public class jlistrender implements ListCellRenderer<Client> {
	public Component getListCellRendererComponent(JList<? extends Client> list,
			Client value, int index, boolean isSelected, boolean cellHasFocus) {
		
			JPanel jPanel=new JPanel();
            JTextArea jt  =new JTextArea(); 
       
        
		if(value !=null)
		{
			//ImageIcon jImage=new ImageIcon("d:\\"+value+".jpg");  
			ImageIcon jImage=new ImageIcon("d:\\QQ\\images\\"+value.getAcount()+".jpg"); 
			jImage.setImage(jImage.getImage().getScaledInstance(60, 60, 0));  //����ͼƬ�Ĵ�С
			jt.setText(value.getName());
			JLabel jLabel=new JLabel(jImage);
			 
		    jPanel.add(jLabel);//ֻ��add���
			jPanel.add(jt);
		}
	       if   (isSelected)   {   
              jPanel. setBackground(list.getSelectionBackground());   
              jPanel.  setForeground(list.getSelectionForeground());   
       }   
       else   {   
               //����ѡȡ��ȡ��ѡȡ��ǰ���뱳����ɫ.   
    	   jPanel.  setBackground(list.getBackground());   
    	   jPanel.  setForeground(list.getForeground());   
       }   
		return jPanel;
	}
}
