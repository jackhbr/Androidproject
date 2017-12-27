package multipleTanBallFinal;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * �ҵ�С����
 * @author chen
 *������ķ����������������ͺܷ����ˣ��򵥵Ĳ�����ʾ�����������Ǻܶ�Ĳ�����ʱ��ͼ򵥶���
 *��Ȼ�������ܸ�ķ�������ķ������ȷ����¼�����ʲô����Ȼ����������࣬Ȼ����Ӧ��Ҫ
 *��ʲô���Ժͷ�����Ȼ��ֱ�����ȥ
 */
public class Ball {
	private int x,y,vx=1,vy=1,d=40;
	private Color color=Color.BLUE;
	private JFrame jf;
	public int flagtimey=0,flagtimex=0;
	
	/**
	 * ������굱ǰ�����λ�û�С��
	 * @param x
	 * @param y
	 */
	public Ball(int x,int y,JFrame jf){  //ʹ�ù��췽�������Σ��ܷ��㣬�Ͳ������Լ�д���κ�����
		this.x = x;
		this.y = y;
		this.jf = jf;
	}
	/**
	 * ��С��ķ���
	 * @param g
	 */
	public void drawBall(Graphics g){//�Ȱ�֮ǰ�Ķ���������Ȼ���˶���Ȼ���ٻ�
		g.setColor(Color.WHITE);
		g.fillOval(x, y, d,d);
		move();
		g.setColor(color);
		g.fillOval(x, y, d,d);
	}
	/**
	 * С���ƶ��ķ���
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
