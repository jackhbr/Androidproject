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


public class CopyOfManager {

	public  static void main(String[] args) {
	     CopyOfManager ma=new CopyOfManager();
	     ma.mainUI();
	    
	 }
	public void mainUI()
	{
		JFrame jf=new JFrame();	
		jf.setLayout(new BorderLayout());
		jf.setSize(430,330);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		
		JButton login=new JButton("登录");
		jf.add(login,BorderLayout.SOUTH);			
		JPanel jpbgp=new JPanel();		
		ImageIcon img1 = new ImageIcon("D:\\QQ\\images\\bgp.jpg");
		img1.setImage(img1.getImage().getScaledInstance(430, 180, 0));		
		JLabel bgp=new JLabel();		
		//bgp.setIcon(img1);	
	//	bgp.setPreferredSize(new Dimension(430, 180));//确实可以设置区域的大小，但是如果图片不改变大小，图片会被擦掉
		jpbgp.setLayout(new BorderLayout());	
		JTextArea qq=new JTextArea();
		qq.setText("QQ");
		jpbgp.add(bgp);
		jf.add(jpbgp,BorderLayout.NORTH);
		
		
		
		jf.setVisible(true);
		Graphics g=jf.getGraphics();
		g.drawString("QQ", 200,90 );
		
		
		
		
		System.out.println("asd");
	}


}
