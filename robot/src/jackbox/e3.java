package jackbox;
import robocode.*;
import java.awt.Color;
import java.lang.Math;

//samplerobot
public class e3 extends AdvancedRobot {
	int count = 0; // Keeps track of how long we've
	// been searching for our target
	double gunTurnAmt; // How much to turn our gun when searching
	String trackName; // Name of the robot we're currently tracking
	byte moveDirection = 1;
	
	/**
	 * run:  Timbot's main run function
	 */	
	public void run() {

		// CUSTOMIZE BABY
		setBodyColor(new Color(138, 43, 226));
		setGunColor(Color.black);
		setRadarColor(Color.white);
		setScanColor(new Color(138, 43, 226));
		setBulletColor(Color.white);
		
		// Prepare gun
		trackName = null; // Initialize to not tracking anyone
		setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
		gunTurnAmt = 10; // Initialize gunTurn to 10

		// Loop forever
		while (true) {
			// turn the Gun (looks for enemy)
			turnGunRight(gunTurnAmt);
			// Keep track of how long we've been looking
			count++;
			// If we've haven't seen our target for 2 turns, look left
			if (count > 3) {
				gunTurnAmt = -10;
			}
			// If we still haven't seen our target for 5 turns, look right
			if (count > 7) {
				gunTurnAmt = 10;
			}
			// If we *still* haven't seen our target after 10 turns, find another target
			if (count > 9) {
				trackName = null;
			}
		}
	}
	
	/**
	 * onScannedRobot:  Here's the good stuff
	 */	
	public void onScannedRobot(ScannedRobotEvent e) {

		// If we have a target, and this isn't it, return immediately
		// so we can get more ScannedRobotEvents.
		if (trackName != null && !e.getName().equals(trackName)) {
			return;
		}

		// If we don't have a target, well, now we do!
		if (trackName == null) {
			trackName = e.getName();
			out.println("Tracking " + trackName + " cause I saw him.");
		}
		// This is our target.  Reset count (see the run method)
		count = 0;
		// If our target is too far away, turn and move torward it.
		if (e.getDistance() > 400 + (200 - getEnergy()*2)) {
			gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));
			
			setTurnGunRight(gunTurnAmt);
			setTurnRight(e.getBearing());
			setAhead(e.getDistance() - 210);
			return;
		}

		// always square off against our enemy
		//setTurnRight(adjustHeadingForWalls(e.getBearing() + 90));
		setTurnRight(e.getBearing() + 90);
		//setTurnRight(normalAbsoluteAngle(e.getBearing() + 90 - (15 * moveDirection)));

		// strafe by changing direction every 20 ticks
		if (getTime() % 25 == 0) {
			moveDirection *= -1;
			setAhead(250 * moveDirection);
		}
	
		// Our target is close.
		gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		// If the gun is cool, the turret is aimed as near as can be and we have life left to spare, shoot.
		if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10)
			{
				setFire(Math.max(800/e.getDistance(), getEnergy()/10));
				out.println("DEBUG - Shot at: " + trackName + ". Damage: " + 800/e.getDistance());
			}
		
		// Oh God run away
		if (e.getDistance() < 200) {
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				moveDirection = -1;
			} else {
				moveDirection = 1;
			}
			setAhead(1000 * moveDirection);
		}
		scan();
	}
	/**
	 * onHitByBullet:  Set him as our new target
	 */	
	public void onHitByBullet(HitByBulletEvent event) {
		// Only print if he's not already our target.
		if (trackName != null && !trackName.equals(event.getName())) {
			out.println("Tracking " + event.getName() + " cause he shot me.");
		}
		// Set the target
		trackName = event.getName();
   }
public void onBulletMissed(BulletMissedEvent e)
{
}
public void onHitWall(HitWallEvent e) {
		out.println("DEBUG - Crashed");		
	}
	/**
	 * onHitRobot:  Set him as our new target
	 */	
	public void onHitRobot(HitRobotEvent e) {
		// Only print if he's not already our target.
		if (trackName != null && !trackName.equals(e.getName())) {
			out.println("Tracking " + e.getName() + " cause he rammed me.");
		}
		// Set the target
		trackName = e.getName();
		// RETALIATION COMMENCES
		gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		setFire(3);
		setAhead(1000 * moveDirection); 
		execute();
	}

	/**
	 * onWin:  ... and hear the lamentation of their women...
	 */	
	public void onWin(WinEvent e) 
	{
		System.out.println("TIM R WINNAR");
	}
	
	/**
	 * normalAbsoluteAngle:  Returns angle such that 0 <= angle < 360
	 */	
	public double normalAbsoluteAngle(double angle) {
		if (angle >= 0 && angle < 360) {
			return angle;
		}
		double fixedAngle = angle;

		while (fixedAngle < 0) {
			fixedAngle += 360;
		}
		while (fixedAngle >= 360) {
			fixedAngle -= 360;
		}
		return fixedAngle;
	}

	/**
	 * normalRelativeAngle:  Returns angle such that -180 < angle <= 180
	 */	
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

	// Perfunctory Wall Avoidance begins now

}

	// Predictive Targeting begins now


