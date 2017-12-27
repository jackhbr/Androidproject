package protocol;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import org.omg.CORBA.portable.InputStream;

public class ChatThread0923 extends Thread {
	private Socket client;
	private OutputStream ops;
    private ArrayList<Socket> jk;
	
	public ChatThread0923(Socket client,ArrayList<Socket> jk) {
		this.client = client;
		this.jk=jk;
	}

	public void run() {
		// ���߳�run�е��ô������ӵķ���
		try {
			processChat(this.client);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ������ִ����Ϻ��߳����Ѽ��˳�...
	}

	private void processChat(Socket client) throws IOException {
		ops=client.getOutputStream();
		java.io.InputStream ins = client.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(ins));
		String string = "��ӭ�����ҵ���ܰС��\n\r";
		byte[] b = string.getBytes();
		ops.write(b);
		//String clientString=br.readLine();
		///if(br.readLine()!="Chat|bye@"){  //���readline()��һֱͣ��������������յ��µ���Ϣ�����������if��ô�͵�ִ������
		//System.out.println("reallineǰ���");
		String clientString=br.readLine();
		//System.out.println("realline�����");
		while(clientString!="Chat|bye@")
		{
			
			   String typeString = type(clientString);
			   if (typeString.equals("Chat")) 
			   {
			     	GroupChat(clientString);
			   } else if (typeString.equals("login"))
			   {
	
			   }
			   clientString=br.readLine();
		}
		client.close();

	}

	private String type(String info) {
		String type = info.substring(0, info.indexOf("|"));
		System.out.println("�������ϢͷΪ"+type);
		return type;
	}

	private void GroupChat(String info) throws IOException {
		String chat = info.substring(info.indexOf("|") + 1, info.indexOf("@"));
		System.out.println("���������Ϊ"+chat);
		// BufferedWriter writer=new BufferedWriter(new
		// OutputStreamWriter(ops));
		// writer.write("����˵��"+chat);
		chat="someone say:"+chat+"\r\n";//ÿ�����������̨���ﶼҪ���ϻ��У���Ȼ��dos�оͲ�����дһ��������
		byte b[] = chat.getBytes();
		for(int i=0;i<jk.size();i++)
		{
			
			ops = jk.get(i).getOutputStream(); // �õ�һ�����/����������
			ops.write(b);
			ops.flush();
		}
		
		//ops.close();  //�����������ܹرգ�������Ȼ�رպ��ȫ����������͹ر��ˣ��Ͳ��ܼ���������
	}
}
