package planefight;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MyPlane {
	private int x=300,y=450,vx,vy;
	private ImageIcon image;
	
	/**
	 * �����ҵķɻ��ģ���ʼ��ͼƬ����
	 */
	public MyPlane(){
		
		image = new ImageIcon(this.getClass().getResource("GreenPlane.png"));
	}
	/**
	 * ���ҵķɻ�
	 * @param g
	 */
	public void drawPlane(Graphics g){
		g.drawImage(image.getImage(),x, y,null);
		x+=vx;
		y+=vy;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	
}
