package first4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;

//生成粒子
public class ParticleControl extends Thread {
//绘制粒子的界面
		private JFrame jf;
		//保存粒子的队列
		private ArrayList<Particle> ps = new ArrayList<Particle>();
		//起始位置
		private int startX = 250, startY = 500;
		public ParticleControl(JFrame jf) {
		this.jf = jf;
		}
		public void setStartXY(int x, int y) {
		this.startX = x;
		this.startY = y;
		}
		public void run() {
		//时间增量
		double dt = 0.1d;
		while (true) {
			//生成粒子放入队列
			Particle tp = new Particle();
			tp.position = new Vec2f(startX, startY);
			tp.velocity = new Vec2f(10, -20);// 方向
			tp.acceleration = sampleDirection();
			tp.life = 400;
			tp.age = 1;
			tp.color = new Color(255, 0, 255);
			tp.size = 12;
			ps.add(tp);
			//将队列中的粒子画到缓冲区，再画到界面上   这里是直接创造图片的办法，而画飞机时是创造一个缓存区
			Image image = jf.createImage(jf.getWidth(), jf.getHeight());
			Graphics bg = image.getGraphics();//注意！！！
			for (int i = 0; i < ps.size(); i++) 
			{
				Particle p = ps.get(i);
				//1.判断粒子的生命是否到期，到期后，从队列移出
				p.age += dt;
					if (p.age >= p.life)
					{
					ps.remove(i);
					System.out.println("****ps remove on****");
					}
				//2.计算每个粒子的下一位置
				int r = p.color.getRGB();
		//返回默认 sRGB ColorModel 中表示颜色的 RGB 值。（24-31 位表示 alpha，16-23 位表示红色，8-15 位表示绿色，0-7 位表示蓝色）。 		
			//	System.out.println("颜色为"+r);//这个值在负12万到负4万之间
				r -= 3000;
				Color c = new Color(r);
				p.color = c;
				p.position = p.position.add(p.velocity.multiply(dt));
				p.velocity = p.velocity.add(p.acceleration.multiply(dt));
				//画到缓冲区
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
		//生成一个随机方向
		public static Vec2f sampleDirection() {
//				double theta = Math.random() * 2 * Math.PI;
//				System.out.println("角度值为"+theta);
//				return new Vec2f((Math.cos(theta)), (Math.sin(theta)));//额，这个也不过是正负参半啊，为啥就能出这种图像呢？额，正负参半都是这样
			double theta = Math.random() * 2 -1;
			System.out.println("角度值为"+theta);
			return new Vec2f(theta,theta );
		
		}
}
