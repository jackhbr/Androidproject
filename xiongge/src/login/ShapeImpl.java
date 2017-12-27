package login;


import java.awt.event.ActionEvent;

public class ShapeImpl extends Object implements Shape {

	/**
	 * 实现Shape接口中的draw抽象方法
	 */
	public void draw() {
		System.out.println(NAME + "在绘制图形！");
	}

	/**
	 * 实现Shape父接口ActionListener接口中的actionPerformed抽象方法
	 */
	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {

	}

}
