

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * ����DrawMain��ͼ�����࣬����̳���JFrame���������Ǵ�����������ࡣ
 */
public class DrawMain extends JFrame {

	public static void main(String[] args) {
		DrawMain dm = new DrawMain();
		dm.initUI();
	}

	private void initUI() {
		// ���ô��������ֵ
		this.setTitle("��ͼ");
		this.setSize(900, 700);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());

		// 2.�ڽ������У�ʵ����DrawListener�¼�������Ķ��󣬶�����Ϊdl��
		DrawListener dl = new DrawListener();

		// �����ַ������飬�����洢��ť�ϵ�������Ϣ
		String[] array = { "Line", "Rect", "Oval","RoundRect","fillOval","fill3DRect","drawImage","text","����ͼ��","����","ˢ��","��Ƥ","��ǹ" };

		// ѭ���������飬���������е�Ԫ����ʵ������ť����
		for (int i = 0; i < array.length; i++) {
			JButton button = new JButton(array[i]);
			button.addActionListener(dl);// 3.�ڽ������У����¼�Դ��ť�������addActionListener()��������������ָ���¼����������Ϊdl��
			this.add(button);// ����ť��ӵ�������
		}
		this.setVisible(true);

		// 3.�ڽ������У����¼�Դ����������addMouseListener()��������������ָ���¼����������Ϊdl��
		this.addMouseListener(dl);
		
		this.addMouseMotionListener(dl);
		// 3.1.�Ӵ����ϻ�ȡGraphics���ʶ���(ע�⣺��ȡ�����ڴ���ɼ�֮���ȡ�������ȡ��Graphics���ʶ���Ϊnull)
		Graphics g = this.getGraphics();
		// 3.2.ͨ���¼�������Ķ���dl,�����ʴ��뵽dl�����g������
		dl.setG(g);
	}
}
