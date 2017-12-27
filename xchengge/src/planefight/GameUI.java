package planefight;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameUI implements KeyListener {
	private Graphics g;
	private GameThread gt;  //��Ϸ�߳�
	private MyPlane mp;     //�ҵķɻ�
 
	public void showUI() {
		// �������
		javax.swing.JFrame jf = new javax.swing.JFrame();
		jf.setTitle("�׵�1.0");
		jf.setSize(700, 600);
		// �����˳����̵ķ���
		jf.setDefaultCloseOperation(3);
		// ���þ�����ʾ
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
			//���ҵķɻ�
			mp= new MyPlane();
			//������Ϸ�߳�
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
