package server;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class sever extends JFrame implements Runnable{
	
	BufferedImage bu;
	
	public static void main(String []args) {
		sever sever=new sever();
		sever.showUI();
		Thread th=new Thread(sever);
		th.start();
		while(true);
		
	}
	
	public void run() {
		
	//	while(true)
		//{
		try {
			bu = CreatImage();
			this.getGraphics().drawImage(bu, 0, 0, null);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	}
		
		
	}
	
	public void showUI()
	{
		Dimension im=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(im);  //���Լ��ĵ����н���ֻ���ǲ��ϵ��ص����������ô�С�������Ҫ���óɺ���Ļһ����
		this.setVisible(true);
		
		
		final Graphics g=this.getGraphics(); //ע����������ڲ����е�Ҫʹ�õĻ���Ӧ��Ҫ���ⲿ����
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				int x=e.getX();
				int y=e.getY();
				Color color=new Color(x*y);
				g.setColor(color);
				g.fillOval(x, y, 50, 50);
				
			}
		});
	}
	
	public BufferedImage CreatImage() throws AWTException {
		Dimension im=Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle aRectangle=new Rectangle(im);
		Robot aRobot=new Robot();
		BufferedImage ab=aRobot.createScreenCapture(aRectangle);
		return ab;
	}
	public void paint(Graphics g) {
		super.paint(g);
		try {
			bu = CreatImage();
			this.getGraphics().drawImage(bu, 0, 0, null);
			System.out.println("�ػ���");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
