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
		jFrame.setTitle("��ã�");
		jFrame.setSize(400,400);
		jFrame.setDefaultCloseOperation(3);//����ر�ʱ�����˳�
		jFrame.setVisible(true);
		
		
	   g=jFrame.getGraphics();
	   
	   mouse jMouse=new mouse();
	   jFrame.addKeyListener(jMouse);  //��JFrame��Ҳ�ǿ��Ի�������
	   jMouse.setG(g);
	   
//		g.drawOval(100, 100, 100, 100);
//		System.out.println("jack");
		
				
	}

}
