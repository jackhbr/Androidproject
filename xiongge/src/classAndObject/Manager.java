package classAndObject;


public class Manager {

	public static void main(String[] args) {
		// 实例化Student学生类的对象
		Student stu = new Student();
		// 给对象的属性赋值
		stu.name = "张三";
		stu.score = 0;
		// 实例化Subject课程类类的对象
		Subject sub = new Subject();
		sub.nae = "Java";
		sub.time = 10;
		sub.score = 3;
		
		//调用方法
		while(sub.time >0){
				stu.study(sub);
				
		}

	}

}
