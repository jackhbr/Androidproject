package AI4winmore;
//ʧ�ܣ�̫�����ˣ��������������ֻ��໥Ӱ�죬���̫�࣬���ַ�����������ʤ�ʸ�
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
 * ����������¼�������,����̳���MouseAdapter�����������
 */
public class ChessListener extends MouseAdapter implements Config ,ActionListener{

	private Graphics2D g;
	private JPanel JP;
	private int clickFlag=0;//�����Ч�ı�־λ��1������Ч
	
	private  int maxhang=0;
	private int maxlie=0;
	
	
	private int countChess=0;//�����ã����ڼ�����Ե������˶��ٸ�����
	
	private int nowI=0,nowJ=0;//��¼�ʺϵ�I��j�������ж���Ӯ�Ĳ����ظ�ִ�ж�Σ����Ч��
	
	private boolean winflag=false;
	
	private boolean manRobot=false;
	
	HashMap<String,Integer> hm = new HashMap<String,Integer>();
   
//    hm.put("11",200);           ����ֻ����������ķ�������ã�����ֱ�������������
//    hm.put("111",2000);
//    hm.put("12",10);
//    hm.put("112",100);
//	
	// 1.1.����һ���洢��ť���ݵ��ַ�������
		private String type = "��ʼ����Ϸ";
	
	private int count=0;
	//����һ����ά��������������ϵ�λ��
	public int[][] chesses;  //ע������Ҫ��public��������private����Ȼ����������Ͳ�������
	
