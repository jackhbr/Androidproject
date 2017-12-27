package BoYiShu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessPanel extends JPanel {
	private ImageIcon map;
	private ImageIcon whitechess;
	private ImageIcon blackchess;
	public int[][]  isChessOn;
	protected boolean win=false;//默认
	protected int win_bw;
	protected int deep=3,weight=7;
	public int drawn_num=110;
	int chess_num=0;
	public int[][] pre=new int[drawn_num+1][2];
	public int bw=0;//表示当前的棋色
	public int sbw=0;//表示玩家的棋色
	//边界值，用于速度优化
	int x_max=15,x_min=0;
	int y_max=15,y_min=0;
	protected boolean able_flag=true;  //判断是否有禁手
	private int h,w,insx,insy;
	private Point mousePoint;
	private int winer;
	private boolean humanhuman=false;//默认值是不是人人，而是人机；
	private int plast=0;//表示已经下过的棋子数；
	public int BLACK_ONE;
	public int WHITE_ONE;
	public int NONE_ONE;
	public int N;
	
	
	
	
	public ChessPanel(){}
	public ChessPanel(ImageIcon r_map,ImageIcon r_blackchess,ImageIcon r_whitechess){
		map=r_map;
		blackchess=r_blackchess;
		whitechess=r_whitechess;
		N=15;
		isChessOn=new int[N][N];
		BLACK_ONE=0;
		WHITE_ONE=1;
		NONE_ONE=2;
		h=blackchess.getIconHeight()*(N-1);
		w=blackchess.getIconWidth()*(N-1);
		insx=0;
		insy=0;
		mousePoint=new Point();
		N=15;
// 		map=new ImageIcon();
// 		blackchess=new ImageIcon();
// 		whitechess=new ImageIcon();
//  		map=r_map;
//  		blackchess=r_blackchess;
//  		whitechess=r_whitechess;
//  		NONE_ONE=2;
//  		BLACK_ONE=0;
//  		WHITE_ONE=1;
//  		winer=NONE_ONE;
//   		isChessOn=new int[N][N];
//    	h=blackchess.getIconHeight()*(N-1);
//    	w=blackchess.getIconWidth()*(N-1);
//    	insx=0;
//    	insy=0;
//    	mousePoint=new Point();
	}
	
	public void reset(){
		winer=NONE_ONE;
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++){
				isChessOn[i][j]=NONE_ONE;
			}
		chess_num=0;
		win=false;
		win_bw=2;
		bw=0;
		x_max=15;x_min=0;y_max=15;y_min=0;
		repaint();
	}
	public void showMousePos(Point p){
		mousePoint.x=p.x/(h/N);
		mousePoint.y=p.y/(h/N);
		repaint();
	}
	public Point getPoint(int x,int y){
		insx=x;
		insy=y;
		Point r=new Point(x/(h/N),y/(h/N));
		return r;
	}
	public void gameOver(int r_winer){
		winer=r_winer;
	}
	public void paint(Graphics g){
		super.paint(g);
		paintChessMap(g);
		paintChess(g);
		if(winer==BLACK_ONE)
			g.drawString(new String("游戏结束，黑棋获胜"), 500, 200);
		if(winer==WHITE_ONE)
			g.drawString(new String("游戏结束，白棋获胜"), 500, 200);
	}
	public void paintChessMap(Graphics g){
		map.paintIcon(this, g, 10, 10);
		int j;
		g.setColor(Color.black);
		for(j=0;j<N;j++){
			g.drawLine(h/N/2, h/N/2+j*h/N, h-h/N/2, h/N/2+j*h/N);
			g.drawLine(h/N/2+j*h/N, h/N/2, h/N/2+j*h/N, h-h/N/2);
		}
		g.fillRect(7*h/N+h/N/2-3, 7*h/N+h/N/2-3, 6, 6);
		g.fillRect(3*h/N+h/N/2-3, 11*h/N+h/N/2-3, 6, 6);
		g.fillRect(11*h/N+h/N/2-3, 3*h/N+h/N/2-3, 6, 6);
		g.fillRect(3*h/N+h/N/2-3, 3*h/N+h/N/2-3, 6, 6);
		g.fillRect(11*h/N+h/N/2-3, 11*h/N+h/N/2-3, 6, 6);
	}
	private void paintChess(Graphics g){
		int i,j;
		for(i=0;i<N;i++)
			for(j=0;j<N;j++){
				if(isChessOn[i][j]==BLACK_ONE)
					blackchess.paintIcon(this, g, i*h/N, j*h/N);
				if(isChessOn[i][j]==WHITE_ONE)
					whitechess.paintIcon(this, g, i*h/N, j*h/N);
			}
	}
	public void putOne(int bwf){
		int x,y,mx= -100000000;
		x=y=-1;
		int [][]  best=getBest(bwf);
		for(int k=0;k<best.length;k++){
			int i=best[k][0];
			int j=best[k][1];
			if(getType(i,j,bwf)==1){
				x=i;
				y=j;
				break;
			}
			if(getType(i,j,1-bwf)==1){
				x=i;
				y=j;
				break;
			}
			int temp1=x_min,temp2=x_max,temp3=y_min,temp4=y_max;
			resetMaxMin(i,j);
			isChessOn[i][j]=bwf;
			int t=findMin(-100000000,100000000,deep);
			isChessOn[i][j]=2;
			x_min=temp1;
			x_max=temp2;
			y_min=temp3;
			y_max=temp4;
			if(t-mx>1000||Math.abs(t-mx)<1000&&RandomTest(3)){
				x=i;
				y=j;
				mx=t;
			}
		}
			boolean flag=haveWin(x,y,bwf);
			System.out.println(flag);
			update(x,y);
			repaint();
			resetMaxMin(x,y);
			if(flag)
				wined(bwf);
			if(!flag&&chess_num>=drawn_num){
				win=true;
				JOptionPane.showMessageDialog(null, new String(chess_num+"步仍然没有分出胜负,判和棋"));
				win=true;
				return;
			}
				
		}
	protected int findMax(int alpha,int beta,int step){
		int max=alpha;
		if(step==0)
			return evaluate();
		int [][] best=getBest(1-sbw);
		for(int k=0;k<best.length;k++){
			int i=best[k][0];
			int j=best[k][1];
			if(getType(i,j,1-sbw)==1)
				 return 100 * ( getMark(1) + step*1000 );
			isChessOn[i][j]=1-sbw;
			int temp1=x_min,temp2=x_max,temp3=y_min,temp4=y_max;
			resetMaxMin(i,j);
			int t=findMin(max,beta,step-1);
			isChessOn[i][j]=2;
			x_min=temp1;
			x_max=temp2;
			y_min=temp3;
			y_max=temp4;
			if(t>max)
				max=t;
			if(max>=beta)
				return max;
		}
		return max;
	}
				
	
		
		
