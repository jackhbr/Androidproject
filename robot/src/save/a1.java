package save;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class a1 extends AdvancedRobot{
	private double EnemyEnergy = 100;
	private int direction = 1;
	public void run()
	{
		setAdjustGunForRobotTurn(true);//��Ͳ��������˶��˶�
		setAdjustRadarForGunTurn(true);
		while(true)
		{
			turnRadarLeftRadians(2);
		}
	}
	public void onScannedRobot(ScannedRobotEvent e) {
		
		
		double Bearing = getHeadingRadians() + e.getBearingRadians();
		setTurnRight(e.getBearing()+90);//���������ƶ�����������ƶ�
		setTurnGunRightRadians(xiangdui(Bearing
				- getGunHeadingRadians()+ Math.asin(e.getVelocity()* Math.sin(e.getHeadingRadians() - Bearing) / 17)));
		setFire(220 / Math.max(e.getDistance(), 90) + 0.9);

		setTurnRadarRightRadians(xiangdui(Bearing
				- getRadarHeadingRadians()) * 2);
		
		EnemyEnergy=e.getEnergy();//��ȡ���ڵ�����ֵ���γ����Ƶ����Ķ���
		if(EnemyEnergy!=e.getEnergy())//�����һ�ε��˵�����ֵ���������ڵ�����ֵ����ô���ƶ�������˵Ĺ���
		{
			if(Math.random()<0.65)//��������ǰ������������һֱ�����������ģ���һֱ���ŶԷ�ȥ����ô��Щ�ڵ������˷ѣ������ʧ
			{
				setAhead(30+Math.random()*e.getDistance());
			}else {
				setBack(20+Math.random()*e.getDistance());
			}
			
			
		}
		
		scan();//����������϶���Ҫ�еģ���Ȼû��һֱ�ƶ������в�ȡ��������scan�İ취����Ϊ
		//��������ͬʱ����ô������飬�ֲ����ö��̣߳���������Ƕ��̵߳�һֱ��ʽ��������scan��Զ���ڽϸߵ��ж����ȼ�
		//����������ܴ���executeʹset�ķ���ִ��
	}
	private double xiangdui(double r) {
		return Math.atan2(Math.sin(r), Math.cos(r));
	}

}