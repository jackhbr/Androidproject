package classDeDelivery;

public class Teacher {

	public String name;
	
	public void teach(Student stu){
		System.out.println(name+"���ڽ�"+stu.getName()+"ѧϰӢ���У�");
		stu.study();
	}
	
}
