package moreballQiuPeng;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class manager extends JFrame
{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		manager dl=new manager();
		dl.showUI();

	}
	
	public void showUI()
	{
		this.setTitle("�߳�С��");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.setBackground(Color.WHITE);
		
		MouseListener dl=new MouseListener();
		
		String[] array = {"����һ���µ�С��"};

		// ѭ���������飬���������е�Ԫ����ʵ������ť����
		for (int i = 0; i < array.length; i++) {
			JButton button = new JButton(array[i]);
			button.addActionListener(dl);
			button.setPreferredSize(new Dimension(200, 50));//��һ��������y
			this.add(button);// ����ť��ӵ�������
			this.addMouseListener(dl);
		}
		
		
		this.setVisible(true);
		
	//	Graphics g = this.getGraphics();  //������ʾ�ɼ�֮���ٻ�ȡ����
		
		dl.setjframe(this);
		
	}
//	public void paint(Graphics g)
//	{
//		super.paint(g);// super��ʾ����Ķ���
//		
//	}

}
	
