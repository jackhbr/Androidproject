package hwr6fei1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.omg.PortableInterceptor.DISCARDING;

public class Aclistener extends AllAdapt { // 两个窗口是可以共用一个事件监听类的！！！

	private JFrame jf;
	private Graphics2D g;
	private int x1, x2, y1, y2;// x1 y1是按下时的坐标，x2 y2是拖动时的坐标??
	int[][] chessValue = new int[Config.ROWS][Config.COLUMNS];// 存放权值
	private int exeflag = 0;// 进行训练的标志位
	private int num ;// 存放此时下拉框的选择的数
	private int readNum[][] = new int[Config.ROWS][Config.COLUMNS];// 用于存放读出的数组的值，然后和写入的数组值进行运算，得出�??
	private int zhi[][] = new int[15][100];// 前面的十是对应哪个文件，后面的100是对应文件里的存有多少个样品?
	private int countFileInt;// 记录文件里的�??有字节数，去替代available！！
    private int countNumPointNum; 
    private int temp;//用于临时存储此时文件的号码，当和上一次相同时加1，不同时变为0；
    private JFrame jk;
	
	public Aclistener() {
		init();
	}

	public void init() {
		for (int i = 0; i < Config.ROWS; i++) {
			for (int j = 0; j < Config.COLUMNS; j++) {
				chessValue[i][j] = 0;
			}
		}
	}

	public void setPa(JFrame jf) {
		this.jf = jf;
		// this.g=(Graphics2D) jf.getGraphics();
	}

