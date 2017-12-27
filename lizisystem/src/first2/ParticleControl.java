package first2;

import java.awt.Graphics;

import javax.swing.JFrame;

public class ParticleControl extends Thread{
		private JFrame jf;
		public ParticleControl(JFrame jf)
		{
		this.jf=jf;
		}
		//1.����һ������ 2.����200������200�����꣩����
		public void run(){
		Vec2f position = new Vec2f(100, 500); //���ӵ���ʼλ������
		Vec2f velocity = new Vec2f(100, -80); //���ӵ��ٶ�����
		Vec2f acceleration = new Vec2f(2,20); //���ӵ���������
		double dt = 0.1d; //ʱ������
		for(int i=0;i<200;i++)
		{//�����ƶ�200��
			position= position.add(velocity.multiply(dt)); //��һλ�ã�����ʽ��
			velocity= velocity.add(acceleration.multiply(dt)); //��һ�ٶȣ�����ʽ��
			//����������
			Graphics g=jf.getGraphics();
			g.fillOval((int)position.x, (int)position.y, 10, 10);
			try{
			Thread.sleep(20);
			}catch(Exception ef){}
		}
		}
}
