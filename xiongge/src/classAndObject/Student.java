package classAndObject;


public class Student {

	public String name;
	public int score;

	/**
	 * 学习课程的方法
	 */
	public void study(Subject sub) {
		sub.time--;
		System.out.println(name + "在学习中，剩余的血量是：" + sub.time);
		if (sub.time <= 0) {
			score += sub.score;
			System.out.println(name + "已经学完" + sub.time+"课程，"+name+"现在的学分是"+score);
		}
	}

}
