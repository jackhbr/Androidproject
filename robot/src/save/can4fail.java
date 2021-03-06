package save;
import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
public class can4fail extends AdvancedRobot{
	double EnemyEnergy = 100;
	double gunTurnAmt; 
	double enemyDistance;
	int flag=0;
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
			setTurnGunRight(gunTurnAmt*1.5);
			
			if(getOthers()<=2 && e.getDistance()>=500)
			{
				
			}else {
				enemyDistance=e.getDistance();
				//setFire(Math.max(800/e.getDistance(), getEnergy()/10));
//				if(enemyDistance<=50)
//				{
//					if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10)
//					setFire(3);
//					flag=3;
//				}else if(enemyDistance>50 && enemyDistance<=200) {
//					if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10)
//					setFire(2);
//					flag=2;
//				}else if (enemyDistance>200) {
//					if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10)
//					setFire(1);
//					flag=1;
//				}
				
				if(getEnergy()<20)
				{
					setFire(0.2);
				}else {
					setFire(220 / Math.max(e.getDistance(), 100) + 0.7);
				}//持续设计会导致子弹的伤害很低
				
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
	public void onHitRobot(HitRobotEvent e) {  //在这里再进行一次射击的话，就能很好地提高bullet dmg的值
		String name=e.getName();
		if(name.contains("deathKnight"))
		{
			
		}else
		{
			gunTurnAmt = adjustAngle(e.getBearing() + (getHeading() - getRadarHeading()));	
			setTurnGunRight(gunTurnAmt*1.5);
			switch (flag) {  
			case 1:setFire(1);
				
				break;
			case 2:setFire(2);
				
				break;
			case 3:setFire(3);
		
				break;
	
			default:
				break;
			}
			execute();
		}
	}

}
