package f6qunliao;

import java.util.HashMap;

/**
 * 系统数据访问操作实现类 账号验证、 数据保存、 更改等功能实现
 * 
 * @author 蓝杰 胡东峰
 */
public class DaoTools {
	/**
	 * 查看这个用户是否可以登录成功！
	 * 
	 * @param user
	 *            :要检查的用户对象
	 * @return:是否登录成功
	 */
	public static boolean checkLogin(UserInfo user) {
		// 在此只验证用户名是否存在
		if (userDB.containsKey(user.getName())) {
			return true;
		}
		System.out.println("认证失败！ : " + user.getName());
		return false;
	}

	// 内存用户信息数据库
	private static java.util.Map<String, UserInfo> userDB = new HashMap();
	// 静态块:模拟生成内存中的用户数据,用户名为user1~10
	// 当程序启动时，这段代码（静态块中的）会自动执行，向userDB中存放数据
	static {
		for (int i = 0; i < 10; i++) {
			UserInfo user = new UserInfo();
			user.setName("user" + i);
			user.setPwd("pwd" + i);
			userDB.put(user.getName(), user);
		}
	}
}
