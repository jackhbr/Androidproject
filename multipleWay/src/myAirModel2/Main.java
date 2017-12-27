package myAirModel2;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.ls.LSException;

public class Main extends JFrame{

	/**
	 * @param args
	 */
	 int a[]={250,500};
	 listener ls=new listener();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main st=new Main();
		st.showUI();

	}
	public void showUI()
	{
		//JFrame jFrame=new JFrame();
		this.setTitle("经典飞机大战");
		this.setSize(500, 600);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);//居于中央位置
		this.setDefaultCloseOperation(3);
		
		 
		
		String arr[]={"开始游戏","暂停","继续"};
		for(int i=0;i<arr.length;i++)
		{
			Button bk=new Button(arr[i]);
			bk.addActionListener(ls);
			this.add(bk);
			
		}
		
		this.getContentPane().setBackground(Color.WHITE);  //居然设置不了背景色？？？？
		//这个窗体的先获取他的层才可以设置背景颜色，其他的元件就可以直接加背景颜色
		
	
		this.addKeyListener(ls);
		this.setVisible(true);
		
		ls.setjf(this);//把窗体传到监听器里面去，当然还有他的画笔
	//	 System.out.println("main中的窗体宽度"+jFrame.getWidth());   getwidth()和width的返回值是不一样的！
		//getwidth()是真的宽度，width就是1.。。。。。
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		ls.setxy(a);
		g.setColor(Color.BLACK);
		g.fillOval(a[0], a[1], 40, 40);
		
		
		System.out.println("重绘里的x="+a[0]+"  y="+a[1]);
	}

}
