package multipleTanBall;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MouseList implements ActionListener {
	private JFrame jf;
	private Graphics g;
	private ThreadDemo td = null;
	// 保存小球对象
	private ArrayList<Ball> listBall = new ArrayList<Ball>();

	public MouseList(JFrame jf) {
		this.jf = jf;

		g = jf.getGraphics();
	}
	public void actionPerformed(ActionEvent e) {

		if ("开始".equals(e.getActionCommand())) {
			// 控制小球运动的线程只启动一次
			if (td == null) {
				td = new ThreadDemo(listBall, g);
				// 启动线程
				Thread t = new Thread(td);

				t.start();
			}
			// 创建小球对象
			Ball ball = new Ball((int) (Math.random() * 500), (int) (Math.random() * 500), jf);
			listBall.add(ball);
		} else if ("暂停".equals(e.getActionCommand())) {
			td.setFlag(true);
		} else if ("恢复".equals(e.getActionCommand())) {
			td.setFlag(false);
		} 
	}

}
