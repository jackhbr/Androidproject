package jackbox;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class go4canwalksuccess extends AdvancedRobot{
	private Boolean HitWallFlag=false;
	private int flag=0;
	public void run(){
		double heading = this.getHeading();
		turnLeft(heading);//���ʱsetturnleft���λ����ת�������turnleft�Ͳ���λ����ת��
		
		if(getX()<300)
		{
			turnRight(90);
			ahead(100);
			turnLeft(90);
		}
		if(getX()>900)
		{
			turnLeft(90);	
			ahead(100);
			turnRight(90);
		}
			
		
		
		while(HitWallFlag==false)
		{
			setAhead(50);
			setTurnGunLeft(10);//�ȵ����������ǽ����˵
			execute();
		}
		turnLeft(90);
		while(true)
		{
			while(true)
			{
			setAhead(50);
			setTurnGunLeft(40);
			if(getX()<200 || getX()>1000)
				break;
			execute();
			
			}
			if(getX()<200 || getX()>1000)
			{
				turnLeft(180);//���180���ܼ���execute�У���Ȼ����ת��������
				ahead(100);
			}
			
			
		}
		
		
	}
	public void onScannedRobot(ScannedRobotEvent event){
		//ɨ�赽��һ����������Ҫִ�е����ݣ�Ĭ���״����Ͳ��һ������ģ����Զ���׼ÿӰ��
		this.fire(3);
		
	}
	
	//ײǽ��ת90��
	public void onHitWall(HitWallEvent event){
		HitWallFlag=true;
	}
	public void onHitRobot(HitRobotEvent e) {
		
	}

}