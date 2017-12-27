package f5multipleway;

public class aa {
	/**
	* 在指定端口上启动一个服务器
	* @param port:服务器所以的端口
	*/
	public void setUpServer(int port){
		try{
			//1.建立绑定在指定端口上的服务器对象
			java.net.ServerSocket server=new java.net.ServerSocket(port);
			System.out.println("服务器创建成功！ "+port);
			while(true){ //让服务器进处循环等待状态
			java.net.Socket client=server.accept();
			//创建了一个线程对象时传入进入的连接
			ServerThread st=new ServerThread(client);
			st.start();//启动这个线程,去处理连接
			System.out.println("已启动了一个线程去处理这个连接对象了 ");
			}
			}catch(Exception ef){
			ef.printStackTrace();
			} }
	public static void main(String[] args) {
		aa kAa=new aa();
		kAa.setUpServer(9090);
	}

}
