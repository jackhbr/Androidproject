package jackbox;

import robocode.ScannedRobotEvent;

public class a2 extends robocode.Robot {

	public void run() {
		while (true) {
			
			turnLeft(90);
			ahead(1);
			back(1);
			ahead(1);
			back(1);

		}
	}

	public void onScannedRobot(ScannedRobotEvent event) {
		String name = event.getName();
		double breaing = event.getBearing();

		if (name.contains("jackhe")) {//分别队友,亲测有效，这就是队名

		} else {

			this.turnLeft(breaing);//反正就是这样用就对了，获得这个方位，然后就向左转那么多的角度就行了
			
			fire(3);
		}
	}
}
