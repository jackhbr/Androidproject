package rankinglist;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.ls.LSException;
/**
 * �������ڽ���
 * @author jack
 *
 */
public class Main extends JFrame{   
	
	 
	private int b[]=new int[2];
	 File f;
	 URI uri;
	    URL url; 
	 /**
	  * ������
	  * @param args����������ø�
	  */
	public static void main(String[] args) {
		
		Main st=new Main();
		st.showUI();
		st.Music();
	}
	/**
	 * ��ʾ����ĺ���
	 */
	public void showUI()
	{		
		this.setTitle("����ɻ���ս");
		this.setSize(500, 600);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);//��������λ��
		this.setDefaultCloseOperation(3);
		listener ls=new listener();
		
		String arr[]={"��ʼ��Ϸ","��ͣ","����","���а�"};		
		Button bks=new Button("��ʼ��Ϸ"); //button�Ŀ���һֱ��ʾ����jbutton������һֱ��ʾ��
		bks.addActionListener(ls);
		this.add(bks);
		
		for(int i=1;i<arr.length;i++)
		{
			Button bk=new Button(arr[i]); //button�Ŀ���һֱ��ʾ����jbutton������һֱ��ʾ��
			bk.addActionListener(ls);
			this.add(bk);
			
		}
		
		
		//���ñ���ɫ��ͼƬҪ�Ƚϴ�
		 ImageIcon background = new ImageIcon(this.getClass().getResource("jc.jpg"));  
		 JLabel label = new JLabel(background); 
		 label.setBounds(0, 0, this.getWidth(), this.getHeight());  
		 JPanel imagePanel = (JPanel) this.getContentPane();
		 imagePanel.setOpaque(false);
		 this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
			
		this.addKeyListener(ls);
		this.setVisible(true);

		ls.setjf(this,bks,b);
				
	}
	public void Music()
	{  
	  try {      
	      f = new File("D:\\ring.wav"); 
	      uri = f.toURI();
	      url = uri.toURL();  //������ַ
	      AudioClip aau; 
	      aau = Applet.newAudioClip(url);
	      aau.loop();  //ѭ������
	      while(true);
	  } catch (Exception e) 
	  { e.printStackTrace();
	  } 
	}
	public void paint(Graphics g)   //��Ȼ���֣�ԭ����ͼƬ��ȥ���٣����ػ���٣�������ȫ���ػ�
	{
		super.paint(g);
		b[0]=1;
	//	ls.setjf(this,bks,stopPaint);��仰�о�Ӧ��Ҫ����������˵����Ϊÿ�θı䶼Ҫ����ȥ����������ǰ��Ĵ���
	}
}
