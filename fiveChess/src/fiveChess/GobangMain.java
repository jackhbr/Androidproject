package fiveChess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * 定义五子棋主界面类，该类继承自JPanel面板容器组件类，当前类就是面板容器组件
 */
public class GobangMain extends JPanel implements Config{ // extends JFrame {

	public static void main(String[] args) {
		GobangMain gm = new GobangMain();
		gm.initUI();
	}

	/**
	 * 初始化界面的方法
	 */
	private void initUI() {
		// 实例化JFrame窗体容器组件类的对象
		JFrame frame = new JFrame();
		// 设置窗体的属性值
		frame.setTitle("Gobang");
		frame.setSize(660, 480);
		frame.setDefaultCloseOperation(3);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		// 实例化一个边框布局类的对象，边框布局是把容器分为5部分，上北下南左西右东中间
		BorderLayout bl = new BorderLayout();
		frame.setLayout(bl);

		// 实例化中间JPanel面板容器组件类的对象
		// JPanel centerPanel = new JPanel();
		// centerPanel.setBackground(Color.BLUE);
		// 将this添加到边框布局的中间部分
		frame.add(this, BorderLayout.CENTER);
		this.setBackground(Color.LIGHT_GRAY); //设置棋盘的底色
		
		
		// 实例化东边JPanel面板容器组件类的对象
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(200, 0));// 设置面板的大小
		eastPanel.setBackground(Color.LIGHT_GRAY);

		// 实例化按钮对象
		JButton buttonStart = new JButton("开始新游戏");
		JButton buttonGiveUp = new JButton("认输");
		JButton buttonBack = new JButton("悔棋");
		// 设置按钮的带下
		buttonGiveUp.setPreferredSize(new Dimension(160, 70));
		buttonStart.setPreferredSize(new Dimension(160, 70));
		buttonBack.setPreferredSize(new Dimension(160, 70));
		// 将按钮添加到东边面板上
		eastPanel.add(buttonStart);
		eastPanel.add(buttonGiveUp);
		eastPanel.add(buttonBack);
		// 实例化单选按钮的分组对象
		ButtonGroup bg = new ButtonGroup();
		// 实例化单选按钮对象
		JRadioButton rbPVP = new JRadioButton("人人对战");
		JRadioButton rbPVAI = new JRadioButton("人机对战");
		// 进行单选按钮分组
		bg.add(rbPVP);
		bg.add(rbPVAI);
		rbPVP.setSelected(true);// 设置默认选中的是人人对战
		// 设置单选按钮的大小
		rbPVP.setPreferredSize(new Dimension(160, 70));
		rbPVAI.setPreferredSize(new Dimension(160, 70));
		// 设置按钮的背景色和面板颜色值一致
		rbPVP.setBackground(Color.LIGHT_GRAY);
		rbPVAI.setOpaque(false);// 设置背景为透明的
		// 将单选按钮添加到东边面板上
		eastPanel.add(rbPVP);
		eastPanel.add(rbPVAI);
		// 将eastPanel添加到边框布局的东边
		frame.add(eastPanel, BorderLayout.EAST);

		frame.setVisible(true);

		// Graphics g = this.getGraphics();
		// g.setColor(Color.black);
		// g.drawLine(100, 50, 400, 50);
		// System.out.println(">>>>>>>>>>>");

		// 实例化下棋事件处理类的对象
		ChessListener cl = new ChessListener();
		// 获取中间面板上的Graphics画笔对象
		Graphics g = this.getGraphics();
		// 将获取的g画笔对象传递到cl对象的g属性上
		cl.setG(g);

		// 给事件源中间面板添加鼠标动作监听方法，指定事件处理类对象为cl
		this.addMouseListener(cl);
	}

	/**
	 * 重写JPanel组件的重绘方法
	 */
	public void paint(Graphics g) {
		super.paint(g);// super表示父类的对象，super.paint(g)表示调用父类中的paint方法
		drawCheeBorder(g);
	}

	/**
	 * 绘制五子棋棋盘的方法
	 * 
	 * @param g画笔对象
	 */
	private void drawCheeBorder(Graphics g) {
		// 绘制五子棋棋盘 单元格大小时30
		for (int i = 0; i < Config.ROWS; i++) {
			
			g.drawLine(Config.X0,Config.Y0+ Config.SIZE * i, Config.X0 + (Config.COLUMNS-1) * Config.SIZE, Config.Y0+ Config.SIZE * i);// 绘制横线
			
		}
    for (int j = 0; j < Config.COLUMNS; j++) {
			
			g.drawLine(Config.X0+j*Config.SIZE,Config.Y0, Config.X0 + j* Config.SIZE, Config.Y0+ Config.SIZE *(Config.ROWS-1));// 绘制横线
			
		}
	}

}
