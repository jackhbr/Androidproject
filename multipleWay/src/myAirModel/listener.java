package myAirModel;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;


public class listener extends AllAdapt{
	private String type;
	private threaddemo td=null;
   public volatile boolean flag=false;
	
	public JFrame jf;
	
	public void setjf(JFrame jf) //把窗体传到监听器里面去，当然还有他的画笔
	{
		this.jf=jf;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		type=e.getActionCommand();
		if(type.equals("开始游戏"))
		{
			if(td==null)
			{
			 td=new threaddemo(jf);  //把窗体再传到线程里面去
			
			// System.out.println("监听器中的窗体宽度"+jf.WIDTH);
			 Thread tk=new Thread(td);
			 tk.start();
			}
			
			
			
			
			System.out.println("开始游戏");
		}
		else if(type.equals("暂停"))
		{
			flag=true;
			 td.setflag(flag);
			System.out.println("暂停");
		}
		else if(type.equals("继续"))
		{
			flag=false;
			 td.setflag(flag);
			System.out.println("继续");
		}
	}

}
