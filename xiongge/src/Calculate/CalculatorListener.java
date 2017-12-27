package Calculate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * 定义的事件处理类
 */
public class CalculatorListener implements ActionListener {

	private JTextField textShow;
	private int flag = 0;// 标识，用来控制点击运算符的次数
	private String value1, value2;// 记录第一个数据和第一个运算符

	public void setTextShow(JTextField textShow) {
		this.textShow = textShow;
	}

	/**
	 * 事件处理方法
	 */
	public void actionPerformed(ActionEvent e) {
		// System.out.println("你点击的按钮是" + e.getActionCommand());
		String ac = e.getActionCommand();// 获取按钮上的数据
		String text = textShow.getText();
		if (ac.equals(".")) {
			if (text.indexOf(".") == -1) {// 判断是否出现过.
				textShow.setText(text + ac);
			}
		} else if (ac.equals("+") || ac.equals("-") || ac.equals("*")
				|| ac.equals("/") || ac.equals("=")) {
			if (flag == 0) {// 标识第一次点击运算符
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
			// 获取textShow中的数据是否是0
			if (text.equals("0")) {
				textShow.setText(ac);
			} else {
				textShow.setText(text + ac);
			}
		}
	}
}
