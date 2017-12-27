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
	Image img = new ImageIcon("3.jpg").getImage();//根目录，每一个斜杠后面要再加上一条斜杠
	
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
		
		ja.add("恭喜你获得的了胡哥独家赞助的茶具一套！！！");
		
		for(int i=0;i<mannum-1;i++)
		{
		ja.add("有点遗憾，不过你还是有获取胡哥中秋节红包的机会");
		}
		
		final JFrame akFrame=new JFrame();
		akFrame.setTitle("抽奖");
		
		akFrame.setSize(600,600);
		akFrame.setLocationRelativeTo(null);//放在设置大小后面才是真正的中央
		akFrame.setLayout(new BorderLayout());
		
		JButton jButton=new JButton("开始抽奖！");
		
		JPanel panel1 = new JPanel();
		panel1.add(jt);
		akFrame.add(panel1, BorderLayout.CENTER);
		akFrame.setVisible(true);//显示出来才能取得画笔
		
		 akFrame.getGraphics().drawImage(img, 0, 0, 300, 300,null);
		
		
		 akFrame.getGraphics().fillOval(0, 0, 300, 300);
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index=(int)(Math.random()*mannum);
				String as=ja.get(index);
				if(as.equals("恭喜你获得的了胡哥独家赞助的茶具一套！！！"))
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
				
				JOptionPane.showMessageDialog(null, as, "抽奖结果", JOptionPane.INFORMATION_MESSAGE);
				 jt.setText(as);
				 ja.remove(index);
				mannum--;
				
				if(mannum<=0)
				{
					akFrame.dispose();
					JOptionPane.showMessageDialog(null, "很抱歉，已经结束抽奖", "结束提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		
		JPanel panel = new JPanel();
		panel.add(jButton);
		akFrame.add(panel, BorderLayout.SOUTH);
		
		
	}

}
