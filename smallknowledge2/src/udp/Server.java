package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

	public static void main(String[] args) throws IOException {		
			String str = "helloword";
//			String host = "localhost";
			InetAddress adress = InetAddress.getByName("localhost");
			DatagramSocket ds = new DatagramSocket();
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(),adress,2000);
			dp.setPort(2000);
			ds.send(dp);
			System.out.println("·¢ËÍUDP°ü");
			ds.close();
	}

}
