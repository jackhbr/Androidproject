package udp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Client {

	public static void main(String[] args) throws IOException {
		
		byte[] by = new byte[20];
		
		 DatagramSocket ds2 = new DatagramSocket(2000);
		 
		 while(true){
			 DatagramPacket dp2 = new DatagramPacket(by, by.length);
			 ds2.receive(dp2);
			 String msg = new String(dp2.getData(), 0, dp2.getLength());
			 System.out.println(213);
			 System.out.println(msg);
		 }
	

	}

}
