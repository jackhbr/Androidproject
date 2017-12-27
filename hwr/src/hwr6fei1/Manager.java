package hwr6fei1;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Manager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager ma = new Manager();
		ma.showUI();

	}

	public void showUI() {
		JFrame jf = new JFrame();
		jf.setTitle("j");
		jf.setSize(230, 200);
		jf.setLocationRelativeTo(null);
		jf.setLayout(new FlowLayout());
		String s[] = { "写入", "进行训练" };

		Aclistener ls = new Aclistener();

		for (int i = 0; i < s.length; i++) {

			JButton bu = new JButton(s[i]); // 论button有时在编码变换后�???直不能显示中文有多坑，坑了我两个小时
			bu.addActionListener(ls); // 包里面的编码要统�???，一�???始用啥就用啥，最好用utf-8.
			jf.add(bu);
		}
		String str1[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		JComboBox<String> jcb = new JComboBox<String>(str1); 
		jcb.addActionListener(ls); // 添加这个监听之后只能�?测出这个下拉框是否改变，下拉框的长度多大
		jf.add(jcb); // 类似于数组队列中使用泛型那样使用这个下拉�?
		
		
		jf.setVisible(true);
		ls.setPa(jf);

	}

}
