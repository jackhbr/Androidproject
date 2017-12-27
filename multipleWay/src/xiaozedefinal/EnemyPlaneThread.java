package xiaozedefinal;

import java.util.ArrayList;

import javax.swing.JFrame;

public class EnemyPlaneThread implements Runnable {
	private ArrayList<EnemyPlane> al;
	private JFrame jf;
	private boolean sy=true;
	public void setSy(boolean sy) {
		this.sy = sy;
	}
	private volatile  boolean  flag=false;
	public  EnemyPlaneThread(JFrame jf,ArrayList<EnemyPlane> al){
		this.jf=jf;
		this.al=al;
	}
    public void run(){
    	while(true){
    		if(flag){
				continue;
			}
    		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		 EnemyPlane ep=new EnemyPlane((int)(Math.random()*jf.getWidth()));
    		 al.add(ep);
    		 if(sy==false){
   			  break;	
   			}
    	}
    }
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
