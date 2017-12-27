package fiveChess2JudgeWin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ����������¼�������,����̳���MouseAdapter�����������
 */
public class ChessListener extends MouseAdapter implements Config {

	private Graphics2D g;
	
	public boolean winflag=false;
	
	private int count=0;
	//����һ����ά��������������ϵ�λ��
	public int[][] chesses=new int[Config.ROWS][Config.COLUMNS];  //ע������Ҫ��public��������private����Ȼ����������Ͳ�������
	
	public void setChess(int[][] chesses)
	{
		this.chesses=chesses;
	}
	
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
		if(winflag==false)  //��������������־λ�Ϳ�����Ӯ���Ժ��ٽ����κβ�����
		{
		int x1 = e.getX();
		int y1= e.getY();
		int nowI=0,nowJ=0;//��¼�ʺϵ�I��j�������ж���Ӯ�Ĳ����ظ�ִ�ж�Σ����Ч��
		
		for(int j=0;j<Config.ROWS;j++)
		{
			for(int i=0;i<Config.COLUMNS;i++)
			{
				
				int x=Config.X0+Config.SIZE*i;//������
				int y=Config.Y0+Config.SIZE*j;//������
				//��Բ�ĵ����Ϊsize/3
				if(x1>x-Config.SIZE/3 && x1<x+Config.SIZE/3 && y1>y-Config.SIZE/3 && y1<y+Config.SIZE/3)
				{
					
					nowI=i;
					nowJ=j;
					if(chesses[i][j]==0)
					{
						if(count==0)  //��һ������ʱ�������0���Ǿͺ������£�Ȼ��count++����������countΪ0��ʱ���º��壬�����°���
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
					   
//						int chess = level(i,j);
//						System.out.println("ˮƽ��ͬԪ�صĸ���Ϊ"+chess);
//						int chess1=Upright(i,j);
//						System.out.println("��ֱ��ͬԪ�صĸ���Ϊ"+chess1);
//						int chess2=upperLeft(i,j);
//						System.out.println("���Ͻ���ͬԪ�صĸ���Ϊ"+chess2);
//						int chess3=upperRight(i,j);
//						System.out.println("���Ͻ���ͬԪ�صĸ���Ϊ"+chess3);
						
						
						
						
						
				   }
				}
			}
		}
		
		if(level(nowI,nowJ)>=5 || Upright(nowI,nowJ)>=5 || upperLeft(nowI,nowJ)>=5 || upperRight(nowI,nowJ)>=5)
		{
			winflag=true;
			if(chesses[nowI][nowJ]==1)
			{
				
				System.out.println("�ڷ�ʤ��");
			}
			else if(chesses[nowI][nowJ]==-1) 
			
			{
				
				System.out.println("�׷�ʤ��");
			}
			
		}
		
		
		}
		
	}
	
	public int level(int x,int y){
		int chess = 0;  //��¼������������
		//����ͳ��
		for(int i=x+1;i<chesses.length;i++){
			if(chesses[i][y] == chesses[x][y]){
				chess++;
			}else{
				break;
			}
		}
		for(int i=x;i>=0;i--){
			if(chesses[i][y] == chesses[x][y]){
				chess++;
			}else{
				break;
			}
		}
		return chess;
		
	}
	
	public int Upright(int x,int y){  //��ֱ�����ϵ�
		int chess = 0;  //��¼������������
		//����ͳ��
		for(int i=y+1;i<chesses[1].length;i++){
			if(chesses[x][i] == chesses[x][y]){
				chess++;
			}else{
				break;
			}
		}
		for(int i=y;i>=0;i--){
			if(chesses[x][i] == chesses[x][y]){
				chess++;
			}else{
				break;
			}
		}
		return chess;
		
	}
	public int upperLeft(int x,int y)
	{  //���Ϸ����ϵ�
		int chess = 0;  //��¼������������
		//����ͳ��
		for(int i=1;i<Config.ROWS;i++){  //���ﲻ̫ȷ��
			if((x+i)>(chesses.length-1)  ||  (y+i)>(chesses[1].length-1))
			{break;}
			if(chesses[x+i][y+i] == chesses[x][y]){
				chess++;
			}else{
				break;
			}
			if((x+i)>=(chesses.length-1)  ||  (y+i)>=(chesses[1].length-1))
			{break;}
		}
		for(int i=0;i<Config.ROWS;i++){  //���ﲻ̫ȷ��
			
			if(chesses[x-i][y-i] == chesses[x][y]){
				chess++;
			}else{
				break;
			}
			if((x-i)<=0  ||  (y-i)<=0)
			{break;}
		}
		return chess;
		
	}
	
	public int upperRight(int x,int y)
	{  //���Ϸ����ϵ�
		int chess = 0;  //��¼������������
		//����ͳ��
		for(int i=1;i<Config.ROWS;i++){  //���ﲻ̫ȷ��
			if((x+i)>(chesses.length-1) || y-i<0 )
			{break;}
			if(chesses[x+i][y-i] == chesses[x][y]){
				chess++;
			}else{
				break;
			}
			if((y-i)<=0)
			{break;}
		}
		for(int i=0;i<Config.ROWS;i++){  //���ﲻ̫ȷ��
			
			if((y+i)>(chesses[1].length-1)  || x-i<0){break;}
			if(chesses[x-i][y+i] == chesses[x][y]){
				chess++;
			}else{
				break;
			}
			if((x-i)<=0)
			{break;}
		}
			
		return chess;
		
	}
	
	
	
	
	
	
}
