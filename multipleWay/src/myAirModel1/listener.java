package myAirModel1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;


public class listener extends AllAdapt{
	private String type;
	private threaddemo td=null;
   public volatile boolean flag=false;
   public JFrame jf;
   private Graphics g;
   private int x,y;
	
	
	
	public void setjf(JFrame jf) //�Ѵ��崫������������ȥ����Ȼ�������Ļ���
	{
		this.jf=jf;
		 g=jf.getGraphics();
		this.jf.requestFocus(); //Ӧ���������ȡ�ý��㣬��������ӣ���Ϊ�������Ѿ����е��ˡ���
	}
	
	 public void keyPressed(KeyEvent e)
	 {
		
		 g.setColor(Color.WHITE);
			g.fillOval(x, y, 40, 40);
			
			 if ( e.getKeyCode()==KeyEvent.VK_DOWN ){   //ע�⣡������Ĳ���e!����keyevent!!
		            y = y+5;
		        } else if (e.getKeyCode()==KeyEvent.VK_UP){
		            y=y-5;
		        } else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
		            x = x+5;
		        } else if (e.getKeyCode()==KeyEvent.VK_LEFT){
		            x=x-5;
		        } 
			 g.setColor(Color.BLUE);
			g.fillOval(x, y, 40, 40);
	 }
	
	
	public void actionPerformed(ActionEvent e)
	{
		
		type=e.getActionCommand();
		if(type.equals("��ʼ��Ϸ"))
		{
			this.jf.requestFocus(); //��ÿ����ť�϶�Ҫ������仰����Ȼ�������ť��Ͳ���ʹ����ص�������
			if(td==null)
			{
			 td=new threaddemo(jf);  //�Ѵ����ٴ����߳�����ȥ
			// td.setflag(flag);
			// System.out.println("�������еĴ�����"+jf.WIDTH);
			 Thread tk=new Thread(td);
			 tk.start();
			}
			
			
			
			
			System.out.println("��ʼ��Ϸ");
		}
		else if(type.equals("��ͣ"))
		{
			this.jf.requestFocus(); //��ÿ����ť�϶�Ҫ������仰����Ȼ�������ť��Ͳ���ʹ����ص�������
			flag=true;
			td.setflag(flag);
			System.out.println("��ͣ");
		}
		else if(type.equals("����"))
		{
			this.jf.requestFocus(); //��ÿ����ť�϶�Ҫ������仰����Ȼ�������ť��Ͳ���ʹ����ص�������
			flag=false;
			 td.setflag(flag);
			System.out.println("����");
		}
	}

}
