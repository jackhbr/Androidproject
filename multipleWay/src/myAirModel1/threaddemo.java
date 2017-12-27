package myAirModel1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class threaddemo implements Runnable{
	private int x,y,d;
	private JFrame jf;
	private Graphics g;
	private int count;
	private  ArrayList<ball> bl=new ArrayList<ball>();
	
	  public volatile boolean flag;
	  public void setflag(boolean flag) 
	  {
 		this.flag=flag;
    	}
	
	public threaddemo(JFrame jf)  //接收从监听器里传来的窗体
	{
		this.jf=jf;
		this.g=jf.getGraphics();
	}
	
	public void run()
	{
		//g.setColor(Color.BLACK);
	//	g.fillOval(100, 100, 100, 100);
		
		count++;
		
		while(true)
		{
			
			//这是因为加了这个延时或者输出后就让这个线程有时间把数据整合到内存中，然后读取的就是正确的值了
			
//			try {                     //这段延时加在前面就可以暂停和结束，加在后面就不可以了
//				Thread.sleep(100);   //如果延时不加在前面，那加一句System.out.println("此时的flag为"+flag);也可以实现暂停和结束
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
		//	System.out.println("此时的flag为"+flag);
			if(flag==true)
				continue;
		//	System.out.println("此时的flag为"+flag);
					count++;
					
					if(count>=8)
					{
					x=(int)(Math.random()*jf.getWidth());
					
					
				//	System.out.println("窗体的宽度为"+jf.getWidth());
				//	System.out.println("传入的x为"+x);
					
					
					d=(int)(Math.random()*10+30);
					ball jBall=new ball(jf,x,y,d);
					jBall.setA(bl, g);
					bl.add(jBall);
					count=0;
					}
				   // jBall.x++;
				//	System.out.println(jBall.x);
					
					
				//	System.out.println("数组队列的长度为"+bl.size());
					
					
					for(int i=0;i<bl.size();i++)
					{
						ball ball = bl.get(i);				
						ball.drawBall(g);
					}
				//System.out.println("线程进行中！");
				
					try {                     //这段延时加在前面就可以暂停和结束，加在后面就不可以了
						Thread.sleep(20);   //如果延时不加在前面，那加一句System.out.println("此时的flag为"+flag);也可以实现暂停和结束
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
		}
		
	}

}
