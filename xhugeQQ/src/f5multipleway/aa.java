package f5multipleway;

public class aa {
	/**
	* ��ָ���˿�������һ��������
	* @param port:���������ԵĶ˿�
	*/
	public void setUpServer(int port){
		try{
			//1.��������ָ���˿��ϵķ���������
			java.net.ServerSocket server=new java.net.ServerSocket(port);
			System.out.println("�����������ɹ��� "+port);
			while(true){ //�÷���������ѭ���ȴ�״̬
			java.net.Socket client=server.accept();
			//������һ���̶߳���ʱ������������
			ServerThread st=new ServerThread(client);
			st.start();//��������߳�,ȥ��������
			System.out.println("��������һ���߳�ȥ����������Ӷ����� ");
			}
			}catch(Exception ef){
			ef.printStackTrace();
			} }
	public static void main(String[] args) {
		aa kAa=new aa();
		kAa.setUpServer(9090);
	}

}
