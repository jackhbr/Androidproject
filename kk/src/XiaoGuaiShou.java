
public class XiaoGuaiShou {
	public String name;
	public int fightPower;
	public int HP;
	
	public void PK(AoTeMan k){
		k.HP-=fightPower;
		System.out.println("С����"+name+"���������һ�Σ���������ʣ������ֵΪ"+k.HP);
		if(k.HP<=0)
		{
			System.out.println("С����ʤ��������������ʧ�ܣ�����");
		}
			
	}

}
