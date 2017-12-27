package use_of_class2;

public class AoTeMan {
	public String name;
	public int fightPower;
	public int HP;
	
	public void PK(AoTeMan k){
		k.HP-=fightPower;
		System.out.println(this.name+"打击"+k.name+"一次    "+k.name+"的剩余生命值为"+k.HP);
		if(k.HP<=0)
		{
			System.out.println(this.name+"胜利！！！    "+k.name+"失败！！！");
		}
			
	}

}
