package first3;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class ParticleControl extends Thread{
		private JFrame jf;
		private int r; 
		private int c;
		public ParticleControl(JFrame jf)
		{
		this.jf=jf;
		}
		//1.创建一个粒子 2.计算200步（即200个坐标）画出
		public void run(){
		Vec2f position = new Vec2f(100, 400); //粒子的起始位置向量
		Vec2f velocity = new Vec2f(100, -40); //粒子的速度向量
		Vec2f acceleration = new Vec2f(2,10); //粒子的重力向量
		double dt = 0.1d; //时间间隔量
		for(int i=0;i<50;i++)
		{//计算移动200步
			position= position.add(velocity.multiply(dt)); //下一位置（见公式）
			velocity= velocity.add(acceleration.multiply(dt)); //下一速度（见公式）
			//画到界面上
			Graphics g=jf.getGraphics();
			Color dfColor=new Color(0,0,c);
			if(c<=250)
			{
				c=c+5;
			}
			else {
				c=0;
			}
			g.setColor(dfColor);
			g.fillOval((int)position.x, (int)position.y, r, r);
			r++;
			try{
			Thread.sleep(20);
			}catch(Exception ef){}
		}
		}
}
