package MyAirPlaneaddtime;

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
	
   public  boolean flag=false;   //����flagҪ��ÿ�θı�����ֵ��ʱ��Ҫ����һ�β������У���Ȼ�ʹ�����
   public volatile boolean reset=false;   //����volatite�ƺ�û������Ӧ���𵽵����ð���������������
   public JFrame jf;
   private Button bks;
   
   private  ArrayList<ball> bl=new ArrayList<ball>(); //�����ｨ���������,��Ϊ��Ҫ�����̺߳���ʱ���̴߳��ݣ���������ȥ�໥��Ĵ�����
   private  ArrayList<ammo> al=new ArrayList<ammo>();	
   
   public void setjf(JFrame jf,Button bks) //�Ѵ��崫������������ȥ����Ȼ�������Ļ���,���ɻ�������Ĵ���
	{
		this.jf=jf;
		 this.bks=bks;
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
			this.jf.requestFocus();
			if(td==null)
			{
			 td=new threaddemo(jf,a,bl,al); 	
			 Thread tk=new Thread(td);
			 tk.start();
			 
			 tv=new planetimethread(jf, bl,flag); //�Ѵ��崫����ʱ��Ȼ���ٴ����л���������ж��Ƿ񳬳��߿�ʱ
			 Thread tj=new Thread(tv);
			 tj.start();
			 
			 ta=new ammotimethread(a,al,flag);  //�ӵ���Ҫ���ɻ�������
			 Thread ta1=new Thread(ta);
			 ta1.start();			 
			}	
			
			System.out.println("��ʼ��Ϸ");
		}
		else if(type.equals("��ͣ"))
		{
			this.jf.requestFocus(); 
			flag=true;
			td.setflag(flag);		
			tv.setflag(flag);
			ta.setflag(flag);
		}
		else if(type.equals("����"))
		{
			this.jf.requestFocus(); 
			flag=false;
			 td.setflag(flag);	
			 tv.setflag(flag);
				ta.setflag(flag);
		}
		else if(type.equals("���¿�ʼ"))
		{
			this.jf.requestFocus();
			reset=true;
			td.setreset(reset);//�Ȱ�֮ǰ�Ĺرգ�Ȼ���ٿ����µ��߳�
			 td=new threaddemo(jf,a,bl,al); 	
			 Thread tk=new Thread(td);
			 tk.start();
			 
			 tv.setreset(reset);
			 bl.removeAll(bl);
			 tv=new planetimethread(jf, bl,flag); //�Ѵ��崫����ʱ��Ȼ���ٴ����л���������ж��Ƿ񳬳��߿�ʱ
			 Thread tj=new Thread(tv);
			 tj.start();
			 
			 
			 ta.setreset(reset);
			 al.removeAll(al); //����һֱ�������������У��������¿�ʼ��ʱ����Ҫ��ԭ���Ķ���������ж����Ƴ���
			 ta=new ammotimethread(a,al,flag);  //�ӵ���Ҫ���ɻ�������
			 Thread ta1=new Thread(ta);
			 ta1.start();
			 

		}
	}

}
