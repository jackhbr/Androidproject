package classDeDelivery;


/**
 * ����UNStudent��ѧ���࣬����̳���Studentѧ����
 * 
 * @author �ܸ�
 * 
 */
public class UNStudent extends Student {

	public String number;// ���е�ѧ������

	/**
	 * ������ķ���
	 */
	public void play() {
		System.out.println(getName() + "������Ϸ�У�");
	}

	// ��д�����е�ѧϰ����
	public void study() {
		System.out.println(getName() + "�Ǵ�ѧ����ѧϰ��ʽ��ͬ����");
	}

}
