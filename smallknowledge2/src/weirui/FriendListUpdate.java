package weirui;

import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.json.JSONArray;
import org.json.JSONObject;


public class FriendListUpdate implements Runnable{
	
	private JSONArray jsarray = null;

	private static JScrollPane jsp;

	public FriendListUpdate(JScrollPane jsp){
		this.jsp = jsp;
	}
	@Override
	public void run() {
		
		while(true){
			UpdateInfo();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
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
	public void UpdateInfo(){
		
		JSONArray jsarray;
		ArrayList<Image> imagelist = new ArrayList<Image>();
		byte[] imagebyte;
		
		try {
			Socket ClientText = new Socket("127.0.0.1", 10000);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(ClientText.getInputStream()));
			OutputStream os = ClientText.getOutputStream();
			
			
			os.write(("{Account:"+UserInfo.getAccount()+",Password:"+UserInfo.getPassword()+"}"+"\r\n").getBytes());
			os.flush();
			
			
			System.out.println("�����˺�����");
			jsarray = new JSONArray(br.readLine());
			System.out.println(jsarray);
			System.out.println(jsarray.length());
			System.out.println(jsarray.get(0));
			System.out.println("�ͻ��˵õ�JSON");
			
				/**
				 *   ͼƬ�˿ڿ��� 
				 *   �õ��û�  ��  ����ͷ��
				 *   imagelist.get(1)Ϊ�û�ͷ��
				 *   �Ժ���Ǻ���ͼ��
				 *   
				 *   �õ�                  �û��������Ϣ��JSONarray  ��   �û�����ѵ�Imagelist �����ͻ��˺����б���
				 *   ���������б������
				 */
				
				Socket ClientImage = new Socket("127.0.0.1", 10002);
				DataInputStream idis = new DataInputStream(ClientImage.getInputStream());
				
				OutputStream ios = ClientImage.getOutputStream();
				ios.write((jsarray.length()+"\r\n").getBytes());
				ios.flush();
				
				for (int i = 1; i < jsarray.length(); i++) {
					org.json.JSONObject jsonimage = (org.json.JSONObject)jsarray.get(i);
					System.out.println("Account:"+jsonimage.get("Tel"));
					ios.write(("{Account:"+jsonimage.get("Tel")+"}"+"\r\n").getBytes());
					ios.flush();
					if(idis.readByte()==0){
						
						imagebyte = new byte[idis.readInt()];
						idis.readFully(imagebyte);
						imagelist.add(bytesToImage(imagebyte));
						
					}else{
							JOptionPane.showMessageDialog(null, "ͼƬ����ʧ��");
						}
				
				}
				jsp.setViewportView(new FriendList(imagelist,jsarray));
				
				/**
				 * ��¼������������߳�
				 */
    			
    			ClientText.close();
    			ClientImage.close();
    			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
}
