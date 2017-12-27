package sever21;

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

	private ArrayList<Client> client=new ArrayList<Client>();
	public static void main(String[] args) {
			Manager ma=new Manager();
			ma.showUI();
//			Client cli=MySql.getClient("55555");
//			System.out.println(cli);
//			if(cli!=null)
//			System.out.println(cli.getAcount()+cli.getName()+cli.getPassWord());
	}
	
	public void showUI()
	{
		JFrame jf=new JFrame();
		jf.setSize(600, 500);
		jf.setTitle("QQ������");
		jf.setLocationRelativeTo(null);
		jf.setLayout(new BorderLayout(0, 0));
		

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
		
		Aclistener ac=new Aclistener(client);
		
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
