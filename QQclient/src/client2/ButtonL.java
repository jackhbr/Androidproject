package client2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ButtonL implements ActionListener{

	private JPasswordField usePassword;
	private JTextField useCount;
	
	private OutputStream ops;
	private InputStream ins;
	private DataInputStream dis;
	private DataOutputStream dos;
	private JFrame jf;
	
	
	public ButtonL( JTextField useCount,JPasswordField usePassword,JFrame jf) {
		this.useCount = useCount;
		this.usePassword = usePassword;
		this.jf=jf; //如果登录成功，则要把原来的窗体关闭掉，新建另一个窗体
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("已经登录");
		String acount=useCount.getText();
		String passWord=usePassword.getText();
		System.out.println("输入的账户是"+acount);
		System.out.println("输入的密码是"+passWord);
		
		try {
			Socket client=new Socket("120.78.55.19", 9090);
			ops = client.getOutputStream();
			ins = client.getInputStream();
			dis = new DataInputStream(ins);
			
			WaitSeverResponseThread waitSever=new WaitSeverResponseThread(dis,jf);
			Thread th=new Thread(waitSever);
			th.start();
			
			dos=new DataOutputStream(ops);
			String header="login|"+acount+"!"+passWord+"#\r\n"; //这个回车很重要，不然就不能用readline了
			byte b[]=header.getBytes();
			dos.write(b);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
