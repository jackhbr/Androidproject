package sever_client_accept_file_from_sever;

import java.awt.Component;
import java.awt.Container;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class secondclient implements Runnable{

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
		    String filename=filenamebyte.toString();
		    System.out.println("传过来的文件的名字是"+filename);
			
			
			int filelength=dis.readInt();
			b=new byte[filelength];
			dis.readFully(b);
			JFileChooser jfc=new JFileChooser();
//			
//			JTextField text;
//			text=getTextField(jfc);
//			text.setText("说好的前"); //可以设置默认的文件名了,不过这里还要调用一个函数，不如调用API来的快
			jfc.setCurrentDirectory(new File("g:"));//这个可以设置文件默认的打开路径
			
			jfc.setSelectedFile(new File("jack.jpg"));//这个直接可以设置默认的文件名了
			
			jfc.setDialogTitle("你好啊，哥");//设置窗体的title
			
		//	jfc.setName("asddasds"); 没看出有用
		//	jfc.setApproveButtonText("asd这");  没看出有用
			
			//JButton  aaButton=new JButton("asddsd");
			jfc.showSaveDialog(null);//注意！！！对jfilechoose的设置都要在这之前设置，不然都会无效
			
			
			File f=jfc.getSelectedFile();
			
			//jfc.showDialog(jfc,"保存文件");//这个后面的东西可以设置保存按钮的字符串
		//	jfc.setName(filename);没有用。。
			//jfc.setApproveButtonText("asd这");
			//jfc.setDialogTitle("你好啊，哥");
			//jfc.setSelectedFile(new File("jack.jpg"));
			
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
	
//	public JTextField getTextField(Container c) {
//        JTextField textField = null;
//        for (int i = 0; i < c.getComponentCount(); i++) {
//            Component cnt = c.getComponent(i);
//            if (cnt instanceof JTextField) {
//                return (JTextField) cnt;
//            }
//            if (cnt instanceof Container) {
//                textField = getTextField((Container) cnt);
//                if (textField != null) {
//                    return textField;
//                }
//            }
//        }
//        return textField;
//    }

}
