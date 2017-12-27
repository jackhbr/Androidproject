package f8clientlogin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;

public class lanjieNetClient extends Thread {
	private String serverIp;// ������IP
	private int port;// �������˿�
	private OutputStream ous;// �������������������
	private BufferedReader brd;// ��������������������
	// �������������ͻ�������ʱ�����������IP�Ͷ˿�
	 //��ʾ���յ�����Ϣ�����Ҫ�ӽ����ϴ�����
	private JTextArea jta_recive;
	public lanjieNetClient(String serverIp, int port,JTextArea jta_recive) {
		this.serverIp = serverIp;
		this.port = port;
		this.jta_recive=jta_recive;
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


}
