package classDeDelivery;


/**
 * ����ѧ����
 * 
 * @author �ܸ�
 * 
 */
public class Student {

	public int score;// ���е�ѧ������
	protected char sex;// �ܱ������Ա�����
	int age;// Ĭ�ϵ���������
	private String name;// ˽�е���������

	/** ע��������Եķ������η�����public����һ��Ҫ����set��get���� */
	// ����������������ֵ�ķ���
	public void setName(String n) {
		name = n;
	}

	// �����ȡ��������ֵ�ķ���
	public String getName() {
		return name;
	}

	// ����ѧϰ�ķ���
	public void study() {
		score++;
		System.out.println(name + "����ѧϰ�У�ѧ����" + score);
	}

}
