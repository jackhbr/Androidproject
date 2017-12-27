package cn.netjava.rmi.share;

/**
 * 远程调用参数对象的实现类,实现IUser接口
 * 
 * @author 蓝杰 www.NetJava.cn
 */
public class UserImp implements cn.netjava.rmi.share.IUser {
	private int id;
	private String name;

	public UserImp(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return id + " - " + name + "\r\n";
	}
}