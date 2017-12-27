package oneBall;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		int x=100,y=100,x1=200,y1=400,x2=20,y2=500;
		int flag=0,flag1=0,flag2=0,flag3=0,flag4=0,flag5=0;
	//	System.out.println(Thread.currentThread().getName());
		//System.out.println(g); //下面这里报错，说是空指针，所以就找这里的对象输出
		//g.drawLine(50, 50, 300, 300);
		g.setColor(Color.BLUE);
		
	//	g.setColor(Color.BLUE);
		//g.fillOval(x, y, 50, 50);
		while(true)
		{
			g.fillOval(0, 700, 80, 80);
			g.fillOval(700, 700, 80, 80);
			g.fillOval(700, 0, 80, 80);
			g.fillOval(0, 0, 80, 80);
			
			
		g.fillOval(x, y, 80, 80);
		g.fillOval(x1, y1, 80, 80);
		g.fillOval(x2, y2, 80, 80);
		System.out.println("x="+x+"  y="+y);
		System.out.println("x1="+x1+"  y1="+y1);
		System.out.println("x2="+x2+"  y2="+y2);
		if(x>=300)
		{
			flag=1;
		}
		if(x1>=300)
		{
			flag1=1;
		}
		if(x2>=300)
		{
			flag2=1;
		}
		if(y>=300)
		{
			flag3=1;
		}
		if(y1>=300)
		{
			flag4=1;
		}
		if(y2>=300)
		{
			flag5=1;
		}
		
		
		
		if(x<=0)
		{
			flag=0;
		}
		if(x1<=0)
		{
			flag1=0;
		}
		if(x2<=0)
		{
			flag2=0;
		}
		if(y<=0)
		{
			flag3=0;
		}
		if(y1<=0)
		{
			flag4=0;
		}
		if(y2<=0)
		{
			flag5=0;
		}
		
		
		if(flag==0)
		{
			x++;
		}
		else {
			x--;
		}
		
		if(flag1==0)
		{
			x1++;
		}
		else {
			x1--;
		}
		
		if(flag2==0)
		{
			x2++;
		}
		else {
			x2--;
		}
		
		if(flag3==0)
		{
			y++;
		}
		else {
			y--;
		}
		
		if(flag4==0)
		{
			y1++;
		}
		else {
			y1--;
		}
		
		if(flag5==0)
		{
			y2++;
		}
		
		
		
		
		
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jframe.paint(g);
		
		}
	}

}
