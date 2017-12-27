package AI2;

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
//学会开始注意这些属性的使用范围
	private Graphics2D g;  //用来传递画笔这个对象
	private JPanel JP;     //用来传递前面的画板对象，这样就可以在类里面重绘
	int clickFlag=0;//点击有效的标志位，1代表有效，用于防止在人机模式中点击无效时也继续下白棋
	
	int maxhang=0;  //记录了电脑下的棋子的位置，在这里定义就可以在整个类里面用了，这样在悔棋的
	int maxlie=0;   //时候就可以同时悔掉两个棋
	private int nowI=0,nowJ=0;//记录适合的I和j，不让判断输赢的部分重复执行多次，提高效率
    //同时在这里定义还可以在悔棋的方法中使用
	
	
	int countChess=0;//调试用，用于计算电脑到底下了多少个棋子
	
	
	public boolean winflag=false;  //定义胜利的标志位，true时为胜利
	
	private boolean manRobot=false;  //定义人机功能的标志位，true时为开启人机模式
	
	HashMap<String,Integer> hm = new HashMap<String,Integer>();
   //这个东西的作用就相当于建立一个对应的关系，这个string对应这个integer，
	//不过这里的对应关系只能是单向的，所以他的get方法里面的参数必定是第一个，返回值必然是第二个
	//注意里面的参数都只能是类，不能用基本的数据类型
	
	

//	
	// 1.1.声明一个存储按钮内容的字符串属性        默认的
		private String type = "开始新游戏"; 
	
		//定义一个变量来确定棋子的颜色
	private int count=0;
	
	
	
	//定义一个二维数组来标记棋盘上的位置
	public int[][] chesses=new int[Config.ROWS][Config.COLUMNS];  //注意这里要用public，不能用private，不然在其他类里就不能用了
	
	
//  hm.put("11",200);           方法只能在类里面的方法里调用，不能直接在类里面调用
//  hm.put("111",2000);
//  hm.put("12",10);
//  hm.put("112",100);
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
		 
		this.chesses=chesses;  //数组里面的地址的赋值，所以这两个数组就相当于是一样了，改变这里的数组的同时也改变了对面类里的数组
	}
	
	int[][] chessValue = new int[Config.ROWS][Config.COLUMNS];//存放权值
	
	public void setG(Graphics g) {
		this.g = (Graphics2D) g;  
		// 设置画笔抗锯齿   所以在这里的是2D的类型
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	
	
	public void repaint(JPanel JP ) {
		this.JP=JP;   //重绘的传参
		
		
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
						
						for(int i=0;i<Config.ROWS;i++)  //横向扫描
						{
							for(int j=0;j<Config.COLUMNS;j++)  //先纵向扫描
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
									System.out.println("您点击的行的坐标为");
									if(chesses[i][j]==0)   //如果是空的话，就加上棋子
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
						
						if(manRobot==true && clickFlag==1)  //同时开启人机模式和点击有效才执行下面的程序
						  {
							clickFlag=0;  //及时清除这个点击有效的标志位，不然后面就会出错了
								AI();  //通过AI算法给各个位置都加上权值
								int maxvalue=0;  //最大的权值，调试的时候用
								
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
			count=0;  //首先是黑子先下
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
	
	
	
	
	//x是横坐标，y是纵坐标
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
					//	hm.get("111");
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
