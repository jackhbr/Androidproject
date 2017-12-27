package rankinglist;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.ls.LSException;
/**
 * 该类用于界面
 * @author jack
 *
 */
public class Main extends JFrame{   
	
	 
	private int b[]=new int[2];
	 File f;
	 URI uri;
	    URL url; 
	 /**
	  * 主函数
	  * @param args这个参数不用改
	  */
	public static void main(String[] args) {
		
		Main st=new Main();
		st.showUI();
		st.Music();
	}
	/**
	 * 显示界面的函数
	 */
	public void showUI()
	{		
		this.setTitle("经典飞机大战");
		this.setSize(500, 600);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);//居于中央位置
		this.setDefaultCloseOperation(3);
		listener ls=new listener();
		
		String arr[]={"开始游戏","暂停","继续","排行榜"};		
		Button bks=new Button("开始游戏"); //button的可以一直显示，而jbutton不可以一直显示，
		bks.addActionListener(ls);
		this.add(bks);
		
		for(int i=1;i<arr.length;i++)
		{
			Button bk=new Button(arr[i]); //button的可以一直显示，而jbutton不可以一直显示，
			bk.addActionListener(ls);
			this.add(bk);
			
		}
		
		
		//设置背景色，图片要比较大
		 ImageIcon background = new ImageIcon(this.getClass().getResource("jc.jpg"));  
		 JLabel label = new JLabel(background); 
		 label.setBounds(0, 0, this.getWidth(), this.getHeight());  
		 JPanel imagePanel = (JPanel) this.getContentPane();
		 imagePanel.setOpaque(false);
		 this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
			
		this.addKeyListener(ls);
		this.setVisible(true);

		ls.setjf(this,bks,b);
				
	}
	public void Music()
	{  
	  try {      
	      f = new File("D:\\ring.wav"); 
	      uri = f.toURI();
	      url = uri.toURL();  //解析地址
	      AudioClip aau; 
	      aau = Applet.newAudioClip(url);
	      aau.loop();  //循环播放
	      while(true);
	  } catch (Exception e) 
	  { e.printStackTrace();
	  } 
	}
	public void paint(Graphics g)   //忽然发现，原来你图片下去多少，就重绘多少，而不是全部重绘
	{
		super.paint(g);
		b[0]=1;
	//	ls.setjf(this,bks,stopPaint);这句话感觉应该要，先试试再说，因为每次改变都要传过去，类似于起前面的传参
	}
}
