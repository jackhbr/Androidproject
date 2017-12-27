package hwr5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFrame;

public class Aclistener extends AllAdapt{  //两个窗口是可以共用一个事件监听类的！！！
	
	private JFrame jf;
	private Graphics2D g;
	private int x1,x2,y1,y2;//x1 y1是按下时的坐标，x2 y2是拖动时的坐�??
	int[][] chessValue = new int[Config.ROWS][Config.COLUMNS];//存放权�??
	private int exeflag=0;//进行训练的标志位
	private String num="0";//存放训练的数�??  ,要赋初�?�，否则会出现空对象这种情况
	private int count;//累加计数每个文件里读取了多少个数，当等于文件里的int的数值时，就跳出
	private int readNum[][]=new int[Config.ROWS][Config.COLUMNS];//用于存放读出的数组的值，然后和写入的数组值进行运算，得出�?
	private int zhi[][]=new int[10][10];//前面的十是对应哪个文件，后面的十是对应文件里的存有多少个样品�?
	private int countFileInt;//记录文件里的�?有字节数，去替代available！！
	
	
	public Aclistener()
	{
		init();
	}
	
	public void init()
	{
		for(int i=0;i<Config.ROWS;i++)
		{
			for(int j=0;j<Config.COLUMNS;j++)
			{
				chessValue[i][j]=0;
			}
		}
	}
	
	public void setPa(JFrame jf)
	{
		this.jf=jf;
		//this.g=(Graphics2D) jf.getGraphics();
	}
	
