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
		
	
		
		if(type.equals("����������"))
		{
			
				if(server==null)
				{
				try {
					server=new ServerSocket(9090);
					System.out.println("������9090�˿ڴ����ɹ���");
					  ct=new Threaded(server);
			    	  ct.start();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				}
				
		    	 JButton jjButton=(JButton) e.getSource();
		    	 jjButton.setText("�رշ�����");
				
				
		
		}
		else if(type.equals("����"))
		{
			System.out.println("��ʱ�޴˹���");
		}
		else if(type.equals("����"))
		{
			System.out.println("��ʱ�޴˹���");
		}
		else if(type.equals("�رշ�����"))
		{
			try {
				server.close();//server�Ƕ�������������ر��˾���ȫ���߳��ж������յ������Ϣ
				
				server=null;//server=null��Ϊ����һ�������¿������������������server��ָ������һ�������ˣ������߳̾Ͳ��ٽ������server�Ķ�����
				System.out.println("��ʱ��sever="+server);    //���������Ѿ����Դﵽ�Լ���Ŀ���ˣ�close����ȥ�ˣ�nullֻ���������þͿ�����
				JButton jjButton=(JButton) e.getSource();
		    	 jjButton.setText("����������");
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
	}

}
