package cn.netjava.rmi.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * 启动RMI服务器,导出服务对象 等待客户机来调用!
 * 
 * @author www.NetJava.cn
 */
public class StartRMIServer {
	/** 启动方法 */
	public static void main(String args[]) {
		int listerPort = 9090; // 设置RMI服务器监听器在9911端口
		String serverIP = "localhost"; // 设置RMI服务器的IP
		String serviceObjName = "service"; // 要导出到客户端的对象名字
		try {
			// 1.设置RMI服务器监听端口
			LocateRegistry.createRegistry(listerPort);
			// 设置日志对象,打印到控制台
			IRMIServiceImp.setLog(System.out);
			// 2.创建导出的对象
			IRMIServiceImp remoteObj = new IRMIServiceImp();
			// 3.将创建好的远程对象与名字绑定到服务器端口上
			Naming.rebind("rmi://" + serverIP + ":" + listerPort + "/"
					+ serviceObjName, remoteObj);
			System.out.println("RMI启动在" + serverIP + ": " + listerPort
					+ " 服务名为: " + serviceObjName);
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}