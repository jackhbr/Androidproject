package drawPicture;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 1.����DrawListener�¼������࣬����ʵ��ActionListener�����¼��ӿں�MouseListener����¼��ӿ�
 * 
 * @author �ܸ�
 * 
 */
public class DrawListener implements ActionListener, MouseListener {

	// 1.1.����һ���洢ͼ�ε��ַ�������
	private String type = "Line";
	// 1.3.�����ĸ������������洢���º��ͷŵ�����ֵ��
	private int x1, y1, x2, y2;
	// 1.5.����һ��Graphics��������
	private Graphics g;

	/**
	 * 1.5.Ȼ�������û�������ֵ�ķ�����
	 * @param g�Ǵ�DrawMain��Ĵ����ϻ�ȡ�Ļ��ʶ���
	 */
	public void setG(Graphics g) {
		this.g = g;
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

	/**
	 * �ڴ����Ϸ�����갴���ͷ�ʱִ�д˷�����
	 */
	public void mouseReleased(MouseEvent e) {
		System.out.println("�ͷ�");
		// 1.4.�ڰ��µķ����л�ȡ��������ֵ��Ȼ���ڷ��������еĻ�ȡ�ͷ�����ֵ���ֱ���������С�
		x2 = e.getX();
		y2 = e.getY();

		// 1.6.���ͷŷ����У����ݰ��¡��ͷ�����ֵ�Լ�ѡ���ͼ�Σ�ʹ��Graphics���ʻ��ƶ�Ӧͼ��
		if (type.equals("Line")) {
			g.drawLine(x1, y1, x2, y2);// ����ֱ��
		} else if (type.equals("Rect")) {
			g.drawRect(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// ���ƾ���
			// g.fillOval(x, y, width, height);//�������Բ
		}
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
