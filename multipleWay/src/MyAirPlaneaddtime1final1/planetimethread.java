package MyAirPlaneaddtime1final1;

import java.util.ArrayList;

import javax.swing.JFrame;

public class planetimethread implements Runnable {
	
	private  ArrayList<ball> bl;
	private int x,y;
	private  JFrame jf;
	public volatile boolean flag;
	  public volatile boolean reset;
	  public void setreset(boolean flag) 
	  {
 		this.reset=flag;
    	}
	
	public planetimethread(JFrame jf,ArrayList<ball> bl,boolean flag)
	{
		this.jf=jf;
		this.bl=bl;
		this.flag=flag;
	}
	
	  public void setflag(boolean flag) 
	  {
 		this.flag=flag;
    	}
	
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		
			if(flag==true)
				continue;
			if(reset==true)
			{
				reset=false;
				break;
			}
		x=(int)(Math.random()*500);		

		ball jBall=new ball(x,y,bl, jf);
		bl.add(jBall);
		
		}
		
	}

}
