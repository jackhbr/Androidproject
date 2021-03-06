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


//Robot 命令
//
//Robocode 机器人的命令集都收录在 Robocode API Javadoc 中。您将会发现这些命令都是 robocode.Robot 类的公共方法。在这一部分，我们将分类讨论每个可用的命令。
//
//移动机器人、炮和雷达
//
//让我们从移动机器人及其装备的基本命令开始：
//
//  * turnRight(double degree) 和 turnLeft(double degree) 使机器人转过一个指定的角度。
//  * ahead(double distance) 和 back(double distance) 使机器人移动指定的像素点距离；这两个方法在机器人碰到墙或另外一个机器人时即告完成。
//  * turnGunRight(double degree) 和 turnGunLeft(double degree) 使炮可以独立于坦克车的方向转动。
//  * turnRadarRight(double degree) 和 turnRadarLeft(double degree) 使炮上面的雷达转动，转动的方向也独立于炮的方向（以及坦克车的方向）。
//
//这些命令都是在执行完毕后才把控制权交还给程序。此外，转动坦克车的时候，除非通过调用下列方法分别指明炮（和雷达）的方向，否则炮（和雷达）的指向也将移动。
//
//  * setAdjustGunForRobotTurn(boolean flag) ：如果 flag 被设置成 true，那么坦克车转动时，炮保持原来的方向。
//  * setAdjustRadarForRobotTurn(boolean flag) ：如果 flag 被设置成 true，那么坦克车（和炮）转动时，雷达会保持原来的方向。
//  * setAdjustRadarForGunTurn(boolean flag) ：如果 flag 被设置成 true，那么炮转动时，雷达会保持原来的方向。而且，它执行的动作如同调用了 setAdjustRadarForRobotTurn(true) 。
//
//获取关于机器人的信息
//
//有许多方法可以得到关于机器人的信息。下面简单列举了常用的方法调用：
//
//  * getX() 和 getY() 可以捕捉到机器人当前的坐标。
//  * getHeading() 、 getGunHeading() 和 getRadarHeading() 可以得出坦克车、炮或雷达当前的方向，该方向是以角度表示的。
//  * getBattleFieldWidth() 和 getBattleFieldHeight() 可以得到当前这一回合的战场尺寸。
//
//射击命令
//
//一旦您掌握了移动机器人以及相关的武器装备的方法，您就该考虑射击和控制损害的任务了。每个机器人在开始时都有一个缺省的“能量级别”，当它的能量级别减小到零的时候，我们就认为这个机器人已经被消灭了。射击的时候，机器人最多可以用掉三个能量单位。提供给炮弹的能量越多，对目标机器人所造成的损害也就越大。 fire(double power) 和 fireBullet(double power) 用来发射指定能量（火力）的炮弹。调用的 fireBullet() 版本返回 robocode.Bullet 对象的一个引用，该引用可以用于高级机器人。
//
//事件
//
//每当机器人在移动或转动时，雷达一直处于激活状态，如果雷达检测到有机器人在它的范围内，就会触发一个事件。作为机器人创建者，您有权选择处理可能在战斗中发生的各类事件。基本的 Robot 类中包括了所有这些事件的缺省处理程序。但是，您可以覆盖其中任何一个“什么也不做的”缺省处理程序，然后实现您自己的定制行为。下面是一些较为常用的事件：
//
//  * ScannedRobotEvent 。通过覆盖 onScannedRobot() 方法来处理 ScannedRobotEvent ；当雷达检测到机器人时，就调用该方法。
//  * HitByBulletEvent 。通过覆盖 onHitByBullet() 方法来处理 HitByBulletEvent ；当机器人被炮弹击中时，就调用该方法。
//  * HitRobotEvent 。通过覆盖 onHitRobot() 方法来处理 HitRobotEvent ；当您的机器人击中另外一个机器人时，就调用该方法。
//  * HitWallEvent 。通过覆盖 onHitWall() 方法来处理 HitWallEvent ；当您的机器人撞到墙时，就调用该方法。
//
//我们只需要知道这些就可以创建一些相当复杂的机器人了。您可以通过战场的帮助菜单或 Robot Editor 的帮助菜单访问 Javadoc 中其余的 Robocode API。
//
//现在，我们该把学到的知识付诸实践了。


