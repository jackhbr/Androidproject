package MyAirPlaneaddtime1final1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class threaddemo implements Runnable{
	private JFrame jf;
	private Graphics g;
	
	private int jx,jy;//����ͼƬλ��
	private volatile int b[];
	
	
	private  ArrayList<ball> bl;
	private  ArrayList<ammo> al;
	
	public volatile int a[];
	private ImageIcon image = new ImageIcon(this.getClass().getResource("GreenPlane.png"));
	private ImageIcon icon = new ImageIcon(this.getClass().getResource("����4.jpg"));	
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
	
	public threaddemo(JFrame jf,int a[],ArrayList<ball> bl,ArrayList<ammo> al,int b[])  //���մӼ������ﴫ���Ĵ���,�������ɻ�������
	{
		this.jf=jf;
		this.g=jf.getGraphics();
		this.a=a;
		this.bl=bl;
		this.al=al;
		this.b=b;
	}
	
	public void run()
	{
		while(true)
		{		
			BufferedImage buff=new BufferedImage(jf.getWidth(), jf.getHeight(),BufferedImage.TYPE_INT_RGB);			
			Graphics buffg=buff.getGraphics();
			
			if(b[0]==1)
			{
				buffg.drawImage(icon.getImage(), jx,jy,null);    //�Ȼ�����ͼƬ���ٻ������Ķ�����������ʾ����
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
					{	System.out.println("ʧ�ܣ�����");
					flag=true;}
					
					ball.Back();//��Ϊ�����ػ�״̬�»��кܶ�Σ�����Ҳ���ڲ�������������ĳ��������ӵ��ͷɻ����ǻᶯ��������Ҫ��ǰ�����һ�����صķ���
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
										
				
					buffg.drawImage(icon.getImage(), jx,jy,null);    //�Ȼ�����ͼƬ���ٻ������Ķ�����������ʾ����
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
						
						jAmmo2.drawammo(buffg);
					}
				
					for(int i=0;i<bl.size();i++)
					{
						ball ball = bl.get(i);		
						double distance= Math.sqrt((ball.x-30-a[0])*(ball.x-30-a[0])+(ball.y-30-a[1])*(ball.y-30-a[1]));
						if(distance<=80) 
						{	System.out.println("ʧ�ܣ�����");
						flag=true;}
						
						ball.drawBall(buffg);
					}	
					
				    buffg.drawImage(image.getImage(),a[0], a[1],null);
			    
					g.drawImage(buff, 0, 0, null);
				
					try {                     //�����ʱ����ǰ��Ϳ�����ͣ�ͽ��������ں���Ͳ�������
						Thread.sleep(10);   //�����ʱ������ǰ�棬�Ǽ�һ��System.out.println("��ʱ��flagΪ"+flag);Ҳ����ʵ����ͣ�ͽ���
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
		}
		
	}

}
