package GUI1interruptstop;

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
		while(!isInterrupted())
		 { //�÷�����ѭ���ȴ�
	    	  Socket client;
	    	  if(server.isClosed())
	    		  {
	    		    System.out.println("���������߳̽���");
	    		  	break;
	    		  }
			try {
				System.out.println("������Ƿ�ر�"+server.isClosed());
//				if(!server.isClosed())  //��������û�йرյĻ����ͼ����ȴ��ͻ��˵Ľ���
//				{
				client = server.accept();//�����и����������������ʱ�ر��̣߳��ͻᱨ����ʾ��ʱ�������Ѿ��رգ�����
				jk.add(client);
				ThreadClient ct=new ThreadClient(client,jk);
		    	  ct.start();
//				}
				
			}
			//������ôʹ�����interrupt�жϰ�������???���accept��Ӧ�ľ���ioexception,
			//�����ô������
			catch (IOException e) {
			
				if(server.isClosed())//�ҵ�Ŀ�ľ�������������������Ϊ�յ���������ܲ��׳���������ֻҪ��������д��������
					break;
				e.printStackTrace();
			} 
	    	  
		 }
		 System.out.println("��ʱ�Ѿ�������������while��true��");
	}
}
