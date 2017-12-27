package login_face;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class ButtonListener implements java.awt.event.ActionListener {
	 private JFrame login;//定义登录窗体的属性名
	/**
	 * 设置登录窗体属性值的方法
	 * @param l从Login类总传递过来的登录窗体
	 */
	public void setLogin(JFrame l){
		login = l;//将传递过来的登录窗体对象赋给login属性
	}
	
	
	public JPasswordField passfield;
	public JTextField field;
	
	public ButtonListener(JTextField field,JPasswordField passfield) //修改这里面的参数，就可以改变传进来的参数的多少了
	{
		this.passfield=passfield;
		this.field=field;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
	    String name=field.getText();
	    String name1=passfield.getText();
		System.out.println("按钮被点击了！");
		//if(passfield==1234){
		System.out.println("输入的账号是"+name);
		System.out.println("输入的账号是"+name1);
		if(name.equals("1234"))
			System.out.println("输入的账号正确");
		else
			System.out.println("输入的账号错误");
		if(name1.equals("123456"))
			System.out.println("输入的密码正确");
		else
			System.out.println("输入的密码错误");
		//}
		if(name.equals("1234") && name1.equals("123456"))
		{
		JFrame frame = new JFrame("画图");// 实例化JFrame窗体对象，同时设置窗体的标题属性值
		frame.setSize(700, 500);// 设置窗体的大小
		frame.setDefaultCloseOperation(2);// 设置窗体的关闭操作，2表是关闭当前窗体，其他窗体不关闭(如果只有一个窗体，关闭窗体退出程序)
		frame.setLocationRelativeTo(null);// 参数为null表示设置窗体相对于屏幕的中央位置，
		frame.setVisible(true);// 设置窗体为可见
		
		//当画图窗体成功显示之后，原来的登录窗体就必须要关闭
		login.dispose();
		}
		else
		{
		
			JOptionPane.showMessageDialog(null, "您输入的账号密码错误", "错误提示", JOptionPane.ERROR_MESSAGE);
			
		}
	}

}
