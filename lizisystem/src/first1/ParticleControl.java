package first1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

//��ֱ���˶�:����һ���߳�ȥ��
public class ParticleControl extends Thread{
	private JFrame jf;
	private int startX;//�������
	private int startY;
	 
	public ParticleControl(JFrame jf,int startX,int startY){
		this.startX=startX;
		this.startY=startY;
	this.jf=jf;
}
	
//���߳��л���
public void run(){
		
		int speed=10;
		//�����ƶ�200��:������i��Ϊspeed
		for(int i=0;i<200;i++){
		startX+=speed;//������һλ��
		startY-=speed;
		Graphics g=jf.getGraphics(); //����������
		int a=(int) (Math.random()*255);
		 int b=(int) (Math.random()*255);
		 int d=(int) (Math.random()*255);
		Color c=new Color(a,b,d);  //���Ϊ255 ������ɫ�ı仯
		g.setColor(c);
		g.fillOval(startX, startY, 10, 10);
		
try{
		Thread.sleep(20);
		}catch(Exception ef){}
}
}
}