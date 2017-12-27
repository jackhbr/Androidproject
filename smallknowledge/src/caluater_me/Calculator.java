package caluater_me;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Calculator extends JFrame {

	public static void main(String[] args) {
		new Calculator().initUI();
	}

	public void initUI() {
		this.setTitle("计算器");
		this.setSize(250, 290);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.setResizable(false);

		JTextField textShow = new JTextField("0");
		textShow.setHorizontalAlignment(JTextField.RIGHT);// 设置文本从右对齐
		textShow.setPreferredSize(new Dimension(220, 80));
		textShow.setEditable(false);//不可编辑
		this.add(textShow);

		String[] array = { "9", "8", "7", "6", "5", "4", "3", "2", "1", "0",
				"+", "-", "*", "/", "=", "." };

		CalculatorListener cl = new CalculatorListener();
		cl.setTextShow(textShow);

		for (int i = 0; i < array.length; i++) {
			JButton button = new JButton(array[i]);
			button.setPreferredSize(new Dimension(50, 35));
			// button.setActionCommand(array[i]);// 设置动作命令

			button.addActionListener(cl);

			this.add(button);
		}

		this.setVisible(true);
	}
}
