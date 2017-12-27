package MyAirPlanebuttonchange;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import myAirModel1.ball;

public class ammo {

	//private Graphics g;
	private JFrame jf;
	
	public int x,y,vx,vy=-10, d;
	
	private ArrayList<ammo> bl;
	private ImageIcon image = new ImageIcon(this.getClass().getResource("jk.jpg"));
	
	public void setA(ArrayList<ammo> bl, Graphics g) {
		this.bl = bl;
	//	this.g = g;
	}
	
	public ammo(JFrame jf,int x,int y,int d) 
	{
		//this.jf=jf;
		this.x=x;
		this.y=y;
		this.d=d;
		//g=jf.getGraphics();
	}
	
	public void drawammo(Graphics g)
	{
//		g.setColor(Color.WHITE);
//		g.fillOval(x, y, d, d);
		
		Move();
		
		//g.setColor(Color.BLUE);
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
