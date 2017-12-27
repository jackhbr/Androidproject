

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessListener extends MouseAdapter {
	private Graphics2D g;
	private int count1, count2, count = 0;
	private int remx = 0, remy = 0;
	private int x, y, w, h;
	private Color color;
	int[][] place = new int[500][500];
	private shape[][] array;
	private int numberx = 0, numbery = 0;
	//用来保存棋盘上的棋子
	private int[][] arrayChess;

	public void setArrayChess(int[][] arrayChess){
		this.arrayChess = arrayChess;
	}

	public void setG(Graphics g) {
		this.g = (Graphics2D) g;
		// 设置画笔抗锯齿
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	/**
	 * 当你在事件源对象上发生鼠标点击动作时执行此方法
	 */
	public void mouseClicked(MouseEvent e) {
		// 获取鼠标点击位置的坐标值
		int x = e.getX();  //可能是31,32,33
		int y = e.getY();
		count1 = x / 30;   //整型的返回值，所以只能为1,不对，这里难道是四舍五入了？？、？
		x = count1 * 30;   //并不是，这里的数据太巧合了，考虑到起始坐标，然后画出坐标图后会发现
		count2 = y / 30;   //刚好在棋子的范围内他是落在那个点处，注意画圆的时候的x,y是左上角的坐标，而不是中心坐标
		y = count2 * 30;   //所以，在无限的巧合下，这个程序没有出错。。不过，这也使我对坐标，圆，棋子之间的相互认识更加的加深了
		if (count % 2 == 0) {
			if (place[x][y] == 0) {
				g.setColor(Color.BLACK);
				g.fillOval(x, y, 30, 30);
				//在arrayChess中存入数组1，表示黑棋
				arrayChess[count1][count2] = 1;
				shape s = new shape(x, y, w, h, color);
//				place[x][y] = s;
			}

		} else if (count % 2 == 1) {
			if (place[x][y] == 0) {
				g.setColor(Color.WHITE);
				g.fillOval(x, y, 30, 30);
				arrayChess[count1][count2] = 2;
				shape s = new shape(x, y, w, h, color);
				place[x][y] = -1;
			}

		}
		int chess = sp(count1,count2);
//		System.out.println("count1:"+count1+"    count2:"+count2);
		count++;
		System.out.println(chess);
	}
	/**
	 * 判断水平方向行棋子相连情况
	 * @param x 当前棋子的交点位置
	 * @param y
	 */
	public int sp(int x,int y){
		int chess = 0;  //记录棋子相连个数
		//向右统计
		for(int i=x+1;i<arrayChess.length;i++){
			if(arrayChess[i][y] == arrayChess[x][y]){
				chess++;
			}else{
				break;
			}
		}
		for(int i=x;i>=0;i--){
			if(arrayChess[i][y] == arrayChess[x][y]){
				chess++;
			}else{
				break;
			}
		}
		return chess;
		
	}
}
