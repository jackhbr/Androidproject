package saveuserdata;

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
		
		String clientString=br.readLine();
		while(!clientString.equals("bye")) 
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
			   if (typeString.equals("chat"))   //Ⱥ��
			   {
			     	GroupChat(clientString);
			   } else if (typeString.equals("login"))
			   {
	              getlogin(clientString);
			   }
			   clientString=br.readLine();
		}
		String ch="bye,too";
		  byte[] k=ch.getBytes();
		  ops.write(k);
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
	
	private void getlogin(String info) 
	{
		String account=info.substring(info.indexOf("|")+1,info.indexOf("#")); //��������˻���
		String password=info.substring(info.indexOf("#")+1,info.indexOf("$"));//�����������
		System.out.println("������˻�����"+account);
		System.out.println("�����������"+password);
	}
}
