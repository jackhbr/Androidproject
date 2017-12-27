package jackbox;

public class a1 extends robocode.Robot{
	public void run()
	{
		while(true)
		{
			fire(3);
			ahead(100);
			turnLeft(90);
			fire(2);
			back(200);
			turnRight(90);
			
		}
	}

}
