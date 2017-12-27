package fractal;


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
 * 1.����DrawListener�¼������࣬����ʵ��ActionListener�����¼��ӿں�MouseListener����¼��ӿ�
 * 
 * @author �ܸ�
 * 
 */
public class DrawListener extends MouseAdapter implements ActionListener {

	// 1.1.����һ���洢ͼ�ε��ַ�������
	private String type = "Line";
	// 1.3.�����ĸ������������洢���º��ͷŵ�����ֵ��
	private int x1, y1, x2, y2;
	// 1.5.����һ��Graphics��������
	private Graphics2D g;

//	private int number = 0;
	private String str = "Line";

	/**
	 * 1.5.Ȼ�������û�������ֵ�ķ�����
	 * 
	 * @param g�Ǵ�DrawMain��Ĵ����ϻ�ȡ�Ļ��ʶ���
	 */
	public void setG(Graphics g) {
		this.g = (Graphics2D) g;
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);// ���û��ʿ����
	}

	/**
	 * 1.��д�ӿ��еĳ��󷽷��� �÷���Ҳ���¼�������
	 */
	public void actionPerformed(ActionEvent e) {
		// e.getSource();//��ȡ�¼�Դ����

		// 1.2.���¼��������У���ȡ��ť�ϵ�������Ϣ�����뵽ͼ���ַ���������
		type = e.getActionCommand();// ��ȡ��ť�ϵ�������Ϣ�����û��������Ϣ��������ַ���""
		System.out.println("type = " + type);

	}

	/**
	 * �ڴ����Ϸ�����갴�����(���º��ͷŵı�����ͬһ��λ����)����ʱִ�д˷���
	 */
	public void mouseClicked(MouseEvent e) {
		System.out.println("���");
	}

	/**
	 * �ڴ����Ϸ�����갴������ʱִ�д˷�����
	 */
	public void mousePressed(MouseEvent e) {
		System.out.println("����");
		// 1.4.�ڰ��µķ����л�ȡ��������ֵ��Ȼ���ڷ��������еĻ�ȡ�ͷ�����ֵ���ֱ���������С�
		x1 = e.getX();
		y1 = e.getY();
	}

	public void mouseDragged(MouseEvent e) {
		//if (number == 3) {
		if(type.equals("����")){
			// 1.4.���϶������еĻ�ȡ�ͷ�����ֵ���ֱ���������С�
			x2 = e.getX();
			y2 = e.getY();
			g.setStroke(new BasicStroke(1));// ʵ����������ϸ��������Ϊ���ʵĴ�ϸ
			// 1.5���ݰ��º��϶�������ֵ����������
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
		else if(type.equals("ˢ��")){
			x2 = e.getX();
			y2 = e.getY();
			g.setStroke(new BasicStroke(15));// ʵ����������ϸ��������Ϊ���ʵĴ�ϸ
			// 1.5���ݰ��º��϶�������ֵ����������
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
			}
		else if(type.equals("��Ƥ")){   
			x2 = e.getX();
			y2 = e.getY();
			g.setStroke(new BasicStroke(15));// ʵ����������ϸ��������Ϊ���ʵĴ�ϸ
			// 1.5���ݰ��º��϶�������ֵ����������
			g.setColor(Color.white);  //��Ƥ���Ǹ�Ϊ��ɫ������
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;}
		else if(type.equals("��ǹ"))
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
	 * �ڴ����Ϸ�����갴���ͷ�ʱִ�д˷�����
	 */
	public void mouseReleased(MouseEvent e) {
		//System.out.println("�ͷ�");
		// 1.4.�ڰ��µķ����л�ȡ��������ֵ��Ȼ���ڷ��������еĻ�ȡ�ͷ�����ֵ���ֱ���������С�
		x2 = e.getX();
		y2 = e.getY();

		// 1.6.���ͷŷ����У����ݰ��¡��ͷ�����ֵ�Լ�ѡ���ͼ�Σ�ʹ��Graphics���ʻ��ƶ�Ӧͼ��
		
		
		//���ﲻ������switch���ж��ַ�����jdk1.7�汾�ľͿ���,�ҵĿ��Ը�Ϊ1.7������so easy
//		switch(type){
//		case "line":
//		}
		
		if (type.equals("Line")) {
			g.drawLine(x1, y1, x2, y2);// ����ֱ��
		} else if (type.equals("Rect")) {
		//	g.drawRect(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// ���ƾ���
			// g.fillOval(x, y, width, height);//�������Բ
			g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));// ���ƾ���
			
		}else if(type.equals("Oval")){
			g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}else if(type.equals("RoundRect")){
			g.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2),Math.round((float)0.2* Math.abs(x1 - x2)),Math.round((float)0.2* Math.abs(y1 - y2)));}
		 //ע�������������Ĳ���Ҫ��Ϊint�ͣ����������õ���ǿ��ת��������math�����ȡ������
		else if(type.equals("fillOval")){
			g.setColor(Color.BLUE);
			g.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));}
		else if(type.equals("fill3DRect")){
			g.setColor(Color.RED);
			g.fill3DRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2),true);}//û����3dЧ��,��������
	
		else if(type.equals("drawImage")){
			//g.setColor(Color.BLUE);
			Image img = new ImageIcon("img/water.jpg").getImage();//��Ŀ¼��ÿһ��б�ܺ���Ҫ�ټ���һ��б��
			g.drawImage(img, Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2),null);}
		else if(type.equals("text")){
			g.setColor(Color.RED);
			g.drawString("hello!", Math.min(x1, x2), Math.min(y1, y2));}
		
		else if(type.equals("����ͼ��")){
			g.setColor(Color.RED);
			g.drawRect((int)(Math.min(x1, x2)-0.33*Math.abs(x1 - x2)), (int)(Math.min(y1, y2)-0.33* Math.abs(y1 - y2)), Math.abs(x1 - x2), Math.abs(y1 - y2));
			g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));	
			g.drawLine((int)(Math.min(x1, x2)-0.33*Math.abs(x1 - x2))+Math.abs(x1 - x2), (int)(Math.min(y1, y2)-0.33* Math.abs(y1 - y2)),Math.min(x1, x2)+Math.abs(x1 - x2), Math.min(y1, y2));
			g.drawLine((int)(Math.min(x1, x2)-0.33*Math.abs(x1 - x2)), (int)(Math.min(y1, y2)-0.33* Math.abs(y1 - y2))+Math.abs(y1 - y2),Math.min(x1, x2), Math.min(y1, y2)+Math.abs(y1 - y2));
			g.drawLine((int)(Math.min(x1, x2)-0.33*Math.abs(x1 - x2))+Math.abs(x1 - x2), (int)(Math.min(y1, y2)-0.33* Math.abs(y1 - y2))+Math.abs(y1 - y2),Math.min(x1, x2)+Math.abs(x1 - x2), Math.min(y1, y2)+Math.abs(y1 - y2));
			g.drawLine((int)(Math.min(x1, x2)-0.33*Math.abs(x1 - x2)), (int)(Math.min(y1, y2)-0.33* Math.abs(y1 - y2)),Math.min(x1, x2), Math.min(y1, y2));
		}
		
		else if(type.equals("С��������"))
		{			
			g.setColor(Color.BLACK);
			g.drawLine(x2,y2,200,200);
			g.drawLine(200,200,200,400);
			g.drawLine(200,400,x2,y2);
			Random rand=new Random();
			int	x3=0;
			int y3=0;
			for(int j=0;j<10000;j++)
			{
		
			int i=rand.nextInt(3);
		
			switch (i) 
			{
			case 0:{
				g.drawLine((x3+x2)/2,(y3+y2)/2,(x3+x2)/2,(y3+y2)/2);
				x3=(x3+x2)/2;y3=(y3+y2)/2;
			}
				break;
			case 1:
			{
				g.drawLine((200+x3)/2,(200+y3)/2,(200+x3)/2,(200+y3)/2);
				x3=(200+x3)/2;y3=(200+y3)/2;
			}
				break;
			case 2:
			{
				g.drawLine((200+x3)/2,(400+y3)/2,(200+x3)/2,(400+y3)/2);
				x3=(200+x3)/2;y3=(400+y3)/2;
			}
				break;			
			}
			}
               
		}
		else if(type.equals("����֮��"))
		{	
			draw(g);
		}
		else if(type.equals("ͼ��һ"))
		{	
			drawDream(g);
		}
		
		
		
		
