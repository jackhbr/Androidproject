package hwr;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Aclistener extends AllAdapt{  //���������ǿ��Թ���һ���¼�������ģ�����
	
	private JFrame jf;
	private int x1,x2,y1,y2;//x1 y1�ǰ���ʱ�����꣬x2 y2���϶�ʱ������
	
	public void setPa(JFrame jf)
	{
		this.jf=jf;
	}
	
	 public void actionPerformed(ActionEvent e)
	 {
		 String type=e.getActionCommand();
		 if(type.equals("д��"))
		 {
			 System.out.println("д��");
			 JFrame jk=new JFrame();//������jf������jf����Ҫ������
			 jk.setTitle("��дʶ���");
			 jk.setSize(400, 400);
			 jk.setDefaultCloseOperation(2);
			 jk.setLocationRelativeTo(null);
			 jk.addMouseMotionListener(this); //�������干��һ���¼�������
			 jk.addMouseListener(this);//�϶��Ͱ��µ���������
			 jk.setVisible(true);
		 }
	 }
	 public void mouseDragged(MouseEvent e)
	 {
		 x2=e.getX();
		 y2=e.getY();
		 System.out.println("�϶�ʱ��x="+x2+"  y="+y2);
	 }
	 public void mousePressed(MouseEvent e)   //ע�ⴰ��������ı߿�Ҳ���ڴ����С�ڣ��������������ʱȴ���������¼�
	 {
		 x1=e.getX();
		 y1=e.getY();
		 System.out.println("����ʱ��x="+x1+"  y="+y1);
	 }

}