//Robot ����
//
//Robocode �����˵��������¼�� Robocode API Javadoc �С������ᷢ����Щ����� robocode.Robot ��Ĺ�������������һ���֣����ǽ���������ÿ�����õ����
//
//�ƶ������ˡ��ں��״�
//
//�����Ǵ��ƶ������˼���װ���Ļ������ʼ��
//
//  * turnRight(double degree) �� turnLeft(double degree) ʹ������ת��һ��ָ���ĽǶȡ�
//  * ahead(double distance) �� back(double distance) ʹ�������ƶ�ָ�������ص���룻�����������ڻ���������ǽ������һ��������ʱ������ɡ�
//  * turnGunRight(double degree) �� turnGunLeft(double degree) ʹ�ڿ��Զ�����̹�˳��ķ���ת����
//  * turnRadarRight(double degree) �� turnRadarLeft(double degree) ʹ��������״�ת����ת���ķ���Ҳ�������ڵķ����Լ�̹�˳��ķ��򣩡�
//
//��Щ�������ִ����Ϻ�Űѿ���Ȩ���������򡣴��⣬ת��̹�˳���ʱ�򣬳���ͨ���������з����ֱ�ָ���ڣ����״�ķ��򣬷����ڣ����״��ָ��Ҳ���ƶ���
//
//  * setAdjustGunForRobotTurn(boolean flag) ����� flag �����ó� true����ô̹�˳�ת��ʱ���ڱ���ԭ���ķ���
//  * setAdjustRadarForRobotTurn(boolean flag) ����� flag �����ó� true����ô̹�˳������ڣ�ת��ʱ���״�ᱣ��ԭ���ķ���
//  * setAdjustRadarForGunTurn(boolean flag) ����� flag �����ó� true����ô��ת��ʱ���״�ᱣ��ԭ���ķ��򡣶��ң���ִ�еĶ�����ͬ������ setAdjustRadarForRobotTurn(true) ��
//
//��ȡ���ڻ����˵���Ϣ
//
//�����෽�����Եõ����ڻ����˵���Ϣ��������о��˳��õķ������ã�
//
//  * getX() �� getY() ���Բ�׽�������˵�ǰ�����ꡣ
//  * getHeading() �� getGunHeading() �� getRadarHeading() ���Եó�̹�˳����ڻ��״ﵱǰ�ķ��򣬸÷������ԽǶȱ�ʾ�ġ�
//  * getBattleFieldWidth() �� getBattleFieldHeight() ���Եõ���ǰ��һ�غϵ�ս���ߴ硣
//
//�������
//
//һ�����������ƶ��������Լ���ص�����װ���ķ��������͸ÿ�������Ϳ����𺦵������ˡ�ÿ���������ڿ�ʼʱ����һ��ȱʡ�ġ��������𡱣����������������С�����ʱ�����Ǿ���Ϊ����������Ѿ��������ˡ������ʱ�򣬻������������õ�����������λ���ṩ���ڵ�������Խ�࣬��Ŀ�����������ɵ���Ҳ��Խ�� fire(double power) �� fireBullet(double power) ��������ָ�����������������ڵ������õ� fireBullet() �汾���� robocode.Bullet �����һ�����ã������ÿ������ڸ߼������ˡ�
//
//�¼�
//
//ÿ�����������ƶ���ת��ʱ���״�һֱ���ڼ���״̬������״��⵽�л����������ķ�Χ�ڣ��ͻᴥ��һ���¼�����Ϊ�����˴����ߣ�����Ȩѡ����������ս���з����ĸ����¼��������� Robot ���а�����������Щ�¼���ȱʡ�������򡣵��ǣ������Ը��������κ�һ����ʲôҲ�����ġ�ȱʡ��������Ȼ��ʵ�����Լ��Ķ�����Ϊ��������һЩ��Ϊ���õ��¼���
//
//  * ScannedRobotEvent ��ͨ������ onScannedRobot() ���������� ScannedRobotEvent �����״��⵽������ʱ���͵��ø÷�����
//  * HitByBulletEvent ��ͨ������ onHitByBullet() ���������� HitByBulletEvent ���������˱��ڵ�����ʱ���͵��ø÷�����
//  * HitRobotEvent ��ͨ������ onHitRobot() ���������� HitRobotEvent �������Ļ����˻�������һ��������ʱ���͵��ø÷�����
//  * HitWallEvent ��ͨ������ onHitWall() ���������� HitWallEvent �������Ļ�����ײ��ǽʱ���͵��ø÷�����
//
//����ֻ��Ҫ֪����Щ�Ϳ��Դ���һЩ�൱���ӵĻ������ˡ�������ͨ��ս���İ����˵��� Robot Editor �İ����˵����� Javadoc ������� Robocode API��
//
//���ڣ����Ǹð�ѧ����֪ʶ����ʵ���ˡ�

