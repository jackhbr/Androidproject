package weirui;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class ServerImage implements Runnable{
	private Socket Client;
	private static JFrame jf;
	
	public ServerImage(Socket Client) {
		this.Client = Client;
	}

	@Override
	public void run() {
		ImageIcon image = null;
		byte[] imagebyte;
		BufferedImage bi;
		//�ͻ��� �׽���
		try {
			
			System.out.println("�߳�����");
			BufferedReader br = new BufferedReader(new InputStreamReader(Client.getInputStream()));
			DataOutputStream dos = new DataOutputStream(Client.getOutputStream());
			String str = br.readLine();
			
			int imagelen = Integer.parseInt(str);
			System.out.println(imagelen);
//			
//			File f = new File("C:\\image");
//			
//			File[] filelist = f.listFiles();
//			
//			for (int i = 0; i < filelist.length; i++) {
//				System.out.println(filelist[i].getName());
//			}
			
			for (int i = 0; i <imagelen-1 ; i++) {
				
				org.json.JSONObject json = new org.json.JSONObject(br.readLine());
				
			/**
			 * try catch ��ѭ������
			 */
			
//				BufferedImage bfi = new BufferedImage(width, height, imageType)
				//imageתBufferimage
				try{
					
						bi = ImageIO.read(new File("C:\\image\\"+json.get("Account")+".jpg"));
						System.out.println("���ͱ���ͼƬ");
					}catch (Exception e) {
						
						bi = ImageIO.read(new File("C:\\image\\default.jpg"));
						System.out.println("����Ĭ��ͼƬ");
					}
				    dos.writeByte(0);
					imagebyte = imageToBytes(bi);

					dos.writeInt(imagebyte.length);
					
					dos.write(imagebyte);
					dos.flush();
					
					System.out.println("image���ͳɹ�");
				}
			
				} catch (Exception e){
					e.printStackTrace();
				}
		try {
			Client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	

	public byte[] imageToBytes(BufferedImage bImage) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		ImageIO.write(bImage,"jpg", out);
		} catch (IOException e) {
		e.printStackTrace();
		}
		return out.toByteArray();
	}
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
	public static void OpenImagePort() throws IOException{
		ExecutorService ImagePool = Executors.newFixedThreadPool(20);
		ServerSocket Service = new ServerSocket(10002);
		while(true){
			Socket Client =Service.accept();
			System.out.println("����һ���ͻ���");
			ImagePool.execute(new ServerImage(Client));
		}
	}
}