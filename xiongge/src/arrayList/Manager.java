package arrayList;


import java.util.Random;

public class Manager {

	public static void main(String[] args) {

		MyArrayList<String> list = new MyArrayList<String>();

		Random rand = new Random();
		int size = rand.nextInt(50);

		for (int i = 0; i < size; i++) {
			String str = "����" + ((char) (97 + i));
			list.add(str);
		}
		System.out.println("��������е�����������" + list.size());
		for (int i = 0; i < list.size(); i++) {
			String str = (String) list.get(i);
			System.out.println(str);
		}

		String str = (String) list.remove(20);
		System.out.println("�Ƴ�����λ20��Ԫ��" + str);

//		list.add(10);

		System.out.println("��������е�����������" + list.size());
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			System.out.println(obj);
		}
	}

}
