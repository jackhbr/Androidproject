package hwr;

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
		jf.setTitle("手写识别");
		jf.setSize(300, 300);
		jf.setLayout(new FlowLayout());
		String s[]={"写入"};
		
		Aclistener ls=new Aclistener();
		
		for(int i=0;i<s.length;i++)
		{
			Button bu=new Button(s[i]);
			bu.addActionListener(ls);
			jf.add(bu);
		}
		
		jf.setVisible(true);
		ls.setPa(jf);
		
	}

}
