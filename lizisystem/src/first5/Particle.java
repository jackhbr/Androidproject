package first5;

import java.awt.Color;

import first2.Vec2f;

//������
public class Particle {

	//���ӵ���㣬�ٶȣ�����
	public first5.Vec2f position,velocity,acceleration;
	public Color color;
	public double life;
	public double age;
	public int size;
	//�ڽ����ϻ���ʱ��X�� Y����
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
