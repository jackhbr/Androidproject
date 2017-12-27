package sever22;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ������߳����и���ȴ��ʹ��ݽ��յ��Ķ���ͻ��˵��ź�
 * @author jack
 *
 */

public class Threaded extends Thread{
	ServerSocket server;
	ArrayList<Socket> jk=new ArrayList<Socket>();
	private ArrayList<Client> client;
	public Threaded(ServerSocket server,ArrayList<Client> client)
	{
		this.server=server;
		this.client=client;
	}
	
	public void run()
	{
		while(true)
		 { //�÷�����ѭ���ȴ�
	    	  Socket clientSocket;
	    	  if(server.isClosed())
	    		  {
	    		    System.out.println("���������߳̽���");
	    		  	break;
	    		  }
			try {
				System.out.println("������Ƿ�ر�"+server.isClosed());
				clientSocket = server.accept();//�����и����������������ʱ�ر��̣߳��ͻᱨ������ʾ��ʱ�������Ѿ��رգ�����
				jk.add(clientSocket);
				ThreadClient ct=new ThreadClient(clientSocket,jk,client);
		    	  ct.start();				
				}
			catch (IOException e) {		
				if(server.isClosed())//�ҵ�Ŀ�ľ�������������������Ϊ�յ���������ܲ��׳���������ֻҪ��������д���������
					break;
				e.printStackTrace();
			} 	    	  
		 }
	}
}