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

	@Override  //ע�⿴���������˵��������value��model��Ļ�ȡ���������Ķ���
	public Component getListCellRendererComponent(JList<? extends String> list,
			String value, int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		
		JPanel jPanel=new JPanel();
		
      //  JTextField jt=new JTextField();  //������б߿��
            JTextArea jt  =new JTextArea();    //�ı�����ǵ��������֣�û�б߿�
       
        
		if(value !=null)
		{
			//ImageIcon jImage=new ImageIcon("d:\\"+value+".jpg");  
			ImageIcon jImage=new ImageIcon("d:\\jc.jpg"); 
			jImage.setImage(jImage.getImage().getScaledInstance(60, 60, 0));  //����ͼƬ�Ĵ�С
			jt.setText(value);
			 JLabel jLabel=new JLabel(jImage);
			 
			 jPanel.add(jLabel);//ֻ��add���
			jPanel.add(jt);
			
			//jPanel.add(comp)
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
