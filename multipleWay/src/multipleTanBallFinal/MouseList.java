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
	private ThreadDemo td = null;// 控制小球运动的线程只启动一次
	// 保存小球对象
	private ArrayList<Ball> listBall = new ArrayList<Ball>();//用数组队列操作起来就很方便
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

		if ("开始".equals(e.getActionCommand())) {
			// 控制小球运动的线程只启动一次
			if (td == null) {
				td = new ThreadDemo(listBall, g);
				// 启动线程
				Thread t = new Thread(td);

				t.start();
			}
			// 创建小球对象
		//	Ball ball = new Ball((int) (Math.random() * 500), (int) (Math.random() * 500), jf);
			flag=1;
		} else if ("暂停".equals(e.getActionCommand())) {
			td.setFlag(true);
		} else if ("恢复".equals(e.getActionCommand())) {
			td.setFlag(false);
		} 
	}

}
