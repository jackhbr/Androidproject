package first;

import java.awt.Graphics;

import javax.swing.JFrame;

//简单直线
	public class ParticleControl {
	private JFrame jf;
	public ParticleControl(JFrame jf){
	this.jf=jf;
	}
//1.设定一个坐标点
//2.计算200步（即200个坐标）画出
public void draw(){
	int startX=100;//起点
	int startY=300;
	int speed=10;
	//计算移动50步:这里用i作为speed
	for(int i=0;i<50;i++){
	startX+=speed;//计算下一位置
	startY-=speed;
	Graphics g=jf.getGraphics(); //画到界面上
	g.fillOval(startX, startY, 10, 10);
	try{ //间隔20ms画一个 ，就有动态效果
	Thread.sleep(20);
	}catch(Exception ef){}
	}
	}
}