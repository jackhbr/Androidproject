package first5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

//��������
public class ParticleControl extends Thread {
//�������ӵĽ���
		private JFrame jf;
		
		private int count;
		
		//�������ӵĶ���
		private ArrayList<Particle> ps = new ArrayList<Particle>();
		//�����µ�����
		private ArrayList<Particle> Mo = new ArrayList<Particle>();
		//��ʼλ��
		private double startX , startY ;
		public ParticleControl(JFrame jf) {
		this.jf = jf;
		}
		public void setStartXY(int x, int y) {
		this.startX = x;
		this.startY = y;
		}
		public void run() {
		//ʱ������
		double dt = 0.1d;
		while (true) {
			//�������ӷ������
			startX=Math.random()*1000;
			startY=800;
			Particle tp = new Particle();
			tp.position = new Vec2f(startX, startY);
			tp.velocity = new Vec2f(0, -100);
			tp.acceleration = new Vec2f(0, 5);
			tp.life = 5;
			tp.age = 1;
			tp.color = new Color(255, 0, 255);
			tp.size = 12;
			count++;
			if(count>=70)
			{
			ps.add(tp);
			count=0;
			}
			//�������е����ӻ������������ٻ���������   ������ֱ�Ӵ���ͼƬ�İ취�������ɻ�ʱ�Ǵ���һ��������
			Image image = jf.createImage(jf.getWidth(), jf.getHeight());
			Graphics bg = image.getGraphics();//ע�⣡����
			for (int i = 0; i < ps.size(); i++) 
			{
				Particle p = ps.get(i);
				//1.�ж����ӵ������Ƿ��ڣ����ں󣬴Ӷ����Ƴ�
				p.age += dt;
					if (p.age >= p.life)
					{
					ps.remove(i);
					newMore(p.getX(), p.getY());
					System.out.println("�����̻�   x������Ϊ"+p.getX()+"   y������Ϊ"+p.getY());
					}
				//2.����ÿ�����ӵ���һλ��
				int r = p.color.getRGB();
		//����Ĭ�� sRGB ColorModel �б�ʾ��ɫ�� RGB ֵ����24-31 λ��ʾ alpha��16-23 λ��ʾ��ɫ��8-15 λ��ʾ��ɫ��0-7 λ��ʾ��ɫ���� 		
			//	System.out.println("��ɫΪ"+r);//���ֵ�ڸ�12�򵽸�4��֮��
				r -= 3000;
				Color c = new Color(r);
				p.color = c;
				p.position = p.position.add(p.velocity.multiply(dt));
				p.velocity = p.velocity.add(p.acceleration.multiply(dt));
				//����������
				bg.setColor(p.color);
				bg.fillOval(p.getX(), p.getY(), p.size, p.size);
			}
			
			
			for (int i = 0; i < Mo.size(); i++) 
			{
				Particle p = Mo.get(i);
				//1.�ж����ӵ������Ƿ��ڣ����ں󣬴Ӷ����Ƴ�
				p.age += dt;
					if (p.age >= p.life)
					{
					Mo.remove(i);
					System.out.println("****ps remove on****");
					}
				//2.����ÿ�����ӵ���һλ��
				int r = p.color.getRGB();
		//����Ĭ�� sRGB ColorModel �б�ʾ��ɫ�� RGB ֵ����24-31 λ��ʾ alpha��16-23 λ��ʾ��ɫ��8-15 λ��ʾ��ɫ��0-7 λ��ʾ��ɫ���� 		
			//	System.out.println("��ɫΪ"+r);//���ֵ�ڸ�12�򵽸�4��֮��
				r -= 3000;
				Color c = new Color(r);
				p.color = c;
				p.position = p.position.add(p.velocity.multiply(dt));
				p.velocity = p.velocity.add(p.acceleration.multiply(dt));
				//����������
				bg.setColor(p.color);
				bg.fillOval(p.getX(), p.getY(), p.size, p.size);
			}
			
			
			jf.getGraphics().drawImage(image, 0, 0, null);
			try {
			Thread.sleep(10);
			} catch (Exception ef) {
			}
			}
		}
		//����һ���������
		public static Vec2f sampleDirection() {	
			return new Vec2f(0,5);
		
		}
		
		public static Vec2f sampleDirectionV() {
			Random kRandom=new Random();
			
			double x=kRandom.nextInt(50)-25;
			double y=kRandom.nextInt(100)-50;
			
			return new Vec2f(x,y);
		
		}
		public void newMore(double x,double y)
		{
			for(int i=0;i<20;i++)
			{
			Particle tp = new Particle();
			tp.position = new Vec2f(x,y);
			tp.velocity = sampleDirectionV();
			tp.acceleration = sampleDirection();
			tp.life = 400;
			tp.age = 1;
			tp.color = new Color(255, 0, 255);
			tp.size = 12;
			Mo.add(tp);
			}
		}
		
}
