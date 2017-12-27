import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class sever {
	
	public ServerSocket sev;
	public void creatSever() throws IOException
	{
        sev=new ServerSocket(8080);
        Socket kSocket=sev.accept();
        InputStream iStream=kSocket.getInputStream();
        OutputStream oStream=kSocket.getOutputStream();
        String string="hello";
        byte b[]=string.getBytes();
     //   byte v[]={'a','c'};
        oStream.write(b);
         int a= iStream.read();
         
         String ss="hello"+a;
         byte aa[]=ss.getBytes();
         oStream.write(aa);
	}
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		sever sever=new sever();
		sever.creatSever();

	}

}
