package f6qunliao;

import java.util.HashMap;

/**
 * ϵͳ���ݷ��ʲ���ʵ���� �˺���֤�� ���ݱ��桢 ���ĵȹ���ʵ��
 * 
 * @author ���� ������
 */
public class DaoTools {
	/**
	 * �鿴����û��Ƿ���Ե�¼�ɹ���
	 * 
	 * @param user
	 *            :Ҫ�����û�����
	 * @return:�Ƿ��¼�ɹ�
	 */
	public static boolean checkLogin(UserInfo user) {
		// �ڴ�ֻ��֤�û����Ƿ����
		if (userDB.containsKey(user.getName())) {
			return true;
		}
		System.out.println("��֤ʧ�ܣ� : " + user.getName());
		return false;
	}

	// �ڴ��û���Ϣ���ݿ�
	private static java.util.Map<String, UserInfo> userDB = new HashMap();
	// ��̬��:ģ�������ڴ��е��û�����,�û���Ϊuser1~10
	// ����������ʱ����δ��루��̬���еģ����Զ�ִ�У���userDB�д������
	static {
		for (int i = 0; i < 10; i++) {
			UserInfo user = new UserInfo();
			user.setName("user" + i);
			user.setPwd("pwd" + i);
			userDB.put(user.getName(), user);
		}
	}
}
