package jackbox;
import robocode.*;
import java.awt.geom.*;
//nikiko
public class e5  extends AdvancedRobot
{

    Rectangle2D playField;
    double lastEnemyHeading,firePower,targetEnergy = 100;
    int radarturn=1;

	public void run()
	{
            playField = new Rectangle2D.Double( 18, 18, getBattleFieldWidth() - 36, getBattleFieldHeight() - 36);
            while(true){
		setTurnRadarRightRadians(radarturn);	
		fire(firePower);
                execute();
            }
	}
	
	public void onScannedRobot(ScannedRobotEvent e)
	{
		
		
                firePower =  Math.min((5000/e.getDistance())*(e.getEnergy()/50)*(getEnergy()/50),3);
                
                if( targetEnergy != e.getEnergy() ){
                    double d = Math.random()*450*(Math.random()*2-1);
                    double newposx = getX() + Math.sin( getHeadingRadians() ) * 1.2 * d;
                    double newposy = getY() + Math.cos( getHeadingRadians() ) * 1.2 * d;
                    if (!playField.contains( newposx, newposy)) { d *= -1; }
                    setAhead( d );
                    targetEnergy = e.getEnergy();
                }
            
		radarturn *= -1;
		double w = e.getHeadingRadians()-lastEnemyHeading;
		lastEnemyHeading = e.getHeadingRadians();
		double eX = e.getDistance()*Math.sin(e.getBearingRadians()+getHeadingRadians());
		double eY = e.getDistance()*Math.cos(e.getBearingRadians()+getHeadingRadians());

		double bulletD = 0;
		double ww = lastEnemyHeading;
                
		while ( bulletD < Point2D.distance(0, 0, eX, eY) ){
			bulletD += (20 - 3 * firePower);
			double dx = e.getVelocity()*Math.sin(ww);
			double dy = e.getVelocity()*Math.cos(ww);
			ww += w; 
			eX += dx;
			eY += dy;
		}
		setTurnGunRightRadians(Math.asin(Math.sin(Math.atan2(eX, eY) - getGunHeadingRadians())));
		setTurnRightRadians(e.getBearingRadians() + Math.PI/2);

	}

}