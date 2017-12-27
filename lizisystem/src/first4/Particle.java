package first4;

import java.awt.Color;

import first2.Vec2f;

//粒子类
public class Particle {
first4.Vec2f position;
	first4.Vec2f velocity;
	//粒子的起点，速度，重力
	public first4.Vec2f acceleration;
	public Color color;
	public double life;
	public double age;
	public int size;
	//在界面上绘制时的X， Y坐标
	public int x,y;
	public Particle(){}
	public int getX(){
	return (int)this.position.x;
	}
	public int getY(){
	return (int)this.position.y;
	}
	public String toString(){
	return "X:"+position.x+" y: "+position.y;
	}
}
