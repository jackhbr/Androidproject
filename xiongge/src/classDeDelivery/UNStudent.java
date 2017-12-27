package classDeDelivery;


/**
 * 定义UNStudent大学生类，该类继承自Student学生类
 * 
 * @author 熊哥
 * 
 */
public class UNStudent extends Student {

	public String number;// 公有的学号属性

	/**
	 * 定义玩的方法
	 */
	public void play() {
		System.out.println(getName() + "在玩游戏中！");
	}

	// 重写父类中的学习方法
	public void study() {
		System.out.println(getName() + "是大学生，学习方式不同啦！");
	}

}
