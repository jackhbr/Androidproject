package moreball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;

public class threadDemo implements Runnable{
	private Graphics2D g;
	private JFrame jframe;
	public void setjframe(JFrame jframe) {  //传参数的时候先把窗体传到mouselistener，然后再传到
		this.jframe=jframe;
        this.g=(Graphics2D)jframe.getGraphics();
		
	}
	public void run()
	{
		int x[]={2,500,200,50};
		int y[]={600,50,200,100};
		int x1[]={3,3,3,3};
		int y1[]={3,3,3,3};
		
	//	System.out.println(Thread.currentThread().getName());
		//System.out.println(g); //下面这里报错，说是空指针，所以就找这里的对象输出
		//g.drawLine(50, 50, 300, 300);
		g.setColor(Color.BLUE);
		

		while(true)
		{
			for(int i=0;i<4;i++)
			{
				g.fillOval(x[i], y[i], 80, 80);
				if(x[i]<=0 || x[i]>=620)
				{
					x1[i]=-x1[i];
				}
				if(y[i]<=30 || y[i]>=620)
				{
					y1[i]=-y1[i];
				}
				x[i]=x[i]+x1[i];
				y[i]=y[i]+y1[i];
			}
			
			
				
			

				try 
				{
					Thread.sleep(20);
				} 
				catch (InterruptedException e)
				{
					
					e.printStackTrace();
				}
				jframe.paint(g);
				
			
		}
	}

}
