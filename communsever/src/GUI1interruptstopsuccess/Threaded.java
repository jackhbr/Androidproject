package GUI1interruptstopsuccess;

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
	
	/**
	 * ���췽��
	 * @param server  �ѷ�����������
	 */
	public Threaded(ServerSocket server)
	{
		this.server=server;
	}
	
	public void run()
	{
		while(true)
		 { //�÷�����ѭ���ȴ�
	    	  Socket client;
	    	  if(server.isClosed())
	    		  {
	    		    System.out.println("���������߳̽���");
	    		  	break;
	    		  }
			try {
				System.out.println("������Ƿ�ر�"+server.isClosed());
				client = server.accept();//�����и����������������ʱ�ر��̣߳��ͻᱨ����ʾ��ʱ�������Ѿ��رգ�����
				jk.add(client);
				ThreadClient ct=new ThreadClient(client,jk);
		    	  ct.start();				
			}
			catch (IOException e) {
			
				if(server.isClosed())//�ҵ�Ŀ�ľ�������������������Ϊ�յ���������ܲ��׳���������ֻҪ��������д��������
					break;
				e.printStackTrace();
			} 
	    	  
		 }
		 System.out.println("��ʱ�Ѿ�������������while��true��");
	}
}
