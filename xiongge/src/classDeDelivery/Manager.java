package classDeDelivery;


public class Manager {

	public static void main(String[] args) {
		UNStudent un = new UNStudent();
		un.age = 20;
		un.score = 10;
		un.sex = '��';
//		un.number = "2017090001";

		un.setName("����");
		un.getName();
		un.study();
//		un.play();
		
		Teacher t = new Teacher();
		t.name = "����ʦ";
		
		
		Student stu = new Student();
		stu.setName("����");
		
		t.teach(un);
		
		t.teach(stu);
	}

}
