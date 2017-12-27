package AI;

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
 * �����������������࣬����̳���JPanel�����������࣬��ǰ���������������
 */
public class GobangMain extends JPanel implements Config{ // extends JFrame {

	public static void main(String[] args) {
		GobangMain gm = new GobangMain();
		gm.initUI();
	}

	public int[][] chesses=new int[Config.ROWS][Config.COLUMNS];
	/**
	 * ��ʼ������ķ���
	 */
	private void initUI() {
		// ʵ����JFrame�������������Ķ���
		JFrame frame = new JFrame();
		// ���ô��������ֵ
		frame.setTitle("Gobang");
		frame.setSize(660, 480);
		frame.setDefaultCloseOperation(3);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		// ʵ����һ���߿򲼾���Ķ��󣬱߿򲼾��ǰ�������Ϊ5���֣��ϱ����������Ҷ��м�
		BorderLayout bl = new BorderLayout();
		frame.setLayout(bl);

		// ʵ�����м�JPanel������������Ķ���
		// JPanel centerPanel = new JPanel();
		// centerPanel.setBackground(Color.BLUE);
		// ��this��ӵ��߿򲼾ֵ��м䲿��
		frame.add(this, BorderLayout.CENTER);
		this.setBackground(Color.LIGHT_GRAY); //�������̵ĵ�ɫ
		
		
		// ʵ��������JPanel������������Ķ���
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(200, 0));// �������Ĵ�С
		eastPanel.setBackground(Color.LIGHT_GRAY);

		// ʵ������ť����
		JButton buttonStart = new JButton("��ʼ����Ϸ");
		JButton buttonGiveUp = new JButton("����");
		JButton buttonBack = new JButton("����");
		
		
		
		
		// ���ð�ť�Ĵ���
		buttonGiveUp.setPreferredSize(new Dimension(160, 70));
		buttonStart.setPreferredSize(new Dimension(160, 70));
		buttonBack.setPreferredSize(new Dimension(160, 70));
		// ����ť��ӵ����������
		eastPanel.add(buttonStart);
		eastPanel.add(buttonGiveUp);
		eastPanel.add(buttonBack);
		// ʵ������ѡ��ť�ķ������
		ButtonGroup bg = new ButtonGroup();
		// ʵ������ѡ��ť����
		JRadioButton rbPVP = new JRadioButton("���˶�ս");
		JRadioButton rbPVAI = new JRadioButton("�˻���ս");
		// ���е�ѡ��ť����
		bg.add(rbPVP);
		bg.add(rbPVAI);
		rbPVP.setSelected(true);// ����Ĭ��ѡ�е������˶�ս
		// ���õ�ѡ��ť�Ĵ�С
		rbPVP.setPreferredSize(new Dimension(160, 70));
		rbPVAI.setPreferredSize(new Dimension(160, 70));
		// ���ð�ť�ı���ɫ�������ɫֵһ��
		rbPVP.setBackground(Color.LIGHT_GRAY);
		rbPVAI.setOpaque(false);// ���ñ���Ϊ͸����
		// ����ѡ��ť��ӵ����������
		eastPanel.add(rbPVP);
		eastPanel.add(rbPVAI);
		// ��eastPanel��ӵ��߿򲼾ֵĶ���
		frame.add(eastPanel, BorderLayout.EAST);

		frame.setVisible(true);

		// Graphics g = this.getGraphics();
		// g.setColor(Color.black);
		// g.drawLine(100, 50, 400, 50);
		// System.out.println(">>>>>>>>>>>");

		// ʵ���������¼�������Ķ���
		ChessListener cl = new ChessListener();
		// ��ȡ�м�����ϵ�Graphics���ʶ���
		Graphics g = this.getGraphics();
		// ����ȡ��g���ʶ��󴫵ݵ�cl�����g������
		cl.setG(g);
		cl.setChess(chesses);
		cl.repaint(this);  //�ػ洫����

		// ���¼�Դ�м���������궯������������ָ���¼����������Ϊcl
		this.addMouseListener(cl);
		cl.creatHashMap();
		buttonStart.addActionListener(cl);
		buttonGiveUp.addActionListener(cl);
		buttonBack.addActionListener(cl);
		rbPVP.addActionListener(cl);
		rbPVAI.addActionListener(cl);
	}

	/**
	 * ��дJPanel������ػ淽��
	 */
	public void paint(Graphics g) {
		super.paint(g);// super��ʾ����Ķ���super.paint(g)��ʾ���ø����е�paint����
		drawCheeBorder(g);
		System.out.println("�ڶ��γ��ִ����ʱ�����ػ�");
		for (int i = 0; i < chesses.length; i++) {
			for (int j = 0; j < chesses[i].length; j++) {
				if(chesses[i][j] == 1){
					g.setColor(Color.BLACK);
					g.fillOval(Config.X0+i*CHESS_SIZE-(Config.CHESS_SIZE/2), Config.Y0+j*CHESS_SIZE-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
				}else if(chesses[i][j] ==2){
					g.setColor(Color.WHITE);
					//g.fillOval(i*30, j*30, 30, 30);
					g.fillOval(Config.X0+i*CHESS_SIZE-(Config.CHESS_SIZE/2), Config.Y0+j*CHESS_SIZE-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
				}
				
					//g.fillOval(Config.X0+i*CHESS_SIZE-(Config.CHESS_SIZE/2), Config.Y0+j*CHESS_SIZE-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
				
			}
		}
		
		
		
	}

	/**
	 * �������������̵ķ���
	 * 
	 * @param g���ʶ���
	 */
	private void drawCheeBorder(Graphics g) {
		// �������������� ��Ԫ���Сʱ30
		for (int i = 0; i < Config.ROWS; i++) {
			
			g.drawLine(Config.X0,Config.Y0+ Config.SIZE * i, Config.X0 + (Config.COLUMNS-1) * Config.SIZE, Config.Y0+ Config.SIZE * i);// ���ƺ���
			
		}
    for (int j = 0; j < Config.COLUMNS; j++) {
			
			g.drawLine(Config.X0+j*Config.SIZE,Config.Y0, Config.X0 + j* Config.SIZE, Config.Y0+ Config.SIZE *(Config.ROWS-1));// ���ƺ���
			
		}
	}

}
