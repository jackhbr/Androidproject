package cn.netjava.rmi.client;

import java.rmi.Naming;
//�����õ��������˶����Զ�̽ӿ�:
import cn.netjava.rmi.share.IRMIService;

/**
 * RMI���ÿͻ���ʾ��
 * 
 * @author www.NetJava.cn
 */
public class RmiClient {
	public static void main(String[] args) {
		int listerPort = 9090;// ����RMI��������9911�˿�
		String serverIP = "localhost";// RMI������IP
		String serviceObjName = "service";// �������˵����Ķ�������
		try {
			// ���ҷ������ϵķ������,ȡ��Զ�̶���
			Object romoteObj = Naming.lookup("rmi://" + serverIP + ":"
					+ listerPort + "/" + serviceObjName);
			// ת��Ϊ�ӿ�����
			IRMIService stub = (IRMIService) romoteObj;
			for (int i = 0; i < 100; i++) {
				// ���ö���ķ��񷽷�����
				Object response = stub.service("��˵��....." + i);//����Ƿ��͸�����˵�����
				System.out.println("RMI������Ӧ��:" + response.toString());//response������������ķ���
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}