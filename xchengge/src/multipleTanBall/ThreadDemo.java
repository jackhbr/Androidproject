package multipleTanBall;

import java.awt.Graphics;
import java.util.ArrayList;

public class ThreadDemo implements Runnable{
	private ArrayList<Ball> listBall;
	private Graphics g;
	private boolean flag=false;
	
	public ThreadDemo(ArrayList<Ball> listBall, Graphics g) {
		this.listBall = listBall;
		this.g = g;
	}
	public void setFlag( boolean flag){
		this.flag = flag;
	}
	/**
	 * 启动线程执行的方法
	 */
	public void run(){
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(flag){
				continue;
			}
			System.out.println("listBall.size():"+listBall.size());
			//取出listBall中的小球对象，画出来
			for(int i=0;i<listBall.size();i++){
				Ball ball = listBall.get(i);
				ball.drawBall(g);
				System.out.println(Thread.currentThread().getName());
			}
			
		}
	}
}
