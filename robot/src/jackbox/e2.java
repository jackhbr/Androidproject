package jackbox;

import robocode.*; 
public class e2 extends Robot
{
       public void run () {
       while (true)
       {
              turnGunRight(5);
       }
       }
  
       public void onScannedRobot(ScannedRobotEvent e)
       {
              System.out.println("getBearing  "+e.getBearing());
              System.out.println("heading  "+getHeading());
              System.out.println("getRadarHeading()  "+getRadarHeading());
              turnGunRight(e.getBearing());
              fire(3);
       }      
}
