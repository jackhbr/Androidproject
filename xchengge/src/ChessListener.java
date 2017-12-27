

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
	//�������������ϵ�����
	private int[][] arrayChess;

	public void setArrayChess(int[][] arrayChess){
		this.arrayChess = arrayChess;
	}

	public void setG(Graphics g) {
		this.g = (Graphics2D) g;
		// ���û��ʿ����
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	/**
	 * �������¼�Դ�����Ϸ������������ʱִ�д˷���
	 */
	public void mouseClicked(MouseEvent e) {
		// ��ȡ�����λ�õ�����ֵ
		int x = e.getX();  //������31,32,33
		int y = e.getY();
		count1 = x / 30;   //���͵ķ���ֵ������ֻ��Ϊ1,���ԣ������ѵ������������ˣ�������
		x = count1 * 30;   //�����ǣ����������̫�ɺ��ˣ����ǵ���ʼ���꣬Ȼ�󻭳�����ͼ��ᷢ��
		count2 = y / 30;   //�պ������ӵķ�Χ�����������Ǹ��㴦��ע�⻭Բ��ʱ���x,y�����Ͻǵ����꣬��������������
		y = count2 * 30;   //���ԣ������޵��ɺ��£��������û�г�������������Ҳʹ�Ҷ����꣬Բ������֮����໥��ʶ���ӵļ�����
		if (count % 2 == 0) {
			if (place[x][y] == 0) {
				g.setColor(Color.BLACK);
				g.fillOval(x, y, 30, 30);
				//��arrayChess�д�������1����ʾ����
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
	 * �ж�ˮƽ�����������������
	 * @param x ��ǰ���ӵĽ���λ��
	 * @param y
	 */
	public int sp(int x,int y){
		int chess = 0;  //��¼������������
		//����ͳ��
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
