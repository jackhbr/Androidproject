package MyAirPlaneaddtime1final1;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;


public class listener extends AllAdapt{
	 int a[]={250,500};
	private String type;
	private threaddemo td=null;
	private planetimethread tv;
	private ammotimethread ta;
	private volatile int b[];
	
   public volatile boolean flag=false;   //额，这个flag要在每次改变他的值的时候都要传递一次参数才行，不然就传不了
   public volatile boolean reset=false;   //额，这个volatite似乎没有起到它应该起到的作用啊。。。。？？？
   public JFrame jf;                       //原来是因为我在对flag操作之前加上了其他语句，所以看不出flag的作用
   private Button bks;                //可是这里为什么还要写setflag函数呢？？？
                                        //因为对于基本数据类型的就是要在改变之后就是要再传一次
   							          //而对于数组等引用类型则从头到尾都只需要再传一次
                                      //所以，对于标志位可以用数组来传递，而一般类的属性，考虑到类与对象，所以还是当普通参数传  
   
   private  ArrayList<ball> bl=new ArrayList<ball>(); //在这里建立数组队列,因为它要给主线程和延时的线程传递，这样就免去相互间的传递了
   private  ArrayList<ammo> al=new ArrayList<ammo>();	
   
   public void setjf(JFrame jf,Button bks,int b[]) //把窗体传到监听器里面去，当然还有他的画笔,主飞机的坐标的传递
	{
		this.jf=jf;
		 this.bks=bks;
		 this.b=b;
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
			this.jf.requestFocus();
			if(td==null)
			{
			 td=new threaddemo(jf,a,bl,al,b); 	
			 Thread tk=new Thread(td);
			 tk.start();
			 
			 tv=new planetimethread(jf, bl,flag); //把窗体传给延时，然后再传到敌机类给里面判断是否超出边框时
			 Thread tj=new Thread(tv);
			 tj.start();
			 
			 ta=new ammotimethread(a,al,flag);  //子弹需要主飞机的坐标
			 Thread ta1=new Thread(ta);
			 ta1.start();			 
			}	
			
			System.out.println("开始游戏");
		}
		else if(type.equals("暂停"))
		{
			this.jf.requestFocus(); 
			flag=true;
			td.setflag(flag);		
			tv.setflag(flag);
			ta.setflag(flag);
		}
		else if(type.equals("继续"))
		{
			this.jf.requestFocus(); 
			flag=false;
			 td.setflag(flag);	
			 tv.setflag(flag);
				ta.setflag(flag);
		}
		else if(type.equals("重新开始"))
		{
			this.jf.requestFocus();
			reset=true;
			td.setreset(reset);//先把之前的关闭，然后再开启新的线程
			 td=new threaddemo(jf,a,bl,al,b); 	
			 Thread tk=new Thread(td);
			 tk.start();
			 
			 tv.setreset(reset);
			 bl.removeAll(bl);
			 tv=new planetimethread(jf, bl,flag); //把窗体传给延时，然后再传到敌机类给里面判断是否超出边框时
			 Thread tj=new Thread(tv);
			 tj.start();
			 
			 
			 ta.setreset(reset);
			 al.removeAll(al); //由于一直都是这个数组队列，所以重新开始的时候需要把原来的队列里的所有对象都移除掉
			 ta=new ammotimethread(a,al,flag);  //子弹需要主飞机的坐标
			 Thread ta1=new Thread(ta);
			 ta1.start();
			 

		}
	}

}
