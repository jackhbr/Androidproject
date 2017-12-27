package f7client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * �������пͻ���ʵ��: ��¼���������������ж�ȡ�û����뷢�͸�������
 * 
 * @author ���� ������
 */
public class NetClient extends Thread {
	private String serverIp;// ������IP
	private int port;// �������˿�
	private OutputStream ous;// �������������������
	private BufferedReader brd;// ��������������������
	// �������������ͻ�������ʱ�����������IP�Ͷ˿�

	public NetClient(String serverIp, int port) {
		this.serverIp = serverIp;
		this.port = port;
	}

	// 1.�����Ϸ�����,�Ƿ����ӳɹ�
	public boolean conn2Server() {
		try {
			// ����һ�����������˵�Socket����
			Socket client = new Socket(this.serverIp, this.port);
			InputStream ins = client.getInputStream();// �õ��������������
			// ���Զ�ȡһ���ַ���,Ҳ������\r\n��β���ַ���
			brd = new BufferedReader(new InputStreamReader(ins));
			ous = client.getOutputStream();
			return true;
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		return false;
	}

	/**
	 * 2.��¼������
	 * 
	 * @param name
	 *            :�û���
	 * @param pwd
	 *            :����
	 * @return: �Ƿ��¼�ɹ�
	 */
	public boolean loginServer(String name, String pwd) {
		try {
			// 1.��ȡ������������һ����Ϣ
			String input = brd.readLine();
			System.out.println("������˵: " + input);
			// 2.���շ��������̣������û���������
			name += "\r\n";// ����ʱ�ַ�������������\r\n !!!
			ous.write(name.getBytes());
			ous.flush();
			System.out.println("�ͻ����ѽ��û������ͣ��ȷ�������Ӧ");
			input = brd.readLine();
			System.out.println("������˵: " + input);
			pwd += "\r\n";
			ous.write(pwd.getBytes());
			ous.flush();
			return true;
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		return false;
	}

	// �߳��ж�ȡ��������������Ϣ
	public void run() {
		while (true) {
			readFromServer();
		}
	}

	// 3.�ӷ�������ȡ��Ϣ����������������������ڶ����߳���
	public void readFromServer() {
		try {
			String input = brd.readLine();
			System.out.println("������˵: " + input);
		} catch (Exception ef) {
			ef.printStackTrace();
		}
	}

	/** ����һ����Ϣ���������ķ��� */
	public void sendMsg(String msg) {
		try {
			msg += "\r\n";
			this.ous.write(msg.getBytes());
			this.ous.flush();
		} catch (Exception ef) {
			ef.printStackTrace();
		}
	}

	// ��������������
	public static void main(String[] args) {
		NetClient nc = new NetClient("localhost", 9090);
		if (nc.conn2Server()) {// ���ӷ������ɹ�
			// ����һ���������ж�ȡ�û������ɨ��������
			Scanner sc = new Scanner(System.in);
			System.out.println("�������û���:");
			String name = sc.next();// ��ȡ����������
			System.out.println("����������:");
			String pwd = sc.next();
			if (nc.loginServer(name, pwd)) {
				nc.start();// ������ȡ�߳�
				while (true) {// ������ȡ���������뷢��������
					System.out.println("����Ҫ���͵���Ϣ:");
					String msg = sc.next();
					nc.sendMsg(msg);
				}
			}
		}
	}
}
