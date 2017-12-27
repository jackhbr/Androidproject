package classDeDelivery;

public class Teacher {

	public String name;
	
	public void teach(Student stu){
		System.out.println(name+"正在教"+stu.getName()+"学习英语中！");
		stu.study();
	}
	
}
