package addjlist;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Manager {
	
	public  ArrayList<String> name=new ArrayList<String>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager manager=new Manager();
		manager.showUI();
 
	}
	public void showUI()
	{
		for(int i=0;i<8;i++)
		name.add("�������ƽ��");
		
		
		
		JFrame jf=new JFrame();
		jf.setSize(600, 500);
		jf.setTitle("��ӭ����QQ");
		jf.setLocationRelativeTo(null);
		jf.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		jf.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout());
		
		JPanel jp=new JPanel();
		
		jf.add(jp,BorderLayout.CENTER);
		jp.setLayout(new FlowLayout());
		
		
		
		//���jlist�����ö�����;��������������������ͣ�����ͨ����������д���model�У�Ȼ����൱�ڰ�������е�ÿ��Ԫ��ӳ�䵽jlist��
		JList<String> jList=new JList<String>();
		jlistmodel jk=new jlistmodel(name);  //ͨ��model��������д���ȥ������˵model��������Դ
		jList.setModel(jk);   
		
		jlistrender jl=new jlistrender();
		jList.setCellRenderer(jl);
		JScrollPane js=new JScrollPane(jList);  //��Ԫ�رȽ϶��ʱ�������������
		js.setPreferredSize(new Dimension(200,500));//����̫��ʱ������ʾ�������Ĺ���
		jp.add(js);
		
		
		
		
		Aclistener ac=new Aclistener();
		
		
		JButton button = new JButton("����������");
		button.addActionListener(ac);
		panel.add(button);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(ac);
		panel.add(btnNewButton);
		
		JButton button_1 = new JButton("����");
		button_1.addActionListener(ac);
		panel.add(button_1);
		
		JButton button_2 = new JButton("����");
		button_2.addActionListener(ac);
		panel.add(button_2);
		jf.setVisible(true);
		
	}

}
