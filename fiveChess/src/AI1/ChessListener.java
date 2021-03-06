package AI1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.JPanel;

/**
 * 定义下棋的事件处理类,该类继承自MouseAdapter鼠标适配器类
 */
public class ChessListener extends MouseAdapter implements Config ,ActionListener{

	private Graphics2D g;
	private JPanel JP;
	
	int countChess=0;//调试用，用于计算电脑到底下了多少个棋子
	
	private int nowI=0,nowJ=0;//记录适合的I和j，不让判断输赢的部分重复执行多次，提高效率
	
	public boolean winflag=false;
	
	private boolean manRobot=false;
	
	HashMap<String,Integer> hm = new HashMap<String,Integer>();
   
//    hm.put("11",200);           方法只能在类里面的方法里调用，不能直接在类里面调用
//    hm.put("111",2000);
//    hm.put("12",10);
//    hm.put("112",100);
//	
	// 1.1.声明一个存储按钮内容的字符串属性
		private String type = "开始新游戏";
	
	private int count=0;
	//定义一个二维数组来标记棋盘上的位置
	public int[][] chesses=new int[Config.ROWS][Config.COLUMNS];  //注意这里要用public，不能用private，不然在其他类里就不能用了
	
	private int maxvalue=0;
	private  int maxhang=0;
	private  int maxlie=0;
	
	public void creatHashMap()
	{
		hm.put("1",20);      //1为黑棋，2为白棋（机子下的棋）
   		hm.put("11",200);
		hm.put("111",700);
		hm.put("1111",800);
		//hm.put("2",10);
		hm.put("12",10);
		hm.put("112",100);
		hm.put("1112",600);
		hm.put("11112",800);
	}
	
	public void setChess(int[][] chesses)
	{       
		 
		this.chesses=chesses;
	}
	
	int[][] chessValue = new int[Config.ROWS][Config.COLUMNS];//存放权值
	
