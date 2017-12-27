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
		//1.����һ������ 2.����200������200�����꣩����
		public void run(){
		Vec2f position = new Vec2f(100, 400); //���ӵ���ʼλ������
		Vec2f velocity = new Vec2f(100, -40); //���ӵ��ٶ�����
		Vec2f acceleration = new Vec2f(2,10); //���ӵ���������
		double dt = 0.1d; //ʱ������
		for(int i=0;i<50;i++)
		{//�����ƶ�200��
			position= position.add(velocity.multiply(dt)); //��һλ�ã�����ʽ��
			velocity= velocity.add(acceleration.multiply(dt)); //��һ�ٶȣ�����ʽ��
			//����������
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
