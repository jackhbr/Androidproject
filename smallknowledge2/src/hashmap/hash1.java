package hashmap;

import java.util.HashMap;



public class hash1 {
	
	public static void main(String[] args) {
		//DIYHashMap<Integer,String> dhs=new DIYHashMap<Integer, String>(30, 0.75);  //而这个哈希表这样做就会没有输出
		HashMap<Integer, String> dhs=new HashMap<Integer, String>();//这个哈希表当在同一个键放入不同值时会输出最后放进去的结果
		dhs.put(3, "three");
		dhs.put(3, "thr33");
		dhs.put(4, "four");
		String aString=dhs.get(3);
		System.out.println(aString);
	}


}
