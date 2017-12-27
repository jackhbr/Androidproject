

import java.awt.Color;
import java.awt.Graphics2D;

public class shape {
	public int x,y,w,h;
	public Color color;
	public shape(int x,int y,int w,int h,Color color){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.color=color;
	}
	public void Draw(Graphics2D g){
		g.setColor(color);
		g.drawOval(x, y, w, h);
	}
}
