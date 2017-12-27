package sever22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
/**
 * ��������ڶԵ����Ŀͻ��˽���ͨ��
 * @author jack
 *
 */

public class ThreadClient extends Thread {

	
	private Socket clientSocket;
	private OutputStream ops;
    private ArrayList<Socket> jk;
    private String clientString ;
    private ArrayList<Client> client;
    private Client cli;
    
    public ThreadClient(Socket clientSocket,ArrayList<Socket> jk,ArrayList<Client> client) {
		this.clientSocket = clientSocket;
		this.jk=jk;
		this.client=client;
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
		chat="someone say:"+chat+"\r\n";//ÿ�����������̨���ﶼҪ���ϻ��У���Ȼ��dos�оͲ�����дһ��������
		byte b[] = chat.getBytes();
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
			System.out.println("��ʱ��¼���ߵ�����Ϊ"+client.size());
			
		}else {
			System.out.println("������������");
		}
		
	}
}
