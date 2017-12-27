

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * 陈哥   2195049056
 * @author qwertyuiop
 *
 */
public class FiveChess extends JPanel {

	private shape[][] array = new shape[15][15];
	
	public int[][] arrayChess = new int[15][15];


	public static void main(String[] args) {
		FiveChess fc = new FiveChess();
		fc.FiveChessUI();

	}

	public void FiveChessUI() {
		
//		int k=0,k1=0,k2=0;
//		k=35/30;
//		k1=50/30;
//		k2=65/30;
//		System.out.println(" 35/30= "+k+"  50/30=  "+k1+" 65/30= "+k2);
		// 创建窗体的基本属性
		JFrame jf = new JFrame();// 创建窗体对象
		jf.setTitle("FIVECHESS");// 标题
		jf.setSize(660, 480);
		jf.setDefaultCloseOperation(3);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);

		BorderLayout bl = new BorderLayout();
		jf.setLayout(bl);
		jf.add(this, BorderLayout.CENTER);
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(200, 0));// 设置面板的大小
		eastPanel.setBackground(Color.LIGHT_GRAY);

		JButton buttonStart = new JButton("开始新游戏");
		JButton buttonGiveUp = new JButton("认输");
		JButton buttonBack = new JButton("悔棋");

		buttonGiveUp.setPreferredSize(new Dimension(160, 70));
		buttonStart.setPreferredSize(new Dimension(160, 70));
		buttonBack.setPreferredSize(new Dimension(160, 70));

		eastPanel.add(buttonStart);
		eastPanel.add(buttonGiveUp);
		eastPanel.add(buttonBack);

		ButtonGroup bg = new ButtonGroup();
		JRadioButton rbPVP = new JRadioButton("人人对战");
		JRadioButton rbPVAI = new JRadioButton("人机对战");
		bg.add(rbPVP);
		bg.add(rbPVAI);
		rbPVP.setSelected(true);
		rbPVP.setPreferredSize(new Dimension(160, 70));
		rbPVAI.setPreferredSize(new Dimension(160, 70));

		rbPVP.setBackground(Color.LIGHT_GRAY);
		rbPVAI.setOpaque(false);

		eastPanel.add(rbPVP);
		eastPanel.add(rbPVAI);

		jf.add(eastPanel, BorderLayout.EAST);

		jf.setVisible(true);

		jf.setVisible(true);
		// 实例化下棋事件处理类的对象
		ChessListener cl = new ChessListener();
		// 获取中间面板上的Graphics画笔对象
		Graphics g = this.getGraphics();
		// 将获取的g画笔对象传递到cl对象的g属性上
		cl.setG(g);
		cl.setArrayChess(arrayChess);

		this.addMouseListener(cl);
	}

	/**
	 * 重写JPanel组件的重绘方法
	 */
	public void paint(Graphics g) {
		super.paint(g);// super表示父类的对象，super.paint(g)表示调用父类中的paint方法
		drawCheeBorder(g);
		for (int i = 0; i < arrayChess.length; i++) {
			for (int j = 0; j < arrayChess[i].length; j++) {
				if(arrayChess[i][j] == 1){
					g.setColor(Color.BLACK);
					g.fillOval(i*30, j*30, 30, 30);      //这种重绘的方法只针对特定的起始坐标，只有这样才能不偏移
				}else if(arrayChess[i][j] == 2){         //而且这样的话后面的坐标表示那里就直接是I和j，不需要大脑思考和判断，
					g.setColor(Color.WHITE);
					g.fillOval(i*30, j*30, 30, 30);
				}
			}
		}
		System.out.println("..............");
	}

	/**
	 * 绘制五子棋棋盘的方法
	 * 
	 * @param g画笔对象
	 */
	private void drawCheeBorder(Graphics g) {
		// 绘制五子棋棋盘 单元格大小时30
		for (int i = 0; i < 15; i++) {
			g.drawLine(15, 15 + 30 * i, 15 + 14 * 30, 15 + 30 * i);// 绘制横线
			g.drawLine(15 + 30 * i, 15, 15 + 30 * i, 15 + 14 * 30);// 绘制竖线
		}
	}

}
