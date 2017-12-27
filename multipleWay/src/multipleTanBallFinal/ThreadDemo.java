package multipleTanBallFinal;

import java.awt.Graphics;
import java.util.ArrayList;

public class ThreadDemo implements Runnable{
	private ArrayList<Ball> listBall;
	private Graphics g;
	private  boolean flag=false;
	
	public ThreadDemo(ArrayList<Ball> listBall, Graphics g) {
		this.listBall = listBall;
		this.g = g;
	}
	public void setFlag( boolean flag){
		this.flag = flag;
	}
	/**
	 * �����߳�ִ�еķ���
	 */
	public void run(){
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(flag){    //Ϊtrue������ͣ
				continue;  //continue֮���ѭ���ĳ���Ͳ���������
			}
			System.out.println("listBall.size():"+listBall.size());//�������Գ���
			//ȡ��listBall�е�С����󣬻�����
			for(int i=0;i<listBall.size();i++){
				Ball ball = listBall.get(i);
				for(int j=0;j<listBall.size();j++){
				
			    Ball ball1 = listBall.get(j);
			    ball.exchangeWay(ball1);
				}
				
				ball.drawBall(g);
			}
			
		}
	}
}
