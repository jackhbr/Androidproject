package multipleTanBall;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UI {

	public void showUI() {
		// �������
		final javax.swing.JFrame jf = new javax.swing.JFrame();
		jf.setTitle("����");
		jf.setSize(600, 600);
		// �����˳����̵ķ���
		jf.setDefaultCloseOperation(3);
		// ���þ�����ʾ
		jf.setLocationRelativeTo(null);
		 jf.getContentPane().setBackground(Color.WHITE);

		// ��ʽ���ֹ�����
		java.awt.FlowLayout f = new java.awt.FlowLayout();
		jf.setLayout(f);

		JButton jbu = new JButton("��ʼ");
		jf.add(jbu);
		JButton jbu2 = new JButton("��ͣ");
		jf.add(jbu2);
		JButton jbu3 = new JButton("�ָ�");
		jf.add(jbu3);

		jf.setVisible(true);
		final Graphics g = jf.getGraphics();

		MouseList mouse = new MouseList(jf);
		// �����ڲ���
		jbu.addActionListener(mouse);
		jbu3.addActionListener(mouse);
		jbu2.addActionListener(mouse);

	}

	public static void main(String[] args) {
		UI ui = new UI();
		ui.showUI();
	}

}
