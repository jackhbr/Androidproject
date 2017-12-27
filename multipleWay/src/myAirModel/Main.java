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
		jFrame.setTitle("经典飞机大战");
		jFrame.setSize(500, 600);
		jFrame.setLayout(new FlowLayout());
		jFrame.setLocationRelativeTo(null);//居于中央位置
		jFrame.setDefaultCloseOperation(3);
		
		listener ls=new listener();
		
		String arr[]={"开始游戏","暂停","继续"};
		for(int i=0;i<arr.length;i++)
		{
			Button bk=new Button(arr[i]);
			bk.addActionListener(ls);
			jFrame.add(bk);
			
		}
		
		
		jFrame.getContentPane().setBackground(Color.WHITE);  //居然设置不了背景色？？？？
		//这个窗体的先获取他的层才可以设置背景颜色，其他的元件就可以直接加背景颜色
		
		
		
		jFrame.setVisible(true);
		
		ls.setjf(jFrame);//把窗体传到监听器里面去，当然还有他的画笔
	//	 System.out.println("main中的窗体宽度"+jFrame.getWidth());   getwidth()和width的返回值是不一样的！
		//getwidth()是真的宽度，width就是1.。。。。。
	}

}
