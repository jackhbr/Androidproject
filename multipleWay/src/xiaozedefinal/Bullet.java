package xiaozedefinal;


import java.awt.Graphics;


import javax.swing.ImageIcon;

public class Bullet {
     private int size=10,x,y,vy=10;
     private ImageIcon image;
     
     public Bullet(int x,int y){
		  image=new ImageIcon(this.getClass().getResource("9(ZRP7~I)6HFKLAIB}MF3LL.png"));
		  this.x=x;
		  this.y=y;
	  }
     
     
     public void drawBullet(Graphics g){
    	 g.drawImage(image.getImage(),x,y,null);
	     move();
	     g.drawImage(image.getImage(),x,y,null);
    	
    	 
     }

	private void move() {
		y-=vy;
		
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getVy() {
		return vy;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
     
}
