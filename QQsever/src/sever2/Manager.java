package sever2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Manager {


	public static void main(String[] args) {
			Manager ma=new Manager();
			ma.showUI();
	}
	
	public void showUI()
	{
		JFrame jf=new JFrame();
		jf.setSize(600, 500);
		jf.setTitle("QQ������");
		jf.setLocationRelativeTo(null);
		jf.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		jf.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Aclistener ac=new Aclistener();
		
		JButton button = new JButton("����������");
		button.addActionListener(ac);
		panel.add(button);

		JButton button_1 = new JButton("����");
		button_1.addActionListener(ac);
		panel.add(button_1);
		
		JButton button_2 = new JButton("����");
		button_2.addActionListener(ac);
		panel.add(button_2);
		jf.setVisible(true);
	}

}
