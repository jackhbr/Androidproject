package xiaozedefinal;

import java.util.ArrayList;

public class BulletThread implements Runnable{
	private ArrayList<Bullet> bl=new ArrayList<Bullet>();
	private MyPlane mp;
	private boolean sy=true;
	public void setSy(boolean sy) {
		this.sy = sy;
	}
	private volatile  boolean  flag=false;
    public BulletThread(ArrayList<Bullet> bl, MyPlane mp) {
		this.bl = bl;
		this.mp = mp;
	}
	public void run(){
		while(true){
			if(flag){
				continue;
			}
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		    Bullet b=new Bullet(mp.getX()+40,mp.getY()-100);
		    bl.add(b);
		    if(sy==false){
		    	
				  break;	
				}
		}
		
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
