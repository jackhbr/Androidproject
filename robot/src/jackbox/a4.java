package jackbox;

import java.awt.Color;

import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;



public class a4 extends robocode.Robot{
	public void run(){
		//��ȡս������
		double width = getBattleFieldWidth();
		//��ȡ��ǰ�Ƕ�ֵ
		double heading = this.getHeading();
		//
		turnLeft(heading);
		turnGunRight(90);
		//��Զ�ظ�ִ��ĳһ��ָ��
		while(true){
			//��ȡλ��
			double x = getX();
			if(x< (width/2)){
				setBodyColor(Color.red);	
			}else{
				setBodyColor(Color.green);
			}
			this.ahead(400);
			
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent event){
		//ɨ�赽��һ����������Ҫִ�е�����
		this.fire(3);
		
	}
	
	//ײǽ��ת90��
	public void onHitWall(HitWallEvent event){
		turnRight(90);
	}
	

}