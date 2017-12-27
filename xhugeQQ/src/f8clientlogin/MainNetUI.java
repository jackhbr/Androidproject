package f8clientlogin;
import f6qunliao.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class MainNetUI {

	/**
	 * @param args
	 */
	private JFrame jf_login;// ��¼����
	private JFrame jf_chat;// ����������
	private JTextField jta_userName; // ��¼�����ϵ��û��������������
	private JTextField jta_pwd;
	private JTextArea jta_input = new JTextArea(5, 20); // ��ʾ���յ�����Ϣ���
	private lanjieNetClient conn; // ������Ҫʹ�õ������Ӷ���
    private UserInfo userInfo;
	public static void main(String[] args) {
		new MainNetUI().showLoginUI();
	}

	private void showLoginUI() {
		jf_login = new javax.swing.JFrame("����ͻ���v0.1,���¼:");
		java.awt.FlowLayout fl = new java.awt.FlowLayout();
		jf_login.setLayout(fl);
		jf_login.setSize(300, 300);
		jf_login.setLocationRelativeTo(null);
		jta_userName = new JTextField(12); // �û������������
		jta_pwd = new JTextField(12);
		JLabel la_name = new JLabel("�û���:"); // �û�������ı�ǩ
		JLabel la_pwd = new JLabel("�� ��:");
		jf_login.add(la_name);
		jf_login.add(jta_userName);
		jf_login.add(la_pwd);
		jf_login.add(jta_pwd);
		javax.swing.JButton bu_login = new javax.swing.JButton("Login");
		bu_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAction();
			}
		});
		jf_login.add(bu_login);
		jf_login.setVisible(true);
	}

	private void loginAction() {
		String name = jta_userName.getText();
		String pwd = jta_pwd.getText();
		// 1.�������Ӷ���
		conn = new lanjieNetClient("localhost", 9090, jta_input);
		// 2.�����Ϸ�����
		if (conn.conn2Server()) {// ����������Ϸ�����
			// 3.��¼
			if (conn.loginServer(name, pwd)) {
				// 4.��ʾ���������� //��¼�ɹ��ˣ�Ҫ�ص���¼����
				showMainUI();
				// 5.���������߳�
				conn.start();
				jf_login.dispose();// �رյ�¼����
			}
		} else {
			JOptionPane.showMessageDialog(jf_login, "�˺�����user1~10");
		}
	}

	// ��ʾ�������
	public void showMainUI() {
		jf_chat = new javax.swing.JFrame("����ͻ���v0.1");
		java.awt.FlowLayout fl = new java.awt.FlowLayout();
		jf_chat.setLayout(fl);
		jf_chat.setSize(200, 300);
		jf_chat.setLocationRelativeTo(null);
		// ��ʾ���յ�����Ϣ
		javax.swing.JLabel la = new JLabel("Ҫ���͵���Ϣ: ");
		// ������Ϣ������
		final javax.swing.JTextArea jta_output = new JTextArea(5, 20);
		// ���Ͱ�ť
		javax.swing.JButton bu_send = new javax.swing.JButton("send");
		bu_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ʵ���������ϵķ��͵���
				String msg = jta_output.getText();
				conn.sendMsg(msg);
			}
		});
		jf_chat.add(jta_input);
		jf_chat.add(la);
		jf_chat.add(jta_output);
		jf_chat.add(bu_send);
		jf_chat.setVisible(true);
	}

}
