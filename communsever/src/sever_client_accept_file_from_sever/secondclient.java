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
		
		System.out.println("�ͻ��˽��ܵ���Ϣ��flag="+flag);
		if(flag==1)
		{
			int filenamelength=dis.readInt();
			byte[] filenamebyte=new byte[filenamelength];
			dis.readFully(filenamebyte);//��ʣ�µ��ֽ�����ȫ����filenamebyte���������
		    String filename=filenamebyte.toString();
		    System.out.println("���������ļ���������"+filename);
			
			
			int filelength=dis.readInt();
			b=new byte[filelength];
			dis.readFully(b);
			JFileChooser jfc=new JFileChooser();
//			
//			JTextField text;
//			text=getTextField(jfc);
//			text.setText("˵�õ�ǰ"); //��������Ĭ�ϵ��ļ�����,�������ﻹҪ����һ���������������API���Ŀ�
			jfc.setCurrentDirectory(new File("g:"));//������������ļ�Ĭ�ϵĴ�·��
			
			jfc.setSelectedFile(new File("jack.jpg"));//���ֱ�ӿ�������Ĭ�ϵ��ļ�����
			
			jfc.setDialogTitle("��ð�����");//���ô����title
			
		//	jfc.setName("asddasds"); û��������
		//	jfc.setApproveButtonText("asd��");  û��������
			
			//JButton  aaButton=new JButton("asddsd");
			jfc.showSaveDialog(null);//ע�⣡������jfilechoose�����ö�Ҫ����֮ǰ���ã���Ȼ������Ч
			
			
			File f=jfc.getSelectedFile();
			
			//jfc.showDialog(jfc,"�����ļ�");//�������Ķ����������ñ��水ť���ַ���
		//	jfc.setName(filename);û���á���
			//jfc.setApproveButtonText("asd��");
			//jfc.setDialogTitle("��ð�����");
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
