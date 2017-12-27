package sever_client_accept_file_from_sever;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JScrollBar;

public class firstclient implements Runnable{

	static Socket soc;
	InputStream ins;
	DataInputStream dis;
	byte b[];
	
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		 soc=new Socket("localhost",9090);
		 firstclient fc=new firstclient();
		 Thread th=new Thread(fc);
		 th.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			ins=soc.getInputStream();
			dis=new DataInputStream(ins);
		flag=dis.readInt();
		
		System.out.println("客户端接受到信息，flag="+flag);
		if(flag==1)
		{
			int filelength=dis.readInt();
			b=new byte[filelength];
			dis.readFully(b);
			JFileChooser jfc=new JFileChooser();
			jfc.showSaveDialog(null);
			File f=jfc.getSelectedFile();
			String filepath=f.getAbsolutePath();
			FileOutputStream fos=new FileOutputStream(filepath);
			DataOutputStream dos=new DataOutputStream(fos);
			dos.write(b);
			dos.flush();
			dos.close();
			
		}
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
