package jackbox;

import java.awt.Color;

import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;



public class a4 extends robocode.Robot{
	public void run(){
		//获取战场宽度
		double width = getBattleFieldWidth();
		//获取当前角度值
		double heading = this.getHeading();
		//
		turnLeft(heading);
		turnGunRight(90);
		//永远重复执行某一段指令
		while(true){
			//获取位置
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
		//扫描到了一个机器人需要执行的内容
		this.fire(3);
		
	}
	
	//撞墙旋转90度
	public void onHitWall(HitWallEvent event){
		turnRight(90);
	}
	

}
