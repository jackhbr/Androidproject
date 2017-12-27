package login;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//import������Ĺؼ��֣�����SWING����е�JFrame�ࡣ
//��������һ����ݼ���Ctrl+Shift+O

/**
 * 1.�½�һ��Login��¼������
 */
public class Login {

	/**
	 * 1.�����ж���������
	 */
	public static void main(String[] args) {
		// 2.���������У�ʵ����Login��Ķ���
		Login login = new Login();
		// 2.Ȼ��ʹ�ö����������ó�ʼ������ķ�����
		login.initUI();
	}

	/**
	 * 1.��ʼ������ķ���
	 */
	public void initUI() {
		// 3.�ڳ�ʼ������ķ����У�ʵ����JFrame�������������Ķ���
		JFrame frame = new JFrame();
		// 4.����JFrmae������������ֵ�����⡢��С����ʾλ�á��رղ��������֡��ɼ���
		frame.setTitle("Login");// ���ñ�������
		frame.setSize(300, 400);// ���ô���Ĵ�С
		frame.setLocationRelativeTo(null);// ���ô�����ʾ����Ļ�����롣
		frame.setDefaultCloseOperation(3);// ���ô���Ĺرղ���Ϊ3,��ʾ�رմ����˳�����

		// ʵ����FlowLayout��ʽ������Ķ���,Ĭ���Ǿ��ж���
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		// ����frame����Ĳ��ַ�ʽΪfl��ʽ����
		frame.setLayout(fl);

		// 5.ʵ�������������Ķ���Ȼ�󽫶�����ӵ�������������ϡ�
		JLabel labName = new JLabel("�˺ţ�");// ʵ����JLabel��Ķ�������Ҫ��ʾ����ϢΪ"�˺ţ�"
		frame.add(labName);// �ú�labName�����ӵ�frame������

		JTextField txtName = new JTextField(20);// ʵ����JTextField�ı��������Ķ���
		frame.add(txtName);// ��txtName��ӵ��������������

		JButton button = new JButton("��¼");
		frame.add(button);

		frame.setVisible(true);// ���ô���Ϊ�ɼ�

		// 2.��������ʵ����LoginListener�¼�������Ķ���
		LoginListener ll = new LoginListener();
		
		//��frame��¼���崫�ݵ�ll�����login������
		ll.setLogin(frame);
		
		// 3.�ڽ������У����¼�Դ��¼��ť�������addActionListener()��������������ָ��LoginListener�¼�������Ķ���
		button.addActionListener(ll);

	}

}
