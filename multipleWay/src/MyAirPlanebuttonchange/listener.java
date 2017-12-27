package MyAirPlanebuttonchange;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class listener extends AllAdapt{
	private String type;
	private threaddemo td=null;
   public volatile boolean flag=false;
   public volatile boolean reset=false;
   public JFrame jf;
   private Graphics g;
   public volatile int a[]=new int[2];
   private int countstart=0;
   private Button bks;
  // private ImageIcon image = new ImageIcon(this.getClass().getResource("GreenPlane.png"));
	
	
   public void setxy(int a[])   //主飞机的坐标的传递
   {
	   this.a=a;
		
   }
   
	
	public void setjf(JFrame jf,Button bks) //把窗体传到监听器里面去，当然还有他的画笔
	{
		this.jf=jf;
		 g=jf.getGraphics();
		 this.bks=bks;
	//	this.jf.requestFocus(); //应该在这里加取得焦点，不在下面加，因为下面是已经运行的了。。
		
	}
	
	 public void keyPressed(KeyEvent e)
	 {
			
			 if ( e.getKeyCode()==KeyEvent.VK_DOWN ){   //注意！！这里的不是e!而是keyevent!!
				 a[1] = a[1]+3;
		        } else if (e.getKeyCode()==KeyEvent.VK_UP){
		        	a[1]=a[1]-3;
		        } else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
		        	a[0] = a[0]+3;
		        } else if (e.getKeyCode()==KeyEvent.VK_LEFT){
		        	a[0]=a[0]-3;
		        } 	
	 }	
	public void actionPerformed(ActionEvent e)
	{
	
		type=e.getActionCommand();
		if(type.equals("开始游戏"))
		{
			bks.setLabel("重新开始");
			//countstart++;		
			this.jf.requestFocus();
			if(td==null)
			{
			 td=new threaddemo(jf,a); 	
			 Thread tk=new Thread(td);
			 tk.start();
			}	
			
			System.out.println("开始游戏");
		}
		else if(type.equals("暂停"))
		{
			this.jf.requestFocus(); 
			flag=true;
			td.setflag(flag);			
		}
		else if(type.equals("继续"))
		{
			this.jf.requestFocus(); 
			flag=false;
			 td.setflag(flag);		
		}	//td.setflag(flag);  //用那个关键词只传一次就行了
		else if(type.equals("重新开始"))
		{
			this.jf.requestFocus();
			reset=true;
			td.setreset(reset);
			 td=new threaddemo(jf,a); 	
			 Thread tk=new Thread(td);
			 tk.start();
	//		 td=null;
			// reset=true;
			//	td.setreset(reset);
//			countstart++;	
//			if(countstart>=2)
//			{
//		
//				reset=true;
//				td.setreset(reset);
//				td=null;
//				countstart=0;
//			}	
		}	//td.setflag(flag);  //用那个关键词只传一次就行了
	}

}
