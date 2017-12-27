package f8clientlogin;
import f6qunliao.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class MainNetUI {

	/**
	 * @param args
	 */
	private JFrame jf_login;// 登录界面
	private JFrame jf_chat;// 聊天主界面
	private JTextField jta_userName; // 登录界面上的用户名，密码输入框
	private JTextField jta_pwd;
	private JTextArea jta_input = new JTextArea(5, 20); // 显示接收到的消息组件
	private lanjieNetClient conn; // 界面所要使用到的连接对象
    private UserInfo userInfo;
	public static void main(String[] args) {
		new MainNetUI().showLoginUI();
	}

	private void showLoginUI() {
		jf_login = new javax.swing.JFrame("聊天客户端v0.1,请登录:");
		java.awt.FlowLayout fl = new java.awt.FlowLayout();
		jf_login.setLayout(fl);
		jf_login.setSize(300, 300);
		jf_login.setLocationRelativeTo(null);
		jta_userName = new JTextField(12); // 用户名密码输入框
		jta_pwd = new JTextField(12);
		JLabel la_name = new JLabel("用户名:"); // 用户名密码的标签
		JLabel la_pwd = new JLabel("密 码:");
		jf_login.add(la_name);
		jf_login.add(jta_userName);
		jf_login.add(la_pwd);
		jf_login.add(jta_pwd);
		javax.swing.JButton bu_login = new javax.swing.JButton("Login");
		bu_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAction();
			}
		});
		jf_login.add(bu_login);
		jf_login.setVisible(true);
	}

	private void loginAction() {
		String name = jta_userName.getText();
		String pwd = jta_pwd.getText();
		// 1.创建连接对象
		conn = new lanjieNetClient("localhost", 9090, jta_input);
		// 2.连接上服务器
		if (conn.conn2Server()) {// 如果能连接上服务器
			// 3.登录
			if (conn.loginServer(name, pwd)) {
				// 4.显示聊天主界面 //登录成功了，要关掉登录界面
				showMainUI();
				// 5.启动接收线程
				conn.start();
				jf_login.dispose();// 关闭登录界面
			}
		} else {
			JOptionPane.showMessageDialog(jf_login, "账号有误！user1~10");
		}
	}

	// 显示聊天界面
	public void showMainUI() {
		jf_chat = new javax.swing.JFrame("聊天客户端v0.1");
		java.awt.FlowLayout fl = new java.awt.FlowLayout();
		jf_chat.setLayout(fl);
		jf_chat.setSize(200, 300);
		jf_chat.setLocationRelativeTo(null);
		// 显示接收到的消息
		javax.swing.JLabel la = new JLabel("要发送的消息: ");
		// 发送消息输入区
		final javax.swing.JTextArea jta_output = new JTextArea(5, 20);
		// 发送按钮
		javax.swing.JButton bu_send = new javax.swing.JButton("send");
		bu_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 实现在连接上的发送调用
				String msg = jta_output.getText();
				conn.sendMsg(msg);
			}
		});
		jf_chat.add(jta_input);
		jf_chat.add(la);
		jf_chat.add(jta_output);
		jf_chat.add(bu_send);
		jf_chat.setVisible(true);
	}

}
