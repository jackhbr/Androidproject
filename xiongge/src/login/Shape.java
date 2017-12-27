package login;


import java.awt.event.ActionListener;

/**
 * 定义Shape接口
 * 
 * @author 熊哥
 * 
 */
public interface Shape extends ActionListener { // ,MouseListener {

	// 定义常量
	public static final String NAME = "熊哥";

	// 定义抽象方法
	public abstract void draw();

}
