package f1;

import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

/**
* �򵥷�����ʵ��
* @author ���� ������
*/
public class ChatServer {
/**
* ��ָ���˿�������һ��������
* @param port:���������ԵĶ˿�
*/
public void setUpServer(int port){
		try{
		//��������ָ���˿��ϵķ���������
		ServerSocket server=new ServerSocket(port);
		System.out.println("�����������ɹ��� "+port);
		//�÷���������ȴ�״̬:����״̬
		Socket client=server.accept();
		//�����Ӷ����ϵõ��������������
		java.io.OutputStream out=client.getOutputStream();
		java.io.InputStream ins=client.getInputStream();
		String s="���,��ӭ��javaKe.com\r\n";
		byte[] data=s.getBytes();//ȡ���������ַ������ֽ�
		out.write(data); //��������������ݣ�
		out.flush();//ǿ�����
		client.close();//�����ͻ���������
		}catch(Exception ef){
		ef.printStackTrace();
		}
}
//������
public static void main(String[] args) {
		ChatServer cs=new ChatServer();
		cs.setUpServer(9090);
}
}