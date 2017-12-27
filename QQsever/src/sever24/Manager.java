package sever24;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;



public class Manager {

	private ArrayList<Client> client=new ArrayList<Client>();
	//StyledDocument doc;
	
	public static void main(String[] args) {
			Manager ma=new Manager();
			ma.showUI();
	}
	
	public void showUI()
	{
		JFrame jf=new JFrame();
		jf.setSize(600, 500);
		jf.setTitle("QQ������");
		jf.setLocationRelativeTo(null);
		jf.setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel jpeast=new JPanel();		
		jpeast.setLayout(new FlowLayout());	
		JTextPane messagePane=new JTextPane();
		messagePane.setEditable(false);
		
		// doc=messagePane.getStyledDocument();
		// insert(getFontAttrib());//ע��������Ҫ�Ȳ����ı���ʽ������ӵ�jscroll�У���Ȼ�Ͳ�������ʾ
		SimpleAttributeSet attrset = new SimpleAttributeSet(); //������λ���ǰ�Java�Դ����඼д��һ�飬��ʵ���ֻҪ���Դ��ľͿ�����
        StyleConstants.setFontSize(attrset,24);
        StyleConstants.setItalic(attrset, true);
		Document docs=messagePane.getDocument();
		
		try {
            docs.insertString(docs.getLength(), "�����¼\n", attrset);//���ı�����׷��
        } catch (BadLocationException e) {  //  \n���ڻ���
            e.printStackTrace();
        }
		
		
		 JScrollPane jsMessage=new JScrollPane(messagePane);
		 jsMessage.setPreferredSize(new Dimension(400,500));//����̫��ʱ������ʾ�������Ĺ���		
		jpeast.add(jsMessage);
		jf.add(jpeast,BorderLayout.EAST);
		

		JPanel jp=new JPanel();
		jf.add(jp,BorderLayout.WEST);
		jp.setLayout(new FlowLayout());
			
		//���jlist�����ö�����;��������������������ͣ�����ͨ����������д���model�У�Ȼ����൱�ڰ�������е�ÿ��Ԫ��ӳ�䵽jlist��
		
		
		JList<Client> jList=new JList<Client>();
		jlistmodel jk=new jlistmodel(client);  //ͨ��model��������д���ȥ������˵model��������Դ
		
		
		jList.setModel(jk);   
		
		jlistrender jl=new jlistrender();
		jList.setCellRenderer(jl);
		JScrollPane js=new JScrollPane(jList);  //��Ԫ�رȽ϶��ʱ�������������
		js.setPreferredSize(new Dimension(200,500));//����̫��ʱ������ʾ�������Ĺ���
		jp.add(js);
		
		
		JPanel panel = new JPanel();
		jf.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Aclistener ac=new Aclistener(client,jList,docs);
		
		JButton button = new JButton("����������");
		button.addActionListener(ac);
		panel.add(button);

		JButton button_1 = new JButton("����");
		button_1.addActionListener(ac);
		panel.add(button_1);
		
		JButton button_2 = new JButton("����");
		button_2.addActionListener(ac);
		panel.add(button_2);
		jf.setVisible(true);
	}
	
	
}
