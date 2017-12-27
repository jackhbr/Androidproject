package airpanetModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;

public class threadDemo implements Runnable{
	private Graphics2D g;
	private JFrame jframe;
	int xb=0,yb=0,flag=0;
	public void setjframe(JFrame jframe,int flag) {  //传参数的时候先把窗体传到mouselistener，然后再传到
		this.jframe=jframe;
        this.g=(Graphics2D)jframe.getGraphics();
        this.flag=flag;
        
	}
	public void setxy(JFrame jframe,int xb,int yb,int flag) {
		this.xb=xb;
		this.yb=yb;
		this.jframe=jframe;
        this.g=(Graphics2D)jframe.getGraphics();
        this.flag=flag;
	}
	public void run()
	{
		
	//	System.out.println(Thread.currentThread().getName());
		//System.out.println(g); //下面这里报错，说是空指针，所以就找这里的对象输出
		//g.drawLine(50, 50, 300, 300);
		g.setColor(Color.BLUE);
		switch(flag)
		{
			case 0:
			{
				while(true)
				{
					
					int y=0;
					Random rando=new Random();
					int k=rando.nextInt(600);
					System.out.println(k);
					while(y<=600)
					{
						g.fillOval(k, y, 80, 80);
					    y=y+1;
					
		
						try 
						{
							Thread.sleep(2);
						} 
						catch (InterruptedException e)
						{
							
							e.printStackTrace();
						}
						jframe.paint(g);
						
					}
				}
			}

		
			case 1:
			{
				g.fillOval(xb, yb, 80, 80);
			}
						
			}
	
	}

}
