package movePicture;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 1.定义DrawListener事件处理类，该类实现ActionListener动作事件接口和MouseListener鼠标事件接口
 * 
 * @author 熊哥
 * 
 */
public class DrawListener extends MouseAdapter implements ActionListener { // implements
	// MouseListener,MouseMotionListener
	// {
	// 1.3.声明四个变量，用来存储按下和释放的坐标值。
	private int x1, y1, x2, y2;
	// 1.5.声明一个Graphics2D画笔属性
	private Graphics2D g;

//	private int number = 0;
	private String str = "Line";

	/**
	 * 1.5.然后定义设置画笔属性值的方法。
	 * 
	 * @param g是从DrawMain类的窗体上获取的画笔对象
	 */
	public void setG(Graphics g) {
		this.g = (Graphics2D) g;
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);// 设置画笔抗锯齿
	}

	/**
	 * 当你在按钮上发生鼠标点击动作时执行此方法
	 */
	public void actionPerformed(ActionEvent e) {
		str = e.getActionCommand();// 获取按钮的动作命令值，如果没有这只动作命令值则获取按钮上的文字，如果按钮上没有文字则获取空字符串""
		System.out.println(str);
		// number = Integer.parseInt(str);// 将字符串转换为整数（str中存储的是数字）
		// System.out.println(number);

	}

	/**
	 * 在窗体上发生鼠标按键按下时执行此方法。
	 */
	public void mousePressed(MouseEvent e) {
		System.out.println("按下");
		// 1.4.在按下的方法中获取按下坐标值，然后在释放方法中的获取释放坐标值，分别存入属性中。
		x1 = e.getX();
		y1 = e.getY();
	}

	/**
	 * 当你在窗体上发生鼠标按键按下拖动时执行此方法。
	 */
	public void mouseDragged(MouseEvent e) {
		//if (number == 3) {
		if(str.equals("Pencil")){
			// 1.4.在拖动方法中的获取释放坐标值，分别存入属性中。
			x2 = e.getX();
			y2 = e.getY();
			g.setStroke(new BasicStroke(10));// 实例化线条粗细对象，设置为画笔的粗细
			// 1.5根据按下和拖动的坐标值来绘制曲线
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
	}

	/**
	 * 在窗体上发生鼠标按键释放时执行此方法。
	 */
	public void mouseReleased(MouseEvent e) {
		System.out.println("释放");
		// 1.4.在按下的方法中获取按下坐标值，然后在释放方法中的获取释放坐标值，分别存入属性中。
		x2 = e.getX();
		y2 = e.getY();
		g.setStroke(new BasicStroke(1));// 实例化线条粗细对象，设置为画笔的粗细
		// 1.6.在释放方法中，根据按下、释放坐标值以及选择的图形，使用Graphics画笔绘制对应图形
		if(str.equals("Line")){
			g.drawLine(x1, y1, x2, y2);// 绘制直线
		}else if(str.equals("Rect")){
			g.drawRect(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// 绘制矩形
		}else if(str.equals("Oval")){
			g.fillOval(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// 绘制填充圆
		}
		
//		switch (number) {
//		case 0:
//
//			g.drawLine(x1, y1, x2, y2);// 绘制直线
//			break;
//		case 1:
//
//			g.drawRect(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// 绘制矩形
//			break;
//		case 2:
//			g.fillOval(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// 绘制填充圆
//
//			break;
//		}
	}

	// /**
	// * 当你的鼠标光标进入到事件源对象上时执行此方法。
	// */
	// public void mouseEntered(MouseEvent e) {
	// System.out.println("进入");
	// }
	//
	// /**
	// * 当你的鼠标光标离开到事件源对象上时执行此方法。
	// */
	// public void mouseExited(MouseEvent e) {
	// System.out.println("离开");
	// }
	//
	// /**
	// * 在窗体上发生鼠标按键点击(按下和释放的必须在同一个位置上)动作时执行此方法
	// */
	// public void mouseClicked(MouseEvent e) {
	// System.out.println("点击");
	// }
}
