package login;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * 1.����LoginListener�¼������࣬����ʵ��ActionListener�����¼��ӿڣ�
 * 
 * @author �ܸ�
 * 
 */
public class LoginListener implements ActionListener {
	
	private JFrame login;//�����¼�����������
	
	/**
	 * ���õ�¼��������ֵ�ķ���
	 * @param l��Login���ܴ��ݹ����ĵ�¼����
	 */
	public void setLogin(JFrame l){
		login = l;//�����ݹ����ĵ�¼������󸳸�login����
	}
	
	/**
	 * 1.��д�ӿ��еĳ��󷽷���
	 */
	public void actionPerformed(ActionEvent e) {
		// System.out.println("������" + e.getActionCommand() + "��ť");
		// 1.1.����д���¼��������У�ʵ�����´���������ô��������ֵ��
		JFrame frame = new JFrame("��ͼ");// ʵ����JFrame�������ͬʱ���ô���ı�������ֵ
		frame.setSize(700, 500);// ���ô���Ĵ�С
		frame.setDefaultCloseOperation(2);// ���ô���Ĺرղ�����2���ǹرյ�ǰ���壬�������岻�ر�(���ֻ��һ�����壬�رմ����˳�����)
		frame.setLocationRelativeTo(null);// ����Ϊnull��ʾ���ô����������Ļ������λ�ã�
		frame.setVisible(true);// ���ô���Ϊ�ɼ�
		
		//����ͼ����ɹ���ʾ֮��ԭ���ĵ�¼����ͱ���Ҫ�ر�
		login.dispose();
	}
}