protected int findMin(int alpha,int beta,int step){
	int min=beta;
	if(step==0)
		return evaluate();
	int[][] rt=getBest(sbw);
	for(int k=0;k<rt.length;k++){
		int i=rt[k][0];
		int j=rt[k][1];
		if(getType(i,j,sbw)==1){
			return -100 * ( getMark(1) + step*1000 );
		}
		isChessOn[i][j]=sbw;
		int temp1=x_min,temp2=x_max,temp3=y_min,temp4=y_max;
		resetMaxMin(i,j);
		int t=findMax(alpha,min,step-1);
		isChessOn[i][j]=2;
		x_min=temp1;
		x_max=temp2;
		y_min=temp3;
		y_max=temp4;
		if(t<min){
			min=t;
		}
		if(min<=alpha){
			return min;
		}
		
	}
	return min;
}



private int[][] getBest(int bwf){
	
	int i_min=(x_min==0?x_min:x_min-1);
	int i_max=(x_max==15?x_max:x_max+1);
	int j_min=(y_min==0?y_min:y_min-1);
	int j_max=(y_max==15?y_max:y_max+1);
	System.out.println(i_min+"  "+i_max+"  "+j_min+"  "+j_max);
	int i,j;
	int n=0;
	int rt[][]  =new int[(i_max-i_min)*(j_max-j_min)][3];
	for(i=i_min;i<i_max;i++)
		for(j=j_min;j<j_max;j++){
			if(isChessOn[i][j]==2){
				int type_1=getType(i,j,bwf);
				int type_2=getType(i,j,1-bwf);
				if(able_flag&&bwf==0&&(type_1==20||type_1==21||type_1==22)){
					continue;
				}
				rt[n][0]=i;
				rt[n][1]=j;
				rt[n][2]=getMark(type_1)+getMark(type_2);
				n++;
			}
		}
	Arrays.sort(rt, new ArrComparator());
	int size=n>weight?weight:n;
	int[][] best=new int[size][3];
	System.arraycopy(rt, 0, best, 0, size);
	return best;
}
private int[] count(int x, int y, int ex, int ey, int bwf) {
  	// 该方向没意义,返回0
//      if( !makesense(x, y, ex, ey, bwf))
//          return new int[] {0, 1};
      
      if(!makesense(x,y,ex,ey,bwf))
    	  return new int[] {0,1};
      // 正方向 以及 反方向棋子个数
  	int rt_1 = 1,rt_2 = 1;
  	// 总棋子个数
  	int rt = 1;
  	// 正方向 以及 反方向连子的活度
      int ok_1 = 0,ok_2 =0;
      // 总活度
      int ok = 0;
      // 连子中间有无空格
      boolean flag_mid1 =false,flag_mid2 = false;
      // 连子中间空格的位置
      int flag_i1 = 1,flag_i2 = 1;
      
      if (isChessOn[x][y] != 2) {
          throw new IllegalArgumentException("position x,y must be empty!..");
      }
      int i;
      // 往正方向搜索
  
     for(i=1;x+ i* ex>=0&&x+i*ex<15&&y+i*ey>=0&&y+i*ey<15;i++){
    	 if(isChessOn[x+i*ex][y+i*ey]==bwf)
    		 rt_1++;
    	 else if(isChessOn[x+i*ex][y+i*ey]==2){
    		 if(!flag_mid1){
    			 flag_mid1=true;
    			 flag_i1=i;
    		 }
    		 else 
    			 break;
    	 }
    	 else
    		 break;
     }
     
     if(x+ i * ex>=0 && x + i * ex <15 && y+ i * ey>=0&& y+ i *ey <15){
    	 if(isChessOn[x+i*ex][y+i*ey]==2){
    		 ok_1++;
    		 if(flag_i1==rt_1){
    			 flag_mid1=false;
    		 }
    		 if(flag_mid1&&rt_1>=4&&flag_i1<4)
    			 ok_1--;
    	 }
    	 //这里我们最好设置成 if 和else if 判断语句，以防止我们在第一个if中做出的改变使得又
    	 //执行下一个if语句；！！！！！
    	 
    	 else if(isChessOn[x+i*ex][y+i*ey]==1-bwf && i>=2){
    		 if(isChessOn[x+(i-1)*ex][y+(i-1)*ey]==2){
    			 ok_1++;
    			 flag_mid1=false;
    		 }
    	 }
     }
     else if(isChessOn[x+(i-1)*ex][y+(i-1)*ey]==2){
    	 ok_1++;
    	 flag_mid1=false;;
     }
      
      // 往反方向搜索        
      for (i = 1; x - i * ex >= 0 && x - i * ex < 15 && y - i * ey >= 0 && y - i * ey < 15; i++) {
          if (isChessOn[x - i * ex][y - i * ey] == bwf)
              rt_2++;
          else if(isChessOn[x - i * ex][y - i * ey] == 2) {
          		if(!flag_mid2) {
          			flag_mid2 = true;
          			flag_i2 = i;
          		}
          		else
          			break;
          	}
          else
              break;
      }
      // 计算反方向活度
      if (x - i * ex < 15 && x - i * ex >= 0 && y - i * ey < 15 && y - i * ey >= 0) {
      	if( isChessOn[x - i * ex][y - i * ey] == 2) {
      		ok_2++;
      		if(rt_2 == flag_i2)
      			flag_mid2 = false;
      	    if(flag_mid2 && rt_2 > 3 && flag_i2 < 4) {
              	ok_2--;
              }
      	}
      	else if( isChessOn[x - i * ex][y - i * ey] != bwf && i >= 2 ) 
      		if(isChessOn[x - (i-1) * ex][y - (i-1) * ey] == 2) {
      			ok_2++;
      			flag_mid2 = false;
      		}
      }
      else if(i >= 2 && isChessOn[x - (i-1) * ex][y - (i-1) * ey] == 2) {
      	ok_2++;
  		flag_mid2 = false;
      }
      
      //------------------分析棋子类型
      // 两边都没中空,直接合成
     if(!flag_mid1&&!flag_mid2){
    	 rt=rt_1+rt_2-1;
    	 ok=ok_1+ok_2;
    	 return new int[]  {rt,ok};
     }
     if(flag_mid1&&flag_mid2){
    	 int temp=flag_i1+flag_i2-1;
    	 if(temp>=5)
    		 return new int[] {temp,2};
    	 if(temp==4)
    		 return new int[] {temp,2};
    	 if(rt_1+flag_i2-1>=4||rt_2+flag_i1-1>=4)
    		 return new int[] {4,1};
    	 if(rt_1+flag_i2-1==3&&ok_1>0||rt_2+flag_i1-1==3&&ok_2>0)
    		 return new int[] {3,2};
    	return new int[] {3,1};
     }
     else{
    	 if(rt_1+rt_2-1<5)
    		 return new int[] {rt_1+rt_2-1,ok_1+ok_2};
    	 else{
    		 if(flag_mid1&&rt_2+flag_i1-1>=5)
    			 return new int[] {rt_2+flag_i1-1,2};
    		 if(flag_mid2&&rt_1+flag_i2-1>=5)
    			 return new int[] {rt_1+flag_i2-1,2};
    		 if(flag_mid1&&(rt_2+flag_i1-1==4&&ok_2>0||flag_i1==4))
    			 return new int[] {4,2};
    		 if(flag_mid2&&(rt_1+flag_i2-1==4&&ok_1>0||flag_i2==4))
    			 return new int[] {4,2};
    		 else
    			 return new int[] {4,1};
    	 }
     }
     
}
private boolean makesense(int x,int y,int ex,int ey,int bwf){
	int rt=1;
	int i;
	for (i = 1; x + i * ex < 15 && x + i * ex >= 0 && y + i * ey < 15 && y + i * ey >= 0 && rt < 5; i++) {
		if(isChessOn[x + i * ex][y + i * ey]!=1-bwf){
			rt++;
		}
		else
			break;
	}
	for (i = 1; x - i * ex < 15 && x - i * ex >= 0 && y - i * ey < 15 && y - i * ey >= 0 && rt < 5; i++) {
		if(isChessOn[x - i * ex][y - i * ey]!=1-bwf){
			rt++;
		}
		else
			break;
	}
	return (rt>=5);
}

