package moreballQiuPeng;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class manager extends JFrame
{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		manager dl=new manager();
		dl.showUI();

	}
	
	public void showUI()
	{
		this.setTitle("线程小球");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.setBackground(Color.WHITE);
		
		MouseListener dl=new MouseListener();
		
		String[] array = {"创建一个新的小球"};

		// 循环遍历数组，根据数组中的元素来实例化按钮对象
		for (int i = 0; i < array.length; i++) {
			JButton button = new JButton(array[i]);
			button.addActionListener(dl);
			button.setPreferredSize(new Dimension(200, 50));//第一个坐标是y
			this.add(button);// 将按钮添加到窗体上
			this.addMouseListener(dl);
		}
		
		
		this.setVisible(true);
		
	//	Graphics g = this.getGraphics();  //窗体显示可见之后再获取画笔
		
		dl.setjframe(this);
		
	}
//	public void paint(Graphics g)
//	{
//		super.paint(g);// super表示父类的对象
//		
//	}

}
	
