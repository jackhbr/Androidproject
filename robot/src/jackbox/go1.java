package jackbox;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class go1 extends robocode.Robot{
	public void run(){
		
	}
	
	public void onScannedRobot(ScannedRobotEvent event){
		//扫描到了一个机器人需要执行的内容
		this.fire(3);
		
	}
	
	//撞墙旋转90度
	public void onHitWall(HitWallEvent event){
		turnRight(90);
	}
	public void onHitRobot(HitRobotEvent e) {
		
	}

}
