package protocol2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Chat {
	ArrayList<Socket> jk=new ArrayList<Socket>();
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Chat chat =new Chat();
		chat.setupSever(9090);
	}
    public void setupSever(int port) throws IOException{
    	//��������ָ���˿��ϵķ���������
    	ServerSocket server=new ServerSocket(port);
    	System.out.println("�����������ɹ���"+port);
    	 while(true){ //�÷�����ѭ���ȴ�
    	  Socket client=server.accept();
    	  jk.add(client);
    	  ChatThread0923 ct=new ChatThread0923(client,jk);
    	  ct.start();
    	 }
         
    }
}
