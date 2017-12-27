package cn.netjava.rmi.server;

import java.util.ArrayList;
import java.util.List;
import cn.netjava.rmi.share.IRMIService;
import cn.netjava.rmi.share.IUser;
import cn.netjava.rmi.share.UserImp;

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
		System.out.println("RMI客户机请求: " + obj);
		// 加上服务器的时间标志返回
		return System.currentTimeMillis() / 1000 + ":" + obj.toString();
	}

	// 实现接口中取得用户列表的方法
	// 远程调用时,将返回用户对象列表
	public List<IUser> getAllUser() {
		List<IUser> users = new ArrayList();
		for (int i = 0; i < 10; i++) {
			IUser u = new UserImp(i, "用户" + i);
			users.add(u);
		}
		return users;
	}
}
