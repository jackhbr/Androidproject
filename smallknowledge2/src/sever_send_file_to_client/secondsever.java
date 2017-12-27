package sever_send_file_to_client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;

public class secondsever implements Runnable{
	
	ServerSocket ss;
	Socket client;
	OutputStream os;
	DataOutputStream dos;
	JButton jb;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		secondsever fi=new secondsever();
		fi.UI();
		Thread th=new Thread(fi);
		fi.creatserver();
		th.start();

	}
	public void UI()
	{
		JFrame jf=new JFrame();
		jf.setTitle("save file");
		jf.setLayout(new FlowLayout()); //没有设置布局就会导致一个按键直接占领了整个jf!!
		
		 jb=new JButton("发送文件");
		//jb.setSize(50, 50);这个不起作用
		//jb.setPreferredSize(new Dimension(50, 50));
		
		jf.add(jb);
		
		jf.setSize(200, 200);
		jf.setLocationRelativeTo(null);
	    jf.setVisible(true);  	
	}
	public void creatserver() throws IOException
	{
		 ss=new ServerSocket(9090);
		 
	}
	public void run()
	{
		try {
			client =ss.accept();
			os=client.getOutputStream();
			dos=new DataOutputStream(os);
			second_buttonlisten al=new second_buttonlisten(dos);
			jb.addActionListener(al);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
