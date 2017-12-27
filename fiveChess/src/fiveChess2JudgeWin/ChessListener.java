package fiveChess2JudgeWin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 定义下棋的事件处理类,该类继承自MouseAdapter鼠标适配器类
 */
public class ChessListener extends MouseAdapter implements Config {

	private Graphics2D g;
	
	public boolean winflag=false;
	
	private int count=0;
	//定义一个二维数组来标记棋盘上的位置
	public int[][] chesses=new int[Config.ROWS][Config.COLUMNS];  //注意这里要用public，不能用private，不然在其他类里就不能用了
	
	public void setChess(int[][] chesses)
	{
		this.chesses=chesses;
	}
	
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
		if(winflag==false)  //在这里加上这个标志位就可以在赢了以后不再进行任何操作了
		{
		int x1 = e.getX();
		int y1= e.getY();
		int nowI=0,nowJ=0;//记录适合的I和j，不让判断输赢的部分重复执行多次，提高效率
		
		for(int j=0;j<Config.ROWS;j++)
		{
			for(int i=0;i<Config.COLUMNS;i++)
			{
				
				int x=Config.X0+Config.SIZE*i;//横坐标
				int y=Config.Y0+Config.SIZE*j;//纵坐标
				//与圆心的误差为size/3
				if(x1>x-Config.SIZE/3 && x1<x+Config.SIZE/3 && y1>y-Config.SIZE/3 && y1<y+Config.SIZE/3)
				{
					
					nowI=i;
					nowJ=j;
					if(chesses[i][j]==0)
					{
						if(count==0)  //第一次来的时候，如果是0，那就黑子先下，然后count++，反正就是count为0的时候下黑棋，否则下白棋
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
					   
//						int chess = level(i,j);
//						System.out.println("水平相同元素的个数为"+chess);
//						int chess1=Upright(i,j);
//						System.out.println("竖直相同元素的个数为"+chess1);
//						int chess2=upperLeft(i,j);
//						System.out.println("左上角相同元素的个数为"+chess2);
//						int chess3=upperRight(i,j);
//						System.out.println("右上角相同元素的个数为"+chess3);
						
						
						
						
						
				   }
				}
			}
		}
		
		if(level(nowI,nowJ)>=5 || Upright(nowI,nowJ)>=5 || upperLeft(nowI,nowJ)>=5 || upperRight(nowI,nowJ)>=5)
		{
			winflag=true;
			if(chesses[nowI][nowJ]==1)
			{
				
				System.out.println("黑方胜利");
			}
			else if(chesses[nowI][nowJ]==-1) 
			
			{
				
				System.out.println("白方胜利");
			}
			
		}
		
		
		}
		
	}
	
	public int level(int x,int y){
		int chess = 0;  //记录棋子相连个数
		//向右统计
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
	
	public int Upright(int x,int y){  //竖直方向上的
		int chess = 0;  //记录棋子相连个数
		//向右统计
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
	{  //左上方向上的
		int chess = 0;  //记录棋子相连个数
		//向右统计
		for(int i=1;i<Config.ROWS;i++){  //这里不太确定
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
		for(int i=0;i<Config.ROWS;i++){  //这里不太确定
			
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
	{  //左上方向上的
		int chess = 0;  //记录棋子相连个数
		//向右统计
		for(int i=1;i<Config.ROWS;i++){  //这里不太确定
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
		for(int i=0;i<Config.ROWS;i++){  //这里不太确定
			
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
