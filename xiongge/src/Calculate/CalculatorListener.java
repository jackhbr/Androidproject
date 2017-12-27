package Calculate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * ������¼�������
 */
public class CalculatorListener implements ActionListener {

	private JTextField textShow;
	private int flag = 0;// ��ʶ���������Ƶ��������Ĵ���
	private String value1, value2;// ��¼��һ�����ݺ͵�һ�������

	public void setTextShow(JTextField textShow) {
		this.textShow = textShow;
	}

	/**
	 * �¼�������
	 */
	public void actionPerformed(ActionEvent e) {
		// System.out.println("�����İ�ť��" + e.getActionCommand());
		String ac = e.getActionCommand();// ��ȡ��ť�ϵ�����
		String text = textShow.getText();
		if (ac.equals(".")) {
			if (text.indexOf(".") == -1) {// �ж��Ƿ���ֹ�.
				textShow.setText(text + ac);
			}
		} else if (ac.equals("+") || ac.equals("-") || ac.equals("*")
				|| ac.equals("/") || ac.equals("=")) {
			if (flag == 0) {// ��ʶ��һ�ε�������
				value1 = textShow.getText();
				value2 = ac;
				textShow.setText("0");
				flag = 1;
			} else if (flag == 1) {
				String value3 = textShow.getText();
				if (value2.equals("+")) {
					double d1 = Double.parseDouble(value1);
					double d3 = Double.parseDouble(value3);
					double d = d1 + d3;
					textShow.setText(d + "");
				}
				if (ac.equals("=")) {
					value1 = "";
					value2 = "";
					flag = 0;
				} else {
					value1 = textShow.getText();
					value2 = ac;
				}
			}
		} else {
			// ��ȡtextShow�е������Ƿ���0
			if (text.equals("0")) {
				textShow.setText(ac);
			} else {
				textShow.setText(text + ac);
			}
		}
	}
}
