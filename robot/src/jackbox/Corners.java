package jackbox;

import java.awt.Color;
import robocode.*;


/**
 * Corners - a sample robot by Mathew Nelson, and maintained by Flemming N. Larsen
 * 
 * This robot moves to a corner, then swings the gun back and forth.
 * If it dies, it tries a new corner in the next round.
 * 
 * ����һ��ר�Ŷ��ڽ�������Ļ����ˣ���ÿһ�ֵ�һ��ʼ�������ᱳ��һ�����䣬��ת���ڹܣ��״�
 * ��׼���ˣ��������  (by ���� www.NetJava.cn)
 */
public class Corners extends Robot {
	int others; // ��ǰ�������������˵�����
	static int corner = 0; // ��ǰ����������һ����
	//����״�ɨ�赽��̷�Χ�������������ˣ���ֹͣ.
	boolean stopWhenSeeRobot = false;  

	/**
	 * run:  Corners' main run function.
	 * �������������������
	 */
	public void run() {
		//���û����˵������ɫ
		setBodyColor(Color.red);
		setGunColor(Color.red);
		setRadarColor(Color.red);
		setBulletColor(Color.red);
		setScanColor(Color.red);

		// ��ǰ����Щ������
		others = getOthers();
		// �ƶ���һ������
		goCorner();
		// ÿ��ɨ��ת��3��
		int gunIncrement = 3;

		// ���ص�ɨ��
		while (true) {
			for (int i = 0; i < 30; i++) {
				//���Ƕȱ�ʾ����ɨ��
				turnGunLeft(gunIncrement);
			}
			//���Ƕȣ���ɨ����
			gunIncrement *= -1;
		}
	}

	/**
	 * �ƶ���һ���������δ���Ҳ��������޸ĵø��ã�
	 */
	public void goCorner() {
		//�����ⲿ����������
//		try{
//		AudioPlayer.player.start(new java.io.FileInputStream("Audios/powerup.au"));
//		}catch(Exception ef){
//			ef.printStackTrace();
//		}
		// ��ɨ�赽����������ʱ,��Ҫֹͣ�Լ����˶�
		stopWhenSeeRobot = false;
		// ת�������ǽ�Ա������Լ�Ҫ����һ����.
		turnRight(normalRelativeAngle(corner - getHeading()));
		// OK,����ͣ����,����������������ʱ
		stopWhenSeeRobot = true;
		// ��ǰ��
		ahead(5000);
		// ת�Լ�������ǽ
		turnLeft(90);
		// ����ǰ
		ahead(5000);
		// ת����Ͳ��ָ��ս����
		turnGunLeft(90);
	}

	/**
	 * ��Robot�̳������¼������״�ɨ�����������˵�ʱ�򣬿���!!!!!!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// ������˶��У�������ȿɲ���Ŷ
		if (stopWhenSeeRobot) {
			// ͣ��������������е�ǰŶ.
			stop();
			// ���𣡣���������������
			smartFire(e.getDistance());
			// ����ɨ������������
			// ע��: �������onScannedRobot�����ڵ���scan()����ʱ����������˻��ڴ�����,��
			//ɨ�赽һ��������ʱ,������ִ������¼�����
			scan();
			//���¿�ʼ�����Ķ���
			resume();
		} else {
			//�����ͣ�������Ϳ쿪��getDistance:����������һ�������˵ľ���
			smartFire(e.getDistance());
		}
	}

	/**
	 * ����Ϊ�Լ������˶���Ŀ��𷽷�����������˵ľ�������ͬ�������ڵ�.
	 * robotDistance:����˵ľ���
	 */
	public void smartFire(double robotDistance) {
		if (robotDistance > 200 || getEnergy() < 15) {
			//���fire��������Robot��̳�����
			fire(1);
		} else if (robotDistance > 50) {
			fire(2);
		} else {
			fire(3);
		}
	}

	/**
	 * onDeath:  �Լ���������¼�����. �ڴ˾�����һ���Ծ���Ҫ���������һ������.
	 */
	public void onDeath(DeathEvent e) {
		// Well, others should never be 0, but better safe than sorry.
		if (others == 0) {
			return;
		}

		// ��������������˶����ţ��Ҿ�Ҫȥ������һ������
		if ((others - getOthers()) / (double) others < .75) {
			corner += 90;
			if (corner == 270) {
				corner = -90;
			}
			out.println("��������ϱ����˴����ˣ�������Ҫת������һ����:  " + corner);
		} else {
			out.println("�����Ĺ��٣�ɱ���˵��ˣ����Ի��Ǵ���������� : " + corner);
		}
	}

	/**
	 * normalRelativeAngle:  ����һ���Ƕ�ֵ -180 < angle <= 180
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
