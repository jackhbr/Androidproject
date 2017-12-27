
public class XiaoGuaiShou {
	public String name;
	public int fightPower;
	public int HP;
	
	public void PK(AoTeMan k){
		k.HP-=fightPower;
		System.out.println("小怪兽"+name+"打击奥特曼一次，奥特曼的剩余生命值为"+k.HP);
		if(k.HP<=0)
		{
			System.out.println("小怪兽胜利！！！奥特曼失败！！！");
		}
			
	}

}
