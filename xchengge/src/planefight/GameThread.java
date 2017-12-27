package planefight;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class GameThread implements Runnable {
	private MyPlane mp;
	private Graphics g;
	private int x,y;         //±³¾°Í¼Æ¬Î»ÖÃ
	private ImageIcon icon;  //±³¾°Í¼Æ¬

	public GameThread(MyPlane mp, Graphics g) {
		this.mp = mp;
		this.g = g;
		
		icon = new ImageIcon(this.getClass().getResource("±³¾°4.jpg"));
	}

	public void run() {
		while(true){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//»­±³¾°
			g.drawImage(icon.getImage(), x,y,null);
			g.drawImage(icon.getImage(), x,y-icon.getIconHeight(),null);
			y++;
			if(y-icon.getIconHeight()>=0){
				y=0;
			}
			//»­ÎÒµÄ·É»ú
			mp.drawPlane(g);
		}
	}
}
