package GUI1interruptstop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 在这个线程类中负责等待和传递接收到的多个客户端的信号
 * @author jack
 *
 */

public class Threaded extends Thread{
	ServerSocket server;
	ArrayList<Socket> jk=new ArrayList<Socket>();
	
	/**
	 * 构造方法
	 * @param server  把服务器传过来
	 */
	public Threaded(ServerSocket server)
	{
		this.server=server;
	}
	
	public void run()
	{
		while(!isInterrupted())
		 { //让服务器循环等待
	    	  Socket client;
	    	  if(server.isClosed())
	    		  {
	    		    System.out.println("服务器的线程结束");
	    		  	break;
	    		  }
			try {
				System.out.println("服务端是否关闭"+server.isClosed());
//				if(!server.isClosed())  //如果服务端没有关闭的话，就继续等待客户端的接入
//				{
				client = server.accept();//这里有个阻塞，所以如果此时关闭线程，就会报错，提示此时服务器已经关闭，不能
				jk.add(client);
				ThreadClient ct=new ThreadClient(client,jk);
		    	  ct.start();
//				}
				
			}
			//到底怎么使用这个interrupt中断啊！！！???这个accept对应的就是ioexception,
			//这个怎么让他在
			catch (IOException e) {
			
				if(server.isClosed())//我的目的就是让它在遇到服务器为空的这种情况能不抛出错误，所以只要在这里进行处理就行了
					break;
				e.printStackTrace();
			} 
	    	  
		 }
		 System.out.println("此时已经跳出服务器的while（true）");
	}
}
