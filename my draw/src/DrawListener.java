

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
import java.util.Random;

import javax.swing.ImageIcon;

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
		else if(type.equals("��ǹ")){
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
		System.out.println("�ͷ�");
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
//		else if(type.equals("����")){
//			g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));}
		
		
		
	}   

	/**
	 * ������������뵽�¼�Դ������ʱִ�д˷�����
	 */
	public void mouseEntered(MouseEvent e) {
		System.out.println("����");
	}

	/**
	 * �����������뿪���¼�Դ������ʱִ�д˷�����
	 */
	public void mouseExited(MouseEvent e) {
		System.out.println("�뿪");
	}
}
