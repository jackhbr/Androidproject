package jackbox;

import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class a3 extends robocode.Robot{ //������������һ����Ҫ�취������notepad�д�Java�ļ���Ȼ��Ѵ��븴�ƽ���
	public void run(){					//���ֱ�Ӱ��ļ����ƽ����ͻᵼ��utf-8��gbk������
		//�������ӽǶ�						//���Ƕ�Ӵ�һЩ��̵Ķ����ð�������֪����С���ɾͶ�
		double heading = getHeading();
		//����ת
		turnLeft(heading);
		//��ת��Ͳ
		turnGunRight(90);
		while(true){
			//ǰ��100
			ahead(100);
		}
		
	}
	
	public void onHitWall(HitWallEvent event){
		//����ת90��
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