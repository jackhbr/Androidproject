package keylistener_me;

import java.awt.Graphics;

import javax.swing.JFrame;

import org.omg.CORBA.PRIVATE_MEMBER;


public class demo {
	
	public Graphics g;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		demo dlDemo=new demo();
		dlDemo.showUI();
	}
	public void showUI() 
	{
		JFrame jFrame=new JFrame();
		jFrame.setTitle("你好！");
		jFrame.setSize(400,400);
		jFrame.setDefaultCloseOperation(3);//窗体关闭时程序退出
		jFrame.setVisible(true);
		
		
	   g=jFrame.getGraphics();
	   
	   mouse jMouse=new mouse();
	   jFrame.addKeyListener(jMouse);  //再JFrame中也是可以画东西的
	   jMouse.setG(g);
	   
//		g.drawOval(100, 100, 100, 100);
//		System.out.println("jack");
		
				
	}

}
