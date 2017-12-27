package movePicture;

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
		this.setSize(700, 500);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());

		// 2.在界面类中，实例化DrawListener事件处理类的对象，对象名为dl。
		DrawListener dl = new DrawListener();
		String[] array = { "Line", "Rect", "Oval", "Pencil" };
		for (int i = 0; i < array.length; i++) {
			JButton button = new JButton(array[i]);
			// button.setActionCommand(i + "");
			button.addActionListener(dl);
			this.add(button);
		}

		this.setVisible(true);

		// 3.在界面类中，给事件源窗体对象添加addMouseListener()动作监听方法，指定事件处理类对象为dl。
		this.addMouseListener(dl);
		// 3.在界面类中，给事件源窗体对象添加addMouseMotionListener()动作监听方法，指定事件处理类对象为dl。
		this.addMouseMotionListener(dl);
		// 3.1.从窗体上获取Graphics画笔对象；(注意：获取必须在窗体可见之后获取，否则获取的Graphics画笔对象为null)
		Graphics g = this.getGraphics();
		// 3.2.通过事件处理类的对象dl,将画笔传入到dl对象的g属性中
		dl.setG(g);
	}
}