	public void actionPerformed(ActionEvent e) {

		//这端语句不应该放在这里，因为当输入的是按钮的时候，执行这段按钮就会出错，应该先判断出这是下拉框了，然后才可以执行下面这段语句
//		JComboBox<String>  jcb = (JComboBox<String>)e.getSource();   //额，忽然又不可以了。。。。	
//		String str = jcb.getSelectedItem().toString(); 
//	    System.out.println(str);
//        num=Integer.parseInt(str);  //字符串转化为整型
       	
		String type = e.getActionCommand();
		if (type.equals("写入")) {
			System.out.println("写入");
			exeflag = 0;
			jk = new JFrame();// 不能用jf，但是jf还是要传过来
			jk.setTitle("手写识别�?");
			jk.setSize(400, 420);
			jk.setDefaultCloseOperation(2);
			jk.setLocationRelativeTo(null);
			jk.addMouseMotionListener(this); // 两个窗体共用�???个事件监听类
			jk.addMouseListener(this);// 拖动和按下的两个监听
			jf.setResizable(false); // false就是不可改变大小
			jk.setVisible(true);
			
			g = (Graphics2D) jk.getGraphics();
		}
		else if (type.equals("进行训练")) {
			System.out.println("进行训练");
			exeflag = 1;
			jk = new JFrame();// 不能用jf，但是jf还是要传过来
			jk.setTitle("手写识别�?");
			jk.setSize(400, 420);
			jk.setDefaultCloseOperation(2);
			jk.setLocationRelativeTo(null);
			jk.addMouseMotionListener(this); // 两个窗体共用�???个事件监听类
			jk.addMouseListener(this);// 拖动和按下的两个监听
			jf.setResizable(false); // false就是不可改变大小
			jk.setVisible(true);
			g = (Graphics2D) jk.getGraphics();
		} 
		else if(type.equals("comboBoxChanged")){
			System.out.println(type);
			JComboBox<String>  jcb = (JComboBox<String>)e.getSource();   //额，忽然又不可以了。。。。	
			String str = jcb.getSelectedItem().toString(); 
		    System.out.println(str);
	        num=Integer.parseInt(str);  //字符串转化为整型
		}

	}
	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(10));// 实例化线条粗细对象，设置为画笔的粗细
		// 1.5根据按下和拖动的坐标值来绘制曲线
		g.drawLine(x1, y1, x2, y2);
		x1 = x2;
		y1 = y2;
		
		for (int i = 0; i < Config.ROWS; i++) {
			for (int j = 0; j < Config.COLUMNS; j++) {

				int x = Config.X0 + Config.SIZE * i;// 横坐�???
													// ,对应的是列数,x为水平向右，y为竖直向�???
				int y = Config.Y0 + Config.SIZE * j;// 纵坐�???
				// 与中心的误差为size/2
				if (x2 > x - Config.SIZE / 2 && x2 < x + Config.SIZE / 2
						&& y2 > y - Config.SIZE / 2 && y2 < y + Config.SIZE / 2) {
					chessValue[i][j] = 1;
				}
			}

		}

	}

	public void mousePressed(MouseEvent e) // 注意窗体�???外面的边框也算在窗体大小内，但是鼠标点击这里时却不会引发事件
	{
		x1 = e.getX();
		y1 = e.getY();
		System.out.println("按下时的x=" + x1 + "  y=" + y1);
	}

	public void mouseReleased(MouseEvent e) // 写入和读出都没问题了，存入都很正�???
	{
        String fatherPath="d:\\java\\filesave";
		 	
		if (exeflag == 1) // 进入训练模式�??
		{
			String file = fatherPath+"\\"+num+"-"+System.currentTimeMillis()+".txt";			
			try {
				System.out.println("进入训练模式后的num=" + num);
				File fileName=new File(file);
				fileName.createNewFile();
				OutputStream ous = new FileOutputStream(fileName); // 写入内容添加到文件的末尾
				DataOutputStream dos = new DataOutputStream(ous);
				for (int i = 0; i < Config.ROWS; i++) {
					for (int j = 0; j < Config.COLUMNS; j++) {
						dos.writeInt(chessValue[j][i]); // 先输出横坐标，再输出纵坐标，�???以时先把前面的坐标输出完�???
					} 
					
				}

				for (int i = 0; i < Config.ROWS; i++) {
					for (int j = 0; j < Config.COLUMNS; j++) {
						System.out.print(chessValue[j][i] + ""); // 先输出横坐标，再输出纵坐标，�???以时先把前面的坐标输出完�???
					}
					System.out.println();
				}
				init();// 写进文件以后数组值全部归�??

				dos.flush();
				dos.close();

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else // 从所有的文件取出�??有的数来进行比对，输出zhi数组,此时非训练模�??
		{
			try {

				for (int i = 0; i < Config.ROWS; i++) // 先把此次写入的输出一次先
				{
					for (int j = 0; j < Config.COLUMNS; j++) {
						System.out.print(chessValue[j][i]); // 先输出横坐标，再输出纵坐标，�???以时先把前面的坐标输出完�???
					}
					System.out.println();
				}
                 
				File file=new File(fatherPath);
				 String[] filelist = file.list();//返回一个数组，这个数组包含了文件夹里面的所有文件名

				 for (int f = 0; f < filelist.length; f++) 
				 {
                     File readfile = new File(fatherPath + "\\" + filelist[f]);
				     System.out.println("此时读取的文件为"+readfile);
					InputStream ins = new FileInputStream(readfile);
					DataInputStream dis = new DataInputStream(ins);
					System.out.println("此文件的拥有的字节数值为" + (ins.available()));
					System.out.println("此文件的拥有的整型字节数为" + (ins.available() / 4));
					System.out.println("此文件的拥有的样品数值为"
							+ (ins.available() / 4 / (19 * 19)));

					char fileNum=filelist[f].charAt(0);  //获取 字符，不是字符串 
					String filString=(String) filelist[f].subSequence(0, 1); //这里可以加个强制转换
					//字符得转换成字符串后才能转换为int
					String jj=String.valueOf(fileNum);//字符转换为字符串
					int fr=Integer.parseInt(filString);
					if(temp==fr)
					{
						countNumPointNum++;
//						if(fr==0 && temp==0)
//						{
//							countNumPointNum=0;
//						}
					}
					else {
						temp=fr;
						countNumPointNum=0;
					}
				
					System.out.println("读出来的文件的号码是"+fr);					
				//	countFileInt = ins.available() / 4;					
						for (int i = 0; i < Config.ROWS; i++) 
						{
							for (int j = 0; j < Config.COLUMNS; j++)
							{
								readNum[j][i] = dis.readInt(); //
															
								zhi[fr][countNumPointNum] = zhi[fr][countNumPointNum]
										+ (chessValue[j][i] - readNum[j][i])
										* (chessValue[j][i] - readNum[j][i]);

							}

						}
						zhi[fr][countNumPointNum] = (int) Math.sqrt(zhi[fr][countNumPointNum]);

					System.out.println("此时的文件号是"+fr+"此时的相同文件的数目为"+countNumPointNum);
					dis.close();
				}
			   
				for (int i = 1; i < 10; i++) { //由于前面的细节处理问题，所以第一行的数都为0，所以直接把第一行去掉了
					for (int j = 0; j < 10; j++) {
						System.out.print(zhi[j][i]); // 先输出横坐标，再输出纵坐标，�???以时先把前面的坐标输出完�???
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
		}
		jk.dispose();
	}

}
