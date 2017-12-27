package drawPicture;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 1.定义DrawListener事件处理类，该类实现ActionListener动作事件接口和MouseListener鼠标事件接口
 * 
 * @author 熊哥
 * 
 */
public class DrawListener implements ActionListener, MouseListener {

	// 1.1.声明一个存储图形的字符串属性
	private String type = "Line";
	// 1.3.声明四个变量，用来存储按下和释放的坐标值。
	private int x1, y1, x2, y2;
	// 1.5.声明一个Graphics画笔属性
	private Graphics g;

	/**
	 * 1.5.然后定义设置画笔属性值的方法。
	 * @param g是从DrawMain类的窗体上获取的画笔对象
	 */
	public void setG(Graphics g) {
		this.g = g;
	}

	/**
	 * 1.重写接口中的抽象方法。 该方法也是事件处理方法
	 */
	public void actionPerformed(ActionEvent e) {
		// e.getSource();//获取事件源对象

		// 1.2.在事件处理方法中，获取按钮上的文字信息，存入到图形字符串属性中
		type = e.getActionCommand();// 获取按钮上的文字信息，如果没有文字信息，输出空字符串""
		System.out.println("type = " + type);

	}

	/**
	 * 在窗体上发生鼠标按键点击(按下和释放的必须在同一个位置上)动作时执行此方法
	 */
	public void mouseClicked(MouseEvent e) {
		System.out.println("点击");
	}

	/**
	 * 在窗体上发生鼠标按键按下时执行此方法。
	 */
	public void mousePressed(MouseEvent e) {
		System.out.println("按下");
		// 1.4.在按下的方法中获取按下坐标值，然后在方法方法中的获取释放坐标值，分别存入属性中。
		x1 = e.getX();
		y1 = e.getY();
	}

	/**
	 * 在窗体上发生鼠标按键释放时执行此方法。
	 */
	public void mouseReleased(MouseEvent e) {
		System.out.println("释放");
		// 1.4.在按下的方法中获取按下坐标值，然后在方法方法中的获取释放坐标值，分别存入属性中。
		x2 = e.getX();
		y2 = e.getY();

		// 1.6.在释放方法中，根据按下、释放坐标值以及选择的图形，使用Graphics画笔绘制对应图形
		if (type.equals("Line")) {
			g.drawLine(x1, y1, x2, y2);// 绘制直线
		} else if (type.equals("Rect")) {
			g.drawRect(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// 绘制矩形
			// g.fillOval(x, y, width, height);//绘制填充圆
		}
	}

	/**
	 * 当你的鼠标光标进入到事件源对象上时执行此方法。
	 */
	public void mouseEntered(MouseEvent e) {
		System.out.println("进入");
	}

	/**
	 * 当你的鼠标光标离开到事件源对象上时执行此方法。
	 */
	public void mouseExited(MouseEvent e) {
		System.out.println("离开");
	}
}
