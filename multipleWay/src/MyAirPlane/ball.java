package MyAirPlane;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.Blob;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import multipleTanBallFinal.Ball;

public class ball {
	private JFrame jf;
	
	public int x,y,vx,vy=1, d;
	
	private ArrayList<ball> bl;
	
	  private ImageIcon image = new ImageIcon(this.getClass().getResource("PaperPlane_01.png"));
	
	public void setA(ArrayList<ball> bl, Graphics g) {
		this.bl = bl;
	}
	
	public ball(JFrame jf,int x,int y,int d) 
	{
		this.jf=jf;
		this.x=x;
		this.y=y;
		this.d=d;
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

}
