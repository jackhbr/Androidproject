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
		jf.setTitle("主界面");
	//	jf.setResizable(false);//这句话会改变窗体的大小
		jf.setSize(150, 160);    //这个窗体有最小的宽度，大概是140左右，不能再小了。加了上面这句话后窗体有有了一个另一个最小宽度
		jf.setLayout(new FlowLayout());
		String s[]={"写入","输出数组","0","1","2","3","4","5","6","7","8","9","进行训练"};
		
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
