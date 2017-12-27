package f6qunliao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

//处理对某个客户机交互流程的实现
public class ServerThread extends Thread {
	private java.net.Socket client;// 线程中处理的客户对象
	private OutputStream ous;// 输出流对象
	private UserInfo user; // 这个线程处理对像代表的用户的信息
	// 创建时必须传入一个Socket对象，

	public ServerThread(java.net.Socket client) {
		this.client = client;
	}

	// 取得这个线程对象代表的用户对象；
	public UserInfo getOwerUser() {
		return this.user;
	}

	public void run() {// 线程中执行的方法
		processSocket();
	}

	// 将一条消息发送给这个线程对象所代表的客户机, 传入前，不需要加上\r\n,
	public void sendMsg2Me(String msg) {
		try {
			msg += "\r\n";
			ous.write(msg.getBytes());
			ous.flush();
		} catch (Exception ef) {
		}
	}

	// 读取客户机发来的消息
	private void processSocket() {
		try {
			InputStream ins = client.getInputStream();
			ous = client.getOutputStream();
			// 将输入流ins封装为可以读取一行字符串,也就是以\r\n结尾的字符串
			BufferedReader brd = new BufferedReader(new InputStreamReader(ins));
			sendMsg2Me("欢迎你来聊天!,请输入你的用户名:");
			String userName = brd.readLine();
			sendMsg2Me(userName + ",请输入你的密码:");
			String pwd = brd.readLine();
			user = new UserInfo();
			user.setName(userName);
			user.setPwd(pwd);
			// 调用数据库模块,验证用户是否存在
			boolean loginState = DaoTools.checkLogin(user);
			if (!loginState) {// 不存在这个用户账号则关闭
				this.closeMe();
				return;
			}
			ChatTools.addClient(this); // 认证成功:将这个对象加入服务器队列
			String input = brd.readLine();// 一行一行的读取客户机发来的消息
			while (!"bye".equals(input)) {// 一直读取
				System.out.println("服务器收到的是: " + input);
				// 读到一条消息后，就发送给其他的客户机去...
				ChatTools.castMsg(this.user, input);
				input = brd.readLine();// 读取下一条
			}
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		ChatTools.castMsg(this.user, "我下线了,再见!");
		this.closeMe();
		// 调用服务器管理方法，通知这人下线了
		// ChatTools.removeClient(this.user);//暂未实现
	}

	// 关闭这个线程处理对象
	public void closeMe() {
		try {
			client.close();
		} catch (Exception ef) {
			ef.printStackTrace();
		}
	}
}
