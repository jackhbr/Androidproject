package rankinglist;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class threaddemo implements Runnable{
	private JFrame jf;
	private Graphics g;
	
	private int jx,jy;//背景图片位置
	private volatile int b[]; //b[0]用于暂停后重绘的标志位，b[1]用于传递分数
	
	private int c[]=new int[4]; //用于排序排行榜里面的三个数,加上存放此时的分数值
	
	
	private  ArrayList<ball> bl;
	private  ArrayList<ammo> al;
	
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
	
	public threaddemo(JFrame jf,int a[],ArrayList<ball> bl,ArrayList<ammo> al,int b[])  //接收从监听器里传来的窗体,还有主飞机的坐标
	{
		this.jf=jf;
		this.g=jf.getGraphics();
		this.a=a;
		this.bl=bl;
		this.al=al;
		this.b=b;
	}
	
	public void obj(String fileName) throws Exception
	{
		FileInputStream fis=new FileInputStream(fileName);
		ObjectInputStream inpu=new ObjectInputStream(fis);  
		Customer inCus;
		try {
			inCus = (Customer)inpu.readObject();
			c[0]=inCus.getFirst();
			c[1]=inCus.getSecond();
			c[2]=inCus.getThird();
			c[3]=b[1];
			System.out.println("c[3]="+c[3]);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		inpu.close();
		
	//	System.out.println("c[0]="+c[0]+"  c[1]="+c[1]+"  c[2]="+c[2]+"   c[3]="+c[3]);
		Arrays.sort(c);//快速排序  ....从小到大的排序
		
		
		
	//	System.out.println("c[0]="+c[0]+"  c[1]="+c[1]+"  c[2]="+c[2]+"   c[3]="+c[3]);
		Customer customer=new Customer();
		customer.setFirst(c[3]);
		customer.setSecond(c[2]);
		customer.setThird(c[1]);
		OutputStream ons=new FileOutputStream(fileName);
		ObjectOutputStream out=new ObjectOutputStream(ons);
		out.writeObject(customer);
		out.flush();
		out.close();
		b[1]=0;
		System.out.println("第一名是 "+c[3]+"第二名是 "+c[2]+" 第三名是 "+c[1]);
		
	}
	
	
	public void run()
	{
		while(true)
		{		
			BufferedImage buff=new BufferedImage(jf.getWidth(), jf.getHeight(),BufferedImage.TYPE_INT_RGB);			
			Graphics buffg=buff.getGraphics();
			
			if(b[0]==1)
			{
				buffg.drawImage(icon.getImage(), jx,jy,null);    //先画背景图片，再画其他的东西，否则显示不了
				buffg.drawImage(icon.getImage(), jx,jy-icon.getIconHeight(),null);
				jy++;
				if(jy-icon.getIconHeight()>=0){
					jy=0;
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
					jAmmo2.Back();
					jAmmo2.drawammo(buffg);
				}
			
				for(int i=0;i<bl.size();i++)
				{
					ball ball = bl.get(i);		
					double distance= Math.sqrt((ball.x-30-a[0])*(ball.x-30-a[0])+(ball.y-30-a[1])*(ball.y-30-a[1]));
					if(distance<=80) 
					{	
					System.out.println("失败！！！");				
					flag=true;
					}
					
					ball.Back();//因为处于重绘状态下会有很多次，所以也是在不断运行这里面的程序，所以子弹和飞机还是会动，所以需要在前面加上一个返回的方法
					ball.drawBall(buffg);
					
				}	
				
			    buffg.drawImage(image.getImage(),a[0], a[1],null);
		    
				g.drawImage(buff, 0, 0, null);
				
				
				b[0]=0;			
			}
			
			if(flag==true)
				continue;
			if(reset==true)
			{
				reset=false;
				break;
			}
										
				
					buffg.drawImage(icon.getImage(), jx,jy,null);    //先画背景图片，再画其他的东西，否则显示不了
					buffg.drawImage(icon.getImage(), jx,jy-icon.getIconHeight(),null);
					jy++;
					if(jy-icon.getIconHeight()>=0){
						jy=0;
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
								b[1]=b[1]+1;  //增加分数
								System.out.println("b[1]="+b[1]);
							}
						}
						
						jAmmo2.drawammo(buffg);
					}
				
					for(int i=0;i<bl.size();i++)
					{
						ball ball = bl.get(i);		
						double distance= Math.sqrt((ball.x-30-a[0])*(ball.x-30-a[0])+(ball.y-30-a[1])*(ball.y-30-a[1]));
						if(distance<=80) 
						{	
							System.out.println("失败！！！");
							
							String file="c:\\Users\\jack\\Desktop\\file.java";//由于gbk的编码会在里面再加上东西，所以以后最好都用utf-8编码，可以重新写一遍路径也可以，自己手写
							
							try {
								obj(file);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						flag=true;
						}
						
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
