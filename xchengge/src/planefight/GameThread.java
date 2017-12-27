package planefight;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class GameThread implements Runnable {
	private MyPlane mp;
	private Graphics g;
	private int x,y;         //����ͼƬλ��
	private ImageIcon icon;  //����ͼƬ

	public GameThread(MyPlane mp, Graphics g) {
		this.mp = mp;
		this.g = g;
		
		icon = new ImageIcon(this.getClass().getResource("����4.jpg"));
	}

	public void run() {
		while(true){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//������
			g.drawImage(icon.getImage(), x,y,null);
			g.drawImage(icon.getImage(), x,y-icon.getIconHeight(),null);
			y++;
			if(y-icon.getIconHeight()>=0){
				y=0;
			}
			//���ҵķɻ�
			mp.drawPlane(g);
		}
	}
}
