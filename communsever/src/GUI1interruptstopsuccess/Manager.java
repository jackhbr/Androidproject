package GUI1interruptstopsuccess;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Manager {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager manager=new Manager();
		manager.showUI();
 
	}
	public void showUI()
	{
		JFrame jf=new JFrame();
		jf.setSize(600, 500);
		jf.setTitle("欢迎来到QQ");
		jf.setLocationRelativeTo(null);
		jf.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		jf.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Aclistener ac=new Aclistener();
		
		
		JButton button = new JButton("开启服务器");
		button.addActionListener(ac);
		panel.add(button);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(ac);
		panel.add(btnNewButton);
		
		JButton button_1 = new JButton("禁言");
		button_1.addActionListener(ac);
		panel.add(button_1);
		
		JButton button_2 = new JButton("踢人");
		button_2.addActionListener(ac);
		panel.add(button_2);
		jf.setVisible(true);
		
	}

}
