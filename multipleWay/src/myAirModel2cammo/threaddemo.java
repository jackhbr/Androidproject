package myAirModel2cammo;

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
	
	private int jx,jy;//����ͼƬλ��
	
	private  ArrayList<ball> bl=new ArrayList<ball>();
	
	private  ArrayList<ammo> al=new ArrayList<ammo>();
	
	public volatile int a[];
	private ImageIcon image = new ImageIcon(this.getClass().getResource("GreenPlane.png"));
	private ImageIcon icon = new ImageIcon(this.getClass().getResource("����4.jpg"));
	private ImageIcon image1 = new ImageIcon(this.getClass().getResource("jk.jpg"));
	
	
	  public volatile boolean flag;
	  public void setflag(boolean flag) 
	  {
 		this.flag=flag;
    	}
	
	public threaddemo(JFrame jf,int a[])  //���մӼ������ﴫ���Ĵ���,�������ɻ�������
	{
		this.jf=jf;
		this.g=jf.getGraphics();
		this.a=a;
		
	}
	
	public void run()
	{
		//g.setColor(Color.BLACK);
	//	g.fillOval(100, 100, 100, 100);
		
	//	count++;
		
		while(true)
		{
			
			//������Ϊ���������ʱ����������������߳���ʱ����������ϵ��ڴ��У�Ȼ���ȡ�ľ�����ȷ��ֵ��
			
//			try {                     //�����ʱ����ǰ��Ϳ�����ͣ�ͽ��������ں���Ͳ�������
//				Thread.sleep(100);   //�����ʱ������ǰ�棬�Ǽ�һ��System.out.println("��ʱ��flagΪ"+flag);Ҳ����ʵ����ͣ�ͽ���
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			BufferedImage buff=new BufferedImage(jf.getWidth(), jf.getHeight(),BufferedImage.TYPE_INT_RGB);
			
			Graphics buffg=buff.getGraphics();
			
			
		//	System.out.println("��ʱ��flagΪ"+flag);
			if(flag==true)
				continue;
		//	System.out.println("��ʱ��flagΪ"+flag);
					count++;
					countammo++;
					
					if(count>=30)
					{
					x=(int)(Math.random()*jf.getWidth());
					
					
				//	System.out.println("����Ŀ��Ϊ"+jf.getWidth());
				//	System.out.println("�����xΪ"+x);
					
					
					d=(int)(Math.random()*10+30);
					ball jBall=new ball(jf,x,y,d);
					jBall.setA(bl, buffg);
					bl.add(jBall);
					count=0;
					}
					
					

					
					
					buffg.drawImage(icon.getImage(), jx,jy,null);    //�Ȼ�����ͼƬ���ٻ������Ķ�����������ʾ����
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
					
			
				
					
			//	System.out.println("������еĳ���Ϊ"+al.size());
					
					
					for(int i=0;i<bl.size();i++)
					{
						ball ball = bl.get(i);		
						double distance= Math.sqrt((ball.x-30-a[0])*(ball.x-30-a[0])+(ball.y-30-a[1])*(ball.y-30-a[1]));
						if(distance<=80) //���Ի���Ϊ����С��Բ���ж���Ӯ���������Ⱦ͸�
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
