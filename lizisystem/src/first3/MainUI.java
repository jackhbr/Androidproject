package first3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainUI extends JFrame{
		private JFrame temF=this;
		public void initUI(){
		this.setTitle("Á£×Ó·ÂÕæ");
		this.setSize(800, 500);
		this.setLayout(new FlowLayout());
		JButton buStart=new JButton("Æô¶¯");
		this.add(buStart);
		this.setVisible(true);
		buStart.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
		ParticleControl pc=new ParticleControl(temF);
		pc.start();
		}
		});
		}
		public static void main(String[] args) {
		MainUI mi=new MainUI();
		mi.initUI();
		}
}