package multipleTanBallFinal;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * 我的小球类
 * @author chen
 *运用类的方法，操作起球来就很方便了，简单的操作显示不出来，但是很多的操作的时候就简单多了
 *忽然回忆起熊哥的分析问题的方法，先分析事件中有什么对象，然后建立对象的类，然后看它应该要
 *有什么属性和方法，然后分别定义上去
 */
public class Ball {
	private int x,y,vx=1,vy=1,d=40;
	private Color color=Color.BLUE;
	private JFrame jf;
	public int flagtimey=0,flagtimex=0;
	
	/**
	 * 根据鼠标当前点击的位置画小球
	 * @param x
	 * @param y
	 */
	public Ball(int x,int y,JFrame jf){  //使用构造方法来传参，很方便，就不用再自己写传参函数了
		this.x = x;
		this.y = y;
		this.jf = jf;
	}
	/**
	 * 画小球的方法
	 * @param g
	 */
	public void drawBall(Graphics g){//先把之前的东西擦掉，然后运动，然后再画
		g.setColor(Color.WHITE);
		g.fillOval(x, y, d,d);
		move();
		g.setColor(color);
		g.fillOval(x, y, d,d);
	}
	/**
	 * 小球移动的方法
	 */
	public void move(){
		x+=vx;
		y+=vy;
		if(x<0 || x+d>jf.getWidth()){
			vx = -vx;
			flagtimex++;
		}
		if(y<0 || y+d>jf.getHeight()){
			vy = -vy;
			flagtimey++;
		}
		if(flagtimex>=2)
		{
			if(x<0)
			{
				x=x+3*Math.abs(vx);
			}
			if(x+d>jf.getWidth())
			{
				x=x-3*Math.abs(vx);
			}
			flagtimex=0;
		}
		
		if(flagtimey>=2)
		{
			if(y<0)
			{
				y=y+3*Math.abs(vy);
			}
			if(y+d>jf.getHeight())
			{
				y=y-3*Math.abs(vy);
			}
			flagtimey=0;
		}
		
	}
	
	public void exchangeWay(Ball ball)
	{
		double distance=Math.sqrt(Math.abs(this.x-ball.x)*Math.abs(this.x-ball.x)+Math.abs(this.y-ball.y)*Math.abs(this.y-ball.y));
		if(distance<=d)
		{
			int tempx=0,tempy=0;
			tempx=this.vx;
			tempy=this.vy;
			this.vx=ball.vx;
			this.vy=ball.vy;
			ball.vx=tempx;
			ball.vy=tempy;
		}	
//		if(distance<=0.75*d)
//		{
//			this.x=this.x+10;
//		}
		
		
		
	}
	
}
