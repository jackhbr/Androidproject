package f6qunliao;

import java.net.ServerSocket;

//����������,������
public class ChatServer {
	// ������

	public static void main(String[] args) {
		ChatServer cs = new ChatServer();

		cs.setupServer(9090);
	}

	// ��ָ���Ķ˿�������������
	public void setupServer(int port) {
		try {
			ServerSocket sc = new ServerSocket(port);
			System.out.println("�����������ɹ�:" + port);
			while (true) {
				java.net.Socket client = sc.accept();// �ȴ����ӽ���
				System.out.println("������һ���ͻ�������:"
						+ client.getRemoteSocketAddress().toString());
				// ����һ�������̣߳�ȥ����������Ӷ���
				ServerThread ct = new ServerThread(client);
				ct.start();
			}
		} catch (Exception ef) {
			ef.printStackTrace();
		}
	}
}
