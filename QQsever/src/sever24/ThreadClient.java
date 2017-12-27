package sever24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JList;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
/**
 * ��������ڶԵ����Ŀͻ��˽���ͨ��
 * @author jack
 *
 */

public class ThreadClient extends Thread {

	ServerSocket server;
	private Socket clientSocket;
	private OutputStream ops;
    private ArrayList<Socket> jk;
    private String clientString ;
    private ArrayList<Client> client;
    private Client cli;
    private JList<Client> jlist;
    private Document docs;
    private SimpleAttributeSet attrset = new SimpleAttributeSet();
    
    public ThreadClient(Socket clientSocket,ArrayList<Socket> jk,ArrayList<Client> client,JList<Client> jlist,ServerSocket server,Document docs) {
    	this.server=server;
    	this.clientSocket = clientSocket;
		this.jk=jk;
		this.client=client;
		this.jlist=jlist;  //���Կ����ýӿ�ʵ�����ൽ�߳���֮��ı������������Ͳ���ͨ����ô��εĴ�����
		this.docs=docs;
	}
	
	public void run()
	{
		try {
			processChat(this.clientSocket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(clientSocket==null)
			{
				jk.remove(clientSocket);
				System.out.println("�ͻ����쳣�˳���clientSocket=null");
			}
			else
			  e.printStackTrace();
		}
	}
	
	private void processChat(Socket clientSocket) throws IOException 
	{
		ops=clientSocket.getOutputStream();
		java.io.InputStream ins = clientSocket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(ins));
//		String string = "��ӭ�������뵽����������\r\n";
//		byte[] b = string.getBytes();
//		ops.write(b);
		
		 clientString=br.readLine();
		 if(clientString==null)
		   {
			   System.out.println("�ͻ����쳣�˳�,clientstring=null");
			   
		   }
		 else 
		 {
			 
		
		while(!clientString.equals("bye") ) 
		{
			if(server.isClosed())  //��Ȼ���ܷ�����һ�رվ����Ͽͻ���Ҳ�����˳�������һ���ͻ��˷�����һ��ָ�����ͻ��˳�
  		  {
  		    System.out.println("���������߳̽���");
  		  	break;
  		  }
			ops=clientSocket.getOutputStream();
			System.out.println("clientstring="+clientString);
			  if( clientString.indexOf("|")==-1)
			  {
				  System.out.println("�������ָ��");
				  String ch="�밴��ָ��������Ϣ\r\n";
				  byte[] k=ch.getBytes();
				  ops.write(k);
				  clientString=br.readLine();//��ÿһ�����ĺ��涼Ҫ�����������������
				  if(clientString==null)
				   {
					   System.out.println("�ͻ����쳣�˳�clientstring=null");
					   break;
				   }
				  
				  if(server.isClosed())  //��Ȼ���ܷ�����һ�رվ����Ͽͻ���Ҳ�����˳�������һ���ͻ��˷�����һ��ָ�����ͻ��˳�
		  		  {
		  		    System.out.println("���������߳̽���");
		  		  	break;
		  		  }
				  
				  continue;
			  }
			   String typeString = type(clientString);
			   if (typeString.equals("chat")) 
			   {
			     	GroupChat(clientString);
			   } else if (typeString.equals("login"))
			   {
	             LoginChat(clientString);
			   }
			   clientString=br.readLine();
			   if(clientString==null)
			   {
				   System.out.println("�ͻ����쳣�˳�clientstring=null");
				   break;
			   }
		}
		 }
		jk.remove(clientSocket);  //ÿ���뿪֮��Ҫ�������б��������ͻ����Ƴ�������Ȼ�������Ŀͻ��˾Ͳ��ܽ�����
		if(client.contains(cli))
		{
			System.out.println("cli�Ƿ�Ϊ��"+cli==null);
		client.remove(cli);
		}
		clientSocket.close();
		
	}
	
	private String type(String info)
	{
		String type = info.substring(0, info.indexOf("|"));
		System.out.println("�������ϢͷΪ"+type);
		return type;
	}
	
	
	private void GroupChat(String info) throws IOException {
		String chat = info.substring(info.indexOf("|") + 1, info.indexOf("@"));
		System.out.println("Ⱥ�����������Ϊ"+chat);
		String chat1=cli.getName()+"say:"+chat+"\r\n";//ÿ�����������̨���ﶼҪ���ϻ��У���Ȼ��dos�оͲ�����дһ��������
		byte b[] = chat1.getBytes();
		
		Date date=new Date();
		 DateFormat format1=new SimpleDateFormat("HH:mm:ss");
		 String time1=format1.format(date);
		
		 StyleConstants.setFontSize(attrset,20);
			try {
	            docs.insertString(docs.getLength(), cli.getName()+"  "+time1+"\n", attrset);//���ı�����׷��
	            docs.insertString(docs.getLength(), chat+"\n\n\n", attrset);//���ı�����׷��
	        } catch (BadLocationException e) {  //  \n���ڻ���
	            e.printStackTrace();
	        }
		
		for(int i=0;i<jk.size();i++)
		{
			
			ops = jk.get(i).getOutputStream(); // �õ�һ�����/����������
			ops.write(b);
			ops.flush();
		}
	}
	
	private void LoginChat(String info) 
	{
		System.out.println("�����˵�¼״̬");
		String useAcount = info.substring(info.indexOf("|") + 1, info.indexOf("!"));
		System.out.println("������˻���"+useAcount);
		String usePassWord = info.substring(info.indexOf("!") + 1, info.indexOf("#"));
		System.out.println("�����������"+usePassWord);
		 cli=MySql.getClient(useAcount);
		if(cli==null)
		{
			System.out.println("�����ڸÿͻ�");
		}
		else if(cli.getPassWord().equals(usePassWord)){
			System.out.println("��¼�ɹ�");
			System.out.println("���û����û�����"+cli.getName());
			client.add(cli);
			
			jlistmodel jm=new jlistmodel(client);//��̬���jlist
			jlist.setModel(jm);
			System.out.println("��ʱ��¼���ߵ�����Ϊ"+client.size());
			
			
			 StyleConstants.setFontSize(attrset,20);
			try {
	            docs.insertString(docs.getLength(), cli.getName()+"������\n", attrset);//���ı�����׷��
	        } catch (BadLocationException e) {  //  \n���ڻ���
	            e.printStackTrace();
	        }
			
		}else {
			System.out.println("������������");
		}
		
	}
}
