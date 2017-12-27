package f8clientlogin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;

public class lanjieNetClient extends Thread {
	private String serverIp;// 服务器IP
	private int port;// 服务器端口
	private OutputStream ous;// 到服务器的输出流对象
	private BufferedReader brd;// 到服务器的输入流对象
	// 构造器，创建客户机对象时，传入服务器IP和端口
	 //显示接收到的消息组件，要从界面上传过来
	private JTextArea jta_recive;
	public lanjieNetClient(String serverIp, int port,JTextArea jta_recive) {
		this.serverIp = serverIp;
		this.port = port;
		this.jta_recive=jta_recive;
	}

	// 1.连接上服务器,是否连接成功
	public boolean conn2Server() {
		try {
			// 创建一个到服务器端的Socket对象
			Socket client = new Socket(this.serverIp, this.port);
			InputStream ins = client.getInputStream();// 得到输入输出流对象
			// 可以读取一行字符串,也就是以\r\n结尾的字符串
			brd = new BufferedReader(new InputStreamReader(ins));
			ous = client.getOutputStream();
			return true;
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		return false;
	}

	/**
	 * 2.登录服务器
	 * 
	 * @param name
	 *            :用户名
	 * @param pwd
	 *            :密码
	 * @return: 是否登录成功
	 */
	public boolean loginServer(String name, String pwd) {
		try {
			// 1.读取服务器发来的一条消息
			String input = brd.readLine();
			System.out.println("服务器说: " + input);
			// 2.按照服务器流程，发送用户名和密码
			name += "\r\n";// 发送时字符串后面必须跟上\r\n !!!
			ous.write(name.getBytes());
			ous.flush();
			System.out.println("客户机已将用户名发送，等服务器回应");

			input = brd.readLine();
			System.out.println("服务器说: " + input);
			pwd += "\r\n";
			ous.write(pwd.getBytes());
			ous.flush();
			return true;
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		return false;
	}

	// 线程中读取服务器发来的消息
	public void run() {
		while (true) {
			readFromServer();
		}
	}

	// 3.从服务器读取消息，这个方法会阻塞，必须在独立线程中
	public void readFromServer() {
		try {
			String input = brd.readLine();
			System.out.println("服务器说: " + input);
		} catch (Exception ef) {
			ef.printStackTrace();
		}
	}

	/** 发送一条消息到服务器的方法 */
	public void sendMsg(String msg) {
		try {
			msg += "\r\n";
			this.ous.write(msg.getBytes());
			this.ous.flush();
		} catch (Exception ef) {
			ef.printStackTrace();
		}
	}


}
