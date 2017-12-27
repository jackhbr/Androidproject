package exploreruse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		new Webserver().con();
	}

	private void con() throws IOException {
		ServerSocket serverSocket = new ServerSocket(9090);
		while (true) {
			Socket client = serverSocket.accept();
			OutputStream ops = client.getOutputStream();
			BufferedReader buff = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			String s = buff.readLine();
     System.out.println("进入了服务器");
			if (s.indexOf("timg1.jpg") >= 0) {
				System.out.println("含有图片");
				java.io.FileInputStream fis = new FileInputStream("timg1.jpg");
				byte[] idata = new byte[fis.available()];
				fis.read(idata);
				ops.write(idata);
				ops.flush();
			} else {
				System.out.println("不含有图片");
				String msgString = "<html><body><img src='timg1.jpg'/><h1><a href=\"toc\">meinu</a></h1>"
						+ "</body></html>";
				ops.write(msgString.getBytes());
				ops.flush();
			}
			client.close();

		}
	}

}
