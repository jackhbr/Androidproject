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
		// 在线程run中调用处理连接的方法
		try {
			processChat(this.client);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 处理方法执行完毕后，线程自已即退出...
	}

	private void processChat(Socket client) throws IOException {
		ops=client.getOutputStream();
		java.io.InputStream ins = client.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(ins));
		String string = "欢迎来到我的温馨小屋\n\r";
		byte[] b = string.getBytes();
		ops.write(b);
		//String clientString=br.readLine();
		///if(br.readLine()!="Chat|bye@"){  //这个readline()会一直停在这里，除非它接收到新的信息，所以如果是if那么就得执行两次
		//System.out.println("realline前面的");
		String clientString=br.readLine();
		//System.out.println("realline后面的");
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
		System.out.println("输入的消息头为"+type);
		return type;
	}

	private void GroupChat(String info) throws IOException {
		String chat = info.substring(info.indexOf("|") + 1, info.indexOf("@"));
		System.out.println("输入的内容为"+chat);
		// BufferedWriter writer=new BufferedWriter(new
		// OutputStreamWriter(ops));
		// writer.write("棅融说了"+chat);
		chat="someone say:"+chat+"\r\n";//每次输出到控制台这里都要加上换行，不然在dos中就不好再写一次命令了
		byte b[] = chat.getBytes();
		for(int i=0;i<jk.size();i++)
		{
			
			ops = jk.get(i).getOutputStream(); // 得到一个输出/输入流对象
			ops.write(b);
			ops.flush();
		}
		
		//ops.close();  //这个输出流不能关闭！！！不然关闭后就全部输入输出就关闭了，就不能继续交流了
	}
}
