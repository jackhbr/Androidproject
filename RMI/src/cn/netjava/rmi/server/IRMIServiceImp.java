package cn.netjava.rmi.server;

import cn.netjava.rmi.share.IRMIService;

/**
 * RMI服务对象接口实现, 此对象将被RMI服务器导出做为远程服务对象
 * 
 * @author www.NetJava.cn javaFound
 */
public class IRMIServiceImp extends java.rmi.server.UnicastRemoteObject
		implements IRMIService {
	// 必须重写无参构造器,并抛出指定异常
	public IRMIServiceImp() throws java.rmi.RemoteException {
	}

	/**
	 * 服务器端实现的要被远程调用的方法
	 */
	public Object service(Object obj) {
		System.out.println("RMI客户机请求: " + obj);  //这个是在服务器自己的控制台上输出
		// 加上服务器的时间标志返回
		return System.currentTimeMillis() / 1000 + ": " + obj.toString();  //这个是在返回给客户端上，客户端上显示
	}
}