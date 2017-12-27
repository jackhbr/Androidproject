package movePicture;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 1.����DrawListener�¼������࣬����ʵ��ActionListener�����¼��ӿں�MouseListener����¼��ӿ�
 * 
 * @author �ܸ�
 * 
 */
public class DrawListener extends MouseAdapter implements ActionListener { // implements
	// MouseListener,MouseMotionListener
	// {
	// 1.3.�����ĸ������������洢���º��ͷŵ�����ֵ��
	private int x1, y1, x2, y2;
	// 1.5.����һ��Graphics2D��������
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
	 * �����ڰ�ť�Ϸ������������ʱִ�д˷���
	 */
	public void actionPerformed(ActionEvent e) {
		str = e.getActionCommand();// ��ȡ��ť�Ķ�������ֵ�����û����ֻ��������ֵ���ȡ��ť�ϵ����֣������ť��û���������ȡ���ַ���""
		System.out.println(str);
		// number = Integer.parseInt(str);// ���ַ���ת��Ϊ������str�д洢�������֣�
		// System.out.println(number);

	}

	/**
	 * �ڴ����Ϸ�����갴������ʱִ�д˷�����
	 */
	public void mousePressed(MouseEvent e) {
		System.out.println("����");
		// 1.4.�ڰ��µķ����л�ȡ��������ֵ��Ȼ�����ͷŷ����еĻ�ȡ�ͷ�����ֵ���ֱ���������С�
		x1 = e.getX();
		y1 = e.getY();
	}

	/**
	 * �����ڴ����Ϸ�����갴�������϶�ʱִ�д˷�����
	 */
	public void mouseDragged(MouseEvent e) {
		//if (number == 3) {
		if(str.equals("Pencil")){
			// 1.4.���϶������еĻ�ȡ�ͷ�����ֵ���ֱ���������С�
			x2 = e.getX();
			y2 = e.getY();
			g.setStroke(new BasicStroke(10));// ʵ����������ϸ��������Ϊ���ʵĴ�ϸ
			// 1.5���ݰ��º��϶�������ֵ����������
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
	}

	/**
	 * �ڴ����Ϸ�����갴���ͷ�ʱִ�д˷�����
	 */
	public void mouseReleased(MouseEvent e) {
		System.out.println("�ͷ�");
		// 1.4.�ڰ��µķ����л�ȡ��������ֵ��Ȼ�����ͷŷ����еĻ�ȡ�ͷ�����ֵ���ֱ���������С�
		x2 = e.getX();
		y2 = e.getY();
		g.setStroke(new BasicStroke(1));// ʵ����������ϸ��������Ϊ���ʵĴ�ϸ
		// 1.6.���ͷŷ����У����ݰ��¡��ͷ�����ֵ�Լ�ѡ���ͼ�Σ�ʹ��Graphics���ʻ��ƶ�Ӧͼ��
		if(str.equals("Line")){
			g.drawLine(x1, y1, x2, y2);// ����ֱ��
		}else if(str.equals("Rect")){
			g.drawRect(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// ���ƾ���
		}else if(str.equals("Oval")){
			g.fillOval(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// �������Բ
		}
		
//		switch (number) {
//		case 0:
//
//			g.drawLine(x1, y1, x2, y2);// ����ֱ��
//			break;
//		case 1:
//
//			g.drawRect(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// ���ƾ���
//			break;
//		case 2:
//			g.fillOval(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// �������Բ
//
//			break;
//		}
	}

	// /**
	// * ������������뵽�¼�Դ������ʱִ�д˷�����
	// */
	// public void mouseEntered(MouseEvent e) {
	// System.out.println("����");
	// }
	//
	// /**
	// * �����������뿪���¼�Դ������ʱִ�д˷�����
	// */
	// public void mouseExited(MouseEvent e) {
	// System.out.println("�뿪");
	// }
	//
	// /**
	// * �ڴ����Ϸ�����갴�����(���º��ͷŵı�����ͬһ��λ����)����ʱִ�д˷���
	// */
	// public void mouseClicked(MouseEvent e) {
	// System.out.println("���");
	// }
}
