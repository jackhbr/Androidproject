package classAndObject;


public class Student {

	public String name;
	public int score;

	/**
	 * ѧϰ�γ̵ķ���
	 */
	public void study(Subject sub) {
		sub.time--;
		System.out.println(name + "��ѧϰ�У�ʣ���Ѫ���ǣ�" + sub.time);
		if (sub.time <= 0) {
			score += sub.score;
			System.out.println(name + "�Ѿ�ѧ��" + sub.time+"�γ̣�"+name+"���ڵ�ѧ����"+score);
		}
	}

}
