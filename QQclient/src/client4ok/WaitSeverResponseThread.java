package client4ok;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;



public class WaitSeverResponseThread implements Runnable{

	private DataInputStream dis;
	private JFrame jf;
	private ArrayList<Client> client=new ArrayList<Client>();
	JTextField sendmessage;//发送信息内容的全局变量
	
	public WaitSeverResponseThread(DataInputStream dis,JFrame jf) {
		this.dis = dis;
		this.jf=jf;
	}
	public void run() 
	{
		while(true)
		{
			try {
				//返回的应该是loginsuccess\r\n
				//然后发送QQ名，QQ头像，好友列表过来
				
		      //  d= new BufferedReader(new InputStreamReader(dis));
		        //这样才能正确的把字节转换成字符流的文本行输入	
				
				String response=dis.readUTF();
				System.out.println("服务器返回  "+response);
				if(response.equals("loginsuccess"))//如果登录成功，那么就显示另一个窗体
				{
					jf.dispose();
					
					Byte a=dis.readByte();
					System.out.println(a+"\r\n");
					
					String friendName=dis.readUTF();
					System.out.println("客户端收到的好友的名字是"+friendName);
				
					 a=dis.readByte();
					System.out.println(a+"\r\n");
					 a=dis.readByte();
						System.out.println(a+"\r\n");
						a=dis.readByte();
						System.out.println(a+"\r\n");
						a=dis.readByte();
						System.out.println(a+"\r\n");
					
					//int b=dis.readInt();
					//System.out.println(b+"\r\n");
					
					acceptFile();
					
					JFrame jFrame=new JFrame();
					jFrame.setSize(630, 550);
					jFrame.setTitle("聊天室");
					jFrame.setLocationRelativeTo(null);
					jFrame.setLayout(new BorderLayout(0, 0));
					
					JPanel jp1=new JPanel();		
					jp1.setLayout(new FlowLayout());	
					JTextPane messagePane=new JTextPane();
					messagePane.setEditable(false);
					SimpleAttributeSet attrset = new SimpleAttributeSet(); //网上那位哥是把Java自带的类都写了一遍，其实这个只要用自带的就可以了
			        StyleConstants.setFontSize(attrset,24);
			        StyleConstants.setItalic(attrset, true);
					Document docs=messagePane.getDocument();					
					try {
			            docs.insertString(docs.getLength(), "聊天记录\n", attrset);//对文本进行追加
			        } catch (BadLocationException e) {  //  \n用于换行
			            e.printStackTrace();
			        }				
					JScrollPane jsMessage=new JScrollPane(messagePane);
					 jsMessage.setPreferredSize(new Dimension(400,400));//当它太长时，会显示不出他的滚条		
					jp1.add(jsMessage);
					
					sendmessage=new JTextField();
					sendmessage.setPreferredSize(new Dimension(400, 100));
					sendmessage.setHorizontalAlignment((int) JTextField.TOP_ALIGNMENT);
					
				
					jp1.add(sendmessage);
					jFrame.add(jp1,BorderLayout.CENTER);
					
					
					JPanel jp2=new JPanel();
					jFrame.add(jp2,BorderLayout.WEST);
					jp2.setLayout(new FlowLayout());
					
					JList<Client> jList=new JList<Client>();
					jlistmodel jk=new jlistmodel(client); 				
					jList.setModel(jk);   
					
					jlistrender jl=new jlistrender();
					jList.setCellRenderer(jl);
					JScrollPane js=new JScrollPane(jList);  //当元素比较多的时候设置这个滚轮
					js.setPreferredSize(new Dimension(200,500));//当它太长时，会显示不出他的滚条
					jp2.add(js);		
					jFrame.setVisible(true);
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void acceptFile()
	{
		boolean flag=true;
		byte b[];
		try {
			
		//flag=dis.readBoolean(); //可以正常读取布朗值
		System.out.println("客户端接受到信息，flag="+flag);
		if(flag==true)
		{
			Byte a=dis.readByte();
			System.out.println(a+"\r\n");
			 a=dis.readByte();
			System.out.println(a+"\r\n");
			 a=dis.readByte();
				System.out.println(a+"\r\n");
				 a=dis.readByte();
					System.out.println(a+"\r\n");
					 a=dis.readByte();
						System.out.println(a+"\r\n");
						
						
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
			
			
//			dis.readInt();//读出来的总是不对，。。不能用？？？？
//			
//			String fileasd=d.readLine();//为啥就是不能读取整型数据呢？
//			String filenamelength=d.readLine();//为啥就是不能读取整型数据呢？
//			
//			System.out.println("试验品"+fileasd);
//			System.out.println("传过来的文件名的长度为"+filenamelength);
//			int filenamelengthint=Integer.parseInt(filenamelength);
//			System.out.println("转为整型后为"+filenamelengthint);
//			byte[] filenamebyte=new byte[filenamelengthint];
//			dis.readFully(filenamebyte);//这个读出来的又不知道是什么鬼。。。。？？？？皖A顳D婰E蕷？？？
//		    String filename=new String(filenamebyte);//应该用string的构造方法来把byte数组变换为字符串！！！
//		    System.out.println("传过来的文件的名字是"+filename);
//		
//			int filelength=dis.readInt();
//			b=new byte[filelength];
//			dis.readFully(b);
//		
//
//			File f=new File("d:\\QQ\\images\\"+filename);
//			String filepath=f.getAbsolutePath();
//			FileOutputStream fos=new FileOutputStream(filepath);
//			DataOutputStream dos=new DataOutputStream(fos);
//			dos.write(b);
//			dos.flush();
//			dos.close();	
		}
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
