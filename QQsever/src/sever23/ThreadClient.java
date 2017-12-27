package sever23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JList;
/**
 * 这个类用于对单独的客户端进行通信
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
    
    public ThreadClient(Socket clientSocket,ArrayList<Socket> jk,ArrayList<Client> client,JList<Client> jlist,ServerSocket server) {
    	this.server=server;
    	this.clientSocket = clientSocket;
		this.jk=jk;
		this.client=client;
		this.jlist=jlist;  //可以考虑用接口实现主类到线程类之间的变量共享，这样就不用通过那么多次的传递了
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
				System.out.println("客户端异常退出，clientSocket=null");
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
//		String string = "欢迎来到接入到服务器当中\r\n";
//		byte[] b = string.getBytes();
//		ops.write(b);
		
		 clientString=br.readLine();
		 if(clientString==null)
		   {
			   System.out.println("客户端异常退出,clientstring=null");
			   
		   }
		 else 
		 {
			 
		
		while(!clientString.equals("bye") ) 
		{
			if(server.isClosed())  //虽然不能服务器一关闭就马上客户端也跟着退出，但是一但客户端发送玩一条指令它就会退出
  		  {
  		    System.out.println("服务器的线程结束");
  		  	break;
  		  }
			ops=clientSocket.getOutputStream();
			System.out.println("clientstring="+clientString);
			  if( clientString.indexOf("|")==-1)
			  {
				  System.out.println("进入错误指令");
				  String ch="请按照指令输入信息\r\n";
				  byte[] k=ch.getBytes();
				  ops.write(k);
				  clientString=br.readLine();//在每一个读的后面都要加上下面这个。。。
				  if(clientString==null)
				   {
					   System.out.println("客户端异常退出clientstring=null");
					   break;
				   }
				  
				  if(server.isClosed())  //虽然不能服务器一关闭就马上客户端也跟着退出，但是一但客户端发送玩一条指令它就会退出
		  		  {
		  		    System.out.println("服务器的线程结束");
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
				   System.out.println("客户端异常退出clientstring=null");
				   break;
			   }
		}
		 }
		jk.remove(clientSocket);  //每次离开之后都要在在线列表里把这个客户端移除掉，不然后面来的客户端就不能交流了
		if(client.contains(cli))
		{
			System.out.println("cli是否为空"+cli==null);
		client.remove(cli);
		}
		clientSocket.close();
		
	}
	
	private String type(String info)
	{
		String type = info.substring(0, info.indexOf("|"));
		System.out.println("输入的消息头为"+type);
		return type;
	}
	
	
	private void GroupChat(String info) throws IOException {
		String chat = info.substring(info.indexOf("|") + 1, info.indexOf("@"));
		System.out.println("群聊输入的内容为"+chat);
		chat="someone say:"+chat+"\r\n";//每次输出到控制台这里都要加上换行，不然在dos中就不好再写一次命令了
		byte b[] = chat.getBytes();
		for(int i=0;i<jk.size();i++)
		{
			
			ops = jk.get(i).getOutputStream(); // 得到一个输出/输入流对象
			ops.write(b);
			ops.flush();
		}
	}
	
	private void LoginChat(String info) 
	{
		System.out.println("进入了登录状态");
		String useAcount = info.substring(info.indexOf("|") + 1, info.indexOf("!"));
		System.out.println("输入的账户是"+useAcount);
		String usePassWord = info.substring(info.indexOf("!") + 1, info.indexOf("#"));
		System.out.println("输入的密码是"+usePassWord);
		 cli=MySql.getClient(useAcount);
		if(cli==null)
		{
			System.out.println("不存在该客户");
		}
		else if(cli.getPassWord().equals(usePassWord)){
			System.out.println("登录成功");
			System.out.println("该用户的用户名是"+cli.getName());
			client.add(cli);
			
			jlistmodel jm=new jlistmodel(client);//动态添加jlist
			jlist.setModel(jm);
			System.out.println("此时登录上线的人数为"+client.size());
			
		}else {
			System.out.println("输入的密码错误");
		}
		
	}
}
