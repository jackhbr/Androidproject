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
	
	
   public void setxy(int a[])   //���ɻ�������Ĵ���
   {
	   this.a=a;
		
   }
   
	
	public void setjf(JFrame jf,Button bks) //�Ѵ��崫������������ȥ����Ȼ�������Ļ���
	{
		this.jf=jf;
		 g=jf.getGraphics();
		 this.bks=bks;
	//	this.jf.requestFocus(); //Ӧ���������ȡ�ý��㣬��������ӣ���Ϊ�������Ѿ����е��ˡ���
		
	}
	
	 public void keyPressed(KeyEvent e)
	 {
			
			 if ( e.getKeyCode()==KeyEvent.VK_DOWN ){   //ע�⣡������Ĳ���e!����keyevent!!
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
		if(type.equals("��ʼ��Ϸ"))
		{
			bks.setLabel("���¿�ʼ");
			//countstart++;		
			this.jf.requestFocus();
			if(td==null)
			{
			 td=new threaddemo(jf,a); 	
			 Thread tk=new Thread(td);
			 tk.start();
			}	
			
			System.out.println("��ʼ��Ϸ");
		}
		else if(type.equals("��ͣ"))
		{
			this.jf.requestFocus(); 
			flag=true;
			td.setflag(flag);			
		}
		else if(type.equals("����"))
		{
			this.jf.requestFocus(); 
			flag=false;
			 td.setflag(flag);		
		}	//td.setflag(flag);  //���Ǹ��ؼ���ֻ��һ�ξ�����
		else if(type.equals("���¿�ʼ"))
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
		}	//td.setflag(flag);  //���Ǹ��ؼ���ֻ��һ�ξ�����
	}

}
