

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
 * �¸�   2195049056
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
		// ��������Ļ�������
		JFrame jf = new JFrame();// �����������
		jf.setTitle("FIVECHESS");// ����
		jf.setSize(660, 480);
		jf.setDefaultCloseOperation(3);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);

		BorderLayout bl = new BorderLayout();
		jf.setLayout(bl);
		jf.add(this, BorderLayout.CENTER);
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(200, 0));// �������Ĵ�С
		eastPanel.setBackground(Color.LIGHT_GRAY);

		JButton buttonStart = new JButton("��ʼ����Ϸ");
		JButton buttonGiveUp = new JButton("����");
		JButton buttonBack = new JButton("����");

		buttonGiveUp.setPreferredSize(new Dimension(160, 70));
		buttonStart.setPreferredSize(new Dimension(160, 70));
		buttonBack.setPreferredSize(new Dimension(160, 70));

		eastPanel.add(buttonStart);
		eastPanel.add(buttonGiveUp);
		eastPanel.add(buttonBack);

		ButtonGroup bg = new ButtonGroup();
		JRadioButton rbPVP = new JRadioButton("���˶�ս");
		JRadioButton rbPVAI = new JRadioButton("�˻���ս");
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
		// ʵ���������¼�������Ķ���
		ChessListener cl = new ChessListener();
		// ��ȡ�м�����ϵ�Graphics���ʶ���
		Graphics g = this.getGraphics();
		// ����ȡ��g���ʶ��󴫵ݵ�cl�����g������
		cl.setG(g);
		cl.setArrayChess(arrayChess);

		this.addMouseListener(cl);
	}

	/**
	 * ��дJPanel������ػ淽��
	 */
	public void paint(Graphics g) {
		super.paint(g);// super��ʾ����Ķ���super.paint(g)��ʾ���ø����е�paint����
		drawCheeBorder(g);
		for (int i = 0; i < arrayChess.length; i++) {
			for (int j = 0; j < arrayChess[i].length; j++) {
				if(arrayChess[i][j] == 1){
					g.setColor(Color.BLACK);
					g.fillOval(i*30, j*30, 30, 30);      //�����ػ�ķ���ֻ����ض�����ʼ���ֻ꣬���������ܲ�ƫ��
				}else if(arrayChess[i][j] == 2){         //���������Ļ�����������ʾ�����ֱ����I��j������Ҫ����˼�����жϣ�
					g.setColor(Color.WHITE);
					g.fillOval(i*30, j*30, 30, 30);
				}
			}
		}
		System.out.println("..............");
	}

	/**
	 * �������������̵ķ���
	 * 
	 * @param g���ʶ���
	 */
	private void drawCheeBorder(Graphics g) {
		// �������������� ��Ԫ���Сʱ30
		for (int i = 0; i < 15; i++) {
			g.drawLine(15, 15 + 30 * i, 15 + 14 * 30, 15 + 30 * i);// ���ƺ���
			g.drawLine(15 + 30 * i, 15, 15 + 30 * i, 15 + 14 * 30);// ��������
		}
	}

}
