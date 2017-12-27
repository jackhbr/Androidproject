package sever23;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.text.StyledDocument;

import sever23.FontAttrib;



public class Manager {

	private ArrayList<Client> client=new ArrayList<Client>();
	StyledDocument doc;
	
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
		
		 doc=messagePane.getStyledDocument();
		 insert(getFontAttrib());//ע��������Ҫ�Ȳ����ı���ʽ������ӵ�jscroll�У���Ȼ�Ͳ�������ʾ
		 
		 JScrollPane jsMessage=new JScrollPane(messagePane);
		 jsMessage.setPreferredSize(new Dimension(200,500));//����̫��ʱ������ʾ�������Ĺ���		
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
		
		Aclistener ac=new Aclistener(client,jList);
		
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
	
	private void insert(FontAttrib attrib) {
		try { // �����ı�
			doc.insertString(doc.getLength(), attrib.getText() + "\n",
					attrib.getAttrSet()
					);
			System.out.println(doc.getLength());//��������������text����ĳ���
			System.out.println(attrib.getText());//����Ҳ������������ı����ݡ�
			System.out.println(attrib.getAttrSet());
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	private FontAttrib getFontAttrib() {
		FontAttrib att = new FontAttrib();
		att.setText("asddasd");
		att.setName("б��");
		att.setSize(12);
		att.setBackColor(new Color(255, 200, 200));
		att.setColor(new Color(0, 0, 255));
		att.setStyle(FontAttrib.ITALIC);
		return att;
	}

}
