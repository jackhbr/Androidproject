package f2;

import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

/**
* 简单服务器实现
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
		ServerSocket server=new ServerSocket(port);
		System.out.println("服务器创建成功！ "+port);
		while(true){ //让服务器循环等待
		Socket client=server.accept();
		//从连接对象上得到输入输出流对象
		java.io.OutputStream out= client.getOutputStream();
		java.io.InputStream ins= client.getInputStream();
		String s="你好,欢迎来javaKe.com\r\n";
		byte[] data=s.getBytes();//取得组成这个字符串的字节
		out.write(data); //用输出对象发送数据
		out.flush();//强制输出
		int in=0; //一个字节一个字节的读取客户机的输入
		while(in!=13){//如果读到不是13,即回车字符
		in=ins.read();
		char a=(char) in;  //这句话把ascii码转化为了真正的字符
		System.out.println("读到的一个是: "+a);
		}
		System.out.println("客户机按了回车,退出:"+in);
		client.close();//半闭与这个客户机的连接
		}
		}catch(Exception ef){ef.printStackTrace();}
		}
//主函数
public static void main(String[] args) {
		ChatServer cs=new ChatServer();
		cs.setUpServer(9090);
}
}