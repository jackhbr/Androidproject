package jackbox;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class go1 extends robocode.Robot{
	public void run(){
		
	}
	
	public void onScannedRobot(ScannedRobotEvent event){
		//ɨ�赽��һ����������Ҫִ�е�����
		this.fire(3);
		
	}
	
	//ײǽ��ת90��
	public void onHitWall(HitWallEvent event){
		turnRight(90);
	}
	public void onHitRobot(HitRobotEvent e) {
		
	}

}
