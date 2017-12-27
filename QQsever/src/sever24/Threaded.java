package sever24;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.text.Document;

/**
 * 在这个线程类中负责等待和传递接收到的多个客户端的信号
 * @author jack
 *
 */

public class Threaded extends Thread{
	ServerSocket server;
	ArrayList<Socket> jk=new ArrayList<Socket>();
	private ArrayList<Client> client;
	private JList<Client> jlist;
	  private Document docs;
	
	public Threaded(ServerSocket server,ArrayList<Client> client,JList<Client> jlist,Document docs)
	{
		this.server=server;
		this.client=client;
		this.jlist=jlist;
		this.docs=docs;
	}
	
	public void run()
	{
		while(true)
		 { //让服务器循环等待
	    	  Socket clientSocket;
	    	  if(server.isClosed())
	    		  {
	    		    System.out.println("服务器的线程结束");
	    		  	break;
	    		  }
			try {
				System.out.println("服务端是否关闭"+server.isClosed());
				clientSocket = server.accept();//这里有个阻塞，所以如果此时关闭线程，就会报错，提示此时服务器已经关闭，不能
				jk.add(clientSocket);
				ThreadClient ct=new ThreadClient(clientSocket,jk,client,jlist,server,docs);
		    	  ct.start();				
				}
			catch (IOException e) {		
				if(server.isClosed())//我的目的就是让它在遇到服务器为空的这种情况能不抛出错误，所以只要在这里进行处理就行了
					break;
				e.printStackTrace();
			} 	    	  
		 }
	}
}
