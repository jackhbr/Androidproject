package arraylist;


import java.util.Random;

public class Manager {

	public static void main(String[] args) {

		//����������൱���½�һ�������ˣ�����ֻ�������½�һ����װ�õ����飬����ֻ��ͨ�����������иı�
		MyArrayList<String> list = new MyArrayList<String>();

		Random rand = new Random();
		int size = rand.nextInt(30);
		
		list.add("����b");
		for (int i = 0; i < size; i++) {
			String str = "����" + ((char) (97 + i));
			list.add(str); //������������������Ԫ��
		}
		list.add("����b");
		System.out.println("��������е�����������" + list.size());
		for (int i = 0; i < list.size(); i++) {
			String str = (String) list.get(i); //����������������ȡԪ�أ�ע��ǰ���ǿ��ת��
			System.out.println(str);
		}

//		String str = (String) list.remove(20);//����������������Ƴ�Ԫ�أ�ע��ǰ���ǿ��ת����������ԭ����ԭ�;�����
//		System.out.println("�Ƴ�����λ20��Ԫ��" + str);
//		boolean k=list.add(5 ,"88");
//		System.out.println("���88���Ԫ��"+k);
//		list.add(10);
		boolean k=list.update("����b","jack");   //Ϊɶ�ҵĵڶ���"����b"���Ǹ��²��˰�������������remove���ǿ���ȫ��remove��
		System.out.println("����"+k);
		

		System.out.println("��������е�����������" + list.size());
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			System.out.println(obj);
		}
	}

}
