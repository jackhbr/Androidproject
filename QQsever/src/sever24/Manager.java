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
		jf.setTitle("QQ服务器");
		jf.setLocationRelativeTo(null);
		jf.setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel jpeast=new JPanel();		
		jpeast.setLayout(new FlowLayout());	
		JTextPane messagePane=new JTextPane();
		messagePane.setEditable(false);
		
		// doc=messagePane.getStyledDocument();
		// insert(getFontAttrib());//注意这里是要先插入文本样式才能添加到jscroll中，不然就不会有显示
		SimpleAttributeSet attrset = new SimpleAttributeSet(); //网上那位哥是把Java自带的类都写了一遍，其实这个只要用自带的就可以了
        StyleConstants.setFontSize(attrset,24);
        StyleConstants.setItalic(attrset, true);
		Document docs=messagePane.getDocument();
		
		try {
            docs.insertString(docs.getLength(), "聊天记录\n", attrset);//对文本进行追加
        } catch (BadLocationException e) {  //  \n用于换行
            e.printStackTrace();
        }
		
		
		 JScrollPane jsMessage=new JScrollPane(messagePane);
		 jsMessage.setPreferredSize(new Dimension(400,500));//当它太长时，会显示不出他的滚条		
		jpeast.add(jsMessage);
		jf.add(jpeast,BorderLayout.EAST);
		

		JPanel jp=new JPanel();
		jf.add(jp,BorderLayout.WEST);
		jp.setLayout(new FlowLayout());
			
		//这个jlist里面的枚举类型就是数组队列里的数据类型，就是通过把数组队列传入model中，然后就相当于把数组队列的每个元素映射到jlist中
		
		
		JList<Client> jList=new JList<Client>();
		jlistmodel jk=new jlistmodel(client);  //通过model把数组队列传进去，所以说model是数据来源
		
		
		jList.setModel(jk);   
		
		jlistrender jl=new jlistrender();
		jList.setCellRenderer(jl);
		JScrollPane js=new JScrollPane(jList);  //当元素比较多的时候设置这个滚轮
		js.setPreferredSize(new Dimension(200,500));//当它太长时，会显示不出他的滚条
		jp.add(js);
		
		
		JPanel panel = new JPanel();
		jf.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Aclistener ac=new Aclistener(client,jList,docs);
		
		JButton button = new JButton("开启服务器");
		button.addActionListener(ac);
		panel.add(button);

		JButton button_1 = new JButton("禁言");
		button_1.addActionListener(ac);
		panel.add(button_1);
		
		JButton button_2 = new JButton("踢人");
		button_2.addActionListener(ac);
		panel.add(button_2);
		jf.setVisible(true);
	}
	
	
}
