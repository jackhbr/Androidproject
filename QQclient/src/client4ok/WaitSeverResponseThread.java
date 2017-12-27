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
	JTextField sendmessage;//������Ϣ���ݵ�ȫ�ֱ���
	
	public WaitSeverResponseThread(DataInputStream dis,JFrame jf) {
		this.dis = dis;
		this.jf=jf;
	}
	public void run() 
	{
		while(true)
		{
			try {
				//���ص�Ӧ����loginsuccess\r\n
				//Ȼ����QQ����QQͷ�񣬺����б����
				
		      //  d= new BufferedReader(new InputStreamReader(dis));
		        //����������ȷ�İ��ֽ�ת�����ַ������ı�������	
				
				String response=dis.readUTF();
				System.out.println("����������  "+response);
				if(response.equals("loginsuccess"))//�����¼�ɹ�����ô����ʾ��һ������
				{
					jf.dispose();
					
					Byte a=dis.readByte();
					System.out.println(a+"\r\n");
					
					String friendName=dis.readUTF();
					System.out.println("�ͻ����յ��ĺ��ѵ�������"+friendName);
				
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
					jFrame.setTitle("������");
					jFrame.setLocationRelativeTo(null);
					jFrame.setLayout(new BorderLayout(0, 0));
					
					JPanel jp1=new JPanel();		
					jp1.setLayout(new FlowLayout());	
					JTextPane messagePane=new JTextPane();
					messagePane.setEditable(false);
					SimpleAttributeSet attrset = new SimpleAttributeSet(); //������λ���ǰ�Java�Դ����඼д��һ�飬��ʵ���ֻҪ���Դ��ľͿ�����
			        StyleConstants.setFontSize(attrset,24);
			        StyleConstants.setItalic(attrset, true);
					Document docs=messagePane.getDocument();					
					try {
			            docs.insertString(docs.getLength(), "�����¼\n", attrset);//���ı�����׷��
			        } catch (BadLocationException e) {  //  \n���ڻ���
			            e.printStackTrace();
			        }				
					JScrollPane jsMessage=new JScrollPane(messagePane);
					 jsMessage.setPreferredSize(new Dimension(400,400));//����̫��ʱ������ʾ�������Ĺ���		
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
					JScrollPane js=new JScrollPane(jList);  //��Ԫ�رȽ϶��ʱ�������������
					js.setPreferredSize(new Dimension(200,500));//����̫��ʱ������ʾ�������Ĺ���
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
			
		//flag=dis.readBoolean(); //����������ȡ����ֵ
		System.out.println("�ͻ��˽��ܵ���Ϣ��flag="+flag);
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
			
			
//			dis.readInt();//�����������ǲ��ԣ����������ã�������
//			
//			String fileasd=d.readLine();//Ϊɶ���ǲ��ܶ�ȡ���������أ�
//			String filenamelength=d.readLine();//Ϊɶ���ǲ��ܶ�ȡ���������أ�
//			
//			System.out.println("����Ʒ"+fileasd);
//			System.out.println("���������ļ����ĳ���Ϊ"+filenamelength);
//			int filenamelengthint=Integer.parseInt(filenamelength);
//			System.out.println("תΪ���ͺ�Ϊ"+filenamelengthint);
//			byte[] filenamebyte=new byte[filenamelengthint];
//			dis.readFully(filenamebyte);//������������ֲ�֪����ʲô������������������A�DD�LEʚ������
//		    String filename=new String(filenamebyte);//Ӧ����string�Ĺ��췽������byte����任Ϊ�ַ���������
//		    System.out.println("���������ļ���������"+filename);
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
