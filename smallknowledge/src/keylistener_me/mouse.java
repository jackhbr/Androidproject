package keylistener_me;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
public class mouse extends MouseAdapter {
	public Graphics g;
	
	public void setG(Graphics g) {
		this.g=g;
		
	}
	public void mouseClicked(MouseEvent e) 
	{
		g.drawLine(100, 100, 200, 200);
	}
	

}
*/

public class mouse extends KeyAdapter {
	public Graphics g;
	public int x,y;
	
	public void setG(Graphics g) {
		this.g=g;
		
	}
	public void keyPressed(KeyEvent e)  //ֻҪ�а������¾ͻ�ִ������������������ĸ�����Ҫ�Լ���������ж�
	{
		g.setColor(Color.WHITE);
		g.fillOval(x, y, 40, 40);
		
		 if ( e.getKeyCode()==KeyEvent.VK_DOWN ){   //ע�⣡������Ĳ���e!����keyevent!!
	            y = y+5;
	        } else if (e.getKeyCode()==KeyEvent.VK_UP){
	            y=y-5;
	        } else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
	            x = x+5;
	        } else if (e.getKeyCode()==KeyEvent.VK_LEFT){
	            x=x-5;
	        } 
		 g.setColor(Color.BLUE);
		g.fillOval(x, y, 40, 40);
	}
	

}