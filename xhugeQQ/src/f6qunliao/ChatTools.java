package f6qunliao;

import java.util.ArrayList;
import java.util.List;

/**
 * ����������ͻ������߳�,ת����Ϣ���� �����ֻ��Ҫ�ṩ��������,����Ҫ��������,���Խ�Ϊ��̬����
 * 
 * @author ���� ������
 */
public class ChatTools {
	// ���洦���̵߳Ķ��ж���
	private static List<ServerThread> stList = new ArrayList<ServerThread>();

	// ����Ҫ���ⲿ��������Ķ���,���Թ����������޶���дΪprivate
	private ChatTools() {
	}

	/**
	 * ��һ���ͻ���Ӧ�Ĵ����̶߳�����뵽������
	 * 
	 * @param ct
	 *            :�����̶߳���
	 */
	public static void addClient(ServerThread ct) {
		// ֪ͨ�������û�������������:
		castMsg(ct.getOwerUser(), "����������Ŀǰ����" + stList.size());
		stList.add(ct);// ����������̶߳�����뵽������
	}

	/**
	 * ��һ����Ϣ���͸������е������ͻ����������
	 * 
	 * @param sender
	 *            :�������û�����
	 * @param msg
	 *            :Ҫ���͵���Ϣ����
	 */
	public static void castMsg(UserInfo sender, String msg) {
		msg = sender.getName() + "˵:" + msg;
		for (int i = 0; i < stList.size(); i++) {
			ServerThread st = stList.get(i);
			// ServerThread���ж�����һ������Ϣ���ͳ�ȥ�ķ���
			st.sendMsg2Me(msg);// ����Ϣ��ÿһ���ͻ���
		}
	}
}
