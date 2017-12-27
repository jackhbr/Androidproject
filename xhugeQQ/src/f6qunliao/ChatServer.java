package f6qunliao;

import java.net.ServerSocket;

//启动服务器,主程序
public class ChatServer {
	// 主函数

	public static void main(String[] args) {
		ChatServer cs = new ChatServer();

		cs.setupServer(9090);
	}

	// 在指定的端口上启动服务器
	public void setupServer(int port) {
		try {
			ServerSocket sc = new ServerSocket(port);
			System.out.println("服务器创建成功:" + port);
			while (true) {
				java.net.Socket client = sc.accept();// 等待连接进入
				System.out.println("进入了一个客户机连接:"
						+ client.getRemoteSocketAddress().toString());
				// 启动一个处理线程，去处理这个连接对象
				ServerThread ct = new ServerThread(client);
				ct.start();
			}
		} catch (Exception ef) {
			ef.printStackTrace();
		}
	}
}
