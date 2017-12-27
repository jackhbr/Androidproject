package client2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
	BufferedReader d;
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
				
		        d= new BufferedReader(new InputStreamReader(dis));
		        //����������ȷ�İ��ֽ�ת�����ַ������ı�������	
				
				String response=d.readLine(); // deprecated,��Ȼ���޳�ʹ�ã�
				System.out.println("����������  "+response);
				if(response.equals("loginsuccess"))//�����¼�ɹ�����ô����ʾ��һ������
				{
					jf.dispose();
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

}
