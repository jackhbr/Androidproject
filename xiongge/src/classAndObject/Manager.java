package classAndObject;


public class Manager {

	public static void main(String[] args) {
		// ʵ����Studentѧ����Ķ���
		Student stu = new Student();
		// ����������Ը�ֵ
		stu.name = "����";
		stu.score = 0;
		// ʵ����Subject�γ�����Ķ���
		Subject sub = new Subject();
		sub.nae = "Java";
		sub.time = 10;
		sub.score = 3;
		
		//���÷���
		while(sub.time >0){
				stu.study(sub);
				
		}

	}

}
