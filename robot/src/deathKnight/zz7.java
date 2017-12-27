package deathKnight;
import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
public class zz7 extends AdvancedRobot{
	double EnemyEnergy = 100;
	double gunTurnAmt; 
	public void run() {
		setAdjustGunForRobotTurn(true);
		do {
			turnGunRightRadians(1);
		} while (true);
	}
	public void onScannedRobot(ScannedRobotEvent e) {
		String name=e.getName();
		if(name.contains("deathKnight"))
		{
			
		}else 
		{
			setTurnRight(e.getBearing()+90);
			
			gunTurnAmt = adjustAngle(e.getBearing() + (getHeading() - getRadarHeading()));	
			setTurnGunRight(gunTurnAmt);
			
			if(getOthers()<=2 && e.getDistance()>=500)
			{
				
			}else {
				if(getEnergy()<20)
				{
					setFire(0.2);
				}else {
					setFire(220 / Math.max(e.getDistance(), 90) + 0.7);
				}
				
			}
			
			if(EnemyEnergy!=e.getEnergy())
			{
				EnemyEnergy=e.getEnergy();
				if(Math.random()<0.65)
				{
					setAhead(30+Math.random()*e.getDistance());
				}else {
					setBack(20+Math.random()*e.getDistance());
				}	
			}
			scan();
		}
	}
	public double adjustAngle(double angle) {
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
	
	public void onHitWall(HitWallEvent e) {
	     turnRight(e.getBearing()+180)  ;
	     ahead(88);
	     }

}
