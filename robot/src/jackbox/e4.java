package jackbox;

import robocode.*;
import java.awt.Color;
/*
 * author   "��ȵ����"team
 */
public class e4 extends AdvancedRobot {
	static int direction = 1;
	static double epEnergy = 100;
	public void run() {
		setColors(Color.white, Color.red, Color.green,Color.yellow,Color.red);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		do {
			turnRadarRightRadians(1);
		} while (true);
	}
	public void onScannedRobot(ScannedRobotEvent e) {
		double Bearing = getHeadingRadians() + e.getBearingRadians();
		setTurnRightRadians(xiangdui(e.getBearingRadians() + Math.PI / 2)
				- Math.PI / 4 * Math.random() * direction);
		setTurnGunRightRadians(xiangdui(Bearing
				- getGunHeadingRadians()+ Math.asin(e.getVelocity()* Math.sin(e.getHeadingRadians() - Bearing) / 17)));
		setFire(220 / Math.max(e.getDistance(), 90) + 0.9);

		setTurnRadarRightRadians(xiangdui(Bearing
				- getRadarHeadingRadians()) * 2);
		
		//���������仯������������ƶ������ⱻ��������켣�ͱ����У�
		if (epEnergy != (epEnergy = e.getEnergy())) {
			setAhead((e.getDistance() * Math.random() + 30)
					* (direction = Math.random() > 0.6 ? 1 : -1));
		}
		scan();
	}
	static double xiangdui(double r) {
		return Math.atan2(Math.sin(r), Math.cos(r));
	}
}

