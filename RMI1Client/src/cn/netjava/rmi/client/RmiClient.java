package cn.netjava.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
//必须用到服务器端定义的远程接口:
import cn.netjava.rmi.share.IRMIService;
import cn.netjava.rmi.share.IUser;

/**
 * RMI调用客户端示例
 * 
 * @author www.NetJava.cn
 */
public class RmiClient {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		int listerPort = 9090;// 设置RMI监听器在9911端口
		String serverIP = "localhost";// RMI服务器IP
		String serviceObjName = "service";// 服务器端导出的对象名字
		// 查找服务器上的服务对象,取得远程对象
		Object romoteObj = Naming.lookup("rmi://" + serverIP + ":"
		+ listerPort + "/" + serviceObjName);
		// 转型为接口类型
		IRMIService stub = (IRMIService) romoteObj;
		// 调用服务器上取得用户列表的方法，返回IUser类型的对象
		//这些对象的具体方法，是在服务器端实现的。
		List<IUser> users=stub.getAllUser();
		System.out.println("RMI服务器返回用户对象表列:\r\n"
		+ users.toString());
	}
}