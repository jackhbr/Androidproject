package cn.netjava.rmi.share;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI���ö���ӿڶ���
 * 
 * @author www.NetJava.cn
 */
public interface IRMIService extends Remote {
	/**
	 * Զ�̷������������ʵ�ֵķ��� ���е�Զ�̵��õķ���, ��������throws RemoteException
	 */
	Object service(Object obj) throws RemoteException;
}