package first4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;

//��������
public class ParticleControl extends Thread {
//�������ӵĽ���
		private JFrame jf;
		//�������ӵĶ���
		private ArrayList<Particle> ps = new ArrayList<Particle>();
		//��ʼλ��
		private int startX = 250, startY = 500;
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
			Particle tp = new Particle();
			tp.position = new Vec2f(startX, startY);
			tp.velocity = new Vec2f(10, -20);// ����
			tp.acceleration = sampleDirection();
			tp.life = 400;
			tp.age = 1;
			tp.color = new Color(255, 0, 255);
			tp.size = 12;
			ps.add(tp);
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
			Thread.sleep(20);
			} catch (Exception ef) {
			}
			}
		}
		//����һ���������
		public static Vec2f sampleDirection() {
//				double theta = Math.random() * 2 * Math.PI;
//				System.out.println("�Ƕ�ֵΪ"+theta);
//				return new Vec2f((Math.cos(theta)), (Math.sin(theta)));//����Ҳ�����������ΰ밡��Ϊɶ���ܳ�����ͼ���أ�������ΰ붼������
			double theta = Math.random() * 2 -1;
			System.out.println("�Ƕ�ֵΪ"+theta);
			return new Vec2f(theta,theta );
		
		}
}
