

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 定义DrawMain画图界面类，该类继承自JFrame，这个类就是窗体容器组件类。
 */
public class DrawMain extends JFrame {

	public static void main(String[] args) {
		DrawMain dm = new DrawMain();
		dm.initUI();
	}

	private void initUI() {
		// 设置窗体的属性值
		this.setTitle("画图");
		this.setSize(900, 700);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());

		// 2.在界面类中，实例化DrawListener事件处理类的对象，对象名为dl。
		DrawListener dl = new DrawListener();

		// 定义字符串数组，用来存储按钮上的文字信息
		String[] array = { "Line", "Rect", "Oval","RoundRect","fillOval","fill3DRect","drawImage","text","立体图形","曲线","刷子","橡皮","喷枪" };

		// 循环遍历数组，根据数组中的元素来实例化按钮对象
		for (int i = 0; i < array.length; i++) {
			JButton button = new JButton(array[i]);
			button.addActionListener(dl);// 3.在界面类中，给事件源按钮对象添加addActionListener()动作监听方法，指定事件处理类对象为dl。
			this.add(button);// 将按钮添加到窗体上
		}
		this.setVisible(true);

		// 3.在界面类中，给事件源窗体对象添加addMouseListener()动作监听方法，指定事件处理类对象为dl。
		this.addMouseListener(dl);
		
		this.addMouseMotionListener(dl);
		// 3.1.从窗体上获取Graphics画笔对象；(注意：获取必须在窗体可见之后获取，否则获取的Graphics画笔对象为null)
		Graphics g = this.getGraphics();
		// 3.2.通过事件处理类的对象dl,将画笔传入到dl对象的g属性中
		dl.setG(g);
	}
}
