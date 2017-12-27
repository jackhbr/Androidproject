package f5multipleway;

import java.io.InputStream;
import java.io.OutputStream;

import f4.ChatServer;

/**
* �������˴�����ͻ������Ӷ�����߳����װ
* @author ���� ������
*/
public class ServerThread extends Thread{
private java.net.Socket client;//�̶߳���Ҫ��������ӱ���
private OutputStream out;//���������
/**��������̶߳���ʱ������һ����Ҫ��������Ӷ���*/
public ServerThread(java.net.Socket sc){
		this.client=sc;
		}
//��������Ϣ�Ĵ����װ��һ��������
public void sendMsg2Me(String msg)throws Exception{
		byte[] data=msg.getBytes();
		out.write(data); //����������ͣ�
		out.flush();//ǿ�����
}
public void run(){
	//���߳�run�е��ô������ӵķ���
	processChat(this.client);
	//������ִ����Ϻ��߳����Ѽ��˳�...
}
//����ͻ�����������Ӷ���
private void processChat(java.net.Socket client){
try{
	out=client.getOutputStream(); //�õ�һ�����/����������
	InputStream ins=client.getInputStream();
	String s="��ã���ӭ������������ \r\n";
	this.sendMsg2Me(s);//������Ϣ
	String inputS=readString(ins);//��ȡ�ͻ���������
	while(!inputS.equals("bye")){
	s="���յ�����Ļ��� "+inputS+"\r\n";
	this.sendMsg2Me(s); //������ַ������͸��ͻ�������
	inputS=readString(ins);//�ٴζ�ȡ
	}
	s="��ã���ӭ������ \r\n";
	this.sendMsg2Me(s);
	client.close();
	}catch(Exception ef){
	ef.printStackTrace();
	}
	}
	/**
	* �������������ж�ȡ�ֽڣ�ƴ��һ���ַ�������
	* �������һ���ֽ�ֵΪ13,����Ϊ��ǰ����һ���ַ���
	* @param ins:����������
	* @return :�����ϣ��ͻ���������)�������ַ���
	*/
	private String readString(InputStream ins) throws Exception{
		StringBuffer stb=new StringBuffer();//����һ���ַ���������
		char c =0;
		while(c!=13){
		//����һ�����У�����һ�仰
		int i= ins.read();//��ȡ�ͻ���������һ���ֽ�
		c=(char)i;//��������ֽ�ת��Ϊһ��Char
		stb.append(c);
		}
		//���������ֽ���תΪ�ַ���
		String inputS=stb.toString().trim();
		return inputS;
	}
	


}