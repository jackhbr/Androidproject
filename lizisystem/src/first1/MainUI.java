package first1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainUI extends JFrame{
private JFrame temF=this;
private int startX=200;//�������
private int startY=200;
public void initUI()
{
	this.setTitle("���ӷ���");
	this.setSize(800, 500);
	this.setLayout(new FlowLayout());
	JButton buStart=new JButton("����");
	this.add(buStart);
	this.setVisible(true);
	buStart.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
		//�����̶߳���
		ParticleControl pc=new ParticleControl(temF,startX,startY);//����̹߳���һ������
		pc.start();//�����߳�
		}
		
	
	}

	);
	this.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		   startX=e.getX();
		   startY=e.getY();
		   System.out.println("stsatx="+startX+"   starty="+startY);
		   ParticleControl pc=new ParticleControl(temF,startX,startY);//����̹߳���һ������
			pc.start();//�����߳�
		}
	}
			);
}
public static void main(String[] args) {
	MainUI mi=new MainUI();
	mi.initUI();
}
}
