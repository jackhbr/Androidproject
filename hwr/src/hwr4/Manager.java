package hwr4;

import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Manager {   // 编码不同的文件组合在�?起也是可以运行程序的

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
		jf.setTitle("主");
		jf.setSize(200, 200);
		 jf.setLocationRelativeTo(null);
		jf.setLayout(new FlowLayout());
		String s[]={"写入","输出数组","0","1","2","3","4","5","6","7","8","9","进行训练"};
		
		Aclistener ls=new Aclistener();
		
		for(int i=0;i<s.length;i++)
		{

			JButton bu=new JButton(s[i]);   //论button有时在编码变换后�?直不能显示中文有多坑，坑了我两个小时
			bu.addActionListener(ls); //包里面的编码要统�?，一�?始用啥就用啥，最好用utf-8.
			jf.add(bu);
		}
		
		jf.setVisible(true);
		jf.requestFocus();//
		ls.setPa(jf);
		
	}

}
