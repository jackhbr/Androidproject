package client1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Manager {

	public  static void main(String[] args) {
	     Manager ma=new Manager();
	     ma.mainUI();
	    
	 }
	public void mainUI()
	{
		JFrame jf=new JFrame();	
		jf.setLayout(new BorderLayout());
		jf.setSize(430,330);
		jf.setLocationRelativeTo(null);
		
		
		JButton login=new JButton("��¼");
		jf.add(login,BorderLayout.SOUTH);			
		JPanel jpbgp=new JPanel();		
		ImageIcon img1 = new ImageIcon("D:\\QQ\\images\\bgp.jpg");
		img1.setImage(img1.getImage().getScaledInstance(430, 180, 0));		
		JLabel bgp=new JLabel();		
		bgp.setIcon(img1);	
	//	bgp.setPreferredSize(new Dimension(430, 180));//ȷʵ������������Ĵ�С���������ͼƬ���ı��С��ͼƬ�ᱻ����
		jpbgp.setLayout(new BorderLayout());	
		JTextArea qq=new JTextArea();
		qq.setText("QQ");
		jpbgp.add(bgp);
		jf.add(jpbgp,BorderLayout.NORTH);
		
		
		jf.setResizable(false);
		jf.setVisible(true);
		Graphics g=jf.getGraphics();
		//g.drawString("QQ", 300,90 );
		g.drawLine(0, 0, 200, 400);
		//�ػ氡�������ػ�Ͳ�����ʾ�κεĶ����ˣ�����Ϊʲô����ֽ�ͼ������drawstring����������쳣��
		//���Ҫ��ʾ��ͼ�����ֵĻ���Ҫ���̣߳�1.�ö���ͼƬ�����ﲻ��ˢ�£�Ȼ��ͬʱ���߳��ﻭ����
		//2.Ҳ���̣߳����Ŷ�ͼ��������һֱ���߳�����ˢ�£��Ϳ��ԴﵽЧ��
		//�������Ҫ��ͼƬ��������֣��Ǿ�extends jframe��Ȼ����дpaint��������������ַ�������
		//���ûʱ�䣬�����Ժ��ÿհ���Щ����������
		
		System.out.println("asd");
	}


}
