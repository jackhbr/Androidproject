package AI4winmore;
//失败，太复杂了，加上其它东西又会相互影响，情况太多，这种方法更本不能胜率高
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

import AI1.Config;

/**
 * 定义下棋的事件处理类,该类继承自MouseAdapter鼠标适配器类
 */
public class ChessListener extends MouseAdapter implements Config ,ActionListener{

	private Graphics2D g;
	private JPanel JP;
	private int clickFlag=0;//点击有效的标志位，1代表有效
	
	private  int maxhang=0;
	private int maxlie=0;
	
	
	private int countChess=0;//调试用，用于计算电脑到底下了多少个棋子
	
	private int nowI=0,nowJ=0;//记录适合的I和j，不让判断输赢的部分重复执行多次，提高效率
	
	private boolean winflag=false;
	
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
	public int[][] chesses;  //注意这里要用public，不能用private，不然在其他类里就不能用了
	
	public void creatHashMap()
	{
		//最大10000
		hm.put("1",20);      //1为黑棋，2为白棋（机子下的棋）
   		hm.put("11",200);
		hm.put("111",3000);
		hm.put("1111",10000);
		//hm.put("2",10);
		hm.put("12",10);
		hm.put("112",100);
		hm.put("1112",2000);
		hm.put("11112",10000);
	
		hm.put("11311",10000);
		hm.put("1131",3000);
		hm.put("1311",3000);
		hm.put("1123311",10000);
		hm.put("112331",2000);
		hm.put("11131",10000);
		hm.put("13111",10000);
		hm.put("113112",10000);
		
		hm.put("2222",10000);
		hm.put("22221",10000);
		hm.put("222",4000);
		hm.put("22332",4000);
		hm.put("23322",4000);
	
	
	
	
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
						
						for(int i=0;i<Config.ROWS;i++)
						{
							for(int j=0;j<Config.COLUMNS;j++)
							{
								
								int x=Config.X0+Config.SIZE*i;//横坐标 ,对应的是列数,x为水平向右，y为竖直向下
								int y=Config.Y0+Config.SIZE*j;//纵坐标
								//与圆心的误差为size/3
								if(x1>x-Config.SIZE/3 && x1<x+Config.SIZE/3 && y1>y-Config.SIZE/3 && y1<y+Config.SIZE/3)
								{
									clickFlag=1;
									
									nowI=i;
									nowJ=j;
									System.out.println("您点击的行的坐标为"+nowJ+"  列的坐标为"+nowI);
									
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
										//在3行4列画一个黑子      悲惨，这里的x是列，y才是行。。。。
								//		g.fillOval(Config.X0+Config.SIZE*3-(Config.CHESS_SIZE/2), Config.Y0+Config.SIZE*4-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
	   
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
						
						if(manRobot==true && clickFlag==1)
						  {
							clickFlag=0;  //及时清除这个点击有效的标志位，不然后面就会出错了
								AI();
								int maxvalue=0;
								
								for(int i=0;i<Config.ROWS;i++)
								{
									for(int j=0;j<Config.COLUMNS;j++)
									{
										if(chessValue[i][j]>=maxvalue)  //放入第一个框中的应该是水平的x,而此时的j才是x.
										{
											maxvalue=chessValue[i][j];   //行数和列数输出的不对，不知道是画的问题，还是哪里的问题，差了一个
											maxhang=j;//这里的j，才是坐标里的x ,行就是y,x就是列
											maxlie=i;
										}
										
										
									}
								}
								System.out.println("最大权值为"+maxvalue);
								chesses[maxlie][maxhang]=2;//如果是白子，就为2
								g.setColor(Color.WHITE);
							//	count=count-1;
								count=0;//暗示着下一个下黑棋
								System.out.println("count为"+count);
							//	g.fillOval(Config.X0+Config.SIZE*maxhang-(Config.CHESS_SIZE/2), Config.Y0+Config.SIZE*maxlie-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
									g.fillOval(Config.X0+Config.SIZE*maxlie-(Config.CHESS_SIZE/2), Config.Y0+Config.SIZE*maxhang-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
	
								countChess=countChess+1;
								System.out.println("电脑下的棋子数为"+countChess);
								System.out.println("电脑下的棋子坐标为行数为"+maxhang+"   列数为"+maxlie);
								System.out.println("  ");
								System.out.println("  ");
								for(int i=0;i<Config.ROWS;i++)  //每次的布局都不同，所以下完一次后要清零权值
								{
									for(int j=0;j<Config.COLUMNS;j++)
									{	
											chessValue[i][j]=0;
									}
								}
								if(level(maxlie,maxhang)>=5 || Upright(maxlie,maxhang)>=5 || upperLeft(maxlie,maxhang)>=5 || upperRight(maxlie,maxhang)>=5) //加了这个白方也能赢了
								{
									winflag=true;
									if(chesses[maxlie][maxhang]==1)
									{
										
										System.out.println("黑方胜利");
									}
									else if(chesses[maxlie][maxhang]==2) 
									
									{
										
										System.out.println("白方胜利");
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
			for(int i=0;i<Config.ROWS;i++)
			{
				for(int j=0;j<Config.COLUMNS;j++)
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
				chesses[maxlie][maxhang]=0;
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
			for(int i=0;i<Config.ROWS;i++)
			{
				for(int j=0;j<Config.COLUMNS;j++)
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
	
	
	
	
	
	public int level(int x,int y)
	{
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
	
	//这里可以用for(int i=x+1,j=y;i<config.rows,j<config.colums;i++,j++)这种格式来写，比较简洁
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
			for(int i=0;i<chesses.length;i++)
			{
				for(int j=0;j<chesses[i].length;j++)
				{
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
							//水平搜索棋子情况
							   chess="";								
								for(int k=i+1;k<chesses.length;k++)
								{
									if(chesses[k][j] == 0)
									{
										break;
									}
									else
									{
										if(chesses[k][j]==1)	
										{
											chess+=chesses[k][j];
										}
										else
										{  
											//颜色不同的情况
											chess+=chesses[k][j];
											chess+=3;//用个三结尾	
											break;
										}
										
										
									}
								}chess+=3;//用个三结尾,加在1的后面
								for(int k=i;k>=0;k--){
									if(chesses[k][j] == 0){
										break;
									}else
									{
										if(chesses[k][j]==1)	
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
								Integer value8= hm.get(chess);
								if(value8!= null)
								{
									chessValue[i][j]+=value8;
								}
								
								//竖直搜索棋子情况
								   chess="";
						
								
									for(int k=j+1;k<chesses[1].length;k++)
									{
										if(chesses[i][k] == 0){
											break;
										}else
										{
											if(chesses[i][k]==1)	
											{
												chess+=chesses[i][k];
											}
											else
											{  
												//颜色不同的情况
												chess+=chesses[i][k];
												chess+=3;//用个三结尾	
												break;
											}
											
											
										}
									}chess+=3;//用个三结尾,加在1的后面
									for(int p=j;p>=0;p--)
									{
										if(chesses[i][p] == 0){
											break;
										}else
										{
											if(chesses[i][p]==1)	
											{
												chess+=chesses[i][p];
											}
											else
											{  
												//颜色不同的情况
												chess+=chesses[i][p];
												break;										
											}
											
										}
									}
									Integer value9= hm.get(chess);
									if(value9!= null)
									{
										chessValue[i][j]+=value9;
									}
								
									 //左上方向上的
									
									 chess="";
										//向右统计
										for(int k=1;k<Config.ROWS;k++)
										{  //这里不太确定
											if((i+k)>(chesses.length-1)  ||  (j+k)>(chesses[1].length-1))
											{break;}
											if(chesses[i+k][j+k] == 0){
												break;
											}else
											{
												if(chesses[i+k][j+k]==1)	
												{
													chess+=chesses[i][k];
												}
												else
												{  
													//颜色不同的情况
													chess+=chesses[i+k][j+k];
													chess+=3;//用个三结尾	
													break;
												}
												
												
											}
											if((i+k)>=(chesses.length-1)  ||  (j+k)>=(chesses[1].length-1))
											{break;}
										}chess+=3;//用个三结尾,加在1的后面
										for(int p=0;p<Config.ROWS;p++)
										{  //这里不太确定
											
											if(chesses[i-p][j-p] == 0){
												break;
											}else
											{
												if(chesses[i-p][j-p]==1)	
												{
													chess+=chesses[i-p][j-p];
												}
												else
												{  
													//颜色不同的情况
													chess+=chesses[i-p][j-p];
													break;										
												}
												
												
											}
												if((i-p)<=0  ||  (j-p)<=0)
											{break;}
										}
										Integer value10= hm.get(chess);
										if(value10!= null)
										{
											chessValue[i][j]+=value10;
										}
										
										//右上方向上的
										 chess="";
											//向右统计
											for(int k=1;k<Config.ROWS;k++)
											{  //这里不太确定
												if((i+k)>(chesses.length-1) || j-k<0 )
												{break;}
												if(chesses[i+k][j-k] == 0){
													break;
												}else
												{
													if(chesses[i+k][j-k]==1)	
													{
														chess+=chesses[i+k][j-k];
													}
													else
													{  
														//颜色不同的情况
														chess+=chesses[i+k][j-k];
														chess+=3;//用个三结尾	
														break;
													}
												
													
												}
												if((j-k)<=0)
												{break;}
											}chess+=3;//用个三结尾,加在1的后面
											for(int p=0;p<Config.ROWS;p++){  //这里不太确定
												
												if((j+p)>(chesses[1].length-1)  || i-p<0){break;}
												if(chesses[i-p][j+p] ==0){
													break;
												}else
												{
													if(chesses[i-p][j+p]==1)	
													{
														chess+=chesses[i-p][j+p];
													}
													else
													{  
														//颜色不同的情况
														chess+=chesses[i-p][j+p];
														break;										
													}
													
													
												}
												if((i-p)<=0)
												{break;}
											}
												
											Integer value11= hm.get(chess);
											if(value11!= null)
											{
												chessValue[i][j]+=value11;
											}
										
										
									
									
						
																											
					}
				}
			}
		 }	
	
	
	
	
	
}
