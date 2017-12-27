package xiaozedefinal;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MyPlane {
	  private int x=350,y=700,vx,vy;
      private ImageIcon image;
      
      public MyPlane() {
		image=new ImageIcon(this.getClass().getResource("LXPlane.png"));
	}

	

	public void setVx(int vx) {
		this.vx = vx;
	}



	public void setVy(int vy) {
		this.vy = vy;
	}



	public void drawPlane(Graphics g){
    	  g.drawImage(image.getImage(), x, y,null);
    	  x+=vx;
    	  y+=vy;
      }



	public int getX() {
		return x;
	}



	public int getY() {
		return y;
	}
      
}
