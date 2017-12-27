package first1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

//简单直线运动:启动一个线程去画
public class ParticleControl extends Thread{
	private JFrame jf;
	private int startX;//起点坐标
	private int startY;
	 
	public ParticleControl(JFrame jf,int startX,int startY){
		this.startX=startX;
		this.startY=startY;
	this.jf=jf;
}
	
//在线程中绘制
public void run(){
		
		int speed=10;
		//计算移动200步:这里用i作为speed
		for(int i=0;i<200;i++){
		startX+=speed;//计算下一位置
		startY-=speed;
		Graphics g=jf.getGraphics(); //画到界面上
		int a=(int) (Math.random()*255);
		 int b=(int) (Math.random()*255);
		 int d=(int) (Math.random()*255);
		Color c=new Color(a,b,d);  //最大为255 设置颜色的变化
		g.setColor(c);
		g.fillOval(startX, startY, 10, 10);
		
try{
		Thread.sleep(20);
		}catch(Exception ef){}
}
}
}