protected int getType(int x,int y,int bwf){
	if(isChessOn[x][y]!=2)
		return -1;
	int[][] types=new int[4][2];
	types[0]=count(x,y,1,0,bwf);
	types[1]=count(x,y,0,1,bwf);
	types[2]=count(x,y,-1,1,bwf);
	types[3]=count(x,y,1,1,bwf);
	
	int longfive=0;
	int five_OR_more=0;
	int four_five=0;
	int four_died=0;
	int three_live=0;
	int three_died=0;
	int two_live=0;
	int two_died=0;
	for(int k=0;k<4;k++){
		if(types[k][0]>5){
			longfive++;
			five_OR_more++;
		}
		if(types[k][0]==5)
			five_OR_more++;
		if(types[k][0]==4&&types[k][1]==2)
			four_five++;
		if(types[k][0]==4&&types[k][1]!=2)
			four_died++;
		if(types[k][0]==3&&types[k][1]==2)
			three_live++;
		if(types[k][0]==3&&types[k][1]!=2)
			three_died++;
		if(types[k][0]==2&&types[k][1]==2)
			two_live++;
		if(types[k][0]==2&&types[k][1]!=2)
			two_died++;
	}
	
	if(bwf==0&&able_flag){
		if(longfive!=0)
			return 20;
		if(four_died+four_five>=2)
			return 21;
		if(three_died+three_live>=2)
			return 22;
	}
	if(five_OR_more!=0)
		return 1;
	if(four_five!=0||four_died>=2||four_died!=0&&three_live!=0)
		return 2;
	if(three_live>=2)
		return 3;
	if(three_live!=0&&three_died!=0)
		return 4;
	if(four_died!=0)
		return 5;
	if(three_live!=0)
		return 6;
	if(two_live>=2)
		return 7;
	if(three_died!=0)
		return 8;
	if(two_live!=0&&two_died!=0)
		return 9;
	if(two_live!=0)
		return 10;
	if(two_died!=0)
		return 11;
	else
		return 12;
}
	
