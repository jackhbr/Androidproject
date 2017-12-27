package multipleThreadFractal;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.NumericShaper.Range;
import java.util.Random;

import javax.swing.ImageIcon;

//import sun.java2d.loops.DrawLine;

/**
 * 1.定义DrawListener事件处理类，该类实现ActionListener动作事件接口和MouseListener鼠标事件接口
 * 
 * @author 熊哥
 * 
 */
public class DrawListener extends MouseAdapter implements ActionListener {

	// 1.1.声明一个存储图形的字符串属性
	public String type = " ";
	// 1.3.声明四个变量，用来存储按下和释放的坐标值。
	public int x1, y1, x2, y2;
	// 1.5.声明一个Graphics画笔属性
	public Graphics2D g;

//	private int number = 0;
	public String str = "Line";

	/**
	 * 1.5.然后定义设置画笔属性值的方法。
	 * 
	 * @param g是从DrawMain类的窗体上获取的画笔对象
	 */
	public void setG(Graphics g) {
		this.g = (Graphics2D) g;
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);// 设置画笔抗锯齿
	}

	/**
	 * 1.重写接口中的抽象方法。 该方法也是事件处理方法
	 */
	public void actionPerformed(ActionEvent e) {
		// e.getSource();//获取事件源对象

		// 1.2.在事件处理方法中，获取按钮上的文字信息，存入到图形字符串属性中
		type = e.getActionCommand();// 获取按钮上的文字信息，如果没有文字信息，输出空字符串""
	//	System.out.println("type = " + type);

	}

	/**
	 * 在窗体上发生鼠标按键点击(按下和释放的必须在同一个位置上)动作时执行此方法
	 */
	public void mouseClicked(MouseEvent e) {
		System.out.println("点击");
//		threadDemo kDemo=new threadDemo();
//		kDemo.setthread(g, x1, y1, x2, y2, type);
//		kDemo.start();
	}

	/**
	 * 在窗体上发生鼠标按键按下时执行此方法。
	 */
	public void mousePressed(MouseEvent e) {
		System.out.println("按下");
		// 1.4.在按下的方法中获取按下坐标值，然后在方法方法中的获取释放坐标值，分别存入属性中。
		x1 = e.getX();
		y1 = e.getY();
	}

	public void mouseDragged(MouseEvent e) {
		//if (number == 3) {
		if(type.equals("曲线")){
			// 1.4.在拖动方法中的获取释放坐标值，分别存入属性中。
			g.setColor(Color.BLACK);
			x2 = e.getX();
			y2 = e.getY();
			g.setStroke(new BasicStroke(1));// 实例化线条粗细对象，设置为画笔的粗细
			// 1.5根据按下和拖动的坐标值来绘制曲线
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
		else if(type.equals("刷子")){
			x2 = e.getX();
			y2 = e.getY();
			g.setStroke(new BasicStroke(15));// 实例化线条粗细对象，设置为画笔的粗细
			// 1.5根据按下和拖动的坐标值来绘制曲线
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
			g.setStroke(new BasicStroke(1));
			}
		else if(type.equals("橡皮")){   
			x2 = e.getX();
			y2 = e.getY();
			g.setStroke(new BasicStroke(15));// 实例化线条粗细对象，设置为画笔的粗细
			// 1.5根据按下和拖动的坐标值来绘制曲线
			g.setColor(Color.white);  //橡皮就是改为白色就行了
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(1));
		}
		else if(type.equals("喷枪"))
		{
			
			x2 = e.getX();
			y2 = e.getY();
			g.setColor(Color.black);
			for(int k=0;k<20;k++){  
                Random i=new Random();         
                int a=i.nextInt(8);  
                int b=i.nextInt(10);  
               
                g.drawLine(x2+a, y2+b, x2+a, y2+b);}
//			x1 = x2;
//			y1 = y2;
		}
		
	}
	
	
	
	
	
	/**
	 * 在窗体上发生鼠标按键释放时执行此方法。
	 */
	public void mouseReleased(MouseEvent e) {
		System.out.println("释放");
		// 1.4.在按下的方法中获取按下坐标值，然后在方法方法中的获取释放坐标值，分别存入属性中。
		x2 = e.getX();
		y2 = e.getY();
		
		threadDemo kDemo=new threadDemo();   //可以在多个地方新建这个线程类然然后运行
		kDemo.setthread(g, x1, y1, x2, y2, type);
		kDemo.start();
		
//		else if(type.equals("曲线")){
//			g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));}
		
		
		
	}   

	/**
	 * 当你的鼠标光标进入到事件源对象上时执行此方法。
	 */
	public void mouseEntered(MouseEvent e) {
		
		//System.out.println("进入");
	}

	/**
	 * 当你的鼠标光标离开到事件源对象上时执行此方法。
	 */
	public void mouseExited(MouseEvent e) {
		//System.out.println("离开");
	}
	
	
	//画永恒之心
	
	
	
}
