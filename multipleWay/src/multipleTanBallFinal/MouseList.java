package multipleTanBallFinal;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MouseList extends MouseAdapter implements ActionListener {
	private JFrame jf;
	private Graphics g;
	private ThreadDemo td = null;// ����С���˶����߳�ֻ����һ��
	// ����С�����
	private ArrayList<Ball> listBall = new ArrayList<Ball>();//��������в��������ͺܷ���
	public int x,y,flag;

	public MouseList(JFrame jf) {
		this.jf = jf;

		g = jf.getGraphics();
	}
	
	  public void mouseClicked(MouseEvent e) {
		  x=e.getX();
		  y=e.getY();
		  System.out.println("x="+x+"  y="+y);
		  if(flag==1)
		  {
		  Ball ball = new Ball(x,  y, jf);
			listBall.add(ball);
			flag=0;
		  }
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
		//	Ball ball = new Ball((int) (Math.random() * 500), (int) (Math.random() * 500), jf);
			flag=1;
		} else if ("��ͣ".equals(e.getActionCommand())) {
			td.setFlag(true);
		} else if ("�ָ�".equals(e.getActionCommand())) {
			td.setFlag(false);
		} 
	}

}
