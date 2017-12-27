package cn.netjava.rmi.server;

import java.util.ArrayList;
import java.util.List;
import cn.netjava.rmi.share.IRMIService;
import cn.netjava.rmi.share.IUser;
import cn.netjava.rmi.share.UserImp;

/**
 * RMI�������ӿ�ʵ��, �˶��󽫱�RMI������������ΪԶ�̷������
 * 
 * @author www.NetJava.cn javaFound
 */
public class IRMIServiceImp extends java.rmi.server.UnicastRemoteObject
		implements IRMIService {
	// ������д�޲ι�����,���׳�ָ���쳣
	public IRMIServiceImp() throws java.rmi.RemoteException {
	}

	/**
	 * ��������ʵ�ֵ�Ҫ��Զ�̵��õķ���
	 */
	public Object service(Object obj) {
		System.out.println("RMI�ͻ�������: " + obj);
		// ���Ϸ�������ʱ���־����
		return System.currentTimeMillis() / 1000 + ":" + obj.toString();
	}

	// ʵ�ֽӿ���ȡ���û��б�ķ���
	// Զ�̵���ʱ,�������û������б�
	public List<IUser> getAllUser() {
		List<IUser> users = new ArrayList();
		for (int i = 0; i < 10; i++) {
			IUser u = new UserImp(i, "�û�" + i);
			users.add(u);
		}
		return users;
	}
}
