package cn.netjava.rmi.share;

/**
 * RMIԶ�̵���ʱ,��������������
 * 
 * @author ���� www.NetJava.cn
 */
public interface IUser extends java.io.Serializable {
	public int getId(); // ȡ��id

	public String getName(); // ȡ���û���

	public String toString(); // ���ڵ���,�������Դ�
}
