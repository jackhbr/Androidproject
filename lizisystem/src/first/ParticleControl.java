package first;

import java.awt.Graphics;

import javax.swing.JFrame;

//��ֱ��
	public class ParticleControl {
	private JFrame jf;
	public ParticleControl(JFrame jf){
	this.jf=jf;
	}
//1.�趨һ�������
//2.����200������200�����꣩����
public void draw(){
	int startX=100;//���
	int startY=300;
	int speed=10;
	//�����ƶ�50��:������i��Ϊspeed
	for(int i=0;i<50;i++){
	startX+=speed;//������һλ��
	startY-=speed;
	Graphics g=jf.getGraphics(); //����������
	g.fillOval(startX, startY, 10, 10);
	try{ //���20ms��һ�� �����ж�̬Ч��
	Thread.sleep(20);
	}catch(Exception ef){}
	}
	}
}