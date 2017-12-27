package login_face;

public class JFrameTest {
	public static void main(String []args){
		JFrameTest tf=new JFrameTest();
		tf.showFrame();
	}
	
	public void showFrame(){
		javax.swing.JFrame frame=new javax.swing.JFrame();
		frame.setTitle("第一个界面程序");
		
		java.awt.FlowLayout f1=new java.awt.FlowLayout();
		frame.setLayout(f1);
		
		javax.swing.JLabel la_name=new javax.swing.JLabel("账号");
		javax.swing.JTextField jta_name=new javax.swing.JTextField(10);
		javax.swing.JLabel la1_name=new javax.swing.JLabel("密码");
		javax.swing.JPasswordField jta1_name=new javax.swing.JPasswordField(10);
		
		javax.swing.JButton bu_name=new javax.swing.JButton("登陆");
		frame.add(la_name);
		frame.add(jta_name);
		frame.add(la1_name);
		frame.add(jta1_name);
		frame.add(bu_name);
		
		frame.setSize(180,200);
		frame.setDefaultCloseOperation(3);//窗体关闭时程序退出
		frame.setVisible(true);
	//	ButtonListener mbl=new ButtonListener(jta_name);
		ButtonListener mbl1=new ButtonListener(jta_name,jta1_name);
		
		mbl1.setLogin(frame);
		
		bu_name.addActionListener(mbl1);//这里有问题，搞不懂这里的程序的运行顺序！
	//	bu_name.addActionListener(mbl);//这里会产生覆盖
		
	}
	
	

}
