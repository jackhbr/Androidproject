package login_face;

public class JFrameTest {
	public static void main(String []args){
		JFrameTest tf=new JFrameTest();
		tf.showFrame();
	}
	
	public void showFrame(){
		javax.swing.JFrame frame=new javax.swing.JFrame();
		frame.setTitle("��һ���������");
		
		java.awt.FlowLayout f1=new java.awt.FlowLayout();
		frame.setLayout(f1);
		
		javax.swing.JLabel la_name=new javax.swing.JLabel("�˺�");
		javax.swing.JTextField jta_name=new javax.swing.JTextField(10);
		javax.swing.JLabel la1_name=new javax.swing.JLabel("����");
		javax.swing.JPasswordField jta1_name=new javax.swing.JPasswordField(10);
		
		javax.swing.JButton bu_name=new javax.swing.JButton("��½");
		frame.add(la_name);
		frame.add(jta_name);
		frame.add(la1_name);
		frame.add(jta1_name);
		frame.add(bu_name);
		
		frame.setSize(180,200);
		frame.setDefaultCloseOperation(3);//����ر�ʱ�����˳�
		frame.setVisible(true);
	//	ButtonListener mbl=new ButtonListener(jta_name);
		ButtonListener mbl1=new ButtonListener(jta_name,jta1_name);
		
		mbl1.setLogin(frame);
		
		bu_name.addActionListener(mbl1);//���������⣬�㲻������ĳ��������˳��
	//	bu_name.addActionListener(mbl);//������������
		
	}
	
	

}
