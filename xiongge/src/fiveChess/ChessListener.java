package fiveChess;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 定义下棋的事件处理类,该类继承自MouseAdapter鼠标适配器类
 */
public class ChessListener extends MouseAdapter {

	private Graphics2D g;

	public void setG(Graphics g) {
		this.g = (Graphics2D) g;
		// 设置画笔抗锯齿
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}

	/**
	 * 当你在事件源对象上发生鼠标点击动作时执行此方法
	 */
	public void mouseClicked(MouseEvent e) {
		// 获取鼠标点击位置的坐标值
		int x = e.getX();
		int y = e.getY();
		// 绘制棋子了
		g.fillOval(x, y, 30, 30);
	}
}
