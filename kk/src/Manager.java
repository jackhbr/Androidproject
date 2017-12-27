
public class Manager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AoTeMan AoT=new AoTeMan();
		AoT.name="diJia";
		AoT.fightPower=100;
		AoT.HP=1000;
		
		XiaoGuaiShou Guai=new XiaoGuaiShou();
		Guai.name="erTianBa";
		Guai.fightPower=80;
		Guai.HP=800;
		
		while(AoT.HP>0 && Guai.HP>0)
		{
			AoT.PK( Guai);
			if( Guai.HP>0)
			Guai.PK(AoT);
		}

	}

}
