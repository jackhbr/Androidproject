package client5;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Manager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager ma=new Manager();
		ma.showUI();

	}
	public void showUI()
	{
		JFrame jf=new JFrame();
		Image img0 = new ImageIcon("D:/bgp.jpg").getImage();//���ô����ͼ��
		jf.setIconImage(img0);
		
		
		JLabel j0=new JLabel();
		Image img1 = new ImageIcon("D:/jc.jpg").getImage().getScaledInstance(80, 80, 0);
        j0.setIcon(new ImageIcon(img1));  
        j0.setBounds(40, 180, 80, 80);
		jf.add(j0);
		
		JTextField useCount=new JTextField();
		useCount.setBounds(130, 170, 200, 30);
		jf.add(useCount);
		
	
		
		JPasswordField usePassword=new JPasswordField();
		usePassword.setBounds(130, 200, 200, 30);
		jf.add(usePassword);
		
		
		JLabel j1=new JLabel("ע���˺�");
		j1.setBounds(340, 165, 70, 40);
		jf.add(j1);
		
		JLabel j2=new JLabel("�һ�����");
		j2.setBounds(340, 195, 70, 40);
		jf.add(j2);
		
		JCheckBox c0=new JCheckBox("��ס����");
		c0.setBounds(130, 230, 80, 25);
		jf.add(c0);
		
		JCheckBox c1=new JCheckBox("�Զ���¼");
		c1.setBounds(230, 230, 80, 25);
		jf.add(c1);
		
		ButtonL bl=new ButtonL(useCount,usePassword,jf);	
		JButton b0=new JButton("��¼");
		b0.addActionListener(bl);
		b0.setBounds(130, 260, 200, 30);
		jf.add(b0);
		
		JLabel j3=new JLabel();
		Image img2 = new ImageIcon("D:/bgp.jpg").getImage().getScaledInstance(430, 160, 0);
        j3.setIcon(new ImageIcon(img2));  
        j3.setBounds(0, 0, 430, 160);
		jf.add(j3);
		
//		JLabel j4=new JLabel("QQ");
//		j4.setBounds(200, 200, 100, 100);
//		jf.add(j4);
		
		
		
		jf.setLayout(null);//ʹ��setbounds�������ʱ����Ҫʹ���Ĳ���Ϊnull
		jf.setSize(430, 330);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		//jf.setUndecorated(true);����ʹ����ȥ���߿򣬿���ȥ��֮��ȴ�����ƶ����壬Ҳ����ʹ����С���ˣ�������ʱ������о��о�

		jf.setVisible(true);
		
//		Graphics g=jf.getGraphics();
//		g.drawString("QQ", 200, 200);
	}

}
