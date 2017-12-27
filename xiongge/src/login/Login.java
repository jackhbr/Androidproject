package login;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//import引入类的关键字，引入SWING组件中的JFrame类。
//引入类有一个快捷键：Ctrl+Shift+O

/**
 * 1.新建一个Login登录界面类
 */
public class Login {

	/**
	 * 1.在类中定义主函数
	 */
	public static void main(String[] args) {
		// 2.在主函数中，实例化Login类的对象
		Login login = new Login();
		// 2.然后使用对象名来调用初始化界面的方法。
		login.initUI();
	}

	/**
	 * 1.初始化界面的方法
	 */
	public void initUI() {
		// 3.在初始化界面的方法中，实例化JFrame窗体容器组件类的对象。
		JFrame frame = new JFrame();
		// 4.设置JFrmae窗体对象的属性值：标题、大小、显示位置、关闭操作、布局、可见。
		frame.setTitle("Login");// 设置标题属性
		frame.setSize(300, 400);// 设置窗体的大小
		frame.setLocationRelativeTo(null);// 设置窗体显示在屏幕的中央。
		frame.setDefaultCloseOperation(3);// 设置窗体的关闭操作为3,表示关闭窗体退出程序。

		// 实例化FlowLayout流式布局类的对象,默认是居中对齐
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		// 设置frame窗体的布局方式为fl流式布局
		frame.setLayout(fl);

		// 5.实例化其他组件类的对象，然后将对象添加到窗体容器组件上。
		JLabel labName = new JLabel("账号：");// 实例化JLabel类的对象，设置要显示的信息为"账号："
		frame.add(labName);// 让后将labName组件添加到frame对象上

		JTextField txtName = new JTextField(20);// 实例化JTextField文本输入框类的对象
		frame.add(txtName);// 将txtName添加到窗体容器组件上

		JButton button = new JButton("登录");
		frame.add(button);

		frame.setVisible(true);// 设置窗体为可见

		// 2.界面类中实例化LoginListener事件处理类的对象。
		LoginListener ll = new LoginListener();
		
		//将frame登录窗体传递到ll对象的login属性中
		ll.setLogin(frame);
		
		// 3.在界面类中，给事件源登录按钮对象，添加addActionListener()动作监听方法，指定LoginListener事件处理类的对象。
		button.addActionListener(ll);

	}

}
