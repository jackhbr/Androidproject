package myAirModel2;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.ls.LSException;

public class Main extends JFrame{

	/**
	 * @param args
	 */
	 int a[]={250,500};
	 listener ls=new listener();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main st=new Main();
		st.showUI();

	}
	public void showUI()
	{
		//JFrame jFrame=new JFrame();
		this.setTitle("����ɻ���ս");
		this.setSize(500, 600);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);//��������λ��
		this.setDefaultCloseOperation(3);
		
		 
		
		String arr[]={"��ʼ��Ϸ","��ͣ","����"};
		for(int i=0;i<arr.length;i++)
		{
			Button bk=new Button(arr[i]);
			bk.addActionListener(ls);
			this.add(bk);
			
		}
		
		this.getContentPane().setBackground(Color.WHITE);  //��Ȼ���ò��˱���ɫ��������
		//���������Ȼ�ȡ���Ĳ�ſ������ñ�����ɫ��������Ԫ���Ϳ���ֱ�Ӽӱ�����ɫ
		
	
		this.addKeyListener(ls);
		this.setVisible(true);
		
		ls.setjf(this);//�Ѵ��崫������������ȥ����Ȼ�������Ļ���
	//	 System.out.println("main�еĴ�����"+jFrame.getWidth());   getwidth()��width�ķ���ֵ�ǲ�һ���ģ�
		//getwidth()����Ŀ�ȣ�width����1.����������
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		ls.setxy(a);
		g.setColor(Color.BLACK);
		g.fillOval(a[0], a[1], 40, 40);
		
		
		System.out.println("�ػ����x="+a[0]+"  y="+a[1]);
	}

}
