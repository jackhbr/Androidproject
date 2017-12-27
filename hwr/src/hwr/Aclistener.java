package hwr;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Aclistener extends AllAdapt{  //两个窗口是可以共用一个事件监听类的！！！
	
	private JFrame jf;
	private int x1,x2,y1,y2;//x1 y1是按下时的坐标，x2 y2是拖动时的坐标
	
	public void setPa(JFrame jf)
	{
		this.jf=jf;
	}
	
	 public void actionPerformed(ActionEvent e)
	 {
		 String type=e.getActionCommand();
		 if(type.equals("写入"))
		 {
			 System.out.println("写入");
			 JFrame jk=new JFrame();//不能用jf，但是jf还是要传过来
			 jk.setTitle("手写识别板");
			 jk.setSize(400, 400);
			 jk.setDefaultCloseOperation(2);
			 jk.setLocationRelativeTo(null);
			 jk.addMouseMotionListener(this); //两个窗体共用一个事件监听类
			 jk.addMouseListener(this);//拖动和按下的两个监听
			 jk.setVisible(true);
		 }
	 }
	 public void mouseDragged(MouseEvent e)
	 {
		 x2=e.getX();
		 y2=e.getY();
		 System.out.println("拖动时的x="+x2+"  y="+y2);
	 }
	 public void mousePressed(MouseEvent e)   //注意窗体最外面的边框也算在窗体大小内，但是鼠标点击这里时却不会引发事件
	 {
		 x1=e.getX();
		 y1=e.getY();
		 System.out.println("按下时的x="+x1+"  y="+y1);
	 }

}
