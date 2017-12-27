package multipleTanBall;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * �ҵ�С����
 * @author chen
 *
 */
public class Ball {
	private int x,y,vx=3,vy=3,d=40;
	private Color color=Color.BLUE;
	private JFrame jf;
	
	/**
	 * ������굱ǰ�����λ�û�С��
	 * @param x
	 * @param y
	 */
	public Ball(int x,int y,JFrame jf){
		this.x = x;
		this.y = y;
		this.jf = jf;
	}
	/**
	 * ��С��ķ���
	 * @param g
	 */
	public void drawBall(Graphics g){
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
		if(x<=0 || x+d>=jf.getWidth()){
			vx = -vx;
		}
		if(y<=0 || y+d>=jf.getHeight()){
			vy = -vy;
		}
	}
}
