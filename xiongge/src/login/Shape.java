package login;


import java.awt.event.ActionListener;

/**
 * ����Shape�ӿ�
 * 
 * @author �ܸ�
 * 
 */
public interface Shape extends ActionListener { // ,MouseListener {

	// ���峣��
	public static final String NAME = "�ܸ�";

	// ������󷽷�
	public abstract void draw();

}
