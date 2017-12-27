package MyAirPlane;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class threaddemo implements Runnable{
	private int x,y,d;
	private JFrame jf;
	private Graphics g;
	private int count;
	private int countammo;
	
	private int jx,jy;//背景图片位置
	
	private  ArrayList<ball> bl=new ArrayList<ball>();
	
	private  ArrayList<ammo> al=new ArrayList<ammo>();
	
	public volatile int a[];
	private ImageIcon image = new ImageIcon(this.getClass().getResource("GreenPlane.png"));
	private ImageIcon icon = new ImageIcon(this.getClass().getResource("背景4.jpg"));	
	  public volatile boolean flag;
	  public volatile boolean reset;
	  public void setflag(boolean flag) 
	  {
 		this.flag=flag;
    	}
	  public void setreset(boolean flag) 
	  {
 		this.reset=flag;
    	}
	
	public threaddemo(JFrame jf,int a[])  //接收从监听器里传来的窗体,还有主飞机的坐标
	{
		this.jf=jf;
		this.g=jf.getGraphics();
		this.a=a;
		
	}
	
	public void run()
	{
		while(true)
		{		
			BufferedImage buff=new BufferedImage(jf.getWidth(), jf.getHeight(),BufferedImage.TYPE_INT_RGB);			
			Graphics buffg=buff.getGraphics();
			if(flag==true)
				continue;
			if(reset==true)
			{
				reset=false;
				break;
			}
					count++;
					countammo++;					
					if(count>=30)
					{
						x=(int)(Math.random()*jf.getWidth());		
						d=(int)(Math.random()*10+30);
						ball jBall=new ball(jf,x,y,d);
						jBall.setA(bl, buffg);
						bl.add(jBall);
						count=0;
					}	
					buffg.drawImage(icon.getImage(), jx,jy,null);    //先画背景图片，再画其他的东西，否则显示不了
					buffg.drawImage(icon.getImage(), jx,jy-icon.getIconHeight(),null);
					jy++;
					if(jy-icon.getIconHeight()>=0){
						jy=0;
					}
					
					
					if(countammo>=40)
					{
					ammo jAmmo=new ammo(jf, a[0]+45, a[1], 10);
					jAmmo.setA(al, buffg);
					al.add(jAmmo);
					countammo=0;
					}					
					for(int j=0;j<al.size();j++)
					{
						ammo jAmmo2=al.get(j);
						for(int i=0;i<bl.size();i++)
						{
							ball ball = bl.get(i);
							double dist=Math.sqrt((ball.x-jAmmo2.x)*(ball.x-jAmmo2.x)+(ball.y-jAmmo2.y)*(ball.y-jAmmo2.y));
							if(dist<=50)
							{
								al.remove(jAmmo2);
								bl.remove(ball);
							}
						}
						
						jAmmo2.drawammo(buffg);
					}
				
					for(int i=0;i<bl.size();i++)
					{
						ball ball = bl.get(i);		
						double distance= Math.sqrt((ball.x-30-a[0])*(ball.x-30-a[0])+(ball.y-30-a[1])*(ball.y-30-a[1]));
						if(distance<=80) 
						{	System.out.println("失败！！！");
						flag=true;}
						
						ball.drawBall(buffg);
					}					
				buffg.drawImage(image.getImage(),a[0], a[1],null);
			    
					g.drawImage(buff, 0, 0, null);
				
					try {                     //这段延时加在前面就可以暂停和结束，加在后面就不可以了
						Thread.sleep(10);   //如果延时不加在前面，那加一句System.out.println("此时的flag为"+flag);也可以实现暂停和结束
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
		}
		
	}

}
