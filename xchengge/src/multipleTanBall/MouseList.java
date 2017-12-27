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
	// ����С�����
	private ArrayList<Ball> listBall = new ArrayList<Ball>();

	public MouseList(JFrame jf) {
		this.jf = jf;

		g = jf.getGraphics();
	}
	public void actionPerformed(ActionEvent e) {

		if ("��ʼ".equals(e.getActionCommand())) {
			// ����С���˶����߳�ֻ����һ��
			if (td == null) {
				td = new ThreadDemo(listBall, g);
				// �����߳�
				Thread t = new Thread(td);

				t.start();
			}
			// ����С�����
			Ball ball = new Ball((int) (Math.random() * 500), (int) (Math.random() * 500), jf);
			listBall.add(ball);
		} else if ("��ͣ".equals(e.getActionCommand())) {
			td.setFlag(true);
		} else if ("�ָ�".equals(e.getActionCommand())) {
			td.setFlag(false);
		} 
	}

}
