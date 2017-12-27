package arraylist;


import java.util.Random;

public class Manager {

	public static void main(String[] args) {

		//下面这里就相当于新建一个数组了，现在只不过是新建一个封装好的数组，操作只能通过这个对象进行改变
		MyArrayList<String> list = new MyArrayList<String>();

		Random rand = new Random();
		int size = rand.nextInt(30);
		
		list.add("姓名b");
		for (int i = 0; i < size; i++) {
			String str = "姓名" + ((char) (97 + i));
			list.add(str); //往这个队列数组里添加元素
		}
		list.add("姓名b");
		System.out.println("数组队列中的数据总数是" + list.size());
		for (int i = 0; i < list.size(); i++) {
			String str = (String) list.get(i); //从这个队列数组里获取元素，注意前面的强制转换
			System.out.println(str);
		}

//		String str = (String) list.remove(20);//从这个队列数组里移除元素，注意前面的强制转换，这是由原函数原型决定的
//		System.out.println("移除索引位20的元素" + str);
//		boolean k=list.add(5 ,"88");
//		System.out.println("添加88这个元素"+k);
//		list.add(10);
		boolean k=list.update("姓名b","jack");   //为啥我的第二个"姓名b"就是更新不了啊！！！？？？remove又是可以全部remove的
		System.out.println("更新"+k);
		

		System.out.println("数组队列中的数据总数是" + list.size());
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			System.out.println(obj);
		}
	}

}
