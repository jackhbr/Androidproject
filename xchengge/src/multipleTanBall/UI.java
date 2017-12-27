package multipleTanBall;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UI {

	public void showUI() {
		// 窗体对象
		final javax.swing.JFrame jf = new javax.swing.JFrame();
		jf.setTitle("分行");
		jf.setSize(600, 600);
		// 设置退出进程的方法
		jf.setDefaultCloseOperation(3);
		// 设置居中显示
		jf.setLocationRelativeTo(null);
		 jf.getContentPane().setBackground(Color.WHITE);

		// 流式布局管理器
		java.awt.FlowLayout f = new java.awt.FlowLayout();
		jf.setLayout(f);

		JButton jbu = new JButton("开始");
		jf.add(jbu);
		JButton jbu2 = new JButton("暂停");
		jf.add(jbu2);
		JButton jbu3 = new JButton("恢复");
		jf.add(jbu3);

		jf.setVisible(true);
		final Graphics g = jf.getGraphics();

		MouseList mouse = new MouseList(jf);
		// 匿名内部类
		jbu.addActionListener(mouse);
		jbu3.addActionListener(mouse);
		jbu2.addActionListener(mouse);

	}

	public static void main(String[] args) {
		UI ui = new UI();
		ui.showUI();
	}

}
