package jackbox;

import java.awt.Color;
import robocode.*;


/**
 * Corners - a sample robot by Mathew Nelson, and maintained by Flemming N. Larsen
 * 
 * This robot moves to a corner, then swings the gun back and forth.
 * If it dies, it tries a new corner in the next round.
 * 
 * 这是一个专门蹲在角上射击的机器人，在每一局的一开始，它都会背靠一个角落，仅转动炮管，雷达
 * 瞄准敌人，进行射击  (by 蓝杰 www.NetJava.cn)
 */
public class Corners extends Robot {
	int others; // 当前局中其它机器人的数量
	static int corner = 0; // 当前所处的是哪一个角
	//如果雷达扫描到射程范围内有其它机器人，则停止.
	boolean stopWhenSeeRobot = false;  

	/**
	 * run:  Corners' main run function.
	 * 这个机器人启动的主法
	 */
	public void run() {
		//设置机器人的相关着色
		setBodyColor(Color.red);
		setGunColor(Color.red);
		setRadarColor(Color.red);
		setBulletColor(Color.red);
		setScanColor(Color.red);

		// 当前有哪些机器人
		others = getOthers();
		// 移动到一个角落
		goCorner();
		// 每次扫描转动3度
		int gunIncrement = 3;

		// 来回的扫描
		while (true) {
			for (int i = 0; i < 30; i++) {
				//正角度表示正向扫描
				turnGunLeft(gunIncrement);
			}
			//负角度：再扫回来
			gunIncrement *= -1;
		}
	}

	/**
	 * 移动到一个角落里，这段代码也许你可以修改得更好！
	 */
	public void goCorner() {
		//播放外部声音有问题
//		try{
//		AudioPlayer.player.start(new java.io.FileInputStream("Audios/powerup.au"));
//		}catch(Exception ef){
//			ef.printStackTrace();
//		}
		// 当扫描到其它机器人时,不要停止自己的运动
		stopWhenSeeRobot = false;
		// 转向右面的墙以便走向自己要呆的一个角.
		turnRight(normalRelativeAngle(corner - getHeading()));
		// OK,现在停下来,当看到其它机器人时
		stopWhenSeeRobot = true;
		// 向前跳
		ahead(5000);
		// 转自己的面向墙
		turnLeft(90);
		// 再向前
		ahead(5000);
		// 转动炮筒，指向战场！
		turnGunLeft(90);
	}

	/**
	 * 从Robot继承来的事件：当雷达扫到其它机器人的时候，开火!!!!!!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// 如果在运动中，射击精度可不好哦
		if (stopWhenSeeRobot) {
			// 停下其它动作，大敌当前哦.
			stop();
			// 开火！！！！！！！！！
			smartFire(e.getDistance());
			// 继续扫描其它机器人
			// 注意: 如果你在onScannedRobot方法内调用scan()方法时，这个机器人会在此组塞,当
			//扫描到一个机器人时,会重新执行这个事件处理
			scan();
			//重新开始其它的动作
			resume();
		} else {
			//如果己停下来，就快开火getDistance:返回与另外一个机器人的距离
			smartFire(e.getDistance());
		}
	}

	/**
	 * 我们为自己机器人定义的开火方法：根据与敌人的距离打出不同能量的炮弹.
	 * robotDistance:与敌人的距离
	 */
	public void smartFire(double robotDistance) {
		if (robotDistance > 200 || getEnergy() < 15) {
			//这个fire方法是人Robot类继承来的
			fire(1);
		} else if (robotDistance > 50) {
			fire(2);
		} else {
			fire(3);
		}
	}

	/**
	 * onDeath:  自己己玩完的事件发生. 在此决定下一场对局中要蹲向的另外一个角落.
	 */
	public void onDeath(DeathEvent e) {
		// Well, others should never be 0, but better safe than sorry.
		if (others == 0) {
			return;
		}

		// 如果有其它机器人都活着，我就要去蹲另外一个角了
		if ((others - getOthers()) / (double) others < .75) {
			corner += 90;
			if (corner == 270) {
				corner = -90;
			}
			out.println("在这个角上被另人打死了，所以我要转向另外一个角:  " + corner);
		} else {
			out.println("我死的光荣，杀死了敌人，所以还是呆在这个福角 : " + corner);
		}
	}

	/**
	 * normalRelativeAngle:  返回一个角度值 -180 < angle <= 180
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
}
