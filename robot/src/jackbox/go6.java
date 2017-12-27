package jackbox;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class go6 extends AdvancedRobot{
	private Boolean HitWallFlag=false;
	private int flag=0;
	public void run(){
		double heading = this.getHeading();
		turnLeft(heading);//如果时setturnleft则会位置旋转，如果是turnleft就不会位置旋转，
		
		if(getX()<300)
		{
			turnRight(90);
			ahead(100);
			turnLeft(90);
		}
		if(getX()>900)
		{
			turnLeft(90);	
			ahead(100);
			turnRight(90);
		}
			
		
		
		while(HitWallFlag==false)
		{
			setAhead(50);
			setTurnGunLeft(10);//先到了最上面的墙壁再说
			execute();
		}
		turnLeft(90);
		while(true)
		{
			System.out.println("时间是"+getTime());
			while(true)
			{
			setAhead(50);
			if(flag==0)
			{
				setTurnGunLeft(40);//在没有打中敌人的情况下就继续圆周找寻敌人
			}
			if(getX()<200 || getX()>1000)
				break;
			execute();
			}
			if(getX()<200 || getX()>1000)
			{
				turnLeft(180);//这个180不能加入execute中，不然会旋转出外面来
				ahead(100);
			}	
		}
	}
	public void onScannedRobot(ScannedRobotEvent event){
		//扫描到了一个机器人需要执行的内容
		
		if(getTime()<1000)
		{
			if(event.getDistance()<100)
				setFire(3);
			if(event.getDistance()<50 && getEnergy()>10 )
				setFire(3);
			else if(event.getDistance()<300 && getEnergy()>10){//这个距离大了其实很难射中
				setFire(1);
			}
			execute();
		}
		else {
			
			if(event.getDistance()<30)
				setFire(1);
			if(event.getDistance()<50 && getEnergy()>10 )
				setFire(3);
			else if(event.getDistance()<300 && getEnergy()>10){//这个距离大了其实很难射中
				setFire(1);
			}
			execute();
			
		}
//		if(event.getDistance()<100)
//		{
//			flag=1;//停止转动		
//		}
		
		
		
	
	}
	
	//撞墙旋转90度
	public void onHitWall(HitWallEvent event){
		HitWallFlag=true;
	}
	public void onHitRobot(HitRobotEvent e) {
		if(getEnergy()>40 )
		{
			setFire(3);
			execute();
		}
			
	}
	public void onHitByBullet(HitByBulletEvent event) 
	{
		
	}

}
