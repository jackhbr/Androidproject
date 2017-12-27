package fiveChess;

import java.awt.Color;
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
    
	private int count=0;
	//定义一个二维数组来标记棋盘上的位置
	private int[][] chesses=new int[Config.ROWS][Config.COLUMNS];
	
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
		int x1 = e.getX();
		int y1= e.getY();
		
		for(int j=0;j<Config.ROWS;j++)
		{
			for(int i=0;i<Config.COLUMNS;i++)
			{
				int x=Config.X0+Config.SIZE*i;//横坐标
				int y=Config.Y0+Config.SIZE*j;//纵坐标
				//与圆心的误差为size/3
				if(x1>x-Config.SIZE/3 && x1<x+Config.SIZE/3 && y1>y-Config.SIZE/3 && y1<y+Config.SIZE/3)
				{
					if(chesses[i][j]==0)
					{
					if(count==0)
					{
						chesses[i][j]=1;//如果是黑子，就为1
						g.setColor(Color.BLACK);
						count++;
					}
					else {
						chesses[i][j]=-1;//如果是白子，就为-1
						g.setColor(Color.WHITE);
						count--;
					}
					// 绘制棋子了
					//除以2和乘以0.5的差别：乘以0.5还要强制转换，除以2直接就行
					g.fillOval(x-(Config.CHESS_SIZE/2), y-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
				}
				}
			}
		}
		
		
		
		
	}
}
