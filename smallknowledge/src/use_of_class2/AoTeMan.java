package use_of_class2;

public class AoTeMan {
	public String name;
	public int fightPower;
	public int HP;
	
	public void PK(AoTeMan k){
		k.HP-=fightPower;
		System.out.println(this.name+"���"+k.name+"һ��    "+k.name+"��ʣ������ֵΪ"+k.HP);
		if(k.HP<=0)
		{
			System.out.println(this.name+"ʤ��������    "+k.name+"ʧ�ܣ�����");
		}
			
	}

}
