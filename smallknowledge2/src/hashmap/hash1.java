package hashmap;

import java.util.HashMap;



public class hash1 {
	
	public static void main(String[] args) {
		//DIYHashMap<Integer,String> dhs=new DIYHashMap<Integer, String>(30, 0.75);  //�������ϣ���������ͻ�û�����
		HashMap<Integer, String> dhs=new HashMap<Integer, String>();//�����ϣ����ͬһ�������벻ֵͬʱ��������Ž�ȥ�Ľ��
		dhs.put(3, "three");
		dhs.put(3, "thr33");
		dhs.put(4, "four");
		String aString=dhs.get(3);
		System.out.println(aString);
	}


}
