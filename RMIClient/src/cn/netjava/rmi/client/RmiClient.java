package cn.netjava.rmi.client;

import java.rmi.Naming;
//必须用到服务器端定义的远程接口:
import cn.netjava.rmi.share.IRMIService;

/**
 * RMI调用客户端示例
 * 
 * @author www.NetJava.cn
 */
public class RmiClient {
	public static void main(String[] args) {
		int listerPort = 9090;// 设置RMI监听器在9911端口
		String serverIP = "localhost";// RMI服务器IP
		String serviceObjName = "service";// 服务器端导出的对象名字
		try {
			// 查找服务器上的服务对象,取得远程对象
			Object romoteObj = Naming.lookup("rmi://" + serverIP + ":"
					+ listerPort + "/" + serviceObjName);
			// 转型为接口类型
			IRMIService stub = (IRMIService) romoteObj;
			for (int i = 0; i < 100; i++) {
				// 调用对象的服务方法测试
				Object response = stub.service("请说话....." + i);//这个是发送给服务端的数据
				System.out.println("RMI服务器应答:" + response.toString());//response就是这个函数的返回
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}