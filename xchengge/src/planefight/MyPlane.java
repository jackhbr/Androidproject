package planefight;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MyPlane {
	private int x=300,y=450,vx,vy;
	private ImageIcon image;
	
	/**
	 * 创建我的飞机的，初始化图片对象
	 */
	public MyPlane(){
		
		image = new ImageIcon(this.getClass().getResource("GreenPlane.png"));
	}
	/**
	 * 画我的飞机
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
