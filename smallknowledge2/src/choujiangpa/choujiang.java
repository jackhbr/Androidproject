package choujiangpa;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class choujiang {

	private int mannum=10;
	ArrayList<String> ja=new ArrayList<String>();
	JTextArea jt=new JTextArea();
	Image img = new ImageIcon("3.jpg").getImage();//��Ŀ¼��ÿһ��б�ܺ���Ҫ�ټ���һ��б��
	
	Image img1 = new ImageIcon("4.jpg").getImage();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    choujiang jChoujiang=new choujiang();
    jChoujiang.UI();

	}
	public void UI() {
		
		ja.add("��ϲ���õ��˺�����������Ĳ��һ�ף�����");
		
		for(int i=0;i<mannum-1;i++)
		{
		ja.add("�е��ź��������㻹���л�ȡ��������ں���Ļ���");
		}
		
		final JFrame akFrame=new JFrame();
		akFrame.setTitle("�齱");
		
		akFrame.setSize(600,600);
		akFrame.setLocationRelativeTo(null);//�������ô�С�����������������
		akFrame.setLayout(new BorderLayout());
		
		JButton jButton=new JButton("��ʼ�齱��");
		
		JPanel panel1 = new JPanel();
		panel1.add(jt);
		akFrame.add(panel1, BorderLayout.CENTER);
		akFrame.setVisible(true);//��ʾ��������ȡ�û���
		
		 akFrame.getGraphics().drawImage(img, 0, 0, 300, 300,null);
		
		
		 akFrame.getGraphics().fillOval(0, 0, 300, 300);
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index=(int)(Math.random()*mannum);
				String as=ja.get(index);
				if(as.equals("��ϲ���õ��˺�����������Ĳ��һ�ף�����"))
				{
					System.out.println("g"+ akFrame.getGraphics());
					System.out.println("img"+img);
					 akFrame.getGraphics().drawImage(img, 300, 300, 300, 300,null);
				
					
				}
				else {
					System.out.println("g"+ akFrame.getGraphics());
					System.out.println("img"+img);
					 akFrame.getGraphics().drawImage(img1, 300, 300, 300, 300,null);
				}
				
				JOptionPane.showMessageDialog(null, as, "�齱���", JOptionPane.INFORMATION_MESSAGE);
				 jt.setText(as);
				 ja.remove(index);
				mannum--;
				
				if(mannum<=0)
				{
					akFrame.dispose();
					JOptionPane.showMessageDialog(null, "�ܱ�Ǹ���Ѿ������齱", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		
		JPanel panel = new JPanel();
		panel.add(jButton);
		akFrame.add(panel, BorderLayout.SOUTH);
		
		
	}

}
