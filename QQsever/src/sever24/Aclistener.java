package sever24;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;



public class Aclistener implements ActionListener {
	ServerSocket server=null;
	private ArrayList<Client> client;
	private JList<Client> jlist;
	  private Document docs;
	public Aclistener(ArrayList<Client> client,JList<Client> jlist,Document docs) {
	this.client=client;
	this.jlist=jlist;
	this.docs=docs;
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
					 Threaded ct=new Threaded(server,client,jlist,docs);
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
			SimpleAttributeSet attrset = new SimpleAttributeSet(); //������λ���ǰ�Java�Դ����඼д��һ�飬��ʵ���ֻҪ���Դ��ľͿ�����
	        StyleConstants.setFontSize(attrset,24);
	        StyleConstants.setItalic(attrset, true);
	        try {
	            docs.insertString(docs.getLength(), "Ҫ������\n", attrset);//���ı�����׷��
	        } catch (BadLocationException e1) {  //  \n���ڻ���
	            e1.printStackTrace();
	        }
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
