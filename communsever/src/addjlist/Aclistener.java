package addjlist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;

import protocol2.ChatThread0923;

public class Aclistener implements ActionListener {
	ServerSocket server=null;
	ArrayList<Socket> jk=new ArrayList<Socket>();
	Thread ct;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type=e.getActionCommand();
		
	
		
		if(type.equals("开启服务器"))
		{
			
				if(server==null)
				{
				try {
					server=new ServerSocket(9090);
					System.out.println("服务器9090端口创建成功！");
					  ct=new Threaded(server);
			    	  ct.start();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				}
				
		    	 JButton jjButton=(JButton) e.getSource();
		    	 jjButton.setText("关闭服务器");
				
				
		
		}
		else if(type.equals("禁言"))
		{
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
				
				server=null;//server=null是为了下一次再重新开启服务器，这里这个server就指向了另一个东西了，其他线程就不再接收这个server的东西了
				System.out.println("此时的sever="+server);    //所以现在已经可以达到自己的目的了，close传过去了，null只是在这里用就可以了
				JButton jjButton=(JButton) e.getSource();
		    	 jjButton.setText("开启服务器");
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
	}

}
