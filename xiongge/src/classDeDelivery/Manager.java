package classDeDelivery;


public class Manager {

	public static void main(String[] args) {
		UNStudent un = new UNStudent();
		un.age = 20;
		un.score = 10;
		un.sex = '男';
//		un.number = "2017090001";

		un.setName("张三");
		un.getName();
		un.study();
//		un.play();
		
		Teacher t = new Teacher();
		t.name = "张老师";
		
		
		Student stu = new Student();
		stu.setName("李四");
		
		t.teach(un);
		
		t.teach(stu);
	}

}
