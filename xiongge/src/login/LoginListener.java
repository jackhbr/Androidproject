package login;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * 1.定义LoginListener事件处理类，该类实现ActionListener动作事件接口，
 * 
 * @author 熊哥
 * 
 */
public class LoginListener implements ActionListener {
	
	private JFrame login;//定义登录窗体的属性名
	
	/**
	 * 设置登录窗体属性值的方法
	 * @param l从Login类总传递过来的登录窗体
	 */
	public void setLogin(JFrame l){
		login = l;//将传递过来的登录窗体对象赋给login属性
	}
	
	/**
	 * 1.重写接口中的抽象方法。
	 */
	public void actionPerformed(ActionEvent e) {
		// System.out.println("你点击了" + e.getActionCommand() + "按钮");
		// 1.1.在重写的事件处理方法中，实例化新窗体对象，设置窗体的属性值。
		JFrame frame = new JFrame("画图");// 实例化JFrame窗体对象，同时设置窗体的标题属性值
		frame.setSize(700, 500);// 设置窗体的大小
		frame.setDefaultCloseOperation(2);// 设置窗体的关闭操作，2表是关闭当前窗体，其他窗体不关闭(如果只有一个窗体，关闭窗体退出程序)
		frame.setLocationRelativeTo(null);// 参数为null表示设置窗体相对于屏幕的中央位置，
		frame.setVisible(true);// 设置窗体为可见
		
		//当画图窗体成功显示之后，原来的登录窗体就必须要关闭
		login.dispose();
	}
}
