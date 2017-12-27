package fiveChess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ����������¼�������,����̳���MouseAdapter�����������
 */
public class ChessListener extends MouseAdapter {

	private Graphics2D g;
    
	private int count=0;
	//����һ����ά��������������ϵ�λ��
	private int[][] chesses=new int[Config.ROWS][Config.COLUMNS];
	
	public void setG(Graphics g) {
		this.g = (Graphics2D) g;
		// ���û��ʿ����
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}

	/**
	 * �������¼�Դ�����Ϸ������������ʱִ�д˷���
	 */
	public void mouseClicked(MouseEvent e) {
		// ��ȡ�����λ�õ�����ֵ
		int x1 = e.getX();
		int y1= e.getY();
		
		for(int j=0;j<Config.ROWS;j++)
		{
			for(int i=0;i<Config.COLUMNS;i++)
			{
				int x=Config.X0+Config.SIZE*i;//������
				int y=Config.Y0+Config.SIZE*j;//������
				//��Բ�ĵ����Ϊsize/3
				if(x1>x-Config.SIZE/3 && x1<x+Config.SIZE/3 && y1>y-Config.SIZE/3 && y1<y+Config.SIZE/3)
				{
					if(chesses[i][j]==0)
					{
					if(count==0)
					{
						chesses[i][j]=1;//����Ǻ��ӣ���Ϊ1
						g.setColor(Color.BLACK);
						count++;
					}
					else {
						chesses[i][j]=-1;//����ǰ��ӣ���Ϊ-1
						g.setColor(Color.WHITE);
						count--;
					}
					// ����������
					//����2�ͳ���0.5�Ĳ�𣺳���0.5��Ҫǿ��ת��������2ֱ�Ӿ���
					g.fillOval(x-(Config.CHESS_SIZE/2), y-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
				}
				}
			}
		}
		
		
		
		
	}
}
