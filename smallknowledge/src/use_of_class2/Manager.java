package use_of_class2;

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
		
		AoTeMan Jac=new AoTeMan();
		Jac.name="jinGang";
		Jac.fightPower=90;
		Jac.HP=900;
		
		
		XiaoGuaiShou Guai=new XiaoGuaiShou();
		Guai.name="erTianBa";
		Guai.fightPower=80;
		Guai.HP=800;
		
		XiaoGuaiShou Gua=new XiaoGuaiShou();
		Gua.name="zuanDiHu";
		Gua.fightPower=70;
		Gua.HP=900;
		
		while(AoT.HP>0 && Guai.HP>0)
		{
			AoT.PK( Guai);
			if( Guai.HP>0)
			Guai.PK(AoT);
		}
		
		
		if(Guai.HP>0)
		{
			Guai.HP=800;
			while(Gua.HP>0 && Guai.HP>0)
			{
				Gua.PK( Guai);
				if( Guai.HP>0)
				Guai.PK(Gua);
			}
		}
		else
		{
			AoT.HP=1000;
			while(AoT.HP>0 && Jac.HP>0)
			{
				AoT.PK( Jac);
				if( Jac.HP>0)
					Jac.PK(AoT);
			}
		}

	}

}
