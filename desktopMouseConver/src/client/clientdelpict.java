package client;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class clientdelpict extends JFrame implements Runnable {

	private OutputStream ops;
	private InputStream ins;
	DataInputStream di;
	byte b[];
	Socket so;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		clientdelpict cl = new clientdelpict();
		cl.showUI();
		Thread th = new Thread(cl);
		th.start();

	}

	public void showUI() {
		Dimension im = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(im);
		this.setVisible(true);

	}

	@Override
	public void run() {

		try {
			so = new Socket("localhost", 9090);
			ops = so.getOutputStream();
			ins = so.getInputStream();
			di = new DataInputStream(ins);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (true) {
				int flag =di.readInt();
				if (flag == 2) {
					System.out.println("flag="+flag);
					System.out.println("��������һ��ͼƬ");
					int length = di.readInt();
					System.out.println("����������ĳ���Ϊ"+length);
					b = new byte[length];
						di.readFully(b);  //���ܡ��á�forѭ�����棬��Ȼ���п�ָ���������������fully����
					}
				
				
				Image kImage=bytesToImage(b);
				this.getGraphics().drawImage(kImage, 0, 0, null);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

	/**
	 * ת��byte����ΪImage
	 * 
	 * @param bytes
	 * @return Image
	 */
	public static Image bytesToImage(byte[] bytes) {
		Image image = Toolkit.getDefaultToolkit().createImage(bytes);
		try {
			MediaTracker mt = new MediaTracker(new Label());
			mt.addImage(image, 0);
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return image;
	}

}