protected int evaluate(){
	int rt=0,mt_c=1,mt_m=1;
	if(bw==sbw)
		mt_m=2;
	else
		mt_c=2;
	int i_min=(x_min==0?x_min:x_min-1);
	int j_min=(y_min==0?y_min:y_min-1);
	int i_max=(x_max==15?x_max:x_max+1);
	int j_max=(y_max==15?y_max:y_max-1);
	int i,j;
	for(i=i_min;i<i_max;i++)
		for(j=j_min;j<j_max;j++){
			if(isChessOn[i][j]==2){
				int type=getType(i,j,1-sbw);
				if(type==1){
					rt+=30*mt_c*getMark(type);
				}
				if(type==2)
					rt+=10*mt_c*getMark(type);
				if(type==3)
					rt+=3*mt_c*getMark(type);
				else
					rt+=mt_c*getMark(type);
				
				type=getType(i,j,sbw);
				if(type==1){
					rt-=30*mt_m*getMark(type);
				}
				if(type==2)
					rt-=10*mt_m*getMark(type);
				if(type==3)
					rt-=3*mt_m*getMark(type);
				else
					rt-=mt_m*getMark(type);
			}
		}
	return rt;
}
void update(int x,int y){
	
	isChessOn[x][y]=bw;
	pre[chess_num][0]=x;
	pre[chess_num][1]=y;
	bw=1-bw;
	chess_num++;
	System.out.println(bw);
	System.out.println(sbw);
}
public void resetMaxMin(int x,int y){
	if(x-1>=0)
		x_min=(x_min<x-1?x_min:x-1);
	if(x+1<15)
		x_max=(x_max>x+1?x_max:x+1);
	if(y-1>=0)
		y_min=(y_min<y-1?y_min:y-1);
	if(y+1<15)
		y_max=(y_max>y+1?y_max:y+1);
}
private boolean RandomTest(int kt){
	Random rd=new Random();
	return (rd.nextInt()%kt==0);
}
private int getMark(int type){
	switch(type){
	case 1:
		return 100000;
	case 2:
		return 30000;
	case 3:
		return 5000;
	case 4:
		return 1000;
	case 5:
		return 500;
	case 6:
		return 200;
	case 7:
		return 100;
	case 8:
		return 50;
	case 9:
		return 10;
	case 10:
		return 5;
	case 11:
		return 3;
	case 12:
		return 2;
	default:
		return 0;
			
	}
}
public boolean haveWin(int x,int y,int bwf){
	boolean flag = false;
    if (count(x, y, 1, 0, bwf)[0] >= 5)
        flag = true;
    if (!flag && count(x, y, 0, 1, bwf)[0] >= 5)
        flag = true;
    if (!flag && count(x, y, 1, 0, bwf)[0] >= 5)
        flag = true;
    if (!flag && count(x, y, 1, -1, bwf)[0] >= 5)
        flag = true;
    if (!flag && count(x, y, 1, 1, bwf)[0] >= 5)
        flag = true;
    // 测试用,激活此行代码,不会有输赢..   flag = false;
    return flag;
	
	
	
	
}
public void wined(int bwf){
	if(!humanhuman){
		win=true;
		win_bw=bwf;
		if(win_bw==sbw){
			JOptionPane.showMessageDialog(null, new String("恭喜您赢了"));
		}
		else{
			JOptionPane.showMessageDialog(null, new String("很遗憾，您输了"));
		}
	}else{
		if(win_bw==BLACK_ONE){
			win=true;
			win_bw=BLACK_ONE;
			JOptionPane.showMessageDialog(null, new String("恭喜黑棋赢了"));
		}else if(bw==WHITE_ONE){
			win=true;
			win_bw=WHITE_ONE;
			JOptionPane.showMessageDialog(null, new String("恭喜白棋赢了"));
		}
	}
}
public void setHumanhuman(boolean hh){
	humanhuman=hh;
}
public boolean getHumanhuman(){
	return humanhuman;
}
}
