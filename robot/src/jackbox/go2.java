package jackbox;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class go2 extends robocode.Robot{
	private Boolean HitWallFlag=false;
	public void run(){
		double heading = this.getHeading();
		turnLeft(heading);
		while(true)
		{	
			if(HitWallFlag==false)
			{
				ahead(10);	
			}
			turnGunLeft(10);
			turnLeft(90);
			
		}
		
	}
	
	public void onScannedRobot(ScannedRobotEvent event){
		//ɨ�赽��һ����������Ҫִ�е�����
		this.fire(3);
		
	}
	
	//ײǽ��ת90��
	public void onHitWall(HitWallEvent event){
		HitWallFlag=true;
		turnRight(180);
	}
	public void onHitRobot(HitRobotEvent e) {
		
	}

}