package f4;

import java.io.InputStream;
import java.io.OutputStream;

/**
* 简单Echo服务器实现
* @author 蓝杰 胡东峰
*/
public class ChatServer {
/**
* 在指定端口上启动一个服务器
* @param port:服务器所以的端口
*/
public void setUpServer(int port){
	try{
	//建立绑定在指定端口上的服务器对象
			java.net.ServerSocket server=new java.net.ServerSocket(port);
			System.out.println("服务器创建成功！ "+port);
			//当有客户机连接上时，等待方法就会返回，返回一个代表与客户机连接的对象
			while(true){ //让服务器进入循环等待状态
			java.net.Socket client=server.accept();
			System.out.println("Incoming clieng:"+client.getRemoteSocketAddress());  //获取客户端的地址和端口号
			
			//调用处理连接对象的方法去处理连接对象
			processChat(client);
	}
	}catch(Exception ef){
	ef.printStackTrace();
	}
	}
/**
* 处理连接对象：读取客户机发来的字符串，回送给客户机
* @param client:与客户机的连接对象
*/
private void processChat(java.net.Socket client) throws Exception{
		OutputStream out=client.getOutputStream();
		InputStream ins=client.getInputStream();
		String s="你好，欢迎来到服务器！ \r\n";
		byte[] data=s.getBytes();//取得组成这个字符串的字节
		out.write(data); //用输出对象发送！
		out.flush();//强制输出
		//调用读取字符串的方法，从输入流中读取一个字符串
		String inputS=readString(ins);      //居然又写了一个方法。。。还不如用bufferreadder的readline呢
		while(!inputS.equals("bye")){       //注意这个是字符串，得用equals，不能用==
		System.out.println("客户机说: "+inputS);
		s="服务器收到:"+inputS+"\r\n";
		data=s.getBytes();//取得组成这个字符串的字节数组
		out.write(data); //用输出流对象发送！
		out.flush();//强制输出
		inputS=readString(ins); //读取客户机的下一次输入
		}
		s="你好，欢迎再来！ \r\n";
		data=s.getBytes();
		out.write(data);
		out.flush();
		client.close();//半闭与客户机的连接
}
/**
* 从输入流对象中读取字节，拼成一个字符串返加
* 如果读到一个字节值为13,则认为以前的是一个字符串
* @param ins:输入流对象
* @return :从流上（客户机发来的)读到的字符串
*/
private String readString(InputStream ins)throws Exception{
//创建一个字符串缓冲区
		StringBuffer stb=new StringBuffer();
		char c =0;
		while(c!=13){
		//遇到一个换行，就是一句话
		int i= ins.read();//读取客户机发来的一个字节
		c=(char)i;//将输入的字节转换为一个Char
		stb.append(c);//将读到的一个字符加到字符串缓冲区中
		}
		//将读到的字节组转为字符串,并调用trim去掉尾部的空格
		String inputS=stb.toString().trim();   //这个没有接触过，感觉有必要重视一下！！！就是防止你在后面加上空格，导致显示得很不好看
		//String inputS=stb.toString();
		return inputS;
}
//程序入口
	public static void main(String[] args) {
		ChatServer cs=new ChatServer();
		cs.setUpServer(9090);
	}
}