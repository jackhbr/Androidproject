package myAirModel2;

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
   public volatile int a[]=new int[2];
	
	
   public void setxy(int a[])
   {
	   this.a=a;
		
   }
   
	
	public void setjf(JFrame jf) //把窗体传到监听器里面去，当然还有他的画笔
	{
		this.jf=jf;
		 g=jf.getGraphics();
		this.jf.requestFocus(); //应该在这里加取得焦点，不在下面加，因为下面是已经运行的了。。
		
	}
	
	 public void keyPressed(KeyEvent e)
	 {
		
		 g.setColor(Color.WHITE);
			g.fillOval(a[0], a[1], 40, 40);
			
			 if ( e.getKeyCode()==KeyEvent.VK_DOWN ){   //注意！！这里的不是e!而是keyevent!!
				 a[1] = a[1]+5;
		        } else if (e.getKeyCode()==KeyEvent.VK_UP){
		        	a[1]=a[1]-5;
		        } else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
		        	a[0] = a[0]+5;
		        } else if (e.getKeyCode()==KeyEvent.VK_LEFT){
		        	a[0]=a[0]-5;
		        } 
			 g.setColor(Color.BLUE);
			g.fillOval(a[0], a[1], 40, 40);
			System.out.println("监听器里的x="+a[0]+"  y="+a[1]);
	 }
	
	
	public void actionPerformed(ActionEvent e)
	{
		
		type=e.getActionCommand();
		if(type.equals("开始游戏"))
		{
			this.jf.requestFocus(); //在每个按钮上都要加上这句话，不然当点击按钮后就不能使焦点回到窗口上
			if(td==null)
			{
			 td=new threaddemo(jf);  //把窗体再传到线程里面去
			// td.setflag(flag);
			// System.out.println("监听器中的窗体宽度"+jf.WIDTH);
			 Thread tk=new Thread(td);
			 tk.start();
			}
			
			
			
			
			System.out.println("开始游戏");
		}
		else if(type.equals("暂停"))
		{
			this.jf.requestFocus(); //在每个按钮上都要加上这句话，不然当点击按钮后就不能使焦点回到窗口上
			flag=true;
			td.setflag(flag);
			System.out.println("暂停");
		}
		else if(type.equals("继续"))
		{
			this.jf.requestFocus(); //在每个按钮上都要加上这句话，不然当点击按钮后就不能使焦点回到窗口上
			flag=false;
			 td.setflag(flag);
			System.out.println("继续");
		}
	}

}
