package multipleThreadFractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;



public class threadDemo extends Thread{
	
	public String type = " ";
	// 1.3.声明四个变量，用来存储按下和释放的坐标值。
	public int x1, y1, x2, y2;
	// 1.5.声明一个Graphics画笔属性
	public Graphics2D g;
	
	public void setthread(Graphics2D g,int x1,int y1,int x2,int y2,String type)
	{
		this.g=g;
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.type=type;
	}
	
	
	
	
	public void run() 
	{
		
		// 1.6.在释放方法中，根据按下、释放坐标值以及选择的图形，使用Graphics画笔绘制对应图形
		
		
		//这里不可以用switch来判断字符串，jdk1.7版本的就可以,我的可以改为1.7，所以so easy
//		switch(type){
//		case "line":
//		}
		
		if (type.equals("Line")) {
			g.drawLine(x1, y1, x2, y2);// 绘制直线
			System.out.println("type = " + type);
		} else if (type.equals("Rect")) {
			System.out.println("type = " + type);
		//	g.drawRect(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));// 绘制矩形
			// g.fillOval(x, y, width, height);//绘制填充圆
			g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));// 绘制矩形
			
		}else if(type.equals("Oval")){
			g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}else if(type.equals("RoundRect")){
			g.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2),Math.round((float)0.2* Math.abs(x1 - x2)),Math.round((float)0.2* Math.abs(y1 - y2)));}
		 //注意这个函数里面的参数要都为int型，所以这里用到了强制转换，还有math里面的取整函数
		else if(type.equals("fillOval")){
			g.setColor(Color.BLUE);
			g.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
			g.setColor(Color.BLACK);	
		}
		
		else if(type.equals("fill3DRect")){
			g.setColor(Color.RED);
			g.fill3DRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2),true);
			g.setColor(Color.BLACK);
		}//没看出3d效果,就这样了
		
	
		else if(type.equals("drawImage")){
			//g.setColor(Color.BLUE);
			Image img = new ImageIcon("img/water.jpg").getImage();//根目录，每一个斜杠后面要再加上一条斜杠
			g.drawImage(img, Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2),null);}
		else if(type.equals("text")){
			g.setColor(Color.RED);
			g.drawString("hello!", Math.min(x1, x2), Math.min(y1, y2));
			g.setColor(Color.BLACK);	
		}
		
		else if(type.equals("立体图形")){
			g.setColor(Color.RED);
			g.drawRect((int)(Math.min(x1, x2)-0.33*Math.abs(x1 - x2)), (int)(Math.min(y1, y2)-0.33* Math.abs(y1 - y2)), Math.abs(x1 - x2), Math.abs(y1 - y2));
			g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));	
			g.drawLine((int)(Math.min(x1, x2)-0.33*Math.abs(x1 - x2))+Math.abs(x1 - x2), (int)(Math.min(y1, y2)-0.33* Math.abs(y1 - y2)),Math.min(x1, x2)+Math.abs(x1 - x2), Math.min(y1, y2));
			g.drawLine((int)(Math.min(x1, x2)-0.33*Math.abs(x1 - x2)), (int)(Math.min(y1, y2)-0.33* Math.abs(y1 - y2))+Math.abs(y1 - y2),Math.min(x1, x2), Math.min(y1, y2)+Math.abs(y1 - y2));
			g.drawLine((int)(Math.min(x1, x2)-0.33*Math.abs(x1 - x2))+Math.abs(x1 - x2), (int)(Math.min(y1, y2)-0.33* Math.abs(y1 - y2))+Math.abs(y1 - y2),Math.min(x1, x2)+Math.abs(x1 - x2), Math.min(y1, y2)+Math.abs(y1 - y2));
			g.drawLine((int)(Math.min(x1, x2)-0.33*Math.abs(x1 - x2)), (int)(Math.min(y1, y2)-0.33* Math.abs(y1 - y2)),Math.min(x1, x2), Math.min(y1, y2));
			g.setColor(Color.BLACK);
		}
		
		else if(type.equals("小泽三角形"))
		{			
		System.out.println(Thread.currentThread().getName());	
			System.out.println("type = " + type);
			g.setColor(Color.BLACK);
			g.drawLine(x2,y2,200,200);
			g.drawLine(200,200,200,400);
			g.drawLine(200,400,x2,y2);
			Random rand=new Random();
			int	x3=0;
			int y3=0;
			for(int j=0;j<10000;j++)
			{
		
			int i=rand.nextInt(3);
		
			switch (i) 
			{
			case 0:{
				g.drawLine((x3+x2)/2,(y3+y2)/2,(x3+x2)/2,(y3+y2)/2);
				x3=(x3+x2)/2;y3=(y3+y2)/2;
			}
				break;
			case 1:
			{
				g.drawLine((200+x3)/2,(200+y3)/2,(200+x3)/2,(200+y3)/2);
				x3=(200+x3)/2;y3=(200+y3)/2;
			}
				break;
			case 2:
			{
				g.drawLine((200+x3)/2,(400+y3)/2,(200+x3)/2,(400+y3)/2);
				x3=(200+x3)/2;y3=(400+y3)/2;
			}
				break;			
			}
			}
               
		}
		else if(type.equals("永恒之心"))
		{	
			System.out.println(Thread.currentThread().getName());	//输出当前线程的名字
			System.out.println("type = " + type);
			draw(g);
			g.setColor(Color.BLACK);
		}
		else if(type.equals("图形一"))
		{	
			System.out.println(Thread.currentThread().getName());	
			drawDream(g);
			g.setColor(Color.BLACK);
		}
		else if(type.equals("叶子"))
		{	
			System.out.println(Thread.currentThread().getName());	
			g.setColor(Color.GREEN);
			leaf(g);
			g.setColor(Color.BLACK);
		}
		
		
		
	}
	
	private void draw(Graphics2D g)
	{
    	threadDemo kDemo=new threadDemo();
		kDemo.start();
		for(int i=0;i<=180;i++){
		for(int j=0;j<=180;j++){
		double r=Math.PI/45*i*(1-Math.sin(Math.PI/45*j))*20;
		double x=r*Math.cos(Math.PI/45*j)*Math.sin(Math.PI/45*i)+300;
		double y=-r*Math.sin(Math.PI/45*j)+200;
		Color c=Color.getHSBColor(i*j/8100.0f, 0.9999f,0.9999f);
		g.setColor(c);
		g.drawOval((int)x, (int)y, 1,1);
		try
		{
		Thread.sleep(1);
		}
		catch(Exception e)
		{}
		}
		}
		
		
	}
	
	
	private void drawDream(Graphics g){
		double x=0f;
		double y=0f;
		//a,b,c,d等4个常量的值预设
		double a=-1.8, b =-2.0, c=-0.5, d=-0.9;
		for(int i=0;i<22500;i++)  //I控制画的时间，太大会导致后面颜色部分越界
		{
		//公式：
		double temx= Math.sin(a*y)+c*Math.cos(a*x);
		
		
		double temy=Math.sin(b*x)+d*Math.cos(b*y);
		//对x1,y1转型，放大，移动到屏幕坐标系： 控制大小，控制位置
		int x1= (int)(temx*80+300);
		int y1= (int)(temy*80+300);
		//System.out.println("x1: "+x1+" y1: "+y1);
		//颜色根据佚代次数加深
		g.setColor(new Color(0,0,i/100));
		g.drawLine(x1, y1, x1,y1);
		x=temx;
		y=temy;
		}
		}
	
	public void leaf(Graphics g)
	{
		double a=0,b=0,c=0,d=0,e=0,f=0;
		double x2=0,y2=0,x1=500,y1=500;
		for(int i=0;i<100000;i++)   //控制概率可以改变叶子的形状
		{
			int randomNumber=(int)(Math.random()*100)+1;//math.random()取得某个范围内的随机数
			//得到一个1到100之间的随机整数     Math.random()的作用是得到0-1之间的随机数
	        if (randomNumber>=1 && randomNumber<=10) 
	        {
				 a=0;b=0;c=0;d=0.16;e=0;f=0;
			}
	        else if (randomNumber>10 && randomNumber<=18)
	        {
	        	a=0.2;b=-0.26;c=0.23;d=0.22;e=0;f=1.6;
			}
	        else if (randomNumber>18 && randomNumber<=26)
	        {
	        	 a=-0.15;b=0.28;c=0.26;d=0.24;e=0;f=0.44;
			}
	        else if (randomNumber>26 && randomNumber<=100)
	        {
	        	 a=0.75;b=0.04;c=-0.04;d=0.85;e=0;f=1.6;
			}
	        
	    	        y2 = c*x1 + d*y1 + f;
	                x2 = a*x1 + b*y1 + e;
	                
	                x2=50*x2;//适当放大数
	                y2=50*y2;
	                System.out.println("x2="+x2+"   y2="+y2);
	        	g.drawLine((int)x2+200,(int) y2+200,(int) x2+200,(int) y2+200);  //这里加上数就可以平移位置
	        	x2=x2/50;
                y2=y2/50; 
	        	x1=x2;y1=y2;  //这里实现迭代
			
		}
		
	}
	
	
	
	
	

}
