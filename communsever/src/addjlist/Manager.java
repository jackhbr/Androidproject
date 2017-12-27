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
		name.add("就是如此平凡");
		
		
		
		JFrame jf=new JFrame();
		jf.setSize(600, 500);
		jf.setTitle("欢迎来到QQ");
		jf.setLocationRelativeTo(null);
		jf.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		jf.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout());
		
		JPanel jp=new JPanel();
		
		jf.add(jp,BorderLayout.CENTER);
		jp.setLayout(new FlowLayout());
		
		
		
		//这个jlist里面的枚举类型就是数组队列里的数据类型，就是通过把数组队列传入model中，然后就相当于把数组队列的每个元素映射到jlist中
		JList<String> jList=new JList<String>();
		jlistmodel jk=new jlistmodel(name);  //通过model把数组队列传进去，所以说model是数据来源
		jList.setModel(jk);   
		
		jlistrender jl=new jlistrender();
		jList.setCellRenderer(jl);
		JScrollPane js=new JScrollPane(jList);  //当元素比较多的时候设置这个滚轮
		js.setPreferredSize(new Dimension(200,500));//当它太长时，会显示不出他的滚条
		jp.add(js);
		
		
		
		
		Aclistener ac=new Aclistener();
		
		
		JButton button = new JButton("开启服务器");
		button.addActionListener(ac);
		panel.add(button);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(ac);
		panel.add(btnNewButton);
		
		JButton button_1 = new JButton("禁言");
		button_1.addActionListener(ac);
		panel.add(button_1);
		
		JButton button_2 = new JButton("踢人");
		button_2.addActionListener(ac);
		panel.add(button_2);
		jf.setVisible(true);
		
	}

}
