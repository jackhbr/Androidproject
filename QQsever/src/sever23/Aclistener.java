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
		
		
		
		
		if(type.equals("����������"))
		{		
				if(server==null)
				{
				try {
					server=new ServerSocket(9090);
					System.out.println("������9090�˿ڴ����ɹ���");
					 Threaded ct=new Threaded(server,client,jlist);
			    	  ct.start();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}		
				}			
		    	 JButton jjButton=(JButton) e.getSource();
		    	 jjButton.setText("�رշ�����");

		}
		else if(type.equals("����"))
		{
			Client cli=new Client();
			cli.setAcount("555555");
			cli.setName("�������ƽ��");
			cli.setPassWord("bbbbbb");
			client.add(cli);
			jlistmodel jm=new jlistmodel(client);
			jlist.setModel(jm);
			
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
				server=null;  //�ص��������ǲ����������µĿͻ��ˣ�����ԭ���Ŀͻ��˻��ǿ�������ͨ��
				System.out.println("��ʱ��sever="+server);
				JButton jjButton=(JButton) e.getSource();
		    	 jjButton.setText("����������");
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
