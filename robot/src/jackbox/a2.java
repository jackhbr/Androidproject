package jackbox;

import robocode.ScannedRobotEvent;

public class a2 extends robocode.Robot {

	public void run() {
		while (true) {
			
			turnLeft(90);
			ahead(1);
			back(1);
			ahead(1);
			back(1);

		}
	}

	public void onScannedRobot(ScannedRobotEvent event) {
		String name = event.getName();
		double breaing = event.getBearing();

		if (name.contains("jackhe")) {//�ֱ����,�ײ���Ч������Ƕ���

		} else {

			this.turnLeft(breaing);//�������������þͶ��ˣ���������λ��Ȼ�������ת��ô��ĽǶȾ�����
			
			fire(3);
		}
	}
}
