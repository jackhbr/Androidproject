package jackbox;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class go3 extends AdvancedRobot{
	private Boolean HitWallFlag=false;
	private int directionflag=0;
	public void run(){
		double heading = this.getHeading();
		turnLeft(heading);
		
		while(true)
		{	
			if(HitWallFlag==false)
			{
				heading = this.getHeading();
				
				setTurnLeft(heading);
				setAhead(10);	
			}
			setTurnGunLeft(10);
			System.out.println("xֵΪ  "+(int)getX()+"yΪ  "+(int)getY());
			
			if(HitWallFlag==true)
			{
				
				if((int)getX()<200)
				{
					directionflag=1;//Ϊ1��ʾҪ������
					
				}else if(getX()>1000)
				{
					directionflag=0;//Ϊ0��ʾҪ������
					
				}
				heading = this.getHeading();
				System.out.println("heading=  "+heading);
				if(directionflag==1)
				{
					if(heading==90)//����պ������ߣ���ת180��������ܵĽǶ�ֵֻ��90��270
						{
						setTurnLeft(180);
						directionflag=2;
						}
				}
				else {
					if(heading==270)
					{
						setTurnRight(180);
						directionflag=2;
					}
					
				}
				
				setAhead(50);
			}
			execute();
			
		}
		
	}
	
	public void onScannedRobot(ScannedRobotEvent event){
		//ɨ�赽��һ����������Ҫִ�е�����
		this.fire(3);
		
	}
	
	//ײǽ��ת90��
	public void onHitWall(HitWallEvent event){
		HitWallFlag=true;
		turnRight(90);
	}
	public void onHitRobot(HitRobotEvent e) {
		
	}

}