	public void creatHashMap()
	{
		//���10000
		hm.put("1",20);      //1Ϊ���壬2Ϊ���壨�����µ��壩
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
	
	int[][] chessValue = new int[Config.ROWS][Config.COLUMNS];//���Ȩֵ
	
	public void setG(Graphics g) {
		this.g = (Graphics2D) g;
		// ���û��ʿ����
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	public void repaint(JPanel JP ) {
		this.JP=JP;
		
		
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
					//	int nowI=0,nowJ=0;//��¼�ʺϵ�I��j�������ж���Ӯ�Ĳ����ظ�ִ�ж�Σ����Ч��
						
						for(int i=0;i<Config.ROWS;i++)
						{
							for(int j=0;j<Config.COLUMNS;j++)
							{
								
								int x=Config.X0+Config.SIZE*i;//������ ,��Ӧ��������,xΪˮƽ���ң�yΪ��ֱ����
								int y=Config.Y0+Config.SIZE*j;//������
								//��Բ�ĵ����Ϊsize/3
								if(x1>x-Config.SIZE/3 && x1<x+Config.SIZE/3 && y1>y-Config.SIZE/3 && y1<y+Config.SIZE/3)
								{
									clickFlag=1;
									
									nowI=i;
									nowJ=j;
									System.out.println("��������е�����Ϊ"+nowJ+"  �е�����Ϊ"+nowI);
									
									if(chesses[i][j]==0)
									{
										if(count==0)  //��һ������ʱ�������0���Ǿͺ������£�Ȼ��count++����������countΪ0��ʱ���º��壬�����°���
										{
											chesses[i][j]=1;//����Ǻ��ӣ���Ϊ1
											g.setColor(Color.BLACK);
											count=count+1;
										}
										else {
											chesses[i][j]=2;//����ǰ��ӣ���Ϊ2
											g.setColor(Color.WHITE);
											count=count-1;
										}
										// ����������
										//����2�ͳ���0.5�Ĳ�𣺳���0.5��Ҫǿ��ת��������2ֱ�Ӿ���
										
										
										g.fillOval(x-(Config.CHESS_SIZE/2), y-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
										//��3��4�л�һ������      ���ң������x���У�y�����С�������
								//		g.fillOval(Config.X0+Config.SIZE*3-(Config.CHESS_SIZE/2), Config.Y0+Config.SIZE*4-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
	   
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
							else if(chesses[nowI][nowJ]==2) 
							
							{
								
								System.out.println("�׷�ʤ��");
							}
							
						}
						
						if(manRobot==true && clickFlag==1)
						  {
							clickFlag=0;  //��ʱ�����������Ч�ı�־λ����Ȼ����ͻ������
								AI();
								int maxvalue=0;
								
								for(int i=0;i<Config.ROWS;i++)
								{
									for(int j=0;j<Config.COLUMNS;j++)
									{
										if(chessValue[i][j]>=maxvalue)  //�����һ�����е�Ӧ����ˮƽ��x,����ʱ��j����x.
										{
											maxvalue=chessValue[i][j];   //��������������Ĳ��ԣ���֪���ǻ������⣬������������⣬����һ��
											maxhang=j;//�����j�������������x ,�о���y,x������
											maxlie=i;
										}
										
										
									}
								}
								System.out.println("���ȨֵΪ"+maxvalue);
								chesses[maxlie][maxhang]=2;//����ǰ��ӣ���Ϊ2
								g.setColor(Color.WHITE);
							//	count=count-1;
								count=0;//��ʾ����һ���º���
								System.out.println("countΪ"+count);
							//	g.fillOval(Config.X0+Config.SIZE*maxhang-(Config.CHESS_SIZE/2), Config.Y0+Config.SIZE*maxlie-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
									g.fillOval(Config.X0+Config.SIZE*maxlie-(Config.CHESS_SIZE/2), Config.Y0+Config.SIZE*maxhang-(Config.CHESS_SIZE/2), Config.CHESS_SIZE, Config.CHESS_SIZE);
	
								countChess=countChess+1;
								System.out.println("�����µ�������Ϊ"+countChess);
								System.out.println("�����µ���������Ϊ����Ϊ"+maxhang+"   ����Ϊ"+maxlie);
								System.out.println("  ");
								System.out.println("  ");
								for(int i=0;i<Config.ROWS;i++)  //ÿ�εĲ��ֶ���ͬ����������һ�κ�Ҫ����Ȩֵ
								{
									for(int j=0;j<Config.COLUMNS;j++)
									{	
											chessValue[i][j]=0;
									}
								}
								if(level(maxlie,maxhang)>=5 || Upright(maxlie,maxhang)>=5 || upperLeft(maxlie,maxhang)>=5 || upperRight(maxlie,maxhang)>=5) //��������׷�Ҳ��Ӯ��
								{
									winflag=true;
									if(chesses[maxlie][maxhang]==1)
									{
										
										System.out.println("�ڷ�ʤ��");
									}
									else if(chesses[maxlie][maxhang]==2) 
									
									{
										
										System.out.println("�׷�ʤ��");
									}
									
								}
								
								
								
								
						  }
		
		}
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		type = e.getActionCommand();// ��ȡ��ť�ϵ�������Ϣ�����û��������Ϣ��������ַ���""
	
		if(type.equals("��ʼ����Ϸ"))
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
		else if(type.equals("����"))
		{
			winflag=true;
			System.out.println("����");
			if(count==0)
			{
				System.out.println("�׷�ʤ����");
			}
			else
			{
				System.out.println("�ڷ�ʤ����");
			}
		}
		else if(type.equals("����"))
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
			
			
			System.out.println("����"); 
		}
		else if(type.equals("�˻���ս"))
		{
			System.out.println("�˻���ս");
			System.out.println("������");
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
		else if(type.equals("���˶�ս"))
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
			
			System.out.println("���˶�ս");
			
			
		}
		
	}
	
	
	
	
	
	public int level(int x,int y)
	{
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
	
	//���������for(int i=x+1,j=y;i<config.rows,j<config.colums;i++,j++)���ָ�ʽ��д���Ƚϼ��
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
	
	
	
	
	
	 public void AI(){
			//ȫ������
			for(int i=0;i<chesses.length;i++)
			{
				for(int j=0;j<chesses[i].length;j++)
				{
					//��λ����Ҫ��Ȩֵ
					if(chesses[i][j] == 0)  //�����ÿ�λ�˸������������������
					{
						
							//���壺chess������¼�������������color��¼������ɫ
							String chess="";
						//	int color = 0;
							//�������������������
							for(int k=i+1;k<chesses.length;k++)
							{
									if(chesses[k][j] == 0)
									{
										break;
									}
									else
									{
	//									if(color==0)
	//									{  //�ұߵ�һ������   color����1��Ϊ��ɫ�����µ���
	//										chess+=chesses[k][j];  //��¼�����������   �ַ����������֣��͵���ǿ��ת��Ϊ���ַ���
	//										color = chesses[k][j];
	//									}
										if(chesses[k][j] == 1)
										{
											chess+=chesses[k][j];
										}
										else
										{  
											//��ɫ��ͬ�����
											chess+=chesses[k][j];
											break;
										}
									}
							}
							//���������������ȡ����Ӧ��Ȩֵ�ۼ�
							Integer value = hm.get(chess);
							if(value != null){
								chessValue[i][j]+=value;
							}
							
							
							
							//���������������������
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
											//��ɫ��ͬ�����
											chess+=chesses[k][j];
											break;
										}
									}
							}
							Integer value1 = hm.get(chess);
							if(value1 != null){
								chessValue[i][j]+=value1;
							}
							
							
							//���������������������
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
											//��ɫ��ͬ�����
											chess+=chesses[i][k];
										//	System.out.println("���µ��ַ�����"+chess);
											break;
										}
									}
							}
							Integer value2 = hm.get(chess);
							if(value2 != null){
								chessValue[i][j]+=value2;
								//System.out.println("���µ�ȨֵΪ"+(int)value2);
							}
							
							
							//���������������������
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
											//��ɫ��ͬ�����
											chess+=chesses[i][k];
											break;
										}
									}
							}
							Integer value3 = hm.get(chess);
							if(value3 != null){
								chessValue[i][j]+=value3;
							}
							
							
							//�����������������������
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
											//��ɫ��ͬ�����
											chess+=chesses[p][k];
											break;
										}
									}
							}
							Integer value4 = hm.get(chess);
							if(value4 != null){
								chessValue[i][j]+=value4;
							}
							
							
							//�����������������������
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
											//��ɫ��ͬ�����
											chess+=chesses[p][k];
											break;
										}
									}
							}
							Integer value5 = hm.get(chess);
							if(value5 != null){
								chessValue[i][j]+=value5;
							}
							
							
							//�����������������������
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
											//��ɫ��ͬ�����
											chess+=chesses[p][k];
											break;
										}
									}
							}
							Integer value6 = hm.get(chess);
							if(value6 != null){
								chessValue[i][j]+=value6;
							}
							
							//�����������������������
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
											//��ɫ��ͬ�����
											chess+=chesses[p][k];
											break;
										}
									}
							}
							Integer value7= hm.get(chess);
							if(value7!= null){
								chessValue[i][j]+=value7;
							}
							//ˮƽ�����������
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
											//��ɫ��ͬ�����
											chess+=chesses[k][j];
											chess+=3;//�ø�����β	
											break;
										}
										
										
									}
								}chess+=3;//�ø�����β,����1�ĺ���
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
											//��ɫ��ͬ�����
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
								
								//��ֱ�����������
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
												//��ɫ��ͬ�����
												chess+=chesses[i][k];
												chess+=3;//�ø�����β	
												break;
											}
											
											
										}
									}chess+=3;//�ø�����β,����1�ĺ���
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
												//��ɫ��ͬ�����
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
								
									 //���Ϸ����ϵ�
									
									 chess="";
										//����ͳ��
										for(int k=1;k<Config.ROWS;k++)
										{  //���ﲻ̫ȷ��
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
													//��ɫ��ͬ�����
													chess+=chesses[i+k][j+k];
													chess+=3;//�ø�����β	
													break;
												}
												
												
											}
											if((i+k)>=(chesses.length-1)  ||  (j+k)>=(chesses[1].length-1))
											{break;}
										}chess+=3;//�ø�����β,����1�ĺ���
										for(int p=0;p<Config.ROWS;p++)
										{  //���ﲻ̫ȷ��
											
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
													//��ɫ��ͬ�����
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
										
										//���Ϸ����ϵ�
										 chess="";
											//����ͳ��
											for(int k=1;k<Config.ROWS;k++)
											{  //���ﲻ̫ȷ��
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
														//��ɫ��ͬ�����
														chess+=chesses[i+k][j-k];
														chess+=3;//�ø�����β	
														break;
													}
												
													
												}
												if((j-k)<=0)
												{break;}
											}chess+=3;//�ø�����β,����1�ĺ���
											for(int p=0;p<Config.ROWS;p++){  //���ﲻ̫ȷ��
												
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
														//��ɫ��ͬ�����
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