	public void setG(Graphics g) {
		this.g = (Graphics2D) g;
		// 设置画笔抗锯齿
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	public void repaint(JPanel JP ) {
		this.JP=JP;
		
		
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
					//	int nowI=0,nowJ=0;//记录适合的I和j，不让判断输赢的部分重复执行多次，提高效率
						
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
											count=count+1;
										}
										else {
											chesses[i][j]=2;//如果是白子，就为2
											g.setColor(Color.WHITE);
											count=count-1;
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
							else if(chesses[nowI][nowJ]==2) 
							
							{
								
								System.out.println("白方胜利");
							}
							
						}
						
						if(manRobot==true)
						  {
							
							
							for(int i=0;i<Config.ROWS;i++)  //每次的布局都不同，所以下完一次后要清零权值
							{
								for(int j=0;j<Config.COLUMNS;j++)
								{	
										chessValue[i][j]=0;
								}
							}
							
							
							
							AI();
//							int maxvalue=0;
//							int maxhang=0;
//							int maxlie=0;
							for(int i=0;i<Config.ROWS;i++)
							{
								for(int j=0;j<Config.COLUMNS;j++)
								{
									if(chessValue[i][j]>=maxvalue)
									{
										maxvalue=chessValue[i][j];
										maxhang=i;
										maxlie=j;
									}
									
									
								}
							}
							System.out.println("最大权值为"+maxvalue);
							chesses[maxhang][maxlie]=2;//如果是白子，就为2
							g.setColor(Color.WHITE);
						//	count=count-1;
							count=0;//暗示着下一个下黑棋
							System.out.println("count为"+count);
							g.fillOval(Config.X0+Config.SIZE*maxhang-(Config.CHESS_SIZE/2), Config.Y0+Config.SIZE*maxlie-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
							countChess=countChess+1;
							System.out.println("电脑下的棋子数为"+countChess);
							System.out.println("电脑下的棋子坐标为行数为"+maxhang+"   列数为"+maxlie);
							System.out.println("  ");
							System.out.println("  ");
							
							if(level(maxhang,maxlie)>=5 || Upright(maxhang,maxlie)>=5 || upperLeft(maxhang,maxlie)>=5 || upperRight(maxhang,maxlie)>=5) //加了这个白方也能赢了
							{
								winflag=true;
								if(chesses[maxhang][maxlie]==1)
								{
									
									System.out.println("黑方胜利");
								}
								else if(chesses[maxhang][maxlie]==2) 
								
								{
									
									System.out.println("白方胜利");
								}
								
							}
							
							
							for(int i=0;i<Config.ROWS;i++)  //每次的布局都不同，所以下完一次后要清零权值
							{
								for(int j=0;j<Config.COLUMNS;j++)
								{	
										chessValue[i][j]=0;
								}
							}
							
							
							
						  }
		
		}
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		type = e.getActionCommand();// 获取按钮上的文字信息，如果没有文字信息，输出空字符串""
	
		if(type.equals("开始新游戏"))
		{
			winflag=false;
			count=0;
			System.out.println("type = " + type);
			for(int j=0;j<Config.ROWS;j++)
			{
				for(int i=0;i<Config.COLUMNS;i++)
				{
					
				chesses[i][j]=0;
					
				}
			}
			JP.paint(g);
		}
		else if(type.equals("认输"))
		{
			winflag=true;
			System.out.println("认输");
			if(count==0)
			{
				System.out.println("白方胜利！");
			}
			else
			{
				System.out.println("黑方胜利！");
			}
		}
		else if(type.equals("悔棋"))
		{
			chesses[nowI][nowJ]=0;
			
			if(count==0)
			{
				count=1;
			}
			else {
				count=0;
			}
			
			if(manRobot==true)
			{
				chesses[maxhang][maxlie]=0;
				count=0;
			}
			JP.paint(g);
			
			
			System.out.println("悔棋"); 
		}
		else if(type.equals("人机对战"))
		{
			System.out.println("人机对战");
			System.out.println("您先下");
			winflag=false;
			for(int j=0;j<Config.ROWS;j++)
			{
				for(int i=0;i<Config.COLUMNS;i++)
				{
					
				chesses[i][j]=0;
					
				}
			}
			JP.paint(g);
			count=0;
			 manRobot=true;
		}
		else if(type.equals("人人对战"))
		{
			winflag=false;
			for(int j=0;j<Config.ROWS;j++)
			{
				for(int i=0;i<Config.COLUMNS;i++)
				{
					
				chesses[i][j]=0;
					
				}
			}
			JP.paint(g);
			count=0;
			 manRobot=false;
			
			System.out.println("人人对战");
			
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
	
	
	
	
	
	 public void AI(){
			//全盘搜索
			for(int i=0;i<chesses.length;i++){
				for(int j=0;j<chesses[i].length;j++){
					//空位才需要给权值
					if(chesses[i][j] == 0)  //搜索该空位八个方向上棋子相连情况
					{
						
						//定义：chess变量记录棋子相连情况，color记录棋子颜色
						String chess="";
					//	int color = 0;
						//向右搜索棋子相连情况
						for(int k=i+1;k<chesses.length;k++)
						{
								if(chesses[k][j] == 0)
								{
									break;
								}
								else
								{
//									if(color==0)
//									{  //右边第一颗棋子   color等于1则为黑色，人下的棋
//										chess+=chesses[k][j];  //记录棋子相连情况   字符串加上数字，就等于强制转换为了字符串
//										color = chesses[k][j];
//									}
									if(chesses[k][j] == 1)
									{
										chess+=chesses[k][j];
									}
									else
									{  
										//颜色不同的情况
										chess+=chesses[k][j];
										break;
									}
								}
						}
						//根据棋子相连情况取出对应的权值累加
						Integer value = hm.get(chess);
						if(value != null){
							chessValue[i][j]+=value;
						}
						
						
						
						//向左搜索棋子相连的情况
						chess="";
						for(int k=i-1;k>=0;k--)
						{
								if(chesses[k][j] == 0)
								{
									break;
								}
								else
								{
									if(chesses[k][j] == 1)
									{
										chess+=chesses[k][j];
									}
									else
									{  
										//颜色不同的情况
										chess+=chesses[k][j];
										break;
									}
								}
						}
						Integer value1 = hm.get(chess);
						if(value1 != null){
							chessValue[i][j]+=value1;
						}
						
						
						//向下搜索棋子相连的情况
						chess="";
						for(int k=j+1;k<chesses[i].length;k++)
						{
								if(chesses[i][k] == 0)
								{
									break;
								}
								else
								{
									if(chesses[i][k] == 1)
									{
										chess+=chesses[i][k];
									}
									else
									{  
										//颜色不同的情况
										chess+=chesses[i][k];
									//	System.out.println("向下的字符串是"+chess);
										break;
									}
								}
						}
						Integer value2 = hm.get(chess);
						if(value2 != null){
							chessValue[i][j]+=value2;
							//System.out.println("向下的权值为"+(int)value2);
						}
						
						
						//向上搜索棋子相连的情况
						chess="";
						for(int k=j-1;k>=0;k--)
						{
								if(chesses[i][k] == 0)
								{
									break;
								}
								else
								{
									if(chesses[i][k] == 1)
									{
										chess+=chesses[i][k];
									}
									else
									{  
										//颜色不同的情况
										chess+=chesses[i][k];
										break;
									}
								}
						}
						Integer value3 = hm.get(chess);
						if(value3 != null){
							chessValue[i][j]+=value3;
						}
						
						
						//向左上搜索棋子相连的情况
						chess="";
						for(int k=j-1,p=i-1;k>=0 && p>=0;k--,p--)
						{
								if(chesses[p][k] == 0)
								{
									break;
								}
								else
								{
									if(chesses[p][k] == 1)
									{
										chess+=chesses[p][k];
									}
									else
									{  
										//颜色不同的情况
										chess+=chesses[p][k];
										break;
									}
								}
						}
						Integer value4 = hm.get(chess);
						if(value4 != null){
							chessValue[i][j]+=value4;
						}
						
						
						//向右下搜索棋子相连的情况
						chess="";
						for(int k=j+1,p=i+1;k<chesses[i].length && p<chesses.length;k++,p++)
						{
								if(chesses[p][k] == 0)
								{
									break;
								}
								else
								{
									if(chesses[p][k] == 1)
									{
										chess+=chesses[p][k];
									}
									else
									{  
										//颜色不同的情况
										chess+=chesses[p][k];
										break;
									}
								}
						}
						Integer value5 = hm.get(chess);
						if(value5 != null){
							chessValue[i][j]+=value5;
						}
						
						
						//向右上搜索棋子相连的情况
						chess="";
						for(int k=j-1,p=i+1;k>=0 && p<chesses.length;k--,p++)
						{
								if(chesses[p][k] == 0)
								{
									break;
								}
								else
								{
									if(chesses[p][k] == 1)
									{
										chess+=chesses[p][k];
									}
									else
									{  
										//颜色不同的情况
										chess+=chesses[p][k];
										break;
									}
								}
						}
						Integer value6 = hm.get(chess);
						if(value6 != null){
							chessValue[i][j]+=value6;
						}
						
						//向左下搜索棋子相连的情况
						chess="";
						for(int k=j+1,p=i-1;k<chesses[i].length && p>=0;k++,p--)
						{
								if(chesses[p][k] == 0)
								{
									break;
								}
								else
								{
									if(chesses[p][k] == 1)
									{
										chess+=chesses[p][k];
									}
									else
									{  
										//颜色不同的情况
										chess+=chesses[p][k];
										break;
									}
								}
						}
						Integer value7= hm.get(chess);
						if(value7!= null){
							chessValue[i][j]+=value7;
						}
						
						
						
						
						
						
						
						
						
					}
				}
			}
		 }	
	
	
	
	
	
}
