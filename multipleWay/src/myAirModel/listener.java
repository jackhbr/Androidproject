package myAirModel;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;


public class listener extends AllAdapt{
	private String type;
	private threaddemo td=null;
   public volatile boolean flag=false;
	
	public JFrame jf;
	
	public void setjf(JFrame jf) //�Ѵ��崫������������ȥ����Ȼ�������Ļ���
	{
		this.jf=jf;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		type=e.getActionCommand();
		if(type.equals("��ʼ��Ϸ"))
		{
			if(td==null)
			{
			 td=new threaddemo(jf);  //�Ѵ����ٴ����߳�����ȥ
			
			// System.out.println("�������еĴ�����"+jf.WIDTH);
			 Thread tk=new Thread(td);
			 tk.start();
			}
			
			
			
			
			System.out.println("��ʼ��Ϸ");
		}
		else if(type.equals("��ͣ"))
		{
			flag=true;
			 td.setflag(flag);
			System.out.println("��ͣ");
		}
		else if(type.equals("����"))
		{
			flag=false;
			 td.setflag(flag);
			System.out.println("����");
		}
	}

}
