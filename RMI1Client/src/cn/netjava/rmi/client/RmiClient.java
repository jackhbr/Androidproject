package cn.netjava.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
//�����õ��������˶����Զ�̽ӿ�:
import cn.netjava.rmi.share.IRMIService;
import cn.netjava.rmi.share.IUser;

/**
 * RMI���ÿͻ���ʾ��
 * 
 * @author www.NetJava.cn
 */
public class RmiClient {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		int listerPort = 9090;// ����RMI��������9911�˿�
		String serverIP = "localhost";// RMI������IP
		String serviceObjName = "service";// �������˵����Ķ�������
		// ���ҷ������ϵķ������,ȡ��Զ�̶���
		Object romoteObj = Naming.lookup("rmi://" + serverIP + ":"
		+ listerPort + "/" + serviceObjName);
		// ת��Ϊ�ӿ�����
		IRMIService stub = (IRMIService) romoteObj;
		// ���÷�������ȡ���û��б�ķ���������IUser���͵Ķ���
		//��Щ����ľ��巽�������ڷ�������ʵ�ֵġ�
		List<IUser> users=stub.getAllUser();
		System.out.println("RMI�����������û��������:\r\n"
		+ users.toString());
	}
}