//		else if(type.equals("����")){
//			g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));}
		
		
		
	}   

	/**
	 * ������������뵽�¼�Դ������ʱִ�д˷�����
	 */
	public void mouseEntered(MouseEvent e) {
		
		//System.out.println("����");
	}

	/**
	 * �����������뿪���¼�Դ������ʱִ�д˷�����
	 */
	public void mouseExited(MouseEvent e) {
		//System.out.println("�뿪");
	}
	
	
	//������֮��
	private void draw(Graphics2D g)
	{
    	threadDemo kDemo=new threadDemo();
		kDemo.start();
		for(int i=0;i<=180;i++){
		for(int j=0;j<=180;j++){
		double r=Math.PI/45*i*(1-Math.sin(Math.PI/45*j))*20;
		double x=r*Math.cos(Math.PI/45*j)*Math.sin(Math.PI/45*i)+300;
		double y=-r*Math.sin(Math.PI/45*j)+200;
		Color c=Color.getHSBColor(i*j/8100.0f, 0.9999f,0.9999f);
		g.setColor(c);
		g.drawOval((int)x, (int)y, 1,1);
		try
		{
		Thread.sleep(1);
		}
		catch(Exception e)
		{}
		}
		}
		
		
	}
	
	
	private void drawDream(Graphics g){
		double x=0f;
		double y=0f;
		//a,b,c,d��4��������ֵԤ��
		double a=-1.8, b =-2.0, c=-0.5, d=-0.9;
		for(int i=0;i<22500;i++)  //I���ƻ���ʱ�䣬̫��ᵼ�º�����ɫ����Խ��
		{
		//��ʽ��
		double temx= Math.sin(a*y)+c*Math.cos(a*x);
		
		
		double temy=Math.sin(b*x)+d*Math.cos(b*y);
		//��x1,y1ת�ͣ��Ŵ��ƶ�����Ļ����ϵ�� ���ƴ�С������λ��
		int x1= (int)(temx*80+300);
		int y1= (int)(temy*80+300);
		System.out.println("x1: "+x1+" y1: "+y1);
		//��ɫ����������������
		g.setColor(new Color(0,0,i/100));
		g.drawLine(x1, y1, x1,y1);
		x=temx;
		y=temy;
		}
		}
	
	
	
	
}
