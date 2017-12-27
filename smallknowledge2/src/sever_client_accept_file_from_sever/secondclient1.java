package sever_client_accept_file_from_sever;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;

public class secondclient1 implements Runnable{

	static Socket soc;
	InputStream ins;
	DataInputStream dis;
	byte b[];
	
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		 soc=new Socket("localhost",9090);
		 secondclient1 fc=new secondclient1();
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
			int filenamelength=dis.readInt();
			byte[] filenamebyte=new byte[filenamelength];
			dis.readFully(filenamebyte);//把剩下的字节数据全放在filenamebyte这个数组里
		    String filename=new String(filenamebyte);//应该用string的构造方法来把byte数组变换为字符串！！！
		    System.out.println("传过来的文件的名字是"+filename);
		
			int filelength=dis.readInt();
			b=new byte[filelength];
			dis.readFully(b);
			JFileChooser jfc=new JFileChooser();
			
			jfc.setCurrentDirectory(new File("g:"));//这个可以设置文件默认的打开路径	
			jfc.setSelectedFile(new File(filename));//这个直接可以设置默认的文件名了
			jfc.setDialogTitle("文件另存为");//设置窗体的title
			jfc.showSaveDialog(null);//注意！！！对jfilechoose的设置都要在这之前设置，不然都会无效
		
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
