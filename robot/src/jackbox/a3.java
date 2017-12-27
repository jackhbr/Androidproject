package jackbox;

import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class a3 extends robocode.Robot{ //解决乱码问题的一大重要办法：先在notepad中打开Java文件，然后把代码复制进来
	public void run(){					//如果直接把文件复制进来就会导致utf-8到gbk的乱码
		//摆正车子角度						//还是多接触一些编程的东西好啊，这样知道的小技巧就多
		double heading = getHeading();
		//向左转
		turnLeft(heading);
		//旋转炮筒
		turnGunRight(90);
		while(true){
			//前进100
			ahead(100);
		}
		
	}
	
	public void onHitWall(HitWallEvent event){
		//向右转90度
		this.turnRight(90);
	}
	
	public void onScannedRobot(ScannedRobotEvent event) {
	       // Assuming radar and gun are aligned...
	       if (event.getDistance() < 100) {
	           fire(3);
	       } else {
	           fire(1);
	       }
	   }

}
