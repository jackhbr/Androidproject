package f2;

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
		while(true){ //�÷�����ѭ���ȴ�
		Socket client=server.accept();
		//�����Ӷ����ϵõ��������������
		java.io.OutputStream out= client.getOutputStream();
		java.io.InputStream ins= client.getInputStream();
		String s="���,��ӭ��javaKe.com\r\n";
		byte[] data=s.getBytes();//ȡ���������ַ������ֽ�
		out.write(data); //���������������
		out.flush();//ǿ�����
		int in=0; //һ���ֽ�һ���ֽڵĶ�ȡ�ͻ���������
		while(in!=13){//�����������13,���س��ַ�
		in=ins.read();
		char a=(char) in;  //��仰��ascii��ת��Ϊ���������ַ�
		System.out.println("������һ����: "+a);
		}
		System.out.println("�ͻ������˻س�,�˳�:"+in);
		client.close();//���������ͻ���������
		}
		}catch(Exception ef){ef.printStackTrace();}
		}
//������
public static void main(String[] args) {
		ChatServer cs=new ChatServer();
		cs.setUpServer(9090);
}
}