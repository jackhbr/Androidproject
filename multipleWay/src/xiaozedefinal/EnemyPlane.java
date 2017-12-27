package xiaozedefinal;

import java.awt.Graphics;


import javax.swing.ImageIcon;

public class EnemyPlane {
	  private int x,y,vy=1;
	  private ImageIcon image;
	 
	  
	  public EnemyPlane(int x){
		  image=new ImageIcon(this.getClass().getResource("XPlane.png"));
		  this.x=x;
	  }
	   public void drawEnemyPlane(Graphics g){ 
		     g.drawImage(image.getImage(),x,y,null);
		     move();
		     g.drawImage(image.getImage(),x,y,null);
		     
	   }
	   public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void move(){
		   y+=vy;
	   }
	  
	  
}