	 public void actionPerformed(ActionEvent e)
	 {
		 String type=e.getActionCommand();
		 if(type.equals("写入"))
		 {
			 System.out.println("写入");
			 exeflag=0;
			 JFrame jk=new JFrame();//不能用jf，但是jf还是要传过来
			 jk.setTitle("手写识别�?");
			 jk.setSize(400, 420);
			 jk.setDefaultCloseOperation(2);
			 jk.setLocationRelativeTo(null);
			 jk.addMouseMotionListener(this); //两个窗体共用�??个事件监听类
			 jk.addMouseListener(this);//拖动和按下的两个监听
			 jf.setResizable(false);  //false就是不可改变大小
			 jk.setVisible(true);
			 g=(Graphics2D) jk.getGraphics();
		 }
		 else if(type.equals("输出数组"))
		 {
			 System.out.println("输出数组");
			 for(int i=0;i<Config.ROWS;i++)
				{
					for(int j=0;j<Config.COLUMNS;j++)
					{
						System.out.print(chessValue[j][i]+""); //先输出横坐标，再输出纵坐标，�??以时先把前面的坐标输出完�??
					}
					System.out.println();
				}
		 }
		 else if(type.equals("进行训练"))
		 {
			 System.out.println("进行训练");
			 exeflag=1;
			 JFrame jk=new JFrame();//不能用jf，但是jf还是要传过来
			 jk.setTitle("手写识别�?");
			 jk.setSize(400, 420);
			 jk.setDefaultCloseOperation(2);
			 jk.setLocationRelativeTo(null);
			 jk.addMouseMotionListener(this); //两个窗体共用�??个事件监听类
			 jk.addMouseListener(this);//拖动和按下的两个监听
			 jf.setResizable(false);  //false就是不可改变大小
			 jk.setVisible(true);
			 g=(Graphics2D) jk.getGraphics();
		 }
		 else   //这剩下就都是数字�??
		 {
		     num=type;	
		 }
	 }
	 public void mouseDragged(MouseEvent e)
	 {
		 x2=e.getX();
		 y2=e.getY();
		 g.setColor(Color.BLUE);
		 g.setStroke(new BasicStroke(10));// 实例化线条粗细对象，设置为画笔的粗细
			// 1.5根据按下和拖动的坐标值来绘制曲线
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		 
		 
	//	 System.out.println("拖动时的x="+x2+"  y="+y2);
		 
		 for(int i=0;i<Config.ROWS;i++)
			{
				for(int j=0;j<Config.COLUMNS;j++)
				{
					
					int x=Config.X0+Config.SIZE*i;//横坐�?? ,对应的是列数,x为水平向右，y为竖直向�??
					int y=Config.Y0+Config.SIZE*j;//纵坐�??
					//与中心的误差为size/2
					if(x2>x-Config.SIZE/2 && x2<x+Config.SIZE/2 && y2>y-Config.SIZE/2 && y2<y+Config.SIZE/2)
					{
						chessValue[i][j]=1;
					}
				}
				
			}
		 
		 
	 }
	 public void mousePressed(MouseEvent e)   //注意窗体�??外面的边框也算在窗体大小内，但是鼠标点击这里时却不会引发事件
	 {
		 x1=e.getX();
		 y1=e.getY();
		 System.out.println("按下时的x="+x1+"  y="+y1);
	 }
	 public void mouseReleased(MouseEvent e)    //写入和读出都没问题了，存入都很正�??
	 {
		
		  
			 
			 String file="jj";
			 if(num.equals("0"))//空的时�?�就是null
			 {
				 file="C:\\Users\\jack\\Desktop\\hwr\\0.txt";
			 }
			 else if(num.equals("1"))
			 {
				 file="C:\\Users\\jack\\Desktop\\hwr\\1.txt";
			 }
			 else if(num.equals("2"))
			 {
				 file="C:\\Users\\jack\\Desktop\\hwr\\2.txt";
			 }
			 else if(num.equals("3"))
			 {
				 file="C:\\Users\\jack\\Desktop\\hwr\\3.txt";
			 }
			 else if(num.equals("4"))
			 {
				 file="C:\\Users\\jack\\Desktop\\hwr\\4.txt";
			 }
			 else if(num.equals("5"))
			 {
				 file="C:\\Users\\jack\\Desktop\\hwr\\5.txt";
			 }
			 else if(num.equals("6"))
			 {
				 file="C:\\Users\\jack\\Desktop\\hwr\\6.txt";
			 }
			 else if(num.equals("7"))
			 {
				 file="C:\\Users\\jack\\Desktop\\hwr\\7.txt";
			 }
			 else if(num.equals("8"))
			 {
				 file="C:\\Users\\jack\\Desktop\\hwr\\8.txt";
			 }
			 else if(num.equals("9"))
			 {
				 file="C:\\Users\\jack\\Desktop\\hwr\\9.txt";
			 }
			 if(exeflag==1) //进入训练模式�?
			 {
			 try {
				 System.out.println("进入训练模式后的num="+num);
//				InputStream ins=new FileInputStream(file);
//				DataInputStream dis=new DataInputStream(ins);
//				
				 OutputStream ous=new FileOutputStream(file,true);  //写入内容添加到文件的末尾
				 DataOutputStream dos=new DataOutputStream(ous);
				for(int i=0;i<Config.ROWS;i++)
				{
					for(int j=0;j<Config.COLUMNS;j++)
					{
						dos.writeInt(chessValue[j][i]); //先输出横坐标，再输出纵坐标，�??以时先把前面的坐标输出完�??
					}		//�??行一行加上去输出�??  这里后面加上48就可以正常在文件中保存的数据�??,然�?�我的并不可以�?��??
				}
				
				for(int i=0;i<Config.ROWS;i++)
				{
					for(int j=0;j<Config.COLUMNS;j++)
					{
						System.out.print(chessValue[j][i]+""); //先输出横坐标，再输出纵坐标，�??以时先把前面的坐标输出完�??
					}
					System.out.println();
				}
				init();//写进文件以后数组值全部归�?
				
				
				dos.flush();
				dos.close();
				
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		 }
		 else //从所有的文件取出�?有的数来进行比对，输出zhi数组,此时非训练模�?
		 {
			try {
			//	int a;
				
				for(int i=0;i<Config.ROWS;i++)   //先把此次写入的输出一次先
				{
					for(int j=0;j<Config.COLUMNS;j++)
					{
						System.out.print(chessValue[j][i]); //先输出横坐标，再输出纵坐标，�??以时先把前面的坐标输出完�??
					}
					System.out.println();
				}
				
				
				
				for(int f=0;f<10;f++)
				{
					String fs=f+"";
					if(fs.equals("0"))
					 {
						System.out.println("此时正在读取0.txt的内�?");
						 file="C:\\Users\\jack\\Desktop\\hwr\\0.txt";
					 }
					 else if(fs.equals("1"))
					 {
						 System.out.println("此时正在读取1.txt的内�?");
						 file="C:\\Users\\jack\\Desktop\\hwr\\1.txt";
					 }
					 else if(fs.equals("2"))
					 {
						 System.out.println("此时正在读取2.txt的内�?");
						 file="C:\\Users\\jack\\Desktop\\hwr\\2.txt";
					 }
					 else if(fs.equals("3"))
					 {
						 System.out.println("此时正在读取3.txt的内�?");
						 file="C:\\Users\\jack\\Desktop\\hwr\\3.txt";
					 }
					 else if(fs.equals("4"))
					 {
						 System.out.println("此时正在读取4.txt的内�?");
						 file="C:\\Users\\jack\\Desktop\\hwr\\4.txt";
					 }
					 else if(fs.equals("5"))
					 {
						 System.out.println("此时正在读取5.txt的内�?");
						 file="C:\\Users\\jack\\Desktop\\hwr\\5.txt";
					 }
					 else if(fs.equals("6"))
					 {
						 System.out.println("此时正在读取6.txt的内�?");
						 file="C:\\Users\\jack\\Desktop\\hwr\\6.txt";
					 }
					 else if(fs.equals("7"))
					 {
						 System.out.println("此时正在读取7.txt的内�?");
						 file="C:\\Users\\jack\\Desktop\\hwr\\7.txt";
					 }
					 else if(fs.equals("8"))
					 {
						 System.out.println("此时正在读取8.txt的内�?");
						 file="C:\\Users\\jack\\Desktop\\hwr\\8.txt";
					 }
					 else if(fs.equals("9"))
					 {
						 System.out.println("此时正在读取9.txt的内�?");
						 file="C:\\Users\\jack\\Desktop\\hwr\\9.txt";
					 }
					
					InputStream ins=new FileInputStream(file);
					DataInputStream dis=new DataInputStream(ins);
					System.out.println("此文件的拥有的字节数值为"+(ins.available()));//�?个int等于4个byte
					System.out.println("此文件的拥有的整数�?�为"+(ins.available()/4));//�?个int等于4个byte
					System.out.println("此文件的拥有的样品数值为"+(ins.available()/4/(19*19)));//�?个int等于4个byte
					
					count=0;//每次进入�?个文件时，一定要置零
					countFileInt=ins.available()/4;
					while(count<=countFileInt)//当读取的整数值小于等于这里拥有的整数值时，继续读�? ，当里面没有数据时，如果还运行进去，就不能进行readint（），所以如果继续运行下去就会报�?
					{            //这个available是可读取的字节数，你读取之后就不能再读取之前的数，所以这个�?�在你读取之后会不断地改变！！！！所以不能用这个值在下面做判�?
						if((ins.available()/4)==0) //如果里面没有数据，就不去读了，但是不能去掉上面的=号，因为为了数据整齐对一�?
							break;
						int fl=count/(19*19);
						for(int i=0;i<Config.ROWS;i++)
						{
							for(int j=0;j<Config.COLUMNS;j++)
							{
								readNum[j][i]=dis.readInt(); //先输出横坐标，再输出纵坐标，�??以时先把前面的坐标输出完�??
								//System.out.println(chessValue[j][i]);
								count=count+1;
							//	System.out.println("此时操作的样品是�?"+fl+"�?");
							//	System.out.println("此时的int数是�?"+count+"");
								//System.out.println("此文件拥有的int数有"+(ins.available()/4));
								zhi[f][fl]=zhi[f][fl]+(chessValue[j][i]-readNum[j][i])*(chessValue[j][i]-readNum[j][i]);
								
							}
						
						}zhi[f][fl]=(int) Math.sqrt(zhi[f][fl]);
						
					
					}					
				}			
				for(int i=0;i<10;i++)
				{
					for(int j=0;j<10;j++)
					{
						System.out.print(zhi[j][i]); //先输出横坐标，再输出纵坐标，�??以时先把前面的坐标输出完�??
					}
					System.out.println();
				}
								
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally  //论finally的重要性
			{
				//System.out.println(a);
			}
		 }
	 }

}
