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
	
	public threaddemo(JFrame jf)  //���մӼ������ﴫ���Ĵ���
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
			
			//������Ϊ���������ʱ����������������߳���ʱ����������ϵ��ڴ��У�Ȼ���ȡ�ľ�����ȷ��ֵ��
			
//			try {                     //�����ʱ����ǰ��Ϳ�����ͣ�ͽ��������ں���Ͳ�������
//				Thread.sleep(100);   //�����ʱ������ǰ�棬�Ǽ�һ��System.out.println("��ʱ��flagΪ"+flag);Ҳ����ʵ����ͣ�ͽ���
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
		//	System.out.println("��ʱ��flagΪ"+flag);
			if(flag==true)
				continue;
		//	System.out.println("��ʱ��flagΪ"+flag);
					count++;
					
					if(count>=8)
					{
					x=(int)(Math.random()*jf.getWidth());
					
					
				//	System.out.println("����Ŀ��Ϊ"+jf.getWidth());
				//	System.out.println("�����xΪ"+x);
					
					
					d=(int)(Math.random()*10+30);
					ball jBall=new ball(jf,x,y,d);
					jBall.setA(bl, g);
					bl.add(jBall);
					count=0;
					}
				   // jBall.x++;
				//	System.out.println(jBall.x);
					
					
				//	System.out.println("������еĳ���Ϊ"+bl.size());
					
					
					for(int i=0;i<bl.size();i++)
					{
						ball ball = bl.get(i);				
						ball.drawBall(g);
					}
				//System.out.println("�߳̽����У�");
				
					try {                     //�����ʱ����ǰ��Ϳ�����ͣ�ͽ��������ں���Ͳ�������
						Thread.sleep(20);   //�����ʱ������ǰ�棬�Ǽ�һ��System.out.println("��ʱ��flagΪ"+flag);Ҳ����ʵ����ͣ�ͽ���
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
		}
		
	}

}
