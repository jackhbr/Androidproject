package myAirModel;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main st=new Main();
		st.showUI();

	}
	public void showUI()
	{
		JFrame jFrame=new JFrame();
		jFrame.setTitle("����ɻ���ս");
		jFrame.setSize(500, 600);
		jFrame.setLayout(new FlowLayout());
		jFrame.setLocationRelativeTo(null);//��������λ��
		jFrame.setDefaultCloseOperation(3);
		
		listener ls=new listener();
		
		String arr[]={"��ʼ��Ϸ","��ͣ","����"};
		for(int i=0;i<arr.length;i++)
		{
			Button bk=new Button(arr[i]);
			bk.addActionListener(ls);
			jFrame.add(bk);
			
		}
		
		
		jFrame.getContentPane().setBackground(Color.WHITE);  //��Ȼ���ò��˱���ɫ��������
		//���������Ȼ�ȡ���Ĳ�ſ������ñ�����ɫ��������Ԫ���Ϳ���ֱ�Ӽӱ�����ɫ
		
		
		
		jFrame.setVisible(true);
		
		ls.setjf(jFrame);//�Ѵ��崫������������ȥ����Ȼ�������Ļ���
	//	 System.out.println("main�еĴ�����"+jFrame.getWidth());   getwidth()��width�ķ���ֵ�ǲ�һ���ģ�
		//getwidth()����Ŀ�ȣ�width����1.����������
	}

}
