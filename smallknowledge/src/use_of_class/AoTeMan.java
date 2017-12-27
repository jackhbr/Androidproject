package use_of_class;

public class AoTeMan {
	public String name;
	public int fightPower;
	public int HP;
	
	public void PK(XiaoGuaiShou k){
		k.HP-=fightPower;
		System.out.println(name+"打击"+k.name+"一次，小怪兽的剩余生命值为"+k.HP);
		if(k.HP<=0)
		{
			System.out.println("奥特曼胜利！！！小怪兽失败！！！");
		}
			
	}

}
