package jackbox;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class go6 extends AdvancedRobot{
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
			System.out.println("ʱ����"+getTime());
			while(true)
			{
			setAhead(50);
			if(flag==0)
			{
				setTurnGunLeft(40);//��û�д��е��˵�����¾ͼ���Բ����Ѱ����
			}
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
		//ɨ�赽��һ����������Ҫִ�е�����
		
		if(getTime()<1000)
		{
			if(event.getDistance()<100)
				setFire(3);
			if(event.getDistance()<50 && getEnergy()>10 )
				setFire(3);
			else if(event.getDistance()<300 && getEnergy()>10){//������������ʵ��������
				setFire(1);
			}
			execute();
		}
		else {
			
			if(event.getDistance()<30)
				setFire(1);
			if(event.getDistance()<50 && getEnergy()>10 )
				setFire(3);
			else if(event.getDistance()<300 && getEnergy()>10){//������������ʵ��������
				setFire(1);
			}
			execute();
			
		}
//		if(event.getDistance()<100)
//		{
//			flag=1;//ֹͣת��		
//		}
		
		
		
	
	}
	
	//ײǽ��ת90��
	public void onHitWall(HitWallEvent event){
		HitWallFlag=true;
	}
	public void onHitRobot(HitRobotEvent e) {
		if(getEnergy()>40 )
		{
			setFire(3);
			execute();
		}
			
	}
	public void onHitByBullet(HitByBulletEvent event) 
	{
		
	}

}