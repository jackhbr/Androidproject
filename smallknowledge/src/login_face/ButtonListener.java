package login_face;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class ButtonListener implements java.awt.event.ActionListener {
	 private JFrame login;//�����¼�����������
	/**
	 * ���õ�¼��������ֵ�ķ���
	 * @param l��Login���ܴ��ݹ����ĵ�¼����
	 */
	public void setLogin(JFrame l){
		login = l;//�����ݹ����ĵ�¼������󸳸�login����
	}
	
	
	public JPasswordField passfield;
	public JTextField field;
	
	public ButtonListener(JTextField field,JPasswordField passfield) //�޸�������Ĳ������Ϳ��Ըı䴫�����Ĳ����Ķ�����
	{
		this.passfield=passfield;
		this.field=field;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
	    String name=field.getText();
	    String name1=passfield.getText();
		System.out.println("��ť������ˣ�");
		//if(passfield==1234){
		System.out.println("������˺���"+name);
		System.out.println("������˺���"+name1);
		if(name.equals("1234"))
			System.out.println("������˺���ȷ");
		else
			System.out.println("������˺Ŵ���");
		if(name1.equals("123456"))
			System.out.println("�����������ȷ");
		else
			System.out.println("������������");
		//}
		if(name.equals("1234") && name1.equals("123456"))
		{
		JFrame frame = new JFrame("��ͼ");// ʵ����JFrame�������ͬʱ���ô���ı�������ֵ
		frame.setSize(700, 500);// ���ô���Ĵ�С
		frame.setDefaultCloseOperation(2);// ���ô���Ĺرղ�����2���ǹرյ�ǰ���壬�������岻�ر�(���ֻ��һ�����壬�رմ����˳�����)
		frame.setLocationRelativeTo(null);// ����Ϊnull��ʾ���ô����������Ļ������λ�ã�
		frame.setVisible(true);// ���ô���Ϊ�ɼ�
		
		//����ͼ����ɹ���ʾ֮��ԭ���ĵ�¼����ͱ���Ҫ�ر�
		login.dispose();
		}
		else
		{
		
			JOptionPane.showMessageDialog(null, "��������˺��������", "������ʾ", JOptionPane.ERROR_MESSAGE);
			
		}
	}

}
