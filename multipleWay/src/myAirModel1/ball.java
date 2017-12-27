package myAirModel1;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.Blob;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import multipleTanBallFinal.Ball;

public class ball {
	private Graphics g;
	private JFrame jf;
	
	public int x,y,vx,vy=2, d;
	
	private ArrayList<ball> bl;
	
	public void setA(ArrayList<ball> bl, Graphics g) {
		this.bl = bl;
		this.g = g;
	}
	
	public ball(JFrame jf,int x,int y,int d) 
	{
		this.jf=jf;
		this.x=x;
		this.y=y;
		this.d=d;
		g=jf.getGraphics();
	}
	
	public void drawBall(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillOval(x, y, d, d);
		
		Move();
		
		g.setColor(Color.BLUE);
		g.fillOval(x, y, d, d);
		
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
