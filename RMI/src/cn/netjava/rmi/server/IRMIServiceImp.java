package cn.netjava.rmi.server;

import cn.netjava.rmi.share.IRMIService;

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
		System.out.println("RMI�ͻ�������: " + obj);  //������ڷ������Լ��Ŀ���̨�����
		// ���Ϸ�������ʱ���־����
		return System.currentTimeMillis() / 1000 + ": " + obj.toString();  //������ڷ��ظ��ͻ����ϣ��ͻ�������ʾ
	}
}