package sever23;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;



public class Aclistener implements ActionListener {
	ServerSocket server=null;
	private ArrayList<Client> client;
	private JList<Client> jlist;
	public Aclistener(ArrayList<Client> client,JList<Client> jlist) {
	this.client=client;
	this.jlist=jlist;
	}

	public void actionPerformed(ActionEvent e) {
		String type=e.getActionCommand();
		
		
		
		
		if(type.equals("开启服务器"))
		{		
				if(server==null)
				{
				try {
					server=new ServerSocket(9090);
					System.out.println("服务器9090端口创建成功！");
					 Threaded ct=new Threaded(server,client,jlist);
			    	  ct.start();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}		
				}			
		    	 JButton jjButton=(JButton) e.getSource();
		    	 jjButton.setText("关闭服务器");

		}
		else if(type.equals("禁言"))
		{
			Client cli=new Client();
			cli.setAcount("555555");
			cli.setName("就是如此平凡");
			cli.setPassWord("bbbbbb");
			client.add(cli);
			jlistmodel jm=new jlistmodel(client);
			jlist.setModel(jm);
			
			System.out.println("暂时无此功能");
		}
		else if(type.equals("踢人"))
		{
			System.out.println("暂时无此功能");
		}
		else if(type.equals("关闭服务器"))
		{
			try {
				server.close();//server是对象，所以在这里关闭了就在全部线程中都可以收到这个信息
				server=null;  //关掉服务器是不能再连接新的客户端，但是原来的客户端还是可以正常通信
				System.out.println("此时的sever="+server);
				JButton jjButton=(JButton) e.getSource();
		    	 jjButton.setText("开启服务器");
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
