package MyAirPlaneaddtime1final1;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ball {
	private JFrame jf;
	
	public int x,y,vx,vy=1;
	
	private ArrayList<ball> bl;
	
	private ImageIcon image = new ImageIcon(this.getClass().getResource("PaperPlane_01.png"));	
	public ball(int x,int y,ArrayList<ball> bl, JFrame jf) 
	{
		this.x=x;
		this.y=y;
		this.jf=jf;
		this.bl = bl;
	}
	
	public void drawBall(Graphics g)
	{
		Move();
		g.drawImage(image.getImage(),x, y,null);
		
	}
	
	public void Move()
	{
		y=y+vy;
		if(y>=jf.getHeight())  //如果运动出这个范围，就把自己删掉。
		{
			bl.remove(this);
		}
	}
	
	public void Back()
	{
		y=y-vy;
		
	}

}
