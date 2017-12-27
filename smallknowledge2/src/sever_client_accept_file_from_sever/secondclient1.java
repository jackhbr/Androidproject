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
		
		System.out.println("�ͻ��˽��ܵ���Ϣ��flag="+flag);
		if(flag==1)
		{
			int filenamelength=dis.readInt();
			byte[] filenamebyte=new byte[filenamelength];
			dis.readFully(filenamebyte);//��ʣ�µ��ֽ�����ȫ����filenamebyte���������
		    String filename=new String(filenamebyte);//Ӧ����string�Ĺ��췽������byte����任Ϊ�ַ���������
		    System.out.println("���������ļ���������"+filename);
		
			int filelength=dis.readInt();
			b=new byte[filelength];
			dis.readFully(b);
			JFileChooser jfc=new JFileChooser();
			
			jfc.setCurrentDirectory(new File("g:"));//������������ļ�Ĭ�ϵĴ�·��	
			jfc.setSelectedFile(new File(filename));//���ֱ�ӿ�������Ĭ�ϵ��ļ�����
			jfc.setDialogTitle("�ļ����Ϊ");//���ô����title
			jfc.showSaveDialog(null);//ע�⣡������jfilechoose�����ö�Ҫ����֮ǰ���ã���Ȼ������Ч
		
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
