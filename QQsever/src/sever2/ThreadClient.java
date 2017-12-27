package sever2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadClient extends Thread {

	
	private Socket client;
	private OutputStream ops;
    private ArrayList<Socket> jk;
    private String clientString ;
    
    public ThreadClient(Socket client,ArrayList<Socket> jk) {
		this.client = client;
		this.jk=jk;
	}
	
	public void run()
	{
		try {
			processChat(this.client);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(client==null)
			{
				jk.remove(client);
				System.out.println("�ͻ����쳣�˳���client=null");
			}
			else
			  e.printStackTrace();
		}
	}
	
	private void processChat(Socket client) throws IOException 
	{
		ops=client.getOutputStream();
		java.io.InputStream ins = client.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(ins));
		String string = "��ӭ�������뵽����������\r\n";
		byte[] b = string.getBytes();
		ops.write(b);
		
		 clientString=br.readLine();
		 if(clientString==null)
		   {
			   System.out.println("�ͻ����쳣�˳�,clientstring=null");
			   
		   }
		 else 
		 {
			 
		
		while(!clientString.equals("bye") ) 
		{
			ops=client.getOutputStream();
			System.out.println("clientstring="+clientString);
			  if( clientString.indexOf("|")==-1)
			  {
				  System.out.println("�������ָ��");
				  String ch="�밴��ָ��������Ϣ\r\n";
				  byte[] k=ch.getBytes();
				  ops.write(k);
				  clientString=br.readLine();
				  continue;
			  }
			   String typeString = type(clientString);
			   if (typeString.equals("chat")) 
			   {
			     	GroupChat(clientString);
			   } else if (typeString.equals("login"))
			   {
	           
			   }
			   clientString=br.readLine();
			   if(clientString==null)
			   {
				   System.out.println("�ͻ����쳣�˳�clientstring=null");
				   break;
			   }
		}
		 }
//		String ch="bye,too";  һ���Ӿ��˳��ˣ�����û�����������͹ص�������
//		  byte[] k=ch.getBytes();
//		  ops.write(k);
		jk.remove(client);  //ÿ���뿪֮��Ҫ�������б��������ͻ����Ƴ�������Ȼ�������Ŀͻ��˾Ͳ��ܽ�����
		client.close();
		
	}
	
	private String type(String info)
	{
		String type = info.substring(0, info.indexOf("|"));
		System.out.println("�������ϢͷΪ"+type);
		return type;
	}
	
	
	private void GroupChat(String info) throws IOException {
		String chat = info.substring(info.indexOf("|") + 1, info.indexOf("@"));
		System.out.println("���������Ϊ"+chat);
		chat="someone say:"+chat+"\r\n";//ÿ�����������̨���ﶼҪ���ϻ��У���Ȼ��dos�оͲ�����дһ��������
		byte b[] = chat.getBytes();
		for(int i=0;i<jk.size();i++)
		{
			
			ops = jk.get(i).getOutputStream(); // �õ�һ�����/����������
			ops.write(b);
			ops.flush();
		}
	}
}
