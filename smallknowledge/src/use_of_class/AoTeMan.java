package use_of_class;

public class AoTeMan {
	public String name;
	public int fightPower;
	public int HP;
	
	public void PK(XiaoGuaiShou k){
		k.HP-=fightPower;
		System.out.println(name+"���"+k.name+"һ�Σ�С���޵�ʣ������ֵΪ"+k.HP);
		if(k.HP<=0)
		{
			System.out.println("������ʤ��������С����ʧ�ܣ�����");
		}
			
	}

}
