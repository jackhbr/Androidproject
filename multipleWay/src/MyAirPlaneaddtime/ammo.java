package MyAirPlaneaddtime;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ammo {
	public int x,y,vx,vy=-10;
	private ArrayList<ammo> bl;
	private ImageIcon image = new ImageIcon(this.getClass().getResource("jk.jpg"));
	public ammo(int x,int y,ArrayList<ammo> bl) 
	{
		this.x=x;
		this.y=y;
		this.bl=bl;
	}
	
	public void drawammo(Graphics g)
	{
		Move();
		g.drawImage(image.getImage(),x, y,null);
		
	}
	
	public void Move()
	{
		y=y+vy;
		if(y<=0)  //如果运动出这个范围，就把自己删掉。
		{
			bl.remove(this);
		}
	}
	
}
