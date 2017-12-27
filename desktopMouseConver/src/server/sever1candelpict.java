package server;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class sever1candelpict extends JFrame implements Runnable {

	BufferedImage bu;
	ServerSocket sev;
	private OutputStream ops;
	Socket client;
	int num;
	DataOutputStream da;
	

	@Override
	public void run() {
		
	   try {
		client=sev.accept();
		System.out.println("客户端接入成功");
		ops=client.getOutputStream();
		 da=new DataOutputStream(ops);
		java.io.InputStream ins = client.getInputStream();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		while (true) {
			try {
				bu = CreatImage();
				byte picture[]=imageToBytes(bu, "jpg");//截屏的后缀名是什么都可以
				//1是文本 2是图片
				da.writeInt(2);
				 num=picture.length;
				da.writeInt(num);
				ops.write(picture);
				
				
				
//				String file="d:\\a\\"+num+".jpg";
//				num++;
//				FileOutputStream  fo=new FileOutputStream(file);
//				fo.write(picture);
//				fo.flush();
//				fo.close();
//				System.out.println("kkk");
				
				

			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		sever1candelpict sever = new sever1candelpict();
		sever.showUI();
		Thread th = new Thread(sever);
		th.start();

	}

	public void showUI() throws IOException {
		 sev=new ServerSocket(9090);

	}

	public BufferedImage CreatImage() throws AWTException {
		Dimension im = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle aRectangle = new Rectangle(im);
		Robot aRobot = new Robot();
		BufferedImage ab = aRobot.createScreenCapture(aRectangle);
		return ab;
	}

	
	/**
	* 转换BufferedImage 数据为byte数组
	* 
	* @param image
	* Image对象
	* @param format
	* image格式字符串.如"gif","png"
	* @return byte数组
	*/
	
	public static byte[] imageToBytes(BufferedImage bImage, String format) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, format, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

}
