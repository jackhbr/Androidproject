package cn.netjava.rmi.share;

/**
 * RMI远程调用时,方法参数的类型
 * 
 * @author 蓝杰 www.NetJava.cn
 */
public interface IUser extends java.io.Serializable {
	public int getId(); // 取得id

	public String getName(); // 取得用户名

	public String toString(); // 用于调试,返回属性串
}
