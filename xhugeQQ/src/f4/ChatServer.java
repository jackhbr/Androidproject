package f4;

import java.io.InputStream;
import java.io.OutputStream;

/**
* ��Echo������ʵ��
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
			java.net.ServerSocket server=new java.net.ServerSocket(port);
			System.out.println("�����������ɹ��� "+port);
			//���пͻ���������ʱ���ȴ������ͻ᷵�أ�����һ��������ͻ������ӵĶ���
			while(true){ //�÷���������ѭ���ȴ�״̬
			java.net.Socket client=server.accept();
			System.out.println("Incoming clieng:"+client.getRemoteSocketAddress());  //��ȡ�ͻ��˵ĵ�ַ�Ͷ˿ں�
			
			//���ô������Ӷ���ķ���ȥ�������Ӷ���
			processChat(client);
	}
	}catch(Exception ef){
	ef.printStackTrace();
	}
	}
/**
* �������Ӷ��󣺶�ȡ�ͻ����������ַ��������͸��ͻ���
* @param client:��ͻ��������Ӷ���
*/
private void processChat(java.net.Socket client) throws Exception{
		OutputStream out=client.getOutputStream();
		InputStream ins=client.getInputStream();
		String s="��ã���ӭ������������ \r\n";
		byte[] data=s.getBytes();//ȡ���������ַ������ֽ�
		out.write(data); //����������ͣ�
		out.flush();//ǿ�����
		//���ö�ȡ�ַ����ķ��������������ж�ȡһ���ַ���
		String inputS=readString(ins);      //��Ȼ��д��һ��������������������bufferreadder��readline��
		while(!inputS.equals("bye")){       //ע��������ַ���������equals��������==
		System.out.println("�ͻ���˵: "+inputS);
		s="�������յ�:"+inputS+"\r\n";
		data=s.getBytes();//ȡ���������ַ������ֽ�����
		out.write(data); //������������ͣ�
		out.flush();//ǿ�����
		inputS=readString(ins); //��ȡ�ͻ�������һ������
		}
		s="��ã���ӭ������ \r\n";
		data=s.getBytes();
		out.write(data);
		out.flush();
		client.close();//�����ͻ���������
}
/**
* �������������ж�ȡ�ֽڣ�ƴ��һ���ַ�������
* �������һ���ֽ�ֵΪ13,����Ϊ��ǰ����һ���ַ���
* @param ins:����������
* @return :�����ϣ��ͻ���������)�������ַ���
*/
private String readString(InputStream ins)throws Exception{
//����һ���ַ���������
		StringBuffer stb=new StringBuffer();
		char c =0;
		while(c!=13){
		//����һ�����У�����һ�仰
		int i= ins.read();//��ȡ�ͻ���������һ���ֽ�
		c=(char)i;//��������ֽ�ת��Ϊһ��Char
		stb.append(c);//��������һ���ַ��ӵ��ַ�����������
		}
		//���������ֽ���תΪ�ַ���,������trimȥ��β���Ŀո�
		String inputS=stb.toString().trim();   //���û�нӴ������о��б�Ҫ����һ�£��������Ƿ�ֹ���ں�����Ͽո񣬵�����ʾ�úܲ��ÿ�
		//String inputS=stb.toString();
		return inputS;
}
//�������
	public static void main(String[] args) {
		ChatServer cs=new ChatServer();
		cs.setUpServer(9090);
	}
}