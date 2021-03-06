package jackbox;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class go3 extends AdvancedRobot{
	private Boolean HitWallFlag=false;
	private int directionflag=0;
	public void run(){
		double heading = this.getHeading();
		turnLeft(heading);
		
		while(true)
		{	
			if(HitWallFlag==false)
			{
				heading = this.getHeading();
				
				setTurnLeft(heading);
				setAhead(10);	
			}
			setTurnGunLeft(10);
			System.out.println("x值为  "+(int)getX()+"y为  "+(int)getY());
			
			if(HitWallFlag==true)
			{
				
				if((int)getX()<200)
				{
					directionflag=1;//为1表示要往右走
					
				}else if(getX()>1000)
				{
					directionflag=0;//为0表示要往左走
					
				}
				heading = this.getHeading();
				System.out.println("heading=  "+heading);
				if(directionflag==1)
				{
					if(heading==90)//如果刚好往左走，就转180，这里可能的角度值只有90和270
						{
						setTurnLeft(180);
						directionflag=2;
						}
				}
				else {
					if(heading==270)
					{
						setTurnRight(180);
						directionflag=2;
					}
					
				}
				
				setAhead(50);
			}
			execute();
			
		}
		
	}
	
	public void onScannedRobot(ScannedRobotEvent event){
		//扫描到了一个机器人需要执行的内容
		this.fire(3);
		
	}
	
	//撞墙旋转90度
	public void onHitWall(HitWallEvent event){
		HitWallFlag=true;
		turnRight(90);
	}
	public void onHitRobot(HitRobotEvent e) {
		
	}

}
