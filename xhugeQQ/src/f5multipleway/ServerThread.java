package f5multipleway;

import java.io.InputStream;
import java.io.OutputStream;

import f4.ChatServer;

/**
* 服务器端处理与客户机连接对象的线程类封装
* @author 蓝杰 胡东峰
*/
public class ServerThread extends Thread{
private java.net.Socket client;//线程对象要处理的连接变量
private OutputStream out;//输出流对象
/**创建这个线程对象时，传入一个它要处理的连接对象*/
public ServerThread(java.net.Socket sc){
		this.client=sc;
		}
//将发送消息的代码包装到一个方法中
public void sendMsg2Me(String msg)throws Exception{
		byte[] data=msg.getBytes();
		out.write(data); //用输出对象发送！
		out.flush();//强制输出
}
public void run(){
	//在线程run中调用处理连接的方法
	processChat(this.client);
	//处理方法执行完毕后，线程自已即退出...
}
//处理客户机进入的连接对象
private void processChat(java.net.Socket client){
try{
	out=client.getOutputStream(); //得到一个输出/输入流对象
	InputStream ins=client.getInputStream();
	String s="你好，欢迎来到服务器！ \r\n";
	this.sendMsg2Me(s);//发送消息
	String inputS=readString(ins);//读取客户机发来的
	while(!inputS.equals("bye")){
	s="我收到了你的话啦 "+inputS+"\r\n";
	this.sendMsg2Me(s); //将这个字符串发送给客户机对象
	inputS=readString(ins);//再次读取
	}
	s="你好，欢迎再来！ \r\n";
	this.sendMsg2Me(s);
	client.close();
	}catch(Exception ef){
	ef.printStackTrace();
	}
	}
	/**
	* 从输入流对象中读取字节，拼成一个字符串返加
	* 如果读到一个字节值为13,则认为以前的是一个字符串
	* @param ins:输入流对象
	* @return :从流上（客户机发来的)读到的字符串
	*/
	private String readString(InputStream ins) throws Exception{
		StringBuffer stb=new StringBuffer();//创建一个字符串缓冲区
		char c =0;
		while(c!=13){
		//遇到一个换行，就是一句话
		int i= ins.read();//读取客户机发来的一个字节
		c=(char)i;//将输入的字节转换为一个Char
		stb.append(c);
		}
		//将读到的字节组转为字符串
		String inputS=stb.toString().trim();
		return inputS;
	}
	


}