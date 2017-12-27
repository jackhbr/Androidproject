package MyAirPlaneaddtime;

import java.util.ArrayList;

public class ammotimethread implements Runnable{
	private int a[];
	private  ArrayList<ammo> al;
	public  boolean flag;
	  public volatile boolean reset;
	  public void setreset(boolean flag) 
	  {
 		this.reset=flag;
    	}
	public ammotimethread(int a[], ArrayList<ammo> al,boolean flag)
	{
		this.a=a;
		this.al=al;
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
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			    
			
			System.out.println("子弹中的flag="+flag);
			System.out.println("子弹中的+reset="+reset);
			if(flag==true)
				continue;
			if(reset==true)
			{
				reset=false;
				break;
			}
			
	
			
			ammo jAmmo=new ammo(a[0]+45, a[1],al);
			al.add(jAmmo);
			System.out.println("子弹数组队列的长度为"+al.size());
		}
	}

}
