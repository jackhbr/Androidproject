package hwr3;

import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Manager {   //

	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		Manager ma=new Manager();
		ma.showUI();

	}
	public void showUI()
	{
		JFrame jf=new JFrame();
		jf.setTitle("������");
	//	jf.setResizable(false);//��仰��ı䴰��Ĵ�С
		jf.setSize(150, 160);    //�����������С�Ŀ�ȣ������140���ң�������С�ˡ�����������仰����������һ����һ����С���
		jf.setLayout(new FlowLayout());
		String s[]={"д��","�������","0","1","2","3","4","5","6","7","8","9","����ѵ��"};
		
		Aclistener ls=new Aclistener();
		
		for(int i=0;i<s.length;i++)
		{
			Button bu=new Button(s[i]);
			bu.addActionListener(ls);
			jf.add(bu);
		}
		
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		ls.setPa(jf);
		
	}

}
