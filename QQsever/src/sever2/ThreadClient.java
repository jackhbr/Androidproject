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
				System.out.println("客户端异常退出，client=null");
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
		String string = "欢迎来到接入到服务器当中\r\n";
		byte[] b = string.getBytes();
		ops.write(b);
		
		 clientString=br.readLine();
		 if(clientString==null)
		   {
			   System.out.println("客户端异常退出,clientstring=null");
			   
		   }
		 else 
		 {
			 
		
		while(!clientString.equals("bye") ) 
		{
			ops=client.getOutputStream();
			System.out.println("clientstring="+clientString);
			  if( clientString.indexOf("|")==-1)
			  {
				  System.out.println("进入错误指令");
				  String ch="请按照指令输入信息\r\n";
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
				   System.out.println("客户端异常退出clientstring=null");
				   break;
			   }
		}
		 }
//		String ch="bye,too";  一下子就退出了，别人没看出这个输出就关掉窗口了
//		  byte[] k=ch.getBytes();
//		  ops.write(k);
		jk.remove(client);  //每次离开之后都要在在线列表里把这个客户端移除掉，不然后面来的客户端就不能交流了
		client.close();
		
	}
	
	private String type(String info)
	{
		String type = info.substring(0, info.indexOf("|"));
		System.out.println("输入的消息头为"+type);
		return type;
	}
	
	
	private void GroupChat(String info) throws IOException {
		String chat = info.substring(info.indexOf("|") + 1, info.indexOf("@"));
		System.out.println("输入的内容为"+chat);
		chat="someone say:"+chat+"\r\n";//每次输出到控制台这里都要加上换行，不然在dos中就不好再写一次命令了
		byte b[] = chat.getBytes();
		for(int i=0;i<jk.size();i++)
		{
			
			ops = jk.get(i).getOutputStream(); // 得到一个输出/输入流对象
			ops.write(b);
			ops.flush();
		}
	}
}
