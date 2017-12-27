package classDeDelivery;


/**
 * 定义学生类
 * 
 * @author 熊哥
 * 
 */
public class Student {

	public int score;// 公有的学分属性
	protected char sex;// 受保护的性别属性
	int age;// 默认的年龄属性
	private String name;// 私有的姓名属性

	/** 注意如果属性的访问修饰符不是public，则一定要定义set和get方法 */
	// 定义设置姓名属性值的方法
	public void setName(String n) {
		name = n;
	}

	// 定义获取姓名属性值的方法
	public String getName() {
		return name;
	}

	// 定义学习的方法
	public void study() {
		score++;
		System.out.println(name + "正在学习中，学分是" + score);
	}

}
