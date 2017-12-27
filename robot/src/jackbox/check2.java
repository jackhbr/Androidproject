package jackbox;

import robocode.*;
import java.awt.Color;
/*
 * author   "��ȵ����"team
 */
public class check2 extends AdvancedRobot {
	static int direction = 1;
	static double epEnergy = 100;
	double gunTurnAmt; 
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
		
		gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));	
		setTurnGunRight(gunTurnAmt);
		
		setFire(220 / Math.max(e.getDistance(), 90) + 0.9);

		setTurnRadarRightRadians(xiangdui(Bearing
				- getRadarHeadingRadians()) * 2);
		
		if(epEnergy!=e.getEnergy())//�����һ�ε��˵�����ֵ���������ڵ�����ֵ����ô���ƶ�������˵Ĺ���
		{
			epEnergy=e.getEnergy();//��ȡ���ڵ�����ֵ���γ����Ƶ����Ķ���
			if(Math.random()<0.65)
			{
				setAhead(30+Math.random()*e.getDistance());
			}else {
				setBack(20+Math.random()*e.getDistance());
			}
			
			
		}
		
		scan();
	}
	static double xiangdui(double r) {
		return Math.atan2(Math.sin(r), Math.cos(r));
	}
	public double normalRelativeAngle(double angle) {
		if (angle > -180 && angle <= 180) {
			return angle;
		}
		double fixedAngle = angle;

		while (fixedAngle <= -180) {
			fixedAngle += 360;
		}
		while (fixedAngle > 180) {
			fixedAngle -= 360;
		}
		return fixedAngle;
	}
}
