package cn.netjava.rmi.share;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRMIService extends Remote {
	/**
	 * Զ�̷������������ʵ�ֵķ��� ���е�Զ�̵��õķ���, ��������throws RemoteException
	 */
	Object service(Object obj) throws RemoteException;

	// ȡ�������û������б�
	List<IUser> getAllUser() throws RemoteException;
}