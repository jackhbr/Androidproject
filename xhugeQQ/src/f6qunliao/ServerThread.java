package f6qunliao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

//�����ĳ���ͻ����������̵�ʵ��
public class ServerThread extends Thread {
	private java.net.Socket client;// �߳��д���Ŀͻ�����
	private OutputStream ous;// ���������
	private UserInfo user; // ����̴߳�����������û�����Ϣ
	// ����ʱ���봫��һ��Socket����

	public ServerThread(java.net.Socket client) {
		this.client = client;
	}

	// ȡ������̶߳��������û�����
	public UserInfo getOwerUser() {
		return this.user;
	}

	public void run() {// �߳���ִ�еķ���
		processSocket();
	}

	// ��һ����Ϣ���͸�����̶߳���������Ŀͻ���, ����ǰ������Ҫ����\r\n,
	public void sendMsg2Me(String msg) {
		try {
			msg += "\r\n";
			ous.write(msg.getBytes());
			ous.flush();
		} catch (Exception ef) {
		}
	}

	// ��ȡ�ͻ�����������Ϣ
	private void processSocket() {
		try {
			InputStream ins = client.getInputStream();
			ous = client.getOutputStream();
			// ��������ins��װΪ���Զ�ȡһ���ַ���,Ҳ������\r\n��β���ַ���
			BufferedReader brd = new BufferedReader(new InputStreamReader(ins));
			sendMsg2Me("��ӭ��������!,����������û���:");
			String userName = brd.readLine();
			sendMsg2Me(userName + ",�������������:");
			String pwd = brd.readLine();
			user = new UserInfo();
			user.setName(userName);
			user.setPwd(pwd);
			// �������ݿ�ģ��,��֤�û��Ƿ����
			boolean loginState = DaoTools.checkLogin(user);
			if (!loginState) {// ����������û��˺���ر�
				this.closeMe();
				return;
			}
			ChatTools.addClient(this); // ��֤�ɹ�:���������������������
			String input = brd.readLine();// һ��һ�еĶ�ȡ�ͻ�����������Ϣ
			while (!"bye".equals(input)) {// һֱ��ȡ
				System.out.println("�������յ�����: " + input);
				// ����һ����Ϣ�󣬾ͷ��͸������Ŀͻ���ȥ...
				ChatTools.castMsg(this.user, input);
				input = brd.readLine();// ��ȡ��һ��
			}
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		ChatTools.castMsg(this.user, "��������,�ټ�!");
		this.closeMe();
		// ���÷�������������֪ͨ����������
		// ChatTools.removeClient(this.user);//��δʵ��
	}

	// �ر�����̴߳������
	public void closeMe() {
		try {
			client.close();
		} catch (Exception ef) {
			ef.printStackTrace();
		}
	}
}
