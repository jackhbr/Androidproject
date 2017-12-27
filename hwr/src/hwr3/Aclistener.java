package hwr3;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.naming.InitialContext;
import javax.swing.JFrame;

public class Aclistener extends AllAdapt{  //两个窗口是可以共用一个事件监听类的！！！
	
	private JFrame jf;
	private int x1,x2,y1,y2;//x1 y1是按下时的坐标，x2 y2是拖动时的坐标
	int[][] chessValue = new int[Config.ROWS][Config.COLUMNS];//存放权值
	private int exeflag=0;
	private String num;
	
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
	}
	
	 public void actionPerformed(ActionEvent e)
	 {
		 String type=e.getActionCommand();
		 if(type.equals("写入"))
		 {
			 System.out.println("写入");
			 exeflag=0;
			 JFrame jk=new JFrame();//不能用jf，但是jf还是要传过来
			 jk.setTitle("手写识别板");
			 jk.setSize(400, 420);
			 jk.setDefaultCloseOperation(2);
			 jk.setLocationRelativeTo(null);
			 jk.addMouseMotionListener(this); //两个窗体共用一个事件监听类
			 jk.addMouseListener(this);//拖动和按下的两个监听
			 jf.setResizable(false);  //false就是不可改变大小
			 jk.setVisible(true);
		 }
		 else if(type.equals("输出数组"))
		 {
			 System.out.println("输出数组");
			 for(int i=0;i<Config.ROWS;i++)
				{
					for(int j=0;j<Config.COLUMNS;j++)
					{
						System.out.print(chessValue[j][i]+""); //先输出横坐标，再输出纵坐标，所以时先把前面的坐标输出完先
					}
					System.out.println();
				}
		 }
		 else if(type.equals("进行训练"))
		 {
			 System.out.println("进行训练");
			 exeflag=1;
			 JFrame jk=new JFrame();//不能用jf，但是jf还是要传过来
			 jk.setTitle("手写识别板");
			 jk.setSize(400, 420);
			 jk.setDefaultCloseOperation(2);
			 jk.setLocationRelativeTo(null);
			 jk.addMouseMotionListener(this); //两个窗体共用一个事件监听类
			 jk.addMouseListener(this);//拖动和按下的两个监听
			 jf.setResizable(false);  //false就是不可改变大小
			 jk.setVisible(true);
			 
		 }
	 }
	 public void mouseDragged(MouseEvent e)
	 {
		 x2=e.getX();
		 y2=e.getY();
		 System.out.println("拖动时的x="+x2+"  y="+y2);
		 
		 for(int i=0;i<Config.ROWS;i++)
			{
				for(int j=0;j<Config.COLUMNS;j++)
				{
					
					int x=Config.X0+Config.SIZE*i;//横坐标 ,对应的是列数,x为水平向右，y为竖直向下
					int y=Config.Y0+Config.SIZE*j;//纵坐标
					//与中心的误差为size/2
					if(x2>x-Config.SIZE/2 && x2<x+Config.SIZE/2 && y2>y-Config.SIZE/2 && y2<y+Config.SIZE/2)
					{
						chessValue[i][j]=1;
					}
				}
				
			}
		 
		 
	 }
	 public void mousePressed(MouseEvent e)   //注意窗体最外面的边框也算在窗体大小内，但是鼠标点击这里时却不会引发事件
	 {
		 x1=e.getX();
		 y1=e.getY();
		 System.out.println("按下时的x="+x1+"  y="+y1);
	 }

}
