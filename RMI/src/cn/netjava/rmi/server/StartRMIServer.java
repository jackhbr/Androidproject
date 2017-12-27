package cn.netjava.rmi.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * ����RMI������,����������� �ȴ��ͻ���������!
 * 
 * @author www.NetJava.cn
 */
public class StartRMIServer {
	/** �������� */
	public static void main(String args[]) {
		int listerPort = 9090; // ����RMI��������������9911�˿�
		String serverIP = "localhost"; // ����RMI��������IP
		String serviceObjName = "service"; // Ҫ�������ͻ��˵Ķ�������
		try {
			// 1.����RMI�����������˿�
			LocateRegistry.createRegistry(listerPort);
			// ������־����,��ӡ������̨
			IRMIServiceImp.setLog(System.out);
			// 2.���������Ķ���
			IRMIServiceImp remoteObj = new IRMIServiceImp();
			// 3.�������õ�Զ�̶��������ְ󶨵��������˿���
			Naming.rebind("rmi://" + serverIP + ":" + listerPort + "/"
					+ serviceObjName, remoteObj);
			System.out.println("RMI������" + serverIP + ": " + listerPort
					+ " ������Ϊ: " + serviceObjName);
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}