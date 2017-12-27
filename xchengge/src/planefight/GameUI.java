package planefight;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameUI implements KeyListener {
	private Graphics g;
	private GameThread gt;  //游戏线程
	private MyPlane mp;     //我的飞机
 
	public void showUI() {
		// 窗体对象
		javax.swing.JFrame jf = new javax.swing.JFrame();
		jf.setTitle("雷电1.0");
		jf.setSize(700, 600);
		// 设置退出进程的方法
		jf.setDefaultCloseOperation(3);
		// 设置居中显示
		jf.setLocationRelativeTo(null);
		
		jf.setVisible(true);
		g = jf.getGraphics();

		jf.addKeyListener(this);

	}
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_A:
			mp.setVx(-5);
			break;
		case KeyEvent.VK_S:
			mp.setVy(5);
			break;
		case KeyEvent.VK_W:
			mp.setVy(-5);
			break;
		case KeyEvent.VK_D:
			mp.setVx(5);
			break;
		case KeyEvent.VK_SPACE:
			//画我的飞机
			mp= new MyPlane();
			//启动游戏线程
			if(gt == null){
				gt = new GameThread(mp,g);
				new Thread(gt).start();
			}
			
			break;
		}
	

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_A:
			mp.setVx(0);
			break;
		case KeyEvent.VK_S:
			mp.setVy(0);
			break;
		case KeyEvent.VK_W:
			mp.setVy(0);
			break;
		case KeyEvent.VK_D:
			mp.setVx(0);
			break;
		}
	}


	public static void main(String[] args) {
		new GameUI().showUI();
	}
}
