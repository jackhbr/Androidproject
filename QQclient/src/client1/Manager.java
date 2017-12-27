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
		
		
		JButton login=new JButton("登录");
		jf.add(login,BorderLayout.SOUTH);			
		JPanel jpbgp=new JPanel();		
		ImageIcon img1 = new ImageIcon("D:\\QQ\\images\\bgp.jpg");
		img1.setImage(img1.getImage().getScaledInstance(430, 180, 0));		
		JLabel bgp=new JLabel();		
		bgp.setIcon(img1);	
	//	bgp.setPreferredSize(new Dimension(430, 180));//确实可以设置区域的大小，但是如果图片不改变大小，图片会被擦掉
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
		//重绘啊，这里重绘就不会显示任何的东西了，具体为什么会出现截图，就是drawstring函数里面的异常了
		//如果要显示动图加文字的话就要用线程，1.用多张图片在这里不断刷新，然后同时在线程里画文字
		//2.也用线程，用张动图，和文字一直在线程里面刷新，就可以达到效果
		//如果单独要在图片后面加文字，那就extends jframe，然后重写paint方法，里面加上字符串就行
		//最近没时间，所以以后用空把这些东西都试试
		
		System.out.println("asd");
	}


}
